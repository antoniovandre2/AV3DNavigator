/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de pizza tridimensional.

Argumentos: Primeiramente a string título e, após barra vertical "|", uma string composta dos item a exibir separados por barra vertical "|", cada item composto do valor e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",".

Última atualização: 05-10-2023.
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
    int o;
    char c;
    int flag = 0;
    char mainstring [MAXTAMANHOCAMPO];
    char titulo [MAXTAMANHOCAMPO];
    char descricao [MAXITENS] [MAXTAMANHOCAMPO];
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char valor [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double soma = 0;
    double valoresnumericos [MAXITENS];
    int resolucao = 15;
    double raio = 2;
    double espessura = 1;
    double anguloinicial = 0;
    char * err;

    if (argc != 2) {printf("Erro.\n"); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {mainstring[i] = '\0'; titulo[i] = '\0';}

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

        soma += valoresnumericos[argi];

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
        if (i > 0) anguloinicial += 2 * M_PI * valoresnumericos[i - 1] / soma;

        for (j = 0; j < resolucao; j++)
            {
            double x = 0;

            printf("%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, 0, 0, x, raio * cos(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), x, raio * cos(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), rgb[i]);

            x = espessura;

            printf("%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, 0, 0, x, raio * cos(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), x, raio * cos(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), rgb[i]);
            }
        }

    anguloinicial = 0;

    for (i = 0; i < argi; i++)
        {
        if (i > 0) anguloinicial += 2 * M_PI * valoresnumericos[i - 1] / soma;

        for (j = 0; j < resolucao; j++)
            printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", 0, raio * cos(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), espessura, raio * cos(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + j * 2 * M_PI * valoresnumericos[i] / soma / resolucao), espessura, raio * cos(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), 0, raio * cos(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), raio * sin(anguloinicial + (j + 1) * 2 * M_PI * valoresnumericos[i] / soma / resolucao), rgb[i]);
        }

    printf("@");

    printf("%s|_____|", titulo);

    for (i = 0; i < argi; i++)
        printf("%s;%s|", descricao[i], rgb[i]);
    }