/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).
 * 
 * Software AV3DNavigator.
 * 
 * Dependências: AntonioVandre.
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
    public double AcrescimoLinear = 1; // Default: 1.
    public double AcrescimoAngular = 0.2; // Default: 0.2.

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
                    }

                if (keyCode == KeyEvent.VK_A) {x += AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_Z) {x -= AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_S) {y += AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_X) {y -= AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_D) {z += AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_C) {z -= AcrescimoLinear;}

                if (keyCode == KeyEvent.VK_F) {Teta += AcrescimoAngular;}

                if (keyCode == KeyEvent.VK_V) {Teta -= AcrescimoAngular;}

                if (keyCode == KeyEvent.VK_G)
                    {if (Math.abs(Phi) < Math.PI / 2 - AcrescimoAngular) Phi += AcrescimoAngular;}

                if (keyCode == KeyEvent.VK_B)
                    {if (Math.abs(Phi) < Math.PI / 2 - AcrescimoAngular) Phi -= AcrescimoAngular;}

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

                    FlagAlteracaoStatus = 1;
                    }

            Point reference = FrameEspaco.getLocationOnScreen();
            MouseX = MouseInfo.getPointerInfo().getLocation().x - reference.x;
            MouseY = MouseInfo.getPointerInfo().getLocation().y - reference.y;

            if (MouseDown == 0)
                {
                if (xt > x) xt -= 0.1;
                if (yt > y) yt -= 0.1;
                if (zt > z) zt -= 0.1;
                if (Tetat > Teta) Tetat -= 0.05;
                if (Phit > Phi) Phit -= 0.05;
                if (xt < x) xt += 0.1;
                if (yt < y) yt += 0.1;
                if (zt < z) zt += 0.1;
                if (Tetat < Teta) Tetat += 0.05;
                if (Phit < Phi) Phit += 0.05;
                }
            else
                {
                Teta = 2 * Math.PI * (MouseX - MouseXR) / TamanhoPlanoX + TetaR;
                Tetat = Teta;

                double Temp = Math.PI * (MouseY - MouseYR) / TamanhoPlanoY + PhiR;

                if (Math.abs(Temp) < Math.PI / 2) {Phi = Temp; Phit = Phi;}

                FlagAlteracaoStatus = 1;
                }

            DesenharEspaco(comp);

            if (FlagAlteracaoStatus == 1)
                {
                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ".<br>z = " + String.valueOf(z) + ".<br>Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br>Distância da tela:" + String.valueOf(DistanciaTela) + ".<br>Ângulo de visão = " + String.valueOf(AnguloVisao) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br>\"W\" para aumentar a distância da tela. \"Q\" para reduzir a distância da tela.<br>\"E\" para reduzir o fator redutor do ângulo de visão. \"R\" para aumentar o fator redutor do ângulo de visão.<br><br>Setas para strafe.<br><br>Mouse pode ser utilizado para movimentar.<br><br>Barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");

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
            double zo = FatorZ * (Double.parseDouble(CoordenadasOrig[2]) - zt);
            double zd = FatorZ * (Double.parseDouble(CoordenadasDest[2]) - zt);

            if ((xo != 0) && (xd != 0))
                {
                int xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(yo / xo) + Tetat))) - CorrecaoX;

                int yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(zo / xo) + Phit))) - CorrecaoY;

                int xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(yd / xd) + Tetat))) - CorrecaoX;

                int yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * Math.atan(Math.tan(Math.atan(zd / xd) + Phit))) - CorrecaoY;
        
                if (((xo * Math.cos(-Tetat) * Math.cos(-Phit) + yo * Math.sin(-Tetat) * Math.cos(-Phit) + zo * Math.sin(-Phit)) / Math.sqrt(xo * xo + yo * yo + zo * zo) < Math.cos(AnguloVisao)) && ((xd * Math.cos(-Tetat) * Math.cos(-Phit) + yd * Math.sin(-Tetat) * Math.cos(-Phit) + zd * Math.sin(-Phit)) / Math.sqrt(xd * xd + yd * yd + zd * zd) < Math.cos(AnguloVisao)))
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
