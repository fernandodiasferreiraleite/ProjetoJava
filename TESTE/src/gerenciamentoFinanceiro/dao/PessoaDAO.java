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
	
	 // M�todo para criar um cliente na base de dados (INSERT)
    Pessoa create(Pessoa pPessoa);

    // M�todo para recuperar um cliente da base de dados (SELECT)
    Pessoa recovery(int pIdPessoa);

    // M�todo para atualizar um cliente na base de dados (UPDATE)
    Pessoa update(Pessoa pPessoa);

    // M�todo para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdPessoa);

    // M�todo para pesquisar todos os clientes da base de dados
    List<Pessoa> search();

    // M�todo para pesquisar por nome todos os clientes da base de dados
    List<Pessoa> searchByNome(String pNome);


}
