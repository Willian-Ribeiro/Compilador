package br.edu.ufabc.compilador.commands;

import br.edu.ufabc.compilador.blocks.Expression;
import br.edu.ufabc.compilador.definitions.DataTypes;
import br.edu.ufabc.compilador.definitions.Variables;

public class CmdAtribuicao extends Command {
    public Expression expression;
    Variables var;

    public CmdAtribuicao(Variables var){
        this.var = var;
        var.setAttributed(true);
        expression = new Expression();
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
        this.var.setType(getExpressionDataType());
    }

    public DataTypes getExpressionDataType()
    {
        return expression.getDataType();
    }

    @Override
    public String toArduino() {
        return null;
    }

    @Override
    public String toJava() {
        return var.getName() + " = " + expression.getRoot().toJava() + ";";
    }
}
