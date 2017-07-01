package br.du.opet.pwai.email.send;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import gerenciamentoFinanceiro.util.ConfiguracaoEmail;

public class SendMail1
{

    public static void main(String[] args)
    {
        String tSenha = obterSenha();
        if (tSenha.isEmpty())
            return;

        String tServidor = ConfiguracaoEmail.getServidorEmail();
        String tPorta = "465";
        String tUsuario = "pwai.opet@gmail.com";

        String tDestino = "pwai.opet@gmail.com";
        String tOrigem = "pwai.opet@gmail.com";

        Properties tPropriedades = new Properties();
        tPropriedades.put("mail.smtp.host", tServidor);
        tPropriedades.put("mail.smtp.port", tPorta);
        tPropriedades.put("mail.smtp.user", tUsuario);
        tPropriedades.put("mail.smtp.auth", "true");
        tPropriedades.put("mail.smtp.debug", "true");
        tPropriedades.put("mail.smtp.socketFactory.port", tPorta);
        tPropriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        tPropriedades.put("mail.smtp.socketFactory.fallback", "false");

        // Obtendo o objeto Session
        Session tSessao = Session.getInstance(tPropriedades, new Autenticador(tUsuario, tSenha));

        try
        {
            // Criando a mensagem
            Message tMensagem = new MimeMessage(tSessao);

            // Configurando a mensagem.
            tMensagem.setFrom(new InternetAddress(tOrigem));
            tMensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tDestino));
            tMensagem.setSubject("Alo Mundo");
            tMensagem.setText("Meu primeiro e-mal enviado pelo Java");

            // Enviando a mensagem
            Transport.send(tMensagem);

            System.out.println("E-mail enviado com sucesso....");
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
