package br.edu.ufabc.compilador;

public class Variables {

    private String value;
    private DataTypes type;

    Variables(String value, DataTypes type, String name)
    {
        this.type = type;
        this.value = value;
    }

    Variables()
    {
        this.type = DataTypes.TYPE_STRING;
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataTypes getType() {
        return type;
    }

    public void setType(DataTypes type) {
        this.type = type;
    }
}
