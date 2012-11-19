
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


// Statements


public abstract class Stmt extends ASTNode<ASTNode> implements Cloneable, CFGNode {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_computed = false;
        succ_computed = false;
        succ_value = null;
        reachingDefinitionsFor_Location_values = null;
        forward_cdep_computed = false;
        forward_cdep_value = null;
        backward_cdep_computed = false;
        backward_cdep_value = null;
        collect_BackwardEdge_CFGNode_values = null;
        collect_ForwardEdge_CFGNode_values = null;
        uncheckedExnTarget_computed = false;
        uncheckedExnTarget_value = null;
        following_computed = false;
        following_value = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Stmt clone() throws CloneNotSupportedException {
        Stmt node = (Stmt)super.clone();
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_computed = false;
        node.succ_computed = false;
        node.succ_value = null;
        node.reachingDefinitionsFor_Location_values = null;
        node.forward_cdep_computed = false;
        node.forward_cdep_value = null;
        node.backward_cdep_computed = false;
        node.backward_cdep_value = null;
        node.collect_BackwardEdge_CFGNode_values = null;
        node.collect_ForwardEdge_CFGNode_values = null;
        node.uncheckedExnTarget_computed = false;
        node.uncheckedExnTarget_value = null;
        node.following_computed = false;
        node.following_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in UnreachableStatements.jrag at line 14

  void checkUnreachableStmt() {
    if(!reachable() && reportUnreachable())
      error("statement is unreachable");
  }

    // Declared in AST.jrag at line 52

	
	public boolean isInBlock() {
		return getParent() != null && getParent().getParent() instanceof Block;
	}

    // Declared in BlockExt.jrag at line 18

	
	public Block enclosingBlock() {
		ASTNode n = this;
		while(n!=null && !(n instanceof Block))
			n = n.getParent();
		return (Block)n;
	}

    // Declared in InsertUnusedType.jrag at line 30

	
	// insert a local class before this statement
	public LocalClassDeclStmt insertLocalClass(ClassDecl cd) {
		LocalClassDeclStmt lcd = new LocalClassDeclStmt(cd);
		insertStmtBefore(lcd);
		cd.checkEnclosingTypeNames(hostType());
		hostBodyDecl().flushCaches();
		return lcd;
	}

    // Declared in IntroduceUnusedLocal.jrag at line 3

	// insert an unused local variable declaration before a given statement
	public VariableDeclaration insertUnusedLocal(VariableDeclaration decl) {
		Block block = this.enclosingBlock();
		if(block == null)
			throw new RefactoringException("local variable cannot be created outside block");
		// ensure that output compiles
		if(!block.canIntroduceLocal(decl.name()))
			throw new RefactoringException("local variable of same name exists");
		// ensure that bindings are preserved
		block.lockNames(Collections.singleton(decl.name()));
		insertStmtBefore(decl);
		block.hostType().flushCaches();
		return decl;
	}

    // Declared in PullStatementFromBlock.jrag at line 2

	public void pullFromBlock() {
		Block block = (Block)getParent().getParent();
		Block outer_block = (Block)block.getParent().getParent();
		int idx = outer_block.getIndexOfStmt(block);
		lockControlFlow();
		block.removeStmt(this);
		outer_block.insertStmt(idx, this);
		hostType().flushCaches();
	}

    // Declared in InsertEmptyStmt.jrag at line 5

	public EmptyStmt insertEmptyStmtBefore() {
		return (EmptyStmt)insertStmtBefore(new EmptyStmt());
	}

    // Declared in InsertEmptyStmt.jrag at line 9

	
	public EmptyStmt insertEmptyStmtAfter() {
		return (EmptyStmt)insertStmtAfter(new EmptyStmt());
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 198

    public Stmt() {
        super();


    }

    // Declared in java.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in ControlFlowGraph.jrag at line 49

    
    public SmallSet<CFGNode> pred() { return collPred(); }

    // Declared in ControlFlow.jrag at line 15

	
	public void lockControlFlow() {
		if(lockedSucc == null)
			lockedSucc = forward_cdep();
		if(lockedPred == null)
			lockedPred = backward_cdep();
	}

    // Declared in ControlFlow.jrag at line 22

	
	public void unlockControlFlow() {
		if(lockedSucc != null) {
			if(!forward_cdep().equals(lockedSucc))
				throw new RefactoringException("couldn't preserve control flow");
			lockedSucc = null;
		}
		if(lockedPred != null) {
			if(!backward_cdep().equals(lockedPred))
				throw new RefactoringException("couldn't preserve control flow");
			lockedPred = null;
		}
	}

    // Declared in ControlFlow.jrag at line 51

	
	public void backward_cdep(Collection<CFGNode> dep) {
		if(succ().size() > 1) {
			dep.add(this);
		} else {
			for(CFGNode p : pred())
				p.backward_cdep(dep);
		}
	}

    // Declared in DependencyEdges.jrag at line 86

	public IdentityHashMap<DependencyEdge, SmallSet<CFGNode>> getDependencies() {
		if(dependencies == null)
			dependencies = new IdentityHashMap<DependencyEdge, SmallSet<CFGNode>>();
		return dependencies;
	}

    // Declared in DependencyEdges.jrag at line 100

	
	public void lockDependencies(DependencyEdge... edges) {
		for(DependencyEdge e : edges)
			if(e.isStart(this))
				getDependencies().put(e, e.collect(this));
		super.lockDependencies(edges);
	}

    // Declared in DependencyEdges.jrag at line 115

	
	public void unlockDependencies(PreservationStrategy r) {
		DependencyEdge e = r.getEdge();
		if(e.isStart(this)) {
			SmallSet<CFGNode> old_edges = getDependencies().get(e);
			if(old_edges != null) {
				SmallSet<CFGNode> new_edges = e.collect(this);
				if(r.preserve(this) && !old_edges.subsetOf(new_edges))
					throw new RefactoringException("couldn't preserve edge: "+e);
				if(r.reflect(this) && !new_edges.subsetOf(old_edges))
					throw new RefactoringException("couldn't reflect edge: "+e);
			}
		}
		super.unlockDependencies(r);
	}

    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 327
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
        if(isDAafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return isDAbefore(v);  }

    protected java.util.Map isDUafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 778
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
        if(isDUafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {
    throw new Error("isDUafter in " + getClass().getName());
  }

    // Declared in LookupVariable.jrag at line 127
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresVariable(String name) {
        ASTNode$State state = state();
        boolean declaresVariable_String_value = declaresVariable_compute(name);
        return declaresVariable_String_value;
    }

    private boolean declaresVariable_compute(String name) {  return false;  }

    // Declared in NameCheck.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public boolean continueLabel() {
        ASTNode$State state = state();
        boolean continueLabel_value = continueLabel_compute();
        return continueLabel_value;
    }

    private boolean continueLabel_compute() {  return false;  }

    // Declared in PrettyPrint.jadd at line 761
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return true;  }

    protected boolean canCompleteNormally_computed = false;
    protected boolean canCompleteNormally_value;
    // Declared in UnreachableStatements.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean canCompleteNormally() {
        if(canCompleteNormally_computed) {
            return canCompleteNormally_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        canCompleteNormally_value = canCompleteNormally_compute();
        if(isFinal && num == state().boundariesCrossed)
            canCompleteNormally_computed = true;
        return canCompleteNormally_value;
    }

    private boolean canCompleteNormally_compute() {  return true;  }

    // Declared in ControlFlowGraph.jrag at line 752
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode targetForContinue() {
        ASTNode$State state = state();
        CFGNode targetForContinue_value = targetForContinue_compute();
        return targetForContinue_value;
    }

    private CFGNode targetForContinue_compute() {  return this;  }

    // Declared in ReachingDefinitions.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return false;  }

    // Declared in ReachingDefinitions.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return false;  }

    // Declared in ControlFlowGraph.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return this;  }

    protected boolean succ_computed = false;
    protected SmallSet<CFGNode> succ_value;
    // Declared in ControlFlowGraph.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> succ() {
        if(succ_computed) {
            return succ_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        succ_value = succ_compute();
        if(isFinal && num == state().boundariesCrossed)
            succ_computed = true;
        return succ_value;
    }

    private SmallSet<CFGNode> succ_compute() {  return following();  }

    // Declared in ControlFlowGraph.jrag at line 761
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> succStmt() {
        ASTNode$State state = state();
        SmallSet<Stmt> succStmt_value = succStmt_compute();
        return succStmt_value;
    }

    private SmallSet<Stmt> succStmt_compute() {
		SmallSet<Stmt> res = new SmallSet<Stmt>();
		for(CFGNode n : succ()) {
			if(n instanceof Stmt)
				res = res.union((Stmt)n);
			else
				res = res.union(n.succStmt());
		}
		return res;
	}

    // Declared in ControlFlowGraph.jrag at line 772
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> predStmt() {
        ASTNode$State state = state();
        SmallSet<Stmt> predStmt_value = predStmt_compute();
        return predStmt_value;
    }

    private SmallSet<Stmt> predStmt_compute() {
		SmallSet<Stmt> res = new SmallSet<Stmt>();
		for(CFGNode n : pred()) {
			if(n instanceof Stmt)
				res = res.union((Stmt)n);
			else
				res = res.union(n.predStmt());
		}
		return res;
	}

    protected java.util.Map reachingDefinitionsFor_Location_values;
    // Declared in ReachingDefinitions.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Definition> reachingDefinitionsFor(Location l) {
        Object _parameters = l;
if(reachingDefinitionsFor_Location_values == null) reachingDefinitionsFor_Location_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(reachingDefinitionsFor_Location_values.containsKey(_parameters)) {
            Object _o = reachingDefinitionsFor_Location_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<Definition>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            reachingDefinitionsFor_Location_values.put(_parameters, _value);
            _value.value = SmallSet.<Definition>empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<Definition> new_reachingDefinitionsFor_Location_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_reachingDefinitionsFor_Location_value = reachingDefinitionsFor_compute(l);
                if ((new_reachingDefinitionsFor_Location_value==null && (SmallSet<Definition>)_value.value!=null) || (new_reachingDefinitionsFor_Location_value!=null && !new_reachingDefinitionsFor_Location_value.equals((SmallSet<Definition>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_reachingDefinitionsFor_Location_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                reachingDefinitionsFor_Location_values.put(_parameters, new_reachingDefinitionsFor_Location_value);
            }
            else {
                reachingDefinitionsFor_Location_values.remove(_parameters);
            state.RESET_CYCLE = true;
            reachingDefinitionsFor_compute(l);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_reachingDefinitionsFor_Location_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<Definition> new_reachingDefinitionsFor_Location_value = reachingDefinitionsFor_compute(l);
            if (state.RESET_CYCLE) {
                reachingDefinitionsFor_Location_values.remove(_parameters);
            }
            else if ((new_reachingDefinitionsFor_Location_value==null && (SmallSet<Definition>)_value.value!=null) || (new_reachingDefinitionsFor_Location_value!=null && !new_reachingDefinitionsFor_Location_value.equals((SmallSet<Definition>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_reachingDefinitionsFor_Location_value;
            }
            return new_reachingDefinitionsFor_Location_value;
        }
        return (SmallSet<Definition>)_value.value;
    }

    private SmallSet<Definition> reachingDefinitionsFor_compute(Location l) {
		SmallSet<Definition> res = SmallSet.<Definition>empty();
		if(isReachingDefinitionFor(l)) {
			res = SmallSet.singleton((Definition)this);
			if(isBlockingDefinitionFor(l))
				return res;
		}
		for(CFGNode p : pred())
			res = res.union(p.reachingDefinitionsFor(l));
		return res;
	}

    protected boolean forward_cdep_computed = false;
    protected Collection<CFGNode> forward_cdep_value;
    // Declared in ControlFlow.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> forward_cdep() {
        if(forward_cdep_computed) {
            return forward_cdep_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        forward_cdep_value = forward_cdep_compute();
        if(isFinal && num == state().boundariesCrossed)
            forward_cdep_computed = true;
        return forward_cdep_value;
    }

    private Collection<CFGNode> forward_cdep_compute() {
		CFGNode p;
		for(p=this;p.succ().size() == 1;p=p.succ().iterator().next());
		Collection<CFGNode> dep = new HashSet<CFGNode>();
		for(CFGNode q : p.succ())
			dep.add(q);
		return dep;
	}

    protected boolean backward_cdep_computed = false;
    protected Collection<CFGNode> backward_cdep_value;
    // Declared in ControlFlow.jrag at line 44
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> backward_cdep() {
        if(backward_cdep_computed) {
            return backward_cdep_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        backward_cdep_value = backward_cdep_compute();
        if(isFinal && num == state().boundariesCrossed)
            backward_cdep_computed = true;
        return backward_cdep_value;
    }

    private Collection<CFGNode> backward_cdep_compute() {
		Collection<CFGNode> dep = new HashSet<CFGNode>();
		for(CFGNode p : pred())
			p.backward_cdep(dep);
		return dep;
	}

    protected java.util.Map collect_BackwardEdge_CFGNode_values;
    // Declared in DependencyEdges.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(BackwardEdge be, CFGNode start) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(be);
        _parameters.add(start);
if(collect_BackwardEdge_CFGNode_values == null) collect_BackwardEdge_CFGNode_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(collect_BackwardEdge_CFGNode_values.containsKey(_parameters)) {
            Object _o = collect_BackwardEdge_CFGNode_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<CFGNode>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            collect_BackwardEdge_CFGNode_values.put(_parameters, _value);
            _value.value = SmallSet.<CFGNode> empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<CFGNode> new_collect_BackwardEdge_CFGNode_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_collect_BackwardEdge_CFGNode_value = collect_compute(be, start);
                if ((new_collect_BackwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_BackwardEdge_CFGNode_value!=null && !new_collect_BackwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_collect_BackwardEdge_CFGNode_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                collect_BackwardEdge_CFGNode_values.put(_parameters, new_collect_BackwardEdge_CFGNode_value);
            }
            else {
                collect_BackwardEdge_CFGNode_values.remove(_parameters);
            state.RESET_CYCLE = true;
            collect_compute(be, start);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_collect_BackwardEdge_CFGNode_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<CFGNode> new_collect_BackwardEdge_CFGNode_value = collect_compute(be, start);
            if (state.RESET_CYCLE) {
                collect_BackwardEdge_CFGNode_values.remove(_parameters);
            }
            else if ((new_collect_BackwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_BackwardEdge_CFGNode_value!=null && !new_collect_BackwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_collect_BackwardEdge_CFGNode_value;
            }
            return new_collect_BackwardEdge_CFGNode_value;
        }
        return (SmallSet<CFGNode>)_value.value;
    }

    private SmallSet<CFGNode> collect_compute(BackwardEdge be, CFGNode start) {
		SmallSet<CFGNode> res = be.isTarget(start, this) ? SmallSet.singleton((CFGNode)this)
													  : SmallSet.<CFGNode>empty();
		if(be.terminates(start, this))
			return res;
		for(CFGNode p : pred())
			res = res.union(p.collect(be, start));
		return res;
	}

    protected java.util.Map collect_ForwardEdge_CFGNode_values;
    // Declared in DependencyEdges.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(ForwardEdge fe, CFGNode start) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(fe);
        _parameters.add(start);
if(collect_ForwardEdge_CFGNode_values == null) collect_ForwardEdge_CFGNode_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(collect_ForwardEdge_CFGNode_values.containsKey(_parameters)) {
            Object _o = collect_ForwardEdge_CFGNode_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<CFGNode>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            collect_ForwardEdge_CFGNode_values.put(_parameters, _value);
            _value.value = SmallSet.<CFGNode> empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<CFGNode> new_collect_ForwardEdge_CFGNode_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_collect_ForwardEdge_CFGNode_value = collect_compute(fe, start);
                if ((new_collect_ForwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_ForwardEdge_CFGNode_value!=null && !new_collect_ForwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_collect_ForwardEdge_CFGNode_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                collect_ForwardEdge_CFGNode_values.put(_parameters, new_collect_ForwardEdge_CFGNode_value);
            }
            else {
                collect_ForwardEdge_CFGNode_values.remove(_parameters);
            state.RESET_CYCLE = true;
            collect_compute(fe, start);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_collect_ForwardEdge_CFGNode_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<CFGNode> new_collect_ForwardEdge_CFGNode_value = collect_compute(fe, start);
            if (state.RESET_CYCLE) {
                collect_ForwardEdge_CFGNode_values.remove(_parameters);
            }
            else if ((new_collect_ForwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_ForwardEdge_CFGNode_value!=null && !new_collect_ForwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_collect_ForwardEdge_CFGNode_value;
            }
            return new_collect_ForwardEdge_CFGNode_value;
        }
        return (SmallSet<CFGNode>)_value.value;
    }

    private SmallSet<CFGNode> collect_compute(ForwardEdge fe, CFGNode start) {
		SmallSet<CFGNode> res = fe.isTarget(start, this) ? SmallSet.singleton((CFGNode)this)
												  	  : SmallSet.<CFGNode>empty();
		if(fe.terminates(start, this))
			return res;
		for(CFGNode p : succ())
			res = res.union(p.collect(fe, start));
		return res;
	}

    // Declared in DefiniteAssignment.jrag at line 234
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        ASTNode$State state = state();
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        return isDAbefore_Variable_value;
    }

    // Declared in DefiniteAssignment.jrag at line 692
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        ASTNode$State state = state();
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        return isDUbefore_Variable_value;
    }

    // Declared in LookupMethod.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupMethod(String name) {
        ASTNode$State state = state();
        Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
        return lookupMethod_String_value;
    }

    // Declared in LookupType.jrag at line 96
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupType(String packageName, String typeName) {
        ASTNode$State state = state();
        TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
        return lookupType_String_String_value;
    }

    // Declared in LookupType.jrag at line 174
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        ASTNode$State state = state();
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        return lookupType_String_value;
    }

    // Declared in LookupVariable.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in TypeAnalysis.jrag at line 512
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl enclosingBodyDecl() {
        ASTNode$State state = state();
        BodyDecl enclosingBodyDecl_value = getParent().Define_BodyDecl_enclosingBodyDecl(this, null);
        return enclosingBodyDecl_value;
    }

    // Declared in TypeAnalysis.jrag at line 584
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in UnreachableStatements.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachable() {
        ASTNode$State state = state();
        boolean reachable_value = getParent().Define_boolean_reachable(this, null);
        return reachable_value;
    }

    // Declared in UnreachableStatements.jrag at line 145
 @SuppressWarnings({"unchecked", "cast"})     public boolean reportUnreachable() {
        ASTNode$State state = state();
        boolean reportUnreachable_value = getParent().Define_boolean_reportUnreachable(this, null);
        return reportUnreachable_value;
    }

    // Declared in ControlFlowGraph.jrag at line 648
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> breakTarget(BreakStmt stmt) {
        ASTNode$State state = state();
        SmallSet<CFGNode> breakTarget_BreakStmt_value = getParent().Define_SmallSet_CFGNode__breakTarget(this, null, stmt);
        return breakTarget_BreakStmt_value;
    }

    // Declared in ControlFlowGraph.jrag at line 649
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> continueTarget(ContinueStmt stmt) {
        ASTNode$State state = state();
        SmallSet<CFGNode> continueTarget_ContinueStmt_value = getParent().Define_SmallSet_CFGNode__continueTarget(this, null, stmt);
        return continueTarget_ContinueStmt_value;
    }

    // Declared in ControlFlowGraph.jrag at line 650
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> returnTarget() {
        ASTNode$State state = state();
        SmallSet<CFGNode> returnTarget_value = getParent().Define_SmallSet_CFGNode__returnTarget(this, null);
        return returnTarget_value;
    }

    // Declared in ControlFlowGraph.jrag at line 651
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> throwTarget(TypeDecl exn) {
        ASTNode$State state = state();
        SmallSet<CFGNode> throwTarget_TypeDecl_value = getParent().Define_SmallSet_CFGNode__throwTarget(this, null, exn);
        return throwTarget_TypeDecl_value;
    }

    protected boolean uncheckedExnTarget_computed = false;
    protected SmallSet<CFGNode> uncheckedExnTarget_value;
    // Declared in ControlFlowGraph.jrag at line 653
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> uncheckedExnTarget() {
        if(uncheckedExnTarget_computed) {
            return uncheckedExnTarget_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        uncheckedExnTarget_value = getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, null);
        if(isFinal && num == state().boundariesCrossed)
            uncheckedExnTarget_computed = true;
        return uncheckedExnTarget_value;
    }

    // Declared in AccessVariable.jrag at line 117
 @SuppressWarnings({"unchecked", "cast"})     public UnknownVarAccess unknownVarAccess() {
        ASTNode$State state = state();
        UnknownVarAccess unknownVarAccess_value = getParent().Define_UnknownVarAccess_unknownVarAccess(this, null);
        return unknownVarAccess_value;
    }

    // Declared in AccessVariable.jrag at line 134
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess accessVariable_Variable_value = getParent().Define_SymbolicVarAccess_accessVariable(this, null, decl);
        return accessVariable_Variable_value;
    }

    // Declared in DeleteStmt.jrag at line 5
 @SuppressWarnings({"unchecked", "cast"})     public boolean delete() {
        ASTNode$State state = state();
        boolean delete_value = getParent().Define_boolean_delete(this, null);
        return delete_value;
    }

    // Declared in InsertEmptyStmt.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public Stmt insertStmtBefore(Stmt s) {
        ASTNode$State state = state();
        Stmt insertStmtBefore_Stmt_value = getParent().Define_Stmt_insertStmtBefore(this, null, s);
        return insertStmtBefore_Stmt_value;
    }

    // Declared in InsertEmptyStmt.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public Stmt insertStmtAfter(Stmt s) {
        ASTNode$State state = state();
        Stmt insertStmtAfter_Stmt_value = getParent().Define_Stmt_insertStmtAfter(this, null, s);
        return insertStmtAfter_Stmt_value;
    }

    protected boolean following_computed = false;
    protected SmallSet<CFGNode> following_value;
    // Declared in ControlFlowGraph.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> following() {
        if(following_computed) {
            return following_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        following_value = getParent().Define_SmallSet_CFGNode__following(this, null);
        if(isFinal && num == state().boundariesCrossed)
            following_computed = true;
        return following_value;
    }

    // Declared in PrettyPrint.jadd at line 351
    public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return indent();
        }
        return getParent().Define_String_typeDeclIndent(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected boolean CFGNode_collPred_computed = false;
    protected SmallSet<CFGNode> CFGNode_collPred_value;
    // Declared in ControlFlowGraph.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collPred() {
        if(CFGNode_collPred_computed) {
            return CFGNode_collPred_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        CFGNode_collPred_value = collPred_compute();
        if(isFinal && num == state().boundariesCrossed)
            CFGNode_collPred_computed = true;
        return CFGNode_collPred_value;
    }

    java.util.Set CFGNode_collPred_contributors;
    public java.util.Set CFGNode_collPred_contributors() {
        if(CFGNode_collPred_contributors == null) CFGNode_collPred_contributors  = new ASTNode$State.IdentityHashSet(4);
        return CFGNode_collPred_contributors;
    }
    private SmallSet<CFGNode> collPred_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof TypeDecl))
            node = node.getParent();
        TypeDecl root = (TypeDecl)node;
        root.collect_contributors_CFGNode_collPred();
        CFGNode_collPred_value = SmallSet.mutable();
        if(CFGNode_collPred_contributors != null)
        for(java.util.Iterator iter = CFGNode_collPred_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_CFGNode_CFGNode_collPred(CFGNode_collPred_value);
        }
        return CFGNode_collPred_value;
    }

    protected void collect_contributors_CFGNode_collPred() {
        // Declared in ControlFlowGraph.jrag at line 37
        for(java.util.Iterator iter = (succ()).iterator(); iter.hasNext(); ) {
            CFGNode ref = (CFGNode)iter.next();
            if(ref != null)
            ref.CFGNode_collPred_contributors().add(this);
        }
        super.collect_contributors_CFGNode_collPred();
    }
    protected void contributeTo_CFGNode_CFGNode_collPred(SmallSet<CFGNode> collection) {
        super.contributeTo_CFGNode_CFGNode_collPred(collection);
        collection.add(this);
    }

}
