
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class ConstructorAccess extends Access implements Cloneable, Call {
    public void flushCache() {
        super.flushCache();
        decls_computed = false;
        decls_value = null;
        decl_computed = false;
        decl_value = null;
        type_computed = false;
        type_value = null;
        succ_computed = false;
        succ_value = null;
        uncaughtExceptions_computed = false;
        uncaughtExceptions_value = null;
        getLocation_computed = false;
        getLocation_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess clone() throws CloneNotSupportedException {
        ConstructorAccess node = (ConstructorAccess)super.clone();
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_computed = false;
        node.decl_value = null;
        node.type_computed = false;
        node.type_value = null;
        node.succ_computed = false;
        node.succ_value = null;
        node.uncaughtExceptions_computed = false;
        node.uncaughtExceptions_value = null;
        node.getLocation_computed = false;
        node.getLocation_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess copy() {
      try {
          ConstructorAccess node = (ConstructorAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess fullCopy() {
        ConstructorAccess res = (ConstructorAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ExceptionHandling.jrag at line 97


  public void exceptionHandling() {
    for(int i = 0; i < decl().getNumException(); i++) {
      TypeDecl exceptionType = decl().getException(i).type();
      if(!handlesException(exceptionType))
        error("" + this + " may throw uncaught exception " + exceptionType.fullName());
    }
  }

    // Declared in ExceptionHandling.jrag at line 244

  
  // 8.8.4 (8.4.4)
  protected boolean reachedException(TypeDecl catchType) {
    for(int i = 0; i < decl().getNumException(); i++) {
      TypeDecl exceptionType = decl().getException(i).type();
      if(catchType.mayCatch(exceptionType))
        return true;
    }
    return super.reachedException(catchType);
  }

    // Declared in NameCheck.jrag at line 112


  public void nameCheck() {
    super.nameCheck();
    if(decls().isEmpty())
      error("no constructor named " + this);
    if(decls().size() > 1 && validArgs()) {
      error("several most specific constructors for " + this);
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        error("         " + ((ConstructorDecl)iter.next()).signature());
      }
    }
  }

    // Declared in PrettyPrint.jadd at line 469


  public void toString(StringBuffer s) {
    s.append(name());
    s.append("(");
    if(getNumArg() > 0) {
      getArg(0).toString(s);
      for(int i = 1; i < getNumArg(); i++) {
        s.append(", ");
        getArg(i).toString(s);
      }
    }
    s.append(")");
  }

    // Declared in Annotations.jrag at line 355


  public void checkModifiers() {
    if(decl().isDeprecated() &&
      !withinDeprecatedAnnotation() &&
      hostType().topLevelType() != decl().hostType().topLevelType() &&
      !withinSuppressWarnings("deprecation"))
        warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated");
  }

    // Declared in Enums.jrag at line 156

  // applied to both ConstructorAccess and SuperConstructorAccess
  protected void transformEnumConstructors() {
    super.transformEnumConstructors();
    getArgList().insertChild(new VarAccess("@p0"),0);
    getArgList().insertChild(new VarAccess("@p1"),1);
  }

    // Declared in Call.jadd at line 61

	
	
	public void insertArg(Expr arg, int i) {
		getArgList().insertChild(arg, i);
	}

    // Declared in DataFlow.jrag at line 58

	
	public void lockDataFlow(int l) {
		lockReachingDefsAndUses(l);
		lockWriteDeps();
		super.lockDataFlow(l);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 18

    public ConstructorAccess() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 18
    public ConstructorAccess(String p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 18
    public ConstructorAccess(beaver.Symbol p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 22


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 25

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 18
    protected String tokenString_ID;

    // Declared in java.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 6

    public int IDstart;

    // Declared in java.ast at line 7

    public int IDend;

    // Declared in java.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 18
    public void setArgList(List<Expr> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumArg() {
        return getArgList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getArg(int i) {
        return (Expr)getArgList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addArg(Expr node) {
        List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addArgNoTransform(Expr node) {
        List<Expr> list = getArgListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setArg(Expr node, int i) {
        List<Expr> list = getArgList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Expr> getArgs() {
        return getArgList();
    }

    // Declared in java.ast at line 31

    public List<Expr> getArgsNoTransform() {
        return getArgListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgList() {
        List<Expr> list = (List<Expr>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgListNoTransform() {
        return (List<Expr>)getChildNoTransform(0);
    }

    // Declared in DefiniteAssignment.jrag at line 298
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return decl().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 754
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return decl().isDUafter(v);  }

    // Declared in LookupConstructor.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableAndAccessible(ConstructorDecl decl) {
        ASTNode$State state = state();
        boolean applicableAndAccessible_ConstructorDecl_value = applicableAndAccessible_compute(decl);
        return applicableAndAccessible_ConstructorDecl_value;
    }

    private boolean applicableAndAccessible_compute(ConstructorDecl decl) {  return decl.applicable(getArgList()) && decl.accessibleFrom(hostType());  }

    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in MethodSignature.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        if(decls_computed) {
            return decls_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decls_value = decls_compute();
        if(isFinal && num == state().boundariesCrossed)
            decls_computed = true;
        return decls_value;
    }

    private SimpleSet decls_compute() {
    return chooseConstructor(lookupConstructor(), getArgList());
  }

    protected boolean decl_computed = false;
    protected ConstructorDecl decl_value;
    // Declared in LookupConstructor.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorDecl decl() {
        if(decl_computed) {
            return decl_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decl_value = decl_compute();
        if(isFinal && num == state().boundariesCrossed)
            decl_computed = true;
        return decl_value;
    }

    private ConstructorDecl decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (ConstructorDecl)decls.iterator().next();
    return unknownConstructor();
  }

    // Declared in NameCheck.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public boolean validArgs() {
        ASTNode$State state = state();
        boolean validArgs_value = validArgs_compute();
        return validArgs_value;
    }

    private boolean validArgs_compute() {
    for(int i = 0; i < getNumArg(); i++)
      if(getArg(i).type().isUnknown())
        return false;
    return true;
  }

    // Declared in QualifiedNames.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return "this";  }

    // Declared in SyntacticClassification.jrag at line 129
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in TypeAnalysis.jrag at line 285
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

    private TypeDecl type_compute() {  return decl().type();  }

    // Declared in MethodSignature.jrag at line 326
 @SuppressWarnings({"unchecked", "cast"})     public int arity() {
        ASTNode$State state = state();
        int arity_value = arity_compute();
        return arity_value;
    }

    private int arity_compute() {  return getNumArg();  }

    // Declared in VariableArityParameters.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public boolean invokesVariableArityAsArray() {
        ASTNode$State state = state();
        boolean invokesVariableArityAsArray_value = invokesVariableArityAsArray_compute();
        return invokesVariableArityAsArray_value;
    }

    private boolean invokesVariableArityAsArray_compute() {
    if(!decl().isVariableArity())
      return false;
    if(arity() != decl().arity())
      return false;
    return getArg(getNumArg()-1).type().methodInvocationConversionTo(decl().lastParameter().type());
  }

    // Declared in Alias.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayDefine(Location l) {
        ASTNode$State state = state();
        boolean mayDefine_Location_value = mayDefine_compute(l);
        return mayDefine_Location_value;
    }

    private boolean mayDefine_compute(Location l) {  return l.isHeapLocation();  }

    // Declared in Alias.jrag at line 46
 @SuppressWarnings({"unchecked", "cast"})     public boolean mustDefine(Location l) {
        ASTNode$State state = state();
        boolean mustDefine_Location_value = mustDefine_compute(l);
        return mustDefine_Location_value;
    }

    private boolean mustDefine_compute(Location l) {  return false;  }

    // Declared in ControlFlowGraph.jrag at line 255
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getNumArg() == 0 ? this : getArg(0).first();  }

    // Declared in ControlFlowGraph.jrag at line 258
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

    private SmallSet<CFGNode> succ_compute() {
		SmallSet<CFGNode> res = following();
		for(Access exn : decl().getExceptions())
			res = res.union(throwTarget(exn.type()));
		res = res.union(uncheckedExnTarget());
		return res;
	}

    // Declared in Exceptions.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<TypeDecl> uncaughtExceptions() {
        if(uncaughtExceptions_computed) {
            return uncaughtExceptions_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        uncaughtExceptions_value = uncaughtExceptions_compute();
        if(isFinal && num == state().boundariesCrossed)
            uncaughtExceptions_computed = true;
        return uncaughtExceptions_value;
    }

    private SmallSet<TypeDecl> uncaughtExceptions_compute() {
    SmallSet<TypeDecl> uncaughtExns = super.uncaughtExceptions();
    for(Access acc : decl().getExceptions())
      uncaughtExns = uncaughtExns.union(acc.type());
    return uncaughtExns;
  }

    // Declared in Purity.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return false;  }

    // Declared in DataFlow.jrag at line 110
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

    private Location getLocation_compute() {  return decl();  }

    // Declared in ExceptionHandling.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        ASTNode$State state = state();
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        return handlesException_TypeDecl_value;
    }

    // Declared in LookupConstructor.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupConstructor() {
        ASTNode$State state = state();
        Collection lookupConstructor_value = getParent().Define_Collection_lookupConstructor(this, null);
        return lookupConstructor_value;
    }

    // Declared in LookupConstructor.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorDecl unknownConstructor() {
        ASTNode$State state = state();
        ConstructorDecl unknownConstructor_value = getParent().Define_ConstructorDecl_unknownConstructor(this, null);
        return unknownConstructor_value;
    }

    // Declared in LookupMethod.jrag at line 29
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupMethod(name);
        }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 88
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().hasPackage(packageName);
        }
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 166
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupType(name);
        }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 131
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 121
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.EXPRESSION_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 18
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return unqualifiedScope().methodHost();
        }
        return getParent().Define_String_methodHost(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 130
    public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_inExplicitConstructorInvocation(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 256
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return SmallSet.<CFGNode>singleton(i + 1 < getNumArg() ? getArg(i + 1).first() : this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 366
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getArg(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 367
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getArg(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in ExprExt.jrag at line 37
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }

    // Declared in Precedence.jrag at line 61
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in AccessMethod.jrag at line 75
    public MethodAccessInfo Define_MethodAccessInfo_accessMethod(ASTNode caller, ASTNode child, MethodDecl md) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().accessMethod(md);
        }
        return getParent().Define_MethodAccessInfo_accessMethod(this, caller, md);
    }

    // Declared in AccessVariable.jrag at line 251
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().accessVariable(decl);
        }
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected void collect_contributors_GenericConstructorDecl_parUses() {
        // Declared in ConstructorExt.jrag at line 48
        if(decl() instanceof ParConstructorDecl) {
        {
            GenericConstructorDecl ref = (GenericConstructorDecl)(((ParConstructorDecl)decl()).sourceConstructorDecl());
            if(ref != null)
                ref.GenericConstructorDecl_parUses_contributors().add(this);
        }
        }
        super.collect_contributors_GenericConstructorDecl_parUses();
    }
    protected void collect_contributors_ConstructorDecl_uses() {
        // Declared in Uses.jrag at line 45
        {
            ConstructorDecl ref = (ConstructorDecl)(decl());
            if(ref != null)
                ref.ConstructorDecl_uses_contributors().add(this);
        }
        super.collect_contributors_ConstructorDecl_uses();
    }
    protected void collect_contributors_TypeDecl_instantiations() {
        // Declared in Uses.jrag at line 51
        {
            TypeDecl ref = (TypeDecl)(decl().hostType());
            if(ref != null)
                ref.TypeDecl_instantiations_contributors().add(this);
        }
        super.collect_contributors_TypeDecl_instantiations();
    }
    protected void contributeTo_GenericConstructorDecl_GenericConstructorDecl_parUses(Collection<Access> collection) {
        super.contributeTo_GenericConstructorDecl_GenericConstructorDecl_parUses(collection);
        if(decl() instanceof ParConstructorDecl)
            collection.add(this);
    }

    protected void contributeTo_ConstructorDecl_ConstructorDecl_uses(Collection<Access> collection) {
        super.contributeTo_ConstructorDecl_ConstructorDecl_uses(collection);
        collection.add(this);
    }

    protected void contributeTo_TypeDecl_TypeDecl_instantiations(Collection<Access> collection) {
        super.contributeTo_TypeDecl_TypeDecl_instantiations(collection);
        collection.add(this);
    }

}
