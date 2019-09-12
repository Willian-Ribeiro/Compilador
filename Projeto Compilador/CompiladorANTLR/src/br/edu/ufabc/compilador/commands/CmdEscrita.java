package br.edu.ufabc.compilador.commands;

public class CmdEscrita extends Command {

    private String  conteudo;

    public CmdEscrita(String conteudo){
        this.conteudo = conteudo;
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        return "System.out.println("+conteudo+");\n";
    }
}
