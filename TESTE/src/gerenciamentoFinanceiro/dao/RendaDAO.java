/**
 * 
 */
package gerenciamentoFinanceiro.dao;

import java.util.List;

import gerenciamentoFinanceiro.model.Renda;


/**
 * @author elain
 *
 */
public interface RendaDAO {
	
	 // M�todo para criar um cliente na base de dados (INSERT)
    Renda create(Renda pRenda);

    // M�todo para recuperar um cliente da base de dados (SELECT)
    Renda recovery(int pIdRenda);

    // M�todo para atualizar um cliente na base de dados (UPDATE)
    Renda update(Renda pRenda);

    // M�todo para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdRenda);

    // M�todo para pesquisar todos os clientes da base de dados
    List<Renda> search();

    // M�todo para pesquisar por nome todos os clientes da base de dados
    List<Renda> searchByDescRenda(String pDescRenda);


}
