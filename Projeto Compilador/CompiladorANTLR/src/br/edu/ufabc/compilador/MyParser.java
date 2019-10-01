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

	Expression expression;
	BinaryOperand binaryOperand;
	BinaryOperand multOrDiv;
	AbstractOperand termoOperand;
	UnaryOperand fatorOperand;
	UnaryOperand lastFator;
	ScopeManager scopeManager = ScopeManager.getInstance();
	CmdAtribuicao cmdAtribuicao;
	Program p;

	public void setProgram(String name){
      p = new Program(name);
    }

    public Variables checkMapaVar(String varName) {
    	// check if var is in any loop
    	if( !scopeManager.loopIsEmpty() && (scopeManager.loopGetVar(varName) != null) )
    		return scopeManager.loopGetVar(varName);
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
			mapaVar = new java.util.HashMap<String, Variables>();scopeManager.setMapaVar(mapaVar);
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
			
								if( scopeManager.loopIsEmpty() )
									mapaVar.put( LT(0).getText(), new Variables(LT(0).getText()) );
								else
									scopeManager.lastLoop().addLoopVariaveis( new Variables(LT(0).getText()) );
							
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
			
							if( scopeManager.loopIsEmpty() )
								p.setVariaveis(mapaVar.values());
					
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
			case LITERAL_leiaTxt:
			case LITERAL_leiaNum:
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
			case LITERAL_enquanto:
			{
				cmdWhile();
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
			switch ( LA(1)) {
			case LITERAL_leiaTxt:
			{
				{
				match(LITERAL_leiaTxt);
				match(T_ap);
				match(T_Id);
				
									if( checkMapaVar(LT(0).getText()) == null )
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
									checkMapaVar(LT(0).getText()).setAttributed(true);
									p.addCommand(new CmdLeitura(checkMapaVar(LT(0).getText())));
								
				match(T_fp);
				}
				break;
			}
			case LITERAL_leiaNum:
			{
				{
				match(LITERAL_leiaNum);
				match(T_ap);
				match(T_Id);
				
									if( checkMapaVar(LT(0).getText()) == null )
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
									checkMapaVar(LT(0).getText()).setAttributed(true);
									checkMapaVar(LT(0).getText()).setType(DataTypes.TYPE_DOUBLE);
									p.addCommand(new CmdLeitura(checkMapaVar(LT(0).getText())));
								
				match(T_fp);
				}
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdEscr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_escreva);
			match(T_ap);
			{
			switch ( LA(1)) {
			case T_texto:
			{
				match(T_texto);
				p.addCommand(new CmdEscrita(LT(0).getText()));
				break;
			}
			case T_num:
			{
				match(T_num);
				p.addCommand(new CmdEscrita(LT(0).getText()));
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
										if( checkMapaVar(LT(0).getText()) == null )
											throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
										if(!checkMapaVar(LT(0).getText()).getAttributed())
												throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
											checkMapaVar(LT(0).getText()).setUsed(true);
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
			expr();
			
								cmdAtribuicao.setExpression(expression);
							
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
				case LITERAL_leiaTxt:
				{
					match(LITERAL_leiaTxt);
					break;
				}
				case LITERAL_leiaNum:
				{
					match(LITERAL_leiaNum);
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
				case LITERAL_enquanto:
				{
					match(LITERAL_enquanto);
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
			scopeManager.addIf(new CmdIfElse());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				scopeManager.lastIfElse().setCondicionail1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
												if(checkMapaVar(LT(0).getText()) != null)
												{
													if(!checkMapaVar(LT(0).getText()).getAttributed())
														throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
													checkMapaVar(LT(0).getText()).setUsed(true);
													scopeManager.lastIfElse().setCondicionail1(checkMapaVar(LT(0).getText()));
												}
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
			scopeManager.lastIfElse().setComparador(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				scopeManager.lastIfElse().setCondicionail2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
												if(checkMapaVar(LT(0).getText()) != null)
												{
													if(!checkMapaVar(LT(0).getText()).getAttributed())
														throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
													checkMapaVar(LT(0).getText()).setUsed(true);
													scopeManager.lastIfElse().setCondicionail2(checkMapaVar(LT(0).getText()));
												}
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
			int _cnt34=0;
			_loop34:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case LITERAL_leiaTxt:
					case LITERAL_leiaNum:
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
													scopeManager.lastIfElse().addCommandIf(p.popCommand());
											
				}
				else {
					if ( _cnt34>=1 ) { break _loop34; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt34++;
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
				int _cnt38=0;
				_loop38:
				do {
					if ((_tokenSet_5.member(LA(1)))) {
						{
						switch ( LA(1)) {
						case LITERAL_leiaTxt:
						case LITERAL_leiaNum:
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
															scopeManager.lastIfElse().addCommandElse(p.popCommand());
												
					}
					else {
						if ( _cnt38>=1 ) { break _loop38; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt38++;
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
			
							p.addCommand(scopeManager.lastIfElse());
							scopeManager.popIf();
						
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
			
											scopeManager.addLoop(new CmdLoop());
											if( mapaVar.get(LT(0).getText()) == null )
											{
												scopeManager.lastLoop().setIterator(new Variables(LT(0).getText()));
											}
											else
											{
												if(!checkMapaVar(LT(0).getText()).getAttributed())
													throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
												checkMapaVar(LT(0).getText()).setUsed(true);
												scopeManager.lastLoop().setIterator( mapaVar.get(LT(0).getText()) );
											}
										
			{
			switch ( LA(1)) {
			case T_attr:
			{
				match(T_attr);
				match(T_num);
				
														scopeManager.lastLoop().getIterator().setUsed(true);
														scopeManager.lastLoop().getIterator().setAttributed(true);
														scopeManager.lastLoop().getIterator().setValue(LT(0).getText());
														scopeManager.lastLoop().getIterator().setType(DataTypes.TYPE_DOUBLE);
													
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
			match(T_pontof);
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				scopeManager.lastLoop().setWhile_var1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
													{
														if(!checkMapaVar(LT(0).getText()).getAttributed())
															throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
														checkMapaVar(LT(0).getText()).setUsed(true);
														scopeManager.lastLoop().setWhile_var1(checkMapaVar(LT(0).getText()));
													}
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
			scopeManager.lastLoop().setComparador(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				scopeManager.lastLoop().setWhile_var2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
													{
														if(!checkMapaVar(LT(0).getText()).getAttributed())
															throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
														checkMapaVar(LT(0).getText()).setUsed(true);
														scopeManager.lastLoop().setWhile_var2( checkMapaVar(LT(0).getText()) );
													}
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
			scopeManager.lastLoop().setIncrementOp(LT(0).getText());
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				scopeManager.lastLoop().setIncrement(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
													if(checkMapaVar(LT(0).getText()) != null)
													{
														if(!checkMapaVar(LT(0).getText()).getAttributed())
															throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
														checkMapaVar(LT(0).getText()).setUsed(true);
														scopeManager.lastLoop().setIncrement( checkMapaVar(LT(0).getText()) );
													}
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
			_loop22:
			do {
				if ((_tokenSet_6.member(LA(1)))) {
					{
					_loop20:
					do {
						if ((LA(1)==LITERAL_declare)) {
							declara();
						}
						else {
							break _loop20;
						}
						
					} while (true);
					}
					{
					switch ( LA(1)) {
					case LITERAL_leiaTxt:
					case LITERAL_leiaNum:
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
					
													// if command changed, should be placed inside repeat scope
													if(!lastCommand.equals(p.getLastCommand()))
														scopeManager.lastLoop().addCommand(p.popCommand());
												
				}
				else {
					break _loop22;
				}
				
			} while (true);
			}
			match(T_fc);
			
							p.addCommand(scopeManager.lastLoop());
							scopeManager.popLoop();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdWhile() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_enquanto);
			match(T_ap);
			scopeManager.addWhile(new CmdWhile());
			expr();
			
										scopeManager.lastWhile().addExpression(expression);
										if(scopeManager.lastWhile().getLastExpression().getRoot() == null)
											throw new RuntimeException("ERROR while condition not set");
									
			{
			_loop25:
			do {
				if ((LA(1)==T_comp)) {
					match(T_comp);
					scopeManager.lastWhile().addComparator(LT(0).getText());
					expr();
					scopeManager.lastWhile().addExpression(expression);
				}
				else {
					break _loop25;
				}
				
			} while (true);
			}
			match(T_fp);
			match(T_ac);
			Command lastCommand = p.getLastCommand();
			{
			int _cnt28=0;
			_loop28:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case LITERAL_leiaTxt:
					case LITERAL_leiaNum:
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
					
												if(!lastCommand.equals(p.getLastCommand()))
													scopeManager.lastWhile().addCommand(p.popCommand());
											
				}
				else {
					if ( _cnt28>=1 ) { break _loop28; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt28++;
			} while (true);
			}
			match(T_fc);
			
							p.addCommand(scopeManager.lastWhile());
							scopeManager.popWhile();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			expression = new Expression();
			termo();
			
								expression.setRoot( termoOperand );
							
			{
			_loop48:
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
					
											// create new BinaryOperand, make it root, former root on left, new termo on right
													BinaryOperand exprBinaryOp = new BinaryOperand(LT(0).getText());
													exprBinaryOp.setLeft(expression.getRoot());
													expression.setRoot( exprBinaryOp );
					
					termo();
					
								// exprBinaryOp.setRight(termoOperand);
								exprBinaryOp = (BinaryOperand) expression.getRoot();
								exprBinaryOp.setRight(termoOperand);
					
				}
				else {
					break _loop48;
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
			fator();
			
									termoOperand = fatorOperand;
								
			{
			_loop52:
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
					
											if(termoOperand.getClass().getSimpleName().equals("UnaryOperand"))
											{
												binaryOperand = new BinaryOperand(LT(0).getText());
												binaryOperand.setLeft(fatorOperand);
												termoOperand = binaryOperand;
											}
											else
											{
												// create new BinaryOperand, make rightmost node its left, make it new rightmost node, new termo on right
												BinaryOperand tempBinary = new BinaryOperand(LT(0).getText());
												binaryOperand = (BinaryOperand) termoOperand;
												tempBinary.setLeft(binaryOperand.getRightmostBinaryOp().getRight());
												binaryOperand.getRightmostBinaryOp().setRight(tempBinary);
											}
										
					fator();
					
											binaryOperand = (BinaryOperand) termoOperand;
											binaryOperand.getRightmostBinaryOp().setRight(fatorOperand);
										
				}
				else {
					break _loop52;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fatorOperand = new UnaryOperand();
			{
			switch ( LA(1)) {
			case T_soma:
			case T_subt:
			{
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
				fatorOperand.setOperand(LT(0).getText());
				break;
			}
			case T_Id:
			case T_num:
			case T_ap:
			case T_texto:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case T_Id:
			{
				match(T_Id);
				
									if( checkMapaVar(LT(0).getText()) == null )
									{
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
									}
									else
									{
										if(!checkMapaVar(LT(0).getText()).getAttributed())
											throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
										checkMapaVar(LT(0).getText()).setUsed(true);
										fatorOperand.setVar( checkMapaVar(LT(0).getText()) );
									}
								
				break;
			}
			case T_num:
			{
				match(T_num);
				
										fatorOperand.setVar( new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
					
				break;
			}
			case T_texto:
			{
				match(T_texto);
				
					        	fatorOperand.setVar(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_STRING));
					
				break;
			}
			case T_ap:
			{
				match(T_ap);
				
									// save state and initialize expression
									fatorOperand.saveState(expression, termoOperand, binaryOperand, fatorOperand, lastFator);
									lastFator = fatorOperand;
								
				expr();
				
									// load state from previous expression
									fatorOperand = lastFator;
									fatorOperand.setInsideParenthesis(expression);
									expression = fatorOperand.getSavedExpression();
									termoOperand = fatorOperand.getSavedAbstractOperand();
									binaryOperand = fatorOperand.getSavedBinaryOperand();
									fatorOperand = fatorOperand.getSavedUnaryOperand();
									lastFator = fatorOperand.getLastFator();
								
				match(T_fp);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
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
		"\"leiaTxt\"",
		"\"leiaNum\"",
		"\"escreva\"",
		"\"se\"",
		"\"senao\"",
		"\"repita\"",
		"\"enquanto\"",
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
		long[] data = { 232784992L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 232784960L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 232785472L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 14681152L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 81790048L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 327936L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 340224L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 389376L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	
	}
