
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Parameterisable {
    // Declared in ParameterExt.jrag at line 17

		int getNumParameter();

    // Declared in ParameterExt.jrag at line 18

		ParameterDeclaration getParameter(int i);

    // Declared in ParameterExt.jrag at line 19

		int getIndexOfParameter(ParameterDeclaration d);

}
