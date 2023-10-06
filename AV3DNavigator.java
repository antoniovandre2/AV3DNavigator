/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).
 * 
 * Software AV3DNavigator.
 * 
 * Dependências: AntonioVandre, Apfloat 1.11.0 (http://www.apfloat.org).
 * 
 * Motor Gráfico: AV3D-n (para objetos próximos).
 * 
 * Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
 * 
 * Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).
 * 
 * Última atualização: 05-10-2023. Não considerando alterações em variáveis globais.
 */

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

import java.lang.Math;

import javax.imageio.ImageIO;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import java.io.*;

// Alta precisão com o Apfloat, porém com custo computacional.

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import AntonioVandre.*;

public class AV3DNavigator extends JComponent
    {
    public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";
    public static String ArquivoAV3DNavigatorURL = "AV3DNavigatorURL.txt";

    // Variáveis globais.

    public int TamanhoPlanoX = 400; // Default: 400.
    public int TamanhoPlanoY = 400; // Default: 400.
    public static int TamanhoEspacoLabelStatus = 350; // Default: 350.
    public static int TamanhoEspacoLabelURL = 20; // Default: 20.
    public static int TamanhoEspacoHelpX = 700; // Default: 700.
    public static int TamanhoEspacoHelpY = 520; // Default: 520.
    public static int TamanhoEspacoInvalidoX = 300; // Default: 300.
    public static int TamanhoEspacoInvalidoY = 80; // Default: 80.
    public static int MinTamanhoPlanoX = 400; // Default: 400.
    public static int MinTamanhoPlanoYMaisLabels = 400 + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL; // Default: 400 + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL.
    public static String AV3DNavigatorIconFilePath = "AV3DNavigator - Logo - 200p.png";
    public double FatorAnguloVisao = 1; // Default: 1.
    public static double TetaMax = Double.MAX_VALUE; // Opção: Math.PI / 3.
    public static double PhiMax = Double.MAX_VALUE; // Opção: Math.PI / 3.
    public static double MargemAnguloVisao = 0.5; // Default: 0.5.
    public static int TamanhoFonteLabelStatus = 10; // Default: 10.
    public static int TamanhoFonteLabelURL = 11; // Default: 11.
    public static int TamanhoFonteLabelHelp = 11; // Default: 11.
    public static int TamanhoFonteLabelPrint = 12; // Default: 12.
    public static int TamanhoFonteLabelErroEspacoInvalido = 11; // Default: 11.
    public double DistanciaTela = 2; // Default: valor inicial: 2.
    public static String MensagemErroEspacoAusente = "Entre com um arquivo de espaço.";
    public static String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";
    public static double FatorMouseWheel = 3; // Default: 3.
    public static double DeslocamentoLinear = 1; // Default: 1.
    public static double DeslocamentoAngular = 0.1; // Default: 0.1.
    public static int FramesDeslocamento = 4; // Default: 4.
    public int ApfloatFlag = 0; // Default: 0.
    public int ShiftVerticalLegendas = 20; // Default: 20.
    public int ResolucaoZbuffer = 1000; // Default: 1000.
    public int TrianguloPoligono = 1; // Opção: 0.
    public int ResolucaoTriangulos = 10; // Default: 10. Considerável custo computacional para valores elevados.

    // Variáveis de funcionamento interno.

    public String Versao;
    public String URL;
    public int CorrecaoX = 8;
    public int CorrecaoY = 0;
    public int CorrecaoXF = 15;
    public int CorrecaoYF = 0;
    public double RaioTeta = 0;
    public double RaioPhi = 0;
    public double AnguloVisao;
    public int FlagCoordRotHor = 0;
    public int FlagCoordRotVert = 0;
    public int FlagTetaSuperior = 0;
    public int FlagTetaInferior = 0;
    public int FlagPhiSuperior = 0;
    public int FlagPhiInferior = 0;
    public int Sair = 0;
    public String Espaco;
    public int FlagAlteracaoStatus = 1;
    public int MaxTentativasCores = Integer.MAX_VALUE;

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double Teta = 0;
    public double Phi = 0;
    public double Rot = 0;
    public double RotacaoTeta = Math.PI + Teta;
    public double RotacaoPhi = Math.PI + Phi;
    public double xRotacaoTeta = x + RaioTeta * Math.cos(Teta);
    public double yRotacaoTeta = y + RaioTeta * Math.sin(Teta);
    public double xRotacaoPhi = x + RaioPhi * Math.cos(Teta) * Math.cos(Phi);
    public double yRotacaoPhi = y + RaioPhi * Math.cos(Teta) * Math.cos(Phi);
    public double zRotacaoPhi = z + RaioPhi * Math.sin(Phi);
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
    public int ContadorFrames = FramesDeslocamento;
    public Color CorBackground;
    public Color CorLinhas;
    public Color CorTriangulosShape;
    public Color CorLegendas;
    public int ContadorCorLinhas = 0; // Default inicial: 0.
    public int ContadorCorBackground = 1; // Default inicial: 1.
    public int ContadorCorTriangulosShape = 0; // Default inicial: 0.
    public int ContadorCorLegendas = 0; // Default inicial: 0.
    public String StringCores = "";
    public double SomaXP = 0;
    public double SomaYP = 0;
    public double SomaZP = 0;
    public int TotalLinhas = 0;
    public int TotalTriangulosShapePreenchidos = 0;
    public int TotalLegendas = 0;
    public int FlagDesenhar = 1;

    public class GradientLabel extends JLabel
        {
        private Color CorInicial;
        private Color CorFinal;

        public GradientLabel(String Texto)
            {
            super(Texto);

            CorInicial = Color.BLUE;
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

        public void paint(Graphics g)
            {
            int width = getWidth();
            int height = getHeight();

            GradientPaint paint = new GradientPaint(0, 0, CorInicial, width, height, CorFinal, true);
            Graphics2D g2d = (Graphics2D) g;
            Paint oldPaint = g2d.getPaint();
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, height);
            g2d.setPaint(oldPaint);
            super.paint(g);
            }
        }

    private static class LineType extends Object
        {
        final int x1; 
        final int y1;
        final int x2;
        final int y2;
        final Color color;

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
        final int x1; 
        final int y1;
        final int x2;
        final int y2;
        final int x3;
        final int y3;
        final Color color;
        final double zbuffer;

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
        final String texto;
        final int x;
        final int y;
        final Color color;

        public TextoType(String texto, int x, int y, Color color)
            {
            this.texto = texto;
            this.x = x;
            this.y = y;
            this.color = color;
            }               
        }

    public LinkedList<LineType> Linhas = new LinkedList<LineType>();
    public LinkedList<TrianguloType> TriangulosShape = new LinkedList<TrianguloType>();
    public LinkedList<TextoType> Textos = new LinkedList<TextoType>();

    public void addLine(int x1, int x2, int x3, int x4, Color color, int n)
        {
        Linhas.add(new LineType(x1, x2, x3, x4, color));
        if (n == TotalLinhas) repaint();repaint();
        }

    public void addTriangulosShape(int x1, int y1, int x2, int y2, int x3, int y3, Color color, double zbuffer, int n)
        {
        TriangulosShape.add(new TrianguloType(x1, y1, x2, y2, x3, y3, color, zbuffer));

        if (n == TotalTriangulosShapePreenchidos)
            repaint();
        }

    public void addTexto(String texto, int x, int y, Color color, int n)
        {
        Textos.add(new TextoType(texto, x, y, color));
        if (n == TotalLegendas) repaint();
        }

    public void clearLines()
        {
        Linhas.clear();
        repaint();
        }

    public void clearTextos()
        {
        Textos.clear();
        repaint();
        }

    public void clearTriangulosShape()
        {
        TriangulosShape.clear();
        repaint();
        }

    protected void paintComponent(Graphics g)
        {
        if (FlagDesenhar == 1)
            {
            Collections.sort(TriangulosShape, new Comparator<TrianguloType>()
                {
                @Override
                public int compare(TrianguloType o1, TrianguloType o2)
                    {
                    int i = 0;

                    while (true)
                        {
                        if ((Math.abs(o1.zbuffer * ResolucaoZbuffer / ++i) < Double.MAX_VALUE) && (Math.abs(o2.zbuffer * ResolucaoZbuffer / i) < Double.MAX_VALUE))
                            return ((int) (o2.zbuffer * ResolucaoZbuffer / i) - (int) (o1.zbuffer * ResolucaoZbuffer / i));
                        }
                    }
                });

            for (int i = 0; i < Linhas.size(); i++)
                {
                g.setColor(Linhas.get(i).color);
                g.drawLine(Linhas.get(i).x1, Linhas.get(i).y1, Linhas.get(i).x2, Linhas.get(i).y2);
                }

            for (int i = 0; i < TriangulosShape.size(); i++)
                {
                g.setColor(TriangulosShape.get(i).color);

                if (TrianguloPoligono == 0)
                    {
                    for (int j = 0; j < ResolucaoTriangulos; j++)
                        {
                        g.drawLine((int) TriangulosShape.get(i).x1, (int) TriangulosShape.get(i).y1, (int) (TriangulosShape.get(i).x2 + j * (TriangulosShape.get(i).x3 - TriangulosShape.get(i).x2) / ResolucaoTriangulos), (int) (TriangulosShape.get(i).y2 + j * (TriangulosShape.get(i).y3 - TriangulosShape.get(i).y2) / ResolucaoTriangulos));

                        g.drawLine((int) TriangulosShape.get(i).x2, (int) TriangulosShape.get(i).y2, (int) (TriangulosShape.get(i).x1 + j * (TriangulosShape.get(i).x3 - TriangulosShape.get(i).x1) / ResolucaoTriangulos), (int) (TriangulosShape.get(i).y1 + j * (TriangulosShape.get(i).y3 - TriangulosShape.get(i).y1) / ResolucaoTriangulos));

                        g.drawLine((int) TriangulosShape.get(i).x3, (int) TriangulosShape.get(i).y3, (int) (TriangulosShape.get(i).x2 + j * (TriangulosShape.get(i).x1 - TriangulosShape.get(i).x2) / ResolucaoTriangulos), (int) (TriangulosShape.get(i).y2 + j * (TriangulosShape.get(i).y1 - TriangulosShape.get(i).y2) / ResolucaoTriangulos));
                        }
                    }
                else
                    g.fillPolygon(new int[]{TriangulosShape.get(i).x1, TriangulosShape.get(i).x2, TriangulosShape.get(i).x3},new int[]{TriangulosShape.get(i).y1, TriangulosShape.get(i).y2, TriangulosShape.get(i).y3}, 3);
                }

            for (int i = 0; i < Textos.size(); i++)
                {
                g.setColor(Textos.get(i).color);
                g.drawString(Textos.get(i).texto, Textos.get(i).x, Textos.get(i).y);
                }
            }
        }

    public static void main (String[] args) {AV3DNavigator mainc = new AV3DNavigator(); if (args.length != 0) mainc.mainrun(args[0]); else mainc.mainrun("");}

    public void mainrun (String ArquivoEspaco)
        {
        Versao = "Versão desconhecida.";
        URL = "URL desconhecida.";

        File fileVersao = new File(ArquivoAV3DNavigatorVersao);

        try
            {
            BufferedReader brVersao = new BufferedReader(new FileReader(fileVersao));
            Versao = brVersao.readLine();
            } catch (IOException e) {}

        File fileURL = new File(ArquivoAV3DNavigatorURL);

        try
            {
            BufferedReader brURL = new BufferedReader(new FileReader(fileURL));
            URL = brURL.readLine();
            } catch (IOException e) {}

        if (! ArquivoEspaco.equals(""))
            {
            Espaco = LerEspaco (ArquivoEspaco);

            if (Espaco.equals("Erro"))
                {
                System.out.println(MensagemErroEspacoInvalido);
                return;
                }
            }
        else
            Espaco = "";

        JFrame FrameEspaco = new JFrame("AV3DNavigator - " + Versao);
        FrameEspaco.setIconImage(new ImageIcon(getClass().getResource(AV3DNavigatorIconFilePath)).getImage());
        FrameEspaco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
        AV3DNavigator Comp = new AV3DNavigator();
        Comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
        FrameEspaco.getContentPane().add(Comp, BorderLayout.PAGE_START);
        GradientLabel LabelStatus = new GradientLabel("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(-y) + ".<br>z = " + String.valueOf(-z) + ".<br><br>θ = " + String.valueOf(Teta) + ". Max θ = " + String.valueOf(TetaMax) + ".<br>φ = " + String.valueOf(Phi) + ". Max φ = " + String.valueOf(PhiMax) + ".<br><br>Rot = " + String.valueOf(Rot) + ".<br><br>Raio θ = " + String.valueOf(RaioTeta) + ".<br>Rotacao θ = " + String.valueOf(RotacaoTeta) + ".<br>Raio φ = " + String.valueOf(RaioPhi) + ".<br>Rotacao φ = " + String.valueOf(RotacaoPhi) + ".<br><br>Distância da tela = " + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + "<br>Aspect ratio = 1.0.<br><br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>Aperte F1 para ajuda.</html>", Color.BLUE, Color.BLACK, Color.WHITE);
        LabelStatus.setBorder(new EmptyBorder(5, 5, 5, 5));
        LabelStatus.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelStatus));
        GradientLabel LabelURL = new GradientLabel("<html>" + URL + "</html>", Color.WHITE, Color.BLACK, Color.BLUE);
        LabelURL.setBorder(new EmptyBorder(5, 5, 5, 5));
        LabelURL.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, TamanhoFonteLabelURL));
        LabelStatus.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoEspacoLabelStatus));
        JPanel LabelStatusLabelURLPanel = new JPanel(new GridBagLayout());
        GridBagConstraints GridBagConstraintsLabelStatusLabelURL = new GridBagConstraints();
        GridBagConstraintsLabelStatusLabelURL.gridx = 0;
        GridBagConstraintsLabelStatusLabelURL.gridy = 0;
        GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.BOTH;
        GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
        GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelStatus - 20;
        LabelStatusLabelURLPanel.add(LabelStatus, GridBagConstraintsLabelStatusLabelURL);
        GridBagConstraintsLabelStatusLabelURL.gridx = 0;
        GridBagConstraintsLabelStatusLabelURL.gridy = 1;
        GridBagConstraintsLabelStatusLabelURL.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraintsLabelStatusLabelURL.weightx = TamanhoPlanoX;
        GridBagConstraintsLabelStatusLabelURL.weighty = TamanhoEspacoLabelURL;
        LabelStatusLabelURLPanel.add(LabelURL, GridBagConstraintsLabelStatusLabelURL);
        FrameEspaco.add(LabelStatusLabelURLPanel);

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
            public void mouseReleased(MouseEvent MouseEvento) {MouseDown = 0; FlagMouseDownArea = 0; FlagCoordRotHor = 0; FlagCoordRotVert = 0;}
            public void mouseDragged(MouseEvent MouseEvento) {}
            public void mouseMoved(MouseEvent MouseEvento) {}
            });

        FrameEspaco.addMouseWheelListener(e -> {
            if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                VariavelLimiteAtingido();
            else
                {
                x -= FatorMouseWheel * e.getWheelRotation() * Math.cos(Phit) * Math.cos(Teta);
                y += FatorMouseWheel * e.getWheelRotation() * Math.cos(Phit) * Math.sin(Teta);
                z += FatorMouseWheel * e.getWheelRotation() * Math.sin(Phit);

                xt = x; yt = y; zt = z;

                FlagAlteracaoStatus = 1;
                FlagCoordRotHor = 0; FlagCoordRotVert = 0;
                }
            });

        FrameEspaco.addKeyListener(new KeyListener()
            {
            public void keyPressed(KeyEvent ke)
                {
                if (ContadorFrames == FramesDeslocamento)
                    {
                    int keyCode = ke.getKeyCode();

                    ContadorFrames = FramesDeslocamento - 1;

                    if (keyCode == KeyEvent.VK_ESCAPE)
                        Sair = 1;

                    if (keyCode == KeyEvent.VK_SPACE)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 0;

                        x = 0;
                        y = 0;
                        z = 0;
                        Teta = 0;
                        Phi = 0;
                        Rot = 0;
                        RotacaoTeta = Teta + Math.PI;
                        RotacaoPhi = Phi + Math.PI;
                        RaioTeta = 0;
                        RaioPhi = 0;
                        xt = x;
                        yt = y;
                        zt = z;
                        Tetat = Teta;
                        Phit = Phi;
                        Rott = Rot;

                        FatorAnguloVisao = 1;

                        DistanciaTela = 2;

                        ContadorFrames = FramesDeslocamento;
                        }

                    if (keyCode == KeyEvent.VK_F1)
                        {
                        JFrame FrameHelp = new JFrame("AV3DNavigator - Ajuda");
                        FrameHelp.setPreferredSize(new Dimension(TamanhoEspacoHelpX, TamanhoEspacoHelpY));
                        GradientLabel LabelHelp = new GradientLabel("<html>F2 para selecionar e abrir arquivo de espaço.<br><br>\"A\" para incrementar x. \"Z\" para decrementar.<br>\"S\" para incrementar y. \"X\" para decrementar.<br>\"D\" para incrementar z. \"C\" para decrementar.<br>\"F\" para incrementar Teta. \"V\" para decrementar.<br>\"G\" para incrementar Phi. \"B\" para decrementar.<br>\"H\" para incrementar a rotação da tela. \"N\" para decrementar.<br>\"J\" para rotação horizontal positiva. \"M\" para negativa.<br>\"K\" para rotação vertical positiva. \",\" para negativa.<br>\"L\" para incrementar o raio de rotação horizontal. \".\" para decrementar.<br>\"[\" para incrementar o raio de rotação vertical. \"]\" para decrementar.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar.<br>\"T\" para shift negativo na cor padrão da linha. \"Y\" para shift positivo.<br>\"U\" para shift negativo na cor de fundo. \"I\" para shift positivo.<br>\"O\" para shift negativo na cor padrão dos polígonos preenchidos. \"P\" para shift positivo.<br>INSERT para shift negativo na cor padrão das legendas. HOME para shift positivo.<br><br>\"0\" para toggle alta precisão Apfloat (com custo computacional).<br><br>Setas para strafe. Mouse pode ser utilizado para movimentar.<br><br>Barra de espaços para resetar as variáveis.<br><br>F11 para setar aspect ratio 1.<br>F12 para screenshot.<br><br>ESC para sair.</html>", Color.BLUE, Color.BLACK, Color.WHITE);
                        LabelHelp.setBorder(new EmptyBorder(5, 5, 5, 5));
                        LabelHelp.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelHelp));
                        FrameHelp.add(LabelHelp);
                        FrameHelp.pack();
                        FrameHelp.setVisible(true);

                        FrameHelp.addKeyListener(new KeyListener()
                            {
                            public void keyPressed(KeyEvent keHelp)
                                {
                                int keyCodeHelp = keHelp.getKeyCode();
                                if (keyCodeHelp == KeyEvent.VK_ESCAPE) FrameHelp.dispose();
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
                        FrameEspaco.printAll(g2d);
                        g2d.setFont(new Font("Monospace", Font.ITALIC, TamanhoFonteLabelPrint));

                        Random geradorPrint = new Random();

                        String [] CoresMatrix = StringCores.split(";");
                        int FlagCores = 0;
                        long TentativasCores = 0;

                        labelDo: do
                            {
                            int iPrintR = geradorPrint.nextInt(256);
                            int iPrintG = geradorPrint.nextInt(256);
                            int iPrintB = geradorPrint.nextInt(256);

                            labelFor: for (int i = 0; i < CoresMatrix.length; i++)
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

                        g2d.drawString(URL, 5 + FrameEspaco.getInsets().left, TamanhoPlanoY + FrameEspaco.getInsets().top - 5);
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
                            Espaco = LerEspaco (selectedFile.getAbsolutePath());

                            if (Espaco.equals("Erro"))
                                {
                                JFrame FrameErroEspacoInvalido = new JFrame("AV3DNavigator - Espaço inválido");
                                FrameErroEspacoInvalido.setPreferredSize(new Dimension(TamanhoEspacoInvalidoX, TamanhoEspacoInvalidoY));
                                GradientLabel LabelErroEspacoInvalido = new GradientLabel(MensagemErroEspacoInvalido, Color.BLUE, Color.BLACK, Color.WHITE);
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

                    if (keyCode == KeyEvent.VK_F11)
                        {
                        int Tamanho = Math.min(TamanhoPlanoX, TamanhoPlanoY);

                        FrameEspaco.setPreferredSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));

                        FrameEspaco.setSize(new Dimension(Tamanho, Tamanho + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));

                        FrameEspaco.pack();
                        }

                    if (keyCode == KeyEvent.VK_A)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(x) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {x += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_Z)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(x) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {x -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_S)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(y) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {y -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_X)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(y) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {y += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_D)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(z) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {z -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_C)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(z) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {z += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_F)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Teta) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {Teta += DeslocamentoAngular; ContadorFrames = 0;} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaSuperior = 1;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_V)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Teta) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Teta) < TetaMax - DeslocamentoAngular) {Teta -= DeslocamentoAngular; ContadorFrames = 0;} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_B)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Phi) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {Phi -= DeslocamentoAngular; ContadorFrames = 0;} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiInferior = 1;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_G)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Phi) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Phi) < PhiMax - DeslocamentoAngular) {Phi += DeslocamentoAngular; ContadorFrames = 0;} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_H)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Rot) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Rot += DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_N)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(Rot) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Rot -= DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_J)
                        {
                        FlagCoordRotHor = 1; FlagCoordRotVert = 0;
                        RotacaoTeta += DeslocamentoAngular;

                        if (Math.abs(Teta + Math.PI) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Teta + Math.PI) < TetaMax - DeslocamentoAngular) {Teta += DeslocamentoAngular; x = xRotacaoTeta + RaioTeta * Math.cos(RotacaoTeta); y = yRotacaoTeta - RaioTeta * Math.sin(RotacaoTeta); ContadorFrames = 0;} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaSuperior = 1;}} else VariavelLimiteAtingido();
                        }

                    if (keyCode == KeyEvent.VK_M)
                        {
                        FlagCoordRotHor = 1; FlagCoordRotVert = 0;
                        RotacaoTeta -= DeslocamentoAngular;

                        if (Math.abs(Teta + Math.PI) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Teta + Math.PI) < TetaMax - DeslocamentoAngular) {Teta -= DeslocamentoAngular; x = xRotacaoTeta + RaioTeta * Math.cos(RotacaoTeta); y = yRotacaoTeta - RaioTeta * Math.sin(RotacaoTeta); ContadorFrames = 0;} else {Teta -= Math.signum(Teta) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta; FlagTetaInferior = 1;}} else VariavelLimiteAtingido();
                        }

                    if (keyCode == KeyEvent.VK_K)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 1;
                        RotacaoPhi -= DeslocamentoAngular;

                        if (Math.abs(Phi + Math.PI) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Phi + Math.PI) < PhiMax - DeslocamentoAngular) {Phi -= DeslocamentoAngular; x = xRotacaoPhi + RaioPhi * Math.cos(RotacaoPhi) * Math.cos(Teta); y = yRotacaoPhi - RaioPhi * Math.cos(RotacaoPhi) * Math.sin(Teta); z = zRotacaoPhi - RaioPhi * Math.sin(RotacaoPhi); ContadorFrames = 0;} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiInferior = 1;}} else VariavelLimiteAtingido();
                        }

                    if (keyCode == KeyEvent.VK_COMMA)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 1;
                        RotacaoPhi += DeslocamentoAngular;

                        if (Math.abs(Phi + Math.PI) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {if (Math.abs(Phi + Math.PI) < PhiMax - DeslocamentoAngular) {Phi += DeslocamentoAngular; x = xRotacaoPhi + RaioPhi * Math.cos(RotacaoPhi) * Math.cos(Teta); y = yRotacaoPhi - RaioPhi * Math.cos(RotacaoPhi) * Math.sin(Teta); z = zRotacaoPhi - RaioPhi * Math.sin(RotacaoPhi); ContadorFrames = 0;} else {Phi -= Math.signum(Phi) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi; FlagPhiSuperior = 1;}} else VariavelLimiteAtingido();
                        }

                    if (keyCode == KeyEvent.VK_L)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (RaioTeta - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {RaioTeta += DeslocamentoLinear; ContadorFrames = FramesDeslocamento;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_PERIOD)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (RaioTeta > DeslocamentoLinear) {RaioTeta -= DeslocamentoLinear; ContadorFrames = FramesDeslocamento;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_OPEN_BRACKET)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (RaioPhi - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {RaioPhi += DeslocamentoLinear; ContadorFrames = FramesDeslocamento;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_CLOSE_BRACKET)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (RaioPhi > DeslocamentoLinear) {RaioPhi -= DeslocamentoLinear; ContadorFrames = FramesDeslocamento;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_Q)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (DistanciaTela > 1) DistanciaTela -= 1;}

                    if (keyCode == KeyEvent.VK_W)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(DistanciaTela) - 1 <= Double.MAX_VALUE - 1) DistanciaTela += 1; else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_E)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (FatorAnguloVisao > 1) FatorAnguloVisao -= 1;}

                    if (keyCode == KeyEvent.VK_R)
                        {FlagCoordRotHor = 0; FlagCoordRotVert = 0; if (Math.abs(FatorAnguloVisao) - 1 <= Double.MAX_VALUE - 1) FatorAnguloVisao += 1; else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_T)
                        if (ContadorCorLinhas > 0) ContadorCorLinhas -= 1;

                    if (keyCode == KeyEvent.VK_Y)
                        if (ContadorCorLinhas < 15) ContadorCorLinhas += 1;

                    if (keyCode == KeyEvent.VK_U)
                        if (ContadorCorBackground > 0) ContadorCorBackground -= 1;

                    if (keyCode == KeyEvent.VK_I)
                        if (ContadorCorBackground < 15) ContadorCorBackground += 1;

                    if (keyCode == KeyEvent.VK_O)
                        if (ContadorCorTriangulosShape > 0) ContadorCorTriangulosShape -= 1;

                    if (keyCode == KeyEvent.VK_P)
                        if (ContadorCorTriangulosShape < 15) ContadorCorTriangulosShape += 1;

                    if (keyCode == KeyEvent.VK_INSERT)
                        if (ContadorCorLegendas > 0) ContadorCorLegendas -= 1;

                    if (keyCode == KeyEvent.VK_HOME)
                        if (ContadorCorLegendas < 15) ContadorCorLegendas += 1;

                    if (keyCode == KeyEvent.VK_0)
                        if (ApfloatFlag == 0) ApfloatFlag = 1; else ApfloatFlag = 0;

                    if (keyCode == KeyEvent.VK_UP)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 0;

                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x += Math.cos(Phit) * Math.cos(Teta);
                            y -= Math.cos(Phit) * Math.sin(Teta);
                            z -= Math.sin(Phit);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_DOWN)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 0;

                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x -= Math.cos(Phit) * Math.cos(Teta);
                            y += Math.cos(Phit) * Math.sin(Teta);
                            z += Math.sin(Phit);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_LEFT)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 0;

                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x += Math.cos(Teta * Math.cos(Teta) - Math.PI / 2);
                            y -= Math.sin(Teta * Math.cos(Teta) - Math.PI / 2);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_RIGHT)
                        {
                        FlagCoordRotHor = 0; FlagCoordRotVert = 0;

                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x -= Math.cos(Teta * Math.cos(Teta) - Math.PI / 2);
                            y += Math.sin(Teta * Math.cos(Teta) - Math.PI / 2);
                            }

                        ContadorFrames = 0;
                        }

                    FlagAlteracaoStatus = 1;
                    }
                }

            public void keyReleased(KeyEvent ke){}
            public void keyTyped(KeyEvent ke){}
            });

        FrameEspaco.pack();
        FrameEspaco.setVisible(true);

        while(Sair == 0)
            {
            int FlagRedimensionarOver = 0;

            int widthFrameEspaco = FrameEspaco.getWidth();
            int heightFrameEspaco = FrameEspaco.getHeight();

            if (widthFrameEspaco < MinTamanhoPlanoX)
                {
                widthFrameEspaco = MinTamanhoPlanoX;
                FrameEspaco.setPreferredSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
                FrameEspaco.setSize(widthFrameEspaco, heightFrameEspaco);
                FlagRedimensionarOver = 1;
                }

            if (heightFrameEspaco < MinTamanhoPlanoYMaisLabels)
                {
                heightFrameEspaco = MinTamanhoPlanoYMaisLabels;
                FrameEspaco.setPreferredSize(new Dimension(widthFrameEspaco, heightFrameEspaco));
                FrameEspaco.setSize(widthFrameEspaco, heightFrameEspaco);
                FlagRedimensionarOver = 1;
                }

            if (FlagRedimensionarOver == 0)
                if ((widthFrameEspaco != TamanhoPlanoX) || (heightFrameEspaco != TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL))
                    {
                    TamanhoPlanoX = widthFrameEspaco;
                    TamanhoPlanoY = heightFrameEspaco - TamanhoEspacoLabelStatus - TamanhoEspacoLabelURL;

                    FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus + TamanhoEspacoLabelURL));
                    Comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
                    FrameEspaco.pack();

