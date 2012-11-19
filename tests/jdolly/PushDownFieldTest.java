//package tests.jdolly;
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyWithField;
//import AST.FieldDeclaration;
//import AST.Program;
//import AST.RefactoringException;
//import AST.TypeDecl;
//
//public class PushDownFieldTest extends Test {
//	private static final String fieldTarget = "fieldid_0";
//	private static final String classTarget = "ClassId_0";
//
//	
//
//	@Override
//	protected Program refactoring(Program program) throws RefactoringException {
//		TypeDecl td = program.findType(classTarget);
//		FieldDeclaration md = td.findField(fieldTarget);
//		md.doPushDown();
//		return program;
//	}
//
//	@Override
//	protected JDolly getCUGen() {
//		JDolly jdolly = new JDollyWithField("../program_counter/alloyTheory/pushdownfield_idioma_novo.als", 2, 3, 1,2);
//		return jdolly;
//	}
//}
