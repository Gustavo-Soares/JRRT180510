
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

abstract class IrreflexiveBackwardEdge extends BackwardEdge {
    // Declared in DependencyEdges.jrag at line 18

		public SmallSet<CFGNode> collect(CFGNode start) {
			if(!isStart(start))
				return SmallSet.<CFGNode>empty();
			SmallSet<CFGNode> res = SmallSet.<CFGNode>empty();
			for(CFGNode p : (SmallSet<CFGNode>)start.pred())
				res = res.union(p.collect(this, start));
			return res;
		}


}
