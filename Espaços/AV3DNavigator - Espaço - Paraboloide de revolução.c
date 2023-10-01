/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Paraboloide de revolução.txt".

Última atualização: 30-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    int resolucao = 10;
    float raio = 4;
    float a = 0.5;
    float c = -4;

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            if (i != 0) printf("%f,%f,%f;%f,%f,%f|", (i * raio / resolucao) * cos(j * 2 * M_PI / resolucao), (i * raio / resolucao) * sin(j * 2 * M_PI / resolucao), a * (i * raio / resolucao) * (i * raio / resolucao) + c, (i * raio / resolucao) * cos((j + 1) * 2 * M_PI / resolucao), (i * raio / resolucao) * sin((j + 1) * 2 * M_PI / resolucao), a * (i * raio / resolucao) * (i * raio / resolucao) + c);

            if (i != resolucao - 1) printf("%f,%f,%f;%f,%f,%f|", (i * raio / resolucao) * cos(j * 2 * M_PI / resolucao), (i * raio / resolucao) * sin(j * 2 * M_PI / resolucao), a * (i * raio / resolucao) * (i * raio / resolucao) + c, ((i + 1) * raio / resolucao) * cos(j * 2 * M_PI / resolucao), ((i + 1) * raio / resolucao) * sin(j * 2 * M_PI / resolucao), a * ((i + 1) * raio / resolucao) * ((i + 1) * raio / resolucao) + c);
            }
    }
