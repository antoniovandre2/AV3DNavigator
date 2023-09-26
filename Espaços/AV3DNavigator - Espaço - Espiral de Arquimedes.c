/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador do "AV3DNavigator - Espaço - Espiral de Arquimedes.txt".

Última atualização: 26-09-2023.
*/

#include <stdio.h>
#include <math.h>

int main ()
    {
    int resolucao = 100;
    float tamanhoraio = 4;
    float tamanhoangulo = 4;
    float raio = tamanhoraio / resolucao;
    float angulo = tamanhoangulo * 2 * (float) (M_PI) / resolucao;

    for (int i = 0; i < resolucao; i++)
        {
        printf("%f,%f,%f;", 0, i * raio * cos(i * angulo), i * raio * sin(i * angulo));
        printf("%f,%f,%f|", 0, (i + 1) * raio * cos((i + 1) * angulo), (i + 1) * raio * sin((i + 1) * angulo));
        }
    }