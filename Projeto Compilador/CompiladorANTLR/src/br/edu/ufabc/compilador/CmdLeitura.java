package br.edu.ufabc.compilador;

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
        return idVar + "=" + Program.INPUT + ".nextInt();";
    }
}
