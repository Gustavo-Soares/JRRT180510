
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface ParTypeDecl extends Parameterization {
    // Declared in Generics.jrag at line 220

    //syn String name();
    int getNumArgument();

    // Declared in Generics.jrag at line 221

    Access getArgument(int index);

    // Declared in Generics.jrag at line 224

    public String typeName();

    // Declared in Generics.jrag at line 225

    SimpleSet localFields(String name);

    // Declared in Generics.jrag at line 226

    HashMap localMethodsSignatureMap();

    // Declared in Generics.jrag at line 697

  public TypeDecl substitute(TypeVariable typeVariable);


    // Declared in Generics.jrag at line 710


  public int numTypeParameter();


    // Declared in Generics.jrag at line 713

  public TypeVariable typeParameter(int index);


    // Declared in Generics.jrag at line 745

  public Access substitute(Parameterization parTypeDecl);


    // Declared in GenericsParTypeDecl.jrag at line 73


  public Access createQualifiedAccess();


    // Declared in Refresh.jrag at line 22

	public TypeDecl refresh();


    // Declared in InsertUnusedType.jrag at line 12

	public MemberTypeDecl insertUnusedType(TypeDecl td, int idx);


    // Declared in InsertUnusedMethod.jrag at line 24

	
	public void insertUnusedMethod(MethodDecl m, int idx);


    // Declared in LockedTypeAccess.jrag at line 52

	
	public Access createLockedAccess();


    // Declared in Generics.jrag at line 222
 @SuppressWarnings({"unchecked", "cast"})     public boolean isParameterizedType();
    // Declared in Generics.jrag at line 223
 @SuppressWarnings({"unchecked", "cast"})     public boolean isRawType();
    // Declared in Generics.jrag at line 351
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameArgument(ParTypeDecl decl);
    // Declared in Generics.jrag at line 548
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSignature(Access a);
    // Declared in Generics.jrag at line 583
 @SuppressWarnings({"unchecked", "cast"})     public boolean sameSignature(ArrayList list);
    // Declared in GenericsParTypeDecl.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public String nameWithArgs();
    // Declared in GenericsParTypeDecl.jrag at line 45
    public TypeDecl genericDecl();
}
