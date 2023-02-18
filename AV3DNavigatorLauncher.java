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
 * Última atualização: 18-02-2023.
 */

import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;
 
import java.io.*;
import java.net.URL;

import java.lang.ProcessBuilder;

import AntonioVandre.*;

public class AV3DNavigatorLauncher
    {
    public static String VersaoLauncher = "10-02-2023";

    public static String URL3DNavigatorVersao = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigatorVersao.txt";

    public static String ArquivoAV3DNavigatorVersao = "AV3DNavigatorVersao.txt";

    public static String URLAV3DNavigator = "https://github.com/antoniovandre2/AV3DNavigator/raw/main/AV3DNavigator.jar";

    public static String ArquivoAV3DNavigator = "AV3DNavigator.jar";

    public static String MensagemErroAtualizar = "Erro ao atualizar o AV3DNavigator.";

    public static String MensagemErroExecutar = "Erro ao executar AV3DNavigator.";

    public static void main(String[] args)
        {
        int FlagSucessoDownloadNet = 1;

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
                    if (AntonioVandre.StringPresente(VersaoNet, "Estrutural"))
                        {
                        JFrame Frame = new JFrame("Nova versão estrutural.");
                        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        Frame.setPreferredSize(new Dimension(100, 50));
                        JLabel Label = new JLabel("<html>Nova versão estrutural disponível. Favor fazer download do pacote completo.</html>");
                        Label.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 12));
                        Label.setLocation(5, 5);
                        Frame.add(Label);
                        Frame.pack();
                        Frame.setVisible(true);
                        }

                    try
                        {
                        downloadUsingStream(URLAV3DNavigator, ArquivoAV3DNavigator);
                        downloadUsingStream(URL3DNavigatorVersao, ArquivoAV3DNavigatorVersao);
                        } catch (IOException e) {}
                    }
                }
            else
                try
                    {
                    downloadUsingStream(URLAV3DNavigator, ArquivoAV3DNavigator);
                    downloadUsingStream(URL3DNavigatorVersao, ArquivoAV3DNavigatorVersao);
                    } catch (IOException e) {}
            }

        try
            {
            String ArquivoEspaco = "";

            if (args.length != 0) ArquivoEspaco = args[0];

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", ArquivoAV3DNavigator, ArquivoEspaco);
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
