// Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).

// Projeto Mathematical Ramblings (mathematicalramblings.blogspot.com).

// Retorna a soma numérica dos argumentos. Para Windows.

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 04-02-2023.

#include "/cygdrive/f/AntonioVandrePedrosaFurtunatoGomes/C/antoniovandre_windows.c"

#define SOMA_MENSAGEM_USO "Use antoniovandre_soma_windows.exe <NÚMERO REAL [NÚMERO REAL] ...>."
#define CABECALHO_ESTATISTICAS_MATHSOMA "soma"

int main (int argc, char *argv [])
	{
	TIPONUMEROREAL soma = 0;
	int i;
	char * err;

	if (argc == 1)
		{
		printf (SOMA_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if ((argc == 2) && ((! strcmp (argv [1], "h")) || (! strcmp (argv [1], "help")) || (! strcmp (argv [1], "-h")) || (! strcmp (argv [1], "--help"))))
		{
		printf (SOMA_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if ((argc == 2) && ((! strcmp (argv [1], "sobre")) || (! strcmp (argv [1], "--sobre"))))
		{
		if (! antoniovandre_mathsobre ()) return -1;
		return 0;
		}

	for (i = 1; i < argc; i++)
		{
		soma += strtod (argv [i], & err);

		if (*err != 0)
			{
			printf (SOMA_MENSAGEM_USO);
			printf ("\n");
			return -1;
			}
		}

	int antoniovandre_precisao_real_valor = antoniovandre_precisao_real (); if (antoniovandre_precisao_real_valor != -1) printf ("%.*lf\n", antoniovandre_precisao_real_valor, (TIPONUMEROREAL) soma); else return -1;

	if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHSOMA)) return -1;

	return 0;
	}
