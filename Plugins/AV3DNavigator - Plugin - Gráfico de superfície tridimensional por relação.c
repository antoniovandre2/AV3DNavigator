/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de superfície por relação.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta do primeiro membro da igualdade, o segundo membro da igualdade, o menor valor atribuído a "X", o maior valor atribuído a "X", o menor valor atribuído a "Y", o maior valor atribuído a "Y", o menor valor atribuído a "Z", o maior valor atribuído a "Z", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução. 3: a acuidade.

Última atualização: 23-02-2025. Sem considerar alterações em variáveis globais.
*/

#include "antoniovandre_eval/antoniovandre.c"

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024

int main (int argc, char * argv[])
	{
	int shift = NUMEROZERO;
	int inicio = NUMEROZERO;
	int argi = NUMEROZERO;
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
	char c;
	int flag = NUMEROZERO;
	char mainstring [MAXTAMANHOCAMPO];
	char resstring [MAXTAMANHOCAMPO];
	char accuracystring [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char titulo [MAXTAMANHOCAMPO];
	char membro1 [MAXITENS] [MAXTAMANHOCAMPO];
	char membro2 [MAXITENS] [MAXTAMANHOCAMPO];
	char menorx [MAXITENS] [MAXTAMANHOCAMPO];
	char maiorx [MAXITENS] [MAXTAMANHOCAMPO];
	char menory [MAXITENS] [MAXTAMANHOCAMPO];
	char maiory [MAXITENS] [MAXTAMANHOCAMPO];
	char menorz [MAXITENS] [MAXTAMANHOCAMPO];
	char maiorz [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	TIPONUMEROREAL menoresx [MAXITENS];
	TIPONUMEROREAL maioresx [MAXITENS];
	TIPONUMEROREAL menoresy [MAXITENS];
	TIPONUMEROREAL maioresy [MAXITENS];
	TIPONUMEROREAL menoresz [MAXITENS];
	TIPONUMEROREAL maioresz [MAXITENS];
	TIPONUMEROREAL x;
	TIPONUMEROREAL y;
	TIPONUMEROREAL z;
	TIPONUMEROREAL valor;
	char * err;
	char tc;
	char * output;
	char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta do primeiro membro da igualdade, o segundo membro da igualdade, o menor valor atribuído a \"X\", o maior valor atribuído a \"X\", o menor valor atribuído a \"Y\", o maior valor atribuído a \"Y\", o menor valor atribuído a \"Z\", o maior valor atribuído a \"Z\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução. 3: a acuidade.\n";
	char * temp;

	int precisao = antoniovandre_precisao_real ();

	if (argc != 4) {printf(mensagemerro); return NUMEROUM;}

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; titulo[i] = '\0'; resstring[i] = '\0'; accuracystring[i] = '\0';}

	for (i = NUMEROZERO; i < MAXITENS; i++)
		for (j = NUMEROZERO; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; membro1[i][j] = '\0'; membro2[i][j] = '\0'; menorx[i][j] = '\0'; maiorx[i][j] = '\0'; menory[i][j] = '\0'; maiory[i][j] = '\0'; menorz[i][j] = '\0'; maiorz[i][j] = '\0'; rgb[i][j] = '\0';}

	j = NUMEROZERO;

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[NUMEROUM][i] == '\0') break;
		mainstring[j++] = argv[NUMEROUM][i];
		}

	j = NUMEROZERO;

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[2][i] == '\0') break;
		resstring[j++] = argv[2][i];
		}

	int resolucao = atoi(resstring);

	j = NUMEROZERO;

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[3][i] == '\0') break;
		accuracystring[j++] = argv[3][i];
		}

	TIPONUMEROREAL accuracy = strtold(accuracystring, & err);

	if ((! strcmp(accuracystring, "")) || (* err == accuracy)) {printf(mensagemerro); return NUMEROUM;}

	if (resolucao == NUMEROZERO) {printf(mensagemerro); return NUMEROUM;}

	do
		{
		c = mainstring[inicio];
		if ((c != '|') && (c != '\0')) {titulo[inicio++] = c;} else break;
		} while (VERDADE);

	titulo[inicio] = '\0';

	shift = inicio;

	do
		{
		i = NUMEROZERO;

		do
			{
			c = mainstring[shift++ + NUMEROUM];
			if (c != ' ') {if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;}
			} while (VERDADE);

		item[argi][i] = '\0';

		if (c == '\0') flag = NUMEROUM;

		j = NUMEROZERO;

		do
			{
			c = item[argi][j];
			if ((c != ';') && (c != '\0')) {membro1[argi][j++] = c;} else break;
			} while (VERDADE);

		membro1[argi][j] = '\0';

		k = NUMEROZERO;

		do
			{
			c = item[argi][j + k + NUMEROUM];
			if ((c != ';') && (c != '\0')) {membro2[argi][k++] = c;} else break;
			} while (VERDADE);

		membro2[argi][k] = '\0';

		l = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {menorx[argi][l++] = c;} else break;
			} while (VERDADE);

		menorx[argi][l] = '\0';

		temp = antoniovandre_eval(menorx[argi], precisao);
		menoresx[argi] = strtod(temp, &err);

		if ((! strcmp(menorx[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		m = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {maiorx[argi][m++] = c;} else break;
			} while (VERDADE);

		maiorx[argi][m] = '\0';

		temp = antoniovandre_eval(maiorx[argi], precisao);
		maioresx[argi] = strtod(temp, &err);

		if ((! strcmp(maiorx[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresx[argi] >= maioresx[argi]) {printf(mensagemerro); return NUMEROUM;}

		n = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {menory[argi][n++] = c;} else break;
			} while (VERDADE);

		menory[argi][n] = '\0';

		temp = antoniovandre_eval(menory[argi], precisao);
		menoresy[argi] = strtod(temp, &err);

		if ((! strcmp(menory[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		o = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + 5];
			if ((c != ';') && (c != '\0')) {maiory[argi][o++] = c;} else break;
			} while (VERDADE);

		maiory[argi][o] = '\0';

		temp = antoniovandre_eval(maiory[argi], precisao);
		maioresy[argi] = strtod(temp, &err);

		if ((! strcmp(maiory[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresy[argi] >= maioresy[argi]) {printf(mensagemerro); return NUMEROUM;}

		p = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + 6];
			if ((c != ';') && (c != '\0')) {menorz[argi][p++] = c;} else break;
			} while (VERDADE);

		menorz[argi][p] = '\0';

		temp = antoniovandre_eval(menorz[argi], precisao);
		menoresz[argi] = strtod(temp, &err);

		if ((! strcmp(menorz[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		q = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + q + 7];
			if ((c != ';') && (c != '\0')) {maiorz[argi][q++] = c;} else break;
			} while (VERDADE);

		maiorz[argi][q] = '\0';

		temp = antoniovandre_eval(maiorz[argi], precisao);
		maioresz[argi] = strtod(temp, &err);

		if ((! strcmp(maiorz[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresz[argi] >= maioresz[argi]) {printf(mensagemerro); return NUMEROUM;}

		r = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + p + q + r + 8];
			if (c != '\0') {rgb[argi][r++] = c;} else break;
			} while (VERDADE);

		rgb[argi][r] = '\0';

		i = NUMEROZERO;

		do
			{
			j = NUMEROZERO;

			for(int k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {verifstr[k] = '\0';}

			do
				{
				c = rgb[argi][i++];
				if ((c != '\0') && (c != ',')) {verifstr[j++] = c;} else break;
				if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') && (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {printf(mensagemerro); return NUMEROUM;}
				} while (VERDADE);

			if ((atoi (verifstr) < NUMEROZERO) || (atoi (verifstr) > 255))  {printf(mensagemerro); return NUMEROUM;}
			} while (c != '\0');

		if (++argi > MAXITENS) {printf(mensagemerro); return NUMEROUM;}
		} while (flag == NUMEROZERO);

	flag = NUMEROZERO;

	for (i = NUMEROZERO; i < argi; i++)
		for (j = NUMEROZERO; j < resolucao; j++)
			for (k = NUMEROZERO; k < resolucao; k++)
				for (l = NUMEROZERO; l < resolucao; l++)
					{
					char valorstr [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) valorstr[m] = '\0';

					char tempstr [MAXTAMANHOCAMPO];

					char pontostrx [MAXTAMANHOCAMPO];
					char pontostry [MAXTAMANHOCAMPO];
					char pontostrz [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) {tempstr[m] = '\0'; pontostrx[m] = '\0'; pontostry[m] = '\0'; pontostrz[m] = '\0';}

					shift = NUMEROZERO;
					n = NUMEROZERO;

					sprintf(pontostrx, "%Lf", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao);

					sprintf(pontostry, "%Lf", menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao);

					sprintf(pontostrz, "%Lf", menoresz[i] + l * (maioresz[i] - menoresz[i]) / resolucao);

					do
						{
						c = membro1[i][shift++];

						if ((c != 'X') && (c != 'Y') && (c != 'Z'))
							{tempstr[n++] = c;}
						else
							{
							if (c == 'X')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostrx);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostrx) + 2;
								}
							else if (c == 'Y')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostry);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostry) + 2;
								}
							else if (c == 'Z')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostrz);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostrz) + 2;
								}
							}
						} while (c != '\0');

					tc = TOKENINICIOEVAL; strncat(valorstr, & tc, NUMEROUM);
					strcat(valorstr, tempstr);
					tc = TOKENFIMEVAL; strncat(valorstr, & tc, NUMEROUM);
					tc = '-'; strncat(valorstr, & tc, NUMEROUM);
					tc = TOKENINICIOEVAL; strncat(valorstr, & tc, NUMEROUM);

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) tempstr[m] = '\0';

					shift = NUMEROZERO;
					n = NUMEROZERO;

					do
						{
						c = membro2[i][shift++];

						if ((c != 'X') && (c != 'Y') && (c != 'Z'))
							{tempstr[n++] = c;}
						else
							{
							if (c == 'X')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostrx);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostrx) + 2;
								}
							else if (c == 'Y')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostry);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostry) + 2;
								}
							else if (c == 'Z')
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostrz);
								tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

								n += strlen(pontostrz) + 2;
								}
							}
						} while (c != '\0');

					strcat(valorstr, tempstr);
					tc = TOKENFIMEVAL; strncat(valorstr, & tc, NUMEROUM);

					output = antoniovandre_eval (valorstr, precisao);

					valor = strtold (output, & err);

					free(output);

					if (err != temp)
						{
						if (fabsl(valor) < accuracy)
							{
							if (flag == NUMEROZERO)
								{
								x = menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao;
								y = menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao;
								z = menoresz[i] + l * (maioresz[i] - menoresz[i]) / resolucao;
								flag = NUMEROUM;
								}
							}
						else
							{
							if (flag == NUMEROUM)
								{
								printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lfc%s|", (x + menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao) / 2, (y - menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao) / 2, (z - menoresz[i] + (l - NUMEROUM) * (maioresz[i] - menoresz[i]) / resolucao) / 2, (x + menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao) / 2, (y - menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao) / 2, (z - menoresz[i] + (l - NUMEROUM) * (maioresz[i] - menoresz[i]) / resolucao) / 2, rgb[i]);

								fflush(stdout);

								flag = NUMEROZERO;
								}
							}
						}
					}

	printf("@@");

	printf("%s|", titulo);

	for (i = NUMEROZERO; i < argi; i++)
		printf("%s = %s;%s|", membro1[i], membro2[i], rgb[i]);
	}