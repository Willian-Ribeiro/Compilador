package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.blocks.Expression;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class CmdAtribuicao extends Command {
    public List<Expression> command;
    Variables var;

    public CmdAtribuicao(Variables var){
        this.var = var;
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        return null;
    }

    public List<Expression> getCommand() {
        return command;
    }

    public void setCommand(List<Expression> command) {
        this.command = command;
    }

}
