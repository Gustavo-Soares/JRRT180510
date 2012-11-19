
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;


// 8.5 Member Type Declarations

public abstract class MemberTypeDecl extends MemberDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public MemberTypeDecl clone() throws CloneNotSupportedException {
        MemberTypeDecl node = (MemberTypeDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in MoveMembers.jrag at line 41

	
	public void moveTo(TypeDecl target) {
		typeDecl().lockAllNames();
		programRoot().lockNames(typeDecl().name());
		hostType().removeBodyDecl(this);
		target.insertUnusedType(typeDecl());
	}

    // Declared in MoveMemberTypeToToplevel.jrag at line 3

	public void moveToToplevel(boolean ownCU, String instanceName, boolean makeInstanceFieldFinal) {
		Program root = programRoot();
		CompilationUnit cu = compilationUnit();
		TypeDecl td = typeDecl();
		root.lockNames(td.name());
		td.lockAllNames();
		if(!td.isStatic()) {
			LinkedList<FieldDeclaration> encls = td.addEnclosingInstanceFields(hostType(), instanceName, makeInstanceFieldFinal);
			ArrayList<TypeDecl> encl_types = new ArrayList<TypeDecl>();
			encl_types.add(td);
			td.adjustMemberMethods(encls, encl_types);
		}
		td.removeNonToplevelModifiers();
		if(ownCU) {
			String path = cu.pathName();
			root.insertUnusedType(path.substring(0, path.lastIndexOf(File.separatorChar)+1),
								  cu.getPackageDecl(), (List<ImportDecl>)cu.getImportDecls().fullCopy(), td);
		} else {
			cu.insertUnusedType(td);
		}
		getParent().removeChild(getChildIndex());
		root.eliminate(WITH_STMT, FRESH_VARIABLES, LOCKED_NAMES);
	}

    // Declared in MoveMemberTypeToToplevel.jrag at line 27

	
	public void moveToToplevel() {
		moveToToplevel(false, null, true);
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 91

    public MemberTypeDecl() {
        super();


    }

    // Declared in java.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in LookupType.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public abstract TypeDecl typeDecl();
    // Declared in LookupType.jrag at line 392
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresType(String name) {
        ASTNode$State state = state();
        boolean declaresType_String_value = declaresType_compute(name);
        return declaresType_String_value;
    }

    private boolean declaresType_compute(String name) {  return typeDecl().name().equals(name);  }

    // Declared in LookupType.jrag at line 394
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type(String name) {
        ASTNode$State state = state();
        TypeDecl type_String_value = type_compute(name);
        return type_String_value;
    }

    private TypeDecl type_compute(String name) {  return declaresType(name) ? typeDecl() : null;  }

    // Declared in Modifiers.jrag at line 246
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        boolean isStatic_value = isStatic_compute();
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return typeDecl().isStatic();  }

    // Declared in PrettyPrint.jadd at line 760
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return false;  }

    // Declared in Annotations.jrag at line 284
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasAnnotationSuppressWarnings(String s) {
        ASTNode$State state = state();
        boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
        return hasAnnotationSuppressWarnings_String_value;
    }

    private boolean hasAnnotationSuppressWarnings_compute(String s) {  return typeDecl().hasAnnotationSuppressWarnings(s);  }

    // Declared in Annotations.jrag at line 322
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDeprecated() {
        ASTNode$State state = state();
        boolean isDeprecated_value = isDeprecated_compute();
        return isDeprecated_value;
    }

    private boolean isDeprecated_compute() {  return typeDecl().isDeprecated();  }

    // Declared in GenericsParTypeDecl.jrag at line 68
 @SuppressWarnings({"unchecked", "cast"})     public boolean visibleTypeParameters() {
        ASTNode$State state = state();
        boolean visibleTypeParameters_value = visibleTypeParameters_compute();
        return visibleTypeParameters_value;
    }

    private boolean visibleTypeParameters_compute() {  return !isStatic();  }

    // Declared in Testing.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public String ppID() {
        ASTNode$State state = state();
        String ppID_value = ppID_compute();
        return ppID_value;
    }

    private String ppID_compute() {  return typeDecl().name();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
