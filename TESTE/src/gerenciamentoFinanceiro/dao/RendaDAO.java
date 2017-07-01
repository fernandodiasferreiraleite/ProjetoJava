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
	
	 // Método para criar um cliente na base de dados (INSERT)
    Renda create(Renda pRenda);

    // Método para recuperar um cliente da base de dados (SELECT)
    Renda recovery(int pIdRenda);

    // Método para atualizar um cliente na base de dados (UPDATE)
    Renda update(Renda pRenda);

    // Método para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdRenda);

    // Método para pesquisar todos os clientes da base de dados
    List<Renda> search();

    // Método para pesquisar por nome todos os clientes da base de dados
    List<Renda> searchByDescRenda(String pDescRenda);


}
