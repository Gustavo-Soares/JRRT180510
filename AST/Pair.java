
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class Pair<F,S> extends java.lang.Object {
    // Declared in Pair.jadd at line 3

		private F fst;

    // Declared in Pair.jadd at line 4

		private S snd;

    // Declared in Pair.jadd at line 6

		
		public Pair(F fst, S snd) {
			this.fst = fst;
			this.snd = snd;
		}

    // Declared in Pair.jadd at line 11

		
		public F fst() {
			return fst;
		}

    // Declared in Pair.jadd at line 15

		
		public S snd() {
			return snd;
		}


}
