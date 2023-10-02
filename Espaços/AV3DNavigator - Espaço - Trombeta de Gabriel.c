/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Trombeta de Gabriel.txt".

Última atualização: 02-10-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    int resolucao = 15;
    float a = 2;
    float comprimento = 7;

    for (int i = 1; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            if (i != resolucao - 1) printf("%f,%f,%f;%f,%f,%f|", a / (i * comprimento / resolucao) * cos(j * 2 * M_PI / resolucao), i * comprimento / resolucao - comprimento / 2, a / (i * comprimento / resolucao) * sin(j * 2 * M_PI / resolucao), a / ((i + 1) * comprimento / resolucao) * cos(j * 2 * M_PI / resolucao), (i + 1) * comprimento / resolucao - comprimento / 2, a / ((i + 1) * comprimento / resolucao) * sin(j * 2 * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", a / (i * comprimento / resolucao) * cos(j * 2 * M_PI / resolucao), i * comprimento / resolucao - comprimento / 2, a / (i * comprimento / resolucao) * sin(j * 2 * M_PI / resolucao), a / (i * comprimento / resolucao) * cos((j + 1) * 2 * M_PI / resolucao), i * comprimento / resolucao - comprimento / 2, a / (i * comprimento / resolucao) * sin((j + 1) * 2 * M_PI / resolucao));
            }
    }