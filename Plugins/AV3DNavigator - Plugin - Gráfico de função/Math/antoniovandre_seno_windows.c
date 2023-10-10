// Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).

// Projeto Mathematical Ramblings (mathematicalramblings.blogspot.com).

// Retorna o seno do primeiro argumento. Para Cygwin.

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 04-02-2023. Sem considerar alterações em variáveis globais.

#include "/cygdrive/f/AntonioVandrePedrosaFurtunatoGomes/C/antoniovandre_windows.c"

#define SENO_MENSAGEM_USO "Use antoniovandre_seno_windows.exe <NÚMERO REAL>."
#define CABECALHO_ESTATISTICAS_MATHSENO "seno"

int main (int argc, char *argv [])
	{
	long double arco;
	char * err;

	if (argc != 2)
		{
		printf (SENO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if ((! strcmp (argv [1], "h")) || (! strcmp (argv [1], "help")) || (! strcmp (argv [1], "-h")) || (! strcmp (argv [1], "--help")))
		{
		printf (SENO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	if ((! strcmp (argv [1], "sobre")) || (! strcmp (argv [1], "--sobre")))
		{
		if (! antoniovandre_mathsobre ()) return -1;
		return 0;
		}

	arco = strtod (argv [1], & err);

	if (*err != 0)
		{
		printf (SENO_MENSAGEM_USO);
		printf ("\n");
		return -1;
		}

	int antoniovandre_precisao_real_valor = antoniovandre_precisao_real (); if (antoniovandre_precisao_real_valor != -1) printf ("%.*lf\n", antoniovandre_precisao_real_valor, sin ((TIPONUMEROREAL) arco)); else return -1;

	if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHSENO)) return -1;

	return 0;
	}
