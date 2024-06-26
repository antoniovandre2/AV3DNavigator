# AV3DNavigator
![AV3DNavigator logo](https://antoniovandre2.github.io/AV3DNavigator/AV3DNavigator%20-%20Logo%20-%20200p.png)
____________________

Proprietário: Antonio Vandré Pedrosa Furtunato Gomes ([bit.ly/antoniovandre_legadoontologico](https://bit.ly/antoniovandre_legadoontologico)).

README do "AV3DNavigator".
____________________

Um simples software de navegação 3D, desenvolvido em Java.
_____

Execute com:

java -jar AV3DNavigatorLauncher.jar [ARQUIVO DE ESPAÇO]

O arquivo de espaço consiste em um plain txt composto de, primeiro, linhas separadas por barra vertical "|", cada linha opcionalmente separada pelo token "c", a primeira parte composta dos dois pontos extremos separados por ponto e vírgula ";", e a segunda pelo código RGB separado por vírgulas ",", cada ponto consiste na abscissa, ordenada e cota separadas por vírgula ","; segundo, após o primeiro arroba "@", os vértices dos polígonos preenchidos, polígonos separados por barra vertical "|", "c" também aplicável; e, terceiro, após o segundo arroba "@", as legendas separadas por barra vertical "|", também opcionalmente aplicável primeiramente o código RGB, e, depois, o tamanho da legenda, separados por ponto e vírgula ";".

Há como exemplo o arquivo de espaço "Casa.txt" anexo.

O repositório oficial de espaços é o de link:

"https://github.com/antoniovandre2/AV3DNavigator/tree/main/Espa%C3%A7os".

O repositório de plugins geradores de espaços considerados de maior utilidade é o de link:

"https://github.com/antoniovandre2/AV3DNavigator/tree/main/Plugins".
____________________

Comandos:

F2 para selecionar e abrir arquivo de espaço.

"A" para incrementar x. "Z" para decrementar.

"S" para incrementar y. "X" para decrementar.

"D" para incrementar z. "C" para decrementar.

"F" para incrementar Teta. "V" para decrementar.

"G" para incrementar Phi. "B" para decrementar.

"H" para incrementar a rotação da tela. "N" para decrementar.

"J" para rotação horizontal positiva. "M" para negativa.

Shift + "J" para rotação vertical positiva. Shift + "M" para negativa.

"K" para rotação total positiva. "," para negativa.

"L" para incrementar o raio de rotação horizontal. "." para decrementar.

Shift + "L" para incrementar o raio de rotação vertical. Shift + "." para decrementar.

"\[" para incrementar o raio de rotação total. "\]" para decrementar.

"W" para aumentar a distância da tela. "Q" para reduzir.

"E" para reduzir o fator redutor do ângulo de visão. "R" para aumentar.

"T" para shift negativo na cor padrão da linha. "Y" para shift positivo.

"U" para shift negativo na cor de fundo. "I" para shift positivo.

"O" para shift negativo na cor padrão dos polígonos preenchidos. "P" para shift positivo.

INSERT para shift negativo na cor padrão das legendas. HOME para shift positivo.

DELETE para shift negativo no tamanho padrão das legendas. END para shift positivo.

"-" para shift negativo no offset das legendas. "=" para shift positivo.

Numpad "1" para shift negativo na resolução dos triângulos. Numpad "2" para shift positivo.

PAGE DOWN para shift negativo no sleep time. PAGE UP para shift positivo.

"0" para toggle alta precisão Apfloat (com custo computacional).

"1" para toggle preenchimento dos polígonos com linhas ou fillPolygon.

Setas para strafe. Mouse pode ser utilizado para movimentar.

Barra de espaços para resetar as variáveis.

F11 para setar aspect ratio 1.

F12 para screenshot.

F3 para ocultar e mostrar os labels.

ESC para sair.
____________________

Há suporte para o arquivo "AV3DNavigator.ini", onde se pode já inicializar variáveis ao próprio gosto.
____________________

![AV3D-n logo](https://antoniovandre2.github.io/AV3DNavigator/Powered%20by%20AV3D-n%20engine%20-%20200p.png)
____________________

Ferramentas de terceiros utilizadas:

Apfloat: "http://www.apfloat.org/apfloat_java/".
____________________

Não me responsabilizo por falhas do Java ou do Apfloat.

I am not responsible for Java or Apfloat failures.
____________________

Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
