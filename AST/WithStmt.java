
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class WithStmt extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        succ_computed = false;
        succ_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public WithStmt clone() throws CloneNotSupportedException {
        WithStmt node = (WithStmt)super.clone();
        node.succ_computed = false;
        node.succ_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public WithStmt copy() {
      try {
          WithStmt node = (WithStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public WithStmt fullCopy() {
        WithStmt res = (WithStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in With.jrag at line 2

	public WithStmt(Access acc, Block block) {
		this(new List<Access>().add(acc), block);
	}

    // Declared in With.jrag at line 7

	
	// pretty printing
	public void toString(StringBuffer s) {
		s.append(indent());
		s.append("with(");
		for(int i=0;i<getNumQualifier();++i) {
			if(i!=0)
				s.append(", ");
			getQualifier(i).toString(s);
		}
		s.append(") ");
		getStmt().toString(s);
	}

    // Declared in With.jrag at line 97

	
	public Stmt eliminateWith() {
		getStmt().eliminateWith(getQualifiers(), hostType());
		return getStmt();
	}

    // Declared in With.ast at line 3
    // Declared in With.ast line 1

    public WithStmt() {
        super();

        setChild(new List(), 0);

    }

    // Declared in With.ast at line 11


    // Declared in With.ast line 1
    public WithStmt(List<Access> p0, Stmt p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in With.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in With.ast at line 19

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in With.ast at line 2
    // Declared in With.ast line 1
    public void setQualifierList(List<Access> list) {
        setChild(list, 0);
    }

    // Declared in With.ast at line 6


    public int getNumQualifier() {
        return getQualifierList().getNumChild();
    }

    // Declared in With.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Access getQualifier(int i) {
        return (Access)getQualifierList().getChild(i);
    }

    // Declared in With.ast at line 14


    public void addQualifier(Access node) {
        List<Access> list = (parent == null || state == null) ? getQualifierListNoTransform() : getQualifierList();
        list.addChild(node);
    }

    // Declared in With.ast at line 19


    public void addQualifierNoTransform(Access node) {
        List<Access> list = getQualifierListNoTransform();
        list.addChild(node);
    }

    // Declared in With.ast at line 24


    public void setQualifier(Access node, int i) {
        List<Access> list = getQualifierList();
        list.setChild(node, i);
    }

    // Declared in With.ast at line 28

    public List<Access> getQualifiers() {
        return getQualifierList();
    }

    // Declared in With.ast at line 31

    public List<Access> getQualifiersNoTransform() {
        return getQualifierListNoTransform();
    }

    // Declared in With.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getQualifierList() {
        List<Access> list = (List<Access>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in With.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getQualifierListNoTransform() {
        return (List<Access>)getChildNoTransform(0);
    }

    // Declared in With.ast at line 2
    // Declared in With.ast line 1
    public void setStmt(Stmt node) {
        setChild(node, 1);
    }

    // Declared in With.ast at line 5

    public Stmt getStmt() {
        return (Stmt)getChild(1);
    }

    // Declared in With.ast at line 9


    public Stmt getStmtNoTransform() {
        return (Stmt)getChildNoTransform(1);
    }

    // Declared in With.jrag at line 69
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

    private SmallSet<CFGNode> succ_compute() {  return getNumQualifier() == 0 ? SmallSet.<CFGNode>singleton(getStmt()) : SmallSet.<CFGNode>singleton(getQualifier(0).first());  }

    // Declared in With.jrag at line 20
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return getNumQualifier() == 0;
        }
        return getParent().Define_boolean_inStaticContext(this, caller);
    }

    // Declared in With.jrag at line 22
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return getQualifier(getNumQualifier()-1).type();
        }
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in With.jrag at line 24
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtNoTransform()){
		for(int i=getNumQualifier()-1;i>=0;--i) {
			TypeDecl td = getQualifier(i).type();
			SimpleSet c = td.memberFields(name);
			if(td.inStaticContext() || td.isStatic())
				c = removeInstanceVariables(c);
			if(!c.isEmpty())
				return c;
		}
		return removeFields(lookupVariable(name));
	}
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in With.jrag at line 48
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtNoTransform()){
		for(int i=getNumQualifier()-1;i>=0;--i) {
			TypeDecl td = getQualifier(i).type();
			Collection c = td.memberMethods(name);
			if(!c.isEmpty())
				return c;
		}
		return Collections.EMPTY_LIST;
	}
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in With.jrag at line 58
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtNoTransform()){
		for(int i=getNumQualifier()-1;i>=0;--i) {
			SimpleSet c = getQualifier(i).qualifiedLookupType(name);
			if(!c.isEmpty())
				return c;
		}
		// TODO: filter out non-static ones?
		return lookupType(name);
	}
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in With.jrag at line 73
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return following();
        }
        if(caller == getQualifierListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i+1 < getNumQualifier() ? SmallSet.<CFGNode>singleton(getQualifier(i+1).first()) : SmallSet.<CFGNode>singleton(getStmt());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
