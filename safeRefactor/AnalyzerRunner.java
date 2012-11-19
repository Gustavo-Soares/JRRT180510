package safeRefactor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AnalyzerRunner {
	public static void execute(String source, String target) {

		File buildFile = new File("ant/build.xml");

		Project p = new Project();
		p.setUserProperty("source_folder", source);
		p.setUserProperty("target_folder", target);
		//p.setUserProperty("tempDir", tests.jdolly.Test.TEMP_DIR);
		p.setUserProperty("tempDir", "/tmp");

		OutputStream out = new ByteArrayOutputStream();
		OutputStream outError = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		PrintStream psError = new PrintStream(outError);
		DefaultLogger consoleLogger = new DefaultLogger();
//		consoleLogger.setErrorPrintStream(psError);
//		consoleLogger.setOutputPrintStream(ps);
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);

		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);

		p.executeTarget("test");

	}

}
