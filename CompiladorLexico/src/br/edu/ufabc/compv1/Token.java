package br.edu.ufabc.compv1;

public class Token {

    public static final int ID             = 0;
    public static final int INT_NUMBER     = 1;
    public static final int FLOAT_NUMBER   = 2;
    public static final int OPERATOR       = 3;
    public static final int RESERVED_WORD = 4;
    public static final int PUNCTUATION    = 5;
    public static final int ERROR          = 6;
    
    private int id;
    private String text;

    public Token() {
        id = 0;
        text = "";
    }

    public Token(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
    
    public String toString(){
        return "<"+idToText(id)+","+text+">";
    }
    
    public String idToText(int id)
    {
        switch(id)
        {
            case 0:
                return "IDENT";
            case 1:
                return "INT_N";
            case 2:
                return "FLO_N";
            case 3:
                return "OPERA";
            case 4:
                return "RSD_W";
            case 5:
                return "PUNCT";
            case 6:
                return "ERROR";
            default: return "No Text to convert from id";
        }
    }
}

