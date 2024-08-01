/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de curva em coordenadas paramétrico-polares.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função θ em "U", a função ρ em "U", o menor valor atribuído a "U", o maior valor atribuído a "U", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 31-06-2024. Sem considerar alterações em variáveis globais.
*/

#include "antoniovandre_eval/antoniovandre.c"

#define BUILTIN VERDADE

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024

#define EVALSOFTWARE "antoniovandre_eval"
#define EVALSOFTWARETAIL " 0 2>> /dev/null \| tr -d ' ' \| tr -d '\n'"

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
	char titulo [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaoteta [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaorho [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaox [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaoy [MAXITENS] [MAXTAMANHOCAMPO];
	char menor [MAXITENS] [MAXTAMANHOCAMPO];
	char maior [MAXITENS] [MAXTAMANHOCAMPO];
	char exclusao [MAXITENS] [MAXTAMANHOCAMPO];
	char exclusaoarr [MAXITENS] [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	TIPONUMEROREAL menores [MAXITENS];
	TIPONUMEROREAL maiores [MAXITENS];
	TIPONUMEROREAL exclusoes [MAXITENS] [MAXITENS];
	TIPONUMEROREAL margemexclusao = 0.1;
	char * err;
	char tc;
	char * output;
	char * mensagemerro = "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função θ em \"U\", a função ρ em \"U\", o menor valor atribuído a \"U\", o maior valor atribuído a \"U\", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n";

	int precisao = antoniovandre_precisao_real ();

	if (argc != 3) {printf(mensagemerro); return NUMEROUM;}

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; resstring[i] = '\0';}

	for (i = NUMEROZERO; i < MAXITENS; i++)
		for (j = NUMEROZERO; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; funcaoteta[i][j] = '\0'; funcaorho[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; menor[i][j] = '\0'; maior[i][j] = '\0'; exclusao[i][j] = '\0'; rgb[i][j] = '\0';}

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
			if ((c != ';') && (c != '\0')) {funcaoteta[argi][j++] = c;} else break;
			} while (VERDADE);

		funcaoteta[argi][j] = '\0';

		k = NUMEROZERO;

		do
			{
			c = item[argi][j + k + NUMEROUM];
			if ((c != ';') && (c != '\0')) {funcaorho[argi][k++] = c;} else break;
			} while (VERDADE);

		funcaorho[argi][k] = '\0';

		l = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {menor[argi][l++] = c;} else break;
			} while (VERDADE);

		menor[argi][l] = '\0';

		menores[argi] = strtod(menor[argi], &err);

		if ((! strcmp(menor[argi], "")) || (err == menor[argi])) {printf(mensagemerro); return NUMEROUM;}

		m = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {maior[argi][m++] = c;} else break;
			} while (VERDADE);

		maior[argi][m] = '\0';

		maiores[argi] = strtod(maior[argi], &err);

		if ((! strcmp(maior[argi], "")) || (err == maior[argi])) {printf(mensagemerro); return NUMEROUM;}

		if (menores[argi] >= maiores[argi]) {printf(mensagemerro); return NUMEROUM;}

		n = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {exclusao[argi][n++] = c;} else break;
			} while (VERDADE);

		exclusao[argi][n] = '\0';

		o = NUMEROZERO;
		p = NUMEROZERO;
		q = NUMEROZERO;

		do
			{
			do
				{
				c = exclusao[argi][p++];
				if ((c != ',') && (c != '\0')) {exclusaoarr[argi][o][q++] = c;} else break;
				} while (VERDADE);

			exclusaoarr[argi][o][q] = '\0';

			if (strlen(exclusaoarr[argi][o]) != NUMEROZERO)
				{
				exclusoes[argi][o] = strtod(exclusaoarr[argi][o], &err);

				if ((! strcmp(exclusaoarr[argi][o], "")) || (err == exclusao[argi][o])) {printf(mensagemerro); return NUMEROUM;}

				if ((exclusoes[argi][o] < menores[argi]) || (exclusoes[argi][o] > maiores[argi])) {printf(mensagemerro); return NUMEROUM;}
				}

			q = NUMEROZERO;
			o++;
			} while (c != '\0');

		r = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + r + 5];
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

	for (i = NUMEROZERO; i < argi; i++)
		{
		shift = NUMEROZERO;
		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, NUMEROUM);

		do strncat(funcaox[i], & funcaorho[i][shift], NUMEROUM); while (funcaorho[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, NUMEROUM);

		strcat(funcaox[i], "cos");
		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		strcat(funcaox[i], funcaoteta[i]);
		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		}

	for (i = NUMEROZERO; i < argi; i++)
		{
		shift = NUMEROZERO;
		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, NUMEROUM);

		do strncat(funcaoy[i], & funcaorho[i][shift], NUMEROUM); while (funcaorho[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, NUMEROUM);

		strcat(funcaoy[i], "sen");
		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		strcat(funcaoy[i], funcaoteta[i]);
		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		}

	for (i = NUMEROZERO; i < argi; i++)
		for (j = NUMEROZERO; j < resolucao; j++)
			{
			flag = NUMEROZERO;

			if (strlen(exclusao[i]) != NUMEROZERO) for (k = NUMEROZERO; k < o; k++)
				if ((exclusoes[i][k] >= menores[i] + j * (maiores[i] - menores[i]) / resolucao - margemexclusao) && (exclusoes[i][k] <= menores[i] + (j + NUMEROUM) * (maiores[i] - menores[i]) / resolucao + margemexclusao))
					flag = NUMEROUM;

			if (flag == NUMEROZERO)
				{
				char valorstr [MAXTAMANHOCAMPO];
				char tempstr [MAXTAMANHOCAMPO];
				char pontostr [MAXTAMANHOCAMPO];

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = NUMEROZERO;
				k = NUMEROZERO;

				sprintf(pontostr, "%Lf", menores[i] + j * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaox[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

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

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0';}

				shift = NUMEROZERO;
				k = NUMEROZERO;

				do
					{
					c = funcaoy[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

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

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = NUMEROZERO;
				k = NUMEROZERO;

				sprintf(pontostr, "%Lf", menores[i] + (j + NUMEROUM) * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaox[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

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

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

				if (! BUILTIN)
					{
					strcpy(valorstr, EVALSOFTWARE);
					strcat(valorstr, " \"");
					}

				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; pontostr[k] = '\0';}

				shift = NUMEROZERO;
				k = NUMEROZERO;

				sprintf(pontostr, "%Lf", menores[i] + (j + NUMEROUM) * (maiores[i] - menores[i]) / resolucao);

				do
					{
					c = funcaoy[i][shift++];

					if (c != 'U')
						{tempstr[k++] = c;}
					else
						{
						tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tc = TOKENFIMEVAL; strncat(tempstr, & tc, NUMEROUM);

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

	for (i = NUMEROZERO; i < argi; i++)
		printf("x = 0, θ = %s, ρ = %s;%s|", funcaoteta[i], funcaorho[i], rgb[i]);
	}