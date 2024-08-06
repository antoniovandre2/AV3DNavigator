/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator superfície tridimensional por coordenadas polares.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em \"VARIAVELDESUBSTITUICAO3\" e "VARIAVELDESUBSTITUICAO4" para "ρ", o menor valor atribuído a "VARIAVELDESUBSTITUICAO3", o maior valor atribuído a "VARIAVELDESUBSTITUICAO3", o menor valor atribuído a "VARIAVELDESUBSTITUICAO4", o maior valor atribuído a "V", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: "grid" apenas para grid ou "fill" para polígonos preenchidos. 3: a resolução.

Última atualização: 06-08-2024. Sem considerar alterações em variáveis globais.
*/

#include "antoniovandre_eval/antoniovandre.c"

#define MAXITENS 10
#define MAXTAMANHOCAMPO 1024

#define BUILTIN VERDADE

#if BUILTIN == VERDADE

#define EVALSOFTWARE ""
#define EVALSOFTWARETAIL ""
#define CALLEVALSOFTWARE {char * ptreval = antoniovandre_eval(valorstr, precisao); printf("%s", ptreval); free (ptreval);}

#define ASPASINICIAL
#define ASPASFINAL

#else

#define EVALSOFTWARE "antoniovandre_eval"
#define EVALSOFTWARETAIL " 0 2>> /dev/null \| tr -d ' ' \| tr -d '\n'"
#define CALLEVALSOFTWARE system(valorstr);

#define ASPASINICIAL strcat(valorstr, " \"");
#define ASPASFINAL strcat(valorstr, "\"");

