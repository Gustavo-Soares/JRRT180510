
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class PackageDecl extends ASTNode<ASTNode> implements Cloneable, Declaration, SimpleSet, Iterator {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PackageDecl clone() throws CloneNotSupportedException {
        PackageDecl node = (PackageDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PackageDecl copy() {
      try {
          PackageDecl node = (PackageDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PackageDecl fullCopy() {
        PackageDecl res = (PackageDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declaration.jrag at line 11

	
	public String name() {
		return getName();
	}

    // Declared in PackageDecl.jrag at line 37

	public SimpleSet add(Object o) {
		return new SimpleSetImpl().add(this).add(o);
	}

    // Declared in PackageDecl.jrag at line 41

	public boolean isSingleton() { return true; }

    // Declared in PackageDecl.jrag at line 42

	public boolean isSingleton(Object o) { return contains(o); }

    // Declared in PackageDecl.jrag at line 45

	private PackageDecl iterElem;

    // Declared in PackageDecl.jrag at line 46

	public Iterator iterator() { iterElem = this; return this; }

    // Declared in PackageDecl.jrag at line 47

	public boolean hasNext() { return iterElem != null; }

    // Declared in PackageDecl.jrag at line 48

	public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in PackageDecl.jrag at line 49

	public void remove() { throw new UnsupportedOperationException(); }

    // Declared in RenamePackage.jrag at line 3

	public void rename(String new_name) {
		String old_name = getName();
		if(old_name.equals(new_name))
			return;
		if(!isValidPackageName(new_name))
			throw new RefactoringException("not a valid name: "+new_name);
		Program root = programRoot();
		if(root.hasPackage(new_name))
			throw new RefactoringException("package exists");
		root.lockAllPackageAccesses();
		setName(new_name);
		for(Iterator<CompilationUnit> iter=root.compilationUnitIterator();iter.hasNext();) {
			CompilationUnit cu = iter.next();
			if(cu.getPackageDecl().equals(old_name))
				cu.setPackageDecl(new_name);
			else if(cu.getPackageDecl().startsWith(old_name + "."))
				cu.setPackageDecl(new_name + "." + cu.getPackageDecl().substring(old_name.length()+1));
		}
		for(PackageDecl subpd : root.getSubPackageDecls(old_name))
			subpd.setName(new_name + "." + subpd.getName().substring(old_name.length()+1));
		root.flushCaches();
		if(!root.typeWithSameNameAsPackage().isEmpty())
			throw new RefactoringException("name clash between member type and subpackage");
		root.eliminate(LOCKED_NAMES);
	}

    // Declared in PackageDecl.ast at line 3
    // Declared in PackageDecl.ast line 1

    public PackageDecl() {
        super();

        is$Final(true);

    }

    // Declared in PackageDecl.ast at line 11


    // Declared in PackageDecl.ast line 1
    public PackageDecl(String p0) {
        setName(p0);
        is$Final(true);
    }

    // Declared in PackageDecl.ast at line 17


    // Declared in PackageDecl.ast line 1
    public PackageDecl(beaver.Symbol p0) {
        setName(p0);
        is$Final(true);
    }

    // Declared in PackageDecl.ast at line 22


  protected int numChildren() {
    return 0;
  }

    // Declared in PackageDecl.ast at line 25

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in PackageDecl.ast at line 2
    // Declared in PackageDecl.ast line 1
    protected String tokenString_Name;

    // Declared in PackageDecl.ast at line 3

    public void setName(String value) {
        tokenString_Name = value;
    }

    // Declared in PackageDecl.ast at line 6

    public int Namestart;

    // Declared in PackageDecl.ast at line 7

    public int Nameend;

    // Declared in PackageDecl.ast at line 8

    public void setName(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setName is only valid for String lexemes");
        tokenString_Name = (String)symbol.value;
        Namestart = symbol.getStart();
        Nameend = symbol.getEnd();
    }

    // Declared in PackageDecl.ast at line 15

    public String getName() {
        return tokenString_Name != null ? tokenString_Name : "";
    }

    // Declared in FreshVariables.jrag at line 126

	
	public void collectAllDecls(Collection<Declaration> res) {
		res.add(this);
		super.collectAllDecls(res);
	}

    // Declared in PackageDecl.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        int size_value = size_compute();
        return size_value;
    }

    private int size_compute() {  return 1;  }

    // Declared in PackageDecl.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    // Declared in PackageDecl.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        ASTNode$State state = state();
        boolean contains_Object_value = contains_compute(o);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
