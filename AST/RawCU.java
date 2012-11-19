
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import tests.CompileHelper;

public class RawCU extends java.lang.Object {
    // Declared in ProgramFactory.jrag at line 115

		private String name;

    // Declared in ProgramFactory.jrag at line 116

		private String content;

    // Declared in ProgramFactory.jrag at line 118


		public RawCU(String name, String content) {
			this.name = name;
			this.content = content;
		}

    // Declared in ProgramFactory.jrag at line 123


		public String getName() {
			return name;
		}

    // Declared in ProgramFactory.jrag at line 127


		public String getContent() {
			return content;
		}


}
