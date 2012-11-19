
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class ArrayTypeWithSizeAccess extends ArrayTypeAccess implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayTypeWithSizeAccess clone() throws CloneNotSupportedException {
        ArrayTypeWithSizeAccess node = (ArrayTypeWithSizeAccess)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayTypeWithSizeAccess copy() {
      try {
          ArrayTypeWithSizeAccess node = (ArrayTypeWithSizeAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayTypeWithSizeAccess fullCopy() {
        ArrayTypeWithSizeAccess res = (ArrayTypeWithSizeAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 494


  public void toString(StringBuffer s) {
    getAccess().toString(s);
    s.append("[");
    getExpr().toString(s);
    s.append("]");
  }

    // Declared in TypeCheck.jrag at line 555


  public void typeCheck() {
    super.typeCheck();
    if(!getExpr().type().unaryNumericPromotion().isInt())
      error(getExpr().type().typeName() + " is not int after unary numeric promotion");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 23

    public ArrayTypeWithSizeAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 23
    public ArrayTypeWithSizeAccess(Access p0, Expr p1) {
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
    // Declared in java.ast line 22
    public void setAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Access getAccess() {
        return (Access)getChild(0);
    }

    // Declared in java.ast at line 9


    public Access getAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 23
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

    // Declared in java.ast at line 2
    // Declared in java.ast line 22
    public void setPackage(String value) {
        tokenString_Package = value;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 22
    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in DefiniteAssignment.jrag at line 361
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getExpr().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 842
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return getExpr().isDUafter(v);  }

    // Declared in ExprExt.jrag at line 54
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {  return getExpr().isConstant();  }

    // Declared in Purity.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return getExpr().isPure();  }

    // Declared in DefiniteAssignment.jrag at line 36
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isDest(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 37
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 362
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getExprNoTransform()) {
            return getAccess().isDAafter(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 843
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getExprNoTransform()) {
            return getAccess().isDUafter(v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in LookupMethod.jrag at line 31
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupMethod(name);
        }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 91
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().hasPackage(packageName);
        }
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 168
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupType(name);
        }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 134
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 123
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return NameType.EXPRESSION_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 189
    public AssignExpr Define_AssignExpr_modifyingAssignExpr(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return null;
        }
        return getParent().Define_AssignExpr_modifyingAssignExpr(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 313
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        if(caller == getAccessNoTransform()) {
            return SmallSet.<CFGNode>singleton(getExpr().first());
        }
        return super.Define_SmallSet_CFGNode__following(caller, child);
    }

    // Declared in ExprExt.jrag at line 39
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }

    // Declared in Precedence.jrag at line 57
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return Integer.MAX_VALUE;
        }
        if(caller == getAccessNoTransform()) {
            return 0;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in AccessMethod.jrag at line 77
    public MethodAccessInfo Define_MethodAccessInfo_accessMethod(ASTNode caller, ASTNode child, MethodDecl md) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().accessMethod(md);
        }
        return getParent().Define_MethodAccessInfo_accessMethod(this, caller, md);
    }

    // Declared in AccessVariable.jrag at line 254
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().accessVariable(decl);
        }
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
