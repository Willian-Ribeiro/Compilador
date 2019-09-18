package br.edu.ufabc.compilador;
import br.edu.ufabc.compilador.definitions.*;
import br.edu.ufabc.compilador.commands.*;
import br.edu.ufabc.compilador.blocks.*;
import br.edu.ufabc.compilador.operands.*;
// $ANTLR 2.7.6 (2005-12-22): "gramatica.g" -> "MyParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class MyParser extends antlr.LLkParser       implements MyParserTokenTypes
 {

	java.util.HashMap<String, Variables> mapaVar;

	java.util.ArrayList<Expression> expList = new java.util.ArrayList<Expression>();
	Expression expression;
	AbstractOperand num;
	AbstractOperand parent;
	BinaryOperand sumOrSubt;
	BinaryOperand multOrDiv;
	Variables variables;
	char op;
	ListManager listManager = new ListManager();
	CmdAtribuicao cmdAtribuicao;

	Program p;

	public void setProgram(String name){
      p = new Program(name);
    }

    public Variables checkMapaVar(String varName) {
    	// check if var is in any loop
    	if( !listManager.loopIsEmpty() && (listManager.loopGetVar(varName) != null) )
    		return listManager.loopGetVar(varName);
    	else if( mapaVar.get(varName) != null )
    		return mapaVar.get(varName);
    	return null;
    }
  
    public Program getProgram(){
       return p;
    }

protected MyParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public MyParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected MyParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public MyParser(TokenStream lexer) {
  this(lexer,1);
}

public MyParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void prog() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			mapaVar = new java.util.HashMap<String, Variables>();
			match(LITERAL_programa);
			declara();
			bloco();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_declare);
			match(T_Id);
			
								if( listManager.loopIsEmpty() )
									mapaVar.put( LT(0).getText(), new Variables(LT(0).getText()) );
								else
									listManager.lastLoop().addLoopVariaveis( new Variables(LT(0).getText()) );
							
			{
			_loop4:
			do {
				if ((LA(1)==T_virg)) {
					match(T_virg);
					match(T_Id);
					mapaVar.put(LT(0).getText(), new Variables(LT(0).getText()) );
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			match(T_pontof);
			
							if( listManager.loopIsEmpty() )
								p.setVariaveis(mapaVar.values());
						  System.out.println("Variable list assembled...");
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void bloco() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt7=0;
			_loop7:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					cmd();
				}
				else {
					if ( _cnt7>=1 ) { break _loop7; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt7++;
			} while (true);
			}
			match(LITERAL_fimprog);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_leia:
			{
				cmdLeia();
				match(T_pontof);
				break;
			}
			case LITERAL_escreva:
			{
				cmdEscr();
				match(T_pontof);
				break;
			}
			case T_Id:
			{
				cmdAttr();
				match(T_pontof);
				System.out.println("Fim Bloco atribuicao");
				break;
			}
			case T_comt:
			{
				cmdIgnore();
				match(T_pontof);
				break;
			}
			case LITERAL_se:
			{
				cmdIfElse();
				match(T_pontof);
				break;
			}
			case LITERAL_repita:
			{
				cmdLoop();
				match(T_pontof);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmdLeia() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_leia);
			System.out.println("Bloco leia");
			match(T_ap);
			match(T_Id);
			
								if( checkMapaVar(LT(0).getText()) == null )
									throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
			
								p.addCommand(new CmdLeitura(checkMapaVar(LT(0).getText()).getName()));
							
			match(T_fp);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdEscr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_escreva);
			System.out.println("Bloco escreva");
			match(T_ap);
			{
			switch ( LA(1)) {
			case T_texto:
			{
				match(T_texto);
				p.addCommand(new CmdEscrita(LT(0).getText()));
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
									if( checkMapaVar(LT(0).getText()) == null )
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
									p.addCommand(new CmdEscrita(checkMapaVar(LT(0).getText()).getName()));
								
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_fp);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdAttr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(T_Id);
			
								if( checkMapaVar(LT(0).getText()) == null )
									throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
								cmdAtribuicao = new CmdAtribuicao(checkMapaVar(LT(0).getText()));
								p.addCommand(cmdAtribuicao);
							
			match(T_attr);
			
									System.out.println("Bloco atribuicao");
									expList = new java.util.ArrayList<Expression>();
									cmdAtribuicao.setCommand(expList);
								
			expr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdIgnore() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(T_comt);
			{
			_loop11:
			do {
				switch ( LA(1)) {
				case T_Id:
				{
					match(T_Id);
					break;
				}
				case T_num:
				{
					match(T_num);
					break;
				}
				case T_soma:
				{
					match(T_soma);
					break;
				}
				case T_subt:
				{
					match(T_subt);
					break;
				}
				case T_mult:
				{
					match(T_mult);
					break;
				}
				case T_divi:
				{
					match(T_divi);
					break;
				}
				case T_comp:
				{
					match(T_comp);
					break;
				}
				case T_virg:
				{
					match(T_virg);
					break;
				}
				case T_ap:
				{
					match(T_ap);
					break;
				}
				case T_fp:
				{
					match(T_fp);
					break;
				}
				case T_texto:
				{
					match(T_texto);
					break;
				}
				case T_attr:
				{
					match(T_attr);
					break;
				}
				case LITERAL_leia:
				{
					match(LITERAL_leia);
					break;
				}
				case LITERAL_escreva:
				{
					match(LITERAL_escreva);
					break;
				}
				case LITERAL_se:
				{
					match(LITERAL_se);
					break;
				}
				case LITERAL_senao:
				{
					match(LITERAL_senao);
					break;
				}
				case LITERAL_repita:
				{
					match(LITERAL_repita);
					break;
				}
				default:
				{
					break _loop11;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdIfElse() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
			match(T_ap);
			listManager.addIf(new CmdIfElse());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				listManager.lastIfElse().setCondicionail1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
												if(checkMapaVar(LT(0).getText()) != null)
													listManager.lastIfElse().setCondicionail1(checkMapaVar(LT(0).getText()));
												else
													throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
										
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_comp);
			listManager.lastIfElse().setComparador(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				listManager.lastIfElse().setCondicionail2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
												if(checkMapaVar(LT(0).getText()) != null)
													listManager.lastIfElse().setCondicionail2(checkMapaVar(LT(0).getText()));
												else
													throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
										
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_fp);
			match(T_ac);
			Command lastCommandIf = p.getLastCommand();
			{
			int _cnt27=0;
			_loop27:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case LITERAL_leia:
					{
						cmdLeia();
						break;
					}
					case LITERAL_escreva:
					{
						cmdEscr();
						break;
					}
					case T_Id:
					{
						cmdAttr();
						break;
					}
					case T_comt:
					{
						cmdIgnore();
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(T_pontof);
					
													if(!lastCommandIf.equals(p.getLastCommand()))
														listManager.lastIfElse().addCommandIf(p.popCommand());
											
				}
				else {
					if ( _cnt27>=1 ) { break _loop27; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt27++;
			} while (true);
			}
			match(T_fc);
			{
			switch ( LA(1)) {
			case LITERAL_senao:
			{
				match(LITERAL_senao);
				match(T_ac);
				Command lastCommandElse = p.getLastCommand();
				{
				int _cnt31=0;
				_loop31:
				do {
					if ((_tokenSet_5.member(LA(1)))) {
						{
						switch ( LA(1)) {
						case LITERAL_leia:
						{
							cmdLeia();
							break;
						}
						case LITERAL_escreva:
						{
							cmdEscr();
							break;
						}
						case T_Id:
						{
							cmdAttr();
							break;
						}
						case T_comt:
						{
							cmdIgnore();
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						match(T_pontof);
						
														if(!lastCommandElse.equals(p.getLastCommand()))
															listManager.lastIfElse().addCommandElse(p.popCommand());
												
					}
					else {
						if ( _cnt31>=1 ) { break _loop31; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt31++;
				} while (true);
				}
				match(T_fc);
				break;
			}
			case T_pontof:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			
							p.addCommand(listManager.lastIfElse());
							listManager.popIf();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdLoop() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_repita);
			match(T_ap);
			match(T_Id);
			
											listManager.addLoop(new CmdLoop());
											if( mapaVar.get(LT(0).getText()) == null )
											{
												listManager.lastLoop().setIterator(new Variables(LT(0).getText()));
											}
											else
												listManager.lastLoop().setIterator( mapaVar.get(LT(0).getText()) );
										
			match(T_attr);
			match(T_num);
			
													listManager.lastLoop().getIterator().setValue(LT(0).getText());
													listManager.lastLoop().getIterator().setType(DataTypes.TYPE_DOUBLE);
												
			match(T_pontof);
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				listManager.lastLoop().setWhile_var1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
														listManager.lastLoop().setWhile_var1(checkMapaVar(LT(0).getText()));
													else
														throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
											
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_comp);
			listManager.lastLoop().setComparador(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				listManager.lastLoop().setWhile_var2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
														listManager.lastLoop().setWhile_var2( checkMapaVar(LT(0).getText()) );
													else
														throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
											
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_pontof);
			{
			switch ( LA(1)) {
			case T_subt:
			{
				match(T_subt);
				break;
			}
			case T_soma:
			{
				match(T_soma);
				break;
			}
			case T_mult:
			{
				match(T_mult);
				break;
			}
			case T_divi:
			{
				match(T_divi);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			listManager.lastLoop().setIncrementOp(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				listManager.lastLoop().setIncrement(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
														listManager.lastLoop().setIncrement( checkMapaVar(LT(0).getText()) );
													else
														throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
											
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_fp);
			match(T_ac);
			Command lastCommand = p.getLastCommand();
			{
			_loop21:
			do {
				if ((_tokenSet_6.member(LA(1)))) {
					{
					_loop19:
					do {
						if ((LA(1)==LITERAL_declare)) {
							declara();
						}
						else {
							break _loop19;
						}
						
					} while (true);
					}
					{
					switch ( LA(1)) {
					case LITERAL_leia:
					{
						cmdLeia();
						break;
					}
					case LITERAL_escreva:
					{
						cmdEscr();
						break;
					}
					case T_Id:
					{
						cmdAttr();
						break;
					}
					case T_comt:
					{
						cmdIgnore();
						break;
					}
					case LITERAL_repita:
					{
						cmdLoop();
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(T_pontof);
					
														if(!lastCommand.equals(p.getLastCommand()))
															listManager.lastLoop().addCommand(p.popCommand());
												
				}
				else {
					break _loop21;
				}
				
			} while (true);
			}
			match(T_fc);
			
							p.addCommand(listManager.lastLoop());
							listManager.popLoop();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			expression = new Expression();
			expr_c();
			
			if (expression.getRoot() == null) expression.setRoot(num);
			expList.add( expression );
			expression.eval();
			System.out.println(expression);
			System.out.println(expression.getRoot().toXml());
			expression = null;
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void expr_c() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			{
			_loop40:
			do {
				if ((LA(1)==T_soma||LA(1)==T_subt)) {
					{
					switch ( LA(1)) {
					case T_soma:
					{
						match(T_soma);
						break;
					}
					case T_subt:
					{
						match(T_subt);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					
											op = LT(0).getText().charAt(0);
					sumOrSubt = new BinaryOperand(op);
					sumOrSubt.setLeft(num);
					System.out.println("fim set left");
					
					termo();
					
					if(expression.getRoot() == null)
					{
											System.out.println("root null");
											sumOrSubt.setRight(num);
											expression.setRoot(sumOrSubt);
											parent = sumOrSubt;
					}
					else
					{
											System.out.println("root not null");
											sumOrSubt = new BinaryOperand(op);
											sumOrSubt.setRight(num);
											BinaryOperand pai = (BinaryOperand)parent;
											sumOrSubt.setLeft(pai.getRight());
											pai.setRight(sumOrSubt);
					}
					
				}
				else {
					break _loop40;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			System.out.println("termo");
			fator();
			{
			_loop44:
			do {
				if ((LA(1)==T_mult||LA(1)==T_divi)) {
					{
					switch ( LA(1)) {
					case T_mult:
					{
						match(T_mult);
						break;
					}
					case T_divi:
					{
						match(T_divi);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					fator();
				}
				else {
					break _loop44;
				}
				
			} while (true);
			}
			System.out.println("fim termo");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_Id:
			{
				System.out.println("inicio fator");
				match(T_Id);
				
									if( checkMapaVar(LT(0).getText()) == null )
									{
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
									}
									else
									{
										num = new UnaryOperand( Float.parseFloat( checkMapaVar(LT(0).getText()).getValue() ) );
										System.out.println("Unary operand (var): " + num.getValue());
									}
								
				break;
			}
			case T_num:
			{
				match(T_num);
				
									System.out.println("setando um fator");
									num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
									System.out.println("Unary operand (num): " + num.getValue());
				
				break;
			}
			case T_ap:
			{
				match(T_ap);
				expr();
				match(T_fp);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"programa\"",
		"\"declare\"",
		"T_Id",
		"T_virg",
		"T_pontof",
		"\"fimprog\"",
		"T_comt",
		"T_num",
		"T_soma",
		"T_subt",
		"T_mult",
		"T_divi",
		"T_comp",
		"T_ap",
		"T_fp",
		"T_texto",
		"T_attr",
		"\"leia\"",
		"\"escreva\"",
		"\"se\"",
		"\"senao\"",
		"\"repita\"",
		"T_ac",
		"T_fc",
		"T_blank"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 48235616L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 48235584L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 48236096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 6292544L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 39847008L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 262400L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 274688L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 323840L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	
	}
