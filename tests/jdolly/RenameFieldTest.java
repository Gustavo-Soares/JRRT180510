//package tests.jdolly;
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyWithField;
//import AST.FieldDeclaration;
//import AST.Program;
//import AST.RefactoringException;
//
//public class RenameFieldTest extends Test {
//
//	private static final String fieldTarget = "fieldid_0";
//	private static final String newName = "fieldid_1";
//	
//	
//
//	public static void main(String[] args) {
//		new RenameMethodTest().run();
//	}
//	
//	@Override
//	protected Program refactoring(Program program) throws RefactoringException {
////	TypeDecl td = program.findType(classTarget);
//			
//		FieldDeclaration md = program.findField(fieldTarget);
//
//		md.rename(newName);
//
//		return program;
//	}
//
//	
//
//	@Override
//	protected JDolly getCUGen() {
//		JDolly jdolly = new JDollyWithField("../program_counter/alloyTheory/renamefield_idioma_novo.als", 2, 3, 1,2);
//		return jdolly;
//	}
//
//}
//
