package tests.jdolly; 

import jdolly.JDolly;
import jdolly.JDollyImp;
import AST.MethodDecl;
import AST.Program;
import AST.RefactoringException;
import AST.TypeDecl;


public class PushDownMethodTest extends Test {

	private static final String methodTarget = "m_0";
	
	

	public static void main(String[] args) {
		new PushDownMethodTest().run();
	}
	
	@Override
	protected Program refactoring(Program program) throws RefactoringException {
			
		MethodDecl md = program.findMethod(methodTarget);
		md.doPushDown(false);

		return program;
	}

	

	@Override
	protected JDolly getCUGen() {		
		JDolly jdolly = new JDollyImp("jdolly-0.4/alloyTheory/pushdownmethod_final.als",2, 3, 4);
		jdolly.setExactMaxPackages(true);
//		jdolly.setMaxClassNames(3);
//		jdolly.setExactMaxClassNames(true);
		jdolly.setMaxMethodNames(3);
		jdolly.setExactMaxMethodNames(true);
		
//		JDolly jdolly = new JDollyNoField("../program_counter/alloyTheory/pushdownmethod_final.als",
//				2, 3, 4);
		return jdolly; 
	}
	 
	

}