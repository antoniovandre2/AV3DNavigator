// Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).

// Projeto Mathematical Ramblings (bit.ly/mathematicalramblings_github).

// Retorna a função mais próxima das passadas como argumento, dados os pontos também passados como argumento. Para Cygwin. Leia o arquivo "README_antoniovandre_funcaomaisproxima_windows.txt".

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 04-02-2023.

#include "/cygdrive/f/AntonioVandrePedrosaFurtunatoGomes/C/antoniovandre_windows.c"

#define FUNCAOMAISPROXIMA_MENSAGEM_USO "Use antoniovandre_funcaomaisproxima <ARQUIVO DE PONTOS> <ARQUIVO DE FUNÇÕES> <LOG>.\n\nLeia o arquivo \"README_antoniovandre_funcaomaisproxima_windows.txt\".\n"
#define CABECALHO_ESTATISTICAS_MATHFUNCAOMAISPROXIMA "funcaomaisproxima"

int main (int argc, char *argv [])
	{
	char * strf;
	char * argvb1;
	char * argvb2;
	char * argvb3;
	int i;
	int j;

	if (! ((argc == 4) || (argc == 2)))
		{
		printf (FUNCAOMAISPROXIMA_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if (argc == 2) if ((! strcmp (argv [1], "h")) || (! strcmp (argv [1], "help")) || (! strcmp (argv [1], "-h")) || (! strcmp (argv [1], "--help")))
		{
		printf (FUNCAOMAISPROXIMA_MENSAGEM_USO);
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
	argvb3 = (char *) malloc (TAMANHO_BUFFER_WORD);

	strcpy (argvb1, argv [1]);
	strcpy (argvb2, argv [2]);
	strcpy (argvb3, argv [3]);

	if ((strcmp (argvb3, "0")) && (strcmp (argvb3, "1")))
		{
		printf (FUNCAOMAISPROXIMA_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	strf = antoniovandre_funcaomaisproxima (argvb1, argvb2, atoi (argvb3));

	if (! strcmp (strf, STRINGSAIDAERRO))
		{
		printf (FUNCAOMAISPROXIMA_MENSAGEM_USO);
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

	if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHFUNCAOMAISPROXIMA)) return -1;

	return 0;
	}
