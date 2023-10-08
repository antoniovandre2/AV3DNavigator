/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de barras tridimensional.

Argumentos: Primeiramente a string título e, após barra vertical "|", uma string composta dos item a exibir separados por barra vertical "|", cada item composto do valor e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",".

Última atualização: 08-10-2023.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024
#define VERDADE 1

int main (int argc, char * argv[])
    {
    int shift = 0;
    int inicio = 0;
    int argi = 0;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    char c;
    int flag = 0;
    char mainstring [MAXTAMANHOCAMPO];
    char titulo [MAXTAMANHOCAMPO];
    char descricao [MAXITENS] [MAXTAMANHOCAMPO];
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char valor [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double max = 0;
    double valoresnumericos [MAXITENS];
    int resolucao = 10;
    double largura = 1;
    double profundidade = 1;
    double espacamento = 0.5;
    double altura = 10;
    char * err;

    if (argc != 2) {printf("Erro.\n"); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        mainstring[i] = '\0';

    for (i = 0; i < MAXITENS; i++)
        for (j = 0; j < MAXTAMANHOCAMPO; j++)
            {item[i][j] = '\0'; valor[i][j] = '\0'; rgb[i][j] = '\0';}

    j = 0;

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {
        if (argv[1][i] == '\0') break;
        mainstring[j++] = argv[1][i];
        }

    do
        {
        c = mainstring[inicio];
        if ((c != '|') && (c != '\0')) {titulo[inicio++] = c;} else break;
        } while (VERDADE);

    titulo[inicio] = '\0';

    shift = inicio;

    do
        {
        i = 0;

        do
            {
            c = mainstring[shift++ + 1];
            if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
            } while (VERDADE);

        item[argi][i] = '\0';

        if (c == '\0') flag = 1;

        j = 0;

        do
            {
            c = item[argi][j];
            if ((c != ';') && (c != '\0')) {descricao[argi][j++] = c;} else break;
            } while (VERDADE);

        descricao[argi][j] = '\0';

        k = 0;

        do
            {
            c = item[argi][j + k + 1];
            if ((c != ';') && (c != '\0')) {valor[argi][k++] = c;} else break;
            } while (VERDADE);

        valor[argi][k] = '\0';

        valoresnumericos[argi] = strtod(valor[argi], &err);

        if ((! strcmp(valor[argi], "")) || (err == valor[argi])) {printf("Erro.\n"); return 1;}

        if (valoresnumericos[argi] > max)
            max = valoresnumericos[argi];

        l = 0;

        do
            {
            c = item[argi][j + k + l + 2];
            if (c != '\0') {rgb[argi][l++] = c;} else break;
            } while (VERDADE);

        rgb[argi][l] = '\0';

        for (n = 1; n <= 3; n++)
            {
            m = 0;

            for(int p = 0; p < MAXTAMANHOCAMPO; p++) {verifstr[p] = '\0';}

            do
                {
                c = rgb[argi][n++];
                if ((c != '\0') && (c != ',')) {verifstr[m++] = c;} else break;
                if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') && (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {printf("Erro.\n"); return 1;}
                } while (VERDADE);

            if ((atoi (verifstr) < 0) || (atoi (verifstr) > 255))  {printf("Erro.\n"); return 1;}
            }

        if (++argi > MAXITENS) {printf("Erro.\n"); return 1;}
        } while (flag == 0);

    printf("@");

    for (i = 0; i < argi; i++)
        {
        for (j = 0; j < resolucao - 1; j++)
            {
            double x = 0;

            printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + j * largura / (resolucao - 1)), 0, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + (j + 1) * largura / (resolucao - 1)), 0, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + (j + 1) * largura / (resolucao - 1)), altura * valoresnumericos[i] / max, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + j * largura / (resolucao - 1)), altura * valoresnumericos[i] / max, rgb[i]);

            x = profundidade;

            printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + j * largura / (resolucao - 1)), 0, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + (j + 1) * largura / (resolucao - 1)), 0, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + (j + 1) * largura / (resolucao - 1)), altura * valoresnumericos[i] / max, x, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + j * largura / (resolucao - 1)), altura * valoresnumericos[i] / max, rgb[i]);
            }

        for (j = 0; j < resolucao - 1; j++)
            {
            double y = 0;

            printf("%f,%f,%f,;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), 0, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), 0, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), altura * valoresnumericos[i] / max, j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), altura * valoresnumericos[i] / max, rgb[i]);

            y = largura;

            printf("%f,%f,%f,;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), 0, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), 0, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), altura * valoresnumericos[i] / max, j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + y), altura * valoresnumericos[i] / max, rgb[i]);
            }
        }

        for (i = 0; i < argi; i++) for (j = 0; j < resolucao - 1; j++)
            {
            double z = 0;

            printf("%f,%f,%f,;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + largura), z, j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, rgb[i]);

            z = altura * valoresnumericos[i] / max;

            printf("%f,%f,%f,;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, (j + 1) * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + largura), z, j * profundidade / (resolucao - 1), -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), z, rgb[i]);
            }

    printf("@");

    printf("%s|_____|", titulo);

    for (i = 0; i < argi; i++)
        printf("%s;%s|", descricao[i], rgb[i]);
    }