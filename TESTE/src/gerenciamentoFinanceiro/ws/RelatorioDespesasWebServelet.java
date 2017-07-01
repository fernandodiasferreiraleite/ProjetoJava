package gerenciamentoFinanceiro.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class Relatoriodespesaservlet
 */


@WebServlet("/RelatorioDespesas")
public class RelatorioDespesasWebServelet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public RelatorioDespesasWebServelet()
	    {
	        super();
	    }

	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
	    	
	        try
	        {
	            JasperReport jasperReport = JasperFactory.getRelacaoDespesas();

	            OutputStream tSaida = response.getOutputStream();

	            //response.setHeader("Content-Disposition", "inline, filename=Relatoriodespesas.pdf");
	            response.setContentType("application/pdf");



	            // Obtendo a lista de despesas
	            DespesaDTO tDto = DespesaController.pesquisar();
	            if (tDto.isOk())
	            {
	                List<Despesa> tLista = tDto.getLista();

	                // DataSource
	                JRDataSource dataSource = new JRBeanCollectionDataSource(tLista);

	                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,
	                                 dataSource);

	                // Gerando o relatório para envio
	                JasperExportManager.exportReportToPdfStream(jasperPrint,tSaida);

	                System.out.println("Done!");

	                tSaida.close();
	            }
	        }
	        catch (JRException tExcept)
	        {
	            throw new ServletException("Problemas na geração do relatórios geral dos despesas", tExcept);
	        }
	        //response.getWriter().append("Served at: ").append(request.getContextPath());
	    }

	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	     */

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
	        doGet(request, response);
	    }

}
