//package tests.jdolly;
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyNoField;
//import AST.MethodDecl;
//import AST.Program;
//import AST.RefactoringException;
//import AST.TypeDecl;
//
//public class RenameMethodTest extends Test {
//
//	private static final String methodTarget = "n_0";
//	private static final String newName = "k_0";
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
//		MethodDecl md = program.findMethod(methodTarget);
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
//		JDolly jdolly = new JDollyNoField("../program_counter/alloyTheory/renamemethod_idioma_novo.als", 2, 3, 3);	
//		return jdolly;
//	}
//
//}
