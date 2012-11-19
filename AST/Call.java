
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public interface Call {
    // Declared in Call.jadd at line 3

		public int getNumArg();

    // Declared in Call.jadd at line 4

		public Expr getArg(int i);

    // Declared in Call.jadd at line 5

		public List<Expr> getArgList();

    // Declared in Call.jadd at line 6

		public void setArg(Expr e, int i);

    // Declared in Call.jadd at line 7

		public void setArgList(List<Expr> list);

    // Declared in Call.jadd at line 8

		public void addArg(Expr e);

}
