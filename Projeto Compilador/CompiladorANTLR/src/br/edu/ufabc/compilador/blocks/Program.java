package br.edu.ufabc.compilador.blocks;

import br.edu.ufabc.compilador.definitions.AppProps;
import br.edu.ufabc.compilador.extra.Identator;
import br.edu.ufabc.compilador.definitions.Variables;
import br.edu.ufabc.compilador.commands.Command;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.io.FileWriter;
import java.io.File;

public class Program {
    private String name;

    List<Variables> variaveis;
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

    public Command popCommand() {return comandos.remove(comandos.size()-1);}

    public Command getLastCommand() {return comandos.get(comandos.size()-1);}

    public void saveToFile(){
        try{
//            File file = new File(name+"."+AppProps.LANGUAGE.toLowerCase());
            File file = new File("temp");
            FileWriter f = new FileWriter(file);

            f.write("public class "+name+"{\n");
            f.write("\tpublic static void main(String args[]){\n");
            f.write("\t\tjava.util.Scanner "+ AppProps.JAVA_SCANNER_INPUT +" = new java.util.Scanner(System.in);\n");

            for(Variables s: variaveis){
                f.write("\t\t" + s.getType() + " " + s.getName()+";\n\n");
            }

            for(Command c: comandos){
                f.write("\t\t"+c.toJava()+"\n");
            }

            f.write("\t}\n");
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
