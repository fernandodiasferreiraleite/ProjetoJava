package br.du.opet.pwai.email.receive;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.sun.mail.pop3.POP3SSLStore;

public class ReceiveMail2
{

    public static void main(String[] args)
    {
        String tSenha = obterSenha();
        if (tSenha.isEmpty())
            return;

        String tServidor = "pop.gmail.com";
        String tPorta = "995";
        String tUsuario = "pwai.opet@gmail.com";

        Properties tPropriedades = new Properties();

        tPropriedades.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        tPropriedades.setProperty("mail.pop3.socketFactory.fallback", "false");
        tPropriedades.setProperty("mail.pop3.port", tPorta);
        tPropriedades.setProperty("mail.pop3.socketFactory.port", tPorta);

        URLName tUrl = new URLName("pop3", tServidor, Integer.parseInt(tPorta), "", tUsuario, tSenha);

        // Obtendo o objeto Session
        Session tSessao = Session.getInstance(tPropriedades, null);

        try
        {
            // Criando o POP3 Store e conectando com o servidor
            Store tStore = new POP3SSLStore(tSessao, tUrl);
            tStore.connect();

            // Recupera a pasta padrão e lista os e-maila dela
            Folder tPasta = tStore.getFolder("INBOX");
            tPasta.open(Folder.READ_ONLY);
            Message[] tMensagens = tPasta.getMessages();

            for (Message tMensagem : tMensagens)
            {
                System.out.println("---------------------------------");
                System.out.println("Assunto: " + tMensagem.getSubject());
                System.out.println("Origem.: " + tMensagem.getFrom()[0]);
                System.out.println("Assunto: " + tMensagem.getSubject());
                System.out.println("Texto..: " + tMensagem.getContent().toString());
            }
        }
        catch (IOException | MessagingException tExcept)
        {
            throw new RuntimeException(tExcept);
        }
    }

    private static String obterSenha()
    {
        JPanel tPainel = new JPanel();
        JLabel tLabel = new JLabel("Senha : ");
        JPasswordField tSenha = new JPasswordField(10);
        tPainel.add(tLabel);
        tPainel.add(tSenha);
        String[] tOpcoes = new String[] { "OK", "Cancel" };
        int option = JOptionPane.showOptionDialog(null, tPainel, "Senha do e-mail",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, tOpcoes, tOpcoes[0]);
        if (option == JOptionPane.OK_OPTION)
        {
            char[] tSenhaCh = tSenha.getPassword();
            return new String(tSenhaCh);
        }

        return "";
    }

    private static class Autenticador extends Authenticator
    {
        private String mUsuario;
        private String mSenha;

        public Autenticador(String pUsuario, String pSenha)
        {
            super();
            mUsuario = pUsuario;
            mSenha = pSenha;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(mUsuario, mSenha);
        }
    }
}
