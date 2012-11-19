
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Variable extends Location, Declaration {
    // Declared in VariableDeclaration.jrag at line 12

    public String name();

    // Declared in VariableDeclaration.jrag at line 13

    public TypeDecl type();

    // Declared in VariableDeclaration.jrag at line 15

    // 4.5.3
    public boolean isClassVariable();

    // Declared in VariableDeclaration.jrag at line 16

    public boolean isInstanceVariable();

    // Declared in VariableDeclaration.jrag at line 17

    public boolean isMethodParameter();

    // Declared in VariableDeclaration.jrag at line 18

    public boolean isConstructorParameter();

    // Declared in VariableDeclaration.jrag at line 19

    public boolean isExceptionHandlerParameter();

    // Declared in VariableDeclaration.jrag at line 20

    public boolean isLocalVariable();

    // Declared in VariableDeclaration.jrag at line 22

    // 4.5.4
    public boolean isFinal();

    // Declared in VariableDeclaration.jrag at line 23

    public boolean isVolatile();

    // Declared in VariableDeclaration.jrag at line 25


    public boolean isBlank();

    // Declared in VariableDeclaration.jrag at line 26

    public boolean isStatic();

    // Declared in VariableDeclaration.jrag at line 27

    public boolean isSynthetic();

    // Declared in VariableDeclaration.jrag at line 29


    public TypeDecl hostType();

    // Declared in VariableDeclaration.jrag at line 31


    public Expr getInit();

    // Declared in VariableDeclaration.jrag at line 32

    public boolean hasInit();

    // Declared in VariableDeclaration.jrag at line 34


    public Constant constant();

    // Declared in VariableDeclaration.jrag at line 36


    public Modifiers getModifiers();

    // Declared in Alias.jrag at line 10

	
	public boolean isHeapLocation();


    // Declared in Alias.jrag at line 20

	public boolean isArrayElement();


    // Declared in Alias.jrag at line 23

	
	public boolean refined_Alias_Variable_mayAlias(Location l);


    // Declared in Alias.jrag at line 25

	
	public boolean mustAlias(Location l);


    // Declared in AST.jrag at line 56

	
	public boolean isField();


    // Declared in Refresh.jrag at line 2

	public Variable refresh();


    // Declared in Uses.jrag at line 3

	// bind all uses of a variable to its declaration
	public Collection<VarAccess> allUses();


    // Declared in Uses.jrag at line 5

	
	public boolean isUsed();


    // Declared in VariableExt.jrag at line 2

	public VariableDeclaration asVariableDeclaration(Expr init);


    // Declared in FreshVariables.jrag at line 133

	
	// this shouldn't really be necessary, but JastAdd doesn't propagate through
	// more than one interface
	public void collectAllDecls(Collection<Declaration> res);


    // Declared in LockedVariableAccess.jrag at line 6

	
	public VarAccess createLockedAccess();


    // Declared in LockedVariableAccess.jrag at line 42

	
	public boolean isSubstituted();


    // Declared in RenameVariable.jrag at line 6

	public void rename(String new_name);


    // Declared in RenameVariable.jrag at line 18

	
	public void checkRenamingPreconds(String new_name);


    // Declared in Testing.jrag at line 212

	
	public Variable findVariable(String name);


    // Declared in DataFlow.jrag at line 132

	
	  public boolean mayAlias(Location l);


    // Declared in Generics.jrag at line 1281
 @SuppressWarnings({"unchecked", "cast"})     public Variable sourceVariableDecl();
    // Declared in Refresh.jrag at line 4
 @SuppressWarnings({"unchecked", "cast"})     public Variable refreshVariable();
    // Declared in DemandFinalQualifier.jrag at line 6
 @SuppressWarnings({"unchecked", "cast"})     public boolean shouldBeFinal();
    // Declared in DemandFinalQualifier.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeFinal();
    // Declared in AccessVariable.jrag at line 128
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSourceDeclAs(Variable decl);
    // Declared in AccessVariable.jrag at line 119
    public UnknownVarAccess unknownVarAccess();
}
