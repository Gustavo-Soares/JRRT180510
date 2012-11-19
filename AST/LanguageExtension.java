
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

abstract class LanguageExtension extends java.lang.Object {
    // Declared in LanguageExtensions.jrag at line 3

		private String name;

    // Declared in LanguageExtensions.jrag at line 5

		
		public LanguageExtension(String name) { 
			this.name = name;
		}

    // Declared in LanguageExtensions.jrag at line 9

		
		public String toString() {
			return name;
		}

    // Declared in LanguageExtensions.jrag at line 13

		
		public abstract ASTNode eliminateOn(ASTNode node);


}
