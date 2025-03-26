/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator gráfico de função. Versão Java.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "Y", o menor valor atribuído a "Y", o maior valor atribuído a "Y", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: a resolução.

Última atualização: 31-10-2023. Sem considerar alterações em variáveis globais.
*/


import org.mariuszgromada.math.mxparser.*;
import java.util.Arrays;
import java.io.*;

public class AV3DNgf
	{
	public static void main (String[] args)
		{
		int MAXITENS = 10;

		Expression expr;
		double resultado;

		int argi = 0;
		int i;
		int j;
		int k;
		int flag = 0;
		String mainstring;
		String resstring;
		String titulo;
		String[] item = new String[MAXITENS];
		String[] funcao = new String[MAXITENS];
		String[][] exclusaoarr = new String[MAXITENS][MAXITENS];
		String[] rgb = new String[MAXITENS];
		String verifstr;
		double[] menores = new double[MAXITENS];
		double[] maiores = new double[MAXITENS];
		double[][] exclusoes = new double[MAXITENS][MAXITENS];
		double margemexclusao = 0.1;
		String mensagemerro = new String("Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"y\", o menor valor atribuído a \"y\", o maior valor atribuído a \"y\", os pontos de exclusões no intervalo separados por vírgula, e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: a resolução.\n");
		int resolucao;

		if (args.length != 2) {System.out.println(mensagemerro); return;}

		mainstring = args[0];

		resstring = args[1];

		try {resolucao = Integer.parseInt(resstring);} catch (Exception e) {System.out.println(mensagemerro); return;}

		if (resolucao == 0) {System.out.println(mensagemerro); return;}

		try {resolucao = Integer.parseInt(resstring);} catch (Exception e) {System.out.println(mensagemerro); return;}

		if (resolucao == 0) {System.out.println(mensagemerro); return;}

		titulo = mainstring.split("\\|")[0];

		item = mainstring.split("\\|");

		for (i = 0; i < item.length - 1; i++) item[i] = item[i+1]; Arrays.copyOf(item, item.length - 1);

		for (argi = 0; argi < item.length; argi++)
			{
			funcao[argi] = item[argi].split(";")[0];

			try {menores[argi] = Double.parseDouble(item[argi].split(";")[1]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			try {maiores[argi] = Double.parseDouble(item[argi].split(";")[2]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			if (menores[argi] >= maiores[argi]) {System.out.println(mensagemerro); return;}

			exclusaoarr[argi] = item[argi].split(";")[3].split(",");

			for (i = 0; i < exclusaoarr[argi].length; i++)
				{
				try {exclusoes[argi][i] = Double.parseDouble(exclusaoarr[argi][i]);} catch (Exception e) {System.out.println(mensagemerro); return;}

				if ((exclusoes[argi][i] < menores[argi]) || (exclusoes[argi][i] > maiores[argi])) {System.out.println(mensagemerro); return;}
				}

			rgb[argi] = item[argi].split(";")[4];

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
				flag = 0;

				if (exclusoes[i] != null) for (k = 0; k < exclusoes[i].length; k++)
					if ((exclusoes[i][k] >= menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1) - margemexclusao) && (exclusoes[i][k] <= menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1) + margemexclusao))
						flag = 1;

				if (flag == 0)
					{
					String valorstr;
					String pontostr;

					System.out.print("0," + Double.toString(menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1)) + ",");

					pontostr = Double.toString(menores[i] + j * (maiores[i] - menores[i]) / (resolucao - 1));

					valorstr = funcao[i].replaceAll("Y", "(" + pontostr + ")");

					expr = new Expression(valorstr);

					try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

					System.out.print(resultado);

					System.out.print(";0," + Double.toString(menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1)) + ",");

					pontostr = Double.toString(menores[i] + (j + 1) * (maiores[i] - menores[i]) / (resolucao - 1));

					valorstr = funcao[i].replaceAll("Y", "(" + pontostr + ")");

					expr = new Expression(valorstr);

					try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

					System.out.print(resultado);

					System.out.print("c" + rgb[i] + "|");
					}
				}

		System.out.print("@@");

		System.out.print(titulo + "|_____|");

		for (i = 0; i < argi - 1; i++)
			System.out.print("x = 0, z = " + funcao[i] + ";" + rgb[i] + "|");
		}
	}