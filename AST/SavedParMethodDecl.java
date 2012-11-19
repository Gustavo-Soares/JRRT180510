
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class SavedParMethodDecl extends SavedMethodDecl {
    // Declared in LockedMethodAccess.jrag at line 35

		private ArrayList<TypeDecl> parms;

    // Declared in LockedMethodAccess.jrag at line 36

		public SavedParMethodDecl(ParMethodDecl pmd) {
			super(pmd.genericMethodDecl());
			parms = new ArrayList<TypeDecl>();
			for(Access acc : pmd.getTypeArguments())
				parms.add(acc.type());
		}

    // Declared in LockedMethodAccess.jrag at line 43

		
		public MethodDecl getDecl() {
			ArrayList<TypeDecl> parmtypes = new ArrayList<TypeDecl>();
			for(TypeDecl std : parms)
				parmtypes.add(std.refresh());
			return ((GenericMethodDecl)super.getDecl()).lookupParMethodDecl(parmtypes);
		}


}
