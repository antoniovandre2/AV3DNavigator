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
 * Última atualização: 12-02-2023
 */

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

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

    public int TamanhoPlanoX = 500;
    public int TamanhoPlanoY = 500;
    public int MinTamanhoPlanoX = 300;
    public int MinTamanhoPlanoY = 300;
    public Color CorBackground = Color.BLACK;
    public Color CorLinhas = Color.WHITE;
    public int TamanhoEspacoLabelStatus = 300;
    public int TamanhoFonteLabelStatus = 12;
    public long DistanciaTela = 1; // Mínimo valor: 1.
    public String MensagemErroEspacoInvalido = "Entre com um arquivo de espaço válido.";

    // Variáveis de funcionamento interno.

    public int CorrecaoX = 10;
    public int CorrecaoY = 0;
    public int Sair = 0;
    public String Espaco;

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

    public static void main (String[] args) {AV3DNavigator mainc = new AV3DNavigator(); if (args.length == 0) System.out.println("Entre com um arquivo de espaço."); else mainc.mainrun(args[0]);}

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
        JLabel LabelStatus = new JLabel("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ". z = " + String.valueOf(z) + ".<br> Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br><br>Setas para strafe.<br><br>Barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");
        LabelStatus.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, TamanhoFonteLabelStatus));
        LabelStatus.setOpaque(true);
        LabelStatus.setLocation(5, TamanhoPlanoY + 5);
        FrameEspaco.add(LabelStatus);

        FrameEspaco.addKeyListener(new KeyListener() {
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

                if (keyCode == KeyEvent.VK_A) {x += 1;}

                if (keyCode == KeyEvent.VK_Z) {x -= 1;}

                if (keyCode == KeyEvent.VK_S) {y += 1;}

                if (keyCode == KeyEvent.VK_X) {y -= 1;}

                if (keyCode == KeyEvent.VK_D) {z += 1;}

                if (keyCode == KeyEvent.VK_C) {z -= 1;}

                if (keyCode == KeyEvent.VK_F) {Teta += 0.2;}

                if (keyCode == KeyEvent.VK_V) {Teta -= 0.2;}

                if (keyCode == KeyEvent.VK_G)
                    {if (Math.abs(Phi) < Math.PI - 0.2) Phi += 0.2;}

                if (keyCode == KeyEvent.VK_B)
                    {if (Math.abs(Phi) < Math.PI - 0.2) Phi -= 0.2;}

                if (keyCode == KeyEvent.VK_UP)
                    {
                    x += Math.cos(Phi) * Math.cos(-Teta);
                    y += Math.cos(Phi) * Math.sin(-Teta);
                    z += Math.sin(Phi);
                    }

                if (keyCode == KeyEvent.VK_DOWN)
                    {
                    x -= Math.cos(Phi) * Math.cos(-Teta);
                    y -= Math.cos(Phi) * Math.sin(-Teta);
                    z -= Math.sin(Phi);
                    }

                if (keyCode == KeyEvent.VK_LEFT)
                    {
                    x += Math.cos(Phi) * Math.cos(-Teta - Math.PI / 2);
                    y += Math.cos(Phi) * Math.sin(-Teta - Math.PI / 2);
                    z += Math.sin(Phi);
                    }

                if (keyCode == KeyEvent.VK_RIGHT)
                    {
                    x -= Math.cos(Phi) * Math.cos(-Teta - Math.PI / 2);
                    y -= Math.cos(Phi) * Math.sin(-Teta - Math.PI / 2);
                    z -= Math.sin(Phi);
                    }

                DesenharEspaco(comp);

                LabelStatus.setText("<html>x = " + String.valueOf(x) + ". y = " + String.valueOf(y) + ". z = " + String.valueOf(z) + ".<br> Teta = " + String.valueOf(Teta) + ". Phi = " + String.valueOf(Phi) + ".<br><br>\"A\" para incrementar x. \"Z\" para decrementar x.<br>\"S\" para incrementar y. \"X\" para decrementar y<br>\"D\" para incrementar z. \"C\" para decrementar z.<br>\"F\" para incrementar Teta. \"V\" para decrementar Teta.<br>\"G\" para incrementar Phi. \"B\" para decrementar Phi.<br><br>Setas para strafe.<br><br>Barra de espaços para resetar as variáveis.<br><br>ESC para sair.</html>");
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
                    }

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

            DesenharEspaco(comp);

            try {Thread.sleep(10);} catch(InterruptedException e) {}
            }

        System.exit(0);
        }

    public void DesenharEspaco(AV3DNavigator comp)
        {
        comp.clearLines();
        
        String [] EspacoLinhas = Espaco.split("\\|");
        
        for (int i = 0; i < EspacoLinhas.length; i++)
            {
            String [] Pontos = EspacoLinhas[i].split(";");

            String [] CoordenadasOrig = Pontos[0].split(",");
            String [] CoordenadasDest = Pontos[1].split(",");

            double xo = (Long.parseLong(CoordenadasOrig[0]) - xt);
            double xd = (Long.parseLong(CoordenadasDest[0]) - xt);
            double yo = (Long.parseLong(CoordenadasOrig[1]) - yt);
            double yd = (Long.parseLong(CoordenadasDest[1]) - yt);
            double zo = (Long.parseLong(CoordenadasOrig[2]) - zt);
            double zd = (Long.parseLong(CoordenadasDest[2]) - zt);

            int xi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (yo / xo + Math.tan(Tetat))) - CorrecaoX;

            int yi = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (zo / xo + Math.tan(Phit))) - CorrecaoY;

            int xf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (yd / xd + Math.tan(Tetat))) - CorrecaoX;

            int yf = (int) (Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 + Math.min(TamanhoPlanoX, TamanhoPlanoY) / 2 * DistanciaTela * (zd / xd + Math.tan(Phit))) - CorrecaoY;
    
            if ((xo * Math.cos(-Tetat) * Math.cos(Phit) + yo * Math.sin(-Tetat) * Math.cos(Phit) + zo * Math.sin(Phit) > DistanciaTela) && (xd * Math.cos(-Tetat) * Math.cos(Phit) + yd * Math.sin(-Tetat) * Math.cos(Phit) + zd * Math.sin(Phit) > DistanciaTela))
                comp.addLine(xi, yi, xf, yf, CorLinhas);
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
                        if (! AntonioVandre.NumeroInteiroLong(Coordenadas[k])) return "Erro";
                    }
                }

            return EspacoStr;
            } catch (IOException e) {return "Erro";}
        }
    }
