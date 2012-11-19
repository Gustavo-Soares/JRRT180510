
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;
/* If we just store the method declaration an access should bind to, we get into trouble with
	 * ParMethodDecls: we might store a reference to some ParMethodDecl, then somebody flushes the
	 * caches, and a new ParMethodDecl will be created the next time we ask for it. Then inherited
	 * attributes won't work anymore, and things generally become unpleasant.
	 * 
	 * So we store a SavedMethodDecl instead: this is either a wrapper around a regular MethodDecl,
	 * or it represents a ParMethodDecl by its genericMethodDecl() and its list of arguments.*/
public class SavedMethodDecl extends java.lang.Object {
    // Declared in LockedMethodAccess.jrag at line 23

		private MethodDecl md;

    // Declared in LockedMethodAccess.jrag at line 25

		
		public SavedMethodDecl(MethodDecl md) {
			this.md = md;
		}

    // Declared in LockedMethodAccess.jrag at line 29

		
		public MethodDecl getDecl() {
			return md;
		}


}
