
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Visible {
    // Declared in Visibility.jrag at line 9

		public boolean isPrivate();

    // Declared in Visibility.jrag at line 10

		public boolean isProtected();

    // Declared in Visibility.jrag at line 11

		public boolean isPublic();

    // Declared in Visibility.jrag at line 12

		public Modifiers getModifiers();

    // Declared in Visibility.jrag at line 19

	
	public int getVisibility();


}
