
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;
// more precise analysis for fields of the receiver object (well-defined, since our analysis is always intra-procedural)
public class FieldInReceiver extends java.lang.Object implements Location {
    // Declared in DataFlow.jrag at line 142

		private MethodDecl method;

    // Declared in DataFlow.jrag at line 143

		private FieldDeclaration field;

    // Declared in DataFlow.jrag at line 145

		
		public FieldInReceiver(MethodDecl method, FieldDeclaration field) {
			this.method = method;
			this.field = field;
		}

    // Declared in DataFlow.jrag at line 150

		
		public MethodDecl getMethod() {
			return method;
		}

    // Declared in DataFlow.jrag at line 154

		
		public FieldDeclaration getField() {
			return field;
		}

    // Declared in DataFlow.jrag at line 158

		
		public TypeDecl type() {
			return field.type();
		}

    // Declared in DataFlow.jrag at line 162

		
		public boolean isHeapLocation() {
			return true;
		}

    // Declared in DataFlow.jrag at line 166

		
		public boolean isArrayElement() {
			return false;
		}

    // Declared in DataFlow.jrag at line 170

		
		public boolean equals(Object o) {
			if(!(o instanceof FieldInReceiver))
				return false;
			FieldInReceiver that = (FieldInReceiver)o;
			return this.method.equals(that.method) && this.field.equals(that.field);
		}


}
