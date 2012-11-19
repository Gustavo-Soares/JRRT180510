
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class DoStmt extends BranchTargetStmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        targetOf_ContinueStmt_values = null;
        targetOf_BreakStmt_values = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        isDUbeforeCondition_Variable_values = null;
        canCompleteNormally_computed = false;
        succ_computed = false;
        succ_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt clone() throws CloneNotSupportedException {
        DoStmt node = (DoStmt)super.clone();
        node.targetOf_ContinueStmt_values = null;
        node.targetOf_BreakStmt_values = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.isDUbeforeCondition_Variable_values = null;
        node.canCompleteNormally_computed = false;
        node.succ_computed = false;
        node.succ_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt copy() {
      try {
          DoStmt node = (DoStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt fullCopy() {
        DoStmt res = (DoStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 594


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("do ");
    getStmt().toString(s);
    s.append("while(");
    getCondition().toString(s);
    s.append(");");
  }

    // Declared in TypeCheck.jrag at line 328

  public void typeCheck() {
    TypeDecl cond = getCondition().type();
    if(!cond.isBoolean()) {
      error("the type of \"" + getCondition() + "\" is " + cond.name() + " which is not boolean");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 212

    public DoStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 212
    public DoStmt(Stmt p0, Expr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 212
    public void setStmt(Stmt node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Stmt getStmt() {
        return (Stmt)getChild(0);
    }

    // Declared in java.ast at line 9


    public Stmt getStmtNoTransform() {
        return (Stmt)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 212
    public void setCondition(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getCondition() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getConditionNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected java.util.Map targetOf_ContinueStmt_values;
    // Declared in BranchTarget.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public boolean targetOf(ContinueStmt stmt) {
        Object _parameters = stmt;
if(targetOf_ContinueStmt_values == null) targetOf_ContinueStmt_values = new java.util.HashMap(4);
        if(targetOf_ContinueStmt_values.containsKey(_parameters)) {
            return ((Boolean)targetOf_ContinueStmt_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean targetOf_ContinueStmt_value = targetOf_compute(stmt);
        if(isFinal && num == state().boundariesCrossed)
            targetOf_ContinueStmt_values.put(_parameters, Boolean.valueOf(targetOf_ContinueStmt_value));
        return targetOf_ContinueStmt_value;
    }

    private boolean targetOf_compute(ContinueStmt stmt) {  return !stmt.hasLabel();  }

    protected java.util.Map targetOf_BreakStmt_values;
    // Declared in BranchTarget.jrag at line 79
 @SuppressWarnings({"unchecked", "cast"})     public boolean targetOf(BreakStmt stmt) {
        Object _parameters = stmt;
if(targetOf_BreakStmt_values == null) targetOf_BreakStmt_values = new java.util.HashMap(4);
        if(targetOf_BreakStmt_values.containsKey(_parameters)) {
            return ((Boolean)targetOf_BreakStmt_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean targetOf_BreakStmt_value = targetOf_compute(stmt);
        if(isFinal && num == state().boundariesCrossed)
            targetOf_BreakStmt_values.put(_parameters, Boolean.valueOf(targetOf_BreakStmt_value));
        return targetOf_BreakStmt_value;
    }

    private boolean targetOf_compute(BreakStmt stmt) {  return !stmt.hasLabel();  }

    // Declared in DefiniteAssignment.jrag at line 590
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
    if(!getCondition().isDAafterFalse(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 1072
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
    if(!isDUbeforeCondition(v)) // start a circular evaluation here
      return false;
    if(!getCondition().isDUafterFalse(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map isDUbeforeCondition_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 1086
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbeforeCondition(Variable v) {
        Object _parameters = v;
if(isDUbeforeCondition_Variable_values == null) isDUbeforeCondition_Variable_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(isDUbeforeCondition_Variable_values.containsKey(_parameters)) {
            Object _o = isDUbeforeCondition_Variable_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return ((Boolean)_o).booleanValue();
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            isDUbeforeCondition_Variable_values.put(_parameters, _value);
            _value.value = Boolean.valueOf(true);
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            boolean new_isDUbeforeCondition_Variable_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
                if (new_isDUbeforeCondition_Variable_value!=((Boolean)_value.value).booleanValue()) {
                    state.CHANGE = true;
                    _value.value = Boolean.valueOf(new_isDUbeforeCondition_Variable_value);
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                isDUbeforeCondition_Variable_values.put(_parameters, new_isDUbeforeCondition_Variable_value);
            }
            else {
                isDUbeforeCondition_Variable_values.remove(_parameters);
            state.RESET_CYCLE = true;
            isDUbeforeCondition_compute(v);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_isDUbeforeCondition_Variable_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            boolean new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
            if (state.RESET_CYCLE) {
                isDUbeforeCondition_Variable_values.remove(_parameters);
            }
            else if (new_isDUbeforeCondition_Variable_value!=((Boolean)_value.value).booleanValue()) {
                state.CHANGE = true;
                _value.value = new_isDUbeforeCondition_Variable_value;
            }
            return new_isDUbeforeCondition_Variable_value;
        }
        return ((Boolean)_value.value).booleanValue();
    }

    private boolean isDUbeforeCondition_compute(Variable v) {
    if(!getStmt().isDUafter(v))
      return false;
    else {
      for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
        ContinueStmt stmt = (ContinueStmt)iter.next();
        if(!stmt.isDUafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }

    // Declared in NameCheck.jrag at line 399
 @SuppressWarnings({"unchecked", "cast"})     public boolean continueLabel() {
        ASTNode$State state = state();
        boolean continueLabel_value = continueLabel_compute();
        return continueLabel_value;
    }

    private boolean continueLabel_compute() {  return true;  }

    // Declared in UnreachableStatements.jrag at line 88
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

    private boolean canCompleteNormally_compute() {  return getStmt().canCompleteNormally() && (!getCondition().isConstant() || !getCondition().isTrue())
    || reachableContinue() && (!getCondition().isConstant() || !getCondition().isTrue()) || reachableBreak();  }

    // Declared in ControlFlowGraph.jrag at line 443
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

    private SmallSet<CFGNode> succ_compute() {  return SmallSet.<CFGNode>singleton(getStmt());  }

    // Declared in ControlFlowGraph.jrag at line 754
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode targetForContinue() {
        ASTNode$State state = state();
        CFGNode targetForContinue_value = targetForContinue_compute();
        return targetForContinue_value;
    }

    private CFGNode targetForContinue_compute() {  return getCondition().first();  }

    // Declared in DefiniteAssignment.jrag at line 601
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getConditionNoTransform()){
    if(!getStmt().isDAafter(v))
      return false;
    for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
      ContinueStmt stmt = (ContinueStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }
        if(caller == getStmtNoTransform()) {
            return isDAbefore(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1085
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getConditionNoTransform()) {
            return isDUbeforeCondition(v);
        }
        if(caller == getStmtNoTransform()) {
            return isDUbefore(v) && getCondition().isDUafterTrue(v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in NameCheck.jrag at line 367
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_insideLoop(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 100
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 150
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 447
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getConditionNoTransform()) {
            return getCondition().followingWhenTrue().union(getCondition().followingWhenFalse());
        }
        if(caller == getStmtNoTransform()) {
            return SmallSet.<CFGNode>singleton(getCondition().first());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 445
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getConditionNoTransform()) {
            return SmallSet.<CFGNode>singleton(getStmt());
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 446
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getConditionNoTransform()) {
            return following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in Precedence.jrag at line 71
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getConditionNoTransform()) {
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in DeleteStmt.jrag at line 11
    public boolean Define_boolean_delete(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()){ setStmt(new EmptyStmt()); return true; }
        return getParent().Define_boolean_delete(this, caller);
    }

    // Declared in InsertEmptyStmt.jrag at line 36
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getStmtNoTransform()){
		setStmt(new Block(new List<Stmt>().add(s).add(getStmt())));
		return s;
	}
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }

    // Declared in InsertEmptyStmt.jrag at line 91
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getStmtNoTransform()){
		if(!getStmt().canCompleteNormally())
			throw new RefactoringException("cannot insert statement here");
		setStmt(new Block(new List<Stmt>().add(getStmt()).add(s)));
		return s;
	}
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
