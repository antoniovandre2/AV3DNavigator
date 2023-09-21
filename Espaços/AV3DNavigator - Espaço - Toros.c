/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Toros.txt".

Última atualização: 21-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    float raioprincipal = 10;
    float raiosecundario = 3;
    int resolucao = 15;
    float shiftsegundotorox = 10;
    float shiftsegundotoroy = -10;
    float shiftsegundotoroz = 10;

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), raiosecundario * sin(i * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos((j + 1) * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin((j + 1) * 2 * M_PI / resolucao), raiosecundario * sin(i * 2 * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), raiosecundario * sin(i * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos((i + 1) * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), (raioprincipal + raiosecundario * cos((i + 1) * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), raiosecundario * sin((i + 1) * 2 * M_PI / resolucao));
            }

// Segundo toro.

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", shiftsegundotorox + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), shiftsegundotoroy + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), shiftsegundotoroz + raiosecundario * sin(i * 2 * M_PI / resolucao), shiftsegundotorox + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos((j + 1) * 2 * M_PI / resolucao), shiftsegundotoroy + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin((j + 1) * 2 * M_PI / resolucao), shiftsegundotoroz + raiosecundario * sin(i * 2 * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", shiftsegundotorox + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), shiftsegundotoroy + (raioprincipal + raiosecundario * cos(i * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), shiftsegundotoroz + raiosecundario * sin(i * 2 * M_PI / resolucao), shiftsegundotorox + (raioprincipal + raiosecundario * cos((i + 1) * 2 * M_PI / resolucao)) * cos(j * 2 * M_PI / resolucao), shiftsegundotoroy + (raioprincipal + raiosecundario * cos((i + 1) * 2 * M_PI / resolucao)) * sin(j * 2 * M_PI / resolucao), shiftsegundotoroz + raiosecundario * sin((i + 1) * 2 * M_PI / resolucao));
            }
    }