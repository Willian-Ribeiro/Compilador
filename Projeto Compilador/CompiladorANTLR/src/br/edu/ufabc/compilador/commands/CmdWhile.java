package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.blocks.Expression;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class CmdWhile extends Command {

    List<Expression> expressions;
    List<String> comparators;

    // inside for variablles
    List<Variables> while_variaveis;
    List<Command> while_comandos;

    public CmdWhile(){
        expressions = new ArrayList<Expression>();
        comparators = new ArrayList<String>();

        while_variaveis = new ArrayList<Variables>();
        while_comandos = new ArrayList<Command>();
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        String command_string = "while( " + expressions.get(0).toJava();

        for(int i=0; i<comparators.size(); i++ )
        {
            command_string += comparators.get(i) + expressions.get(i+1).toJava();
        }

        command_string += " ) {\n";

        for( Variables var: while_variaveis) {
            command_string += "\t\t\t" + var.getType() + " " + var.getName() + ";\n";
        }

        for( Command cmd: while_comandos) {
            command_string += "\t\t\t" + cmd.toJava()+"\n";
        }

        command_string += "\t\t}\n";

        return command_string;
    }

    public Variables whileGetVar(String varName)
    {
        for (Variables var : while_variaveis)
        {
            if(var.getName().equals(varName))
                return var;
        }
        return null;
    }

    public void addCommand(Command c){
        this.while_comandos.add(c);
    }

    public void addExpression(Expression exp){
        this.expressions.add(exp);
    }

    public Expression getLastExpression(){
        return this.expressions.get(expressions.size()-1);
    }

    public void addComparator(String comp){
        this.comparators.add(comp);
    }

    public boolean checkVarsAttributedUsed()
    {
        for (Variables var : while_variaveis)
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
