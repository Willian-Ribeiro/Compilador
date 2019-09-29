package br.edu.ufabc.compilador.definitions;

public enum DataTypes {

    TYPE_STRING(5),
    TYPE_DOUBLE(4),
    TYPE_FLOAT(3),
    TYPE_INT(2),
    TYPE_BOOLEAN(1);

    private int priority;

    DataTypes(int priority)
    {
        this.priority = priority;
    }

    public int getPriority()
    {
        return this.priority;
    }
}
