# Projeto Compilador para .java 

**Membros:**

- Willian Ribeiro 11018010
- Carlos Alberto 11098512
- Daniel Czeresnia 11003716

## Breve Descricao

Projeto para o curso de Compiladores, consistindo no Desenvolvimento de um compilador de uma linguagem generica para linguagem Java. Composto de um breve analisador Lexico, *parser* e gerenciador de certos casos de erro, o compilador adere a certos fundamentos de linguagem listados a seguir performando como prototipo para desenvolvimentos futuros.

## Checklist

- [x] Possui 2 tipos de variáveis 
- [x] Possui a estrutura If Else
- [x] 1a Estrutura de Repetição
- [x] 2a Estrutura de Repetição
- [x] Verificar se variável foi atribuída ou não
- [x] Possui operações de entrada e saída
- [x] Aceita números decimais
- [x] Verificar se variável foi declarada
- [x] Verificar se variável declarada foi ou não usada

## Instalacao e Inicio

###### Requisitos:
- **Sistema Operacional:** recomenda-se os baseados em Linux.
- **IDE:** recomenda-se o uso de Intellij IDEA Community edition 2019.2.2.

###### Manual:
**1.** Instalar sua IDE de preferencia

**2.** Clonar o repositorio para um endereco de sua preferencia. Abrir o diretorio do projeto com sua IDE.

**3.** Alterar o arquivo *pEm.cu* no caminho: *Projeto-Compilador\Projeto Compilador\CompiladorANTLR\pEm.cu* com o conteudo desejado a compilar

**4.** Executar o arquivo .java no caminho: *Projeto-Compilador\Projeto Compilador\CompiladorANTLR\src\br\edu\ufabc\compilador\Main.java*

**5.** O arquivo compilado de saida estara em: *Carlos Alberto\IdeaProjects\Projeto-Compilador\Projeto Compilador\CompiladorANTLR\ProgramaEm.java

Caso deseje alterar as regras de Gramatica para os analisadores Lexico e Sintatico:
**1.** Deve-se alterar o arquivo gramatica.g, do qual o ANTLR recebe como input e gera classes javas (Analisadores Lexico e Sintatico) para serem incluidas no projeto do nosso compilador;

**2.** Sempre que alterar gramatica.g, rode o ANTLR para gerar novas classes .java e copiar os arquivos para as dependencias do projeto;


## Elementos da Linguagem

- "*declare*"  palavra reservada, que seguida de IDs declara uma serie de variaveis;
- "*escreva( ... )*" :palavra seguida de abre paresentes, algum valor de texto e fecha parenteses. Representa a emissao de texto na tela do usuario;
- "*leia( ...)*": palavra seguida de abre paresentes, algum valor de texto e fecha parenteses. Representa o registro de dados em alguma variavel;
- "*repita( ...)*": Declaracao utilizada para repeticoes do tipo "*for*". Segue o formato **repita(iterador=a; iterador<3; +b ) {    }** em que o primeiro argumento representa a variavel de controle da iteracao, o segundo argumento a condicao de parada e o terceiro argumento representa a operacao aritmetica a cada ciclo;
- "*enquanto( ...){ }*": estrutura de repeticao mais simples, de unico argumento, no qual checa a cada ciclo se a condicao de parada foi atingida. Requisita o uso de chaves;
- "*se( ...)    Senao   ...*": Estrutura simples de condicional, sem capacidade de ElseIfs ou aninhamentos;
- *Operacoes Aritmeticas basicas:* Operacoes cobertas de adicao, subtracao, divisao e multiplicacao;

## Sinopse

Algumas classes e funcoes sao importantes para entendimento do projeto, valendo sua citacao. Pontuemos algumas:

**1.**  **Classe MyLexer:** classe responsavel pelo gerenciamento lexico da compilacao, isto e, a estruturacao do grupo de caracteres de uma entrada em uma forma organizada de *tokens*/palavras;

**2.**  **Classe MyParser:** classe responsavel pela atribuicao de estruturas gramaticais a um conjunto de tokens/palavras, como or exemplo Leitura e Condicionais;

**3.**  **Classe CmdAtribuicao:** transcreve de "*variavel = expressao*" de nossa linguagem para "*variavel = expressao"* em Java, alem de carregar o tipo de dado de nivel mais abstrato da expressao.

**4.**  **Classe CmdEscrita:** transcreve "*Escreva( conteudo )*" de nossa linguagem em *"System.out.println("+conteudo+")"* em Java.

**5.**  **Classe CmdIfElse:** in production

