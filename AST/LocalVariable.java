
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;
// for the purposes of refactoring, local variables include parameters
public interface LocalVariable extends Variable, SymbolicVarAccess {
    // Declared in LocalVariable.jrag at line 4

		public BodyDecl enclosingBodyDecl();

    // Declared in AccessVariable.jrag at line 85

	public SymbolicVarAccess moveInto(Scope s);


    // Declared in AccessVariable.jrag at line 90

	public boolean isUnknownVarAccess();


    // Declared in AccessVariable.jrag at line 93

	public SymbolicVarAccess ensureStatic();


    // Declared in AccessVariable.jrag at line 96

	public SymbolicVarAccess ensureFinal();


    // Declared in LockedVariableAccess.jrag at line 133

	
	public Access asAccess(Expr qualifier, TypeDecl enclosing);


}
