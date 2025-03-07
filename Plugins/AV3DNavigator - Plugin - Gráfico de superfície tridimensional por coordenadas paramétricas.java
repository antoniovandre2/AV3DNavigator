/*
Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)

AV3DNavigator: "https://github.com/antoniovandre2/AV3DNavigator".

Arquivo gerador de um espaço do AV3DNavigator superfície tridimensional por coordenadas paramétricas. Versão Java.

Argumentos: 1: primeiramente a string título e, após barra vertical "|", strings separadas por barra vertical "|" com campos separados por ponto e vírgula ";", composta da função em "U" e "V" para "x", função em "U" e "V" para "y", função em "U" e "V" para "z", o menor valor atribuído a "U", o maior valor atribuído a "U", o menor valor atribuído a "V", o maior valor atribuído a "V", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula ",". 2: "grid" apenas para grid ou "fill" para polígonos preenchidos. 3: a resolução.

Última atualização: 07-03-2025. Sem considerar alterações em variáveis globais.
*/

import org.mariuszgromada.math.mxparser.*;
import java.util.Arrays;
import java.io.*;

public class AV3DNgstcp
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
		String mainstring;
		String fillstring;
		String resstring;
		String titulo;
		String[] item = new String[MAXITENS];
		String[] funcaox = new String[MAXITENS];
		String[] funcaoy = new String[MAXITENS];
		String[] funcaoz = new String[MAXITENS];
		String[] rgb = new String[MAXITENS];
		String[] rgbs = new String[MAXITENS];
		String verifstr;
		int deslocamentorgb = 50;
		double[] menoresu = new double[MAXITENS];
		double[] maioresu = new double[MAXITENS];
		double[] menoresv = new double[MAXITENS];
		double[] maioresv = new double[MAXITENS];
		String mensagemerro = new String("Erro.\n\nArgumentos: 1: primeiramente a string título e, após barra vertical \"|\", strings separadas por barra vertical \"|\" com campos separados por ponto e vírgula \";\", composta da função em \"U\" e \"V\" para \"x\", função em \"U\" e \"V\" para \"y\", função em \"U\" e \"V\" para \"z\", o menor valor atribuído a \"U\", o maior valor atribuído a \"U\", o menor valor atribuído a \"V\", o maior valor atribuído a \"V\", e a cor RGB com os menores para vermelho, verde e azul separados por vírgula \",\". 2: \"grid\" apenas para grid ou \"fill\" para polígonos preenchidos. 3: a resolução.\n");
		int resolucao;

		if (args.length != 3) {System.out.println(mensagemerro); return;}

		mainstring = args[0];

		fillstring = args[1];

		if (! ((fillstring.equals("grid")) || (fillstring.equals("fill")))) {System.out.println(mensagemerro); return;}

		resstring = args[2];

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

			try {menoresu[argi] = Double.parseDouble(item[argi].split(";")[3]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			try {maioresu[argi] = Double.parseDouble(item[argi].split(";")[4]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			if (menoresu[argi] >= maioresu[argi]) {System.out.println(mensagemerro); return;}

			try {menoresv[argi] = Double.parseDouble(item[argi].split(";")[5]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			try {maioresv[argi] = Double.parseDouble(item[argi].split(";")[6]);} catch (Exception e) {System.out.println(mensagemerro); return;}

			if (menoresv[argi] >= maioresv[argi]) {System.out.println(mensagemerro); return;}

			rgb[argi] = item[argi].split(";")[7]; rgbs[argi] = "";

			for (i = 0; i < 3; i++)
				{
				verifstr = rgb[argi].split(",")[i];

				int verifstri;

				try {verifstri = Integer.parseInt(verifstr);} catch (Exception e) {System.out.println(mensagemerro); return;}

				if ((verifstri < 0) || (verifstri > 255)) {System.out.println(mensagemerro); return;}

				if (verifstri + deslocamentorgb <= 255)
					rgbs[argi] = rgbs[argi] + Integer.toString(verifstri + deslocamentorgb);
				else
					rgbs[argi] = rgbs[argi] + Integer.toString(verifstri - deslocamentorgb);

				if (i < 2) rgbs[argi] = rgbs[argi] + ",";
				}
			if (argi > MAXITENS) {System.out.println(mensagemerro); return;}
			}

		boolean isCallSuccessful = License.iConfirmNonCommercialUse("Av3DNavigator: \"https://github.com/antoniovandre2/AV3DNavigator\".");

		if (fillstring.equals("grid"))
			{
			for (i = 0; i < argi; i++)
				for (j = 0; j < resolucao + 1; j++)
					{
					String pontostrv;

					pontostrv = Double.toString(menoresv[i] + j * (maioresv[i] - menoresv[i]) / resolucao);

					for (k = 0; k < resolucao; k++)
						{
						String valorstr;
						String pontostru;

						pontostru =  Double.toString(menoresu[i] + k * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(";");

						pontostru = Double.toString(menoresu[i] + (k + 1) * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print("c" + rgb[i] + "|");
						}
					}

			for (i = 0; i < argi; i++)
				for (j = 0; j < resolucao + 1; j++)
					{
					String pontostru;

					pontostru = Double.toString(menoresu[i] + j * (maioresu[i] - menoresu[i]) / (resolucao - 1));

					for (k = 0; k < resolucao; k++)
						{
						String valorstr;
						String pontostrv;

						pontostrv =  Double.toString(menoresv[i] + k * (maioresv[i] - menoresv[i]) / resolucao);

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(";");

						pontostrv = Double.toString(menoresv[i] + (k + 1) * (maioresv[i] - menoresv[i]) / resolucao);

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print("c" + rgb[i] + "|");
						}
					}
			}

		System.out.print("@");

		if (fillstring.equals("fill"))
			{
			for (i = 0; i < argi; i++)
				for (j = 0; j < resolucao + 1; j++)
					for (k = 0; k < resolucao; k++)
						{
						String valorstr;
						String pontostrv;
						String pontostru;

						pontostrv = Double.toString(menoresv[i] + j * (maioresv[i] - menoresv[i]) / (resolucao - 1));

						pontostru =  Double.toString(menoresu[i] + k * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(";");

						pontostru = Double.toString(menoresu[i] + (k + 1) * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(";");

						pontostrv = Double.toString(menoresv[i] + (j + 1) * (maioresv[i] - menoresv[i]) / (resolucao - 1));

						pontostru =  Double.toString(menoresu[i] + (k + 1) * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(";");

						pontostru =  Double.toString(menoresu[i] + k * (maioresu[i] - menoresu[i]) / (resolucao - 1));

						valorstr = funcaox[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoy[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						System.out.print(",");

						valorstr = funcaoz[i].replaceAll("V", "(" + pontostrv + ")").replaceAll("U", "(" + pontostru + ")");

						expr = new Expression(valorstr);

						try {resultado = expr.calculate();} catch (Exception e) {System.out.println(mensagemerro); return;}

						System.out.print(resultado);

						if ((j + k) % 2 == 0)
							System.out.print("c" + rgb[i] + "|");
						else
							System.out.print("c" + rgbs[i] + "|");
						}
					}

		System.out.print("@" + titulo + "|_____|");

		for (i = 0; i < argi - 1; i++)
			System.out.print("x = " + funcaox[i] + ", y = " + funcaoy[i] + ", z = " + funcaoz[i] + ";" + rgb[i] + "|");

		return;
		}
	}