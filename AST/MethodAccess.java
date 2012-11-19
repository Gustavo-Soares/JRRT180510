
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class MethodAccess extends Access implements Cloneable, Definition, Call, LockableName {
    public void flushCache() {
        super.flushCache();
        computeDAbefore_int_Variable_values = null;
        exceptionCollection_computed = false;
        exceptionCollection_value = null;
        decls_computed = false;
        decls_value = null;
        decl_computed = false;
        decl_value = null;
        type_computed = false;
        type_value = null;
        typeArguments_MethodDecl_values = null;
        succ_computed = false;
        succ_value = null;
        uncaughtExceptions_computed = false;
        uncaughtExceptions_value = null;
        possibleTargets_computed = false;
        possibleTargets_value = null;
        getUniqueTarget_computed = false;
        getUniqueTarget_value = null;
        getLocation_computed = false;
        getLocation_value = null;
        shouldMakeConversionExplicit_TypeDecl_values = null;
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        Definition_reachedUses_computed = false;
        Definition_reachedUses_value = null;
    Definition_reachedUses_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodAccess clone() throws CloneNotSupportedException {
        MethodAccess node = (MethodAccess)super.clone();
        node.computeDAbefore_int_Variable_values = null;
        node.exceptionCollection_computed = false;
        node.exceptionCollection_value = null;
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_computed = false;
        node.decl_value = null;
        node.type_computed = false;
        node.type_value = null;
        node.typeArguments_MethodDecl_values = null;
        node.succ_computed = false;
        node.succ_value = null;
        node.uncaughtExceptions_computed = false;
        node.uncaughtExceptions_value = null;
        node.possibleTargets_computed = false;
        node.possibleTargets_value = null;
        node.getUniqueTarget_computed = false;
        node.getUniqueTarget_value = null;
        node.getLocation_computed = false;
        node.getLocation_value = null;
        node.shouldMakeConversionExplicit_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodAccess copy() {
      try {
          MethodAccess node = (MethodAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodAccess fullCopy() {
        MethodAccess res = (MethodAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AnonymousClasses.jrag at line 203


  protected void collectExceptions(Collection c, ASTNode target) {
    super.collectExceptions(c, target);
    for(int i = 0; i < decl().getNumException(); i++)
      c.add(decl().getException(i).type());
  }

    // Declared in ExceptionHandling.jrag at line 43

  
  public void exceptionHandling() {
    for(Iterator iter = exceptionCollection().iterator(); iter.hasNext(); ) {
      TypeDecl exceptionType = (TypeDecl)iter.next();
      if(!handlesException(exceptionType))
        error("" + decl().hostType().fullName() + "." + this + " invoked in " + hostType().fullName() + " may throw uncaught exception " + exceptionType.fullName());
    }
  }

    // Declared in ExceptionHandling.jrag at line 225


  protected boolean reachedException(TypeDecl catchType) {
    for(Iterator iter = exceptionCollection().iterator(); iter.hasNext(); ) {
      TypeDecl exceptionType = (TypeDecl)iter.next();
      if(catchType.mayCatch(exceptionType))
        return true;
    }
    return super.reachedException(catchType);
  }

    // Declared in LookupMethod.jrag at line 119

  private static SimpleSet removeInstanceMethods(SimpleSet c) {
    SimpleSet set = SimpleSet.emptySet;
    for(Iterator iter = c.iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m.isStatic())
        set = set.add(m);
    }
    return set;
  }

    // Declared in LookupMethod.jrag at line 158

  
  public boolean applicable(MethodDecl decl) {
    if(getNumArg() != decl.getNumParameter())
      return false;
    if(!name().equals(decl.name()))
      return false;
    for(int i = 0; i < getNumArg(); i++) {
      if(!getArg(i).type().instanceOf(decl.getParameter(i).type()))
        return false;
    }
    return true;
  }

    // Declared in NodeConstructors.jrag at line 56


  public MethodAccess(String name, List args, int start, int end) {
    this(name, args);
    setStart(start);
    setEnd(end);
  }

    // Declared in PrettyPrint.jadd at line 456


  public void refined_PrettyPrint_MethodAccess_toString(StringBuffer s) {
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

    // Declared in TypeHierarchyCheck.jrag at line 23

  
  public void nameCheck() {
    if(isQualified() && qualifier().isPackageAccess() && !qualifier().isUnknown())
      error("The method " + decl().signature() + 
          " can not be qualified by a package name.");
    if(isQualified() && decl().isAbstract() && qualifier().isSuperAccess())
      error("may not access abstract methods in superclass");
    if(decls().isEmpty() && (!isQualified() || !qualifier().isUnknown())) {
      StringBuffer s = new StringBuffer();
      s.append("no method named " + name());
      s.append("(");
      for(int i = 0; i < getNumArg(); i++) {
        if(i != 0)
          s.append(", ");
        s.append(getArg(i).type().typeName());
      }
      s.append(")" + " in " + methodHost() + " matches.");
      if(singleCandidateDecl() != null)
        s.append(" However, there is a method " + singleCandidateDecl().signature());
      error(s.toString());
    }
    if(decls().size() > 1) {
      boolean allAbstract = true;
      for(Iterator iter = decls().iterator(); iter.hasNext() && allAbstract; ) {
         MethodDecl m = (MethodDecl)iter.next();
        if(!m.isAbstract() && !m.hostType().isObject())
          allAbstract = false;
      }
      if(!allAbstract && validArgs()) {
        StringBuffer s = new StringBuffer();
        s.append("several most specific methods for " + this + "\n");
        for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
          MethodDecl m = (MethodDecl)iter.next();
          s.append("    " + m.signature() + " in " + m.hostType().typeName() + "\n");
        }
        error(s.toString());
      }
       
    }
  }

    // Declared in Annotations.jrag at line 336


  public void checkModifiers() {
    if(decl().isDeprecated() &&
      !withinDeprecatedAnnotation() &&
      hostType().topLevelType() != decl().hostType().topLevelType() &&
      !withinSuppressWarnings("deprecation"))
        warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated");
  }

    // Declared in GenericMethodsInference.jrag at line 46


  // Generic Method Type Inference
  public Collection computeConstraints(GenericMethodDecl decl) {
    Constraints c = new Constraints();
    // store type parameters
    for(int i = 0; i < decl.original().getNumTypeParameter(); i++)
      c.addTypeVariable(decl.original().getTypeParameter(i));
    
    // add initial constraints
    for(int i = 0; i < getNumArg(); i++) {
      TypeDecl A = getArg(i).type();
      int index = i >= decl.getNumParameter() ? decl.getNumParameter() - 1 : i;
      TypeDecl F = decl.getParameter(index).type();
      if(decl.getParameter(index) instanceof VariableArityParameterDeclaration 
         && (getNumArg() != decl.getNumParameter() || !A.isArrayDecl())) {
        F = F.componentType();
      }
      c.convertibleTo(A, F);
    }
    if(c.rawAccess)
      return new ArrayList();
    
    //c.printConstraints();
    //System.err.println("Resolving equality constraints");
    c.resolveEqualityConstraints();
    //c.printConstraints();

    //System.err.println("Resolving supertype constraints");
    c.resolveSupertypeConstraints();
    //c.printConstraints();

    //System.err.println("Resolving unresolved type arguments");
    //c.resolveBounds();
    //c.printConstraints();

    if(c.unresolvedTypeArguments()) {
      TypeDecl S = assignConvertedType();
      if(S.isUnboxedPrimitive())
        S = S.boxed();
      TypeDecl R = decl.type();
      // TODO: replace all uses of type variables in R with their inferred types
      TypeDecl Rprime = R;
      if(R.isVoid())
        R = typeObject();
      c.convertibleFrom(S, R);
      // TODO: additional constraints

      c.resolveEqualityConstraints();
      c.resolveSupertypeConstraints();
      //c.resolveBounds();

      c.resolveSubtypeConstraints();
    }

    return c.typeArguments();
  }

    // Declared in MethodSignature.jrag at line 23


  protected SimpleSet potentiallyApplicable(Collection candidates) {
    SimpleSet potentiallyApplicable = SimpleSet.emptySet;
    // select potentially applicable methods
    for(Iterator iter = candidates.iterator(); iter.hasNext(); ) {
      MethodDecl decl = (MethodDecl)iter.next();
      if(potentiallyApplicable(decl) && accessible(decl)) {
        if(decl instanceof GenericMethodDecl) {
          decl = ((GenericMethodDecl)decl).lookupParMethodDecl(typeArguments(decl));
        }
        potentiallyApplicable = potentiallyApplicable.add(decl);
      }
    }
    return potentiallyApplicable;
  }

    // Declared in MethodSignature.jrag at line 38


  protected SimpleSet applicableBySubtyping(SimpleSet potentiallyApplicable) {
    SimpleSet maxSpecific = SimpleSet.emptySet;
    for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
      MethodDecl decl = (MethodDecl)iter.next();
      if(applicableBySubtyping(decl))
        maxSpecific = mostSpecific(maxSpecific, decl);
    }
    return maxSpecific;
  }

    // Declared in MethodSignature.jrag at line 48


  protected SimpleSet applicableByMethodInvocationConversion(SimpleSet potentiallyApplicable, SimpleSet maxSpecific) {
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        MethodDecl decl = (MethodDecl)iter.next();
        if(applicableByMethodInvocationConversion(decl))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }
    return maxSpecific;
  }

    // Declared in MethodSignature.jrag at line 59


  protected SimpleSet applicableVariableArity(SimpleSet potentiallyApplicable, SimpleSet maxSpecific) {
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        MethodDecl decl = (MethodDecl)iter.next();
        if(decl.isVariableArity() && applicableVariableArity(decl))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }
    return maxSpecific;
  }

    // Declared in MethodSignature.jrag at line 140


  private static SimpleSet mostSpecific(SimpleSet maxSpecific, MethodDecl decl) {
    if(maxSpecific.isEmpty())
      maxSpecific = maxSpecific.add(decl);
    else {
      if(decl.moreSpecificThan((MethodDecl)maxSpecific.iterator().next()))
        maxSpecific = SimpleSet.emptySet.add(decl);
      else if(!((MethodDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
        maxSpecific = maxSpecific.add(decl);
    }
    return maxSpecific;
  }

    // Declared in DataFlow.jrag at line 64

	
	public void lockDataFlow(int l) {
		lockReachingDefsAndUses(l);
		lockWriteDeps();
		super.lockDataFlow(l);
	}

    // Declared in InlineMethod.jrag at line 3

	
	public void inline() {
		ASTNode n = inlineToAnonymousMethod().introduceOutParameter().open().inline();
		if(n instanceof Block)
			((Block)n).inline();
	}

    // Declared in InlineMethod.jrag at line 20

	
	public void doInline() {
    	Program root = programRoot();
		inline();
		root.eliminate(WITH_STMT, FRESH_VARIABLES, LOCKED_NAMES);
	}

    // Declared in InlineMethodAccess.jrag at line 3

	
	public AnonymousMethod inlineToAnonymousMethod() {
		MethodDecl target = getUniqueTarget();
		if(target == null)
			throw new RefactoringException("cannot inline ambiguous method call");
		if(!target.hasBlock() || !target.fromSource())
			throw new RefactoringException("cannot inline call to method without body");
		MethodDecl target_cp = target.fullCopy();
		target.unfoldSynchronized();
		target.lockAllNames();
		AnonymousMethod anon = target.asAnonymousMethod();
		bundleQualifier();
		if(isRightChildOfDot()) {
			Expr qual = qualifier();
			if(qual.isSuperAccess())
				qual = qual.convertSuperToThis();
			if(!(qual instanceof ThisAccess)) {
				qual.lockAllNames();
				anon.setBlock(new Block(new List<Stmt>().add(new WithStmt((Access)qual, anon.getBlock()))));
			}
			parentDot().replaceWith(anon);
		} else {
			replaceWith(anon);
		}
		target.replaceWith(target_cp);
		anon.setArgList(getArgList());
		target_cp.hostType().flushCaches();
		return anon;
	}

    // Declared in FreshVariables.jrag at line 155

	
	public void collectAllDecls(Collection<Declaration> res) {
		res.add(decl());
		super.collectAllDecls(res);
	}

    // Declared in With.jrag at line 158

	
	public void eliminateWith(List<Access> qualifiers, TypeDecl hostType) {
		if(isQualified()) {
			super.eliminateWith(qualifiers, hostType);
		} else {
			for(int i=qualifiers.getNumChild()-1;i>=0;i--) {
				Access qual = qualifiers.getChild(i);
				if(qual.type().memberMethods(name()).contains(decl())) {
					if(!decl().accessibleFrom(hostType))
						throw new RefactoringException("method not accessible");
					qualifyWith((Expr)qual.fullCopy());
					flushCaches();
					super.eliminateWith(qualifiers, hostType);
					return;
				}
			}
			throw new RefactoringException("cannot eliminate with");
		}
	}

    // Declared in LockedMethodAccess.jrag at line 4

	/* A locked method access is a method access that does not obey the normal method lookup
	 * rules, but instead immediately binds to its target. */
	private SavedMethodDecl targetMethod = null;

    // Declared in LockedMethodAccess.jrag at line 6

	
	public MethodAccess(MethodDecl target, List<Expr> args) {
		this(target.name(), args);
		targetMethod = target.save();
	}

    // Declared in LockedMethodAccess.jrag at line 87

	
	// introducing locked method accesses
	public ASTNode lockMethodNames(Collection<String> endangered) {
		if(endangered.contains(name()))
			lock();
		return super.lockMethodNames(endangered);
	}

    // Declared in LockedMethodAccess.jrag at line 93

	
	public ASTNode lock() {
		return targetMethod == null ? lock(decl()) : this;
	}

    // Declared in LockedMethodAccess.jrag at line 97

	
	public ASTNode lock(MethodDecl md) {
		assert md != null && !md.isUnknown();
		MethodDecl target = md.isSubstituted() ? md.sourceMethodDecl() : md;
		targetMethod = target.save();
		return this;
	}

    // Declared in LockedMethodAccess.jrag at line 107

	
	public boolean isLocked() { return targetMethod != null; }

    // Declared in LockedMethodAccess.jrag at line 111

	public Access eliminateLockedNames() {
		return targetMethod == null ? this : unlock(null);
	}

    // Declared in LockedMethodAccess.jrag at line 115

	
	public Access unlock(Expr qual) {
		MethodDecl target = targetMethod.getDecl();
		targetMethod = null;
		flushCache();
		if(fromSource())
			setID(target.name());
		if(decl().sameSourceDeclAs(target)) {
			return qual == null ? this : qual.qualifiesAccess(this);
		} else if(fromSource()) {
			if((qual == null ? inStaticContext() : qual.staticContextQualifier()) && !target.isStatic())
				throw new RefactoringException("cannot access instance method in static context");
			MethodAccessInfo acc = accessMethod(target);
			if(acc == null) {
				if((qual == null || qual.isPure()) && target.isStatic()) {
					TypeDecl host = target.hostType();
					// since the target is static, we can access it through the raw type
					if(host.isGenericType())
						host = ((GenericTypeDecl)host).rawType();
					if(host.accessibleFrom(hostType()) && target.accessibleFrom(hostType()))
						return host.createLockedAccess().qualifiesAccess(this);
				} else if(qual != null && (qual.isThisAccess() || qual.isSuperAccess())) {
					acc = parentDot().accessMethod(target);
					if(acc != null)
						return acc.eliminate(null, enclosingType(), inStaticContext(), getArgs());
				}
				throw new RefactoringException("cannot access method "+target.name());
			}
			Access res = acc.eliminate(qual, enclosingType(), inStaticContext(), getArgs());
			if(res == null)
				throw new RefactoringException("cannot access method "+target.name());
			return res;
		} else {
			throw new RefactoringException("cannot access method "+target.name());
		}
	}

    // Declared in AddRequiredMembers.jrag at line 24

	
	public void addRequiredMembers(TypeDecl host, java.util.Set<MethodDecl> meths, java.util.Set<FieldDeclaration> fds, java.util.Set<MemberTypeDecl> mtds) {
		if(/*!decl().isStatic() &&*/ decl().hostType() == host)
			meths.add(decl());
		super.addRequiredMembers(host, meths, fds, mtds);
	}

    // Declared in Locking.jrag at line 88

	
	public MethodAccess lockedCopy() {
		MethodAccess res = (MethodAccess)super.lockedCopy();
		res.lock(decl());
		return res;
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 17

    public MethodAccess() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 17
    public MethodAccess(String p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 17
    public MethodAccess(beaver.Symbol p0, List<Expr> p1) {
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
    // Declared in java.ast line 17
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
    // Declared in java.ast line 17
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

    // Declared in MethodSignature.jrag at line 11

    protected SimpleSet maxSpecific(Collection candidates) {
    SimpleSet potentiallyApplicable = potentiallyApplicable(candidates);
    // first phase
    SimpleSet maxSpecific = applicableBySubtyping(potentiallyApplicable);
    // second phase
    maxSpecific = applicableByMethodInvocationConversion(potentiallyApplicable,
        maxSpecific);
    // third phase
    maxSpecific = applicableVariableArity(potentiallyApplicable, maxSpecific);
    return maxSpecific;
  }

    // Declared in LockedMethodAccess.jrag at line 76

	
	  public void toString(StringBuffer s) {
		if(targetMethod == null) {
			refined_PrettyPrint_MethodAccess_toString(s);
		} else {
			s.append("[[");
			refined_PrettyPrint_MethodAccess_toString(s);
			s.append("]]");
		}
	}

    // Declared in MethodSignature.jrag at line 331


  // 15.12.3
  // refine old type checking to be valid when using variable arity parameters
    public void typeCheck() {
    if(isQualified() && decl().isAbstract() && qualifier().isSuperAccess())
      error("may not access abstract methods in superclass");
    if(!decl().isVariableArity() || invokesVariableArityAsArray()) {
      for(int i = 0; i < decl().getNumParameter(); i++) {
        TypeDecl exprType = getArg(i).type();
        TypeDecl parmType = decl().getParameter(i).type();
        if(!exprType.methodInvocationConversionTo(parmType) && !exprType.isUnknown() && !parmType.isUnknown()) {
          error("#The type " + exprType.typeName() + " of expr " +
            getArg(i) + " is not compatible with the method parameter " +
            decl().getParameter(i));
        }
      }
    }
  }

    // Declared in LookupMethod.jrag at line 96
private SimpleSet refined_LookupMethod_MethodAccess_decls()
{
    SimpleSet maxSpecific = maxSpecific(lookupMethod(name()));
    if(isQualified() ? qualifier().staticContextQualifier() : inStaticContext())
      maxSpecific = removeInstanceMethods(maxSpecific);
    return maxSpecific;
  }

    // Declared in TypeAnalysis.jrag at line 284
private TypeDecl refined_TypeAnalysis_MethodAccess_type()
{ return decl().type(); }

    // Declared in Alias.jrag at line 42
private boolean refined_Alias_MethodAccess_mayDefine_Location(Location l)
{ return l.isHeapLocation(); }

    // Declared in Locking.jrag at line 36

	
	public ASTNode lockAllNames() {
		ASTNode res = lock();
		if(res == this)
			return super.lockAllNames();
		else
			return res.lockAllNames();
	}

    protected java.util.Map computeDAbefore_int_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 414
 @SuppressWarnings({"unchecked", "cast"})     public boolean computeDAbefore(int i, Variable v) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(Integer.valueOf(i));
        _parameters.add(v);
if(computeDAbefore_int_Variable_values == null) computeDAbefore_int_Variable_values = new java.util.HashMap(4);
        if(computeDAbefore_int_Variable_values.containsKey(_parameters)) {
            return ((Boolean)computeDAbefore_int_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean computeDAbefore_int_Variable_value = computeDAbefore_compute(i, v);
        if(isFinal && num == state().boundariesCrossed)
            computeDAbefore_int_Variable_values.put(_parameters, Boolean.valueOf(computeDAbefore_int_Variable_value));
        return computeDAbefore_int_Variable_value;
    }

    private boolean computeDAbefore_compute(int i, Variable v) {  return i == 0 ? isDAbefore(v) : getArg(i-1).isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 416
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return getNumArg() == 0 ? isDAbefore(v) : getArg(getNumArg()-1).isDAafter(v);  }

    protected boolean exceptionCollection_computed = false;
    protected Collection exceptionCollection_value;
    // Declared in ExceptionHandling.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public Collection exceptionCollection() {
        if(exceptionCollection_computed) {
            return exceptionCollection_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        exceptionCollection_value = exceptionCollection_compute();
        if(isFinal && num == state().boundariesCrossed)
            exceptionCollection_computed = true;
        return exceptionCollection_value;
    }

    private Collection exceptionCollection_compute() {
    //System.out.println("Computing exceptionCollection for " + name());
    HashSet set = new HashSet();
    Iterator iter = decls().iterator();
    if(!iter.hasNext())
      return set;

    MethodDecl m = (MethodDecl)iter.next();
    //System.out.println("Processing first found method " + m.signature() + " in " + m.hostType().fullName());

    for(int i = 0; i < m.getNumException(); i++) {
      TypeDecl exceptionType = m.getException(i).type();
      set.add(exceptionType);
    }
    while(iter.hasNext()) {
      HashSet first = new HashSet();
      first.addAll(set);
      HashSet second = new HashSet();
      m = (MethodDecl)iter.next();
      //System.out.println("Processing the next method " + m.signature() + " in " + m.hostType().fullName());
      for(int i = 0; i < m.getNumException(); i++) {
        TypeDecl exceptionType = m.getException(i).type();
        second.add(exceptionType);
      }
      set = new HashSet();
      for(Iterator i1 = first.iterator(); i1.hasNext(); ) {
        TypeDecl firstType = (TypeDecl)i1.next(); 
        for(Iterator i2 = second.iterator(); i2.hasNext(); ) {
          TypeDecl secondType = (TypeDecl)i2.next();
          if(firstType.instanceOf(secondType)) {
            set.add(firstType);
          }
          else if(secondType.instanceOf(firstType)) {
            set.add(secondType);
          }
        }
      }
    }
    return set;
  }

    // Declared in LookupMethod.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl singleCandidateDecl() {
        ASTNode$State state = state();
        MethodDecl singleCandidateDecl_value = singleCandidateDecl_compute();
        return singleCandidateDecl_value;
    }

    private MethodDecl singleCandidateDecl_compute() {
    MethodDecl result = null;
    for(Iterator iter = lookupMethod(name()).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(result == null)
        result = m;
      else if(m.getNumParameter() == getNumArg() && result.getNumParameter() != getNumArg())
        result = m;
    }
    return result;
  }

    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LockedMethodAccess.jrag at line 74
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

    private SimpleSet decls_compute() {  return targetMethod == null ? refined_LookupMethod_MethodAccess_decls() : targetMethod.getDecl();  }

    protected boolean decl_computed = false;
    protected MethodDecl decl_value;
    // Declared in LookupMethod.jrag at line 103
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl decl() {
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

    private MethodDecl decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (MethodDecl)decls.iterator().next();

    // 8.4.6.4 - only return the first method in case of multply inherited abstract methods
    boolean allAbstract = true;
    for(Iterator iter = decls.iterator(); iter.hasNext() && allAbstract; ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(!m.isAbstract() && !m.hostType().isObject())
        allAbstract = false;
    }
    if(decls.size() > 1 && allAbstract)
      return (MethodDecl)decls.iterator().next();
    return unknownMethod();
  }

    // Declared in LookupMethod.jrag at line 170
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessible(MethodDecl m) {
        ASTNode$State state = state();
        boolean accessible_MethodDecl_value = accessible_compute(m);
        return accessible_MethodDecl_value;
    }

    private boolean accessible_compute(MethodDecl m) {
    if(!isQualified())
      return true;
    if(!m.accessibleFrom(hostType()))
      return false;
    // the method is not accessible if the type is not accessible
    if(!qualifier().type().accessibleFrom(hostType()))
      return false;
    // 6.6.2.1 -  include qualifier type for protected access
    if(m.isProtected() && !m.hostPackage().equals(hostPackage()) && !m.isStatic() && !qualifier().isSuperAccess()) {
      TypeDecl C = m.hostType();
      TypeDecl S = hostType().subclassWithinBody(C);
      TypeDecl Q = qualifier().type();
      if(S == null || !Q.instanceOf(S))
        return false;
    }
    return true;
  }

    // Declared in NameCheck.jrag at line 60
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

    // Declared in PrettyPrint.jadd at line 802
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    // Declared in QualifiedNames.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    // Declared in ResolveAmbiguousNames.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodAccess() {
        ASTNode$State state = state();
        boolean isMethodAccess_value = isMethodAccess_compute();
        return isMethodAccess_value;
    }

    private boolean isMethodAccess_compute() {  return true;  }

    // Declared in SyntacticClassification.jrag at line 113
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in Generics.jrag at line 12
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

    private TypeDecl type_compute() {
    if(getNumArg() == 0 && name().equals("getClass") && decl().hostType().isObject()) {
      TypeDecl bound = isQualified() ? qualifier().type() : hostType();
      ArrayList args = new ArrayList();
      args.add(bound.erasure().asWildcardExtends());
      return ((GenericClassDecl)lookupType("java.lang", "Class")).lookupParTypeDecl(args);
    }
    else
      return refined_TypeAnalysis_MethodAccess_type();
  }

    // Declared in MethodSignature.jrag at line 181
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableBySubtyping(MethodDecl m) {
        ASTNode$State state = state();
        boolean applicableBySubtyping_MethodDecl_value = applicableBySubtyping_compute(m);
        return applicableBySubtyping_MethodDecl_value;
    }

    private boolean applicableBySubtyping_compute(MethodDecl m) {
    if(m.getNumParameter() != getNumArg())
      return false;
    for(int i = 0; i < m.getNumParameter(); i++)
      if(!getArg(i).type().instanceOf(m.getParameter(i).type()))
        return false;
    return true;
  }

    // Declared in MethodSignature.jrag at line 201
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableByMethodInvocationConversion(MethodDecl m) {
        ASTNode$State state = state();
        boolean applicableByMethodInvocationConversion_MethodDecl_value = applicableByMethodInvocationConversion_compute(m);
        return applicableByMethodInvocationConversion_MethodDecl_value;
    }

    private boolean applicableByMethodInvocationConversion_compute(MethodDecl m) {
    if(m.getNumParameter() != getNumArg())
      return false;
    for(int i = 0; i < m.getNumParameter(); i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.getParameter(i).type()))
        return false;
    return true;
  }

    // Declared in MethodSignature.jrag at line 221
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableVariableArity(MethodDecl m) {
        ASTNode$State state = state();
        boolean applicableVariableArity_MethodDecl_value = applicableVariableArity_compute(m);
        return applicableVariableArity_MethodDecl_value;
    }

    private boolean applicableVariableArity_compute(MethodDecl m) {
    for(int i = 0; i < m.getNumParameter() - 1; i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.getParameter(i).type()))
        return false;
    for(int i = m.getNumParameter() - 1; i < getNumArg(); i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.lastParameter().type().componentType()))
        return false;
    return true;
  }

    // Declared in MethodSignature.jrag at line 262
 @SuppressWarnings({"unchecked", "cast"})     public boolean potentiallyApplicable(MethodDecl m) {
        ASTNode$State state = state();
        boolean potentiallyApplicable_MethodDecl_value = potentiallyApplicable_compute(m);
        return potentiallyApplicable_MethodDecl_value;
    }

    private boolean potentiallyApplicable_compute(MethodDecl m) {
    if(!m.name().equals(name()))
      return false;
    if(!m.accessibleFrom(hostType()))
      return false;
    if(m.isVariableArity() && !(arity() >= m.arity()-1))
      return false;
    if(!m.isVariableArity() && !(m.arity() == arity()))
      return false;
    if(m instanceof GenericMethodDecl) {
      GenericMethodDecl gm = (GenericMethodDecl)m;
      ArrayList list = typeArguments(m);
      if(list.size() != 0) {
        if(gm.getNumTypeParameter() != list.size())
          return false;
        for(int i = 0; i < gm.getNumTypeParameter(); i++)
          if(!((TypeDecl)list.get(i)).subtype(gm.original().getTypeParameter(i)))
            return false;
      }
    }
    return true;
  }

    // Declared in MethodSignature.jrag at line 285
 @SuppressWarnings({"unchecked", "cast"})     public int arity() {
        ASTNode$State state = state();
        int arity_value = arity_compute();
        return arity_value;
    }

    private int arity_compute() {  return getNumArg();  }

    protected java.util.Map typeArguments_MethodDecl_values;
    // Declared in MethodSignature.jrag at line 287
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList typeArguments(MethodDecl m) {
        Object _parameters = m;
if(typeArguments_MethodDecl_values == null) typeArguments_MethodDecl_values = new java.util.HashMap(4);
        if(typeArguments_MethodDecl_values.containsKey(_parameters)) {
            return (ArrayList)typeArguments_MethodDecl_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        ArrayList typeArguments_MethodDecl_value = typeArguments_compute(m);
        if(isFinal && num == state().boundariesCrossed)
            typeArguments_MethodDecl_values.put(_parameters, typeArguments_MethodDecl_value);
        return typeArguments_MethodDecl_value;
    }

    private ArrayList typeArguments_compute(MethodDecl m) {
    ArrayList typeArguments = new ArrayList();
    if(m instanceof GenericMethodDecl) {
      GenericMethodDecl g = (GenericMethodDecl)m;
      Collection arguments = computeConstraints(g);
      if(arguments.isEmpty())
        return typeArguments;
      int i = 0;
      for(Iterator iter = arguments.iterator(); iter.hasNext(); i++) {
        TypeDecl typeDecl = (TypeDecl)iter.next();
        if(typeDecl == null) {
          TypeVariable v = g.original().getTypeParameter(i);
          if(v.getNumTypeBound() == 0)
            typeDecl = typeObject();
          else if(v.getNumTypeBound() == 1)
            typeDecl = v.getTypeBound(0).type();
          else
            typeDecl = v.lubType();
        }
        typeArguments.add(typeDecl);
      }
    }
    return typeArguments;
  }

    // Declared in VariableArityParameters.jrag at line 40
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

    // Declared in DataFlow.jrag at line 138
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayDefine(Location l) {
        ASTNode$State state = state();
        boolean mayDefine_Location_value = mayDefine_compute(l);
        return mayDefine_Location_value;
    }

    private boolean mayDefine_compute(Location l) {  return !decl().isConstantMethod() && refined_Alias_MethodAccess_mayDefine_Location(l);  }

    // Declared in Alias.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean mustDefine(Location l) {
        ASTNode$State state = state();
        boolean mustDefine_Location_value = mustDefine_compute(l);
        return mustDefine_Location_value;
    }

    private boolean mustDefine_compute(Location l) {  return false;  }

    // Declared in ControlFlowGraph.jrag at line 241
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getNumArg() == 0 ? this : getArg(0).first();  }

    // Declared in ControlFlowGraph.jrag at line 246
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

    // Declared in Exceptions.jrag at line 32
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

    // Declared in ReachingDefinitions.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return mayDefine(l);  }

    // Declared in ReachingDefinitions.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return mustDefine(l);  }

    // Declared in ExprExt.jrag at line 7
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatementExpression() {
        ASTNode$State state = state();
        boolean isStatementExpression_value = isStatementExpression_compute();
        return isStatementExpression_value;
    }

    private boolean isStatementExpression_compute() {  return true;  }

    // Declared in ExprExt.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {  return decl().isConstantMethod() || super.isFlowInvariant();  }

    // Declared in MethodAccessExt.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperCall() {
        ASTNode$State state = state();
        boolean isSuperCall_value = isSuperCall_compute();
        return isSuperCall_value;
    }

    private boolean isSuperCall_compute() {  return this.isQualified() && this.qualifier().isSuperAccess();  }

    // Declared in MethodAccessExt.jrag at line 3
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMonoCall() {
        ASTNode$State state = state();
        boolean isMonoCall_value = isMonoCall_compute();
        return isMonoCall_value;
    }

    private boolean isMonoCall_compute() {  return decl().isStatic() || decl().isPrivate() || isSuperCall();  }

    // Declared in MethodAccessExt.jrag at line 4
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPolyCall() {
        ASTNode$State state = state();
        boolean isPolyCall_value = isPolyCall_compute();
        return isPolyCall_value;
    }

    private boolean isPolyCall_compute() {  return !isMonoCall();  }

    protected boolean possibleTargets_computed = false;
    protected Collection<MethodDecl> possibleTargets_value;
    // Declared in MethodAccessExt.jrag at line 6
 @SuppressWarnings({"unchecked", "cast"})     public Collection<MethodDecl> possibleTargets() {
        if(possibleTargets_computed) {
            return possibleTargets_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        possibleTargets_value = possibleTargets_compute();
        if(isFinal && num == state().boundariesCrossed)
            possibleTargets_computed = true;
        return possibleTargets_value;
    }

    private Collection<MethodDecl> possibleTargets_compute() {
		HashSet<MethodDecl> res = new HashSet<MethodDecl>();
		res.add(decl());
		if(isMonoCall())
			return res;
		int sz;
		do {
			sz = res.size();
			HashSet<MethodDecl> next_layer = new HashSet<MethodDecl>();
			for(MethodDecl md : res)
				next_layer.addAll(md.overriders());
			res.addAll(next_layer);
		} while(res.size() != sz);
		return res;
	}

    protected boolean getUniqueTarget_computed = false;
    protected MethodDecl getUniqueTarget_value;
    // Declared in MethodAccessExt.jrag at line 22
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl getUniqueTarget() {
        if(getUniqueTarget_computed) {
            return getUniqueTarget_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        getUniqueTarget_value = getUniqueTarget_compute();
        if(isFinal && num == state().boundariesCrossed)
            getUniqueTarget_computed = true;
        return getUniqueTarget_value;
    }

    private MethodDecl getUniqueTarget_compute() {
		MethodDecl md = decl();
		if(isPolyCall() && !md.overriders().isEmpty())
			return null;
		return decl();
	}

    // Declared in Purity.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return false;  }

    // Declared in DataFlow.jrag at line 109
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

    private Location getLocation_compute() {  return decl().isConstantMethod() ? null : decl();  }

    // Declared in MakeConversionExplicit.jrag at line 10
 @SuppressWarnings({"unchecked", "cast"})     public boolean shouldMakeConversionExplicit(TypeDecl destType) {
        Object _parameters = destType;
if(shouldMakeConversionExplicit_TypeDecl_values == null) shouldMakeConversionExplicit_TypeDecl_values = new java.util.HashMap(4);
        if(shouldMakeConversionExplicit_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)shouldMakeConversionExplicit_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean shouldMakeConversionExplicit_TypeDecl_value = shouldMakeConversionExplicit_compute(destType);
        if(isFinal && num == state().boundariesCrossed)
            shouldMakeConversionExplicit_TypeDecl_values.put(_parameters, Boolean.valueOf(shouldMakeConversionExplicit_TypeDecl_value));
        return shouldMakeConversionExplicit_TypeDecl_value;
    }

    private boolean shouldMakeConversionExplicit_compute(TypeDecl destType) {
		// we need to handle the case where the result type of a method is inferred from a surrounding assignment conversion
		if(!(this instanceof ParMethodAccess) && decl() instanceof ParMethodDecl) {
			ParMethodDecl pmd = (ParMethodDecl)decl();
			// TODO: this will insert spurious conversions in cases where the result type can be inferred from the arguments
			return pmd.genericMethodDecl().type().isTypeVariable();
		}
		return super.shouldMakeConversionExplicit(destType);
	}

    // Declared in ExceptionHandling.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        ASTNode$State state = state();
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        return handlesException_TypeDecl_value;
    }

    // Declared in LookupMethod.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl unknownMethod() {
        ASTNode$State state = state();
        MethodDecl unknownMethod_value = getParent().Define_MethodDecl_unknownMethod(this, null);
        return unknownMethod_value;
    }

    // Declared in TypeHierarchyCheck.jrag at line 123
 @SuppressWarnings({"unchecked", "cast"})     public boolean inExplicitConstructorInvocation() {
        ASTNode$State state = state();
        boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
        return inExplicitConstructorInvocation_value;
    }

    // Declared in GenericMethodsInference.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeObject() {
        ASTNode$State state = state();
        TypeDecl typeObject_value = getParent().Define_TypeDecl_typeObject(this, null);
        return typeObject_value;
    }

    // Declared in LockedMethodAccess.jrag at line 110
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl enclosingType() {
        ASTNode$State state = state();
        TypeDecl enclosingType_value = getParent().Define_TypeDecl_enclosingType(this, null);
        return enclosingType_value;
    }

    // Declared in DefiniteAssignment.jrag at line 413
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return computeDAbefore(i, v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in LookupMethod.jrag at line 28
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupMethod(name);
        }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 87
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().hasPackage(packageName);
        }
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 165
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupType(name);
        }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 130
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 120
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.EXPRESSION_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 17
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return unqualifiedScope().methodHost();
        }
        return getParent().Define_String_methodHost(this, caller);
    }

    // Declared in GenericMethodsInference.jrag at line 41
    public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return typeObject();
        }
        return getParent().Define_TypeDecl_assignConvertedType(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 242
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return SmallSet.<CFGNode>singleton(i + 1 < getNumArg() ? getArg(i + 1).first() : this);
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 362
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenTrue(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getArg(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 363
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__followingWhenFalse(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return getArg(i).following();
        }
        return getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, caller);
    }

    // Declared in ExprExt.jrag at line 36
    public boolean Define_boolean_inInextractiblePosition(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_inInextractiblePosition(this, caller);
    }

    // Declared in Precedence.jrag at line 62
    public int Define_int_maxPrecedence(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return Integer.MAX_VALUE;
        }
        return getParent().Define_int_maxPrecedence(this, caller);
    }

    // Declared in AccessMethod.jrag at line 74
    public MethodAccessInfo Define_MethodAccessInfo_accessMethod(ASTNode caller, ASTNode child, MethodDecl md) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().accessMethod(md);
        }
        return getParent().Define_MethodAccessInfo_accessMethod(this, caller, md);
    }

    // Declared in AccessVariable.jrag at line 250
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

    protected void collect_contributors_GenericMethodDecl_parUses() {
        // Declared in MethodExt.jrag at line 94
        if(decl() instanceof ParMethodDecl) {
        {
            GenericMethodDecl ref = (GenericMethodDecl)(((ParMethodDecl)decl()).sourceMethodDecl());
            if(ref != null)
                ref.GenericMethodDecl_parUses_contributors().add(this);
        }
        }
        super.collect_contributors_GenericMethodDecl_parUses();
    }
    protected void collect_contributors_MethodDecl_polyUses() {
        // Declared in Uses.jrag at line 34
        for(java.util.Iterator iter = (possibleTargets()).iterator(); iter.hasNext(); ) {
            MethodDecl ref = (MethodDecl)iter.next();
            if(ref != null)
            ref.MethodDecl_polyUses_contributors().add(this);
        }
        super.collect_contributors_MethodDecl_polyUses();
    }
    protected void collect_contributors_MethodDecl_uses() {
        // Declared in Uses.jrag at line 39
        {
            MethodDecl ref = (MethodDecl)(decl());
            if(ref != null)
                ref.MethodDecl_uses_contributors().add(this);
        }
        super.collect_contributors_MethodDecl_uses();
    }
    protected void contributeTo_GenericMethodDecl_GenericMethodDecl_parUses(Collection<MethodAccess> collection) {
        super.contributeTo_GenericMethodDecl_GenericMethodDecl_parUses(collection);
        if(decl() instanceof ParMethodDecl)
            collection.add(this);
    }

    protected void contributeTo_MethodDecl_MethodDecl_polyUses(Collection<MethodAccess> collection) {
        super.contributeTo_MethodDecl_MethodDecl_polyUses(collection);
        collection.add(this);
    }

    protected void contributeTo_MethodDecl_MethodDecl_uses(Collection<MethodAccess> collection) {
        super.contributeTo_MethodDecl_MethodDecl_uses(collection);
        collection.add(this);
    }

}
