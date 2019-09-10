package br.edu.ufabc.compilador;

public class AppProps {
    // property name
    private static final String filename = "filename";
    private static final String language = "language";

    // get property from environment
    public static final String FILENAME = System.getProperty(filename, "unnamedFile");
    public static String LANGUAGE = System.getProperty(language, "JAVA").toUpperCase();
}
