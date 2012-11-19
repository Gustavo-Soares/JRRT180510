
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class FreshParameter extends ParameterDeclaration implements Cloneable, FreshVariable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshParameter clone() throws CloneNotSupportedException {
        FreshParameter node = (FreshParameter)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshParameter copy() {
      try {
          FreshParameter node = (FreshParameter)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshParameter fullCopy() {
        FreshParameter res = (FreshParameter)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in FreshVariables.jrag at line 11

	
	public FreshParameter(Access type, String name) {
		this(new Modifiers(new List()), type, name);
	}

    // Declared in FreshVariables.jrag at line 37

	
	public ParameterDeclaration eliminateFreshVariables() {
		String new_name = pickFreshName();
		ParameterDeclaration pd = new ParameterDeclaration(getModifiers(), getTypeAccess(), new_name);
		for(VarAccess va : uses()) {
			assert va.isLocked();
			va.lock(pd);
		}
		return pd;
	}

    // Declared in FreshVariables.jrag at line 79

	
	public String pickFreshName() {
		String fresh_name = getID();
		Collection<Declaration> decls = hostBodyDecl().allDecls();
		if(conflicts(fresh_name, decls)) {
			for(int i=0;;++i) {
				fresh_name = getID()+i;
				if(!conflicts(fresh_name, decls))
					break;
			}
		}
		return fresh_name;
	}

    // Declared in FreshVariables.ast at line 3
    // Declared in FreshVariables.ast line 1

    public FreshParameter() {
        super();


    }

    // Declared in FreshVariables.ast at line 10


    // Declared in FreshVariables.ast line 1
    public FreshParameter(Modifiers p0, Access p1, String p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in FreshVariables.ast at line 17


    // Declared in FreshVariables.ast line 1
    public FreshParameter(Modifiers p0, Access p1, beaver.Symbol p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in FreshVariables.ast at line 23


  protected int numChildren() {
    return 2;
  }

    // Declared in FreshVariables.ast at line 26

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
    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 5

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 12

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in FreshVariables.jrag at line 19

	
	public boolean canIntroduceLocal(String name) {
		return true;
	}

    // Declared in FreshVariables.jrag at line 23

	
	public String name() {
		return "#fresh#" + super.name();
	}

    // Declared in FreshVariables.jrag at line 107

	
	// check whether the name conflicts with any of the decls
	public boolean conflicts(String name, Collection<Declaration> decls) {
		for(Declaration decl : decls)
			if(decl != this && !(decl instanceof MethodDecl) && decl.name().equals(name))
				return true;
		return false;
	}

    // Declared in FreshVariables.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public Variable refreshVariable() {
        ASTNode$State state = state();
        Variable refreshVariable_value = refreshVariable_compute();
        return refreshVariable_value;
    }

    private Variable refreshVariable_compute() {  return this;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
