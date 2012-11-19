
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class TypeAccess extends Access implements Cloneable, LockableName {
    public void flushCache() {
        super.flushCache();
        decls_computed = false;
        decls_value = null;
        decl_computed = false;
        decl_value = null;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess clone() throws CloneNotSupportedException {
        TypeAccess node = (TypeAccess)super.clone();
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_computed = false;
        node.decl_value = null;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess copy() {
      try {
          TypeAccess node = (TypeAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess fullCopy() {
        TypeAccess res = (TypeAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AccessControl.jrag at line 128

  
  public void accessControl() {
    super.accessControl();
    TypeDecl hostType = hostType();
    if(hostType != null && !hostType.isUnknown() && !type().accessibleFrom(hostType)) {
      error("" + this + " in " + hostType().fullName() + " can not access type " + type().fullName());
    }
    else if((hostType == null || hostType.isUnknown())  && !type().accessibleFromPackage(hostPackage())) {
      error("" + this + " can not access type " + type().fullName());
    }
  }

    // Declared in NameCheck.jrag at line 155

  
  public void nameCheck() {
    if(isQualified() && !qualifier().isTypeAccess() && !qualifier().isPackageAccess())
      error("can not access the type named " + decl().typeName() + " in this context");
    if(decls().isEmpty())
      error("no visible type named " + typeName());
    if(decls().size() > 1) {
      StringBuffer s = new StringBuffer();
      s.append("several types named " + name() + ":");
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        TypeDecl t = (TypeDecl)iter.next();
        s.append(" " + t.typeName());
      }
      error(s.toString());
    }
  }

    // Declared in NodeConstructors.jrag at line 23

  public TypeAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }

    // Declared in NodeConstructors.jrag at line 44


  public TypeAccess(String typeName) {
    this("", typeName);
  }

    // Declared in PrettyPrint.jadd at line 482

  
  public void refined_PrettyPrint_TypeAccess_toString(StringBuffer s) {
    if(decl().isReferenceType())
      s.append(nameWithPackage());
    else
      s.append(decl().name());
  }

    // Declared in Annotations.jrag at line 328


  public void checkModifiers() {
    if(decl().isDeprecated() &&
       !withinDeprecatedAnnotation() &&
       (hostType() == null || hostType().topLevelType() != decl().topLevelType()) &&
       !withinSuppressWarnings("deprecation"))
      warning(decl().typeName() + " has been deprecated");
  }

    // Declared in Generics.jrag at line 269


  // this method assumes that the bound type is generic
  public boolean isRaw() {
    /*
    if(hasNextAccess())
      return false;
    */
    ASTNode parent = getParent();
    while(parent instanceof AbstractDot)
      parent = parent.getParent();
    if(parent instanceof ParTypeAccess)
      return false;
    if(parent instanceof ImportDecl)
      return false;
    /*
    Access a = this;
    while(a.isTypeAccess() && hasNextAccess())
      a = a.nextAccess();
    if(a.isThisAccess() || a.isSuperAccess())
      return false;
    */
    return true;
  }

    // Declared in Generics.jrag at line 409


  public void typeCheck() {
    TypeDecl type = type();
    if(type.isRawType() && type.isNestedType() && type.enclosingType().isParameterizedType() && !type.enclosingType().isRawType())
      error("Can not access a member type of a paramterized type as a raw type");
  }

    // Declared in GenericsExt.jrag at line 17

	
	public ASTNode substituteAll(Parameterization parTypeDecl) {
		if(decl().isTypeVariable())
			return parTypeDecl.substitute((TypeVariable)decl()).createBoundAccess();
		else
			return super.substituteAll(parTypeDecl);
	}

    // Declared in PackageDecl.jrag at line 58

	
	public String getTopLevelPackage() {
		String[] comp = packageName().split("\\.");
		if(comp.length == 0)
			return null;
		return comp[0];
	}

    // Declared in TypeVariableExt.jrag at line 38

	
	public void typeVarAccesses(java.util.Set<TypeAccess> res) {
		if(decl().isTypeVariable())
			res.add(this);
		super.typeVarAccesses(res);
	}

    // Declared in TypeVariableExt.jrag at line 59

	
	protected void collectUsedTypeVars(Collection<TypeVariable> tvars) {
		if(decl().isTypeVariable())
			tvars.add((TypeVariable)decl());
		super.collectUsedTypeVars(tvars);
	}

    // Declared in TypeVariableExt.jrag at line 83

	
	public void translateTypeVars(Map<TypeVariable, TypeVariable> dict) {
		if(decl().isTypeVariable()) {
			TypeVariable tv = (TypeVariable)decl();
			TypeVariable tv2 = dict.get(tv);
			if(tv2 != null)
				lock(tv2);
		}
		super.translateTypeVars(dict);
	}

    // Declared in FreshVariables.jrag at line 143

	
	public void collectAllDecls(Collection<Declaration> res) {
		if(!getPackage().equals(""))
			res.add(lookupPackage(getTopLevelPackage()));
		res.add(decl());
		super.collectAllDecls(res);
	}

    // Declared in LockedPackageAccess.jrag at line 3

	private PackageDecl targetPackage = null;

    // Declared in LockedPackageAccess.jrag at line 26

	
	public ASTNode lockAllPackageAccesses() {
		if(!(this instanceof ArrayTypeAccess) && !getPackage().equals("") && !getPackage().equals("@primitive"))
			lock();
		return super.lockAllPackageAccesses();
	}

    // Declared in LockedTypeAccess.jrag at line 14

	
	/* A locked type access is a type access that does not obey the normal type lookup
	 * rules, but instead immediately binds to its target. */
	private TypeDecl targetType = null;

    // Declared in LockedTypeAccess.jrag at line 17

	
	public boolean isLocked() { return targetType != null; }

    // Declared in LockedTypeAccess.jrag at line 85

	
	public ASTNode lockNames(Collection<String> endangered) {
		if(!isLocked() &&
				(endangered.contains(name()) || endangered.contains(getTopLevelPackage())))
			return lock();
		return super.lockNames(endangered);
	}

    // Declared in LockedTypeAccess.jrag at line 106

	
	public Access eliminateLockedNames() {
		return isLocked() ? unlock(null) : this;
	}

    // Declared in LockedTypeAccess.jrag at line 137

	public ASTNode lock() { return lock(decl());	}

    // Declared in LockedTypeAccess.jrag at line 139

	public ASTNode lock(TypeDecl decl) {
		assert decl != null && !decl.isUnknown();
		targetType = decl;
		return this;
	}

    // Declared in LockedTypeAccess.jrag at line 154

	
	public Access unlock(Expr qual) {
		assert qual == null || qual.isPure();
		TypeDecl target = targetType.refresh();
		targetType = null;
		flushCaches();
		if(!target.hasName())
			throw new RefactoringException("cannot unlock access to nameless type");
		try {
			if(hostType() == null && !target.accessibleFromPackage(hostPackage())
					|| hostType() != null && !target.accessibleFrom(hostType()))
				throw new RefactoringException("inaccessible type");
			if(!fromSource() || hostBodyDecl() instanceof MethodDecl && ((MethodDecl)hostBodyDecl()).isNative()) {
				if(!decl().equals(target))
					throw new RefactoringException("cannot fix access in library");
				return this;
			}
			if(target.isRawType())
				target = target.erasure();
			if(target.isParameterizedType())
				return unlockParTypeAccess((ParTypeDecl)target, qual);
			if(target.isTypeVariable())
				return unlockTypeVariableAccess((TypeVariable)target, qual);
			if(target instanceof PrimitiveType || target instanceof VoidType)
				return unlockPrimitiveTypeAccess(target, qual);
			if(target.isArrayDecl())
				return unlockArrayTypeAccess((ArrayDecl)target, qual);
			if(target.isLocalClass())
				return unlockLocalClassAccess((ClassDecl)target, qual);
			if(target.isMemberType())
				return unlockMemberTypeAccess(target, qual);
			if(target.isTopLevelType())
				return unlockTopLevelTypeAccess(target, qual);
			throw new Error("cannot fix access to this kind of type");
		} finally {
			targetType = null;
		}
	}

    // Declared in LockedTypeAccess.jrag at line 192

	
	protected Access unlockPrimitiveTypeAccess(TypeDecl target, Expr qual) {
		setID(target.name());
		return this;
	}

    // Declared in LockedTypeAccess.jrag at line 197

	
	protected Access unlockTypeVariableAccess(TypeVariable target, Expr qual) {
		if(!lookupName(target.name()).isSingleton(target))
			throw new RefactoringException("cannot access shadowed/obscured type variable");
		setPackage("");
		setID(target.name());
		return this;
	}

    // Declared in LockedTypeAccess.jrag at line 205

	
	protected Access unlockLocalClassAccess(ClassDecl target, Expr qual) {
		if(!lookupName(target.name()).isSingleton(target))
			throw new RefactoringException("cannot access shadowed/obscured local class");
		setPackage("");
		setID(target.name());
		return this;
	}

    // Declared in LockedTypeAccess.jrag at line 213


	protected Access unlockMemberTypeAccess(TypeDecl target, Expr qual) {
		if(qual != null) {
			// try to build an access using the given qualifier
			if(qual.isTypeAccess()) {
				TypeDecl outer = qual.type().refresh();
				if(outer.memberTypes(target.name()).isSingleton(target)) {
					setPackage("");
					setID(target.name());
					return qual.qualifiesAccess(this);
				}
			}
			// we need to throw away the qualifier, but make sure it is pure
			if(!qual.isPure())
				throw new RefactoringException("cannot access member type");
		}
		if(lookupName(target.name()).isSingleton(target)) {
			setPackage("");
			setID(target.name());
			return this;
		} else {
			TypeDecl outer = target.enclosingType();
			if(!target.isStatic() && getParent() instanceof ClassInstanceExpr && hostType().withinBodyThatSubclasses(outer) == null)
				throw new RefactoringException("cannot access non-static member types without enclosing instance in instanceof");
			if(!outer.memberTypes(target.name()).isSingleton(target))
				throw new RefactoringException("cannot access shadowed member type");
			setPackage("");
			setID(target.name());
			// TODO: more thought on when precisely this is necessary
			// (rationale here is that if target is a generic type declaration, we are actually
			// trying to build an access to its raw type)
			if(target.isGenericType() && outer.isGenericType())
				outer = ((GenericTypeDecl)outer).rawType();
			return outer.createLockedAccess().qualifiesAccess(this);
		}
	}

    // Declared in LockedTypeAccess.jrag at line 249

	
	protected Access unlockTopLevelTypeAccess(TypeDecl target, Expr qual) {
		programRoot().flushCaches();
		if(lookupName(target.name()).isSingleton(target)) {
			setPackage("");
			setID(target.name());
			return this;
		}
		setPackage(target.packageName());
		setID(target.name());
		String pkg = getTopLevelPackage();
		SimpleSet s = lookupName(pkg);
		if(!s.isEmpty() && !s.isSingleton(lookupPackage(pkg))
				|| !lookupType(target.packageName(), target.name()).equals(target))
			throw new RefactoringException("cannot access toplevel type");
		return this;
	}

    // Declared in LockedTypeAccess.jrag at line 266

	
	protected Access unlockParTypeAccess(ParTypeDecl target, Expr qual) {
		List<Access> args = new List<Access>();
		for(int i=0;i<target.getNumArgument();++i)
			args.add(target.getArgument(i).type().createLockedAccess());
		Access acc = new ParTypeAccess(((GenericTypeDecl)target.genericDecl()).rawType().createLockedAccess(), args);
		if(qual != null)
			acc = qual.qualifiesAccess(acc);
		return acc;
	}

    // Declared in LockedTypeAccess.jrag at line 276

	
	protected Access unlockArrayTypeAccess(ArrayDecl target, Expr qual) {
		Access acc = target.componentType().createLockedAccess();
		if(qual != null)
			acc = qual.qualifiesAccess(acc);
		return new ArrayTypeAccess(acc);
	}

    // Declared in AddRequiredMembers.jrag at line 30

	
	public void addRequiredMembers(TypeDecl host, java.util.Set<MethodDecl> meths, java.util.Set<FieldDeclaration> fds, java.util.Set<MemberTypeDecl> mtds) {
		if(!decl().isStatic() && decl().enclosingType() == host)
			mtds.add((MemberTypeDecl)decl().getParent());
		super.addRequiredMembers(host, meths, fds, mtds);
	}

    // Declared in Locking.jrag at line 76

	
	public TypeAccess lockedCopy() {
		TypeAccess res = (TypeAccess)super.lockedCopy();
		res.lock(decl());
		return res;
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 20

    public TypeAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 20
    public TypeAccess(String p0, String p1) {
        setPackage(p0);
        setID(p1);
    }

    // Declared in java.ast at line 16


    // Declared in java.ast line 20
    public TypeAccess(beaver.Symbol p0, beaver.Symbol p1) {
        setPackage(p0);
        setID(p1);
    }

    // Declared in java.ast at line 21


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 24

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 20
    protected String tokenString_Package;

    // Declared in java.ast at line 3

    public void setPackage(String value) {
        tokenString_Package = value;
    }

    // Declared in java.ast at line 6

    public int Packagestart;

    // Declared in java.ast at line 7

    public int Packageend;

    // Declared in java.ast at line 8

    public void setPackage(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setPackage is only valid for String lexemes");
        tokenString_Package = (String)symbol.value;
        Packagestart = symbol.getStart();
        Packageend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getPackage() {
        return tokenString_Package != null ? tokenString_Package : "";
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 20
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

    // Declared in LockedTypeAccess.jrag at line 23

	
	  public void toString(StringBuffer s) {
		if(!isLocked()) {
			refined_PrettyPrint_TypeAccess_toString(s);
		} else {
			s.append("[[");
			refined_PrettyPrint_TypeAccess_toString(s);
			s.append("]]");
		}
	}

    // Declared in LookupType.jrag at line 135
private SimpleSet refined_TypeScopePropagation_TypeAccess_decls()
{
    if(packageName().equals(""))
      return lookupType(name());
    else {
      TypeDecl typeDecl = lookupType(packageName(), name());
      if(typeDecl != null)
        return SimpleSet.emptySet.add(typeDecl);
      return SimpleSet.emptySet;
    }
  }

    // Declared in LookupType.jrag at line 150
private TypeDecl refined_TypeScopePropagation_TypeAccess_decl()
{
    SimpleSet decls = decls();
    if(decls.size() == 1) {
      return (TypeDecl)decls.iterator().next();
    }
    return unknownType();
  }

    // Declared in Generics.jrag at line 261
private TypeDecl refined_GenericsTypeAnalysis_TypeAccess_decl()
{
    TypeDecl decl = refined_TypeScopePropagation_TypeAccess_decl();
    if(decl instanceof GenericTypeDecl && isRaw())
      return ((GenericTypeDecl)decl).lookupParTypeDecl(new ArrayList());
    return decl;
  }

    // Declared in Locking.jrag at line 36

	
	public ASTNode lockAllNames() {
		ASTNode res = lock();
		if(res == this)
			return super.lockAllNames();
		else
			return res.lockAllNames();
	}

    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LockedTypeAccess.jrag at line 20
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

    private SimpleSet decls_compute() {  return isLocked() ? targetType : refined_TypeScopePropagation_TypeAccess_decls();  }

    protected boolean decl_computed = false;
    protected TypeDecl decl_value;
    // Declared in LockedTypeAccess.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl decl() {
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

    private TypeDecl decl_compute() {  return isLocked() ? targetType : refined_GenericsTypeAnalysis_TypeAccess_decl();  }

    // Declared in LookupVariable.jrag at line 152
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupVariable(String name) {
        ASTNode$State state = state();
        SimpleSet qualifiedLookupVariable_String_value = qualifiedLookupVariable_compute(name);
        return qualifiedLookupVariable_String_value;
    }

    private SimpleSet qualifiedLookupVariable_compute(String name) {
    if(type().accessibleFrom(hostType())) {
      SimpleSet c = type().memberFields(name);
      c = keepAccessibleFields(c);
      if(type().isClassDecl() && c.size() == 1)
        c = removeInstanceVariables(c);
      return c;
    }
    return SimpleSet.emptySet;
  }

    // Declared in PrettyPrint.jadd at line 803
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getPackage() + ", " + getID() + "]";  }

    // Declared in QualifiedNames.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    // Declared in QualifiedNames.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public String packageName() {
        ASTNode$State state = state();
        String packageName_value = packageName_compute();
        return packageName_value;
    }

    private String packageName_compute() {  return getPackage();  }

    // Declared in QualifiedNames.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public String nameWithPackage() {
        ASTNode$State state = state();
        String nameWithPackage_value = nameWithPackage_compute();
        return nameWithPackage_value;
    }

    private String nameWithPackage_compute() {  return getPackage().equals("") ? name() : (getPackage() + "." + name());  }

    // Declared in QualifiedNames.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        ASTNode$State state = state();
        String typeName_value = typeName_compute();
        return typeName_value;
    }

    private String typeName_compute() {  return isQualified() ? (qualifier().typeName() + "." + name()) : nameWithPackage();  }

    // Declared in ResolveAmbiguousNames.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        ASTNode$State state = state();
        boolean isTypeAccess_value = isTypeAccess_compute();
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return true;  }

    // Declared in SyntacticClassification.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.PACKAGE_OR_TYPE_NAME;  }

    // Declared in TypeAnalysis.jrag at line 279
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

    private TypeDecl type_compute() {  return decl();  }

    // Declared in TypeHierarchyCheck.jrag at line 154
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return true;  }

    // Declared in Generics.jrag at line 911
 @SuppressWarnings({"unchecked", "cast"})     public boolean usesTypeVariable() {
        ASTNode$State state = state();
        boolean usesTypeVariable_value = usesTypeVariable_compute();
        return usesTypeVariable_value;
    }

    private boolean usesTypeVariable_compute() {  return decl().usesTypeVariable() || super.usesTypeVariable();  }

    // Declared in ExprExt.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return true;  }

    // Declared in ExprExt.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFlowInvariant() {
        ASTNode$State state = state();
        boolean isFlowInvariant_value = isFlowInvariant_compute();
        return isFlowInvariant_value;
    }

    private boolean isFlowInvariant_compute() {  return true;  }

    // Declared in AccessVariable.jrag at line 272
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess qualifiedAccessVariable(Variable decl) {
        ASTNode$State state = state();
        SymbolicVarAccess qualifiedAccessVariable_Variable_value = qualifiedAccessVariable_compute(decl);
        return qualifiedAccessVariable_Variable_value;
    }

    private SymbolicVarAccess qualifiedAccessVariable_compute(Variable decl) {
		if(type().accessibleFrom(hostType())) {
			SymbolicFieldAccess acc = type().accessMemberField(decl);
			acc = acc.ensureAccessible(this);
			if(type().isClassDecl())
				acc = acc.ensureStatic();
			return acc;
		}
		return unknownVarAccess();
	}

    // Declared in TypeAccessExt.jrag at line 2
 @SuppressWarnings({"unchecked", "cast"})     public boolean inImportDecl() {
        ASTNode$State state = state();
        boolean inImportDecl_value = getParent().Define_boolean_inImportDecl(this, null);
        return inImportDecl_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected void collect_contributors_TypeDecl_uses() {
        // Declared in Uses.jrag at line 28
        {
            TypeDecl ref = (TypeDecl)(decl());
            if(ref != null)
                ref.TypeDecl_uses_contributors().add(this);
        }
        super.collect_contributors_TypeDecl_uses();
    }
    protected void contributeTo_TypeDecl_TypeDecl_uses(Collection<Access> collection) {
        super.contributeTo_TypeDecl_TypeDecl_uses(collection);
        collection.add(this);
    }

}
