
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class FreshField extends FieldDeclaration implements Cloneable, FreshVariable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshField clone() throws CloneNotSupportedException {
        FreshField node = (FreshField)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshField copy() {
      try {
          FreshField node = (FreshField)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FreshField fullCopy() {
        FreshField res = (FreshField)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in FreshVariables.jrag at line 57

	
	public FieldDeclaration eliminateFreshVariables() {
		String new_name = pickFreshName();
		FieldDeclaration fd = new FieldDeclaration(getModifiers(), getTypeAccess(), new_name, getInitOpt());
		for(VarAccess va : uses()) {
			assert va.isLocked();
			va.lock(fd);
		}
		return fd;
	}

    // Declared in FreshVariables.jrag at line 67

	
	public String pickFreshName() {
		String fresh_name = getID();
		if(!hostType().localFields(fresh_name).isEmpty()) {
			for(int i=0;;++i) {
				fresh_name = getID()+i;
				if(hostType().localFields(fresh_name).isEmpty())
					break;
			}
		}
		return fresh_name;
	}

    // Declared in FreshVariables.ast at line 3
    // Declared in FreshVariables.ast line 3

    public FreshField() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in FreshVariables.ast at line 11


    // Declared in FreshVariables.ast line 3
    public FreshField(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in FreshVariables.ast at line 19


    // Declared in FreshVariables.ast line 3
    public FreshField(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in FreshVariables.ast at line 26


  protected int numChildren() {
    return 3;
  }

    // Declared in FreshVariables.ast at line 29

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
