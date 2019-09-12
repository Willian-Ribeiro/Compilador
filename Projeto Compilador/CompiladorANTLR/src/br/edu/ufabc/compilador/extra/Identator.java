package br.edu.ufabc.compilador.extra;

import br.edu.ufabc.compilador.definitions.AppProps;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Identator {

    private static int counter = 0;

    public static void Ident(File file) throws IOException {

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String readLine = "";

            try{
                FileWriter writer = new FileWriter(AppProps.FILENAME.substring(0, AppProps.FILENAME.length()-2)
                        + AppProps.LANGUAGE.toLowerCase());

                BufferedWriter bw = new BufferedWriter(writer);

                while ((readLine = b.readLine()) != null) {
                    printaIdentado(readLine.trim(), bw);
                }

                bw.close();

            }

            catch(IOException e){
                System.err.format("IOException: %s%n", e);
            }

            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void contaChaveAbre(String s){
        counter += s.contains("{") ? 1 : 0;
    }

    private static void contaChaveFecha(String s){
        counter -= s.contains("}") ? 1 : 0;
    }

    private static void printaIdentado(String s, BufferedWriter bw) throws IOException{

        String[] parts = s.split(";");

        for(int i = 0; i < parts.length - 1; i++){
            contaChaveFecha(parts[i]);
            for(int j = 1; j <= counter; j++){
                bw.write("\t");
            }
            contaChaveAbre(parts[i]);
            bw.write(parts[i].trim() + ";\n");
        }

        if(parts.length > 0){

            String last = parts[parts.length - 1];

            if(s.length() > 0){

                boolean addSemicolon = s.substring(s.length() - 1).equals(";") ? true : false;

                contaChaveFecha(last);
                for(int j = 1; j <= counter; j++){
                    bw.write("\t");
                }
                contaChaveAbre(last);
                bw.write(last.trim());
                if(addSemicolon) bw.write(";");
                bw.write("\n");
            }
        }
    }
}
