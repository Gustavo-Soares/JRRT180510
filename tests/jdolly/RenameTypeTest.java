//package tests.jdolly;
//
//import java.util.List;
//
//import org.eclipse.jdt.core.dom.CompilationUnit;
//import org.testorrery.IGenerator;
//
//import br.edu.dsc.ufcg.jdolly.JDolly;
//import br.edu.dsc.ufcg.jdolly.JDollyNoField;
//import AST.Program;
//import AST.RefactoringException;
//import AST.TypeDecl;
//
//public class RenameTypeTest extends Test {
//	
//	private static final String newName = "ClassId2_0";
//	private static final String classTarget = "ClassId_1";
//
//	
//
//	@Override
//	protected Program refactoring(Program program) throws RefactoringException {
//		
//		TypeDecl tp = program.findType(classTarget);
//		tp.rename(newName);
//		
//		return program;
//	}
//
//	@Override
//	protected IGenerator<List<CompilationUnit>> getCUGen() {
//		JDolly jdolly = new JDollyNoField("../program_counter/alloyTheory/renameclass_idioma_novo.als",
//				2, 3, 3);
//		return jdolly; 
//	}
//
//}