/* Reinicialização opcional das variáveis de localização.

                    x = 0;
                    y = 0;
                    z = 0;
                    Teta = 0;
                    Phi = 0;
                    Rot = 0;
                    RotacaoTeta = Teta + Math.PI;
                    RotacaoPhi = Phi + Math.PI;
                    RaioTeta = 0;
                    RaioPhi = 0;
                    xt = x;
                    yt = y;
                    zt = z;
                    Tetat = Teta;
                    Phit = Phi;
                    Rott = Rot;

                    FatorAnguloVisao = 1;

                    DistanciaTela = 2;

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

            if (FlagCoordRotHor == 0)
                {
                xRotacaoTeta = x + RaioTeta * Math.cos(Teta);
                yRotacaoTeta = y - RaioTeta * Math.sin(Teta);
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
                if ((Math.abs(Teta) - DeslocamentoAngular > Double.MAX_VALUE - DeslocamentoAngular) || (Math.abs(Phi) - DeslocamentoAngular > Double.MAX_VALUE - DeslocamentoAngular))
                    VariavelLimiteAtingido();
                else
                    {
                    if (Math.abs(Teta) < TetaMax - DeslocamentoAngular)
                        {
                        Teta = 2 * Math.PI * (MouseX - MouseXR) / TamanhoPlanoX + TetaR;
                        Teta -= Math.signum(Math.cos(Teta)) * Math.signum(Math.cos(TetaR)) * DeslocamentoAngular;
                        }
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
                        Phi -= Math.signum(PhiR) * DeslocamentoAngular;
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

                switch (ContadorCorLinhas)
                    {
                    case 15:
                        CorLinhas = new Color(192, 192, 192);
                        StringCores = StringCores + "192,192,192;";
                        break;
                    case 14:
                        CorLinhas = new Color(175, 255, 175);
                        StringCores = StringCores + "175,255,175;";
                        break;
                    case 13:
                        CorLinhas = new Color(128, 128, 255);
                        StringCores = StringCores + "128,128,255;";
                        break;
                    case 12:
                        CorLinhas = Color.YELLOW;
                        StringCores = StringCores + "255,255,0;";
                        break;
                    case 11:
                        CorLinhas = Color.ORANGE;
                        StringCores = StringCores + "255,200,0;";
                        break;
                    case 10:
                        CorLinhas = Color.PINK;
                        StringCores = StringCores + "255,175,175;";
                        break;
                    case 9:
                        CorLinhas = Color.CYAN;
                        StringCores = StringCores + "0,255,255;";
                        break;
                    case 8:
                        CorLinhas = Color.BLUE;
                        StringCores = StringCores + "0,0,255;";
                        break;
                    case 7:
                        CorLinhas = Color.MAGENTA;
                        StringCores = StringCores + "255,0,255;";
                        break;
                    case 6:
                        CorLinhas = new Color(128, 175, 175);
                        StringCores = StringCores + "128,175,175;";
                        break;
                    case 5:
                        CorLinhas = Color.GRAY;
                        StringCores = StringCores + "128,128,128;";
                        break;
                    case 4:
                        CorLinhas = Color.RED;
                        StringCores = StringCores + "255,0,0;";
                        break;
                    case 3:
                        CorLinhas = new Color(64, 64, 64);
                        StringCores = StringCores + "64,64,64;";
                        break;
                    case 2:
                        CorLinhas = new Color(64, 64, 128);
                        StringCores = StringCores + "64,64,128;";
                        break;
                    case 1:
                        CorLinhas = Color.BLACK;
                        StringCores = StringCores + "0,0,0;";
                        break;
                    case 0:
                        CorLinhas = Color.WHITE;
                        StringCores = StringCores + "255,255,255;";
                        break;
                    }

                switch (ContadorCorBackground)
                    {
                    case 15:
                        CorBackground = new Color(192, 192, 192);
                        StringCores = StringCores + "192,192,192;";
                        break;
                    case 14:
                        CorBackground = new Color(175, 255, 175);
                        StringCores = StringCores + "175,255,175;";
                        break;
                    case 13:
                        CorBackground = new Color(128, 128, 255);
                        StringCores = StringCores + "128,128,255;";
                        break;
                    case 12:
                        CorBackground = Color.YELLOW;
                        StringCores = StringCores + "255,255,0;";
                        break;
                    case 11:
                        CorBackground = Color.ORANGE;
                        StringCores = StringCores + "255,200,0;";
                        break;
                    case 10:
                        CorBackground = Color.PINK;
                        StringCores = StringCores + "255,175,175;";
                        break;
                    case 9:
                        CorBackground = Color.CYAN;
                        StringCores = StringCores + "0,255,255;";
                        break;
                    case 8:
                        CorBackground = Color.BLUE;
                        StringCores = StringCores + "0,0,255;";
                        break;
                    case 7:
                        CorBackground = Color.MAGENTA;
                        StringCores = StringCores + "255,0,255;";
                        break;
                    case 6:
                        CorBackground = new Color(128, 175, 175);
                        StringCores = StringCores + "128,175,175;";
                        break;
                    case 5:
                        CorBackground = Color.GRAY;
                        StringCores = StringCores + "128,128,128;";
                        break;
                    case 4:
                        CorBackground = Color.RED;
                        StringCores = StringCores + "255,0,0;";
                        break;
                    case 3:
                        CorBackground = new Color(64, 64, 64);
                        StringCores = StringCores + "64,64,64;";
                        break;
                    case 2:
                        CorBackground = new Color(64, 64, 128);
                        StringCores = StringCores + "64,64,128;";
                        break;
                    case 1:
                        CorBackground = Color.BLACK;
                        StringCores = StringCores + "0,0,0;";
                        break;
                    case 0:
                        CorBackground = Color.WHITE;
                        StringCores = StringCores + "255,255,255;";
                        break;
                    }

                switch (ContadorCorTriangulosShape)
                    {
                    case 15:
                        CorTriangulosShape = new Color(192, 192, 192);
                        StringCores = StringCores + "192,192,192;";
                        break;
                    case 14:
                        CorTriangulosShape = new Color(175, 255, 175);
                        StringCores = StringCores + "175,255,175;";
                        break;
                    case 13:
                        CorTriangulosShape = new Color(128, 128, 255);
                        StringCores = StringCores + "128,128,255;";
                        break;
                    case 12:
                        CorTriangulosShape = Color.YELLOW;
                        StringCores = StringCores + "255,255,0;";
                        break;
                    case 11:
                        CorTriangulosShape = Color.ORANGE;
                        StringCores = StringCores + "255,200,0;";
                        break;
                    case 10:
                        CorTriangulosShape = Color.PINK;
                        StringCores = StringCores + "255,175,175;";
                        break;
                    case 9:
                        CorTriangulosShape = Color.CYAN;
                        StringCores = StringCores + "0,255,255;";
                        break;
                    case 8:
                        CorTriangulosShape = Color.BLUE;
                        StringCores = StringCores + "0,0,255;";
                        break;
                    case 7:
                        CorTriangulosShape = Color.MAGENTA;
                        StringCores = StringCores + "255,0,255;";
                        break;
                    case 6:
                        CorTriangulosShape = new Color(128, 175, 175);
                        StringCores = StringCores + "128,175,175;";
                        break;
                    case 5:
                        CorTriangulosShape = Color.GRAY;
                        StringCores = StringCores + "128,128,128;";
                        break;
                    case 4:
                        CorTriangulosShape = Color.RED;
                        StringCores = StringCores + "255,0,0;";
                        break;
                    case 3:
                        CorTriangulosShape = new Color(64, 64, 64);
                        StringCores = StringCores + "64,64,64;";
                        break;
                    case 2:
                        CorTriangulosShape = new Color(64, 64, 128);
                        StringCores = StringCores + "64,64,128;";
                        break;
                    case 1:
                        CorTriangulosShape = Color.BLACK;
                        StringCores = StringCores + "0,0,0;";
                        break;
                    case 0:
                        CorTriangulosShape = Color.WHITE;
                        StringCores = StringCores + "255,255,255;";
                        break;
                    }

                switch (ContadorCorLegendas)
                    {
                    case 15:
                        CorLegendas = new Color(192, 192, 192);
                        StringCores = StringCores + "192,192,192;";
                        break;
                    case 14:
                        CorLegendas = new Color(175, 255, 175);
                        StringCores = StringCores + "175,255,175;";
                        break;
                    case 13:
                        CorLegendas = new Color(128, 128, 255);
                        StringCores = StringCores + "128,128,255;";
                        break;
                    case 12:
                        CorLegendas = Color.YELLOW;
                        StringCores = StringCores + "255,255,0;";
                        break;
                    case 11:
                        CorLegendas = Color.ORANGE;
                        StringCores = StringCores + "255,200,0;";
                        break;
                    case 10:
                        CorLegendas = Color.PINK;
                        StringCores = StringCores + "255,175,175;";
                        break;
                    case 9:
                        CorLegendas = Color.CYAN;
                        StringCores = StringCores + "0,255,255;";
                        break;
                    case 8:
                        CorLegendas = Color.BLUE;
                        StringCores = StringCores + "0,0,255;";
                        break;
                    case 7:
                        CorLegendas = Color.MAGENTA;
                        StringCores = StringCores + "255,0,255;";
                        break;
                    case 6:
                        CorLegendas = new Color(128, 175, 175);
                        StringCores = StringCores + "128,175,175;";
                        break;
                    case 5:
                        CorLegendas = Color.GRAY;
                        StringCores = StringCores + "128,128,128;";
                        break;
                    case 4:
                        CorLegendas = Color.RED;
                        StringCores = StringCores + "255,0,0;";
                        break;
                    case 3:
                        CorLegendas = new Color(64, 64, 64);
                        StringCores = StringCores + "64,64,64;";
                        break;
                    case 2:
                        CorLegendas = new Color(64, 64, 128);
                        StringCores = StringCores + "64,64,128;";
                        break;
                    case 1:
                        CorLegendas = Color.BLACK;
                        StringCores = StringCores + "0,0,0;";
                        break;
                    case 0:
                        CorLegendas = Color.WHITE;
                        StringCores = StringCores + "255,255,255;";
                        break;
                    }

                if (ApfloatFlag == 0)
                    {
                    AnguloVisao = Math.atan(TamanhoPlanoX / 2 / DistanciaTela);

                    AnguloVisao /= FatorAnguloVisao;
                    }
                else
                    {
                    AnguloVisao = ApfloatMath.atan(new Apfloat(TamanhoPlanoX).divide(new Apfloat(2)).divide(new Apfloat(DistanciaTela))).doubleValue();

                    AnguloVisao = (new Apfloat(AnguloVisao)).divide(new Apfloat(FatorAnguloVisao)).doubleValue();
                    }

                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(-y) + ".<br>z = " + String.valueOf(-z) + ".<br><br>θ = " + String.valueOf(Teta) + ". Max θ = " + String.valueOf(TetaMax) + ".<br>φ = " + String.valueOf(Phi) + ". Max φ = " + String.valueOf(PhiMax) + ".<br><br>Rot = " + String.valueOf(Rot) + ".<br><br>Raio θ = " + String.valueOf(RaioTeta) + ".<br>Rotacao θ = " + String.valueOf(RotacaoTeta) + ".<br>Raio φ = " + String.valueOf(RaioPhi) + ".<br>Rotacao φ = " + String.valueOf(RotacaoPhi) + ".<br><br>Distância da tela = " + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + "<br>Aspect ratio = " + String.valueOf((double) (TamanhoPlanoX) / ((double) (TamanhoPlanoY))) + ".<br><br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>Aperte F1 para ajuda.</html>");

                FrameEspaco.getContentPane().setBackground(CorBackground);

                try {Thread.sleep(5);} catch(InterruptedException e) {}

                DesenharEspaco(Comp);

                FlagAlteracaoStatus = 0;
                }

            try {Thread.sleep(5);} catch(InterruptedException e) {}
            }

        FrameEspaco.dispose();
        System.exit(0);
        }

    public void DesenharEspaco(AV3DNavigator Comp)
        {
        FlagDesenhar = 1;

        String [] EspacoStr2 = Espaco.split("@");

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

            for (int i = 3; i < EspacoStr2.length; i++)
                UniaoStringLegendas = UniaoStringLegendas + "@" + EspacoStr2[i];

            EspacoLegendas = UniaoStringLegendas.split("\\|");
            }

        Comp.clearLines();

        for (int i = 0; i < EspacoLinhas.length; i++)
            {
            if (! (EspacoLinhas[i].equals("")))
                {
                String [] Campos = EspacoLinhas[i].split("c");
                String [] Pontos = Campos[0].split(";");
                String [] CoordenadasOrig = Pontos[0].split(",");
                String [] CoordenadasDest = Pontos[1].split(",");

                if (ApfloatFlag == 0)
                    {
                    double xo = Double.parseDouble(CoordenadasOrig[0]) - xt;

                    double xd = Double.parseDouble(CoordenadasDest[0]) - xt;

                    double yo = -Double.parseDouble(CoordenadasOrig[1]) - yt;

                    double yd = -Double.parseDouble(CoordenadasDest[1]) - yt;

                    double zo = -Double.parseDouble(CoordenadasOrig[2]) - zt;

                    double zd = -Double.parseDouble(CoordenadasDest[2]) - zt;

                    int xi;
                    int yi;
                    int xf;
                    int yf;

                    try
                        {
                        xi = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * DistanciaTela * (Math.cos(Rott) * (xo * Math.sin(Tetat) + yo * Math.cos(Tetat)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)) - Math.sin(Rott) * (xo * Math.cos(Tetat) * Math.sin(Phit) - yo * Math.sin(Tetat) * Math.sin(Phit) + zo * Math.cos(Phit)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)))) - CorrecaoX;

                        yi = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * DistanciaTela * (Math.sin(Rott) * (xo * Math.sin(Tetat) + yo * Math.cos(Tetat)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)) + Math.cos(Rott) * (xo * Math.cos(Tetat) * Math.sin(Phit) - yo * Math.sin(Tetat) * Math.sin(Phit) + zo * Math.cos(Phit)) / (Math.sqrt(xo * xo + yo * yo + zo * zo)))) - CorrecaoY;

                        xf = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * DistanciaTela * (Math.cos(Rott) * (xd * Math.sin(Tetat) + yd * Math.cos(Tetat)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)) - Math.sin(Rott) * (xd * Math.cos(Tetat) * Math.sin(Phit) - yd * Math.sin(Tetat) * Math.sin(Phit) + zd * Math.cos(Phit)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)))) - CorrecaoX;

                        yf = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * DistanciaTela * (Math.sin(Rott) * (xd * Math.sin(Tetat) + yd * Math.cos(Tetat)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)) + Math.cos(Rott) * (xd * Math.cos(Tetat) * Math.sin(Phit) - yd * Math.sin(Tetat) * Math.sin(Phit) + zd * Math.cos(Phit)) / (Math.sqrt(xd * xd + yd * yd + zd * zd)))) - CorrecaoY;

                        double ProdutoEscalaro = xo * Math.cos(Tetat) * Math.cos(Phit) - yo * Math.sin(Tetat) * Math.cos(Phit);

                        double ProdutoEscalard = xd * Math.cos(Tetat) * Math.cos(Phit) - yd * Math.sin(Tetat) * Math.cos(Phit);

                        if ((Math.acos(ProdutoEscalaro / Math.sqrt(xo * xo + yo * yo + zo * zo)) < AnguloVisao + MargemAnguloVisao) && ((Math.acos(ProdutoEscalard / Math.sqrt(xd * xd + yd * yd + zd * zd)) < AnguloVisao + MargemAnguloVisao) && (Math.min(xi, Math.min(yi, Math.min(xf, yf))) > 0) && (Math.max(xi + CorrecaoXF, xf + CorrecaoXF) < TamanhoPlanoX) && (Math.max(yi + CorrecaoYF, yf + CorrecaoYF) < TamanhoPlanoY)))
                            {
                            TotalLinhas++;

                            if (Campos.length == 1)
                                Comp.addLine(xi, yi, xf, yf, CorLinhas, i + 1);
                            else
                                {
                                String [] RGB = Campos[1].split(",");
                                Comp.addLine(xi, yi, xf, yf, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), i + 1);
                                StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
                                }
                            }
                        }
                    catch (Exception e) {}
                    }
                else
                    {
                    // Alta precisão com o Apfloat, porém com custo computacional.

                    Apfloat xoa = (new Apfloat(Double.parseDouble(CoordenadasOrig[0]))).add(new Apfloat(-xt));

                    Apfloat xda = (new Apfloat(Double.parseDouble(CoordenadasDest[0]))).add(new Apfloat(-xt));

                    Apfloat yoa = (new Apfloat(-Double.parseDouble(CoordenadasOrig[1]))).add(new Apfloat(-yt));

                    Apfloat yda = (new Apfloat(-Double.parseDouble(CoordenadasDest[1]))).add(new Apfloat(-yt));

                    Apfloat zoa = (new Apfloat(-Double.parseDouble(CoordenadasOrig[2]))).add(new Apfloat(-zt));

                    Apfloat zda = (new Apfloat(-Double.parseDouble(CoordenadasDest[2]))).add(new Apfloat(-zt));

                    int xi;
                    int yi;
                    int xf;
                    int yf;

                    try
                        {
                        xi = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(xoa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(yoa.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zoa.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).multiply(new Apfloat(-1))))).doubleValue()) - CorrecaoX;

                        yi = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(xoa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(yoa.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zoa.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa)))))))).doubleValue()) - CorrecaoY;

                        xf = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(xda.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(yda.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(xda.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zda.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).multiply(new Apfloat(-1))))).doubleValue()) - CorrecaoX;

                        yf = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(xda.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(yda.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(xda.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zda.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda)))))))).doubleValue()) - CorrecaoY;

                        Apfloat ProdutoEscalaroa = xoa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).multiply(new Apfloat(-1)));

                        Apfloat ProdutoEscalarda = xda.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).add(yda.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).multiply(new Apfloat(-1)));

                        if ((ApfloatMath.acos(ProdutoEscalaroa.divide(ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).doubleValue() < AnguloVisao + MargemAnguloVisao) && (ApfloatMath.acos(ProdutoEscalarda.divide(ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).doubleValue() < AnguloVisao + MargemAnguloVisao) && (ApfloatMath.min(new Apfloat(xi), ApfloatMath.min(new Apfloat(yi), ApfloatMath.min(new Apfloat(xf), new Apfloat(yf)))).doubleValue() > 0) && (ApfloatMath.max(new Apfloat(xi + CorrecaoXF), (new Apfloat(xf + CorrecaoXF))).doubleValue() < (new Apfloat(TamanhoPlanoX)).doubleValue()) && (ApfloatMath.max(new Apfloat(yi + CorrecaoYF), (new Apfloat(yf + CorrecaoYF))).doubleValue() < (new Apfloat(TamanhoPlanoY)).doubleValue()))
                            {
                            TotalLinhas++;

                            if (Campos.length == 1)
                                Comp.addLine(xi, yi, xf, yf, CorLinhas, i + 1);
                            else
                                {
                                String [] RGB = Campos[1].split(",");
                                Comp.addLine(xi, yi, xf, yf, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), i + 1);
                                StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
                                }
                            }
                        }
                    catch (Exception e) {}
                    }
                }
            }

        Comp.clearTriangulosShape();

        for (int i = 0; i < EspacoTriangulosShapePreenchidos.length; i++)
            {
            if (! (EspacoTriangulosShapePreenchidos[i].equals("")))
                {
                String TriangulosString = "";
                String [] Campos = EspacoTriangulosShapePreenchidos[i].split("c");
                String [] Pontos = Campos[0].split(";");

                int ContadorPontos = 0;
                int ContadorTriangulosDesenhar = 0;

                for (int j = 0; j < Pontos.length; j++)
                    {
                    String [] Coordenadas = Pontos[j].split(",");

                    ContadorTriangulosDesenhar += Coordenadas.length;

                    if (ApfloatFlag == 0)
                        {
                        double xp = Double.parseDouble(Coordenadas[0]) - xt;

                        double yp = -Double.parseDouble(Coordenadas[1]) - yt;

                        double zp = -Double.parseDouble(Coordenadas[2]) - zt;

                        int xpp;
                        int ypp;

                        try
                            {
                            xpp = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * DistanciaTela * (Math.cos(Rott) * (xp * Math.sin(Tetat) + yp * Math.cos(Tetat)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)) - Math.sin(Rott) * (xp * Math.cos(Tetat) * Math.sin(Phit) - yp * Math.sin(Tetat) * Math.sin(Phit) + zp * Math.cos(Phit)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)))) - CorrecaoX;

                            ypp = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * DistanciaTela * (Math.sin(Rott) * (xp * Math.sin(Tetat) + yp * Math.cos(Tetat)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)) + Math.cos(Rott) * (xp * Math.cos(Tetat) * Math.sin(Phit) - yp * Math.sin(Tetat) * Math.sin(Phit) + zp * Math.cos(Phit)) / (Math.sqrt(xp * xp + yp * yp + zp * zp)))) - CorrecaoY;

                            double ProdutoEscalar = xp * Math.cos(Tetat) * Math.cos(Phit) - yp * Math.sin(Tetat) * Math.cos(Phit);

                            if ((Math.acos(ProdutoEscalar / Math.sqrt(xp * xp + yp * yp + zp * zp)) < AnguloVisao + MargemAnguloVisao) && ((Math.min(xpp, ypp) > 0) && (xpp + CorrecaoXF < TamanhoPlanoX) && (ypp + CorrecaoYF < TamanhoPlanoY)))
                                {
                                ContadorPontos++;
                                TriangulosString = TriangulosString + Integer.toString(xpp) + "," + Integer.toString(ypp) + ";";
                                SomaXP += xp; SomaYP += yp; SomaZP += zp;
                                }
                            }
                        catch (Exception e) {}
                        }
                    else
                        {
                        Apfloat xpa = (new Apfloat(Double.parseDouble(Coordenadas[0]))).add(new Apfloat(-xt));

                        Apfloat ypa = (new Apfloat(-Double.parseDouble(Coordenadas[1]))).add(new Apfloat(-yt));

                        Apfloat zpa = (new Apfloat(-Double.parseDouble(Coordenadas[2]))).add(new Apfloat(-zt));

                        int xpp;
                        int ypp;

                        try
                            {
                            xpp = (int) (TamanhoPlanoX / 2 + TamanhoPlanoX / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(xpa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(ypa.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zpa.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).multiply(new Apfloat(-1))))).doubleValue()) - CorrecaoX;

                            ypp = (int) (TamanhoPlanoY / 2 + TamanhoPlanoY / 2 * ((new Apfloat(DistanciaTela)).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(xpa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).add(ypa.multiply(ApfloatMath.cos(new Apfloat(Tetat))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.sin(new Apfloat(Phit))).multiply(new Apfloat(-1))).add(zpa.multiply(ApfloatMath.cos(new Apfloat(Phit))))).divide((ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa)))))))).doubleValue()) - CorrecaoY;

                            Apfloat ProdutoEscalara = xpa.multiply(ApfloatMath.cos(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(ApfloatMath.cos(new Apfloat(Phit))).multiply(new Apfloat(-1)));

                            if ((ApfloatMath.acos(ProdutoEscalara.divide(ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(ApfloatMath.sin(new Apfloat(Tetat))).multiply(zpa))))).doubleValue() < AnguloVisao + MargemAnguloVisao) && ((new Apfloat(xpp + CorrecaoXF)).doubleValue() < (new Apfloat(TamanhoPlanoX)).doubleValue()) && (new Apfloat(ypp + CorrecaoYF)).doubleValue() < (new Apfloat(TamanhoPlanoY)).doubleValue())
                                {
                                ContadorPontos++;
                                TriangulosString = TriangulosString + Integer.toString(xpp) + "," + Integer.toString(ypp) + ";";
                                SomaXP += xpa.doubleValue();
                                SomaYP += ypa.doubleValue();
                                SomaZP += zpa.doubleValue();
                                }
                            }
                        catch (Exception e) {}
                        }

                    TotalTriangulosShapePreenchidos += Pontos.length;

                    if (ContadorPontos == Pontos.length)
                            {
                            String [] PontosTriangulos = TriangulosString.split(";");
                            String [] ParametroTriangulo = new String[3];

                            int l = 0;

                            do
                                {
                                int k = 0;

                                do
                                    {
                                    ParametroTriangulo[k] = PontosTriangulos[(k + l) % Pontos.length];
                                    } while (k++ < 2);

                                String [] ParametroTrianguloCoordenadas1 = ParametroTriangulo[0].split(",");

                                String [] ParametroTrianguloCoordenadas2 = ParametroTriangulo[1].split(",");

                                String [] ParametroTrianguloCoordenadas3 = ParametroTriangulo[2].split(",");

                                if (Campos.length == 1)
                                    Comp.addTriangulosShape(Integer.parseInt(ParametroTrianguloCoordenadas1[0]), Integer.parseInt(ParametroTrianguloCoordenadas1[1]), Integer.parseInt(ParametroTrianguloCoordenadas2[0]), Integer.parseInt(ParametroTrianguloCoordenadas2[1]), Integer.parseInt(ParametroTrianguloCoordenadas3[0]), Integer.parseInt(ParametroTrianguloCoordenadas3[1]), CorTriangulosShape, (x - SomaXP / Pontos.length) * (x - SomaXP / Pontos.length) + (y - SomaYP / Pontos.length) * (y - SomaYP / Pontos.length) + (z - SomaZP / Pontos.length) * (z - SomaZP / Pontos.length), ContadorTriangulosDesenhar);
                                else
                                    {
                                    String [] RGB = Campos[1].split(",");
                                    Comp.addTriangulosShape(Integer.parseInt(ParametroTrianguloCoordenadas1[0]), Integer.parseInt(ParametroTrianguloCoordenadas1[1]), Integer.parseInt(ParametroTrianguloCoordenadas2[0]), Integer.parseInt(ParametroTrianguloCoordenadas2[1]), Integer.parseInt(ParametroTrianguloCoordenadas3[0]), Integer.parseInt(ParametroTrianguloCoordenadas3[1]), new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), (x - SomaXP / Pontos.length) * (x - SomaXP / Pontos.length) + (y - SomaYP / Pontos.length) * (y - SomaYP / Pontos.length) + (z - SomaZP / Pontos.length) * (z - SomaZP / Pontos.length), ContadorTriangulosDesenhar);
                                    StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
                                    }

                                k = 0;
                                } while (l++ < Pontos.length);

                            TriangulosString = "";
                            SomaXP = 0; SomaYP = 0; SomaZP = 0;
                            }
                    }
                }
            }

        Comp.clearTextos();

        int yl = ShiftVerticalLegendas;

        for (int i = 0; i < EspacoLegendas.length; i++)
            {
            TotalLegendas = EspacoLegendas.length;

            if (! (EspacoLegendas[i].equals("")))
                {
                String [] Campos = EspacoLegendas[i].split(";");

                if (Campos.length == 1)
                    Comp.addTexto(Campos[0], 5, yl, CorLegendas, i + 1);
                else
                    {
                    if (! (Campos[1].equals("")))
                        {
                        String [] RGB = Campos[1].split(",");
                        Comp.addTexto(Campos[0], 5, yl, new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2])), i + 1);
                        StringCores = StringCores + RGB[0] + "," + RGB[1] + "," + RGB[2] + ";";
                        }
                    }

                yl += ShiftVerticalLegendas;
                }
            }

        try {Thread.sleep(5);} catch(InterruptedException e) {}
        FlagDesenhar = 0;
        }
        
    public String LerEspaco(String ArquivoEspacoArg)
        {
        File file = new File(ArquivoEspacoArg);

        try
            {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String EspacoStr = br.readLine();

            String [] EspacoStr2 = EspacoStr.split("@");

            if (EspacoStr2.length >= 1)
                {
                String [] EspacoLinhas = EspacoStr2[0].split("\\|");

                for (int i = 0; i < EspacoLinhas.length; i++)
                    {
                    if (! (EspacoLinhas[i].equals("")))
                        {
                        String [] Campos = EspacoLinhas[i].split("c");
                        if (Campos.length > 2) return "Erro";

                        String [] Pontos = Campos[0].split(";");

                        if (Pontos.length != 2) return "Erro";

                        for (int j = 0; j < Pontos.length; j++)
                            {
                            String [] Coordenadas = Pontos[j].split(",");

                            if (Coordenadas.length != 3) return "Erro";

                            for (int k = 0; k < Coordenadas.length; k++)
                                if (! AntonioVandre.NumeroReal(Coordenadas[k])) return "Erro";
                            }

                        if (Campos.length == 2)
                            {
                            String [] RGB = Campos[1].split(",");

                            if (RGB.length != 3) return "Erro";

                            for (int k = 0; k < RGB.length; k++)
                                {
                                if (! AntonioVandre.NumeroInteiro(RGB[k])) return "Erro";

                                if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255))
                                    return "Erro";
                                }
                            }
                        }
                    }
                }

            if (EspacoStr2.length >= 2)
                {
                String [] EspacoTriangulosShapePreenchidos = EspacoStr2[1].split("\\|");

                for (int i = 0; i < EspacoTriangulosShapePreenchidos.length; i++)
                    {
                    if (! (EspacoTriangulosShapePreenchidos[i].equals("")))
                        {
                        String [] Campos = EspacoTriangulosShapePreenchidos[i].split("c");
                        if (Campos.length > 2) return "Erro";
                        String [] Pontos = Campos[0].split(";");

                        for (int j = 0; j < Pontos.length; j++)
                            {
                            String [] Coordenadas = Pontos[j].split(",");

                            if (Coordenadas.length != 3) return "Erro";

                            for (int k = 0; k < Coordenadas.length; k++)
                                if (! AntonioVandre.NumeroReal(Coordenadas[k])) return "Erro";
                            }

                        if (Campos.length == 2)
                            {
                            String [] RGB = Campos[1].split(",");

                            if (RGB.length != 3) return "Erro";

                            for (int k = 0; k < RGB.length; k++)
                                {
                                if (! AntonioVandre.NumeroInteiro(RGB[k])) return "Erro";

                                if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255))
                                    return "Erro";
                                }
                            }

                        }
                    }
                }

            if (EspacoStr2.length >= 3)
                {
                String UniaoStringLegendas = EspacoStr2[2];

                for (int i = 3; i < EspacoStr2.length; i++)
                    UniaoStringLegendas = UniaoStringLegendas + "@" + EspacoStr2[i];

                String [] EspacoLegendas = UniaoStringLegendas.split("\\|");

                for (int i = 0; i < EspacoLegendas.length; i++)
                    {
                    if (! (EspacoLegendas[i].equals("")))
                        {
                        String [] Campos = EspacoLegendas[i].split(";");
                        if (Campos.length > 2) return "Erro";

                        if (Campos.length == 2)
                            {
                            if (! (Campos[1].equals("")))
                                {
                                String [] RGB = Campos[1].split(",");

                                if (RGB.length != 3) return "Erro";

                                for (int k = 0; k < RGB.length; k++)
                                    {
                                    if (! AntonioVandre.NumeroInteiro(RGB[k])) return "Erro";

                                    if ((Integer.parseInt(RGB[k]) < 0) || (Integer.parseInt(RGB[k]) > 255))
                                        return "Erro";
                                    }
                                }
                            }
                        }
                    }
                }

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
        RotacaoTeta = Teta + Math.PI;
        RotacaoPhi = Phi + Math.PI;
        RaioTeta = 0;
        RaioPhi = 0;
        xt = x;
        yt = y;
        zt = z;
        Tetat = Teta;
        Phit = Phi;
        Rott = Rot;
        }
    }
