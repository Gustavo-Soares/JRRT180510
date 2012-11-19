
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class SavedMethodDeclSubstituted extends SavedMethodDecl {
    // Declared in LockedMethodAccess.jrag at line 52

		private TypeDecl host;

    // Declared in LockedMethodAccess.jrag at line 54


		public SavedMethodDeclSubstituted(MethodDeclSubstituted mds) {
			super(mds.getOriginal());
			host = mds.hostType();
		}

    // Declared in LockedMethodAccess.jrag at line 59

		
		public MethodDecl getDecl() {
			TypeDecl host = this.host.refresh();
			MethodDecl md = super.getDecl();
			host.localMethodsSignatureMap();
			for(BodyDecl bd : host.getBodyDecls())
				if(bd instanceof MethodDeclSubstituted && ((MethodDeclSubstituted)bd).getOriginal() == md)
					return (MethodDecl)bd;
			throw new Error("no such method");
		}


}
