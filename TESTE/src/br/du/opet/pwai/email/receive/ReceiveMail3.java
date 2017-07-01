package br.du.opet.pwai.email.receive;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ReceiveMail3
{

    public static void main(String[] args)
    {
        String tSenha = obterSenha();
        if (tSenha.isEmpty())
            return;

        String tServidor = "imap.gmail.com";
        String tUsuario = "pwai.opet@gmail.com";

        Properties tPropriedades = new Properties();

        tPropriedades.setProperty("mail.store.protocol", "imaps");

        // Obtendo o objeto Session
        Session tSessao = Session.getInstance(tPropriedades, null);

        try
        {
            // Criando o POP3 Store e conectando com o servidor
            Store tStore = tSessao.getStore();
            tStore.connect(tServidor, tUsuario, tSenha);

            // Recupera a pasta padrão e lista os e-maila dela
            Folder tPasta = tStore.getFolder("[Gmail]");

            Folder[] tPastas = tPasta.list();

            for (Folder tFolder : tPastas)
            {
                System.out.println(tFolder.getFullName());
            }
        }
        catch (MessagingException tExcept)
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
