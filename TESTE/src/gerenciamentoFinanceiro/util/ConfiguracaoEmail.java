package gerenciamentoFinanceiro.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConfiguracaoEmail
{
    private static String sServidorEmail;

    // C�digo de inicializa��o de classe
    static
    {
        // Carregando o arquivo de configura��o do JDBC para as propriedades
        InputStream tArqEntrada = ConfiguracaoEmail.class.getResourceAsStream("email.properties");

        if (tArqEntrada == null)
            throw new RuntimeException("Arquivo de configura��o 'email.properties' n�o existe no diret�rio do pacote");

        try
        {
            Properties tPropriedades = new Properties();
            tPropriedades.load(tArqEntrada);
            tArqEntrada.close();

            // Recuperando as propriedades do arquivo e validando se est�o ok
            sServidorEmail = tPropriedades.getProperty("servidorEmail");
            if (sServidorEmail == null || sServidorEmail.isEmpty())
                throw new InvalidPropertiesFormatException("Propriedade 'servidorEmail' n�o existe ou em branco no arquivo 'email.properties'");
        }
        catch (IOException tExcept)
        {
            throw new RuntimeException("Problemas na leitura do arquivo 'email.properties'", tExcept);
        }

    }

    public static String getServidorEmail()
    {
        return sServidorEmail;
    }

}
