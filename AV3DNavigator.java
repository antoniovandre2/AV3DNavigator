/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).
 * 
 * Software AV3DNavigator.
 * 
 * Dependências: AntonioVandre, Apfloat (http://www.apfloat.org).
 * 
 * Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
 * 
 * Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).
 * 
 * Última atualização: 14-03-2023
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.PointerInfo;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Thread;

import java.lang.Math;
import java.io.*;

// Alta precisão com o Apfloat, porém com custo computacional.

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import AntonioVandre.*;

public class AV3DNavigator extends JComponent
    {
    public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";

    // Variáveis globais.

    public int TamanhoPlanoX = 400; // Default: 400.
    public int TamanhoPlanoY = 400; // Default: 400.
    public static int MinTamanhoPlanoX = 300; // Default: 300.
    public static int MinTamanhoPlanoY = 300; // Default: 300.
    public static String AV3DNavigatorIconFilePath = "AV3DNavigator - Logo - 200p.png";
    public double FatorAnguloVisao = 1; // Default: 1.
    public static double InfimoCossenoTetaIgnorar = 0.1; // Default: 0.1.
    public static double InfimoCossenoPhiIgnorar = 0.1; // Default: 0.1.
    public static int TamanhoEspacoLabelStatus = 360; // Default: 380.
    public static int TamanhoFonteLabelStatus = 7; // Default: 11.
    public double DistanciaTela = 2; // Default: valor inicial: 2.
    public static String MensagemErroEspacoAusente = "Entre com um arquivo de espaço.";
    public static String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";
    public static double FatorMouseWheel = 3; // Default: 3.
    public static double DeslocamentoLinear = 1; // Default: 1.
    public static double DeslocamentoAngular = 0.2; // Default: 0.2.
    public static int FramesDeslocamento = 4; // Default: 4.
    public static double FatorCorrecaoAspecto = 0; // Default: 0.
    public static double FatorMaxCorrecaoAspecto = 1; // Default: 1.
    public int ApfloatFlag = 0; // Default: 0.

    // Variáveis de funcionamento interno.

    public int CorrecaoX = 10;
    public int CorrecaoY = 0;
    public double AnguloVisao;
    public int Sair = 0;
    public String Espaco;
    public int FlagAlteracaoStatus = 1;

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double Teta = 0;
    public double Phi = 0;
    public double Rot = 0;
    public double xt = x;
    public double yt = y;
    public double zt = z;
    public double Tetat = Teta;
    public double Phit = Phi;
    public double Rott = Rot;
    public int MouseDown = 0;
    public int MouseX;
    public int MouseY;
    public int MouseXR;
    public int MouseYR;
    public double TetaR;
    public double PhiR;
    public int ContadorFrames = FramesDeslocamento;
    public Color CorBackground;
    public Color CorLinhas;
    public Color CorPoligonosShape;
    public int ContadorCorLinhas = 0; // Default inicial: 0.
    public int ContadorCorBackground = 1; // Default inicial: 1.
    public int ContadorCorPoligonosShape = 0; // Default inicial: 0.

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

    private static class PoligonoType extends Object
        {
        final Polygon poligono;
        final Color color;

        public PoligonoType(Polygon poligono, Color color)
            {
            this.poligono = poligono;
            this.color = color;
            }               
        }

    private final LinkedList<LineType> Linhas = new LinkedList<LineType>();
    private final LinkedList<PoligonoType> PoligonosShape = new LinkedList<PoligonoType>();

    public void addLine(int x1, int x2, int x3, int x4, Color color)
        {
        Linhas.add(new LineType(x1, x2, x3, x4, color));
        repaint();
        }

    public void addPoligonosShape(Polygon poligonoshape, Color color)
        {
        PoligonosShape.add(new PoligonoType(poligonoshape, color));
        repaint();
        }

    public void clearLines()
        {
        Linhas.clear();
        repaint();
        }

    public void clearPoligonosShape()
        {
        PoligonosShape.clear();
        repaint();
        }

    protected void paintComponent(Graphics g)
        {
        for (int i = 0; i < Linhas.size(); i++)
            {
            g.setColor(Linhas.get(i).color);
            g.drawLine(Linhas.get(i).x1, Linhas.get(i).y1, Linhas.get(i).x2, Linhas.get(i).y2);
            }

        for (int i = 0; i < PoligonosShape.size(); i++)
            {
            g.setColor(PoligonosShape.get(i).color);
            g.fillPolygon(PoligonosShape.get(i).poligono);
            }
        }

    public static void main (String[] args) {AV3DNavigator mainc = new AV3DNavigator(); if (args.length == 0) System.out.println(MensagemErroEspacoAusente); else mainc.mainrun(args[0]);}

    public void mainrun (String ArquivoEspaco)
        {
        String Versao = "Versão desconhecida.";

        File fileVersao = new File(ArquivoAV3DNavigatorVersao);

        try
            {
            BufferedReader brVersao = new BufferedReader(new FileReader(fileVersao));
            Versao = brVersao.readLine();
            } catch (IOException e) {}

        Espaco = LerEspaco (ArquivoEspaco);

        if (Espaco.equals("Erro"))
            {
            System.out.println(MensagemErroEspacoInvalido);
            return;
            }

        JFrame FrameEspaco = new JFrame("AV3DNavigator - " + Versao);
        FrameEspaco.setIconImage(new ImageIcon(getClass().getResource(AV3DNavigatorIconFilePath)).getImage());

        FrameEspaco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus));
        AV3DNavigator comp = new AV3DNavigator();
        comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
        FrameEspaco.getContentPane().add(comp, BorderLayout.PAGE_START);
        JLabel LabelStatus = new JLabel("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br><br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br><br>Rot = " + String.valueOf(Rot) + ".<br><br>Distância da tela = " + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar.<br>\"S\" para incrementar y. \"X\" para decrementar.<br>\"D\" para incrementar z. \"C\" para decrementar.<br>\"F\" para incrementar Teta. \"V\" para decrementar.<br>\"G\" para incrementar Phi. \"B\" para decrementar.<br>\"H\" para incrementar a rotação da tela. \"N\" para decrementar.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar.<br>\"T\" para shift negativo na cor da linha. \"Y\" para shift positivo.<br>\"U\" para shift negativo na cor de fundo. \"I\" para shift positivo.<br>\"O\" para shift negativo na cor dos polígonos preenchidos. \"P\" para shift positivo.<br><br>\"0\" para toggle alta precisão Apfloat (com custo computacional).<br><br>Setas para strafe. Mouse pode ser utilizado para movimentar.<br><br>Aperte barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");
        LabelStatus.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelStatus));
        LabelStatus.setOpaque(true);
        LabelStatus.setLocation(5, TamanhoPlanoY + 5);
        FrameEspaco.add(LabelStatus);

        FrameEspaco.addMouseListener(new MouseListener()
            {
            public void mousePressed(MouseEvent MouseEvento)
                {
                MouseXR = MouseX;
                MouseYR = MouseY;
                TetaR = Teta;
                PhiR = Phi;
                MouseDown = 1;
                }

            public void mouseClicked(MouseEvent MouseEvento) {}
            public void mouseEntered(MouseEvent MouseEvento) {}
            public void mouseExited(MouseEvent MouseEvento) {}
            public void mouseReleased(MouseEvent MouseEvento) {MouseDown = 0;}
            public void mouseDragged(MouseEvent MouseEvento) {}
            public void mouseMoved(MouseEvent MouseEvento) {}
            });

        FrameEspaco.addMouseWheelListener(e -> {
            if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                VariavelLimiteAtingido();
            else
                {
                x -= FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.cos(-Teta);

                y -= FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.sin(-Teta);

                z -= FatorMouseWheel * e.getWheelRotation() * Math.signum(Math.cos(-Teta)) * Math.sin(-Phi);

                xt = x; yt = y; zt = z;

                FlagAlteracaoStatus = 1;
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
                        x = 0;
                        y = 0;
                        z = 0;
                        Teta = 0;
                        Phi = 0;
                        Rot = 0;
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

                    if (keyCode == KeyEvent.VK_A)
                        {if (Math.abs(x) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {x += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_Z)
                        {if (Math.abs(x) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {x -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_S)
                        {if (Math.abs(y) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {y += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_X)
                        {if (Math.abs(y) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {y -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_D)
                        {if (Math.abs(z) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {z += DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_C)
                        {if (Math.abs(z) - DeslocamentoLinear <= Double.MAX_VALUE - DeslocamentoLinear) {z -= DeslocamentoLinear; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_F)
                        {if (Math.abs(Teta) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Teta += DeslocamentoAngular; ContadorFrames = 0; while (Math.abs(Math.cos(-Teta)) <= InfimoCossenoTetaIgnorar) {Teta += DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_V)
                        {if (Math.abs(Teta) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Teta -= DeslocamentoAngular; ContadorFrames = 0; while (Math.abs(Math.cos(-Teta)) <= InfimoCossenoTetaIgnorar) {Teta -= DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Tetat = Teta;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_G)
                        {if (Math.abs(Phi) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Phi += Math.signum(Math.cos(-Teta)) * DeslocamentoAngular; ContadorFrames = 0; while (Math.abs(Math.cos(-Phi)) <= InfimoCossenoPhiIgnorar) {Phi += Math.signum(Math.cos(-Teta)) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_B)
                        {if (Math.abs(Phi) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Phi -= Math.signum(Math.cos(-Teta)) * DeslocamentoAngular; ContadorFrames = 0; while (Math.abs(Math.cos(-Phi)) <= InfimoCossenoPhiIgnorar) {Phi -= Math.signum(Math.cos(-Teta)) * DeslocamentoAngular; ContadorFrames = FramesDeslocamento; Phit = Phi;}} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_H)
                        {if (Math.abs(Rot) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Rot += DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_N)
                        {if (Math.abs(Rot) - DeslocamentoAngular <= Double.MAX_VALUE - DeslocamentoAngular) {Rot -= DeslocamentoAngular; ContadorFrames = 0;} else VariavelLimiteAtingido();}
        
                    if (keyCode == KeyEvent.VK_Q)
                        {if (DistanciaTela > 1) DistanciaTela -= 1;}

                    if (keyCode == KeyEvent.VK_W)
                        {if (Math.abs(DistanciaTela) - 1 <= Double.MAX_VALUE - 1) DistanciaTela += 1; else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_E)
                        {if (FatorAnguloVisao > 1) FatorAnguloVisao -= 1;}

                    if (keyCode == KeyEvent.VK_R)
                        {if (Math.abs(FatorAnguloVisao) - 1 <= Double.MAX_VALUE - 1) FatorAnguloVisao += 1; else VariavelLimiteAtingido();}

                    if (keyCode == KeyEvent.VK_T)
                        if (ContadorCorLinhas > 0) ContadorCorLinhas -= 1;

                    if (keyCode == KeyEvent.VK_Y)
                        if (ContadorCorLinhas < 15) ContadorCorLinhas += 1;

                    if (keyCode == KeyEvent.VK_U)
                        if (ContadorCorBackground > 0) ContadorCorBackground -= 1;

                    if (keyCode == KeyEvent.VK_I)
                        if (ContadorCorBackground < 15) ContadorCorBackground += 1;

                    if (keyCode == KeyEvent.VK_O)
                        if (ContadorCorPoligonosShape > 0) ContadorCorPoligonosShape -= 1;

                    if (keyCode == KeyEvent.VK_P)
                        if (ContadorCorPoligonosShape < 15) ContadorCorPoligonosShape += 1;

                    if (keyCode == KeyEvent.VK_0)
                        if (ApfloatFlag == 0) ApfloatFlag = 1; else ApfloatFlag = 0;

                    if (keyCode == KeyEvent.VK_UP)
                        {
                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x += Math.cos(-Phi) * Math.cos(-Teta);
                            y += Math.cos(-Phi) * Math.sin(-Teta);
                            z += Math.signum(Math.cos(-Teta)) * Math.sin(-Phi);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_DOWN)
                        {
                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(z) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x -= Math.cos(-Phi) * Math.cos(-Teta);
                            y -= Math.cos(-Phi) * Math.sin(-Teta);
                            z -= Math.signum(Math.cos(-Teta)) * Math.sin(-Phi);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_LEFT)
                        {
                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x += Math.cos(-Teta * Math.cos(-Teta) - Math.PI / 2);
                            y += Math.sin(-Teta * Math.cos(-Teta) - Math.PI / 2);
                            }

                        ContadorFrames = 0;
                        }

                    if (keyCode == KeyEvent.VK_RIGHT)
                        {
                        if ((Math.abs(x) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear) || (Math.abs(y) - DeslocamentoLinear > Double.MAX_VALUE - DeslocamentoLinear))
                            VariavelLimiteAtingido();
                        else
                            {
                            x -= Math.cos(-Teta * Math.cos(-Teta) - Math.PI / 2);
                            y -= Math.sin(-Teta * Math.cos(-Teta) - Math.PI / 2);
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

            int width = FrameEspaco.getWidth();
            int height = FrameEspaco.getHeight();

            if (width < MinTamanhoPlanoX)
                {
                width = MinTamanhoPlanoX;
                FrameEspaco.setPreferredSize(new Dimension(width, height));
                FrameEspaco.setSize(width, height);
                FlagRedimensionarOver = 1;
                }

            if (height < MinTamanhoPlanoY)
                {
                height = MinTamanhoPlanoY;
                FrameEspaco.setPreferredSize(new Dimension(width, height));
                FrameEspaco.setSize(width, height);
                FlagRedimensionarOver = 1;
                }

            if (FlagRedimensionarOver == 0)
                if ((width != TamanhoPlanoX) || (height != TamanhoPlanoY + TamanhoEspacoLabelStatus))
                    {
                    TamanhoPlanoX = width;
                    TamanhoPlanoY = height - TamanhoEspacoLabelStatus;

                    FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus));
                    comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
                    FrameEspaco.pack();

                    x = 0;
                    y = 0;
                    z = 0;
                    Teta = 0;
                    Phi = 0;
                    Rot = 0;
                    xt = x;
                    yt = y;
                    zt = z;
                    Tetat = Teta;
                    Phit = Phi;
                    Rott = Rot;

                    FatorAnguloVisao = 1;

                    DistanciaTela = 2;

                    ContadorFrames = FramesDeslocamento;

                    FlagAlteracaoStatus = 1;
                    }

            Point reference = FrameEspaco.getLocationOnScreen();
            MouseX = MouseInfo.getPointerInfo().getLocation().x - reference.x;
            MouseY = MouseInfo.getPointerInfo().getLocation().y - reference.y;

            if (MouseDown == 0)
                {
                if (ContadorFrames < FramesDeslocamento)
                    {
                    if (xt > x)
                        {
                        xt -= DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (yt > y)
                        {
                        yt -= DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (zt > z)
                        {
                        zt -= DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (Tetat > Teta)
                        {
                        Tetat -= DeslocamentoAngular / FramesDeslocamento; FlagAlteracaoStatus = 1;
                        }

                    if (Phit > Phi)
                        {
                        Phit -= DeslocamentoAngular / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (Rott > Rot)
                        {
                        Rott -= DeslocamentoAngular / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (xt < x)
                        {
                        xt += DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (yt < y)
                        {
                        yt += DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (zt < z)
                        {
                        zt += DeslocamentoLinear / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (Tetat < Teta)
                        {
                        Tetat +=  DeslocamentoAngular / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (Phit < Phi)
                        {
                        Phit +=  DeslocamentoAngular / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    if (Rott < Rot)
                        {
                        Rott +=  DeslocamentoAngular / FramesDeslocamento;
                        FlagAlteracaoStatus = 1;
                        }

                    ContadorFrames++;
                    }
                }
            else
                {
                if ((Math.abs(Teta) - DeslocamentoAngular > Double.MAX_VALUE - DeslocamentoAngular) || (Math.abs(Phi) - DeslocamentoAngular > Double.MAX_VALUE - DeslocamentoAngular))
                    VariavelLimiteAtingido();
                else
                    {
                    Teta = 2 * Math.PI * (MouseX - MouseXR) / TamanhoPlanoX + TetaR;
                    while (Math.abs(Math.cos(-Teta)) <= InfimoCossenoTetaIgnorar) Teta -= Math.signum(Math.cos(-TetaR)) * DeslocamentoAngular;
                    Tetat = Teta;
                    Phi = Math.signum(Math.cos(-Teta)) * Math.PI * (MouseY - MouseYR) / TamanhoPlanoY + PhiR;
                    while (Math.abs(Math.cos(-Phi)) <= InfimoCossenoPhiIgnorar) Phi -= Math.signum(-PhiR) * Math.signum(Math.cos(-Teta)) * DeslocamentoAngular;
                    Phit = Phi;

                    FlagAlteracaoStatus = 1;
                    }
                }

            if (FlagAlteracaoStatus == 1)
                {
                switch (ContadorCorLinhas)
                    {
                    case 15: CorLinhas = new Color(192, 192, 192); break;
                    case 14: CorLinhas = new Color(175, 255, 175); break;
                    case 13: CorLinhas = new Color(128, 128, 255); break;
                    case 12: CorLinhas = Color.YELLOW; break;
                    case 11: CorLinhas = Color.ORANGE; break;
                    case 10: CorLinhas = Color.PINK; break;
                    case 9: CorLinhas = Color.CYAN; break;
                    case 8: CorLinhas = Color.BLUE; break;
                    case 7: CorLinhas = Color.MAGENTA; break;
                    case 6: CorLinhas = new Color(128, 175, 175); break;
                    case 5: CorLinhas = Color.GRAY; break;
                    case 4: CorLinhas = Color.RED; break;
                    case 3: CorLinhas = new Color(64, 64, 64); break;
                    case 2: CorLinhas = new Color(64, 64, 128); break;
                    case 1: CorLinhas = Color.BLACK; break;
                    case 0: CorLinhas = Color.WHITE; break;
                    }

                switch (ContadorCorBackground)
                    {
                    case 15: CorBackground = new Color(192, 192, 192); break;
                    case 14: CorBackground = new Color(175, 255, 175); break;
                    case 13: CorBackground = new Color(128, 128, 255); break;
                    case 12: CorBackground = Color.YELLOW; break;
                    case 11: CorBackground = Color.ORANGE; break;
                    case 10: CorBackground = Color.PINK; break;
                    case 9: CorBackground = Color.CYAN; break;
                    case 8: CorBackground = Color.BLUE; break;
                    case 7: CorBackground = Color.MAGENTA; break;
                    case 6: CorBackground = new Color(128, 175, 175); break;
                    case 5: CorBackground = Color.GRAY; break;
                    case 4: CorBackground = Color.RED; break;
                    case 3: CorBackground = new Color(64, 64, 64); break;
                    case 2: CorBackground = new Color(64, 64, 128); break;
                    case 1: CorBackground = Color.BLACK; break;
                    case 0: CorBackground = Color.WHITE; break;
                    }

                switch (ContadorCorPoligonosShape)
                    {
                    case 15: CorPoligonosShape = new Color(192, 192, 192); break;
                    case 14: CorPoligonosShape = new Color(175, 255, 175); break;
                    case 13: CorPoligonosShape = new Color(128, 128, 255); break;
                    case 12: CorPoligonosShape = Color.YELLOW; break;
                    case 11: CorPoligonosShape = Color.ORANGE; break;
                    case 10: CorPoligonosShape = Color.PINK; break;
                    case 9: CorPoligonosShape = Color.CYAN; break;
                    case 8: CorPoligonosShape = Color.BLUE; break;
                    case 7: CorPoligonosShape = Color.MAGENTA; break;
                    case 6: CorPoligonosShape = new Color(128, 175, 175); break;
                    case 5: CorPoligonosShape = Color.GRAY; break;
                    case 4: CorPoligonosShape = Color.RED; break;
                    case 3: CorPoligonosShape = new Color(64, 64, 64); break;
                    case 2: CorPoligonosShape = new Color(64, 64, 128); break;
                    case 1: CorPoligonosShape = Color.BLACK; break;
                    case 0: CorPoligonosShape = Color.WHITE; break;
                    }

                FrameEspaco.getContentPane().setBackground(CorBackground);

                if (ApfloatFlag == 0)
                    {
                    AnguloVisao = Math.atan(Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 / DistanciaTela);

                    AnguloVisao /= FatorAnguloVisao;
                    }
                else
                    {
                    AnguloVisao = ApfloatMath.atan(new Apfloat(Math.min(TamanhoPlanoX, TamanhoPlanoY)).divide(new Apfloat(2)).divide(new Apfloat(DistanciaTela))).doubleValue();

                    AnguloVisao = (new Apfloat(AnguloVisao)).divide(new Apfloat(FatorAnguloVisao)).doubleValue();
                    }

                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br><br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br><br>Rot = " + String.valueOf(Rot) + ".<br><br>Distância da tela = " + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar.<br>\"S\" para incrementar y. \"X\" para decrementar.<br>\"D\" para incrementar z. \"C\" para decrementar.<br>\"F\" para incrementar Teta. \"V\" para decrementar.<br>\"G\" para incrementar Phi. \"B\" para decrementar.<br>\"H\" para incrementar a rotação da tela. \"N\" para decrementar.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar.<br>\"T\" para shift negativo na cor da linha. \"Y\" para shift positivo.<br>\"U\" para shift negativo na cor de fundo. \"I\" para shift positivo.<br>\"O\" para shift negativo na cor dos polígonos preenchidos. \"P\" para shift positivo.<br><br>\"0\" para toggle alta precisão Apfloat (com custo computacional).<br><br>Setas para strafe. Mouse pode ser utilizado para movimentar.<br><br>Aperte barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");

                DesenharEspaco(comp);

                FlagAlteracaoStatus = 0;
                }

            try {Thread.sleep(10);} catch(InterruptedException e) {}
            }

        System.exit(0);
        }

    public void DesenharEspaco(AV3DNavigator comp)
        {
        String [] EspacoStr2 = Espaco.split("@");

        String [] EspacoLinhas = EspacoStr2[0].split("\\|");
        String [] EspacoPoligonosShapePreenchidos = EspacoStr2[1].split("\\|");

        comp.clearLines();
        comp.clearPoligonosShape();

        for (int i = 0; i < EspacoLinhas.length; i++)
            {
            String [] Pontos = EspacoLinhas[i].split(";");

            String [] CoordenadasOrig = Pontos[0].split(",");
            String [] CoordenadasDest = Pontos[1].split(",");

            if (ApfloatFlag == 0)
                {
                double xo = Double.parseDouble(CoordenadasOrig[0]) - xt;

                double xd = Double.parseDouble(CoordenadasDest[0]) - xt;

                double yo = Double.parseDouble(CoordenadasOrig[1]) - yt;

                double yd = Double.parseDouble(CoordenadasDest[1]) - yt;

                double zo = (-Double.parseDouble(CoordenadasOrig[2])) - zt;

                double zd = (-Double.parseDouble(CoordenadasDest[2])) - zt;

                int xi;
                int yi;
                int xf;
                int yf;

                if ((xo != 0) && (xd != 0))
                    {
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (Math.cos(Rott) * Math.tan(Math.atan(yo / xo) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) - Math.sin(Rott) * Math.tan(Math.atan(zo / xo) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoX;

                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(Math.cos(-Teta)) * (Math.sin(Rott) * Math.cos(-Teta) * Math.tan(Math.atan(yo / xo) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) + Math.cos(Rott) * Math.tan(Math.atan(zo / xo) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoY;

                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (Math.cos(Rott) * Math.tan(Math.atan(yd / xd) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) - Math.sin(Rott) * Math.tan(Math.atan(zd / xd) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoX;
            
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(Math.cos(-Teta)) * (Math.sin(Rott) * Math.tan(Math.atan(yd / xd) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) + Math.cos(Rott) * Math.tan(Math.atan(zd / xd) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoY;
                                        
                    double ProdutoEscalaro = xo * Math.cos(-Tetat) * Math.cos(-Phit) + yo * Math.sin(-Tetat) * Math.cos(-Phit) + zo * Math.sin(-Phit) * Math.signum(Math.cos(-Teta));

                    double ProdutoEscalard = xd * Math.cos(-Tetat) * Math.cos(-Phit) + yd * Math.sin(-Tetat) * Math.cos(-Phit) + zd * Math.sin(-Phit) * Math.signum(Math.cos(-Teta));

                    if ((ProdutoEscalaro > 0) && (ProdutoEscalard > 0) && (Math.acos(ProdutoEscalaro / Math.sqrt(xo * xo + yo * yo + zo * zo)) < AnguloVisao) && (Math.acos(ProdutoEscalard / Math.sqrt(xd * xd + yd * yd + zd * zd)) < AnguloVisao) && (Math.min(xi, Math.min(yi, Math.min(xf, yf))) > 0) && (Math.max(xi, Math.max(yi, Math.max(xf, yf))) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                        comp.addLine(xi, yi, xf, yf, CorLinhas);
                    }
                }
            else
                {
                // Alta precisão com o Apfloat, porém com custo computacional.

                Apfloat xoa = (new Apfloat(Double.parseDouble(CoordenadasOrig[0]))).add(new Apfloat(-xt));

                Apfloat xda = (new Apfloat(Double.parseDouble(CoordenadasDest[0]))).add(new Apfloat(-xt));

                Apfloat yoa = (new Apfloat(Double.parseDouble(CoordenadasOrig[1]))).add(new Apfloat(-yt));

                Apfloat yda = (new Apfloat(Double.parseDouble(CoordenadasDest[1]))).add(new Apfloat(-yt));

                Apfloat zoa = (new Apfloat(-Double.parseDouble(CoordenadasOrig[2]))).add(new Apfloat(-zt));

                Apfloat zda = (new Apfloat(-Double.parseDouble(CoordenadasDest[2]))).add(new Apfloat(-zt));

                int xi;
                int yi;
                int xf;
                int yf;

                if ((xoa.doubleValue() != 0) && (xda.doubleValue() != 0))
                    {
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(yoa.divide(xoa)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zoa.divide(xoa)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).multiply(new Apfloat(-1)))).doubleValue()) - CorrecaoX;

                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(ApfloatMath.cos(new Apfloat(-Teta)).doubleValue()))).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(yoa.divide(xoa)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zoa.divide(xoa)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))))).doubleValue()) - CorrecaoY;

                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(yda.divide(xda)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zda.divide(xda)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).multiply(new Apfloat(-1)))).doubleValue()) - CorrecaoX;
            
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(ApfloatMath.cos(new Apfloat(-Teta)).doubleValue()))).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(yda.divide(xda)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zda.divide(xda)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))))).doubleValue()) - CorrecaoY;
                    
                    Apfloat ProdutoEscalaroa = xoa.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zoa.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(Math.signum(ApfloatMath.cos(new Apfloat(-Teta)).doubleValue()))));

                    Apfloat ProdutoEscalarda = xda.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(yda.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zda.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(Math.signum(ApfloatMath.cos(new Apfloat(-Teta)).doubleValue()))));

                    if ((ProdutoEscalaroa.doubleValue() > 0) && (ProdutoEscalarda.doubleValue() > 0) && (ApfloatMath.acos(ProdutoEscalaroa.divide(ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).doubleValue() < AnguloVisao) && (ApfloatMath.acos(ProdutoEscalarda.divide(ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).doubleValue() < AnguloVisao) && (ApfloatMath.min(new Apfloat(xi), ApfloatMath.min(new Apfloat(yi), ApfloatMath.min(new Apfloat(xf), new Apfloat(yf)))).doubleValue() > 0) && (ApfloatMath.max(new Apfloat(xi), ApfloatMath.max(new Apfloat(yi), ApfloatMath.max(new Apfloat(xf), new Apfloat(yf)))).doubleValue() < ApfloatMath.min(new Apfloat(TamanhoPlanoX), new Apfloat(TamanhoPlanoY)).doubleValue()))
                        comp.addLine(xi, yi, xf, yf, CorLinhas);
                    }
                }
            }

        for (int i = 0; i < EspacoPoligonosShapePreenchidos.length; i++)
            {
            Polygon Poligono = new Polygon();

            String [] Pontos = EspacoPoligonosShapePreenchidos[i].split(";");

            int ContadorPontos = 0;
            int FlagPontoNatureza = 0;

            for (int j = 0; j < Pontos.length; j++)
                {
                String [] Coordenadas = Pontos[j].split(",");

                if (ApfloatFlag == 0)
                    {
                    double xp = Double.parseDouble(Coordenadas[0]) - xt;

                    double yp = Double.parseDouble(Coordenadas[1]) - yt;
    
                    double zp = (-Double.parseDouble(Coordenadas[2])) - zt;
    
                    int xpp;
                    int ypp;

                    if (xp != 0)
                        {
                        xpp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (Math.cos(Rott) * Math.tan(Math.atan(yp / xp) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) - Math.sin(Rott) * Math.tan(Math.atan(zp / xp) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoX;

                        ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(Math.cos(-Teta)) * (Math.sin(Rott) * Math.tan(Math.atan(yp / xp) + Tetat) / Math.max(Math.pow(Math.abs(Math.cos(-Tetat)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto) + Math.cos(Rott) * Math.tan(Math.atan(zp / xp) + Phit) / Math.max(Math.pow(Math.abs(Math.cos(-Phit)), FatorCorrecaoAspecto), 1 / FatorMaxCorrecaoAspecto))) - CorrecaoY;

                        double ProdutoEscalar = xp * Math.cos(-Tetat) * Math.cos(-Phit) + yp * Math.sin(-Tetat) * Math.cos(-Phit) + zp * Math.sin(-Phit) * Math.signum(Math.cos(-Teta));

                        if ((ProdutoEscalar > 0) && (Math.acos(ProdutoEscalar / Math.sqrt(xp * xp + yp * yp + zp * zp)) < AnguloVisao) && ((Math.min(xpp, ypp) > 0) && (Math.max(xpp, ypp)) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                            {
                            ContadorPontos++;
                            Poligono.addPoint(xpp, ypp);
                            }
                        }
                    }
                else
                    {
                    Apfloat xpa = (new Apfloat(Double.parseDouble(Coordenadas[0]))).add(new Apfloat(-xt));

                    Apfloat ypa = (new Apfloat(Double.parseDouble(Coordenadas[1]))).add(new Apfloat(-yt));

                    Apfloat zpa = (new Apfloat(-Double.parseDouble(Coordenadas[2]))).add(new Apfloat(-zt));

                    int xpp;
                    int ypp;

                    if (xpa.doubleValue() != 0)
                        {
                        xpp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(ypa.divide(xpa)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zpa.divide(xpa)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).multiply(new Apfloat(-1)))).doubleValue()) - CorrecaoX;

                        ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(Math.cos(-Teta)))).multiply(ApfloatMath.sin(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(ypa.divide(xpa)).add(new Apfloat(Tetat)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Tetat))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))).add(ApfloatMath.cos(new Apfloat(Rott)).multiply(ApfloatMath.tan(ApfloatMath.atan(zpa.divide(xpa)).add(new Apfloat(Phit)))).divide(ApfloatMath.max(ApfloatMath.pow(ApfloatMath.abs(ApfloatMath.cos(new Apfloat(-Phit))), (new Apfloat(FatorCorrecaoAspecto))), (new Apfloat(1)).divide(new Apfloat(FatorMaxCorrecaoAspecto)))))).doubleValue()) - CorrecaoX;

                        Apfloat ProdutoEscalara = xpa.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zpa.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(Math.signum(ApfloatMath.cos(new Apfloat(-Teta)).doubleValue()))));

                        if ((ProdutoEscalara.doubleValue() > 0) && (ApfloatMath.acos(ProdutoEscalara.divide(ApfloatMath.sqrt(xpa.multiply(xpa).add(ypa.multiply(ypa)).add(zpa.multiply(zpa))))).doubleValue() < AnguloVisao) && ((Math.min(xpp, ypp) > 0) && (Math.max(xpp, ypp)) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                            {
                            ContadorPontos++;
                            Poligono.addPoint(xpp, ypp);
                            }
                        }
                    }

                if (ContadorPontos == Pontos.length)
                    comp.addPoligonosShape(Poligono, CorPoligonosShape);
                }
            }
        }
        
    public String LerEspaco(String ArquivoEspacoArg)
        {
        File file = new File(ArquivoEspacoArg);

        try
            {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String EspacoStr = br.readLine();

            String [] EspacoStr2 = EspacoStr.split("@");

            String [] EspacoLinhas = EspacoStr2[0].split("\\|");

            for (int i = 0; i < EspacoLinhas.length; i++)
                {
                String [] Pontos = EspacoLinhas[i].split(";");

                if (Pontos.length != 2) return "Erro";

                for (int j = 0; j < Pontos.length; j++)
                    {
                    String [] Coordenadas = Pontos[j].split(",");

                    if (Coordenadas.length != 3) return "Erro";
                    for (int k = 0; k < Coordenadas.length; k++)
                        if (! AntonioVandre.NumeroReal(Coordenadas[k])) return "Erro";
                    }
                }

            String [] EspacoPoligonosShapePreenchidos = EspacoStr2[1].split("\\|");

            for (int i = 0; i < EspacoPoligonosShapePreenchidos.length; i++)
                {
                String [] Pontos = EspacoPoligonosShapePreenchidos[i].split(";");

                for (int j = 0; j < Pontos.length; j++)
                    {
                    String [] Coordenadas = Pontos[j].split(",");

                    if (Coordenadas.length != 3) return "Erro";
                    for (int k = 0; k < Coordenadas.length; k++)
                        if (! AntonioVandre.NumeroReal(Coordenadas[k])) return "Erro";
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
        xt = x;
        yt = y;
        zt = z;
        Tetat = Teta;
        Phit = Phi;
        Rott = Rot;
        }
    }
