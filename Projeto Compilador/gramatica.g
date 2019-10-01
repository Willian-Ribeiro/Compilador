
class MyParser extends Parser;
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
}

prog    :   {mapaVar = new java.util.HashMap<String, Variables>();scopeManager.setMapaVar(mapaVar);}
			"programa" declara bloco
		;

declara :   "declare" 
			T_Id {
					if( scopeManager.loopIsEmpty() )
						mapaVar.put( LT(0).getText(), new Variables(LT(0).getText()) );
					else
						scopeManager.lastLoop().addLoopVariaveis( new Variables(LT(0).getText()) );
				 }
			(T_virg T_Id {mapaVar.put(LT(0).getText(), new Variables(LT(0).getText()) ); } )*
			T_pontof
			{
				if( scopeManager.loopIsEmpty() )
					p.setVariaveis(mapaVar.values());
		    }
        ;

bloco	:	( cmd )+ "fimprog"
		;

cmd     : 	cmdLeia 	T_pontof
		|	cmdEscr		T_pontof
		|	cmdAttr		T_pontof
		|	cmdIgnore   T_pontof
		|   cmdIfElse	T_pontof
		|	cmdLoop     T_pontof
		|	cmdWhile    T_pontof
		;

cmdIgnore : T_comt ( T_Id | T_num | T_soma | T_subt | T_mult | T_divi | T_comp
		  | T_virg | T_ap | T_fp | T_texto | T_attr | "leiaTxt" | "leiaNum" | "escreva" | "se"
		  | "senao" | "repita" | "enquanto" )* // seguido de qualquer coisa
		  ;

cmdLoop	:	"repita" T_ap
						T_Id {
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
							 }
						(T_attr T_num {
										scopeManager.lastLoop().getIterator().setUsed(true);
										scopeManager.lastLoop().getIterator().setAttributed(true);
										scopeManager.lastLoop().getIterator().setValue(LT(0).getText());
										scopeManager.lastLoop().getIterator().setType(DataTypes.TYPE_DOUBLE);
									 })? T_pontof

						(T_num {scopeManager.lastLoop().setWhile_var1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );}
						| T_Id {
									if(checkMapaVar(LT(0).getText()) != null)
									{
										if(!checkMapaVar(LT(0).getText()).getAttributed())
											throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
										checkMapaVar(LT(0).getText()).setUsed(true);
										scopeManager.lastLoop().setWhile_var1(checkMapaVar(LT(0).getText()));
									}
									else
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
							   })

						T_comp {scopeManager.lastLoop().setComparador(LT(0).getText());}
						(T_num {scopeManager.lastLoop().setWhile_var2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );}
						| T_Id {
									if(checkMapaVar(LT(0).getText()) != null)
									{
										if(!checkMapaVar(LT(0).getText()).getAttributed())
											throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
										checkMapaVar(LT(0).getText()).setUsed(true);
										scopeManager.lastLoop().setWhile_var2( checkMapaVar(LT(0).getText()) );
									}
									else
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
							   }) T_pontof

						(T_subt | T_soma | T_mult | T_divi) {scopeManager.lastLoop().setIncrementOp(LT(0).getText());}
						(T_num {scopeManager.lastLoop().setIncrement(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );}
						| T_Id {
									if(checkMapaVar(LT(0).getText()) != null)
									{
										if(!checkMapaVar(LT(0).getText()).getAttributed())
											throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
										checkMapaVar(LT(0).getText()).setUsed(true);
										scopeManager.lastLoop().setIncrement( checkMapaVar(LT(0).getText()) );
									}
									else
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
							   })
					T_fp 
					T_ac 
						{Command lastCommand = p.getLastCommand();}

						( ( declara )* ( cmdLeia | cmdEscr | cmdAttr | cmdIgnore | cmdLoop ) T_pontof
							{
								// if command changed, should be placed inside repeat scope
								if(!lastCommand.equals(p.getLastCommand()))
									scopeManager.lastLoop().addCommand(p.popCommand());
							}
						)*
					T_fc

			{
				p.addCommand(scopeManager.lastLoop());
				scopeManager.popLoop();
			}
		;

