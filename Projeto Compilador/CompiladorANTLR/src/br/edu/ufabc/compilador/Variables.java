package br.edu.ufabc.compilador;

public class Variables {

    private String name;
    private String value;
    private DataTypes type;

    Variables(String value, DataTypes type, String name)
    {
        this.type = type;
        this.value = value;
        this.name = name;
    }

    Variables(String name)
    {
        this.name = name;
        this.type = DataTypes.TYPE_INT;
        this.value = "";
    }

    @Override
    public String toString()
    {
        return this.name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
