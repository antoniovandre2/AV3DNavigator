// Projeto Mathematical Ramblings (mathematicalramblings.blogspot.com).

// Retorna a avaliação (eval) da expressão passada como argumento.

// Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).

// Última atualização: 14-07-2024. Não considerando alterações em variáveis globais.

#include "../antoniovandre.c"

#define EVAL_MENSAGEM_USO "Use antoniovandre_eval <EXPRESSÃO VÁLIDA> [LOG].\n\nPipes também podem ser utilizados, consistem de uma string separada por DELIMITADORSTRING, o primeiro campo a substring a avaliar, e o segundo 0 para não gerar log ou qualquer outro valor para gerar log."
#define CABECALHO_ESTATISTICAS_MATHEVAL "eval"

int main (int argc, char *argv [])
	{
	char * strf;
	char * argvb;
	char * argvb2;
	char * argvb3;
	int i;
	int j = NUMEROZERO;
	int flag = NUMEROZERO;

	int precisao = antoniovandre_precisao_real ();

	argvb = (char *) malloc (TAMANHO_BUFFER_PHRASE);
	argvb2 = (char *) malloc (TAMANHO_BUFFER_PHRASE);
	argvb3 = (char *) malloc (TAMANHO_BUFFER_PHRASE);

	antoniovandre_copiarstring (argvb, STRINGVAZIA);
	antoniovandre_copiarstring (argvb2, STRINGVAZIA);
	antoniovandre_copiarstring (argvb3, STRINGVAZIA);

	if (argc > NUMEROUM)
		{
		if (! ((argc == 2) || (argc == 3)))
			{
			printf (EVAL_MENSAGEM_USO);
			printf ("\n");
			return NUMEROMENOSUM;
			}

		antoniovandre_copiarstring (argvb, argv [NUMEROUM]);

		if (argc == 2) if ((! strcmp (argv [NUMEROUM], "h")) || (! strcmp (argv [NUMEROUM], "help")) || (! strcmp (argv [NUMEROUM], "-h")) || (! strcmp (argv [NUMEROUM], "--help")))
			{
			printf (EVAL_MENSAGEM_USO);
			printf ("\n");
			return NUMEROMENOSUM;
			}

		if (argc == 2) if ((! strcmp (argv [NUMEROUM], "sobre")) || (! strcmp (argv [NUMEROUM], "--sobre")))
			{
			if (! antoniovandre_mathsobre ()) return NUMEROMENOSUM;
			return NUMEROZERO;
			}
		}
	else fgets(argvb, TAMANHO_BUFFER_PHRASE, stdin);

	flag = NUMEROZERO;

	for (i = NUMEROZERO; i < strlen (argvb); i++)
		{
		if (argvb [i] == DELIMITADORSTRING) {flag = NUMEROUM; i++; j = NUMEROZERO;}

		if (flag == NUMEROZERO)
			{
			if ((argvb [i] != ESPACOBRANCO) && (argvb [i] != CARACTEREFIMLINHA))
				argvb2 [j++] = argvb [i];
			}
		else if (argc == NUMEROUM)
			{
			if ((argvb [i] != ESPACOBRANCO) && (argvb [i] != CARACTEREFIMLINHA))
				argvb3 [j++] = argvb [i];
			}
		}

	flag = NUMEROZERO;

	strf = antoniovandre_eval (argvb2, precisao);

	if (! strcmp (strf, STRINGSAIDAERRO))
		{
		printf (EVAL_MENSAGEM_USO);
		printf ("\n");
		return NUMEROMENOSUM;
		}
	else if (! strcmp (strf, STRINGSAIDAERROOVER))
		{
		printf (MENSAGEM_ERRO_OVER);
		printf ("\n");
		return NUMEROMENOSUM;
		}
	else
		printf ("%s\n", strf);

	if (! strcmp (argvb3, STRINGVAZIA))
		{if (argc == 3) if (! strcmp (argv [2], "0")) flag = NUMEROUM;}
	else if (! strcmp (argvb3, "0")) flag = NUMEROUM;

	if (flag == NUMEROZERO) if (! antoniovandre_salvarmathestatisticas (CABECALHO_ESTATISTICAS_MATHEVAL)) return NUMEROMENOSUM;

	return NUMEROZERO;
	}
