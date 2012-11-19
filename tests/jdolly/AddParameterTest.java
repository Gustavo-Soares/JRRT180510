package tests.jdolly;


import jdolly.JDolly;
import jdolly.JDollyImp;
import AST.IntegerLiteral;
import AST.Literal;
import AST.MethodDecl;
import AST.ParameterDeclaration;
import AST.PrimitiveType;
import AST.Program;
import AST.RefactoringException;
import AST.SimpleSet;
import AST.TypeDecl;

public class AddParameterTest extends Test {

	@Override
	protected Program refactoring(Program program) throws RefactoringException {
		TypeDecl td = program.findSimpleType("ClassId_0");
//		program.findMethodBySig("methodid_1()");
		SimpleSet s = td.localMethodsSignature("methodid_1()");
		if (s == null || s.isEmpty()) {
			td = program.findSimpleType("ClassId_1");
			s = td.localMethodsSignature("methodid_0()");
			if (s== null || s.isEmpty()) {
				td = program.findSimpleType("ClassId_2");
				s = td.localMethodsSignature("methodid_1()");
			}
		}
		MethodDecl md = (MethodDecl) s.iterator().next();
//		MethodDecl md = program.findMethodBySig("methodid_1()");
		PrimitiveType param = new PrimitiveType();
		param.setID("int");
		Literal l = new IntegerLiteral("0");
		md.doAddParameter(new ParameterDeclaration(param, "i"), 0, l, false);
		return program;
	}

	@Override
	protected JDolly getCUGen() {
		return new JDollyImp(
				"../program_counter/alloyTheory/addparameter_idioma_novo.als",
				2, 3, 3);
	}

}
