//package tests.jdolly; 
//
//import java.util.Collection;
//import java.util.List;
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyNoField;
//
//
//
//import tests.CompileHelper;
//import AST.CompilationUnit;
//import AST.MethodDecl;
//import AST.Program;
//import AST.RefactoringException;
//import AST.TypeDecl;
//
//public class PushDownMethodTest extends Test {
//
//	private static final String methodTarget = "m_0";
//	
//	
//
//	public static void main(String[] args) {
//		new PushDownMethodTest().run();
//	}
//	
//	@Override
//	protected Program refactoring(Program program) throws RefactoringException {
//			
//		MethodDecl md = program.findMethod(methodTarget);
//		md.doPushDown(false);
//
//		return program;
//	}
//
//	
//
//	@Override
//	protected JDolly getCUGen() {		
//		JDolly jdolly = new JDollyNoField("../program_counter/alloyTheory/pushdownmethod_final.als",
//				2, 3, 4);
//		return jdolly; 
//	}
//	 
//	
//
//}