cmdWhile:	"enquanto"
				T_ap {scopeManager.addWhile(new CmdWhile());}
					expr {
							scopeManager.lastWhile().addExpression(expression);
							if(scopeManager.lastWhile().getLastExpression().getRoot() == null)
								throw new RuntimeException("ERROR while condition not set");
						 }
					(
						T_comp { scopeManager.lastWhile().addComparator(LT(0).getText()); }
						expr { scopeManager.lastWhile().addExpression(expression); } 
					)*
				T_fp 
				T_ac
					{Command lastCommand = p.getLastCommand();}

					( ( cmdLeia | cmdEscr | cmdAttr | cmdIgnore ) T_pontof
						{
							if(!lastCommand.equals(p.getLastCommand()))
								scopeManager.lastWhile().addCommand(p.popCommand());
						}
					)+
				T_fc 
			{
				p.addCommand(scopeManager.lastWhile());
				scopeManager.popWhile();
			}
		;

cmdIfElse:  "se" T_ap {scopeManager.addIf(new CmdIfElse());}
					(T_num {scopeManager.lastIfElse().setCondicionail1(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );}
					| T_Id {
								if(checkMapaVar(LT(0).getText()) != null)
								{
									if(!checkMapaVar(LT(0).getText()).getAttributed())
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
									checkMapaVar(LT(0).getText()).setUsed(true);
									scopeManager.lastIfElse().setCondicionail1(checkMapaVar(LT(0).getText()));
								}
								else
									throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
						   })

					T_comp {scopeManager.lastIfElse().setComparador(LT(0).getText());}

					(T_num {scopeManager.lastIfElse().setCondicionail2(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );}
					| T_Id {
								if(checkMapaVar(LT(0).getText()) != null)
								{
									if(!checkMapaVar(LT(0).getText()).getAttributed())
										throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
									checkMapaVar(LT(0).getText()).setUsed(true);
									scopeManager.lastIfElse().setCondicionail2(checkMapaVar(LT(0).getText()));
								}
								else
									throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
						   })
				T_fp 
				T_ac
					{Command lastCommandIf = p.getLastCommand();}

					( ( cmdLeia | cmdEscr | cmdAttr | cmdIgnore ) T_pontof
						{
							if(!lastCommandIf.equals(p.getLastCommand()))
								scopeManager.lastIfElse().addCommandIf(p.popCommand());
						}
					)+
				T_fc 
			("senao"
				T_ac
					{Command lastCommandElse = p.getLastCommand();}

					( ( cmdLeia | cmdEscr | cmdAttr | cmdIgnore ) T_pontof
						{
								if(!lastCommandElse.equals(p.getLastCommand()))
									scopeManager.lastIfElse().addCommandElse(p.popCommand());
						}
					)+
				T_fc )?
			{
				p.addCommand(scopeManager.lastIfElse());
				scopeManager.popIf();
			}
		;

cmdLeia	:	("leiaTxt" T_ap
			T_Id {
					if( checkMapaVar(LT(0).getText()) == null )
						throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
					checkMapaVar(LT(0).getText()).setAttributed(true);
					p.addCommand(new CmdLeitura(checkMapaVar(LT(0).getText())));
				 }

			T_fp )
			|("leiaNum" T_ap
			T_Id {
					if( checkMapaVar(LT(0).getText()) == null )
						throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
					checkMapaVar(LT(0).getText()).setAttributed(true);
					checkMapaVar(LT(0).getText()).setType(DataTypes.TYPE_DOUBLE);
					p.addCommand(new CmdLeitura(checkMapaVar(LT(0).getText())));
				 }

			T_fp )
		;

cmdEscr	: 	"escreva"
				
			T_ap 
			( 
				T_texto { p.addCommand(new CmdEscrita(LT(0).getText())); }
				| T_num { p.addCommand(new CmdEscrita(LT(0).getText())); }
				| T_Id
					{
						if( checkMapaVar(LT(0).getText()) == null )
							throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
						if(!checkMapaVar(LT(0).getText()).getAttributed())
								throw new RuntimeException("ERROR ID " + LT(0).getText() + " variable not attributed");
							checkMapaVar(LT(0).getText()).setUsed(true);
						p.addCommand(new CmdEscrita(checkMapaVar(LT(0).getText()).getName()));
					}
			)
			T_fp
		;

