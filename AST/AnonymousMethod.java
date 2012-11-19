
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class AnonymousMethod extends Expr implements Cloneable, Scope {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
        uncheckedExceptionExit_computed = false;
        uncheckedExceptionExit_value = null;
        returns_computed = false;
        returns_value = null;
        finals_computed = false;
        finals_value = null;
        accessParameter_Variable_values = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AnonymousMethod clone() throws CloneNotSupportedException {
        AnonymousMethod node = (AnonymousMethod)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.uncheckedExceptionExit_computed = false;
        node.uncheckedExceptionExit_value = null;
        node.returns_computed = false;
        node.returns_value = null;
        node.finals_computed = false;
        node.finals_value = null;
        node.accessParameter_Variable_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AnonymousMethod copy() {
      try {
          AnonymousMethod node = (AnonymousMethod)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AnonymousMethod fullCopy() {
        AnonymousMethod res = (AnonymousMethod)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in InlineAnonymousMethod.jrag at line 3

	
	public ASTNode inline() {
		TypeDecl root = hostType();
		if(getNumParameter() > 0)
			throw new RefactoringException("cannot inline this closure");
		if(getParent() instanceof ExprStmt) {
			String label = getBlock().pickFreshLabel("l");
			java.util.Set<BreakStmt> breaks = new HashSet<BreakStmt>();
			for(Stmt node : returns()) {
				ReturnStmt ret = (ReturnStmt)node;
				if(ret.hasResult() && !ret.getResult().isPure()) {
					if(!ret.getResult().isStatementExpression())
						throw new RefactoringException("cannot inline this closure");
					ret.insertStmtBefore(new ExprStmt(ret.getResult()));
				}
				BreakStmt brk = new BreakStmt(label);
				breaks.add(brk);
				ret.replaceWith(brk);
			}
			LabeledStmt body = new LabeledStmt(label, getBlock());
			getParent().replaceWith(body);
			root.flushCaches();
			for(BreakStmt brk : breaks)
				brk.removeUseless();
			return body.removeUselessLabel();
		} else if(isExprClosure()) {
			Expr expr = getExpr();
			replaceWith(expr);
			root.flushCaches();
			return expr;
		} else if(getParent().getParent() instanceof ReturnStmt) {
			Block block = getBlock();
			getParent().getParent().replaceWith(block);
			root.flushCaches();
			return block;
		} else {
			throw new RefactoringException("cannot inline this closure");
		}
	}

    // Declared in IntroduceOutParameter.jrag at line 2

	public AnonymousMethod introduceOutParameter() {
		eliminateVarargs();
		programRoot().flushCaches();
		if(!(getParent() instanceof AssignSimpleExpr))
			return this;
		AssignSimpleExpr parent = (AssignSimpleExpr)getParent();
		if(!(parent.getDest() instanceof VarAccess))
			throw new RefactoringException("cannot introduce non-variable out parameter");
		VarAccess v = (VarAccess)parent.getDest();
		getBlock().lockDataFlow();
		setReturnType(new PrimitiveTypeAccess("void"));
		ParameterDeclaration pd = new FreshParameter(new Modifiers("out"), v.type().createLockedAccess(), "res");
		addParameter(pd);
		addArg(v.decl().createLockedAccess());
		getBlock().addAssignToReturns(pd);
		parent.replaceWith(this);
		hostType().flushCaches();
		return this;
	}

    // Declared in OpenVariables.jrag at line 3

	
	public AnonymousMethod open() {
		getBlock().flushCaches();
		getBlock().freshenAllDeclarations();
		for(int i=getNumParameter()-1;i>=0;--i)
			getParameter(i).inline();
		hostType().flushCaches();
		return this;
	}

    // Declared in AnonymousMethods.jrag at line 2

	public void toString(StringBuffer s) {
		s.append("({ ");
		if(getNumTypeParameter() > 0) {
			s.append("<");
    		getTypeParameter(0).toString(s);
    		for (int i = 1; i < getNumTypeParameter(); i++) {
    			s.append(", ");
    			getTypeParameter(i).toString(s);
    		}
	    	s.append("> ");		
	    }
		s.append("(");
		if(getNumParameter() > 0) {
			getParameter(0).toString(s);
			for(int i = 1; i < getNumParameter(); i++) {
				s.append(", ");
				getParameter(i).toString(s);
			}
		}
		s.append(") : ");
		getReturnType().toString(s);
		if(getNumException() > 0) {
			s.append(" throws ");
			getException(0).toString(s);
			for(int i = 1; i < getNumException(); i++) {
				s.append(", ");
				getException(i).toString(s);
			}
		}
		s.append(" => ");
		getBlock().toString(s);
		s.append("}) (");
		if(getNumArg() > 0) {
			getArg(0).toString(s);
			for(int i = 1; i < getNumArg(); i++) {
				s.append(", ");
				getArg(i).toString(s);
			}
		}
		s.append(")");
	}

    // Declared in AnonymousMethods.jrag at line 51

	
	// equations for some attributes defined elsewhere
	public boolean canIntroduceLocal(String name) {
		if(!parameterDeclaration(name).isEmpty())
			return false;
		return super.canIntroduceLocal(name);
	}

    // Declared in AnonymousMethods.jrag at line 68

	
	// artificial node to represent throws of uncaught exceptions
	private final ExitStmt uncheckedExceptionExit = new ExitStmt();

    // Declared in AnonymousMethods.jrag at line 232

	
	public void removeArg(int i) {
		getArgList().removeChild(i);
	}

    // Declared in AnonymousMethods.jrag at line 236

	
	public void removeParameter(int i) {
		getParameterList().removeChild(i);
	}

    // Declared in AnonymousMethods.jrag at line 253

	
	// find argument for parameter
	public Expr lookupArg(String parmname) {
		for(int i = 0; i < getNumParameter(); ++i)
			if(getParameter(i).name().equals(parmname))
				return getArg(i);
		return null;
	}

    // Declared in AnonymousMethods.jrag at line 278
	
	
	public void eliminateVarargs() {
		if(!isVariableArity())
			return;
		VariableArityParameterDeclaration last = (VariableArityParameterDeclaration)lastParameter();
		ParameterDeclaration new_last = last.asFixedArityParameter();
		for(VarAccess va : last.uses())
			if(va.isLocked())
				va.lock(new_last);
		setParameter(new_last, getNumParameter()-1);
		if(!invokesVariableArityAsArray()) {
			List<Expr> varargs = new List<Expr>();
			for(int i=getNumArg()-1; i>getNumParameter()-1;--i) {
				Expr arg = getArg(i);
				removeArg(i);
				varargs.insertChild(arg, 0);
			}
			varargs.insertChild(getArg(getNumParameter()-1), 0);
			setArg(new ArrayCreationExpr(last.type().createLockedAccess(), new Opt(new ArrayInit(varargs))), getNumParameter()-1);
		}
	}

    // Declared in AnonymousMethods.ast at line 3
    // Declared in AnonymousMethods.ast line 1

    public AnonymousMethod() {
        super();

        setChild(new List(), 0);
        setChild(new List(), 1);
        setChild(new List(), 3);
        setChild(new List(), 5);

    }

    // Declared in AnonymousMethods.ast at line 14


    // Declared in AnonymousMethods.ast line 1
    public AnonymousMethod(List<ParameterDeclaration> p0, List<TypeVariable> p1, Access p2, List<Access> p3, Block p4, List<Expr> p5) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
        setChild(p5, 5);
    }

    // Declared in AnonymousMethods.ast at line 23


  protected int numChildren() {
    return 6;
  }

    // Declared in AnonymousMethods.ast at line 26

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 0);
    }

    // Declared in AnonymousMethods.ast at line 6


    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in AnonymousMethods.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in AnonymousMethods.ast at line 14


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = (parent == null || state == null) ? getParameterListNoTransform() : getParameterList();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 19


    public void addParameterNoTransform(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterListNoTransform();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 24


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in AnonymousMethods.ast at line 28

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in AnonymousMethods.ast at line 31

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in AnonymousMethods.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        List<ParameterDeclaration> list = (List<ParameterDeclaration>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in AnonymousMethods.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(0);
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setTypeParameterList(List<TypeVariable> list) {
        setChild(list, 1);
    }

    // Declared in AnonymousMethods.ast at line 6


    public int getNumTypeParameter() {
        return getTypeParameterList().getNumChild();
    }

    // Declared in AnonymousMethods.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public TypeVariable getTypeParameter(int i) {
        return (TypeVariable)getTypeParameterList().getChild(i);
    }

    // Declared in AnonymousMethods.ast at line 14


    public void addTypeParameter(TypeVariable node) {
        List<TypeVariable> list = (parent == null || state == null) ? getTypeParameterListNoTransform() : getTypeParameterList();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 19


    public void addTypeParameterNoTransform(TypeVariable node) {
        List<TypeVariable> list = getTypeParameterListNoTransform();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 24


    public void setTypeParameter(TypeVariable node, int i) {
        List<TypeVariable> list = getTypeParameterList();
        list.setChild(node, i);
    }

    // Declared in AnonymousMethods.ast at line 28

    public List<TypeVariable> getTypeParameters() {
        return getTypeParameterList();
    }

    // Declared in AnonymousMethods.ast at line 31

    public List<TypeVariable> getTypeParametersNoTransform() {
        return getTypeParameterListNoTransform();
    }

    // Declared in AnonymousMethods.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<TypeVariable> getTypeParameterList() {
        List<TypeVariable> list = (List<TypeVariable>)getChild(1);
        list.getNumChild();
        return list;
    }

    // Declared in AnonymousMethods.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<TypeVariable> getTypeParameterListNoTransform() {
        return (List<TypeVariable>)getChildNoTransform(1);
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setReturnType(Access node) {
        setChild(node, 2);
    }

    // Declared in AnonymousMethods.ast at line 5

    public Access getReturnType() {
        return (Access)getChild(2);
    }

    // Declared in AnonymousMethods.ast at line 9


    public Access getReturnTypeNoTransform() {
        return (Access)getChildNoTransform(2);
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setExceptionList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in AnonymousMethods.ast at line 6


    public int getNumException() {
        return getExceptionList().getNumChild();
    }

    // Declared in AnonymousMethods.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Access getException(int i) {
        return (Access)getExceptionList().getChild(i);
    }

    // Declared in AnonymousMethods.ast at line 14


    public void addException(Access node) {
        List<Access> list = (parent == null || state == null) ? getExceptionListNoTransform() : getExceptionList();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 19


    public void addExceptionNoTransform(Access node) {
        List<Access> list = getExceptionListNoTransform();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 24


    public void setException(Access node, int i) {
        List<Access> list = getExceptionList();
        list.setChild(node, i);
    }

    // Declared in AnonymousMethods.ast at line 28

    public List<Access> getExceptions() {
        return getExceptionList();
    }

    // Declared in AnonymousMethods.ast at line 31

    public List<Access> getExceptionsNoTransform() {
        return getExceptionListNoTransform();
    }

    // Declared in AnonymousMethods.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionList() {
        List<Access> list = (List<Access>)getChild(3);
        list.getNumChild();
        return list;
    }

    // Declared in AnonymousMethods.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setBlock(Block node) {
        setChild(node, 4);
    }

    // Declared in AnonymousMethods.ast at line 5

    public Block getBlock() {
        return (Block)getChild(4);
    }

    // Declared in AnonymousMethods.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(4);
    }

    // Declared in AnonymousMethods.ast at line 2
    // Declared in AnonymousMethods.ast line 1
    public void setArgList(List<Expr> list) {
        setChild(list, 5);
    }

    // Declared in AnonymousMethods.ast at line 6


    public int getNumArg() {
        return getArgList().getNumChild();
    }

    // Declared in AnonymousMethods.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getArg(int i) {
        return (Expr)getArgList().getChild(i);
    }

    // Declared in AnonymousMethods.ast at line 14


    public void addArg(Expr node) {
        List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 19


    public void addArgNoTransform(Expr node) {
        List<Expr> list = getArgListNoTransform();
        list.addChild(node);
    }

    // Declared in AnonymousMethods.ast at line 24


    public void setArg(Expr node, int i) {
        List<Expr> list = getArgList();
        list.setChild(node, i);
    }

    // Declared in AnonymousMethods.ast at line 28

    public List<Expr> getArgs() {
        return getArgList();
    }

    // Declared in AnonymousMethods.ast at line 31

    public List<Expr> getArgsNoTransform() {
        return getArgListNoTransform();
    }

    // Declared in AnonymousMethods.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgList() {
        List<Expr> list = (List<Expr>)getChild(5);
        list.getNumChild();
        return list;
    }

    // Declared in AnonymousMethods.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgListNoTransform() {
        return (List<Expr>)getChildNoTransform(5);
    }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in AnonymousMethods.jrag at line 60
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

    private TypeDecl type_compute() {  return getReturnType().type();  }

    // Declared in AnonymousMethods.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public int precedence() {
        ASTNode$State state = state();
        int precedence_value = precedence_compute();
        return precedence_value;
    }

    private int precedence_compute() {  return 0;  }

    protected boolean uncheckedExceptionExit_computed = false;
    protected Stmt uncheckedExceptionExit_value;
    // Declared in AnonymousMethods.jrag at line 69
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

    // Declared in AnonymousMethods.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode first() {
        ASTNode$State state = state();
        CFGNode first_value = first_compute();
        return first_value;
    }

    private CFGNode first_compute() {  return getNumArg() == 0 ? getBlock() : getArg(0).first();  }

    // Declared in AnonymousMethods.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode entry() {
        ASTNode$State state = state();
        CFGNode entry_value = entry_compute();
        return entry_value;
    }

    private CFGNode entry_compute() {  return getBlock();  }

    // Declared in AnonymousMethods.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public CFGNode exit() {
        ASTNode$State state = state();
        CFGNode exit_value = exit_compute();
        return exit_value;
    }

    private CFGNode exit_compute() {  return this;  }

    protected boolean returns_computed = false;
    protected SmallSet<Stmt> returns_value;
    // Declared in AnonymousMethods.jrag at line 173
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> returns() {
        if(returns_computed) {
            return returns_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        returns_value = returns_compute();
        if(isFinal && num == state().boundariesCrossed)
            returns_computed = true;
        return returns_value;
    }

    private SmallSet<Stmt> returns_compute() {  return getReturnType().predStmt();  }

    protected boolean finals_computed = false;
    protected SmallSet<Stmt> finals_value;
    // Declared in AnonymousMethods.jrag at line 175
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<Stmt> finals() {
        if(finals_computed) {
            return finals_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        finals_value = finals_compute();
        if(isFinal && num == state().boundariesCrossed)
            finals_computed = true;
        return finals_value;
    }

    private SmallSet<Stmt> finals_compute() {
    	SmallSet<Stmt> res = SmallSet.empty();
    	for(CFGNode n : pred()) {
    		if(n == getReturnType())
    			continue;
    		if(n instanceof Stmt) {
    			if(((Stmt)n).canCompleteNormally())
    				res = res.union((Stmt)n);
    		} else {
    			for(Stmt stmt : (SmallSet<Stmt>)n.predStmt())
    				if(stmt.canCompleteNormally())
    					res = res.union(stmt);
    		}
    	}
    	return res;
	}

    // Declared in AnonymousMethods.jrag at line 201
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet parameterDeclaration(String name) {
        ASTNode$State state = state();
        SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
        return parameterDeclaration_String_value;
    }

    private SimpleSet parameterDeclaration_compute(String name) {
		for(int i = 0; i < getNumParameter(); i++)
			if(getParameter(i).name().equals(name))
				return (ParameterDeclaration)getParameter(i);
		return SimpleSet.emptySet;
	}

    protected java.util.Map accessParameter_Variable_values;
    // Declared in AnonymousMethods.jrag at line 216
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
		for(ParameterDeclaration pd : getParameters())
			if(pd == decl)
				return pd;
		return unknownVarAccess();
	}

    // Declared in AnonymousMethods.jrag at line 224
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasVariable(String name) {
        ASTNode$State state = state();
        boolean hasVariable_String_value = hasVariable_compute(name);
        return hasVariable_String_value;
    }

    private boolean hasVariable_compute(String name) {  return !parameterDeclaration(name).isEmpty();  }

    // Declared in AnonymousMethods.jrag at line 240
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariableArity() {
        ASTNode$State state = state();
        boolean isVariableArity_value = isVariableArity_compute();
        return isVariableArity_value;
    }

    private boolean isVariableArity_compute() {  return getNumParameter() == 0 ? false : getParameter(getNumParameter()-1).isVariableArity();  }

    // Declared in AnonymousMethods.jrag at line 241
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration lastParameter() {
        ASTNode$State state = state();
        ParameterDeclaration lastParameter_value = lastParameter_compute();
        return lastParameter_value;
    }

    private ParameterDeclaration lastParameter_compute() {  return getParameter(getNumParameter()-1);  }

    // Declared in AnonymousMethods.jrag at line 242
 @SuppressWarnings({"unchecked", "cast"})     public Expr lastArg() {
        ASTNode$State state = state();
        Expr lastArg_value = lastArg_compute();
        return lastArg_value;
    }

    private Expr lastArg_compute() {  return getArg(getNumArg()-1);  }

    // Declared in AnonymousMethods.jrag at line 244
 @SuppressWarnings({"unchecked", "cast"})     public boolean invokesVariableArityAsArray() {
        ASTNode$State state = state();
        boolean invokesVariableArityAsArray_value = invokesVariableArityAsArray_compute();
        return invokesVariableArityAsArray_value;
    }

    private boolean invokesVariableArityAsArray_compute() {
		if(!isVariableArity())
			return false;
		if(getNumParameter() != getNumArg())
			return false;
		return lastArg().type().methodInvocationConversionTo(lastParameter().type());
	}

    // Declared in AnonymousMethods.jrag at line 300
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExprClosure() {
        ASTNode$State state = state();
        boolean isExprClosure_value = isExprClosure_compute();
        return isExprClosure_value;
    }

    private boolean isExprClosure_compute() {  return getBlock().getNumStmt() == 1 && getBlock().getStmt(0) instanceof ReturnStmt &&
	      ((ReturnStmt)getBlock().getStmt(0)).hasResult();  }

    // Declared in AnonymousMethods.jrag at line 304
 @SuppressWarnings({"unchecked", "cast"})     public Expr getExpr() {
        ASTNode$State state = state();
        Expr getExpr_value = getExpr_compute();
        return getExpr_value;
    }

    private Expr getExpr_compute() {  return ((ReturnStmt)getBlock().getStmt(0)).getResult();  }

    // Declared in AnonymousMethods.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public SmallSet<CFGNode> throwTarget(TypeDecl exn) {
        ASTNode$State state = state();
        SmallSet<CFGNode> throwTarget_TypeDecl_value = getParent().Define_SmallSet_CFGNode__throwTarget(this, null, exn);
        return throwTarget_TypeDecl_value;
    }

    // Declared in AnonymousMethods.jrag at line 58
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in AnonymousMethods.jrag at line 81
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__following(ASTNode caller, ASTNode child) {
        if(caller == uncheckedExceptionExit_value){
            return uncheckedExnTarget();
        }
        if(caller == getExceptionListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return throwTarget(getException(i).type());
        }
        if(caller == getReturnTypeNoTransform()) {
            return SmallSet.<CFGNode>singleton(exit());
        }
        if(caller == getBlockNoTransform()) {
            return SmallSet.<CFGNode>singleton(exit());
        }
        if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i < getNumArg()-1 ? SmallSet.<CFGNode>singleton(getArg(i+1).first()) 
			  			  											   : SmallSet.<CFGNode>singleton(getBlock());
        }
        return getParent().Define_SmallSet_CFGNode__following(this, caller);
    }

    // Declared in AnonymousMethods.jrag at line 83
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__breakTarget(ASTNode caller, ASTNode child, BreakStmt stmt) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.empty();
        }
        return getParent().Define_SmallSet_CFGNode__breakTarget(this, caller, stmt);
    }

    // Declared in AnonymousMethods.jrag at line 84
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__continueTarget(ASTNode caller, ASTNode child, ContinueStmt stmt) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.empty();
        }
        return getParent().Define_SmallSet_CFGNode__continueTarget(this, caller, stmt);
    }

    // Declared in AnonymousMethods.jrag at line 85
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__returnTarget(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(getReturnType());
        }
        return getParent().Define_SmallSet_CFGNode__returnTarget(this, caller);
    }

    // Declared in AnonymousMethods.jrag at line 86
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
        return getParent().Define_SmallSet_CFGNode__throwTarget(this, caller, exn);
    }

    // Declared in AnonymousMethods.jrag at line 95
    public SmallSet<CFGNode> Define_SmallSet_CFGNode__uncheckedExnTarget(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return SmallSet.<CFGNode>singleton(uncheckedExceptionExit());
        }
        return getParent().Define_SmallSet_CFGNode__uncheckedExnTarget(this, caller);
    }

    // Declared in AnonymousMethods.jrag at line 198
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return parameterDeclaration(name);
        }
        if(caller == getBlockNoTransform()){
		SimpleSet set = parameterDeclaration(name);
		if(!set.isEmpty()) return set;
		return lookupVariable(name);
	}
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in AnonymousMethods.jrag at line 213
    public SymbolicVarAccess Define_SymbolicVarAccess_accessVariable(ASTNode caller, ASTNode child, Variable decl) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return accessParameter(decl);
        }
        if(caller == getBlockNoTransform()){
		SymbolicVarAccess acc = accessParameter(decl);
		if(!acc.isUnknownVarAccess()) return acc;
		return accessVariable(decl).moveInto(this);
	}
        return getParent().Define_SymbolicVarAccess_accessVariable(this, caller, decl);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
