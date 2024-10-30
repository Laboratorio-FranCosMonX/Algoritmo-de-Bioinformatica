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

</div>
