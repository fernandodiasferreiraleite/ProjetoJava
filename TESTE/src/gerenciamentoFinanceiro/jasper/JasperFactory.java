package gerenciamentoFinanceiro.jasper;

import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class JasperFactory
{
    private static JasperReport sRelacaoDespesas;

    public static JasperReport getRelacaoDespesas() throws JRException
    {
        if (sRelacaoDespesas == null)
        {
            try
            {
                // Abrindo o arquivo JRXML
                InputStream tArqEntrada = JasperFactory.class.getResourceAsStream("RelatorioDespesasBean.jrxml");

                // Compilando o arquivo JRXML
                sRelacaoDespesas = JasperCompileManager.compileReport(tArqEntrada);

                // Fechando o arquivo JRXML
                tArqEntrada.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Retornando o relatório compilado
        return sRelacaoDespesas;
    }
}
