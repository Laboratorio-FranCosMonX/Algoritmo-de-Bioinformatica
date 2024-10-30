# Algoritmo Smith Waterman (alinhamento global)

Algoritmo de alinhamento (global) para a disciplina de bioinformatica da Universidade Federal do Piaui.

## Implementação

O algoritmo tem duas estapas:
1 - Montar a tabela com os calculos considerando MISS MATCH (-1), GAP (-2) e MATCH (+1).
2 - Backtrace: encontrar o maior valor localizado na ultima linha ou coluna e começar voltar o caminho criado na primeira etapa.

### Matriz

O tamanho da matriz depende das entradas e cada indice da matriz contem um objeto Valor que, por sua vez, contém o valor (inteiro) e uma listas responsável por armazenar as coordenadas (x,y) dos inidices filhos criando, assim, um caminho a ser usado na etapa 2.

<div align="center">
  <img src="./imagens/Captura de tela de 2024-10-29 11-43-26.png" alt="imagem da primeira etapa do algoritmo de smith waterman"> </img>
  <p>Resultado da primeira etapa para a entrada CTTTG (vertical) e GCTT (horizontal).</p>
</div>
