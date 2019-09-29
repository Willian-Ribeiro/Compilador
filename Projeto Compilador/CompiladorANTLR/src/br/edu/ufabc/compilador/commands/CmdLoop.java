package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class CmdLoop extends Command {

    Variables iterator;
    Variables while_var1, while_var2;
    String comparador;
    String incrementOp;
    Variables increment;

    List<Variables> loop_variaveis;
    List<Command> loop_comandos;

    public CmdLoop(){
        loop_variaveis = new ArrayList<Variables>();
        loop_comandos = new ArrayList<Command>();
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

        for( Variables var: loop_variaveis) {
            command_string += "\t\t\t" + var.getType() + " " + var.getName() + ";\n";
        }

        for( Command cmd: loop_comandos) {
            command_string += "\t\t\t" + cmd.toJava()+"\n";
        }

        command_string += "\t\t}\n";

        return command_string;
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

    public void setWhile_var1(Variables while_var1) {
        this.while_var1 = while_var1;
    }

    public void setWhile_var2(Variables while_var2) {
        this.while_var2 = while_var2;
    }

    public void setIncrement(Variables increment) {
        this.increment = increment;
    }

    public void setComparador(String comparador) {
        this.comparador = comparador;
    }

    public void setIncrementOp(String incrementOp) {
        this.incrementOp = incrementOp;
    }

    public void addLoopVariaveis(Variables var)
    {
        this.loop_variaveis.add(var);
    }

    public Variables loopGetVar(String varName)
    {
        if(varName.equals(iterator.getName()))
            return iterator;
        for (Variables var : loop_variaveis)
        {
            if(var.getName().equals(varName))
                return var;
        }
        return null;
    }

    public boolean checkVarsAttributedUsed()
    {
        for (Variables var : loop_variaveis)
        {
            System.out.println("Var name: "+var.getName()+" used: "+var.getUsed()+" attributed: "+var.getAttributed()+"\n");
            if(!var.getAttributed())
                throw new RuntimeException("ERROR Variable " + var.getName() + " not attributed");
            if(!var.getUsed())
                throw new RuntimeException("WARNING? Variable " + var.getName() + " not being used");
        }

        return true;
    }
}
