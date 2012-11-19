
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class CONSTANT_Utf8_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 223

    public String string;

    // Declared in BytecodeCONSTANT.jrag at line 225


    public CONSTANT_Utf8_Info(BytecodeParser parser) {
      super(parser);
      string = p.readUTF();
    }

    // Declared in BytecodeCONSTANT.jrag at line 230


    public String toString() {
      return "Utf8Info: " + string;
    }

    // Declared in BytecodeCONSTANT.jrag at line 234


    public Expr expr() {
      return new StringLiteral(string);
    }

    // Declared in BytecodeCONSTANT.jrag at line 238


    public String string() {
      return string;
    }


}
