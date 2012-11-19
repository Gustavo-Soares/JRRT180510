
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public abstract class Expr extends ASTNode<ASTNode> implements Cloneable, CFGNode {
    public void flushCache() {
        super.flushCache();
        isArrayInit_computed = false;
        shouldMakeConversionExplicit_TypeDecl_values = null;
        succ_computed = false;
        succ_value = null;
        reachingDefinitionsFor_Location_values = null;
        forward_cdep_computed = false;
        forward_cdep_value = null;
        backward_cdep_computed = false;
        backward_cdep_value = null;
        collect_BackwardEdge_CFGNode_values = null;
        collect_ForwardEdge_CFGNode_values = null;
        uncheckedExnTarget_computed = false;
        uncheckedExnTarget_value = null;
        following_computed = false;
        following_value = null;
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        CFGNode_collPred_computed = false;
        CFGNode_collPred_value = null;
    CFGNode_collPred_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Expr clone() throws CloneNotSupportedException {
        Expr node = (Expr)super.clone();
        node.isArrayInit_computed = false;
        node.shouldMakeConversionExplicit_TypeDecl_values = null;
        node.succ_computed = false;
        node.succ_value = null;
        node.reachingDefinitionsFor_Location_values = null;
        node.forward_cdep_computed = false;
        node.forward_cdep_value = null;
        node.backward_cdep_computed = false;
        node.backward_cdep_value = null;
        node.collect_BackwardEdge_CFGNode_values = null;
        node.collect_ForwardEdge_CFGNode_values = null;
        node.uncheckedExnTarget_computed = false;
        node.uncheckedExnTarget_value = null;
        node.following_computed = false;
        node.following_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in LookupType.jrag at line 373

    
  public SimpleSet keepAccessibleTypes(SimpleSet oldSet) {
    SimpleSet newSet = SimpleSet.emptySet;
    TypeDecl hostType = hostType();
    for(Iterator iter = oldSet.iterator(); iter.hasNext(); ) {
      TypeDecl t = (TypeDecl)iter.next();
      if((hostType != null && t.accessibleFrom(hostType)) || (hostType == null && t.accessibleFromPackage(hostPackage())))
        newSet = newSet.add(t);
    }
    return newSet;
  }

    // Declared in LookupVariable.jrag at line 164


  // remove fields that are not accessible when using this Expr as qualifier
  public SimpleSet keepAccessibleFields(SimpleSet oldSet) {
    SimpleSet newSet = SimpleSet.emptySet;
    for(Iterator iter = oldSet.iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(v instanceof FieldDeclaration) {
        FieldDeclaration f = (FieldDeclaration)v;
        if(mayAccess(f))
          newSet = newSet.add(f);
      }
    }
    return newSet;
  }

    // Declared in LookupVariable.jrag at line 187


  public boolean mayAccess(FieldDeclaration f) {
    if(f.isPublic()) 
      return true;
    else if(f.isProtected()) {
      if(f.hostPackage().equals(hostPackage()))
        return true;
      TypeDecl C = f.hostType();
      TypeDecl S = hostType().subclassWithinBody(C);
      TypeDecl Q = type();
      if(S == null)
        return false;
      if(f.isInstanceVariable() && !isSuperAccess())
        return Q.instanceOf(S);
      return true;
    }
    else if(f.isPrivate())
      return f.hostType().topLevelType() == hostType().topLevelType();
    else
      return f.hostPackage().equals(hostType().hostPackage());
  }

    // Declared in ResolveAmbiguousNames.jrag at line 106


  public Dot qualifiesAccess(Access access) {
    Dot dot = new Dot(this, access);
    dot.setStart(this.getStart());
    dot.setEnd(access.getEnd());
    return dot;
  }

    // Declared in MethodSignature.jrag at line 91


  protected SimpleSet chooseConstructor(Collection constructors, List argList) {
    SimpleSet potentiallyApplicable = SimpleSet.emptySet;
    // select potentially applicable constructors
    for(Iterator iter = constructors.iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(decl.potentiallyApplicable(argList) && decl.accessibleFrom(hostType()))
        potentiallyApplicable = potentiallyApplicable.add(decl);
    }
    // first phase
    SimpleSet maxSpecific = SimpleSet.emptySet;
    for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(decl.applicableBySubtyping(argList))
        maxSpecific = mostSpecific(maxSpecific, decl);
    }

    // second phase
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        ConstructorDecl decl = (ConstructorDecl)iter.next();
        if(decl.applicableByMethodInvocationConversion(argList))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }

    // third phase
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        ConstructorDecl decl = (ConstructorDecl)iter.next();
        if(decl.isVariableArity() && decl.applicableVariableArity(argList))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }
    return maxSpecific;
  }

    // Declared in MethodSignature.jrag at line 128



  protected static SimpleSet mostSpecific(SimpleSet maxSpecific, ConstructorDecl decl) {
    if(maxSpecific.isEmpty())
      maxSpecific = maxSpecific.add(decl);
    else {
      if(decl.moreSpecificThan((ConstructorDecl)maxSpecific.iterator().next()))
        maxSpecific = SimpleSet.emptySet.add(decl);
      else if(!((ConstructorDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
        maxSpecific = maxSpecific.add(decl);
    }
    return maxSpecific;
  }

    // Declared in BlockExt.jrag at line 25

	
	public Block enclosingBlock() {
		return enclosingStmt().enclosingBlock();
	}

    // Declared in TypeVariableExt.jrag at line 112

	
	// JastAddJ represents type variable captures as bound accesses to type variables of the generic type
	// this solution is a bit hackish, so let's solve it in a hackish way ;-)
	public boolean isInaccessibleTypeVariable(TypeDecl td) {
		if(!td.isTypeVariable())
			return false;
		return lookupType(td.name()) != td;
	}

    // Declared in IntroduceParameter.jrag at line 2

	public void introduceParameter(String name) {
		if(inextractible() || !isFlowInvariant())
			throw new RefactoringException("expression cannot be extracted");
		if(!(hostBodyDecl() instanceof MethodDecl))
			throw new RefactoringException("cannot introduce parameter for non-method");
		MethodDecl md = (MethodDecl)hostBodyDecl();
		if(md.relatives().size() != 1)
			throw new RefactoringException("cannot introduce parameter in methods with relatives");
		programRoot().lockMethodNames(md.name());
		TypeDecl td = effectiveType();
		int idx = md.isVariableArity() ? md.getNumParameter()-1 : md.getNumParameter();
		if(!md.parameterDeclaration(name).isEmpty())
			throw new RefactoringException("parameter of same name exists");
		ParameterDeclaration pd = new ParameterDeclaration(td.createLockedAccess(), name);
		md.getParameterList().insertChild(pd, idx);
		for(MethodAccess ma : md.uses())
			ma.getArgList().insertChild((Expr)lockedCopy(), idx);
		replaceWith(pd.createLockedAccess());
	}

    // Declared in IntroduceParameter.jrag at line 22

	
	public void doIntroduceParameter(String name) {
		Program root = programRoot();
		introduceParameter(name);
		root.eliminate(LOCKED_NAMES);
	}

    // Declared in ExtractAssignment.jrag at line 3
	
	// extract an expression as an assignment to a variable
	public VariableDeclaration extractAssignment(VariableDeclaration v) {
		if(inextractible())
			throw new RefactoringException("expression is not extractible");
		if(getParent() instanceof ExprStmt) {
			ExprStmt parent = (ExprStmt)getParent();
			parent.setExpr(new AssignSimpleExpr(v.createLockedAccess(), this));
		} else {
			Block block = enclosingBlock();
			if(block == null)
				throw new RefactoringException("cannot extract assignment here");
			lockAllNames();
			lockDataFlow();
			block.lockSyncDependencies();
			VarAccess vacc = v.createLockedAccess();
			replaceWith(vacc);
			vacc.insertStmtBefore(AssignExpr.asStmt(v.createLockedAccess(), this));
			block.hostType().flushCaches();
			unlockDataFlow();
			block.unlockSyncDependencies();
		}
		return v;
	}

    // Declared in ExtractConstant.jrag at line 2

	public void extractConstant(String name) {
		if(inextractible())
			throw new RefactoringException("not extractible");
		lockAllNames();
		lockDataFlow();
		TypeDecl host = hostType();
		Opt<Expr> fd_init = new Opt<Expr>();
		Modifiers mods = new Modifiers("static", "final");
		mods.addModifier(hostType().isInterfaceDecl() ? "public" : "private");
		FieldDeclaration fd = new FieldDeclaration(mods, effectiveType().createLockedAccess(), name, fd_init);
		replaceWith(fd.createLockedAccess());
		fd_init.setChild(this, 0);
		host.insertField(fd);
		host.flushCaches();
	}

    // Declared in ExtractConstant.jrag at line 18

	
	public void doExtractConstant(String name) {
		Program root = programRoot();
		extractConstant(name);
		root.eliminate(LOCKED_NAMES, LOCKED_DATAFLOW);
	}

    // Declared in ExtractTemp.jrag at line 2

	public VariableDeclaration extractTemp(String name, boolean makeFinal) {
		TypeDecl type = effectiveType();
		return insertUnusedLocal(new VariableDeclaration(type.createLockedAccess(), name)).
			   extractAssignment(this).merge().makeFinal(makeFinal);
	}

    // Declared in ExtractTemp.jrag at line 8

	
	public VariableDeclaration extractTemp(String name) {
		return extractTemp(name, false);
	}

    // Declared in ExtractTemp.jrag at line 13

	
    // stand-alone refactoring
    public VariableDeclaration doExtract(String name, boolean makeFinal) {
    	Program root = programRoot();
    	VariableDeclaration decl = extractTemp(name, makeFinal);
    	root.eliminate(LOCKED_DATAFLOW, LOCKED_NAMES);
    	return decl;
    }

    // Declared in ExtractTemp.jrag at line 20

    
    public VariableDeclaration doExtract(String name) {
    	return doExtract(name, false);
    }

    // Declared in IntroduceUnusedLocal.jrag at line 17

	
	public VariableDeclaration insertUnusedLocal(VariableDeclaration decl) {
		Stmt s = enclosingStmt();
		if(s == null)
			throw new RefactoringException("no surrounding statement");
		return s.insertUnusedLocal(decl);
	}

    // Declared in ArrayInits.jrag at line 5

	  
	  public Expr wrapArrayInit() {
		  if(isArrayInit())
			  return new ArrayCreationExpr(type().createLockedAccess(), new Opt(this));
		  return this;
	  }

    // Declared in MakeConversionExplicit.jrag at line 2
	
	public Expr makeConversionExplicit(TypeDecl destType) {
		if(shouldMakeConversionExplicit(destType))
			return createConversion(destType, this);
		return this;
	}

    // Declared in With.jrag at line 113

	
	public void eliminateWith(List<Access> qualifiers, TypeDecl hostType) {
		if(isThisAccess()) {
			for(int i=qualifiers.getNumChild()-1;i>=0;--i) {
				Access qual = qualifiers.getChild(i);
				TypeDecl qual_type = qual.type().isParameterizedType() ? ((ParTypeDecl)qual.type()).genericDecl() : qual.type();
				if(this.type().equals(qual_type)) {
					if(!qual.isThisAccess())
						replaceWith(qual.fullCopy());
					return;
				}
			}
			throw new RefactoringException("cannot eliminate this");
		} else if(isSuperAccess()) {
			for(int i=qualifiers.getNumChild()-1;i>=0;--i) {
				Access qual = qualifiers.getChild(i);
				TypeDecl qual_type = qual.type().isParameterizedType() ? ((ParTypeDecl)qual.type()).genericDecl() : qual.type();
				if(!qual_type.isClassDecl() || !((ClassDecl)qual_type).hasSuperclass())
					continue;
				ClassDecl cd = (ClassDecl)qual_type;
				if(this.type().equals(cd.superclass())) {
					if(!qual.isThisAccess()) {
						if(((Access)this).qualifiesMethodAccess())
							throw new RefactoringException("cannot eliminate with on super method calls");
						replaceWith(new ParExpr(new CastExpr(cd.superclass().createLockedAccess(),
								                             (Expr)qual.fullCopy())));
					}
					return;
				}
			}
			throw new RefactoringException("cannot eliminate super");
		} else {
			super.eliminateWith(qualifiers, hostType);
		}
	}

    // Declared in Lookup.jrag at line 3

	// simulates the effect of plugging in name as a ParseName, disambiguating and then taking decl()
	public SimpleSet lookupName(String name) {
		NameType nt = this.nameType();
		if(nt == NameType.PACKAGE_NAME) {
			return lookupPackage(name);
		} else if(nt == NameType.TYPE_NAME) {
			return lookupType(name);
		} else if(nt == NameType.PACKAGE_OR_TYPE_NAME) {
			if(!lookupType(name).isEmpty())
				return lookupType(name);
			return lookupPackage(name);
		} else if(nt == NameType.AMBIGUOUS_NAME) {
			if(!lookupVariable(name).isEmpty())
				return lookupVariable(name);
			if(!lookupType(name).isEmpty())
				return lookupType(name);
			return lookupPackage(name);
		} else if(nt == NameType.METHOD_NAME) {
			return new SimpleSet.SimpleSetImpl(lookupMethod(name));
		} else if(nt == NameType.EXPRESSION_NAME) {
			return lookupVariable(name);
		}
		throw new Error("unsupported name type");
	}

    // Declared in Testing.jrag at line 314

	
	public void unparenthesise() {
		if(getParent() instanceof ParExpr) {
			getParent().replaceWith(this);
			unparenthesise();
		}
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 97

    public Expr() {
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

    // Declared in TypeAnalysis.jrag at line 276
 @SuppressWarnings({"unchecked", "cast"})     public abstract TypeDecl type();
    // Declared in Precedence.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public abstract int precedence();
    // Declared in ConstantExpression.jrag at line 98
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        Constant constant_value = constant_compute();
        return constant_value;
    }

    private Constant constant_compute() {
    throw new UnsupportedOperationException("ConstantExpression operation constant" +
      " not supported for type " + getClass().getName()); 
  }

    // Declared in ConstantExpression.jrag at line 241
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPositive() {
        ASTNode$State state = state();
        boolean isPositive_value = isPositive_compute();
        return isPositive_value;
    }

    private boolean isPositive_compute() {  return false;  }

    // Declared in ConstantExpression.jrag at line 454
 @SuppressWarnings({"unchecked", "cast"})     public boolean representableIn(TypeDecl t) {
        ASTNode$State state = state();
        boolean representableIn_TypeDecl_value = representableIn_compute(t);
        return representableIn_TypeDecl_value;
    }

    private boolean representableIn_compute(TypeDecl t) {	
  	if (!type().isByte() && !type().isChar() && !type().isShort() && !type().isInt()) {
  		return false;
  	}
  	if (t.isByte())
  		return constant().intValue() >= Byte.MIN_VALUE && constant().intValue() <= Byte.MAX_VALUE;
  	if (t.isChar())
  		return constant().intValue() >= Character.MIN_VALUE && constant().intValue() <= Character.MAX_VALUE;
  	if (t.isShort())
  		return constant().intValue() >= Short.MIN_VALUE && constant().intValue() <= Short.MAX_VALUE;
    if(t.isInt()) 
      return constant().intValue() >= Integer.MIN_VALUE && constant().intValue() <= Integer.MAX_VALUE;
	  return false;
  }

    // Declared in ConstantExpression.jrag at line 482
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        boolean isConstant_value = isConstant_compute();
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return false;  }

    // Declared in ConstantExpression.jrag at line 511
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTrue() {
        ASTNode$State state = state();
        boolean isTrue_value = isTrue_compute();
        return isTrue_value;
    }

    private boolean isTrue_compute() {  return isConstant() && type() instanceof BooleanType && constant().booleanValue();  }

    // Declared in ConstantExpression.jrag at line 512
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFalse() {
        ASTNode$State state = state();
        boolean isFalse_value = isFalse_compute();
        return isFalse_value;
    }

    private boolean isFalse_compute() {  return isConstant() && type() instanceof BooleanType && !constant().booleanValue();  }

    // Declared in DefiniteAssignment.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        Variable varDecl_value = varDecl_compute();
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return null;  }

    // Declared in DefiniteAssignment.jrag at line 340
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFalse(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
        return isDAafterFalse_Variable_value;
    }

    private boolean isDAafterFalse_compute(Variable v) {  return isTrue() || isDAbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 342
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterTrue(Variable v) {
        ASTNode$State state = state();
        boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
        return isDAafterTrue_Variable_value;
    }

    private boolean isDAafterTrue_compute(Variable v) {  return isFalse() || isDAbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 345
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        ASTNode$State state = state();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return (isDAafterFalse(v) && isDAafterTrue(v)) || isDAbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 782
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFalse(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterFalse_Variable_value = isDUafterFalse_compute(v);
        return isDUafterFalse_Variable_value;
    }

    private boolean isDUafterFalse_compute(Variable v) {
    if(isTrue())
      return true;
    return isDUbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 788
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterTrue(Variable v) {
        ASTNode$State state = state();
        boolean isDUafterTrue_Variable_value = isDUafterTrue_compute(v);
        return isDUafterTrue_Variable_value;
    }

    private boolean isDUafterTrue_compute(Variable v) {
    if(isFalse())
      return true;
    return isDUbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 798
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        ASTNode$State state = state();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return (isDUafterFalse(v) && isDUafterTrue(v)) || isDUbefore(v);  }

    // Declared in LookupConstructor.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet mostSpecificConstructor(Collection constructors) {
        ASTNode$State state = state();
        SimpleSet mostSpecificConstructor_Collection_value = mostSpecificConstructor_compute(constructors);
        return mostSpecificConstructor_Collection_value;
    }

    private SimpleSet mostSpecificConstructor_compute(Collection constructors) {
    SimpleSet maxSpecific = SimpleSet.emptySet;
    for(Iterator iter = constructors.iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(applicableAndAccessible(decl)) {
        if(maxSpecific.isEmpty())
          maxSpecific = maxSpecific.add(decl);
        else {
          if(decl.moreSpecificThan((ConstructorDecl)maxSpecific.iterator().next()))
            maxSpecific = SimpleSet.emptySet.add(decl);
          else if(!((ConstructorDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
            maxSpecific = maxSpecific.add(decl);
        }
      }
    }
    return maxSpecific;
  }

    // Declared in LookupConstructor.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableAndAccessible(ConstructorDecl decl) {
        ASTNode$State state = state();
        boolean applicableAndAccessible_ConstructorDecl_value = applicableAndAccessible_compute(decl);
        return applicableAndAccessible_ConstructorDecl_value;
    }

    private boolean applicableAndAccessible_compute(ConstructorDecl decl) {  return false;  }

    // Declared in LookupType.jrag at line 83
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasQualifiedPackage(String packageName) {
        ASTNode$State state = state();
        boolean hasQualifiedPackage_String_value = hasQualifiedPackage_compute(packageName);
        return hasQualifiedPackage_String_value;
    }

    private boolean hasQualifiedPackage_compute(String packageName) {  return false;  }

    // Declared in LookupType.jrag at line 342
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupType(String name) {
        ASTNode$State state = state();
        SimpleSet qualifiedLookupType_String_value = qualifiedLookupType_compute(name);
        return qualifiedLookupType_String_value;
    }

    private SimpleSet qualifiedLookupType_compute(String name) {  return keepAccessibleTypes(type().memberTypes(name));  }

    // Declared in LookupVariable.jrag at line 146
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet qualifiedLookupVariable_String_value = qualifiedLookupVariable_compute(name);
        return qualifiedLookupVariable_String_value;
    }

    private SimpleSet qualifiedLookupVariable_compute(String name) {
    if(type().accessibleFrom(hostType()))
      return keepAccessibleFields(type().memberFields(name));
    return SimpleSet.emptySet;
  }

    // Declared in QualifiedNames.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public String packageName() {
        ASTNode$State state = state();
        String packageName_value = packageName_compute();
        return packageName_value;
    }

    private String packageName_compute() {  return "";  }

    // Declared in QualifiedNames.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        ASTNode$State state = state();
        String typeName_value = typeName_compute();
        return typeName_value;
    }

    private String typeName_compute() {  return "";  }

    // Declared in ResolveAmbiguousNames.jrag at line 13
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        ASTNode$State state = state();
        boolean isTypeAccess_value = isTypeAccess_compute();
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodAccess() {
        ASTNode$State state = state();
        boolean isMethodAccess_value = isMethodAccess_compute();
        return isMethodAccess_value;
    }

    private boolean isMethodAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccess() {
        ASTNode$State state = state();
        boolean isFieldAccess_value = isFieldAccess_compute();
        return isFieldAccess_value;
    }

    private boolean isFieldAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        boolean isSuperAccess_value = isSuperAccess_compute();
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        boolean isThisAccess_value = isThisAccess_compute();
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPackageAccess() {
        ASTNode$State state = state();
        boolean isPackageAccess_value = isPackageAccess_compute();
        return isPackageAccess_value;
    }

    private boolean isPackageAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 41
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayAccess() {
        ASTNode$State state = state();
        boolean isArrayAccess_value = isArrayAccess_compute();
        return isArrayAccess_value;
    }

    private boolean isArrayAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassAccess() {
        ASTNode$State state = state();
        boolean isClassAccess_value = isClassAccess_compute();
        return isClassAccess_value;
    }

    private boolean isClassAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperConstructorAccess() {
        ASTNode$State state = state();
        boolean isSuperConstructorAccess_value = isSuperConstructorAccess_compute();
        return isSuperConstructorAccess_value;
    }

    private boolean isSuperConstructorAccess_compute() {  return false;  }

    // Declared in ResolveAmbiguousNames.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLeftChildOfDot() {
        ASTNode$State state = state();
        boolean isLeftChildOfDot_value = isLeftChildOfDot_compute();
        return isLeftChildOfDot_value;
    }

    private boolean isLeftChildOfDot_compute() {  return hasParentDot() && parentDot().getLeft() == this;  }

    // Declared in ResolveAmbiguousNames.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean isRightChildOfDot() {
        ASTNode$State state = state();
        boolean isRightChildOfDot_value = isRightChildOfDot_compute();
        return isRightChildOfDot_value;
    }

    private boolean isRightChildOfDot_compute() {  return hasParentDot() && parentDot().getRight() == this;  }

    // Declared in ResolveAmbiguousNames.jrag at line 69
 @SuppressWarnings({"unchecked", "cast"})     public AbstractDot parentDot() {
        ASTNode$State state = state();
        AbstractDot parentDot_value = parentDot_compute();
        return parentDot_value;
    }

    private AbstractDot parentDot_compute() {  return getParent() instanceof AbstractDot ? (AbstractDot)getParent() : null;  }

    // Declared in ResolveAmbiguousNames.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasParentDot() {
        ASTNode$State state = state();
        boolean hasParentDot_value = hasParentDot_compute();
        return hasParentDot_value;
    }

    private boolean hasParentDot_compute() {  return parentDot() != null;  }

    // Declared in ResolveAmbiguousNames.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public Access nextAccess() {
        ASTNode$State state = state();
        Access nextAccess_value = nextAccess_compute();
        return nextAccess_value;
    }

    private Access nextAccess_compute() {  return parentDot().nextAccess();  }

    // Declared in ResolveAmbiguousNames.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasNextAccess() {
        ASTNode$State state = state();
        boolean hasNextAccess_value = hasNextAccess_compute();
        return hasNextAccess_value;
    }

    private boolean hasNextAccess_compute() {  return isLeftChildOfDot();  }

    // Declared in TypeAnalysis.jrag at line 504
 @SuppressWarnings({"unchecked", "cast"})     public Stmt enclosingStmt() {
        ASTNode$State state = state();
        Stmt enclosingStmt_value = enclosingStmt_compute();
        return enclosingStmt_value;
    }

    private Stmt enclosingStmt_compute() {
    ASTNode node = this;
    while(node != null && !(node instanceof Stmt))
      node = node.getParent();
    return (Stmt)node;
  }

    // Declared in TypeCheck.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return false;  }

    // Declared in TypeHierarchyCheck.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public boolean isUnknown() {
        ASTNode$State state = state();
        boolean isUnknown_value = isUnknown_compute();
        return isUnknown_value;
    }

    private boolean isUnknown_compute() {  return type().isUnknown();  }

    // Declared in TypeHierarchyCheck.jrag at line 150
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return false;  }

    // Declared in ReachingDefinitions.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return false;  }

    // Declared in ReachingDefinitions.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return false;  }

    // Declared in ExprExt.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatementExpression() {
        ASTNode$State state = state();
        boolean isStatementExpression_value = isStatementExpression_compute();
        return isStatementExpression_value;
    }

    private boolean isStatementExpression_compute() {  return false;  }

    // Declared in ExprExt.jrag at line 12
 @SuppressWarnings({"unchecked", "cast"})     public boolean inextractible() {
        ASTNode$State state = state();
        boolean inextractible_value = inextractible_compute();
        return inextractible_value;
    }

    private boolean inextractible_compute() {  return notAnObject() || inInextractiblePosition();  }

    // Declared in ExprExt.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return type().isVoid();  }

    // Declared in ExprExt.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {  return isConstant();  }

    // Declared in ExprExt.jrag at line 80
 @SuppressWarnings({"unchecked", "cast"})     public Expr convertSuperToThis() {
        ASTNode$State state = state();
        Expr convertSuperToThis_value = convertSuperToThis_compute();
        return convertSuperToThis_value;
    }

    private Expr convertSuperToThis_compute() {  return this;  }

    // Declared in Purity.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return false;  }

    // Declared in TypeExt.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl effectiveType() {
        ASTNode$State state = state();
        TypeDecl effectiveType_value = effectiveType_compute();
        return effectiveType_value;
    }

    private TypeDecl effectiveType_compute() {
		TypeDecl type = type();
		if(type.isAnonymous())
			return ((AnonymousDecl)type).superType();
		else if(isInaccessibleTypeVariable(type))
			return ((TypeVariable)type).uniqueUpperBound();
		return type;
	}

    // Declared in VariableExt.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccessInThis(FieldDeclaration fd) {
        ASTNode$State state = state();
        boolean isFieldAccessInThis_FieldDeclaration_value = isFieldAccessInThis_compute(fd);
        return isFieldAccessInThis_FieldDeclaration_value;
    }

    private boolean isFieldAccessInThis_compute(FieldDeclaration fd) {  return false;  }

    protected boolean isArrayInit_computed = false;
    protected boolean isArrayInit_value;
    // Declared in ArrayInits.jrag at line 2
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

    private boolean isArrayInit_compute() {  return false;  }

    protected java.util.Map shouldMakeConversionExplicit_TypeDecl_values;
    // Declared in MakeConversionExplicit.jrag at line 8
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

    private boolean shouldMakeConversionExplicit_compute(TypeDecl destType) {  return !type().equals(destType);  }

    // Declared in AccessVariable.jrag at line 264
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess qualifiedAccessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess qualifiedAccessVariable_Variable_value = qualifiedAccessVariable_compute(decl);
        return qualifiedAccessVariable_Variable_value;
    }

    private SymbolicVarAccess qualifiedAccessVariable_compute(Variable decl) {
		if(type().accessibleFrom(hostType()))
			return type().accessMemberField(decl).ensureAccessible(this);
		return unknownVarAccess();
	}

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

    // Declared in DefiniteAssignment.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDest() {
        ASTNode$State state = state();
        boolean isDest_value = getParent().Define_boolean_isDest(this, null);
        return isDest_value;
    }

    // Declared in DefiniteAssignment.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSource() {
        ASTNode$State state = state();
        boolean isSource_value = getParent().Define_boolean_isSource(this, null);
        return isSource_value;
    }

    // Declared in DefiniteAssignment.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public boolean isIncOrDec() {
        ASTNode$State state = state();
        boolean isIncOrDec_value = getParent().Define_boolean_isIncOrDec(this, null);
        return isIncOrDec_value;
    }

    // Declared in DefiniteAssignment.jrag at line 236
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        ASTNode$State state = state();
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        return isDAbefore_Variable_value;
    }

    // Declared in DefiniteAssignment.jrag at line 694
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        ASTNode$State state = state();
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        return isDUbefore_Variable_value;
    }

    // Declared in LookupMethod.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupMethod(String name) {
        ASTNode$State state = state();
        Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
        return lookupMethod_String_value;
    }

    // Declared in LookupType.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeBoolean() {
        ASTNode$State state = state();
        TypeDecl typeBoolean_value = getParent().Define_TypeDecl_typeBoolean(this, null);
        return typeBoolean_value;
    }

    // Declared in LookupType.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeByte() {
        ASTNode$State state = state();
        TypeDecl typeByte_value = getParent().Define_TypeDecl_typeByte(this, null);
        return typeByte_value;
    }

    // Declared in LookupType.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeShort() {
        ASTNode$State state = state();
        TypeDecl typeShort_value = getParent().Define_TypeDecl_typeShort(this, null);
        return typeShort_value;
    }

    // Declared in LookupType.jrag at line 52
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeChar() {
        ASTNode$State state = state();
        TypeDecl typeChar_value = getParent().Define_TypeDecl_typeChar(this, null);
        return typeChar_value;
    }

    // Declared in LookupType.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeInt() {
        ASTNode$State state = state();
        TypeDecl typeInt_value = getParent().Define_TypeDecl_typeInt(this, null);
        return typeInt_value;
    }

    // Declared in LookupType.jrag at line 54
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeLong() {
        ASTNode$State state = state();
        TypeDecl typeLong_value = getParent().Define_TypeDecl_typeLong(this, null);
        return typeLong_value;
    }

    // Declared in LookupType.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeFloat() {
        ASTNode$State state = state();
        TypeDecl typeFloat_value = getParent().Define_TypeDecl_typeFloat(this, null);
        return typeFloat_value;
    }

    // Declared in LookupType.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeDouble() {
        ASTNode$State state = state();
        TypeDecl typeDouble_value = getParent().Define_TypeDecl_typeDouble(this, null);
        return typeDouble_value;
    }

    // Declared in LookupType.jrag at line 57
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeString() {
        ASTNode$State state = state();
        TypeDecl typeString_value = getParent().Define_TypeDecl_typeString(this, null);
        return typeString_value;
    }

    // Declared in LookupType.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeVoid() {
        ASTNode$State state = state();
        TypeDecl typeVoid_value = getParent().Define_TypeDecl_typeVoid(this, null);
        return typeVoid_value;
    }

    // Declared in LookupType.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeNull() {
        ASTNode$State state = state();
        TypeDecl typeNull_value = getParent().Define_TypeDecl_typeNull(this, null);
        return typeNull_value;
    }

    // Declared in LookupType.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        ASTNode$State state = state();
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        return unknownType_value;
    }

    // Declared in LookupType.jrag at line 86
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasPackage(String packageName) {
        ASTNode$State state = state();
        boolean hasPackage_String_value = getParent().Define_boolean_hasPackage(this, null, packageName);
        return hasPackage_String_value;
    }

    // Declared in LookupType.jrag at line 95
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupType(String packageName, String typeName) {
        ASTNode$State state = state();
        TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
        return lookupType_String_String_value;
    }

    // Declared in LookupType.jrag at line 176
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        ASTNode$State state = state();
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        return lookupType_String_value;
    }

    // Declared in LookupVariable.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in SyntacticClassification.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public NameType nameType() {
        ASTNode$State state = state();
        NameType nameType_value = getParent().Define_NameType_nameType(this, null);
        return nameType_value;
    }

    // Declared in TypeAnalysis.jrag at line 511
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl enclosingBodyDecl() {
        ASTNode$State state = state();
        BodyDecl enclosingBodyDecl_value = getParent().Define_BodyDecl_enclosingBodyDecl(this, null);
        return enclosingBodyDecl_value;
    }

    // Declared in TypeAnalysis.jrag at line 568
 @SuppressWarnings({"unchecked", "cast"})     public String hostPackage() {
        ASTNode$State state = state();
        String hostPackage_value = getParent().Define_String_hostPackage(this, null);
        return hostPackage_value;
    }

    // Declared in TypeAnalysis.jrag at line 583
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in TypeHierarchyCheck.jrag at line 11
 @SuppressWarnings({"unchecked", "cast"})     public String methodHost() {
        ASTNode$State state = state();
        String methodHost_value = getParent().Define_String_methodHost(this, null);
        return methodHost_value;
    }

    // Declared in TypeHierarchyCheck.jrag at line 134
 @SuppressWarnings({"unchecked", "cast"})     public boolean inStaticContext() {
        ASTNode$State state = state();
        boolean inStaticContext_value = getParent().Define_boolean_inStaticContext(this, null);
        return inStaticContext_value;
    }

    // Declared in GenericMethodsInference.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl assignConvertedType() {
        ASTNode$State state = state();
        TypeDecl assignConvertedType_value = getParent().Define_TypeDecl_assignConvertedType(this, null);
        return assignConvertedType_value;
    }

    // Declared in ControlFlowGraph.jrag at line 245
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> throwTarget(TypeDecl exn) {
        ASTNode$State state = state();
        SmallSet<CFGNode> throwTarget_TypeDecl_value = getParent().Define_SmallSet_CFGNode__throwTarget(this, null, exn);
        return throwTarget_TypeDecl_value;
    }

    // Declared in ControlFlowGraph.jrag at line 316
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> followingWhenTrue() {
        ASTNode$State state = state();
        SmallSet<CFGNode> followingWhenTrue_value = getParent().Define_SmallSet_CFGNode__followingWhenTrue(this, null);
        return followingWhenTrue_value;
    }

    // Declared in ControlFlowGraph.jrag at line 317
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> followingWhenFalse() {
        ASTNode$State state = state();
        SmallSet<CFGNode> followingWhenFalse_value = getParent().Define_SmallSet_CFGNode__followingWhenFalse(this, null);
        return followingWhenFalse_value;
    }

    protected boolean uncheckedExnTarget_computed = false;
    protected SmallSet<CFGNode> uncheckedExnTarget_value;
    // Declared in ControlFlowGraph.jrag at line 652
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> uncheckedExnTarget() {
        if(uncheckedExnTarget_computed) {
            return uncheckedExnTarget_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        uncheckedExnTarget_value = getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, null);
        if(isFinal && num == state().boundariesCrossed)
            uncheckedExnTarget_computed = true;
        return uncheckedExnTarget_value;
    }

    // Declared in ExprExt.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public boolean inInextractiblePosition() {
        ASTNode$State state = state();
        boolean inInextractiblePosition_value = getParent().Define_boolean_inInextractiblePosition(this, null);
        return inInextractiblePosition_value;
    }

    // Declared in PackageDecl.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public PackageDecl lookupPackage(String name) {
        ASTNode$State state = state();
        PackageDecl lookupPackage_String_value = getParent().Define_PackageDecl_lookupPackage(this, null, name);
        return lookupPackage_String_value;
    }

    // Declared in Precedence.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public int maxPrecedence() {
        ASTNode$State state = state();
        int maxPrecedence_value = getParent().Define_int_maxPrecedence(this, null);
        return maxPrecedence_value;
    }

    // Declared in AccessMethod.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public MethodAccessInfo accessMethod(MethodDecl md) {
        ASTNode$State state = state();
        MethodAccessInfo accessMethod_MethodDecl_value = getParent().Define_MethodAccessInfo_accessMethod(this, null, md);
        return accessMethod_MethodDecl_value;
    }

    // Declared in AccessVariable.jrag at line 115
 @SuppressWarnings({"unchecked", "cast"})     public UnknownVarAccess unknownVarAccess() {
        ASTNode$State state = state();
        UnknownVarAccess unknownVarAccess_value = getParent().Define_UnknownVarAccess_unknownVarAccess(this, null);
        return unknownVarAccess_value;
    }

    // Declared in AccessVariable.jrag at line 137
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess accessVariable_Variable_value = getParent().Define_SymbolicVarAccess_accessVariable(this, null, decl);
        return accessVariable_Variable_value;
    }

    // Declared in InsertEmptyStmt.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public Stmt insertStmtBefore(Stmt s) {
        ASTNode$State state = state();
        Stmt insertStmtBefore_Stmt_value = getParent().Define_Stmt_insertStmtBefore(this, null, s);
        return insertStmtBefore_Stmt_value;
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

public ASTNode rewriteTo() {
    return super.rewriteTo();
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

    protected void collect_contributors_CFGNode_collPred() {
        // Declared in ControlFlowGraph.jrag at line 38
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
