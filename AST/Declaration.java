
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Declaration {
    // Declared in Declaration.jrag at line 3
 
		String name();

    // Declared in FreshVariables.jrag at line 126

	
	public void collectAllDecls(Collection<Declaration> res);


}
