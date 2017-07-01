package gerenciamentoFinanceiro.dao;

import gerenciamentoFinanceiro.jdbc.dao.DespesaJdbcDAO;
import gerenciamentoFinanceiro.jdbc.dao.PessoaJdbcDAO;
import gerenciamentoFinanceiro.jdbc.dao.RendaJdbcDAO;
import gerenciamentoFinanceiro.jdbc.dao.TipoDespesaJdbcDAO;

public class DaoFactory {
	
	public static DespesaDAO getDespesaDAO()
    {
        return new DespesaJdbcDAO();
    }
	
	public static PessoaDAO getPessoaDAO(){
		
		return new PessoaJdbcDAO();
	}

	public static RendaDAO getRendaDAO(){
		
		return new RendaJdbcDAO();
	}
	
	public static TipoDespesaDAO getTipoDespesaDAO(){
		
		return new TipoDespesaJdbcDAO();
	}
	
}
