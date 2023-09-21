/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Esferas.txt".

Última atualização: 21-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    float raio = 5;
    int resolucao = 10;
    float shiftsegundaesferax = 10;
    float shiftsegundaesferay = -10;
    float shiftsegundaesferaz = 10;

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", raio * cos(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * sin(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * cos(i * M_PI / resolucao), raio * cos((j + 1) * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * sin((j + 1) * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * cos(i * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", raio * cos(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * sin(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), raio * cos(i * M_PI / resolucao), raio * cos(j * 2 * M_PI / resolucao) * sin((i + 1) * M_PI / resolucao), raio * sin(j * 2 * M_PI / resolucao) * sin((i + 1) * M_PI / resolucao), raio * cos((i + 1) * M_PI / resolucao));
            }

// Segunda esfera.

    for (int i = 0; i < resolucao; i++)
        for (int j = 0; j < resolucao; j++)
            {
            printf("%f,%f,%f;%f,%f,%f|", shiftsegundaesferax + raio * cos(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferay + raio * sin(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferaz + raio * cos(i * M_PI / resolucao), shiftsegundaesferax + raio * cos((j + 1) * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferay + raio * sin((j + 1) * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferaz + raio * cos(i * M_PI / resolucao));

            printf("%f,%f,%f;%f,%f,%f|", shiftsegundaesferax + raio * cos(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferay + raio * sin(j * 2 * M_PI / resolucao) * sin(i * M_PI / resolucao), shiftsegundaesferaz + raio * cos(i * M_PI / resolucao), shiftsegundaesferax + raio * cos(j * 2 * M_PI / resolucao) * sin((i + 1) * M_PI / resolucao), shiftsegundaesferay + raio * sin(j * 2 * M_PI / resolucao) * sin((i + 1) * M_PI / resolucao), shiftsegundaesferaz + raio * cos((i + 1) * M_PI / resolucao));
            }
    }