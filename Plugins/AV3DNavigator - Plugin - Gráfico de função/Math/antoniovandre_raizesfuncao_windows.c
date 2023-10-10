// Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).

// Projeto Mathematical Ramblings (bit.ly/mathematicalramblings_github).

// Retorna as raízes por aproximação de uma função passada como argumento. Para Cygwin.

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 04-02-2023. Sem considerar alterações em variáveis globais.

#include "/cygdrive/f/AntonioVandrePedrosaFurtunatoGomes/C/antoniovandre_windows.c"

#define RAIZESFUNCAO_MENSAGEM_USO "Use antoniovandre_raizesfuncao_windows.exe <FUNÇÃO> <MÍNIMO VALOR DA PESQUISA> <MÁXIMO VALOR DA PESQUISA> <STEP> <LOG>."
#define CABECALHO_ESTATISTICAS_MATHRAIZESFUNCAO "raizesfuncao"

int main (int argc, char *argv [])
	{
	char * strf;
	char * argvb1;
	char * argvb2;
	char * argvb3;
	char * argvb4;
	char * argvb5;
	TIPONUMEROREAL step;
	int i;
	int j;
	char * err;

	if (! ((argc == 6) || (argc == 2)))
		{
		printf (RAIZESFUNCAO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if (argc == 2) if ((! strcmp (argv [1], "h")) || (! strcmp (argv [1], "help")) || (! strcmp (argv [1], "-h")) || (! strcmp (argv [1], "--help")))
		{
		printf (RAIZESFUNCAO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if (argc == 2) if ((! strcmp (argv [1], "sobre")) || (! strcmp (argv [1], "--sobre")))
		{
		if (! antoniovandre_mathsobre ()) return -1;
		return 0;
		}

	argvb1 = (char *) malloc (TAMANHO_BUFFER_PHRASE);
	argvb2 = (char *) malloc (TAMANHO_BUFFER_PHRASE);
	argvb3 = (char *) malloc (TAMANHO_BUFFER_PHRASE);
	argvb4 = (char *) malloc (TAMANHO_BUFFER_WORD);
	argvb5 = (char *) malloc (TAMANHO_BUFFER_WORD);

	strcpy (argvb1, argv [1]);
	strcpy (argvb2, argv [2]);
	strcpy (argvb3, argv [3]);
	strcpy (argvb4, argv [4]);
	strcpy (argvb5, argv [5]);

	step = strtold (argvb4, & err);

	if ((* err != NUMEROZERO) || ((strcmp (argvb5, "0")) && (strcmp (argvb5, "1"))))
		{
		printf (RAIZESFUNCAO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	strf = antoniovandre_raizesfuncao (argvb1, argvb2, argvb3, step, atoi (argvb5));

	if (! strcmp (strf, STRINGSAIDAERRO))
		{
		printf (RAIZESFUNCAO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}
	else if (! strcmp (strf, STRINGSAIDAERROOVER))
		{
		printf (MENSAGEM_ERRO_OVER);
		printf ("\n");
		return -1;
		}
	else
		printf ("%s\n", strf);

	if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHRAIZESFUNCAO)) return -1;

	return 0;
	}
