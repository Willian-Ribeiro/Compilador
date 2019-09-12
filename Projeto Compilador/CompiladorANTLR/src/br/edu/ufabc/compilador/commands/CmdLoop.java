package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.definitions.AppProps;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class CmdLoop extends Command {

    private static int Id = 0;
    String loop_name;

    Variables iterator;
    Variables while_var1, while_var2;
    String comparador;
    String incrementOp;
    Variables increment;

//    public List<Variables> loop_variaveis;
    List<Command> loop_comandos;

    public CmdLoop(){
        loop_name = AppProps.LOOPS_NAME_PREFIX + "_" + newId() + "__";
        loop_comandos = new ArrayList<Command>();
//        loop_variaveis = new ArrayList<Variables>();
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        String command_string = "for( " + iterator.getType() + " " + iterator.getName()
                + "=" + iterator.getValue() + "; ";
        if(while_var1.getName().equals(Variables.NUMBER))
            command_string += while_var1.getValue();
        else
            command_string += while_var1.getName();

        command_string += " " + comparador + " ";

        if(while_var2.getName().equals(Variables.NUMBER))
            command_string += while_var2.getValue();
        else
            command_string += while_var2.getName();

        command_string += "; " + iterator.getName() + " " + incrementOp +"= ";

        if(increment.getName().equals(Variables.NUMBER))
            command_string += increment.getValue();
        else
            command_string += increment.getName();

        command_string += " ) {\n";

        for( Command cmd: loop_comandos) {
            command_string += "\t" + cmd.toJava()+"\n";
        }

        command_string += "}\n";

        return command_string;
    }

    public static int newId()
    {
        Id++;
        return Id--;
    }

    public void addCommand(Command c){
        this.loop_comandos.add(c);
    }

    public Variables getIterator() {
        return iterator;
    }

    public void setIterator(Variables var) {
        this.iterator = var;
    }

    public Variables getWhile_var1() {
        return while_var1;
    }

    public void setWhile_var1(Variables while_var1) {
        this.while_var1 = while_var1;
    }

    public Variables getWhile_var2() {
        return while_var2;
    }

    public void setWhile_var2(Variables while_var2) {
        this.while_var2 = while_var2;
    }

    public Variables getIncrement() {
        return increment;
    }

    public void setIncrement(Variables increment) {
        this.increment = increment;
    }

    public String getComparador() {
        return comparador;
    }

    public void setComparador(String comparador) {
        this.comparador = comparador;
    }

    public String getIncrementOp() {
        return incrementOp;
    }

    public void setIncrementOp(String incrementOp) {
        this.incrementOp = incrementOp;
    }

    public String getLoop_name() {
        return loop_name;
    }
}
