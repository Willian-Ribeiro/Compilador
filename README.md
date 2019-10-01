# Projeto Compilador para .java 

**Membros:**

- Willian Ribeiro
- Carlos Alberto
- Daniel Czeresnia

## Breve Descricao

Projeto para o curso de Compiladores, consistindo no Desenvolvimento de um compilador de uma linguagem generica para linguagem Java. Composto de um breve analisador Lexico, *parser* e gerenciador de certos casos de erro, o compilador adere a certos fundamentos de linguagem listados a seguir performando como prototipo para desenvolvimentos futuros.

## Checklist

- [x] possui 2 tipos de variáveis 
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

## Sinopse

Algumas classes e funcoes sao importantes para entendimento do projeto, valendo sua citacao. Pontuemos algumas:
**1.**  **Classe MyLexer:**



## Testes e Exemplos

