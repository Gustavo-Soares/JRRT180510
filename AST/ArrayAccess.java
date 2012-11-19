
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class ArrayAccess extends Access implements Cloneable, Location {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
        getLocation_computed = false;
        getLocation_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess clone() throws CloneNotSupportedException {
        ArrayAccess node = (ArrayAccess)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.getLocation_computed = false;
        node.getLocation_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess copy() {
      try {
          ArrayAccess node = (ArrayAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess fullCopy() {
        ArrayAccess res = (ArrayAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 513


  public void toString(StringBuffer s) {
    s.append("[");
    getExpr().toString(s);
    s.append("]");
  }

    // Declared in TypeCheck.jrag at line 137

          
  // 15.13
  public void typeCheck() {
    if(isQualified() && !qualifier().type().isArrayDecl() && !qualifier().type().isUnknown())
      error("the type " + qualifier().type().name() + " of the indexed element is not an array");
    if(!getExpr().type().unaryNumericPromotion().isInt() || !getExpr().type().isIntegralType())
      error("array index must be int after unary numeric promotion which " + getExpr().type().typeName() + " is not");
  }

    // Declared in Alias.jrag at line 12

	public boolean isHeapLocation() { return true; }

    // Declared in Alias.jrag at line 21

	public boolean isArrayElement() { return true; }

    // Declared in DataFlow.jrag at line 46

	
	public void lockDataFlow(int l) {
		lockReachingDefsAndUses(l);
		lockWriteDeps();
		super.lockDataFlow(l);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 28

    public ArrayAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 28
    public ArrayAccess(Expr p0) {
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
    // Declared in java.ast line 28
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

    // Declared in Alias.jrag at line 33
private boolean refined_Alias_ArrayAccess_mayReferTo_Location(Location l)
{ return l.isArrayElement() && type().castingConversionTo(l.type()); }

    // Declared in DefiniteAssignment.jrag at line 359
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getExpr().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 840
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return getExpr().isDUafter(v);  }

    // Declared in ResolveAmbiguousNames.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayAccess() {
        ASTNode$State state = state();
        boolean isArrayAccess_value = isArrayAccess_compute();
        return isArrayAccess_value;
    }

    private boolean isArrayAccess_compute() {  return true;  }

    // Declared in SyntacticClassification.jrag at line 100
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.EXPRESSION_NAME;  }

    // Declared in TypeAnalysis.jrag at line 280
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

    private TypeDecl type_compute() {  return isQualified() ? qualifier().type().componentType() : unknownType();  }

    // Declared in TypeCheck.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return true;  }

    // Declared in Alias.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public Location getLocation() {
        if(getLocation_computed) {
            return getLocation_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        getLocation_value = getLocation_compute();
        if(isFinal && num == state().boundariesCrossed)
            getLocation_computed = true;
        return getLocation_value;
    }

    private Location getLocation_compute() {  return this;  }

    // Declared in DataFlow.jrag at line 136
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayReferTo(Location l) {
        ASTNode$State state = state();
        boolean mayReferTo_Location_value = mayReferTo_compute(l);
        return mayReferTo_Location_value;
    }

    private boolean mayReferTo_compute(Location l) {  return l instanceof Callable || refined_Alias_ArrayAccess_mayReferTo_Location(l);  }

    // Declared in Alias.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public boolean mustReferTo(Location l) {
        ASTNode$State state = state();
        boolean mustReferTo_Location_value = mustReferTo_compute(l);
        return mustReferTo_Location_value;
    }

    private boolean mustReferTo_compute(Location l) {  return false;  }

    // Declared in ControlFlowGraph.jrag at line 292
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getExpr().first();  }

    // Declared in ExprExt.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return true;  }

    // Declared in Purity.jrag at line 6
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return getExpr().isPure();  }

    // Declared in TypeAnalysis.jrag at line 281
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        ASTNode$State state = state();
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        return unknownType_value;
    }

    // Declared in DefiniteAssignment.jrag at line 34
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_isDest(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 35
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in LookupMethod.jrag at line 30
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupMethod(name);
        }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 90
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().hasPackage(packageName);
        }
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 167
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupType(name);
        }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 133
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 122
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return NameType.EXPRESSION_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 188
    public AssignExpr Define_AssignExpr_modifyingAssignExpr(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return null;
        }
        return getParent().Define_AssignExpr_modifyingAssignExpr(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 293
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return SmallSet.<CFGNode>singleton(this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ExprExt.jrag at line 38
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }

    // Declared in Precedence.jrag at line 58
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in AccessMethod.jrag at line 76
    public MethodAccessInfo Define_MethodAccessInfo_accessMethod(ASTNode caller, ASTNode child, MethodDecl md) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().accessMethod(md);
        }
        return getParent().Define_MethodAccessInfo_accessMethod(this, caller, md);
    }

    // Declared in AccessVariable.jrag at line 253
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
