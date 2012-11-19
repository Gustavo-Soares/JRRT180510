
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public abstract class Access extends Expr implements Cloneable, Definition {
    public void flushCache() {
        super.flushCache();
        prevExpr_computed = false;
        prevExpr_value = null;
        hasPrevExpr_computed = false;
        type_computed = false;
        type_value = null;
        getLocation_computed = false;
        getLocation_value = null;
        qualifiesMethodAccess_computed = false;
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
     @SuppressWarnings({"unchecked", "cast"})  public Access clone() throws CloneNotSupportedException {
        Access node = (Access)super.clone();
        node.prevExpr_computed = false;
        node.prevExpr_value = null;
        node.hasPrevExpr_computed = false;
        node.type_computed = false;
        node.type_value = null;
        node.getLocation_computed = false;
        node.getLocation_value = null;
        node.qualifiesMethodAccess_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in ResolveAmbiguousNames.jrag at line 148


  public Access addArrayDims(List list) {
    Access a = this;
    for(int i = 0; i < list.getNumChildNoTransform(); i++) {
      Dims dims = (Dims)list.getChildNoTransform(i);
      Opt opt = dims.getExprOpt();
      if(opt.getNumChildNoTransform() == 1)
        a = new ArrayTypeWithSizeAccess(a, (Expr)opt.getChildNoTransform(0));
      else
        a = new ArrayTypeAccess(a);
      a.setStart(dims.start());
      a.setEnd(dims.end());
    }
    return a;
  }

    // Declared in ReachingDefinitions.jrag at line 10

	
	public SmallSet<Definition> reachingDefinitions() {
		Location l = getLocation();
		if(!isSource() || l == null)
			return SmallSet.<Definition>empty();
		// we don't want the node to be its own reaching definition
		SmallSet<Definition> res = SmallSet.<Definition>empty();
		for(CFGNode p : pred())
			res = res.union(p.reachingDefinitionsFor(l));
		return res;
	}

    // Declared in ReachingDefinitions.jrag at line 34

	
	// for a write, returns the set of writes to the same variable preceding it in the CFG
	public SmallSet<Definition> writeDeps() {
		Location l = getLocation();
		if(!isDest() || l == null)
			return SmallSet.<Definition>empty();
		// we don't want the node to write-depend on itself
		SmallSet<Definition> res = SmallSet.<Definition>empty();
		for(CFGNode p : pred())
			res = res.union(p.reachingDefinitionsFor(l));
		return res;
	}

    // Declared in AST.jrag at line 84

	
	protected void qualifyWith(Expr qual) {
		ASTNode parent = getParent();
		int idx = parent.getIndexOfChild(this);
		parent.setChild(qual.qualifiesAccess(this), idx);
	}

    // Declared in AST.jrag at line 142

	
	/* f.bundleQualifier() does the following:
	 * 
	 * 
	 *      .                    .
	 *     / \                  / \
	 *    A   .        ~>      .   f
	 *       / \              / \
	 *     this f            A this
	 *     
	 *     
	 *      .                    .
	 *     / \                  / \
	 *    A   .                .   g
	 *       / \       ~>     / \
	 *     this .            .   f
	 *         / \          / \
	 *        f   g        A this
	 *        
	 */
	public void bundleQualifier() {
		if(isLeftChildOfDot()) {
			AbstractDot parent = parentDot();
			if(!parent.isRightChildOfDot())
				return;
			AbstractDot g = new AbstractDot(null, parent.getRight());
			AbstractDot p = parent.parentDot();
			p.setRight(this);
			while(p.isRightChildOfDot())
				p = p.parentDot();
			p.rotateLeft();
			p.replaceWith(g);
			g.setLeft(p);
		} else if(isRightChildOfDot()) {
			AbstractDot p = parentDot();
			while(p.isRightChildOfDot())
				p = p.parentDot();
			p.rotateLeft();
		}
	}

    // Declared in DataFlow.jrag at line 11

	
	protected SmallSet<Definition> lockedReachingDefs = null;

    // Declared in DataFlow.jrag at line 12

	protected SmallSet<Access> lockedReachedUses = null;

    // Declared in DataFlow.jrag at line 13

	protected SmallSet<Definition> lockedWriteDeps = null;

    // Declared in DataFlow.jrag at line 15

	
	public void lockReachingDefs() {
		lockReachingDefs(0);
	}

    // Declared in DataFlow.jrag at line 18

	public void lockReachingDefs(int l) {
		if(lockedReachingDefs == null) {
			lockedReachingDefs = reachingDefinitions();
			if(l > 0)
				for(Definition d : lockedReachingDefs)
					((ASTNode)d).lockDataFlow(l-1);
		}
	}

    // Declared in DataFlow.jrag at line 27

	
	public void lockReachedUses(int l) {
		if(lockedReachedUses == null) {
			lockedReachedUses = reachedUses();
			if(l > 0)
				for(Access a : lockedReachedUses)
					a.lockDataFlow(l-1);
		}
	}

    // Declared in DataFlow.jrag at line 36

	
	public void lockReachingDefsAndUses(int l) {
		lockReachingDefs(l);
		lockReachedUses(l);
	}

    // Declared in DataFlow.jrag at line 41

	
	public void lockWriteDeps() {
		if(lockedWriteDeps == null)
			lockedWriteDeps = writeDeps();
	}

    // Declared in DataFlow.jrag at line 79

	
	public void unlockDataFlow() {
		if(hostType() != null)
			hostType().flushCollectionCache();
		if(lockedReachingDefs != null) {
			if(!reachingDefinitions().equals(lockedReachingDefs))
				throw new RefactoringException("couldn't preserve data flow");
			lockedReachingDefs = null;
		}
		if(lockedReachedUses != null) {
			if(!reachedUses().equals(lockedReachedUses))
				throw new RefactoringException("couldn't preserve data flow");
			lockedReachedUses = null;
		}
		if(lockedWriteDeps != null) {
			if(!writeDeps().equals(lockedWriteDeps))
				throw new RefactoringException("couldn't preserve data flow");
			lockedWriteDeps = null;
		}
	}

    // Declared in AnonymousMethods.jrag at line 44

	
	public boolean isDeclaredClosureException() {
		if(!(getParent().getParent() instanceof AnonymousMethod))
			return false;
		return ((AnonymousMethod)getParent().getParent()).getExceptions() == getParent();
	}

    // Declared in LockedTypeAccess.jrag at line 136

	
	public ASTNode lock() { return this; }

    // Declared in java.ast at line 3
    // Declared in java.ast line 11

    public Access() {
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

    // Declared in LookupMethod.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public Expr unqualifiedScope() {
        ASTNode$State state = state();
        Expr unqualifiedScope_value = unqualifiedScope_compute();
        return unqualifiedScope_value;
    }

    private Expr unqualifiedScope_compute() {  return isQualified() ? nestedScope() : this;  }

    // Declared in ResolveAmbiguousNames.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public boolean isQualified() {
        ASTNode$State state = state();
        boolean isQualified_value = isQualified_compute();
        return isQualified_value;
    }

    private boolean isQualified_compute() {  return hasPrevExpr();  }

    // Declared in ResolveAmbiguousNames.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public Expr qualifier() {
        ASTNode$State state = state();
        Expr qualifier_value = qualifier_compute();
        return qualifier_value;
    }

    private Expr qualifier_compute() {  return prevExpr();  }

    // Declared in ResolveAmbiguousNames.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public Access lastAccess() {
        ASTNode$State state = state();
        Access lastAccess_value = lastAccess_compute();
        return lastAccess_value;
    }

    private Access lastAccess_compute() {  return this;  }

    protected boolean prevExpr_computed = false;
    protected Expr prevExpr_value;
    // Declared in ResolveAmbiguousNames.jrag at line 78
 @SuppressWarnings({"unchecked", "cast"})     public Expr prevExpr() {
        if(prevExpr_computed) {
            return prevExpr_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        prevExpr_value = prevExpr_compute();
        if(isFinal && num == state().boundariesCrossed)
            prevExpr_computed = true;
        return prevExpr_value;
    }

    private Expr prevExpr_compute() {
    if(isLeftChildOfDot()) {
      if(parentDot().isRightChildOfDot())
        return parentDot().parentDot().leftSide();
    }
    else if(isRightChildOfDot())
      return parentDot().leftSide();
    throw new Error(this + " does not have a previous expression");
  }

    protected boolean hasPrevExpr_computed = false;
    protected boolean hasPrevExpr_value;
    // Declared in ResolveAmbiguousNames.jrag at line 89
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasPrevExpr() {
        if(hasPrevExpr_computed) {
            return hasPrevExpr_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        hasPrevExpr_value = hasPrevExpr_compute();
        if(isFinal && num == state().boundariesCrossed)
            hasPrevExpr_computed = true;
        return hasPrevExpr_value;
    }

    private boolean hasPrevExpr_compute() {
    if(isLeftChildOfDot()) {
      if(parentDot().isRightChildOfDot())
        return true;
    }
    else if(isRightChildOfDot())
      return true;
    return false;
  }

    // Declared in SyntacticClassification.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.NO_NAME;  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 278
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

    private TypeDecl type_compute() {  return unknownType();  }

    protected boolean getLocation_computed = false;
    protected Location getLocation_value;
    // Declared in Alias.jrag at line 14
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

    private Location getLocation_compute() {  return null;  }

    // Declared in Alias.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayReferTo(Location l) {
        ASTNode$State state = state();
        boolean mayReferTo_Location_value = mayReferTo_compute(l);
        return mayReferTo_Location_value;
    }

    private boolean mayReferTo_compute(Location l) {  return false;  }

    // Declared in Alias.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public boolean mustReferTo(Location l) {
        ASTNode$State state = state();
        boolean mustReferTo_Location_value = mustReferTo_compute(l);
        return mustReferTo_Location_value;
    }

    private boolean mustReferTo_compute(Location l) {  return false;  }

    // Declared in ReachingDefinitions.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReachingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isReachingDefinitionFor_Location_value = isReachingDefinitionFor_compute(l);
        return isReachingDefinitionFor_Location_value;
    }

    private boolean isReachingDefinitionFor_compute(Location l) {  return isDest() && mayReferTo(l);  }

    // Declared in ReachingDefinitions.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlockingDefinitionFor(Location l) {
        ASTNode$State state = state();
        boolean isBlockingDefinitionFor_Location_value = isBlockingDefinitionFor_compute(l);
        return isBlockingDefinitionFor_Location_value;
    }

    private boolean isBlockingDefinitionFor_compute(Location l) {  return mustReferTo(l);  }

    // Declared in ExprExt.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public Expr enclosingInstanceQualifier(TypeDecl td) {
        ASTNode$State state = state();
        Expr enclosingInstanceQualifier_TypeDecl_value = enclosingInstanceQualifier_compute(td);
        return enclosingInstanceQualifier_TypeDecl_value;
    }

    private Expr enclosingInstanceQualifier_compute(TypeDecl td) {  return hostType().enclosingInstanceQualifier(td, true);  }

    // Declared in Precedence.jrag at line 5
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return 0;  }

    // Declared in Purity.jrag at line 4
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPure() {
        ASTNode$State state = state();
        boolean isPure_value = isPure_compute();
        return isPure_value;
    }

    private boolean isPure_compute() {  return true;  }

    protected boolean qualifiesMethodAccess_computed = false;
    protected boolean qualifiesMethodAccess_value;
    // Declared in With.jrag at line 216
 @SuppressWarnings({"unchecked", "cast"})     public boolean qualifiesMethodAccess() {
        if(qualifiesMethodAccess_computed) {
            return qualifiesMethodAccess_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        qualifiesMethodAccess_value = qualifiesMethodAccess_compute();
        if(isFinal && num == state().boundariesCrossed)
            qualifiesMethodAccess_computed = true;
        return qualifiesMethodAccess_value;
    }

    private boolean qualifiesMethodAccess_compute() {  return hasParentDot() && parentDot().isMethodAccess();  }

    // Declared in LookupMethod.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public Expr nestedScope() {
        ASTNode$State state = state();
        Expr nestedScope_value = getParent().Define_Expr_nestedScope(this, null);
        return nestedScope_value;
    }

    // Declared in LookupType.jrag at line 133
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        ASTNode$State state = state();
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        return unknownType_value;
    }

    // Declared in LookupVariable.jrag at line 228
 @SuppressWarnings({"unchecked", "cast"})     public Variable unknownField() {
        ASTNode$State state = state();
        Variable unknownField_value = getParent().Define_Variable_unknownField(this, null);
        return unknownField_value;
    }

    // Declared in Annotations.jrag at line 268
 @SuppressWarnings({"unchecked", "cast"})     public boolean withinSuppressWarnings(String s) {
        ASTNode$State state = state();
        boolean withinSuppressWarnings_String_value = getParent().Define_boolean_withinSuppressWarnings(this, null, s);
        return withinSuppressWarnings_String_value;
    }

    // Declared in Annotations.jrag at line 372
 @SuppressWarnings({"unchecked", "cast"})     public boolean withinDeprecatedAnnotation() {
        ASTNode$State state = state();
        boolean withinDeprecatedAnnotation_value = getParent().Define_boolean_withinDeprecatedAnnotation(this, null);
        return withinDeprecatedAnnotation_value;
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

    protected void collect_contributors_Definition_reachedUses() {
        // Declared in ReachingDefinitions.jrag at line 31
        for(java.util.Iterator iter = (reachingDefinitions()).iterator(); iter.hasNext(); ) {
            Definition ref = (Definition)iter.next();
            if(ref != null)
            ref.Definition_reachedUses_contributors().add(this);
        }
        super.collect_contributors_Definition_reachedUses();
    }
    protected void contributeTo_Definition_Definition_reachedUses(SmallSet<Access> collection) {
        super.contributeTo_Definition_Definition_reachedUses(collection);
        collection.add(this);
    }

}
