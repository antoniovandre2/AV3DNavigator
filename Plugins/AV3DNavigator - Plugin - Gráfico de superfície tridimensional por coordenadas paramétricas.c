/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de função.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "u" e "v" para "x", função em "u" e "v" para "y", função em "u" e "v" para "z", o menor valor atribuído a "u", o maior valor atribuído a "u", o menor valor atribuído a "v", o maior valor atribuído a "v", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 12-10-2023. Sem considerar alterações em variáveis globais.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024
#define VERDADE 1

#define EVALSOFTWARE "antoniovandre_eval_windows.exe"
#define EVALSOFTWARETAIL " 0 2>> /dev/null \| tr -d '\n'"
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
    int o;
    int p;
    int q;
    char c;
    int flag = 0;
    char mainstring [MAXTAMANHOCAMPO];
    char resstring [MAXTAMANHOCAMPO];
    char titulo [MAXTAMANHOCAMPO];
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaox [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaoy [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaoz [MAXITENS] [MAXTAMANHOCAMPO];
    char menoru [MAXITENS] [MAXTAMANHOCAMPO];
    char maioru [MAXITENS] [MAXTAMANHOCAMPO];
    char menorv [MAXITENS] [MAXTAMANHOCAMPO];
    char maiorv [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double menoresu [MAXITENS];
    double maioresu [MAXITENS];
    double menoresv [MAXITENS];
    double maioresv [MAXITENS];
    char * err;
    char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"u\" e \"v\" para \"x\", função em \"u\" e \"v\" para \"y\", função em \"u\" e \"v\" para \"z\", o menor valor atribuído a \"u\", o maior valor atribuído a \"u\", o menor valor atribuído a \"v\", o maior valor atribuído a \"v\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n";

    if (argc != 3) {printf(mensagemerro); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; resstring[i] = '\0';}

    for (i = 0; i < MAXITENS; i++)
        for (j = 0; j < MAXTAMANHOCAMPO; j++)
            {item[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; funcaoz[i][j] = '\0'; menoru[i][j] = '\0'; maioru[i][j] = '\0'; menorv[i][j] = '\0'; maiorv[i][j] = '\0'; rgb[i][j] = '\0';}

    j = 0;

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {
        if (argv[1][i] == '\0') break;
        mainstring[j++] = argv[1][i];
        }

    j = 0;

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {
        if (argv[2][i] == '\0') break;
        resstring[j++] = argv[2][i];
        }

    int resolucao = atoi(resstring);

    if (resolucao == 0) {printf(mensagemerro); return 1;}

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
            if (c != ' ') {if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;}
            } while (VERDADE);

        item[argi][i] = '\0';

        if (c == '\0') flag = 1;

        j = 0;

        do
            {
            c = item[argi][j];
            if ((c != ';') && (c != '\0')) {funcaox[argi][j++] = c;} else break;
            } while (VERDADE);

        funcaox[argi][j] = '\0';

        k = 0;

        do
            {
            c = item[argi][k + j + 1];
            if ((c != ';') && (c != '\0')) {funcaoy[argi][k++] = c;} else break;
            } while (VERDADE);

        funcaoy[argi][k] = '\0';

        l = 0;

        do
            {
            c = item[argi][j + k + l + 2];
            if ((c != ';') && (c != '\0')) {funcaoz[argi][l++] = c;} else break;
            } while (VERDADE);

        funcaoz[argi][l] = '\0';

        m = 0;

        do
            {
            c = item[argi][j + k + l + m + 3];
            if ((c != ';') && (c != '\0')) {menoru[argi][m++] = c;} else break;
            } while (VERDADE);

        menoru[argi][m] = '\0';

        menoresu[argi] = strtod(menoru[argi], &err);

        if ((! strcmp(menoru[argi], "")) || (err == menoru[argi])) {printf(mensagemerro); return 1;}

        n = 0;

        do
            {
            c = item[argi][j + k + l + m + n + 4];
            if ((c != ';') && (c != '\0')) {maioru[argi][n++] = c;} else break;
            } while (VERDADE);

        maioru[argi][n] = '\0';

        maioresu[argi] = strtod(maioru[argi], &err);

        if ((! strcmp(maioru[argi], "")) || (err == maioru[argi])) {printf(mensagemerro); return 1;}

        if (menoresu[argi] >= maioresu[argi]) {printf(mensagemerro); return 1;}

        o = 0;

        do
            {
            c = item[argi][j + k + l + m + n + o + 5];
            if ((c != ';') && (c != '\0')) {menorv[argi][o++] = c;} else break;
            } while (VERDADE);

        menorv[argi][o] = '\0';

        menoresv[argi] = strtod(menorv[argi], &err);

        if ((! strcmp(menorv[argi], "")) || (err == menorv[argi])) {printf(mensagemerro); return 1;}

        p = 0;

        do
            {
            c = item[argi][j + k + l + m + n + o + p + 6];
            if ((c != ';') && (c != '\0')) {maiorv[argi][p++] = c;} else break;
            } while (VERDADE);

        maiorv[argi][p] = '\0';

        maioresv[argi] = strtod(maiorv[argi], &err);

        if ((! strcmp(maiorv[argi], "")) || (err == maiorv[argi])) {printf(mensagemerro); return 1;}

        if (menoresv[argi] >= maioresv[argi]) {printf(mensagemerro); return 1;}

        q = 0;

        do
            {
            c = item[argi][j + k + l + m + n + o + p + q + 7];
            if (c != '\0') {rgb[argi][q++] = c;} else break;
            } while (VERDADE);

        rgb[argi][q] = '\0';

        for (i = 1; i <= 3; i++)
            {
            j = 0;

            for(int k = 0; k < MAXTAMANHOCAMPO; k++) {verifstr[k] = '\0';}

            do
                {
                c = rgb[argi][i++];
                if ((c != '\0') && (c != ',')) {verifstr[j++] = c;} else break;
                if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') && (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {printf(mensagemerro); return 1;}
                } while (VERDADE);

            if ((atoi (verifstr) < 0) || (atoi (verifstr) > 255))  {printf(mensagemerro); return 1;}
            }

        if (++argi > MAXITENS) {printf(mensagemerro); return 1;}
        } while (flag == 0);

    for (i = 0; i < argi; i++)
         for (l = 0; l < resolucao; l++)
            {
            char valorstr [MAXTAMANHOCAMPO];
            char tempstr [MAXTAMANHOCAMPO];
            char tempstr2 [MAXTAMANHOCAMPO];
            char pontostru [MAXTAMANHOCAMPO];
            char pontostrv [MAXTAMANHOCAMPO];

            for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

            sprintf(pontostrv, "%f", menoresv[i] + l * (maioresv[i] - menoresv[i]) / resolucao);

            for (j = 0; j < resolucao - 1; j++)
                {
                for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

                sprintf(pontostru, "%f", menoresu[i] + j * (maioresu[i] - menoresu[i]) / (resolucao - 1));

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaox[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoy[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoz[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(";"); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

                sprintf(pontostru, "%f", menoresu[i] + (j + 1) * (maioresu[i] - menoresu[i]) / (resolucao - 1));

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaox[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoy[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoz[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf("c%s|", rgb[i]); fflush(stdout);
                }
            }

    for (i = 0; i < argi; i++)
         for (l = 0; l < resolucao; l++)
            {
            char valorstr [MAXTAMANHOCAMPO];
            char tempstr [MAXTAMANHOCAMPO];
            char tempstr2 [MAXTAMANHOCAMPO];
            char pontostru [MAXTAMANHOCAMPO];
            char pontostrv [MAXTAMANHOCAMPO];

            for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

            sprintf(pontostru, "%f", menoresu[i] + l * (maioresu[i] - menoresu[i]) / (resolucao - 1));

            for (j = 0; j < resolucao - 1; j++)
                {
                for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

                sprintf(pontostrv, "%f", menoresv[i] + j * (maioresv[i] - menoresv[i]) / resolucao);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaox[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoy[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoz[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(";"); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

                sprintf(pontostrv, "%f", menoresv[i] + (j + 1) * (maioresv[i] - menoresv[i]) / resolucao);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaox[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoy[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf(","); fflush(stdout);

                for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

                strcpy(valorstr, EVALSOFTWARE);
                strcat(valorstr, " \"");

                for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

                shift = 0;
                k = 0;

                do
                    {
                    c = funcaoz[i][shift++];

                    if (c != 'u')
                        {tempstr[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr, TOKENINICIOEVAL);
                            strcat(tempstr, pontostru);
                            strcat(tempstr, TOKENFIMEVAL);
                            }

                        k += strlen(pontostru) + 2;
                        }
                    } while (c != '\0');

                tempstr[k] = '\0';

                shift = 0;
                k = 0;

                do
                    {
                    c = tempstr[shift++];

                    if (c != 'v')
                        {tempstr2[k++] = c;}
                    else
                        {
                        if (shift == 1)
                            {
                            strcpy(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }
                        else
                            {
                            strcat(tempstr2, TOKENINICIOEVAL);
                            strcat(tempstr2, pontostrv);
                            strcat(tempstr2, TOKENFIMEVAL);
                            }

                        k += strlen(pontostrv) + 2;
                        }
                    } while (c != '\0');

                tempstr2[k] = '\0';

                strcat(valorstr, tempstr2);
                strcat(valorstr, "\"");
                strcat(valorstr, EVALSOFTWARETAIL);

                system(valorstr); fflush(stdout);

                printf("c%s|", rgb[i]); fflush(stdout);
                }
            }


    printf("@@");

    printf("%s|_____|", titulo);

    for (i = 0; i < argi; i++)
        printf("x = %s, y = %s, z = %s;%s|", funcaox[i], funcaoy[i], funcaoz[i], rgb[i]);
    }