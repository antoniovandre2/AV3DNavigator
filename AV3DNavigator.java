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
 * Última atualização: 20-02-2023
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
    public static int TamanhoEspacoLabelStatus = 330; // Default: 380.
    public static int TamanhoFonteLabelStatus = 7; // Default: 11.
    public double DistanciaTela = 2; // Default: valor inicial: 2.
    public static String MensagemErroEspacoAusente = "Entre com um arquivo de espaço.";
    public static String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";
    public static double FatorMouseWheel = 3; // Default: 3.
    public static double DeslocamentoLinear = 1; // Default: 1.
    public static double DeslocamentoAngular = 0.2; // Default: 0.2.
    public static int FramesDeslocamento = 4; // Default: 5.
    public static double ShiftCartesianoAnular = 0.01; // Default: 0.01.
    public int ApfloatFlag = 0; // Default: 0.

    // Variáveis de funcionamento interno.

    public int CorrecaoX = 10;
    public int CorrecaoY = 0;
    public int FatorZ;
    public double AnguloVisao;
    public int Sair = 0;
    public String Espaco;
    public int FlagAlteracaoStatus = 1;

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double Teta = 0;
    public double Phi = 0;
    public double xt = x;
    public double yt = y;
    public double zt = z;
    public double Tetat = Teta;
    public double Phit = Phi;
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
        JLabel LabelStatus = new JLabel("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br>Distância da tela:" + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir a distância da tela.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar o fator redutor do ângulo de visão.<br>\"T\" para shift negativo na cor da linha. \"Y\" para shift positivo na cor da linha.<br>\"U\" para shift negativo na cor de fundo. \"I\" para shift positivo na cor de fundo.<br>\"O\" para shift negativo na cor dos polígonos preenchidos. \"P\" para shift positivo na cor dos polígonos preenchidos.<br><br>\"0\" para toggle alta precisão Apfloat (com custo computacional).<br><br>Setas para strafe. Mouse pode ser utilizado para movimentar.<br><br>Aperte barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");
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
            x -= FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.cos(-Teta);

            y -= FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.sin(-Teta);

            z -= FatorMouseWheel * e.getWheelRotation() * Math.sin(-Phi);

            xt = x; yt = y; zt = z;

            FlagAlteracaoStatus = 1;
            });

        FrameEspaco.addKeyListener(new KeyListener()
            {
            public void keyPressed(KeyEvent ke)
                {
                int keyCode = ke.getKeyCode();

                if (keyCode == KeyEvent.VK_ESCAPE)
                    {Sair = 1;}

                if (keyCode == KeyEvent.VK_SPACE)
                    {
                    x = 0;
                    y = 0;
                    z = 0;
                    Teta = 0;
                    Phi = 0;
                    xt = x;
                    yt = y;
                    zt = z;
                    Tetat = Teta;
                    Phit = Phi;
                    FatorAnguloVisao = 1;

                    DistanciaTela = 2;

                    ContadorFrames = FramesDeslocamento;
                    }

                if (keyCode == KeyEvent.VK_A) {x += DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_Z) {x -= DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_S) {y += DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_X) {y -= DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_D) {z += DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_C) {z -= DeslocamentoLinear;}

                if (keyCode == KeyEvent.VK_F) {Teta += DeslocamentoAngular;}

                if (keyCode == KeyEvent.VK_V) {Teta -= DeslocamentoAngular;}

                if (keyCode == KeyEvent.VK_G)
                    if (Math.abs(Phi) < Math.PI / 2) Phi += DeslocamentoAngular;

                if (keyCode == KeyEvent.VK_B)
                    if (Math.abs(Phi) < Math.PI / 2) Phi -= DeslocamentoAngular;

                if (keyCode == KeyEvent.VK_Q)
                    {if (DistanciaTela > 1) DistanciaTela -= 1;}

                if (keyCode == KeyEvent.VK_W)
                    DistanciaTela += 1;

                if (keyCode == KeyEvent.VK_E)
                    {if (FatorAnguloVisao > 1) FatorAnguloVisao -= 1;}

                if (keyCode == KeyEvent.VK_R)
                    FatorAnguloVisao += 1;

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
                    x += Math.cos(-Phi) * Math.cos(-Teta);
                    y += Math.cos(-Phi) * Math.sin(-Teta);
                    z += Math.sin(-Phi);
                    }

                if (keyCode == KeyEvent.VK_DOWN)
                    {
                    x -= Math.cos(-Phi) * Math.cos(-Teta);
                    y -= Math.cos(-Phi) * Math.sin(-Teta);
                    z -= Math.sin(-Phi);
                    }

                if (keyCode == KeyEvent.VK_LEFT)
                    {
                    x += Math.cos(-Teta - Math.PI / 2);
                    y += Math.sin(-Teta - Math.PI / 2);
                    }

                if (keyCode == KeyEvent.VK_RIGHT)
                    {
                    x -= Math.cos(-Teta - Math.PI / 2);
                    y -= Math.sin(-Teta - Math.PI / 2);
                    }

                ContadorFrames = 0;
                FlagAlteracaoStatus = 1;
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
                    xt = x;
                    yt = y;
                    zt = z;
                    Tetat = Teta;
                    Phit = Phi;
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

                    ContadorFrames++;
                    }
                }
            else
                {
                Teta = 2 * Math.PI * (MouseX - MouseXR) / TamanhoPlanoX + TetaR;
                Tetat = Teta;

                double PhiTemp = Math.PI * (MouseY - MouseYR) / TamanhoPlanoY + PhiR;

                if (Math.abs(Phi) < Math.PI / 2)
                    Phi = PhiTemp;
                else
                    Phi = (Math.PI / 2 - DeslocamentoAngular) * Math.signum(Phi);
                
                Phit = Phi;

                FlagAlteracaoStatus = 1;
                }

            if ((Math.abs(Phi) >= Math.PI / 2))
                Phi = (Math.PI / 2 - DeslocamentoAngular) * Math.signum(Phi);

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

                if (Math.cos(-Teta) > 0) FatorZ = 1; else FatorZ = -1;

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

                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br>Distância da tela:" + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>Apfloat = " + String.valueOf(ApfloatFlag) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir a distância da tela.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar o fator redutor do ângulo de visão.<br>\"T\" para shift negativo na cor da linha. \"Y\" para shift positivo na cor da linha.<br>\"U\" para shift negativo na cor de fundo. \"I\" para shift positivo na cor de fundo.<br>\"O\" para shift negativo na cor dos polígonos preenchidos. \"P\" para shift positivo na cor dos polígonos preenchidos.<br><br>\"0\" para toggle alta precisão Apfloat (com custo computacional).<br><br>Setas para strafe. Mouse pode ser utilizado para movimentar.<br><br>Aperte barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");

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
                double xo = (Double.parseDouble(CoordenadasOrig[0]) - xt);
                double xd = (Double.parseDouble(CoordenadasDest[0]) - xt);
                double yo = (Double.parseDouble(CoordenadasOrig[1]) - yt);
                double yd = (Double.parseDouble(CoordenadasDest[1]) - yt);
                double zo = (FatorZ * (-Double.parseDouble(CoordenadasOrig[2]) - zt));
                double zd = (FatorZ * (-Double.parseDouble(CoordenadasDest[2]) - zt));

                if ((Math.abs(xo) > ShiftCartesianoAnular) && (Math.abs(xd) > ShiftCartesianoAnular) && (Math.abs(yo) > ShiftCartesianoAnular) && (Math.abs(yd) > ShiftCartesianoAnular) && (Math.abs(zo) > ShiftCartesianoAnular) && (Math.abs(zd) > ShiftCartesianoAnular))
                    {
                    int xi;
                    int yi;
                    int xf;
                    int yf;

                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(yo / xo) + Tetat)) - CorrecaoX;

                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(yd / xd) + Tetat)) - CorrecaoX;

                    if (Math.abs(xo) > Math.abs(yo))
                        yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(zo / xo) + Phit)) - CorrecaoY;
                    else
                        yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(yo) * FatorZ * Math.tan(Math.atan(zo / yo) + Math.signum(yd) * FatorZ * Phit)) - CorrecaoY;

                    if (Math.abs(xd) > Math.abs(yd))
                        yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(zd / xd) + Phit)) - CorrecaoY;
                    else
                        yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(yd) * FatorZ * Math.tan(Math.atan(zd / yd) + Math.signum(yd) * FatorZ * Phit)) - CorrecaoY;
                    
                    double ProdutoEscalaro = xo * Math.cos(-Tetat) * Math.cos(-Phit) + yo * Math.sin(-Tetat) * Math.cos(-Phit) + FatorZ * zo * Math.sin(-Phit);

                    double ProdutoEscalard = xd * Math.cos(-Tetat) * Math.cos(-Phit) + yd * Math.sin(-Tetat) * Math.cos(-Phit) + FatorZ * zd * Math.sin(-Phit);
    
                    if ((Math.acos(ProdutoEscalaro / Math.sqrt(xo * xo + yo * yo + zo * zo)) < AnguloVisao) && (Math.acos(ProdutoEscalard / Math.sqrt(xd * xd + yd * yd + zd * zd)) < AnguloVisao) && (Math.min(xi, Math.min(yi, Math.min(xf, yf))) > 0) && (Math.max(xi, Math.max(yi, Math.max(xf, yf))) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                        comp.addLine(xi, yi, xf, yf, CorLinhas);
                    }
                }
            else
                {
                // Alta precisão com o Apfloat, porém com custo computacional.

                Apfloat xoa = new Apfloat(Double.parseDouble(CoordenadasOrig[0]) - xt);

                Apfloat xda = new Apfloat(Double.parseDouble(CoordenadasDest[0]) - xt);

                Apfloat yoa = new Apfloat(Double.parseDouble(CoordenadasOrig[1]) - yt);

                Apfloat yda = new Apfloat(Double.parseDouble(CoordenadasDest[1]) - yt);

                Apfloat zoa = new Apfloat(FatorZ * (-Double.parseDouble(CoordenadasOrig[2]) - zt));

                Apfloat zda = new Apfloat(FatorZ * (-Double.parseDouble(CoordenadasDest[2]) - zt));

                if ((ApfloatMath.abs(xoa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(xda).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(yoa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(yoa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(zoa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(zda).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()))
                    {
                    int xi;
                    int yi;
                    int xf;
                    int yf;

                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(yoa.divide(xoa)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;

                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(yda.divide(xda)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;

                    if (ApfloatMath.abs(xoa).doubleValue() > ApfloatMath.abs(yoa).doubleValue())
                        yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(zoa.divide(xoa)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
                    else
                        yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(yoa.doubleValue()))).multiply(new Apfloat(FatorZ)).multiply(ApfloatMath.tan(ApfloatMath.atan(zoa.divide(yoa)).add((new Apfloat(Phit)).multiply(new Apfloat(Math.signum(yoa.doubleValue()))).multiply(new Apfloat(FatorZ))))).doubleValue()) - CorrecaoY;

                    if (ApfloatMath.abs(xda).doubleValue() > ApfloatMath.abs(yda).doubleValue())
                        yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(zda.divide(xda)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
                    else
                        yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(yda.doubleValue()))).multiply(new Apfloat(FatorZ)).multiply(ApfloatMath.tan(ApfloatMath.atan(zda.divide(yda)).add((new Apfloat(Phit)).multiply(new Apfloat(Math.signum(yda.doubleValue()))).multiply(new Apfloat(FatorZ))))).doubleValue()) - CorrecaoY;

                    Apfloat ProdutoEscalaroa = xoa.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(yoa.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zoa.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(FatorZ)));

                    Apfloat ProdutoEscalarda = xda.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(yda.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zda.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(FatorZ)));

                    if ((ApfloatMath.acos(ProdutoEscalaroa.divide(ApfloatMath.sqrt(xoa.multiply(xoa).add(yoa.multiply(yoa)).add(zoa.multiply(zoa))))).doubleValue() < AnguloVisao) && (ApfloatMath.acos(ProdutoEscalarda.divide(ApfloatMath.sqrt(xda.multiply(xda).add(yda.multiply(yda)).add(zda.multiply(zda))))).doubleValue() < AnguloVisao) && (ApfloatMath.min(new Apfloat(xi), ApfloatMath.min(new Apfloat(yi), ApfloatMath.min(new Apfloat(xf), new Apfloat(yf)))).doubleValue() > 0) && (ApfloatMath.max(new Apfloat(xi), ApfloatMath.max(new Apfloat(yi), ApfloatMath.max(new Apfloat(xf), new Apfloat(yf)))).doubleValue() < ApfloatMath.min(new Apfloat(TamanhoPlanoX), new Apfloat(TamanhoPlanoY)).doubleValue()))
                        comp.addLine(xi, yi, xf, yf, CorLinhas);
                    }
                }
            }

        for (int i = 0; i < EspacoPoligonosShapePreenchidos.length; i++)
            {
            Polygon Poligono = new Polygon();

            String [] Pontos = EspacoPoligonosShapePreenchidos[i].split(";");

            int ContadorPontos = 0;

            for (int j = 0; j < Pontos.length; j++)
                {
                String [] Coordenadas = Pontos[j].split(",");

                if (ApfloatFlag == 0)
                    {
                    double xp = (Double.parseDouble(Coordenadas[0]) - xt);
                    double yp = (Double.parseDouble(Coordenadas[1]) - yt);
                    double zp = (FatorZ * (-Double.parseDouble(Coordenadas[2]) - zt));

                    if ((Math.abs(xp) > ShiftCartesianoAnular) && (Math.abs(yp) > ShiftCartesianoAnular) && (Math.abs(zp) > ShiftCartesianoAnular))
                        {
                        int xpp;
                        int ypp;

                        xpp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(yp / xp) + Tetat)) - CorrecaoX;

                        if (Math.abs(xp) > Math.abs(yp))
                            ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.tan(Math.atan(zp / xp) + Phit)) - CorrecaoY;
                        else
                            ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.signum(yp) * FatorZ * Math.tan(Math.atan(zp / yp) + Math.signum(yp) * FatorZ * Phit)) - CorrecaoY;

                        double ProdutoEscalar = xp * Math.cos(-Tetat) * Math.cos(-Phit) + yp * Math.sin(-Tetat) * Math.cos(-Phit) + FatorZ * zp * Math.sin(-Phit);

                        if ((Math.acos(ProdutoEscalar / Math.sqrt(xp * xp + yp * yp + zp * zp)) < AnguloVisao) && ((Math.min(xpp, ypp) > 0) && (Math.max(xpp, ypp)) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                            {
                            ContadorPontos++;
                            Poligono.addPoint(xpp, ypp);
                            }
                        }
                    }
                else
                    {
                    Apfloat xpa = (new Apfloat(Double.parseDouble(Coordenadas[0]) - xt));
                    Apfloat ypa = (new Apfloat(Double.parseDouble(Coordenadas[1]) - yt));
                    Apfloat zpa = (new Apfloat(FatorZ * (-Double.parseDouble(Coordenadas[2]) - zt)));

                    if ((ApfloatMath.abs(xpa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(ypa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()) && (ApfloatMath.abs(zpa).doubleValue() > (new Apfloat(ShiftCartesianoAnular)).doubleValue()))
                        {
                        int xpp;
                        int ypp;

                        xpp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(ypa.divide(xpa).add(new Apfloat(Tetat))))).doubleValue()) - CorrecaoX;

                        if (ApfloatMath.abs(xpa).doubleValue() > ApfloatMath.abs(ypa).doubleValue())
                            ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(ApfloatMath.tan(ApfloatMath.atan(zpa.divide(xpa)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
                        else
                            ypp = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * (new Apfloat(DistanciaTela)).multiply(new Apfloat(Math.signum(ypa.doubleValue()))).multiply(new Apfloat(FatorZ)).multiply(ApfloatMath.tan(ApfloatMath.atan(zpa.divide(ypa)).add((new Apfloat(Phit)).multiply(new Apfloat(Math.signum(ypa.doubleValue()))).multiply(new Apfloat(FatorZ))))).doubleValue()) - CorrecaoY;

                        Apfloat ProdutoEscalara = xpa.multiply(ApfloatMath.cos(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit))).add(ypa.multiply(ApfloatMath.sin(new Apfloat(-Tetat))).multiply(ApfloatMath.cos(new Apfloat(-Phit)))).add(zpa.multiply(ApfloatMath.sin(new Apfloat(-Phit))).multiply(new Apfloat(FatorZ)));

                        if ((ApfloatMath.acos(ProdutoEscalara.divide(ApfloatMath.sqrt(xpa.multiply(xpa)).add(ypa.multiply(ypa)).add(zpa.multiply(zpa)))).doubleValue() < AnguloVisao) && ((Math.min(xpp, ypp) > 0) && (Math.max(xpp, ypp)) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
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
    }
