
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class CastExpr extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr clone() throws CloneNotSupportedException {
        CastExpr node = (CastExpr)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr copy() {
      try {
          CastExpr node = (CastExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr fullCopy() {
        CastExpr res = (CastExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 384

  

  public void toString(StringBuffer s) {
    s.append("(");
    getTypeAccess().toString(s);
    s.append(")");
    getExpr().toString(s);
  }

    // Declared in TypeCheck.jrag at line 252

  
  // 15.16
  public void typeCheck() {
    TypeDecl expr = getExpr().type();
    TypeDecl type = getTypeAccess().type();
    if(!expr.isUnknown()) {
      if(!expr.castingConversionTo(type))
        error(expr.typeName() + " can not be cast into " + type.typeName());
      if(!getTypeAccess().isTypeAccess())
        error("" + getTypeAccess() + " is not a type access in cast expression");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 147

    public CastExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 147
    public CastExpr(Access p0, Expr p1) {
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
    // Declared in java.ast line 147
    public void setTypeAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(0);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 147
    public void setExpr(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    // Declared in ConstantExpression.jrag at line 110
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        Constant constant_value = constant_compute();
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(getExpr().constant());  }

    // Declared in ConstantExpression.jrag at line 485
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        boolean isConstant_value = isConstant_compute();
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getExpr().isConstant() &&
    (getTypeAccess().type().isPrimitive() || getTypeAccess().type().isString());  }

    // Declared in DefiniteAssignment.jrag at line 403
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getExpr().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 847
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return getExpr().isDUafter(v);  }

    // Declared in ResolveAmbiguousNames.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        boolean isSuperAccess_value = isSuperAccess_compute();
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return getExpr().isSuperAccess();  }

    // Declared in ResolveAmbiguousNames.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        boolean isThisAccess_value = isThisAccess_compute();
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return getExpr().isThisAccess();  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 320
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

    private TypeDecl type_compute() {  return getTypeAccess().type();  }

    // Declared in TypeHierarchyCheck.jrag at line 152
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return getExpr().staticContextQualifier();  }

    // Declared in ControlFlowGraph.jrag at line 231
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getExpr().first();  }

    // Declared in ExprExt.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return super.notAnObject() || getExpr().notAnObject();  }

    // Declared in Precedence.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return 3;  }

    // Declared in Purity.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return getExpr().isPure();  }

    // Declared in SyntacticClassification.jrag at line 88
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 233
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return SmallSet.empty();
        }
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 354
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 355
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in Precedence.jrag at line 87
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return precedence();
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
