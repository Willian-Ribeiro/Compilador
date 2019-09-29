package br.edu.ufabc.compilador.operands;

import br.edu.ufabc.compilador.definitions.DataTypes;

public class BinaryOperand  extends AbstractOperand{
    private String op;
    private AbstractOperand left;
    private AbstractOperand right;
    private DataTypes dataType;
    
    public BinaryOperand(String op, AbstractOperand l, AbstractOperand r){
        this.op = op;
        this.left = l;
        this.right = r;
    }
    public BinaryOperand(){
        this.left = null;
        this.right = null;
    }
    public BinaryOperand(String op){
        this.op = op;
    }
    
    public void setLeft(AbstractOperand left) {
        this.left = left;
    }

    public AbstractOperand getRight() {
        return right;
    }

    public void setRight(AbstractOperand right) {
        this.right = right;
    }

    public BinaryOperand getRightmostBinaryOp()
    {
        BinaryOperand binaryOperand = this;
        AbstractOperand rightNode = right;

        while(rightNode.getClass().getSimpleName().equals("BinaryOperand"))
        {
            binaryOperand = (BinaryOperand) rightNode;
            rightNode = binaryOperand.right;
        }
        return binaryOperand;
    }

    @Override
    public String toJava() {
        String result;

        if(right.getClass().getSimpleName().equals("UnaryOperand"))
        {
            UnaryOperand temp = (UnaryOperand) right;
            if(op.equals("/") && temp.getVar().getValue() == "0" )
                throw new RuntimeException("DIVISION BY ZERO!!!");
        }

        result = printLeft();
        result += " " + op + " ";
        result += printRight();

        return result;
    }

    @Override
    public String toArduino()
    {
        return null;
    }

    @Override
    public DataTypes getDataType()
    {
        DataTypes leftDT = left.getDataType();
        DataTypes rightDT = right.getDataType();

        if(leftDT.getPriority() > rightDT.getPriority())
            return leftDT;
        else
            return rightDT;
    }

    public String printLeft()
    {
        AbstractOperand leftOperand;
        String result;

        if(left == null)
            return "";

        if(left.getClass().getSimpleName().equals("UnaryOperand"))
        {
            leftOperand = (UnaryOperand) left;
            result = leftOperand.toJava();
        }
        else
        {
            System.out.println("tipo cclasse printleft");
            System.out.println(left.getClass().getSimpleName());
            leftOperand = (BinaryOperand) left;
            result = leftOperand.toJava();
        }

        return result;
    }

    public String printRight()
    {
        AbstractOperand rightOperand;
        String result;

        if(right == null)
            return "";

        if(right.getClass().getSimpleName().equals("UnaryOperand"))
        {
            rightOperand = (UnaryOperand) right;
            result = rightOperand.toJava();
        }
        else
        {
            rightOperand = (BinaryOperand) right;
            result = rightOperand.toJava();
        }

        return result;
    }

}
