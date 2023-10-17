/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator texto tridimensional.

Argumentos: 1: uma string separada por barras verticais "|", cada campo composto  do texto, do tamanho, da posição z, da profundidade, o espaçamento entre os caracteres, e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",". 2: o arquivo de fontes. 3: a resolução.

Última atualização: 17-10-2023.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAXITENS 10
#define MAXTAMANHOCAMPO 8192
#define VERDADE 1

int main (int argc, char * argv[])
    {
    FILE *fptr;
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
    int r;
    int s;
    int t;
    double x;
    char c;
    int flag = 0;
    char mainstring [MAXTAMANHOCAMPO];
    char arqfontstring [MAXTAMANHOCAMPO];
    char fontstring [MAXTAMANHOCAMPO];
    char resstring [MAXTAMANHOCAMPO];
    char item [MAXITENS] [MAXTAMANHOCAMPO];
    char texto [MAXITENS] [MAXTAMANHOCAMPO];
    char tamanho [MAXITENS] [MAXTAMANHOCAMPO];
    char posz [MAXITENS] [MAXTAMANHOCAMPO];
    char profundidade [MAXITENS] [MAXTAMANHOCAMPO];
    char espacamento [MAXITENS] [MAXTAMANHOCAMPO];
    char rgb [MAXITENS] [MAXTAMANHOCAMPO];
    char verifstr [MAXTAMANHOCAMPO];
    double max = 0;
    double tamanhovaloresnumericos [MAXITENS];
    double poszvaloresnumericos [MAXITENS];
    double profundidadevaloresnumericos [MAXITENS];
    double espacamentovaloresnumericos [MAXITENS];
    char * err;

    char * mensagemerro = "Erro.\n\nArgumentos: 1: uma string separada por barras verticais \"|\", cada campo composto  do texto, do tamanho, da posição z, da profundidade, o espaçamento entre os caracteres, e da cor separados por ponto e vírgula \";\", a cor RGB com os valores para vermelho, verde e azul separados por vírgula \",\". 2: o arquivo de fontes. 3: a resolução.\n";

    char * mensagemerroarquivofontenaoabrir = "Erro.\n\nNão foi possível abrir o arquivo de fontes.\n";

    char * mensagemerroarqfon = "Erro.\n\nArquivo de fontes inválido.\n";

    char * mensagemerroarqfonchar = "\n\nErro.\n\nCaractere em texto não encontrado no arquivo de fontes.\n";

    if (argc != 4) {printf(mensagemerro); return 1;}

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {mainstring[i] = '\0'; arqfontstring[i] = '\0'; fontstring[i] = '\0'; resstring[i] = '\0';}

    for (i = 0; i < MAXITENS; i++)
        for (j = 0; j < MAXTAMANHOCAMPO; j++)
            {item[i][j] = '\0'; texto[i][j] = '\0'; tamanho[i][j] = '\0'; posz[i][j] = '\0'; profundidade[i][j] = '\0'; espacamento[i][j] = '\0'; rgb[i][j] = '\0';}

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
        arqfontstring[j++] = argv[2][i];
        }

    fptr = fopen(arqfontstring, "r");

    if (fptr == NULL) {printf(mensagemerroarquivofontenaoabrir); return 1;}

    fread(fontstring, MAXTAMANHOCAMPO, 1, fptr);

    fclose(fptr);

    j = 0;

    for (i = 0; i < MAXTAMANHOCAMPO; i++)
        {
        if (argv[3][i] == '\0') break;
        resstring[j++] = argv[3][i];
        }

    int resolucao = atoi(resstring);

    if (resolucao == 0) {printf(mensagemerro); return 1;}

    do
        {
        shift = 0;
        i = 0;

        do
            {
            c = mainstring[shift++];
            if (c != ' ') {if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;}
            } while (VERDADE);

        item[argi][i] = '\0';

        if (c == '\0') flag = 1;

        j = 0;

        do
            {
            c = item[argi][j];
            if ((c != ';') && (c != '\0')) {texto[argi][j++] = c;} else break;
            } while (VERDADE);

        texto[argi][j] = '\0';

        k = 0;

        do
            {
            c = item[argi][j + k + 1];
            if ((c != ';') && (c != '\0')) {tamanho[argi][k++] = c;} else break;
            } while (VERDADE);

        tamanho[argi][k] = '\0';

        tamanhovaloresnumericos[argi] = strtod(tamanho[argi], &err);

        if ((! strcmp(tamanho[argi], "")) || (err == tamanho[argi])) {printf(mensagemerro); return 1;}

        l = 0;

        do
            {
            c = item[argi][j + k + l + 2];
            if ((c != ';') && (c != '\0')) {posz[argi][l++] = c;} else break;
            } while (VERDADE);

        posz[argi][l] = '\0';

        poszvaloresnumericos[argi] = strtod(posz[argi], &err);

        if ((! strcmp(posz[argi], "")) || (err == posz[argi])) {printf(mensagemerro); return 1;}

        m = 0;

        do
            {
            c = item[argi][j + k + l + m + 3];
            if ((c != ';') && (c != '\0')) {profundidade[argi][m++] = c;} else break;
            } while (VERDADE);

        profundidade[argi][m] = '\0';

        profundidadevaloresnumericos[argi] = strtod(profundidade[argi], &err);

        if ((! strcmp(profundidade[argi], "")) || (err == profundidade[argi])) {printf(mensagemerro); return 1;}

        n = 0;

        do
            {
            c = item[argi][j + k + l + m + n + 4];
            if ((c != ';') && (c != '\0')) {espacamento[argi][n++] = c;} else break;
            } while (VERDADE);

        espacamento[argi][n] = '\0';

        espacamentovaloresnumericos[argi] = strtod(espacamento[argi], &err);

        if ((! strcmp(espacamento[argi], "")) || (err == espacamento[argi])) {printf(mensagemerro); return 1;}

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

    printf("@");

    double shiftlateral = 0;
    long lengthfontstr = strlen(fontstring);
    long kprocurar = -1;

    for (long I = 0; I < lengthfontstr; I++)
        if ((fontstring[I] == '|') && (fontstring[I+1] == '|') && (fontstring[I+2] == '|'))
            {kprocurar = I + 3; break;}

    if (kprocurar != -1) for (i = 0; i < argi; i++)
        {
        int lengthtexto = strlen(texto[i]);

        for (j = 0; j < lengthtexto; j++)
            {
            int flagfonteencontrada = 0;
            int flagquadrilateros = 0;
            int flagcurvas = 0;
            double max = 0;

            k = kprocurar;

            do
                {
                if ((fontstring[k + 1] != '\0'))
                    {
                    if ((texto[i][j] == fontstring[k]) && (fontstring[k + 1] == ';'))
                        flagfonteencontrada = 1;

                    if ((flagfonteencontrada == 1) && (flagquadrilateros == 0)) if (fontstring[k] == 'q')
                        {
                        flagquadrilateros = 1;

                        char vx1str [MAXTAMANHOCAMPO];
                        char vy1str [MAXTAMANHOCAMPO];
                        char vx2str [MAXTAMANHOCAMPO];
                        char vy2str [MAXTAMANHOCAMPO];
                        char vx3str [MAXTAMANHOCAMPO];
                        char vy3str [MAXTAMANHOCAMPO];
                        char vx4str [MAXTAMANHOCAMPO];
                        char vy4str [MAXTAMANHOCAMPO];

                        do
                            {
                            k += 2;

                            for (l = 0; l < MAXTAMANHOCAMPO; l++)
                                {vx1str[l] = '\0'; vy1str[l] = '\0'; vx2str[l] = '\0'; vy2str[l] = '\0'; vx3str[l] = '\0'; vy3str[l] = '\0'; vx4str[l] = '\0'; vy4str[l] = '\0';}

                            l = 0;

                            do
                                {
                                c = fontstring[k + l];
                                if ((c != ',') && (c != '\0')) {vx1str[l++] = c;} else break;
                                } while (VERDADE);

                            vx1str[l] = '\0';

                            double vx1 = strtod(vx1str, &err);

                            if ((! strcmp(vx1str, "")) || (err == vx1str)) {printf(mensagemerroarqfon); return 1;}

                            m = 0;

                            do
                                {
                                c = fontstring[k + l + m + 1];
                                if ((c != ',') && (c != '\0')) {vy1str[m++] = c;} else break;
                                } while (VERDADE);

                            vy1str[m] = '\0';

                            double vy1 = strtod(vy1str, &err);

                            if ((! strcmp(vy1str, "")) || (err == vy1str)) {printf(mensagemerroarqfon); return 1;}

                            n = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + 2];
                                if ((c != ',') && (c != '\0')) {vx2str[n++] = c;} else break;
                                } while (VERDADE);

                            vx2str[n] = '\0';

                            double vx2 = strtod(vx2str, &err);

                            if ((! strcmp(vx2str, "")) || (err == vx2str)) {printf(mensagemerroarqfon); return 1;}

                            o = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + 3];
                                if ((c != ',') && (c != '\0')) {vy2str[o++] = c;} else break;
                                } while (VERDADE);

                            vy2str[o] = '\0';

                            double vy2 = strtod(vy2str, &err);

                            if ((! strcmp(vy2str, "")) || (err == vy2str)) {printf(mensagemerroarqfon); return 1;}

                            p = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + p + 4];
                                if ((c != ',') && (c != '\0')) {vx3str[p++] = c;} else break;
                                } while (VERDADE);

                            vx3str[p] = '\0';

                            double vx3 = strtod(vx3str, &err);

                            if ((! strcmp(vx3str, "")) || (err == vx3str)) {printf(mensagemerroarqfon); return 1;}

                            q = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + p + q + 5];
                                if ((c != ',') && (c != '\0')) {vy3str[q++] = c;} else break;
                                } while (VERDADE);

                            vy3str[q] = '\0';

                            double vy3 = strtod(vy3str, &err);

                            if ((! strcmp(vy3str, "")) || (err == vy3str)) {printf(mensagemerroarqfon); return 1;}

                            r = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + p + q + r + 6];
                                if ((c != ',') && (c != '\0')) {vx4str[r++] = c;} else break;
                                } while (VERDADE);

                            vx4str[r] = '\0';

                            double vx4 = strtod(vx4str, &err);

                            if ((! strcmp(vx4str, "")) || (err == vx4str)) {printf(mensagemerroarqfon); return 1;}

                            s = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + p + q + r + s + 7];
                                if ((c != ':') && (c != ';') && (c != '|') && (c != '\0')) {vy4str[s++] = c;} else break;
                                } while (VERDADE);

                            vy4str[s] = '\0';

                            double vy4 = strtod(vy4str, &err);

                            if ((! strcmp(vy4str, "")) || (err == vy4str)) {printf(mensagemerroarqfon); return 1;}

                            for (t = 0; t < resolucao - 1; t++)
                                {
                                x = 0;

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, (vx1 + t * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + t * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx2 + t * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + t * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx1 + (t + 1) * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + (t + 1) * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx2 + (t + 1) * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + (t + 1) * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                x = profundidadevaloresnumericos[i];

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, (vx1 + t * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + t * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx2 + t * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + t * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx1 + (t + 1) * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + (t + 1) * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vx2 + (t + 1) * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + (t + 1) * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", 0, (vx1 + t * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + t * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vx1 + t * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + t * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos, (vx1 + (t + 1) * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + (t + 1) * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], 0, (vx1 + (t + 1) * (vx4 - vx1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy1 + (t + 1) * (vy4 - vy1) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", 0, (vx2 + t * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + t * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vx2 + t * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + t * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos, (vx2 + (t + 1) * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + (t + 1) * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], 0, (vx2 + (t + 1) * (vx3 - vx2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + shiftlateral, (vy2 + (t + 1) * (vy3 - vy2) / (resolucao - 1)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                }

                            k += l + m + n + o + p + q + r + s + 6;

                            max = 0; if (max < vx1) max = vx1; if (max < vx2) max = vx2; if (max < vx3) max = vx3; if (max < vx4) max = vx4;
                            } while ((c != ';') && (c != '|') && (c != '\0'));

                        if (c == '|') flagcurvas = 1;
                        }

                    if ((flagfonteencontrada == 1) && (flagcurvas == 0)) if (fontstring[k] == 'c')
                        {
                        flagcurvas = 1;

                        char vcxstr [MAXTAMANHOCAMPO];
                        char vcystr [MAXTAMANHOCAMPO];
                        char vr1str [MAXTAMANHOCAMPO];
                        char vr2str [MAXTAMANHOCAMPO];
                        char vaistr [MAXTAMANHOCAMPO];
                        char vafstr [MAXTAMANHOCAMPO];

                        do
                            {
                            k += 2;

                            for (l = 0; l < MAXTAMANHOCAMPO; l++)
                                {vcxstr[l] = '\0'; vcystr[l] = '\0'; vr1str[l] = '\0'; vr2str[l] = '\0'; vaistr[l] = '\0'; vafstr[l] = '\0';}

                            l = 0;

                            do
                                {
                                c = fontstring[k + l];
                                if ((c != ',') && (c != '\0')) {vcxstr[l++] = c;} else break;
                                } while (VERDADE);

                            vcxstr[l] = '\0';

                            double vcx = strtod(vcxstr, &err);

                            if ((! strcmp(vcxstr, "")) || (err == vcxstr)) {printf(mensagemerroarqfon); return 1;}

                            m = 0;

                            do
                                {
                                c = fontstring[k + l + m + 1];
                                if ((c != ',') && (c != '\0')) {vcystr[m++] = c;} else break;
                                } while (VERDADE);

                            vcystr[m] = '\0';

                            double vcy = strtod(vcystr, &err);

                            if ((! strcmp(vcystr, "")) || (err == vcystr)) {printf(mensagemerroarqfon); return 1;}

                            n = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + 2];
                                if ((c != ',') && (c != '\0')) {vr1str[n++] = c;} else break;
                                } while (VERDADE);

                            vr1str[n] = '\0';

                            double vr1 = strtod(vr1str, &err);

                            if ((! strcmp(vr1str, "")) || (err == vr1str)) {printf(mensagemerroarqfon); return 1;}

                            o = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + 3];
                                if ((c != ',') && (c != '\0')) {vr2str[o++] = c;} else break;
                                } while (VERDADE);

                            vr2str[n] = '\0';

                            double vr2 = strtod(vr2str, &err);

                            if ((! strcmp(vr2str, "")) || (err == vr2str)) {printf(mensagemerroarqfon); return 1;}

                            p = 0;

                            do
                                {
                                c = fontstring[k + l + m + n + o + p + 4];
                                if ((c != ',') && (c != '\0')) {vaistr[p++] = c;} else break;
                                } while (VERDADE);

                            vaistr[p] = '\0';

                            double vai = strtod(vaistr, &err);

                            if ((! strcmp(vaistr, "")) || (err == vaistr)) {printf(mensagemerroarqfon); return 1;}

                            q = 0;

                            do
                                {
                                c = fontstring[k + l + m + n +o + p + q + 5];
                                if ((c != ':') && (c != '|') && (c != '\0')) {vafstr[q++] = c;} else break;
                                } while (VERDADE);

                            vafstr[q] = '\0';

                            double vaf = strtod(vafstr, &err);

                            if ((! strcmp(vafstr, "")) || (err == vafstr)) {printf(mensagemerroarqfon); return 1;}

                            for (t = 0; t < resolucao - 1; t++)
                                {
                                x = 0;

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, (vcx + vr1 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr2 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr2 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr1 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                x = profundidadevaloresnumericos[i];

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", x, (vcx + vr1 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr2 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr2 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], x, (vcx + vr1 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;", 0, (vcx + vr1 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vcx + vr1 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vcx + vr1 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

                                fflush(stdout);

                                printf("%f,%f,%fc%s|", 0, (vcx + vr1 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr1 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);

                                printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;", 0, (vcx + vr2 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vcx + vr2 * cos(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + t * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], profundidadevaloresnumericos[i], (vcx + vr2 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

                                fflush(stdout);

                                printf("%f,%f,%fc%s|", 0, (vcx + vr2 * cos(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + shiftlateral, (vcy + vr2 * sin(vai + (t + 1) * (vaf - vai) / (resolucao - 1))) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

                                fflush(stdout);
                                }

                            k += l + m + n +o + p + q + 4;

                            if (max < vcx + vr1 * cos(vai)) max = vcx + vr1 * cos(vai);
                            if (max < vcx + vr2 * cos(vai)) max = vcx + vr2 * cos(vai);
                            } while ((c != '|') && (c != '\0'));

                        if (c == '|') flagquadrilateros = 1;
                        }
                    }

                k++;
                } while (k < lengthfontstr);

            shiftlateral += espacamentovaloresnumericos[i] + max * tamanhovaloresnumericos[i];

            if (flagfonteencontrada == 0) {printf(mensagemerroarqfonchar); return 1;}
            }
        }
    }