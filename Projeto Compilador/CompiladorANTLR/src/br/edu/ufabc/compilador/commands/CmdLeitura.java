package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.blocks.Program;
import br.edu.ufabc.compilador.definitions.AppProps;

public class CmdLeitura extends Command {

    private String idVar;

    public CmdLeitura(String idVar){
        this.idVar = idVar;
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        return idVar + "=" + AppProps.JAVA_SCANNER_INPUT + ".next();";
    }
}
