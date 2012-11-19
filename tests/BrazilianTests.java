package tests;

import junit.framework.TestCase;
import AST.FieldDeclaration;
import AST.IntegerLiteral;
import AST.MemberTypeDecl;
import AST.MethodDecl;
import AST.ParameterDeclaration;
import AST.Program;
import AST.RawCU;
import AST.RefactoringException;
import AST.SimpleSet;
import AST.TypeAccess;
import AST.TypeDecl;

// Tests contributed by Gustavo Soares (Gustavo Soares <gsoares@copin.ufcg.edu.br>)
public class BrazilianTests extends TestCase {
	private @interface BrazilianID {
		String id();
	}
	
	public BrazilianTests(String name) {
		super(name);
	}
	
	private void renameMethodFail(String meth, String newName, Program in) {
		assertNotNull(in);
		MethodDecl methDecl = in.findMethod(meth);
		assertNotNull(methDecl);
		try {
			methDecl.rename(newName);
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) { 
			System.out.println(rfe);
		}
	}

	private void renameFieldSucc(String tpName, String fieldName, String newName, Program in, Program out) {
		assertNotNull(in);
		assertNotNull(out);
		TypeDecl tp = in.findType(tpName);
		assertNotNull(tp);
		FieldDeclaration fd = tp.findField(fieldName);
		assertNotNull(fd);
		try {
			fd.rename(newName);
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.getMessage());
		}
	}
	
	private void pushDownMethodFail(String tp, String meth, Program in) {
		TypeDecl tpDecl = in.findType(tp);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(meth);
		assertNotNull(methDecl);
		try {
			methDecl.doPushDown(false);
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) {
			System.out.println(rfe);
		}
	}

	private void pushDownMethodSucc(String tp, String meth, Program in, Program outProg) {
		TypeDecl tpDecl = in.findType(tp);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(meth);
		assertNotNull(methDecl);
		try {
			methDecl.doPushDown(false);
			assertEquals(outProg.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(outProg.toString(), rfe.getMessage());
		}
	}

	private void pullUpMethodSucc(String tp, String meth, Program in, Program out) {
		TypeDecl tpDecl = in.findType(tp);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(meth);
		assertNotNull(methDecl);
		try {
			methDecl.doPullUp();
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.getMessage());
		}
	}

	private void pullUpMethodFail(String tp, String meth, Program in) {
		TypeDecl tpDecl = in.findType(tp);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(meth);
		assertNotNull(methDecl);
		try {
			methDecl.doPullUp();
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) { 
			System.out.println(rfe);
		}
	}

	private void pullUpFieldSucc(String tpName, String fieldName, Program in, Program out) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
		FieldDeclaration fieldDecl = tpDecl.findField(fieldName);
		assertNotNull(fieldDecl);
		try {
			tpDecl.doPullUpMembers(new MethodDecl[]{}, new boolean[]{}, new FieldDeclaration[]{ fieldDecl }, new MemberTypeDecl[]{});
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.getMessage());
		}
	}
	
	private void pullUpFieldFail(String tpName, String fieldName, Program in) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
		FieldDeclaration fieldDecl = tpDecl.findField(fieldName);
		assertNotNull(fieldDecl);
		try {
			tpDecl.doPullUpMembers(new MethodDecl[]{}, new boolean[]{}, new FieldDeclaration[]{ fieldDecl }, new MemberTypeDecl[]{});
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) {
			System.out.println(rfe.toString());
		}
	}

	private void addParameterSucc(String tpName, String methName, TypeAccess parmType, String parmName, IntegerLiteral defaultValue, Program in, Program out) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(methName);
		assertNotNull(methDecl);
		try {
			methDecl.doAddParameter(new ParameterDeclaration(parmType, parmName), 0, defaultValue, false);
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.getMessage());
		}
	}

	private void addParameterFail(String tpName, String methName, TypeAccess parmType, String parmName, IntegerLiteral defaultValue, Program in) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(methName);
		assertNotNull(methDecl);
		try {
			methDecl.doAddParameter(new ParameterDeclaration(parmType, parmName), 0, defaultValue, false);
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) {
		}
	}

	private void moveMethodSucc(String tpName, String methName, String targetField, Program in, Program out) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
		MethodDecl methDecl = tpDecl.findMethod(methName);
		assertNotNull(methDecl);
		try {
			methDecl.doMoveToField(targetField, true, true, true);
			assertEquals(out.toString(), in.toString());
		} catch(RefactoringException rfe) {
			assertEquals(out.toString(), rfe.getMessage());
		}
	}

	private void moveMethodFail(String tpName, String methName, String targetField, Program in) {
		TypeDecl tpDecl = in.findType(tpName);
		assertNotNull(tpDecl);
//		MethodDecl methDecl = tpDecl.findMethod(methName);
//		MethodDecl methDecl = tpDecl.findMethodBySig(methName);
		SimpleSet s = tpDecl.localMethodsSignature(methName);		
		MethodDecl md = (MethodDecl)s.iterator().next();
		assertNotNull(md);
		try {
			md.doMoveToField(targetField, true, true, true);
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) {
			System.out.println(rfe);
		}
	}

	@BrazilianID(id = "1.1.1")
	public void test1() {
		renameFieldSucc("A", "n", "k", 
						Program.fromClasses(
						  "class A { protected int n=7; }",
						  "class B extends A { protected int k=-29; }",
						  "class C extends B { protected long m() { return super.n; } }"), 
						Program.fromClasses(
						  "class A { protected int k=7; }",
						  "class B extends A { protected int k=-29; }",
						  "class C extends B { protected long m() { return ((A)this).k; } }"));
	}
	
	@BrazilianID(id = "1.1.2")
	public void test2() {
		renameFieldSucc("A", "n", "k",
						Program.fromCompilationUnits(
						  new RawCU("A.java",
						  "package p1;" +
						  "public class A {" +
						  "  protected int n=-31;" +
						  "}"),
						  new RawCU("B.java",
						  "package p2;" +
						  "import p1.*;" +
						  "public class B extends A {" +
						  "  int k=17;" +
						  "  public long m() {" +
						  "    return this.n;" +
						  "  }" +
						  "}")),
						Program.fromCompilationUnits(
						  new RawCU("A.java",
						  "package p1;" +
						  "public class A {" +
						  "  public int k=-31;" +
						  "}"),
						  new RawCU("B.java",
						  "package p2;" +
						  "import p1.*;" +
						  "public class B extends A {" +
						  "  int k=17;" +
						  "  public long m() {" +
						  "    return ((A)this).k;" +
						  "  }" +
						  "}")));
	}
	
	@BrazilianID(id = "1.1.3")
	public void test3() {
		renameFieldSucc("B", "n", "k", 
				Program.fromCompilationUnits(
				new RawCU("A.java",
				"package p1;" +
				"public class A {" +
				"  protected int k=-76;" +
				"}"),
				new RawCU("B.java",
				"package p2;" +
				"import p1.*;" +
				"public class B extends A {" +
				"  int n=-74;" +
				"}"),
				new RawCU("C.java",
				"package p1;" +
				"import p2.*;" +
				"public class C extends B {" +
				"  public long m() {" +
				"    return k;" +
				"  }" +
				"}")),
				Program.fromCompilationUnits(
				new RawCU("A.java",
				"package p1;" +
				"public class A {" +
				"  protected int k=-76;" +
				"}"),
				new RawCU("B.java",
				"package p2;" +
				"import p1.*;" +
				"public class B extends A {" +
				"  int k=-74;" +
				"}"),
				new RawCU("C.java",
				"package p1;" +
				"import p2.*;" +
				"public class C extends B {" +
				"  public long m() {" +
				"    return ((A)this).k;" +
				"  }" +
				"}")));
	}
	
	@BrazilianID(id = "1.2.1")
	public void test4() {
		renameFieldSucc("B", "n", "k", 
				Program.fromCompilationUnits(
				new RawCU("A.java",
				"package p1;" +
				"public class A {" +
				"  public int k=-76;" +
				"}"),
				new RawCU("B.java",
				"package p2;" +
				"import p1.*;" +
				"public class B extends A {" +
				"  public int n=-74;" +
				"}"),
				new RawCU("C.java",
				"package p1;" +
				"import p2.*;" +
				"public class C extends B {" +
				"  public long m() {" +
				"    return k;" +
				"  }" +
				"}")),
				Program.fromCompilationUnits(
				new RawCU("A.java",
				"package p1;" +
				"public class A {" +
				"  public int k=-76;" +
				"}"),
				new RawCU("B.java",
				"package p2;" +
				"import p1.*;" +
				"public class B extends A {" +
				"  public int k=-74;" +
				"}"),
				new RawCU("C.java",
				"package p1;" +
				"import p2.*;" +
				"public class C extends B {" +
				"  public long m() {" +
				"    return ((A)this).k;" +
				"  }" +
				"}")));
	}
	
	@BrazilianID(id = "2.1.1")
	public void test5() {
		pushDownMethodSucc("A", "m", 
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"public class A {" +
					"  public long m() {" +
					"    return A.this.k();" +
					"  }" +
					"  public long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"public class B extends A { }")), 
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"public class A {" +
					"  public long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"public class B extends A {" +
					"  public long m() {" +
					"    return this.k();" +
					"  }" +
					"}")));
	}

	@BrazilianID(id = "2.1.2")
	public void test6() {
		pushDownMethodSucc("A", "m",
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  public long m() {" +
					"    return new A().k();" +
					"  }" +
					"  protected long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A { }")),
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  public long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long m() {" +
					"    return new A().k();" +
					"  }" +
					"}")));
	}

	@BrazilianID(id = "2.2.1")
	public void test7() {
		pushDownMethodSucc("A", "m",
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"public class A {" +
					"  public long m() {" +
					"    return k();" +
					"  }" +
					"  protected long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"public class B extends A {" +
					"  public long k() {" +
					"    return 2;" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")),
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"public class A {" +
					"  protected long k() {" +
					"    return 1;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"public class B extends A {" +
					"  public long k() {" +
					"    return 2;" +
					"  }" +
					"  public long m() {" +
					"    return k();" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")));
	}

	@BrazilianID(id = "2.2.2")
	public void test8() {
		pushDownMethodSucc("A", "m",
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  public long m() {" +
					"    return new A().k(2);" +
					"  }" +
					"  protected long k(int a) {" +
					"    return 1;" +
					"  }" +
					"  public long k(long a) {" +
					"    return 2;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")),
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  public long k(int a) {" +
					"    return 1;" +
					"  }" +
					"  public long k(long a) {" +
					"    return 2;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long m() {" +
					"    return new A().k(2);" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")));
	}
	
	@BrazilianID(id = "3.1.1")
	public void test9() {
		pullUpMethodFail("B", "m", Program.fromClasses(
							"public class A { }",
							"public class B extends A {" +
							"  public long m() {" +
							"    return B.this.k();" +
							"  }" +
							"  public long k() {" +
							"    return 1;" +
							"  }" +
							"}"));
	}
	
	@BrazilianID(id = "3.1.2")
	public void test10() {
		pullUpMethodSucc("B", "m", 
					Program.fromCompilationUnits(
					new RawCU("C.java",
					"package p2;" +
					"public class C {" +
					"  protected long k(long a) {" +
					"    return 3;" +
					"  }" +
					"}"),
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A extends C { }"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long m() {" +
					"    return new C().k(2);" +
					"  }" +
					"  public long k(int a) {" +
					"    return 1;" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")),
					Program.fromCompilationUnits(
					new RawCU("C.java",
					"package p2;" +
					"public class C {" +
					"  public long k(long a) {" +
					"    return 3;" +
					"  }" +
					"}"),
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A extends C {" +
					"  public long m() {" +
					"    return new C().k(2);" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long k(int a) {" +
					"    return 1;" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")));
	}
	
	@BrazilianID(id = "3.2.1")
	public void test11() {
		pullUpMethodFail("B", "m", Program.fromClasses(
					"public class A {" +
					"  public int k() {" +
					"    return 10;" +
					"  }" +
					"}",
					"public class B extends A {" +
					"  public int k() {" +
					"    return 20;" +
					"  }" +
					"  public int m() {" +
					"    return super.k();" +
					"  }" +
					"  public int test() {" +
					"    return m();" +
					"  }" +
					"}"));
	}

	@BrazilianID(id = "3.2.2")
	public void test12() {
		pullUpMethodSucc("C", "m", 
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  protected long k(int a) {" +
					"    return 10;" +
					"  }" +
					"  public long k(long a) {" +
					"    return 20;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"}"),
					new RawCU("C.java",
					"package p1;" +
					"import p2.*;" +
					"public class C extends B{" +
					"  public long m() {" +
					"    return new A().k(2);" +
					"  }" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")),
					Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"public class A {" +
					"  public long k(int a) {" +
					"    return 10;" +
					"  }" +
					"  public long k(long a) {" +
					"    return 20;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"import p1.*;" +
					"public class B extends A {" +
					"  public long m() {" +
					"    return new A().k(2);" +
					"  }" +
					"}"),
					new RawCU("C.java",
					"package p1;" +
					"import p2.*;" +
					"public class C extends B{" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}")));
	}
	
	@BrazilianID(id="4.1.1")
	public void test13() {
		pullUpFieldSucc("C", "f", 
						Program.fromCompilationUnits(
						new RawCU("A.java",
						"public class A {" +
						"  long f = 79;" +
						"}"),
						new RawCU("B.java",
						"public class B extends A {" +
						"}"),
						new RawCU("C.java",
						"public class C extends B {" +
						"  int f = -53;" +
						"  public long m() {" +
						"    return super.f;" +
						"  }" +
						"}")), 
						Program.fromCompilationUnits(
						new RawCU("A.java",
						"public class A {" +
						"  long f = 79;" +
						"}"),
						new RawCU("B.java",
						"public class B extends A {" +
						"  int f = -53;" +
						"}"),
						new RawCU("C.java",
						"public class C extends B {" +
						"  public long m() {" +
						"    return ((A)this).f;" +
						"  }" +
						"}")));
	}
	
	@BrazilianID(id="4.1.2")
	public void test14() {
		pullUpFieldSucc("C", "f",
						Program.fromCompilationUnits(
						new RawCU("A.java",
						"package p1;" +
						"public class A {" +
						"  protected int f=1;" +
						"}"),
						new RawCU("B.java",
						"package p2;" +
						"import p1.*;" +
						"public class B extends A {" +
						"  public long m() {" +
						"    return this.f;" +
						"  }" +
						"}"),
						new RawCU("C.java",
						"package p1;" +
						"import p2.*;" +
						"public class C extends B {" +
						"  protected long f=2;" +
						"}")),
						Program.fromCompilationUnits(
						new RawCU("A.java",
						"package p1;" +
						"public class A {" +
						"  public int f=1;" +
						"}"),
						new RawCU("B.java",
						"package p2;" +
						"import p1.*;" +
						"public class B extends A {" +
						"  public long m() {" +
						"    return ((A)this).f;" +
						"  }" +
						"  protected long f=2;" +
						"}"),
						new RawCU("C.java",
						"package p1;" +
						"import p2.*;" +
						"public class C extends B {" +
						"}")));						
	}
	
	@BrazilianID(id="5.1.1")
	public void test15() {
		addParameterSucc("A", "k", new TypeAccess("int"), "i", new IntegerLiteral("0"), 
					 Program.fromCompilationUnits(
						new RawCU("A.java",
						"package p1;" +
						"public class A {" +
						"  protected long k() {" +
						"    return 0;" +
						"  }" +
						"  protected long m() {" +
						"    return k();" +
						"  }" +
						"}"),
						new RawCU("B.java",
						"package p2;" +
						"import p1.*;" +
						"public class B extends A {" +
						"  protected long k(int a) {" +
						"    return 2;" +
						"  }" +
						"  public long test() {" +
						"    return m();" +
						"  }" +
						"}")), 
					 Program.fromCompilationUnits(
						new RawCU("A.java",
						"package p1;" +
						"public class A {" +
						"  long k(int i) {" +
						"    return 0;" +
						"  }" +
						"  protected long m() {" +
						"    return k(0);" +
						"  }" +
						"}"),
						new RawCU("B.java",
						"package p2;" +
						"import p1.*;" +
						"public class B extends A {" +
						"  protected long k(int a) {" +
						"    return 2;" +
						"  }" +
						"  public long test() {" +
						"    return m();" +
						"  }" +
						"}")));
	}
	
	@BrazilianID(id="5.1.2")
	public void test16() {
		addParameterFail("A", "k", new TypeAccess("int"), "i", new IntegerLiteral("0"), 
					 	Program.fromCompilationUnits(
						new RawCU("A.java",
						"package p1;" +
						"public class A {" +
						"  long k() {" +
						"    return 10;" +
						"  }" +
						"  protected long m() {" +
						"    return A.this.k();" +
						"  }" +
						"}"),
						new RawCU("B.java",
						"package p2;" +
						"import p1.*;" +
						"public class B extends A {" +
						"}"),
						new RawCU("C.java",
						"package p1;" +
						"import p2.*;" +
						"public class C extends B {" +
						"  public long k() {" +
						"    return 39;" +
						"  }" +
						"  public long test() {" +
						"    return m();" +
						"  }" +
						"}")));
	}
	
	@BrazilianID(id="6.1.1")
	public void test17() {
		Program in = Program.fromClasses(
					"public class A {" +
					"  public long getF() {" +
					"    return 10;" +
					"  }" +
					"  public long m() {" +
					"    return getF();" +
					"  }" +
				//	"  { new B().test(); }" +
					"}",
					"public class B extends A {" +
					"  public long f;" +
					"  public long test() {" +
					"    return m();" +
					"  }" +
					"}");
		TypeDecl tpDecl = in.findType("B");
		assertNotNull(tpDecl);
		FieldDeclaration fieldDecl = tpDecl.findField("f");
		assertNotNull(fieldDecl);
		try {
			fieldDecl.doSelfEncapsulate();
			assertEquals("<failure>", in.toString());
		} catch(RefactoringException rfe) {}
	}
	
	@BrazilianID(id="7.1.1")
	public void test18() {
		Program in = Program.fromCompilationUnits(
					new RawCU("B.java",
					"package p2;" +
					"public class B { }"),
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A extends B {" +
					"  public B f = null;" +
					"  public long m(int a) {" +
					"    return 0;" +
					"  }" +
					"  public long test() {" +
					"    return new A().m(2);" +
					"  }" +
					"}"),
					new RawCU("C.java",
					"package p1;" +
					"import p2.*;" +
					"public class C extends B {" +
					"  protected long m(int a) {" +
					"    return 2;" +
					"  }" +
					"}"));
		Program out = Program.fromCompilationUnits(
					new RawCU("B.java",
					"package p2;" +
					"public class B {" +
					"  public long m(int a) {" +
					"    return 0;" +
					"  }" +
					"}"),
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A extends B {" +
					"  public B f = null;" +
					"  public long test() {" +
					"    return f.m(2);" +
					"  }" +
					"}"),
					new RawCU("C.java",
					"package p1;" +
					"import p2.*;" +
					"public class C extends B {" +
					"  public long m(int a) {" +
					"    return 2;" +
					"  }" +
					"}"));
		moveMethodSucc("A", "m", "f", in, out);
	}
	
	@BrazilianID(id = "7.1.2")
	public void test19() {
		Program in = Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A {" +
					"  public B f = null;" +
					"  public long m(int a) {" +
					"    return 0;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"public class B {" +
					"  public long m(int a) {" +
					"    return 2;" +
					"  }" +
					"}"));
		Program out = Program.fromCompilationUnits(
				new RawCU("A.java",
				"package p1;" +
				"import p2.*;" +
				"public class A {" +
				"  public B f = null;" +
				"}"),
				new RawCU("B.java",
				"package p2;" +
				"public class B {" +
				"  public long m(int a) {" +
				"    return 2;" +
				"  }" +
				"  public long m(p1.A a0, int a) {" +
				"    return 0;" +
				"  }" +
				"}"));
		moveMethodSucc("A", "m", "f", in, out);
	}
	
	@BrazilianID(id="7.1.3")
	public void test20() {
		Program in = Program.fromCompilationUnits(
					new RawCU("A.java",
					"package p1;" +
					"import p2.*;" +
					"public class A {" +
					"  public B f = null;" +
					"  public long m() {" +
					"    return 0;" +
					"  }" +
					"}"),
					new RawCU("B.java",
					"package p2;" +
					"public class B {" +
					"}"),
					new RawCU("C.java",
					"package p1;" +
					"import p2.*;" +
					"public class C extends B {" +
					"  long m() {" +
					"    return 1;" +
					"  }" +
					"}"));
		moveMethodFail("A", "m", "f", in);
	}
	
	// Test 7.2.1 omitted; I'm not sure what the correct output should be
	//nao é bug.
	@BrazilianID(id="7.2.2")
	public void test22() {
		Program in = Program.fromCompilationUnits(
				new RawCU("ClassId_1.java",
				"package Package_1;" +
				"public class ClassId_1 {" +
				"}"),
				new RawCU("ClassId_0.java",
				"package Package_0;" +
				"import Package_1.*;" +
				"public class ClassId_0 extends ClassId_1 {" +
				"  public long methodid_0(){" +
				"    return new A_0().m_0(2);" +
				"  }" +
				"}"),
				new RawCU("A_0.java",
				"package Package_1;" +
				"import Package_0.*;" +
				"public class A_0 extends ClassId_0 {" +
				"  public ClassId_0 fieldid_0=null;" +
				"  public long m_0(  long a){" +
				"    return 1;" +
				"  }" +
				"  protected long m_0(  int a){" +
				"    return 0;" +
				"  }" +
				"}"));
	moveMethodFail("A_0", "m_0(int)", "fieldid_0", in);
	}
	
	@BrazilianID(id="7.1.3")
	public void test23() {
		Program in = Program.fromCompilationUnits(
					new RawCU("ClassId_1.java",
					"package Package_1;" +
					"public class ClassId_1 {" +
					"}"),
					new RawCU("ClassId_0.java",
					"package Package_0;" +
					"import Package_1.*;" +
					"public class ClassId_0 extends A_0 {" +
					"  public long methodid_0(){" + 
					"   return m_0(2); " + 
					" }"+ 
					"}"),
					new RawCU("A_0.java",
					"package Package_1;" +
					"import Package_0.*;" +
					"public class A_0 extends ClassId_1 {" +
					"public ClassId_0 fieldid_0=null;" +
					"protected long m_0(  long a){" +
					"    return 1;" +
					"  }" +
					"long m_0(  int a){" + 
					"return 0;" + 
					" }" + 
					"}"));
		moveMethodFail("A_0", "m_0(int)", "fieldid_0", in);
	}
	
	
//	//new bugs
//	//detalhe: só é bug no open world 
//	@BrazilianID(id="enable overriding")
//	public void test24() {
//		Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//									"import Package_0.*;" +
//									"public class ClassId_1 extends ClassId_0 {" +
//									"  public long m_0(){" +
//									"    return new ClassId_1().n_0(2);" +
//									"  }" +
//									"  public long n_0(  int a){" +
//									"    return 0;" +
//									"  }}"),
//					new RawCU("ClassId_0.java",
//					"package Package_0;" +
//					"public class ClassId_0 {" +
//					"  public long k_0(  int a){" +
//					"    return 1;" +
//					"  }" +
//					"}"));
//		renameMethodFail("n_0", "k_0", in);
//	}
//	
//	@BrazilianID(id="enable overloading")
//	public void test25() {
//		Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//							"import Package_0.*;" +
//							"public class ClassId_1 extends ClassId_0 {" +
//							"  protected long n_0(  int a){" +
//							"    return 0;" +
//							"  }" +
//							"  public long m_0(){" +
//							"    return k_0(2);" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_0.java",
//					"package Package_0;" +
//					"public class ClassId_0 {" +
//					"  public long k_0(  long a){" +
//					"    return 1;" +
//					"  }" +
//					"}"));
//		renameMethodFail("n_0", "k_0", in);
//	}
//	
	
	
	
//	//comment: This bug does not happen in JRRT v1
//	@BrazilianID(id="implicit cast")
//		public void test27() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//									"public class ClassId_1 extends ClassId_0 {" +
//									"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_1;" +
//									"public class ClassId_0 {" +
//									"  long methodid_1(  long a){" +
//									"    return 1;" +
//									"  }" +
//									"  public long m_0(){" +
//									"    return methodid_1(2);" +
//									"  }" +
//									"}"),
//					new RawCU("ClassId_1.java",
//							"package Package_0;" +
//									"import Package_1.*;" +
//									"public class ClassId_1 extends ClassId_0 {" +
//									"  protected long methodid_1(  int a){" +
//									"    return 0;" +
//									"  }" +
//									"  public long methodid_0(){" +
//									"    return m_0();" +
//									"  }" +
//									"}"));
//						
//		
//			pushDownMethodFail("ClassId_0","m_0", in);
//		}
		
//		@BrazilianID(id="enable overriding")
//		public void test28() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//							"public class ClassId_1 extends ClassId_0 {" +
//							"}"),
//					new RawCU("ClassId_1.java",
//							"package Package_0;" +
//							"import Package_1.*;" +
//							"public class ClassId_1 extends ClassId_0 {" +
//							"  public long methodid_1(){" +
//							"    return 0;" +
//							"  }" +
//							"  public long methodid_0(){" +
//							"    return m_0();" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_1;" +
//							"import Package_0.*;" +
//							"public class ClassId_0 {" +
//							"  protected long methodid_1(){" +
//							"    return 1;" +
//							"  }" +
//							"  public long m_0(){" +
//							"    return new ClassId_1().methodid_1();" +
//							"  }" +
//							"}"));
//			
//		
//			pushDownMethodFail("ClassId_0","m_0", in);
//		}
//		
//		@BrazilianID(id="enable overloading")
//		public void test29() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//							"public class ClassId_1 extends ClassId_0 {" +
//							"}"),
//					new RawCU("ClassId_1.java",
//							"package Package_0;" +
//							"import Package_1.*;" +
//							"public class ClassId_1 extends ClassId_0 {" +
//							"  public long methodid_1(  long a){" +
//							"    return 0;" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_1;" +
//							"import Package_0.*;" +
//							"public class ClassId_0 {" +
//							"  long methodid_1(  int a){" +
//							"    return 1;" +
//							"  }" +
//							"  public long methodid_0(){" +
//							"    return m_0();" +
//							"  }" +
//							"  public long m_0(){" +
//							"    return new ClassId_1().methodid_1(2);" +
//							"  }}"));
//						pushDownMethodFail("ClassId_0","m_0", in);
//		}
//		
//		//pullupmethod
//		@BrazilianID(id="enable overloading")
//		public void test30() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("ClassId_2.java",
//							"package Package_1;" +
//							"public class ClassId_2 {" +
//							"  protected long methodid_0(  long a){" +
//							"    return 0;" +
//							"  }}"),
//					new RawCU("ClassId_1.java",
//							"package Package_0;" +
//							"import Package_1.*;" +
//							"public class ClassId_1 extends ClassId_2 {" +
//							"  private long methodid_0(  int a){" +
//							"    return 1;" +
//							" }" +
//							"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_0;" +
//							"public class ClassId_0 extends ClassId_1 {" +
//							"  public long methodid_1(){" +
//							"    return m_0();" +
//							"  }" +
//							"  public long m_0(){" +
//							"    return methodid_0(2);" +
//							"  }}"));
//						pullUpMethodFail("ClassId_0","m_0", in);
//		}	
//		
//		//move method
//		
//		@BrazilianID(id="disable overriding")
//		public void test31() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("A_0.java",
//							"package Package_0;" +
//							"import Package_1.*;" +
//							"public class A_0 extends ClassId_0 {" +
//							"  public ClassId_0 fieldid_0=null;" +
//							"  public long methodid_0(){" +
//							"    return super.m_0(2);" +
//							"  }" +
//							"  public long m_0(  int a){" +
//							"    return 0;" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//							"					public class ClassId_1 {" +							
//							"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_1;" +
//							"public class ClassId_0 extends ClassId_1 {" +
//							"  public long m_0(  int a){" +
//							"    return 1;" +
//							"  }" +
//							"}"));
//						moveMethodFail("A_0", "m_0", "fieldid_0", in);
//		}
//		
//		@BrazilianID(id="enable overloading")
//		public void test32() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("A_0.java",
//							"package Package_1;" +
//							"import Package_0.*;" +
//							"public class A_0 {" +
//							"  public ClassId_0 fieldid_0=null;" +
//							"  private long m_0(  int a){" +
//							"    return 0;" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_1.java",
//							"package Package_1;" +
//							"public class ClassId_1 extends A_0 {" +
//							"  protected long m_0(  long a){" +
//							"    return 1;" +
//							"  }" +
//							"}"),
//					new RawCU("ClassId_0.java",
//							"package Package_0;" +
//							"import Package_1.*;" +
//							"public class ClassId_0 extends ClassId_1 {" +
//							"  public long methodid_0(){" +
//							"    return m_0(2);" +
//							"  }" +
//							"}"));
//			
//						moveMethodFail("A_0", "m_0(int)", "fieldid_0", in);
//		}	
		
		
//		//OVERLY STRONG CONDITIONS
//		@BrazilianID(id="overriding has changed")
//		public void test33() {
//			Program in = Program.fromCompilationUnits(
//						new RawCU("ClassId_1.java",
//								"package Package_1;" +
//										"import Package_0.*;" +
//										"public class ClassId_1 extends ClassId_0 {" +
//										"  public long m_0(){" +
//										"    return new ClassId_1().n_0(2);" +
//										"  }" +
//										"  public long n_0(  int a){" +
//										"    return 0;" +
//										"  }}"),
//						new RawCU("ClassId_0.java",
//						"package Package_0;" +
//						"public class ClassId_0 {" +
//						"  public long k_0(  int a){" +
//						"    return 1;" +
//						"  }" +
//						"}"));
//			renameMethodFail("n_0", "k_0", in);
//		}
//		
//		@BrazilianID(id="cannot access method")
//		public void test34() {
//			
//			Program in = Program.fromCompilationUnits(
//					new RawCU("Class1_0.java",
//							"package P1_0;" +
//							"import P2_0.*;" +
//							"public class Class1_0 extends Class3_0 {" +
//							"  public long m_0(){" +
//							"    return this.k_0(2);" +
//							"  }" +
//							"}"),
//					new RawCU("Class2_0.java",
//							"package P2_0;" +
//							"import P1_0.*;" +
//							"public class Class2_0 extends Class1_0 {" +
//							"  protected long k_0(  int a){" +
//							"    return 59;" +
//							"  }" +
//							"  public long test_0(){" +
//							"    return m_0();" +
//							"  }" +
//							"}"),
//					new RawCU("Class3_0.java",
//							"package P2_0;" +
//							"public class Class3_0 {" +
//							"  protected long k_0(  int a){" +
//							"    return 96;" +
//							"  }" +
//							"}"));
//			
//		
//			pushDownMethodFail("Class1_0","m_0", in);
//		}
//		
//		//pullupmethod
//				@BrazilianID(id="method is used")
//				public void test35() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("Class1_0.java",
//									"package P1_0;" +
//									"public class Class1_0 {" +
//									"  private long k_0(  long a){" +
//									"    return 39;" +
//									"  }" +
//									"}"),
//							new RawCU("Class2_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class2_0 extends Class1_0 {" +
//									"  public long m_0(){" +
//									"    return new Class3_0().k_0(2);" +
//									"  }" +
//									"  public long test_0(){" +
//									"    return m_0();" +
//									"  }" +
//									"}"),
//							new RawCU("Class3_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class3_0 extends Class1_0 {" +
//									"  public long k_0(  int a){" +
//									"    return 49;" +
//									"  }" +
//									"}"));
//								pullUpMethodFail("Class2_0","m_0", in);
//				}	
//				
//				//pullupmethod
//				@BrazilianID(id="cannot access method")
//				public void test36() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("Class1_0.java",
//									"package P1_0;" +
//									"public class Class1_0 {" +
//									"  protected long k_0(  int a){" +
//									"    return 5;" +
//									"  }" +
//									"}"),
//							new RawCU("Class2_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class2_0 extends Class1_0 {" +
//									"  public long m_0(){" +
//									"    return k_0(2);" +
//									"  }" +
//									"  public long k_0(  int a){" +
//									"    return 64;" +
//									"  }" +
//									"  public long test_0(){" +
//									"    return m_0();" +
//									"  }" +
//									"}"),
//							new RawCU("Class3_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class3_0 extends Class1_0 {" +
//									"}"));
//								pullUpMethodFail("Class2_0","m_0", in);
//				}		
//				
//				
//				//pull up field
//				@BrazilianID(id="cannot access variable")
//				public void test37() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("Class1_0.java",
//									"package P1_0;" +
//									"public class Class1_0 {" +
//									"}"),
//							new RawCU("Class2_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class2_0 extends Class1_0 {" +
//									"  long f_0=6;" +
//									"  public long m_0(){" +
//									"    return Class2_0.this.f_0;" +
//									"  }" +
//									"}"),
//							new RawCU("Class3_0.java",
//									"package P2_0;" +
//									"public class Class3_0 {" +
//									"  protected int f_0=7;" +
//									"}"));
//								pullUpFieldFail("Class2_0","f_0", in);
//				}	
//				
//				//encapsulate field
//				@BrazilianID(id="cannot insert method here")
//				public void test38() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("Class1_0.java",
//									"package P1_0;" +
//									"public class Class1_0 {" +
//									"  public long f_0=32;" +
//									"  protected long getf_0(){" +
//									"    return 10;" +
//									"  }" +
//									"}"),
//							new RawCU("Class2_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class2_0 extends Class1_0 {" +
//									"}"),
//							new RawCU("Class3_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class3_0 extends Class1_0 {" +
//									"  protected int f_0=45;" +
//									"  public long m_0(){" +
//									"    return getf_0();" +
//									"  }" +
//									"}"));
//					TypeDecl tpDecl = in.findType("Class1_0");
//					assertNotNull(tpDecl);
//					FieldDeclaration fieldDecl = tpDecl.findField("f_0");
//					assertNotNull(fieldDecl);
//					try {
//						fieldDecl.doSelfEncapsulate();
//						assertEquals("<failure>", in.toString());
//					} catch(RefactoringException rfe) {
//						System.out.println(rfe.toString());
//					}
//				}	
//				
//				@BrazilianID(id="Cannot inline ambiguous method call")
//				public void test39() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("Class1_0.java",
//									"package P1_0;" +
//									"import P2_0.*;" +
//									"public class Class1_0 extends Class2_0 {" +
//									"  public Class2_0 fieldid_0=null;" +
//									"  public long k_0(  int a){" +
//									"    return 38;" +
//									"  }" +
//									"}"),
//							new RawCU("Class3_0.java",
//									"package P1_0;" +
//									"public class Class3_0 {" +
//									"  long k_0(  long a){" +
//									"    return 77;" +
//									"  }" +
//									"  public long m_0(){" +
//									"    return new Class3_0().k_0(2);" +
//									"  }" +
//									"}"),
//							new RawCU("Class2_0.java",
//									"package P2_0;" +
//									"import P1_0.*;" +
//									"public class Class2_0 extends Class3_0 {" +
//									"}"));
//							moveMethodFail("Class1_0", "k_0(int)", "fieldid_0", in);
//				}
		
//				//apagar
//				//encapsulate field
//				@BrazilianID(id="6.2.1")
//				public void test41() {
//					
//					Program in = Program.fromCompilationUnits(
//							new RawCU("A.java",
//									"package p1;" +
//									"public class A {" +
//									"  protected long getf(){" +
//									"    return 0;" +
//									"  }" +
//									"}"),
//							new RawCU("B.java",											
//									"package p2;" +
//									"import p1.*;" +
//									"public class B extends A {" +
//									"  public long f=10;" +
//									"  public long test(){" +
//									"    return m();" +
//									"  }" +
//									"  public long m(){" +
//									"    return getf();" +
//									"  }" +
//									"}")
//							);
//					TypeDecl tpDecl = in.findType("B");
//					assertNotNull(tpDecl);
//					FieldDeclaration fieldDecl = tpDecl.findField("f");
//					assertNotNull(fieldDecl);
//					try {
//						fieldDecl.doSelfEncapsulate();
//						assertEquals("<failure>", in.toString());
//					} catch(RefactoringException rfe) {
//						System.out.println(rfe.toString());
//					}
//				}	
//				
//				//TESTE: APAGAR:
//				@BrazilianID(id = "3.2.1")
//				public void test42() {
//					pullUpFieldFail("C", "f", Program.fromClasses(
//								"public class A {public int f;}" +
//								"public class B extends A {}" +								
//								"public class C extends B{" +
//								"	 private int f;" +
//								"		void m() {super.f = 10;}" +
//								"}"));
//				}
		
}
