
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Callable extends Parameterisable, Location, Scope {
    // Declared in Call.jadd at line 15
 
		public void setParameter(ParameterDeclaration pd, int i);

    // Declared in Call.jadd at line 16

		public List<ParameterDeclaration> getParameterList();

    // Declared in Call.jadd at line 17

		public boolean isStatic();

    // Declared in Call.jadd at line 18

		public boolean hasBlock();

    // Declared in Call.jadd at line 19

		public Block getBlock();

    // Declared in Call.jadd at line 20

		public void setBlock(Block b);

    // Declared in Call.jadd at line 25

	
	public void setParameter(String name, ParameterDeclaration pd);


    // Declared in Call.jadd at line 29

	
	public void insertParameter(ParameterDeclaration pd, int i);


    // Declared in Call.jadd at line 33

	
	public void removeParameter(int i);


    // Declared in Call.jadd at line 37

	
	public int getIndexOfParameter(ParameterDeclaration pd);


    // Declared in Call.jadd at line 51

	
	public ParameterDeclaration getParameter(String name);


    // Declared in Call.jadd at line 44
 @SuppressWarnings({"unchecked", "cast"})     public int getIndexOfParameter(String name);
    // Declared in AccessVariable.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasVariable(String name);
    // Declared in AccessVariable.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public SymbolicVarAccess accessParameter(Variable decl);
    // Declared in AccessVariable.jrag at line 118
    public UnknownVarAccess unknownVarAccess();
}
