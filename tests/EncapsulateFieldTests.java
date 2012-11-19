package tests;


import junit.framework.TestCase;
import AST.FieldDeclaration;
import AST.IntegerLiteral;
import AST.Literal;
import AST.MethodDecl;
import AST.ParameterDeclaration;
import AST.PrimitiveType;
import AST.Program;
import AST.RawCU;
import AST.RefactoringException;
import AST.SimpleSet;
import AST.TypeDecl;

public class EncapsulateFieldTests extends TestCase {
	public EncapsulateFieldTests(String name) {
		super(name);
	}
	
	public void testSucc(Program in, Program out) {		
		
		
		assertNotNull(in);
		assertNotNull(out);
		TypeDecl td = in.findType("A");
		assertNotNull(td);
		FieldDeclaration fd = td.findField("f");
		assertNotNull(fd);
		
		try {
			fd.doSelfEncapsulate();
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			fail("Refactoring was supposed to succeed; failed with "+rfe);
		}
	}

	public void testFail(Program in) {		
		assertNotNull(in);
		TypeDecl td = in.findType("B");
		assertNotNull(td);
		FieldDeclaration fd = td.findField("f");
		assertNotNull(fd);
		try {
			fd.doSelfEncapsulate();
			fail("Refactoring was supposed to fail; succeeded with "+in);
		} catch(RefactoringException rfe) { }
	}
	
    
  //bug 6.1.1
    public void test2() {
    	testFail(Program.fromClasses(   			 
       	     "public class A {" +
       	     "public long getF(){" +
       	     "   return 42;" +
       	     "  }" +
       	     "  public long m(){" +
       	     "    return getF();" +
       	     "  }" +
       	     "}",   			 
   			 "public class B extends A {" +
   			 "  public long f;" +   
   			 "  public long test(){" +
       	     "    return m();" +
       	     "  }" +
   			 "}"));
    }

}
