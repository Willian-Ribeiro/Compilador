package br.edu.ufabc.compilador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {

        final String fileName = "prog.cu";
        InputStream inputstream = null;

        try{
            System.out.println( "User directory" + System.getProperty("user.dir"));
            inputstream = new FileInputStream(fileName);
            System.out.println(inputstream.toString());
            MyLexer lexer = new MyLexer(inputstream);
            MyParser parser = new MyParser(lexer);

            if(inputstream == null) System.out.println("Input null");

            System.out.println("Starting compiling process ...");

            parser.prog();

            System.out.println("Compilation finished!");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
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
