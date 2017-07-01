package gerenciamentoFinanceiro.pwai.email.send;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import gerenciamentoFinanceiro.jasper.JasperFactory;
import gerenciamentoFinanceiro.jdbc.Conexao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class RelatorioDespesas {
	
	
	public static void main(String[] args) throws JRException
	{
        InputStream tArqEntrada = RelatorioDespesas.class.getResourceAsStream("RelatorioDespesasBean.jrxml");

        Connection tConexao = Conexao.getConexao();

        // Compile jrxml file.
       JasperReport jasperReport = JasperFactory.getRelacaoDespesas();

       // Parameters for report
       Map<String, Object> parameters = new HashMap<String, Object>();

       // DataSource
       JRDataSource dataSource = new JREmptyDataSource();

       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
               parameters, tConexao);


       // Make sure the output directory exists.
       File outDir = new File("./jasperoutput");
       outDir.mkdirs();

       // Export to PDF.
       JasperExportManager.exportReportToPdfFile(jasperPrint,
               "./jasperoutput/StyledTextReport.pdf");

       System.out.println("Done!");
}

}


