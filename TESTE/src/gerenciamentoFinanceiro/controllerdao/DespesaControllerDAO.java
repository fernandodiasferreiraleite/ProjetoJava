package gerenciamentoFinanceiro.controllerdao;

import java.util.List;

import gerenciamentoFinanceiro.jdbc.dao.DespesaJdbcDAO;
import gerenciamentoFinanceiro.model.Despesa;


public class DespesaControllerDAO {

	
	DespesaJdbcDAO despesajdbcDAO;
	
	public DespesaControllerDAO(){
		
		despesajdbcDAO = new DespesaJdbcDAO();
	}
	
	public Despesa cadastrar(Despesa pDespesa) {

		return despesajdbcDAO.create(pDespesa);

	}

	public List<Despesa> consultar() {

		return despesajdbcDAO.search();
	}

	public Despesa atualizar(Despesa pDespesa) {

		return despesajdbcDAO.update(pDespesa);
	}

	public Boolean excluir(int pidDespesa) {

		return despesajdbcDAO.delete(pidDespesa);
	}

	public Despesa pesquisar(int pidDespesa) {

		return despesajdbcDAO.recovery(pidDespesa);
	}

	public List<Despesa> pesquisar(String pNomeDespesa) {

		return despesajdbcDAO.searchByNomeDespesa(pNomeDespesa);

	}

}
