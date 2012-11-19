
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface CFGNode {
    // Declared in ControlFlowGraph.jrag at line 49

    
    public SmallSet<CFGNode> pred();


    // Declared in ControlFlow.jrag at line 15

	
	public void lockControlFlow();


    // Declared in ControlFlow.jrag at line 22

	
	public void unlockControlFlow();


    // Declared in ControlFlow.jrag at line 51

	
	public void backward_cdep(Collection<CFGNode> dep);


    // Declared in DependencyEdges.jrag at line 86

	public IdentityHashMap<DependencyEdge, SmallSet<CFGNode>> getDependencies();


    // Declared in DependencyEdges.jrag at line 100

	
	public void lockDependencies(DependencyEdge... edges);


    // Declared in DependencyEdges.jrag at line 115

	
	public void unlockDependencies(PreservationStrategy r);


    // Declared in ControlFlowGraph.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> succ();
    // Declared in ControlFlowGraph.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first();
    // Declared in ControlFlowGraph.jrag at line 761
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> succStmt();
    // Declared in ControlFlowGraph.jrag at line 772
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> predStmt();
    // Declared in ReachingDefinitions.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Definition> reachingDefinitionsFor(Location l);
    // Declared in ControlFlow.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> forward_cdep();
    // Declared in ControlFlow.jrag at line 44
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> backward_cdep();
    // Declared in DependencyEdges.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(BackwardEdge be, CFGNode start);
    // Declared in DependencyEdges.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(ForwardEdge fe, CFGNode start);
    // Declared in ControlFlowGraph.jrag at line 29
    public SmallSet<CFGNode> following();
    public java.util.Set CFGNode_collPred_contributors();
}
