package gerenciamentoFinanceiro.pwai.email.send;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import tNomeArquivo.tNomeArquivo;

public class SendMail2
{

    public static void main(String[] args)
    {
        String email=null;
        
        String tSenha = obterSenha();
        if (tSenha.isEmpty())
            return;

        String tServidor = "smtp.gmail.com";
        String tPorta = "465";
        String tUsuario = "fernandodiasferreiraleite@gmail.com";


        String tDestino = "fernandodiasferreiraleite@gmail.com";
        String tOrigem = "fernandodiasferreiraleite@gmail.com";

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
            tMensagem.setSubject("Relatorio de despesa");

            // Criando o e-mail com varias partes
            Multipart tMultiplasPartes = new MimeMultipart();

            // Criando a parte texto do e-mail e armazenando
            BodyPart tParteTexto = new MimeBodyPart();
            tParteTexto.setText("Veja o anexo para maiores detalhes");
            tMultiplasPartes.addBodyPart(tParteTexto);

            // Criando a parte que contém o arquivo em anexo
            
          
            
            
            
            BodyPart tParteArquivo = new MimeBodyPart();
            String tNomeArquivo = "arquivolocal.pdf";
            DataSource tArquivo = new FileDataSource(tNomeArquivo);
            tParteArquivo.setDataHandler(new DataHandler(tArquivo));
            tParteArquivo.setFileName(tNomeArquivo);
            tMultiplasPartes.addBodyPart(tParteArquivo);

            // Colocando na mesangem as partes
            tMensagem.setContent(tMultiplasPartes);

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
