/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de função.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "t" para "x", função em "t" para "y", função em "t" para "z", o menor valor atribuído a "t", o maior valor atribuído a "t", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 22-10-2023. Sem considerar alterações em variáveis globais.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "antoniovandre_eval/antoniovandre_windows.c"

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024

#define BUILTIN FALSIDADE

#if BUILTIN == VERDADE

#define EVALSOFTWARE ""
#define EVALSOFTWARETAIL ""
#define CALLEVALSOFTWARE {char * ptreval = antoniovandre_eval(valorstr); printf("%s", ptreval); free (ptreval);}

#define ASPASINICIAL
#define ASPASFINAL

#else

#define EVALSOFTWARE "antoniovandre_eval_windows.exe"
#define EVALSOFTWARETAIL " 0 2>> /dev/null \| tr -d ' ' \| tr -d '\n'"
#define TOKENINICIOEVAL '('
#define TOKENFIMEVAL ')'
#define CALLEVALSOFTWARE system(valorstr);

#define ASPASINICIAL strcat(valorstr, " \"");
#define ASPASFINAL strcat(valorstr, "\"");

#endif

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
    char resstring [MAXTAMANHOCAMPO];
    char titulo [MAXTAMANHOCAMPO];
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaox [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaoy [MAXITENS] [MAXTAMANHOCAMPO];
    char funcaoz [MAXITENS] [MAXTAMANHOCAMPO];
    char menor [MAXITENS] [MAXTAMANHOCAMPO];
    char maior [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double menores [MAXITENS];
    double maiores [MAXITENS];
    char * err;
    char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"t\" para \"x\", função em \"t\" para \"y\", função em \"t\" para \"z\", o menor valor atribuído a \"t\", o maior valor atribuído a \"t\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n";

    if (argc != 3) {printf(mensagemerro); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; resstring[i] = '\0';}

    for (i = 0; i < MAXITENS; i++)
        for (j = 0; j < MAXTAMANHOCAMPO; j++)
            {item[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; funcaoz[i][j] = '\0'; menor[i][j] = '\0'; maior[i][j] = '\0'; rgb[i][j] = '\0';}

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
            if (c != ' ') if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
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
            if ((c != ';') && (c != '\0')) {menor[argi][m++] = c;} else break;
            } while (VERDADE);

        menor[argi][m] = '\0';

        menores[argi] = strtod(menor[argi], &err);

        if ((! strcmp(menor[argi], "")) || (err == menor[argi])) {printf(mensagemerro); return 1;}

        n = 0;

        do
            {
            c = item[argi][j + k + l + m + n + 4];
            if ((c != ';') && (c != '\0')) {maior[argi][n++] = c;} else break;
            } while (VERDADE);

        maior[argi][n] = '\0';

        maiores[argi] = strtod(maior[argi], &err);

        if ((! strcmp(maior[argi], "")) || (err == maior[argi])) {printf(mensagemerro); return 1;}

        if (menores[argi] >= maiores[argi]) {printf(mensagemerro); return 1;}

        o = 0;

        do
            {
            c = item[argi][j + k + l + m + n + o + 5];
            if (c != '\0') {rgb[argi][o++] = c;} else break;
            } while (VERDADE);

        rgb[argi][o] = '\0';

        i = 0;

        do
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
            } while (c != '\0');

        if (++argi > MAXITENS) {printf(mensagemerro); return 1;}
        } while (flag == 0);

    for (i = 0; i < argi; i++)
        for (j = 0; j < resolucao - 1; j++)
            {
            char valorstr [MAXTAMANHOCAMPO];
            char tempstr [MAXTAMANHOCAMPO];
            char pontostr [MAXTAMANHOCAMPO];

            for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostr[k] = '\0';

            sprintf(pontostr, "%f", menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1));

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaox[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf(","); fflush(stdout);

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaoy[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf(","); fflush(stdout);

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaoz[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf(";"); fflush(stdout);

            for (k = 0; k < MAXTAMANHOCAMPO; k++) pontostr[k] = '\0';

            sprintf(pontostr, "%f", menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1));

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaox[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf(","); fflush(stdout);

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaoy[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf(","); fflush(stdout);

            for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

            strcpy(valorstr, EVALSOFTWARE);
            ASPASINICIAL

            for (k = 0; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

            shift = 0;
            k = 0;

            do
                {
                c = funcaoz[i][shift++];

                if (c != 't')
                    {tempstr[k++] = c;}
                else
                    {
                    if (shift == 1)
                        {
                        tempstr[0] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }
                    else
                        {
                        tempstr[strlen(tempstr) - 1] = TOKENINICIOEVAL;
                        strcat(tempstr, pontostr);
                        tempstr[strlen(tempstr) - 1] = TOKENFIMEVAL;
                        }

                    k += strlen(pontostr) + 2;
                    }
                } while (c != '\0');

            tempstr[k] = '\0';

            strcat(valorstr, tempstr);
            ASPASFINAL
            strcat(valorstr, EVALSOFTWARETAIL);

            CALLEVALSOFTWARE fflush(stdout);

            printf("c%s|", rgb[i]); fflush(stdout);
            }

    printf("@@");

    printf("%s|_____|", titulo);

    for (i = 0; i < argi; i++)
        printf("x = %s, y = %s, z = %s;%s|", funcaox[i], funcaoy[i], funcaoz[i], rgb[i]);
    }