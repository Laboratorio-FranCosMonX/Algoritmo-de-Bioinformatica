# Algoritmo Smith Waterman (alinhamento global)

<div align="justify">

Algoritmo de alinhamento (global) de sequências de proteinas para a disciplina de bioinformatica da Universidade Federal do Piaui.

## Implementação

O algoritmo tem duas estapas:
1 - Montar a tabela com os calculos considerando MISS MATCH (-1), GAP (-2) e MATCH (+1).
2 - Backtrace: encontrar o maior valor localizado na ultima linha ou coluna e começar voltar o caminho criado na primeira etapa.

### Matriz

O tamanho da matriz depende das entradas e cada indice da matriz contem um objeto Valor que, por sua vez, contém o valor (inteiro) e uma listas responsável por armazenar as coordenadas (x,y) dos inidices filhos criando, assim, um caminho a ser usado na etapa 2.

<div align="center">
  <img src="./imagens/Captura de tela de 2024-10-29 11-43-26.png" alt="imagem da primeira etapa do algoritmo de smith waterman"> </img>
  <p>Resultado da primeira etapa para a entrada CTTTG (vertical) e GCTT (horizontal). Matriz 6x5.</p>
</div>

### Backtrace

Após a criação da matriz e armazenamento dos dados, é dado inicio a segunda e última etapa, o backtrace. Nada mais é que encontrar o maior valor entre os indices localizados na ultima coluna e os indices da ultima linha e seguir o caminho "de volta" para a origem (x = 0, y = 0). Após isto é pegue a primeira coordenada armazenada na lista de possiveis caminhos e é analisado qual (quais) ácido(s) nucleico(s) deixará(am) de ser visivel(is) (irá para outra linha e/ou coluna). Caso o ácido nucleico deixe de ser visível, ele deverá ser guardado, caso contrário, um gap deve ser colocado no local.
<div align="center">
  <img src="./imagens/Captura de tela 2024-10-30 093412.png" alt="imagem da segunda etapa do algoritmo de smith waterman"> </img>
  <p>Resultado da segunda etapa para a entrada CTTTG (vertical) e GCTT (horizontal).</p>
</div>

Ao encontrar o maior valor, o algoritmo pega a primeira coordenada armazenada no incide e compara os acidos nucleicos dos respectivos indices, como apresentado no seguinte processo.

1. Encontrado o maior valor no indice localizado em x = 5 e y = 4 com valor igual a 1.
2. Pegando a primeira coordenada armazenada na lista de possiveis caminhos. Verifica-se que tanto o ácido nucleico do eixo Y quanto a do eixo X deixaram de ser visto. Logo será armazenado as saídas [T] e [T].
3. A partir da nova coordenada, paga-se a primeira coordenada armazenada na lista de possiveis caminhos, verifica que tanto o ácido nucleico do eixo Y quanto a do eixo X deixaram de ser visto. Logo será armazenado as saídas [TT] e [TT].
4. A partir da nova coordenada, paga-se a primeira coordenada armazenada na lista de possiveis caminhos, verifica que tanto o ácido nucleico do eixo Y quanto a do eixo X deixaram de ser visto. Logo será armazenado as saídas [CTT] e [CTT].
5. A partir da nova coordenada, paga-se a primeira coordenada armazenada na lista de possiveis caminhos, verifica que apenas o ácido nucleico do eixo X deixará de ser visto. Logo será armazenado as saídas [-CTT] e [GCTT].

> Nesta segunda etapa, como está sendo retornado em um caminho previamente criado, a escrita será armazenada de trás para a frente.

## Aplicação

O usuário pode optar por inserir os dados (preencher os campos) contidos na interface, sendo obrigado o preenchimento de todos os campos, para que seja gerado uma tabela e mostrado o resultado do alinhamento (mas não mostra o Score nesta versão) e pode optar por inserir os dados em um arquivo de texto nomeado de `"input.txt"` respeitando a seguinte sintase:
* **1ª Linha:** Vertical;
* **2ª Linha:** Horizontal;
* **3ª Linha:** valor do GAP;
* **4ª Linha:** valor do MISMATCH;
* **5ª Linha:** valor do MATCH;

