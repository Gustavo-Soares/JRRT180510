
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class FileRange extends java.lang.Object {
    // Declared in FileRange.jadd at line 10


		public String filename;

    // Declared in FileRange.jadd at line 11

		public int sl, sc, el, ec;

    // Declared in FileRange.jadd at line 13


		public FileRange(String filename, int sl, int sc, int el, int ec) {
			this.filename = filename;
			this.sl = sl; this.sc = sc;
			this.el = el; this.ec = ec;
		}

    // Declared in FileRange.jadd at line 19


		public FileRange(String filename, int start, int end) {
			this.filename = filename;
			this.sl = ASTNode.getLine(start); this.sc = ASTNode.getColumn(start);
			this.el = ASTNode.getLine(end); this.ec = ASTNode.getColumn(end);
		}

    // Declared in FileRange.jadd at line 25


		public String toString() {
			return filename+":("+sl+", "+sc+")-("+el+", "+ec+")";
		}

    // Declared in FileRange.jadd at line 31

	
	public boolean startsBefore(FileRange that) {
		return this.sl < that.sl ||
		   this.sl == that.sl && this.sc < that.sc;
	}


}
