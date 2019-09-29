package br.edu.ufabc.compilador.operands;

import br.edu.ufabc.compilador.definitions.DataTypes;

public abstract class AbstractOperand {
    public abstract String toJava();
    public abstract String toArduino();
    public abstract DataTypes getDataType();
}
