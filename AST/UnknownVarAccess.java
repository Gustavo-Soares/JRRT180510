
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class UnknownVarAccess extends SymbolicFieldAccess {
    // Declared in AccessVariable.jrag at line 101

		public UnknownVarAccess(FieldDeclaration unknownField) {
			super(unknownField);
		}

    // Declared in AccessVariable.jrag at line 105

		
		public UnknownVarAccess moveDownTo(TypeDecl td) { return this; }

    // Declared in AccessVariable.jrag at line 106

		public UnknownVarAccess moveInto(Scope s) { return this; }

    // Declared in AccessVariable.jrag at line 107

		public UnknownVarAccess ensureStatic() { return this; }

    // Declared in AccessVariable.jrag at line 108

		public UnknownVarAccess ensureFinal() { return this; }

    // Declared in AccessVariable.jrag at line 109

		public UnknownVarAccess ensureAccessible(Expr e) { return this; }

    // Declared in AccessVariable.jrag at line 111

		
		public boolean isUnknownVarAccess() { return true; }

    // Declared in LockedVariableAccess.jrag at line 129

	
	public Access asAccess(Expr qualifier, TypeDecl enclosing) {
		throw new RefactoringException("cannot access variable");
	}


}
