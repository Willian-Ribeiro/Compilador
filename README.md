# Compilador
Desenvolvimento de um compilador para a matéria Compiladores

O projeto foi realizado utilizando Intellij Community, portanto use-o também para já rodar o código sem ter que remover os .java e adaptar para outra IDE

A entrega do projeto final está maracada para dia 23/08

Dúvidas quanto ao Git Hub, acesse: https://rogerdudler.github.io/git-guide/


#################################### PARA USAR O MATERIAL QUE ESTA AQUI:

use uma maquina Linux - facilita a vida pra instalar as coisas
Use intellij community como IDE - pois eh a que estou usando

Baixou tudo? secesso, nao baixou? give your jumps
Para usar o programa basta abrir o arquivo da pasta CompiladorANTLR na IDE

tente rodar o programa, ele deve funcionar com a ultima versao docompilador, e deve dar build successsful
indicando que a linguagem de programacao que estamos criando esta sendo compilada

#################################### PARA FAZER ALTERACOES
deve-se alterar o arquivo gramatica.g, pois o ANTLR o recebe como input e gera classes javas (Analisadores Lexico e Sintatico) para serem incluidas no projeto do nosso compilador

toda vez que alterar gramatica.g, tem que rodar o ANTLR e gerar novas classes java e copiar os arquivos para o projeto

portanto definamos:
PROCESSO_CHATO
{
  rodar o ANTLR na gramatica.g, gerando novas classes java dos analisadores
  as classes java para o projeto
}

para nao encher tanto o saco, criei um BASH script (acho q nao roda em Windows), que faz esse processo todo

Com Intellij, da pra ainda colocar pra executar esse script toda vez antes de executar a aplicacao, nao tendo que ficar se preocupando com o PROCESSO_CHATO

quaisquer duvidas, contatem Carlos Alberto
