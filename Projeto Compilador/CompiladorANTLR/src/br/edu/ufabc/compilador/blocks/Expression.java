package br.edu.ufabc.compilador.blocks;

import br.edu.ufabc.compilador.definitions.DataTypes;
import br.edu.ufabc.compilador.operands.AbstractOperand;

public class Expression{
   private float value;
   
   private AbstractOperand root;
   
   public Expression(){
	   this.root  = null;
	   this.value = 0.0f;
   }
   
   public Expression(AbstractOperand root){
	   this.root  = root;
	   this.value = 0.0f;
   }

   public String toString(){
       return "Expression: " + this.value;
   }

   public String toJava()
   {
       return root.toJava();
   }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public AbstractOperand getRoot() {
        return root;
    }

    public void setRoot(AbstractOperand root) {
        this.root = root;
    }

    public DataTypes getDataType()
    {
        return root.getDataType();
    }

}