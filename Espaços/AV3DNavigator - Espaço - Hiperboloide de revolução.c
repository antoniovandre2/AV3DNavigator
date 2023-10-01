/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Elipsoide de revolução.txt".

Última atualização: 30-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    int resolucao = 20;
    float a = 3;
    float b = 2;
    float limite = 20;

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            if ((cos(j * 2 * M_PI / resolucao) != 0) && (cos((j + 1) * 2 * M_PI / resolucao) != 0) && (fabs(b * tan(j * 2 * M_PI / resolucao)) <= limite) && (fabs(b * tan((j + 1) * 2 * M_PI / resolucao)) <= limite))
                {
                printf("%f,%f,%f;%f,%f,%f|", b * tan(j * 2 * M_PI / resolucao) * cos(i * 2 * M_PI / resolucao), a * 1 / cos(j * 2 * M_PI / resolucao), b * tan(j * 2 * M_PI / resolucao) * sin(i * 2 * M_PI / resolucao), b * tan((j + 1) * 2 * M_PI / resolucao) * cos(i * 2 * M_PI / resolucao), a * 1 / cos((j + 1) * 2 * M_PI / resolucao), b * tan((j + 1) * 2 * M_PI / resolucao) * sin(i * 2 * M_PI / resolucao));

                printf("%f,%f,%f;%f,%f,%f|", b * tan(j * 2 * M_PI / resolucao) * cos(i * 2 * M_PI / resolucao), a * 1 / cos(j * 2 * M_PI / resolucao), b * tan(j * 2 * M_PI / resolucao) * sin(i * 2 * M_PI / resolucao), b * tan(j * 2 * M_PI / resolucao) * cos((i + 1) * 2 * M_PI / resolucao), a * 1 / cos(j * 2 * M_PI / resolucao), b * tan(j * 2 * M_PI / resolucao) * sin((i + 1) * 2 * M_PI / resolucao));
                }
            }
    }