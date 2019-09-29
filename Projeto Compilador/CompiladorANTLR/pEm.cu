programa
    declare a,b,c,d;
    escreva("Este programa foi feito com o Compilador Universal");

    escreva("Digite o valor de a");
    leia( a );

    escreva("Digite o valor de b");
    leia( b );

    escreva("c = soma de a + b = ");
    c = 1 + 2;
    escreva( c );

    escreva("d = 2 * c = ");
    d = 2 * c;
    escreva( d );

    // posso declarar variavel dentro do bloco repita;
    // deve ocorrer antes dos comandos;
    repita( iterador=3; iterador<10; +1 ) {
        declare j;
        leia(j);
        j = 1 * 8.4;
        escreva(j);
        escreva("isto eh um loop");
        declare k;
        leia(k);
        k = 1+-2;
        escreva(k);

        // posso declarar iterador dentro do comando repita;
        repita( iteradorNovo=5; iteradorNovo<8; +1 ) {
            declare f;
            leia(f);
            escreva("isto eh um loop aninhado");
            // usando f;
            escreva(f);
        };

    };

    escreva("fim dos loops");

    se( a < b )
    {
        escreva("isto eh um if");
    };

    se( 2 < 3 )
    {
        escreva("isto eh um if");
    }
    senao
    {
        escreva("isto eh um else");
    };

    // while;
    enquanto( d < 2 ){
        escreva("Isto eh um laco while");
        c = c + 1;
    };

fimprog
