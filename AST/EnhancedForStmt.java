
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class EnhancedForStmt extends BranchTargetStmt implements Cloneable, VariableScope, Scope {
    public void flushCache() {
        super.flushCache();
        targetOf_ContinueStmt_values = null;
        targetOf_BreakStmt_values = null;
        canCompleteNormally_computed = false;
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        succ_computed = false;
        succ_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public EnhancedForStmt clone() throws CloneNotSupportedException {
        EnhancedForStmt node = (EnhancedForStmt)super.clone();
        node.targetOf_ContinueStmt_values = null;
        node.targetOf_BreakStmt_values = null;
        node.canCompleteNormally_computed = false;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.succ_computed = false;
        node.succ_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public EnhancedForStmt copy() {
      try {
          EnhancedForStmt node = (EnhancedForStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public EnhancedForStmt fullCopy() {
        EnhancedForStmt res = (EnhancedForStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in EnhancedFor.jrag at line 15

  // type checking
	public void typeCheck() {
		if (!getExpr().type().isArrayDecl() && !getExpr().type().isIterable()) {
			error("type " + getExpr().type().name() + 
			      " of expression in foreach is neither array type nor java.lang.Iterable");
		}	
    else if(getExpr().type().isArrayDecl() && !getExpr().type().componentType().assignConversionTo(getVariableDeclaration().type(), null))
      error("parameter of type " + getVariableDeclaration().type().typeName() + " can not be assigned an element of type " + getExpr().type().componentType().typeName()); 
    else if(getExpr().type().isIterable() && !getExpr().type().isUnknown()) {
      MethodDecl iterator = (MethodDecl)getExpr().type().memberMethods("iterator").iterator().next();
      MethodDecl next = (MethodDecl)iterator.type().memberMethods("next").iterator().next();
      TypeDecl componentType = next.type();
      if(!componentType.assignConversionTo(getVariableDeclaration().type(), null))
        error("parameter of type " + getVariableDeclaration().type().typeName() + " can not be assigned an element of type " + componentType.typeName()); 
    }
	}

    // Declared in EnhancedFor.jrag at line 58

  
	// pretty printing
  public void toString(StringBuffer s) {
    s.append("for (");
    getVariableDeclaration().getModifiers().toString(s);
    getVariableDeclaration().getTypeAccess().toString(s);
    s.append(" " + getVariableDeclaration().name());
    s.append(" : ");
    getExpr().toString(s);
    s.append(") ");
    getStmt().toString(s);
  }

    // Declared in EnhancedFor.ast at line 3
    // Declared in EnhancedFor.ast line 1

    public EnhancedForStmt() {
        super();


    }

    // Declared in EnhancedFor.ast at line 10


    // Declared in EnhancedFor.ast line 1
    public EnhancedForStmt(VariableDeclaration p0, Expr p1, Stmt p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
    }

    // Declared in EnhancedFor.ast at line 16


  protected int numChildren() {
    return 3;
  }

    // Declared in EnhancedFor.ast at line 19

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in EnhancedFor.ast at line 2
    // Declared in EnhancedFor.ast line 1
    public void setVariableDeclaration(VariableDeclaration node) {
        setChild(node, 0);
    }

    // Declared in EnhancedFor.ast at line 5

    public VariableDeclaration getVariableDeclaration() {
        return (VariableDeclaration)getChild(0);
    }

    // Declared in EnhancedFor.ast at line 9


    public VariableDeclaration getVariableDeclarationNoTransform() {
        return (VariableDeclaration)getChildNoTransform(0);
    }

    // Declared in EnhancedFor.ast at line 2
    // Declared in EnhancedFor.ast line 1
    public void setExpr(Expr node) {
        setChild(node, 1);
    }

    // Declared in EnhancedFor.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(1);
    }

    // Declared in EnhancedFor.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    // Declared in EnhancedFor.ast at line 2
    // Declared in EnhancedFor.ast line 1
    public void setStmt(Stmt node) {
        setChild(node, 2);
    }

    // Declared in EnhancedFor.ast at line 5

    public Stmt getStmt() {
        return (Stmt)getChild(2);
    }

    // Declared in EnhancedFor.ast at line 9


    public Stmt getStmtNoTransform() {
        return (Stmt)getChildNoTransform(2);
    }

    // Declared in EnhancedFor.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet localLookupVariable_String_value = localLookupVariable_compute(name);
        return localLookupVariable_String_value;
    }

    private SimpleSet localLookupVariable_compute(String name) {
		if(getVariableDeclaration().name().equals(name)) {
      return SimpleSet.emptySet.add(getVariableDeclaration());
    }
 	  return lookupVariable(name);
	}

    protected java.util.Map targetOf_ContinueStmt_values;
    // Declared in EnhancedFor.jrag at line 74
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
    // Declared in EnhancedFor.jrag at line 75
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

    // Declared in EnhancedFor.jrag at line 78
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

    private boolean canCompleteNormally_compute() {  return reachable();  }

    // Declared in EnhancedFor.jrag at line 82
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
    if(!getExpr().isDAafter(v))
      return false;
    /*
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    */
    return true;
  }

    // Declared in EnhancedFor.jrag at line 98
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
    if(!getExpr().isDUafter(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    // Declared in EnhancedFor.jrag at line 113
 @SuppressWarnings({"unchecked", "cast"})     public boolean continueLabel() {
        ASTNode$State state = state();
        boolean continueLabel_value = continueLabel_compute();
        return continueLabel_value;
    }

    private boolean continueLabel_compute() {  return true;  }

    // Declared in ControlFlowGraph.jrag at line 468
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

    private SmallSet<CFGNode> succ_compute() {  return SmallSet.<CFGNode>singleton(getExpr().first());  }

    // Declared in ControlFlowGraph.jrag at line 757
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode targetForContinue() {
        ASTNode$State state = state();
        CFGNode targetForContinue_value = targetForContinue_compute();
        return targetForContinue_value;
    }

    private CFGNode targetForContinue_compute() {  return getVariableDeclaration();  }

    // Declared in AccessVariable.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasVariable(String name) {
        ASTNode$State state = state();
        boolean hasVariable_String_value = hasVariable_compute(name);
        return hasVariable_String_value;
    }

    private boolean hasVariable_compute(String name) {  return getVariableDeclaration().name().equals(name);  }

    // Declared in AccessVariable.jrag at line 368
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess localAccessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess localAccessVariable_Variable_value = localAccessVariable_compute(decl);
        return localAccessVariable_Variable_value;
    }

    private SymbolicVarAccess localAccessVariable_compute(Variable decl) {
		if(getVariableDeclaration() == decl)
			return getVariableDeclaration();
		return accessVariable(decl).moveInto(this);
	}

    // Declared in EnhancedFor.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in EnhancedFor.jrag at line 41
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtNoTransform()) {
            return localLookupVariable(name);
        }
        if(caller == getExprNoTransform()) {
            return localLookupVariable(name);
        }
        if(caller == getVariableDeclarationNoTransform()) {
            return localLookupVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in EnhancedFor.jrag at line 43
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getVariableDeclarationNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 48
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return this;
        }
        if(caller == getExprNoTransform()) {
            return this;
        }
        if(caller == getVariableDeclarationNoTransform()) {
            return this;
        }
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 70
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getVariableDeclarationNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 71
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getVariableDeclarationNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 72
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getVariableDeclarationNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 79
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in EnhancedFor.jrag at line 96
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getStmtNoTransform()) {
            return getExpr().isDAafter(v);
        }
        if(caller == getExprNoTransform()) {
            return v == getVariableDeclaration() || isDAbefore(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in EnhancedFor.jrag at line 110
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getStmtNoTransform()) {
            return getExpr().isDUafter(v);
        }
        if(caller == getExprNoTransform()) {
            return v != getVariableDeclaration() && isDUbefore(v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in EnhancedFor.jrag at line 112
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_insideLoop(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 471
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return SmallSet.<CFGNode>singleton(getVariableDeclaration());
        }
        if(caller == getVariableDeclarationNoTransform()) {
            return following().union(getStmt());
        }
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(getVariableDeclaration());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in Precedence.jrag at line 78
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in AccessVariable.jrag at line 365
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getStmtNoTransform()) {
            return localAccessVariable(decl);
        }
        if(caller == getExprNoTransform()) {
            return localAccessVariable(decl);
        }
        if(caller == getVariableDeclarationNoTransform()) {
            return localAccessVariable(decl);
        }
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

    // Declared in RenameVariable.jrag at line 43
    public Stmt Define_Stmt_getArea(ASTNode caller, ASTNode child) {
        if(caller == getVariableDeclarationNoTransform()) {
            return this;
        }
        return getParent().Define_Stmt_getArea(this, caller);
    }

    // Declared in DeleteStmt.jrag at line 15
    public boolean Define_boolean_delete(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()){ setStmt(new EmptyStmt()); return true; }
        return getParent().Define_boolean_delete(this, caller);
    }

    // Declared in InsertEmptyStmt.jrag at line 47
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getStmtNoTransform()){
		setStmt(new Block(new List<Stmt>().add(s).add(getStmt())));
		return s;
	}
        if(caller == getVariableDeclarationNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }

    // Declared in InsertEmptyStmt.jrag at line 106
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getStmtNoTransform()){
		if(!getStmt().canCompleteNormally())
			throw new RefactoringException("cannot insert statement here");
		setStmt(new Block(new List<Stmt>().add(getStmt()).add(s)));
		return s;
	}
        if(caller == getVariableDeclarationNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
