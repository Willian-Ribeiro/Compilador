package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.blocks.Expression;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class CmdIfElse extends Command {

    Variables condicionail1;
    Variables condicionail2;
    String comparador;

    List<Expression> expressions;
    List<String> comparators;

    List<Variables> if_variaveis;
    List<Command> if_comandos;

    List<Variables> else_variaveis;
    List<Command> else_comandos;

    public CmdIfElse(){
        if_variaveis = new ArrayList<Variables>();
        if_comandos = new ArrayList<Command>();

        expressions = new ArrayList<Expression>();
        comparators = new ArrayList<String>();

        else_variaveis = new ArrayList<Variables>();
        else_comandos = new ArrayList<Command>();
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        String command_string = "if( ";

        if(condicionail1.getName().equals(Variables.NUMBER))
            command_string += condicionail1.getValue();
        else
            command_string += condicionail1.getName();

        command_string += comparador;

        if(condicionail2.getName().equals(Variables.NUMBER))
            command_string += condicionail2.getValue();
        else
            command_string += condicionail2.getName();

        command_string += " ) {\n";

        for( Variables var: if_variaveis) {
            command_string += "\t\t\t" + var.getType() + " " + var.getName() + ";\n";
        }

        for( Command cmd: if_comandos) {
            command_string += "\t\t\t" + cmd.toJava()+"\n";
        }

        command_string += "\t\t}\n";

        if(else_variaveis.size() > 0 || else_comandos.size() > 0)
        {
            command_string += "\t\telse {\n";

            for( Variables var: else_variaveis) {
                command_string += "\t\t\t" + var.getType() + " " + var.getName() + ";\n";
            }

            for( Command cmd: else_comandos) {
                command_string += "\t\t\t" + cmd.toJava()+"\n";
            }

            command_string += "\t\t}\n";
        }

        return command_string;
    }

    public void addCommandIf(Command c){
        this.if_comandos.add(c);
    }

    public void addCommandElse(Command c){
        this.else_comandos.add(c);
    }

    public void setCondicionail1(Variables condicionail1) {
        this.condicionail1 = condicionail1;
    }

    public void setCondicionail2(Variables condicionail2) {
        this.condicionail2 = condicionail2;
    }

    public void setComparador(String comparador) {
        this.comparador = comparador;
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
        for (Variables var : if_variaveis)
        {
            System.out.println("Var name: "+var.getName()+" used: "+var.getUsed()+" attributed: "+var.getAttributed()+"\n");
            if(!var.getAttributed())
                throw new RuntimeException("ERROR Variable " + var.getName() + " not attributed");
            if(!var.getUsed())
                throw new RuntimeException("WARNING? Variable " + var.getName() + " not being used");
        }

        for (Variables var : else_variaveis)
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
