/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de função em coordenadas polares.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "U", o menor valor atribuído a "U", o maior valor atribuído a "U", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 31-06-2024. Sem considerar alterações em variáveis globais.
*/

#include "antoniovandre_eval/antoniovandre.c"

#define BUILTIN VERDADE

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024
#define VERDADE 1

#define EVALSOFTWARE "antoniovandre_eval"
#define EVALSOFTWARETAIL " 0 2>> /dev/null \| tr -d ' ' \| tr -d '\n'"

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
	char funcao [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaox [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaoy [MAXITENS] [MAXTAMANHOCAMPO];
	char menor [MAXITENS] [MAXTAMANHOCAMPO];
	char maior [MAXITENS] [MAXTAMANHOCAMPO];
	char exclusao [MAXITENS] [MAXTAMANHOCAMPO];
	char exclusaoarr [MAXITENS] [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	long double menores [MAXITENS];
	long double maiores [MAXITENS];
	long double exclusoes [MAXITENS] [MAXITENS];
	long double margemexclusao = 0.1;
	char * err;
	char tc;
	char * output;
	char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"U\", o menor valor atribuído a \"U\", o maior valor atribuído a \"U\", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n";

	int precisao = antoniovandre_precisao_real ();

	if (argc != 3) {printf(mensagemerro); return 1;}

	for (i = 0; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; resstring[i] = '\0';}

	for (i = 0; i < MAXITENS; i++)
		for (j = 0; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; funcao[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; menor[i][j] = '\0'; maior[i][j] = '\0'; exclusao[i][j] = '\0'; rgb[i][j] = '\0';}

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

		if ((! strcmp(menor[argi], "")) || (err == menor[argi])) {printf(mensagemerro); return 1;}

		l = 0;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {maior[argi][l++] = c;} else break;
			} while (VERDADE);

		maior[argi][l] = '\0';

		maiores[argi] = strtod(maior[argi], &err);

		if ((! strcmp(maior[argi], "")) || (err == maior[argi])) {printf(mensagemerro); return 1;}

		if (menores[argi] >= maiores[argi]) {printf(mensagemerro); return 1;}

		m = 0;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {exclusao[argi][m++] = c;} else break;
			} while (VERDADE);

		exclusao[argi][m] = '\0';

		o = 0;
		p = 0;
		q = 0;

		do
			{
			do
				{
				c = exclusao[argi][p++];
				if ((c != ',') && (c != '\0')) {exclusaoarr[argi][o][q++] = c;} else break;
				} while (VERDADE);

			exclusaoarr[argi][o][q] = '\0';

			if (strlen(exclusaoarr[argi][o]) != 0)
				{
				exclusoes[argi][o] = strtod(exclusaoarr[argi][o], &err);

				if ((! strcmp(exclusaoarr[argi][o], "")) || (err == exclusao[argi][o])) {printf(mensagemerro); return 1;}

				if ((exclusoes[argi][o] < menores[argi]) || (exclusoes[argi][o] > maiores[argi])) {printf(mensagemerro); return 1;}
				}

			q = 0;
			o++;
			} while (c != '\0');

		n = 0;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if (c != '\0') {rgb[argi][n++] = c;} else break;
			} while (VERDADE);

		rgb[argi][n] = '\0';

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
		{
		shift = 0;
		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, 1);

		do strncat(funcaox[i], & funcao[i][shift], 1); while (funcao[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, 1);
		strcat(funcaox[i], "cosU");
		}

	for (i = 0; i < argi; i++)
		{
		shift = 0;
		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, 1);

		do strncat(funcaoy[i], & funcao[i][shift], 1); while (funcao[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, 1);
		strcat(funcaoy[i], "senU");
		}

	for (i = 0; i < argi; i++)
		for (j = 0; j < resolucao; j++)
			{
			flag = 0;

			if (strlen(exclusao[i]) != 0) for (k = 0; k < o; k++)
				if ((exclusoes[i][k] >= menores[i] + j * (maiores[i] - menores[i]) / resolucao - margemexclusao) && (exclusoes[i][k] <= menores[i] + (j + 1) * (maiores[i] - menores[i]) / resolucao + margemexclusao))
					flag = 1;

			if (flag == 0)
				{
				char valorstr [MAXTAMANHOCAMPO];
				char tempstr [MAXTAMANHOCAMPO];
				char pontostr [MAXTAMANHOCAMPO];

				for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = 0;
				k = 0;

				sprintf(pontostr, "%Lf", menores[i] + j * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaox[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, 1);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, 1);

						k += strlen(pontostr) + 2;
						}
					} while (c != '\0');

				tempstr[k] = '\0';

				strcat(valorstr, tempstr);

				if (! BUILTIN)
					{
					strcat(valorstr, "\"");
					strcat(valorstr, EVALSOFTWARETAIL);
					}

				printf("0,"); fflush(stdout);

				if (BUILTIN)
					{
					output = antoniovandre_eval (valorstr, precisao);
					printf("%s", output);
					free (output);
					}
				else
					system(valorstr);

				fflush(stdout);

				printf(","); fflush(stdout);

				for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0';}

				shift = 0;
				k = 0;

				do
					{
					c = funcaoy[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, 1);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, 1);

						k += strlen(pontostr) + 2;
						}
					} while (c != '\0');

				tempstr[k] = '\0';

				strcat(valorstr, tempstr);

				if (! BUILTIN)
					{
					strcat(valorstr, "\"");
					strcat(valorstr, EVALSOFTWARETAIL);
					}

				if (BUILTIN)
					{
					output = antoniovandre_eval (valorstr, precisao);
					printf("%s", output);
					free (output);
					}
				else
					system(valorstr);

				fflush(stdout);

				printf(";0,"); fflush(stdout);

				for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = 0;
				k = 0;

				sprintf(pontostr, "%Lf", menores[i] + (j + 1) * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaox[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, 1);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, 1);

						k += strlen(pontostr) + 2;
						}

					} while (c != '\0');

				tempstr[k] = '\0';

				strcat(valorstr, tempstr);

				if (! BUILTIN)
					{
					strcat(valorstr, "\"");
					strcat(valorstr, EVALSOFTWARETAIL);
					}

				if (BUILTIN)
					{
					output = antoniovandre_eval (valorstr, precisao);
					printf("%s", output);
					free (output);
					}
				else
					system(valorstr);

				fflush(stdout);

				printf(","); fflush(stdout);

				for (k = 0; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = 0; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = 0;
				k = 0;

				sprintf(pontostr, "%Lf", menores[i] + (j + 1) * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaoy[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, 1);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, 1);

						k += strlen(pontostr) + 2;
						}
					} while (c != '\0');

				tempstr[k] = '\0';

				strcat(valorstr, tempstr);

				if (! BUILTIN)
					{
					strcat(valorstr, "\"");
					strcat(valorstr, EVALSOFTWARETAIL);
					}

				if (BUILTIN)
					{
					output = antoniovandre_eval (valorstr, precisao);
					printf("%s", output);
					free (output);
					}
				else
					system(valorstr);

				fflush(stdout);

				printf("c%s|", rgb[i]);

				fflush(stdout);
				}
			}

	printf("@@");

	printf("%s|_____|", titulo);

	for (i = 0; i < argi; i++)
		printf("x = 0, ρ = %s;%s|", funcao[i], rgb[i]);
	}