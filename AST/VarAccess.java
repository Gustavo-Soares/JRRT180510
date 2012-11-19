
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class VarAccess extends Access implements Cloneable, LockableName {
    public void flushCache() {
        super.flushCache();
        isConstant_visited = -1;
        isConstant_computed = false;
        isConstant_initialized = false;
        isDAafter_Variable_values = null;
        decls_computed = false;
        decls_value = null;
        decl_computed = false;
        decl_value = null;
        isFieldAccess_computed = false;
        type_computed = false;
        type_value = null;
        getLocation_computed = false;
        getLocation_value = null;
        succ_computed = false;
        succ_value = null;
        modifyingAssignExpr_computed = false;
        modifyingAssignExpr_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess clone() throws CloneNotSupportedException {
        VarAccess node = (VarAccess)super.clone();
        node.isConstant_visited = -1;
        node.isConstant_computed = false;
        node.isConstant_initialized = false;
        node.isDAafter_Variable_values = null;
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_computed = false;
        node.decl_value = null;
        node.isFieldAccess_computed = false;
        node.type_computed = false;
        node.type_value = null;
        node.getLocation_computed = false;
        node.getLocation_value = null;
        node.succ_computed = false;
        node.succ_value = null;
        node.modifyingAssignExpr_computed = false;
        node.modifyingAssignExpr_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess copy() {
      try {
          VarAccess node = (VarAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess fullCopy() {
        VarAccess res = (VarAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DefiniteAssignment.jrag at line 94

  
  public void definiteAssignment() {
    if(isSource()) {
      if(decl() instanceof VariableDeclaration) {
        VariableDeclaration v = (VariableDeclaration)decl();
        //System.err.println("Is " + v + " final? " + v.isFinal() + ", DAbefore: " + isDAbefore(v));
        if(v.isValue()) {
        }
        else if(v.isBlankFinal()) {
          //if(!isDAbefore(v) && !v.hasInit() && !v.getInit().isConstant())
          if(!isDAbefore(v))
            error("Final variable " + v.name() + " is not assigned before used");
        }
        else {
          //if(!v.hasInit() && !isDAbefore(v)) {
          if(!isDAbefore(v))
          error("Local variable " + v.name() + " in not assigned before used");
        }
      }
      
      else if(decl() instanceof FieldDeclaration && !isQualified()) {
        FieldDeclaration f = (FieldDeclaration)decl();
        //if(f.isFinal() && f.isInstanceVariable() && !isDAbefore(f)) {
        //if(f.isFinal() && !isDAbefore(f) && (!f.hasInit() || !f.getInit().isConstant())) {
        //if(f.isFinal() && (!f.hasInit() || !f.getInit().isConstant()) && !isDAbefore(f)) {
        if(f.isFinal() && !f.hasInit() && !isDAbefore(f)) {
          error("Final field " + f + " is not assigned before used");
        }
      }
      
    }
    if(isDest()) {
      Variable v = decl();
      // Blank final field
      if(v.isFinal() && v.isBlank() && !hostType().instanceOf(v.hostType()))
        error("The final variable is not a blank final in this context, so it may not be assigned.");
      else if(v.isFinal() && isQualified() && (!qualifier().isThisAccess() || ((Access)qualifier()).isQualified()))
        error("the blank final field " + v.name() + " may only be assigned by simple name");
      
      // local variable or parameter
      else if(v instanceof VariableDeclaration) {
        VariableDeclaration var = (VariableDeclaration)v;
        //System.out.println("### is variable");
        if(!var.isValue() && var.getParent().getParent().getParent() instanceof SwitchStmt && var.isFinal()) {
          if(!isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isValue()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isBlankFinal()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        if(var.isFinal() && (var.hasInit() || !isDUbefore(var))) {
        //if(var.isFinal() && ((var.hasInit() && var.getInit().isConstant()) || !isDUbefore(var))) {
        }
      }
      // field
      else if(v instanceof FieldDeclaration) {
        FieldDeclaration f = (FieldDeclaration)v;
        if(f.isFinal()) {
          if(f.hasInit())
            error("initialized field " + f.name() + " can not be assigned");
          else {
            BodyDecl bodyDecl = enclosingBodyDecl();
            if(!(bodyDecl instanceof ConstructorDecl) && !(bodyDecl instanceof InstanceInitializer) && !(bodyDecl instanceof StaticInitializer) && !(bodyDecl instanceof FieldDeclaration))
              error("final field " + f.name() + " may only be assigned in constructors and initializers");
            else if(!isDUbefore(f))
              error("Final field " + f.name() + " may only be assigned once");
          }
        }
      }
      else if(v instanceof ParameterDeclaration) {
        ParameterDeclaration p = (ParameterDeclaration)v;

        // 8.4.1
        if(p.isFinal()) {
          error("Final parameter " + p.name() + " may not be assigned");
        }
      }
      
    }
  }

    // Declared in DefiniteAssignment.jrag at line 458


  protected boolean checkDUeverywhere(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return super.checkDUeverywhere(v);
  }

    // Declared in NameCheck.jrag at line 177


  public void nameCheck() {
    if(decls().isEmpty() && (!isQualified() || !qualifier().type().isUnknown() || qualifier().isPackageAccess()))
      error("no field named " + name());
    if(decls().size() > 1) {
      StringBuffer s = new StringBuffer();
      s.append("several fields named " + name());
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        Variable v = (Variable)iter.next();
        s.append("\n    " + v.type().typeName() + "." + v.name() + " declared in " + v.hostType().typeName());
      }
      error(s.toString());
    }
      
    // 8.8.5.1
    if(inExplicitConstructorInvocation() && !isQualified() && decl().isInstanceVariable() && hostType() == decl().hostType())
      error("instance variable " + name() + " may not be accessed in an explicit constructor invocation");

    Variable v = decl();
    if(!v.isFinal() && !v.isClassVariable() && !v.isInstanceVariable() && v.hostType() != hostType())
      error("A parameter/variable used but not declared in an inner class must be declared final");

    // 8.3.2.3
    if((decl().isInstanceVariable() || decl().isClassVariable()) && !isQualified()) {
      if(hostType() != null && !hostType().declaredBeforeUse(decl(), this)) {
        if(inSameInitializer() && !simpleAssignment() && inDeclaringClass()) {
          BodyDecl b = closestBodyDecl(hostType());
          error("variable " + decl().name() + " is used in " + b + " before it is declared");
        }
      }
    }

  }

    // Declared in NameCheck.jrag at line 211


  // find the bodydecl declared in t in which this construct is nested
  public BodyDecl closestBodyDecl(TypeDecl t) {
    ASTNode node = this;
    while(!(node.getParent().getParent() instanceof Program) && node.getParent().getParent() != t) {
      node = node.getParent();
    }
    if(node instanceof BodyDecl)
      return (BodyDecl)node;
    return null;
  }

    // Declared in NodeConstructors.jrag at line 38

  public VarAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }

    // Declared in PrettyPrint.jadd at line 452


  public void refined_PrettyPrint_VarAccess_toString(StringBuffer s) {
    s.append(name());
  }

    // Declared in Annotations.jrag at line 344


  public void checkModifiers() {
    if(decl() instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)decl();
      if(f.isDeprecated() &&
        !withinDeprecatedAnnotation() &&
        hostType().topLevelType() != f.hostType().topLevelType() &&
        !withinSuppressWarnings("deprecation"))
          warning(f.name() + " in " + f.hostType().typeName() + " has been deprecated");
    }
  }

    // Declared in Enums.jrag at line 428

  protected void checkEnum(EnumDecl enumDecl) {
    super.checkEnum(enumDecl);
    if(decl().isStatic() && decl().hostType() == enumDecl && !isConstant())
      error("may not reference a static field of an enum type from here");
  }

    // Declared in ControlFlowGraph.jrag at line 221

	public VarAccess getDestLocation() {
		return this;
	}

    // Declared in ReachingDefinitions.jrag at line 21

	
	public SmallSet<Definition> reachingDefinitions() {
		if(decl().isField()) {
			FieldDeclaration fd = (FieldDeclaration)decl();
			if(fd.isConstant() && fd.hasInit())
				return SmallSet.<Definition>singleton(fd);
		}
		return super.reachingDefinitions();
	}

    // Declared in LocalVariable.jrag at line 32

	
	public void localVarAccesses(java.util.Set<VarAccess> res) {
		if(decl() instanceof LocalVariable)
			res.add(this);
		super.localVarAccesses(res);
	}

    // Declared in DataFlow.jrag at line 70

	
	public void lockDataFlow(int l) {
		lockReachingDefsAndUses(l);
		if(decl().isField())
			lockWriteDeps();
		super.lockDataFlow(l);
	}

    // Declared in InlineConstant.jrag at line 12

	
	public void inlineConstant() {
		if(!decl().isField() || !decl().isFinal() || !decl().isStatic())
			throw new RefactoringException("not a use of a constant");
		FieldDeclaration cnst = (FieldDeclaration)decl();
		if(!cnst.hasInit())
			throw new RefactoringException("no initialiser");
		bundleQualifier();
		if(isQualified() && !qualifier().isPure())
			throw new RefactoringException("cannot discard impure qualifier");
		Expr p = this;
		if(isQualified())
			p = (Expr)p.getParent();
		Expr init = cnst.getInit();
		init.lockAllNames();
		init.lockDataFlow();
		cnst.setInit((Expr)init.fullCopy());
		p.replaceWith(init.precedence() > p.maxPrecedence() ? new ParExpr(init) : init);
	}

    // Declared in InlineConstant.jrag at line 37

	
	public void doInlineConstant() {
		inlineConstant();
		programRoot().eliminate(LOCKED_NAMES, LOCKED_DATAFLOW);
	}

    // Declared in InlineTemp.jrag at line 9

	
    public void inline() {
    	if(!(decl() instanceof VariableDeclaration))
    		throw new RefactoringException("cannot inline non-local definitions");
    	SmallSet<Definition> pred = this.reachingDefinitions();
    	if(!pred.isSingleton())
    		throw new RefactoringException("ambiguous dataflow");
    	Definition def = pred.iterator().next();
    	if(def instanceof VariableDeclaration) {
    		((VariableDeclaration)def).inline();
    	} else {
    		VarAccess va = (VarAccess)def;
    		if(va.getParent() instanceof AssignSimpleExpr)
    			((AssignSimpleExpr)va.getParent()).inline();
    		else
    			throw new RefactoringException("cannot inline this assignment");
    	}
    }

    // Declared in FreshVariables.jrag at line 150

	
	public void collectAllDecls(Collection<Declaration> res) {
		res.add(decl());
		super.collectAllDecls(res);
	}

    // Declared in UnfoldAssign.jrag at line 3

	// unfold uses of o= and ++,--
	public void unfoldAbbrevAssign(ArrayList<VarAccess> uses) {
		bundleQualifier();
		flushCaches();
		if(isQualified() && !qualifier().isPure())
			return;
		Access unqual = isQualified() ? (Access)getParent() : this;
		ASTNode p = unqual.getParent();
		if(p instanceof PreIncExpr || p instanceof PostIncExpr && p.getParent() instanceof ExprStmt) {
			Access unqual_copy = (Access)unqual.fullCopy();
			p.replaceWith(new AssignSimpleExpr(unqual, new AddExpr(unqual_copy, new IntegerLiteral("1"))));
			uses.add((VarAccess)unqual_copy.lastAccess());
		} else if(p instanceof PreDecExpr || p instanceof PostDecExpr && p.getParent() instanceof ExprStmt) {
			Access unqual_copy = (Access)unqual.fullCopy();
			p.replaceWith(new AssignSimpleExpr(unqual, new SubExpr(unqual_copy, new IntegerLiteral("1"))));
			uses.add((VarAccess)unqual_copy.lastAccess());
		} else if(p instanceof AssignExpr && !(p instanceof AssignSimpleExpr)) {
			Access unqual_copy = ((AssignExpr)p).unfold();
			if(unqual_copy != null)
				uses.add((VarAccess)unqual_copy.lastAccess());
		}
	}

    // Declared in With.jrag at line 197

	
	public void eliminateWith(List<Access> qualifiers, TypeDecl hostType) {
		enclosingBodyDecl().flushCaches();
		if(!isQualified() && decl() instanceof FieldDeclaration && !decl().isStatic()) {
			for(int i=qualifiers.getNumChild()-1;i>=0;i--) {
				Access qual = qualifiers.getChild(i);
				if(qual.type().memberFields(name()).contains(decl())) {
					if(!((FieldDeclaration)decl()).accessibleFrom(hostType))
						throw new RefactoringException("field not accessible");
					qualifyWith((Expr)qual.fullCopy());
					flushCaches();
					super.eliminateWith(qualifiers, hostType);
					return;
				}
			}
			throw new RefactoringException("cannot eliminate with");
		}
		super.eliminateWith(qualifiers, hostType);
	}

    // Declared in LockedVariableAccess.jrag at line 4

	/* A locked variable access is a variable access that does not obey the normal variable lookup
	 * rules, but instead immediately binds to its target. */
	private Variable targetVariable = null;

    // Declared in LockedVariableAccess.jrag at line 25

	
	// introducing locked variable accesses
	public ASTNode lockNames(Collection<String> endangered) {
		if(endangered.contains(name()))
			return lock();
		else
			return super.lockNames(endangered);
	}

    // Declared in LockedVariableAccess.jrag at line 32

	
	public ASTNode lock() {
		return targetVariable == null ? lock(decl()) : this;
	}

    // Declared in LockedVariableAccess.jrag at line 36

	
	public ASTNode lock(Variable target) {
		assert target != null;
		targetVariable = target;
		return this;
	}

    // Declared in LockedVariableAccess.jrag at line 47

	
	public boolean isLocked() { return targetVariable != null; }

    // Declared in LockedVariableAccess.jrag at line 51

	public Access eliminateLockedNames() { return targetVariable == null ? this : unlock(null); }

    // Declared in LockedVariableAccess.jrag at line 52

	public Access unlock(Expr qual) {
		Variable target = targetVariable.refresh();
		targetVariable = null;
		flushCache();
		if(fromSource())
			setID(target.name());
		if(decl().sameSourceDeclAs(target)) {
			return qual == null ? this : qual.qualifiesAccess(this);
		} else if(fromSource()) {
			if((qual == null ? inStaticContext() : qual.staticContextQualifier()) && !target.isStatic())
				throw new RefactoringException("cannot access instance variable in static context");
			SymbolicVarAccess acc = accessVariable(target);
			if(acc.isUnknownVarAccess()) {
				if((qual == null || qual.isPure()) && target.isStatic()) {
					TypeDecl host = target.hostType();
					if(host.accessibleFrom(hostType()) && mayAccess((FieldDeclaration)target))
						return host.createLockedAccess().qualifiesAccess(this);
				} else if(qual != null && (qual.isThisAccess() || qual.isSuperAccess())) {
					acc = parentDot().accessVariable(target);
					if(acc != null)
						return acc.asAccess(null, enclosingType());
				}
				throw new RefactoringException("cannot access variable "+target.name());
			}
			return acc.asAccess(qual, enclosingType());
		} else {
			throw new RefactoringException("cannot access variable "+target.name());
		}
	}

    // Declared in AddRequiredMembers.jrag at line 18

	
	public void addRequiredMembers(TypeDecl host, java.util.Set<MethodDecl> meths, java.util.Set<FieldDeclaration> fds, java.util.Set<MemberTypeDecl> mtds) {
		if(decl().isInstanceVariable() && ((FieldDeclaration)decl()).hostType() == host)
			fds.add((FieldDeclaration)decl());
		super.addRequiredMembers(host, meths, fds, mtds);
	}

    // Declared in Locking.jrag at line 82

	
	public VarAccess lockedCopy() {
		VarAccess res = (VarAccess)super.lockedCopy();
		res.lock(decl());
		return res;
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 16

    public VarAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 16
    public VarAccess(String p0) {
        setID(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 16
    public VarAccess(beaver.Symbol p0) {
        setID(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 16
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

    // Declared in LockedVariableAccess.jrag at line 14

	
	  public void toString(StringBuffer s) {
		if(targetVariable == null) {
			refined_PrettyPrint_VarAccess_toString(s);
		} else {
			s.append("[[");
			refined_PrettyPrint_VarAccess_toString(s);
			s.append("]]");
		}
	}

    // Declared in LookupVariable.jrag at line 230
private SimpleSet refined_VariableScopePropagation_VarAccess_decls()
{
    SimpleSet set = lookupVariable(name());
    if(set.size() == 1) {
      Variable v = (Variable)set.iterator().next();
      if(!isQualified() && inStaticContext()) {
        if(v.isInstanceVariable() && !hostType().memberFields(v.name()).isEmpty())
          return SimpleSet.emptySet;
      }
      else if(isQualified() && qualifier().staticContextQualifier()) {
        if(v.isInstanceVariable())
          return SimpleSet.emptySet;
      }
    }
    return set;
  }

    // Declared in Alias.jrag at line 34
private boolean refined_Alias_VarAccess_mayReferTo_Location(Location l)
{ return decl().mayAlias(l); }

    // Declared in Alias.jrag at line 39
private boolean refined_Alias_VarAccess_mustReferTo_Location(Location l)
{ return decl().mustAlias(l); }

    // Declared in Locking.jrag at line 36

	
	public ASTNode lockAllNames() {
		ASTNode res = lock();
		if(res == this)
			return super.lockAllNames();
		else
			return res.lockAllNames();
	}

    // Declared in ConstantExpression.jrag at line 108
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        Constant constant_value = constant_compute();
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(decl().getInit().constant());  }

    protected int isConstant_visited = -1;
    protected boolean isConstant_computed = false;
    protected boolean isConstant_initialized = false;
    protected boolean isConstant_value;
    // Declared in ConstantExpression.jrag at line 500
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        if(isConstant_computed) {
            return isConstant_value;
        }
        ASTNode$State state = state();
        if (!isConstant_initialized) {
            isConstant_initialized = true;
            isConstant_value = false;
        }
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            do {
                isConstant_visited = state.CIRCLE_INDEX;
                state.CHANGE = false;
                boolean new_isConstant_value = isConstant_compute();
                if (new_isConstant_value!=isConstant_value)
                    state.CHANGE = true;
                isConstant_value = new_isConstant_value; 
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
            isConstant_computed = true;
            }
            else {
            state.RESET_CYCLE = true;
            isConstant_compute();
            state.RESET_CYCLE = false;
              isConstant_computed = false;
              isConstant_initialized = false;
            }
            state.IN_CIRCLE = false; 
            return isConstant_value;
        }
        if(isConstant_visited != state.CIRCLE_INDEX) {
            isConstant_visited = state.CIRCLE_INDEX;
            if (state.RESET_CYCLE) {
                isConstant_computed = false;
                isConstant_initialized = false;
                isConstant_visited = -1;
                return isConstant_value;
            }
            boolean new_isConstant_value = isConstant_compute();
            if (new_isConstant_value!=isConstant_value)
                state.CHANGE = true;
            isConstant_value = new_isConstant_value; 
            return isConstant_value;
        }
        return isConstant_value;
    }

    private boolean isConstant_compute() {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      return f.isConstant() && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
    }
    boolean result = v.isFinal() && v.hasInit() && v.getInit().isConstant() && (v.type().isPrimitive() || v.type().isString());
    return result && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
  }

    // Declared in DefiniteAssignment.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        Variable varDecl_value = varDecl_compute();
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return decl();  }

    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 353
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
    return (isDest() && decl() == v) || isDAbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 833
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return isDUbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 1208
 @SuppressWarnings({"unchecked", "cast"})     public boolean unassignedEverywhere(Variable v, TryStmt stmt) {
        ASTNode$State state = state();
        boolean unassignedEverywhere_Variable_TryStmt_value = unassignedEverywhere_compute(v, stmt);
        return unassignedEverywhere_Variable_TryStmt_value;
    }

    private boolean unassignedEverywhere_compute(Variable v, TryStmt stmt) {
    if(isDest() && decl() == v && enclosingStmt().reachable()) {
      return false;
    }
    return super.unassignedEverywhere(v, stmt);
  }

    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LockedVariableAccess.jrag at line 12
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

    private SimpleSet decls_compute() {  return targetVariable == null ? refined_VariableScopePropagation_VarAccess_decls() : SimpleSet.emptySet.add(targetVariable);  }

    protected boolean decl_computed = false;
    protected Variable decl_value;
    // Declared in LookupVariable.jrag at line 245
 @SuppressWarnings({"unchecked", "cast"})     public Variable decl() {
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

    private Variable decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (Variable)decls.iterator().next();
    return unknownField();
  }

    // Declared in NameCheck.jrag at line 221
 @SuppressWarnings({"unchecked", "cast"})     public boolean inSameInitializer() {
        ASTNode$State state = state();
        boolean inSameInitializer_value = inSameInitializer_compute();
        return inSameInitializer_value;
    }

    private boolean inSameInitializer_compute() {
    BodyDecl b = closestBodyDecl(decl().hostType());
    if(b == null) return false;
    if(b instanceof FieldDeclaration && ((FieldDeclaration)b).isStatic() == decl().isStatic())
      return true;
    if(b instanceof InstanceInitializer && !decl().isStatic())
      return true;
    if(b instanceof StaticInitializer && decl().isStatic())
      return true;
    return false;
  }

    // Declared in NameCheck.jrag at line 233
 @SuppressWarnings({"unchecked", "cast"})     public boolean simpleAssignment() {
        ASTNode$State state = state();
        boolean simpleAssignment_value = simpleAssignment_compute();
        return simpleAssignment_value;
    }

    private boolean simpleAssignment_compute() {  return isDest() && getParent() instanceof AssignSimpleExpr;  }

    // Declared in NameCheck.jrag at line 235
 @SuppressWarnings({"unchecked", "cast"})     public boolean inDeclaringClass() {
        ASTNode$State state = state();
        boolean inDeclaringClass_value = inDeclaringClass_compute();
        return inDeclaringClass_value;
    }

    private boolean inDeclaringClass_compute() {  return hostType() == decl().hostType();  }

    // Declared in PrettyPrint.jadd at line 801
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    // Declared in QualifiedNames.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected boolean isFieldAccess_computed = false;
    protected boolean isFieldAccess_value;
    // Declared in ResolveAmbiguousNames.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccess() {
        if(isFieldAccess_computed) {
            return isFieldAccess_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isFieldAccess_value = isFieldAccess_compute();
        if(isFinal && num == state().boundariesCrossed)
            isFieldAccess_computed = true;
        return isFieldAccess_value;
    }

    private boolean isFieldAccess_compute() {  return decl().isClassVariable() || decl().isInstanceVariable();  }

    // Declared in SyntacticClassification.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in TypeAnalysis.jrag at line 283
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

    // Declared in TypeCheck.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return true;  }

    // Declared in DataFlow.jrag at line 178
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

    private Location getLocation_compute() {
		Variable decl = decl();
		BodyDecl host = hostBodyDecl();
		if(decl.isInstanceVariable() && host instanceof MethodDecl && 
				(!isQualified() || qualifier().isThisAccess() || qualifier().isSuperAccess()))
			return new FieldInReceiver((MethodDecl)host, (FieldDeclaration)decl);
		return decl;
	}

    // Declared in DataFlow.jrag at line 187
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayReferTo(Location l) {
        ASTNode$State state = state();
        boolean mayReferTo_Location_value = mayReferTo_compute(l);
        return mayReferTo_Location_value;
    }

    private boolean mayReferTo_compute(Location l) {
		if(l instanceof FieldInReceiver) {
			FieldInReceiver fir = (FieldInReceiver)l;
			if(decl() == fir.getField())
				return true;
		}
		return refined_Alias_VarAccess_mayReferTo_Location(l);
	}

    // Declared in DataFlow.jrag at line 196
 @SuppressWarnings({"unchecked", "cast"})     public boolean mustReferTo(Location l) {
        ASTNode$State state = state();
        boolean mustReferTo_Location_value = mustReferTo_compute(l);
        return mustReferTo_Location_value;
    }

    private boolean mustReferTo_compute(Location l) {
		if(l instanceof FieldInReceiver) {
			FieldInReceiver fir = (FieldInReceiver)l;
			if(decl() == fir.getField() && hostBodyDecl() == fir.getMethod() &&
					(!isQualified() || qualifier().isThisAccess() || qualifier().isSuperAccess()))
				return true;
		} else if(l instanceof FieldDeclaration && ((FieldDeclaration)l).isStatic()) {
			return decl() == l;
		}
		return refined_Alias_VarAccess_mustReferTo_Location(l);
	}

    // Declared in ControlFlowGraph.jrag at line 163
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {
		if(!isDest())
			return this;
		AssignExpr assgn = modifyingAssignExpr();
		if(assgn == null)
			return this;
		return assgn.getSource().first();
	}

    // Declared in ControlFlowGraph.jrag at line 171
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
		if(!isDest())
			return following();
		AssignExpr assgn = modifyingAssignExpr();
		if(assgn == null)
			return following();
		return SmallSet.<CFGNode>singleton(assgn);
	}

    // Declared in VariableExt.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccessInThis(FieldDeclaration fd) {
        ASTNode$State state = state();
        boolean isFieldAccessInThis_FieldDeclaration_value = isFieldAccessInThis_compute(fd);
        return isFieldAccessInThis_FieldDeclaration_value;
    }

    private boolean isFieldAccessInThis_compute(FieldDeclaration fd) {  return decl() == fd;  }

    // Declared in TypeHierarchyCheck.jrag at line 122
 @SuppressWarnings({"unchecked", "cast"})     public boolean inExplicitConstructorInvocation() {
        ASTNode$State state = state();
        boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
        return inExplicitConstructorInvocation_value;
    }

    protected boolean modifyingAssignExpr_computed = false;
    protected AssignExpr modifyingAssignExpr_value;
    // Declared in ControlFlowGraph.jrag at line 181
 @SuppressWarnings({"unchecked", "cast"})     public AssignExpr modifyingAssignExpr() {
        if(modifyingAssignExpr_computed) {
            return modifyingAssignExpr_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        modifyingAssignExpr_value = getParent().Define_AssignExpr_modifyingAssignExpr(this, null);
        if(isFinal && num == state().boundariesCrossed)
            modifyingAssignExpr_computed = true;
        return modifyingAssignExpr_value;
    }

    // Declared in LockedVariableAccess.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl enclosingType() {
        ASTNode$State state = state();
        TypeDecl enclosingType_value = getParent().Define_TypeDecl_enclosingType(this, null);
        return enclosingType_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected void collect_contributors_FieldDeclaration_uses() {
        // Declared in Uses.jrag at line 9
        if(decl() instanceof FieldDeclaration) {
        {
            FieldDeclaration ref = (FieldDeclaration)((FieldDeclaration)decl());
            if(ref != null)
                ref.FieldDeclaration_uses_contributors().add(this);
        }
        }
        super.collect_contributors_FieldDeclaration_uses();
    }
    protected void collect_contributors_VariableDeclaration_uses() {
        // Declared in Uses.jrag at line 15
        if(decl() instanceof VariableDeclaration) {
        {
            VariableDeclaration ref = (VariableDeclaration)((VariableDeclaration)decl());
            if(ref != null)
                ref.VariableDeclaration_uses_contributors().add(this);
        }
        }
        super.collect_contributors_VariableDeclaration_uses();
    }
    protected void collect_contributors_ParameterDeclaration_uses() {
        // Declared in Uses.jrag at line 21
        if(decl() instanceof ParameterDeclaration) {
        {
            ParameterDeclaration ref = (ParameterDeclaration)((ParameterDeclaration)decl());
            if(ref != null)
                ref.ParameterDeclaration_uses_contributors().add(this);
        }
        }
        super.collect_contributors_ParameterDeclaration_uses();
    }
    protected void contributeTo_FieldDeclaration_FieldDeclaration_uses(Collection<VarAccess> collection) {
        super.contributeTo_FieldDeclaration_FieldDeclaration_uses(collection);
        if(decl() instanceof FieldDeclaration)
            collection.add(this);
    }

    protected void contributeTo_VariableDeclaration_VariableDeclaration_uses(Collection<VarAccess> collection) {
        super.contributeTo_VariableDeclaration_VariableDeclaration_uses(collection);
        if(decl() instanceof VariableDeclaration)
            collection.add(this);
    }

    protected void contributeTo_ParameterDeclaration_ParameterDeclaration_uses(Collection<VarAccess> collection) {
        super.contributeTo_ParameterDeclaration_ParameterDeclaration_uses(collection);
        if(decl() instanceof ParameterDeclaration)
            collection.add(this);
    }

}
