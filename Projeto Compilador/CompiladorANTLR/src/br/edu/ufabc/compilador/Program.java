package br.edu.ufabc.compilador;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.io.FileWriter;
import java.io.File;

public class Program {

    public static final String INPUT="__SCAN_CU_MACRO__";
    private String name;

    List<Variables>  variaveis;
    List<Command> comandos;

    public Program(String name){
        this.name = name;
        comandos = new ArrayList<Command>();
        variaveis = new ArrayList<Variables>();
    }

    public void setVariaveis(Collection lista){
        Iterator it = lista.iterator();
        while (it.hasNext()){
            variaveis.add((Variables) it.next());
        }

    }
    public void addCommand(Command c){
        comandos.add(c);
    }

    public void saveToFile(){
        try{
//            File file = new File(name+"."+AppProps.LANGUAGE.toLowerCase());
            File file = new File("temp");
            FileWriter f = new FileWriter(file);

            f.write("public class "+name+"{\n");
            f.write("    public static void main(String args[]){\n");
            f.write("      java.util.Scanner "+INPUT+" = new java.util.Scanner(System.in);\n");

            for(Variables s: variaveis){
                f.write(" int "+s.getName()+";\n");
            }

            for(Command c: comandos){
                f.write(c.toJava()+"\n");
            }

            f.write("}\n");
            f.write("}\n");
            f.close();

            Identator.Ident(file);
        }
        catch(Exception ex){
            System.out.println("ERRO:"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
