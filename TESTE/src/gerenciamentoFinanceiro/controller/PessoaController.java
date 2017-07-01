package gerenciamentoFinanceiro.controller;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.PessoaDAO;
import gerenciamentoFinanceiro.dto.PessoaDTO;
import gerenciamentoFinanceiro.model.Pessoa;

public class PessoaController {
	
	 // M�todo para criar um cliente
    public static PessoaDTO cadastrar(Pessoa pPessoa)
    {
        if (pPessoa == null)
            return new PessoaDTO(false, "Erro ao tentar cadastrar um cliente");

        // Chamando a camada de persist�ncia
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        Pessoa tPessoa = tDao.create(pPessoa);

        // Verificando se houve erro de cria��o
        if (tPessoa == null)
            return new PessoaDTO(false, "Problemas na grava��o do cliente");

        // Retornando o indicativo de sucesso com o objeto criado
        return new PessoaDTO(true, "Pessoa gravado com sucesso", tPessoa);
    }

    // M�todo para recuperar uma disciplina
    public static PessoaDTO recuperar(int pidPessoa)
    {
        // Lendo o objeto
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        Pessoa tPessoa = tDao.recovery(pidPessoa);

        // Verificando se houve erro de recupera��o
        if (tPessoa == null)
            return new PessoaDTO(false, "Pessoa n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new PessoaDTO(true, "Pessoa lido com sucesso", tPessoa);
    }

    // M�todo para atualizar uma disciplina
    public static PessoaDTO atualizar(Pessoa pPessoa)
    {
        if (pPessoa == null)
            return new PessoaDTO(false, "Tentativa de atualizar cliente falhou");

        // Chamando a camada de persist�ncia
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        Pessoa tPessoa = tDao.update(pPessoa);

        // Verificando se houve erro de atualiza��o
        if (tPessoa == null)
            return new PessoaDTO(false, "Pessoa n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new PessoaDTO(true, "Pessoa atualizado com sucesso", tPessoa);
    }

    // M�todo para deletar um cliente
    public static PessoaDTO remover(int pidPessoa)
    {
        //  Verificando se houve erro de remo��o
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        if (! tDao.delete(pidPessoa))
            return new PessoaDTO(false, "Pessoa n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new PessoaDTO(true, "Pessoa removido com sucesso");
    }

    // M�todo para pesquisar todos os clientes
    public static PessoaDTO pesquisar()
    {
        // Obtendo a lista de clientes
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        List<Pessoa> tLista = tDao.search();

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new PessoaDTO(false, "Lista de clientes vazia");

        // Retornando a lista obtida
        return new PessoaDTO(true, "Lista de clientes recuperada", tLista);
    }

    // M�todo para pesquisar por nome todas os clientes
    public static PessoaDTO pesquisarPorNome(String pNomePessoa)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pNomePessoa == null)
            return pesquisar();

        // Obtendo a lista de clientes
        PessoaDAO tDao = DaoFactory.getPessoaDAO();
        List<Pessoa> tLista = tDao.searchByNome(pNomePessoa);

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new PessoaDTO(false, "Nenhum registro encontrado com nome '" + pNomePessoa + "'");

        // Retornando a lista obtida
        return new PessoaDTO(true, "Lista de clientes recuperados", tLista);
    }


}
