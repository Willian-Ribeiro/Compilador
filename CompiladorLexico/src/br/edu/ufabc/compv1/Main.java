package br.edu.ufabc.compv1;

public class Main {

    public static void main(String[] args) {
        AnalisadorLexico l;
        l = new AnalisadorLexico("prog.in");
        System.out.println(l.nextToken());
        System.out.println(l.nextToken());

        
        while(!l.eof())
        {
            Token token = l.nextToken();
            if(token != null) System.out.println(token);
            else break;
        }
    }
    
}