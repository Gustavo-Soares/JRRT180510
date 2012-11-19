
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public class LocalClassDeclStmt extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_values = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalClassDeclStmt clone() throws CloneNotSupportedException {
        LocalClassDeclStmt node = (LocalClassDeclStmt)super.clone();
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalClassDeclStmt copy() {
      try {
          LocalClassDeclStmt node = (LocalClassDeclStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalClassDeclStmt fullCopy() {
        LocalClassDeclStmt res = (LocalClassDeclStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 739

  
  public void toString(StringBuffer s) {
    getClassDecl().toString(s);
  }

    // Declared in CloseOverVariables.jrag at line 3

	// closing a local class over local variables and type variables
	public void closeOverLocalVariables() {
		Map<LocalVariable, FieldDeclaration> fmap = new HashMap<LocalVariable, FieldDeclaration>();
		for(VarAccess va : localVarAccesses())
			getClassDecl().closeOver(va, fmap);		
	}

    // Declared in CloseOverVariables.jrag at line 41

	
	public ClassDecl closeOverTypeVariables() {
		Map<TypeVariable, TypeVariable> tvmap = new HashMap<TypeVariable, TypeVariable>();
		ArrayList<TypeVariable> tvs = new ArrayList<TypeVariable>();
		ClassDecl cd = getClassDecl();
		Collection<Access> cd_uses = cd.uses();
		for(TypeAccess ta : typeVarAccesses()) {
			TypeVariable tv = (TypeVariable)ta.decl();
			if(tv.owner() == enclosingBodyDecl()) {
				TypeVariable tv2 = tvmap.get(tv);
				if(tv2 == null) {
					if(!cd.isGenericType())
						setClassDecl(cd = new GenericClassDecl(cd.getModifiers(), cd.getID(), cd.getSuperClassAccessOpt(), cd.getImplementsList(), cd.getBodyDeclList(), new List<TypeVariable>()));
					GenericClassDecl gcd = (GenericClassDecl)cd;
					gcd.addTypeParameter(tv2 = (TypeVariable)tv.fullCopy());
					tvmap.put(tv, tv2);
					tvs.add(tv);
				}
				ta.lock(tv2);
			}
		}
		if(tvs.size() > 0) {
			for(Access tacc : cd_uses) {
				if(tacc instanceof ParTypeAccess) {
					ParTypeAccess ptacc = (ParTypeAccess)tacc;
					for(TypeVariable tv : tvs)
						ptacc.addTypeArgument(tv.createLockedAccess());
				} else {
					List<Access> parms = new List<Access>();
					for(TypeVariable tv : tvs)
						parms.add(tv.createLockedAccess());
					int idx = tacc.getChildIndex();
					ASTNode parent = tacc.getParent();
					parent.setChild(new ParTypeAccess(tacc, parms), idx);
				}
			}
			enclosingBodyDecl().flushCaches();
		}
		return cd;
	}

    // Declared in LocalClassToMemberClass.jrag at line 2

	public MemberTypeDecl promoteToMemberClass(boolean makePublic) {
		Program root = programRoot();
		ClassDecl cd = getClassDecl();
		// close over accesses to local variables
		cd = closeOverTypeVariables();
		closeOverLocalVariables();
		if(!cd.enclosingType().isLocalClass() && !cd.enclosingType().isAnonymous())
			cd.setVisibility(makePublic ? VIS_PUBLIC : VIS_PRIVATE);
		if(cd.inStaticContext())
			cd.getModifiers().addModifier("static");
		// lock names
		root.lockNames(cd.name());
		cd.lockAllNames();
		// move
		MemberTypeDecl mtd = cd.enclosingType().insertUnusedType(cd, 0);
		enclosingBlock().removeStmt(this);
		return mtd;
	}

    // Declared in LocalClassToMemberClass.jrag at line 21

	
	public MemberTypeDecl doPromoteToMemberClass(boolean makePublic) {
		Program root = programRoot();
		MemberTypeDecl mtd = promoteToMemberClass(makePublic);
		root.eliminate(FRESH_VARIABLES, LOCKED_NAMES);
		return mtd;
	}

    // Declared in LocalClassToMemberClass.jrag at line 28

	
	public MemberTypeDecl doPromoteToMemberClass() {
		return doPromoteToMemberClass(false);
	}

    // Declared in Testing.jrag at line 194

	
	public LocalClassDeclStmt findLocalClass(String name) {
		if(getClassDecl().name().equals(name))
			return this;
		return super.findLocalClass(name);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 227

    public LocalClassDeclStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 227
    public LocalClassDeclStmt(ClassDecl p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 227
    public void setClassDecl(ClassDecl node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public ClassDecl getClassDecl() {
        return (ClassDecl)getChild(0);
    }

    // Declared in java.ast at line 9


    public ClassDecl getClassDeclNoTransform() {
        return (ClassDecl)getChildNoTransform(0);
    }

    // Declared in DefiniteAssignment.jrag at line 491
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

    private boolean isDAafter_compute(Variable v) {  return isDAbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 877
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

    private boolean isDUafter_compute(Variable v) {  return isDUbefore(v);  }

    // Declared in UnreachableStatements.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public boolean canCompleteNormally() {
        if(canCompleteNormally_computed) {
            return canCompleteNormally_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        canCompleteNormally_value = canCompleteNormally_compute();
        if(isFinal && num == state().boundariesCrossed)
            canCompleteNormally_computed = true;
        return canCompleteNormally_value;
    }

    private boolean canCompleteNormally_compute() {  return reachable();  }

    // Declared in TypeAnalysis.jrag at line 544
    public boolean Define_boolean_isLocalClass(ASTNode caller, ASTNode child) {
        if(caller == getClassDeclNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_isLocalClass(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
