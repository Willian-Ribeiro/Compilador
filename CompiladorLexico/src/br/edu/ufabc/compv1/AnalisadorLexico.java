package br.edu.ufabc.compv1;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class AnalisadorLexico {

    private String[] reservedWords = {"programa","declare","escreva","leia", "inicio", "fim", "inteiros"};
    private char[] content;
    private int pos;

    public AnalisadorLexico(String filename) {
        try {
            byte[] bContent = Files.readAllBytes(new File(filename).toPath());
            this.content = new String(bContent).toCharArray();
            this.pos = 0;
        } catch (IOException ex) {
            System.err.println("Erro ao ler arquivo");
        }

    }

    private boolean isReservedWord(String text){
        for (String s: reservedWords){
            if (text.equals(s)){
                return true;
            }
        }
        return false;
    }
    
    public boolean eof() {
        return pos >= content.length;
    }

    private char nextChar() {
        return content[pos++];
    }

    // Case insensitive?
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // quebra de linha ou espaco em branco, em todos os casos, ignoro o char
    private boolean isBlank(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    private void eliminateBlank()
    {
        while(true)
        {
            if( eof() ) return;
            if( !isBlank(nextChar()) )
            {
                retroceder();
                return;
            }
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    private boolean isPunctuation(char c) {
        return c == '.' || c == ',' || c == ':' || c == '(' || c == ')';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void retroceder() {
        pos--;
    }

    public Token nextToken() {
        Token token;

        eliminateBlank();

        if(eof()) return null;

        token = IdentifierAutomaton();
        if( token != null ) return token;

        token = NumberAutomaton();
        if( token != null ) return token;

        token = OperatorAutomaton();
        if( token != null ) return token;

        token = PunctuationAutomaton();
        if( token != null ) return token;

        // error - invalid char
        return new Token(Token.ERROR, "" + nextChar());
    }

    private Token IdentifierAutomaton()
    {
        int state = 0;
        String text="";
        Token token;

        while (true) {
            if (eof()) {
                return null;
            }

            switch (state) {
                case 0:
                    char c = nextChar();
                    if (isLetter(c)) {
                        state = 1;
                        text += c;
                    } else {
                        retroceder();
                        return null;
                    }
                    break;
                case 1:
                    c = nextChar();
                    if (isLetter(c) || isDigit(c)) {
                        state = 1;
                        text += c;
                    }
                    else
                    {
                        if (isReservedWord(text))
                            token = new Token(Token.RESERVED_WORD, text);
                        else
                            token = new Token(Token.ID, text);

                        retroceder();
                        return token;
                    }
                    break;
            }
        }
    }

    private Token NumberAutomaton()
    {
        int state = 0;
        String text="";

        while (true) {
            if (eof()) {
                return null;
            }

            switch (state) {
                case 0:
                    char c = nextChar();
                    if (isDigit(c)) {
                        state = 1;
                        text += c;
                    } else {
                        retroceder();
                        return null;
                    }
                    break;
                case 1:
                    c = nextChar();
                    if ( isDigit(c) ) {
                        state = 1;
                        text += c;
                    }
                    else if(c == '.')
                    {
                        state = 2;
                        text += c;
                    } else {
                        retroceder();
                        return new Token(Token.INT_NUMBER, text);
                    }
                    break;
                case 2:
                    c = nextChar();
                    if( isDigit(c) )
                    {
                        state = 3;
                        text += c;
                    } else {
                        retroceder();
                        retroceder();
                        return new Token(Token.INT_NUMBER, text.substring(0, text.length()-1));
                    }
                case 3:
                    c = nextChar();
                    if( isDigit(c) )
                    {
                        state = 3;
                        text += c;
                    } else {
                        retroceder();
                        return new Token(Token.FLOAT_NUMBER, text);
                    }
            }
        }
    }

    private Token OperatorAutomaton()
    {
        int state = 0;
        String text="";

        while (true) {
            if (eof()) {
                return null;
            }

            switch (state) {
                case 0:
                    char c = nextChar();
                    if (isOperator(c)) {
                        text += c;
                        return new Token(Token.OPERATOR, text);
                    } else {
                        retroceder();
                        return null;
                    }
            }
        }
    }

    private Token PunctuationAutomaton()
    {
        int state = 0;
        String text="";

        while (true) {
            if (eof()) {
                return null;
            }

            switch (state) {
                case 0:
                    char c = nextChar();
                    if ( isPunctuation(c) ) {
                        state = 1;
                        text += c;
                    } else {
                        retroceder();
                        return null;
                    }
                    break;
                case 1:
                    c = nextChar();
                    if ( (text.charAt(0) == ':') && (c == '=') ) {
                        text += c;
                        return new Token(Token.OPERATOR, text);
                    }
                    else {
                        retroceder();
                        return new Token(Token.PUNCTUATION, text);
                    }
            }
        }
    }

}
