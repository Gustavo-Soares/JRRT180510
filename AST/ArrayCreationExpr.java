
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class ArrayCreationExpr extends PrimaryExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr clone() throws CloneNotSupportedException {
        ArrayCreationExpr node = (ArrayCreationExpr)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr copy() {
      try {
          ArrayCreationExpr node = (ArrayCreationExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr fullCopy() {
        ArrayCreationExpr res = (ArrayCreationExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 355


  public void toString(StringBuffer s) {
    s.append("new ");
    getTypeAccess().toString(s);
    if(hasArrayInit()) {
      getArrayInit().toString(s);
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 136

    public ArrayCreationExpr() {
        super();

        setChild(new Opt(), 1);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 136
    public ArrayCreationExpr(Access p0, Opt<ArrayInit> p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 19

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 136
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
    // Declared in java.ast line 136
    public void setArrayInitOpt(Opt<ArrayInit> opt) {
        setChild(opt, 1);
    }

    // Declared in java.ast at line 6


    public boolean hasArrayInit() {
        return getArrayInitOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ArrayInit getArrayInit() {
        return (ArrayInit)getArrayInitOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setArrayInit(ArrayInit node) {
        getArrayInitOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<ArrayInit> getArrayInitOpt() {
        return (Opt<ArrayInit>)getChild(1);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<ArrayInit> getArrayInitOptNoTransform() {
        return (Opt<ArrayInit>)getChildNoTransform(1);
    }

    // Declared in DefiniteAssignment.jrag at line 433
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterCreation(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterCreation_Variable_value = isDAafterCreation_compute(v);
        return isDAafterCreation_Variable_value;
    }

    private boolean isDAafterCreation_compute(Variable v) {  return getTypeAccess().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 434
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDAafter(v) : isDAafterCreation(v);  }

    // Declared in DefiniteAssignment.jrag at line 864
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterCreation(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterCreation_Variable_value = isDUafterCreation_compute(v);
        return isDUafterCreation_Variable_value;
    }

    private boolean isDUafterCreation_compute(Variable v) {  return getTypeAccess().isDUafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 865
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDUafter(v) : isDUafterCreation(v);  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 312
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

    // Declared in ControlFlowGraph.jrag at line 296
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getTypeAccess().first();  }

    // Declared in ExprExt.jrag at line 46
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {  return getTypeAccess().isFlowInvariant() && (!hasArrayInit() || getArrayInit().isFlowInvariant());  }

    // Declared in Precedence.jrag at line 9
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return 1;  }

    // Declared in Purity.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return false;  }

    // Declared in DefiniteAssignment.jrag at line 435
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getArrayInitOptNoTransform()) {
            return isDAafterCreation(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 867
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getArrayInitOptNoTransform()) {
            return isDUafterCreation(v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in SyntacticClassification.jrag at line 87
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 262
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getArrayInitOptNoTransform()) {
            return type();
        }
        return getParent().Define_TypeDecl_declType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 299
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getArrayInitOptNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        if(caller == getTypeAccessNoTransform()) {
            return SmallSet.<CFGNode>singleton(hasArrayInit() ? getArrayInit().first() : this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ExprExt.jrag at line 41
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        if(caller == getArrayInitOptNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
