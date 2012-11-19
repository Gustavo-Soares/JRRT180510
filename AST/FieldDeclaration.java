
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class FieldDeclaration extends MemberDecl implements Cloneable, SimpleSet, Iterator, Variable, CFGNode, Definition, Visible {
    public void flushCache() {
        super.flushCache();
        accessibleFrom_TypeDecl_values = null;
        exceptions_computed = false;
        exceptions_value = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        constant_computed = false;
        constant_value = null;
        usesTypeVariable_computed = false;
        sourceVariableDecl_computed = false;
        sourceVariableDecl_value = null;
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
        FieldDeclaration_uses_computed = false;
        FieldDeclaration_uses_value = null;
    FieldDeclaration_uses_contributors = null;
        FieldDeclaration_staticImports_computed = false;
        FieldDeclaration_staticImports_value = null;
    FieldDeclaration_staticImports_contributors = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        FieldDeclaration_uses_computed = false;
        FieldDeclaration_uses_value = null;
    FieldDeclaration_uses_contributors = null;
        FieldDeclaration_staticImports_computed = false;
        FieldDeclaration_staticImports_value = null;
    FieldDeclaration_staticImports_contributors = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration clone() throws CloneNotSupportedException {
        FieldDeclaration node = (FieldDeclaration)super.clone();
        node.accessibleFrom_TypeDecl_values = null;
        node.exceptions_computed = false;
        node.exceptions_value = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.constant_computed = false;
        node.constant_value = null;
        node.usesTypeVariable_computed = false;
        node.sourceVariableDecl_computed = false;
        node.sourceVariableDecl_value = null;
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
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration copy() {
      try {
          FieldDeclaration node = (FieldDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration fullCopy() {
        FieldDeclaration res = (FieldDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BoundNames.jrag at line 11

  public Access createQualifiedBoundAccess() {
    if(isStatic())
      return hostType().createQualifiedAccess().qualifiesAccess(new BoundFieldAccess(this));
    else
      return new ThisAccess("this").qualifiesAccess(
        new BoundFieldAccess(this));
  }

    // Declared in BoundNames.jrag at line 86


  public Access createBoundFieldAccess() {
    return createQualifiedBoundAccess();
  }

    // Declared in DataStructures.jrag at line 81

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 85

  public boolean isSingleton() { return true; }

    // Declared in DataStructures.jrag at line 86

  public boolean isSingleton(Object o) { return contains(o); }

    // Declared in DataStructures.jrag at line 89

  private FieldDeclaration iterElem;

    // Declared in DataStructures.jrag at line 90

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 91

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 92

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 93

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in DefiniteAssignment.jrag at line 179

  
  public void definiteAssignment() {
    super.definiteAssignment();
    if(isBlank() && isFinal() && isClassVariable()) {
      boolean found = false;
      TypeDecl typeDecl = hostType();
      for(int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof StaticInitializer) {
          StaticInitializer s = (StaticInitializer)typeDecl.getBodyDecl(i);
          if(s.isDAafter(this))
            found = true;
        }
        
        else if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(f.isStatic() && f.isDAafter(this))
            found = true;
        }
        
      }
      if(!found)
        error("blank final class variable " + name() + " in " + hostType().typeName() + " is not definitely assigned in static initializer");

    }
    if(isBlank() && isFinal() && isInstanceVariable()) {
      TypeDecl typeDecl = hostType();
      boolean found = false;
      for(int i = 0; !found && i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(!f.isStatic() && f.isDAafter(this))
            found = true;
        }
        else if(typeDecl.getBodyDecl(i) instanceof InstanceInitializer) {
          InstanceInitializer ii = (InstanceInitializer)typeDecl.getBodyDecl(i);
          if(ii.getBlock().isDAafter(this))
            found = true;
        }
      }
      for(Iterator iter = typeDecl.constructors().iterator(); !found && iter.hasNext(); ) {
        ConstructorDecl c = (ConstructorDecl)iter.next();
        if(!c.isDAafter(this)) {
          error("blank final instance variable " + name() + " in " + hostType().typeName() + " is not definitely assigned after " + c.signature());
          }
      }
    }
    if(isBlank() && hostType().isInterfaceDecl()) {
            error("variable  " + name() + " in " + hostType().typeName() + " which is an interface must have an initializer");
    }

  }

    // Declared in Modifiers.jrag at line 112

 
  public void checkModifiers() {
    super.checkModifiers();
    if(hostType().isInterfaceDecl()) {
      if(isProtected())
        error("an interface field may not be protected");
      if(isPrivate())
        error("an interface field may not be private");
      if(isTransient())
        error("an interface field may not be transient");
      if(isVolatile())
        error("an interface field may not be volatile");
    }
  }

    // Declared in NameCheck.jrag at line 277


  public void nameCheck() {
    super.nameCheck();
    // 8.3
    for(Iterator iter = hostType().memberFields(name()).iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(v != this && v.hostType() == hostType())
        error("field named " + name() + " is multiply declared in type " + hostType().typeName());
    }

  }

    // Declared in NodeConstructors.jrag at line 86


  public FieldDeclaration(Modifiers m, Access type, String name) {
    this(m, type, name, new Opt());
  }

    // Declared in NodeConstructors.jrag at line 90

  
  public FieldDeclaration(Modifiers m, Access type, String name, Expr init) {
    this(m, type, name, new Opt(init));
  }

    // Declared in PrettyPrint.jadd at line 151


  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";");
  }

    // Declared in TypeCheck.jrag at line 33


  // 5.2
  public void typeCheck() {
    if(hasInit()) {
      TypeDecl source = getInit().type();
      TypeDecl dest = type();
      if(!source.assignConversionTo(dest, getInit()))
        error("can not assign " + name() + " of type " + dest.typeName() +
              " a value of type " + source.typeName());
    }
  }

    // Declared in Generics.jrag at line 1058

  public BodyDecl p(Parameterization parTypeDecl) {
    FieldDeclaration f = new FieldDeclarationSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getTypeAccess().type().substituteReturnType(parTypeDecl),
      getID(),
      new Opt(),
      this
    );
    return f;
  }

    // Declared in Alias.jrag at line 11

	public boolean isHeapLocation() { return !isConstant(); }

    // Declared in GenericsExt.jrag at line 35

	
	
	public Collection<FieldDeclarationSubstituted> substitutedCopies() {
		Collection<FieldDeclarationSubstituted> res = new LinkedList<FieldDeclarationSubstituted>();
		if(!hostType().isGenericType())
			return res;
		GenericTypeDecl host = (GenericTypeDecl)hostType();
		for(int i=0;i<host.getNumParTypeDecl();++i) {
			ParTypeDecl ptd = host.getParTypeDecl(i);
			for(Iterator<FieldDeclarationSubstituted> iter=ptd.localFields(name()).iterator();iter.hasNext();)
				res.add(iter.next());
		}
		return res;
	}

    // Declared in ModifiersExt.jrag at line 47

	public void makeModifiersExplicit() {
		if(hostType().isInterfaceDecl())
			getModifiers().addModifiers("public", "static", "final");
	}

    // Declared in Uses.jrag at line 65


	// field declarations can be used by static imports
	public boolean isUsed() {
		return !uses().isEmpty() || isStatic() && !staticImports().isEmpty();
	}

    // Declared in VariableExt.jrag at line 8

	
	public MethodDecl createGetter() {
//		String getter_name = type().isBoolean() ? "is"+capitalisedName() : "get"+capitalisedName();
		String getter_name = type().isBoolean() ? "is"+capitalisedName() : "get"+name();
		Modifiers old_mods = (Modifiers)getModifiers().fullCopy();
		old_mods.removeModifier("final");
		MethodDecl getter = new MethodDecl(old_mods, type().createLockedAccess(), getter_name, new List<ParameterDeclaration>(),
										   new List<Access>(), new Opt<Block>(new Block(new ReturnStmt(new VarAccess(name())))));
		hostType().insertUnusedMethod(getter);
		return getter;
	}

    // Declared in VariableExt.jrag at line 18

	
	public MethodDecl createSetter() {
		String setter_name = "set"+capitalisedName();
		Modifiers old_mods = (Modifiers)getModifiers().fullCopy();
		old_mods.removeModifier("final");
		ParameterDeclaration parm = new ParameterDeclaration(type().createLockedAccess(), name());
		MethodDecl setter = new MethodDecl(old_mods, type().createLockedAccess(), setter_name, 
				  					      new List<ParameterDeclaration>().add(parm), new List<Access>(),
				  					      new Opt<Block>(new Block(new ReturnStmt(new AssignSimpleExpr(this.createLockedAccess(), 
				  					    		  													   parm.createLockedAccess())))));
		hostType().insertUnusedMethod(setter);
		return setter;
	}

    // Declared in VariableExt.jrag at line 48

	
	// uses of a field, including all its substituted copies
	public ArrayList<VarAccess> usesOfAllCopies() {
		ArrayList<VarAccess> res = new ArrayList<VarAccess>(uses());
		for(FieldDeclaration fd : substitutedCopies())
			res.addAll(fd.uses());
		return res;
	}

    // Declared in InlineConstant.jrag at line 2

	public void inlineConstant(boolean remove) {
		if(hasInit())
			setInit(getInit().makeConversionExplicit(type()));
		for(VarAccess va : uses())
			va.inlineConstant();
		programRoot().flushCaches();
		if(remove)
			removeUnused();
	}

    // Declared in InlineConstant.jrag at line 31

	
	public void doInlineConstant(boolean remove) {
		Program root = programRoot();
		inlineConstant(remove);
		root.eliminate(LOCKED_NAMES, LOCKED_DATAFLOW);
	}

    // Declared in MoveMembers.jrag at line 21

	
	public void moveTo(TypeDecl target) {
		if(hasInit()) {
			getInit().lockAllNames();
			getInit().lockDataFlow();
		}
		programRoot().lockNames(name());
		hostType().removeBodyDecl(this);
		target.insertField(this);
	}

    // Declared in RemoveField.jrag at line 2

	public void removeUnused() {
		if(!isUsed() && (!hasInit() || getInit().isPure()))
			getParent().removeChild(getChildIndex());
	}

    // Declared in PushDownField.jrag at line 16
	
	/**
	 * The Push Down Field Refactoring.
	 * 
	 * Preconditions:
	 *  - if field to be pushed has initialiser, that initialiser has to be constant
	 *  - host type cannot have more than one child type
	 *  - that child type cannot already contain a field with the same name
	 *  
	 * The preconditions ensure that it is enough to adjust all accesses to
	 * the field being pushed and all accesses within its initialiser.
	 * 
	 * If pushing from an interface to a class, we need to make the pushed
	 * field public and static.
	 */
	public void pushDown() {
		if(hasInit() && !getInit().isConstant())
			throw new RefactoringException("cannot push fields with non-constant initialiser");
		Collection<TypeDecl> children = hostType().childTypes();
		if(children.size() != 1)
			throw new RefactoringException("can only push down to exactly one subclass");
		TypeDecl child = children.iterator().next();
		if(!child.localFields(name()).isEmpty())
			throw new RefactoringException("field with that name already exists");
		if(hostType().isInterfaceDecl() && !child.isInterfaceDecl())
			getModifiers().addModifiers("public", "static");
		lockAllNames();
		programRoot().lockNames(name());
		hostType().removeBodyDecl(this);
		child.addBodyDecl(this);
		programRoot().flushCaches();
	}

    // Declared in PushDownField.jrag at line 34

	
    public void doPushDown() {
    	pushDown();
    	programRoot().eliminate(LOCKED_NAMES);
    }

    // Declared in RenameVariable.jrag at line 20

	
	public void checkRenamingPreconds(String new_name) {
		if(!hostType().localFields(new_name).isEmpty())
			throw new RefactoringException("field named "+new_name+" exists");
	}

    // Declared in SelfEncapsulateField.jrag at line 2

	public void selfEncapsulate() {
		MethodDecl getter = createGetter();
		MethodDecl setter = null;
		if(!isFinal())
			setter = createSetter();
		
		programRoot().lockNames(name());
		ArrayList<VarAccess> uses = usesOfAllCopies();
		for(int i=0;i<uses.size();++i) {
			VarAccess va = uses.get(i);
			if(va.hostBodyDecl() == getter || va.hostBodyDecl() == setter)
				continue;
			if(va.isDest()) {
				// constructors can write final fields
				if(setter == null)
					continue;
				va.unfoldAbbrevAssign(uses);
				ASTNode p = va;
				if(va.isRightChildOfDot())
					p = va.getParent();
				if(p.getParent() instanceof AssignSimpleExpr && p.getChildIndex() == 0) {
					AssignSimpleExpr assgn = (AssignSimpleExpr)p.getParent();
					Access setter_invocation = setter.createLockedAccess(new List<Expr>().add(assgn.getSource()));
					if(va.isQualified())
						setter_invocation = va.qualifier().qualifiesAccess(setter_invocation);
					assgn.replaceWith(setter_invocation);
				} else {
					throw new RefactoringException("cannot encapsulate this access");
				}
			} else {
				va.replaceWith(getter.createLockedAccess(new List<Expr>()));
			}
		}
		
		getModifiers().setVisibility(VIS_PRIVATE);
		programRoot().flushCaches();
	}

    // Declared in SelfEncapsulateField.jrag at line 40

	
	public void doSelfEncapsulate() {
		selfEncapsulate();
		programRoot().eliminate(LOCKED_NAMES);
	}

    // Declared in Testing.jrag at line 266

	
	public FieldDeclaration findField(String name) {
		if(name().equals(name))
			return this;
		return super.findField(name);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 77

    public FieldDeclaration() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 77
    public FieldDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 19


    // Declared in java.ast line 77
    public FieldDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 26


  protected int numChildren() {
    return 3;
  }

    // Declared in java.ast at line 29

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
    public void setInitOpt(Opt<Expr> opt) {
        setChild(opt, 2);
    }

    // Declared in java.ast at line 6


    public boolean hasInit() {
        return getInitOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getInit() {
        return (Expr)getInitOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setInit(Expr node) {
        getInitOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOpt() {
        return (Opt<Expr>)getChild(2);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOptNoTransform() {
        return (Opt<Expr>)getChildNoTransform(2);
    }

    // Declared in Alias.jrag at line 20

	public boolean isArrayElement() { return false; }

    // Declared in Alias.jrag at line 23

	
	public boolean refined_Alias_Variable_mayAlias(Location l) { return this == l; }

    // Declared in Alias.jrag at line 25

	
	public boolean mustAlias(Location l) { return false; }

    // Declared in AST.jrag at line 56

	
	public boolean isField() {
		return isClassVariable() || isInstanceVariable();
	}

    // Declared in Refresh.jrag at line 2

	public Variable refresh() { return refreshVariable(); }

    // Declared in Uses.jrag at line 3

	// bind all uses of a variable to its declaration
	public Collection<VarAccess> allUses() { return uses(); }

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

    // Declared in Visibility.jrag at line 19

	
	public int getVisibility() {
		if(isPrivate()) return VIS_PRIVATE;
		if(isProtected()) return VIS_PROTECTED;
		if(isPublic()) return VIS_PUBLIC;
		return VIS_PACKAGE;
	}

    protected java.util.Map accessibleFrom_TypeDecl_values;
    // Declared in AccessControl.jrag at line 109
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFrom(TypeDecl type) {
        Object _parameters = type;
if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
        if(accessibleFrom_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
        return accessibleFrom_TypeDecl_value;
    }

    private boolean accessibleFrom_compute(TypeDecl type) {
    if(isPublic())
      return true;
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage()))
        return true;
      if(type.withinBodyThatSubclasses(hostType()) != null)
        return true;
      return false;
    }
    else if(isPrivate())
      return hostType().topLevelType() == type.topLevelType();
    else
      return hostPackage().equals(type.hostPackage());
  }

    protected boolean exceptions_computed = false;
    protected Collection exceptions_value;
    // Declared in AnonymousClasses.jrag at line 166
 @SuppressWarnings({"unchecked", "cast"})     public Collection exceptions() {
        if(exceptions_computed) {
            return exceptions_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        exceptions_value = exceptions_compute();
        if(isFinal && num == state().boundariesCrossed)
            exceptions_computed = true;
        return exceptions_value;
    }

    private Collection exceptions_compute() {
    HashSet set = new HashSet();
    if(isInstanceVariable() && hasInit()) {
      collectExceptions(set, this);
      for(Iterator iter = set.iterator(); iter.hasNext(); ) {
        TypeDecl typeDecl = (TypeDecl)iter.next();
        if(!getInit().reachedException(typeDecl))
          iter.remove();
      }
    }
    return set;
  }

    // Declared in ConstantExpression.jrag at line 479
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        boolean isConstant_value = isConstant_compute();
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return isFinal() && hasInit() && getInit().isConstant() && (type() instanceof PrimitiveType || type().isString());  }

    // Declared in DataStructures.jrag at line 79
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        int size_value = size_compute();
        return size_value;
    }

    private int size_compute() {  return 1;  }

    // Declared in DataStructures.jrag at line 80
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    // Declared in DataStructures.jrag at line 84
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        ASTNode$State state = state();
        boolean contains_Object_value = contains_compute(o);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

    // Declared in DefiniteAssignment.jrag at line 316
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

    private boolean isDAafter_compute(Variable v) {
    if(v == this)
      return hasInit();
    return hasInit() ? getInit().isDAafter(v) : isDAbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 772
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

    private boolean isDUafter_compute(Variable v) {
    if(v == this)
      return !hasInit();
    return hasInit() ? getInit().isDUafter(v) : isDUbefore(v);
  }

    // Declared in Modifiers.jrag at line 214
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        boolean isSynthetic_value = isSynthetic_compute();
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }

    // Declared in Modifiers.jrag at line 237
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        ASTNode$State state = state();
        boolean isPublic_value = isPublic_compute();
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic() || hostType().isInterfaceDecl();  }

    // Declared in Modifiers.jrag at line 238
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        ASTNode$State state = state();
        boolean isPrivate_value = isPrivate_compute();
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }

    // Declared in Modifiers.jrag at line 239
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        ASTNode$State state = state();
        boolean isProtected_value = isProtected_compute();
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return getModifiers().isProtected();  }

    // Declared in Modifiers.jrag at line 240
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        boolean isStatic_value = isStatic_compute();
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return getModifiers().isStatic() || hostType().isInterfaceDecl();  }

    // Declared in Modifiers.jrag at line 242
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        boolean isFinal_value = isFinal_compute();
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal() || hostType().isInterfaceDecl();  }

    // Declared in Modifiers.jrag at line 243
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTransient() {
        ASTNode$State state = state();
        boolean isTransient_value = isTransient_compute();
        return isTransient_value;
    }

    private boolean isTransient_compute() {  return getModifiers().isTransient();  }

    // Declared in Modifiers.jrag at line 244
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVolatile() {
        ASTNode$State state = state();
        boolean isVolatile_value = isVolatile_compute();
        return isVolatile_value;
    }

    private boolean isVolatile_compute() {  return getModifiers().isVolatile();  }

    // Declared in PrettyPrint.jadd at line 810
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    // Declared in TypeAnalysis.jrag at line 251
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        ASTNode$State state = state();
        TypeDecl type_value = type_compute();
        return type_value;
    }

    private TypeDecl type_compute() {  return getTypeAccess().type();  }

    // Declared in TypeAnalysis.jrag at line 273
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        boolean isVoid_value = isVoid_compute();
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return type().isVoid();  }

    // Declared in VariableDeclaration.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassVariable() {
        ASTNode$State state = state();
        boolean isClassVariable_value = isClassVariable_compute();
        return isClassVariable_value;
    }

    private boolean isClassVariable_compute() {  return isStatic() || hostType().isInterfaceDecl();  }

    // Declared in VariableDeclaration.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInstanceVariable() {
        ASTNode$State state = state();
        boolean isInstanceVariable_value = isInstanceVariable_compute();
        return isInstanceVariable_value;
    }

    private boolean isInstanceVariable_compute() {  return (hostType().isClassDecl() || hostType().isAnonymous() )&& !isStatic();  }

    // Declared in VariableDeclaration.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodParameter() {
        ASTNode$State state = state();
        boolean isMethodParameter_value = isMethodParameter_compute();
        return isMethodParameter_value;
    }

    private boolean isMethodParameter_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorParameter() {
        ASTNode$State state = state();
        boolean isConstructorParameter_value = isConstructorParameter_compute();
        return isConstructorParameter_value;
    }

    private boolean isConstructorParameter_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExceptionHandlerParameter() {
        ASTNode$State state = state();
        boolean isExceptionHandlerParameter_value = isExceptionHandlerParameter_compute();
        return isExceptionHandlerParameter_value;
    }

    private boolean isExceptionHandlerParameter_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLocalVariable() {
        ASTNode$State state = state();
        boolean isLocalVariable_value = isLocalVariable_compute();
        return isLocalVariable_value;
    }

    private boolean isLocalVariable_compute() {  return false;  }

    // Declared in VariableDeclaration.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlank() {
        ASTNode$State state = state();
        boolean isBlank_value = isBlank_compute();
        return isBlank_value;
    }

    private boolean isBlank_compute() {  return !hasInit();  }

    // Declared in VariableDeclaration.jrag at line 68
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected boolean constant_computed = false;
    protected Constant constant_value;
    // Declared in VariableDeclaration.jrag at line 69
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        if(constant_computed) {
            return constant_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        constant_value = constant_compute();
        if(isFinal && num == state().boundariesCrossed)
            constant_computed = true;
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(getInit().constant());  }

    // Declared in Annotations.jrag at line 287
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasAnnotationSuppressWarnings(String s) {
        ASTNode$State state = state();
        boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
        return hasAnnotationSuppressWarnings_String_value;
    }

    private boolean hasAnnotationSuppressWarnings_compute(String s) {  return getModifiers().hasAnnotationSuppressWarnings(s);  }

    // Declared in Annotations.jrag at line 325
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDeprecated() {
        ASTNode$State state = state();
        boolean isDeprecated_value = isDeprecated_compute();
        return isDeprecated_value;
    }

    private boolean isDeprecated_compute() {  return getModifiers().hasDeprecatedAnnotation();  }

    protected boolean usesTypeVariable_computed = false;
    protected boolean usesTypeVariable_value;
    // Declared in Generics.jrag at line 910
 @SuppressWarnings({"unchecked", "cast"})     public boolean usesTypeVariable() {
        if(usesTypeVariable_computed) {
            return usesTypeVariable_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        usesTypeVariable_value = usesTypeVariable_compute();
        if(isFinal && num == state().boundariesCrossed)
            usesTypeVariable_computed = true;
        return usesTypeVariable_value;
    }

    private boolean usesTypeVariable_compute() {  return getTypeAccess().usesTypeVariable();  }

    protected boolean sourceVariableDecl_computed = false;
    protected Variable sourceVariableDecl_value;
    // Declared in Generics.jrag at line 1283
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

    // Declared in GenericsParTypeDecl.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public boolean visibleTypeParameters() {
        ASTNode$State state = state();
        boolean visibleTypeParameters_value = visibleTypeParameters_compute();
        return visibleTypeParameters_value;
    }

    private boolean visibleTypeParameters_compute() {  return !isStatic();  }

    // Declared in ReachingDefinitions.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return this == l;  }

    // Declared in ReachingDefinitions.jrag at line 107
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return false;  }

    // Declared in AST.jrag at line 6
 @SuppressWarnings({"unchecked", "cast"})     public String capitalisedName() {
        ASTNode$State state = state();
        String capitalisedName_value = capitalisedName_compute();
        return capitalisedName_value;
    }

    private String capitalisedName_compute() {  return Character.toUpperCase(name().charAt(0))+name().substring(1);  }

    // Declared in Refresh.jrag at line 5
 @SuppressWarnings({"unchecked", "cast"})     public Variable refreshVariable() {
        ASTNode$State state = state();
        Variable refreshVariable_value = refreshVariable_compute();
        return refreshVariable_value;
    }

    private Variable refreshVariable_compute() {
		// the "length" field of array types is synthesised anew for every new array type
		if(name().equals("length") && hostType().isArrayDecl())
			return (FieldDeclaration)hostType().refresh().localFields("length").iterator().next();
		return this;
	}

    // Declared in DemandFinalQualifier.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeFinal() {
        ASTNode$State state = state();
        boolean mayBeFinal_value = mayBeFinal_compute();
        return mayBeFinal_value;
    }

    private boolean mayBeFinal_compute() {  return false;  }

    // Declared in Testing.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public String ppID() {
        ASTNode$State state = state();
        String ppID_value = ppID_compute();
        return ppID_value;
    }

    private String ppID_compute() {  return name();  }

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

    // Declared in ExceptionHandling.jrag at line 34
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        ASTNode$State state = state();
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        return handlesException_TypeDecl_value;
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

    // Declared in DefiniteAssignment.jrag at line 39
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 322
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitOptNoTransform()){
    return isDAbefore(v);
  }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in ExceptionHandling.jrag at line 143
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        if(caller == getInitOptNoTransform()){
    if(hostType().isAnonymous())
      return true;
    if(!exceptionType.isUncheckedException())
      return true;
    for(Iterator iter = hostType().constructors().iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(!decl.throwsException(exceptionType))
        return false;
    }
    return true;
  }
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }

    // Declared in Modifiers.jrag at line 260
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePublic(this, caller);
    }

    // Declared in Modifiers.jrag at line 261
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }

    // Declared in Modifiers.jrag at line 262
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }

    // Declared in Modifiers.jrag at line 263
    public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeStatic(this, caller);
    }

    // Declared in Modifiers.jrag at line 264
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Modifiers.jrag at line 265
    public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeTransient(this, caller);
    }

    // Declared in Modifiers.jrag at line 266
    public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeVolatile(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 78
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 260
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return type();
        }
        return getParent().Define_TypeDecl_declType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 141
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return isStatic() || hostType().isInterfaceDecl();
        }
        return getParent().Define_boolean_inStaticContext(this, caller);
    }

    // Declared in Annotations.jrag at line 80
    public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
        if(caller == getModifiersNoTransform()) {
            return name.equals("FIELD");
        }
        return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
    }

    // Declared in GenericMethodsInference.jrag at line 35
    public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return type();
        }
        return getParent().Define_TypeDecl_assignConvertedType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 119
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == exit_value){
            return getFollowingFieldOrInitializer(isStatic());
        }
        if(caller == getInitOptNoTransform()) {
            return SmallSet.<CFGNode>singleton(exit());
        }
        if(caller == entry_value){
            return SmallSet.<CFGNode>singleton(hasInit() ? getInit().first() : exit());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ModifiersExt.jrag at line 8
    public Variable Define_Variable_getModifiedVariable(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return this;
        }
        return getParent().Define_Variable_getModifiedVariable(this, caller);
    }

    // Declared in Precedence.jrag at line 51
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return Integer.MAX_VALUE;
        }
        if(caller == getTypeAccessNoTransform()) {
            return 0;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected boolean FieldDeclaration_uses_computed = false;
    protected Collection<VarAccess> FieldDeclaration_uses_value;
    // Declared in Uses.jrag at line 7
 @SuppressWarnings({"unchecked", "cast"})     public Collection<VarAccess> uses() {
        if(FieldDeclaration_uses_computed) {
            return FieldDeclaration_uses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        FieldDeclaration_uses_value = uses_compute();
        if(isFinal && num == state().boundariesCrossed)
            FieldDeclaration_uses_computed = true;
        return FieldDeclaration_uses_value;
    }

    java.util.Set FieldDeclaration_uses_contributors;
    public java.util.Set FieldDeclaration_uses_contributors() {
        if(FieldDeclaration_uses_contributors == null) FieldDeclaration_uses_contributors  = new ASTNode$State.IdentityHashSet(4);
        return FieldDeclaration_uses_contributors;
    }
    private Collection<VarAccess> uses_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof Program))
            node = node.getParent();
        Program root = (Program)node;
        root.collect_contributors_FieldDeclaration_uses();
        FieldDeclaration_uses_value = new HashSet<VarAccess>();
        if(FieldDeclaration_uses_contributors != null)
        for(java.util.Iterator iter = FieldDeclaration_uses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_FieldDeclaration_FieldDeclaration_uses(FieldDeclaration_uses_value);
        }
        return FieldDeclaration_uses_value;
    }

    protected boolean FieldDeclaration_staticImports_computed = false;
    protected Collection<StaticImportDecl> FieldDeclaration_staticImports_value;
    // Declared in Uses.jrag at line 69
 @SuppressWarnings({"unchecked", "cast"})     public Collection<StaticImportDecl> staticImports() {
        if(FieldDeclaration_staticImports_computed) {
            return FieldDeclaration_staticImports_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        FieldDeclaration_staticImports_value = staticImports_compute();
        if(isFinal && num == state().boundariesCrossed)
            FieldDeclaration_staticImports_computed = true;
        return FieldDeclaration_staticImports_value;
    }

    java.util.Set FieldDeclaration_staticImports_contributors;
    public java.util.Set FieldDeclaration_staticImports_contributors() {
        if(FieldDeclaration_staticImports_contributors == null) FieldDeclaration_staticImports_contributors  = new ASTNode$State.IdentityHashSet(4);
        return FieldDeclaration_staticImports_contributors;
    }
    private Collection<StaticImportDecl> staticImports_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof Program))
            node = node.getParent();
        Program root = (Program)node;
        root.collect_contributors_FieldDeclaration_staticImports();
        FieldDeclaration_staticImports_value = new HashSet<StaticImportDecl>();
        if(FieldDeclaration_staticImports_contributors != null)
        for(java.util.Iterator iter = FieldDeclaration_staticImports_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_FieldDeclaration_FieldDeclaration_staticImports(FieldDeclaration_staticImports_value);
        }
        return FieldDeclaration_staticImports_value;
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
        // Declared in ControlFlowGraph.jrag at line 40
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
