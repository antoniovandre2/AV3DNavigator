# AV3DNavigator
![AV3DNavigator logo](https://antoniovandre2.github.io/AV3DNavigator/AV3DNavigator%20-%20Logo%20-%20200p.png)
____________________

Proprietário: Antonio Vandré Pedrosa Furtunato Gomes ([bit.ly/antoniovandre_legadoontologico](https://bit.ly/antoniovandre_legadoontologico)).

README do "AV3DNavigator".
____________________

Um simples software de navegação 3D, desenvolvido em Java.
_____

Execute com:

java -jar AV3DNavigatorLauncher.jar <ARQUIVO DE ESPAÇO>

O arquivo de espaço consiste em um plain txt composto de, primeiro, linhas separadas por barra vertical "|", cada linha composta dos dois pontos extremos separados por ponto e vírgula ";", cada ponto consiste na abscissa, ordenada e cota separadas por vírgula ","; e, segundo, após o arroba "@", os vértices dos polígonos preenchidos, polígonos separados por barra vertical "|".

Há como exemplo o arquivo de espaço "Casa.txt" anexo.
____________________

Comandos:

"A" para incrementar x. "Z" para decrementar x.

"S" para incrementar y. "X" para decrementar y

"D" para incrementar z. "C" para decrementar z.

"F" para incrementar Teta. "V" para decrementar Teta.

"G" para incrementar Phi. "B" para decrementar Phi.

"W" para aumentar a distância da tela. "Q" para reduzir a distância da tela.

"E" para reduzir o fator redutor do ângulo de visão. "R" para aumentar o fator redutor do ângulo de visão.

"T" para shift negativo na cor da linha. "Y" para shift positivo na cor da linha.

"U" para shift negativo na cor de fundo. "I" para shift positivo na cor de fundo.

"O" para shift negativo na cor dos polígonos preenchidos. "P" para shift positivo na cor dos polígonos preenchidos.

"0" para toggle alta precisão Apfloat (com custo computacional).

Setas para strafe. Mouse pode ser utilizado para movimentar.

Aperte barra de espaços para resetar as variáveis.

ESC para sair.
____________________

Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
