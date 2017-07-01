/**
 * 
 */
package gerenciamentoFinanceiro.dao;

import java.util.List;

import gerenciamentoFinanceiro.model.Pessoa;


/**
 * @author elain
 *
 */
public interface PessoaDAO {
	
	 // Método para criar um cliente na base de dados (INSERT)
    Pessoa create(Pessoa pPessoa);

    // Método para recuperar um cliente da base de dados (SELECT)
    Pessoa recovery(int pIdPessoa);

    // Método para atualizar um cliente na base de dados (UPDATE)
    Pessoa update(Pessoa pPessoa);

    // Método para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdPessoa);

    // Método para pesquisar todos os clientes da base de dados
    List<Pessoa> search();

    // Método para pesquisar por nome todos os clientes da base de dados
    List<Pessoa> searchByNome(String pNome);


}
