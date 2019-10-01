package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.definitions.AppProps;
import br.edu.ufabc.compilador.definitions.DataTypes;
import br.edu.ufabc.compilador.definitions.Variables;

public class CmdLeitura extends Command {

    private Variables var;

    public CmdLeitura(Variables var){
        this.var = var;
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        if(var.getDataType() == DataTypes.TYPE_STRING)
            return var.getName() + "=" + AppProps.JAVA_SCANNER_INPUT + ".next();";
        else
            return var.getName() + "=" + AppProps.JAVA_SCANNER_INPUT + ".nextDouble();";
    }
}
