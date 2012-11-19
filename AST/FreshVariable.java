
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface FreshVariable extends Variable {
    // Declared in FreshVariables.jrag at line 5
 
		String pickFreshName();

    // Declared in FreshVariables.jrag at line 19

	
	public boolean canIntroduceLocal(String name);


    // Declared in FreshVariables.jrag at line 23

	
	public String name();


    // Declared in FreshVariables.jrag at line 107

	
	// check whether the name conflicts with any of the decls
	public boolean conflicts(String name, Collection<Declaration> decls);


}
