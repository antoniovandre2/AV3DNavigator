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

O arquivo de espaço consiste em um plain txt composto de, primeiro, linhas separadas por barra vertical "|", cada linha opcionalmente separada pelo token "c" ou "color", a primeira parte composta dos dois pontos extremos separados por ponto e vírgula ";" ou pelo token "DIVISOR", e a segunda pelo código RGB separado por vírgulas "," ou por tokens "divisor", cada ponto consiste na abscissa, ordenada e cota separadas por vírgula "," ou por "divisor"; segundo, após o primeiro arroba "@", os vértices dos polígonos preenchidos, polígonos separados por barra vertical "|", "c" ou "color" também aplicável; e, terceiro, após o segundo arroba "@", as legendas separadas por barra vertical "|", também opcionalmente aplicável primeiramente o código RGB, e, depois, o tamanho da legenda, separados por ponto e vírgula ";" ou pelo token "DIVISOR". Todo número pode ser substituído por funções em "AV3DNP$", onde $ varia de 0 a 9, correspondente aos parâmetros de 0 a 9, quando nele deseja-se utilizar um valor variável, também pode-se utilizar os tokens "AV3DNPf$", onde $ varia de 0 a 9, para ler os conteúdos dos arquivos "AV3DNParFile$.txt", onde $ varia de 0 a 9, respectivamente, útil para trabalho conjunto com scripts e leituras de APIs, também pode-se utilizar os tokens "AV3DNPTS", "AV3DNPTM" e "AV3DNPTH" para retornar o tempo em segundos, minutos e horas, respectivamente, em todos estes casos deve-se obrigatoriamente utilizar o token "color" ao lugar de "c", o token "divisor" ao lugar de ",", e o token "DIVISOR" ao lugar de ";". Verifique as funções disponíveis no mXparser.

Comentários são permitidos, iniciados com "#". Linhas em branco são ignoradas.

Há como exemplo o arquivo de espaço "Casa.txt" anexo.

O repositório oficial de espaços é o de link:

"https://github.com/antoniovandre2/AV3DNavigator/tree/main/Espa%C3%A7os".

O repositório de plugins geradores de espaços considerados de maior utilidade é o de link:

"https://github.com/antoniovandre2/AV3DNavigator/tree/main/Plugins".
____________________

Há suporte para o arquivo "AV3DNavigator.ini", onde se pode já inicializar variáveis ao próprio gosto.
____________________

![AV3D-n logo](https://antoniovandre2.github.io/AV3DNavigator/Powered%20by%20AV3D-n%20engine%20-%20200p.png) ![apfloat logo](https://antoniovandre2.github.io/AV3DNavigator/Powered%20by%20apfloat%20-%20200p.png) ![mXparser logo](https://antoniovandre2.github.io/AV3DNavigator/Powered%20by%20mXparser%20-%20200p.png)
____________________

Ferramentas de terceiros utilizadas:

Apfloat: "http://www.apfloat.org/apfloat_java/".

mXparser: "https://mathparser.org/"
____________________

Não me responsabilizo por falhas do Java, do mXparser ou do Apfloat.

I am not responsible for Java, mXparser or Apfloat failures.
____________________

Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