Em ambos os casos, deverá mostrar uma mensagem do sistema informando se teve êxito ou erro na execução da aplicação.

### Usando a Aplicação

A duas formas de adicionar dados e ver o resultado. A primeira envolve arquivos de entrada e outra de saída e a segunda senvolve os campos da interface que irá gerar uma tabela ao apertar no botão "avançar".

### Instalação

A aplicação foi desenvolvida utilizando o JDK 22 (versão 22), então é necessário que o usuário tenha o JDK na versão 22 instalado em sua máquina. Após isto, é necessário instalar a aplicação que está localizado na raiz deste repositório, chamado de "Smith Waterman - bioinformatica.jar".

#### Utilizando arquivos

Executar a aplicação "Smith Waterman - bioinformatica.jar" lhe mostrará uma interface igual a esta visivel logo a seguir.

<div align="center">
  <img src="./imagens/Captura de tela 2024-11-07 142746.png" alt="imagem contendo os campos de entrada a serem preenchidas pelo o usuário para que o algoritmo possa a ser executado. Contendo dois botões."> </img>
  <p>Interface Visual para preenchimento de dados.</p>
</div>

Para a execução da aplicação é necessário que seja criado, no mesmo diretório que se encontra a aplicação, um arquivo chamado "input.txt" que deve ser preenchido com os dados necessário para a execução do algoritmo. Com isto, basta pressionar o botão "usar arquivo" e logo a seguir será informado se a execução foi bem sucedida ou não.

O resultado deverá ser gravado em um arquivo "saida.txt" que poderá ser encontrado no mesmo diretório onde se encontra a aplicação. O resultado deve ser semelhante ao da imagem a seguir.

<div align="center">
  <img src="./imagens/Captura de tela 2024-11-08 110537.png" alt="resultado para uma execução envolvendo as entradas contidas no arquivo input.txt."> </img>
  <p>Resultado do alinhamento de CGACGCC (vertical) e AACGCA (horizontal) usando -4, -2 e 6 como GAP, MISMATCH e MATCH.</p>
</div>

#### Utilizando a interface

Executar a aplicação "Smith Waterman - bioinformatica.jar" lhe mostrará uma interface igual a esta visivel logo a seguir.

<div align="center">
  <img src="./imagens/Captura de tela 2024-11-07 142746.png" alt="imagem contendo os campos de entrada a serem preenchidas pelo o usuário para que o algoritmo possa a ser executado. Contendo dois botões."> </img>
  <p>Interface Visual para preenchimento de dados.</p>
</div>

A seguir, basta preencher todos os campos corretamente (não foi implementado validação de entradas). A interface apresenta dois campos que recebem string chamados de vertical e horizontal e três campos de inteiros chamados de GAP, MISSMATCH E MATCH.

Ao preencher os campos, basta pressionar o botão "avançar". Com isso, irá apresentar uma nova interface mostrando a tabela (ordenada como se estivesse no 4º quadrante de um plano cartesiano) e o resultado do alinhamento (não informa o Score).

<div align="center">
  <img src="./imagens/Captura de tela 2024-11-08 112254.png" alt="Imagem da interface de preenchimento de dados contendo as entradas CGACGCC (vertical), AACGCA (horizontal), -4 (gap), -2 (mismatch) e 6 (match)."> </img>
  <p>Imagem da interface de preenchimento de dados contendo as entradas CGACGCC (vertical), AACGCA (horizontal), -4 (gap), -2 (mismatch) e 6 (match).</p>
</div>

<div align="center">
  <img src="./imagens/Captura de tela 2024-11-08 103050.png" alt="Resultado do processamento dos dados inseridos nos campos da imagem anterior."> </img>
  <p>Resultado do processamento do algoritmo de acordo com os dados inseridos pelo usuário.</p>
</div>

</div>
