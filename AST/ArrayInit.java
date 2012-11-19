
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class ArrayInit extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        computeDABefore_int_Variable_values = null;
        computeDUbefore_int_Variable_values = null;
        type_computed = false;
        type_value = null;
        isArrayInit_computed = false;
        declType_computed = false;
        declType_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayInit clone() throws CloneNotSupportedException {
        ArrayInit node = (ArrayInit)super.clone();
        node.computeDABefore_int_Variable_values = null;
        node.computeDUbefore_int_Variable_values = null;
        node.type_computed = false;
        node.type_value = null;
        node.isArrayInit_computed = false;
        node.declType_computed = false;
        node.declType_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayInit copy() {
      try {
          ArrayInit node = (ArrayInit)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayInit fullCopy() {
        ArrayInit res = (ArrayInit)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 220


  public void toString(StringBuffer s) {
    s.append("{ ");
    if(getNumInit() > 0) {
      getInit(0).toString(s);
      for(int i = 1; i < getNumInit(); i++) {
        s.append(", ");
        getInit(i).toString(s);
      }
    }
    s.append(" } ");
  }

    // Declared in TypeCheck.jrag at line 144


  public void typeCheck() {
    TypeDecl initializerType = declType().componentType();
    if(initializerType.isUnknown())
      error("the dimension of the initializer is larger than the expected dimension");
    for(int i = 0; i < getNumInit(); i++) {
      Expr e = getInit(i);
      if(!e.type().assignConversionTo(initializerType, e))
        error("the type " + e.type().name() + " of the initializer is not compatible with " + initializerType.name()); 
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 86

    public ArrayInit() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 86
    public ArrayInit(List<Expr> p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 86
    public void setInitList(List<Expr> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumInit() {
        return getInitList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getInit(int i) {
        return (Expr)getInitList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addInit(Expr node) {
        List<Expr> list = (parent == null || state == null) ? getInitListNoTransform() : getInitList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addInitNoTransform(Expr node) {
        List<Expr> list = getInitListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setInit(Expr node, int i) {
        List<Expr> list = getInitList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Expr> getInits() {
        return getInitList();
    }

    // Declared in java.ast at line 31

    public List<Expr> getInitsNoTransform() {
        return getInitListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getInitList() {
        List<Expr> list = (List<Expr>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getInitListNoTransform() {
        return (List<Expr>)getChildNoTransform(0);
    }

    // Declared in ConstantExpression.jrag at line 469
 @SuppressWarnings({"unchecked", "cast"})     public boolean representableIn(TypeDecl t) {
        ASTNode$State state = state();
        boolean representableIn_TypeDecl_value = representableIn_compute(t);
        return representableIn_TypeDecl_value;
    }

    private boolean representableIn_compute(TypeDecl t) {
    for(int i = 0; i < getNumInit(); i++)
      if(!getInit(i).representableIn(t))
        return false;
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 500
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getNumInit() == 0 ? isDAbefore(v) : getInit(getNumInit()-1).isDAafter(v);  }

    protected java.util.Map computeDABefore_int_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 503
 @SuppressWarnings({"unchecked", "cast"})     public boolean computeDABefore(int childIndex, Variable v) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(Integer.valueOf(childIndex));
        _parameters.add(v);
if(computeDABefore_int_Variable_values == null) computeDABefore_int_Variable_values = new java.util.HashMap(4);
        if(computeDABefore_int_Variable_values.containsKey(_parameters)) {
            return ((Boolean)computeDABefore_int_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean computeDABefore_int_Variable_value = computeDABefore_compute(childIndex, v);
        if(isFinal && num == state().boundariesCrossed)
            computeDABefore_int_Variable_values.put(_parameters, Boolean.valueOf(computeDABefore_int_Variable_value));
        return computeDABefore_int_Variable_value;
    }

    private boolean computeDABefore_compute(int childIndex, Variable v) {
    if(childIndex == 0) return isDAbefore(v);
    int index = childIndex-1;
    while(index > 0 && getInit(index).isConstant())
      index--;
    return getInit(childIndex-1).isDAafter(v);
  }

    // Declared in DefiniteAssignment.jrag at line 886
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return getNumInit() == 0 ? isDUbefore(v) : getInit(getNumInit()-1).isDUafter(v);  }

    protected java.util.Map computeDUbefore_int_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 889
 @SuppressWarnings({"unchecked", "cast"})     public boolean computeDUbefore(int childIndex, Variable v) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(Integer.valueOf(childIndex));
        _parameters.add(v);
if(computeDUbefore_int_Variable_values == null) computeDUbefore_int_Variable_values = new java.util.HashMap(4);
        if(computeDUbefore_int_Variable_values.containsKey(_parameters)) {
            return ((Boolean)computeDUbefore_int_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean computeDUbefore_int_Variable_value = computeDUbefore_compute(childIndex, v);
        if(isFinal && num == state().boundariesCrossed)
            computeDUbefore_int_Variable_values.put(_parameters, Boolean.valueOf(computeDUbefore_int_Variable_value));
        return computeDUbefore_int_Variable_value;
    }

    private boolean computeDUbefore_compute(int childIndex, Variable v) {
    if(childIndex == 0) return isDUbefore(v);
    int index = childIndex-1;
    while(index > 0 && getInit(index).isConstant())
      index--;
    return getInit(childIndex-1).isDUafter(v);
  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 265
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

    private TypeDecl type_compute() {  return declType();  }

    // Declared in ControlFlowGraph.jrag at line 302
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getNumInit() == 0 ?	this : getInit(0).first();  }

    // Declared in ExprExt.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {
		for(Expr e : getInits())
			if(!e.isFlowInvariant())
				return false;
		return true;
	}

    // Declared in Precedence.jrag at line 3
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return -1;  }

    // Declared in Purity.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return false;  }

    // Declared in ArrayInits.jrag at line 3
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayInit() {
        if(isArrayInit_computed) {
            return isArrayInit_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isArrayInit_value = isArrayInit_compute();
        if(isFinal && num == state().boundariesCrossed)
            isArrayInit_computed = true;
        return isArrayInit_value;
    }

    private boolean isArrayInit_compute() {  return true;  }

    protected boolean declType_computed = false;
    protected TypeDecl declType_value;
    // Declared in TypeAnalysis.jrag at line 255
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl declType() {
        if(declType_computed) {
            return declType_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        declType_value = getParent().Define_TypeDecl_declType(this, null);
        if(isFinal && num == state().boundariesCrossed)
            declType_computed = true;
        return declType_value;
    }

    // Declared in DefiniteAssignment.jrag at line 42
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 501
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return computeDABefore(childIndex, v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 887
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return computeDUbefore(childIndex, v);
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in TypeAnalysis.jrag at line 263
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return declType().componentType();
        }
        return getParent().Define_TypeDecl_declType(this, caller);
    }

    // Declared in GenericMethodsInference.jrag at line 37
    public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return declType().componentType();
        }
        return getParent().Define_TypeDecl_assignConvertedType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 304
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return SmallSet.<CFGNode>singleton(i + 1 < getNumInit() ? getInit(i + 1).first() : this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 374
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getInit(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 375
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getInit(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in Precedence.jrag at line 55
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getInitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
