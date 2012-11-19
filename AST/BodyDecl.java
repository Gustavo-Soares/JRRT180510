
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public abstract class BodyDecl extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        entry_computed = false;
        entry_value = null;
        exit_computed = false;
        exit_value = null;
        isDAbefore_Variable_values = null;
        isDUbefore_Variable_values = null;
        typeThrowable_computed = false;
        typeThrowable_value = null;
        lookupVariable_String_values = null;
    collect_contributors_VariableDeclaration_uses = false;
    collect_contributors_ParameterDeclaration_uses = false;
    collect_contributors_LabeledStmt_uses = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    collect_contributors_VariableDeclaration_uses = false;
    collect_contributors_ParameterDeclaration_uses = false;
    collect_contributors_LabeledStmt_uses = false;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl clone() throws CloneNotSupportedException {
        BodyDecl node = (BodyDecl)super.clone();
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.entry_computed = false;
        node.entry_value = null;
        node.exit_computed = false;
        node.exit_value = null;
        node.isDAbefore_Variable_values = null;
        node.isDUbefore_Variable_values = null;
        node.typeThrowable_computed = false;
        node.typeThrowable_value = null;
        node.lookupVariable_String_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in BranchTarget.jrag at line 211

  public void collectFinally(Stmt branchStmt, ArrayList list) {
    // terminate search if body declaration is reached
  }

    // Declared in Generics.jrag at line 1001


  public BodyDecl p(Parameterization parTypeDecl) {
    throw new Error("Operation p not supported for " + getClass().getName());
  }

    // Declared in ControlFlowGraph.jrag at line 14

	
	/** Every body declaration has an entry and an exit node. We implement these as
	 *  NTAs for clarity, but this complicates the definition of pred() below.
	 *  These nodes are only placeholders that do not need to be recomputed after
	 *  a cache flush, so we cache them explicitly. */
	private EntryStmt entry = new EntryStmt();

    // Declared in ControlFlowGraph.jrag at line 16

	private ExitStmt exit = new ExitStmt();

    // Declared in ControlFlowGraph.jrag at line 43

	
	// NTAs are not, in general, included in the collection attribute traversal; we have to fudge around this
    protected void collect_contributors_CFGNode_collPred() {
    	entry().collect_contributors_CFGNode_collPred();
    	exit().collect_contributors_CFGNode_collPred();
    	super.collect_contributors_CFGNode_collPred();
    }

    // Declared in AST.jrag at line 73

	
	public void flushCaches() {
		entry.flushCaches();
		exit.flushCaches();
		super.flushCaches();
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 69

    public BodyDecl() {
        super();


    }

    // Declared in java.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in Uses.jrag at line 13
    private boolean collect_contributors_VariableDeclaration_uses = false;
    protected void collect_contributors_VariableDeclaration_uses() {
        if(collect_contributors_VariableDeclaration_uses) return;
        super.collect_contributors_VariableDeclaration_uses();
        collect_contributors_VariableDeclaration_uses = true;
    }



    // Declared in Uses.jrag at line 19
    private boolean collect_contributors_ParameterDeclaration_uses = false;
    protected void collect_contributors_ParameterDeclaration_uses() {
        if(collect_contributors_ParameterDeclaration_uses) return;
        super.collect_contributors_ParameterDeclaration_uses();
        collect_contributors_ParameterDeclaration_uses = true;
    }



    // Declared in Uses.jrag at line 54
    private boolean collect_contributors_LabeledStmt_uses = false;
    protected void collect_contributors_LabeledStmt_uses() {
        if(collect_contributors_LabeledStmt_uses) return;
        super.collect_contributors_LabeledStmt_uses();
        collect_contributors_LabeledStmt_uses = true;
    }



    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 245
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

    private boolean isDAafter_compute(Variable v) {  return true;  }

    protected java.util.Map isDUafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 709
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

    private boolean isDUafter_compute(Variable v) {  return true;  }

    // Declared in LookupType.jrag at line 391
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresType(String name) {
        ASTNode$State state = state();
        boolean declaresType_String_value = declaresType_compute(name);
        return declaresType_String_value;
    }

    private boolean declaresType_compute(String name) {  return false;  }

    // Declared in LookupType.jrag at line 393
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type(String name) {
        ASTNode$State state = state();
        TypeDecl type_String_value = type_compute(name);
        return type_String_value;
    }

    private TypeDecl type_compute(String name) {  return null;  }

    // Declared in PrettyPrint.jadd at line 759
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return true;  }

    // Declared in TypeAnalysis.jrag at line 271
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        boolean isVoid_value = isVoid_compute();
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return false;  }

    // Declared in Annotations.jrag at line 283
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasAnnotationSuppressWarnings(String s) {
        ASTNode$State state = state();
        boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
        return hasAnnotationSuppressWarnings_String_value;
    }

    private boolean hasAnnotationSuppressWarnings_compute(String s) {  return false;  }

    // Declared in Annotations.jrag at line 326
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDeprecated() {
        ASTNode$State state = state();
        boolean isDeprecated_value = isDeprecated_compute();
        return isDeprecated_value;
    }

    private boolean isDeprecated_compute() {  return false;  }

    // Declared in Enums.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEnumConstant() {
        ASTNode$State state = state();
        boolean isEnumConstant_value = isEnumConstant_compute();
        return isEnumConstant_value;
    }

    private boolean isEnumConstant_compute() {  return false;  }

    // Declared in GenericsParTypeDecl.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public boolean visibleTypeParameters() {
        ASTNode$State state = state();
        boolean visibleTypeParameters_value = visibleTypeParameters_compute();
        return visibleTypeParameters_value;
    }

    private boolean visibleTypeParameters_compute() {  return true;  }

    protected boolean entry_computed = false;
    protected Stmt entry_value;
    // Declared in ControlFlowGraph.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public Stmt entry() {
        if(entry_computed) {
            return entry_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        entry_value = entry_compute();
        entry_value.setParent(this);
        entry_value.is$Final = true;
        if(true)
            entry_computed = true;
        return entry_value;
    }

    private Stmt entry_compute() {  return entry;  }

    protected boolean exit_computed = false;
    protected Stmt exit_value;
    // Declared in ControlFlowGraph.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public Stmt exit() {
        if(exit_computed) {
            return exit_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        exit_value = exit_compute();
        exit_value.setParent(this);
        exit_value.is$Final = true;
        if(true)
            exit_computed = true;
        return exit_value;
    }

    private Stmt exit_compute() {  return exit;  }

    // Declared in ControlFlowGraph.jrag at line 99
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> getFollowingFieldOrInitializer(boolean statik) {
        ASTNode$State state = state();
        SmallSet<CFGNode> getFollowingFieldOrInitializer_boolean_value = getFollowingFieldOrInitializer_compute(statik);
        return getFollowingFieldOrInitializer_boolean_value;
    }

    private SmallSet<CFGNode> getFollowingFieldOrInitializer_compute(boolean statik) {
		BodyDecl bd = hostType().getFieldOrInitializerAfter(getParent().getIndexOfChild(this)+1, statik);
		if(bd != null)
			return SmallSet.<CFGNode>singleton(bd.entry());
		SmallSet<CFGNode> res = SmallSet.empty();
		if(!statik)
			for(ConstructorDecl cd : (Collection<ConstructorDecl>)hostType().constructors())
				if(cd.invokesSuperConstructor())
					res = res.union(cd.getBlock().first());
		return res;
	}

    // Declared in Testing.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public String ppID() {
        ASTNode$State state = state();
        String ppID_value = ppID_compute();
        return ppID_value;
    }

    private String ppID_compute() {  return "";  }

    protected java.util.Map isDAbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 244
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        Object _parameters = v;
if(isDAbefore_Variable_values == null) isDAbefore_Variable_values = new java.util.HashMap(4);
        if(isDAbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDAbefore_Variable_values.put(_parameters, Boolean.valueOf(isDAbefore_Variable_value));
        return isDAbefore_Variable_value;
    }

    protected java.util.Map isDUbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 708
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
        if(isDUbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
        return isDUbefore_Variable_value;
    }

    protected boolean typeThrowable_computed = false;
    protected TypeDecl typeThrowable_value;
    // Declared in ExceptionHandling.jrag at line 22
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

    // Declared in LookupMethod.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupMethod(String name) {
        ASTNode$State state = state();
        Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
        return lookupMethod_String_value;
    }

    // Declared in LookupType.jrag at line 97
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupType(String packageName, String typeName) {
        ASTNode$State state = state();
        TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
        return lookupType_String_String_value;
    }

    // Declared in LookupType.jrag at line 173
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        ASTNode$State state = state();
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        return lookupType_String_value;
    }

    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 15
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

    // Declared in SyntacticClassification.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public NameType nameType() {
        ASTNode$State state = state();
        NameType nameType_value = getParent().Define_NameType_nameType(this, null);
        return nameType_value;
    }

    // Declared in TypeAnalysis.jrag at line 567
 @SuppressWarnings({"unchecked", "cast"})     public String hostPackage() {
        ASTNode$State state = state();
        String hostPackage_value = getParent().Define_String_hostPackage(this, null);
        return hostPackage_value;
    }

    // Declared in TypeAnalysis.jrag at line 582
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in AccessMethod.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public MethodAccessInfo accessMethod(MethodDecl md) {
        ASTNode$State state = state();
        MethodAccessInfo accessMethod_MethodDecl_value = getParent().Define_MethodAccessInfo_accessMethod(this, null, md);
        return accessMethod_MethodDecl_value;
    }

    // Declared in AccessVariable.jrag at line 133
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess accessVariable_Variable_value = getParent().Define_SymbolicVarAccess_accessVariable(this, null, decl);
        return accessVariable_Variable_value;
    }

    // Declared in PrettyPrint.jadd at line 352
    public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return indent();
        }
        return getParent().Define_String_typeDeclIndent(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 515
    public BodyDecl Define_BodyDecl_enclosingBodyDecl(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return this;
        }
        return getParent().Define_BodyDecl_enclosingBodyDecl(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 676
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__breakTarget(ASTNode caller, ASTNode child, BreakStmt stmt) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.empty();
        }
        return getParent().Define_SmallSet_CFGNode__breakTarget(this, caller, stmt);
    }

    // Declared in ControlFlowGraph.jrag at line 677
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__continueTarget(ASTNode caller, ASTNode child, ContinueStmt stmt) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.empty();
        }
        return getParent().Define_SmallSet_CFGNode__continueTarget(this, caller, stmt);
    }

    // Declared in ControlFlowGraph.jrag at line 678
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__returnTarget(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(exit());
        }
        return getParent().Define_SmallSet_CFGNode__returnTarget(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 679
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__throwTarget(ASTNode caller, ASTNode child, TypeDecl exn) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(exit());
        }
        return getParent().Define_SmallSet_CFGNode__throwTarget(this, caller, exn);
    }

    // Declared in ControlFlowGraph.jrag at line 680
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__uncheckedExnTarget(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(exit());
        }
        return getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, caller);
    }

    // Declared in LocalVariable.jrag at line 15
    public BodyDecl Define_BodyDecl_hostBodyDecl(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return this;
        }
        return getParent().Define_BodyDecl_hostBodyDecl(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
