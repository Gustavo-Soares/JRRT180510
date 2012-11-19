package tests.jdolly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import saferefactor.core.util.Constants;



import AST.Program;
import AST.TypeDecl;


/**
 * Logs refactoring test results to the console and a directory structure.
 * 
 * The directory structure has the following format:
 * 
 * <pre>
 * {logDir}N/{SUMMARY_FILE_NAME}
 *           {TEST_NAME_BASE}M/
 *                            in/
 *                              {compilationUnit}.java
 *                            out/
 *                               {compilationUnit}.java
 *                               {SUCCESSFUL_REFACTORING_LOG_FILE} 
 *                               {PRE_REFACTOR_NOT_COMPILE_LOG_FILE}
 *                               {REFACTORING_INAPPLICABLE_LOG_FILE}
 *                               {REFACTORING_EXECUTION_ERROR_LOG_FILE}
 *                               {POST_REFACTOR_NOT_COMPILE_LOG_FILE}
 *                               {POSTCONDITION_FAILURE_LOG_FILE}
 * </pre>
 * 
 * Where {logDir} is the directory passed into the constructor (usually from
 * {@link RefactoringTest#getLogDirPath()}), N and M are unique numbers to
 * ensure that multiple runs do not overwrite each other, and other curly-braced
 * values are those defined in the class.
 */
public class TestLogger {
	private static final String TEST_NAME_BASE = "test";
	private static final String PRE_REFACTOR_NOT_COMPILE_LOG_FILE = "PRE_REFACTOR_NOT_COMPILE";
	private static final String REFACTORING_INAPPLICABLE_LOG_FILE = "REFACTORING_INAPPLICABLE";
	private static final String REFACTORING_EXECUTION_ERROR_LOG_FILE = "REFACTORING_EXECUTION_ERROR";
	private static final String POST_REFACTOR_NOT_COMPILE_LOG_FILE = "POST_REFACTOR_NOT_COMPILE";
	private static final String POSTCONDITION_FAILURE_LOG_FILE = "POSTCONDITION_FAILURE";
	// gustavo
	private static final String BEHAVIORCHANGE_LOG_FILE = "BEHAVIORCHANGE_FAILURE";
	private static final String SUCCESSFUL_REFACTORING_LOG_FILE = "SUCCESSFUL_REFACTORING";
	private static final String SUMMARY_FILE_NAME = "SUMMARY";

	private int generatedCount = 0;
	private int preRefactorNotCompileCount = 0;
	private int refactoringInapplicableCount = 0;
	private int refactoringExecutionErrorCount = 0;
	private int postRefactoringNotCompileCount = 0;
	private int postconditionFailureCount = 0;
	private int successfulRefactorings = 0;
	private String currentSource;
	private String currentTarget;
	// gustavo
	private int behaviorChangeCount = 0;
	private long start;
	private long stop;
	Map<String, Integer> problems = new HashMap<String, Integer>();
	private List<String> refactoredTests = new ArrayList<String>();
	private List<String> underCoveragePrograms = new ArrayList<String>();
	private File logDir;
	public File getLogDir() {
		return logDir;
	}

	public void setLogDir(File logDir) {
		this.logDir = logDir;
	}

	private String refactoringName;
	// private String generatorName;
	
	private double currentCobertura;
	private double menorCobertura = 0; 
	private double maiorCobertura = 0; 
	private List<Double> coberturas = new ArrayList<Double>();
	
	
	private List<String> programsBC = new ArrayList<String>();

	




	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private List<String> programsCE = new ArrayList<String>();;

	public TestLogger(String refactoringName) {
		this.refactoringName = refactoringName;
		// this.generatorName = generatorName;
	}

	public TestLogger(String refactoringName, String logDirPath) {
		this(refactoringName);
		this.logDir = createUniqueLogDir(logDirPath, 0);
	}

