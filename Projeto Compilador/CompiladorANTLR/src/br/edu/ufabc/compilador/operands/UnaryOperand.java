package br.edu.ufabc.compilador.operands;

import br.edu.ufabc.compilador.definitions.DataTypes;
import br.edu.ufabc.compilador.definitions.Variables;

public class UnaryOperand extends AbstractOperand{
    private Variables var;
    private String operand;

    public UnaryOperand(){
        this.operand = "+";
    }

    @Override
    public String toJava()
    {
        return (var.getName().equals(Variables.NUMBER))? var.getValue() : var.getName();
    }

    @Override
    public String toArduino()
    {
        return null;
    }

    @Override
    public DataTypes getDataType()
    {
        return var.getDataType();
    }

    public Variables getVar() {
        return var;
    }

    // already make var account sign
    public void setVar(Variables var) {
        if(operand.equals("-"))
            var.setValue("-" + var.getValue());
        this.var = var;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }
}
