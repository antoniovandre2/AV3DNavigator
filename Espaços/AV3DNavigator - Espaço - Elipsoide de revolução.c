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
    int resolucao = 12;
    float a = 3;
    float b = 2;

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", b * sin(j * 2 * M_PI / resolucao), a * cos(j * 2 * M_PI / resolucao) * b * cos(i * 2 * M_PI / resolucao), b * sin(i * 2 * M_PI / resolucao), b * sin((j + 1) * 2 * M_PI / resolucao), a * cos((j + 1) * 2 * M_PI / resolucao) * b * cos(i * 2 * M_PI / resolucao), b * sin(i * 2 * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", b * sin(j * 2 * M_PI / resolucao), a * cos(j * 2 * M_PI / resolucao) * b * cos(i * 2 * M_PI / resolucao), b * sin(i * 2 * M_PI / resolucao), b * sin(j * 2 * M_PI / resolucao), a * cos(j * 2 * M_PI / resolucao) * b * cos((i + 1) * 2 * M_PI / resolucao), b * sin((i + 1) * 2 * M_PI / resolucao));
            }
    }
