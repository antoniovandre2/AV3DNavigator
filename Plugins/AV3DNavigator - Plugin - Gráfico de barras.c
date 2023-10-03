/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de barras tridimensional.

Argumentos: Uma string composta dos item a exibir separados por barra vertical "|", cada item composto do valor e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",".

Última atualização: 03-10-2023.
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
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char valor [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double soma = 0;
    double valoresnumericos [MAXITENS];
    double largura = 1;
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

        if ((argv[1][i] == '0') || (argv[1][i] == '1') || (argv[1][i] == '2') || (argv[1][i] == '3') || (argv[1][i] == '4') || (argv[1][i] == '5') || (argv[1][i] == '6') || (argv[1][i] == '7') || (argv[1][i] == '8') || (argv[1][i] == '9') || (argv[1][i] == '.') || (argv[1][i] == ',') || (argv[1][i] == ';') || (argv[1][i] == '|'))
            {mainstring[j++] = argv[1][i];}
        else if (argv[1][i] != ' ') {printf("Erro.\n"); return 1;}
        }

    do
        {
        i = 0;

        do
            {
            c = mainstring[shift++];
            if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
            } while (VERDADE);

        item[argi][i] = '\0';

        if (c == '\0') flag = 1;

        j = 0;

        do
            {
            c = item[argi][j];
            if ((c != ';') && (c != '\0')) {valor[argi][j++] = c;} else break;
            } while (VERDADE);

        valor[argi][j] = '\0';

        valoresnumericos[argi] = strtod(valor[argi], &err);

        if (err == valor[argi]) {printf("Erro.\n"); return 1;}

        soma += valoresnumericos[argi];

        k = 0;

        do
            {
            c = item[argi][j + k + 1];
            if (c != '\0') {rgb[argi][k++] = c;} else break;
            } while (VERDADE);

        rgb[argi][k] = '\0';

        l = 0;

        for (n = 1; n <= 3; n++)
            {
            m = 0;

            for(int o = 0; o < MAXTAMANHOCAMPO; o++) {verifstr[n] = '\0';}

            do
                {
                c = rgb[argi][l++];
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
        printf("%f,%f,%f,;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", 0, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), 0, 0, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + largura), 0, 0, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2 + largura), altura * valoresnumericos[i] / soma, 0, -(i * (espacamento + largura) - (largura * argi + espacamento * (argi - 1)) / 2), altura * valoresnumericos[i] / soma, rgb[i]);
        }
    }