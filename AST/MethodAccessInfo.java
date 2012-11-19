
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class MethodAccessInfo extends java.lang.Object {
    // Declared in AccessMethod.jrag at line 19

		private MethodDecl target;

    // Declared in AccessMethod.jrag at line 20

		private TypeDecl source;

    // Declared in AccessMethod.jrag at line 21

		private TypeDecl bend;

    // Declared in AccessMethod.jrag at line 22

		private Collection<MethodDecl> competitors;

    // Declared in AccessMethod.jrag at line 23

		private boolean directlyVisible;

    // Declared in AccessMethod.jrag at line 25

		
		public MethodAccessInfo(MethodDecl target, TypeDecl td, 
								Collection<MethodDecl> competitors) {
			this.target = target;
			this.source = td;
			this.bend = td;
			this.competitors = competitors;
			this.directlyVisible = true;
		}

    // Declared in AccessMethod.jrag at line 44


	public MethodAccessInfo moveDownTo(TypeDecl td) {
		bend = td;
		if(!td.localMethods(target.name()).isEmpty())
			directlyVisible = false;
		else if(directlyVisible)
			source = td;
		return this;
	}

    // Declared in AccessMethod.jrag at line 53

		
	public MethodAccessInfo moveInto(TypeDecl td) {
		if(!td.memberMethods(target.name()).isEmpty())
			directlyVisible = false;
		return this;
	}

    // Declared in LockedMethodAccess.jrag at line 152

	
	// eliminating locked method accesses
	public Access eliminate(Expr qualifier, TypeDecl enclosing, boolean inStaticCtxt, List<Expr> args) {
		if(qualifier == null)
			return eliminate(enclosing, inStaticCtxt, args);
		else
			return eliminateQualified(qualifier, enclosing, inStaticCtxt, args);
	}

    // Declared in LockedMethodAccess.jrag at line 159

	
	public Access eliminate(TypeDecl enclosing, boolean inStaticCtxt, List<Expr> args) {
		if(!directlyVisible) {
			if(target.isStatic())
				return computeStaticAccess(enclosing, args);
			if(inStaticCtxt)
				return null;
			if(source == bend) {
				return computeThisAccess(enclosing, args);
			} else if(bend instanceof ClassDecl && source == ((ClassDecl)bend).superclass()) {
				return computeSuperAccess(enclosing, args);
			} else {
				return null;
			}
		} else {
			return constructAccess(args);
		}
	}

    // Declared in LockedMethodAccess.jrag at line 177

	
	public Access computeStaticAccess(TypeDecl enclosing, List<Expr> args) {
		return source.createLockedAccess().qualifiesAccess(constructAccess(args));
	}

    // Declared in LockedMethodAccess.jrag at line 181

	
	public Access computeThisAccess(TypeDecl enclosing, List<Expr> args) {
		MethodAccess ma = constructAccess(args);
		if(source == enclosing)
			return ma;
		else
			return bend.createLockedAccess().qualifiesAccess(new ThisAccess("this").qualifiesAccess(ma));
	}

    // Declared in LockedMethodAccess.jrag at line 189

	
	public Access computeSuperAccess(TypeDecl enclosing, List<Expr> args) {
		MethodAccess ma = constructAccess(args);
		if(bend == enclosing)
			return new SuperAccess("super").qualifiesAccess(ma);
		else
			return bend.createLockedAccess().qualifiesAccess(new SuperAccess("super").qualifiesAccess(ma));
	}

    // Declared in LockedMethodAccess.jrag at line 197


	protected MethodAccess constructAccess(List<Expr> args) {
		// check if any of the competitors is more specific than the target for
		// this argument list; then we need casts
		for(MethodDecl cand : competitors)
			if(cand.applicableTo(args) && cand.moreSpecificThan(target))
				return new MethodAccess(target.name(), insertCasts(args));
		return new MethodAccess(target.name(), args);
	}

    // Declared in LockedMethodAccess.jrag at line 206

	
	protected List<Expr> insertCasts(List<Expr> args) {
		List<Expr> new_args = new List<Expr>();
		for(int i=0;i<target.getNumParameter();++i) {
			ParameterDeclaration parm = target.getParameter(i);
			Expr arg = args.getChild(i);
			TypeDecl tp = parm.type();
			if(parm.isVariableArity() && !arg.type().methodInvocationConversionTo(tp)) {
				List<Expr> varargs = new List<Expr>();
				while(i<args.getNumChild())
					varargs.add(args.getChild(i++));
				new_args.addChild(new ArrayCreationExpr(tp.createLockedAccess(), new Opt(new ArrayInit(varargs))));
			} else {
				new_args.addChild(new CastExpr(tp.createLockedAccess(), arg));
			}
		}
		return new_args;
	}

    // Declared in LockedMethodAccess.jrag at line 224


	public Access eliminateQualified(Expr qualifier, TypeDecl enclosing,
			boolean inStaticCtxt, List<Expr> args) {
		MethodAccess ma = constructAccess(args);
		if(!directlyVisible) {
			if(!target.isStatic())
				return null;
			if(inStaticCtxt)
				if(qualifier.isTypeAccess() && qualifier.type().sameSourceDeclAs(source))
					return qualifier.qualifiesAccess(ma);
				else
					return null;
			if(source == bend && source.sameSourceDeclAs(qualifier.type()))
				return qualifier.qualifiesAccess(ma);
			else if(!qualifier.isTypeAccess() && qualifier.type().sameSourceDeclAs(bend))
				return new ParExpr(new CastExpr(source.createLockedAccess(), qualifier)).qualifiesAccess(ma);
			return null;
		} else {
			if(!target.accessibleFrom(enclosing))
				throw new RefactoringException("method not accessible");
			return qualifier.qualifiesAccess(ma);
		}
	}


}
