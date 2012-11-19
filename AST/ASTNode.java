
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

// Generated with JastAdd II (http://jastadd.cs.lth.se) version R20090722

public class ASTNode<T extends ASTNode> extends beaver.Symbol  implements Cloneable, Iterable<T> {
    public void flushCache() {
        uncaughtExceptions_computed = false;
        uncaughtExceptions_value = null;
        fromSource_computed = false;
        usedTypeVars_computed = false;
        usedTypeVars_value = null;
    }
    public void flushCollectionCache() {
    }
     @SuppressWarnings({"unchecked", "cast"})  public ASTNode<T> clone() throws CloneNotSupportedException {
        ASTNode node = (ASTNode)super.clone();
        node.uncaughtExceptions_computed = false;
        node.uncaughtExceptions_value = null;
        node.fromSource_computed = false;
        node.usedTypeVars_computed = false;
        node.usedTypeVars_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ASTNode<T> copy() {
      try {
          ASTNode node = (ASTNode)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ASTNode<T> fullCopy() {
        ASTNode res = (ASTNode)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AccessControl.jrag at line 125
     public MethodDecl findMethodBySig(String sig) {
 		for(int i=0;i<getNumChild();++i) {
 			ASTNode child = getChild(i);
 			if(child != null) {
 				MethodDecl md = child.findMethodBySig(sig);
 				if(md != null) return md;
 			}
 		}
 		return null;		
 	}
    
  public void accessControl() {
  }

    // Declared in AnonymousClasses.jrag at line 190


  protected void collectExceptions(Collection c, ASTNode target) {
    for(int i = 0; i < getNumChild(); i++)
      getChild(i).collectExceptions(c, target);
  }

    // Declared in BranchTarget.jrag at line 45

  
  public void collectBranches(Collection c) {
    for(int i = 0; i < getNumChild(); i++)
      getChild(i).collectBranches(c);
  }

    // Declared in BranchTarget.jrag at line 151

  public Stmt branchTarget(Stmt branchStmt) {
    if(getParent() != null)
      return getParent().branchTarget(branchStmt);
    else
      return null;
  }

    // Declared in BranchTarget.jrag at line 191

  public void collectFinally(Stmt branchStmt, ArrayList list) {
    if(getParent() != null)
      getParent().collectFinally(branchStmt, list);
  }

    // Declared in DeclareBeforeUse.jrag at line 13

  public int varChildIndex(Block b) {
    ASTNode node = this;
    while(node.getParent().getParent() != b) {
      node = node.getParent();
    }
    return b.getStmtListNoTransform().getIndexOfChild(node);
  }

    // Declared in DeclareBeforeUse.jrag at line 31


  public int varChildIndex(TypeDecl t) {
    ASTNode node = this;
    while(node != null && node.getParent() != null && node.getParent().getParent() != t) {
      node = node.getParent();
    }
    if(node == null)
      return -1;
    return t.getBodyDeclListNoTransform().getIndexOfChild(node);
  }

    // Declared in DefiniteAssignment.jrag at line 12


  public void definiteAssignment() {
  }

    // Declared in DefiniteAssignment.jrag at line 451


  // 16.2.2 9th, 10th bullet
  protected boolean checkDUeverywhere(Variable v) {
    for(int i = 0; i < getNumChild(); i++)
      if(!getChild(i).checkDUeverywhere(v))
        return false;
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 561


  protected boolean isDescendantTo(ASTNode node) {
    if(this == node)
      return true;
    if(getParent() == null)
      return false;
    return getParent().isDescendantTo(node);
  }

    // Declared in ErrorCheck.jrag at line 12


  protected String sourceFile() {
    ASTNode node = this;
    while(node != null && !(node instanceof CompilationUnit))
      node = node.getParent();
    if(node == null)
      return "Unknown file";
    CompilationUnit u = (CompilationUnit)node;
    return u.relativeName();
  }

    // Declared in ErrorCheck.jrag at line 34


  // set start and end position to the same as the argument and return self
  public ASTNode setLocation(ASTNode node) {
    setStart(node.getStart());
    setEnd(node.getEnd());
    return this;
  }

    // Declared in ErrorCheck.jrag at line 40


  public ASTNode setStart(int i) {
    start = i;
    return this;
  }

    // Declared in ErrorCheck.jrag at line 44

  public int start() {
    return start;
  }

    // Declared in ErrorCheck.jrag at line 47

  public ASTNode setEnd(int i) {
    end = i;
    return this;
  }

    // Declared in ErrorCheck.jrag at line 51

  public int end() {
    return end;
  }

    // Declared in ErrorCheck.jrag at line 55


  public String location() {
    return "" + lineNumber();
  }

    // Declared in ErrorCheck.jrag at line 58

  public String errorPrefix() {
    return sourceFile() + ":" + location() + ":\n" + "  *** Semantic Error: ";
  }

    // Declared in ErrorCheck.jrag at line 61

  public String warningPrefix() {
    return sourceFile() + ":" + location() + ":\n" + "  *** WARNING: ";
  }

    // Declared in ErrorCheck.jrag at line 171


  public void error(String s) {
    ASTNode node = this;
    while(node != null && !(node instanceof CompilationUnit))
      node = node.getParent();
    CompilationUnit cu = (CompilationUnit)node;
    if(getNumChild() == 0 && getStart() != 0 && getEnd() != 0) {  
      int line = getLine(getStart());
      int column = getColumn(getStart());
      int endLine = getLine(getEnd());
      int endColumn = getColumn(getEnd());
      cu.errors.add(new Problem(sourceFile(), s, line, column, endLine, endColumn, Problem.Severity.ERROR, Problem.Kind.SEMANTIC));
    }
    else
      cu.errors.add(new Problem(sourceFile(), s, lineNumber(), Problem.Severity.ERROR, Problem.Kind.SEMANTIC));
  }

    // Declared in ErrorCheck.jrag at line 187


  public void warning(String s) {
    ASTNode node = this;
    while(node != null && !(node instanceof CompilationUnit))
      node = node.getParent();
    CompilationUnit cu = (CompilationUnit)node;
    cu.warnings.add(new Problem(sourceFile(), "WARNING: " + s, lineNumber(), Problem.Severity.WARNING));
  }

    // Declared in ErrorCheck.jrag at line 195

  
  public void collectErrors() {
    nameCheck();
    typeCheck();
    accessControl();
    exceptionHandling();
    checkUnreachableStmt();
    definiteAssignment();
    checkModifiers();
    for(int i = 0; i < getNumChild(); i++) {
      getChild(i).collectErrors();
    }
  }

    // Declared in ExceptionHandling.jrag at line 40

  
  public void exceptionHandling() {
  }

    // Declared in ExceptionHandling.jrag at line 196


  protected boolean reachedException(TypeDecl type) {
    for(int i = 0; i < getNumChild(); i++)
      if(getChild(i).reachedException(type))
        return true;
    return false;
  }

    // Declared in LookupMethod.jrag at line 54

  public static Collection removeInstanceMethods(Collection c) {
    c = new LinkedList(c);
    for(Iterator iter = c.iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(!m.isStatic())
        iter.remove();
    }
    return c;
  }

    // Declared in LookupMethod.jrag at line 348

  protected static void putSimpleSetElement(HashMap map, Object key, Object value) {
    SimpleSet set = (SimpleSet)map.get(key);
    if(set == null) set = SimpleSet.emptySet;
    map.put(key, set.add(value));
  }

    // Declared in LookupVariable.jrag at line 177


  public SimpleSet removeInstanceVariables(SimpleSet oldSet) {
    SimpleSet newSet = SimpleSet.emptySet;
    for(Iterator iter = oldSet.iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(!v.isInstanceVariable())
        newSet = newSet.add(v);
    }
    return newSet;
  }

    // Declared in Modifiers.jrag at line 11

  void checkModifiers() {
  }

    // Declared in NameCheck.jrag at line 11

  public void nameCheck() {
  }

    // Declared in NameCheck.jrag at line 14


  public TypeDecl extractSingleType(SimpleSet c) {
    if(c.size() != 1)
      return null;
    return (TypeDecl)c.iterator().next();
  }

    // Declared in Options.jadd at line 14

  public Options options() {
    return state().options;
  }

    // Declared in PrettyPrint.jadd at line 13

  // Default output
  
  public String toString() {
    StringBuffer s = new StringBuffer();
    toString(s);
    return s.toString().trim();
  }

    // Declared in PrettyPrint.jadd at line 19

  
  public void toString(StringBuffer s) {
    throw new Error("Operation toString(StringBuffer s) not implemented for " + getClass().getName());
  }

    // Declared in PrettyPrint.jadd at line 769


  // dump the AST to standard output

  public String dumpTree() {
    StringBuffer s = new StringBuffer();
    dumpTree(s, 0);
    return s.toString();
  }

    // Declared in PrettyPrint.jadd at line 775


  public void dumpTree(StringBuffer s, int j) {
    for(int i = 0; i < j; i++) {
      s.append("  ");
    }
    s.append(dumpString() + "\n");
    for(int i = 0; i < getNumChild(); i++)
      getChild(i).dumpTree(s, j + 1);
  }

    // Declared in PrettyPrint.jadd at line 784


  public String dumpTreeNoRewrite() {
    StringBuffer s = new StringBuffer();
    dumpTreeNoRewrite(s, 0);
    return s.toString();
  }

    // Declared in PrettyPrint.jadd at line 789

  protected void dumpTreeNoRewrite(StringBuffer s, int indent) {
    for(int i = 0; i < indent; i++)
      s.append("  ");
    s.append(dumpString());
    s.append("\n");
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      getChildNoTransform(i).dumpTreeNoRewrite(s, indent+1);
    }
  }

    // Declared in PrimitiveTypes.jrag at line 11

  protected static final String PRIMITIVE_PACKAGE_NAME = "@primitive";

    // Declared in TypeCheck.jrag at line 12

  public void typeCheck() {
  }

    // Declared in UnreachableStatements.jrag at line 12


  void checkUnreachableStmt() {
  }

    // Declared in VariableDeclaration.jrag at line 146


  public void clearLocations() {
    setStart(0);
    setEnd(0);
    for(int i = 0; i < getNumChildNoTransform(); i++)
      getChildNoTransform(i).clearLocations();
  }

    // Declared in Enums.jrag at line 128


  protected void transformEnumConstructors() {
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode child = getChildNoTransform(i);
      if(child != null)
        child.transformEnumConstructors();
    }
  }

    // Declared in Enums.jrag at line 411

  
  /*
    14) It is a compile-time error to reference a static field of an enum type that
    is not a compile-time constant (\ufffd15.28) from constructors, instance
    initializer blocks, or instance variable initializer expressions of that
    type.
  */

  protected void checkEnum(EnumDecl enumDecl) {
    for(int i = 0; i < getNumChild(); i++)
      getChild(i).checkEnum(enumDecl);
  }

    // Declared in ControlFlowGraph.jrag at line 212


	// descend through ParExprs and AbstractDots to find a VarAccess
	public VarAccess getDestLocation() {
		return null;
	}

    // Declared in AST.jrag at line 2

	public static String unCapitalise(String s) {
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}

    // Declared in AST.jrag at line 8
 
	
	public static Object epsilon(SimpleSet set, Object alt) {
		if(set.size() == 1)
			return set.iterator().next();
		return alt;
	}

    // Declared in AST.jrag at line 14

	
	public ASTNode getParent(int i) {
		if(i <= 0)
			return this;
		if(getParent() == null)
			return null;
		return getParent().getParent(i-1);
	}

    // Declared in AST.jrag at line 22

	
	public int getChildIndex() {
		return childIndex;
	}

    // Declared in AST.jrag at line 26

	
	public Program programRoot() {
		ASTNode n=this;
		while(n!=null && !(n instanceof Program))
			n=n.getParent();
		return (Program)n;
	}

    // Declared in AST.jrag at line 33

	
	public CompilationUnit compilationUnit() {
		ASTNode n=this;
		while(n!=null && !(n instanceof CompilationUnit))
			n=n.getParent();
		return (CompilationUnit)n;
	}

    // Declared in AST.jrag at line 40

	
	public BodyDecl hostBodyDecl() {
		ASTNode n=this;
		while(n!=null && !(n instanceof BodyDecl))
			n=n.getParent();
		return (BodyDecl)n;
	}

    // Declared in AST.jrag at line 64


	public void flushCaches() {
		this.flushCache();
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.flushCaches();
		}
	}

    // Declared in AST.jrag at line 79

	
	protected void replaceWith(ASTNode newnode) {
		ASTNode parent = getParent();
		parent.setChild(newnode, parent.getIndexOfChild(this));
	}

    // Declared in GenericsExt.jrag at line 6

	
	public ASTNode substituteAll(Parameterization parTypeDecl) {
		ASTNode res = (ASTNode)copy();
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child = child.substituteAll(parTypeDecl);
			res.setChild(child, i);
		}
		return res;
	}

    // Declared in LocalVariable.jrag at line 18

	
	// determine all references to local declarations in a subtree
	public java.util.Set<VarAccess> localVarAccesses() {
		java.util.Set<VarAccess> res = new LinkedHashSet<VarAccess>();
		localVarAccesses(res);
		return res;
	}

    // Declared in LocalVariable.jrag at line 24


	public void localVarAccesses(java.util.Set<VarAccess> res) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.localVarAccesses(res);
		}
	}

    // Declared in MethodExt.jrag at line 258

	
	protected static boolean allAbstract(Collection<MethodDecl> mds) {
		for(MethodDecl md : mds)
			if(!md.isAbstract())
				return false;
		return true;
	}

    // Declared in Nesting.jrag at line 3

	// make sure there are no types below this node whose name is equal to an enclosing type of td
	public void checkEnclosingTypeNames(TypeDecl td) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.checkEnclosingTypeNames(td);
		}
	}

    // Declared in TypeVariableExt.jrag at line 24

	
	// collect all references to type variables in a subtree
	public java.util.Set<TypeAccess> typeVarAccesses() {
		java.util.Set<TypeAccess> res = new LinkedHashSet<TypeAccess>();
		typeVarAccesses(res);
		return res;
	}

    // Declared in TypeVariableExt.jrag at line 30


	public void typeVarAccesses(java.util.Set<TypeAccess> res) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.typeVarAccesses(res);
		}
	}

    // Declared in TypeVariableExt.jrag at line 51

	
	protected void collectUsedTypeVars(Collection<TypeVariable> tvars) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.collectUsedTypeVars(tvars);
		}
	}

    // Declared in TypeVariableExt.jrag at line 66


	// does this subtree use type variables declared outside it?
	public boolean usesForeignTypeVars() {
		Collection<TypeVariable> tvars = usedTypeVars();
		for(TypeVariable tv : tvars)
			if(!tv.isDescendantTo(this))
				return true;
		return false;
	}

    // Declared in TypeVariableExt.jrag at line 75

	
	// translating type variables
	public void translateTypeVars(Map<TypeVariable, TypeVariable> dict) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.translateTypeVars(dict);
		}
	}

    // Declared in Visibility.jrag at line 3

	// unfortunately, JastAdd doesn't support enums very well
	public static final int VIS_PRIVATE = 0;

    // Declared in Visibility.jrag at line 4

	public static final int VIS_PACKAGE = 1;

    // Declared in Visibility.jrag at line 5

	public static final int VIS_PROTECTED = 2;

    // Declared in Visibility.jrag at line 6

	public static final int VIS_PUBLIC = 3;

    // Declared in PermuteParameters.jrag at line 43

	
	// check whether perm is a permutation of the integers {0, ..., max-1}
	public static boolean isPerm(int[] perm, int max) {
		if(perm.length != max)
			return false;
		boolean[] gotit = new boolean[max];
		for(int i=0;i<max;++i) {
			if(perm[i] < 0 || perm[i] >= max)
				return false;
			if(gotit[perm[i]])
				return false;
			gotit[perm[i]] = true;
		}
		return true;
	}

    // Declared in PermuteParameters.jrag at line 57

	
	public static boolean isIdentityPerm(int[] perm) {
		for(int i=0;i<perm.length;++i)
			if(perm[i] != i)
				return false;
		return true;
	}

    // Declared in PermuteParameters.jrag at line 64

	
	public void permute(int[] perm) {
		ASTNode[] children_copy = new ASTNode[children.length];
		System.arraycopy(children, 0, children_copy, 0, children.length);
		for(int i=0;i<perm.length;++i)
			children[perm[i]] = children_copy[i];
	}

    // Declared in SynchronizationDependencies.jrag at line 5

	/* The implementation of this aspect cannot currently be distributed
	 * together with the rest of the refactoring framework due to 
	 * licensing issues. This file only contains stubs. */
	public void lockSyncDependencies() { }

    // Declared in SynchronizationDependencies.jrag at line 6

	public void unlockSyncDependencies() { }

    // Declared in ControlFlow.jrag at line 2

	public void lockControlFlow() {
		for(int i=0;i<getNumChild();++i)
			getChild(i).lockControlFlow();
	}

    // Declared in ControlFlow.jrag at line 7

	
	public void unlockControlFlow() {
		for(int i=0;i<getNumChild();++i)
			getChild(i).unlockControlFlow();
	}

    // Declared in ControlFlow.jrag at line 12

	
	protected Collection<CFGNode> lockedSucc = null;

    // Declared in ControlFlow.jrag at line 13

	protected Collection<CFGNode> lockedPred = null;

    // Declared in DataFlow.jrag at line 2

	public void lockDataFlow() {
		lockDataFlow(0);
	}

    // Declared in DataFlow.jrag at line 6

	
	public void lockDataFlow(int l) {
		for(int i=0;i<getNumChild();++i)
			getChild(i).lockDataFlow(l);
	}

    // Declared in DataFlow.jrag at line 77

	
	public void unlockDataFlow() { }

    // Declared in DataFlow.jrag at line 99

	
	public static LanguageExtension LOCKED_DATAFLOW = new LanguageExtension("locked data flow") {
		public ASTNode eliminateOn(ASTNode n) {
			n.unlockDataFlow();
			return n;
		}
	};

    // Declared in DependencyEdges.jrag at line 85

	
	protected IdentityHashMap<DependencyEdge, SmallSet<CFGNode>> dependencies = null;

    // Declared in DependencyEdges.jrag at line 92

	
	public void lockDependencies(DependencyEdge... edges) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.lockDependencies(edges);
		}
	}

    // Declared in DependencyEdges.jrag at line 107


	public void unlockDependencies(PreservationStrategy r) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.unlockDependencies(r);
		}		
	}

    // Declared in IntroduceOutParameter.jrag at line 22


	public void addAssignToReturns(Variable v) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.addAssignToReturns(v);
		}
	}

    // Declared in CreateStruct.jrag at line 3

	// create a struct, i.e. a class with fields and no methods except a constructor
	public static Pair<ClassDecl, Map<String, FieldDeclaration>> createStruct(String name, Iterable<? extends Variable> vars) {
		return createStruct(name, VIS_PUBLIC, vars);
	}

    // Declared in CreateStruct.jrag at line 7

	
	public static Pair<ClassDecl, Map<String, FieldDeclaration>> createStruct(String name, int vis, Iterable<? extends Variable> vars) {
		Map<String, FieldDeclaration> map = new HashMap<String, FieldDeclaration>();
		List<BodyDecl> bds = new List<BodyDecl>();
		List<ParameterDeclaration> ctor_parms = new List<ParameterDeclaration>();
		Block ctor_body = new Block();
		Modifiers ctor_mods = new Modifiers();
		ctor_mods.setVisibility(vis);
		ConstructorDecl ctor = new ConstructorDecl(ctor_mods, name, ctor_parms, new List<Access>(), new Opt<Stmt>(), ctor_body);
		for(Variable v : vars) {
			if(map.containsKey(v.name()))
				throw new RefactoringException("duplicate field name");
			Modifiers field_mods = v.getModifiers().fullCopy();
			field_mods.setVisibility(v.isField() ? ((FieldDeclaration)v).getVisibility() : VIS_PUBLIC);
			FieldDeclaration fd = new FieldDeclaration(field_mods, v.type().createLockedAccess(), v.name(), new Opt<Expr>());
			bds.add(fd);
			map.put(v.name(), fd);
			if(!v.isField() || v.hasInit()) {
				ParameterDeclaration pd = new ParameterDeclaration(v.type().createLockedAccess(), v.name());
				ctor_parms.add(pd);
				ctor_body.addStmt(AssignExpr.asStmt(fd.createLockedAccess(), pd.createLockedAccess()));
			}
		}
		bds.add(ctor);
		Modifiers class_mods = (Modifiers)ctor_mods.fullCopy();
		class_mods.addModifier("static");
		return new Pair(new ClassDecl(class_mods, name, new Opt<Access>(), new List<Access>(), bds), map);
	}

    // Declared in BareParMethodAccess.jrag at line 4

	
	public static LanguageExtension BARE_PARMETHODACCESS = new LanguageExtension("bare ParMethodAccess") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateBareParMethodAccess();
		}
	};

    // Declared in BareParMethodAccess.jrag at line 10

	
	public ASTNode eliminateBareParMethodAccess() {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child instanceof ParMethodAccess && ((ParMethodAccess)child).isBare()) {
				ParMethodAccess pma = (ParMethodAccess)child;
				MethodDecl md = pma.decl().sourceMethodDecl();
				if(md.isStatic()) {
					setChild((T)md.hostType().createLockedAccess().qualifiesAccess(pma), i);
				} else {
					Access qual = null;
					for(TypeDecl td=pma.enclosingType();td!=null;td=td.enclosingType()) {
						if(td.memberMethod(md) == md) {
							if(td == pma.enclosingType())
								qual = new ThisAccess("this");
							else
								qual = td.createLockedAccess().qualifiesAccess(new ThisAccess("this"));
							break;
						}
					}
					setChild((T)qual.qualifiesAccess(pma), i);
				}
				pma.flushCache();
			}
		}
		return this;
	}

    // Declared in DemandFinalQualifier.jrag at line 43


	public static LanguageExtension DEMAND_FINAL_MODIFIER = new LanguageExtension("demand final modifier") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateDemandFinal();
		}
	};

    // Declared in DemandFinalQualifier.jrag at line 49

	
	public ASTNode eliminateDemandFinal() { return this; }

    // Declared in FreshVariables.jrag at line 29

	
	public static LanguageExtension FRESH_VARIABLES = new LanguageExtension("fresh variables") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateFreshVariables();
		}
	};

    // Declared in FreshVariables.jrag at line 35

	
	public ASTNode eliminateFreshVariables() { return this; }

    // Declared in FreshVariables.jrag at line 115

	
	// collect all declarations made or referenced in a subtree
	public Collection<Declaration> allDecls() {
		HashSet<Declaration> res = new HashSet<Declaration>();
		collectAllDecls(res);
		return res;
	}

    // Declared in FreshVariables.jrag at line 121

	
	public void collectAllDecls(Collection<Declaration> res) {
		for(int i=0;i<getNumChild();++i)
			getChild(i).collectAllDecls(res);
	}

    // Declared in FreshVariables.jrag at line 161


	// replace all declarations in this subtree by their fresh counterparts
	public void freshenAllDeclarations() {
		freshenDeclaration();
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.freshenAllDeclarations();
		}
	}

    // Declared in FreshVariables.jrag at line 171


	public void freshenDeclaration() { }

    // Declared in LanguageExtensions.jrag at line 16


	public void eliminate(LanguageExtension... les) {
		flushCaches();
		for(LanguageExtension le : les)
			eliminate(le);
	}

    // Declared in LanguageExtensions.jrag at line 22

	
	protected void eliminate(LanguageExtension le) {
		flushCache();
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);			
			ASTNode new_child = le.eliminateOn(child);
			if(new_child == null) {
				removeChild(i);
				--i;
			} else {
				setChild((T)new_child, i);
				new_child.eliminate(le);
			}
		}
	}

    // Declared in MakeConversionExplicit.jrag at line 20

	
	public static Expr createConversion(TypeDecl dest, Expr src) {
		// check whether src can be boxe into dest (we assume there is an assignment conversion)
		if(src.type() instanceof PrimitiveType && !dest.unboxed().isUnknown()) {
			if(!src.type().equals(dest.unboxed()))
				src = createConversion(dest.unboxed(), src);
			return new ClassInstanceExpr(dest.createLockedAccess(), new List<Expr>().add(src));
		} else {
			CastExpr ce = new CastExpr(dest.createLockedAccess(), src);
			if(src.precedence() > src.maxPrecedence())
				ce.setExpr(new ParExpr(src));
			return ce;
		}
	}

    // Declared in ReturnVoid.jrag at line 4

	
	public static LanguageExtension RETURN_VOID = new LanguageExtension("return void") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateReturnVoid();
		}
	};

    // Declared in ReturnVoid.jrag at line 10

	
	public ASTNode eliminateReturnVoid() { return this; }

    // Declared in With.jrag at line 36

	
	public SimpleSet removeFields(SimpleSet s) {
		SimpleSet res = SimpleSet.emptySet;
		for(Iterator iter=s.iterator();iter.hasNext();) {
			Variable v = (Variable)iter.next();
			if(!v.isInstanceVariable() && !v.isClassVariable())
				res = res.add(v);
		}
		return res;
	}

    // Declared in With.jrag at line 76

	
	// eliminating with statements
	public static LanguageExtension WITH_STMT = new LanguageExtension("with statement") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateWith();
		}
	};

    // Declared in With.jrag at line 82

	
	public ASTNode eliminateWith() { return this; }

    // Declared in With.jrag at line 102

	
	public void eliminateWith(List<Access> qualifiers, TypeDecl hostType) {
		for(int i=0;i<getNumChild();++i)
			getChild(i).eliminateWith(qualifiers, hostType);
	}

    // Declared in LockedPackageAccess.jrag at line 5

	
	public ASTNode lockAllPackageAccesses() {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.lockAllPackageAccesses();
		}
		return this;
	}

    // Declared in Rename.jrag at line 2

	public static LanguageExtension LOCKED_NAMES = new LanguageExtension("locked names") {
		public ASTNode eliminateOn(ASTNode n) {
			return n.eliminateLockedNames();
		}
	};

    // Declared in Rename.jrag at line 8

	
	public ASTNode eliminateLockedNames() { return this; }

    // Declared in VarNesting.jrag at line 13

	
	// check whether this subtree contains a conflicting local declaration
	public boolean canIntroduceLocal(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null && !child.canIntroduceLocal(name))
				return false;
		}
		return true;
	}

    // Declared in VarNesting.jrag at line 41

	
	// check whether this subtree contains no conflicting labels
	public boolean canIntroduceLabel(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				if(!child.canIntroduceLabel(name))
					return false;
		}
		return true;
	}

    // Declared in AddRequiredMembers.jrag at line 11

	
	public void addRequiredMembers(TypeDecl host, java.util.Set<MethodDecl> meths, java.util.Set<FieldDeclaration> fds, java.util.Set<MemberTypeDecl> mtds) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			child.addRequiredMembers(host, meths, fds, mtds);
		}
	}

    // Declared in Locking.jrag at line 2

	public ASTNode lockNames(String... endangered) {
		return lockNames(Arrays.asList(endangered));
	}

    // Declared in Locking.jrag at line 5

	public ASTNode lockNames(Collection<String> endangered) {
		for(int i=0;i<getNumChild();++i)
			setChild((T)getChild(i).lockNames(endangered), i);
		return this;
	}

    // Declared in Locking.jrag at line 11

	
	public ASTNode lockMethodNames(String... endangered) {
		return lockMethodNames(Arrays.asList(endangered));
	}

    // Declared in Locking.jrag at line 14

	public ASTNode lockMethodNames(Collection<String> endangered) {
		for(int i=0;i<getNumChild();++i)
			setChild((T)getChild(i).lockMethodNames(endangered), i);
		return this;
	}

    // Declared in Locking.jrag at line 20

	
	public ASTNode lockAllNames() {
		for(int i=0;i<getNumChild();++i)
			setChild((T)getChild(i).lockAllNames(), i);
		return this;
	}

    // Declared in Locking.jrag at line 59

	
	// locked copy of a subtree
	public ASTNode lockedCopy() {
		ASTNode res = (ASTNode)copy();
		for(int i=0;i<getNumChildNoTransform();++i) {
			ASTNode child = getChildNoTransform(i);
			if(child != null)
				child = child.lockedCopy();
			res.setChild(child, i);
		}
		return res;
	}

    // Declared in Names.jadd at line 3

	// JLS 3.9
	private static final String[] reservedNames = new String[] {
		"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
		"continue", "degault", "do", "double", "else", "enum", "extends", "final", "finally", "float",
		"for", "if", "goto", "implements", "import", "instanceof", "int", "interface", "long", "native",
		"new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super",
		"switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while",
		"true", "false", "null"
	};

    // Declared in Names.jadd at line 13

	
	// JLS 3.8
	public static boolean isValidName(String name) {
		if(name == null)
			return false;
		for(String res : reservedNames)
			if(res.equals(name))
				return false;
		if(name.length() == 0)
			return false;
		if(!Character.isJavaIdentifierStart(name.charAt(0)))
			return false;
		for(int i=1;i<name.length();++i)
			if(!Character.isJavaIdentifierPart(name.charAt(i)))
				return false;
		return true;
	}

    // Declared in RenameMethod.jrag at line 70

	
	public void lockOverridingDependencies(String... names) {
		lockOverridingDependencies(Arrays.asList(names));
	}

    // Declared in RenameMethod.jrag at line 73

	public void lockOverridingDependencies(Collection<String> names) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null)
				child.lockOverridingDependencies(names);
		}
	}

    // Declared in RenameMethod.jrag at line 90

	
	public static LanguageExtension LOCKED_OVERRIDING = new LanguageExtension("locked overriding") {
		public ASTNode eliminateOn(ASTNode n) {
			n.unlockOverriding();
			return n;
		}
	};

    // Declared in RenameMethod.jrag at line 97

	
	public void unlockOverriding() { }

    // Declared in RenamePackage.jrag at line 29

	
	protected static boolean isValidPackageName(String name) {
		if(name.equals(""))
			return false;
		String[] comp = name.split("\\.");
		for(int i=0;i<comp.length;++i)
			if(!isValidName(comp[i]))
				return false;
		return true;
	}

    // Declared in RenameType.jrag at line 50

	
    public boolean hasNestedType(String name) {
    	for(int i=0;i<getNumChild();++i) {
    		ASTNode child = getChild(i);
    		if(child != null && child.hasNestedType(name))
    			return true;
    	}
    	return false;
    }

    // Declared in RenameType.jrag at line 69

	
    public boolean containsNativeMethod() {
    	for(int i=0;i<getNumChild();++i) {
    		ASTNode child = getChild(i);
    		if(child != null && child.containsNativeMethod())
    			return true;
    	}
    	return false;
    }

    // Declared in Testing.jrag at line 166

	
	public TypeDecl findSimpleType(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				TypeDecl td = child.findSimpleType(name);
				if(td != null) return td;
			}
		}
		return null;
	}

    // Declared in Testing.jrag at line 183

	
	public LocalClassDeclStmt findLocalClass(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				LocalClassDeclStmt lcd = child.findLocalClass(name);
				if(lcd!=null) return lcd;
			}
		}
		return null;
	}

    // Declared in Testing.jrag at line 201

	
	// find a variable given its simple name
	public Variable findVariable(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				Variable v = child.findVariable(name);
				if(v != null) return v;
			}
		}
		return null;		
	}

    // Declared in Testing.jrag at line 219

	
	// find a local variable given its simple name
	public VariableDeclaration findLocalVariable(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				VariableDeclaration v = child.findLocalVariable(name);
				if(v != null) return v;
			}
		}
		return null;		
	}

    // Declared in Testing.jrag at line 237

	
	// find a method given its name
	public MethodDecl findMethod(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				MethodDecl md = child.findMethod(name);
				if(md != null) return md;
			}
		}
		return null;		
	}

    // Declared in Testing.jrag at line 255

	
	// find a field given its name
	public FieldDeclaration findField(String name) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				FieldDeclaration md = child.findField(name);
				if(md != null) return md;
			}
		}
		return null;		
	}

    // Declared in Testing.jrag at line 273

	
	// find a doubly-parenthesised expression
	public Expr findDoublyParenthesised() {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				Expr e = child.findDoublyParenthesised();
				if(e != null) return e;
			}
		}
		return null;
	}

    // Declared in Testing.jrag at line 291

	
	// find a statement with a given label
	public LabeledStmt findStmtWithLabel(String l) {
		for(int i=0;i<getNumChild();++i) {
			ASTNode child = getChild(i);
			if(child != null) {
				LabeledStmt s = child.findStmtWithLabel(l);
				if(s != null) return s;
			}
		}
		return null;
	}

    // Declared in ASTNode.ast at line 3
    // Declared in ASTNode.ast line 0

    public ASTNode() {
        super();


    }

    // Declared in ASTNode.ast at line 9


   public static final boolean generatedWithCircularEnabled = true;

    // Declared in ASTNode.ast at line 10

   public static final boolean generatedWithCacheCycle = false;

    // Declared in ASTNode.ast at line 11

   public static final boolean generatedWithComponentCheck = false;

    // Declared in ASTNode.ast at line 12

   protected static ASTNode$State state = new ASTNode$State();

    // Declared in ASTNode.ast at line 13

   public final ASTNode$State state() { return state; }

    // Declared in ASTNode.ast at line 14

  public boolean in$Circle = false;

    // Declared in ASTNode.ast at line 15

  public boolean in$Circle() { return in$Circle; }

    // Declared in ASTNode.ast at line 16

  public void in$Circle(boolean b) { in$Circle = b; }

    // Declared in ASTNode.ast at line 17

  public boolean is$Final = false;

    // Declared in ASTNode.ast at line 18

  public boolean is$Final() { return is$Final; }

    // Declared in ASTNode.ast at line 19

  public void is$Final(boolean b) { is$Final = b; }

    // Declared in ASTNode.ast at line 20

  @SuppressWarnings("cast") public T getChild(int i) {
    return (T)ASTNode.getChild(this, i);
  }

    // Declared in ASTNode.ast at line 23

  public static ASTNode getChild(ASTNode that, int i) {
    ASTNode node = that.getChildNoTransform(i);
    if(node.is$Final()) return node;
    if(!node.mayHaveRewrite()) {
      node.is$Final(that.is$Final());
      return node;
    }
    if(!node.in$Circle()) {
      int rewriteState;
      int num = that.state().boundariesCrossed;
      do {
        that.state().push(ASTNode$State.REWRITE_CHANGE);
        ASTNode oldNode = node;
        oldNode.in$Circle(true);
        node = node.rewriteTo();
        if(node != oldNode)
          that.setChild(node, i);
        oldNode.in$Circle(false);
        rewriteState = that.state().pop();
      } while(rewriteState == ASTNode$State.REWRITE_CHANGE);
      if(rewriteState == ASTNode$State.REWRITE_NOCHANGE && that.is$Final()) {
        node.is$Final(true);
        that.state().boundariesCrossed = num;
      }
    }
    else if(that.is$Final() != node.is$Final()) that.state().boundariesCrossed++;
    return node;
  }

    // Declared in ASTNode.ast at line 51

  private int childIndex;

    // Declared in ASTNode.ast at line 52

  public int getIndexOfChild(ASTNode node) {
    if(node != null && node.childIndex < getNumChildNoTransform() && node == getChildNoTransform(node.childIndex))
      return node.childIndex;
    for(int i = 0; i < getNumChildNoTransform(); i++)
      if(getChildNoTransform(i) == node) {
        node.childIndex = i;
        return i;
      }
    return -1;
  }

    // Declared in ASTNode.ast at line 63


  public void addChild(T node) {
    setChild(node, getNumChildNoTransform());
  }

    // Declared in ASTNode.ast at line 66

  @SuppressWarnings("cast") public final T getChildNoTransform(int i) {
    return (T)children[i];
  }

    // Declared in ASTNode.ast at line 69

  protected int numChildren;

    // Declared in ASTNode.ast at line 70

  protected int numChildren() {
    return numChildren;
  }

    // Declared in ASTNode.ast at line 73

  public int getNumChild() {
    return numChildren();
  }

    // Declared in ASTNode.ast at line 76

  public final int getNumChildNoTransform() {
    return numChildren();
  }

    // Declared in ASTNode.ast at line 79

  public void setChild(T node, int i) {
    if(children == null) {
      children = new ASTNode[i + 1];
    } else if (i >= children.length) {
      ASTNode c[] = new ASTNode[i << 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = node;
    if(i >= numChildren) numChildren = i+1;
    if(node != null) { node.setParent(this); node.childIndex = i; }
  }

    // Declared in ASTNode.ast at line 91

  public void insertChild(T node, int i) {
    if(children == null) {
      children = new ASTNode[i + 1];
      children[i] = node;
    } else {
      ASTNode c[] = new ASTNode[children.length + 1];
      System.arraycopy(children, 0, c, 0, i);
      c[i] = node;
      if(i < children.length)
        System.arraycopy(children, i, c, i+1, children.length-i);
      children = c;
    }
    numChildren++;
    if(node != null) { node.setParent(this); node.childIndex = i; }
  }

    // Declared in ASTNode.ast at line 106

  public void removeChild(int i) {
    if(children != null) {
      ASTNode child = (ASTNode)children[i];
      if(child != null) {
        child.setParent(null);
        child.childIndex = -1;
      }
      System.arraycopy(children, i+1, children, i, children.length-i-1);
      numChildren--;
    }
  }

    // Declared in ASTNode.ast at line 117

  public ASTNode getParent() {
    if(parent != null && ((ASTNode)parent).is$Final() != is$Final()) {
      state().boundariesCrossed++;
    }
    return (ASTNode)parent;
  }

    // Declared in ASTNode.ast at line 123

  public void setParent(ASTNode node) {
    parent = node;
  }

    // Declared in ASTNode.ast at line 126

  protected ASTNode parent;

    // Declared in ASTNode.ast at line 127

  protected ASTNode[] children;

    // Declared in ASTNode.ast at line 129

    protected boolean duringLookupConstructor() {
        if(state().duringLookupConstructor == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 140

    protected boolean duringBoundNames() {
        if(state().duringBoundNames == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 151

    protected boolean duringResolveAmbiguousNames() {
        if(state().duringResolveAmbiguousNames == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 162

    protected boolean duringSyntacticClassification() {
        if(state().duringSyntacticClassification == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 173

    protected boolean duringAnonymousClasses() {
        if(state().duringAnonymousClasses == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 184

    protected boolean duringVariableDeclaration() {
        if(state().duringVariableDeclaration == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 195

    protected boolean duringConstantExpression() {
        if(state().duringConstantExpression == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 206

    protected boolean duringDefiniteAssignment() {
        if(state().duringDefiniteAssignment == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 217

    protected boolean duringAnnotations() {
        if(state().duringAnnotations == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 228

    protected boolean duringEnums() {
        if(state().duringEnums == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 239

    protected boolean duringGenericTypeVariables() {
        if(state().duringGenericTypeVariables == 0) {
            return false;
        }
        else {
            state().pop();
            state().push(ASTNode$State.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 299

    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int counter = 0;
            public boolean hasNext() {
                return counter < getNumChild();
            }
            @SuppressWarnings("unchecked") public T next() {
                if(hasNext())
                    return (T)getChild(counter++);
                else
                    return null;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Declared in ASTNode.ast at line 316

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in RenamePackage.jrag at line 39
    protected void collect_contributors_Program_typeWithSameNameAsPackage() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_Program_typeWithSameNameAsPackage();
    }

    protected void contributeTo_Program_Program_typeWithSameNameAsPackage(Collection<TypeDecl> collection) {
    }


    // Declared in ReachingDefinitions.jrag at line 30
    protected void collect_contributors_Definition_reachedUses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_Definition_reachedUses();
    }

    protected void contributeTo_Definition_Definition_reachedUses(SmallSet<Access> collection) {
    }


    // Declared in Inheritance.jrag at line 3
    protected void collect_contributors_TypeDecl_childTypes() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_TypeDecl_childTypes();
    }

    protected void contributeTo_TypeDecl_TypeDecl_childTypes(Collection<TypeDecl> collection) {
    }


    // Declared in Uses.jrag at line 26
    protected void collect_contributors_TypeDecl_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_TypeDecl_uses();
    }

    protected void contributeTo_TypeDecl_TypeDecl_uses(Collection<Access> collection) {
    }


    // Declared in Uses.jrag at line 48
    protected void collect_contributors_TypeDecl_instantiations() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_TypeDecl_instantiations();
    }

    protected void contributeTo_TypeDecl_TypeDecl_instantiations(Collection<Access> collection) {
    }


    // Declared in Uses.jrag at line 42
    protected void collect_contributors_ConstructorDecl_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_ConstructorDecl_uses();
    }

    protected void contributeTo_ConstructorDecl_ConstructorDecl_uses(Collection<Access> collection) {
    }


    // Declared in Uses.jrag at line 7
    protected void collect_contributors_FieldDeclaration_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_FieldDeclaration_uses();
    }

    protected void contributeTo_FieldDeclaration_FieldDeclaration_uses(Collection<VarAccess> collection) {
    }


    // Declared in Uses.jrag at line 69
    protected void collect_contributors_FieldDeclaration_staticImports() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_FieldDeclaration_staticImports();
    }

    protected void contributeTo_FieldDeclaration_FieldDeclaration_staticImports(Collection<StaticImportDecl> collection) {
    }


    // Declared in ControlFlowGraph.jrag at line 36
    protected void collect_contributors_CFGNode_collPred() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_CFGNode_collPred();
    }

    protected void contributeTo_CFGNode_CFGNode_collPred(SmallSet<CFGNode> collection) {
    }


    // Declared in Uses.jrag at line 13
    protected void collect_contributors_VariableDeclaration_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_VariableDeclaration_uses();
    }

    protected void contributeTo_VariableDeclaration_VariableDeclaration_uses(Collection<VarAccess> collection) {
    }


    // Declared in Uses.jrag at line 19
    protected void collect_contributors_ParameterDeclaration_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_ParameterDeclaration_uses();
    }

    protected void contributeTo_ParameterDeclaration_ParameterDeclaration_uses(Collection<VarAccess> collection) {
    }


    // Declared in Overriding.jrag at line 53
    protected void collect_contributors_MethodDecl_coll_overriders() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_MethodDecl_coll_overriders();
    }

    protected void contributeTo_MethodDecl_MethodDecl_coll_overriders(HashSet<MethodDecl> collection) {
    }


    // Declared in Uses.jrag at line 32
    protected void collect_contributors_MethodDecl_polyUses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_MethodDecl_polyUses();
    }

    protected void contributeTo_MethodDecl_MethodDecl_polyUses(Collection<MethodAccess> collection) {
    }


    // Declared in Uses.jrag at line 37
    protected void collect_contributors_MethodDecl_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_MethodDecl_uses();
    }

    protected void contributeTo_MethodDecl_MethodDecl_uses(Collection<MethodAccess> collection) {
    }


    // Declared in Uses.jrag at line 54
    protected void collect_contributors_LabeledStmt_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_LabeledStmt_uses();
    }

    protected void contributeTo_LabeledStmt_LabeledStmt_uses(Collection<Stmt> collection) {
    }


    // Declared in MethodExt.jrag at line 92
    protected void collect_contributors_GenericMethodDecl_parUses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_GenericMethodDecl_parUses();
    }

    protected void contributeTo_GenericMethodDecl_GenericMethodDecl_parUses(Collection<MethodAccess> collection) {
    }


    // Declared in ConstructorExt.jrag at line 43
    protected void collect_contributors_GenericConstructorDecl_parUses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_GenericConstructorDecl_parUses();
    }

    protected void contributeTo_GenericConstructorDecl_GenericConstructorDecl_parUses(Collection<Access> collection) {
    }


    // Declared in DefiniteAssignment.jrag at line 1200
 @SuppressWarnings({"unchecked", "cast"})     public boolean unassignedEverywhere(Variable v, TryStmt stmt) {
        ASTNode$State state = state();
        boolean unassignedEverywhere_Variable_TryStmt_value = unassignedEverywhere_compute(v, stmt);
        return unassignedEverywhere_Variable_TryStmt_value;
    }

    private boolean unassignedEverywhere_compute(Variable v, TryStmt stmt) {
    for(int i = 0; i < getNumChild(); i++) {
      if(!getChild(i).unassignedEverywhere(v, stmt))
        return false;
    }
    return true;
  }

    // Declared in ErrorCheck.jrag at line 22
 @SuppressWarnings({"unchecked", "cast"})     public int lineNumber() {
        ASTNode$State state = state();
        int lineNumber_value = lineNumber_compute();
        return lineNumber_value;
    }

    private int lineNumber_compute() {
    ASTNode n = this;
    while(n.getParent() != null && n.getStart() == 0) {
      n = n.getParent();
    }
    return getLine(n.getStart());
  }

    // Declared in PrettyPrint.jadd at line 743
 @SuppressWarnings({"unchecked", "cast"})     public String indent() {
        ASTNode$State state = state();
        String indent_value = indent_compute();
        return indent_value;
    }

    private String indent_compute() {
    String indent = extractIndent();
    return indent.startsWith("\n") ? indent : ("\n" + indent);
  }

    // Declared in PrettyPrint.jadd at line 748
 @SuppressWarnings({"unchecked", "cast"})     public String extractIndent() {
        ASTNode$State state = state();
        String extractIndent_value = extractIndent_compute();
        return extractIndent_value;
    }

    private String extractIndent_compute() {
    if(getParent() == null)
      return "";
    String indent = getParent().extractIndent();
    if(getParent().addsIndentationLevel())
      indent += "  ";
    return indent;
  }

    // Declared in PrettyPrint.jadd at line 757
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return false;  }

    // Declared in PrettyPrint.jadd at line 799
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName();  }

    // Declared in Generics.jrag at line 901
 @SuppressWarnings({"unchecked", "cast"})     public boolean usesTypeVariable() {
        ASTNode$State state = state();
        boolean usesTypeVariable_value = usesTypeVariable_compute();
        return usesTypeVariable_value;
    }

    private boolean usesTypeVariable_compute() {
    for(int i = 0; i < getNumChild(); i++)
      if(getChild(i).usesTypeVariable())
        return true;
    return false;
  }

    // Declared in ControlFlowGraph.jrag at line 566
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> collectBranches() {
        ASTNode$State state = state();
        SmallSet<CFGNode> collectBranches_value = collectBranches_compute();
        return collectBranches_value;
    }

    private SmallSet<CFGNode> collectBranches_compute() {
		SmallSet<CFGNode> collectBranches = SmallSet.empty();
		for(int i = 0; i < getNumChild(); i++)
			collectBranches = collectBranches.union(getChild(i).collectBranches());
		return collectBranches;
	}

    protected boolean uncaughtExceptions_computed = false;
    protected SmallSet<TypeDecl> uncaughtExceptions_value;
    // Declared in Exceptions.jrag at line 4
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
    SmallSet<TypeDecl> uncaughtExns = SmallSet.empty();
    for(int i = 0; i < getNumChild(); i++)
      uncaughtExns = uncaughtExns.union(getChild(i).uncaughtExceptions());
    return uncaughtExns;
  }

    protected boolean fromSource_computed = false;
    protected boolean fromSource_value;
    // Declared in AST.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public boolean fromSource() {
        if(fromSource_computed) {
            return fromSource_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        fromSource_value = fromSource_compute();
        if(isFinal && num == state().boundariesCrossed)
            fromSource_computed = true;
        return fromSource_value;
    }

    private boolean fromSource_compute() {
		CompilationUnit cu = compilationUnit();
		return cu == null ? false : cu.fromSource(); 
	}

    protected boolean usedTypeVars_computed = false;
    protected Collection<TypeVariable> usedTypeVars_value;
    // Declared in TypeVariableExt.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public Collection<TypeVariable> usedTypeVars() {
        if(usedTypeVars_computed) {
            return usedTypeVars_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        usedTypeVars_value = usedTypeVars_compute();
        if(isFinal && num == state().boundariesCrossed)
            usedTypeVars_computed = true;
        return usedTypeVars_value;
    }

    private Collection<TypeVariable> usedTypeVars_compute() {
		Collection<TypeVariable> res = new HashSet<TypeVariable>();
		collectUsedTypeVars(res);
		return res;
	}

public ASTNode rewriteTo() {
    if(state().peek() == ASTNode$State.REWRITE_CHANGE) {
        state().pop();
        state().push(ASTNode$State.REWRITE_NOCHANGE);
    }
    return this;
}

    public TypeDecl Define_TypeDecl_superType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_superType(this, caller);
    }
    public ConstructorDecl Define_ConstructorDecl_constructorDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_ConstructorDecl_constructorDecl(this, caller);
    }
    public TypeDecl Define_TypeDecl_componentType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_componentType(this, caller);
    }
    public LabeledStmt Define_LabeledStmt_lookupLabel(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_LabeledStmt_lookupLabel(this, caller, name);
    }
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isDest(this, caller);
    }
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isSource(this, caller);
    }
    public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isIncOrDec(this, caller);
    }
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }
    public TypeDecl Define_TypeDecl_typeException(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeException(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeRuntimeException(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeRuntimeException(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeError(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeError(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeNullPointerException(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeNullPointerException(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeThrowable(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeThrowable(this, caller);
    }
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }
    public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
        return getParent().Define_Collection_lookupConstructor(this, caller);
    }
    public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
        return getParent().Define_Collection_lookupSuperConstructor(this, caller);
    }
    public Expr Define_Expr_nestedScope(ASTNode caller, ASTNode child) {
        return getParent().Define_Expr_nestedScope(this, caller);
    }
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }
    public TypeDecl Define_TypeDecl_typeObject(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeObject(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeCloneable(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeCloneable(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeSerializable(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeSerializable(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeBoolean(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeBoolean(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeByte(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeByte(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeShort(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeShort(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeChar(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeChar(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeInt(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeInt(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeLong(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeLong(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeFloat(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeFloat(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeDouble(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeDouble(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeString(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeString(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeVoid(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeVoid(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeNull(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeNull(this, caller);
    }
    public TypeDecl Define_TypeDecl_unknownType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_unknownType(this, caller);
    }
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }
    public TypeDecl Define_TypeDecl_lookupType(ASTNode caller, ASTNode child, String packageName, String typeName) {
        return getParent().Define_TypeDecl_lookupType(this, caller, packageName, typeName);
    }
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBePublic(this, caller);
    }
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }
    public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeStatic(this, caller);
    }
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }
    public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeAbstract(this, caller);
    }
    public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeVolatile(this, caller);
    }
    public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeTransient(this, caller);
    }
    public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeStrictfp(this, caller);
    }
    public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeSynchronized(this, caller);
    }
    public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_mayBeNative(this, caller);
    }
    public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
        return getParent().Define_ASTNode_enclosingBlock(this, caller);
    }
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        return getParent().Define_VariableScope_outerScope(this, caller);
    }
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_insideLoop(this, caller);
    }
    public boolean Define_boolean_insideSwitch(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_insideSwitch(this, caller);
    }
    public Case Define_Case_bind(ASTNode caller, ASTNode child, Case c) {
        return getParent().Define_Case_bind(this, caller, c);
    }
    public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
        return getParent().Define_String_typeDeclIndent(this, caller);
    }
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        return getParent().Define_NameType_nameType(this, caller);
    }
    public boolean Define_boolean_isAnonymous(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isAnonymous(this, caller);
    }
    public Variable Define_Variable_unknownField(ASTNode caller, ASTNode child) {
        return getParent().Define_Variable_unknownField(this, caller);
    }
    public MethodDecl Define_MethodDecl_unknownMethod(ASTNode caller, ASTNode child) {
        return getParent().Define_MethodDecl_unknownMethod(this, caller);
    }
    public ConstructorDecl Define_ConstructorDecl_unknownConstructor(ASTNode caller, ASTNode child) {
        return getParent().Define_ConstructorDecl_unknownConstructor(this, caller);
    }
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_declType(this, caller);
    }
    public BodyDecl Define_BodyDecl_enclosingBodyDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_BodyDecl_enclosingBodyDecl(this, caller);
    }
    public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isMemberType(this, caller);
    }
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_hostType(this, caller);
    }
    public TypeDecl Define_TypeDecl_switchType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_switchType(this, caller);
    }
    public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_returnType(this, caller);
    }
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        return getParent().Define_String_methodHost(this, caller);
    }
    public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_inExplicitConstructorInvocation(this, caller);
    }
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_inStaticContext(this, caller);
    }
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }
    public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
    }
    public ElementValue Define_ElementValue_lookupElementTypeValue(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_ElementValue_lookupElementTypeValue(this, caller, name);
    }
    public boolean Define_boolean_withinSuppressWarnings(ASTNode caller, ASTNode child, String s) {
        return getParent().Define_boolean_withinSuppressWarnings(this, caller, s);
    }
    public boolean Define_boolean_withinDeprecatedAnnotation(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_withinDeprecatedAnnotation(this, caller);
    }
    public Annotation Define_Annotation_lookupAnnotation(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
        return getParent().Define_Annotation_lookupAnnotation(this, caller, typeDecl);
    }
    public TypeDecl Define_TypeDecl_enclosingAnnotationDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_enclosingAnnotationDecl(this, caller);
    }
    public GenericMethodDecl Define_GenericMethodDecl_genericMethodDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_GenericMethodDecl_genericMethodDecl(this, caller);
    }
    public GenericConstructorDecl Define_GenericConstructorDecl_genericConstructorDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_GenericConstructorDecl_genericConstructorDecl(this, caller);
    }
    public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_assignConvertedType(this, caller);
    }
    public TypeDecl Define_TypeDecl_typeWildcard(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_typeWildcard(this, caller);
    }
    public TypeDecl Define_TypeDecl_lookupWildcardExtends(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
        return getParent().Define_TypeDecl_lookupWildcardExtends(this, caller, typeDecl);
    }
    public TypeDecl Define_TypeDecl_lookupWildcardSuper(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
        return getParent().Define_TypeDecl_lookupWildcardSuper(this, caller, typeDecl);
    }
    public LUBType Define_LUBType_lookupLUBType(ASTNode caller, ASTNode child, Collection bounds) {
        return getParent().Define_LUBType_lookupLUBType(this, caller, bounds);
    }
    public GLBType Define_GLBType_lookupGLBType(ASTNode caller, ASTNode child, ArrayList bounds) {
        return getParent().Define_GLBType_lookupGLBType(this, caller, bounds);
    }
    public TypeDecl Define_TypeDecl_genericDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_genericDecl(this, caller);
    }
    public boolean Define_boolean_variableArityValid(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_variableArityValid(this, caller);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }
    public AssignExpr Define_AssignExpr_modifyingAssignExpr(ASTNode caller, ASTNode child) {
        return getParent().Define_AssignExpr_modifyingAssignExpr(this, caller);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__breakTarget(ASTNode caller, ASTNode child, BreakStmt stmt) {
        return getParent().Define_SmallSet_CFGNode__breakTarget(this, caller, stmt);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__continueTarget(ASTNode caller, ASTNode child, ContinueStmt stmt) {
        return getParent().Define_SmallSet_CFGNode__continueTarget(this, caller, stmt);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__returnTarget(ASTNode caller, ASTNode child) {
        return getParent().Define_SmallSet_CFGNode__returnTarget(this, caller);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__throwTarget(ASTNode caller, ASTNode child, TypeDecl exn) {
        return getParent().Define_SmallSet_CFGNode__throwTarget(this, caller, exn);
    }
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__uncheckedExnTarget(ASTNode caller, ASTNode child) {
        return getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, caller);
    }
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }
    public BodyDecl Define_BodyDecl_hostBodyDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_BodyDecl_hostBodyDecl(this, caller);
    }
    public Variable Define_Variable_getModifiedVariable(ASTNode caller, ASTNode child) {
        return getParent().Define_Variable_getModifiedVariable(this, caller);
    }
    public PackageDecl Define_PackageDecl_lookupPackage(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_PackageDecl_lookupPackage(this, caller, name);
    }
    public Parameterisable Define_Parameterisable_parameterOwner(ASTNode caller, ASTNode child) {
        return getParent().Define_Parameterisable_parameterOwner(this, caller);
    }
    public boolean Define_boolean_inImportDecl(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_inImportDecl(this, caller);
    }
    public GenericElement Define_GenericElement_owner(ASTNode caller, ASTNode child) {
        return getParent().Define_GenericElement_owner(this, caller);
    }
    public MethodAccessInfo Define_MethodAccessInfo_accessMethod(ASTNode caller, ASTNode child, MethodDecl md) {
        return getParent().Define_MethodAccessInfo_accessMethod(this, caller, md);
    }
    public UnknownVarAccess Define_UnknownVarAccess_unknownVarAccess(ASTNode caller, ASTNode child) {
        return getParent().Define_UnknownVarAccess_unknownVarAccess(this, caller);
    }
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }
    public Stmt Define_Stmt_getArea(ASTNode caller, ASTNode child) {
        return getParent().Define_Stmt_getArea(this, caller);
    }
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }
    public CompilationUnit Define_CompilationUnit_compilationUnit(ASTNode caller, ASTNode child) {
        return getParent().Define_CompilationUnit_compilationUnit(this, caller);
    }
    public SimpleSet Define_SimpleSet_allImportedTypes(ASTNode caller, ASTNode child, String name) {
        return getParent().Define_SimpleSet_allImportedTypes(this, caller, name);
    }
    public String Define_String_packageName(ASTNode caller, ASTNode child) {
        return getParent().Define_String_packageName(this, caller);
    }
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }
    public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isNestedType(this, caller);
    }
    public boolean Define_boolean_isLocalClass(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_isLocalClass(this, caller);
    }
    public String Define_String_hostPackage(ASTNode caller, ASTNode child) {
        return getParent().Define_String_hostPackage(this, caller);
    }
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        return getParent().Define_int_maxPrecedence(this, caller);
    }
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_reachable(this, caller);
    }
    public boolean Define_boolean_delete(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_delete(this, caller);
    }
    public boolean Define_boolean_reachableCatchClause(ASTNode caller, ASTNode child) {
        return getParent().Define_boolean_reachableCatchClause(this, caller);
    }
}
