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
 * Última atualização: 15-02-2023
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

/*          Alta precisão com o Apfloat, porém com custo computacional.

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
*/

import AntonioVandre.*;

public class AV3DNavigator extends JComponent
    {
    public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";

    // Variáveis globais.

    public int TamanhoPlanoX = 400; // Default: 400.
    public int TamanhoPlanoY = 400; // Default: 400.
    public int MinTamanhoPlanoX = 300; // Default: 300.
    public int MinTamanhoPlanoY = 300; // Default: 300.
    public double AnguloVisao = Math.PI / 2; // Default: Math.PI / 2.
    public double FatorAnguloVisao = 1; // Default: 1.
    public Color CorBackground = Color.BLACK; // Default: Color.BLACK.
    public Color CorLinhas = Color.WHITE; // Default: Color.WHITE.
    public int TamanhoEspacoLabelStatus = 360; // Default: 320.
    public int TamanhoFonteLabelStatus = 10; // Default: 11.
    public long DistanciaTela = 2; // Default: valor inicial: 2.
    public static String MensagemErroEspacoAusente = "Entre com um arquivo de espaço.";
    public String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";
    public double FatorMouseWheel = 3; // Default: 3.
    public double DeslocamentoLinear = 1; // Default: 1.
    public double DeslocamentoAngular = 0.2; // Default: 0.2.
    public int FramesDeslocamento = 4; // Default: 5.

    // Variáveis de funcionamento interno.

    public int CorrecaoX = 10;
    public int CorrecaoY = 0;
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

    private static class Line
        {
        final int x1; 
        final int y1;
        final int x2;
        final int y2;   
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color)
            {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            }               
        }

    private final LinkedList<Line> lines = new LinkedList<Line>();

    public void addLine(int x1, int x2, int x3, int x4)
        {
        addLine(x1, x2, x3, x4, Color.black);
        }

    public void addLine(int x1, int x2, int x3, int x4, Color color)
        {
        lines.add(new Line(x1,x2,x3,x4, color));        
        repaint();
        }

    public void clearLines()
        {
        lines.clear();
        repaint();
        }

    @Override
    protected void paintComponent(Graphics g)
        {
        super.paintComponent(g);

        for (Line line : lines)
            {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
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
        FrameEspaco.setIconImage(new ImageIcon(getClass().getResource("AV3DNavigator - Logo.png")).getImage());
        FrameEspaco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FrameEspaco.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY + TamanhoEspacoLabelStatus));
        FrameEspaco.getContentPane().setBackground(CorBackground);
        AV3DNavigator comp = new AV3DNavigator();
        comp.setPreferredSize(new Dimension(TamanhoPlanoX, TamanhoPlanoY));
        FrameEspaco.getContentPane().add(comp, BorderLayout.PAGE_START);
        JLabel LabelStatus = new JLabel("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br>Distância da tela:" + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir a distância da tela.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar o fator redutor do ângulo de visão.<br><br>Setas para strafe.<br><br>Mouse pode ser utilizado para movimentar.<br><br>Barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");
        LabelStatus.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelStatus));
        LabelStatus.setOpaque(true);
        LabelStatus.setLocation(5, TamanhoPlanoY + 5);
        FrameEspaco.add(LabelStatus);

        FrameEspaco.addMouseListener(new MouseListener()
            {
            public void mousePressed(MouseEvent MouseEvento)
                {MouseXR = MouseX; MouseYR = MouseY; TetaR = Teta; PhiR = Phi; MouseDown = 1;}

            public void mouseClicked(MouseEvent MouseEvento) {}
            public void mouseEntered(MouseEvent MouseEvento) {}
            public void mouseExited(MouseEvent MouseEvento) {}
            public void mouseReleased(MouseEvent MouseEvento) {MouseDown = 0;}
            public void mouseDragged(MouseEvent MouseEvento) {}
            public void mouseMoved(MouseEvent MouseEvento) {}
            });

        FrameEspaco.addMouseWheelListener(e -> {
            x += FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.cos(-Teta);

            y += FatorMouseWheel * e.getWheelRotation() * Math.cos(-Phi) * Math.sin(-Teta);

            z += FatorMouseWheel * e.getWheelRotation() * Math.sin(-Phi);

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
                    {if (Math.abs(Phi) < Math.PI / 2 - DeslocamentoAngular) Phi += DeslocamentoAngular;}

                if (keyCode == KeyEvent.VK_B)
                    {if (Math.abs(Phi) < Math.PI / 2 - DeslocamentoAngular) Phi -= DeslocamentoAngular;}

                if (keyCode == KeyEvent.VK_Q)
                    {if (DistanciaTela > 1) DistanciaTela -= 1;}

                if (keyCode == KeyEvent.VK_W)
                    DistanciaTela += 1;

                if (keyCode == KeyEvent.VK_E)
                    {if (FatorAnguloVisao > 1) {FatorAnguloVisao -= 1; AnguloVisao = Math.PI / (2 * FatorAnguloVisao);}}

                if (keyCode == KeyEvent.VK_R)
                    {FatorAnguloVisao += 1; AnguloVisao = Math.PI / (2 * FatorAnguloVisao);}

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
                    x -= Math.cos(-Teta - Math.PI / 2);
                    y -= Math.sin(-Teta - Math.PI / 2);
                    }

                if (keyCode == KeyEvent.VK_RIGHT)
                    {
                    x += Math.cos(-Teta - Math.PI / 2);
                    y += Math.sin(-Teta - Math.PI / 2);
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

                double Temp = Math.PI * (MouseY - MouseYR) / TamanhoPlanoY + PhiR;

                if (Math.abs(Temp) < Math.PI / 2) {Phi = Temp; Phit = Phi;}

                FlagAlteracaoStatus = 1;
                }

            if (FlagAlteracaoStatus == 1)
                {
                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br>Distância da tela:" + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir a distância da tela.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar o fator redutor do ângulo de visão.<br><br>Setas para strafe.<br><br>Mouse pode ser utilizado para movimentar.<br><br>Barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");

                DesenharEspaco(comp);

                FlagAlteracaoStatus = 0;
                }

            try {Thread.sleep(10);} catch(InterruptedException e) {}
            }

        System.exit(0);
        }

    public void DesenharEspaco(AV3DNavigator comp)
        {
        int FatorZ = 1;

        if (Math.cos(-Teta) < 0) FatorZ = -1;
        
        String [] EspacoLinhas = Espaco.split("\\|");

        comp.clearLines();

        for (int i = 0; i < EspacoLinhas.length; i++)
            {
            String [] Pontos = EspacoLinhas[i].split(";");

            String [] CoordenadasOrig = Pontos[0].split(",");
            String [] CoordenadasDest = Pontos[1].split(",");

            double xo = (-Double.parseDouble(CoordenadasOrig[0]) - xt);
            double xd = (-Double.parseDouble(CoordenadasDest[0]) - xt);
            double yo = (Double.parseDouble(CoordenadasOrig[1]) - yt);
            double yd = (Double.parseDouble(CoordenadasDest[1]) - yt);
            double zo = (FatorZ * (Double.parseDouble(CoordenadasOrig[2]) - zt));
            double zd = (FatorZ * (Double.parseDouble(CoordenadasDest[2]) - zt));

/*          Alta precisão com o Apfloat, porém com custo computacional.

            Apfloat xoa = new Apfloat(-Double.parseDouble(CoordenadasOrig[0]) - xt);

            Apfloat xda = new Apfloat(-Double.parseDouble(CoordenadasDest[0]) - xt);

            Apfloat yoa = new Apfloat(Double.parseDouble(CoordenadasOrig[1]) - yt);

            Apfloat yda = new Apfloat(Double.parseDouble(CoordenadasDest[1]) - yt);

            Apfloat zoa = new Apfloat(FatorZ * (Double.parseDouble(CoordenadasOrig[2]) - zt));

            Apfloat zda = new Apfloat(FatorZ * (Double.parseDouble(CoordenadasDest[2]) - zt));

            double xo = xoa.doubleValue();
            double xd = xda.doubleValue();
            double yo = yoa.doubleValue();
            double yd = yda.doubleValue();
            double zo = zoa.doubleValue();
            double zd = zda.doubleValue();
*/

            if ((xo != 0) && (xd != 0) && (yo != 0) && (yd != 0) && (zo != 0) && (zd != 0))
                {
                int xi;
                int yi;
                int xf;
                int yf;

                if (Math.abs(xo) > Math.abs(yo))
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(yo / xo) + Tetat))) - CorrecaoX;
                else
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.PI / 2 - Math.atan(xo / yo) + Tetat))) - CorrecaoX;

                if (Math.abs(xo) > Math.abs(zo))
                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(zo / xo) + Phit))) - CorrecaoY;
                else
                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.PI / 2 - Math.atan(xo / zo) + Phit))) - CorrecaoY;

                if (Math.abs(xd) > Math.abs(yd))
                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(yd / xd) + Tetat))) - CorrecaoX;
                else
                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.PI / 2 - Math.atan(xd / yd) + Tetat))) - CorrecaoX;

                if (Math.abs(xd) > Math.abs(zd))
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(zd / xd) + Phit))) - CorrecaoY;
                else
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.PI / 2 - Math.atan(xd / zd) + Phit))) - CorrecaoY;

