
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface SymbolicVarAccess {
    // Declared in AccessVariable.jrag at line 32

		SymbolicVarAccess moveInto(Scope s);

    // Declared in AccessVariable.jrag at line 33

		boolean isUnknownVarAccess();

    // Declared in AccessVariable.jrag at line 34

		public SymbolicVarAccess ensureFinal();

    // Declared in AccessVariable.jrag at line 35

		public SymbolicVarAccess ensureStatic();

    // Declared in AccessVariable.jrag at line 36

		Access asAccess(Expr qualifier, TypeDecl enclosing);

}
