
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface MemberSubstitutor extends Parameterization {
    // Declared in Generics.jrag at line 680

    TypeDecl original();

    // Declared in Generics.jrag at line 681

    void addBodyDecl(BodyDecl b);

    // Declared in Generics.jrag at line 682

    TypeDecl substitute(TypeVariable typeVariable);

    // Declared in Generics.jrag at line 929
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localMethodsSignatureMap();
    // Declared in Generics.jrag at line 944
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localFields(String name);
    // Declared in Generics.jrag at line 959
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localTypeDecls(String name);
    // Declared in Generics.jrag at line 989
 @SuppressWarnings({"unchecked", "cast"})     public Collection constructors();
}
