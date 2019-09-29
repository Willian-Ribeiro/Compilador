package br.edu.ufabc.compilador;
import br.edu.ufabc.compilador.definitions.*;
import br.edu.ufabc.compilador.commands.*;
import br.edu.ufabc.compilador.blocks.*;
import br.edu.ufabc.compilador.operands.*;
// $ANTLR 2.7.6 (2005-12-22): "gramatica.g" -> "MyParser.java"$

public interface MyParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int LITERAL_programa = 4;
	int LITERAL_declare = 5;
	int T_Id = 6;
	int T_virg = 7;
	int T_pontof = 8;
	int LITERAL_fimprog = 9;
	int T_comt = 10;
	int T_num = 11;
	int T_soma = 12;
	int T_subt = 13;
	int T_mult = 14;
	int T_divi = 15;
	int T_comp = 16;
	int T_ap = 17;
	int T_fp = 18;
	int T_texto = 19;
	int T_attr = 20;
	int LITERAL_leia = 21;
	int LITERAL_escreva = 22;
	int LITERAL_se = 23;
	int LITERAL_senao = 24;
	int LITERAL_repita = 25;
	int LITERAL_enquanto = 26;
	int T_ac = 27;
	int T_fc = 28;
	int T_blank = 29;
}
