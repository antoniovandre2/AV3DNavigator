/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de superfície tridimensional por relação (não z).

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da expressão em X e Y, o menor valor atribuído a "X", o maior valor atribuído a "X", o menor valor atribuído a "Y", o maior valor atribuído a "Y", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: "grid" apenas para grid ou "fill" para polígonos preenchidos. 3: a resolução.

Última atualização: 06-03-2025. Sem considerar alterações em variáveis globais.
*/

#include "antoniovandre_eval/antoniovandre.c"

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024

#define MAXRES 100

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
	char fillstring [MAXTAMANHOCAMPO];
	char resstring [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char titulo [MAXTAMANHOCAMPO];
	char expressao [MAXITENS] [MAXTAMANHOCAMPO];
	char menorx [MAXITENS] [MAXTAMANHOCAMPO];
	char maiorx [MAXITENS] [MAXTAMANHOCAMPO];
	char menory [MAXITENS] [MAXTAMANHOCAMPO];
	char maiory [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char rgbs [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	int deslocamentorgb = 50;
	TIPONUMEROREAL menoresx [MAXITENS];
	TIPONUMEROREAL maioresx [MAXITENS];
	TIPONUMEROREAL menoresy [MAXITENS];
	TIPONUMEROREAL maioresy [MAXITENS];
	TIPONUMEROREAL matrizvalores [MAXRES] [MAXRES];
	TIPONUMEROREAL valor;
	char * err;
	char tc;
	char * output;
	char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da expressão em X e Y, o menor valor atribuído a \"X\", o maior valor atribuído a \"X\", o menor valor atribuído a \"Y\", o maior valor atribuído a \"Y\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: \"grid\" apenas para grid ou \"fill\" para polígonos preenchidos. 3: a resolução.\n";
	char * temp;

	int precisao = antoniovandre_precisao_real ();

	if (argc != 4) {printf(mensagemerro); return NUMEROUM;}

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; titulo[i] = '\0'; fillstring[i] = '\0'; resstring[i] = '\0';}

	for (i = NUMEROZERO; i < MAXITENS; i++)
		for (j = NUMEROZERO; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; expressao[i][j] = '\0'; menorx[i][j] = '\0'; maiorx[i][j] = '\0'; menory[i][j] = '\0'; maiory[i][j] = '\0'; rgb[i][j] = '\0'; rgbs[i][j] = '\0';}

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
		fillstring[j++] = argv[2][i];
		}

	if ((strcmp(fillstring, "grid")) && (strcmp(fillstring, "fill")))
		{printf(mensagemerro); return NUMEROUM;}

	j = NUMEROZERO;

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++)
		{
		if (argv[3][i] == '\0') break;
		resstring[j++] = argv[3][i];
		}

	int resolucao = atoi(resstring);

	if (resolucao == NUMEROZERO) {printf(mensagemerro); return NUMEROUM;}
	if (resolucao > MAXRES) {printf("A resolução deve ser menor que %d.", MAXRES + NUMEROUM); return NUMEROUM;}

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
			if ((c != ';') && (c != '\0')) {expressao[argi][j++] = c;} else break;
			} while (VERDADE);

		expressao[argi][j] = '\0';

		k = NUMEROZERO;

		do
			{
			c = item[argi][j + k + NUMEROUM];
			if ((c != ';') && (c != '\0')) {menorx[argi][k++] = c;} else break;
			} while (VERDADE);

		menorx[argi][k] = '\0';

		temp = antoniovandre_eval(menorx[argi], precisao);
		menoresx[argi] = strtod(temp, &err);

		if ((! strcmp(menorx[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		l = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {maiorx[argi][l++] = c;} else break;
			} while (VERDADE);

		maiorx[argi][l] = '\0';

		temp = antoniovandre_eval(maiorx[argi], precisao);
		maioresx[argi] = strtod(temp, &err);

		if ((! strcmp(maiorx[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresx[argi] >= maioresx[argi]) {printf(mensagemerro); return NUMEROUM;}

		m = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {menory[argi][m++] = c;} else break;
			} while (VERDADE);

		menory[argi][m] = '\0';

		temp = antoniovandre_eval(menory[argi], precisao);
		menoresy[argi] = strtod(temp, &err);

		if ((! strcmp(menory[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		n = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {maiory[argi][n++] = c;} else break;
			} while (VERDADE);

		maiory[argi][n] = '\0';

		temp = antoniovandre_eval(maiory[argi], precisao);
		maioresy[argi] = strtod(temp, &err);

		if ((! strcmp(maiory[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresy[argi] >= maioresy[argi]) {printf(mensagemerro); return NUMEROUM;}

		o = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + 5];
			if (c != '\0') {rgb[argi][o++] = c;} else break;
			} while (VERDADE);

		rgb[argi][o] = '\0';

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

		i = NUMEROZERO;
		l = NUMEROZERO;

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

			if (c == ',') l++;

			int rgbi = atoi (verifstr);

			if ((rgbi < 0) || (rgbi > 255))  {printf(mensagemerro); return NUMEROUM;}

			if (rgbi + deslocamentorgb <= 255)
				{
				char tstr [MAXTAMANHOCAMPO];

				for(int k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tstr[k] = '\0';}

				sprintf(tstr, "%d", rgbi + deslocamentorgb);

				if (l == NUMEROUM)
					{
					strcpy(rgbs[argi], tstr);
					strcat(rgbs[argi], ",");
					}
				else if (l == 2)
					{
					strcat(rgbs[argi], tstr);
					strcat(rgbs[argi], ",");
					}
				else
					strcat(rgbs[argi], tstr);
				}
			else
				{
				char tstr [MAXTAMANHOCAMPO];

				sprintf(tstr, "%d", rgbi - deslocamentorgb);

				if (l == NUMEROUM)
					{
					strcpy(rgbs[argi], tstr);
					strcat(rgbs[argi], ",");
					}
				else if (l == 2)
					{
					strcat(rgbs[argi], tstr);
					strcat(rgbs[argi], ",");
					}
				else
					strcat(rgbs[argi], tstr);
				}
			} while (c != '\0');

		if (++argi > MAXITENS) {printf(mensagemerro); return NUMEROUM;}
		} while (flag == NUMEROZERO);



	if (! strcmp(fillstring, "grid"))
		{
		for (i = NUMEROZERO; i < argi; i++)
			for (j = NUMEROZERO; j < resolucao; j++)
				for (k = NUMEROZERO; k < resolucao; k++)
					{
					char valorstr [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) valorstr[m] = '\0';

					char tempstr [MAXTAMANHOCAMPO];

					char pontostrx [MAXTAMANHOCAMPO];
					char pontostry [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) {tempstr[m] = '\0'; pontostrx[m] = '\0'; pontostry[m] = '\0';}

					shift = NUMEROZERO;
					n = NUMEROZERO;

					sprintf(pontostrx, "%Lf", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao);

					sprintf(pontostry, "%Lf", menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao);

					do
						{
						c = expressao[i][shift++];

						if ((c != 'X') && (c != 'Y'))
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
							}
						} while (c != '\0');

					strcat(valorstr, tempstr);

					output = antoniovandre_eval (valorstr, precisao);

					valor = strtold (output, & err);

					if (err == output) {free(output); return NUMEROUM;}

					free(output);

					matrizvalores[j][k] = valor;
					}

		for (i = NUMEROZERO; i < argi; i++)
			for (j = NUMEROZERO; j < resolucao - NUMEROUM; j++)
				for (k = NUMEROZERO; k < resolucao - NUMEROUM; k++)
					{
					printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lfc%s|", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k], menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k], rgb[i]);

					fflush(stdout);

					printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lfc%s|", menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k], menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k + NUMEROUM], rgb[i]);

					fflush(stdout);

					if ((k % 2 == NUMEROZERO) || (k == resolucao - 2))
						{
						printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lfc%s|", menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k + NUMEROUM], menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k + NUMEROUM], rgb[i]);

						fflush(stdout);
						}

					if (j % 2 == NUMEROZERO)
						{
						printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lfc%s|", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k + NUMEROUM], menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k], rgb[i]);

						fflush(stdout);
						}
					}

		printf("@@");
		}

	if (! strcmp(fillstring, "fill"))
		{
		for (i = NUMEROZERO; i < argi; i++)
			for (j = NUMEROZERO; j < resolucao; j++)
				for (k = NUMEROZERO; k < resolucao; k++)
					{
					char valorstr [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) valorstr[m] = '\0';

					char tempstr [MAXTAMANHOCAMPO];

					char pontostrx [MAXTAMANHOCAMPO];
					char pontostry [MAXTAMANHOCAMPO];

					for (m = NUMEROZERO; m < MAXTAMANHOCAMPO; m++) {tempstr[m] = '\0'; pontostrx[m] = '\0'; pontostry[m] = '\0';}

					shift = NUMEROZERO;
					n = NUMEROZERO;

					sprintf(pontostrx, "%Lf", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao);

					sprintf(pontostry, "%Lf", menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao);

					do
						{
						c = expressao[i][shift++];

						if ((c != 'X') && (c != 'Y'))
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
							}
						} while (c != '\0');

					strcat(valorstr, tempstr);

					output = antoniovandre_eval (valorstr, precisao);

					valor = strtold (output, & err);

					if (err == output) {free(output); return NUMEROUM;}

					free(output);

					matrizvalores[j][k] = valor;
					}

		printf("@");

		for (i = NUMEROZERO; i < argi; i++)
			for (j = NUMEROZERO; j < resolucao - NUMEROUM; j++)
				for (k = NUMEROZERO; k < resolucao - NUMEROUM; k++)
					{
					printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf;", menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k], menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + k * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k]);

					fflush(stdout);

					printf("%Lf,%Lf,%Lf;%Lf,%Lf,%Lf", menoresx[i] + (j + NUMEROUM) * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j + NUMEROUM][k + NUMEROUM], menoresx[i] + j * (maioresx[i] - menoresx[i]) / resolucao, menoresy[i] + (k + NUMEROUM) * (maioresy[i] - menoresy[i]) / resolucao, matrizvalores[j][k + NUMEROUM]);

					fflush(stdout);

					if ((j + k) % 2 == NUMEROZERO)
						printf("c%s|", rgb[i]);
					else
						printf("c%s|", rgbs[i]);

					fflush(stdout);
					}

		printf("@");
		}

	printf("%s|", titulo);

	for (i = NUMEROZERO; i < argi; i++)
		printf("Z = %s;%s|", expressao[i], rgb[i]);
	}