/*              Alta precisão com o Apfloat, porém com custo computacional.

                if (Math.abs(xo) > Math.abs(yo))
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(yoa.divide(xoa)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;
                else
                    xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(xoa.divide(yoa)).multiply(new Apfloat(-1)).add(new Apfloat(Math.PI / 2)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;

                if (Math.abs(xo) > Math.abs(zo))
                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(zoa.divide(xoa)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
                else
                    yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(xoa.divide(zoa)).multiply(new Apfloat(-1)).add(new Apfloat(Math.PI / 2)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;

                if (Math.abs(xd) > Math.abs(yd))
                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(yda.divide(xda)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;
                else
                    xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(xda.divide(yda)).multiply(new Apfloat(-1)).add(new Apfloat(Math.PI / 2)).add(new Apfloat(Tetat)))).doubleValue()) - CorrecaoX;

                if (Math.abs(xd) > Math.abs(zd))
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(zda.divide(xda)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
                else
                    yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * ApfloatMath.atan(ApfloatMath.tan(ApfloatMath.atan(xda.divide(zda)).multiply(new Apfloat(-1)).add(new Apfloat(Math.PI / 2)).add(new Apfloat(Phit)))).doubleValue()) - CorrecaoY;
*/

                if (((xo * Math.cos(-Tetat) * Math.cos(-Phit) + yo * Math.sin(-Tetat) * Math.cos(-Phit) + zo * Math.sin(-Phit)) / Math.sqrt(xo * xo + yo * yo + zo * zo) < Math.cos(AnguloVisao)) && ((xd * Math.cos(-Tetat) * Math.cos(-Phit) + yd * Math.sin(-Tetat) * Math.cos(-Phit) + zd * Math.sin(-Phit)) / Math.sqrt(xd * xd + yd * yd + zd * zd) < Math.cos(AnguloVisao)) && (Math.min(xi, Math.min(yi, Math.min(xf, yf))) > 0) && (Math.max(xi, Math.max(yi, Math.max(xf, yf))) < Math.min(TamanhoPlanoX, TamanhoPlanoY)))
                    comp.addLine(xi, yi, xf, yf, CorLinhas);

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

            String [] EspacoLinhas = EspacoStr.split("\\|");

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

            return EspacoStr;
            } catch (IOException e) {return "Erro";}
        }
    }
