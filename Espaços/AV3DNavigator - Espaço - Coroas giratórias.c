/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Coroas giratórias.txt".

Última atualização: 08-08-2024.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main (int argc, char * argv[])
	{
	int resolucao = 5;
	long double raio1 = 5;
	long double raio2 = 3;
	long double espessura = 1;
	long double profundidade = 1;
	int i;

	printf("@");

	for (i = 0; i < resolucao; i++)
		{
		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", profundidade, raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", profundidade, raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", profundidade, raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", profundidade, raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", profundidade, raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", profundidade, raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", profundidade, raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);", raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		printf("%Lf,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", profundidade, raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

		fflush(stdout);

		if (i == 0)
			{
			printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio1 - espessura, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("%Lf,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", profundidade, raio1, " * cos(p + ", (long double) (i * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) (i * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao), profundidade, raio2 - espessura, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("%Lf,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", profundidade, raio2, " * cos(-p + ", (long double) (i * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) (i * M_PI / 2 / resolucao));

			fflush(stdout);
			}

		if (i == resolucao - 1)
			{
			printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio1 - espessura, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1 - espessura, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("%Lf,%Lf%s%Lf),%Lf%s%Lf)color255,0,0|", profundidade, raio1, " * cos(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio1, " * sin(p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("0,%Lf%s%Lf),%Lf%s%Lf);0,%Lf%s%Lf),%Lf%s%Lf);%Lf,%Lf%s%Lf),%Lf%s%Lf);", raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), profundidade, raio2 - espessura, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2 - espessura, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

			fflush(stdout);

			printf("%Lf,%Lf%s%Lf),%Lf%s%Lf)color0,255,0|", profundidade, raio2, " * cos(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao), raio2, " * sin(-p + ", (long double) ((i + 1) * M_PI / 2 / resolucao));

			fflush(stdout);
			}
		}
	}