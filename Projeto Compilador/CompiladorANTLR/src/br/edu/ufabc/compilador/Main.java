/*
    Project: Compilador Universal

    Description: compiler capable of generating program in more than one language
                 currently supports: Java, Arduino

    Authors: Willian Ribeiro,
*/

package br.edu.ufabc.compilador;

import br.edu.ufabc.compilador.blocks.ScopeManager;
import br.edu.ufabc.compilador.definitions.AppProps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        InputStream inputstream = null;

        System.out.println( "User directory " + System.getProperty("user.dir"));
        System.out.println("Language selected " + AppProps.LANGUAGE);
        System.out.println("File name " + AppProps.FILENAME);

        try{
            if(AppProps.FILENAME == "unnamedFile" || !AppProps.FILENAME.contains(".cu") )
                throw new Exception("\nInsert your .cu file\n");

            inputstream = new FileInputStream(AppProps.FILENAME);

            MyLexer lexer = new MyLexer(inputstream);
            MyParser parser = new MyParser(lexer);

            parser.setProgram(AppProps.FILENAME.substring(0,AppProps.FILENAME.length() - 3));

            if(inputstream == null)
                System.out.println("Empty file");

            System.out.println("\n\nStarting compiling process ...\n");

            parser.prog();

            // checking variable usage
            ScopeManager.getInstance().checkUsedAttributedVariables();

            System.out.println("\nGenerating Code...");
            parser.getProgram().saveToFile();
            System.out.println("\nProgram successfully compiled to " + AppProps.LANGUAGE);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
