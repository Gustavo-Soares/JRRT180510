//package jdolly;
//
//import io.InputManager;
//import io.InputManagerASCII;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//public class CECategorizer {
//	
//	private static int counter = 0;
//
//	public static void main(String[] args) {
//		
//		Set<Set<String>> setCE = new HashSet<Set<String>>();
//		for (int i = 1; i < 31; i++ ) {
//			String path = "/Users/gustavo/Dropbox/TSE/results/addparamter/netbeans7.0.1/parte" + i +  "/programsCE";
//			String printProgram = "Duplicate field";
//			File f = new File(path);
//			if (!f.exists()) continue;
//			setCE.addAll(getCEset(path, printProgram));
//		}
//		System.out.println(counter);
////		for (Set<String> set : setCE) {
////			System.out.println("-------------");
////			// System.out.println(set);
////			for (String string : set) {
////				System.out.println(string);
////			}
////		}
//		
//		Set<Set<String>> uniqueCE = getUniqueCE(setCE);
//		for (Set<String> set : uniqueCE) {
//			System.out.println("-------------");
//			// System.out.println(set);
//			for (String string : set) {
//				System.out.println(string);
//			}
//		}
//	}
//	
//	private static Set<Set<String>> getCEset(String path, String printProgram) {
//		
//		boolean print = false; 
//		
//		Set<Set<String>> result = new HashSet<Set<String>>();
//		InputManager im;
//		try {
//
//			im = new InputManagerASCII(path);
//			im.openFile();
//
//			boolean isCompilationError = false;
//			boolean isTarget = false;
//
//			String tmpSource = "";
//			String tmpTarget = "";
//			boolean isNew = true;
//			List<String> listClasses = new ArrayList<String>();
//			String errorLine = "";
//
//			Set<String> errors = null;
//			
//			boolean isError = false;
//			while (!im.isEndOfFile()) {
//				String line = im.readLine();
//				if (line.equals(""))
//					continue;
//
//				if (isCompilationError) {
//					if (line.equals("<compilation error>")) {
//						result.add(errors);
//						// System.out.println("------------");
//						isCompilationError = false;
//					} else {
//						if (line.contains("WARNING")) isError = false;
//						if (line.contains("ERROR")) isError = true;
//						if (!line.contains("----------")) {
//							errorLine = line;
//						} else {
//							if (!errorLine.equals("")
//									&& !errorLine.contains("problem")
//									&& !errorLine.contains("----------")) {
//
//								errorLine = errorLine
//										.replaceAll(
//												"[(]?[(]?[a-zA-Z0-9]+_[0-9][(]?[(]?[\\w]*[)]?[\\w]*[)]?",
//												" ");
////								if (errorLine.contains("cannot be resolved to a type"))
////									counter++;
//								
//								if (isError)
//									errors.add(errorLine);
//								// System.out.println(errorLine);
//
//								errorLine = line;
//							}
//
//						}
//
//					}
//				} else {
//					if (line.equals("<compilation error>")) {
//						errors = new HashSet<String>();
//						// System.out.println("------------");
//						isCompilationError = true;
//					}
//				}
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	private static Set<Set<String>> getUniqueCE(Set<Set<String>> setErrors) {
//		if (hasNoIntersaction(setErrors))
//			return setErrors;
//		else {
//			Set<Set<String>> uniqueCE = new HashSet<Set<String>>();
//			List<Set<String>> listCE = new ArrayList<Set<String>>(setErrors);
//			for (int i = 0; i < listCE.size(); i++) {
//				Set<String> set = listCE.get(i);
//				boolean hasIntersaction = false;
//				for (int j = 0; j < listCE.size(); j++) {
//					if (i != j) {
//						Set<String> comparedSet = listCE.get(j);
//						Set<String> intersection = new HashSet<String>(set);
//						intersection.retainAll(comparedSet);
//						if (intersection.size() > 0) {
//							hasIntersaction = true;							
//							uniqueCE.add(intersection);
//							break;
//						}
//					}
//				}
//				if (hasIntersaction == false)
//					uniqueCE.add(set);
//
//			}
//			return getUniqueCE(uniqueCE);
//		}
//
//	}
//
//	private static boolean hasNoIntersaction(Set<Set<String>> setErrors) {
//
//		List<Set<String>> listCE = new ArrayList<Set<String>>(setErrors);
//
//		for (int i = 0; i < listCE.size(); i++) {
//			Set<String> set = listCE.get(i);
//			boolean hasIntersaction = false;
//			for (int j = 0; j < listCE.size(); j++) {
//				if (i != j) {
//					Set<String> comparedSet = listCE.get(j);
//					Set<String> intersection = new HashSet<String>(set);
//					intersection.retainAll(comparedSet);
//					if (intersection.size() > 0)
//						return false;
//				}
//			}
//
//		}
//		return true;
//	}
//
//}
