
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


public class ExitStmt extends EmptyStmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        reachingDefinitionsFor_Location_values = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExitStmt clone() throws CloneNotSupportedException {
        ExitStmt node = (ExitStmt)super.clone();
        node.reachingDefinitionsFor_Location_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExitStmt copy() {
      try {
          ExitStmt node = (ExitStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExitStmt fullCopy() {
        ExitStmt res = (ExitStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ControlFlowGraph.jrag at line 23

	
	public void toString(StringBuffer s) {
		s.append("<exit>");
	}

    // Declared in Nodes.ast at line 3
    // Declared in Nodes.ast line 2

    public ExitStmt() {
        super();


    }

    // Declared in Nodes.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in Nodes.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in ReachingDefinitions.jrag at line 91
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
		if(getParent() instanceof FieldDeclaration) {
			FieldDeclaration fd = (FieldDeclaration)getParent();
			if(fd.isReachingDefinitionFor(l)) {
				res = SmallSet.singleton((Definition)fd);
				if(fd.isBlockingDefinitionFor(l))
					return res;
			}
		}
		for(CFGNode p : pred())
			res = res.union(p.reachingDefinitionsFor(l));
		return res;
	}

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
