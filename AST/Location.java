
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Location {
    // Declared in Alias.jrag at line 4
 
		TypeDecl type();

    // Declared in Alias.jrag at line 5

		boolean isHeapLocation();

    // Declared in Alias.jrag at line 19

	
	public boolean isArrayElement();


}
