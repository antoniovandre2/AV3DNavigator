/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de função.

Argumentos: Primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "x", o menor menor atribuído a "x", o maior menor atribuído a "x", o ponto de exclusão no intervalo, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",".

Última atualização: 10-10-2023.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024
#define VERDADE 1

#define EVALSOFTWARE "antoniovandre_eval_windows.exe"
#define EVALSOFTWARETAIL " 2>> /dev/null \| tr -d '\n'"
#define TOKENINICIOEVAL "("
#define TOKENFIMEVAL ")"

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
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char funcao [MAXITENS] [MAXTAMANHOCAMPO];
    char menor [MAXITENS] [MAXTAMANHOCAMPO];
    char maior [MAXITENS] [MAXTAMANHOCAMPO];
    char exclusao [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double menores [MAXITENS];
    double maiores [MAXITENS];
    double exclusoes [MAXITENS];
    int resolucao = 10;
    char * err;

    if (argc != 2) {printf("Erro.\n"); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        mainstring[i] = '\0';

    for (i = 0; i < MAXITENS; i++)
        for (j = 0; j < MAXTAMANHOCAMPO; j++)
            {item[i][j] = '\0'; funcao[i][j] = '\0'; menor[i][j] = '\0'; maior[i][j] = '\0'; exclusao[i][j] = '\0'; rgb[i][j] = '\0';}

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
            if ((c != ';') && (c != '\0')) {funcao[argi][j++] = c;} else break;
            } while (VERDADE);

        funcao[argi][j] = '\0';

        k = 0;

        do
            {
            c = item[argi][j + k + 1];
            if ((c != ';') && (c != '\0')) {menor[argi][k++] = c;} else break;
            } while (VERDADE);

        menor[argi][k] = '\0';

        menores[argi] = strtod(menor[argi], &err);

        if ((! strcmp(menor[argi], "")) || (err == menor[argi])) {printf("Erro.\n"); return 1;}

        l = 0;

        do
            {
            c = item[argi][j + k + l + 2];
            if ((c != ';') && (c != '\0')) {maior[argi][l++] = c;} else break;
            } while (VERDADE);

        maior[argi][l] = '\0';

        maiores[argi] = strtod(maior[argi], &err);

        if ((! strcmp(maior[argi], "")) || (err == maior[argi])) {printf("Erro.\n"); return 1;}

        if (menores[argi] >= maiores[argi]) {printf("Erro.\n"); return 1;}

        m = 0;

        do
            {
            c = item[argi][j + k + l + m + 3];
            if ((c != ';') && (c != '\0')) {exclusao[argi][m++] = c;} else break;
            } while (VERDADE);

        exclusao[argi][m] = '\0';

        if (strlen(exclusao[argi]) != 0)
            {
            exclusoes[argi] = strtod(exclusao[argi], &err);

            if ((! strcmp(exclusao[argi], "")) || (err == exclusao[argi])) {printf("Erro.\n"); return 1;}

            if ((exclusoes[argi] < menores[argi]) || (exclusoes[argi] > maiores[argi])) {printf("Erro.\n"); return 1;}
            }

        n = 0;

        do
            {
            c = item[argi][j + k + l + m + n + 4];
            if (c != '\0') {rgb[argi][n++] = c;} else break;
            } while (VERDADE);

        rgb[argi][n] = '\0';

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

    for (i = 0; i < argi; i++)
        for (j = 0; j < resolucao - 1; j++)
            if ((strlen(exclusao[i]) == 0) || (! ((exclusoes[i] >= menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1)) && (exclusoes[i] <= menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1)))))
                {
                char valorstr [MAXTAMANHOCAMPO];

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                printf("0,%f,", menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1));

                fflush(stdout);

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                char tempstr [MAXTAMANHOCAMPO];
                char pontostr [MAXTAMANHOCAMPO];

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

                shift = 0;
                k = 0;

                sprintf(pontostr, "%f", menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1));

                do
                    {
                    c = funcao[i][shift++];

                    if (c != 'x')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostr);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostr);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostr) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                strcat(valorstr, tempstr);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr);

                fflush(stdout);

                printf(";0,%f,", menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1));

                fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

                shift = 0;
                k = 0;

                sprintf(pontostr, "%f", menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1));

                do
                    {
                    c = funcao[i][shift++];

                    if (c != 'x')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostr);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostr);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostr) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                strcat(valorstr, tempstr);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr);

                fflush(stdout);

                printf("c%s|", rgb[i]);

                fflush(stdout);
                }

    printf("@@");

    printf("%s|_____|", titulo);

    for (i = 0; i < argi; i++)
        printf("%s;%s|", funcao[i], rgb[i]);
    }