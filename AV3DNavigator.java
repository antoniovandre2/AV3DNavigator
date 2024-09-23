/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).
 * 
 * Software AV3DNavigator.
 * 
 * Dependências: AntonioVandre >= 20240921, Apfloat 1.14.0 (http://www.apfloat.org), mXparser v6.0.0 Picon (https://mathparser.org/).
 * 
 * Motor Gráfico: AV3D.
 * 
 * Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
 * 
 * Licença de uso: Creative Commons Attribution ShareAlike License V3.0.
 * 
 * Última atualização: 23-09-2024. Não considerando alterações em variáveis globais.
 */

import java.lang.IllegalThreadStateException;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.event.AWTEventListener;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import java.lang.Math;
import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;
import java.lang.ArithmeticException;

import javax.imageio.ImageIO;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.io.*;

// Alta precisão com o Apfloat, porém com custo computacional.

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.FixedPrecisionApfloatHelper;
import org.apfloat.InfiniteExpansionException;

// mXparser para cálculo de funções envolvendo parâmetros.

import org.mariuszgromada.math.mxparser.*;

import AntonioVandre.*;

public class AV3DNavigator extends JComponent
	{
	public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";
	public static String ArquivoAV3DNavigatorURL = "AV3DNavigatorURL.txt";
	public static String ArquivoAV3DNavigatorINI = "AV3DNavigator.ini";
	public static String ArquivoAV3DNavigatorAtribuicao = "AV3DNavigatorAtribuicao.txt";

	// Variáveis globais.

	public int CorJanelaR = 0; // Default: 0.
	public int CorJanelaG = 0; // Default: 0.
	public int CorJanelaB = 255; // Default: 255.
	public int CorJanelaGradienteR = 0; // Default: 0.
	public int CorJanelaGradienteG = 0; // Default: 0.
	public int CorJanelaGradienteB = 0; // Default: 0.
	public int CorFonteJanelaR = 255; // Default: 255.
	public int CorFonteJanelaG = 255; // Default: 255.
	public int CorFonteJanelaB = 255; // Default: 255.
	public int TamanhoPlanoX = 460; // Default: 460.
	public int TamanhoPlanoY = 460; // Default: 460.
	public static int TamanhoEspacoLabelStatus = 300; // Default: 300.
	public static int TamanhoEspacoLabelURL = 65; // Default: 65.
	public static int TamanhoEspacoHelpX = 800; // Default: 800.
	public static int TamanhoEspacoHelpY = 890; // Default: 890.
	public static int TamanhoEspacoInvalidoX = 300; // Default: 300.
	public static int TamanhoEspacoInvalidoY = 80; // Default: 80.
	public static int MinTamanhoPlanoX = 400; // Default: 400.
	public static String AV3DNavigatorIconFilePath = "AV3DNavigator - Logo - 200p.png";
	public double FatorAnguloVisao = 1; // Default: 1.

	public static double TetaMax = AntonioVandre.MaximoValorReal; // Opção: Math.PI / 3.
	public static double PhiMax = AntonioVandre.MaximoValorReal; // Opção: Math.PI / 3.

	public static double MargemAnguloVisao = 0; // Default: 0.
	public static int TamanhoFonteLabelStatus = 7; // Default: 7.
	public static int TamanhoFonteLabelURL = 11; // Default: 11.
	public static int TamanhoFonteLabelHelp = 10; // Default: 10.
	public static int TamanhoFonteLabelPrint = 12; // Default: 12.
	public static int TamanhoFonteLabelErroEspacoInvalido = 11; // Default: 11.
	public double DistanciaTela = 2; // Default: valor inicial: 2.
	public static String MensagemErroAntonioVandreLib = "Requer AntonioVandre >= 20231101.";
	public static String MensagemErroEspacoAusente = "Entre com um arquivo de espaço.";
	public static String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";
	public static double FatorMouseWheel = 3; // Default: 3.
	public static double DeslocamentoLinear = 1; // Default: 1.
	public static double DeslocamentoAngular = 0.1; // Default: 0.1.
	public static int FramesDeslocamento = 4; // Default: 4.
	public static int EspacamentoVerticalLegendas = 6; // Default: 6.
	public double Parametro0 = 0; // Default: valor inicial: 0.
	public double Parametro0Step = 1; // Default: valor inicial: 1.
	public double Parametro1 = 0; // Default: valor inicial: 0.
	public double Parametro1Step = 1; // Default: valor inicial: 1.
	public double Parametro2 = 0; // Default: valor inicial: 0.
	public double Parametro2Step = 1; // Default: valor inicial: 1.
	public double Parametro3 = 0; // Default: valor inicial: 0.
	public double Parametro3Step = 1; // Default: valor inicial: 1.
	public double Parametro4 = 0; // Default: valor inicial: 0.
	public double Parametro4Step = 1; // Default: valor inicial: 1.
	public double Parametro5 = 0; // Default: valor inicial: 0.
	public double Parametro5Step = 1; // Default: valor inicial: 1.
	public double Parametro6 = 0; // Default: valor inicial: 0.
	public double Parametro6Step = 1; // Default: valor inicial: 1.
	public double Parametro7 = 0; // Default: valor inicial: 0.
	public double Parametro7Step = 1; // Default: valor inicial: 1.
	public double Parametro8 = 0; // Default: valor inicial: 0.
	public double Parametro8Step = 1; // Default: valor inicial: 1.
	public double Parametro9 = 0; // Default: valor inicial: 0.
	public double Parametro9Step = 1; // Default: valor inicial: 1.
	public double CameraMovePar = 0; // Default: valor inicial: 0.
	public double CameraMoveParStep = 1; // Default: valor inicial: 1.
	public int PrecisaoApfloat = 20; // Default: 20.
	public int LabelAnimado = 1;
	public int PrintR;
	public int PrintG;
	public int PrintB;
	public long CiclesTimeIgn = 100; // Default: 100.

	// Variáveis de funcionamento interno.

	JPanel LabelStatusLabelURLPanel;
	GradientLabel LabelHelp;
	public String Versao;
	public String URL;
	public String AtribuicaoString;
	public String INI = new String("");
	public int FlagINI = 0;
	public int FlagMostrarLabel = 1;
	public int ValorInteiro;
	public int ValorInteiro1;
	public int ValorInteiro2;
	public int CorrecaoX = 8;
	public int CorrecaoY = 0;
	public int CorrecaoXF = 15;
	public int CorrecaoYF = 0;
	public int MinTamanhoPlanoYMaisLabels = TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL;
	public double RaioRot = 0;
	public double RaioTeta = 0;
	public double RaioPhi = 0;
	double ProdutoEscalar;
	double ProdutoEscalaro;
	double ProdutoEscalard;
	public double AnguloVisao;
	String TriangulosString = "";
	public int xi;
	public int yi;
	public int xf;
	public int yf;
	public int xpp;
	public int ypp;
	public double AspectRatio;
	public int ShiftVerticalLegendas = 25; // Default: valor inicial: 25;
	public int TamanhoFonteLegendas = 12; // Default: valor inicial: 12.
	public int ResolucaoTriangulos = 10; // Default: valor inicial: 10. Considerável custo computacional para valores elevados.
	public int FlagCoordRot = 0;
	public int FlagCoordRotHor = 0;
	public int FlagCoordRotVert = 0;
	public int FlagTetaSuperior = 0;
	public int FlagTetaInferior = 0;
	public int FlagPhiSuperior = 0;
	public int FlagPhiInferior = 0;
	public int Sair = 0;
	public int StretchFlag = 1;
	public int ApfloatFlag = 0;
	public int TrianguloPoligono = 1;
	public String Espaco;
	public int FlagAlteracaoStatus = 1;
	public int MaxTentativasCores = Integer.MAX_VALUE;
	public long CameraId = -1;
	public int SleepTime = 7; // Default: valor inicial: 7.

	public double ParametroFile0 = 0; // Default: valor inicial: 0.
	public double ParametroFile1 = 0; // Default: valor inicial: 0.
	public double ParametroFile2 = 0; // Default: valor inicial: 0.
	public double ParametroFile3 = 0; // Default: valor inicial: 0.
	public double ParametroFile4 = 0; // Default: valor inicial: 0.
	public double ParametroFile5 = 0; // Default: valor inicial: 0.
	public double ParametroFile6 = 0; // Default: valor inicial: 0.
	public double ParametroFile7 = 0; // Default: valor inicial: 0.
	public double ParametroFile8 = 0; // Default: valor inicial: 0.
	public double ParametroFile9 = 0; // Default: valor inicial: 0.

	public int ParametroTimeS = Integer.parseInt(new SimpleDateFormat("ss").format(Calendar.getInstance().getTime()));
	public int ParametroTimeM = Integer.parseInt(new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()));
	public int ParametroTimeH = Integer.parseInt(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()));
	public int FlagTime = 0;
	public long ContadorTime = 0;

	public long ValorInteiroLong;
	public int i;
	public int j;
	public int k;
	public int l;
	public double x = 0;
	public double y = 0;
	public double z = 0;
	public double Teta = 0;
	public double Phi = 0;
	public double Rot = 0;
	public double Rotacao = Math.PI;
	public double Phi0Rotacao;
	public double RotacaoTeta = Math.PI + Teta;
	public double RotacaoPhi = Math.PI + Phi;
	public double xRotacaoTeta = x + RaioTeta * Math.cos(Teta) * Math.cos(Phi);
	public double yRotacaoTeta = y + RaioTeta * Math.sin(Teta) * Math.cos(Phi);
	public double xRotacaoPhi = x + RaioPhi * Math.cos(Teta) * Math.cos(Phi);
	public double yRotacaoPhi = y + RaioPhi * Math.sin(Teta) * Math.cos(Phi);
	public double zRotacaoPhi = z + RaioPhi * Math.sin(Phi);
	public double xRotacao = x + RaioRot * Math.cos(Teta) * Math.cos(Phi);
	public double yRotacao = y + RaioRot * Math.sin(Teta) * Math.cos(Phi);
	public double zRotacao = z + RaioRot * Math.sin(Phi);
	public double xt = x;
	public double yt = y;
	public double zt = z;
	public double Tetat = Teta;
	public double Phit = Phi;
	public double Rott = Rot;
	public double IntervaloX = 0;
	public double IntervaloY = 0;
	public double IntervaloZ = 0;
	public double IntervaloTeta = 0;
	public double IntervaloPhi = 0;
	public double IntervaloRot = 0;
	public int MouseDown = 0;
	public int FlagMouseDownArea = 0;
	public int MouseX;
	public int MouseY;
	public int MouseXR;
	public int MouseYR;
	public double TetaR;
	public double PhiR;
	public int TamanhoPlanoXAd;
	public int TamanhoPlanoYAd;
	public int ContadorFrames = FramesDeslocamento;
	public Color CorBackground;
	public Color CorLinha;
	public Color CorTrianguloShape;
	public Color CorLegenda;
	public int CorLinhaRed = 255; // Default inicial: 255.
	public int CorLinhaGreen = 255; // Default inicial: 255.
	public int CorLinhaBlue = 255; // Default inicial: 255.
	public int CorBackgroundRed = 0; // Default inicial: 0.
	public int CorBackgroundGreen = 0; // Default inicial: 0.
	public int CorBackgroundBlue = 0; // Default inicial: 0.
	public int CorTrianguloShapeRed = 0; // Default inicial: 0.
	public int CorTrianguloShapeGreen = 0; // Default inicial: 0.
	public int CorTrianguloShapeBlue = 255; // Default inicial: 255.
	public int CorLegendaRed = 255; // Default inicial: 255.
	public int CorLegendaGreen = 255; // Default inicial: 255.
	public int CorLegendaBlue = 255; // Default inicial: 255.
	public int FlagPrintInit = 0;
	public String StringCores = "";
	public double SomaXP = 0;
	public double SomaYP = 0;
	public double SomaZP = 0;
	public int TotalLinhas = 0;
	public int TotalTriangulosShapePreenchidos = 0;
	public int TotalLegendas = 0;
	public double FlagMouseY = 1;
	public int TEspaco;
	public int Tpaint;
	public int FlagRepaint = 0;
	public int Pontoslength;
	public int dxGLS = 0;
	public int dyGLS = 0;
	public int fxGLS;
	public int fyGLS;
	public int dxGLH = 0;
	public int dyGLH = 0;
	public int fxGLH;
	public int fyGLH;
	public int FlagHelp = 0;

	// Threads.

	public Thread AV3DNavigatorExecCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorExecCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public Thread AV3DNavigatorEspacosPCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorEspacosPCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public Thread AV3DNavigatorEspacosPFileCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorEspacosPFileCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public Thread AV3DNavigatorEspacosPTimeCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorEspacosPTimeCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public Thread AV3DNavigatorApfloatCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorApfloatCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public Thread AV3DNavigatorHelpCountThread = new Thread () {
		public void run ()
			{
			try
				{
				URL ExecUrl = new URL("https://github.com/antoniovandre2/AV3DNavigator/releases/download/AV3DNavigatorStatsTag/AV3DNavigatorHelpCount");
				BufferedReader in = new BufferedReader(new InputStreamReader(ExecUrl.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null);
				in.close();
				} catch (IOException e) {}
			}
		};

	public class GradientLabel extends JLabel
		{
		private Color CorInicial;
		private Color CorFinal;
		private int id;

		public GradientLabel(String Texto)
			{
			super(Texto);

			CorInicial = new Color(CorJanelaR, CorJanelaG, CorJanelaB);
			CorFinal = Color.BLACK;
			this.setForeground(Color.WHITE);
			}

		public GradientLabel(String Texto, Color CorInicial, Color CorFinal)
			{
			super(Texto);
			this.CorInicial = CorInicial;
			this.CorFinal = CorFinal;
			this.setForeground(Color.WHITE);
			}

		public GradientLabel(String Texto, Color CorInicial, Color CorFinal, Color CorForeground)
			{
			super(Texto);
			this.CorInicial = CorInicial;
			this.CorFinal = CorFinal;
			this.setForeground(CorForeground);
			}

		public GradientLabel(String Texto, Color CorInicial, Color CorFinal, Color CorForeground, int idGL)
			{
			super(Texto);
			this.CorInicial = CorInicial;
			this.CorFinal = CorFinal;
			this.setForeground(CorForeground);
			id = idGL;
			}


		public void paint(Graphics g)
			{
			int width = getWidth();
			int height = getHeight();

			GradientPaint paint = null;

			switch (id)
				{
				case 1:
					if (FlagMostrarLabel == 1)
						{
						if (LabelAnimado == 1)
							{
							if ((dxGLS == 0) && (dyGLS == 0)) {fxGLS = 1; fyGLS = 0;}
							if ((dxGLS == width) && (dyGLS == 0)) {fxGLS = 0; fyGLS = 1;}
							if ((dxGLS == width) && (dyGLS == height)) {fxGLS = -1; fyGLS = 0;}
							if ((dxGLS == 0) && (dyGLS == height)) {fxGLS = 0; fyGLS = -1;}

							dxGLS += fxGLS; dyGLS += fyGLS;
							}

						paint = new GradientPaint(dxGLS, dyGLS, CorInicial, width - dxGLS, height - dyGLS, CorFinal, true);
						}

					break;

				case 2:
					if (LabelAnimado == 1)
						{
						if ((dxGLH == 0) && (dyGLH == 0)) {fxGLH = 1; fyGLH = 0;}
						if ((dxGLH == width) && (dyGLH == 0)) {fxGLH = 0; fyGLH = 1;}
						if ((dxGLH == width) && (dyGLH == height)) {fxGLH = -1; fyGLH = 0;}
						if ((dxGLH == 0) && (dyGLH == height)) {fxGLH = 0; fyGLH = -1;}

						dxGLH += fxGLH; dyGLH += fyGLH;
						}

					paint = new GradientPaint(dxGLH, dyGLH, CorInicial, width - dxGLH, height - dyGLH, CorFinal, true);
					break;

				default:
					paint = new GradientPaint(0, 0, CorInicial, width, height, CorFinal, true);

					break;
				}

			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(paint);
			g2d.fillRect(0, 0, width, height);
			super.paint(g);
			}
		}

	private static class LineType extends Object
		{
		int x1; 
		int y1;
		int x2;
		int y2;
		Color color;

		public LineType(int x1, int y1, int x2, int y2, Color color)
			{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
			}			   
		}

	private static class TrianguloType extends Object
		{
		int x1; 
		int y1;
		int x2;
		int y2;
		int x3;
		int y3;
		Color color;
		double zbuffer;

		public TrianguloType(int x1, int y1, int x2, int y2, int x3, int y3, Color color, double zbuffer)
			{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.x3 = x3;
			this.y3 = y3;
			this.color = color;
			this.zbuffer = zbuffer;
			}			   
		}

	private static class TextoType extends Object
		{
		String texto;
		int x;
		int y;
		Color color;
		int tamanho;

		public TextoType(String texto, int x, int y, Color color, int tamanho)
			{
			this.texto = texto;
			this.x = x;
			this.y = y;
			this.color = color;
			this.tamanho = tamanho;
			}			   
		}

	public LinkedList<LineType> Linhas = new LinkedList<LineType>();
	public LinkedList<TrianguloType> TriangulosShape = new LinkedList<TrianguloType>();
	public LinkedList<TextoType> Textos = new LinkedList<TextoType>();

	public void addLine(int x1, int x2, int x3, int x4, Color color, int n)
		{
		Linhas.add(new LineType(x1, x2, x3, x4, color));

		// Último repaint para desenhar.

		if (n == Integer.MAX_VALUE) repaint();
		}

	public void addTriangulosShape(int x1, int y1, int x2, int y2, int x3, int y3, Color color, double zbuffer, int n)
		{
		TriangulosShape.add(new TrianguloType(x1, y1, x2, y2, x3, y3, color, zbuffer));

		// Último repaint para desenhar.

		if (n == Integer.MAX_VALUE) repaint();
		}

	public void addTexto(String texto, int x, int y, Color color, int tamanho, int n)
		{
		Textos.add(new TextoType(texto, x, y, color, tamanho));

		// Último repaint para desenhar.

		if (n == Integer.MAX_VALUE) repaint();
		}

	public void clearObjects()
		{Linhas.clear(); TriangulosShape.clear(); Textos.clear();}

	public void paintComponent(Graphics g)
		{
		try
			{
			/* Algoritmo alternativo para reordenação dos polígonos.

			int TamTriangulosShape = TriangulosShape.size();

			for (i = 0; i < TamTriangulosShape; i++)
				for (j = i + 1; j < TamTriangulosShape; j++)
					if (TriangulosShape.get(j).zbuffer > TriangulosShape.get(i).zbuffer)
						{
						TrianguloType TrianguloTemp = TriangulosShape.get(j);
						TriangulosShape.set(j, TriangulosShape.get(i));
						TriangulosShape.set(i, TrianguloTemp);
						}
			*/

			Collections.sort(TriangulosShape, new Comparator<TrianguloType>()
				{
				public int compare(TrianguloType o1, TrianguloType o2)
					{return ((int) (o2.zbuffer) - (int) (o1.zbuffer));}
				});

			Tpaint = Linhas.size();

			for (i = 0; i < Tpaint; i++)
				{
				LineType Linha = Linhas.get(i);

				g.setColor(Linha.color);
				g.drawLine(Linha.x1, Linha.y1, Linha.x2, Linha.y2);
				}

			Tpaint = Textos.size();

			for (i = 0; i < Tpaint; i++)
				{
				TextoType Texto = Textos.get(i);

				g.setColor(Texto.color);
				g.setFont(new Font("SansSerif", Font.PLAIN, Texto.tamanho));
				g.drawString(Texto.texto, Texto.x, Texto.y);
				}

			Tpaint = TriangulosShape.size();

			for (i = 0; i < Tpaint; i++)
				{
				TrianguloType Triangulo = TriangulosShape.get(i);

				g.setColor(Triangulo.color);

				if (TrianguloPoligono == 0)
					{
					for (j = 0; j < ResolucaoTriangulos; j++)
						{
						g.drawLine((int) Triangulo.x1, (int) Triangulo.y1, (int) (Triangulo.x2 + j * (Triangulo.x3 - Triangulo.x2) / ResolucaoTriangulos), (int) (Triangulo.y2 + j * (Triangulo.y3 - Triangulo.y2) / ResolucaoTriangulos));

						g.drawLine((int) Triangulo.x2, (int) Triangulo.y2, (int) (Triangulo.x1 + j * (Triangulo.x3 - Triangulo.x1) / ResolucaoTriangulos), (int) (Triangulo.y1 + j * (Triangulo.y3 - Triangulo.y1) / ResolucaoTriangulos));

						g.drawLine((int) Triangulo.x3, (int) Triangulo.y3, (int) (Triangulo.x2 + j * (Triangulo.x1 - Triangulo.x2) / ResolucaoTriangulos), (int) (Triangulo.y2 + j * (Triangulo.y1 - Triangulo.y2) / ResolucaoTriangulos));
						}
					}
				else
					g.fillPolygon(new int[]{Triangulo.x1, Triangulo.x2, Triangulo.x3},new int[]{Triangulo.y1, Triangulo.y2, Triangulo.y3}, 3);
				}
			} catch (NoSuchElementException | ConcurrentModificationException | NullPointerException | IndexOutOfBoundsException e) {}
		}

	public static void main (String[] args) {AV3DNavigator mainc = new AV3DNavigator(); if (args.length == 1) {if (args[0].equals("Debug")) mainc.mainrun("", "Debug"); else mainc.mainrun(args[0], "");} else if (args.length == 2) mainc.mainrun(args[0], args[1]); else mainc.mainrun("", "");}

	public void mainrun (String ArquivoEspaco, String Debug)
		{
		Versao = "Versão desconhecida.";
		URL = "URL desconhecida.";
		AtribuicaoString = "Antonio Vandré's AV3DNavigator.|bit.ly/antoniovandre_legadoontologico";

		try
			{
			ValorInteiroLong = Long.parseLong(String.valueOf(AntonioVandre.Versao));
			}
		catch (NumberFormatException e)
			{
			System.out.println(MensagemErroAntonioVandreLib);
			return;
			}

		if (AntonioVandre.Versao < 20240921)
			{
			System.out.println(MensagemErroAntonioVandreLib);
			return;
			}

		File fileVersao = new File(ArquivoAV3DNavigatorVersao);

		try
			{
			BufferedReader brVersao = new BufferedReader(new FileReader(fileVersao));

			do {Versao = brVersao.readLine();} while (((Versao.replaceAll(" ", "").equals(""))) || (Versao.replaceAll(" ", "").charAt(0) == '#'));
			} catch (IOException e) {}

		File fileURL = new File(ArquivoAV3DNavigatorURL);

		try
			{
			BufferedReader brURL = new BufferedReader(new FileReader(fileURL));
			URL = brURL.readLine();
			} catch (IOException e) {}

		File fileAtribuicao = new File(ArquivoAV3DNavigatorAtribuicao);

		try
			{
			BufferedReader brAtribuicao = new BufferedReader(new FileReader(fileAtribuicao));
			AtribuicaoString = brAtribuicao.readLine();
			} catch (IOException e) {}

		File fileINI = new File(ArquivoAV3DNavigatorINI);

		try
			{
			BufferedReader brINI = new BufferedReader(new FileReader(fileINI));
			String linha = null;
			while ((linha = brINI.readLine()) != null)
				INI = INI + "\n" + linha;
			} catch (IOException e) {}

		if (! ArquivoEspaco.equals(""))
			{
			Espaco = LerEspaco(ArquivoEspaco, Debug);

			if (Espaco.equals("Erro"))
				{
				System.out.println(MensagemErroEspacoInvalido);
				return;
				}
			}
		else
			Espaco = "";

		if (! (Debug.equals("Debug"))) AV3DNavigatorExecCountThread.start();

		if (! INI.equals(""))
			{
			String[] INIarr = INI.split("\\r?\\n");
			String[] Cores;

			for (i = 0; i < INIarr.length; i++)
				if (! INIarr[i].equals(""))
					if (INIarr[i].replaceAll(" ", "").charAt(0) != '#')
						{
						String[] INIelements = INIarr[i].split("=");

						if (INIelements.length == 2)
							switch (INIelements[0].replaceAll(" ", ""))
								{
								case "x":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										x = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										xt = x;
										FlagINI = 1;
										}

									break;

								case "y":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										y = -Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										yt = y;
										FlagINI = 1;
										}

									break;

								case "z":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										z = -Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										zt = z;
										FlagINI = 1;
										}

									break;

								case "Teta":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Teta = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										Tetat = Teta;
										FlagINI = 1;
										}

									break;

								case "Phi":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Phi = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										Phit = Phi;
										FlagINI = 1;
										}

									break;

								case "Rot":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Rot = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										Rott = Rot;
										FlagINI = 1;
										}

									break;

								case "RaioTeta":
									if (AntonioVandre.NumeroRealNaoNegativo(INIelements[1].replaceAll(" ", "")))
										{
										RaioTeta = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "RotacaoTeta":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										RotacaoTeta = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "RaioPhi":
									if (AntonioVandre.NumeroRealNaoNegativo(INIelements[1].replaceAll(" ", "")))
										{
										RaioPhi = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "RotacaoPhi":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										RotacaoPhi = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "DistanciaTela":
									if (AntonioVandre.NumeroRealPositivo(INIelements[1].replaceAll(" ", "")))
										{
										DistanciaTela = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "FatorAnguloVisao":
									if (AntonioVandre.NumeroRealPositivo(INIelements[1].replaceAll(" ", "")))
										{
										FatorAnguloVisao = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "MargemAnguloVisao":
									if (AntonioVandre.NumeroRealNaoNegativo(INIelements[1].replaceAll(" ", "")))
										{
										MargemAnguloVisao = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Stretch":
									if (AntonioVandre.NumeroInteiro(INIelements[1].replaceAll(" ", "")))
										{
										ValorInteiro = Integer.parseInt(INIelements[1].replaceAll(" ", ""));

										if ((ValorInteiro == 0) || (ValorInteiro == 1))
											{
											StretchFlag = ValorInteiro;
											FlagINI = 1;
											}
										}

								case "Apfloat":
									if (AntonioVandre.NumeroInteiro(INIelements[1].replaceAll(" ", "")))
										{
										ValorInteiro = Integer.parseInt(INIelements[1].replaceAll(" ", ""));

										if ((ValorInteiro == 0) || (ValorInteiro == 1))
											{
											ApfloatFlag = ValorInteiro;
											FlagINI = 1;
											}
										}

									break;

								case "fillPolygon":
									if (AntonioVandre.NumeroInteiro(INIelements[1].replaceAll(" ", "")))
										{
										ValorInteiro = Integer.parseInt(INIelements[1].replaceAll(" ", ""));

										if ((ValorInteiro == 0) || (ValorInteiro == 1))
											{
											TrianguloPoligono = ValorInteiro;
											FlagINI = 1;
											}
										}

									break;

								case "ResolucaoTriangulos":
									if (AntonioVandre.NumeroNaturalPositivo(INIelements[1].replaceAll(" ", "")))
										{
										ResolucaoTriangulos = Integer.parseInt(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "SleepTime":
									if (AntonioVandre.NumeroNaturalPositivo(INIelements[1].replaceAll(" ", "")))
										{
										SleepTime = Integer.parseInt(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "CorJanela":
									Cores = INIelements[1].replaceAll(" ", "").split(",");

									if (Cores.length == 3)
										if ((AntonioVandre.NumeroNatural(Cores[0].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[1].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[2].replaceAll(" ", ""))))
											{
											ValorInteiro = Integer.parseInt(Cores[0].replaceAll(" ", ""));

											ValorInteiro1 = Integer.parseInt(Cores[1].replaceAll(" ", ""));

											ValorInteiro2 = Integer.parseInt(Cores[2].replaceAll(" ", ""));

											if ((ValorInteiro >= 0) && (ValorInteiro <= 255) && (ValorInteiro1 >= 0) && (ValorInteiro1 <= 255) && (ValorInteiro2 >= 0) && (ValorInteiro2 <= 255))
												{
												CorJanelaR = ValorInteiro;
												CorJanelaG = ValorInteiro1;
												CorJanelaB = ValorInteiro2;
												FlagINI = 1;
												}
											}

									break;

								case "CorFonteJanela":
									Cores = INIelements[1].replaceAll(" ", "").split(",");

									if (Cores.length == 3)
										if ((AntonioVandre.NumeroNatural(Cores[0].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[1].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[2].replaceAll(" ", ""))))
											{
											ValorInteiro = Integer.parseInt(Cores[0].replaceAll(" ", ""));

											ValorInteiro1 = Integer.parseInt(Cores[1].replaceAll(" ", ""));

											ValorInteiro2 = Integer.parseInt(Cores[2].replaceAll(" ", ""));

											if ((ValorInteiro >= 0) && (ValorInteiro <= 255) && (ValorInteiro1 >= 0) && (ValorInteiro1 <= 255) && (ValorInteiro2 >= 0) && (ValorInteiro2 <= 255))
												{
												CorFonteJanelaR = ValorInteiro;
												CorFonteJanelaG = ValorInteiro1;
												CorFonteJanelaB = ValorInteiro2;
												FlagINI = 1;
												}
											}

									break;

								case "CorJanelaGradiente":
									Cores = INIelements[1].replaceAll(" ", "").split(",");

									if (Cores.length == 3)
										if ((AntonioVandre.NumeroNatural(Cores[0].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[1].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[2].replaceAll(" ", ""))))
											{
											ValorInteiro = Integer.parseInt(Cores[0].replaceAll(" ", ""));

											ValorInteiro1 = Integer.parseInt(Cores[1].replaceAll(" ", ""));

											ValorInteiro2 = Integer.parseInt(Cores[2].replaceAll(" ", ""));

											if ((ValorInteiro >= 0) && (ValorInteiro <= 255) && (ValorInteiro1 >= 0) && (ValorInteiro1 <= 255) && (ValorInteiro2 >= 0) && (ValorInteiro2 <= 255))
												{
												CorJanelaGradienteR = ValorInteiro;
												CorJanelaGradienteG = ValorInteiro1;
												CorJanelaGradienteB = ValorInteiro2;
												FlagINI = 1;
												}
											}

									break;

								case "CorPrint":
									Cores = INIelements[1].replaceAll(" ", "").split(",");

									if (Cores.length == 3)
										if ((AntonioVandre.NumeroNatural(Cores[0].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[1].replaceAll(" ", ""))) && (AntonioVandre.NumeroNatural(Cores[2].replaceAll(" ", ""))))
											{
											ValorInteiro = Integer.parseInt(Cores[0].replaceAll(" ", ""));

											ValorInteiro1 = Integer.parseInt(Cores[1].replaceAll(" ", ""));

											ValorInteiro2 = Integer.parseInt(Cores[2].replaceAll(" ", ""));

											if ((ValorInteiro >= 0) && (ValorInteiro <= 255) && (ValorInteiro1 >= 0) && (ValorInteiro1 <= 255) && (ValorInteiro2 >= 0) && (ValorInteiro2 <= 255))
												{
												PrintR = ValorInteiro;
												PrintG = ValorInteiro1;
												PrintB = ValorInteiro2;
												FlagPrintInit = 1;
												FlagINI = 1;
												}
											}

									break;

								case "Parametro0":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro0 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro0Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro0Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro1":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro1 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro1Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro1Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro2":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro2 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro2Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro2Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro3":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro3 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro3Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro3Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro4":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro4 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro4Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro4Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro5":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro5 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro5Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro5Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro6":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro6 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro6Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro6Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro7":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro7 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro7Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro7Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro8":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro8 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro8Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro8Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro9":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro9 = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "Parametro9Step":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										Parametro9Step = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "CameraMovePar":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										CameraMovePar = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "CameraMoveParStep":
									if (AntonioVandre.NumeroReal(INIelements[1].replaceAll(" ", "")))
										{
										CameraMoveParStep = Double.parseDouble(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								case "LabelAnimado":
									if (AntonioVandre.NumeroInteiro(INIelements[1].replaceAll(" ", "")))
										{
										ValorInteiro = Integer.parseInt(INIelements[1].replaceAll(" ", ""));

										if ((ValorInteiro == 0) || (ValorInteiro == 1))
											{
											LabelAnimado = ValorInteiro;
											FlagINI = 1;
											}
										}

									break;

								case "PrecisaoApfloat":
									if (AntonioVandre.NumeroNaturalPositivo(INIelements[1].replaceAll(" ", "")))
										{
										PrecisaoApfloat = Integer.parseInt(INIelements[1].replaceAll(" ", ""));
										FlagINI = 1;
										}

									break;

								default:
									break;
								}
						}
			}

		// Licenciamento do mXparser.

		boolean isCallSuccessful = License.iConfirmNonCommercialUse("Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico)");

		// Helper do Apfloat definindo a precisão do retorno de todas as funções.

		FixedPrecisionApfloatHelper ApfloatHelper = new FixedPrecisionApfloatHelper(PrecisaoApfloat);

		JFrame FrameEspaco;

		if (Debug.equals("Debug")) FrameEspaco = new JFrame("AV3DNavigator - " + Versao + " - Debug"); else FrameEspaco = new JFrame("AV3DNavigator - " + Versao);

		FrameEspaco.setIconImage(new ImageIcon(getClass().getResource(AV3DNavigatorIconFilePath)).getImage());
		FrameEspaco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
		FrameEspaco.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
		AV3DNavigator Comp = new AV3DNavigator();
		Comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
		Comp.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
		FrameEspaco.getContentPane().add(Comp, BorderLayout.PAGE_START);
		GradientLabel LabelStatus = new GradientLabel("", new Color(CorJanelaR, CorJanelaG, CorJanelaB), new Color(CorJanelaGradienteR, CorJanelaGradienteG, CorJanelaGradienteB), new Color(CorFonteJanelaR, CorFonteJanelaG, CorFonteJanelaB), 1);;
		LabelStatus.setBorder(new EmptyBorder(5, 5, 5, 5));
		LabelStatus.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelStatus));
		GradientLabel LabelURL = new GradientLabel("<html>" + URL + "</html>", Color.WHITE, Color.BLACK, Color.BLUE, 0);
		LabelURL.setBorder(new EmptyBorder(5, 5, 5, 5));
		LabelURL.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, TamanhoFonteLabelURL));
		LabelStatus.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoEspacoLabelStatus));
		LabelStatus.setSize(new Dimension(TamanhoPlanoX, TamanhoEspacoLabelStatus));
		LabelStatusLabelURLPanel = new JPanel(new GridBagLayout());
		GridBagConstraints GridBagConstraintsLabelStatusLabelURL = new GridBagConstraints();
		GridBagConstraintsLabelStatusLabelURL.gridx = 0;
		GridBagConstraintsLabelStatusLabelURL.gridy = 0;
		GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.BOTH;
		GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
		GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelStatus;
		LabelStatusLabelURLPanel.add(LabelStatus, GridBagConstraintsLabelStatusLabelURL);
		GridBagConstraintsLabelStatusLabelURL.gridx = 0;
		GridBagConstraintsLabelStatusLabelURL.gridy = 1;
		GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.BOTH;
		GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
		GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelURL;
		LabelStatusLabelURLPanel.add(LabelURL, GridBagConstraintsLabelStatusLabelURL);
		FrameEspaco.getContentPane().add(LabelStatusLabelURLPanel);

		FrameEspaco.addMouseListener(new MouseListener()
			{
			public void mousePressed(MouseEvent MouseEvento)
				{
				MouseXR = MouseX;
				MouseYR = MouseY;
				TetaR = Teta;
				PhiR = Phi;
				MouseDown = 1;
				if ((MouseX > 0) && (MouseX <= TamanhoPlanoX) && (MouseY > FrameEspaco.getInsets().top) && (MouseY <= TamanhoPlanoY + FrameEspaco.getInsets().top)) FlagMouseDownArea = 1;
				}

			public void mouseClicked(MouseEvent MouseEvento) {}
			public void mouseEntered(MouseEvent MouseEvento) {}
			public void mouseExited(MouseEvent MouseEvento) {}
			public void mouseReleased(MouseEvent MouseEvento) {ContadorFrames = FramesDeslocamento; MouseDown = 0; FlagMouseDownArea = 0; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0;}
			public void mouseDragged(MouseEvent MouseEvento) {}
			public void mouseMoved(MouseEvent MouseEvento) {}
			});

		FrameEspaco.addMouseWheelListener(e -> {
			if ((MouseX > 0) && (MouseX <= TamanhoPlanoX) && (MouseY > FrameEspaco.getInsets().top) && (MouseY <= TamanhoPlanoY + FrameEspaco.getInsets().top))
				{
				if ((Math.abs(x - FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.cos(Phi) * Math.cos(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y + FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.cos(Phi) * Math.sin(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z + FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.sin(Phi)) >= AntonioVandre.MaximoValorReal))
					VariavelLimiteAtingido();
				else
					{
					x -= FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.cos(Phi) * Math.cos(Teta);

					y += FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.cos(Phi) * Math.sin(Teta);

					z += FlagMouseY * FatorMouseWheel * e.getWheelRotation() * Math.sin(Phi);

					xt = x; yt = y; zt = z;

					FlagAlteracaoStatus = 1;
					FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;
					}
				}
			});

		FrameEspaco.addKeyListener(new KeyListener()
			{
			public void keyPressed(KeyEvent ke)
				{
				if (ContadorFrames == FramesDeslocamento)
					{
					int keyCode = ke.getKeyCode();

					if (keyCode == KeyEvent.VK_ESCAPE)
						Sair = 1;

					if (keyCode == KeyEvent.VK_SPACE)
						{
						FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						x = 0;
						y = 0;
						z = 0;
						Teta = 0;
						Phi = 0;
						Rot = 0;
						TamanhoFonteLegendas = 12;
						ShiftVerticalLegendas = 25;
						RotacaoTeta = Teta + Math.PI;
						RotacaoPhi = Phi + Math.PI;
						RaioRot = 0;
						RaioTeta = 0;
						RaioPhi = 0;
						xt = x;
						yt = y;
						zt = z;
						Tetat = Teta;
						Phit = Phi;
						Rott = Rot;
						FlagMouseY = 1;
						FatorAnguloVisao = 1;
						DistanciaTela = 2;
						CorLinhaRed = 255;
						CorLinhaGreen = 255;
						CorLinhaBlue = 255;
						CorBackgroundRed = 0;
						CorBackgroundGreen = 0;
						CorBackgroundBlue = 0;
						CorTrianguloShapeRed = 0;
						CorTrianguloShapeGreen = 0;
						CorTrianguloShapeBlue = 255;
						CorLegendaRed = 255;
						CorLegendaGreen = 255;
						CorLegendaBlue = 255;
						Parametro0 = 0;
						Parametro0Step = 1;
						Parametro1 = 0;
						Parametro1Step = 1;
						Parametro2 = 0;
						Parametro2Step = 1;
						Parametro3 = 0;
						Parametro3Step = 1;
						Parametro4 = 0;
						Parametro4Step = 1;
						Parametro5 = 0;
						Parametro5Step = 1;
						Parametro6 = 0;
						Parametro6Step = 1;
						Parametro7 = 0;
						Parametro7Step = 1;
						Parametro8 = 0;
						Parametro8Step = 1;
						Parametro9 = 0;
						Parametro9Step = 1;
						CameraMovePar = 0;
						CameraMoveParStep = 1;
						StretchFlag = 1;
						SleepTime = 7;
						FlagTime = 0;
						}

					if (keyCode == KeyEvent.VK_F1) if (FlagHelp == 0)
						{
						if (! (Debug.equals("Debug"))) try
							{
							AV3DNavigatorHelpCountThread.interrupt ();
							AV3DNavigatorHelpCountThread.start();
							} catch (IllegalThreadStateException e) {}

						JFrame FrameHelp = new JFrame("AV3DNavigator - Ajuda");
						FrameHelp.setPreferredSize(new Dimension(TamanhoEspacoHelpX, TamanhoEspacoHelpY));
						FrameHelp.setSize(new Dimension(TamanhoEspacoHelpX, TamanhoEspacoHelpY));
						LabelHelp = new GradientLabel("<html>F2 para selecionar e abrir arquivo de espaço.<br><br>\"A\" para incrementar x. \"Z\" para decrementar.<br>\"S\" para incrementar y. \"X\" para decrementar.<br>\"D\" para incrementar z. \"C\" para decrementar.<br>\"F\" para incrementar Teta. \"V\" para decrementar. \"G\" para incrementar Phi. \"B\" para decrementar.<br>Shift + \"F\" para rotação lateral esquerda. Shift + \"V\" para direita.<br>Shift + \"B\" para rotação vertical para baixo. Shift + \"G\" para cima.<br>\"H\" para incrementar a rotação da tela. \"N\" para decrementar.<br>\"J\" para rotação horizontal positiva. \"M\" para negativa.<br>Shift + \"J\" para rotação vertical positiva. Shift + \"M\" para negativa.<br>\"K\" para rotação total positiva. \",\" para negativa.<br>\"L\" para incrementar o raio de rotação horizontal. \".\" para decrementar.<br>Shift + \"L\" para incrementar o raio de rotação vertical. Shift + \".\" para decrementar.<br>\"[\" para incrementar o raio de rotação total. \"]\" para decrementar.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar.<br>\"T\" para shift negativo na cor vermelha padrão da linha. \"Y\" para shift positivo.<br>Shift + \"T\" para shift negativo na cor verde padrão da linha. Shift + \"Y\" para shift positivo.<br>Ctrl + \"T\" para shift negativo na cor azul padrão da linha. Ctrl + \"Y\" para shift positivo.<br>\"U\" para shift negativo na cor vermelha padrão de fundo. \"I\" para shift positivo.<br>Shift + \"U\" para shift negativo na cor verde padrão de fundo. Shift + \"I\" para shift positivo.<br>Ctrl + \"U\" para shift negativo na cor azul padrão de fundo. Ctrl + \"I\" para shift positivo.<br>\"O\" para shift negativo na cor vermelha padrão dos polígonos preenchidos. \"P\" para shift positivo.<br>Shift + \"O\" para shift negativo na cor verde padrão dos polígonos preenchidos. Shift + \"P\" para shift positivo.<br>Ctrl + \"O\" para shift negativo na cor azul padrão dos polígonos preenchidos. Ctrl + \"P\" para shift positivo.<br>INSERT para shift negativo na cor vermelha padrão das legendas. HOME para shift positivo.<br>Shift + INSERT para shift negativo na cor verde padrão das legendas. Shift + HOME para shift positivo.<br>Ctrl + INSERT para shift negativo na cor azul padrão das legendas. Ctrl + HOME para shift positivo.<br>DELETE para shift negativo no tamanho padrão das legendas. END para shift positivo.<br>\"-\" para shift negativo no offset das legendas. \"=\" para shift positivo.<br>Numpad \"1\" para shift negativo na resolução dos triângulos. Numpad \"2\" para shift positivo.<br>PAGE DOWN para shift negativo no sleep time. PAGE UP para shift positivo.<br><br>Numpad \"0\" para toggle alta precisão Apfloat (com custo computacional).<br>F4 para toggle preenchimento dos polígonos com linhas ou fillPolygon.<br><br>Ctrl + ENTER para shift positivo em câmeras predefinidas, Ctrl + Shift + ENTER para negativo.<br><br>Numpad \"3\" para incremento no parâmetro de movimentação da câmera. Shift + Numpad \"3\" para decremento.<br>Ctrl + Numpad \"3\" para incremento no step de variação do parâmetro de movimentação da câmera. Ctrl + Shift = Numpad \"3\" para decremento.<br><br>Teclas de \"0\" a \"9\" para incrementar o parâmetro correspondente. Shift + tecla para decrementar.<br>Ctrl + tecla para incrementar o step do parâmetro. Ctrl + Shift + tecla para decrementar.<br><br>ENTER para ler os arquivos de parâmetros.<br><br>Shift + ENTER para ativar / desativar os parâmetros de tempo.<br><br>Setas para strafe. Shift + setas para strafe com rotação de tela.<br>Mouse pode ser utilizado para movimentar.<br><br>Barra de espaços para resetar as variáveis.<br><br>F10 para togle stretch. F11 para setar aspect ratio 1. F12 para screenshot.<br>F3 para ocultar e mostrar os labels.<br>BACKSPACE para ativar / desativar labels animados.<br><br>ESC para sair.</html>", new Color(CorJanelaR, CorJanelaG, CorJanelaB), new Color(CorJanelaGradienteR, CorJanelaGradienteG, CorJanelaGradienteB), new Color(CorFonteJanelaR, CorFonteJanelaG, CorFonteJanelaB), 2);
						LabelHelp.setBorder(new EmptyBorder(5, 5, 5, 5));
						LabelHelp.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelHelp));
						FrameHelp.add(LabelHelp);
						FrameHelp.pack();
						FrameHelp.setVisible(true);

						FlagHelp = 1;

						FrameHelp.addKeyListener(new KeyListener()
							{
							public void keyPressed(KeyEvent keHelp)
								{
								int keyCodeHelp = keHelp.getKeyCode();
								if (keyCodeHelp == KeyEvent.VK_ESCAPE) {FrameHelp.dispose(); FlagHelp = 0;}
								}

							public void keyReleased(KeyEvent keHelp){}
							public void keyTyped(KeyEvent keHelp){}
							});
						}

					if (keyCode == KeyEvent.VK_F12)
						{
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
						LocalDateTime now = LocalDateTime.now();
						BufferedImage ImagemFrame = new BufferedImage(TamanhoPlanoX, TamanhoPlanoY + FrameEspaco.getInsets().top, BufferedImage.TYPE_INT_RGB);
						Graphics2D g2d = ImagemFrame.createGraphics();
						FrameEspaco.print(g2d);

						g2d.setFont(new Font("SansSerif", Font.ITALIC, TamanhoFonteLabelPrint));

						if (FlagPrintInit == 0)
							{
							Random geradorPrint = new Random();

							String [] CoresMatrix = StringCores.split(";");
							int FlagCores = 0;
							long TentativasCores = 0;

							labelDo: do
								{
								int iPrintR = geradorPrint.nextInt(256);
								int iPrintG = geradorPrint.nextInt(256);
								int iPrintB = geradorPrint.nextInt(256);

								labelFor: for (i = 0; i < CoresMatrix.length; i++)
									{
									String [] RGB = CoresMatrix[i].split(",");

									if ((iPrintR == Integer.parseInt(RGB[0])) && (iPrintG == Integer.parseInt(RGB[1])) && (iPrintB == Integer.parseInt(RGB[2])))
										{
										TentativasCores++;
										FlagCores = 1;
										continue labelDo;
										}
									else
										{
										g2d.setColor(new Color(iPrintR, iPrintG, iPrintB));
										FlagCores = 0;
										break labelFor;
										}
									}
								} while ((FlagCores == 1) && (TentativasCores < MaxTentativasCores));

							if (TentativasCores == MaxTentativasCores)
								g2d.setColor(Color.WHITE);
							}
						else
							g2d.setColor(new Color(PrintR, PrintG, PrintB));

						if (FlagMostrarLabel == 1)
							g2d.drawString(URL, 5 + FrameEspaco.getInsets().left, TamanhoPlanoY + FrameEspaco.getInsets().top - 5);
						else
							{
							String[] AtribuicaoStringArrP = AtribuicaoString.split("\\|");

							if (AtribuicaoStringArrP.length == 2)
								{
								if (AntonioVandre.NumeroNaturalPositivo(AtribuicaoStringArrP[0].replaceAll(" ", "")))
									{
									String[] AtribuicaoStringArr = AtribuicaoStringArrP[1].split(";");

									for (i = 0; i < AtribuicaoStringArr.length; i++)
										g2d.drawString(AtribuicaoStringArr[i], TamanhoPlanoX - FrameEspaco.getInsets().right - Integer.parseInt(AtribuicaoStringArrP[0].replaceAll(" ", "")), TamanhoPlanoY + FrameEspaco.getInsets().top - 5 - ((AtribuicaoStringArr.length - 1) * 20) + i * 20);
									}
								}
							}

						g2d.dispose();
						BufferedImage ImagemFramePrint = ImagemFrame.getSubimage(FrameEspaco.getInsets().left, FrameEspaco.getInsets().top, TamanhoPlanoX - FrameEspaco.getInsets().left - FrameEspaco.getInsets().right, TamanhoPlanoY);
						try {ImageIO.write(ImagemFramePrint, "png", new File("AV3DNavigator - Screenshot - " + dtf.format(now) + ".png"));} catch(IOException e) {}
						}

					if (keyCode == KeyEvent.VK_F2)
						{
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
						int result = fileChooser.showOpenDialog(FrameEspaco);
						if (result == JFileChooser.APPROVE_OPTION)
							{
							File selectedFile = fileChooser.getSelectedFile();
							Espaco = LerEspaco (selectedFile.getAbsolutePath(), Debug);

							if (Espaco.equals("Erro"))
								{
								JFrame FrameErroEspacoInvalido = new JFrame("AV3DNavigator - Espaço inválido");
								FrameErroEspacoInvalido.setPreferredSize(new Dimension(TamanhoEspacoInvalidoX, TamanhoEspacoInvalidoY));
								FrameErroEspacoInvalido.setSize(new Dimension(TamanhoEspacoInvalidoX, TamanhoEspacoInvalidoY));
								GradientLabel LabelErroEspacoInvalido = new GradientLabel(MensagemErroEspacoInvalido, new Color(CorJanelaR, CorJanelaG, CorJanelaB), new Color(CorJanelaGradienteR, CorJanelaGradienteG, CorJanelaGradienteB), new Color(CorFonteJanelaR, CorFonteJanelaG, CorFonteJanelaB), 0);
								LabelErroEspacoInvalido.setBorder(new EmptyBorder(5, 5, 5, 5));
								LabelErroEspacoInvalido.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelErroEspacoInvalido));
								FrameErroEspacoInvalido.add(LabelErroEspacoInvalido);
								FrameErroEspacoInvalido.pack();
								FrameErroEspacoInvalido.setVisible(true);

								FrameErroEspacoInvalido.addKeyListener(new KeyListener()
									{
									public void keyPressed(KeyEvent keErroEspacoInvalido)
										{
										int keyCodeHelp = keErroEspacoInvalido.getKeyCode();

										if (keyCodeHelp == KeyEvent.VK_ESCAPE)
											FrameErroEspacoInvalido.dispose();
										}

									public void keyReleased(KeyEvent keErroEspacoInvalido){}
									public void keyTyped(KeyEvent keErroEspacoInvalido){}
									});

								Espaco = "";
								}
							else
								DesenharEspaco(Comp);
							}
						}

					if (keyCode == KeyEvent.VK_F10)
						{if (StretchFlag == 0) StretchFlag = 1; else StretchFlag = 0;}

					if (keyCode == KeyEvent.VK_F11)
						{
						if (StretchFlag == 1)
							{
							int Tamanho = Math.min(TamanhoPlanoX, TamanhoPlanoY);

							if (FlagMostrarLabel == 1)
								{
								FrameEspaco.setPreferredSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
								FrameEspaco.setSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
								}
							else
								{
								FrameEspaco.setPreferredSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelURL));
								FrameEspaco.setSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelURL));
								}

							FrameEspaco.revalidate(); FrameEspaco.repaint(); FrameEspaco.pack();
							}
						}

					if (keyCode == KeyEvent.VK_F3)
						{
						if (FlagMostrarLabel == 0)
							{
							FrameEspaco.getContentPane().remove(LabelURL);
							LabelStatusLabelURLPanel = new JPanel(new GridBagLayout());
							GridBagConstraints GridBagConstraintsLabelStatusLabelURL = new GridBagConstraints();
							GridBagConstraintsLabelStatusLabelURL.gridx = 0;
							GridBagConstraintsLabelStatusLabelURL.gridy = 0;
							GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.BOTH;
							GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
							GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelStatus;
							LabelStatusLabelURLPanel.add(LabelStatus, GridBagConstraintsLabelStatusLabelURL);
							GridBagConstraintsLabelStatusLabelURL.gridx = 0;
							GridBagConstraintsLabelStatusLabelURL.gridy = 1;
							GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.BOTH;
							GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
							GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelURL;
							LabelStatusLabelURLPanel.add(LabelURL, GridBagConstraintsLabelStatusLabelURL);
							FrameEspaco.getContentPane().add(LabelStatusLabelURLPanel);
							LabelStatusLabelURLPanel.setVisible(true);
							LabelStatusLabelURLPanel.revalidate();
							LabelStatusLabelURLPanel.repaint();

							MinTamanhoPlanoYMaisLabels = TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL;

							FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
							FrameEspaco.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));

							FlagMostrarLabel = 1;
							}
						else
							{
							LabelStatusLabelURLPanel.removeAll();
							FrameEspaco.getContentPane().remove(LabelStatusLabelURLPanel);
							FrameEspaco.getContentPane().add(LabelURL, BorderLayout.PAGE_END);
							LabelURL.setVisible(true);
							LabelStatusLabelURLPanel.setVisible(false);
							LabelStatusLabelURLPanel.revalidate();
							LabelStatusLabelURLPanel.repaint();

							MinTamanhoPlanoYMaisLabels = TamanhoPlanoY + TamanhoEspacoLabelURL;

							FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelURL));
							FrameEspaco.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelURL));

							FlagMostrarLabel = 0;
							}

						FrameEspaco.revalidate(); FrameEspaco.repaint(); FrameEspaco.pack();

						try {Thread.sleep(50);} catch(InterruptedException e) {}
						}

					if (keyCode == KeyEvent.VK_END) {TamanhoFonteLegendas++;}

					if (keyCode == KeyEvent.VK_DELETE)
						{if (TamanhoFonteLegendas > 1) TamanhoFonteLegendas--;}

					if (keyCode == KeyEvent.VK_EQUALS) {ShiftVerticalLegendas++;}

					if (keyCode == KeyEvent.VK_MINUS)
						{if (ShiftVerticalLegendas > 20) ShiftVerticalLegendas--;}

					if (keyCode == KeyEvent.VK_NUMPAD2) {ResolucaoTriangulos++;}

					if (keyCode == KeyEvent.VK_NUMPAD1)
						{if (ResolucaoTriangulos > 2) ResolucaoTriangulos--;}

					if (keyCode == KeyEvent.VK_NUMPAD3)
						{
						if (ke.isControlDown())
							{
							if (ke.isShiftDown())
								{if (Math.abs(CameraMoveParStep - 0.1) < AntonioVandre.MaximoValorReal) CameraMoveParStep -= 0.1;}
							else
								{if (Math.abs(CameraMoveParStep + 0.1) < AntonioVandre.MaximoValorReal) CameraMoveParStep += 0.1;}
							}
						else
							{
							File file = new File("AV3DNCamMove.txt");

							if (ke.isShiftDown())
								{if (Math.abs(CameraMovePar - CameraMoveParStep) < AntonioVandre.MaximoValorReal) CameraMovePar -= CameraMoveParStep;}
							else
								{if (Math.abs(CameraMovePar + CameraMoveParStep) < AntonioVandre.MaximoValorReal) CameraMovePar += CameraMoveParStep;}

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));

								String Linha;

								do {Linha = br.readLine();} while (((Linha.replaceAll(" ", "").equals(""))) || (Linha.replaceAll(" ", "").charAt(0) == '#'));
								
								String [] LinhaArr = Linha.split("DIVISOR");

								if (LinhaArr.length == 6)
									{
									double xcam = (new Expression(LinhaArr[0].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", ""))).calculate();

									double ycam = (new Expression("(" + LinhaArr[1].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", "") + ") * (-1)")).calculate();

									double zcam = (new Expression("(" + LinhaArr[2].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", "") + ") * (-1)")).calculate();

									double Tetacam = (new Expression(LinhaArr[3].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", ""))).calculate();

									double Phicam = (new Expression(LinhaArr[4].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", ""))).calculate();

									double Rotcam = (new Expression(LinhaArr[5].replaceAll("AV3DCamPar", "(" + String.valueOf(CameraMovePar) + ")").replaceAll(" ", ""))).calculate();

									if ((AntonioVandre.NumeroReal(String.valueOf(xcam))) && (AntonioVandre.NumeroReal(String.valueOf(ycam))) && (AntonioVandre.NumeroReal(String.valueOf(zcam))) && (AntonioVandre.NumeroReal(String.valueOf(Tetacam))) && (AntonioVandre.NumeroReal(String.valueOf(Phicam))) && (AntonioVandre.NumeroReal(String.valueOf(Rotcam))))
											{
											x = xcam;
											y = ycam;
											z = zcam;
											Teta = Tetacam;
											Phi = Phicam;
											Rot = Rotcam;

											xt = x; yt = y; zt = z; Tetat = Teta; Phit = Phi; Rott = Rot; ContadorFrames = FramesDeslocamento;
											}
									}
								} catch (IOException e) {}
							}
						}

					if (keyCode == KeyEvent.VK_PAGE_UP) {SleepTime++;}

					if (keyCode == KeyEvent.VK_PAGE_DOWN)
						{if (SleepTime > 1) SleepTime--;}

					if (keyCode == KeyEvent.VK_A)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(x + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {x += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_Z)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(x - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {x -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_S)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(y - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {y -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_X)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(y + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {y += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_D)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(z - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {z -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_C)
						{FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(z + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {z += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_F)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if ((! (Math.abs(Rot + DeslocamentoAngular * Math.cos(Rot) * Math.sin(Phi)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Teta + DeslocamentoAngular * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Phi - DeslocamentoAngular * Math.sin(Rot) * Math.cos(Phi)) >= AntonioVandre.MaximoValorReal))) {Teta += DeslocamentoAngular * Math.cos(Rot); Phi -= DeslocamentoAngular * Math.sin(Rot) * Math.cos(Phi); Rot += DeslocamentoAngular * Math.cos(Rot) * Math.sin(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						else
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (! (Math.abs(Teta + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Teta += DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_V)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if ((! (Math.abs(Rot - DeslocamentoAngular * Math.cos(Rot) * Math.sin(Phi)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Teta - DeslocamentoAngular * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Phi + DeslocamentoAngular * Math.sin(Rot) * Math.cos(Phi)) >= AntonioVandre.MaximoValorReal))) {Teta -= DeslocamentoAngular * Math.cos(Rot); Phi += DeslocamentoAngular * Math.sin(Rot) * Math.cos(Phi); Rot -= DeslocamentoAngular * Math.cos(Rot) * Math.sin(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						else
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (! (Math.abs(Teta - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Teta -= DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_B)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if ((! (Math.abs(Rot - DeslocamentoAngular * Math.sin(Rot) * Math.sin(Phi)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Teta - DeslocamentoAngular * Math.sin(Rot)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Phi - DeslocamentoAngular * Math.cos(Rot) * Math.cos(Phi)) >= AntonioVandre.MaximoValorReal))) {Teta -= DeslocamentoAngular * Math.sin(Rot); Phi -= DeslocamentoAngular * Math.cos(Rot) * Math.cos(Phi); Rot -= DeslocamentoAngular * Math.sin(Rot) * Math.sin(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						else
							{
							if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if (! (Math.abs(Phi - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Phi -= DeslocamentoAngular; ContadorFrames = 0;} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_G)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if ((! (Math.abs(Rot + DeslocamentoAngular * Math.sin(Rot) * Math.sin(Phi)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Teta + DeslocamentoAngular * Math.sin(Rot)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(Phi + DeslocamentoAngular * Math.cos(Rot) * Math.cos(Phi)) >= AntonioVandre.MaximoValorReal))) {Teta += DeslocamentoAngular * Math.sin(Rot); Phi += DeslocamentoAngular * Math.cos(Rot) * Math.cos(Phi); Rot += DeslocamentoAngular * Math.sin(Rot) * Math.sin(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						else
							{
							if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if (! (Math.abs(Phi - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Phi += DeslocamentoAngular; ContadorFrames = 0;}  else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_H)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(Rot + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Rot += DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_N)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(Rot - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Rot -= DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_J)
						{
						if (ke.isShiftDown())
							{
							FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 1; CameraId = -1;

							if (! (Math.abs(RotacaoPhi - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) RotacaoPhi -= DeslocamentoAngular; else VariavelLimiteAtingido();

							if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if (! (Math.abs(Phi - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Phi -= DeslocamentoAngular; x = xRotacaoPhi + RaioPhi * Math.cos(RotacaoPhi) * Math.cos(Teta); y = yRotacaoPhi - RaioPhi * Math.cos(RotacaoPhi) * Math.sin(Teta); z = zRotacaoPhi - RaioPhi * Math.sin(RotacaoPhi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}
							}
						else
							{
							FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 1; FlagCoordRotVert = 0; CameraId = -1;

							if (! (Math.abs(RotacaoTeta + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) RotacaoTeta += DeslocamentoAngular; else VariavelLimiteAtingido();

							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (! (Math.abs(Teta + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Teta += DeslocamentoAngular; x = xRotacaoTeta + RaioTeta * Math.cos(RotacaoTeta) * Math.cos(Phi); y = yRotacaoTeta - RaioTeta * Math.sin(RotacaoTeta) * Math.cos(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_M)
						{
						if (ke.isShiftDown())
							{
							FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 1; CameraId = -1;

							if (! (Math.abs(RotacaoPhi + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) RotacaoPhi += DeslocamentoAngular; else VariavelLimiteAtingido();

							if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if (! (Math.abs(Phi + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Phi += DeslocamentoAngular; x = xRotacaoPhi + RaioPhi * Math.cos(RotacaoPhi) * Math.cos(Teta); y = yRotacaoPhi - RaioPhi * Math.cos(RotacaoPhi) * Math.sin(Teta); z = zRotacaoPhi - RaioPhi * Math.sin(RotacaoPhi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}
							}
						else
							{
							FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 1; FlagCoordRotVert = 0; CameraId = -1;

							if (! (Math.abs(RotacaoTeta - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) RotacaoTeta -= DeslocamentoAngular; else VariavelLimiteAtingido();

							if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (! (Math.abs(Teta - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) {Teta -= DeslocamentoAngular; x = xRotacaoTeta + RaioTeta * Math.cos(RotacaoTeta) * Math.cos(Phi); y = yRotacaoTeta - RaioTeta * Math.sin(RotacaoTeta) * Math.cos(Phi); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
							}
						}

					if (keyCode == KeyEvent.VK_K)
						{
						FlagMouseY = 1; FlagCoordRot = 1; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (! (Math.abs(Rotacao - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) Rotacao -= DeslocamentoAngular; else VariavelLimiteAtingido();

						if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if ((! (Math.abs(Teta - DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) || (! ((Math.signum(Math.cos(Phi0Rotacao)) >= 0) && (Math.floor(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 + Phi0Rotacao % (Math.PI / 2) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao)) >= AntonioVandre.MaximoValorReal)) || (! ((Math.signum(Math.cos(Phi0Rotacao)) < 0) && (Math.ceil(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 - (Math.PI / 2 - (Phi0Rotacao % (Math.PI / 2))) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(xRotacao + RaioRot * Math.cos(Phi + Math.PI) * Math.cos(Teta)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(yRotacao - RaioRot * Math.cos(Phi + Math.PI) * Math.sin(Teta)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(zRotacao - RaioRot * Math.sin(Phi + Math.PI)) >= AntonioVandre.MaximoValorReal))) {Teta -= DeslocamentoAngular; if (Math.signum(Math.cos(Phi0Rotacao)) >= 0) Phi = Math.floor(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 + Phi0Rotacao % (Math.PI / 2) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao); else Phi = Math.ceil(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 - (Math.PI / 2 - (Phi0Rotacao % (Math.PI / 2))) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao); x = xRotacao + RaioRot * Math.cos(Phi + Math.PI) * Math.cos(Teta); y = yRotacao - RaioRot * Math.cos(Phi + Math.PI) * Math.sin(Teta); z = zRotacao - RaioRot * Math.sin(Phi + Math.PI); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiInferior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
						}

					if (keyCode == KeyEvent.VK_COMMA)
						{
						FlagMouseY = 1; FlagCoordRot = 1; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (! (Math.abs(Rotacao + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) Rotacao += DeslocamentoAngular; else VariavelLimiteAtingido();

						if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {if ((! (Math.abs(Teta + DeslocamentoAngular) >= AntonioVandre.MaximoValorReal)) || (! ((Math.signum(Math.cos(Phi0Rotacao)) >= 0) && (Math.floor(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 + Phi0Rotacao % (Math.PI / 2) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao)) >= AntonioVandre.MaximoValorReal)) || (! ((Math.signum(Math.cos(Phi0Rotacao)) < 0) && (Math.ceil(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 - (Math.PI / 2 - (Phi0Rotacao % (Math.PI / 2))) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(xRotacao + RaioRot * Math.cos(Phi + Math.PI) * Math.cos(Teta)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(yRotacao - RaioRot * Math.cos(Phi + Math.PI) * Math.sin(Teta)) >= AntonioVandre.MaximoValorReal)) || (! (Math.abs(zRotacao - RaioRot * Math.sin(Phi + Math.PI)) >= AntonioVandre.MaximoValorReal))) {Teta += DeslocamentoAngular; if (Math.signum(Math.cos(Phi0Rotacao)) >= 0) Phi = Math.floor(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 + Phi0Rotacao % (Math.PI / 2) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao); else Phi = Math.ceil(Phi0Rotacao / (Math.PI / 2)) * Math.PI / 2 - (Math.PI / 2 - (Phi0Rotacao % (Math.PI / 2))) * Math.cos(Rotacao) - Rot * Math.sin(Rotacao); x = xRotacao + RaioRot * Math.cos(Phi + Math.PI) * Math.cos(Teta); y = yRotacao - RaioRot * Math.cos(Phi + Math.PI) * Math.sin(Teta); z = zRotacao - RaioRot * Math.sin(Phi + Math.PI); ContadorFrames = 0;} else VariavelLimiteAtingido();} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiInferior = 1;}} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}
						}

					if (keyCode == KeyEvent.VK_L)
						{
						if (ke.isShiftDown())
							{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioPhi + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioPhi += DeslocamentoLinear;} else VariavelLimiteAtingido();}
						else
							{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioTeta + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioTeta += DeslocamentoLinear;} else VariavelLimiteAtingido();}
						}

					if (keyCode == KeyEvent.VK_PERIOD)
						{
						if (ke.isShiftDown())
							{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioPhi - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioPhi -= DeslocamentoLinear;} else VariavelLimiteAtingido();}
						else
							{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioTeta - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioTeta -= DeslocamentoLinear;} else VariavelLimiteAtingido();}
						}

					if (keyCode == KeyEvent.VK_OPEN_BRACKET)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioRot + DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioRot += DeslocamentoLinear;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_CLOSE_BRACKET)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (Math.abs(RaioRot - DeslocamentoLinear) >= AntonioVandre.MaximoValorReal)) {RaioRot -= DeslocamentoLinear;} else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_Q)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (DistanciaTela >= 1) DistanciaTela -= 1;}

					if (keyCode == KeyEvent.VK_W)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (DistanciaTela + 1 >= AntonioVandre.MaximoValorReal)) DistanciaTela += 1; else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_E)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (FatorAnguloVisao > 1) FatorAnguloVisao -= 1;}

					if (keyCode == KeyEvent.VK_R)
						{FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1; if (! (FatorAnguloVisao + 1 >= AntonioVandre.MaximoValorReal)) FatorAnguloVisao += 1; else VariavelLimiteAtingido();}

					if (keyCode == KeyEvent.VK_T)
						{
						if (ke.isShiftDown())
							{
							if (CorLinhaGreen > 0) CorLinhaGreen -= 1;
							}
						else if (ke.isControlDown())
							{
							if (CorLinhaBlue > 0) CorLinhaBlue -= 1;
							}
						else
							{
							if (CorLinhaRed > 0) CorLinhaRed -= 1;
							}
						}

					if (keyCode == KeyEvent.VK_Y)
						{
						if (ke.isShiftDown())
							{
							if (CorLinhaGreen < 255) CorLinhaGreen += 1;
							}
						else if (ke.isControlDown())
							{
							if (CorLinhaBlue < 255) CorLinhaBlue += 1;
							}
						else
							{
							if (CorLinhaRed < 255) CorLinhaRed += 1;
							}
						}

					if (keyCode == KeyEvent.VK_U)
						{
						if (ke.isShiftDown())
							{
							if (CorBackgroundGreen > 0) CorBackgroundGreen -= 1;
							}
						else if (ke.isControlDown())
							{
							if (CorBackgroundBlue > 0) CorBackgroundBlue -= 1;
							}
						else
							{
							if (CorBackgroundRed > 0) CorBackgroundRed -= 1;
							}
						}

					if (keyCode == KeyEvent.VK_I)
						{
						if (ke.isShiftDown())
							{
							if (CorBackgroundGreen < 255) CorBackgroundGreen += 1;
							}
						else if (ke.isControlDown())
							{
							if (CorBackgroundBlue < 255) CorBackgroundBlue += 1;
							}
						else
							{
							if (CorBackgroundRed < 255) CorBackgroundRed += 1;
							}
						}

					if (keyCode == KeyEvent.VK_O)
						{
						if (ke.isShiftDown())
							{
							if (CorTrianguloShapeGreen > 0) CorTrianguloShapeGreen -= 1;
							}
						else if (ke.isControlDown())
							{
							if (CorTrianguloShapeBlue > 0) CorTrianguloShapeBlue -= 1;
							}
						else
							{
							if (CorTrianguloShapeRed > 0) CorTrianguloShapeRed -= 1;
							}
						}

					if (keyCode == KeyEvent.VK_P)
						{
						if (ke.isShiftDown())
							{
							if (CorTrianguloShapeGreen < 255) CorTrianguloShapeGreen += 1;
							}
						else if (ke.isControlDown())
							{
							if (CorTrianguloShapeBlue < 255) CorTrianguloShapeBlue += 1;
							}
						else
							{
							if (CorTrianguloShapeRed < 255) CorTrianguloShapeRed += 1;
							}
						}

					if (keyCode == KeyEvent.VK_INSERT)
						{
						if (ke.isShiftDown())
							{
							if (CorLegendaGreen > 0) CorLegendaGreen -= 1;
							}
						else if (ke.isControlDown())
							{
							if (CorLegendaBlue > 0) CorLegendaBlue -= 1;
							}
						else
							{
							if (CorLegendaRed > 0) CorLegendaRed -= 1;
							}
						}

					if (keyCode == KeyEvent.VK_HOME)
						{
						if (ke.isShiftDown())
							{
							if (CorLegendaGreen < 255) CorLegendaGreen += 1;
							}
						else if (ke.isControlDown())
							{
							if (CorLegendaBlue < 255) CorLegendaBlue += 1;
							}
						else
							{
							if (CorLegendaRed < 255) CorLegendaRed += 1;
							}
						}

					if (keyCode == KeyEvent.VK_NUMPAD0)
						{
						if (ApfloatFlag == 0)
							{
							if (! (Debug.equals("Debug"))) try
								{
								AV3DNavigatorApfloatCountThread.interrupt ();
								AV3DNavigatorApfloatCountThread.start();
								} catch (IllegalThreadStateException e) {}

							ApfloatFlag = 1;
							}
						else
							ApfloatFlag = 0;

						TriangulosString = "";
						}

					if (keyCode == KeyEvent.VK_F4)
						if (TrianguloPoligono == 0) TrianguloPoligono = 1; else TrianguloPoligono = 0;

					if (keyCode == KeyEvent.VK_UP)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if ((Math.abs(x + Math.sin(-Teta) * Math.cos(-Rot + Math.PI / 2) - Math.sin(Phi) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y - Math.cos(-Teta) * Math.cos(-Rot + Math.PI / 2) + Math.sin(Phi) * Math.sin(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z - Math.sin(-Phi - Rot + Math.PI / 2) + Math.cos(Phi) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x += Math.sin(-Teta) * Math.cos(-Rot + Math.PI / 2) - Math.sin(Phi) * Math.cos(Rot);
								y -= Math.cos(-Teta) * Math.cos(-Rot + Math.PI / 2) + Math.sin(Phi) * Math.sin(Rot);
								z -= Math.sin(-Phi - Rot + Math.PI / 2) + Math.cos(Phi) * Math.cos(Rot);
								}
							}
						else
							{
							if ((Math.abs(x + Math.cos(Phi) * Math.cos(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y - Math.cos(Phi) * Math.sin(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z - Math.sin(Phit)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x += Math.cos(Phi) * Math.cos(Teta);
								y -= Math.cos(Phi) * Math.sin(Teta);
								z -= Math.sin(Phi);
								}
							}

						ContadorFrames = 0;
						}

					if (keyCode == KeyEvent.VK_DOWN)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if ((Math.abs(x - Math.sin(-Teta) * Math.cos(-Rot + Math.PI / 2) - Math.sin(Phi) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y + Math.cos(-Teta) * Math.cos(-Rot + Math.PI / 2) + Math.sin(Phi) * Math.sin(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z + Math.sin(-Phi - Rot + Math.PI / 2) + Math.cos(Phi) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x -= Math.sin(-Teta) * Math.cos(-Rot + Math.PI / 2) - Math.sin(Phi) * Math.cos(Rot);
								y += Math.cos(-Teta) * Math.cos(-Rot + Math.PI / 2) + Math.sin(Phi) * Math.sin(Rot);
								z += Math.sin(-Phi - Rot + Math.PI / 2) + Math.cos(Phi) * Math.cos(Rot);
								}
							}
						else
							{
							if ((Math.abs(x - Math.cos(Phi) * Math.cos(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y + Math.cos(Phi) * Math.sin(Teta)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z + Math.sin(Phit)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x -= Math.cos(Phi) * Math.cos(Teta);
								y += Math.cos(Phi) * Math.sin(Teta);
								z += Math.sin(Phi);
								}
							}

						ContadorFrames = 0;
						}

					if (keyCode == KeyEvent.VK_LEFT)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if ((Math.abs(x - Math.cos(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y + Math.sin(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z + Math.sin(-Phi * Math.sin(Teta) + Rot)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x -= Math.cos(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot);
								y += Math.sin(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot);
								z += Math.sin(-Phi * Math.sin(Teta) + Rot);
								}
							}
						else
							{
							if ((Math.abs(x - Math.cos(Teta - Math.PI / 2)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y + Math.sin(Teta - Math.PI / 2)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x -= Math.cos(Teta - Math.PI / 2);
								y += Math.sin(Teta - Math.PI / 2);
								}
							}

						ContadorFrames = 0;
						}

					if (keyCode == KeyEvent.VK_RIGHT)
						{
						FlagMouseY = 1; FlagCoordRot = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0; CameraId = -1;

						if (ke.isShiftDown())
							{
							if ((Math.abs(x + Math.cos(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y - Math.sin(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot)) >= AntonioVandre.MaximoValorReal) || (Math.abs(z - Math.sin(-Phi * Math.sin(Teta) + Rot)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x += Math.cos(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot);
								y -= Math.sin(Teta - Rot * Math.sin(Phi) - Math.PI / 2) * Math.cos(Rot);
								z -= Math.sin(-Phi * Math.sin(Teta) + Rot);
								}
							}
						else
							{
							if ((Math.abs(x + Math.cos(Teta - Math.PI / 2)) >= AntonioVandre.MaximoValorReal) || (Math.abs(y - Math.sin(Teta - Math.PI / 2)) >= AntonioVandre.MaximoValorReal))
								VariavelLimiteAtingido();
							else
								{
								x += Math.cos(Teta - Math.PI / 2);
								y -= Math.sin(Teta - Math.PI / 2);
								}
							}

						ContadorFrames = 0;
						}

					if (keyCode == KeyEvent.VK_0)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro0Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro0Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro0Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro0Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro0 - Parametro0Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro0 -= Parametro0Step;
							}
						else
							{
							if (Math.abs(Parametro0 + Parametro0Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro0 += Parametro0Step;
							}
						}

					if (keyCode == KeyEvent.VK_1)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro1Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro1Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro1Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro1Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro1 - Parametro1Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro1 -= Parametro1Step;
							}
						else
							{
							if (Math.abs(Parametro1 + Parametro1Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro1 += Parametro1Step;
							}
						}

					if (keyCode == KeyEvent.VK_2)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro2Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro2Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro2Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro2Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro2 - Parametro2Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro2 -= Parametro2Step;
							}
						else
							{
							if (Math.abs(Parametro2 + Parametro2Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro2 += Parametro2Step;
							}
						}

					if (keyCode == KeyEvent.VK_3)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro3Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro3Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro3Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro3Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro3 - Parametro3Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro3 -= Parametro3Step;
							}
						else
							{
							if (Math.abs(Parametro3 + Parametro3Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro3 += Parametro3Step;
							}
						}

					if (keyCode == KeyEvent.VK_4)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro4Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro4Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro4Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro4Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro4 - Parametro4Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro4 -= Parametro4Step;
							}
						else
							{
							if (Math.abs(Parametro4 + Parametro4Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro4 += Parametro4Step;
							}
						}

					if (keyCode == KeyEvent.VK_5)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro5Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro5Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro5Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro5Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro5 - Parametro5Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro5 -= Parametro5Step;
							}
						else
							{
							if (Math.abs(Parametro5 + Parametro5Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro5 += Parametro5Step;
							}
						}

					if (keyCode == KeyEvent.VK_6)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro6Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro6Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro6Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro6Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro6 - Parametro6Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro6 -= Parametro6Step;
							}
						else
							{
							if (Math.abs(Parametro6 + Parametro6Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro6 += Parametro6Step;
							}
						}

					if (keyCode == KeyEvent.VK_7)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro7Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro7Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro7Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro7Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro7 - Parametro7Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro0 -= Parametro7Step;
							}
						else
							{
							if (Math.abs(Parametro7 + Parametro7Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro7 += Parametro7Step;
							}
						}

					if (keyCode == KeyEvent.VK_8)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro8Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro8Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro8Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro8Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro8 - Parametro8Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro8 -= Parametro8Step;
							}
						else
							{
							if (Math.abs(Parametro8 + Parametro8Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro8 += Parametro8Step;
							}
						}

					if (keyCode == KeyEvent.VK_9)
						{
						if ((ke.isControlDown()) && (ke.isShiftDown()))
							{
							if (Math.abs(Parametro9Step - 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro9Step -= 0.1;
							}
						else if (ke.isControlDown())
							{
							if (Math.abs(Parametro9Step + 0.1) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro9Step += 0.1;
							}
						else if (ke.isShiftDown())
							{
							if (Math.abs(Parametro9 - Parametro9Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro9 -= Parametro9Step;
							}
						else
							{
							if (Math.abs(Parametro9 + Parametro9Step) >= AntonioVandre.MaximoValorReal)
								VariavelLimiteAtingido();
							else
								Parametro9 += Parametro9Step;
							}
						}

					if (keyCode == KeyEvent.VK_BACK_SPACE)
						{if (LabelAnimado == 0) LabelAnimado = 1; else LabelAnimado = 0;}

					if (keyCode == KeyEvent.VK_ENTER)
						{
						if (ke.isControlDown())
							{
							File file = new File("AV3DNCamIds.txt");

							if (ke.isShiftDown())
								{if (CameraId > 0) CameraId--;}
							else CameraId++;

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));

								String Linha;

								i = 0;

								do
									{
									Linha = br.readLine();

									if (Linha == null)
										{
										CameraId = i - 1;

										if (CameraId > -1)
											{br = new BufferedReader(new FileReader(file)); i = 0; continue;}
										else
											break;
										}
									else if ((! (Linha.replaceAll(" ", "").equals(""))) && (Linha.replaceAll(" ", "").charAt(0) != '#'))
										{
										String LinhaArr [] = null;

										if (Linha.contains("DIVISOR"))
											{
											LinhaArr = Linha.split("DIVISOR");

											if (LinhaArr.length == 6)
												{
												double xcam = (new Expression(LinhaArr[0].replaceAll(" ", ""))).calculate();

												double ycam = (new Expression("(" + LinhaArr[1].replaceAll(" ", "") + ") * (-1)")).calculate();

												double zcam = (new Expression("(" + LinhaArr[2].replaceAll(" ", "") + ") * (-1)")).calculate();

												double Tetacam = (new Expression(LinhaArr[3].replaceAll(" ", ""))).calculate();

												double Phicam = (new Expression(LinhaArr[4].replaceAll(" ", ""))).calculate();

												double Rotcam = (new Expression(LinhaArr[5].replaceAll(" ", ""))).calculate();

												if ((AntonioVandre.NumeroReal(String.valueOf(xcam))) && (AntonioVandre.NumeroReal(String.valueOf(ycam))) && (AntonioVandre.NumeroReal(String.valueOf(zcam))) && (AntonioVandre.NumeroReal(String.valueOf(Tetacam))) && (AntonioVandre.NumeroReal(String.valueOf(Phicam))) && (AntonioVandre.NumeroReal(String.valueOf(Rotcam))))
													if (CameraId == i++)
														{
														x = xcam;
														y = ycam;
														z = zcam;
														Teta = Tetacam;
														Phi = Phicam;
														Rot = Rotcam;
										
														xt = x; yt = y; zt = z; Tetat = Teta; Phit = Phi; Rott = Rot; ContadorFrames = FramesDeslocamento;

														break;
														}
												}
											}
										else
											{
											LinhaArr = Linha.split(",");

											if (LinhaArr.length == 6) if ((AntonioVandre.NumeroReal(LinhaArr[0].replaceAll(" ", ""))) && (AntonioVandre.NumeroReal(LinhaArr[1].replaceAll(" ", ""))) && (AntonioVandre.NumeroReal(LinhaArr[2].replaceAll(" ", ""))) && (AntonioVandre.NumeroReal(LinhaArr[3].replaceAll(" ", ""))) && (AntonioVandre.NumeroReal(LinhaArr[4].replaceAll(" ", ""))) && (AntonioVandre.NumeroReal(LinhaArr[5].replaceAll(" ", ""))))
												if (CameraId == i++)
													{
													x = Double.parseDouble(LinhaArr[0].replaceAll(" ", ""));

													y = -Double.parseDouble(LinhaArr[1].replaceAll(" ", ""));

													z = -Double.parseDouble(LinhaArr[2].replaceAll(" ", ""));

													Teta = Double.parseDouble(LinhaArr[3].replaceAll(" ", ""));

													Phi = Double.parseDouble(LinhaArr[4].replaceAll(" ", ""));

													Rot = Double.parseDouble(LinhaArr[5].replaceAll(" ", ""));

													xt = x; yt = y; zt = z; Tetat = Teta; Phit = Phi; Rott = Rot; ContadorFrames = FramesDeslocamento;

													break;
													}
											}
										}
									} while (true);
								} catch (IOException e) {}
							}
						else if (ke.isShiftDown())
							{if (FlagTime == 0) FlagTime = 1; else FlagTime = 0;}
						else
							{
							File file = new File("AV3DNParFile0.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile0 = Double.parseDouble(Content);
								else
									ParametroFile0 = 0;
								} catch (IOException e) {ParametroFile0 = 0;}


							file = new File("AV3DNParFile1.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile1 = Double.parseDouble(Content);
								else
									ParametroFile1 = 0;
								} catch (IOException e) {ParametroFile1 = 0;}

							file = new File("AV3DNParFile2.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile2 = Double.parseDouble(Content);
								else
									ParametroFile2 = 0;
								} catch (IOException e) {ParametroFile1 = 2;}

							file = new File("AV3DNParFile3.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile3 = Double.parseDouble(Content);
								else
									ParametroFile3 = 0;
								} catch (IOException e) {ParametroFile3 = 0;}

							file = new File("AV3DNParFile4.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile4 = Double.parseDouble(Content);
								else
									ParametroFile4 = 0;
								} catch (IOException e) {ParametroFile4 = 0;}

							file = new File("AV3DNParFile5.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile5 = Double.parseDouble(Content);
								else
									ParametroFile5 = 0;
								} catch (IOException e) {ParametroFile5 = 0;}

							file = new File("AV3DNParFile6.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile6 = Double.parseDouble(Content);
								else
									ParametroFile6 = 0;
								} catch (IOException e) {ParametroFile6 = 0;}

							file = new File("AV3DNParFile7.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile7 = Double.parseDouble(Content);
								else
									ParametroFile7 = 0;
								} catch (IOException e) {ParametroFile7 = 0;}

							file = new File("AV3DNParFile8.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile8 = Double.parseDouble(Content);
								else
									ParametroFile8 = 0;
								} catch (IOException e) {ParametroFile8 = 0;}

							file = new File("AV3DNParFile9.txt");

							try
								{
								BufferedReader br = new BufferedReader(new FileReader(file));
								String Content = br.readLine();

								if (AntonioVandre.NumeroReal(Content))
									ParametroFile9 = Double.parseDouble(Content);
								else
									ParametroFile9 = 0;
								} catch (IOException e) {ParametroFile9 = 0;}
							}
						}

					FlagAlteracaoStatus = 1;
					}
				}

			public void keyReleased(KeyEvent ke){}
			public void keyTyped(KeyEvent ke){}
			});

		FrameEspaco.pack();
		FrameEspaco.setVisible(true);

		if (! (Espaco.equals("") || Espaco.equals("Erro"))) DesenharEspaco(Comp);

		while(Sair == 0)
			{
			int FlagRedimensionarOver = 0;

			int widthFrameEspaco = FrameEspaco.getWidth();
			int heightFrameEspaco = FrameEspaco.getHeight();

			if (widthFrameEspaco < MinTamanhoPlanoX)
				{
				widthFrameEspaco = MinTamanhoPlanoX;
				FrameEspaco.setPreferredSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
				FrameEspaco.setSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
				FlagRedimensionarOver = 1;
				}

			if (heightFrameEspaco < MinTamanhoPlanoYMaisLabels)
				{
				heightFrameEspaco = MinTamanhoPlanoYMaisLabels;
				FrameEspaco.setPreferredSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
				FrameEspaco.setSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
				FlagRedimensionarOver = 1;
				}

			if (FlagRedimensionarOver == 0)
				if ((widthFrameEspaco != TamanhoPlanoX) || ((FlagMostrarLabel == 1) && (heightFrameEspaco != TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL)) || ((FlagMostrarLabel == 0) && (heightFrameEspaco != TamanhoPlanoY + TamanhoEspacoLabelURL)))
					{
					TamanhoPlanoX = widthFrameEspaco;

					if (FlagMostrarLabel == 1)
						{
						TamanhoPlanoY = heightFrameEspaco - TamanhoEspacoLabelStatus - TamanhoEspacoLabelURL;

						FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
						FrameEspaco.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
						}
					else
						{
						TamanhoPlanoY = heightFrameEspaco - TamanhoEspacoLabelURL;

						FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelURL));
						FrameEspaco.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelURL));
						}

					Comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
					Comp.setSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
					FrameEspaco.revalidate(); FrameEspaco.repaint(); FrameEspaco.pack();

					/* Reinicialização opcional das variáveis de localização.

					x = 0;
					y = 0;
					z = 0;
					Teta = 0;
					Phi = 0;
					Rot = 0;
					TamanhoFonteLegendas = 12;
					RotacaoTeta = Teta + Math.PI;
					RotacaoPhi = Phi + Math.PI;
					RaioRot = 0;
					RaioTeta = 0;
					RaioPhi = 0;
					xt = x;
					yt = y;
					zt = z;
					Tetat = Teta;
					Phit = Phi;
					Rott = Rot;
					FlagMouseY = 1;
					FatorAnguloVisao = 1;
					DistanciaTela = 2;
					Parametro0 = 0;
					Parametro0Step = 1;
					Parametro1 = 0;
					Parametro1Step = 1;
					Parametro2 = 0;
					Parametro2Step = 1;
					Parametro3 = 0;
					Parametro3Step = 1;
					Parametro4 = 0;
					Parametro4Step = 1;
					Parametro5 = 0;
					Parametro5Step = 1;
					Parametro6 = 0;
					Parametro6Step = 1;
					Parametro7 = 0;
					Parametro7Step = 1;
					Parametro8 = 0;
					Parametro8Step = 1;
					Parametro9 = 0;
					Parametro9Step = 1;
					CameraMovePar = 0;
					CameraMoveParStep = 1;
					StretchFlag = 1;
					SleepTime = 7;
					FlagTime = 0;

					ContadorFrames = FramesDeslocamento;
*/

					DesenharEspaco(Comp);
					FlagAlteracaoStatus = 1;
				}

			Point reference = FrameEspaco.getLocationOnScreen();
			MouseX = MouseInfo.getPointerInfo().getLocation().x - reference.x;
			MouseY = MouseInfo.getPointerInfo().getLocation().y - reference.y;

			if (MouseDown == 0)
				{
				if (ContadorFrames < FramesDeslocamento)
					{
					if (xt != x)
						{
						if (IntervaloX == 0)
							IntervaloX = x - xt;

						xt += IntervaloX / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					if (yt != y)
						{
						if (IntervaloY == 0)
							IntervaloY = y - yt;

						yt += IntervaloY / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					if (zt != z)
						{
						if (IntervaloZ == 0)
							IntervaloZ = z - zt;

						zt += IntervaloZ / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					if (Tetat != Teta)
						{
						if (IntervaloTeta == 0)
							IntervaloTeta = Teta - Tetat;

						Tetat += IntervaloTeta / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					if (Phit != Phi)
						{
						if (IntervaloPhi == 0)
							IntervaloPhi = Phi - Phit;

						Phit += IntervaloPhi / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					if (Rott != Rot)
						{
						if (IntervaloRot == 0)
							IntervaloRot = Rot - Rott;

						Rott += IntervaloRot / FramesDeslocamento;
						FlagAlteracaoStatus = 1;
						}

					ContadorFrames++;

					if (ContadorFrames == FramesDeslocamento)
						{
						IntervaloX = 0;
						IntervaloY = 0;
						IntervaloZ = 0;
						IntervaloTeta = 0;
						IntervaloPhi = 0;
						IntervaloRot = 0;
						}
					}
				}

			if (FlagCoordRot == 0)
				{
				xRotacao = x + RaioRot * Math.cos(Teta) * Math.cos(Phi);
				yRotacao = y - RaioRot * Math.sin(Teta) * Math.cos(Phi);
				zRotacao = z - RaioRot * Math.sin(Phi);
				Rotacao = 0;
				Phi0Rotacao = Phi;
				FlagCoordRot = 1;
				}

			if (FlagCoordRotHor == 0)
				{
				xRotacaoTeta = x + RaioTeta * Math.cos(Teta) * Math.cos(Phi);
				yRotacaoTeta = y - RaioTeta * Math.sin(Teta) * Math.cos(Phi);
				RotacaoTeta = Teta + Math.PI;
				FlagCoordRotHor = 1;
				}

			if (FlagCoordRotVert == 0)
				{
				xRotacaoPhi = x + RaioPhi * Math.cos(Teta) * Math.cos(Phi);
				yRotacaoPhi = y - RaioPhi * Math.sin(Teta) * Math.cos(Phi);
				zRotacaoPhi = z - RaioPhi * Math.sin(Phi);
				RotacaoPhi = Phi + Math.PI;
				FlagCoordRotVert = 1;
				}

			if (FlagMouseDownArea == 1)
				{
				if ((Math.abs(Teta) - DeslocamentoAngular > AntonioVandre.MaximoValorReal - DeslocamentoAngular) || (Math.abs(Phi) - DeslocamentoAngular > AntonioVandre.MaximoValorReal - DeslocamentoAngular))
					VariavelLimiteAtingido();
				else
					{
					FlagMouseY = Math.signum(Math.cos(Phi));

					if (Math.abs(Teta) < TetaMax - DeslocamentoAngular)
						Teta = FlagMouseY * 2 * Math.PI * (MouseX - MouseXR) / TamanhoPlanoX + TetaR;
					else
						{
						MouseXR = MouseX;
						TetaR -= Math.signum(Math.sin(Teta)) * DeslocamentoAngular;
						Teta = TetaR;

						if (Math.signum(Teta) > 0)
							FlagTetaSuperior = 1;
						else
							FlagTetaInferior = 1;
						}

					Tetat = Teta;

					if (Math.abs(Phi) < PhiMax - DeslocamentoAngular)
						{
						Phi = Math.PI * (MouseY - MouseYR) / TamanhoPlanoY + PhiR;
						}
					else
						{
						MouseYR = MouseY;
						PhiR -= Math.signum(Phi) * DeslocamentoAngular;
						Phi = PhiR;

						if (Math.signum(Phi) > 0)
							FlagPhiSuperior = 1;
						else
							FlagPhiInferior = 1;
						}

					Phit = Math.atan(Math.tan(Phi));

					FlagAlteracaoStatus = 1;
					}
				}

			if (FlagAlteracaoStatus == 1)
				{
				StringCores = "";

				CorLinha = new Color(CorLinhaRed, CorLinhaGreen, CorLinhaBlue);
				StringCores = StringCores + String.valueOf(CorLinhaRed) + "," + String.valueOf(CorLinhaGreen) + ","  + String.valueOf(CorLinhaBlue) + ";";

				CorBackground = new Color(CorBackgroundRed, CorBackgroundGreen, CorBackgroundBlue);
				StringCores = StringCores + String.valueOf(CorBackgroundRed) + "," + String.valueOf(CorBackgroundGreen) + ","  + String.valueOf(CorBackgroundBlue) + ";";

				CorTrianguloShape = new Color(CorTrianguloShapeRed, CorTrianguloShapeGreen, CorTrianguloShapeBlue);
				StringCores = StringCores + String.valueOf(CorTrianguloShapeRed) + "," + String.valueOf(CorTrianguloShapeGreen) + ","  + String.valueOf(CorTrianguloShapeBlue) + ";";

				CorLegenda = new Color(CorLegendaRed, CorLegendaGreen, CorLegendaBlue);
				StringCores = StringCores + String.valueOf(CorLegendaRed) + "," + String.valueOf(CorLegendaGreen) + ","  + String.valueOf(CorLegendaBlue) + ";";

				if (ApfloatFlag == 0)
					{
					AnguloVisao = Math.atan(Math.max(TamanhoPlanoX, TamanhoPlanoY) / 2 / DistanciaTela);

					AnguloVisao /= FatorAnguloVisao;
					}
				else
					{
					AnguloVisao = ApfloatMath.atan(ApfloatMath.max((new Apfloat(TamanhoPlanoX, PrecisaoApfloat)), (new Apfloat(TamanhoPlanoY, PrecisaoApfloat))).divide(new Apfloat(2)).divide(new Apfloat(DistanciaTela, PrecisaoApfloat))).doubleValue();

					AnguloVisao = (new Apfloat(AnguloVisao)).divide(new Apfloat(FatorAnguloVisao)).doubleValue();
					}

				try {Thread.sleep(SleepTime);} catch(InterruptedException e) {}

				if (StretchFlag == 1)
					AspectRatio = (double) TamanhoPlanoX / (double) TamanhoPlanoY;
				else
					AspectRatio = 1;

				LabelStatus.setText("<html><p style=\"line-height: 50%;\">x = " + String.valueOf(x) + ". y = " + String.valueOf(-y) + ". z = " + String.valueOf(-z) + ".<br><br>θ = " + String.valueOf(Teta) + ". Max θ = " + String.valueOf(TetaMax) + ".<br>φ = " + String.valueOf(Phi) + ". Max φ = " + String.valueOf(PhiMax) + ".<br><br>Rot = " + String.valueOf(Rot) + ".<br><br>Raio θ = " + String.valueOf(RaioTeta) + ". Rotacao θ = " + String.valueOf(RotacaoTeta) + ".<br>Raio φ = " + String.valueOf(RaioPhi) + ". Rotacao φ = " + String.valueOf(RotacaoPhi) + ".<br>Raio de rotação = " + String.valueOf(RaioRot) + ". Rotacao = " + String.valueOf(Rotacao) + ".<br><br>Distância da tela = " + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao + MargemAnguloVisao) + ".<br>Aspect ratio = " + String.valueOf(AspectRatio) + "<br><br>P0 = " + String.valueOf(Parametro1) + ". P0S = " + String.valueOf(Parametro0Step) + ". P1 = " + String.valueOf(Parametro1) + ". P1S = " + String.valueOf(Parametro1Step) + ".<br>P2 = " + String.valueOf(Parametro2) + ". P2S = " + String.valueOf(Parametro2Step) + ". P3 = " + String.valueOf(Parametro3) + ". P3S = " + String.valueOf(Parametro3Step) + ".<br>P4 = " + String.valueOf(Parametro4) + ". P4S = " + String.valueOf(Parametro4Step) + ". P5 = " + String.valueOf(Parametro5) + ". P5S = " + String.valueOf(Parametro5Step) + ".<br>P6 = " + String.valueOf(Parametro6) + ". P6S = " + String.valueOf(Parametro6Step) + ". P7 = " + String.valueOf(Parametro7) + ". P7S = " + String.valueOf(Parametro7Step) + ".<br>P8 = " + String.valueOf(Parametro8) + ". P8S = " + String.valueOf(Parametro8Step) + ". P9 = " + String.valueOf(Parametro9) + ". P9S = " + String.valueOf(Parametro9Step) + ".<br><br>CameraMovePar = " + String.valueOf(CameraMovePar) + ". CameraMoveParStep = " + String.valueOf(CameraMoveParStep) + ".<br><br>Stretch = " + String.valueOf(StretchFlag) + ". Apfloat = " + String.valueOf(ApfloatFlag) + ". fillPolygon = " + String.valueOf(TrianguloPoligono) + ". ResolucaoTriangulos = " + String.valueOf(ResolucaoTriangulos) + ". SleepTime = " + String.valueOf(SleepTime) + ". FlagTime = " + String.valueOf(FlagTime) + ". CamId = " + String.valueOf(CameraId) + ".<br><br>Aperte F1 para ajuda.</p></html>");

				FrameEspaco.getContentPane().setBackground(CorBackground);

				DesenharEspaco(Comp);

				FlagAlteracaoStatus = 0;
				}

			if (FlagINI == 1) {FlagAlteracaoStatus = 1; FlagINI = 0;}

			if (FlagMostrarLabel == 1) if (LabelAnimado == 1) {LabelStatus.setVisible(false); LabelStatus.setVisible(true); if (FlagHelp == 1) {LabelHelp.setVisible(false); LabelHelp.setVisible(true);};}

			if (FlagTime == 1)
				{
				if (ContadorTime * SleepTime >= CiclesTimeIgn)
					{
					ParametroTimeS = Integer.parseInt(new SimpleDateFormat("ss").format(Calendar.getInstance().getTime()));

					ParametroTimeM = Integer.parseInt(new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()));

					ParametroTimeH = Integer.parseInt(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()));

					ContadorTime = 0;
					FlagAlteracaoStatus = 1;
					}
				else ContadorTime++;
				}

			try {Thread.sleep(SleepTime);} catch(InterruptedException e) {}
			}

		FrameEspaco.dispose();
		System.exit(0);
		}

	public void DesenharEspaco(AV3DNavigator Comp)
		{
		if (StretchFlag == 1)
			{
			TamanhoPlanoXAd = TamanhoPlanoX;
			TamanhoPlanoYAd = TamanhoPlanoY;
			}
		else
			{
			TamanhoPlanoXAd = Math.min(TamanhoPlanoX, TamanhoPlanoY);
			TamanhoPlanoYAd = TamanhoPlanoXAd;
			}

		String [] EspacoStr2 = Espaco.split("@");

		String EspacoP;

		if (EspacoStr2.length == 1) EspacoP = EspacoStr2[0]; else EspacoP = EspacoStr2[0] + EspacoStr2[1];

		String [] EspacoLinhas = {};
		String [] EspacoTriangulosShapePreenchidos = {};
		String [] EspacoLegendas = {};

		if (EspacoStr2.length >= 1)
			EspacoLinhas = EspacoStr2[0].split("\\|");

		if (EspacoStr2.length >= 2)
			EspacoTriangulosShapePreenchidos = EspacoStr2[1].split("\\|");

		if (EspacoStr2.length >= 3)
			{
			String UniaoStringLegendas = EspacoStr2[2];

			for (i = 3; i < EspacoStr2.length; i++)
				UniaoStringLegendas = UniaoStringLegendas + "@" + EspacoStr2[i];

			EspacoLegendas = UniaoStringLegendas.split("\\|");
			}

		Comp.clearObjects();

		TotalLinhas = 0;

		TEspaco = EspacoLinhas.length;

		labelLinhas: for (i = 0; i < TEspaco; i++)
			{
			if (! (EspacoLinhas[i].equals("")))
				{
				String [] Campos;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					Campos = EspacoLinhas[i].split("color");
				else if (EspacoLinhas[i].contains("color"))
					Campos = EspacoLinhas[i].split("color");
				else
					Campos = EspacoLinhas[i].split("c");

				String [] Pontos;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					Pontos = Campos[0].split("DIVISOR");
				else if (Campos[0].contains("DIVISOR"))
					Pontos = Campos[0].split("DIVISOR");
				else
					Pontos = Campos[0].split(";");

				String [] CoordenadasOrig;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					CoordenadasOrig = Pontos[0].split("divisor");
				else if (Pontos[0].contains("divisor"))
					CoordenadasOrig = Pontos[0].split("divisor");
				else
					CoordenadasOrig = Pontos[0].split(",");

				String [] CoordenadasDest;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					CoordenadasDest = Pontos[1].split("divisor");
				else if (Pontos[1].contains("divisor"))
					CoordenadasDest = Pontos[1].split("divisor");
				else
					CoordenadasDest = Pontos[1].split(",");

				Pontoslength = Pontos.length;

				if (ApfloatFlag == 0)
					{
					double xo = 0;

					if ((CoordenadasOrig[0].contains("AV3DNP0")) || (CoordenadasOrig[0].contains("AV3DNP1")) || (CoordenadasOrig[0].contains("AV3DNP2")) || (CoordenadasOrig[0].contains("AV3DNP3")) || (CoordenadasOrig[0].contains("AV3DNP4")) || (CoordenadasOrig[0].contains("AV3DNP5")) || (CoordenadasOrig[0].contains("AV3DNP6")) || (CoordenadasOrig[0].contains("AV3DNP7")) || (CoordenadasOrig[0].contains("AV3DNP8")) || (CoordenadasOrig[0].contains("AV3DNP9")) || (CoordenadasOrig[0].contains("AV3DNPF0")) || (CoordenadasOrig[0].contains("AV3DNPF1")) || (CoordenadasOrig[0].contains("AV3DNPF2")) || (CoordenadasOrig[0].contains("AV3DNPF3")) || (CoordenadasOrig[0].contains("AV3DNPF4")) || (CoordenadasOrig[0].contains("AV3DNF5")) || (CoordenadasOrig[0].contains("AV3DNPF6")) || (CoordenadasOrig[0].contains("AV3DNPF7")) || (CoordenadasOrig[0].contains("AV3DNPF8")) || (CoordenadasOrig[0].contains("AV3DNPF9")) || (CoordenadasOrig[0].contains("AV3DNPTS")) || (CoordenadasOrig[0].contains("AV3DNPTM")) || (CoordenadasOrig[0].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {xo = expr.calculate() - xt;} catch (Exception e) {}
						}
					else
						xo = Double.parseDouble(CoordenadasOrig[0]) - xt;

					double xd = 0;

					if ((CoordenadasDest[0].contains("AV3DNP0")) || (CoordenadasDest[0].contains("AV3DNP1")) || (CoordenadasDest[0].contains("AV3DNP2")) || (CoordenadasDest[0].contains("AV3DNP3")) || (CoordenadasDest[0].contains("AV3DNP4")) || (CoordenadasDest[0].contains("AV3DNP5")) || (CoordenadasDest[0].contains("AV3DNP6")) || (CoordenadasDest[0].contains("AV3DNP7")) || (CoordenadasDest[0].contains("AV3DNP8")) || (CoordenadasDest[0].contains("AV3DNP9")) || (CoordenadasDest[0].contains("AV3DNPF0")) || (CoordenadasDest[0].contains("AV3DNPF1")) || (CoordenadasDest[0].contains("AV3DNPF2")) || (CoordenadasDest[0].contains("AV3DNPF3")) || (CoordenadasDest[0].contains("AV3DNPF4")) || (CoordenadasDest[0].contains("AV3DNF5")) || (CoordenadasDest[0].contains("AV3DNPF6")) || (CoordenadasDest[0].contains("AV3DNPF7")) || (CoordenadasDest[0].contains("AV3DNPF8")) || (CoordenadasDest[0].contains("AV3DNPF9")) || (CoordenadasDest[0].contains("AV3DNPTS")) || (CoordenadasDest[0].contains("AV3DNPTM")) || (CoordenadasDest[0].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {xd = expr.calculate() - xt;} catch (Exception e) {}
						}
					else
						xd = Double.parseDouble(CoordenadasDest[0]) - xt;

					double yo = 0;

					if ((CoordenadasOrig[1].contains("AV3DNP0")) || (CoordenadasOrig[1].contains("AV3DNP1")) || (CoordenadasOrig[1].contains("AV3DNP2")) || (CoordenadasOrig[1].contains("AV3DNP3")) || (CoordenadasOrig[1].contains("AV3DNP4")) || (CoordenadasOrig[1].contains("AV3DNP5")) || (CoordenadasOrig[1].contains("AV3DNP6")) || (CoordenadasOrig[1].contains("AV3DNP7")) || (CoordenadasOrig[1].contains("AV3DNP8")) || (CoordenadasOrig[1].contains("AV3DNP9")) || (CoordenadasOrig[1].contains("AV3DNPF0")) || (CoordenadasOrig[1].contains("AV3DNPF1")) || (CoordenadasOrig[1].contains("AV3DNPF2")) || (CoordenadasOrig[1].contains("AV3DNPF3")) || (CoordenadasOrig[1].contains("AV3DNPF4")) || (CoordenadasOrig[1].contains("AV3DNF5")) || (CoordenadasOrig[1].contains("AV3DNPF6")) || (CoordenadasOrig[1].contains("AV3DNPF7")) || (CoordenadasOrig[1].contains("AV3DNPF8")) || (CoordenadasOrig[1].contains("AV3DNPF9")) || (CoordenadasOrig[1].contains("AV3DNPTS")) || (CoordenadasOrig[1].contains("AV3DNPTM")) || (CoordenadasOrig[1].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {yo = -expr.calculate() - yt;} catch (Exception e) {}
						}
					else
						yo = -Double.parseDouble(CoordenadasOrig[1]) - yt;

					double yd = 0;

					if ((CoordenadasDest[1].contains("AV3DNP0")) || (CoordenadasDest[1].contains("AV3DNP1")) || (CoordenadasDest[1].contains("AV3DNP2")) || (CoordenadasDest[1].contains("AV3DNP3")) || (CoordenadasDest[1].contains("AV3DNP4")) || (CoordenadasDest[1].contains("AV3DNP5")) || (CoordenadasDest[1].contains("AV3DNP6")) || (CoordenadasDest[1].contains("AV3DNP7")) || (CoordenadasDest[1].contains("AV3DNP8")) || (CoordenadasDest[1].contains("AV3DNP9")) || (CoordenadasDest[1].contains("AV3DNPF0")) || (CoordenadasDest[1].contains("AV3DNPF1")) || (CoordenadasDest[1].contains("AV3DNPF2")) || (CoordenadasDest[1].contains("AV3DNPF3")) || (CoordenadasDest[1].contains("AV3DNPF4")) || (CoordenadasDest[1].contains("AV3DNF5")) || (CoordenadasDest[1].contains("AV3DNPF6")) || (CoordenadasDest[1].contains("AV3DNPF7")) || (CoordenadasDest[1].contains("AV3DNPF8")) || (CoordenadasDest[1].contains("AV3DNPF9")) || (CoordenadasDest[1].contains("AV3DNPTS")) || (CoordenadasDest[1].contains("AV3DNPTM")) || (CoordenadasDest[1].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {yd = -expr.calculate() - yt;} catch (Exception e) {}
						}
					else
						yd = -Double.parseDouble(CoordenadasDest[1]) - yt;

					double zo = 0;

					if ((CoordenadasOrig[2].contains("AV3DNP0")) || (CoordenadasOrig[2].contains("AV3DNP1")) || (CoordenadasOrig[2].contains("AV3DNP2")) || (CoordenadasOrig[2].contains("AV3DNP3")) || (CoordenadasOrig[2].contains("AV3DNP4")) || (CoordenadasOrig[2].contains("AV3DNP5")) || (CoordenadasOrig[2].contains("AV3DNP6")) || (CoordenadasOrig[2].contains("AV3DNP7")) || (CoordenadasOrig[2].contains("AV3DNP8")) || (CoordenadasOrig[2].contains("AV3DNP9")) || (CoordenadasOrig[2].contains("AV3DNPF0")) || (CoordenadasOrig[2].contains("AV3DNPF1")) || (CoordenadasOrig[2].contains("AV3DNPF2")) || (CoordenadasOrig[2].contains("AV3DNPF3")) || (CoordenadasOrig[2].contains("AV3DNPF4")) || (CoordenadasOrig[2].contains("AV3DNF5")) || (CoordenadasOrig[2].contains("AV3DNPF6")) || (CoordenadasOrig[2].contains("AV3DNPF7")) || (CoordenadasOrig[2].contains("AV3DNPF8")) || (CoordenadasOrig[2].contains("AV3DNPF9")) || (CoordenadasOrig[2].contains("AV3DNPTS")) || (CoordenadasOrig[2].contains("AV3DNPTM")) || (CoordenadasOrig[2].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {zo = -expr.calculate() - zt;} catch (Exception e) {}
						}
					else
						zo = -Double.parseDouble(CoordenadasOrig[2]) - zt;

					double zd = 0;

					if ((CoordenadasDest[2].contains("AV3DNP0")) || (CoordenadasDest[2].contains("AV3DNP1")) || (CoordenadasDest[2].contains("AV3DNP2")) || (CoordenadasDest[2].contains("AV3DNP3")) || (CoordenadasDest[2].contains("AV3DNP4")) || (CoordenadasDest[2].contains("AV3DNP5")) || (CoordenadasDest[2].contains("AV3DNP6")) || (CoordenadasDest[2].contains("AV3DNP7")) || (CoordenadasDest[2].contains("AV3DNP8")) || (CoordenadasDest[2].contains("AV3DNP9")) || (CoordenadasDest[2].contains("AV3DNPF0")) || (CoordenadasDest[2].contains("AV3DNPF1")) || (CoordenadasDest[2].contains("AV3DNPF2")) || (CoordenadasDest[2].contains("AV3DNPF3")) || (CoordenadasDest[2].contains("AV3DNPF4")) || (CoordenadasDest[2].contains("AV3DNF5")) || (CoordenadasDest[2].contains("AV3DNPF6")) || (CoordenadasDest[2].contains("AV3DNPF7")) || (CoordenadasDest[2].contains("AV3DNPF8")) || (CoordenadasDest[2].contains("AV3DNPF9")) || (CoordenadasDest[2].contains("AV3DNPTS")) || (CoordenadasDest[2].contains("AV3DNPTM")) || (CoordenadasDest[2].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {zd = -expr.calculate() - zt;} catch (Exception e) {}
						}
					else
						zd = -Double.parseDouble(CoordenadasDest[2]) - zt;

					xi = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * DistanciaTela * (Math.cos(Rott) * (xo * Math.sin(Tetat) + yo * Math.cos(Tetat)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)) - Math.sin(Rott) * (xo * Math.cos(Tetat) * Math.sin(Phit) - yo * Math.sin(Tetat) * Math.sin(Phit) + zo * Math.cos(Phit)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)))) - CorrecaoX;

					yi = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * DistanciaTela * (Math.sin(Rott) * (xo * Math.sin(Tetat) + yo * Math.cos(Tetat)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)) + Math.cos(Rott) * (xo * Math.cos(Tetat) * Math.sin(Phit) - yo * Math.sin(Tetat) * Math.sin(Phit) + zo * Math.cos(Phit)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)))) - CorrecaoY;

					xf = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * DistanciaTela * (Math.cos(Rott) * (xd * Math.sin(Tetat) + yd * Math.cos(Tetat)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)) - Math.sin(Rott) * (xd * Math.cos(Tetat) * Math.sin(Phit) - yd * Math.sin(Tetat) * Math.sin(Phit) + zd * Math.cos(Phit)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)))) - CorrecaoX;

					yf = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * DistanciaTela * (Math.sin(Rott) * (xd * Math.sin(Tetat) + yd * Math.cos(Tetat)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)) + Math.cos(Rott) * (xd * Math.cos(Tetat) * Math.sin(Phit) - yd * Math.sin(Tetat) * Math.sin(Phit) + zd * Math.cos(Phit)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)))) - CorrecaoY;

					ProdutoEscalaro = xo * Math.cos(Tetat) * Math.cos(Phit) - yo * Math.sin(Tetat) * Math.cos(Phit) - zo * Math.sin(Phit);

					ProdutoEscalard = xd * Math.cos(Tetat) * Math.cos(Phit) - yd * Math.sin(Tetat) * Math.cos(Phit) - zd * Math.sin(Phit);

					if ((Math.acos(FlagMouseY * ProdutoEscalaro / Math.sqrt(xo * xo + yo * yo + zo * zo)) < AnguloVisao + MargemAnguloVisao) && ((Math.acos(FlagMouseY * ProdutoEscalard / Math.sqrt(xd * xd + yd * yd + zd * zd)) < AnguloVisao + MargemAnguloVisao) && (Math.min(xi, Math.min(yi, Math.min(xf, yf))) > 0) && (Math.max(xi + CorrecaoXF, xf + CorrecaoXF) < TamanhoPlanoX) && (Math.max(yi + CorrecaoYF, yf + CorrecaoYF) < TamanhoPlanoY)))
						{
						if (TotalLinhas + 1 < Integer.MAX_VALUE)
							TotalLinhas++;
						else break labelLinhas;

						if (Campos.length == 1)
							Comp.addLine(xi, yi, xf, yf, CorLinha, TotalLinhas);
						else
							{
							String [] RGB;

							if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
								RGB = Campos[1].split("divisor");
							else if (Campos[1].contains("divisor"))
								RGB = Campos[1].split("divisor");
							else
								RGB = Campos[1].split(",");

							for (j = 0; j < 3; j++)
								if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
									{
									Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
									try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
									if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
									}

							Comp.addLine(xi, yi, xf, yf, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), TotalLinhas);
							StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
							}
						}
					}
				else
					{
					// Alta precisão com o Apfloat, porém com custo computacional.

					Apfloat xoa = null;

					if ((CoordenadasOrig[0].contains("AV3DNP0")) || (CoordenadasOrig[0].contains("AV3DNP1")) || (CoordenadasOrig[0].contains("AV3DNP2")) || (CoordenadasOrig[0].contains("AV3DNP3")) || (CoordenadasOrig[0].contains("AV3DNP4")) || (CoordenadasOrig[0].contains("AV3DNP5")) || (CoordenadasOrig[0].contains("AV3DNP6")) || (CoordenadasOrig[0].contains("AV3DNP7")) || (CoordenadasOrig[0].contains("AV3DNP8")) || (CoordenadasOrig[0].contains("AV3DNP9")) || (CoordenadasOrig[0].contains("AV3DNPF0")) || (CoordenadasOrig[0].contains("AV3DNPF1")) || (CoordenadasOrig[0].contains("AV3DNPF2")) || (CoordenadasOrig[0].contains("AV3DNPF3")) || (CoordenadasOrig[0].contains("AV3DNPF4")) || (CoordenadasOrig[0].contains("AV3DNF5")) || (CoordenadasOrig[0].contains("AV3DNPF6")) || (CoordenadasOrig[0].contains("AV3DNPF7")) || (CoordenadasOrig[0].contains("AV3DNPF8")) || (CoordenadasOrig[0].contains("AV3DNPF9")) || (CoordenadasOrig[0].contains("AV3DNPTS")) || (CoordenadasOrig[0].contains("AV3DNPTM")) || (CoordenadasOrig[0].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {xoa = new Apfloat(expr.calculate() - xt, PrecisaoApfloat);} catch (Exception e) {}
						}
					else
						xoa = (new Apfloat(Double.parseDouble(CoordenadasOrig[0]), PrecisaoApfloat)).add(new Apfloat(-xt, PrecisaoApfloat));

					Apfloat xda = null;

					if ((CoordenadasDest[0].contains("AV3DNP0")) || (CoordenadasDest[0].contains("AV3DNP1")) || (CoordenadasDest[0].contains("AV3DNP2")) || (CoordenadasDest[0].contains("AV3DNP3")) || (CoordenadasDest[0].contains("AV3DNP4")) || (CoordenadasDest[0].contains("AV3DNP5")) || (CoordenadasDest[0].contains("AV3DNP6")) || (CoordenadasDest[0].contains("AV3DNP7")) || (CoordenadasDest[0].contains("AV3DNP8")) || (CoordenadasDest[0].contains("AV3DNP9")) || (CoordenadasDest[0].contains("AV3DNPF0")) || (CoordenadasDest[0].contains("AV3DNPF1")) || (CoordenadasDest[0].contains("AV3DNPF2")) || (CoordenadasDest[0].contains("AV3DNPF3")) || (CoordenadasDest[0].contains("AV3DNPF4")) || (CoordenadasDest[0].contains("AV3DNF5")) || (CoordenadasDest[0].contains("AV3DNPF6")) || (CoordenadasDest[0].contains("AV3DNPF7")) || (CoordenadasDest[0].contains("AV3DNPF8")) || (CoordenadasDest[0].contains("AV3DNPF9")) || (CoordenadasDest[0].contains("AV3DNPTS")) || (CoordenadasDest[0].contains("AV3DNPTM")) || (CoordenadasDest[0].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {xda = new Apfloat(expr.calculate() - xt, PrecisaoApfloat);} catch (Exception e) {}
						}
					else
						xda = (new Apfloat(Double.parseDouble(CoordenadasDest[0]), PrecisaoApfloat)).add(new Apfloat(-xt, PrecisaoApfloat));

					Apfloat yoa = null;

					if ((CoordenadasOrig[1].contains("AV3DNP0")) || (CoordenadasOrig[1].contains("AV3DNP1")) || (CoordenadasOrig[1].contains("AV3DNP2")) || (CoordenadasOrig[1].contains("AV3DNP3")) || (CoordenadasOrig[1].contains("AV3DNP4")) || (CoordenadasOrig[1].contains("AV3DNP5")) || (CoordenadasOrig[1].contains("AV3DNP6")) || (CoordenadasOrig[1].contains("AV3DNP7")) || (CoordenadasOrig[1].contains("AV3DNP8")) || (CoordenadasOrig[1].contains("AV3DNP9")) || (CoordenadasOrig[1].contains("AV3DNPF0")) || (CoordenadasOrig[1].contains("AV3DNPF1")) || (CoordenadasOrig[1].contains("AV3DNPF2")) || (CoordenadasOrig[1].contains("AV3DNPF3")) || (CoordenadasOrig[1].contains("AV3DNPF4")) || (CoordenadasOrig[1].contains("AV3DNF5")) || (CoordenadasOrig[1].contains("AV3DNPF6")) || (CoordenadasOrig[1].contains("AV3DNPF7")) || (CoordenadasOrig[1].contains("AV3DNPF8")) || (CoordenadasOrig[1].contains("AV3DNPF9")) || (CoordenadasOrig[1].contains("AV3DNPTS")) || (CoordenadasOrig[1].contains("AV3DNPTM")) || (CoordenadasOrig[1].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {yoa = new Apfloat(-expr.calculate() - yt, PrecisaoApfloat);} catch (Exception e) {}
						}
					else
						yoa = (new Apfloat(-Double.parseDouble(CoordenadasOrig[1]), PrecisaoApfloat)).add(new Apfloat(-yt, PrecisaoApfloat));

					Apfloat yda = null;

					if ((CoordenadasDest[1].contains("AV3DNP0")) || (CoordenadasDest[1].contains("AV3DNP1")) || (CoordenadasDest[1].contains("AV3DNP2")) || (CoordenadasDest[1].contains("AV3DNP3")) || (CoordenadasDest[1].contains("AV3DNP4")) || (CoordenadasDest[1].contains("AV3DNP5")) || (CoordenadasDest[1].contains("AV3DNP6")) || (CoordenadasDest[1].contains("AV3DNP7")) || (CoordenadasDest[1].contains("AV3DNP8")) || (CoordenadasDest[1].contains("AV3DNP9")) || (CoordenadasDest[1].contains("AV3DNPF0")) || (CoordenadasDest[1].contains("AV3DNPF1")) || (CoordenadasDest[1].contains("AV3DNPF2")) || (CoordenadasDest[1].contains("AV3DNPF3")) || (CoordenadasDest[1].contains("AV3DNPF4")) || (CoordenadasDest[1].contains("AV3DNF5")) || (CoordenadasDest[1].contains("AV3DNPF6")) || (CoordenadasDest[1].contains("AV3DNPF7")) || (CoordenadasDest[1].contains("AV3DNPF8")) || (CoordenadasDest[1].contains("AV3DNPF9")) || (CoordenadasDest[1].contains("AV3DNPTS")) || (CoordenadasDest[1].contains("AV3DNPTM")) || (CoordenadasDest[1].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {yda = new Apfloat(-expr.calculate() - yt, PrecisaoApfloat);} catch (Exception e) {}
						}
					else
						yda = (new Apfloat(-Double.parseDouble(CoordenadasDest[1]), PrecisaoApfloat)).add(new Apfloat(-yt, PrecisaoApfloat));

					Apfloat zoa = null;

					if ((CoordenadasOrig[2].contains("AV3DNP0")) || (CoordenadasOrig[2].contains("AV3DNP1")) || (CoordenadasOrig[2].contains("AV3DNP2")) || (CoordenadasOrig[2].contains("AV3DNP3")) || (CoordenadasOrig[2].contains("AV3DNP4")) || (CoordenadasOrig[2].contains("AV3DNP5")) || (CoordenadasOrig[2].contains("AV3DNP6")) || (CoordenadasOrig[2].contains("AV3DNP7")) || (CoordenadasOrig[2].contains("AV3DNP8")) || (CoordenadasOrig[2].contains("AV3DNP9")) || (CoordenadasOrig[2].contains("AV3DNPF0")) || (CoordenadasOrig[2].contains("AV3DNPF1")) || (CoordenadasOrig[2].contains("AV3DNPF2")) || (CoordenadasOrig[2].contains("AV3DNPF3")) || (CoordenadasOrig[2].contains("AV3DNPF4")) || (CoordenadasOrig[2].contains("AV3DNF5")) || (CoordenadasOrig[2].contains("AV3DNPF6")) || (CoordenadasOrig[2].contains("AV3DNPF7")) || (CoordenadasOrig[2].contains("AV3DNPF8")) || (CoordenadasOrig[2].contains("AV3DNPF9")) || (CoordenadasOrig[2].contains("AV3DNPTS")) || (CoordenadasOrig[2].contains("AV3DNPTM")) || (CoordenadasOrig[2].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasOrig[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {zoa = new Apfloat(-expr.calculate() - zt, PrecisaoApfloat);} catch (Exception e) {}
						
						}
					else
						zoa = (new Apfloat(-Double.parseDouble(CoordenadasOrig[2]), PrecisaoApfloat)).add(new Apfloat(-zt, PrecisaoApfloat));

					Apfloat zda = null;

					if ((CoordenadasDest[2].contains("AV3DNP0")) || (CoordenadasDest[2].contains("AV3DNP1")) || (CoordenadasDest[2].contains("AV3DNP2")) || (CoordenadasDest[2].contains("AV3DNP3")) || (CoordenadasDest[2].contains("AV3DNP4")) || (CoordenadasDest[2].contains("AV3DNP5")) || (CoordenadasDest[2].contains("AV3DNP6")) || (CoordenadasDest[2].contains("AV3DNP7")) || (CoordenadasDest[2].contains("AV3DNP8")) || (CoordenadasDest[2].contains("AV3DNP9")) || (CoordenadasDest[2].contains("AV3DNPF0")) || (CoordenadasDest[2].contains("AV3DNPF1")) || (CoordenadasDest[2].contains("AV3DNPF2")) || (CoordenadasDest[2].contains("AV3DNPF3")) || (CoordenadasDest[2].contains("AV3DNPF4")) || (CoordenadasDest[2].contains("AV3DNF5")) || (CoordenadasDest[2].contains("AV3DNPF6")) || (CoordenadasDest[2].contains("AV3DNPF7")) || (CoordenadasDest[2].contains("AV3DNPF8")) || (CoordenadasDest[2].contains("AV3DNPF9")) || (CoordenadasDest[2].contains("AV3DNPTS")) || (CoordenadasDest[2].contains("AV3DNPTM")) || (CoordenadasDest[2].contains("AV3DNPTH")))
						{
						Expression expr = new Expression(CoordenadasDest[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
						try {zda = new Apfloat(-expr.calculate() - zt, PrecisaoApfloat);} catch (Exception e) {}
						}
					else
						zda = (new Apfloat(-Double.parseDouble(CoordenadasDest[2]), PrecisaoApfloat)).add(new Apfloat(-zt, PrecisaoApfloat));

					int xi;
					int yi;
					int xf;
					int yf;

					xi = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xoa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(yoa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).add(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zoa.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).multiply(new Apfloat(-1, PrecisaoApfloat))))).doubleValue()) - CorrecaoX;

					yi = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xoa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(yoa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).add(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zoa.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa)))))))).doubleValue()) - CorrecaoY;

					xf = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xda.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(yda.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).add(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xda.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zda.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).multiply(new Apfloat(-1, PrecisaoApfloat))))).doubleValue()) - CorrecaoX;

					yf = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xda.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(yda.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).add(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xda.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zda.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda)))))))).doubleValue()) - CorrecaoY;

					Apfloat ProdutoEscalaroa = xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zoa.multiply(new Apfloat(Phit, PrecisaoApfloat)).multiply(new Apfloat(-1, PrecisaoApfloat)));

					Apfloat ProdutoEscalarda = xda.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zda.multiply(new Apfloat(Phit, PrecisaoApfloat)).multiply(new Apfloat(-1, PrecisaoApfloat)));

					try
						{
						if ((ApfloatMath.acos((new Apfloat(FlagMouseY, PrecisaoApfloat)).multiply(ProdutoEscalaroa).divide(ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).doubleValue() < AnguloVisao + MargemAnguloVisao) && (ApfloatMath.acos((new Apfloat(FlagMouseY, PrecisaoApfloat)).multiply(ProdutoEscalarda).divide(ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yoa)).add(zda.multiply(zoa))))).doubleValue() < AnguloVisao + MargemAnguloVisao) && (ApfloatMath.min(new Apfloat(xi, PrecisaoApfloat), ApfloatMath.min(new Apfloat(yi, PrecisaoApfloat), ApfloatMath.min(new Apfloat(xf, PrecisaoApfloat), new Apfloat(yf, PrecisaoApfloat)))).doubleValue() > 0) && (ApfloatMath.max(new Apfloat(xi + CorrecaoXF, PrecisaoApfloat), (new Apfloat(xf + CorrecaoXF, PrecisaoApfloat))).doubleValue() < (new Apfloat(TamanhoPlanoX, PrecisaoApfloat)).doubleValue()) && (ApfloatMath.max(new Apfloat(yi + CorrecaoYF, PrecisaoApfloat), (new Apfloat(yf + CorrecaoYF, PrecisaoApfloat))).doubleValue() < (new Apfloat(TamanhoPlanoY, PrecisaoApfloat)).doubleValue()))
							{
							if (TotalLinhas + 1 < Integer.MAX_VALUE)
								TotalLinhas++;
							else break labelLinhas;

							if (Campos.length == 1)
								Comp.addLine(xi, yi, xf, yf, CorLinha, TotalLinhas);
							else
								{
								if (! (Campos[1].equals("")))
									{
									String [] RGB;

									if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
										RGB = Campos[1].split("divisor");
									else if (Campos[1].contains("divisor"))
										RGB = Campos[1].split("divisor");
									else
										RGB = Campos[1].split(",");

									for (j = 0; j < 3; j++)
										if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
											{
											Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
											try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
											if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
											}

									Comp.addLine(xi, yi, xf, yf, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), TotalLinhas);

									StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
									}
								else
									Comp.addLine(xi, yi, xf, yf, CorLinha, TotalLinhas);
								}
							}
						} catch (InfiniteExpansionException | ArithmeticException e) {}
					}
				}
			}

		if (TEspaco > 0) FlagRepaint = 1;

		// Para identificar a última linha.

		// if (TEspaco > 0) Comp.addLine(0, 0, 0, 0, Color.WHITE, Integer.MAX_VALUE);

		TotalTriangulosShapePreenchidos = 0;

		TEspaco = EspacoTriangulosShapePreenchidos.length;

		labelPoligonos: for (i = 0; i < TEspaco; i++)
			{
			if (! (EspacoTriangulosShapePreenchidos[i].equals("")))
				{
				TriangulosString = "";

				String [] Campos;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					Campos = EspacoTriangulosShapePreenchidos[i].split("color");
				else if (EspacoTriangulosShapePreenchidos[i].contains("color"))
					Campos = EspacoTriangulosShapePreenchidos[i].split("color");
				else
					Campos = EspacoTriangulosShapePreenchidos[i].split("c");

				String [] Pontos;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					Pontos = Campos[0].split("DIVISOR");
				else if (Campos[0].contains("DIVISOR"))
					Pontos = Campos[0].split("DIVISOR");
				else
					Pontos = Campos[0].split(";");

				Pontoslength = Pontos.length;

				int ContadorPontos = 0;

				for (j = 0; j < Pontoslength; j++)
					{
					String [] Coordenadas;

					if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
						Coordenadas = Pontos[j].split("divisor");
					else if (Pontos[j].contains("divisor"))
						Coordenadas = Pontos[j].split("divisor");
					else
						Coordenadas = Pontos[j].split(",");

					if (ApfloatFlag == 0)
						{
						double xp = 0;

						if ((Coordenadas[0].contains("AV3DNP0")) || (Coordenadas[0].contains("AV3DNP1")) || (Coordenadas[0].contains("AV3DNP2")) || (Coordenadas[0].contains("AV3DNP3")) || (Coordenadas[0].contains("AV3DNP4")) || (Coordenadas[0].contains("AV3DNP5")) || (Coordenadas[0].contains("AV3DNP6")) || (Coordenadas[0].contains("AV3DNP7")) || (Coordenadas[0].contains("AV3DNP8")) || (Coordenadas[0].contains("AV3DNP9")) || (Coordenadas[0].contains("AV3DNPF0")) || (Coordenadas[0].contains("AV3DNPF1")) || (Coordenadas[0].contains("AV3DNPF2")) || (Coordenadas[0].contains("AV3DNPF3")) || (Coordenadas[0].contains("AV3DNPF4")) || (Coordenadas[0].contains("AV3DNF5")) || (Coordenadas[0].contains("AV3DNPF6")) || (Coordenadas[0].contains("AV3DNPF7")) || (Coordenadas[0].contains("AV3DNPF8")) || (Coordenadas[0].contains("AV3DNPF9")) || (Coordenadas[0].contains("AV3DNPTS")) || (Coordenadas[0].contains("AV3DNPTM")) || (Coordenadas[0].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {xp = expr.calculate() - xt;} catch (Exception e) {}
							}
						else
							xp = Double.parseDouble(Coordenadas[0]) - xt;

						double yp = 0;

						if ((Coordenadas[1].contains("AV3DNP0")) || (Coordenadas[1].contains("AV3DNP1")) || (Coordenadas[1].contains("AV3DNP2")) || (Coordenadas[1].contains("AV3DNP3")) || (Coordenadas[1].contains("AV3DNP4")) || (Coordenadas[1].contains("AV3DNP5")) || (Coordenadas[1].contains("AV3DNP6")) || (Coordenadas[1].contains("AV3DNP7")) || (Coordenadas[1].contains("AV3DNP8")) || (Coordenadas[1].contains("AV3DNP9")) || (Coordenadas[1].contains("AV3DNPF0")) || (Coordenadas[1].contains("AV3DNPF1")) || (Coordenadas[1].contains("AV3DNPF2")) || (Coordenadas[1].contains("AV3DNPF3")) || (Coordenadas[1].contains("AV3DNPF4")) || (Coordenadas[1].contains("AV3DNF5")) || (Coordenadas[1].contains("AV3DNPF6")) || (Coordenadas[1].contains("AV3DNPF7")) || (Coordenadas[1].contains("AV3DNPF8")) || (Coordenadas[1].contains("AV3DNPF9")) || (Coordenadas[1].contains("AV3DNPTS")) || (Coordenadas[1].contains("AV3DNPTM")) || (Coordenadas[1].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {yp = -expr.calculate() - yt;} catch (Exception e) {}
							}
						else
							yp = -Double.parseDouble(Coordenadas[1]) - yt;

						double zp = 0;

						if ((Coordenadas[2].contains("AV3DNP0")) || (Coordenadas[2].contains("AV3DNP1")) || (Coordenadas[2].contains("AV3DNP2")) || (Coordenadas[2].contains("AV3DNP3")) || (Coordenadas[2].contains("AV3DNP4")) || (Coordenadas[2].contains("AV3DNP5")) || (Coordenadas[2].contains("AV3DNP6")) || (Coordenadas[2].contains("AV3DNP7")) || (Coordenadas[2].contains("AV3DNP8")) || (Coordenadas[2].contains("AV3DNP9")) || (Coordenadas[2].contains("AV3DNPF0")) || (Coordenadas[2].contains("AV3DNPF1")) || (Coordenadas[2].contains("AV3DNPF2")) || (Coordenadas[2].contains("AV3DNPF3")) || (Coordenadas[2].contains("AV3DNPF4")) || (Coordenadas[2].contains("AV3DNF5")) || (Coordenadas[2].contains("AV3DNPF6")) || (Coordenadas[2].contains("AV3DNPF7")) || (Coordenadas[2].contains("AV3DNPF8")) || (Coordenadas[2].contains("AV3DNPF9")) || (Coordenadas[2].contains("AV3DNPTS")) || (Coordenadas[2].contains("AV3DNPTM")) || (Coordenadas[2].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {zp = -expr.calculate() - zt;} catch (Exception e) {}
							}
						else
							zp = -Double.parseDouble(Coordenadas[2]) - zt;

						xpp = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * DistanciaTela * (Math.cos(Rott) * (xp * Math.sin(Tetat) + yp * Math.cos(Tetat)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)) - Math.sin(Rott) * (xp * Math.cos(Tetat) * Math.sin(Phit) - yp * Math.sin(Tetat) * Math.sin(Phit) + zp * Math.cos(Phit)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)))) - CorrecaoX;

						ypp = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * DistanciaTela * (Math.sin(Rott) * (xp * Math.sin(Tetat) + yp * Math.cos(Tetat)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)) + Math.cos(Rott) * (xp * Math.cos(Tetat) * Math.sin(Phit) - yp * Math.sin(Tetat) * Math.sin(Phit) + zp * Math.cos(Phit)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)))) - CorrecaoY;

						ProdutoEscalar = xp * Math.cos(Tetat) * Math.cos(Phit) - yp * Math.sin(Tetat) * Math.cos(Phit) - zp * Math.sin(Phit);

						if ((Math.acos(FlagMouseY * ProdutoEscalar / Math.sqrt(xp * xp + yp * yp + zp * zp)) < AnguloVisao + MargemAnguloVisao) && ((Math.min(xpp, ypp) > 0) && (xpp + CorrecaoXF < TamanhoPlanoX) && (ypp + CorrecaoYF < TamanhoPlanoY)))
							{
							ContadorPontos++;

								if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
									TriangulosString = TriangulosString + Integer.toString(xpp) + "divisor" + Integer.toString(ypp) + "DIVISOR";
								else
									TriangulosString = TriangulosString + Integer.toString(xpp) + "," + Integer.toString(ypp) + ";";

							SomaXP += xp; SomaYP += yp; SomaZP += zp;
							}
						}
					else
						{
						// Alta precisão com o Apfloat, porém com custo computacional.

						Apfloat xpa = null;

						if ((Coordenadas[0].contains("AV3DNP0")) || (Coordenadas[0].contains("AV3DNP1")) || (Coordenadas[0].contains("AV3DNP2")) || (Coordenadas[0].contains("AV3DNP3")) || (Coordenadas[0].contains("AV3DNP4")) || (Coordenadas[0].contains("AV3DNP5")) || (Coordenadas[0].contains("AV3DNP6")) || (Coordenadas[0].contains("AV3DNP7")) || (Coordenadas[0].contains("AV3DNP8")) || (Coordenadas[0].contains("AV3DNP9")) || (Coordenadas[0].contains("AV3DNPF0")) || (Coordenadas[0].contains("AV3DNPF1")) || (Coordenadas[0].contains("AV3DNPF2")) || (Coordenadas[0].contains("AV3DNPF3")) || (Coordenadas[0].contains("AV3DNPF4")) || (Coordenadas[0].contains("AV3DNF5")) || (Coordenadas[0].contains("AV3DNPF6")) || (Coordenadas[0].contains("AV3DNPF7")) || (Coordenadas[0].contains("AV3DNPF8")) || (Coordenadas[0].contains("AV3DNPF9")) || (Coordenadas[0].contains("AV3DNPTS")) || (Coordenadas[0].contains("AV3DNPTM")) || (Coordenadas[0].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[0].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {xpa = new Apfloat(expr.calculate() - xt, PrecisaoApfloat);} catch (Exception e) {}
							}
						else
							xpa = (new Apfloat(Double.parseDouble(Coordenadas[0]), PrecisaoApfloat)).add(new Apfloat(-xt, PrecisaoApfloat));

						Apfloat ypa = null;

						if ((Coordenadas[1].contains("AV3DNP0")) || (Coordenadas[1].contains("AV3DNP1")) || (Coordenadas[1].contains("AV3DNP2")) || (Coordenadas[1].contains("AV3DNP3")) || (Coordenadas[1].contains("AV3DNP4")) || (Coordenadas[1].contains("AV3DNP5")) || (Coordenadas[1].contains("AV3DNP6")) || (Coordenadas[1].contains("AV3DNP7")) || (Coordenadas[1].contains("AV3DNP8")) || (Coordenadas[1].contains("AV3DNP9")) || (Coordenadas[1].contains("AV3DNPF0")) || (Coordenadas[1].contains("AV3DNPF1")) || (Coordenadas[1].contains("AV3DNPF2")) || (Coordenadas[1].contains("AV3DNPF3")) || (Coordenadas[1].contains("AV3DNPF4")) || (Coordenadas[1].contains("AV3DNF5")) || (Coordenadas[1].contains("AV3DNPF6")) || (Coordenadas[1].contains("AV3DNPF7")) || (Coordenadas[1].contains("AV3DNPF8")) || (Coordenadas[1].contains("AV3DNPF9")) || (Coordenadas[1].contains("AV3DNPTS")) || (Coordenadas[1].contains("AV3DNPTM")) || (Coordenadas[1].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[1].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {ypa = new Apfloat(-expr.calculate() - yt, PrecisaoApfloat);} catch (Exception e) {}
							}
						else
							ypa = (new Apfloat(-Double.parseDouble(Coordenadas[1]), PrecisaoApfloat)).add(new Apfloat(-yt, PrecisaoApfloat));

						Apfloat zpa = null;

						if ((Coordenadas[2].contains("AV3DNP0")) || (Coordenadas[2].contains("AV3DNP1")) || (Coordenadas[2].contains("AV3DNP2")) || (Coordenadas[2].contains("AV3DNP3")) || (Coordenadas[2].contains("AV3DNP4")) || (Coordenadas[2].contains("AV3DNP5")) || (Coordenadas[2].contains("AV3DNP6")) || (Coordenadas[2].contains("AV3DNP7")) || (Coordenadas[2].contains("AV3DNP8")) || (Coordenadas[2].contains("AV3DNP9")) || (Coordenadas[2].contains("AV3DNPF0")) || (Coordenadas[2].contains("AV3DNPF1")) || (Coordenadas[2].contains("AV3DNPF2")) || (Coordenadas[2].contains("AV3DNPF3")) || (Coordenadas[2].contains("AV3DNPF4")) || (Coordenadas[2].contains("AV3DNF5")) || (Coordenadas[2].contains("AV3DNPF6")) || (Coordenadas[2].contains("AV3DNPF7")) || (Coordenadas[2].contains("AV3DNPF8")) || (Coordenadas[2].contains("AV3DNPF9")) || (Coordenadas[2].contains("AV3DNPTS")) || (Coordenadas[2].contains("AV3DNPTM")) || (Coordenadas[2].contains("AV3DNPTH")))
							{
							Expression expr = new Expression(Coordenadas[2].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
							try {zpa = new Apfloat(-expr.calculate() - zt, PrecisaoApfloat);} catch (Exception e) {}
							}
						else
							zpa = (new Apfloat(-Double.parseDouble(Coordenadas[2]), PrecisaoApfloat)).add(new Apfloat(-zt, PrecisaoApfloat));

						xpp = (int) (TamanhoPlanoXAd / 2 + TamanhoPlanoXAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xpa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(ypa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).add(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zpa.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).multiply(new Apfloat(-1, PrecisaoApfloat))))).doubleValue()) - CorrecaoX;

						ypp = (int) (TamanhoPlanoYAd / 2 + FlagMouseY * TamanhoPlanoYAd / 2 * ((new Apfloat(DistanciaTela, PrecisaoApfloat)).multiply(ApfloatMath.sin(new Apfloat(Rott, PrecisaoApfloat)).multiply(xpa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).add(ypa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).add(ApfloatMath.cos(new Apfloat(Rott, PrecisaoApfloat)).multiply(xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.sin(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zpa.multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa)))))))).doubleValue()) - CorrecaoY;

						Apfloat ProdutoEscalara = xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(ApfloatMath.cos(new Apfloat(Phit, PrecisaoApfloat))).multiply(new Apfloat(-1, PrecisaoApfloat))).add(zpa.multiply(new Apfloat(Phit, PrecisaoApfloat)).multiply(new Apfloat(-1, PrecisaoApfloat)));

						try
							{
							if (((ApfloatMath.acos((new Apfloat(FlagMouseY, PrecisaoApfloat)).multiply(ProdutoEscalara).divide(ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(ApfloatMath.sin(new Apfloat(Tetat, PrecisaoApfloat))).multiply(zpa))))).doubleValue() < AnguloVisao + MargemAnguloVisao)) && (ApfloatMath.min(new Apfloat(xpp, PrecisaoApfloat), new Apfloat(ypp, PrecisaoApfloat)).doubleValue() > 0) && ((new Apfloat(xpp + CorrecaoXF)).doubleValue() < (new Apfloat(TamanhoPlanoX, PrecisaoApfloat)).doubleValue()) && (new Apfloat(ypp + CorrecaoYF)).doubleValue() < (new Apfloat(TamanhoPlanoY, PrecisaoApfloat)).doubleValue())
								{
								ContadorPontos++;

								if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
									TriangulosString = TriangulosString + Integer.toString(xpp) + "divisor" + Integer.toString(ypp) + "DIVISOR";
								else
									TriangulosString = TriangulosString + Integer.toString(xpp) + "," + Integer.toString(ypp) + ";";

								SomaXP += xpa.doubleValue();
								SomaYP += ypa.doubleValue();
								SomaZP += zpa.doubleValue();
								}
							} catch (InfiniteExpansionException | ArithmeticException e) {}
					}


					if (TotalTriangulosShapePreenchidos + Pontoslength < Integer.MAX_VALUE)
						TotalTriangulosShapePreenchidos += Pontoslength;
					else break labelPoligonos;

					if (ContadorPontos == Pontoslength)
							{
							String [] PontosTriangulos;

							if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
								PontosTriangulos = TriangulosString.split("DIVISOR");
							else if (TriangulosString.contains("DIVISOR"))
								PontosTriangulos = TriangulosString.split("DIVISOR");
							else
								PontosTriangulos = TriangulosString.split(";");

							String [] ParametroTriangulo = new String[3];

							l = 0;

							do
								{
								ParametroTriangulo[0] = PontosTriangulos[l % Pontoslength];

								k = 1;

								do
									{
									ParametroTriangulo[k] = PontosTriangulos[(k + l) % Pontoslength];
									} while (k++ < 2);

								String [] ParametroTrianguloCoordenadas1;

								if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
									ParametroTrianguloCoordenadas1 = ParametroTriangulo[0].split("divisor");
								else if (ParametroTriangulo[0].contains("divisor"))
									ParametroTrianguloCoordenadas1 = ParametroTriangulo[0].split("divisor");
								else
									ParametroTrianguloCoordenadas1 = ParametroTriangulo[0].split(",");

								String [] ParametroTrianguloCoordenadas2;

								if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
									ParametroTrianguloCoordenadas2 = ParametroTriangulo[1].split("divisor");
								else if (ParametroTriangulo[1].contains("divisor"))
									ParametroTrianguloCoordenadas2 = ParametroTriangulo[1].split("divisor");
								else
									ParametroTrianguloCoordenadas2 = ParametroTriangulo[1].split(",");

								String [] ParametroTrianguloCoordenadas3;

								if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
									ParametroTrianguloCoordenadas3 = ParametroTriangulo[2].split("divisor");
								else if (ParametroTriangulo[2].contains("divisor"))
									ParametroTrianguloCoordenadas3 = ParametroTriangulo[2].split("divisor");
								else
									ParametroTrianguloCoordenadas3 = ParametroTriangulo[2].split(",");

								if (Campos.length == 1)
									Comp.addTriangulosShape(Integer.parseInt(ParametroTrianguloCoordenadas1[0]), Integer.parseInt(ParametroTrianguloCoordenadas1[1]), Integer.parseInt(ParametroTrianguloCoordenadas2[0]), Integer.parseInt(ParametroTrianguloCoordenadas2[1]), Integer.parseInt(ParametroTrianguloCoordenadas3[0]), Integer.parseInt(ParametroTrianguloCoordenadas3[1]), CorTrianguloShape, (x - SomaXP / Pontoslength) * (x - SomaXP / Pontoslength) + (y - SomaYP / Pontoslength) * (y - SomaYP / Pontoslength) + (z - SomaZP / Pontoslength) * (z - SomaZP / Pontoslength), TotalTriangulosShapePreenchidos);
								else
									{
									if (! (Campos[1].equals("")))
										{
										String [] RGB;

										if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
											RGB = Campos[1].split("divisor");
										else if (Campos[1].contains("divisor"))
											RGB = Campos[1].split("divisor");
										else
											RGB = Campos[1].split(",");

										for (j = 0; j < 3; j++)
											if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
												{
												Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
												try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
												if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
												}

										Comp.addTriangulosShape(Integer.parseInt(ParametroTrianguloCoordenadas1[0]), Integer.parseInt(ParametroTrianguloCoordenadas1[1]), Integer.parseInt(ParametroTrianguloCoordenadas2[0]), Integer.parseInt(ParametroTrianguloCoordenadas2[1]), Integer.parseInt(ParametroTrianguloCoordenadas3[0]), Integer.parseInt(ParametroTrianguloCoordenadas3[1]), new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), (x - SomaXP / Pontoslength) * (x - SomaXP / Pontoslength) + (y - SomaYP / Pontoslength) * (y - SomaYP / Pontoslength) + (z - SomaZP / Pontoslength) * (z - SomaZP / Pontoslength), TotalTriangulosShapePreenchidos);
										StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
										}
									else
										Comp.addTriangulosShape(Integer.parseInt(ParametroTrianguloCoordenadas1[0]), Integer.parseInt(ParametroTrianguloCoordenadas1[1]), Integer.parseInt(ParametroTrianguloCoordenadas2[0]), Integer.parseInt(ParametroTrianguloCoordenadas2[1]), Integer.parseInt(ParametroTrianguloCoordenadas3[0]), Integer.parseInt(ParametroTrianguloCoordenadas3[1]), CorTrianguloShape, (x - SomaXP / Pontoslength) * (x - SomaXP / Pontoslength) + (y - SomaYP / Pontoslength) * (y - SomaYP / Pontoslength) + (z - SomaZP / Pontoslength) * (z - SomaZP / Pontoslength), TotalTriangulosShapePreenchidos);
									}

								k = 0;
								} while (l++ < Pontoslength);

							TriangulosString = "";
							SomaXP = 0; SomaYP = 0; SomaZP = 0;
							}
					}
				}
			}

		if (TEspaco > 0) FlagRepaint = 1;

		// Para identificar o último triângulo.

		// if (TEspaco > 0) Comp.addTriangulosShape(0, 0, 0, 0, 0, 0, Color.WHITE, 0, Integer.MAX_VALUE);

		TotalLegendas = 0;

		int yl = ShiftVerticalLegendas;

		TEspaco = EspacoLegendas.length;

		labelLegendas: for (i = 0; i < TEspaco; i++)
			{
			if (EspacoLegendas.length < Integer.MAX_VALUE)
				TotalLegendas = EspacoLegendas.length;
			else break labelLegendas;

			if (! (EspacoLegendas[i].equals("")))
				{
				String [] Campos;

				if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
					Campos = EspacoLegendas[i].split("DIVISOR");
				else if (EspacoLegendas[i].contains("DIVISOR"))
					Campos = EspacoLegendas[i].split("DIVISOR");
				else
					Campos = EspacoLegendas[i].split(";");

				if (Campos.length == 1)
					{
					Comp.addTexto(Campos[0], 5, yl, CorLegenda, TamanhoFonteLegendas, i + 1);
					yl += TamanhoFonteLegendas + EspacamentoVerticalLegendas;
					}
				else if (Campos.length <= 2)
					{
					if (! (Campos[1].equals("")))
						{
						String [] RGB;

						if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
							RGB = Campos[1].split("divisor");
						else if (Campos[1].contains("divisor"))
							RGB = Campos[1].split("divisor");
						else
							RGB = Campos[1].split(",");

						for (j = 0; j < 3; j++)
							if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
								{
								Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
								try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
								if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
								}

						Comp.addTexto(Campos[0], 5, yl, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), TamanhoFonteLegendas, i + 1);

						StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
						}
					else
						{
						String [] RGB;

						if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
							RGB = Campos[1].split("divisor");
						else if (Campos[1].contains("divisor"))
							RGB = Campos[1].split("divisor");
						else
							RGB = Campos[1].split(",");

						for (j = 0; j < 3; j++)
							if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
								{
								Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
								try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
								if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
								}

						Comp.addTexto(Campos[0], 5, yl, CorLegenda, TamanhoFonteLegendas, i + 1);

						StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
						}

					yl += TamanhoFonteLegendas + EspacamentoVerticalLegendas;
					}
				else
					{
					if (! (Campos[2].equals("")))
						{
						String [] RGB;

						if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
							RGB = Campos[1].split("divisor");
						else if (Campos[1].contains("divisor"))
							RGB = Campos[1].split("divisor");
						else
							RGB = Campos[1].split(",");

						for (j = 0; j < 3; j++)
							if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
								{
								Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
								try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
								if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
								}

						Comp.addTexto(Campos[0], 5, yl, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), Integer.parseInt(Campos[2]), i + 1);

						StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
						yl += Integer.parseInt(Campos[2]);
						}
					else
						{
						String [] RGB;

						if ((EspacoP.contains("AV3DNP0")) || (EspacoP.contains("AV3DNP1")) || (EspacoP.contains("AV3DNP2")) || (EspacoP.contains("AV3DNP3")) || (EspacoP.contains("AV3DNP4")) || (EspacoP.contains("AV3DNP5")) || (EspacoP.contains("AV3DNP6")) || (EspacoP.contains("AV3DNP7")) || (EspacoP.contains("AV3DNP8")) || (EspacoP.contains("AV3DNP9")) || (EspacoP.contains("AV3DNPF0")) || (EspacoP.contains("AV3DNPF1")) || (EspacoP.contains("AV3DNPF2")) || (EspacoP.contains("AV3DNPF3")) || (EspacoP.contains("AV3DNPF4")) || (EspacoP.contains("AV3DNF5")) || (EspacoP.contains("AV3DNPF6")) || (EspacoP.contains("AV3DNPF7")) || (EspacoP.contains("AV3DNPF8")) || (EspacoP.contains("AV3DNPF9")) || (EspacoP.contains("AV3DNPTS")) || (EspacoP.contains("AV3DNPTM")) || (EspacoP.contains("AV3DNPTH")))
							RGB = Campos[1].split("divisor");
						else if (Campos[1].contains("divisor"))
							RGB = Campos[1].split("divisor");
						else
							RGB = Campos[1].split(",");

						for (j = 0; j < 3; j++)
							if ((RGB[j].contains("AV3DNP0")) || (RGB[j].contains("AV3DNP1")) || (RGB[j].contains("AV3DNP2")) || (RGB[j].contains("AV3DNP3")) || (RGB[j].contains("AV3DNP4")) || (RGB[j].contains("AV3DNP5")) || (RGB[j].contains("AV3DNP6")) || (RGB[j].contains("AV3DNP7")) || (RGB[j].contains("AV3DNP8")) || (RGB[j].contains("AV3DNP9")) || (RGB[j].contains("AV3DNPF0")) || (RGB[j].contains("AV3DNPF1")) || (RGB[j].contains("AV3DNPF2")) || (RGB[j].contains("AV3DNPF3")) || (RGB[j].contains("AV3DNPF4")) || (RGB[j].contains("AV3DNF5")) || (RGB[j].contains("AV3DNPF6")) || (RGB[j].contains("AV3DNPF7")) || (RGB[j].contains("AV3DNPF8")) || (RGB[j].contains("AV3DNPF9")) || (RGB[j].contains("AV3DNPTS")) || (RGB[j].contains("AV3DNPTM")) || (RGB[j].contains("AV3DNPTH")))
								{
								Expression expr = new Expression(RGB[j].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
								try {RGB[j] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[j] = String.valueOf(255);}
								if ((Integer.parseInt(RGB[j]) < 0) || (Integer.parseInt(RGB[j]) > 255)) RGB[j] = String.valueOf(255);
								}

						Comp.addTexto(Campos[0], 5, yl, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), TamanhoFonteLegendas, i + 1);

						StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
						yl += TamanhoFonteLegendas + EspacamentoVerticalLegendas;
						}
					}
				}
			}

		// Para identificar a última legenda.

		if ((TEspaco > 0) || (FlagRepaint == 1))
			{Comp.addTexto("", 0, 0, Color.WHITE, 0, Integer.MAX_VALUE); FlagRepaint = 0;}
		}
		
	public String LerEspaco(String ArquivoEspacoArg, String Debug)
		{
		File file = new File(ArquivoEspacoArg);
		String EspacoStr;
		int ContadorEspacoInvalido = 0;

		try
			{
			BufferedReader br = new BufferedReader(new FileReader(file));
			EspacoStr = "";
			String Linha;

			do
				{
				Linha = br.readLine(); if (Linha == null) break;
				if ((! Linha.replaceAll(" ", "").equals("")) && (! (Linha.replaceAll(" ", "").charAt(0) == '#'))) EspacoStr += Linha;
				} while (true);

			if (EspacoStr == "") return "Erro";

			String [] EspacoStr2 = EspacoStr.split("@");

			String EspacoStrP;

			if (EspacoStr2.length == 1) EspacoStrP = EspacoStr2[0];	else EspacoStrP = EspacoStr2[0] + EspacoStr2[1];

			if (EspacoStr2.length >= 1)
				{
				String [] EspacoLinhas = EspacoStr2[0].split("\\|");

				TEspaco = EspacoLinhas.length;

				ContadorEspacoInvalido += TEspaco;

				for (i = 0; i < TEspaco; i++)
					{
					if (! (EspacoLinhas[i].equals("")))
						{
						String [] Campos;

						if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
							Campos = EspacoLinhas[i].split("color");
						else if (EspacoLinhas[i].contains("color"))
							Campos = EspacoLinhas[i].split("color");
						else
							Campos = EspacoLinhas[i].split("c");

						if (Campos.length > 2) return "Erro";

						String [] Pontos;

						if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
							Pontos = Campos[0].split("DIVISOR");
						else if (Campos[0].contains("DIVISOR"))
							Pontos = Campos[0].split("DIVISOR");
						else
							Pontos = Campos[0].split(";");

						Pontoslength = Pontos.length;

						if (Pontoslength != 2) return "Erro";

						for (j = 0; j < Pontoslength; j++)
							{
							String [] Coordenadas;

							if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
								Coordenadas = Pontos[j].split("divisor");
							else if (Pontos[j].contains("divisor"))
								Coordenadas = Pontos[j].split("divisor");
							else
								Coordenadas = Pontos[j].split(",");

							if (Coordenadas.length != 3) return "Erro";

							for (k = 0; k < Coordenadas.length; k++)
								if ((! AntonioVandre.NumeroReal(Coordenadas[k])) && (! Coordenadas[k].contains("AV3DNP0")) && (! Coordenadas[k].contains("AV3DNP1")) && (! Coordenadas[k].contains("AV3DNP2")) && (! Coordenadas[k].contains("AV3DNP3")) && (! Coordenadas[k].contains("AV3DNP4")) && (! Coordenadas[k].contains("AV3DNP5")) && (! Coordenadas[k].contains("AV3DNP6")) && (! Coordenadas[k].contains("AV3DNP7")) && (! Coordenadas[k].contains("AV3DNP8")) && (! Coordenadas[k].contains("AV3DNP9")) && (! Coordenadas[k].contains("AV3DNPF0")) && (! Coordenadas[k].contains("AV3DNPF1")) && (! Coordenadas[k].contains("AV3DNPF2")) && (! Coordenadas[k].contains("AV3DNPF3")) && (! Coordenadas[k].contains("AV3DNPF4")) && (! Coordenadas[k].contains("AV3DNPF5")) && (! Coordenadas[k].contains("AV3DNPF6")) && (! Coordenadas[k].contains("AV3DNPF7")) && (! Coordenadas[k].contains("AV3DNPF8")) && (! Coordenadas[k].contains("AV3DNPF9")) && (! Coordenadas[k].contains("AV3DNPTS")) && (! Coordenadas[k].contains("AV3DNPTM")) && (! Coordenadas[k].contains("AV3DNPTH"))) return "Erro";
							}

						if (Campos.length == 2)
							{
							if (! (Campos[1].equals("")))
								{
								String [] RGB;

								if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
									RGB = Campos[1].split("divisor");
								else if (Campos[1].contains("divisor"))
									RGB = Campos[1].split("divisor");
								else
									RGB = Campos[1].split(",");

								if (RGB.length != 3) return "Erro";

								for (k = 0; k < RGB.length; k++)
									{
									if ((RGB[k].contains("AV3DNP0")) || (RGB[k].contains("AV3DNP1")) || (RGB[k].contains("AV3DNP2")) || (RGB[k].contains("AV3DNP3")) || (RGB[k].contains("AV3DNP4")) || (RGB[k].contains("AV3DNP5")) || (RGB[k].contains("AV3DNP6")) || (RGB[k].contains("AV3DNP7")) || (RGB[k].contains("AV3DNP8")) || (RGB[k].contains("AV3DNP9")) || (RGB[k].contains("AV3DNPF0")) || (RGB[k].contains("AV3DNPF1")) || (RGB[k].contains("AV3DNPF2")) || (RGB[k].contains("AV3DNPF3")) || (RGB[k].contains("AV3DNPF4")) || (RGB[k].contains("AV3DNF5")) || (RGB[k].contains("AV3DNPF6")) || (RGB[k].contains("AV3DNPF7")) || (RGB[k].contains("AV3DNPF8")) || (RGB[k].contains("AV3DNPF9")) || (RGB[k].contains("AV3DNPTS")) || (RGB[k].contains("AV3DNPTM")) || (RGB[k].contains("AV3DNPTH")))
										{
										Expression expr = new Expression(RGB[k].replaceAll("AV3DNP0", "(" + String.valueOf(Parametro0) + ")").replaceAll("AV3DNP1", "(" + String.valueOf(Parametro1) + ")").replaceAll("AV3DNP2", "(" + String.valueOf(Parametro2) + ")").replaceAll("AV3DNP3", "(" + String.valueOf(Parametro3) + ")").replaceAll("AV3DNP4", "(" + String.valueOf(Parametro4) + ")").replaceAll("AV3DNP5", "(" + String.valueOf(Parametro5) + ")").replaceAll("AV3DNP6", "(" + String.valueOf(Parametro6) + ")").replaceAll("AV3DNP7", "(" + String.valueOf(Parametro7) + ")").replaceAll("AV3DNP8", "(" + String.valueOf(Parametro8) + ")").replaceAll("AV3DNP9", "(" + String.valueOf(Parametro9) + ")").replaceAll("AV3DNPF0", "(" + String.valueOf(ParametroFile0) + ")").replaceAll("AV3DNPF1", "(" + String.valueOf(ParametroFile1) + ")").replaceAll("AV3DNPF2", "(" + String.valueOf(ParametroFile2) + ")").replaceAll("AV3DNPF3", "(" + String.valueOf(ParametroFile3) + ")").replaceAll("AV3DNPF4", "(" + String.valueOf(ParametroFile4) + ")").replaceAll("AV3DNPF5", "(" + String.valueOf(ParametroFile5) + ")").replaceAll("AV3DNPF6", "(" + String.valueOf(ParametroFile6) + ")").replaceAll("AV3DNPF7", "(" + String.valueOf(ParametroFile7) + ")").replaceAll("AV3DNPF8", "(" + String.valueOf(ParametroFile8) + ")").replaceAll("AV3DNPF9", "(" + String.valueOf(ParametroFile9) + ")").replaceAll("AV3DNPTS", "(" + String.valueOf(ParametroTimeS) + ")").replaceAll("AV3DNPTM", "(" + String.valueOf(ParametroTimeM) + ")").replaceAll("AV3DNPTH", "(" + String.valueOf(ParametroTimeH) + ")"));
										try {RGB[k] = String.valueOf((int) expr.calculate());} catch (Exception e) {RGB[k] = String.valueOf(255);}
										if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255)) RGB[k] = String.valueOf(255);
										return "Erro";
										}
									}
								}
							}
						}
					}
				}

			if (EspacoStr2.length >= 2)
				{
				String [] EspacoTriangulosShapePreenchidos = EspacoStr2[1].split("\\|");

				TEspaco = EspacoTriangulosShapePreenchidos.length;

				ContadorEspacoInvalido += TEspaco;

				for (i = 0; i < TEspaco; i++)
					{
					if (! (EspacoTriangulosShapePreenchidos[i].equals("")))
						{
						String [] Campos;

						if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
							Campos = EspacoTriangulosShapePreenchidos[i].split("color");
						else if (EspacoTriangulosShapePreenchidos[i].contains("color"))
							Campos = EspacoTriangulosShapePreenchidos[i].split("color");
						else
							Campos = EspacoTriangulosShapePreenchidos[i].split("c");

						if (Campos.length > 2) return "Erro";

						String [] Pontos;

						if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
							Pontos = Campos[0].split("DIVISOR");
						else if (Campos[0].contains("DIVISOR"))
							Pontos = Campos[0].split("DIVISOR");
						else
							Pontos = Campos[0].split(";");

						Pontoslength = Pontos.length;

						for (j = 0; j < Pontoslength; j++)
							{
							String [] Coordenadas;

							if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
								Coordenadas = Pontos[j].split("divisor");
							else if (Pontos[j].contains("divisor"))
								Coordenadas = Pontos[j].split("divisor");
							else
								Coordenadas = Pontos[j].split(",");

							if (Coordenadas.length != 3) return "Erro";

							for (k = 0; k < Coordenadas.length; k++)
								if ((! AntonioVandre.NumeroReal(Coordenadas[k])) && (! Coordenadas[k].contains("AV3DNP0")) && (! Coordenadas[k].contains("AV3DNP1")) && (! Coordenadas[k].contains("AV3DNP2")) && (! Coordenadas[k].contains("AV3DNP3")) && (! Coordenadas[k].contains("AV3DNP4")) && (! Coordenadas[k].contains("AV3DNP5")) && (! Coordenadas[k].contains("AV3DNP6")) && (! Coordenadas[k].contains("AV3DNP7")) && (! Coordenadas[k].contains("AV3DNP8")) && (! Coordenadas[k].contains("AV3DNP9")) && (! Coordenadas[k].contains("AV3DNPF0")) && (! Coordenadas[k].contains("AV3DNPF1")) && (! Coordenadas[k].contains("AV3DNPF2")) && (! Coordenadas[k].contains("AV3DNPF3")) && (! Coordenadas[k].contains("AV3DNPF4")) && (! Coordenadas[k].contains("AV3DNPF5")) && (! Coordenadas[k].contains("AV3DNPF6")) && (! Coordenadas[k].contains("AV3DNPF7")) && (! Coordenadas[k].contains("AV3DNPF8")) && (! Coordenadas[k].contains("AV3DNPF9")) && (! Coordenadas[k].contains("AV3DNPTS")) && (! Coordenadas[k].contains("AV3DNPTM")) && (! Coordenadas[k].contains("AV3DNPTH"))) return "Erro";
							}

						if (Campos.length == 2)
							{
							if (! (Campos[1].equals("")))
								{
								String [] RGB;

								if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
									RGB = Campos[1].split("divisor");
								else if (Campos[1].contains("divisor"))
									RGB = Campos[1].split("divisor");
								else
									RGB = Campos[1].split(",");

								if (RGB.length != 3) return "Erro";

								for (k = 0; k < RGB.length; k++)
									{
									if ((! AntonioVandre.NumeroNatural(RGB[k])) && (! RGB[k].contains("AV3DNP0")) && (! RGB[k].contains("AV3DNP1")) && (! RGB[k].contains("AV3DNP2")) && (! RGB[k].contains("AV3DNP3")) && (! RGB[k].contains("AV3DNP4")) && (! RGB[k].contains("AV3DNP5")) && (! RGB[k].contains("AV3DNP6")) && (! RGB[k].contains("AV3DNP7")) && (! RGB[k].contains("AV3DNP8")) && (! RGB[k].contains("AV3DNP9"))) return "Erro";

									if ((! RGB[k].contains("AV3DNP0")) && (! RGB[k].contains("AV3DNP1")) && (! RGB[k].contains("AV3DNP2")) && (! RGB[k].contains("AV3DNP3")) && (! RGB[k].contains("AV3DNP4")) && (! RGB[k].contains("AV3DNP5")) && (! RGB[k].contains("AV3DNP6")) && (! RGB[k].contains("AV3DNP7")) && (! RGB[k].contains("AV3DNP8")) && (! RGB[k].contains("AV3DNP9"))) if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255))
										return "Erro";
									}
								}
							}
						}
					}
				}

			if (EspacoStr2.length >= 3)
				{
				String UniaoStringLegendas = EspacoStr2[2];

				for (i = 3; i < EspacoStr2.length; i++)
					UniaoStringLegendas = UniaoStringLegendas + "@" + EspacoStr2[i];

				String [] EspacoLegendas = UniaoStringLegendas.split("\\|");

				TEspaco = EspacoLegendas.length;

				ContadorEspacoInvalido += TEspaco;

				for (i = 0; i < TEspaco; i++)
					{
					if (! (EspacoLegendas[i].equals("")))
						{
						String [] Campos;

						if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
							Campos = EspacoLegendas[i].split("DIVISOR");
						else if (EspacoLegendas[i].contains("DIVISOR"))
							Campos = EspacoLegendas[i].split("DIVISOR");
						else
							Campos = EspacoLegendas[i].split(";");

						if (Campos.length > 3) return "Erro";

						if (Campos.length >= 2)
							{
							if (! (Campos[1].equals("")))
								{
								String [] RGB;

								if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
									RGB = Campos[1].split("divisor");
								else if (Campos[1].contains("divisor"))
									RGB = Campos[1].split("divisor");
								else
									RGB = Campos[1].split(",");

								if (RGB.length != 3) return "Erro";

								for (k = 0; k < RGB.length; k++)
									{
									if ((! AntonioVandre.NumeroNatural(RGB[k])) && (! RGB[k].contains("AV3DNP0")) && (! RGB[k].contains("AV3DNP1")) && (! RGB[k].contains("AV3DNP2")) && (! RGB[k].contains("AV3DNP3")) && (! RGB[k].contains("AV3DNP4")) && (! RGB[k].contains("AV3DNP5")) && (! RGB[k].contains("AV3DNP6")) && (! RGB[k].contains("AV3DNP7")) && (! RGB[k].contains("AV3DNP8")) && (! RGB[k].contains("AV3DNP9"))) return "Erro";

									if (! RGB[k].contains("AV3DNP0")) if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255))
										return "Erro";
									}
								}
							}

						if (Campos.length == 3)
							if (! (Campos[2].equals("")))
								if (! AntonioVandre.NumeroNaturalPositivo(Campos[2])) return "Erro";
						}
					}
				}

			if (ContadorEspacoInvalido == 0) return "Erro";

			if (! (Debug.equals("Debug"))) if ((EspacoStrP.contains("AV3DNP0")) || (EspacoStrP.contains("AV3DNP1")) || (EspacoStrP.contains("AV3DNP2")) || (EspacoStrP.contains("AV3DNP3")) || (EspacoStrP.contains("AV3DNP4")) || (EspacoStrP.contains("AV3DNP5")) || (EspacoStrP.contains("AV3DNP6")) || (EspacoStrP.contains("AV3DNP7")) || (EspacoStrP.contains("AV3DNP8")) || (EspacoStrP.contains("AV3DNP9")) || (EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")) || (EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
				try
					{
					AV3DNavigatorEspacosPCountThread.interrupt ();
					AV3DNavigatorEspacosPCountThread.start();
					} catch (IllegalThreadStateException e) {}

			if (! (Debug.equals("Debug"))) if ((EspacoStrP.contains("AV3DNPF0")) || (EspacoStrP.contains("AV3DNPF1")) || (EspacoStrP.contains("AV3DNPF2")) || (EspacoStrP.contains("AV3DNPF3")) || (EspacoStrP.contains("AV3DNPF4")) || (EspacoStrP.contains("AV3DNF5")) || (EspacoStrP.contains("AV3DNPF6")) || (EspacoStrP.contains("AV3DNPF7")) || (EspacoStrP.contains("AV3DNPF8")) || (EspacoStrP.contains("AV3DNPF9")))
				try
					{
					AV3DNavigatorEspacosPFileCountThread.interrupt ();
					AV3DNavigatorEspacosPFileCountThread.start();
					} catch (IllegalThreadStateException e) {}

			if (! (Debug.equals("Debug"))) if ((EspacoStrP.contains("AV3DNPTS")) || (EspacoStrP.contains("AV3DNPTM")) || (EspacoStrP.contains("AV3DNPTH")))
				try
					{
					AV3DNavigatorEspacosPTimeCountThread.interrupt ();
					AV3DNavigatorEspacosPTimeCountThread.start();
					} catch (IllegalThreadStateException e) {}

			return EspacoStr;
			} catch (IOException e) {return "Erro";}
		}

	public void VariavelLimiteAtingido()
		{
		x = 0;
		y = 0;
		z = 0;
		Teta = 0;
		Phi = 0;
		Rot = 0;
		TamanhoFonteLegendas = 12;
		RotacaoTeta = Teta + Math.PI;
		RotacaoPhi = Phi + Math.PI;
		RaioRot = 0;
		RaioTeta = 0;
		RaioPhi = 0;
		xt = x;
		yt = y;
		zt = z;
		Tetat = Teta;
		Phit = Phi;
		Rott = Rot;
		FlagMouseY = 1;
		FatorAnguloVisao = 1;
		DistanciaTela = 2;
		Parametro0 = 0;
		Parametro0Step = 1;
		Parametro1 = 0;
		Parametro1Step = 1;
		Parametro2 = 0;
		Parametro2Step = 1;
		Parametro3 = 0;
		Parametro3Step = 1;
		Parametro4 = 0;
		Parametro4Step = 1;
		Parametro5 = 0;
		Parametro5Step = 1;
		Parametro6 = 0;
		Parametro6Step = 1;
		Parametro7 = 0;
		Parametro7Step = 1;
		Parametro8 = 0;
		Parametro8Step = 1;
		Parametro9 = 0;
		Parametro9Step = 1;
		CameraMovePar = 0;
		CameraMoveParStep = 1;
		StretchFlag = 1;
		SleepTime = 7;
		FlagTime = 0;
		}
	}
