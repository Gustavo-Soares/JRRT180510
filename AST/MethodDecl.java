
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class MethodDecl extends MemberDecl implements Cloneable, SimpleSet, Iterator, Callable, Declaration, Visible {
    public void flushCache() {
        super.flushCache();
        accessibleFrom_TypeDecl_values = null;
        throwsException_TypeDecl_values = null;
        signature_computed = false;
        signature_value = null;
        moreSpecificThan_MethodDecl_values = null;
        overrides_MethodDecl_values = null;
        hides_MethodDecl_values = null;
        parameterDeclaration_String_values = null;
        type_computed = false;
        type_value = null;
        usesTypeVariable_computed = false;
        sourceMethodDecl_computed = false;
        sourceMethodDecl_value = null;
        uncheckedExceptionExit_computed = false;
        uncheckedExceptionExit_value = null;
        isCallable_computed = false;
        monoUses_computed = false;
        monoUses_value = null;
        isDynamicallyCallable_computed = false;
        inheritingTypes_computed = false;
        inheritingTypes_value = null;
        rootdef_computed = false;
        rootdef_value = null;
        relatives_computed = false;
        relatives_value = null;
        isCallableFromOutside_computed = false;
        isReferencedFromOutside_computed = false;
        canOverrideOrHide_MethodDecl_values = null;
        overrides_computed = false;
        overrides_value = null;
        isGetterFor_FieldDeclaration_values = null;
        isConstantMethod_computed = false;
        getIndexOfParameter_String_values = null;
        accessParameter_Variable_values = null;
        handlesException_TypeDecl_values = null;
        MethodDecl_coll_overriders_computed = false;
        MethodDecl_coll_overriders_value = null;
    MethodDecl_coll_overriders_contributors = null;
        MethodDecl_polyUses_computed = false;
        MethodDecl_polyUses_value = null;
    MethodDecl_polyUses_contributors = null;
        MethodDecl_uses_computed = false;
        MethodDecl_uses_value = null;
    MethodDecl_uses_contributors = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
        MethodDecl_coll_overriders_computed = false;
        MethodDecl_coll_overriders_value = null;
    MethodDecl_coll_overriders_contributors = null;
        MethodDecl_polyUses_computed = false;
        MethodDecl_polyUses_value = null;
    MethodDecl_polyUses_contributors = null;
        MethodDecl_uses_computed = false;
        MethodDecl_uses_value = null;
    MethodDecl_uses_contributors = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodDecl clone() throws CloneNotSupportedException {
        MethodDecl node = (MethodDecl)super.clone();
        node.accessibleFrom_TypeDecl_values = null;
        node.throwsException_TypeDecl_values = null;
        node.signature_computed = false;
        node.signature_value = null;
        node.moreSpecificThan_MethodDecl_values = null;
        node.overrides_MethodDecl_values = null;
        node.hides_MethodDecl_values = null;
        node.parameterDeclaration_String_values = null;
        node.type_computed = false;
        node.type_value = null;
        node.usesTypeVariable_computed = false;
        node.sourceMethodDecl_computed = false;
        node.sourceMethodDecl_value = null;
        node.uncheckedExceptionExit_computed = false;
        node.uncheckedExceptionExit_value = null;
        node.isCallable_computed = false;
        node.monoUses_computed = false;
        node.monoUses_value = null;
        node.isDynamicallyCallable_computed = false;
        node.inheritingTypes_computed = false;
        node.inheritingTypes_value = null;
        node.rootdef_computed = false;
        node.rootdef_value = null;
        node.relatives_computed = false;
        node.relatives_value = null;
        node.isCallableFromOutside_computed = false;
        node.isReferencedFromOutside_computed = false;
        node.canOverrideOrHide_MethodDecl_values = null;
        node.overrides_computed = false;
        node.overrides_value = null;
        node.isGetterFor_FieldDeclaration_values = null;
        node.isConstantMethod_computed = false;
        node.getIndexOfParameter_String_values = null;
        node.accessParameter_Variable_values = null;
        node.handlesException_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodDecl copy() {
      try {
          MethodDecl node = (MethodDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodDecl fullCopy() {
        MethodDecl res = (MethodDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BoundNames.jrag at line 77


  public Access createBoundAccess(List args) {
    if(isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(
        new BoundMethodAccess(name(), args, this)
      );
    }
    return new BoundMethodAccess(name(), args, this);
  }

    // Declared in DataStructures.jrag at line 154

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 158

  public boolean isSingleton() { return true; }

    // Declared in DataStructures.jrag at line 159

  public boolean isSingleton(Object o) { return contains(o); }

    // Declared in DataStructures.jrag at line 162

  private MethodDecl iterElem;

    // Declared in DataStructures.jrag at line 163

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 164

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 165

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 166

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in Modifiers.jrag at line 127

  
  // 8.4.3
  public void checkModifiers() {
    super.checkModifiers();
    if(hostType().isClassDecl()) {
      // 8.4.3.1
      if(isAbstract() && !hostType().isAbstract())
        error("class must be abstract to include abstract methods");
      // 8.4.3.1
      if(isAbstract() && isPrivate())
        error("method may not be abstract and private");
      // 8.4.3.1
      // 8.4.3.2
      if(isAbstract() && isStatic())
        error("method may not be abstract and static");
      if(isAbstract() && isSynchronized())
        error("method may not be abstract and synchronized");
      // 8.4.3.4
      if(isAbstract() && isNative())
        error("method may not be abstract and native");
      if(isAbstract() && isStrictfp())
        error("method may not be abstract and strictfp");
      if(isNative() && isStrictfp())
        error("method may not be native and strictfp");
    }
    if(hostType().isInterfaceDecl()) {
      // 9.4
      if(isStatic())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be static");
      if(isStrictfp())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be strictfp");
      if(isNative())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be native");
      if(isSynchronized())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be synchronized");
      if(isProtected())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be protected");
      if(isPrivate())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be private");
      else if(isFinal())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be final");
    }
  }

    // Declared in NameCheck.jrag at line 96


  public void nameCheck() {
    // 8.4
    // 8.4.2
    if(!hostType().methodsSignature(signature()).contains(this))
      error("method with signature " + signature() + " is multiply declared in type " + hostType().typeName());
    // 8.4.3.4
    if(isNative() && hasBlock())
      error("native methods must have an empty semicolon body");
    // 8.4.5
    if(isAbstract() && hasBlock())
      error("abstract methods must have an empty semicolon body");
    // 8.4.5
    if(!hasBlock() && !(isNative() || isAbstract()))
      error("only abstract and native methods may have an empty semicolon body");
  }

    // Declared in PrettyPrint.jadd at line 175


  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name() + "(");
    if(getNumParameter() > 0) {
      getParameter(0).toString(s);
      for(int i = 1; i < getNumParameter(); i++) {
        s.append(", ");
        getParameter(i).toString(s);
      }
    }
    s.append(")");
    if(getNumException() > 0) {
      s.append(" throws ");
      getException(0).toString(s);
      for(int i = 1; i < getNumException(); i++) {
        s.append(", ");
        getException(i).toString(s);
      }
    }
    if(hasBlock()) {
      s.append(" ");
      getBlock().toString(s);
    }
    else {
      s.append(";");
    }
  }

    // Declared in TypeCheck.jrag at line 386


  public void typeCheck() {
    // Thrown vs super class method see MethodDecl.nameCheck
    // 8.4.4
    TypeDecl exceptionType = typeThrowable();
    for(int i = 0; i < getNumException(); i++) {
      TypeDecl typeDecl = getException(i).type();
      if(!typeDecl.instanceOf(exceptionType))
        error(signature() + " throws non throwable type " + typeDecl.fullName());
    }

    // check returns
    if(!isVoid() && hasBlock() && getBlock().canCompleteNormally())
      error("the body of a non void method may not complete normally");

  }

    // Declared in Generics.jrag at line 1005


  public BodyDecl p(Parameterization parTypeDecl) {
    //System.out.println("Begin substituting " + signature() + " in " + hostType().typeName() + " with " + parTypeDecl.typeSignature());
    MethodDecl m = new MethodDeclSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getTypeAccess().type().substituteReturnType(parTypeDecl),
      getID(),
      getParameterList().substitute(parTypeDecl),
      getExceptionList().substitute(parTypeDecl),
      substituteBody(parTypeDecl),
      this
    );
    //System.out.println("End substituting " + signature());
    return m;
  }

    // Declared in ControlFlowGraph.jrag at line 705

	
	// artificial node to represent throws of uncaught exceptions
	private final ExitStmt uncheckedExceptionExit = new ExitStmt();

    // Declared in MethodExt.jrag at line 4

	
	public void makeAbstract() {
		if(isNative() || isFinal() || isStatic())
			throw new RefactoringException("cannot make this method abstract");
		getModifiers().addModifier("abstract");
		setBlockOpt(new Opt());
	}

    // Declared in MethodExt.jrag at line 64

	
	// uses of a method, including its substituted and parameterised copies
	public Collection<MethodAccess> usesOfAllCopies() {
		Collection<MethodAccess> res = new HashSet<MethodAccess>(uses());
		for(MethodDecl md : substitutedCopies())
			res.addAll(md.uses());
		return res;
	}

    // Declared in MethodExt.jrag at line 77

	
	public Collection<MethodDeclSubstituted> substitutedCopies() {
		Collection<MethodDeclSubstituted> res = new LinkedList<MethodDeclSubstituted>();
		if(!hostType().isGenericType())
			return res;
		GenericTypeDecl host = (GenericTypeDecl)hostType();
		for(int i=0;i<host.getNumParTypeDecl();++i) {
			ParTypeDecl ptd = host.getParTypeDecl(i);
			for(Object o : ptd.localMethodsSignatureMap().values())
				if(o instanceof MethodDeclSubstituted && ((MethodDeclSubstituted)o).sourceMethodDecl() == this)
					res.add((MethodDeclSubstituted)o);
		}
		return res;
	}

    // Declared in MethodExt.jrag at line 198


	// copy of method with locked names, but empty body
	public MethodDecl lockedCopyWithEmptyBody() {
		Modifiers mods = (Modifiers)getModifiers().fullCopy();
		Access rettype = type().createLockedAccess();
		String name = name();
		List<ParameterDeclaration> parms = new List<ParameterDeclaration>();
		for(ParameterDeclaration pd : getParameters())
			parms.add(pd.lockedCopy());
		List<Access> exns = new List<Access>();
		for(Access exn : getExceptions())
			exns.add(exn.type().createLockedAccess());
		return new MethodDecl(mods, rettype, name, parms, exns, new Opt<Block>());
	}

    // Declared in ModifiersExt.jrag at line 51

	public void makeModifiersExplicit() {
		if(hostType().isInterfaceDecl())
			getModifiers().addModifiers("public", "static", "abstract");
	}

    // Declared in Overriding.jrag at line 56

	
	public HashSet<MethodDecl> overriders() {
		if(this.isFinal() || this.isPrivate() || this.isStatic())
			return new HashSet<MethodDecl>();
		return coll_overriders();
	}

    // Declared in TypeVariableExt.jrag at line 94

	
	// translate type variables in a method from sup to sub
	private void translateTypeVars(TypeDecl sub, TypeDecl sup) {
		if(!sub.isGenericType() || !sup.isParameterizedType())
			return;
		GenericTypeDecl gsub = (GenericTypeDecl)sub;
		ParTypeDecl psup = (ParTypeDecl)sup;
		GenericTypeDecl gsup = (GenericTypeDecl)psup.genericDecl();
		Map<TypeVariable, TypeVariable> dict = new HashMap<TypeVariable, TypeVariable>();
		for(TypeVariable tv : (List<TypeVariable>)gsub.getTypeParameterList()) {
			for(int i=0;i<psup.getNumArgument();++i) {
				if(psup.getArgument(i).type() == tv)
					dict.put(tv, gsup.getTypeParameter(i));
			}
		}
		translateTypeVars(dict);
	}

    // Declared in AddParameter.jrag at line 3

	// TODO: check that we don't pick up new overriding relationships
	public void addParameter(ParameterDeclaration pd, int idx, Literal value, boolean createDelegate) {
		if(idx < 0 || idx > getNumParameter())
			throw new RefactoringException("invalid index for new parameter");
		programRoot().lockMethodNames(name());
		Collection<MethodDecl> relatives = relatives();
		for(MethodDecl md : relatives()) {
			if(!md.parameterDeclaration(pd.name()).isEmpty())
				throw new RefactoringException("parameter of same name exists");
			if(idx == md.getNumParameter() && md.isVariableArity())
				throw new RefactoringException("cannot insert parameter after vararg");
			if(idx != md.getNumParameter() && pd.isVariableArity())
				throw new RefactoringException("cannot insert vararg parameter anywhere except in last position");
			// TODO: use locked copy
			Collection<MethodAccess> uses = md.uses();
			if(createDelegate) {
				MethodDecl delegate = (MethodDecl)md.fullCopy();
				if(!md.hostType().isInterfaceDecl()) {
					delegate.getModifiers().removeModifiers("abstract", "native");
					List<Expr> args = new List<Expr>();
					for(ParameterDeclaration mdpd : md.getParameters())
						args.add(new VarAccess(mdpd.name()));
					args.insertChild((Expr)value.fullCopy(), idx);
					delegate.setBlock(new Block(new List<Stmt>().add(new ReturnStmt(md.createLockedAccess(args)))));
				}
				md.hostType().getBodyDeclList().insertChild(delegate, md.getChildIndex());
			}
			md.getParameterList().insertChild(pd.fullCopy(), idx);
			md.flushCaches();
			if(!md.hostType().canIntroduceMethod(md))
				throw new RefactoringException("signature conflict");
			for(MethodAccess ma : uses) {
				if(relatives.contains(ma.hostBodyDecl()))
					ma.getArgList().insertChild(new VarAccess(pd.name()), idx);
				else
					ma.getArgList().insertChild((Expr)value.fullCopy(), idx);
			}
		}
	}

    // Declared in AddParameter.jrag at line 42

	
	public void doAddParameter(ParameterDeclaration pd, int idx, Literal value, boolean createDelegate) {
		addParameter(pd, idx, value, createDelegate);
		programRoot().eliminate(RETURN_VOID, LOCKED_NAMES);
	}

    // Declared in PermuteParameters.jrag at line 3

	// TODO: ensure we do not pick up any new overriding relationships
	public void permuteParameters(int[] perm, boolean createDelegate) {
		int n = getNumParameter();
		if(!isPerm(perm, n))
			throw new RefactoringException("not a permutation");
		if(isIdentityPerm(perm))
			return;
		programRoot().lockMethodNames(name());
		for(MethodDecl md : relatives()) {
			Collection<MethodAccess> uses = md.uses();
			if(md.isNative() || !md.fromSource())
				throw new RefactoringException("cannot permute parameters");
			if(md.isVariableArity() && perm[n-1] != n-1)
				throw new RefactoringException("variable arity parameter has to come last");
			if(createDelegate) {
				MethodDecl delegate = (MethodDecl)md.fullCopy();
				if(!md.hostType().isInterfaceDecl()) {
					delegate.getModifiers().removeModifiers("abstract", "native");
					List<Expr> args = new List<Expr>();
					for(ParameterDeclaration pd : md.getParameters())
						args.add(new VarAccess(pd.name()));
					args.permute(perm);
					delegate.setBlock(new Block(new List<Stmt>().add(new ReturnStmt(md.createLockedAccess(args)))));
				}
				md.hostType().getBodyDeclList().insertChild(delegate, md.getChildIndex());
			}
			md.getParameterList().permute(perm);
			md.flushCaches();
			for(MethodAccess ma : uses) {
				ma.getArgList().lockDataFlow();
				ma.getArgList().permute(perm);
			}
		}
	}

    // Declared in PermuteParameters.jrag at line 37

	
	public void doPermuteParameters(int[] perm, boolean createDelegate) {
		permuteParameters(perm, createDelegate);
		programRoot().eliminate(RETURN_VOID, LOCKED_DATAFLOW, LOCKED_NAMES);
	}

    // Declared in RemoveParameter.jrag at line 3

	// TODO: check that we don't pick up new overriding relations
	public void removeUnusedParameter(int i) {
		// lock all calls
		programRoot().lockMethodNames(name());
		// consider every relative in turn
		for(MethodDecl rel : relatives()) {
			if(!rel.getParameter(i).allUses().isEmpty())
				throw new RefactoringException("parameter is used");
			Collection<MethodAccess> uses = rel.uses();
			for(MethodAccess call : uses)
				if(!call.getArg(i).isPure())
					throw new RefactoringException("invocation has impure argument");
			// remove arguments and parameter
			rel.removeParameter(i);
			rel.flushCaches();
			if(!rel.hostType().canIntroduceMethod(rel))
				throw new RefactoringException("signature conflict");
			for(MethodAccess call : uses)
				call.getArgList().removeChild(i);
		}
	}

    // Declared in RemoveParameter.jrag at line 24

	
	public void doRemoveUnusedParameter(int i) {
		removeUnusedParameter(i);
		programRoot().eliminate(ASTNode.LOCKED_NAMES);
	}

    // Declared in DataFlow.jrag at line 113

	
	public boolean isArrayElement() { return false; }

    // Declared in DataFlow.jrag at line 116

	
	public boolean isHeapLocation() { return true; }

    // Declared in InlineMethod.jrag at line 9

	
	public void doInline(boolean delete) {
		TypeDecl host = hostType();
		int idx = host.getBodyDeclList().getIndexOfChild(this);
		for(MethodAccess ma : this.polyUses())
			ma.doInline();
		if(delete) {
			MethodDecl md = (MethodDecl)host.getBodyDecl(idx);
			try { md.removeUnused(false); } catch(RefactoringException rfe) { }			
		}
	}

    // Declared in IntroduceIndirection.jrag at line 7

	
	// TODO: rewrite this as Extract Method; Make Method Static; Move Static Method (but what about overriding methods?)
	public void introduceIndirection(String indname, String parmname, TypeDecl targetType) {
		MethodDecl indirection = lockedCopyWithEmptyBody();
		indirection.setModifiers(new Modifiers("public", "static"));
		indirection.setID(indname);
		List<Expr> args = new List<Expr>();
		for(ParameterDeclaration pd : indirection.getParameters())
			args.addChild(pd.createLockedAccess());
		ParameterDeclaration parm = null;
		Block body = null;
		if(isStatic()) {
			body = new Block(new ReturnStmt(this.createLockedAccess(args)));
		} else {
			parm = new FreshParameter(hostType().createLockedAccess(), parmname);
			indirection.insertParameter(parm, 0);
			body = new Block(new ReturnStmt(parm.createLockedAccess().qualifiesAccess(this.createLockedAccess(args))));
		}
		indirection.setBlock(body);
		targetType.insertUnusedMethod(indirection, getChildIndex());
		indirection = closeOverTypeVariables(indirection);
		for(MethodAccess ma : usesOfAllCopies()) {
			if(ma.hostBodyDecl() == indirection)
				continue;
			if(ma.isMonoCall() && !this.overriders().isEmpty())
				continue;
			if(!isStatic()) {
				ma.bundleQualifier();
				ASTNode p = ma;
				if(ma.isQualified())
					p = p.getParent();
				Expr qual = ma.isQualified() && !ma.qualifier().isSuperAccess() ? ma.qualifier() : null;
				if(qual == null) {
					for(TypeDecl enc=ma.hostType(); enc!=null; enc=enc.enclosingType()) {
						if(enc.memberMethod(this) == this) {
							if(enc == ma.hostType())
								qual = new ThisAccess("this");
							else
								qual = enc.createLockedAccess().qualifiesAccess(new ThisAccess("this"));
							break;
						}
					}
				}
				ma.getArgList().insertChild(qual == null ? new ThisAccess("this") : qual, 0);
				p.replaceWith(ma);
			}
			ma.lock(indirection);
		}
		programRoot().flushCaches();
	}

    // Declared in IntroduceIndirection.jrag at line 56

	
	public void doIntroduceIndirection(String indname, String parmname, TypeDecl targetType) {
		introduceIndirection(indname, parmname, targetType);
		programRoot().eliminate(RETURN_VOID, FRESH_VARIABLES, BARE_PARMETHODACCESS, LOCKED_NAMES);
	}

    // Declared in IntroduceIndirection.jrag at line 61

	
	public void doIntroduceIndirection(String indname, TypeDecl targetType) {
		String hostname = hostType().name();
		doIntroduceIndirection(indname, Character.toLowerCase(hostname.charAt(0))+hostname.substring(1), targetType);
	}

    // Declared in IntroduceParameterObject.jrag at line 2

	public MemberTypeDecl introduceParameterObject(Collection<String> parms_to_wrap, String className, String parmName) {
		ArrayList<ParameterDeclaration> parms = new ArrayList<ParameterDeclaration>();
		java.util.BitSet positions_to_wrap = new java.util.BitSet(this.getNumParameter());
		List<ParameterDeclaration> old_parms = (List<ParameterDeclaration>)getParameters().fullCopy();
		for(int i=0;i<getNumParameter();++i) {
			if(parms_to_wrap == null || parms_to_wrap.contains(getParameter(i).name())) {
				parms.add(getParameter(i));
				positions_to_wrap.set(i);
			}
		}
		Pair<ClassDecl, Map<String, FieldDeclaration>> tmp = createStruct(className, parms);
		ClassDecl struct = tmp.fst();
		programRoot().lockMethodNames(name());
		
		// adjust this method and all its relatives
		for(MethodDecl md : relatives())
			md.uses();
		for(MethodDecl md : relatives()) {
			eliminateVarArgs();
			for(MethodAccess ma : md.uses()) {
				List<Expr> wrapped_args = new List<Expr>();
				List<Expr> newargs = new List<Expr>().add(new ClassInstanceExpr(struct.createLockedAccess(), wrapped_args));
				for(int i=0;i<md.getNumParameter();++i)
					if(positions_to_wrap.get(i))
						wrapped_args.add(ma.getArg(i));
					else
						newargs.add(ma.getArg(i));
				ma.setArgList(newargs);
			}
			ParameterDeclaration parm = new ParameterDeclaration(struct.createLockedAccess(), parmName);
			List<ParameterDeclaration> newparms = new List<ParameterDeclaration>().add(parm);
			for(int i=0;i<md.getNumParameter();++i) {
				ParameterDeclaration pd = md.getParameter(i);
				if(positions_to_wrap.get(i) && md.hasBlock())
					md.getBlock().insertStmt(i, pd.asVariableDeclaration(parm.createLockedAccess().qualifiesAccess(new VarAccess(old_parms.getChild(i).name()))));
				if(!positions_to_wrap.get(i))
					newparms.add(pd);
			}
			md.setParameterList(newparms);
		}
		
		return hostType().insertUnusedType(struct, getChildIndex());
	}

    // Declared in IntroduceParameterObject.jrag at line 46

	
	public void doIntroduceParameterObject(Collection<String> parms, String className, String parmName, boolean toplevel) {
		MemberTypeDecl mcd = introduceParameterObject(parms, className, parmName);
		if(toplevel)
			mcd.moveToToplevel(true, null, true);
		programRoot().eliminate(LOCKED_NAMES);
	}

    // Declared in EliminateVarargs.jrag at line 2
	
	public void eliminateVarArgs() {
		if(!isVariableArity())
			return;
		VariableArityParameterDeclaration last = (VariableArityParameterDeclaration)lastParameter();
		ParameterDeclaration new_last = last.asFixedArityParameter();
		for(VarAccess va : last.uses())
			if(va.isLocked())
				va.lock(new_last);
		for(MethodAccess ma : uses()) {
			if(!ma.invokesVariableArityAsArray()) {
				List<Expr> varargs = new List<Expr>();
				for(int i=ma.getNumArg()-1; i>=getNumParameter()-1;--i) {
					Expr arg = ma.getArg(i);
					ma.getArgList().removeChild(i);
					varargs.insertChild(arg, 0);
				}
				ma.getArgList().insertChild(new ArrayCreationExpr(last.type().createLockedAccess(), new Opt(new ArrayInit(varargs))), getNumParameter()-1);
			}
		}
		setParameter(new_last, getNumParameter()-1);
	}

    // Declared in MakeMethodStatic.jrag at line 2

	public void makeStatic() {
		if(isStatic())
			return;
		if(!fromSource() || !hasBlock())
			throw new RefactoringException("cannot make static");
		programRoot().lockMethodNames(Collections.singleton(name()));
		MethodDecl newMethod = this;
		MethodDecl delegator = (MethodDecl)this.fullCopy();
		TypeDecl hostType = hostType();
		// make new method static
		newMethod.getModifiers().addModifier(new Modifier("static"));
		// fix value arguments
		String newParmName = hostType.name().toLowerCase();
		ParameterDeclaration newParm = new FreshParameter(hostType.createLockedAccess(), newParmName);
		newParm.setDemandFinal();
		newMethod.getParameterList().insertChild(newParm, 0);
		// fix type arguments
		newMethod = closeOverTypeVariables(newMethod);
		// fix body
		Access newParmAcc = newParm.createLockedAccess();
		WithStmt withStmt = new WithStmt(new List<Access>().add(newParmAcc), newMethod.getBlock());
		newMethod.setBlock(new Block(new List<Stmt>().add(withStmt)));
		newMethod.flushCaches();
		newMethod.signature();
		// prepare delegating method
		List<Expr> delegationArgs = new List<Expr>().add(new ThisAccess("this"));
		for(int i=1;i<newMethod.getNumParameter();++i)
			delegationArgs.add(new VarAccess(newMethod.getParameter(i).name()));
		Expr delegationCall = new MethodAccess(newMethod.name(), delegationArgs);
		delegator.setBlock(new Block(new List<Stmt>().add(new ReturnStmt(delegationCall))));
		// plug in delegating method
		newMethod.replaceWith(delegator);
		delegator.programRoot().flushCaches();
		if(!hostType.canIntroduceMethod(newMethod))
			throw new RefactoringException("cannot introduce method");
		hostType.addBodyDecl(newMethod);
		newMethod.programRoot().eliminate(RETURN_VOID, FRESH_VARIABLES, WITH_STMT, LOCKED_NAMES, DEMAND_FINAL_MODIFIER);
	}

    // Declared in MakeMethodStatic.jrag at line 41

	
	MethodDecl closeOverTypeVariables(MethodDecl newMethod) {
		if(newMethod.usesForeignTypeVars()) {
			newMethod = newMethod.makeGeneric();
			for(TypeVariable tv : newMethod.usedTypeVars()) {
				if(!tv.isDescendantTo(newMethod)) {
					TypeVariable new_tv = (TypeVariable)tv.fullCopy();
					((GenericMethodDecl)newMethod).addTypeParameter(new_tv);
					for(Access tvu : tv.uses())
						if(tvu.isDescendantTo(newMethod))
							((TypeAccess)tvu).lock(new_tv);
				}
			}
		}
		return newMethod;
	}

    // Declared in MakeMethodStatic.jrag at line 57

	
	GenericMethodDecl makeGeneric() {
		GenericMethodDecl g = new GenericMethodDecl(getModifiers(), getTypeAccess(), getID(), 
				                                    getParameterList(), getExceptionList(), 
				                                    getBlockOpt(), new List<TypeVariable>());
		replaceWith(g);
		return g;
	}

    // Declared in MoveMembers.jrag at line 31

	
	public void moveTo(TypeDecl target) {
		if(!isStatic())
			throw new RefactoringException("cannot move instance methods");
		lockAllNames();
		programRoot().lockNames(name());
		TypeDecl host = hostType();
		target.insertUnusedMethod(this);
		host.removeBodyDecl(this);
	}

    // Declared in MoveMethod.jrag at line 2

	public void doMoveToParameter(String name, boolean inlineDelegator, boolean removeDelegator, boolean removeSpuriousParameters) {
		doMoveTo(getParameter(name), inlineDelegator, removeDelegator, removeSpuriousParameters);
	}

    // Declared in MoveMethod.jrag at line 6

	
	public void doMoveToField(String name, boolean inlineDelegator, boolean removeDelegator, boolean removeSpuriousParameters) {
		doMoveTo((FieldDeclaration)hostType().localFields(name), inlineDelegator, removeDelegator, removeSpuriousParameters);
	}

    // Declared in MoveMethod.jrag at line 10

	
	public void moveToFirstParameter() {
		doMoveTo(getNumParameter() > 0 ? getParameter(0) : null, false, false, false);
	}

    // Declared in MoveMethod.jrag at line 14


	public void doMoveTo(Variable target, boolean inlineDelegator, boolean removeDelegator, boolean removeSpuriousParameters) {
		MethodDecl delegator = moveTo(target, removeSpuriousParameters);
		programRoot().eliminate(RETURN_VOID, FRESH_VARIABLES, WITH_STMT, LOCKED_NAMES, DEMAND_FINAL_MODIFIER);
		if(inlineDelegator)
			delegator.doInline(removeDelegator);
	}

    // Declared in MoveMethod.jrag at line 21

	
	public MethodDecl moveTo(Variable b, boolean removeSpuriousParameters) {
		MethodDecl m = this;
		checkMoveMethodPreconditions(m, b);
		MethodDecl m_copy = (MethodDecl)m.fullCopy();
		for(MethodAccess ma : m.uses())
			ma.lock(m_copy);
		Variable b_copy = b.isMethodParameter() ? m_copy.getParameter(b.name()) : b;
		List<Expr> args = new List<Expr>();
		for(ParameterDeclaration pd : m_copy.getParameters())
			args.add(pd.createLockedAccess());
		// NB: synchronization is done by delegating method
		m.getModifiers().removeModifier("synchronized");
		m.lockAllNames();
		VariableDeclaration new_b = b.asVariableDeclaration(new ThisAccess("this"));
		for(VarAccess va : b.allUses()) {
			if(va.isDescendantTo(m))
				va.lock(new_b);
		}
		int i;
		if(b.isMethodParameter()) {
			i = m.getIndexOfParameter(b.name());
			m.removeParameter(i);
			args.removeChild(i);
		} else {
			i = 0;
		}
		List<Access> quals = new List<Access>();
		for(TypeDecl A=m.hostType(); A!=null; A=A.enclosingType()) {
			String a = A.name().toLowerCase();
			ParameterDeclaration a_decl = new FreshParameter(A.createLockedAccess(), a); 
			a_decl.setDemandFinal();
			m.insertParameter(a_decl, i);
			args.insertChild(A==m.hostType() ? new ThisAccess("this") : A.createLockedAccess().qualifiesAccess(new ThisAccess("this")), i);
			quals.insertChild(a_decl.createLockedAccess(), 0);
		}
		Block withBlock = m.getBlock();
		m.setBlock(new Block(new_b, new WithStmt(quals, withBlock)));
		m.replaceWith(m_copy);
		m_copy.programRoot().flushCaches(); m.flushCaches();
		b.type().insertUnusedMethod(m);
		VarAccess b_acc = b_copy.createLockedAccess();
		m_copy.setBlock(new Block(new ReturnStmt(b_acc.qualifiesAccess(new MethodAccess(m, args)))));
		m.eliminate(ASTNode.WITH_STMT, ASTNode.FRESH_VARIABLES, ASTNode.LOCKED_NAMES);
		new_b.inline();
		// remove spurious parameters
		if(removeSpuriousParameters) {
			for(int k=i+quals.getNumChild()-1;k>=i;--k)
				try {
					m.removeUnusedParameter(k);
				} catch(RefactoringException rfe) { }
		}
		return m_copy;
	}

    // Declared in MoveMethod.jrag at line 75


	private void checkMoveMethodPreconditions(MethodDecl m, Variable b) {
		if(m == null || b == null)
			throw new RefactoringException("method or target does not exist");
		if(!m.hostType().fromSource() || !b.type().fromSource())
			throw new RefactoringException("cannot move inside bytecode");
		if(m.isStatic())
			throw new RefactoringException("cannot move static method (yet)");
		if(!m.hasBlock())
			throw new RefactoringException("cannot move body-less method");
	}

    // Declared in RemoveUnusedMethod.jrag at line 5

	public void removeUnused(boolean leaveAbstract) {
		if(isCallableFromOutside())
			throw new RefactoringException("method is still used");
		if(leaveAbstract || isReferencedFromOutside()) {
			if(isAbstract())
				return;
			if(!canBeAbstract())
				throw new RefactoringException("cannot make method abstract");
			for(TypeDecl td : inheritingTypes())
				td.makeAbstract();
			setBlockOpt(new Opt());
			getModifiers().addModifier("abstract");
		} else {
			if(!overrides().isEmpty() && allAbstract(overrides()))
				hostType().makeAbstract();
			hostType().removeBodyDecl(this);
		}
	}

    // Declared in AccessMethod.jrag at line 35

	
	public boolean applicableTo(List<Expr> args) {
		if(getNumParameter() != args.getNumChild())
			return false;
		for(int i=0;i<getNumParameter();++i)
			if(!args.getChild(i).type().instanceOf(getParameter(i).type()))
				return false;
		return true;
	}

    // Declared in LockedMethodAccess.jrag at line 11

	
	public MethodAccess createLockedAccess(List<Expr> args) {
		return new MethodAccess(this, args);
	}

    // Declared in LockedMethodAccess.jrag at line 70

	
	public SavedMethodDecl save() { return new SavedMethodDecl(this); }

    // Declared in LockedMethodAccess.jrag at line 104

	
	public boolean isSubstituted() { return false; }

    // Declared in PullUpMethod.jrag at line 2

	public void pullUp(boolean onlyAbstract) {
		if(!hostType().isClassDecl() || !hostType().fromSource() || !((ClassDecl)hostType()).superclass().fromSource())
			throw new RefactoringException("no fitting super class");
		ClassDecl host = (ClassDecl)hostType();
		ClassDecl sup = host.superclass();
		MethodDecl md = this;
		lockAllNames();
		translateTypeVars(host, sup);
		MethodDecl md_copy = (MethodDecl)fullCopy();
		md.replaceWith(md_copy);
		if(onlyAbstract)
			md.makeAbstract();
		sup.insertUnusedMethod(md);
		if(!md.isAbstract() || md_copy.isAbstract())
			host.removeBodyDecl(md_copy);
		programRoot().flushCaches();
	}

    // Declared in PullUpMethod.jrag at line 20

	
	public void doPullUp(boolean onlyAbstract) {
		pullUp(onlyAbstract);
		programRoot().eliminate(LOCKED_NAMES);
	}

    // Declared in PullUpMethod.jrag at line 25

	
	public void doPullUp() {
		doPullUp(false);
	}

    // Declared in FoldSynchronized.jrag at line 5

	/* The implementation of this aspect cannot currently be distributed
	 * together with the rest of the refactoring framework due to 
	 * licensing issues. This file only contains stubs. */
	public void foldSynchronized() {}

    // Declared in PushDownMethod.jrag at line 2

	public void pushDown(boolean leaveAbstract) {
		if(!fromSource())
			throw new RefactoringException("cannot push library method");
		if(isPrivate() || isStatic())
			pushDownMono(leaveAbstract);
		else
			pushDownVirtual(leaveAbstract);
	}

    // Declared in PushDownMethod.jrag at line 11

	
	public void pushDownMono(boolean leaveAbstract) {
		MethodDecl md = this;
		md.unfoldSynchronized();
		for(TypeDecl child : hostType().childTypes()) {
			if(!child.localMethodsSignature(md.signature()).isEmpty() || md.isStatic() && child.isInnerClass())
				throw new RefactoringException("cannot insert method here");
			md.lockAllNames();
			MethodDecl md2 = md;
			md = (MethodDecl)md.fullCopy();
			md2.replaceWith(md);
			md.programRoot().lockMethodNames(md.name());
			child.addBodyDecl(md2);
			md2.foldSynchronized();
			md.programRoot().flushCaches();
			md.programRoot().eliminateLockedNames();
		}
		md.removeUnused(leaveAbstract);
	}

    // Declared in PushDownMethod.jrag at line 30

	
	public void pushDownVirtual(boolean leaveAbstract) {
		ASTNode parent = getParent();
		int idx = parent.getIndexOfChild(this);
		MethodDecl md = this;
		for(TypeDecl child : hostType().childTypes()) {
			MethodAccess ma = child.triviallyOverrideInheritedMethod(md);
			if(ma != null) {
				MethodDecl pushed_down = (MethodDecl)ma.hostBodyDecl();
				ma.inline();
				pushed_down.foldSynchronized();
			}
			md = (MethodDecl)parent.getChild(idx);
			child.eliminate(WITH_STMT, FRESH_VARIABLES);
		}
		md.removeUnused(leaveAbstract);
	}

    // Declared in PushDownMethod.jrag at line 47

	
	public void doPushDown(boolean leaveAbstract) {
		Program root = programRoot();
		pushDown(leaveAbstract);
		root.eliminate(RETURN_VOID, LOCKED_NAMES, LOCKED_DATAFLOW);
	}

    // Declared in UnfoldSynchronized.jrag at line 5

	/* The implementation of this aspect cannot currently be distributed
	 * together with the rest of the refactoring framework due to 
	 * licensing issues. This file only contains stubs. */
	public void unfoldSynchronized() { }

    // Declared in RenameMethod.jrag at line 7

	// rename a method and all its relatives
	public void rename(String new_name) {
		if(name().equals(new_name))
			return;
		if(!isValidName(new_name))
			throw new RefactoringException("not a valid name: "+new_name);
		programRoot().lockMethodNames(name(), new_name);
		programRoot().lockOverridingDependencies(name(), new_name);
		for(MethodDecl md : relatives()) {
			md.checkRenamingPreconds(new_name);
			md.setID(new_name);
		}
		programRoot().eliminate(LOCKED_NAMES, LOCKED_OVERRIDING);
	}

    // Declared in RenameMethod.jrag at line 21

	
	private void checkRenamingPreconds(String new_name) {
		if(!fromSource() || isNative())
			throw new RefactoringException("cannot rename");
		String sig = signature();
		int idx = sig.indexOf('(');
		String new_sig = new_name + sig.substring(idx);
		if(!hostType().canIntroduceMethod(this, new_sig))
			throw new RefactoringException("cannot introduce new method");
	}

    // Declared in RenameMethod.jrag at line 81

	
	private HashSet<SavedMethodDecl> lockedOverridingDependencies = null;

    // Declared in RenameMethod.jrag at line 82

	public void lockOverridingDependencies(Collection<String> names) {
		if(names.contains(name())) {
			lockedOverridingDependencies = new HashSet<SavedMethodDecl>();
			for(MethodDecl md : overrides())
				lockedOverridingDependencies.add(md.save());
		}
	}

    // Declared in RenameMethod.jrag at line 98

	public void unlockOverriding() {
		if(lockedOverridingDependencies != null) {
			HashSet<MethodDecl> old_overrides = new HashSet<MethodDecl>();
			for(SavedMethodDecl md : lockedOverridingDependencies)
				old_overrides.add(md.getDecl());
			lockedOverridingDependencies = null;
			if(!old_overrides.equals(overrides()))
				throw new RefactoringException("overriding has changed");
		}
	}

    // Declared in RenameType.jrag at line 78

    
    public boolean containsNativeMethod() {
    	return isNative() || super.containsNativeMethod();
    }

    // Declared in Testing.jrag at line 248

	
	public MethodDecl findMethod(String name) {
		if(name().equals(name))
			return this;
		return super.findMethod(name);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 88

    public MethodDecl() {
        super();

        setChild(new List(), 2);
        setChild(new List(), 3);
        setChild(new Opt(), 4);

    }

    // Declared in java.ast at line 13


    // Declared in java.ast line 88
    public MethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
    }

    // Declared in java.ast at line 23


    // Declared in java.ast line 88
    public MethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
    }

    // Declared in java.ast at line 32


  protected int numChildren() {
    return 5;
  }

    // Declared in java.ast at line 35

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
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
    // Declared in java.ast line 88
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
    // Declared in java.ast line 88
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
    // Declared in java.ast line 88
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 2);
    }

    // Declared in java.ast at line 6


    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = (parent == null || state == null) ? getParameterListNoTransform() : getParameterList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addParameterNoTransform(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in java.ast at line 31

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        List<ParameterDeclaration> list = (List<ParameterDeclaration>)getChild(2);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(2);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
    public void setExceptionList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in java.ast at line 6


    public int getNumException() {
        return getExceptionList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Access getException(int i) {
        return (Access)getExceptionList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addException(Access node) {
        List<Access> list = (parent == null || state == null) ? getExceptionListNoTransform() : getExceptionList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addExceptionNoTransform(Access node) {
        List<Access> list = getExceptionListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setException(Access node, int i) {
        List<Access> list = getExceptionList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Access> getExceptions() {
        return getExceptionList();
    }

    // Declared in java.ast at line 31

    public List<Access> getExceptionsNoTransform() {
        return getExceptionListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionList() {
        List<Access> list = (List<Access>)getChild(3);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
    public void setBlockOpt(Opt<Block> opt) {
        setChild(opt, 4);
    }

    // Declared in java.ast at line 6


    public boolean hasBlock() {
        return getBlockOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Block getBlock() {
        return (Block)getBlockOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setBlock(Block node) {
        getBlockOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getBlockOpt() {
        return (Opt<Block>)getChild(4);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getBlockOptNoTransform() {
        return (Opt<Block>)getChildNoTransform(4);
    }

    // Declared in GenericsExt.jrag at line 2

	  Opt substituteBody(Parameterization parTypeDecl) {
		return (Opt)getBlockOpt().substituteAll(parTypeDecl);
	}

    // Declared in LookupMethod.jrag at line 148
private boolean refined_MethodDecl_MethodDecl_moreSpecificThan_MethodDecl(MethodDecl m)
{
    if(getNumParameter() == 0)
      return false;
    for(int i = 0; i < getNumParameter(); i++) {
      if(!getParameter(i).type().instanceOf(m.getParameter(i).type()))
        return false;
    }
    return true;
  }

    // Declared in Call.jadd at line 25

	
	public void setParameter(String name, ParameterDeclaration pd) {
		setParameter(pd, getIndexOfParameter(name));
	}

    // Declared in Call.jadd at line 29

	
	public void insertParameter(ParameterDeclaration pd, int i) {
		getParameterList().insertChild(pd, i);
	}

    // Declared in Call.jadd at line 33

	
	public void removeParameter(int i) {
		getParameterList().removeChild(i);
	}

    // Declared in Call.jadd at line 37

	
	public int getIndexOfParameter(ParameterDeclaration pd) {
		for(int i=0;i<getNumParameter();++i)
			if(getParameter(i) == pd)
				return i;
		return -1;
	}

    // Declared in Call.jadd at line 51

	
	public ParameterDeclaration getParameter(String name) {
		if(getIndexOfParameter(name) == -1)
			return null;
		return getParameter(getIndexOfParameter(name));
	}

    // Declared in FreshVariables.jrag at line 126

	
	public void collectAllDecls(Collection<Declaration> res) {
		res.add(this);
		super.collectAllDecls(res);
	}

    // Declared in Visibility.jrag at line 19

	
	public int getVisibility() {
		if(isPrivate()) return VIS_PRIVATE;
		if(isProtected()) return VIS_PROTECTED;
		if(isPublic()) return VIS_PUBLIC;
		return VIS_PACKAGE;
	}

    protected java.util.Map accessibleFrom_TypeDecl_values;
    // Declared in AccessControl.jrag at line 77
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
    if(isPublic()) {
      return true;
    }
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

    // Declared in DataStructures.jrag at line 152
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        int size_value = size_compute();
        return size_value;
    }

    private int size_compute() {  return 1;  }

    // Declared in DataStructures.jrag at line 153
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    // Declared in DataStructures.jrag at line 157
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        ASTNode$State state = state();
        boolean contains_Object_value = contains_compute(o);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

    // Declared in ErrorCheck.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public int lineNumber() {
        ASTNode$State state = state();
        int lineNumber_value = lineNumber_compute();
        return lineNumber_value;
    }

    private int lineNumber_compute() {  return getLine(IDstart);  }

    protected java.util.Map throwsException_TypeDecl_values;
    // Declared in ExceptionHandling.jrag at line 123
 @SuppressWarnings({"unchecked", "cast"})     public boolean throwsException(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(throwsException_TypeDecl_values == null) throwsException_TypeDecl_values = new java.util.HashMap(4);
        if(throwsException_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)throwsException_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean throwsException_TypeDecl_value = throwsException_compute(exceptionType);
        if(isFinal && num == state().boundariesCrossed)
            throwsException_TypeDecl_values.put(_parameters, Boolean.valueOf(throwsException_TypeDecl_value));
        return throwsException_TypeDecl_value;
    }

    private boolean throwsException_compute(TypeDecl exceptionType) {
    for(int i = 0; i < getNumException(); i++)
      if(exceptionType.instanceOf(getException(i).type()))
        return true;
    return false;
  }

    // Declared in LookupMethod.jrag at line 131
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected boolean signature_computed = false;
    protected String signature_value;
    // Declared in MethodSignature.jrag at line 347
 @SuppressWarnings({"unchecked", "cast"})     public String signature() {
        if(signature_computed) {
            return signature_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        signature_value = signature_compute();
        if(isFinal && num == state().boundariesCrossed)
            signature_computed = true;
        return signature_value;
    }

    private String signature_compute() {
    StringBuffer s = new StringBuffer();
    s.append(name() + "(");
    for(int i = 0; i < getNumParameter(); i++) {
      if(i != 0) s.append(", ");
      s.append(getParameter(i).type().erasure().typeName());
    }
    s.append(")");
    return s.toString();

  }

    // Declared in LookupMethod.jrag at line 146
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSignature(MethodDecl m) {
        ASTNode$State state = state();
        boolean sameSignature_MethodDecl_value = sameSignature_compute(m);
        return sameSignature_MethodDecl_value;
    }

    private boolean sameSignature_compute(MethodDecl m) {  return signature().equals(m.signature());  }

    protected java.util.Map moreSpecificThan_MethodDecl_values;
    // Declared in MethodSignature.jrag at line 155
 @SuppressWarnings({"unchecked", "cast"})     public boolean moreSpecificThan(MethodDecl m) {
        Object _parameters = m;
if(moreSpecificThan_MethodDecl_values == null) moreSpecificThan_MethodDecl_values = new java.util.HashMap(4);
        if(moreSpecificThan_MethodDecl_values.containsKey(_parameters)) {
            return ((Boolean)moreSpecificThan_MethodDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean moreSpecificThan_MethodDecl_value = moreSpecificThan_compute(m);
        if(isFinal && num == state().boundariesCrossed)
            moreSpecificThan_MethodDecl_values.put(_parameters, Boolean.valueOf(moreSpecificThan_MethodDecl_value));
        return moreSpecificThan_MethodDecl_value;
    }

    private boolean moreSpecificThan_compute(MethodDecl m) {
    if(!isVariableArity() && !m.isVariableArity())
      return refined_MethodDecl_MethodDecl_moreSpecificThan_MethodDecl(m);
    int num = Math.max(getNumParameter(), m.getNumParameter());
    for(int i = 0; i < num; i++) {
      TypeDecl t1 = i < getNumParameter() - 1 ? getParameter(i).type() : getParameter(getNumParameter()-1).type().componentType();
      TypeDecl t2 = i < m.getNumParameter() - 1 ? m.getParameter(i).type() : m.getParameter(m.getNumParameter()-1).type().componentType();
      if(!t1.instanceOf(t2))
        return false;
    }
    return true;
  }

    protected java.util.Map overrides_MethodDecl_values;
    // Declared in LookupMethod.jrag at line 189
 @SuppressWarnings({"unchecked", "cast"})     public boolean overrides(MethodDecl m) {
        Object _parameters = m;
if(overrides_MethodDecl_values == null) overrides_MethodDecl_values = new java.util.HashMap(4);
        if(overrides_MethodDecl_values.containsKey(_parameters)) {
            return ((Boolean)overrides_MethodDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean overrides_MethodDecl_value = overrides_compute(m);
        if(isFinal && num == state().boundariesCrossed)
            overrides_MethodDecl_values.put(_parameters, Boolean.valueOf(overrides_MethodDecl_value));
        return overrides_MethodDecl_value;
    }

    private boolean overrides_compute(MethodDecl m) {  return !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType()) && 
     hostType().instanceOf(m.hostType()) && m.signature().equals(signature());  }

    protected java.util.Map hides_MethodDecl_values;
    // Declared in LookupMethod.jrag at line 193
 @SuppressWarnings({"unchecked", "cast"})     public boolean hides(MethodDecl m) {
        Object _parameters = m;
if(hides_MethodDecl_values == null) hides_MethodDecl_values = new java.util.HashMap(4);
        if(hides_MethodDecl_values.containsKey(_parameters)) {
            return ((Boolean)hides_MethodDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean hides_MethodDecl_value = hides_compute(m);
        if(isFinal && num == state().boundariesCrossed)
            hides_MethodDecl_values.put(_parameters, Boolean.valueOf(hides_MethodDecl_value));
        return hides_MethodDecl_value;
    }

    private boolean hides_compute(MethodDecl m) {  return isStatic() && !m.isPrivate() && m.accessibleFrom(hostType()) && 
     hostType().instanceOf(m.hostType()) && m.signature().equals(signature());  }

    protected java.util.Map parameterDeclaration_String_values;
    // Declared in LookupVariable.jrag at line 99
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet parameterDeclaration(String name) {
        Object _parameters = name;
if(parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
        if(parameterDeclaration_String_values.containsKey(_parameters)) {
            return (SimpleSet)parameterDeclaration_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
        return parameterDeclaration_String_value;
    }

    private SimpleSet parameterDeclaration_compute(String name) {
    for(int i = 0; i < getNumParameter(); i++)
      if(getParameter(i).name().equals(name))
        return (ParameterDeclaration)getParameter(i);
    return SimpleSet.emptySet;
  }

    // Declared in Modifiers.jrag at line 213
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        boolean isSynthetic_value = isSynthetic_compute();
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }

    // Declared in Modifiers.jrag at line 222
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        ASTNode$State state = state();
        boolean isPublic_value = isPublic_compute();
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic() || hostType().isInterfaceDecl();  }

    // Declared in Modifiers.jrag at line 223
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        ASTNode$State state = state();
        boolean isPrivate_value = isPrivate_compute();
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }

    // Declared in Modifiers.jrag at line 224
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        ASTNode$State state = state();
        boolean isProtected_value = isProtected_compute();
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return getModifiers().isProtected();  }

    // Declared in Modifiers.jrag at line 225
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        ASTNode$State state = state();
        boolean isAbstract_value = isAbstract_compute();
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return getModifiers().isAbstract() || hostType().isInterfaceDecl();  }

    // Declared in Modifiers.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        boolean isStatic_value = isStatic_compute();
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return getModifiers().isStatic();  }

    // Declared in Modifiers.jrag at line 228
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        boolean isFinal_value = isFinal_compute();
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal() || hostType().isFinal() || isPrivate();  }

    // Declared in Modifiers.jrag at line 229
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynchronized() {
        ASTNode$State state = state();
        boolean isSynchronized_value = isSynchronized_compute();
        return isSynchronized_value;
    }

    private boolean isSynchronized_compute() {  return getModifiers().isSynchronized();  }

    // Declared in Modifiers.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNative() {
        ASTNode$State state = state();
        boolean isNative_value = isNative_compute();
        return isNative_value;
    }

    private boolean isNative_compute() {  return getModifiers().isNative();  }

    // Declared in Modifiers.jrag at line 231
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStrictfp() {
        ASTNode$State state = state();
        boolean isStrictfp_value = isStrictfp_compute();
        return isStrictfp_value;
    }

    private boolean isStrictfp_compute() {  return getModifiers().isStrictfp();  }

    // Declared in PrettyPrint.jadd at line 813
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 269
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

    // Declared in TypeAnalysis.jrag at line 272
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        boolean isVoid_value = isVoid_compute();
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return type().isVoid();  }

    // Declared in GenericMethods.jrag at line 84
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayOverrideReturn(MethodDecl m) {
        ASTNode$State state = state();
        boolean mayOverrideReturn_MethodDecl_value = mayOverrideReturn_compute(m);
        return mayOverrideReturn_MethodDecl_value;
    }

    private boolean mayOverrideReturn_compute(MethodDecl m) {
    return type().instanceOf(m.type());
  }

    // Declared in Annotations.jrag at line 139
 @SuppressWarnings({"unchecked", "cast"})     public boolean annotationMethodOverride() {
        ASTNode$State state = state();
        boolean annotationMethodOverride_value = annotationMethodOverride_compute();
        return annotationMethodOverride_value;
    }

    private boolean annotationMethodOverride_compute() {  return !hostType().ancestorMethods(signature()).isEmpty();  }

    // Declared in Annotations.jrag at line 285
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasAnnotationSuppressWarnings(String s) {
        ASTNode$State state = state();
        boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
        return hasAnnotationSuppressWarnings_String_value;
    }

    private boolean hasAnnotationSuppressWarnings_compute(String s) {  return getModifiers().hasAnnotationSuppressWarnings(s);  }

    // Declared in Annotations.jrag at line 323
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDeprecated() {
        ASTNode$State state = state();
        boolean isDeprecated_value = isDeprecated_compute();
        return isDeprecated_value;
    }

    private boolean isDeprecated_compute() {  return getModifiers().hasDeprecatedAnnotation();  }

    protected boolean usesTypeVariable_computed = false;
    protected boolean usesTypeVariable_value;
    // Declared in Generics.jrag at line 907
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

    private boolean usesTypeVariable_compute() {  return getModifiers().usesTypeVariable() || getTypeAccess().usesTypeVariable() ||
    getParameterList().usesTypeVariable() || getExceptionList().usesTypeVariable();  }

    protected boolean sourceMethodDecl_computed = false;
    protected MethodDecl sourceMethodDecl_value;
    // Declared in Generics.jrag at line 1273
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl sourceMethodDecl() {
        if(sourceMethodDecl_computed) {
            return sourceMethodDecl_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        sourceMethodDecl_value = sourceMethodDecl_compute();
        if(isFinal && num == state().boundariesCrossed)
            sourceMethodDecl_computed = true;
        return sourceMethodDecl_value;
    }

    private MethodDecl sourceMethodDecl_compute() {  return this;  }

    // Declared in GenericsParTypeDecl.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public boolean visibleTypeParameters() {
        ASTNode$State state = state();
        boolean visibleTypeParameters_value = visibleTypeParameters_compute();
        return visibleTypeParameters_value;
    }

    private boolean visibleTypeParameters_compute() {  return !isStatic();  }

    // Declared in MethodSignature.jrag at line 284
 @SuppressWarnings({"unchecked", "cast"})     public int arity() {
        ASTNode$State state = state();
        int arity_value = arity_compute();
        return arity_value;
    }

    private int arity_compute() {  return getNumParameter();  }

    // Declared in VariableArityParameters.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariableArity() {
        ASTNode$State state = state();
        boolean isVariableArity_value = isVariableArity_compute();
        return isVariableArity_value;
    }

    private boolean isVariableArity_compute() {  return getNumParameter() == 0 ? false : getParameter(getNumParameter()-1).isVariableArity();  }

    // Declared in VariableArityParameters.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration lastParameter() {
        ASTNode$State state = state();
        ParameterDeclaration lastParameter_value = lastParameter_compute();
        return lastParameter_value;
    }

    private ParameterDeclaration lastParameter_compute() {  return getParameter(getNumParameter() - 1);  }

    protected boolean uncheckedExceptionExit_computed = false;
    protected Stmt uncheckedExceptionExit_value;
    // Declared in ControlFlowGraph.jrag at line 707
 @SuppressWarnings({"unchecked", "cast"})     public Stmt uncheckedExceptionExit() {
        if(uncheckedExceptionExit_computed) {
            return uncheckedExceptionExit_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        uncheckedExceptionExit_value = uncheckedExceptionExit_compute();
        uncheckedExceptionExit_value.setParent(this);
        uncheckedExceptionExit_value.is$Final = true;
        if(true)
            uncheckedExceptionExit_computed = true;
        return uncheckedExceptionExit_value;
    }

    private Stmt uncheckedExceptionExit_compute() {  return uncheckedExceptionExit;  }

    // Declared in MethodExt.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean isUnknown() {
        ASTNode$State state = state();
        boolean isUnknown_value = isUnknown_compute();
        return isUnknown_value;
    }

    private boolean isUnknown_compute() {  return hostType().isUnknown();  }

    protected boolean isCallable_computed = false;
    protected boolean isCallable_value;
    // Declared in MethodExt.jrag at line 12
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCallable() {
        if(isCallable_computed) {
            return isCallable_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isCallable_value = isCallable_compute();
        if(isFinal && num == state().boundariesCrossed)
            isCallable_computed = true;
        return isCallable_value;
    }

    private boolean isCallable_compute() {  return !monoUses().isEmpty() || isDynamicallyCallable();  }

    protected boolean monoUses_computed = false;
    protected Collection<MethodAccess> monoUses_value;
    // Declared in MethodExt.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public Collection<MethodAccess> monoUses() {
        if(monoUses_computed) {
            return monoUses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        monoUses_value = monoUses_compute();
        if(isFinal && num == state().boundariesCrossed)
            monoUses_computed = true;
        return monoUses_value;
    }

    private Collection<MethodAccess> monoUses_compute() {
		Collection<MethodAccess> res = new HashSet<MethodAccess>();
		for(MethodAccess ma : uses())
			if(ma.isMonoCall())
				res.add(ma);
		return res;
	}

    protected boolean isDynamicallyCallable_computed = false;
    protected boolean isDynamicallyCallable_value;
    // Declared in MethodExt.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDynamicallyCallable() {
        if(isDynamicallyCallable_computed) {
            return isDynamicallyCallable_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isDynamicallyCallable_value = isDynamicallyCallable_compute();
        if(isFinal && num == state().boundariesCrossed)
            isDynamicallyCallable_computed = true;
        return isDynamicallyCallable_value;
    }

    private boolean isDynamicallyCallable_compute() {
		if(isStatic() || isPrivate())
			return false;
		// not if neither this method nor a method it overrides is ever called
		if(polyUses().isEmpty())
			return false;
		// not if no object of a type that inherits this method is ever constructed
		for(TypeDecl td : inheritingTypes())
			if(td.instancesAreConstructed())
				return true;
		return false;
	}

    protected boolean inheritingTypes_computed = false;
    protected Collection<TypeDecl> inheritingTypes_value;
    // Declared in MethodExt.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public Collection<TypeDecl> inheritingTypes() {
        if(inheritingTypes_computed) {
            return inheritingTypes_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        inheritingTypes_value = inheritingTypes_compute();
        if(isFinal && num == state().boundariesCrossed)
            inheritingTypes_computed = true;
        return inheritingTypes_value;
    }

    private Collection<TypeDecl> inheritingTypes_compute() {
		if(isStatic() || isPrivate())
			return Collections.singleton(hostType());
		Collection<TypeDecl> result = new HashSet<TypeDecl>();
		LinkedList<TypeDecl> worklist = new LinkedList<TypeDecl>();
		worklist.add(hostType());
		while(!worklist.isEmpty()) {
			TypeDecl td = worklist.pop();
			result.add(td);
			for(TypeDecl child : td.childTypes())
				if(!child.overrides(this))
					worklist.push(child);
		}
		return result;
	}

    // Declared in MethodExt.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public boolean canBeAbstract() {
        ASTNode$State state = state();
        boolean canBeAbstract_value = canBeAbstract_compute();
        return canBeAbstract_value;
    }

    private boolean canBeAbstract_compute() {  return !(isStatic() || isPrivate() || isFinal());  }

    protected boolean rootdef_computed = false;
    protected MethodDecl rootdef_value;
    // Declared in MethodExt.jrag at line 120
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl rootdef() {
        if(rootdef_computed) {
            return rootdef_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        rootdef_value = rootdef_compute();
        if(isFinal && num == state().boundariesCrossed)
            rootdef_computed = true;
        return rootdef_value;
    }

    private MethodDecl rootdef_compute() {
		if(isPrivate() || isStatic())
			return this;
		if(!hostType().isClassDecl())
			throw new Error("undefined operation");
		return hostType().rootdef(signature(), isPackageProtected());
	}

    protected boolean relatives_computed = false;
    protected Collection<MethodDecl> relatives_value;
    // Declared in MethodExt.jrag at line 150
 @SuppressWarnings({"unchecked", "cast"})     public Collection<MethodDecl> relatives() {
        if(relatives_computed) {
            return relatives_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        relatives_value = relatives_compute();
        if(isFinal && num == state().boundariesCrossed)
            relatives_computed = true;
        return relatives_value;
    }

    private Collection<MethodDecl> relatives_compute() {
		if(isPrivate() || isStatic())
			return Collections.singleton(this);
		Collection<MethodDecl> res = new HashSet<MethodDecl>();
		if(hostType().isInterfaceDecl()) {
			LinkedList<InterfaceDecl> worklist = new LinkedList<InterfaceDecl>();
			worklist.add((InterfaceDecl)hostType());
			while(!worklist.isEmpty()) {
				InterfaceDecl iface = worklist.pop();
				for(Iterator<MethodDecl> iter=iface.localMethodsSignature(signature()).iterator();iter.hasNext();)
					res.add(iter.next());
				for(TypeDecl child : iface.childTypes()) {
					if(child.isInterfaceDecl()) {
						worklist.push((InterfaceDecl)child);
					} else {
						ClassDecl cd = (ClassDecl)child;
						for(MethodDecl md : (Collection<MethodDecl>)cd.memberMethods(name()))
							if(md.signature().equals(this.signature()))
								((ClassDecl)md.rootdef().hostType()).collectRelatives(this, res);
					}
				}
			}
		} else {
			((ClassDecl)rootdef().hostType()).collectRelatives(this, res);
		}
		return res;
	}

    protected boolean isCallableFromOutside_computed = false;
    protected boolean isCallableFromOutside_value;
    // Declared in MethodExt.jrag at line 237
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCallableFromOutside() {
        if(isCallableFromOutside_computed) {
            return isCallableFromOutside_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isCallableFromOutside_value = isCallableFromOutside_compute();
        if(isFinal && num == state().boundariesCrossed)
            isCallableFromOutside_computed = true;
        return isCallableFromOutside_value;
    }

    private boolean isCallableFromOutside_compute() {
		if(isDynamicallyCallable()) {
			for(MethodAccess ma : polyUses())
				if(!ma.isDescendantTo(getBlock()))
					return true;
		} else {
			for(MethodAccess ma : monoUses())
				if(!ma.isDescendantTo(getBlock()))
					return true;
		}
		return false;
	}

    protected boolean isReferencedFromOutside_computed = false;
    protected boolean isReferencedFromOutside_value;
    // Declared in MethodExt.jrag at line 251
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReferencedFromOutside() {
        if(isReferencedFromOutside_computed) {
            return isReferencedFromOutside_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isReferencedFromOutside_value = isReferencedFromOutside_compute();
        if(isFinal && num == state().boundariesCrossed)
            isReferencedFromOutside_computed = true;
        return isReferencedFromOutside_value;
    }

    private boolean isReferencedFromOutside_compute() {
		for(MethodAccess ma : uses())
			if(!ma.isDescendantTo(getBlock()))
				return true;
		return false;
	}

    // Declared in ModifiersExt.jrag at line 12
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPackageProtected() {
        ASTNode$State state = state();
        boolean isPackageProtected_value = isPackageProtected_compute();
        return isPackageProtected_value;
    }

    private boolean isPackageProtected_compute() {  return !isPublic() && !isPrivate() && !isProtected();  }

    protected java.util.Map canOverrideOrHide_MethodDecl_values;
    // Declared in Overriding.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean canOverrideOrHide(MethodDecl md) {
        Object _parameters = md;
if(canOverrideOrHide_MethodDecl_values == null) canOverrideOrHide_MethodDecl_values = new java.util.HashMap(4);
        if(canOverrideOrHide_MethodDecl_values.containsKey(_parameters)) {
            return ((Boolean)canOverrideOrHide_MethodDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean canOverrideOrHide_MethodDecl_value = canOverrideOrHide_compute(md);
        if(isFinal && num == state().boundariesCrossed)
            canOverrideOrHide_MethodDecl_values.put(_parameters, Boolean.valueOf(canOverrideOrHide_MethodDecl_value));
        return canOverrideOrHide_MethodDecl_value;
    }

    private boolean canOverrideOrHide_compute(MethodDecl md) {
		// this code is copy-n-pasted from TypeHierarchyCheck.jrag sans error messages
		if(!isStatic() && !md.isPrivate() && 
				md.accessibleFrom(hostType()) && hostType().instanceOf(md.hostType())) {
			if(md.isStatic())
				return false;
			if(!mayOverrideReturn(md))
				return false;
			for(int i = 0; i < getNumException(); i++) {
				Access e = getException(i);
				boolean found = false;
				for(int j = 0; !found && j < md.getNumException(); j++) {
					if(e.type().instanceOf(md.getException(j).type()))
						found = true;
				}
				if(!found && e.type().isUncheckedException())
					return false;
			}
			if(md.isPublic() && !isPublic() ||
		       md.isProtected() && !(isPublic() || isProtected()) ||
		       !md.isPrivate() && !md.isProtected() && !md.isPublic() && isPrivate())
				return false;
			if(md.isFinal())
				return false;
		} else if(isStatic() && !md.isPrivate() && 
				md.accessibleFrom(hostType()) && hostType().instanceOf(md.hostType())) {
			if(!md.isStatic())
				return false;
			if(type() != md.type())
				return false;
			for(int i = 0; i < getNumException(); i++) {
				Access e = getException(i);
				boolean found = false;
				for(int j = 0; !found && j < md.getNumException(); j++) {
					if(e.type().instanceOf(md.getException(j).type()))
						found = true;
				}
				if(!found)
					return false;
			}
			if(md.isPublic() && !isPublic() ||
			   md.isProtected() && !(isPublic() || isProtected()) ||
			   !md.isPrivate() && !md.isProtected() && !md.isPublic() && isPrivate())
				return false;
			if(md.isFinal())
				return false;
		}
		return true;
	}

    protected boolean overrides_computed = false;
    protected HashSet<MethodDecl> overrides_value;
    // Declared in Overriding.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public HashSet<MethodDecl> overrides() {
        if(overrides_computed) {
            return overrides_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        overrides_value = overrides_compute();
        if(isFinal && num == state().boundariesCrossed)
            overrides_computed = true;
        return overrides_value;
    }

    private HashSet<MethodDecl> overrides_compute() {
		HashSet<MethodDecl> ret = new HashSet<MethodDecl>();
		if(this.isPrivate() || this.isStatic())
			return ret;
		for(Iterator<MethodDecl> iter=hostType().ancestorMethods(signature()).iterator();
			iter.hasNext();) {
			MethodDecl md = iter.next();
			if(overrides(md))
				ret.add(md);
		}
		return ret;
	}

    protected java.util.Map isGetterFor_FieldDeclaration_values;
    // Declared in VariableExt.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public boolean isGetterFor(FieldDeclaration fd) {
        Object _parameters = fd;
if(isGetterFor_FieldDeclaration_values == null) isGetterFor_FieldDeclaration_values = new java.util.HashMap(4);
        if(isGetterFor_FieldDeclaration_values.containsKey(_parameters)) {
            return ((Boolean)isGetterFor_FieldDeclaration_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isGetterFor_FieldDeclaration_value = isGetterFor_compute(fd);
        if(isFinal && num == state().boundariesCrossed)
            isGetterFor_FieldDeclaration_values.put(_parameters, Boolean.valueOf(isGetterFor_FieldDeclaration_value));
        return isGetterFor_FieldDeclaration_value;
    }

    private boolean isGetterFor_compute(FieldDeclaration fd) {
		if(getNumParameter() != 0 || type() != fd.type() || !hasBlock())
			return false;
		Block body = getBlock();
		if(body.getNumStmt() != 1 || !(body.getStmt(0) instanceof ReturnStmt))
			return false;
		ReturnStmt ret = (ReturnStmt)body.getStmt(0);
		return ret.hasResult() && ret.getResult().isFieldAccessInThis(fd);
	}

    protected boolean isConstantMethod_computed = false;
    protected boolean isConstantMethod_value;
    // Declared in DataFlow.jrag at line 120
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstantMethod() {
        if(isConstantMethod_computed) {
            return isConstantMethod_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isConstantMethod_value = isConstantMethod_compute();
        if(isFinal && num == state().boundariesCrossed)
            isConstantMethod_computed = true;
        return isConstantMethod_value;
    }

    private boolean isConstantMethod_compute() {
		if(!isStatic() && !isPrivate() || !hasBlock())
			return false;
		Block body = getBlock();
		if(body.getNumStmt() != 1)
			return false;
		if(!(body.getStmt(0) instanceof ReturnStmt))
			return false;
		ReturnStmt ret = (ReturnStmt)body.getStmt(0);
		return !ret.hasResult() || ret.getResult().isConstant() || ret.getResult() instanceof NullLiteral;
	}

    // Declared in AnonymousMethods.jrag at line 260
 @SuppressWarnings({"unchecked", "cast"})     public AnonymousMethod asAnonymousMethod() {
        ASTNode$State state = state();
        AnonymousMethod asAnonymousMethod_value = asAnonymousMethod_compute();
        return asAnonymousMethod_value;
    }

    private AnonymousMethod asAnonymousMethod_compute() {
		return new AnonymousMethod(getParameterList(),
								   new List<TypeVariable>(),
								   getTypeAccess(),
								   getExceptions(),
								   getBlock(),
								   new List<Expr>());
	}

    // Declared in AccessVariable.jrag at line 126
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSourceDeclAs(MethodDecl decl) {
        ASTNode$State state = state();
        boolean sameSourceDeclAs_MethodDecl_value = sameSourceDeclAs_compute(decl);
        return sameSourceDeclAs_MethodDecl_value;
    }

    private boolean sameSourceDeclAs_compute(MethodDecl decl) {  return sourceMethodDecl().equals(decl.sourceMethodDecl());  }

    // Declared in Testing.jrag at line 34
 @SuppressWarnings({"unchecked", "cast"})     public String ppID() {
        ASTNode$State state = state();
        String ppID_value = ppID_compute();
        return ppID_value;
    }

    private String ppID_compute() {  return name();  }

    protected java.util.Map getIndexOfParameter_String_values;
    // Declared in Call.jadd at line 44
 @SuppressWarnings({"unchecked", "cast"})     public int getIndexOfParameter(String name) {
        Object _parameters = name;
if(getIndexOfParameter_String_values == null) getIndexOfParameter_String_values = new java.util.HashMap(4);
        if(getIndexOfParameter_String_values.containsKey(_parameters)) {
            return ((Integer)getIndexOfParameter_String_values.get(_parameters)).intValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        int getIndexOfParameter_String_value = getIndexOfParameter_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            getIndexOfParameter_String_values.put(_parameters, Integer.valueOf(getIndexOfParameter_String_value));
        return getIndexOfParameter_String_value;
    }

    private int getIndexOfParameter_compute(String name) {
		for(int i=0; i<getNumParameter(); ++i)
			if(getParameter(i).name().equals(name))
				return i;
		return -1;
	}

    // Declared in AccessVariable.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasVariable(String name) {
        ASTNode$State state = state();
        boolean hasVariable_String_value = hasVariable_compute(name);
        return hasVariable_String_value;
    }

    private boolean hasVariable_compute(String name) {  return !parameterDeclaration(name).isEmpty();  }

    protected java.util.Map accessParameter_Variable_values;
    // Declared in AccessVariable.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessParameter(Variable decl) {
        Object _parameters = decl;
if(accessParameter_Variable_values == null) accessParameter_Variable_values = new java.util.HashMap(4);
        if(accessParameter_Variable_values.containsKey(_parameters)) {
            return (SymbolicVarAccess)accessParameter_Variable_values.get(_parameters);
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SymbolicVarAccess accessParameter_Variable_value = accessParameter_compute(decl);
        if(isFinal && num == state().boundariesCrossed)
            accessParameter_Variable_values.put(_parameters, accessParameter_Variable_value);
        return accessParameter_Variable_value;
    }

    private SymbolicVarAccess accessParameter_compute(Variable decl) {
		for(ParameterDeclaration pd : getParameterList())
			if(pd == decl)
				return pd;
		return unknownVarAccess();
	}

    protected java.util.Map handlesException_TypeDecl_values;
    // Declared in ExceptionHandling.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
        if(handlesException_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        if(isFinal && num == state().boundariesCrossed)
            handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
        return handlesException_TypeDecl_value;
    }

    // Declared in LookupMethod.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl unknownMethod() {
        ASTNode$State state = state();
        MethodDecl unknownMethod_value = getParent().Define_MethodDecl_unknownMethod(this, null);
        return unknownMethod_value;
    }

    // Declared in AccessVariable.jrag at line 118
 @SuppressWarnings({"unchecked", "cast"})     public UnknownVarAccess unknownVarAccess() {
        ASTNode$State state = state();
        UnknownVarAccess unknownVarAccess_value = getParent().Define_UnknownVarAccess_unknownVarAccess(this, null);
        return unknownVarAccess_value;
    }

    // Declared in DefiniteAssignment.jrag at line 438
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockOptNoTransform()) {
            return v.isFinal() && (v.isClassVariable() || v.isInstanceVariable()) ? true : isDAbefore(v);
        }
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 872
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockOptNoTransform()) {
            return v.isFinal() && (v.isClassVariable() || v.isInstanceVariable()) ? false : true;
        }
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in ExceptionHandling.jrag at line 120
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        if(caller == getBlockOptNoTransform()) {
            return throwsException(exceptionType) || handlesException(exceptionType);
        }
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }

    // Declared in LookupVariable.jrag at line 46
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return parameterDeclaration(name);
        }
        if(caller == getBlockOptNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    // A declaration of a method parameter name shadows any other variable declarations
    if(!set.isEmpty()) return set;
    // Delegate to other declarations in scope
    return lookupVariable(name);
  }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Modifiers.jrag at line 269
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePublic(this, caller);
    }

    // Declared in Modifiers.jrag at line 270
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }

    // Declared in Modifiers.jrag at line 271
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }

    // Declared in Modifiers.jrag at line 272
    public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeAbstract(this, caller);
    }

    // Declared in Modifiers.jrag at line 273
    public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeStatic(this, caller);
    }

    // Declared in Modifiers.jrag at line 274
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Modifiers.jrag at line 275
    public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeSynchronized(this, caller);
    }

    // Declared in Modifiers.jrag at line 276
    public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeNative(this, caller);
    }

    // Declared in Modifiers.jrag at line 277
    public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeStrictfp(this, caller);
    }

    // Declared in NameCheck.jrag at line 241
    public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return this;
        }
        return getParent().Define_ASTNode_enclosingBlock(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 82
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExceptionListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeCheck.jrag at line 405
    public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return type();
        }
        return getParent().Define_TypeDecl_returnType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 142
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return isStatic();
        }
        return getParent().Define_boolean_inStaticContext(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 33
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 84
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 85
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 86
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in Annotations.jrag at line 86
    public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
        if(caller == getModifiersNoTransform()) {
            return name.equals("METHOD");
        }
        return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
    }

    // Declared in VariableArityParameters.jrag at line 22
    public boolean Define_boolean_variableArityValid(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i == getNumParameter() - 1;
        }
        return getParent().Define_boolean_variableArityValid(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 145
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return SmallSet.<CFGNode>singleton(exit());
        }
        if(caller == getParameterListNoTransform()) { 
   int i = caller.getIndexOfChild(child);
{
		if(i + 1 < getNumParameter())
			return SmallSet.<CFGNode>singleton(getParameter(i + 1).first());
		return hasBlock() ? SmallSet.<CFGNode>singleton(getBlock().first()) : SmallSet.<CFGNode>singleton(exit());
	}
}
        if(caller == entry_value){
{
		if(getNumParameter() > 0)
			return SmallSet.<CFGNode>singleton(getParameter(0).first());
		return hasBlock() ? SmallSet.<CFGNode>singleton(getBlock().first()) : SmallSet.<CFGNode>singleton(exit());
	}
}
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in ControlFlowGraph.jrag at line 683
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__throwTarget(ASTNode caller, ASTNode child, TypeDecl exn) {
        if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
		// this selects (somewhat arbitrarily) the left-most matching exception type
		for(Access acc : getExceptions())
			if(exn.instanceOf(acc.type()))
				return SmallSet.<CFGNode>singleton(acc);
		if(exn.isCheckedException())	// JastAddJ's definition of checked/unchecked is confused...
			return SmallSet.<CFGNode>singleton(uncheckedExceptionExit());
		return SmallSet.empty();
	}
}
        return super.Define_SmallSet_CFGNode__throwTarget(caller, child, exn);
    }

    // Declared in ControlFlowGraph.jrag at line 692
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__uncheckedExnTarget(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(uncheckedExceptionExit());
        }
        return super.Define_SmallSet_CFGNode__uncheckedExnTarget(caller, child);
    }

    // Declared in ModifiersExt.jrag at line 7
    public Variable Define_Variable_getModifiedVariable(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return null;
        }
        return getParent().Define_Variable_getModifiedVariable(this, caller);
    }

    // Declared in ParameterExt.jrag at line 38
    public Parameterisable Define_Parameterisable_parameterOwner(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return this;
        }
        return getParent().Define_Parameterisable_parameterOwner(this, caller);
    }

    // Declared in AccessVariable.jrag at line 162
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return accessParameter(decl);
        }
        if(caller == getBlockOptNoTransform()){
		SymbolicVarAccess acc = accessParameter(decl);
		if(!acc.isUnknownVarAccess()) return acc;
		return accessVariable(decl).moveInto(this);
	}
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

    // Declared in InsertEmptyStmt.jrag at line 54
    public Stmt Define_Stmt_insertStmtBefore(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getBlockOptNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtBefore(this, caller, s);
    }

    // Declared in InsertEmptyStmt.jrag at line 115
    public Stmt Define_Stmt_insertStmtAfter(ASTNode caller, ASTNode child, Stmt s) {
        if(caller == getBlockOptNoTransform()){ throw new RefactoringException("cannot insert statement here"); }
        return getParent().Define_Stmt_insertStmtAfter(this, caller, s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected boolean MethodDecl_coll_overriders_computed = false;
    protected HashSet<MethodDecl> MethodDecl_coll_overriders_value;
    // Declared in Overriding.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public HashSet<MethodDecl> coll_overriders() {
        if(MethodDecl_coll_overriders_computed) {
            return MethodDecl_coll_overriders_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        MethodDecl_coll_overriders_value = coll_overriders_compute();
        if(isFinal && num == state().boundariesCrossed)
            MethodDecl_coll_overriders_computed = true;
        return MethodDecl_coll_overriders_value;
    }

    java.util.Set MethodDecl_coll_overriders_contributors;
    public java.util.Set MethodDecl_coll_overriders_contributors() {
        if(MethodDecl_coll_overriders_contributors == null) MethodDecl_coll_overriders_contributors  = new ASTNode$State.IdentityHashSet(4);
        return MethodDecl_coll_overriders_contributors;
    }
    private HashSet<MethodDecl> coll_overriders_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof Program))
            node = node.getParent();
        Program root = (Program)node;
        root.collect_contributors_MethodDecl_coll_overriders();
        MethodDecl_coll_overriders_value = new HashSet<MethodDecl>();
        if(MethodDecl_coll_overriders_contributors != null)
        for(java.util.Iterator iter = MethodDecl_coll_overriders_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_MethodDecl_MethodDecl_coll_overriders(MethodDecl_coll_overriders_value);
        }
        return MethodDecl_coll_overriders_value;
    }

    protected boolean MethodDecl_polyUses_computed = false;
    protected Collection<MethodAccess> MethodDecl_polyUses_value;
    // Declared in Uses.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public Collection<MethodAccess> polyUses() {
        if(MethodDecl_polyUses_computed) {
            return MethodDecl_polyUses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        MethodDecl_polyUses_value = polyUses_compute();
        if(isFinal && num == state().boundariesCrossed)
            MethodDecl_polyUses_computed = true;
        return MethodDecl_polyUses_value;
    }

    java.util.Set MethodDecl_polyUses_contributors;
    public java.util.Set MethodDecl_polyUses_contributors() {
        if(MethodDecl_polyUses_contributors == null) MethodDecl_polyUses_contributors  = new ASTNode$State.IdentityHashSet(4);
        return MethodDecl_polyUses_contributors;
    }
    private Collection<MethodAccess> polyUses_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof Program))
            node = node.getParent();
        Program root = (Program)node;
        root.collect_contributors_MethodDecl_polyUses();
        MethodDecl_polyUses_value = new HashSet<MethodAccess>();
        if(MethodDecl_polyUses_contributors != null)
        for(java.util.Iterator iter = MethodDecl_polyUses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_MethodDecl_MethodDecl_polyUses(MethodDecl_polyUses_value);
        }
        return MethodDecl_polyUses_value;
    }

    protected boolean MethodDecl_uses_computed = false;
    protected Collection<MethodAccess> MethodDecl_uses_value;
    // Declared in Uses.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public Collection<MethodAccess> uses() {
        if(MethodDecl_uses_computed) {
            return MethodDecl_uses_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        MethodDecl_uses_value = uses_compute();
        if(isFinal && num == state().boundariesCrossed)
            MethodDecl_uses_computed = true;
        return MethodDecl_uses_value;
    }

    java.util.Set MethodDecl_uses_contributors;
    public java.util.Set MethodDecl_uses_contributors() {
        if(MethodDecl_uses_contributors == null) MethodDecl_uses_contributors  = new ASTNode$State.IdentityHashSet(4);
        return MethodDecl_uses_contributors;
    }
    private Collection<MethodAccess> uses_compute() {
        ASTNode node = this;
        while(node.getParent() != null && !(node instanceof Program))
            node = node.getParent();
        Program root = (Program)node;
        root.collect_contributors_MethodDecl_uses();
        MethodDecl_uses_value = new HashSet<MethodAccess>();
        if(MethodDecl_uses_contributors != null)
        for(java.util.Iterator iter = MethodDecl_uses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_MethodDecl_MethodDecl_uses(MethodDecl_uses_value);
        }
        return MethodDecl_uses_value;
    }

    protected void collect_contributors_MethodDecl_coll_overriders() {
        // Declared in Overriding.jrag at line 54
        for(java.util.Iterator iter = (overrides()).iterator(); iter.hasNext(); ) {
            MethodDecl ref = (MethodDecl)iter.next();
            if(ref != null)
            ref.MethodDecl_coll_overriders_contributors().add(this);
        }
        super.collect_contributors_MethodDecl_coll_overriders();
    }
    protected void contributeTo_MethodDecl_MethodDecl_coll_overriders(HashSet<MethodDecl> collection) {
        super.contributeTo_MethodDecl_MethodDecl_coll_overriders(collection);
        collection.add(this);
    }

}
