
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class ParExpr extends PrimaryExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr clone() throws CloneNotSupportedException {
        ParExpr node = (ParExpr)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr copy() {
      try {
          ParExpr node = (ParExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr fullCopy() {
        ParExpr res = (ParExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 320


  public void toString(StringBuffer s) {
    s.append("(");
    getExpr().toString(s);
    s.append(")");
  }

    // Declared in TypeCheck.jrag at line 263


  public void typeCheck() {
    if(getExpr().isTypeAccess())
      error("" + getExpr() + " is a type and may not be used in parenthesized expression");
  }

    // Declared in ControlFlowGraph.jrag at line 215

	public VarAccess getDestLocation() {
		return getExpr().getDestLocation();
	}

    // Declared in Testing.jrag at line 284

	
	public Expr findDoublyParenthesised() {
		if(getExpr() instanceof ParExpr)
			return ((ParExpr)getExpr()).getExpr();
		return super.findDoublyParenthesised();
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 134

    public ParExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 134
    public ParExpr(Expr p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 134
    public void setExpr(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in ConstantExpression.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        Constant constant_value = constant_compute();
        return constant_value;
    }

    private Constant constant_compute() {  return getExpr().constant();  }

    // Declared in ConstantExpression.jrag at line 494
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        boolean isConstant_value = isConstant_compute();
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getExpr().isConstant();  }

    // Declared in DefiniteAssignment.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        Variable varDecl_value = varDecl_compute();
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return getExpr().varDecl();  }

    // Declared in DefiniteAssignment.jrag at line 350
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterTrue(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
        return isDAafterTrue_Variable_value;
    }

    private boolean isDAafterTrue_compute(Variable v) {  return getExpr().isDAafterTrue(v) || isFalse();  }

    // Declared in DefiniteAssignment.jrag at line 351
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFalse(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
        return isDAafterFalse_Variable_value;
    }

    private boolean isDAafterFalse_compute(Variable v) {  return getExpr().isDAafterFalse(v) || isTrue();  }

    // Declared in DefiniteAssignment.jrag at line 401
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getExpr().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 800
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterTrue(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterTrue_Variable_value = isDUafterTrue_compute(v);
        return isDUafterTrue_Variable_value;
    }

    private boolean isDUafterTrue_compute(Variable v) {  return getExpr().isDUafterTrue(v);  }

    // Declared in DefiniteAssignment.jrag at line 801
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFalse(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterFalse_Variable_value = isDUafterFalse_compute(v);
        return isDUafterFalse_Variable_value;
    }

    private boolean isDUafterFalse_compute(Variable v) {  return getExpr().isDUafterFalse(v);  }

    // Declared in DefiniteAssignment.jrag at line 845
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return getExpr().isDUafter(v);  }

    // Declared in ResolveAmbiguousNames.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        boolean isSuperAccess_value = isSuperAccess_compute();
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return getExpr().isSuperAccess();  }

    // Declared in ResolveAmbiguousNames.jrag at line 34
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        boolean isThisAccess_value = isThisAccess_compute();
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return getExpr().isThisAccess();  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 309
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        if(type_computed) {
            return type_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        type_value = type_compute();
        if(isFinal && num == state().boundariesCrossed)
            type_computed = true;
        return type_value;
    }

    private TypeDecl type_compute() {  return getExpr().isTypeAccess() ? unknownType() : getExpr().type();  }

    // Declared in TypeCheck.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return getExpr().isVariable();  }

    // Declared in TypeHierarchyCheck.jrag at line 151
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return getExpr().staticContextQualifier();  }

    // Declared in ControlFlowGraph.jrag at line 236
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getExpr().first();  }

    // Declared in ExprExt.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return super.notAnObject() || getExpr().notAnObject();  }

    // Declared in Precedence.jrag at line 7
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return 0;  }

    // Declared in Purity.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return getExpr().isPure();  }

    // Declared in ControlFlowGraph.jrag at line 237
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 358
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 359
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in Precedence.jrag at line 91
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