#endif

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
	char c;
	int flag = NUMEROZERO;
	char mainstring [MAXTAMANHOCAMPO];
	char fillstring [MAXTAMANHOCAMPO];
	char resstring [MAXTAMANHOCAMPO];
	char titulo [MAXTAMANHOCAMPO];
	char item [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaorho [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaox [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaoy [MAXITENS] [MAXTAMANHOCAMPO];
	char funcaoz [MAXITENS] [MAXTAMANHOCAMPO];
	char menoru [MAXITENS] [MAXTAMANHOCAMPO];
	char maioru [MAXITENS] [MAXTAMANHOCAMPO];
	char menorv [MAXITENS] [MAXTAMANHOCAMPO];
	char maiorv [MAXITENS] [MAXTAMANHOCAMPO];
	char rgb [MAXITENS] [MAXTAMANHOCAMPO];
	char rgbs [MAXITENS] [MAXTAMANHOCAMPO];
	char verifstr [MAXTAMANHOCAMPO];
	int deslocamentorgb = 50;
	TIPONUMEROREAL menoresu [MAXITENS];
	TIPONUMEROREAL maioresu [MAXITENS];
	TIPONUMEROREAL menoresv [MAXITENS];
	TIPONUMEROREAL maioresv [MAXITENS];
	char * err;
	char tc;
	char mensagemerro [MAXTAMANHOCAMPO];
	char valorstr [MAXTAMANHOCAMPO];
	char tempstr [MAXTAMANHOCAMPO];
	char tempstr2 [MAXTAMANHOCAMPO];
	char pontostru [MAXTAMANHOCAMPO];
	char pontostrv [MAXTAMANHOCAMPO];
	char * temp;

	int precisao = antoniovandre_precisao_real ();

	char variavel1 = (char) ((int) strtold (antoniovandre_eval("system variaveldesubstituicao3", precisao), & err));

	char variavel2 = (char) ((int) strtold (antoniovandre_eval("system variaveldesubstituicao4", precisao), & err));

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) mensagemerro[i] = '\0';

	strcpy(mensagemerro, "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"VARIAVELDESUBSTITUICAO3\" e \"VARIAVELDESUBSTITUICAO4\" para \"ρ\", o menor valor atribuído a \"VARIAVELDESUBSTITUICAO3\", o maior valor atribuído a \"VARIAVELDESUBSTITUICAO3\", o menor valor atribuído a \"VARIAVELDESUBSTITUICAO4\", o maior valor atribuído a \"VARIAVELDESUBSTITUICAO4\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: \"grid\" apenas para grid ou \"fill\" para polígonos preenchidos. 3: a resolução.\n");

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) tempstr[i] = '\0';

	for (i = NUMEROZERO; i < strlen(mensagemerro); i++)
		{
		temp = antoniovandre_substring(mensagemerro, i, i + 22);

		if (! (strcmp(temp, "VARIAVELDESUBSTITUICAO3")))
			{
			strncat(tempstr, & variavel1, NUMEROUM);
			i += 22;
			}
		else if (! (strcmp(temp, "VARIAVELDESUBSTITUICAO4")))
			{
			strncat(tempstr, & variavel2, NUMEROUM);
			i += 22;
			}
		else
			strncat(tempstr, & mensagemerro[i], NUMEROUM);

		free(temp);
		}

	strcpy(mensagemerro, tempstr);

	if (argc != 4) {printf(mensagemerro); return NUMEROUM;}

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; fillstring[i] = '\0'; resstring[i] = '\0';}

	for (i = NUMEROZERO; i < MAXITENS; i++)
		for (j = NUMEROZERO; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; funcaorho[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; funcaoz[i][j] = '\0'; menoru[i][j] = '\0'; maioru[i][j] = '\0'; menorv[i][j] = '\0'; maiorv[i][j] = '\0'; rgb[i][j] = '\0'; rgbs[i][j] = '\0';}

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
			if ((c != ';') && (c != '\0')) {funcaorho[argi][j++] = c;} else break;
			} while (VERDADE);

		funcaorho[argi][j] = '\0';

		k = NUMEROZERO;

		do
			{
			c = item[argi][j + k + NUMEROUM];
			if ((c != ';') && (c != '\0')) {menoru[argi][k++] = c;} else break;
			} while (VERDADE);

		menoru[argi][k] = '\0';

		temp = antoniovandre_eval(menoru[argi], precisao);
		menoresu[argi] = strtod(temp, & err);

		if ((! strcmp(menoru[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		l = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {maioru[argi][l++] = c;} else break;
			} while (VERDADE);

		maioru[argi][l] = '\0';

		temp = antoniovandre_eval(maioru[argi], precisao);
		maioresu[argi] = strtod(temp, & err);

		if ((! strcmp(maioru[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresu[argi] >= maioresu[argi]) {printf(mensagemerro); return NUMEROUM;}

		m = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {menorv[argi][m++] = c;} else break;
			} while (VERDADE);

		menorv[argi][m] = '\0';

		temp = antoniovandre_eval(menorv[argi], precisao);
		menoresv[argi] = strtod(temp, & err);

		if ((! strcmp(menorv[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		n = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {maiorv[argi][n++] = c;} else break;
			} while (VERDADE);

		maiorv[argi][n] = '\0';

		temp = antoniovandre_eval(maiorv[argi], precisao);
		maioresv[argi] = strtod(temp, & err);

		if ((! strcmp(maiorv[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menoresv[argi] >= maioresv[argi]) {printf(mensagemerro); return NUMEROUM;}

		o = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + o + 5];
			if (c != '\0') {rgb[argi][o++] = c;} else break;
			} while (VERDADE);

		rgb[argi][o] = '\0';

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

	for (i = NUMEROZERO; i < argi; i++)
		{
		shift = NUMEROZERO;
		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, NUMEROUM);

		do strncat(funcaox[i], & funcaorho[i][shift], NUMEROUM); while (funcaorho[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, NUMEROUM);

		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		strcat(funcaox[i], "cos");
		strncat(funcaox[i], & variavel1, NUMEROUM);
		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		tc = TOKENINICIOEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		strcat(funcaox[i], "cos");
		strncat(funcaox[i], & variavel2, NUMEROUM);
		tc = TOKENFIMEVAL; strncat(funcaox[i], & tc, NUMEROUM);
		}

	for (i = NUMEROZERO; i < argi; i++)
		{
		shift = NUMEROZERO;
		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, NUMEROUM);

		do strncat(funcaoy[i], & funcaorho[i][shift], NUMEROUM); while (funcaorho[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, NUMEROUM);

		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		strcat(funcaoy[i], "sen");
		strncat(funcaoy[i], & variavel1, NUMEROUM);
		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		tc = TOKENINICIOEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		strcat(funcaoy[i], "cos");
		strncat(funcaoy[i], & variavel2, NUMEROUM);
		tc = TOKENFIMEVAL; strncat(funcaoy[i], & tc, NUMEROUM);
		}

	for (i = NUMEROZERO; i < argi; i++)
		{
		shift = NUMEROZERO;
		tc = TOKENINICIOEVAL; strncat(funcaoz[i], & tc, NUMEROUM);

		do strncat(funcaoz[i], & funcaorho[i][shift], NUMEROUM); while (funcaorho[i][++shift] != '\0');

		tc = TOKENFIMEVAL; strncat(funcaoz[i], & tc, NUMEROUM);

		tc = TOKENINICIOEVAL; strncat(funcaoz[i], & tc, NUMEROUM);
		strcat(funcaoz[i], "sen");
		strncat(funcaoz[i], & variavel2, NUMEROUM);
		tc = TOKENFIMEVAL; strncat(funcaoz[i], & tc, NUMEROUM);
		}

	if (! strcmp(fillstring, "grid"))
		{
		for (i = NUMEROZERO; i < argi; i++)
			for (l = NUMEROZERO; l < resolucao; l++)
				{
				for (j = NUMEROZERO; j < resolucao; j++)
					{
					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

					sprintf(pontostrv, "%Lf", menoresv[i] + l * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + j * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(";"); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + (j + NUMEROUM) * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf("c%s|", rgb[i]); fflush(stdout);
					}
				}

		for (i = NUMEROZERO; i < argi; i++)
			for (l = NUMEROZERO; l < resolucao; l++)
				{
				for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

				sprintf(pontostru, "%Lf", menoresu[i] + l * (maioresu[i] - menoresu[i]) / resolucao);

				for (j = NUMEROZERO; j < resolucao; j++)
					{
					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

					sprintf(pontostrv, "%Lf", menoresv[i] + j * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(";"); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

					sprintf(pontostrv, "%Lf", menoresv[i] + (j + NUMEROUM) * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf("c%s|", rgb[i]); fflush(stdout);
					}
				}
		}

	printf("@"); fflush(stdout);

	if (! strcmp(fillstring, "fill"))
		{
		for (i = NUMEROZERO; i < argi; i++)
			for (l = NUMEROZERO; l < resolucao; l++)
				{
				for (j = NUMEROZERO; j < resolucao; j++)
					{
					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

					sprintf(pontostrv, "%Lf", menoresv[i] + l * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + j * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(";"); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + (j + NUMEROUM) * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(";"); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostrv[k] = '\0';

					sprintf(pontostrv, "%Lf", menoresv[i] + (l + NUMEROUM) * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + (j + NUMEROUM) * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(";"); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostru[k] = '\0';

					sprintf(pontostru, "%Lf", menoresu[i] + j * (maioresu[i] - menoresu[i]) / resolucao);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaox[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoy[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					printf(","); fflush(stdout);

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

					strcpy(valorstr, EVALSOFTWARE);
					ASPASINICIAL

					for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) {tempstr[k] = '\0'; tempstr2[k] = '\0';}

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = funcaoz[i][shift++];

						if (c != variavel1)
							{tempstr[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr, & tc, NUMEROUM);
								strcat(tempstr, pontostru);
								tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostru) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr[k] = '\0';

					shift = NUMEROZERO;
					k = NUMEROZERO;

					do
						{
						c = tempstr[shift++];

						if (c != variavel2)
							{tempstr2[k++] = c;}
						else
							{
							if (shift == NUMEROUM)
								{
								tempstr2[NUMEROZERO] = TOKENINICIOEVAL;
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}
							else
								{
								tc = TOKENINICIOEVAL; strncat(tempstr2, & tc, NUMEROUM);
								strcat(tempstr2, pontostrv);
								tempstr2[strlen(tempstr2) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
								}

							k += strlen(pontostrv) + NUMEROUM;
							}
						} while (c != '\0');

					tempstr2[k] = '\0';

					strcat(valorstr, tempstr2);
					ASPASFINAL
					strcat(valorstr, EVALSOFTWARETAIL);

					CALLEVALSOFTWARE fflush(stdout);

					if ((j + l) % 2 == NUMEROZERO)
						printf("c%s|", rgb[i]);
					else
						printf("c%s|", rgbs[i]);

					fflush(stdout);
					}
				}
		}

	printf("@%s|_____|", titulo); fflush(stdout);

	for (i = NUMEROZERO; i < argi; i++)
		printf("ρ = %s;%s|", funcaorho[i], rgb[i]);
	}