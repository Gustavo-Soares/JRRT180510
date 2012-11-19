
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;



public abstract class AbstractWildcard extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AbstractWildcard clone() throws CloneNotSupportedException {
        AbstractWildcard node = (AbstractWildcard)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in LockedTypeAccess.jrag at line 138

	public ASTNode lock() { return this; }

    // Declared in Generics.ast at line 3
    // Declared in Generics.ast line 17

    public AbstractWildcard() {
        super();


    }

    // Declared in Generics.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in Generics.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in ExprExt.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean notAnObject() {
        ASTNode$State state = state();
        boolean notAnObject_value = notAnObject_compute();
        return notAnObject_value;
    }

    private boolean notAnObject_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
