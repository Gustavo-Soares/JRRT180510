//package tests.jdolly;
//
//
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyWithField;
//import AST.FieldDecl;
//import AST.FieldDeclaration;
//import AST.MemberTypeDecl;
//import AST.MethodDecl;
//import AST.Program;
//import AST.RefactoringException;
//import AST.TypeDecl;
//
//public class PullUpFieldTest extends Test {
//
//	private static final String fieldTarget = "fieldid_0";
//	private static final String classTarget = "ID1_0";
//
//
//
//	@Override
//	protected Program refactoring(Program program) throws RefactoringException {
//		TypeDecl td = program.findType(classTarget);
//		
//		FieldDeclaration[] fields = new FieldDeclaration[1];
//		MethodDecl[] methods = new MethodDecl[0];
//		MemberTypeDecl[] types = new MemberTypeDecl[0];
//		FieldDeclaration fd = td.findField(fieldTarget);
//		fields[0] = fd;
//		
//		td.doPullUpMembers(methods, null, fields, types);
//		
//		return program;
//	}
//
//	@Override
//	protected JDolly getCUGen() {
//		JDolly jdolly = new JDollyWithField("../program_counter/alloyTheory/pullupfield_idioma_novo.als", 2, 3, 1,2);
//		return jdolly;
//	}
//
//}