cmdAttr	: 	T_Id {
					if( checkMapaVar(LT(0).getText()) == null )
						throw new RuntimeException("ERROR ID " + LT(0).getText() + " not declared");
					cmdAtribuicao = new CmdAtribuicao(checkMapaVar(LT(0).getText()));
					p.addCommand(cmdAtribuicao);
				 }
			T_attr expr 
				{
					cmdAtribuicao.setExpression(expression);
				}
		;

//        	termo ((T_soma  | T_subt) termo)*
expr  	:	{expression = new Expression();}
			termo
				{
					expression.setRoot( termoOperand );
				}
          	((T_soma  | T_subt){
          						// create new BinaryOperand, make it root, former root on left, new termo on right
								BinaryOperand exprBinaryOp = new BinaryOperand(LT(0).getText());
								exprBinaryOp.setLeft(expression.getRoot());
								expression.setRoot( exprBinaryOp );
                             }
          	termo {
          			// exprBinaryOp.setRight(termoOperand);
          			exprBinaryOp = (BinaryOperand) expression.getRoot();
          			exprBinaryOp.setRight(termoOperand);
                }
          	)*
		;

		// fator (( T_mult | T_divi ) fator )*
termo 	:	fator 
					{
						termoOperand = fatorOperand;
					}
			(
				( T_mult | T_divi )
					{
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
					}
				fator
					{
						binaryOperand = (BinaryOperand) termoOperand;
						binaryOperand.getRightmostBinaryOp().setRight(fatorOperand);
					}
			)*
		;

		// (T_soma | T_subt)? (T_Id | T_num | T_texto | T_ap expr T_fp )
fator 	:	{ fatorOperand = new UnaryOperand(); }
			((T_soma | T_subt) {fatorOperand.setOperand(LT(0).getText());})?
			( T_Id {
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
				 }

			| T_num {
						fatorOperand.setVar( new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_DOUBLE) );
	                }

	        | T_texto
	        {
	        	fatorOperand.setVar(new Variables(Variables.NUMBER, LT(0).getText(), DataTypes.TYPE_STRING));
	        }

			| T_ap
				{
					// save state and initialize expression
					fatorOperand.saveState(expression, termoOperand, binaryOperand, fatorOperand, lastFator);
					lastFator = fatorOperand;
				}
				expr 
				{
					// load state from previous expression
					fatorOperand = lastFator;
					fatorOperand.setInsideParenthesis(expression);
					expression = fatorOperand.getSavedExpression();
					termoOperand = fatorOperand.getSavedAbstractOperand();
					binaryOperand = fatorOperand.getSavedBinaryOperand();
					fatorOperand = fatorOperand.getSavedUnaryOperand();
					lastFator = fatorOperand.getLastFator();
				}
			T_fp )

		;




class MyLexer extends Lexer;
options{
	caseSensitive = true;
	k=2;
}

T_Id 		:	( 'a'..'z' | 'A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9')*
			;

T_num 		:   ('0'..'9')+ (('.') ('0'..'9')+)?
			;

T_soma		:	'+'
			;

T_subt		:	'-'
			;

T_mult		:	'*'
			;

T_divi		:	'/'
			;

T_virg		:	','
			;

T_pontof	:	';'
			;

T_ap		:	'('
			;

T_fp		:	')'
			;

T_ac		:	'{'
			;

T_fc		:	'}'
			;

T_texto		:	'"' ( ' ' | '!' | '#'..'Z' | '_'..'~' )+ '"'
			;

T_attr		:	'='
			;

T_blank		:	( ' ' | '\n' {newline();} | '\r' | '\t' ) {_ttype=Token.SKIP;}
			;

T_comt		:	"//"
			;

T_comp		: '<' | "<=" | '>' | ">=" | "!=" | "?="
			;
