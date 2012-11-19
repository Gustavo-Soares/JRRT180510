package tests;

import junit.framework.TestCase;
import AST.MethodDecl;
import AST.Program;
import AST.RawCU;
import AST.RefactoringException;
import AST.TypeDecl;

public class PullUpMethodTests extends TestCase {
	public PullUpMethodTests(String name) {
		super(name);
	}
	
	public void testSucc(Program in, Program out) {		
		assertNotNull(in);
		assertNotNull(out);
		TypeDecl td = in.findType("A");
		assertNotNull(td);
		MethodDecl md = td.findMethod("m");
		assertNotNull(md);
		try {
			md.doPullUp();
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.toString());
		}
	}

	public void testFail(Program in) {		
		assertNotNull(in);
		TypeDecl td = in.findType("A");
		assertNotNull(td);
		MethodDecl md = td.findMethod("m");
		assertNotNull(md);
		try {
			md.doPullUp();
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) { }
	}

    public void test1() {
    	testSucc(
    	    Program.fromClasses(
    	      "class Super { }",
    	      "class A extends Super { void m() { } }"),
    	    Program.fromClasses(
    	      "class Super { void m() { } }",
    	      "class A extends Super { }"));
    }
    
    public void test2() {
    	testFail( 
    		Program.fromClasses(
    		  "class Super { }",
    		  "class A extends Super { void m() { } }",
    		  "class B extends Super { int m() { return 23; } }"));
    }

    public void test3() {
    	testFail(
    		Program.fromClasses(
    		  "class Super { void m() { } }",
    		  "class A extends Super { void m() { } }"));
    }

    public void test4() {
    	testFail(
    		Program.fromClasses(
    		  "class SuperSuper { void m() { } }",
    		  "class Super extends SuperSuper { }",
    		  "class A extends Super { void m() { } }",
    		  "class B { { SuperSuper s = new Super(); s.m(); } }"));
    }
    
    public void test5() {
    	testSucc(
    		Program.fromClasses(
    		  "class Super { }",
    		  "class A extends Super { int m() { return 23; } }",
    		  "class B extends Super { int m() { return 42; } }"),
    		Program.fromClasses(
    	      "class Super { int m() { return 23; } }",
    	      "class A extends Super { }",
    	      "class B extends Super { int m() { return 42; } }"));
    }
    
    public void test6() {
    	testFail(
    		Program.fromClasses(
    		  "class SuperSuper { int m() { return 56; } }",
    		  "class Super extends SuperSuper { }",
    		  "class A extends Super { int m() { return 23; } }",
    		  "class B extends Super { int m() { return 42; } }",
    		  "class C { { SuperSuper s = new Super(); s.m(); } } "));
    }

    public void test7() {
    	testFail(
    		Program.fromClasses(
    		  "class SuperSuper { int m() { return 56; } }",
    		  "class Super extends SuperSuper { }",
    		  "class A extends Super { int m() { return 23; } }",
    		  "class B extends Super { int m() { return super.m(); } }"));
    }
    
    public void test8() {
    	testSucc(
    		Program.fromClasses(
    		  "class Super { }",
    		  "abstract class A extends Super { abstract void m(); }"),
    		Program.fromClasses(
    		  "abstract class Super { abstract void m(); }",
    		  "abstract class A extends Super { }"));
    }
    
    public void test9() {
    	testSucc(
    		Program.fromClasses(
    		  "class Super { int x; }",
    		  "class A extends Super {" +
    		  "  int x;" +
    		  "  int m() { return super.x; }" +
    		  "}"),
    		Program.fromClasses(
    		  "class Super {" +
    		  "  int x;" +
    		  "  int m() { return x; }" +
    		  "}",
    		  "class A extends Super { int x; }"));
    }
    
    public void test10() {
    	testSucc(
    		Program.fromClasses(
    		  "class Super {" +
    		  "  public int m(long l) {" +
    		  "    return 10;" +
    		  "  }" +
    		  "}",
    		  "class A extends Super {" +
    		  "  public int m(int i) {" +
    		  "    return 20;" +
    		  "  }" +
    		  "  public int test() { return new Super().m(2); }" +
    		  "}"),
    		Program.fromClasses(
    				"class Super {" +
    	    		  "  public int m(long l) {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "  public int m(int i) {" +
    	    		  "    return 20;" +
    	    		  "  }" +
    	    		  "}",
    	    		  "class A extends Super {" +
    	    		  "  public int test() { return new Super().m(2); }" +
    	    		  "}"));
    }
    
    public void test11() {
    	testSucc(
    		Program.fromClasses(
    		  "class Super {" +
    		  "  public int k(long l) {" +
    		  "    return 10;" +
    		  "  }" +
    		  "  private int k(int i) {" +
    		  "    return 20;" +
    		  "  }" +
    		  "}",
    		  "class A extends Super {" +
    		  "  public int m() { return k(2); }" +
    		  "}"),
    		Program.fromClasses(
    				"class Super {" +
    	    		  "  public int k(long l) {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "  private int k(int i) {" +
    	    		  "    return 20;" +
    	    		  "  }" +
    	    		  "  public int m() { return k(2); }" +
    	    		  "}",
    	    		  "class A extends Super {" +
    	    		  "}"));
    }
    
    public void test12() {
    	testSucc(
    		Program.fromClasses(
    		  "class SuperS {" +
    		  "  public int m() {" +
    		  "    return 10;" +
    		  "  }" +
    		  "}",
    		  "class Super extends SuperS  {" +    		  
    		  "  public int test() { return m(); }" +
    		  "}",
    		  "class A extends Super {" +
    		  "  public int m() {" +
    		  "    return 20;" +
    		  "  }" +
    		  "}"),
    		Program.fromClasses(
    				"class SuperS {" +
    	    		  "  public int m() {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "}",
    	    		  "class Super extends SuperS  {" +    		  
    	    		  "  public int test() { return m(); }" +
    	    		  "  public int m() {" +
    	    		  "    return 20;" +
    	    		  "  }" +
    	    		  "}",
    	    		  "class A extends Super {" +    	    	
    	    		  "}"));
    }
    
    public void test13() {
    	testSucc(
    		Program.fromClasses(
    		  "public class Super {" +
    		  "  public int k() {" +
    		  "    return 10;" +
    		  "  }" +
    		  "}",
    		  "public class A extends Super {" +
    		  "  public int k() {" +
    		  "    return 20;" +
    		  "  }" +
    		  "  public int m() { return super.k(); }" +
    		  "  public int test() { return m(); }" +
    		  "}"),
    		Program.fromClasses(
    				"public class Super {" +
    	    		  "  public int k() {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "  public int m() { return k(); }" +
    	    		  "}",
    	    		  "public class A extends Super {" +
    	    		  "  public int k() {" +
    	    		  "    return 20;" +
    	    		  "  }" +
    	    		  "  public int test() { return m(); }" +
    	    		  "}"));
    }
    
    public void test454() {
    	testSucc(
    		Program.fromClasses(
    		  "public class Super {" +
    		  "  private int k() {" +
    		  "    return 10;" +
    		  "  }" +
    		  "}",
    		  "public class A extends Super {" +
    		  "  public int k() {" +
    		  "    return 20;" +
    		  "  }" +
    		  "  public int m() { return k(); }" +
    		  "  public int test() { return m(); }" +
    		  "}"),
    		Program.fromClasses(
    				"public class Super {" +
    	    		  "  private int k() {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "  public int m() { return k(); }" +
    	    		  "}",
    	    		  "public class A extends Super {" +
    	    		  "  public int k() {" +
    	    		  "    return 20;" +
    	    		  "  }" +    	    		  
    	    		  "  public int test() { return m(); }" +
    	    		  "}"));
    }
    
    public void test455() {
    	testSucc(
    		Program.fromClasses(
    		  "public class Super {" +
    		  "  private int k() {" +
    		  "    return 10;" +
    		  "  }" +
    		  "}",
    		  "public class A extends Super {" +
    		  "  public int k() {" +
    		  "    return 20;" +
    		  "  }" +
    		  "  public int m() { return k(); }" +
    		  "  public int test() { return m(); }" +
    		  "}"),
    		Program.fromClasses(
    				"public class Super {" +
    	    		  "  private int k() {" +
    	    		  "    return 10;" +
    	    		  "  }" +
    	    		  "  public int m() { return k(); }" +
    	    		  "}",
    	    		  "public class A extends Super {" +
    	    		  "  public int k() {" +
    	    		  "    return 20;" +
    	    		  "  }" +    	    		  
    	    		  "  public int test() { return m(); }" +
    	    		  "}"));
    }
    
    public void test456() {
    	testFail(
    		Program.fromCompilationUnits(
		            new RawCU("Super.java", 
		                      "package p1;"+
		                      "public class Super {"+
		                      "protected	int k(int l) {return 10;}"+
		                      "public	int k(long l) {return 20;}"+
		                      "}"), 
		                      new RawCU("A.java", 
	    		                      "package p2;"+
	    		                      "import p1.*;"+
	    		                      "public class A extends Super {"+	    		                      
	    		                      "	int m() {return new A().k(2);}"+
	    		                      "}"
	    		                      )));
    }
    
    public void test457() {
    	testSucc(
    			Program.fromClasses(
    		    		  "public class Super {" +    		    		  
    		    		  "}",
    		    		  "public class A extends Super {" +    		    		
    		    		  "  public void m() { new B().k();}" +    		    		  
    		    		  "  public void test() { m(); }" +
    		    		  "}" +
    		    		  "public class B extends Super {" +
    		    		  "  public void k() {" +    		    		  
    		    		  "  }" +
    		    		  "}"), Program.fromClasses(
    	    		    		  "public class Super {" +    		    		  
    	    		    		  "}",
    	    		    		  "public class A extends Super {" +
    	    		    		  "  public int k() {" +
    	    		    		  "    return 20;" +
    	    		    		  "  }" +
    	    		    		  "  public int m() { return k(); }" +
    	    		    		  "  public int test() { return m(); }" +
    	    		    		  "}"));
    }
}
