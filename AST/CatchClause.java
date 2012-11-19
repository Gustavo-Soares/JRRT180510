
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class CatchClause extends ASTNode<ASTNode> implements Cloneable, VariableScope, Parameterisable, Scope {
    public void flushCache() {
        super.flushCache();
        parameterDeclaration_String_values = null;
        accessParameter_Variable_values = null;
        typeThrowable_computed = false;
        typeThrowable_value = null;
        lookupVariable_String_values = null;
        reachableCatchClause_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause clone() throws CloneNotSupportedException {
        CatchClause node = (CatchClause)super.clone();
        node.parameterDeclaration_String_values = null;
        node.accessParameter_Variable_values = null;
        node.typeThrowable_computed = false;
        node.typeThrowable_value = null;
        node.lookupVariable_String_values = null;
        node.reachableCatchClause_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause copy() {
      try {
          CatchClause node = (CatchClause)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause fullCopy() {
        CatchClause res = (CatchClause)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 721


  public void toString(StringBuffer s) {
    s.append("catch (");
    getParameter().toString(s);
    s.append(") ");
    getBlock().toString(s);
  }

    // Declared in TypeCheck.jrag at line 368


  public void typeCheck() {
    if(!getParameter().type().instanceOf(typeThrowable()))
      error("*** The catch variable must extend Throwable");
  }

    // Declared in ParameterExt.jrag at line 25

	
	public int getNumParameter() { return 1; }

    // Declared in ParameterExt.jrag at line 26

	public ParameterDeclaration getParameter(int i) {
		if(i == 0)
			return getParameter();
		throw new IllegalArgumentException("catch clauses only have one argument");
	}

    // Declared in ParameterExt.jrag at line 31

	public int getIndexOfParameter(ParameterDeclaration pd) {
		return pd == this.getParameter() ? 0 : -1;
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 223

    public CatchClause() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 223
    public CatchClause(ParameterDeclaration p0, Block p1) {
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
    // Declared in java.ast line 223
    public void setParameter(ParameterDeclaration node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public ParameterDeclaration getParameter() {
        return (ParameterDeclaration)getChild(0);
    }

    // Declared in java.ast at line 9


    public ParameterDeclaration getParameterNoTransform() {
        return (ParameterDeclaration)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 223
    public void setBlock(Block node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Block getBlock() {
        return (Block)getChild(1);
    }

    // Declared in java.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(1);
    }

    // Declared in ExceptionHandling.jrag at line 189
 @SuppressWarnings({"unchecked", "cast"})     public boolean handles(TypeDecl exceptionType) {
        ASTNode$State state = state();
        boolean handles_TypeDecl_value = handles_compute(exceptionType);
        return handles_TypeDecl_value;
    }

    private boolean handles_compute(TypeDecl exceptionType) {  return !getParameter().type().isUnknown()
    && exceptionType.instanceOf(getParameter().type());  }

    protected java.util.Map parameterDeclaration_String_values;
    // Declared in LookupVariable.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet parameterDeclaration(String name) {
        Object _parameters = name;
if(parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
        if(parameterDeclaration_String_values.containsKey(_parameters)) {
            return (SimpleSet)parameterDeclaration_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
        return parameterDeclaration_String_value;
    }

    private SimpleSet parameterDeclaration_compute(String name) {  return getParameter().name().equals(name) ? (ParameterDeclaration)getParameter() : SimpleSet.emptySet;  }

    // Declared in ControlFlowGraph.jrag at line 741
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesAllUncheckedExceptions() {
        ASTNode$State state = state();
        boolean handlesAllUncheckedExceptions_value = handlesAllUncheckedExceptions_compute();
        return handlesAllUncheckedExceptions_value;
    }

    private boolean handlesAllUncheckedExceptions_compute() {  return handles(typeRuntimeException()) && handles(typeError());  }

    // Declared in ControlFlowGraph.jrag at line 746
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesUncheckedException() {
        ASTNode$State state = state();
        boolean handlesUncheckedException_value = handlesUncheckedException_compute();
        return handlesUncheckedException_value;
    }

    private boolean handlesUncheckedException_compute() {  return handles(typeRuntimeException()) || handles(typeError())
			|| getParameter().type().instanceOf(typeRuntimeException())
			|| getParameter().type().instanceOf(typeError());  }

    // Declared in AccessVariable.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasVariable(String name) {
        ASTNode$State state = state();
        boolean hasVariable_String_value = hasVariable_compute(name);
        return hasVariable_String_value;
    }

    private boolean hasVariable_compute(String name) {  return getParameter().name().equals(name);  }

    protected java.util.Map accessParameter_Variable_values;
    // Declared in AccessVariable.jrag at line 233
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessParameter(Variable decl) {
        Object _parameters = decl;
if(accessParameter_Variable_values == null) accessParameter_Variable_values = new java.util.HashMap(4);
        if(accessParameter_Variable_values.containsKey(_parameters)) {
            return (SymbolicVarAccess)accessParameter_Variable_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SymbolicVarAccess accessParameter_Variable_value = accessParameter_compute(decl);
        if(isFinal && num == state().boundariesCrossed)
            accessParameter_Variable_values.put(_parameters, accessParameter_Variable_value);
        return accessParameter_Variable_value;
    }

    private SymbolicVarAccess accessParameter_compute(Variable decl) {  return getParameter() == decl ? getParameter() : unknownVarAccess();  }

    protected boolean typeThrowable_computed = false;
    protected TypeDecl typeThrowable_value;
    // Declared in LookupType.jrag at line 68
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeThrowable() {
        if(typeThrowable_computed) {
            return typeThrowable_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        typeThrowable_value = getParent().Define_TypeDecl_typeThrowable(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeThrowable_computed = true;
        return typeThrowable_value;
    }

    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        Object _parameters = name;
if(lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
        if(lookupVariable_String_values.containsKey(_parameters)) {
            return (SimpleSet)lookupVariable_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        if(isFinal && num == state().boundariesCrossed)
            lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
        return lookupVariable_String_value;
    }

    protected boolean reachableCatchClause_computed = false;
    protected boolean reachableCatchClause_value;
    // Declared in UnreachableStatements.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachableCatchClause() {
        if(reachableCatchClause_computed) {
            return reachableCatchClause_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        reachableCatchClause_value = getParent().Define_boolean_reachableCatchClause(this, null);
        if(isFinal && num == state().boundariesCrossed)
            reachableCatchClause_computed = true;
        return reachableCatchClause_value;
    }

    // Declared in ControlFlowGraph.jrag at line 739
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeRuntimeException() {
        ASTNode$State state = state();
        TypeDecl typeRuntimeException_value = getParent().Define_TypeDecl_typeRuntimeException(this, null);
        return typeRuntimeException_value;
    }

    // Declared in ControlFlowGraph.jrag at line 740
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeError() {
        ASTNode$State state = state();
        TypeDecl typeError_value = getParent().Define_TypeDecl_typeError(this, null);
        return typeError_value;
    }

    // Declared in AccessVariable.jrag at line 120
 @SuppressWarnings({"unchecked", "cast"})     public UnknownVarAccess unknownVarAccess() {
        ASTNode$State state = state();
        UnknownVarAccess unknownVarAccess_value = getParent().Define_UnknownVarAccess_unknownVarAccess(this, null);
        return unknownVarAccess_value;
    }

    // Declared in AccessVariable.jrag at line 138
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess accessVariable_Variable_value = getParent().Define_SymbolicVarAccess_accessVariable(this, null, decl);
        return accessVariable_Variable_value;
    }

    // Declared in LookupVariable.jrag at line 83
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getParameterNoTransform()) {
            return parameterDeclaration(name);
        }
        if(caller == getBlockNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    return lookupVariable(name);
  }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in NameCheck.jrag at line 290
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return this;
        }
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 86
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 122
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachableCatchClause();
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 87
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 88
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 89
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in VariableArityParameters.jrag at line 23
    public boolean Define_boolean_variableArityValid(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_variableArityValid(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 539
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return SmallSet.<CFGNode>singleton(getBlock());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ParameterExt.jrag at line 39
    public Parameterisable Define_Parameterisable_parameterOwner(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return this;
        }
        return getParent().Define_Parameterisable_parameterOwner(this, caller);
    }

    // Declared in AccessVariable.jrag at line 209
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getParameterNoTransform()) {
            return accessParameter(decl);
        }
        if(caller == getBlockNoTransform()){
		SymbolicVarAccess acc = accessParameter(decl);
		if(!acc.isUnknownVarAccess()) return acc;
		return accessVariable(decl).moveInto(this);
	}
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

    // Declared in InsertEmptyStmt.jrag at line 59
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getBlockNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }

    // Declared in InsertEmptyStmt.jrag at line 120
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getBlockNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
