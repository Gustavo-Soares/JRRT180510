package tests;


import junit.framework.TestCase;
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

public class AddParameterTests extends TestCase {
	public AddParameterTests(String name) {
		super(name);
	}
	
	public void testSucc(Program in, Program out) {		
		
		
		assertNotNull(in);
		assertNotNull(out);
		TypeDecl td = in.findType("A");
		assertNotNull(td);
		SimpleSet s = td.localMethodsSignature("k()");
		MethodDecl md = (MethodDecl) s.iterator().next();
		assertNotNull(md);
		
		try {
			PrimitiveType param = new PrimitiveType();
			param.setID("int");
			Literal l = new IntegerLiteral("0");
			md.doAddParameter(new ParameterDeclaration(param,"i"), 0, l, false);
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			fail("Refactoring was supposed to succeed; failed with "+rfe);
		}
	}

	public void testFail(Program in) {		
		assertNotNull(in);
		TypeDecl td = in.findType("A");
		assertNotNull(td);
		SimpleSet s = td.localMethodsSignature("k()");
		MethodDecl md = (MethodDecl) s.iterator().next();
		assertNotNull(md);
		try {
			PrimitiveType param = new PrimitiveType();
			param.setID("int");
			Literal l = new IntegerLiteral("0");
			md.doAddParameter(new ParameterDeclaration(param,"i"), 0, l, false);
			fail("Refactoring was supposed to fail; succeeded with "+in);
		} catch(RefactoringException rfe) { }
	}
	
    
  //bug 5.1.2
    public void test2() {
    	testFail(Program.fromCompilationUnits(
   			 new RawCU("A.java",
   			 "package p1;" +
       	     "public class A {" +
       	     "	long k() {" +
       	     "		return 10;" +
       	     "	}" +
       	     "	public long m() {" +
       	     "		return A.this.k();" +
       	     "	}" +
       	     "}" ),
   			 new RawCU("B.java",
   			 "package p2;" +
   			 "import p1.*;" +
   			 "public class B extends A { }"),
   			 new RawCU("C.java","package p1;" +
   			 		"import p2.*;" +
   			 		"public class C extends B {" +
   			 		"	public long k() {" +
   			 		"		return 39;" +
   			 		"	}" +
   			 		"	public long test() {" +
   			 		"		return m();" +
   			 		"	}" +
   			 		"}")
   			 ));
    }

}
