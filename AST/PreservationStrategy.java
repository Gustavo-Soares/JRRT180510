
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class PreservationStrategy extends java.lang.Object {
    // Declared in DependencyEdges.jrag at line 71

		private DependencyEdge edge;

    // Declared in DependencyEdges.jrag at line 73

		
		public PreservationStrategy(DependencyEdge edge) {
			this.edge = edge;
		}

    // Declared in DependencyEdges.jrag at line 77

		
		public DependencyEdge getEdge() {
			return edge;
		}

    // Declared in DependencyEdges.jrag at line 81

		
		public boolean preserve(CFGNode n) { return true; }

    // Declared in DependencyEdges.jrag at line 82

		public boolean reflect(CFGNode n) { return true; }


}