	private File createUniqueLogDir(String logDirPathBase, int suffix) {
		String logDirPath = logDirPathBase + "/" + this.refactoringName
				+ suffix;
		File uniqueLogDir = new File(logDirPath);
		if (uniqueLogDir.exists()) {
			return createUniqueLogDir(logDirPathBase, suffix + 1);
		}
		info("Creating log directory " + uniqueLogDir.getAbsolutePath());
		if (!uniqueLogDir.mkdirs()) {
			throw new RuntimeException(String.format(
					"Log directory %s could not be created",
					uniqueLogDir.getAbsolutePath()));
		}
		return uniqueLogDir;
	}

	public boolean fileOutputIsEnabled() {
		return logDir != null;
	}

	public int getGeneratedCount() {
		return generatedCount;
	}

	public void start() {
		// info("Starting " + test.getClass().getName().toString());
		// info("Description: " + test.getDescription());
		start = System.currentTimeMillis();
	}

	public void logGenerated(CompilationUnit cu, String cuName) {
		generatedCount++;
		info("==================================");
		String cuSource = cu.toString();
		info(String.format("Input %d before refactoring:\n%s", generatedCount,
				cuSource));
		outputCompilationUnitFile("in", cuSource, cuName);
	}

	public String logGenerated(List<CompilationUnit> icus) {
		generatedCount++;
		info("==================================");
		String cuSources = "";

		String outputCompilationUnitFile = "";
		for (CompilationUnit cu : icus) {
			String cuName = getPrimaryTypeName(cu);
			String cuSource = cu.toString();
			String packageName = cu.getPackage().getName()
					.getFullyQualifiedName();
			outputCompilationUnitFile = outputCompilationUnitFile("in/"
					+ packageName, cuSource, cuName);
			cuSources = cuSources + cuSource;
		}
		info(String.format("Input %d before refactoring:\n%s", generatedCount,
				cuSources));
		currentSource = cuSources;

		return outputCompilationUnitFile;

	}

	protected String getPrimaryTypeName(CompilationUnit cu) {
		String first = null;
		for (Object type : cu.types()) {
			if (type instanceof TypeDeclaration) {
				String typeName = ((TypeDeclaration) type).getName().toString();
				if (first == null) {
					first = typeName;
				}
				for (Object modifier : ((TypeDeclaration) type).modifiers()) {
					if (((Modifier) modifier).getKeyword() == Modifier.ModifierKeyword.PUBLIC_KEYWORD) {
						return typeName;
					}
				}
			}
		}
		return first;
	}

