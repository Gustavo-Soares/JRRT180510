
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface LockableName {
    // Declared in Locking.jrag at line 27

		public ASTNode lock();

    // Declared in Locking.jrag at line 28

		public Expr unlock(Expr qualifier);

    // Declared in Locking.jrag at line 29

		public boolean isLocked();

    // Declared in Locking.jrag at line 36

	
	public ASTNode lockAllNames();


}
