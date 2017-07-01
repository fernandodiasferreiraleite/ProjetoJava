/**
 *
 */
package gerenciamentoFinanceiro.view;

import java.io.File;
import java.util.List;

import gerenciamentoFinanceiro.controller.DespesaController;
import gerenciamentoFinanceiro.dto.DespesaDTO;
import gerenciamentoFinanceiro.jasper.JasperFactory;
import gerenciamentoFinanceiro.model.Despesa;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * @author elain
 *
 */
public class RelatoriosDespesasBean {

	    public static void main(String[] args) throws JRException
	    {
	        JasperReport jasperReport = JasperFactory.getRelacaoDespesas();

	        // Parameters for report
	        //Map<String, Object> parameters = new HashMap<String, Object>();
	        //parameters.put("FACULDADE", "Faculdades OPET");

	        // Obtendo a lista de alunos
	        DespesaDTO tDto = DespesaController.pesquisar();
	        if (tDto.isOk())
	        {
	            List<Despesa> tLista = tDto.getLista();

	            // DataSource
	            JRDataSource dataSource = new JRBeanCollectionDataSource(tLista);

	            System.out.println();
	            System.out.println("Preenchendo lista geral");
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,
	                             dataSource);

	            // Make sure the output directory exists.
	            File outDir = new File("./jasperoutput");
	            outDir.mkdirs();

	            // Export to PDF.
	            System.out.println();
	            System.out.println("Exportando lista geral");
	            JasperExportManager.exportReportToPdfFile(jasperPrint,
	                            "./jasperoutput/RelacaoDespesasGeral.pdf");

	            System.out.println("Done!");
	        }

	        // Obtendo a lista de alunos
	        tDto = DespesaController.pesquisarPorNome("despesa");
	        if (tDto.isOk())
	        {
	            List<Despesa> tLista = tDto.getLista();

	            // DataSource
	            JRDataSource dataSource = new JRBeanCollectionDataSource(tLista);

	            System.out.println();
	            System.out.println("Preenchendo lista por nome");
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,
	                             dataSource);

	            // Make sure the output directory exists.
	            File outDir = new File("./jasperoutput");
	            outDir.mkdirs();

	            // Export to PDF.
	            System.out.println();
	            System.out.println("Exportando lista por nome");
	            JasperExportManager.exportReportToPdfFile(jasperPrint,
	                            "./jasperoutput/RelacaoDespesasNome.pdf");

	            System.out.println("Done!");
	        }


	}


}
