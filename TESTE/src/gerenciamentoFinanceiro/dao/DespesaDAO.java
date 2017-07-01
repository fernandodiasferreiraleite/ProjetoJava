/**
 * 
 */
package gerenciamentoFinanceiro.dao;

import java.util.List;

import gerenciamentoFinanceiro.model.Despesa;

/*
 * @author elain
 *
 */
public interface DespesaDAO {

	 // M�todo para criar um cliente na base de dados (INSERT)
    Despesa create(Despesa pDespesa);

    // M�todo para recuperar um cliente da base de dados (SELECT)
    Despesa recovery(int pIdDespesa);

    // M�todo para atualizar um cliente na base de dados (UPDATE)
    Despesa update(Despesa pDespesa);

    // M�todo para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdDespesa);

    // M�todo para pesquisar todos os clientes da base de dados
    List<Despesa> search();

    // M�todo para pesquisar por nome todos os clientes da base de dados
    List<Despesa> searchByNomeDespesa(String pDescDespesa);

}
