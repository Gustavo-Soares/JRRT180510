
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

abstract class ForwardEdge extends DependencyEdge {
    // Declared in DependencyEdges.jrag at line 40

		public abstract boolean terminates(CFGNode start, CFGNode end);

    // Declared in DependencyEdges.jrag at line 41

		public SmallSet<CFGNode> collect(CFGNode start) {
			if(!isStart(start))
				return SmallSet.<CFGNode>empty();
			return start.collect(this, start);
		}


}