	private static void eclipseOracle(Map<String, Integer> problems, File test,
			File target) {
		try {
			FileReader in = new FileReader(target);
			BufferedReader br = new BufferedReader(in);
			String s;

			// pega s� o 1o. erro
			// s = br.readLine();
			// s = s.replaceAll(
			// " [a-zA-Z0-9]+_[0-9][(]?[\\w]*[)]?", " ");
			// System.out.println("----------------------------------------------");
			// System.out.println(test);
			// System.out.println("----------------------------------------------");
			// pega todos os errors
			String x = "";
			while ((s = br.readLine()) != null) {
				s = s.replaceAll(" [a-zA-Z0-9]+_[0-9][(]?[\\w]*[)]?", " ");
				x = x + "\n" + s;
			}
			// System.out.println(x);
			if (problems.containsKey(x)) {
				Integer integer = problems.get(x);
				integer = integer + 1;
				problems.put(x, integer);
			} else {
				// System.out.println(test);

				problems.put(x, 1);
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logSuccessfulRefactoring() {
		outputToLogFile(SUCCESSFUL_REFACTORING_LOG_FILE, null);
		successfulRefactorings++;
	}

	public void stop() {
		stop = System.currentTimeMillis();
		outputSummary();
	}

	private static class Result {
		public String name;
		public String value;

		public Result(String name, Object value) {
			this.name = name;
			this.value = value.toString();
		}
	}

	private void outputSummary() {
		LinkedList<Result> results = new LinkedList<Result>();
		results.add(new Result("Refactoring", refactoringName));

		results.add(new Result("Total Generated", generatedCount));
		results.add(new Result("Time", (stop - start) / 1000F));
		results.add(new Result("Compilable Inputs", generatedCount
				- preRefactorNotCompileCount));
		// results.add(new Result("Pre-Refactoring Compilation Failures",
		// preRefactorNotCompileCount));
		results.add(new Result("WarningStatus (Ecl)",
				refactoringInapplicableCount));
		results.add(new Result("WarningStatus (NB)", "\\Fix{NBWS}"));
		// results.add(new Result("Successful Refactorings",
		// successfulRefactorings));
		// results.add(new Result("Refactoring Execution Errors",
		// refactoringExecutionErrorCount));
		results.add(new Result("DoesNotCompile (Failures)",
				postRefactoringNotCompileCount));
		results.add(new Result("DoesNotCompile (Bugs)", problems.size()));

		results.add(new Result("Differential", "\\Fix{Diff}"));
		results.add(new Result("BehavioralChange (Ecl)", behaviorChangeCount));
		results.add(new Result("Bugs Reported (Ecl)", "\\Fix{Ebugs}"));
		results.add(new Result("Compilation error bugs", problems.toString()));

		double somaCoberturas = 0;
		for (Double cobertura : getCoberturas()) {
			somaCoberturas = somaCoberturas + cobertura;
		}
		double porcentagemCobertura = somaCoberturas / getCoberturas().size();		
		results.add(new Result("Menor Cobertura", getMenorCobertura()));
		results.add(new Result("Maior Cobertura", getMaiorCobertura()));
		results.add(new Result("Test coverage", porcentagemCobertura));
		outputConsoleSummary(results);
		outputSummaryLogFile(results);
	}

	private void outputConsoleSummary(LinkedList<Result> results) {
		for (Result result : results) {
			System.out.print(result.name);
			System.out.print(": ");
			System.out.println(result.value);
		}
	}

	public void info(String info) {
		System.out.println("INFO: " + info);
	}

	public void warn(String warning) {
		System.out.println("WARNING: " + warning);
	}

	public void fail(String failure) {
		System.out.println("FAILURE: " + failure);
	}

	protected void outputToLogFile(String logFileName, Iterable<String> messages) {
		if (!fileOutputIsEnabled()) {
			return;
		}
		File logFile = new File(getInOutDir("out/eclipse"), logFileName);
		try {
			if (!logFile.createNewFile()) {
				throw new RuntimeException(String.format(
						"Log file %s could not be created",
						logFile.getAbsolutePath()));
			}
			FileWriter fw = new FileWriter(logFile);
			if (messages != null) {
				for (String message : messages) {
					fw.write(message);
					fw.write('\n');
				}
			}
			fw.flush();
			fw.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String outputCompilationUnitFile(String inOutDirName,
			String source, String cuName) {
		if (!fileOutputIsEnabled()) {
			return null;
		}

		File inOutDir = getInOutDir(inOutDirName);
		if (!cuName.endsWith(".java")) {
			cuName = cuName + ".java";
		}
		File cuFile = new File(inOutDir, cuName); // ../<compilation_unit_name>.java
		if (cuFile.exists())
			cuFile.delete();
		try {
			if (!cuFile.createNewFile()) {
				throw new RuntimeException(String.format(
						"Compilation unit file %s could not be created",
						cuFile.getAbsolutePath()));
			}
			FileWriter fw = new FileWriter(cuFile);
			fw.write(source);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // HACK
		}
		return inOutDir.toString();
	}

	protected File getInOutDir(String inOutDirName) {
		if (!fileOutputIsEnabled()) {
			return null;
		}
		File inOutDir = new File(getTestDirectory(), inOutDirName); // <temp_dir>/<refactoring>/<test_dir>/(in|out)
		if (!inOutDir.exists()) {
			if (!inOutDir.mkdirs()) {
				throw new RuntimeException(String.format(
						"In/Out directory %s could not be created",
						inOutDir.getAbsolutePath()));
			}
		}
		return inOutDir;
	}

	public File getTestDirectory() {
		if (!fileOutputIsEnabled()) {
			return null;
		}
		File curTestDir = new File(logDir, TEST_NAME_BASE + generatedCount);
		if (!curTestDir.exists()) {
			if (!curTestDir.mkdirs()) {
				throw new RuntimeException(String.format(
						"Test directory %s could not be created",
						curTestDir.getAbsolutePath()));
			}
			info("Test directory created at " + curTestDir.getAbsolutePath());
		}
		return curTestDir;
	}

	private void outputSummaryLogFile(Iterable<Result> results) {
		if (!fileOutputIsEnabled()) {
			return;
		}
		File summaryFile = new File(logDir, SUMMARY_FILE_NAME);
		try {
			if (!summaryFile.createNewFile()) {
				throw new RuntimeException("Unable to create summary file");
			}
			FileWriter fw = new FileWriter(summaryFile);
			writeHeaders(results, fw);
			writeValues(results, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException(e); // HACK
		}
	}

	private void writeHeaders(Iterable<Result> results, FileWriter fw)
			throws IOException {
		fw.write("%%");
		boolean first = true;
		for (Result result : results) {
			if (!first) {
				fw.write(" & ");
			}
			fw.write("\\RHD{"); // the rotated header command in the TeX file
			fw.write(result.name);
			fw.write("}");
			first = false;
		}
		fw.write('\n');
	}

	private void writeValues(Iterable<Result> results, FileWriter fw)
			throws IOException {
		Boolean first2 = true;
		for (Result result : results) {
			if (!first2) {
				fw.write(" & ");
			}
			fw.write(result.value);
			first2 = false;
		}
		fw.write("\\\\\n");
	}

	public void logCoverage() {

		dbf.setNamespaceAware(false);

		DocumentBuilder docBuilder;
		try {
			docBuilder = dbf.newDocumentBuilder();
			Document docSource = docBuilder.parse(new File(Constants.SAFEREFACTOR_DIR
					+ "/coverage/coverage.xml"));

			double cl = Integer.parseInt(docSource.getDocumentElement().getAttribute("lines-covered"));
//			System.out.println(coveredLines);
			double vl = Integer.parseInt(docSource.getDocumentElement().getAttribute("lines-valid"));

			double pc = cl / vl;
			currentCobertura = pc;
			if (currentCobertura < 1) { 
				logUnderCoverage();
			}
			if (getMenorCobertura() == 0)
				setMenorCobertura(pc);
			if (getMenorCobertura() > pc)
				setMenorCobertura(pc);
			if (getMaiorCobertura() == 0)
				setMaiorCobertura(pc);
			if (getMaiorCobertura() < pc)
				setMaiorCobertura(pc);
			getCoberturas().add(pc);
			//			System.out.println(validLines);
			
			// System.out.println(validLines);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public void logPreRefactoring(Program source) {
		info(String.format("Input %d after refactoring", generatedCount));
		AST.List<AST.CompilationUnit> compilationUnits = source
				.getCompilationUnits();
		String cuSources = "";
		for (AST.CompilationUnit compilationUnit : compilationUnits) {
			if (compilationUnit.fromSource()) {
				String packageDecl = compilationUnit.getPackageDecl();

				// File pacote = new File(path + packageDecl);
				// pacote.mkdir();

				TypeDecl fileName = compilationUnit.getTypeDecl(0);
				String javaContent = compilationUnit.toString();
				cuSources = cuSources + "\n" + javaContent;
				// String filePath = path + packageDecl + FILE_SEPARATOR
				// + fileName.getID() + ".java";
				//
				// System.out.println(filePath);
				// System.out.println(javaContent);
				info(fileName.getID() + "\n" + javaContent);
				// FileUtil.gravaArquivo(filePath, javaContent);
				outputCompilationUnitFile("in/completo/" + packageDecl,
						javaContent, fileName.getID());
			}
			
		}
		currentSource = cuSources;		
	}

	public void logPostRefactoring2(Program target) {
		info(String.format("Input %d after refactoring", generatedCount));
		AST.List<AST.CompilationUnit> compilationUnits = target
				.getCompilationUnits();
		String cuSources = "";
		for (AST.CompilationUnit compilationUnit : compilationUnits) {
			if (compilationUnit.fromSource()) {
				String packageDecl = compilationUnit.getPackageDecl();

				// File pacote = new File(path + packageDecl);
				// pacote.mkdir();

				TypeDecl fileName = compilationUnit.getTypeDecl(0);
				String javaContent = compilationUnit.toString();
				cuSources = cuSources + "\n" + javaContent;
				// String filePath = path + packageDecl + FILE_SEPARATOR
				// + fileName.getID() + ".java";
				//
				// System.out.println(filePath);
				// System.out.println(javaContent);
				info(fileName.getID() + "\n" + javaContent);
				// FileUtil.gravaArquivo(filePath, javaContent);
				outputCompilationUnitFile("out/jrrt/completo/" + packageDecl,
						javaContent, fileName.getID());
			}

		}
		currentTarget = cuSources;
	}

	public void logPostRefactoring(Program target) {
		info(String.format("Input %d after refactoring", generatedCount));
		AST.List<AST.CompilationUnit> compilationUnits = target
				.getCompilationUnits();
		String cuSources = "";
		for (AST.CompilationUnit compilationUnit : compilationUnits) {
			if (compilationUnit.fromSource()) {
				String packageDecl = compilationUnit.getPackageDecl();

				// File pacote = new File(path + packageDecl);
				// pacote.mkdir();

				TypeDecl fileName = compilationUnit.getTypeDecl(0);
				String javaContent = compilationUnit.toString();
				cuSources = cuSources + "\n" + javaContent;

				// String filePath = path + packageDecl + FILE_SEPARATOR
				// + fileName.getID() + ".java";
				//
				// System.out.println(filePath);
				// System.out.println(javaContent);
				info(fileName.getID() + "\n" + javaContent);
				// FileUtil.gravaArquivo(filePath, javaContent);
				outputCompilationUnitFile("out/jrrt/" + packageDecl,
						javaContent, fileName.getID());
			}
		}
		currentTarget = cuSources;
	}

	public void logPostRefactoring(IPackageFragmentRoot packageRoot)
			throws JavaModelException {
		info(String.format("Input %d after refactoring", generatedCount));
		IPackageFragment packFrag;
		ICompilationUnit icu;
		for (IJavaElement element : packageRoot.getChildren()) {
			if (element instanceof IPackageFragment) {
				packFrag = (IPackageFragment) element;
				String packFragName = packFrag.getElementName();
				for (IJavaElement element2 : packFrag.getChildren()) {
					if (element2 instanceof ICompilationUnit) {
						icu = (ICompilationUnit) element2;
						String cuSource = icu.getSource();
						String cuName = icu.getElementName();

						info(cuName + "\n" + cuSource);
						outputCompilationUnitFile(
								"out/eclipse/" + packFragName, cuSource, cuName);
					}
				}
			}

		}
	}

	private void salvaTarget(Program target, String path) {

	}

	public void logBehavioralChange() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<source>\n");
		sb.append(currentSource);
		sb.append("\n<source>\n");
		sb.append("\n<target>\n");
		sb.append(currentTarget);
		sb.append("\n<target>\n");
		programsBC.add(sb.toString());
	}

	public void logProgramsBC() {
		StringBuffer sb = new StringBuffer();

		for (String transformation : programsBC) {
			sb.append("<transformation>");
			sb.append(transformation);
			sb.append("<transformation>");
		}
		FileUtil.gravaArquivo(logDir + "/programsBC", sb.toString());

	}
	
	public void logProgramsCE() {
		StringBuffer sb = new StringBuffer();

		for (String transformation : programsCE) {
			sb.append("<transformation>");
			sb.append(transformation);
			sb.append("<transformation>");
		}
		FileUtil.gravaArquivo(logDir + "/programsCE", sb.toString());

	}
	
	public void logRefactoredTests() {
		StringBuffer sb = new StringBuffer();

		for (String transformation : refactoredTests) {
			sb.append("<transformation>");
			sb.append(transformation);
			sb.append("<transformation>");
		}
		FileUtil.gravaArquivo(logDir + "/refactoredTests", sb.toString());

	}
	
	public void logUnderCoveragePrograms() {
		StringBuffer sb = new StringBuffer();

		for (String transformation : underCoveragePrograms) {
			sb.append("<transformation>");
			sb.append(transformation);
			sb.append("<transformation>");
		}
		FileUtil.gravaArquivo(logDir + "/underCoveragePrograms", sb.toString());

	}
	
	public void logCEs(Map<String, Integer> problems2) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Integer>> iterator = problems2.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> pair = iterator.next();
			String problem = pair.getKey();
			int qnt = pair.getValue();
			sb.append(problem);
			sb.append(" : ");
			sb.append(qnt);
			sb.append("\n");
		}
		FileUtil.gravaArquivo(logDir + "/CEs", sb.toString());
		
	}

	public void logProgramCE(String ce) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<compilation error>\n");
		sb.append(ce);
		sb.append("\n<compilation error>\n");
		sb.append("\n<source>\n");
		sb.append(currentSource);
		sb.append("\n<source>\n");
		sb.append("\n<target>\n");
		sb.append(currentTarget);
		sb.append("\n<target>\n");
		programsCE.add(sb.toString());
		
	}

	public List<Double> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<Double> coberturas) {
		this.coberturas = coberturas;
	}

	public double getMenorCobertura() {
		return menorCobertura;
	}

	public void setMenorCobertura(double menorCobertura) {
		this.menorCobertura = menorCobertura;
	}

	public double getMaiorCobertura() {
		return maiorCobertura;
	}

	public void setMaiorCobertura(double maiorCobertura) {
		this.maiorCobertura = maiorCobertura;
	}

	public void checkTestRefactoring() {
		File in = new File(getTestDirectory(), "in/completo/"); 
		File out = new File(getTestDirectory(), "out/jrrt/completo/"); 
		if (!in.exists() || !out.exists()) {
			throw new RuntimeException("In/Out directory not found.");
		}
		File[] inTests = in.listFiles(javaFileFilter());
		File[] outTests = out.listFiles(javaFileFilter());
		boolean hasChange = false;		
		for (File inTest : inTests) {
			if (hasChange)	break;
			for (File outTest : outTests) {
				if (outTest.getName().equals(inTest.getName())) {
					hasChange = hasChange(inTest, outTest);
					if (hasChange)
						logRefactoredTest();
					break;
				}
					
			}
		}
		
		
	}
	private void logUnderCoverage() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<testdir>\n");
		sb.append(getTestDirectory().getAbsolutePath());
		sb.append("\n<testdir>\n");
		sb.append("\n<Coverage>\n");
		sb.append(currentCobertura);
		sb.append("\n<Coverage>\n");
		sb.append("\n<source>\n");
		sb.append(currentSource);
		sb.append("\n<source>\n");
		sb.append("\n<target>\n");
		sb.append(currentTarget);
		sb.append("\n<target>\n");
		getUnderCoveragePrograms().add(sb.toString());
		
	}

	private void logRefactoredTest() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<testdir>\n");
		sb.append(getTestDirectory().getAbsolutePath());
		sb.append("\n<testdir>\n");
		sb.append("\n<source>\n");
		sb.append(currentSource);
		sb.append("\n<source>\n");
		sb.append("\n<target>\n");
		sb.append(currentTarget);
		sb.append("\n<target>\n");
		getRefactoredTests().add(sb.toString());		
	}

	private boolean hasChange(File inTest, File outTest) {
		String inContent = FileUtil.leArquivo(inTest.getAbsolutePath());
		String outContent = FileUtil.leArquivo(outTest.getAbsolutePath());
		
		
		boolean hasChange = !inContent.equals(outContent);
//		if (hasChange) {
//			System.out.println("teste different");
//		}
		return hasChange;
		
	}

	private FileFilter javaFileFilter() {
		return new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith(".java"))
					return true;
				return false;
			}
		};
	}

	public List<String> getRefactoredTests() {
		return refactoredTests;
	}

	public void setRefactoredTests(List<String> refactoredTests) {
		this.refactoredTests = refactoredTests;
	}

	public List<String> getUnderCoveragePrograms() {
		return underCoveragePrograms;
	}

	public void setUnderCoveragePrograms(List<String> underCoveragePrograms) {
		this.underCoveragePrograms = underCoveragePrograms;
	}

}