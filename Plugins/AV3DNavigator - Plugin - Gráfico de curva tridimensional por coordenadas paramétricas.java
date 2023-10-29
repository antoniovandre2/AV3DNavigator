/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

Av3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de uma curva tridimensional por coordenadas paramétricas. Versão Java.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "T" para "x", função em "T" para "y", função em "T" para "z", o menor valor atribuído a "T", o maior valor atribuído a "T", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 29-10-2023. Sem considerar alterações em variáveis globais.
*/

import org.mariuszgromada.math.mxparser.*;
import java.util.Arrays;
import java.io.*;

public class AV3DNgctcp
    {
    public static void main (String[] args)
        {
        int MAXITENS = 10;

        Expression expr;
        double resultado;

        int shift = 0;
        int inicio = 0;
        int argi = 0;
        int i;
        int j;
        String mainstring;
        String resstring;
        String titulo;
        String[] item = new String[MAXITENS];
        String[] funcaox = new String[MAXITENS];
        String[] funcaoy = new String[MAXITENS];
        String[] funcaoz = new String[MAXITENS];
        String[] rgb = new String[MAXITENS];
        String verifstr;
        double[] menores = new double[MAXITENS];
        double[] maiores = new double[MAXITENS];
        String mensagemerro = new String("Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"T\" para \"x\", função em \"T\" para \"y\", função em \"T\" para \"z\", o menor valor atribuído a \"T\", o maior valor atribuído a \"T\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n");
        int resolucao;

        if (args.length != 2) {System.out.println(mensagemerro); return;}

        mainstring = args[0];

        resstring = args[1];

        try {resolucao = Integer.parseInt(resstring);} catch (Exception e) {System.out.println(mensagemerro); return;}

        if (resolucao == 0) {System.out.println(mensagemerro); return;}

        titulo = mainstring.split("\\|")[0];

        item = mainstring.split("\\|");

        for (i = 0; i < item.length - 1; i++) item[i] = item[i+1]; Arrays.copyOf(item, item.length - 1);

        for (argi = 0; argi < item.length; argi++)
            {
            funcaox[argi] = item[argi].split(";")[0];
            funcaoy[argi] = item[argi].split(";")[1];
            funcaoz[argi] = item[argi].split(";")[2];

            try {menores[argi] = Double.parseDouble(item[argi].split(";")[3]);} catch (Exception e) {System.out.println(mensagemerro); return;}

            try {maiores[argi] = Double.parseDouble(item[argi].split(";")[4]);} catch (Exception e) {System.out.println(mensagemerro); return;}

            if (menores[argi] >= maiores[argi]) {System.out.println(mensagemerro); return;}

            rgb[argi] = item[argi].split(";")[5];

            for (i = 0; i < 3; i++)
                {
                verifstr = rgb[argi].split(",")[i];

                int verifstri;

                try {verifstri = Integer.parseInt(verifstr);} catch (Exception e) {System.out.println(mensagemerro); return;}

                if ((verifstri < 0) || (verifstri > 255)) {System.out.println(mensagemerro); return;}
                }

            if (argi > MAXITENS) {System.out.println(mensagemerro); return;}
            }

        boolean isCallSuccessful = License.iConfirmNonCommercialUse("Av3DNavigator: \"https://github.com/antoniovandre2/AV3DNavigator\".");

        for (i = 0; i < argi; i++)
            for (j = 0; j < resolucao - 1; j++)
                {
                String valorstr;
                String tempstr;
                String pontostr;

                pontostr =  Double.toString(menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1));

                valorstr = funcaox[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print(",");

                valorstr = funcaoy[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print(",");

                valorstr = funcaoz[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print(";");

                pontostr = Double.toString(menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1));

                valorstr = funcaox[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print(",");

                valorstr = funcaoy[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print(",");

                valorstr = funcaoz[i].replaceAll("T", "(" + pontostr + ")");

                expr = new Expression(valorstr);

                try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

                System.out.print(resultado);

                System.out.print("c" + rgb[i] + "|");
                }

        System.out.print("@@");

        System.out.print(titulo + "|_____|");

        for (i = 0; i < argi - 1; i++)
            System.out.print("x = " + funcaox[i] + ", y = " + funcaoy[i] + ", z = " + funcaoz[i] + ";" + rgb[i] + "|");

        return;
        }
    }