
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Parameterization {
    // Declared in Generics.jrag at line 674

    boolean isRawType();

    // Declared in Generics.jrag at line 675

    TypeDecl substitute(TypeVariable typeVariable);

}
