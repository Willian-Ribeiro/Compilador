package br.edu.ufabc.compilador.operands;

import br.edu.ufabc.compilador.blocks.Expression;
import br.edu.ufabc.compilador.definitions.DataTypes;
import br.edu.ufabc.compilador.definitions.Variables;

public class UnaryOperand extends AbstractOperand{
    private Variables var;
    private String operand;

    private Expression insideParenthesis;
    private Expression saveExpression;
    private AbstractOperand saveAbstractOperand;
    private BinaryOperand saveBinaryOperand;
    private UnaryOperand saveUnaryOperand;
    private UnaryOperand lastFator;

    public UnaryOperand(){
        this.operand = "+";
    }

    @Override
    public String toJava()
    {
        if(var != null)
            return (var.getName().equals(Variables.NUMBER))? getValue() : var.getName();
        else if(insideParenthesis != null)
            return operand + "(" + insideParenthesis.toJava() + ")";
        else
            return "variable not initialized";
    }

    @Override
    public String toArduino()
    {
        return null;
    }

    @Override
    public DataTypes getDataType()
    {
        if(var != null)
            return var.getDataType();
        else
            return insideParenthesis.getDataType();
    }

    public Variables getVar() {
        return var;
    }

    public String getValue()
    {
        if(operand.equals("-"))
            return "-" + var.getValue();
        return var.getValue();
    }

    // already make var account sign
    public void setVar(Variables var) {
        this.var = var;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public void saveState(Expression expression, AbstractOperand absOp, BinaryOperand binOp, UnaryOperand unOp, UnaryOperand lastFator) {
        this.saveExpression = expression;
        this.saveAbstractOperand = absOp;
        this.saveBinaryOperand = binOp;
        this.saveUnaryOperand = unOp;
        this.lastFator = lastFator;
    }

    public void setInsideParenthesis(Expression insideParenthesis)
    {
        this.insideParenthesis = insideParenthesis;
    }

    public Expression getSavedExpression() {
        return saveExpression;
    }

    public AbstractOperand getSavedAbstractOperand() {
        return saveAbstractOperand;
    }

    public BinaryOperand getSavedBinaryOperand() {
        return saveBinaryOperand;
    }

    public UnaryOperand getSavedUnaryOperand() {
        return saveUnaryOperand;
    }

    public UnaryOperand getLastFator() {
        return lastFator;
    }
}
