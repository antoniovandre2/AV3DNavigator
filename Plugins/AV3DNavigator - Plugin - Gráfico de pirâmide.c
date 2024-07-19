/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de pirâmide tridimensional.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", uma string composta dos item a exibir separados por barra vertical "|", cada item composto do valor e da cor separados por ponto e vírgula ";", a cor RGB com os valores para vermelho, verde e azul separados por vírgula ",". 2: a largura. 3: a altura. 4: a resolução.

Última atualização: 19-07-2024.
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
	int flagdescricao;
	int i;
	int j;
	int k;
	int l;
	int m;
	int n;
	char c;
	int flag = 0;
	char mainstring [MAXTAMANHOCAMPO];
	char lstring [MAXTAMANHOCAMPO];
	char altstring [MAXTAMANHOCAMPO];
	char resstring [MAXTAMANHOCAMPO];
	char titulo [MAXTAMANHOCAMPO];
	char descricao [MAXITENS] [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char valor [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	double max = 0;
	double h;
	double H;
	double valoresnumericos [MAXITENS];
	char * err;
	char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", uma string composta dos item a exibir separados por barra vertical \"|\", cada item composto do valor e da cor separados por ponto e vírgula \";\", a cor RGB com os valores para vermelho, verde e azul separados por vírgula \",\". 2: a largura. 3: a altura. 4: a resolução.\n";

	if (argc != 5) {printf(mensagemerro); return 1;}

	for (i = 0; i < MAXTAMANHOCAMPO; i++)
		{mainstring[i] = '\0'; lstring[i] = '\0'; altstring[i] = '\0'; resstring[i] = '\0';}

	for (i = 0; i < MAXITENS; i++)
		for (j = 0; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; valor[i][j] = '\0'; rgb[i][j] = '\0';}

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
		lstring[j++] = argv[2][i];
		}

	double largura = strtod(lstring, &err);

	if ((! strcmp(lstring, "")) || (err == lstring)) {printf(mensagemerro); return 1;}

	j = 0;

	for (i = 0; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[3][i] == '\0') break;
		altstring[j++] = argv[3][i];
		}

	double altura = strtod(altstring, &err);

	if ((! strcmp(altstring, "")) || (err == altstring)) {printf(mensagemerro); return 1;}

	j = 0;

	for (i = 0; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[4][i] == '\0') break;
		resstring[j++] = argv[4][i];
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
		flagdescricao = 0;

		do
			{
			c = mainstring[shift++ + 1];
			if (c == ';') flagdescricao = 1;
			if (! ((c == ' ') && (flagdescricao == 1))) if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
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

		if ((! strcmp(valor[argi], "")) || (err == valor[argi])) {printf(mensagemerro); return 1;}

		if (valoresnumericos[argi] > max)
			max = valoresnumericos[argi];

		strcat(descricao[argi], " - ");
		strcat(descricao[argi], valor[argi]);

		l = 0;

		do
			{
			c = item[argi][j + k + l + 2];
			if (c != '\0') {rgb[argi][l++] = c;} else break;
			} while (VERDADE);

		rgb[argi][l] = '\0';

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

	H = 3 * largura * valoresnumericos[0] / max / (pow(largura * valoresnumericos[0], 2));

	for (i = 1; i < argi; i++)
		{H += 3 * largura * valoresnumericos[i] / (pow(largura * valoresnumericos[i] / max, 2) + pow(pow(largura * valoresnumericos[i] / max, 2) * pow(largura * valoresnumericos[i-1] / max, 2), 0.5) + pow(largura * valoresnumericos[i-1] / max, 2));}

	max = H;

	printf("@");

	for (i = 0; i < argi; i++)
		{
		if (i == 0) {h = 0; H = 3 * largura * valoresnumericos[i] / (pow(largura * valoresnumericos[i] / max, 2));}
		else {h = H; H += 3 * largura * valoresnumericos[i] / (pow(largura * valoresnumericos[i] / max, 2) + pow(pow(largura * valoresnumericos[i] / max, 2) * pow(largura * valoresnumericos[i-1] / max, 2), 0.5) + pow(largura * valoresnumericos[i-1] / max, 2));}

		for (j = 0; j < resolucao; j++)
			{
			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", largura / 2 * H / max, j / resolucao * largura / 2 * H / max, altura * H / max, largura / 2 * H / max, (j+1) / resolucao * largura / 2 * H / max, altura * H / max, largura / 2 * h / max, (j+1) / resolucao * largura / 2 * h / max, altura * h / max, largura / 2 * h / max, j / resolucao * largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", largura / 2 * H / max, -j / resolucao * largura / 2 * H / max, altura * H / max, largura / 2 * H / max, -(j+1) / resolucao * largura / 2 * H / max, altura * H / max, largura / 2 * h / max, -(j+1) / resolucao * largura / 2 * h / max, altura * h / max, largura / 2 * h / max, -j / resolucao * largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", -largura / 2 * H / max, j / resolucao * largura / 2 * H / max, altura * H / max, -largura / 2 * H / max, (j+1) / resolucao * largura / 2 * H / max, altura * H / max, -largura / 2 * h / max, (j+1) / resolucao * largura / 2 * h / max, altura * h / max, -largura / 2 * h / max, j / resolucao * largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", -largura / 2 * H / max, -j / resolucao * largura / 2 * H / max, altura * H / max, -largura / 2 * H / max, -(j+1) / resolucao * largura / 2 * H / max, altura * H / max, -largura / 2 * h / max, -(j+1) / resolucao * largura / 2 * h / max, altura * h / max, -largura / 2 * h / max, -j / resolucao * largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * h / max, largura / 2 * h / max, altura * h / max, j / resolucao * largura / 2 * h / max, largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", -j / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * h / max, largura / 2 * h / max, altura * h / max, -j / resolucao * largura / 2 * h / max, largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * h / max, -largura / 2 * h / max, altura * h / max, j / resolucao * largura / 2 * h / max, -largura / 2 * h / max, altura * h / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", -j / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * h / max, -largura / 2 * h / max, altura * h / max, -j / resolucao * largura / 2 * h / max, -largura / 2 * h / max, altura * h / max, rgb[i]);

			fflush(stdout);
			}

		for (j = 0; j < resolucao; j++)
			{
			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", j / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, (j+1) / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, j / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, rgb[i]);

			printf("%f,%f,%f;%f,%f,%f;%f,%f,%f;%f,%f,%fc%s|", -j / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * H / max, largura / 2 * H / max, altura * H / max, -(j+1) / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, -j / resolucao * largura / 2 * H / max, -largura / 2 * H / max, altura * H / max, rgb[i]);

			fflush(stdout);
			}
		}

	printf("@");

	printf("%s|_____|", titulo);

	for (i = 0; i < argi; i++)
		printf("%s;%s|", descricao[i], rgb[i]);
	}