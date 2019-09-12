package br.edu.ufabc.compilador.definitions;

public class AppProps {
    // property name
    private static final String filename = "filename";
    private static final String language = "language";

    // get property from environment
    public static final String FILENAME = System.getProperty(filename, "unnamedFile");
    public static String LANGUAGE = System.getProperty(language, "JAVA").toUpperCase();

    // hard coded props
    public static final String JAVA_SCANNER_INPUT = "__SCAN_CU_MACRO__";
    public static final String LOOPS_NAME_PREFIX = "__LOOPS_CU_MACRO__";
}
