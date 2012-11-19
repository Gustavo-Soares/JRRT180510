
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class SymbolicFieldAccess extends java.lang.Object implements SymbolicVarAccess {
    // Declared in AccessVariable.jrag at line 40

		private FieldDeclaration target;

    // Declared in AccessVariable.jrag at line 41

		private TypeDecl source;

    // Declared in AccessVariable.jrag at line 42

		private TypeDecl bend;

    // Declared in AccessVariable.jrag at line 43

		private boolean directlyVisible;

    // Declared in AccessVariable.jrag at line 45
  // i.e., neither shadowed nor hidden
		
		public SymbolicFieldAccess(FieldDeclaration target) {
			this.target = target;
			this.source = target.hostType();
			this.bend = target.hostType();
			this.directlyVisible = true;
		}

    // Declared in AccessVariable.jrag at line 52

		
		public SymbolicFieldAccess moveDownTo(TypeDecl td) {
			bend = td;
			if(!td.localFields(target.name()).isEmpty())
				directlyVisible = false;
			else
				source = td;
			return this;
		}

    // Declared in AccessVariable.jrag at line 61

		
		public SymbolicFieldAccess moveInto(Scope s) {
			if(s.hasVariable(target.name()))
				directlyVisible = false;
			return this;
		}

    // Declared in AccessVariable.jrag at line 67

		
		public SymbolicFieldAccess ensureStatic() {
			return target.isStatic() ? this : target.unknownVarAccess();
		}

    // Declared in AccessVariable.jrag at line 71

		
		public SymbolicFieldAccess ensureFinal() {
			return target.isFinal() ? this : target.unknownVarAccess();
		}

    // Declared in AccessVariable.jrag at line 75

		
		public SymbolicFieldAccess ensureAccessible(Expr e) {
			if(target instanceof FieldDeclaration && e.mayAccess((FieldDeclaration)target))
				return this;
			return target.unknownVarAccess();
		}

    // Declared in AccessVariable.jrag at line 81

		
		public boolean isUnknownVarAccess() { return false; }

    // Declared in LockedVariableAccess.jrag at line 83

	
	// eliminating locked variable accesses
	public Access asAccess(Expr qualifier, TypeDecl enclosing) {
		if(target.isStatic()) {
			return target.hostType().createLockedAccess().qualifiesAccess(new VarAccess(target.name()));
		} else {
			if(directlyVisible) {
				if(qualifier != null)
					return qualifier.qualifiesAccess(new VarAccess(target.name()));
				return new VarAccess(target.name());
			} else {
				VarAccess va = new VarAccess(target.name());
				if(qualifier == null) {
					if(source.sameSourceDeclAs(bend)) {
						if(bend.equals(enclosing)) {                                             // this.f
							return new ThisAccess("this").qualifiesAccess(va);
						} else {                                                                       // B.this.f
							return bend.createLockedAccess().qualifiesAccess(
									new ThisAccess("this").qualifiesAccess(va));
						}
					} else if(bend.isClassDecl() && source.equals(((ClassDecl)bend).superclass())) {
						if(bend.equals(enclosing)) {                                             // super.f
							return new SuperAccess("super").qualifiesAccess(va);
						} else {
							return bend.createLockedAccess().qualifiesAccess(                               // B.super.f
									new SuperAccess("super").qualifiesAccess(va));
						}
					} else {
						if(bend.equals(enclosing)) {
							return new ParExpr(new CastExpr(source.createLockedAccess(),                    // ((S)this).f 
									new ThisAccess("this"))).qualifiesAccess(va);
						} else {
							return new ParExpr(new CastExpr(source.createLockedAccess(),                    // ((S)B.this).f
									bend.createLockedAccess().qualifiesAccess(
											new ThisAccess("this")))).qualifiesAccess(va);
						}
					}
				} else {
					if(!bend.equals(qualifier.type()))
						throw new RefactoringException("cannot access variable");
					if(source.equals(qualifier.type()))
						return qualifier.qualifiesAccess(va);
					return new ParExpr(new CastExpr(source.createLockedAccess(), qualifier)).qualifiesAccess(va);
				}
			}
		}
	}


}
