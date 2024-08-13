/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Esfera camaleão.txt".

Última atualização: 13-08-2024.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main (int argc, char * argv[])
	{
	int resolucao = 20;
	long double raio = 5;
	int colorstep = 50;
	long double fatorcolormove = 100;
	int i; int j;

	printf("@");

	for (i = 0; i < resolucao; i++) for (j = 0; j < resolucao; j++)
		{
		printf("%Lfdivisor%Lfdivisor%LfDIVISOR%Lfdivisor%Lfdivisor%LfDIVISOR", raio * cosl(2 * M_PI * i / resolucao) * cosl(-M_PI_2 + M_PI * j / resolucao), raio * sinl(2 * M_PI * i / resolucao) * cosl(-M_PI_2 + M_PI * j / resolucao), raio * sinl(-M_PI_2 + M_PI * j / resolucao), raio * cosl(2 * M_PI * i / resolucao) * cosl(-M_PI_2 + M_PI * (j + 1) / resolucao), raio * sinl(2 * M_PI * i / resolucao) * cosl(-M_PI_2 + M_PI * (j + 1) / resolucao), raio * sinl(-M_PI_2 + M_PI * (j + 1) / resolucao));

		fflush(stdout);

		printf("%Lfdivisor%Lfdivisor%LfDIVISOR%Lfdivisor%Lfdivisor%Lf", raio * cosl(2 * M_PI * (i + 1) / resolucao) * cosl(-M_PI_2 + M_PI * (j + 1) / resolucao), raio * sinl(2 * M_PI * (i + 1) / resolucao) * cosl(-M_PI_2 + M_PI * (j + 1) / resolucao), raio * sinl(-M_PI_2 + M_PI * (j + 1) / resolucao), raio * cosl(2 * M_PI * (i + 1) / resolucao) * cosl(-M_PI_2 + M_PI * j / resolucao), raio * sinl(2 * M_PI * (i + 1) / resolucao) * cosl(-M_PI_2 + M_PI * j / resolucao), raio * sinl(-M_PI_2 + M_PI * j / resolucao));

		fflush(stdout);

		printf("color%s%Lf%s%d%s%Lf%s%d%s", "mod (p * ", fatorcolormove, " + ", (int) ((i + j) * colorstep), ", 256)divisormod (p * ", fatorcolormove, " + ", (int) ((i + j) * colorstep), ", 256)divisor255|");

		fflush(stdout);
		}
	}