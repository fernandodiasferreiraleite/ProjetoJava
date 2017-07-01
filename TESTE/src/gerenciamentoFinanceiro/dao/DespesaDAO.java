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

	 // Método para criar um cliente na base de dados (INSERT)
    Despesa create(Despesa pDespesa);

    // Método para recuperar um cliente da base de dados (SELECT)
    Despesa recovery(int pIdDespesa);

    // Método para atualizar um cliente na base de dados (UPDATE)
    Despesa update(Despesa pDespesa);

    // Método para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdDespesa);

    // Método para pesquisar todos os clientes da base de dados
    List<Despesa> search();

    // Método para pesquisar por nome todos os clientes da base de dados
    List<Despesa> searchByNomeDespesa(String pDescDespesa);

}
