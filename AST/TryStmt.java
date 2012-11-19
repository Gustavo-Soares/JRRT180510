
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class TryStmt extends Stmt implements Cloneable, FinallyHost {
    public void flushCache() {
        super.flushCache();
        branches_computed = false;
        branches_value = null;
        branchesFromFinally_computed = false;
        branchesFromFinally_value = null;
        targetBranches_computed = false;
        targetBranches_value = null;
        escapedBranches_computed = false;
        escapedBranches_value = null;
        isDAafter_Variable_values = null;
        isDUbefore_Variable_values = null;
        isDUafter_Variable_values = null;
        reachableThrow_CatchClause_values = null;
        canCompleteNormally_computed = false;
        succ_computed = false;
        succ_value = null;
        uncaughtExceptions_computed = false;
        uncaughtExceptions_value = null;
        handlesException_TypeDecl_values = null;
        typeError_computed = false;
        typeError_value = null;
        typeRuntimeException_computed = false;
        typeRuntimeException_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TryStmt clone() throws CloneNotSupportedException {
        TryStmt node = (TryStmt)super.clone();
        node.branches_computed = false;
        node.branches_value = null;
        node.branchesFromFinally_computed = false;
        node.branchesFromFinally_value = null;
        node.targetBranches_computed = false;
        node.targetBranches_value = null;
        node.escapedBranches_computed = false;
        node.escapedBranches_value = null;
        node.isDAafter_Variable_values = null;
        node.isDUbefore_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.reachableThrow_CatchClause_values = null;
        node.canCompleteNormally_computed = false;
        node.succ_computed = false;
        node.succ_value = null;
        node.uncaughtExceptions_computed = false;
        node.uncaughtExceptions_value = null;
        node.handlesException_TypeDecl_values = null;
        node.typeError_computed = false;
        node.typeError_value = null;
        node.typeRuntimeException_computed = false;
        node.typeRuntimeException_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TryStmt copy() {
      try {
          TryStmt node = (TryStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TryStmt fullCopy() {
        TryStmt res = (TryStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BranchTarget.jrag at line 61

  public void collectBranches(Collection c) {
    c.addAll(escapedBranches());
  }

    // Declared in BranchTarget.jrag at line 162

  public Stmt branchTarget(Stmt branchStmt) {
    if(targetBranches().contains(branchStmt))
      return this;
    return super.branchTarget(branchStmt);
  }

    // Declared in BranchTarget.jrag at line 200

  public void collectFinally(Stmt branchStmt, ArrayList list) {
    if(hasFinally() && !branchesFromFinally().contains(branchStmt))
      list.add(this);
    if(targetBranches().contains(branchStmt))
      return;
    super.collectFinally(branchStmt, list);
  }

    // Declared in ExceptionHandling.jrag at line 203


  protected boolean reachedException(TypeDecl type) {
    boolean found = false;
    // found is true if the exception type is caught by a catch clause
    for(int i = 0; i < getNumCatchClause() && !found; i++)
      if(getCatchClause(i).handles(type))
        found = true;
    // if an exception is thrown in the block and the exception is not caught and
    // either there is no finally block or the finally block can complete normally
    if(!found && (!hasFinally() || getFinally().canCompleteNormally()) )
      if(getBlock().reachedException(type))
        return true;
    // even if the exception is caught by the catch clauses they may 
    // throw new exceptions
    for(int i = 0; i < getNumCatchClause() && found; i++)
      if(getCatchClause(i).reachedException(type))
        return true;
    return hasFinally() && getFinally().reachedException(type);
  }

    // Declared in PrettyPrint.jadd at line 706


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("try ");
    getBlock().toString(s);
    for(int i = 0; i < getNumCatchClause(); i++) {
      s.append(indent());
      getCatchClause(i).toString(s);
    }
    if(hasFinally()) {
      s.append(indent());
      s.append("finally ");
      getFinally().toString(s);
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 222

    public TryStmt() {
        super();

        setChild(new List(), 1);
        setChild(new Opt(), 2);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 222
    public TryStmt(Block p0, List<CatchClause> p1, Opt<Block> p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
    }

    // Declared in java.ast at line 18


  protected int numChildren() {
    return 3;
  }

    // Declared in java.ast at line 21

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 222
    public void setBlock(Block node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Block getBlock() {
        return (Block)getChild(0);
    }

    // Declared in java.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 222
    public void setCatchClauseList(List<CatchClause> list) {
        setChild(list, 1);
    }

    // Declared in java.ast at line 6


    public int getNumCatchClause() {
        return getCatchClauseList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public CatchClause getCatchClause(int i) {
        return (CatchClause)getCatchClauseList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addCatchClause(CatchClause node) {
        List<CatchClause> list = (parent == null || state == null) ? getCatchClauseListNoTransform() : getCatchClauseList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addCatchClauseNoTransform(CatchClause node) {
        List<CatchClause> list = getCatchClauseListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setCatchClause(CatchClause node, int i) {
        List<CatchClause> list = getCatchClauseList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<CatchClause> getCatchClauses() {
        return getCatchClauseList();
    }

    // Declared in java.ast at line 31

    public List<CatchClause> getCatchClausesNoTransform() {
        return getCatchClauseListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<CatchClause> getCatchClauseList() {
        List<CatchClause> list = (List<CatchClause>)getChild(1);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<CatchClause> getCatchClauseListNoTransform() {
        return (List<CatchClause>)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 222
    public void setFinallyOpt(Opt<Block> opt) {
        setChild(opt, 2);
    }

    // Declared in java.ast at line 6


    public boolean hasFinally() {
        return getFinallyOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Block getFinally() {
        return (Block)getFinallyOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setFinally(Block node) {
        getFinallyOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getFinallyOpt() {
        return (Opt<Block>)getChild(2);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getFinallyOptNoTransform() {
        return (Opt<Block>)getChildNoTransform(2);
    }

    protected boolean branches_computed = false;
    protected Collection branches_value;
    // Declared in BranchTarget.jrag at line 116
 @SuppressWarnings({"unchecked", "cast"})     public Collection branches() {
        if(branches_computed) {
            return branches_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        branches_value = branches_compute();
        if(isFinal && num == state().boundariesCrossed)
            branches_computed = true;
        return branches_value;
    }

    private Collection branches_compute() {
    HashSet set = new HashSet();
    getBlock().collectBranches(set);
    for(int i = 0; i < getNumCatchClause(); i++)
      getCatchClause(i).collectBranches(set);
    return set;
  }

    protected boolean branchesFromFinally_computed = false;
    protected Collection branchesFromFinally_value;
    // Declared in BranchTarget.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public Collection branchesFromFinally() {
        if(branchesFromFinally_computed) {
            return branchesFromFinally_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        branchesFromFinally_value = branchesFromFinally_compute();
        if(isFinal && num == state().boundariesCrossed)
            branchesFromFinally_computed = true;
        return branchesFromFinally_value;
    }

    private Collection branchesFromFinally_compute() {
    HashSet set = new HashSet();
    if(hasFinally())
      getFinally().collectBranches(set);
    return set;
  }

    protected boolean targetBranches_computed = false;
    protected Collection targetBranches_value;
    // Declared in BranchTarget.jrag at line 132
 @SuppressWarnings({"unchecked", "cast"})     public Collection targetBranches() {
        if(targetBranches_computed) {
            return targetBranches_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        targetBranches_value = targetBranches_compute();
        if(isFinal && num == state().boundariesCrossed)
            targetBranches_computed = true;
        return targetBranches_value;
    }

    private Collection targetBranches_compute() {
    HashSet set = new HashSet();
    if(hasFinally() && !getFinally().canCompleteNormally())
      set.addAll(branches());
    return set;
  }

    protected boolean escapedBranches_computed = false;
    protected Collection escapedBranches_value;
    // Declared in BranchTarget.jrag at line 140
 @SuppressWarnings({"unchecked", "cast"})     public Collection escapedBranches() {
        if(escapedBranches_computed) {
            return escapedBranches_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        escapedBranches_value = escapedBranches_compute();
        if(isFinal && num == state().boundariesCrossed)
            escapedBranches_computed = true;
        return escapedBranches_value;
    }

    private Collection escapedBranches_compute() {
    HashSet set = new HashSet();
    if(hasFinally())
      set.addAll(branchesFromFinally());
    if(!hasFinally() || getFinally().canCompleteNormally())
      set.addAll(branches());
    return set;
  }

    // Declared in DefiniteAssignment.jrag at line 667
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

    private boolean isDAafter_compute(Variable v) {
    // 16.2.15 4th bullet
    if(!hasFinally()) {
      if(!getBlock().isDAafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDAafter(v))
          return false;
      return true;
    }
    else {
      // 16.2.15 5th bullet
      if(getFinally().isDAafter(v))
        return true;
      if(!getBlock().isDAafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDAafter(v))
          return false;
      return true;
    }
  }

    // Declared in DefiniteAssignment.jrag at line 918
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFinally(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterFinally_Variable_value = isDUafterFinally_compute(v);
        return isDUafterFinally_Variable_value;
    }

    private boolean isDUafterFinally_compute(Variable v) {  return getFinally().isDUafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 921
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFinally(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterFinally_Variable_value = isDAafterFinally_compute(v);
        return isDAafterFinally_Variable_value;
    }

    private boolean isDAafterFinally_compute(Variable v) {  return getFinally().isDAafter(v);  }

    protected java.util.Map isDUbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 1189
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(isDUbefore_Variable_values.containsKey(_parameters)) {
            Object _o = isDUbefore_Variable_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return ((Boolean)_o).booleanValue();
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            isDUbefore_Variable_values.put(_parameters, _value);
            _value.value = Boolean.valueOf(true);
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            boolean new_isDUbefore_Variable_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_isDUbefore_Variable_value = isDUbefore_compute(v);
                if (new_isDUbefore_Variable_value!=((Boolean)_value.value).booleanValue()) {
                    state.CHANGE = true;
                    _value.value = Boolean.valueOf(new_isDUbefore_Variable_value);
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                isDUbefore_Variable_values.put(_parameters, new_isDUbefore_Variable_value);
            }
            else {
                isDUbefore_Variable_values.remove(_parameters);
            state.RESET_CYCLE = true;
            isDUbefore_compute(v);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_isDUbefore_Variable_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            boolean new_isDUbefore_Variable_value = isDUbefore_compute(v);
            if (state.RESET_CYCLE) {
                isDUbefore_Variable_values.remove(_parameters);
            }
            else if (new_isDUbefore_Variable_value!=((Boolean)_value.value).booleanValue()) {
                state.CHANGE = true;
                _value.value = new_isDUbefore_Variable_value;
            }
            return new_isDUbefore_Variable_value;
        }
        return ((Boolean)_value.value).booleanValue();
    }

    private boolean isDUbefore_compute(Variable v) {  return super.isDUbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 1225
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
    // 16.2.14 4th bullet
    if(!hasFinally()) {
      if(!getBlock().isDUafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDUafter(v))
          return false;
      return true;
    }
    else
      return getFinally().isDUafter(v);
  }

    protected java.util.Map reachableThrow_CatchClause_values;
    // Declared in ExceptionHandling.jrag at line 193
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachableThrow(CatchClause c) {
        Object _parameters = c;
if(reachableThrow_CatchClause_values == null) reachableThrow_CatchClause_values = new java.util.HashMap(4);
        if(reachableThrow_CatchClause_values.containsKey(_parameters)) {
            return ((Boolean)reachableThrow_CatchClause_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean reachableThrow_CatchClause_value = reachableThrow_compute(c);
        if(isFinal && num == state().boundariesCrossed)
            reachableThrow_CatchClause_values.put(_parameters, Boolean.valueOf(reachableThrow_CatchClause_value));
        return reachableThrow_CatchClause_value;
    }

    private boolean reachableThrow_compute(CatchClause c) {  return getBlock().reachedException(c.getParameter().type());  }

    // Declared in UnreachableStatements.jrag at line 113
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

    private boolean canCompleteNormally_compute() {
     boolean anyCatchClauseCompleteNormally = false;
     for(int i = 0; i < getNumCatchClause() && !anyCatchClauseCompleteNormally; i++)
       anyCatchClauseCompleteNormally = getCatchClause(i).getBlock().canCompleteNormally();
     return (getBlock().canCompleteNormally() || anyCatchClauseCompleteNormally) &&
       (!hasFinally() || getFinally().canCompleteNormally());
  }

    // Declared in ControlFlowGraph.jrag at line 536
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

    private SmallSet<CFGNode> succ_compute() {  return SmallSet.<CFGNode>singleton(getBlock());  }

    // Declared in ControlFlowGraph.jrag at line 594
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collectBranches() {
        ASTNode$State state = state();
        SmallSet<CFGNode> collectBranches_value = collectBranches_compute();
        return collectBranches_value;
    }

    private SmallSet<CFGNode> collectBranches_compute() {
		// Try
		SmallSet<CFGNode> branchesInTry = getBlock().collectBranches(); //add try
		EmptyStmt emptyStmt = new EmptyStmt();
		if(getBlock().canCompleteNormally())
			branchesInTry = branchesInTry.union(emptyStmt); //try_normally

		// Catch
		SmallSet<CFGNode> remainingBranches = SmallSet.empty();
		for(CFGNode branch : branchesInTry) {
			if(branch instanceof ThrowStmt) {
				ThrowStmt throwStmt = (ThrowStmt)branch;
				boolean caught = false;
				for (int i = 0; i < getNumCatchClause() && !caught; i++) {
					if(getCatchClause(i).handles(throwStmt.getExpr().type())) {
						caught = true;
						remainingBranches = //add catch
							remainingBranches.union(getCatchClause(i).getBlock().collectBranches());
						if(getCatchClause(i).getBlock().canCompleteNormally())
							remainingBranches = remainingBranches.union(emptyStmt);
					}
				}
				if (!caught) 
					remainingBranches = remainingBranches.union(throwStmt);  		
			}
			else
				remainingBranches = remainingBranches.union(branch);   	
		}

		if(!hasFinally()) return remainingBranches;

		// hasFinally
		SmallSet<CFGNode> branchesInFinally = SmallSet.empty();
		SmallSet<CFGNode> branchesInAll = SmallSet.empty();

		// Ensure that branchesInFinally does not contain EmptyStmt
		for(CFGNode branch : getFinally().collectBranches())
			if(!(branch instanceof EmptyStmt))
				branchesInFinally = branchesInFinally.union(branch);

		if(getFinally().canCompleteNormally()) { 
			// branches above Finally are available
			branchesInAll = branchesInAll.union(remainingBranches);
			// branches(in Finally) except EmptyStmt can replace the branches above
			branchesInAll = branchesInAll.union(branchesInFinally);
		} else {
			//Branches in Finally except EmptyStmt replace all the possible branches above 
			branchesInAll = branchesInAll.union(branchesInFinally);
		}        	     

		return branchesInAll;
	}

    // Declared in Exceptions.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<TypeDecl> uncaughtExceptions() {
        if(uncaughtExceptions_computed) {
            return uncaughtExceptions_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        uncaughtExceptions_value = uncaughtExceptions_compute();
        if(isFinal && num == state().boundariesCrossed)
            uncaughtExceptions_computed = true;
        return uncaughtExceptions_value;
    }

    private SmallSet<TypeDecl> uncaughtExceptions_compute() {
    // Create a set containing the remaining throws after a throw-catch match
    SmallSet<TypeDecl> res = SmallSet.empty();
    for(TypeDecl exn : getBlock().uncaughtExceptions()) {
      boolean caught = false;
      for(int i = 0; i < getNumCatchClause() && !caught; i++)
	  caught = getCatchClause(i).handles(exn);
      if(!caught)
	  res = res.union(exn);
    }
    // Add throws from catch blocks and finally
    for(int i = 0; i < getNumCatchClause(); i++)
      res = res.union(getCatchClause(i).getBlock().uncaughtExceptions());
    if (hasFinally())
      res = res.union(getFinally().uncaughtExceptions());
    return res;
  }

    protected java.util.Map handlesException_TypeDecl_values;
    // Declared in ExceptionHandling.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
        if(handlesException_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        if(isFinal && num == state().boundariesCrossed)
            handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
        return handlesException_TypeDecl_value;
    }

    protected boolean typeError_computed = false;
    protected TypeDecl typeError_value;
    // Declared in UnreachableStatements.jrag at line 136
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeError() {
        if(typeError_computed) {
            return typeError_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        typeError_value = getParent().Define_TypeDecl_typeError(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeError_computed = true;
        return typeError_value;
    }

    protected boolean typeRuntimeException_computed = false;
    protected TypeDecl typeRuntimeException_value;
    // Declared in UnreachableStatements.jrag at line 137
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeRuntimeException() {
        if(typeRuntimeException_computed) {
            return typeRuntimeException_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        typeRuntimeException_value = getParent().Define_TypeDecl_typeRuntimeException(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeRuntimeException_computed = true;
        return typeRuntimeException_value;
    }

    // Declared in DefiniteAssignment.jrag at line 666
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getFinallyOptNoTransform()) {
            return isDAbefore(v);
        }
        if(caller == getCatchClauseListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return getBlock().isDAbefore(v);
        }
        if(caller == getBlockNoTransform()) {
            return isDAbefore(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1216
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getFinallyOptNoTransform()){
    if(!getBlock().isDUeverywhere(v))
      return false;
    for(int i = 0; i < getNumCatchClause(); i++)
      if(!getCatchClause(i).getBlock().unassignedEverywhere(v, this))
        return false;
    return true;
  }
        if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(!getBlock().isDUafter(v))
      return false;
    if(!getBlock().isDUeverywhere(v))
      return false;
    return true;
  }
}
        if(caller == getBlockNoTransform()) {
            return isDUbefore(v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in ExceptionHandling.jrag at line 179
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        if(caller == getBlockNoTransform()){
    for(int i = 0; i < getNumCatchClause(); i++)
      if(getCatchClause(i).handles(exceptionType))
        return true;
    if(hasFinally() && !getFinally().canCompleteNormally())
      return true;
    return handlesException(exceptionType);
  }
        if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(hasFinally() && !getFinally().canCompleteNormally())
      return true;
    return handlesException(exceptionType);
  }
}
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }

    // Declared in UnreachableStatements.jrag at line 121
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getFinallyOptNoTransform()) {
            return reachable();
        }
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 125
    public boolean Define_boolean_reachableCatchClause(ASTNode caller, ASTNode child) {
        if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    TypeDecl type = getCatchClause(childIndex).getParameter().type();
    for(int i = 0; i < childIndex; i++)
      if(getCatchClause(i).handles(type))
        return false;
    if(reachableThrow(getCatchClause(childIndex)))
      return true;
    if(type.mayCatch(typeError()) || type.mayCatch(typeRuntimeException()))
      return true;
    return false;
  }
}
        return getParent().Define_boolean_reachableCatchClause(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 154
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getFinallyOptNoTransform()) {
            return reachable();
        }
        if(caller == getCatchClauseListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return reachable();
        }
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 544
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getFinallyOptNoTransform()){ 
		SmallSet<CFGNode> branches = collectBranches(); // all the branches accumulated at the end of Finally()
		SmallSet<CFGNode> succ = SmallSet.empty();

		for(CFGNode branch : branches) {
			// TODO: this is very ugly
			if(branch instanceof EmptyStmt) // can complete normally
				succ = succ.union(following());
			else if(branch instanceof BreakStmt)
				succ = succ.union(breakTarget((BreakStmt)branch));
			else if(branch instanceof ContinueStmt)
				succ = succ.union(continueTarget((ContinueStmt)branch));
			else if(branch instanceof ThrowStmt)
				succ = succ.union(throwTarget(((ThrowStmt)branch).getExpr().type()));
			else
				succ = succ.union(returnTarget());
		}   

		return succ;
	}
        if(caller == getCatchClauseListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : following();
        }
        if(caller == getBlockNoTransform()) {
            return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : following();
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 711
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__breakTarget(ASTNode caller, ASTNode child, BreakStmt stmt) {
        if(caller == getBlockNoTransform()) {
            return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : breakTarget(stmt);
        }
        return getParent().Define_SmallSet_CFGNode__breakTarget(this, caller, stmt);
    }

    // Declared in ControlFlowGraph.jrag at line 713
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__continueTarget(ASTNode caller, ASTNode child, ContinueStmt stmt) {
        if(caller == getBlockNoTransform()) {
            return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : continueTarget(stmt);
        }
        return getParent().Define_SmallSet_CFGNode__continueTarget(this, caller, stmt);
    }

    // Declared in ControlFlowGraph.jrag at line 715
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__returnTarget(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : returnTarget();
        }
        return getParent().Define_SmallSet_CFGNode__returnTarget(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 717
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__throwTarget(ASTNode caller, ASTNode child, TypeDecl exn) {
        if(caller == getBlockNoTransform()){
		for(int i = 0; i < getNumCatchClause(); i++) 
			if(getCatchClause(i).handles(exn))
				return SmallSet.<CFGNode>singleton(getCatchClause(i).getParameter());
		return hasFinally() ? SmallSet.<CFGNode>singleton(getFinally()) : throwTarget(exn);
	}
        return getParent().Define_SmallSet_CFGNode__throwTarget(this, caller, exn);
    }

    // Declared in ControlFlowGraph.jrag at line 726
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__uncheckedExnTarget(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()){
		SmallSet<CFGNode> res = SmallSet.empty();
		for(CatchClause clause : getCatchClauses()) {
			if(clause.handlesAllUncheckedExceptions())
				return res.union(clause.getParameter());
			if(clause.handlesUncheckedException())
				res = res.union(clause.getParameter());
		}
		return res.union(uncheckedExnTarget());
	}
        return getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, caller);
    }

    // Declared in InsertEmptyStmt.jrag at line 58
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getFinallyOptNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        if(caller == getBlockNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }

    // Declared in InsertEmptyStmt.jrag at line 119
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getFinallyOptNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        if(caller == getBlockNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
