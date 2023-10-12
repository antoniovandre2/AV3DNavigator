// Proprietário: AntonioVandrePedrosaFurtunatoGomes (bit.ly/antoniovandre_legadoontologico).

// Projeto Mathematical Ramblings (mathematicalramblings.blogspot.com).

// Retorna a avaliação (eval) da expressão passada como argumento. Para Cygwin.

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 04-02-2023. Sem considerar alterações em variáveis globais.

#include "../antoniovandre_windows.c"

#define EVAL_MENSAGEM_USO "Use antoniovandre_eval_windows.exe <EXPRESSÃO VÁLIDA> [LOG]."
#define CABECALHO_ESTATISTICAS_MATHEVAL "eval"

int main (int argc, char *argv [])
	{
	char * strf;
	char * argvb;
	int i;
	int j;
	int flag = NUMEROZERO;

	argvb = (char *) malloc (TAMANHO_BUFFER_PHRASE);

	if (! ((argc == 2) || (argc == 3)))
		{
		printf (EVAL_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	strcpy (argvb, argv [1]);

	if (argc == 2) if ((! strcmp (argv [1], "h")) || (! strcmp (argv [1], "help")) || (! strcmp (argv [1], "-h")) || (! strcmp (argv [1], "--help")))
		{
		printf (EVAL_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if (argc == 2) if ((! strcmp (argv [1], "sobre")) || (! strcmp (argv [1], "--sobre")))
		{
		if (! antoniovandre_mathsobre ()) return -1;
		return 0;
		}

	strf = antoniovandre_eval (argvb);

	if (! strcmp (strf, STRINGSAIDAERRO))
		{
		printf (EVAL_MENSAGEM_USO);
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

	if (argc == 3) if (! strcmp (argv [2], "0")) flag = NUMEROUM;

	if (flag == NUMEROZERO) if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHEVAL)) return -1;

	return 0;
	}
