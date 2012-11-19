
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

abstract class DependencyEdge extends java.lang.Object {
    // Declared in DependencyEdges.jrag at line 3

		protected abstract boolean isStart(CFGNode node);

    // Declared in DependencyEdges.jrag at line 4

		protected abstract boolean isTarget(CFGNode start, CFGNode end);

    // Declared in DependencyEdges.jrag at line 5

		public abstract SmallSet<CFGNode> collect(CFGNode start);


}
