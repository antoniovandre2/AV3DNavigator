/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de uma curva tridimensional por coordenadas paramétricas.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "VARIAVELDESUBSTITUICAO3" para "x", função em "VARIAVELDESUBSTITUICAO3" para "y", função em "VARIAVELDESUBSTITUICAO3" para "z", o menor valor atribuído a "VARIAVELDESUBSTITUICAO3", o maior valor atribuído a \"VARIAVELDESUBSTITUICAO3\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

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
	char tempstr [MAXTAMANHOCAMPO];
	TIPONUMEROREAL menores [MAXITENS];
	TIPONUMEROREAL maiores [MAXITENS];
	char * err;
	char mensagemerro [MAXTAMANHOCAMPO];
	char * temp;

	int precisao = antoniovandre_precisao_real ();

	char variavel = (char) ((int) strtold (antoniovandre_eval("system variaveldesubstituicao3", precisao), & err));

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) mensagemerro[i] = '\0';

	strcpy(mensagemerro, "Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"VARIAVELDESUBSTITUICAO3\" para \"x\", função em \"VARIAVELDESUBSTITUICAO3\" para \"y\", função em \"VARIAVELDESUBSTITUICAO3\" para \"z\", o menor valor atribuído a \"VARIAVELDESUBSTITUICAO3\", o maior valor atribuído a \"VARIAVELDESUBSTITUICAO3\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n");

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) tempstr[i] = '\0';

	for (i = NUMEROZERO; i < strlen(mensagemerro); i++)
		{
		temp = antoniovandre_substring(mensagemerro, i, i + 22);

		if (! (strcmp(temp, "VARIAVELDESUBSTITUICAO3")))
			{
			strncat(tempstr, & variavel, NUMEROUM);
			i += 22;
			}
		else
			strncat(tempstr, & mensagemerro[i], NUMEROUM);

		free(temp);
		}

	strcpy(mensagemerro, tempstr);

	if (argc != 3) {printf(mensagemerro); return NUMEROUM;}

	for (i = NUMEROZERO; i < MAXTAMANHOCAMPO; i++) {mainstring[i] = '\0'; resstring[i] = '\0';}

	for (i = NUMEROZERO; i < MAXITENS; i++)
		for (j = NUMEROZERO; j < MAXTAMANHOCAMPO; j++)
			{item[i][j] = '\0'; funcaox[i][j] = '\0'; funcaoy[i][j] = '\0'; funcaoz[i][j] = '\0'; menor[i][j] = '\0'; maior[i][j] = '\0'; rgb[i][j] = '\0';}

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
			c = mainstring[shift++ + 1];
			if (c != ' ') if ((c != '|') && (c != '\0')) {item[argi][i++] = c;} else break;
			} while (VERDADE);

		item[argi][i] = '\0';

		if (c == '\0') flag = NUMEROUM;

		j = NUMEROZERO;

		do
			{
			c = item[argi][j];
			if ((c != ';') && (c != '\0')) {funcaox[argi][j++] = c;} else break;
			} while (VERDADE);

		funcaox[argi][j] = '\0';

		k = NUMEROZERO;

		do
			{
			c = item[argi][k + j + 1];
			if ((c != ';') && (c != '\0')) {funcaoy[argi][k++] = c;} else break;
			} while (VERDADE);

		funcaoy[argi][k] = '\0';

		l = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + 2];
			if ((c != ';') && (c != '\0')) {funcaoz[argi][l++] = c;} else break;
			} while (VERDADE);

		funcaoz[argi][l] = '\0';

		m = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + 3];
			if ((c != ';') && (c != '\0')) {menor[argi][m++] = c;} else break;
			} while (VERDADE);

		menor[argi][m] = '\0';

		temp = antoniovandre_eval(menor[argi], precisao);
		menores[argi] = strtod(temp, &err);

		if ((! strcmp(menor[argi], "")) || (err == temp)) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		n = NUMEROZERO;

		do
			{
			c = item[argi][j + k + l + m + n + 4];
			if ((c != ';') && (c != '\0')) {maior[argi][n++] = c;} else break;
			} while (VERDADE);

		maior[argi][n] = '\0';

		temp = antoniovandre_eval(maior[argi], precisao);
		maiores[argi] = strtod(temp, &err);

		if ((! strcmp(maior[argi], "")) || (err == maior[argi])) {printf(mensagemerro); free(temp); return NUMEROUM;}

		free(temp);

		if (menores[argi] >= maiores[argi]) {printf(mensagemerro); return NUMEROUM;}

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

		if (++argi > MAXITENS) {printf(mensagemerro); return NUMEROUM;}
		} while (flag == NUMEROZERO);

	for (i = NUMEROZERO; i < argi; i++)
		for (j = NUMEROZERO; j < resolucao; j++)
			{
			char valorstr [MAXTAMANHOCAMPO];
			char tempstr [MAXTAMANHOCAMPO];
			char pontostr [MAXTAMANHOCAMPO];

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostr[k] = '\0';

			sprintf(pontostr, "%Lf", menores[i] + j * (maiores[i] - menores[i]) / resolucao);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaox[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
					}
				} while (c != '\0');

			tempstr[k] = '\0';

			strcat(valorstr, tempstr);
			ASPASFINAL
			strcat(valorstr, EVALSOFTWARETAIL);

			CALLEVALSOFTWARE fflush(stdout);

			printf(","); fflush(stdout);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaoy[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
					}
				} while (c != '\0');

			tempstr[k] = '\0';

			strcat(valorstr, tempstr);
			ASPASFINAL
			strcat(valorstr, EVALSOFTWARETAIL);

			CALLEVALSOFTWARE fflush(stdout);

			printf(","); fflush(stdout);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaoz[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
					}
				} while (c != '\0');

			tempstr[k] = '\0';

			strcat(valorstr, tempstr);
			ASPASFINAL
			strcat(valorstr, EVALSOFTWARETAIL);

			CALLEVALSOFTWARE fflush(stdout);

			printf(";"); fflush(stdout);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) pontostr[k] = '\0';

			sprintf(pontostr, "%Lf", menores[i] + (j + NUMEROUM) * (maiores[i] - menores[i]) / resolucao);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaox[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
					}
				} while (c != '\0');

			tempstr[k] = '\0';

			strcat(valorstr, tempstr);
			ASPASFINAL
			strcat(valorstr, EVALSOFTWARETAIL);

			CALLEVALSOFTWARE fflush(stdout);

			printf(","); fflush(stdout);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaoy[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
					}
				} while (c != '\0');

			tempstr[k] = '\0';

			strcat(valorstr, tempstr);
			ASPASFINAL
			strcat(valorstr, EVALSOFTWARETAIL);

			CALLEVALSOFTWARE fflush(stdout);

			printf(","); fflush(stdout);

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) valorstr[k] = '\0';

			strcpy(valorstr, EVALSOFTWARE);
			ASPASINICIAL

			for (k = NUMEROZERO; k < MAXTAMANHOCAMPO; k++) tempstr[k] = '\0';

			shift = NUMEROZERO;
			k = NUMEROZERO;

			do
				{
				c = funcaoz[i][shift++];

				if (c != variavel)
					{tempstr[k++] = c;}
				else
					{
					if (shift == NUMEROUM)
						{
						tempstr[NUMEROZERO] = TOKENINICIOEVAL;
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}
					else
						{
						char tc = TOKENINICIOEVAL; strncat (tempstr, & tc, NUMEROUM);
						strcat(tempstr, pontostr);
						tempstr[strlen(tempstr) - NUMEROUM] = TOKENFIMEVAL; c = TOKENFIMEVAL;
						}

					k += strlen(pontostr) + NUMEROUM;
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

	for (i = NUMEROZERO; i < argi; i++)
		printf("x = %s, y = %s, z = %s;%s|", funcaox[i], funcaoy[i], funcaoz[i], rgb[i]);
	}