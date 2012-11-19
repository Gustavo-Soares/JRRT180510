
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class ParameterDeclaration extends ASTNode<ASTNode> implements Cloneable, SimpleSet, Iterator, Variable, CFGNode, Definition, LocalVariable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
        sourceVariableDecl_computed = false;
        sourceVariableDecl_value = null;
        isIn_computed = false;
        isOut_computed = false;
        isRef_computed = false;
        isWrite_computed = false;
        isRead_computed = false;
        succ_computed = false;
        succ_value = null;
        reachingDefinitionsFor_Location_values = null;
        forward_cdep_computed = false;
        forward_cdep_value = null;
        backward_cdep_computed = false;
        backward_cdep_value = null;
        collect_BackwardEdge_CFGNode_values = null;
        collect_ForwardEdge_CFGNode_values = null;
        following_computed = false;
        following_value = null;
        ParameterDeclaration_uses_computed = false;
        ParameterDeclaration_uses_value = null;
    ParameterDeclaration_uses_contributors = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        ParameterDeclaration_uses_computed = false;
        ParameterDeclaration_uses_value = null;
    ParameterDeclaration_uses_contributors = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration clone() throws CloneNotSupportedException {
        ParameterDeclaration node = (ParameterDeclaration)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.sourceVariableDecl_computed = false;
        node.sourceVariableDecl_value = null;
        node.isIn_computed = false;
        node.isOut_computed = false;
        node.isRef_computed = false;
        node.isWrite_computed = false;
        node.isRead_computed = false;
        node.succ_computed = false;
        node.succ_value = null;
        node.reachingDefinitionsFor_Location_values = null;
        node.forward_cdep_computed = false;
        node.forward_cdep_value = null;
        node.backward_cdep_computed = false;
        node.backward_cdep_value = null;
        node.collect_BackwardEdge_CFGNode_values = null;
        node.collect_ForwardEdge_CFGNode_values = null;
        node.following_computed = false;
        node.following_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration copy() {
      try {
          ParameterDeclaration node = (ParameterDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration fullCopy() {
        ParameterDeclaration res = (ParameterDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DataStructures.jrag at line 117

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 121

  public boolean isSingleton() { return true; }

    // Declared in DataStructures.jrag at line 122

  public boolean isSingleton(Object o) { return contains(o); }

    // Declared in DataStructures.jrag at line 125

  private ParameterDeclaration iterElem;

    // Declared in DataStructures.jrag at line 126

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 127

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 128

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 129

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in NameCheck.jrag at line 328

  
  public void nameCheck() {
    SimpleSet decls = outerScope().lookupVariable(name());
    for(Iterator iter = decls.iterator(); iter.hasNext(); ) {
      Variable var = (Variable)iter.next();
      if(var instanceof VariableDeclaration) {
        VariableDeclaration decl = (VariableDeclaration)var;
	      if(decl.enclosingBodyDecl() == enclosingBodyDecl())
  	      error("duplicate declaration of local variable " + name());
      }
      else if(var instanceof ParameterDeclaration) {
        ParameterDeclaration decl = (ParameterDeclaration)var;
	      if(decl.enclosingBodyDecl() == enclosingBodyDecl())
          error("duplicate declaration of local variable " + name());
      }
    }

    // 8.4.1  
    if(!lookupVariable(name()).contains(this)) {
      error("duplicate declaration of parameter " + name());
    }
  }

    // Declared in NodeConstructors.jrag at line 11

  public ParameterDeclaration(Access type, String name) {
    this(new Modifiers(new List()), type, name);
  }

    // Declared in NodeConstructors.jrag at line 14

  public ParameterDeclaration(TypeDecl type, String name) {
    this(new Modifiers(new List()), type.createQualifiedAccess(), name);
  }

    // Declared in PrettyPrint.jadd at line 232


  public void toString(StringBuffer s) {
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
  }

    // Declared in Alias.jrag at line 26

	public boolean mustAlias(Location l) { return mayAlias(l); }

    // Declared in ParameterExt.jrag at line 8

	
	public ParameterDeclaration lockedCopy() {
		return new ParameterDeclaration((Modifiers)getModifiers().fullCopy(), type().createLockedAccess(), name());
	}

    // Declared in IntroduceOutParameter.jrag at line 43


	public void makeOut() {
		if(isWrite())
			return;
		getModifiers().addModifier(new Modifier("out"));
	}

    // Declared in OpenVariables.jrag at line 12

	
	public void inline() {
		AnonymousMethod anon = (AnonymousMethod)getParent().getParent();
		int i = anon.getParameterList().getIndexOfChild(this);
		Expr arg = anon.lookupArg(name());
		if(isIn()) {
			arg = (Expr)arg.lockAllNames();
			VariableDeclaration newdecl = asFreshVariableDeclaration();
			anon.removeParameter(i);
			anon.removeArg(i);
			newdecl.setInit(arg);
			anon.getBlock().insertStmt(0, newdecl);
		} else {
			if(!(arg instanceof VarAccess))
				throw new RefactoringException("cannot inline argument");
			Variable decl = ((VarAccess)arg).decl();
			Collection<VarAccess> uses = uses();
			anon.removeParameter(i);
			anon.removeArg(i);
			for(VarAccess v : uses) {
				v.flushCaches();
				v.lock(decl);
			}
		}
	}

    // Declared in DemandFinalQualifier.jrag at line 40

	
	public void setDemandFinal() { getModifiers().addDemandFinalModifier(); }

    // Declared in FreshVariables.jrag at line 172

	public void freshenDeclaration() {
		Collection<VarAccess> uses = allUses();
		FreshParameter fp = new FreshParameter(getModifiers(), getTypeAccess(), getID());
		for(VarAccess va : uses)
			va.lock(fp);
		replaceWith(fp);
	}

    // Declared in FreshVariables.jrag at line 187


	public FreshLocalVariable asFreshVariableDeclaration() {
		Collection<VarAccess> uses = allUses();
		FreshLocalVariable fv = new FreshLocalVariable(getModifiers(), getTypeAccess(), getID(), new Opt());
		for(VarAccess va : uses)
			va.lock(fv);
		return fv;
	}

    // Declared in VarNesting.jrag at line 23

	
	// TODO: this should only be defined once, on LocalDeclaration
	public boolean canIntroduceLocal(String name) {
		if(name.equals(name()))
			return false;
		return super.canIntroduceLocal(name);
	}

    // Declared in RenameVariable.jrag at line 35


	public void checkRenamingPreconds(String new_name) {
		if(!hostBodyDecl().canIntroduceLocal(new_name))
			throw new RefactoringException("cannot rename variable to "+new_name);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 84

    public ParameterDeclaration() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 84
    public ParameterDeclaration(Modifiers p0, Access p1, String p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 84
    public ParameterDeclaration(Modifiers p0, Access p1, beaver.Symbol p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in java.ast at line 23


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 26

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in java.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(1);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
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

    // Declared in Alias.jrag at line 10

	
	public boolean isHeapLocation() { return false; }

    // Declared in Alias.jrag at line 20

	public boolean isArrayElement() { return false; }

    // Declared in Alias.jrag at line 23

	
	public boolean refined_Alias_Variable_mayAlias(Location l) { return this == l; }

    // Declared in AST.jrag at line 56

	
	public boolean isField() {
		return isClassVariable() || isInstanceVariable();
	}

    // Declared in Refresh.jrag at line 2

	public Variable refresh() { return refreshVariable(); }

    // Declared in Uses.jrag at line 3

	// bind all uses of a variable to its declaration
	public Collection<VarAccess> allUses() { return uses(); }

    // Declared in Uses.jrag at line 5

	
	public boolean isUsed() { return !allUses().isEmpty(); }

    // Declared in VariableExt.jrag at line 2

	public VariableDeclaration asVariableDeclaration(Expr init) {
		Modifiers mods = (Modifiers)getModifiers().fullCopy();
		mods.removeModifiers("public", "protected", "private", "static");
		return new VariableDeclaration(mods, type().createLockedAccess(), name(), new Opt(init));
	}

    // Declared in FreshVariables.jrag at line 133

	
	// this shouldn't really be necessary, but JastAdd doesn't propagate through
	// more than one interface
	public void collectAllDecls(Collection<Declaration> res) {
		res.add(this);
		super.collectAllDecls(res);
	}

    // Declared in LockedVariableAccess.jrag at line 6

	
	public VarAccess createLockedAccess() {
		VarAccess va = new VarAccess(this.name());
		va.lock(this);
		return va;
	}

    // Declared in LockedVariableAccess.jrag at line 42

	
	public boolean isSubstituted() { return false; }

    // Declared in RenameVariable.jrag at line 6

	public void rename(String new_name) {
		if(name().equals(new_name))
			return;
		if(!isValidName(new_name))
			throw new RefactoringException("not a valid name: "+new_name);
		checkRenamingPreconds(new_name);
		programRoot().lockNames(name(), new_name);
		setID(new_name);
		programRoot().flushCaches();
		programRoot().eliminate(LOCKED_NAMES);
	}

    // Declared in Testing.jrag at line 212

	
	public Variable findVariable(String name) {
		if(name().equals(name))
			return this;
		return super.findVariable(name);
	}

    // Declared in DataFlow.jrag at line 132

	
	  public boolean mayAlias(Location l) {
		return l instanceof Callable && this.isField() || refined_Alias_Variable_mayAlias(l);
	}

    // Declared in ControlFlowGraph.jrag at line 49

    
    public SmallSet<CFGNode> pred() { return collPred(); }

    // Declared in ControlFlow.jrag at line 15

	
	public void lockControlFlow() {
		if(lockedSucc == null)
			lockedSucc = forward_cdep();
		if(lockedPred == null)
			lockedPred = backward_cdep();
	}

    // Declared in ControlFlow.jrag at line 22

	
	public void unlockControlFlow() {
		if(lockedSucc != null) {
			if(!forward_cdep().equals(lockedSucc))
				throw new RefactoringException("couldn't preserve control flow");
			lockedSucc = null;
		}
		if(lockedPred != null) {
			if(!backward_cdep().equals(lockedPred))
				throw new RefactoringException("couldn't preserve control flow");
			lockedPred = null;
		}
	}

    // Declared in ControlFlow.jrag at line 51

	
	public void backward_cdep(Collection<CFGNode> dep) {
		if(succ().size() > 1) {
			dep.add(this);
		} else {
			for(CFGNode p : pred())
				p.backward_cdep(dep);
		}
	}

    // Declared in DependencyEdges.jrag at line 86

	public IdentityHashMap<DependencyEdge, SmallSet<CFGNode>> getDependencies() {
		if(dependencies == null)
			dependencies = new IdentityHashMap<DependencyEdge, SmallSet<CFGNode>>();
		return dependencies;
	}

    // Declared in DependencyEdges.jrag at line 100

	
	public void lockDependencies(DependencyEdge... edges) {
		for(DependencyEdge e : edges)
			if(e.isStart(this))
				getDependencies().put(e, e.collect(this));
		super.lockDependencies(edges);
	}

    // Declared in DependencyEdges.jrag at line 115

	
	public void unlockDependencies(PreservationStrategy r) {
		DependencyEdge e = r.getEdge();
		if(e.isStart(this)) {
			SmallSet<CFGNode> old_edges = getDependencies().get(e);
			if(old_edges != null) {
				SmallSet<CFGNode> new_edges = e.collect(this);
				if(r.preserve(this) && !old_edges.subsetOf(new_edges))
					throw new RefactoringException("couldn't preserve edge: "+e);
				if(r.reflect(this) && !new_edges.subsetOf(old_edges))
					throw new RefactoringException("couldn't reflect edge: "+e);
			}
		}
		super.unlockDependencies(r);
	}

    // Declared in AccessVariable.jrag at line 85

	public SymbolicVarAccess moveInto(Scope s) {
		if(s.hasVariable(name()))
			return unknownVarAccess();
		return this;
	}

    // Declared in AccessVariable.jrag at line 90

	public boolean isUnknownVarAccess() {
		return false;
	}

    // Declared in AccessVariable.jrag at line 93

	public SymbolicVarAccess ensureStatic() {
		return unknownVarAccess();
	}

    // Declared in AccessVariable.jrag at line 96

	public SymbolicVarAccess ensureFinal() {
		return isFinal() ? this : unknownVarAccess();
	}

    // Declared in LockedVariableAccess.jrag at line 133

	
	public Access asAccess(Expr qualifier, TypeDecl enclosing) {
		if(qualifier != null)
			throw new RefactoringException("cannot qualify access to local variable");
		return new VarAccess(name());
	}

    // Declared in DataStructures.jrag at line 115
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        int size_value = size_compute();
        return size_value;
    }

    private int size_compute() {  return 1;  }

    // Declared in DataStructures.jrag at line 116
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    // Declared in DataStructures.jrag at line 120
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        ASTNode$State state = state();
        boolean contains_Object_value = contains_compute(o);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

    // Declared in Modifiers.jrag at line 218
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        boolean isSynthetic_value = isSynthetic_compute();
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }

    // Declared in PrettyPrint.jadd at line 812
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 253
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

    // Declared in VariableDeclaration.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassVariable() {
        ASTNode$State state = state();
        boolean isClassVariable_value = isClassVariable_compute();
        return isClassVariable_value;
    }

    private boolean isClassVariable_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInstanceVariable() {
        ASTNode$State state = state();
        boolean isInstanceVariable_value = isInstanceVariable_compute();
        return isInstanceVariable_value;
    }

    private boolean isInstanceVariable_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 78
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLocalVariable() {
        ASTNode$State state = state();
        boolean isLocalVariable_value = isLocalVariable_compute();
        return isLocalVariable_value;
    }

    private boolean isLocalVariable_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 96
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        boolean isFinal_value = isFinal_compute();
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal();  }

    // Declared in VariableDeclaration.jrag at line 97
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVolatile() {
        ASTNode$State state = state();
        boolean isVolatile_value = isVolatile_compute();
        return isVolatile_value;
    }

    private boolean isVolatile_compute() {  return getModifiers().isVolatile();  }

    // Declared in VariableDeclaration.jrag at line 98
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlank() {
        ASTNode$State state = state();
        boolean isBlank_value = isBlank_compute();
        return isBlank_value;
    }

    private boolean isBlank_compute() {  return true;  }

    // Declared in VariableDeclaration.jrag at line 99
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        boolean isStatic_value = isStatic_compute();
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 101
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    // Declared in VariableDeclaration.jrag at line 103
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasInit() {
        ASTNode$State state = state();
        boolean hasInit_value = hasInit_compute();
        return hasInit_value;
    }

    private boolean hasInit_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 104
 @SuppressWarnings({"unchecked", "cast"})     public Expr getInit() {
        ASTNode$State state = state();
        Expr getInit_value = getInit_compute();
        return getInit_value;
    }

    private Expr getInit_compute() { throw new UnsupportedOperationException(); }

    // Declared in VariableDeclaration.jrag at line 105
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        Constant constant_value = constant_compute();
        return constant_value;
    }

    private Constant constant_compute() { throw new UnsupportedOperationException(); }

    protected boolean sourceVariableDecl_computed = false;
    protected Variable sourceVariableDecl_value;
    // Declared in Generics.jrag at line 1285
 @SuppressWarnings({"unchecked", "cast"})     public Variable sourceVariableDecl() {
        if(sourceVariableDecl_computed) {
            return sourceVariableDecl_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        sourceVariableDecl_value = sourceVariableDecl_compute();
        if(isFinal && num == state().boundariesCrossed)
            sourceVariableDecl_computed = true;
        return sourceVariableDecl_value;
    }

    private Variable sourceVariableDecl_compute() {  return this;  }

    // Declared in VariableArityParameters.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariableArity() {
        ASTNode$State state = state();
        boolean isVariableArity_value = isVariableArity_compute();
        return isVariableArity_value;
    }

    private boolean isVariableArity_compute() {  return false;  }

    // Declared in ReachingDefinitions.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return mayAlias(l);  }

    // Declared in ReachingDefinitions.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return mustAlias(l);  }

    // Declared in Refresh.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public Variable refreshVariable() {
        ASTNode$State state = state();
        Variable refreshVariable_value = refreshVariable_compute();
        return refreshVariable_value;
    }

    private Variable refreshVariable_compute() {  return this;  }

    protected boolean isIn_computed = false;
    protected boolean isIn_value;
    // Declared in AnonymousMethods.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public boolean isIn() {
        if(isIn_computed) {
            return isIn_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isIn_value = isIn_compute();
        if(isFinal && num == state().boundariesCrossed)
            isIn_computed = true;
        return isIn_value;
    }

    private boolean isIn_compute() {  return !isOut() && !isRef();  }

    protected boolean isOut_computed = false;
    protected boolean isOut_value;
    // Declared in AnonymousMethods.jrag at line 227
 @SuppressWarnings({"unchecked", "cast"})     public boolean isOut() {
        if(isOut_computed) {
            return isOut_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isOut_value = isOut_compute();
        if(isFinal && num == state().boundariesCrossed)
            isOut_computed = true;
        return isOut_value;
    }

    private boolean isOut_compute() {  return getModifiers().numModifier("out") != 0;  }

    protected boolean isRef_computed = false;
    protected boolean isRef_value;
    // Declared in AnonymousMethods.jrag at line 228
 @SuppressWarnings({"unchecked", "cast"})     public boolean isRef() {
        if(isRef_computed) {
            return isRef_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isRef_value = isRef_compute();
        if(isFinal && num == state().boundariesCrossed)
            isRef_computed = true;
        return isRef_value;
    }

    private boolean isRef_compute() {  return getModifiers().numModifier("ref") != 0;  }

    protected boolean isWrite_computed = false;
    protected boolean isWrite_value;
    // Declared in AnonymousMethods.jrag at line 229
 @SuppressWarnings({"unchecked", "cast"})     public boolean isWrite() {
        if(isWrite_computed) {
            return isWrite_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isWrite_value = isWrite_compute();
        if(isFinal && num == state().boundariesCrossed)
            isWrite_computed = true;
        return isWrite_value;
    }

    private boolean isWrite_compute() {  return !isIn();  }

    protected boolean isRead_computed = false;
    protected boolean isRead_value;
    // Declared in AnonymousMethods.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public boolean isRead() {
        if(isRead_computed) {
            return isRead_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isRead_value = isRead_compute();
        if(isFinal && num == state().boundariesCrossed)
            isRead_computed = true;
        return isRead_value;
    }

    private boolean isRead_compute() {  return !isOut();  }

    // Declared in DemandFinalQualifier.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeFinal() {
        ASTNode$State state = state();
        boolean mayBeFinal_value = mayBeFinal_compute();
        return mayBeFinal_value;
    }

    private boolean mayBeFinal_compute() {
		for(VarAccess va : uses())
			if(va.isDest())
				return false;
		return true;
	}

    // Declared in DemandFinalQualifier.jrag at line 6
 @SuppressWarnings({"unchecked", "cast"})     public boolean shouldBeFinal() {
        ASTNode$State state = state();
        boolean shouldBeFinal_value = shouldBeFinal_compute();
        return shouldBeFinal_value;
    }

    private boolean shouldBeFinal_compute() {
		if(isClassVariable() || isInstanceVariable())
			return false;
		for(VarAccess va : uses())
			if(va.hostType() != this.hostType())
				return true;
		return false;
	}

    // Declared in AccessVariable.jrag at line 128
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSourceDeclAs(Variable decl) {
        ASTNode$State state = state();
        boolean sameSourceDeclAs_Variable_value = sameSourceDeclAs_compute(decl);
        return sameSourceDeclAs_Variable_value;
    }

    private boolean sameSourceDeclAs_compute(Variable decl) {  return sourceVariableDecl().equals(decl.sourceVariableDecl());  }

    // Declared in ControlFlowGraph.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return this;  }

    protected boolean succ_computed = false;
    protected SmallSet<CFGNode> succ_value;
    // Declared in ControlFlowGraph.jrag at line 56
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

    private SmallSet<CFGNode> succ_compute() {  return following();  }

    // Declared in ControlFlowGraph.jrag at line 761
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> succStmt() {
        ASTNode$State state = state();
        SmallSet<Stmt> succStmt_value = succStmt_compute();
        return succStmt_value;
    }

    private SmallSet<Stmt> succStmt_compute() {
		SmallSet<Stmt> res = new SmallSet<Stmt>();
		for(CFGNode n : succ()) {
			if(n instanceof Stmt)
				res = res.union((Stmt)n);
			else
				res = res.union(n.succStmt());
		}
		return res;
	}

    // Declared in ControlFlowGraph.jrag at line 772
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> predStmt() {
        ASTNode$State state = state();
        SmallSet<Stmt> predStmt_value = predStmt_compute();
        return predStmt_value;
    }

    private SmallSet<Stmt> predStmt_compute() {
		SmallSet<Stmt> res = new SmallSet<Stmt>();
		for(CFGNode n : pred()) {
			if(n instanceof Stmt)
				res = res.union((Stmt)n);
			else
				res = res.union(n.predStmt());
		}
		return res;
	}

    protected java.util.Map reachingDefinitionsFor_Location_values;
    // Declared in ReachingDefinitions.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Definition> reachingDefinitionsFor(Location l) {
        Object _parameters = l;
if(reachingDefinitionsFor_Location_values == null) reachingDefinitionsFor_Location_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(reachingDefinitionsFor_Location_values.containsKey(_parameters)) {
            Object _o = reachingDefinitionsFor_Location_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<Definition>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            reachingDefinitionsFor_Location_values.put(_parameters, _value);
            _value.value = SmallSet.<Definition>empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<Definition> new_reachingDefinitionsFor_Location_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_reachingDefinitionsFor_Location_value = reachingDefinitionsFor_compute(l);
                if ((new_reachingDefinitionsFor_Location_value==null && (SmallSet<Definition>)_value.value!=null) || (new_reachingDefinitionsFor_Location_value!=null && !new_reachingDefinitionsFor_Location_value.equals((SmallSet<Definition>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_reachingDefinitionsFor_Location_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                reachingDefinitionsFor_Location_values.put(_parameters, new_reachingDefinitionsFor_Location_value);
            }
            else {
                reachingDefinitionsFor_Location_values.remove(_parameters);
            state.RESET_CYCLE = true;
            reachingDefinitionsFor_compute(l);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_reachingDefinitionsFor_Location_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<Definition> new_reachingDefinitionsFor_Location_value = reachingDefinitionsFor_compute(l);
            if (state.RESET_CYCLE) {
                reachingDefinitionsFor_Location_values.remove(_parameters);
            }
            else if ((new_reachingDefinitionsFor_Location_value==null && (SmallSet<Definition>)_value.value!=null) || (new_reachingDefinitionsFor_Location_value!=null && !new_reachingDefinitionsFor_Location_value.equals((SmallSet<Definition>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_reachingDefinitionsFor_Location_value;
            }
            return new_reachingDefinitionsFor_Location_value;
        }
        return (SmallSet<Definition>)_value.value;
    }

    private SmallSet<Definition> reachingDefinitionsFor_compute(Location l) {
		SmallSet<Definition> res = SmallSet.<Definition>empty();
		if(isReachingDefinitionFor(l)) {
			res = SmallSet.singleton((Definition)this);
			if(isBlockingDefinitionFor(l))
				return res;
		}
		for(CFGNode p : pred())
			res = res.union(p.reachingDefinitionsFor(l));
		return res;
	}

    protected boolean forward_cdep_computed = false;
    protected Collection<CFGNode> forward_cdep_value;
    // Declared in ControlFlow.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> forward_cdep() {
        if(forward_cdep_computed) {
            return forward_cdep_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        forward_cdep_value = forward_cdep_compute();
        if(isFinal && num == state().boundariesCrossed)
            forward_cdep_computed = true;
        return forward_cdep_value;
    }

    private Collection<CFGNode> forward_cdep_compute() {
		CFGNode p;
		for(p=this;p.succ().size() == 1;p=p.succ().iterator().next());
		Collection<CFGNode> dep = new HashSet<CFGNode>();
		for(CFGNode q : p.succ())
			dep.add(q);
		return dep;
	}

    protected boolean backward_cdep_computed = false;
    protected Collection<CFGNode> backward_cdep_value;
    // Declared in ControlFlow.jrag at line 44
 @SuppressWarnings({"unchecked", "cast"})     public Collection<CFGNode> backward_cdep() {
        if(backward_cdep_computed) {
            return backward_cdep_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        backward_cdep_value = backward_cdep_compute();
        if(isFinal && num == state().boundariesCrossed)
            backward_cdep_computed = true;
        return backward_cdep_value;
    }

    private Collection<CFGNode> backward_cdep_compute() {
		Collection<CFGNode> dep = new HashSet<CFGNode>();
		for(CFGNode p : pred())
			p.backward_cdep(dep);
		return dep;
	}

    protected java.util.Map collect_BackwardEdge_CFGNode_values;
    // Declared in DependencyEdges.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(BackwardEdge be, CFGNode start) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(be);
        _parameters.add(start);
if(collect_BackwardEdge_CFGNode_values == null) collect_BackwardEdge_CFGNode_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(collect_BackwardEdge_CFGNode_values.containsKey(_parameters)) {
            Object _o = collect_BackwardEdge_CFGNode_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<CFGNode>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            collect_BackwardEdge_CFGNode_values.put(_parameters, _value);
            _value.value = SmallSet.<CFGNode> empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<CFGNode> new_collect_BackwardEdge_CFGNode_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_collect_BackwardEdge_CFGNode_value = collect_compute(be, start);
                if ((new_collect_BackwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_BackwardEdge_CFGNode_value!=null && !new_collect_BackwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_collect_BackwardEdge_CFGNode_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                collect_BackwardEdge_CFGNode_values.put(_parameters, new_collect_BackwardEdge_CFGNode_value);
            }
            else {
                collect_BackwardEdge_CFGNode_values.remove(_parameters);
            state.RESET_CYCLE = true;
            collect_compute(be, start);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_collect_BackwardEdge_CFGNode_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<CFGNode> new_collect_BackwardEdge_CFGNode_value = collect_compute(be, start);
            if (state.RESET_CYCLE) {
                collect_BackwardEdge_CFGNode_values.remove(_parameters);
            }
            else if ((new_collect_BackwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_BackwardEdge_CFGNode_value!=null && !new_collect_BackwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_collect_BackwardEdge_CFGNode_value;
            }
            return new_collect_BackwardEdge_CFGNode_value;
        }
        return (SmallSet<CFGNode>)_value.value;
    }

    private SmallSet<CFGNode> collect_compute(BackwardEdge be, CFGNode start) {
		SmallSet<CFGNode> res = be.isTarget(start, this) ? SmallSet.singleton((CFGNode)this)
													  : SmallSet.<CFGNode>empty();
		if(be.terminates(start, this))
			return res;
		for(CFGNode p : pred())
			res = res.union(p.collect(be, start));
		return res;
	}

    protected java.util.Map collect_ForwardEdge_CFGNode_values;
    // Declared in DependencyEdges.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collect(ForwardEdge fe, CFGNode start) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(fe);
        _parameters.add(start);
if(collect_ForwardEdge_CFGNode_values == null) collect_ForwardEdge_CFGNode_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(collect_ForwardEdge_CFGNode_values.containsKey(_parameters)) {
            Object _o = collect_ForwardEdge_CFGNode_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return (SmallSet<CFGNode>)_o;
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            collect_ForwardEdge_CFGNode_values.put(_parameters, _value);
            _value.value = SmallSet.<CFGNode> empty();
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            SmallSet<CFGNode> new_collect_ForwardEdge_CFGNode_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
                new_collect_ForwardEdge_CFGNode_value = collect_compute(fe, start);
                if ((new_collect_ForwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_ForwardEdge_CFGNode_value!=null && !new_collect_ForwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                    state.CHANGE = true;
                    _value.value = new_collect_ForwardEdge_CFGNode_value;
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                collect_ForwardEdge_CFGNode_values.put(_parameters, new_collect_ForwardEdge_CFGNode_value);
            }
            else {
                collect_ForwardEdge_CFGNode_values.remove(_parameters);
            state.RESET_CYCLE = true;
            collect_compute(fe, start);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_collect_ForwardEdge_CFGNode_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
            SmallSet<CFGNode> new_collect_ForwardEdge_CFGNode_value = collect_compute(fe, start);
            if (state.RESET_CYCLE) {
                collect_ForwardEdge_CFGNode_values.remove(_parameters);
            }
            else if ((new_collect_ForwardEdge_CFGNode_value==null && (SmallSet<CFGNode>)_value.value!=null) || (new_collect_ForwardEdge_CFGNode_value!=null && !new_collect_ForwardEdge_CFGNode_value.equals((SmallSet<CFGNode>)_value.value))) {
                state.CHANGE = true;
                _value.value = new_collect_ForwardEdge_CFGNode_value;
            }
            return new_collect_ForwardEdge_CFGNode_value;
        }
        return (SmallSet<CFGNode>)_value.value;
    }

    private SmallSet<CFGNode> collect_compute(ForwardEdge fe, CFGNode start) {
		SmallSet<CFGNode> res = fe.isTarget(start, this) ? SmallSet.singleton((CFGNode)this)
												  	  : SmallSet.<CFGNode>empty();
		if(fe.terminates(start, this))
			return res;
		for(CFGNode p : succ())
			res = res.union(p.collect(fe, start));
		return res;
	}

    // Declared in LookupVariable.jrag at line 22
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in NameCheck.jrag at line 288
 @SuppressWarnings({"unchecked", "cast"})     public VariableScope outerScope() {
        ASTNode$State state = state();
        VariableScope outerScope_value = getParent().Define_VariableScope_outerScope(this, null);
        return outerScope_value;
    }

    // Declared in NameCheck.jrag at line 349
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl enclosingBodyDecl() {
        ASTNode$State state = state();
        BodyDecl enclosingBodyDecl_value = getParent().Define_BodyDecl_enclosingBodyDecl(this, null);
        return enclosingBodyDecl_value;
    }

    // Declared in TypeAnalysis.jrag at line 586
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in VariableDeclaration.jrag at line 75
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodParameter() {
        ASTNode$State state = state();
        boolean isMethodParameter_value = getParent().Define_boolean_isMethodParameter(this, null);
        return isMethodParameter_value;
    }

    // Declared in VariableDeclaration.jrag at line 76
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorParameter() {
        ASTNode$State state = state();
        boolean isConstructorParameter_value = getParent().Define_boolean_isConstructorParameter(this, null);
        return isConstructorParameter_value;
    }

    // Declared in VariableDeclaration.jrag at line 77
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExceptionHandlerParameter() {
        ASTNode$State state = state();
        boolean isExceptionHandlerParameter_value = getParent().Define_boolean_isExceptionHandlerParameter(this, null);
        return isExceptionHandlerParameter_value;
    }

    // Declared in LocalVariable.jrag at line 11
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl hostBodyDecl() {
        ASTNode$State state = state();
        BodyDecl hostBodyDecl_value = getParent().Define_BodyDecl_hostBodyDecl(this, null);
        return hostBodyDecl_value;
    }

    // Declared in ParameterExt.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public Parameterisable parameterOwner() {
        ASTNode$State state = state();
        Parameterisable parameterOwner_value = getParent().Define_Parameterisable_parameterOwner(this, null);
        return parameterOwner_value;
    }

    // Declared in AccessVariable.jrag at line 140
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess accessVariable_Variable_value = getParent().Define_SymbolicVarAccess_accessVariable(this, null, decl);
        return accessVariable_Variable_value;
    }

    // Declared in AccessVariable.jrag at line 119
 @SuppressWarnings({"unchecked", "cast"})     public UnknownVarAccess unknownVarAccess() {
        ASTNode$State state = state();
        UnknownVarAccess unknownVarAccess_value = getParent().Define_UnknownVarAccess_unknownVarAccess(this, null);
        return unknownVarAccess_value;
    }

    protected boolean following_computed = false;
    protected SmallSet<CFGNode> following_value;
    // Declared in ControlFlowGraph.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> following() {
        if(following_computed) {
            return following_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        following_value = getParent().Define_SmallSet_CFGNode__following(this, null);
        if(isFinal && num == state().boundariesCrossed)
            following_computed = true;
        return following_value;
    }

    // Declared in Modifiers.jrag at line 286
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Annotations.jrag at line 83
    public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
        if(caller == getModifiersNoTransform()) {
            return name.equals("PARAMETER");
        }
        return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
    }

    // Declared in Enums.jrag at line 79
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 542
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return SmallSet.empty();
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ModifiersExt.jrag at line 10
    public Variable Define_Variable_getModifiedVariable(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return this;
        }
        return getParent().Define_Variable_getModifiedVariable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected boolean ParameterDeclaration_uses_computed = false;
    protected Collection<VarAccess> ParameterDeclaration_uses_value;
    // Declared in Uses.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public Collection<VarAccess> uses() {
        if(ParameterDeclaration_uses_computed) {
            return ParameterDeclaration_uses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        ParameterDeclaration_uses_value = uses_compute();
        if(isFinal && num == state().boundariesCrossed)
            ParameterDeclaration_uses_computed = true;
        return ParameterDeclaration_uses_value;
    }

    java.util.Set ParameterDeclaration_uses_contributors;
    public java.util.Set ParameterDeclaration_uses_contributors() {
        if(ParameterDeclaration_uses_contributors == null) ParameterDeclaration_uses_contributors  = new ASTNode$State.IdentityHashSet(4);
        return ParameterDeclaration_uses_contributors;
    }
    private Collection<VarAccess> uses_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof BodyDecl))
            node = node.getParent();
        BodyDecl root = (BodyDecl)node;
        root.collect_contributors_ParameterDeclaration_uses();
        ParameterDeclaration_uses_value = new HashSet<VarAccess>();
        if(ParameterDeclaration_uses_contributors != null)
        for(java.util.Iterator iter = ParameterDeclaration_uses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_ParameterDeclaration_ParameterDeclaration_uses(ParameterDeclaration_uses_value);
        }
        return ParameterDeclaration_uses_value;
    }

    protected boolean CFGNode_collPred_computed = false;
    protected SmallSet<CFGNode> CFGNode_collPred_value;
    // Declared in ControlFlowGraph.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collPred() {
        if(CFGNode_collPred_computed) {
            return CFGNode_collPred_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        CFGNode_collPred_value = collPred_compute();
        if(isFinal && num == state().boundariesCrossed)
            CFGNode_collPred_computed = true;
        return CFGNode_collPred_value;
    }

    java.util.Set CFGNode_collPred_contributors;
    public java.util.Set CFGNode_collPred_contributors() {
        if(CFGNode_collPred_contributors == null) CFGNode_collPred_contributors  = new ASTNode$State.IdentityHashSet(4);
        return CFGNode_collPred_contributors;
    }
    private SmallSet<CFGNode> collPred_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof TypeDecl))
            node = node.getParent();
        TypeDecl root = (TypeDecl)node;
        root.collect_contributors_CFGNode_collPred();
        CFGNode_collPred_value = SmallSet.mutable();
        if(CFGNode_collPred_contributors != null)
        for(java.util.Iterator iter = CFGNode_collPred_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_CFGNode_CFGNode_collPred(CFGNode_collPred_value);
        }
        return CFGNode_collPred_value;
    }

    protected boolean Definition_reachedUses_computed = false;
    protected SmallSet<Access> Definition_reachedUses_value;
    // Declared in ReachingDefinitions.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Access> reachedUses() {
        if(Definition_reachedUses_computed) {
            return Definition_reachedUses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        Definition_reachedUses_value = reachedUses_compute();
        if(isFinal && num == state().boundariesCrossed)
            Definition_reachedUses_computed = true;
        return Definition_reachedUses_value;
    }

    java.util.Set Definition_reachedUses_contributors;
    public java.util.Set Definition_reachedUses_contributors() {
        if(Definition_reachedUses_contributors == null) Definition_reachedUses_contributors  = new ASTNode$State.IdentityHashSet(4);
        return Definition_reachedUses_contributors;
    }
    private SmallSet<Access> reachedUses_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof TypeDecl))
            node = node.getParent();
        TypeDecl root = (TypeDecl)node;
        root.collect_contributors_Definition_reachedUses();
        Definition_reachedUses_value = SmallSet.mutable();
        if(Definition_reachedUses_contributors != null)
        for(java.util.Iterator iter = Definition_reachedUses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_Definition_Definition_reachedUses(Definition_reachedUses_value);
        }
        return Definition_reachedUses_value;
    }

    protected void collect_contributors_CFGNode_collPred() {
        // Declared in ControlFlowGraph.jrag at line 39
        for(java.util.Iterator iter = (succ()).iterator(); iter.hasNext(); ) {
            CFGNode ref = (CFGNode)iter.next();
            if(ref != null)
            ref.CFGNode_collPred_contributors().add(this);
        }
        super.collect_contributors_CFGNode_collPred();
    }
    protected void contributeTo_CFGNode_CFGNode_collPred(SmallSet<CFGNode> collection) {
        super.contributeTo_CFGNode_CFGNode_collPred(collection);
        collection.add(this);
    }

}
