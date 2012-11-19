
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface GenericTypeDecl extends GenericElement {
    // Declared in Generics.jrag at line 140

    TypeDecl original();

    // Declared in Generics.jrag at line 141

    int getNumTypeParameter();

    // Declared in Generics.jrag at line 142

    TypeVariable getTypeParameter(int index);

    // Declared in Generics.jrag at line 143

    List getTypeParameterList();

    // Declared in Generics.jrag at line 145

    public String fullName();

    // Declared in Generics.jrag at line 146

    public String typeName();

    // Declared in Generics.jrag at line 147

    public int getNumParTypeDecl();

    // Declared in Generics.jrag at line 148

    public ParTypeDecl getParTypeDecl(int index);

    // Declared in Generics.jrag at line 213

  public TypeDecl makeGeneric(Signatures.ClassSignature s);


    // Declared in Generics.jrag at line 460


  public SimpleSet addTypeVariables(SimpleSet c, String name);


    // Declared in Generics.jrag at line 661

  public List createArgumentList(ArrayList params);


    // Declared in LockedTypeAccess.jrag at line 43

	
	public Access createLockedAccess();


    // Declared in Generics.jrag at line 139
 @SuppressWarnings({"unchecked", "cast"})     public boolean isGenericType();
    // Declared in Generics.jrag at line 144
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl rawType();
    // Declared in Generics.jrag at line 595
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupParTypeDecl(ParTypeAccess p);
    // Declared in Generics.jrag at line 632
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupParTypeDecl(ArrayList list);
}
