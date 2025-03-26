/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator texto tridimensional.

Argumentos: 1: uma string separada por barras verticais "|", cada campo composto  do texto, do tamanho, da posição x, da posição y, da posição z, do ângulo de rotação teta, da profundidade, o espaçamento entre os caracteres, e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",". 2: o arquivo de fontes. 3: a resolução.

Última atualização: 06-08-2024.
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
	long shift = 0;
	long inicio = 0;
	long argi = 0;
	int flagtexto;
	long i;
	long j;
	long k;
	long l;
	long m;
	long n;
	long o;
	long p;
	long q;
	long r;
	long s;
	long t;
	long double p1;
	long double p2;
	char c;
	int flag = 0;
	char mainstring [MAXTAMANHOCAMPO];
	char arqfontstring [MAXTAMANHOCAMPO];
	char fontstring [MAXTAMANHOCAMPO];
	char resstring [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char texto [MAXITENS] [MAXTAMANHOCAMPO];
	char tamanho [MAXITENS] [MAXTAMANHOCAMPO];
	char posx [MAXITENS] [MAXTAMANHOCAMPO];
	char posy [MAXITENS] [MAXTAMANHOCAMPO];
	char posz [MAXITENS] [MAXTAMANHOCAMPO];
	char teta [MAXITENS] [MAXTAMANHOCAMPO];
	char profundidade [MAXITENS] [MAXTAMANHOCAMPO];
	char espacamento [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	long double tamanhovaloresnumericos [MAXITENS];
	long double posxvaloresnumericos [MAXITENS];
	long double posyvaloresnumericos [MAXITENS];
	long double poszvaloresnumericos [MAXITENS];
	long double tetavaloresnumericos [MAXITENS];
	long double profundidadevaloresnumericos [MAXITENS];
	long double espacamentovaloresnumericos [MAXITENS];
	char * err;

	char * mensagemerro = "Erro.\n\nArgumentos: 1: uma string separada por barras verticais \"|\", cada campo composto  do texto, do tamanho, da posicao x, da posição y, da posição z, do ângulo de rotação teta, da profundidade, o espaçamento entre os caracteres, e da cor separados por ponto e vírgula \";\", a cor RGB com os valores para vermelho, verde e azul separados por vírgula \",\". 2: o arquivo de fontes. 3: a resolução.\n";

	char * mensagemerroarquivofontenaoabrir = "Erro.\n\nNão foi possível abrir o arquivo de fontes.\n";

	char * mensagemerroarqfon = "Erro.\n\nArquivo de fontes inválido.\n";

	char * mensagemerroarqfonchar = "\n\nErro.\n\nCaractere em texto não encontrado no arquivo de fontes.\n";

	if (argc != 4) {printf(mensagemerro); return 1;}

	for (i = 0; i < MAXTAMANHOCAMPO; i++)
		{mainstring[i] = '\0'; arqfontstring[i] = '\0'; fontstring[i] = '\0'; resstring[i] = '\0';}

	for (i = 0; i < MAXITENS; i++)
		for (j = 0; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; texto[i][j] = '\0'; tamanho[i][j] = '\0'; posx[i][j] = '\0'; posy[i][j] = '\0'; posz[i][j] = '\0'; teta[i][j] = '\0'; profundidade[i][j] = '\0'; espacamento[i][j] = '\0'; rgb[i][j] = '\0';}

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

	shift = 0;

	do
		{
		i = 0;
		flagtexto = 0;

		do
			{
			c = mainstring[shift++];
			if (c == ';') flagtexto = 1;
			if (! ((c == ' ') && (flagtexto == 1))) if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
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
			if ((c != ';') && (c != '\0')) {posx[argi][l++] = c;} else break;
			} while (VERDADE);

		posx[argi][l] = '\0';

		posxvaloresnumericos[argi] = strtod(posx[argi], &err);

		if ((! strcmp(posx[argi], "")) || (err == posx[argi])) {printf(mensagemerro); return 1;}

		m = 0;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {posy[argi][m++] = c;} else break;
			} while (VERDADE);

		posy[argi][m] = '\0';

		posyvaloresnumericos[argi] = strtod(posy[argi], &err);

		if ((! strcmp(posy[argi], "")) || (err == posy[argi])) {printf(mensagemerro); return 1;}

		n = 0;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {posz[argi][n++] = c;} else break;
			} while (VERDADE);

		posy[argi][n] = '\0';

		poszvaloresnumericos[argi] = strtod(posz[argi], &err);

		if ((! strcmp(posz[argi], "")) || (err == posz[argi])) {printf(mensagemerro); return 1;}

		o = 0;

		do
			{
			c = item[argi][j + k + l + m + n + o + 5];
			if ((c != ';') && (c != '\0')) {teta[argi][o++] = c;} else break;
			} while (VERDADE);

		teta[argi][o] = '\0';

		tetavaloresnumericos[argi] = strtod(teta[argi], &err);

		if ((! strcmp(teta[argi], "")) || (err == teta[argi])) {printf(mensagemerro); return 1;}

		p = 0;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + 6];
			if ((c != ';') && (c != '\0')) {profundidade[argi][p++] = c;} else break;
			} while (VERDADE);

		profundidade[argi][p] = '\0';

		profundidadevaloresnumericos[argi] = strtod(profundidade[argi], &err);

		if ((! strcmp(profundidade[argi], "")) || (err == profundidade[argi])) {printf(mensagemerro); return 1;}

		q = 0;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + q + 7];
			if ((c != ';') && (c != '\0')) {espacamento[argi][q++] = c;} else break;
			} while (VERDADE);

		espacamento[argi][q] = '\0';

		espacamentovaloresnumericos[argi] = strtod(espacamento[argi], &err);

		if ((! strcmp(espacamento[argi], "")) || (err == espacamento[argi])) {printf(mensagemerro); return 1;}

		r = 0;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + q + r + 8];
			if (c != '\0') {rgb[argi][r++] = c;} else break;
			} while (VERDADE);

		rgb[argi][r] = '\0';

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

	long lengthfontstr = strlen(fontstring);
	long kprocurar = -1;

	for (long I = 0; I < lengthfontstr; I++)
		if ((fontstring[I] == '|') && (fontstring[I+1] == '|') && (fontstring[I+2] == '|'))
			{kprocurar = I + 3; break;}

	if (kprocurar != -1) for (i = 0; i < argi; i++)
		{
		long double shiftlateral = 0;
		long double max = 0;
		int lengthtexto = strlen(texto[i]);

		for (j = 0; j < lengthtexto; j++)
			{
			if (texto[i][j] == ' ')
				shiftlateral += espacamentovaloresnumericos[i] + tamanhovaloresnumericos[i];
			else
				{
				int flagfonteencontrada = 0;
				int flagquadrilateros = 0;
				int flagcurvas = 0;

				k = kprocurar;

				do
					{
					if ((fontstring[k + 1] != '\0'))
						{
						if ((fontstring[k - 1] == '|') && (texto[i][j] == fontstring[k]) && (fontstring[k + 1] == ';'))
							flagfonteencontrada = 1;

						if ((flagfonteencontrada == 1) && (flagquadrilateros == 0)) if ((fontstring[k] == 'q') && (fontstring[k - 1] != '|'))
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

								long double vx1 = strtod(vx1str, &err);

								if ((! strcmp(vx1str, "")) || (err == vx1str)) {printf(mensagemerroarqfon); return 1;}

								m = 0;

								do
									{
									c = fontstring[k + l + m + 1];
									if ((c != ',') && (c != '\0')) {vy1str[m++] = c;} else break;
									} while (VERDADE);

								vy1str[m] = '\0';

								long double vy1 = strtod(vy1str, &err);

								if ((! strcmp(vy1str, "")) || (err == vy1str)) {printf(mensagemerroarqfon); return 1;}

								n = 0;

								do
									{
									c = fontstring[k + l + m + n + 2];
									if ((c != ',') && (c != '\0')) {vx2str[n++] = c;} else break;
									} while (VERDADE);

								vx2str[n] = '\0';

								long double vx2 = strtod(vx2str, &err);

								if ((! strcmp(vx2str, "")) || (err == vx2str)) {printf(mensagemerroarqfon); return 1;}

								o = 0;

								do
									{
									c = fontstring[k + l + m + n + o + 3];
									if ((c != ',') && (c != '\0')) {vy2str[o++] = c;} else break;
									} while (VERDADE);

								vy2str[o] = '\0';

								long double vy2 = strtod(vy2str, &err);

								if ((! strcmp(vy2str, "")) || (err == vy2str)) {printf(mensagemerroarqfon); return 1;}

								p = 0;

								do
									{
									c = fontstring[k + l + m + n + o + p + 4];
									if ((c != ',') && (c != '\0')) {vx3str[p++] = c;} else break;
									} while (VERDADE);

								vx3str[p] = '\0';

								long double vx3 = strtod(vx3str, &err);

								if ((! strcmp(vx3str, "")) || (err == vx3str)) {printf(mensagemerroarqfon); return 1;}

								q = 0;

								do
									{
									c = fontstring[k + l + m + n + o + p + q + 5];
									if ((c != ',') && (c != '\0')) {vy3str[q++] = c;} else break;
									} while (VERDADE);

								vy3str[q] = '\0';

								long double vy3 = strtod(vy3str, &err);

								if ((! strcmp(vy3str, "")) || (err == vy3str)) {printf(mensagemerroarqfon); return 1;}

								r = 0;

								do
									{
									c = fontstring[k + l + m + n + o + p + q + r + 6];
									if ((c != ',') && (c != '\0')) {vx4str[r++] = c;} else break;
									} while (VERDADE);

								vx4str[r] = '\0';

								long double vx4 = strtod(vx4str, &err);

								if ((! strcmp(vx4str, "")) || (err == vx4str)) {printf(mensagemerroarqfon); return 1;}

								s = 0;

								do
									{
									c = fontstring[k + l + m + n + o + p + q + r + s + 7];
									if ((c != ':') && (c != ';') && (c != '|') && (c != '\0')) {vy4str[s++] = c;} else break;
									} while (VERDADE);

								vy4str[s] = '\0';

								long double vy4 = strtod(vy4str, &err);

								if ((! strcmp(vy4str, "")) || (err == vy4str)) {printf(mensagemerroarqfon); return 1;}

								for (t = 0; t < resolucao; t++)
									{
									p1 = 0;
									p2 = 0;

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy1 + t * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy2 + t * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy2 + (t + 1) * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									p1 = profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]);

									p2 = profundidadevaloresnumericos[i] * sinl(M_PI_2 + tetavaloresnumericos[i]);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy1 + t * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy2 + t * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vy2 + (t + 1) * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + t * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + t * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + t * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx4 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy4 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

								   printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx1 + t * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + t * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + t * (vy2 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + t * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + t * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + t * (vy2 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx1 + (t + 1) * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy2 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx1 + (t + 1) * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx1 + (t + 1) * (vx2 - vx1) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy1 + (t + 1) * (vy2 - vy1) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy2 + t * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx2 + t * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy2 + t * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy2 + (t + 1) * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx2 + (t + 1) * (vx3 - vx2) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy2 + (t + 1) * (vy3 - vy2) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vx3 + t * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx3 + t * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy3 + t * (vy4 - vy3) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx3 + t * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx3 + t * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy3 + t * (vy4 - vy3) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vx3 + (t + 1) * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx3 + (t + 1) * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy3 + (t + 1) * (vy4 - vy3) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vx3 + (t + 1) * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vx3 + (t + 1) * (vx4 - vx3) / resolucao) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vy3 + (t + 1) * (vy4 - vy3) / resolucao) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);
									}

								k += l + m + n + o + p + q + r + s + 6;

								if (max < vx1) max = vx1; if (max < vx2) max = vx2; if (max < vx3) max = vx3; if (max < vx4) max = vx4;
								} while ((c != ';') && (c != '|') && (c != '\0'));

							if (c == '|') flagcurvas = 1;
							}

						if ((flagfonteencontrada == 1) && (flagcurvas == 0)) if ((fontstring[k] == 'c') && (fontstring[k - 1] != '|'))
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

								long double vcx = strtod(vcxstr, &err);

								if ((! strcmp(vcxstr, "")) || (err == vcxstr)) {printf(mensagemerroarqfon); return 1;}

								m = 0;

								do
									{
									c = fontstring[k + l + m + 1];
									if ((c != ',') && (c != '\0')) {vcystr[m++] = c;} else break;
									} while (VERDADE);

								vcystr[m] = '\0';

								long double vcy = strtod(vcystr, &err);

								if ((! strcmp(vcystr, "")) || (err == vcystr)) {printf(mensagemerroarqfon); return 1;}

								n = 0;

								do
									{
									c = fontstring[k + l + m + n + 2];
									if ((c != ',') && (c != '\0')) {vr1str[n++] = c;} else break;
									} while (VERDADE);

								vr1str[n] = '\0';

								long double vr1 = strtod(vr1str, &err);

								if ((! strcmp(vr1str, "")) || (err == vr1str)) {printf(mensagemerroarqfon); return 1;}

								o = 0;

								do
									{
									c = fontstring[k + l + m + n + o + 3];
									if ((c != ',') && (c != '\0')) {vr2str[o++] = c;} else break;
									} while (VERDADE);

								vr2str[n] = '\0';

								long double vr2 = strtod(vr2str, &err);

								if ((! strcmp(vr2str, "")) || (err == vr2str)) {printf(mensagemerroarqfon); return 1;}

								p = 0;

								do
									{
									c = fontstring[k + l + m + n + o + p + 4];
									if ((c != ',') && (c != '\0')) {vaistr[p++] = c;} else break;
									} while (VERDADE);

								vaistr[p] = '\0';

								long double vai = strtod(vaistr, &err);

								if ((! strcmp(vaistr, "")) || (err == vaistr)) {printf(mensagemerroarqfon); return 1;}

								q = 0;

								do
									{
									c = fontstring[k + l + m + n +o + p + q + 5];
									if ((c != ':') && (c != '|') && (c != '\0')) {vafstr[q++] = c;} else break;
									} while (VERDADE);

								vafstr[q] = '\0';

								long double vaf = strtod(vafstr, &err);

								if ((! strcmp(vafstr, "")) || (err == vafstr)) {printf(mensagemerroarqfon); return 1;}

								for (t = 0; t < resolucao; t++)
									{
									p1 = 0;
									p2 = 0;

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									p1 = profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]);

									p2 = profundidadevaloresnumericos[i] * sinl(M_PI_2 + tetavaloresnumericos[i]);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + p1 + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + p2 + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr1 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + t * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(M_PI_2 + tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + profundidadevaloresnumericos[i] * cosl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i]);

									fflush(stdout);

									printf("%Lf,%Lf,%Lfc%s|", ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * cosl(tetavaloresnumericos[i]) + posxvaloresnumericos[i], ((vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + shiftlateral) * sinl(tetavaloresnumericos[i]) + posyvaloresnumericos[i], (vcy + vr2 * sinl(vai + (t + 1) * (vaf - vai) / resolucao)) * tamanhovaloresnumericos[i] + poszvaloresnumericos[i], rgb[i]);

									fflush(stdout);

									if (max < vcx + vr1 * cosl(vai)) max = vcx + vr1 * cosl(vai);

									if (max < vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao))
										max = vcx + vr1 * cosl(vai + (t + 1) * (vaf - vai) / resolucao);

									if (max < vcx + vr2 * cosl(vai)) max = vcx + vr2 * cosl(vai);

									if (max < vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao))
										max = vcx + vr2 * cosl(vai + (t + 1) * (vaf - vai) / resolucao);
									}

								k += l + m + n +o + p + q + 4;
								} while ((c != '|') && (c != '\0'));

							if (c == '|') flagquadrilateros = 1;
							}
						}

					k++;
					} while (k < lengthfontstr);

				shiftlateral += espacamentovaloresnumericos[i] + max * tamanhovaloresnumericos[i]; max = 0;

				if (flagfonteencontrada == 0) {printf(mensagemerroarqfonchar); return 1;}
				}
			}
		}
	}