/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Tubo em espiral.txt".

Última atualização: 30-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    float raioprincipal = 10;
    float raiosecundario = 3;
    int resolucao = 18;
    int voltas = 2;
    float altura = 20;

    for (int i = 0; i < resolucao * voltas; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", raioprincipal * cos(i * 2 * M_PI / resolucao) + raiosecundario * cos(j * 2 * M_PI / resolucao) * cos(i * 2 * M_PI / resolucao), raioprincipal * sin(i * 2 * M_PI / resolucao) + raiosecundario * cos(j * 2 * M_PI / resolucao) * sin(i * 2 * M_PI / resolucao), i * altura / (resolucao * voltas) - altura / 2 + raiosecundario * sin(j * 2 * M_PI / resolucao), raioprincipal * cos(i * 2 * M_PI / resolucao) + raiosecundario * cos((j + 1) * 2 * M_PI / resolucao) * cos(i * 2 * M_PI / resolucao), raioprincipal * sin(i * 2 * M_PI / resolucao) + raiosecundario * cos((j + 1) * 2 * M_PI / resolucao) * sin(i * 2 * M_PI / resolucao), i * altura / (resolucao * voltas) - altura / 2 + raiosecundario * sin((j + 1) * 2 * M_PI / resolucao));
            }
    }