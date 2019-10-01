programa
    declare a,b,c,d,e,f,g;
    escreva("Este programa foi feito com o Compilador Universal");

    escreva("Digite o texto de a");
    leiaTxt( a );

    escreva( a );

    escreva("Digite o valor de b");
    leiaNum( b );

    c = 1 + 2 * 3.3 * 8.8 * 9 * 10 * 11.1 * 12 * 13 + (1);

    d = 1 - -44 * 3 * 8 * -9  + -( 1 * 4 * 3 * 2 + b * (1 + 2));

    e = "texto";

    // secomentar o comando escreva abaixo,var e deixa de ser utilizada;
    escreva(e);

    escreva("Numero 5");
    escreva(5);

    escreva("f = soma de 1 + 2 + 3 + 4 ");
    f = 1 + 2 + 3 + 4;

    escreva( f );

    escreva("g = 2 * f * ( f + b ) = ");
    g = 2 * f * ( f + b );
    escreva( g );

    // posso declarar variavel dentro do bloco repita;
    // deve ocorrer antes dos comandos;
    repita( iterador=1; iterador<3; +1 ) {
        declare j;
        escreva("Qual valor de j?");
        leiaNum(j);
        j = j * 8.4;
        escreva("j = j * 8.5");
        escreva(j);
        escreva("isto eh um loop, repetindo");
        escreva(iterador);
        escreva("vezes");

        // posso declarar iterador dentro do comando repita;
        repita( iteradorNovo=5; iteradorNovo<7; +1 ) {
            declare l;
            escreva("Qual valor de l?");
            leiaNum(l);
            escreva("isto eh um loop aninhado");
            escreva("l vale ");
            // usando l;
            escreva(l);
        };

    };

    escreva("fim dos loops");

    escreva("se c de valor");
    escreva(c);
    escreva("maior que b de valor ");
    escreva(b);
    escreva("execute if");

    se( c > b )
    {
        escreva("isto eh um if");
    };

    escreva("se 2>3 execute if");
    se( 2 > 3 )
    {
        escreva("isto eh um if");
    }
    senao
    {
        escreva("isto eh um else");
    };

    // while;
    escreva("Isto eh um laco while");
    enquanto( d < 10 ){
        escreva("Repetindo enquanto d < 10");
        leiaNum( d );
    };

    escreva("fim do programa");

fimprog
