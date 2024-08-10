/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes (bit.ly/antoniovandre_legadoontologico).
 * 
 * Launcher do software AV3DNavigator.
 * 
 * Dependências: AntonioVandre.
 * 
 * Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
 * 
 * Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).
 * 
 * Última atualização: 10-08-2024.
 */

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BorderLayout;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.net.URL;

import java.lang.ProcessBuilder;
import java.lang.ProcessBuilder.Redirect;

import java.io.*;

public class AV3DNavigatorLauncher
    {
    public static String VersaoLauncher = "10-08-2024";

    public static String URL3DNavigatorVersao = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigatorVersao.txt";

    public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";

    public static String URLAV3DNavigator = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigator.jar";

    public static String ArquivoAV3DNavigator = "AV3DNavigator.jar";

    public static String URL3DNavigatorURL = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigatorURL.txt";

    public static String ArquivoAV3DNavigatorURL = "AV3DNavigatorURL.txt";

    public static String URL3DNavigatorAtribuicao = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigatorAtribuicao.txt";

    public static String URLapfloat = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/apfloat.jar";

    public static String URLmXparser = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/MathParser.org-mXparser.jar";

    public static String ArquivoAV3DNavigatorAtribuicao = "AV3DNavigatorAtribuicao.txt";

    public static String Arquivoapfloat = "apfloat.jar";

    public static String ArquivomXparser = "MathParser.org-mXparser.jar";

    public static String MensagemErroAtualizar = "Erro ao atualizar o AV3DNavigator.";

    public static String MensagemErroExecutar = "Erro ao executar AV3DNavigator. Pode ser perda de conexão com a internet.";

	public class SplashScreen {

		public static final JWindow SPLASH_SCREEN;

		static {SPLASH_SCREEN = new JWindow();}

		public static void show() {
			BufferedImage Img;
			try {Img = ImageIO.read(AV3DNavigatorLauncher.class.getResourceAsStream("AV3DNavigator - SplashScreen.png"));} catch (IOException e) {return;}
			Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
			SPLASH_SCREEN.setBounds((Screen.width - Img.getWidth()) / 2, (Screen.height - Img.getHeight()) / 2, Img.getWidth(), Img.getHeight());
			SPLASH_SCREEN.setBackground(new Color(0, 0, 0, 0));
			SPLASH_SCREEN.add(new JLabel(new ImageIcon(Img)) , BorderLayout.CENTER);
			SPLASH_SCREEN.setVisible(true);
		}

		public static void close() {
			SPLASH_SCREEN.dispose();
		}
	}

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

    public static void main (String[] args) {AV3DNavigatorLauncher mainc = new AV3DNavigatorLauncher(); mainc.mainrun(args);}

    public void mainrun(String[] args)
        {
        int FlagSucessoDownloadNet = 1;

		SplashScreen.show();

        try
            {
            downloadUsingStream(URL3DNavigatorVersao, ArquivoAV3DNavigatorVersao + ".tmp");
            } catch (IOException e) {FlagSucessoDownloadNet = 0;}

        if (FlagSucessoDownloadNet == 1)
            {
            File file = new File(ArquivoAV3DNavigatorVersao);
            int FlagSucessoVersaoLocal = 1;
            String VersaoLocal = "";

            try
                {
                BufferedReader br = new BufferedReader(new FileReader(file));
                VersaoLocal = br.readLine();
                } catch (IOException e) {FlagSucessoVersaoLocal = 0;}

            File fileNet = new File(ArquivoAV3DNavigatorVersao + ".tmp");
            int FlagSucessoVersaoNet = 1;
            String VersaoNet = "";

            try
                {
                BufferedReader brNet = new BufferedReader(new FileReader(fileNet));
                VersaoNet = brNet.readLine();
                } catch (IOException e) {FlagSucessoVersaoNet = 0;}

            if ((FlagSucessoVersaoLocal == 1) && (FlagSucessoVersaoNet == 1))
                {
                if (! (VersaoNet.equals(VersaoLocal)))
                    {
                    try
                        {
                        downloadUsingStream(URLAV3DNavigator, ArquivoAV3DNavigator);
                        downloadUsingStream(URL3DNavigatorVersao, ArquivoAV3DNavigatorVersao);
						downloadUsingStream(URLapfloat, Arquivoapfloat);
						downloadUsingStream(URLmXparser, ArquivomXparser);
                        } catch (IOException e) {}

                    JFrame Frame = new JFrame("Nova versão.");
                    Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    Frame.setPreferredSize(new Dimension(320, 130));
                    GradientLabel Label = new GradientLabel("<html>O software jar foi atualizado, entretanto podem haver atualizações estruturais; assim sendo, pode ser necessário fazer o download de um novo pacote completo.</html>", Color.BLUE, Color.BLACK, Color.WHITE);
                    Label.setBorder(new EmptyBorder(5, 5, 5, 5));
                    Label.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 12));
                    Frame.add(Label);
                    Frame.pack();
                    Frame.setVisible(true);
                    }
                }
            else
                try
                    {
                    downloadUsingStream(URLAV3DNavigator, ArquivoAV3DNavigator);
                    downloadUsingStream(URL3DNavigatorVersao, ArquivoAV3DNavigatorVersao);
					downloadUsingStream(URLapfloat, Arquivoapfloat);
					downloadUsingStream(URLmXparser, ArquivomXparser);
                    } catch (IOException e) {}
            }

        try
            {
            String ArquivoEspaco = "";

            if (args.length != 0) ArquivoEspaco = args[0];

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", ArquivoAV3DNavigator, ArquivoEspaco);
			pb.redirectOutput(Redirect.DISCARD);
			pb.redirectError(Redirect.DISCARD);
			SplashScreen.close();
            Process p = pb.start();
            } catch (IOException e) {System.out.println(MensagemErroExecutar);}
        }

    private static void downloadUsingStream(String urlStr, String file) throws IOException
        {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        }
}
