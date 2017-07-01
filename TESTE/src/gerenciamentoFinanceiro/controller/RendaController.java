package gerenciamentoFinanceiro.controller;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.RendaDAO;
import gerenciamentoFinanceiro.dto.RendaDTO;
import gerenciamentoFinanceiro.model.Renda;

public class RendaController {

	 // M�todo para criar um cliente
    public static RendaDTO cadastrar(Renda pRenda)
    {
        if (pRenda == null)
            return new RendaDTO(false, "Erro ao tentar cadastrar um cliente");

        // Chamando a camada de persist�ncia
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.create(pRenda);

        // Verificando se houve erro de cria��o
        if (tRenda == null)
            return new RendaDTO(false, "Problemas na grava��o do cliente");

        // Retornando o indicativo de sucesso com o objeto criado
        return new RendaDTO(true, "Renda gravado com sucesso", tRenda);
    }

    // M�todo para recuperar uma disciplina
    public static RendaDTO recuperar(int pidRenda)
    {
        // Lendo o objeto
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.recovery(pidRenda);

        // Verificando se houve erro de recupera��o
        if (tRenda == null)
            return new RendaDTO(false, "Renda n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new RendaDTO(true, "Renda lido com sucesso", tRenda);
    }

    // M�todo para atualizar uma disciplina
    public static RendaDTO atualizar(Renda pRenda)
    {
        if (pRenda == null)
            return new RendaDTO(false, "Tentativa de atualizar cliente falhou");

        // Chamando a camada de persist�ncia
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.update(pRenda);

        // Verificando se houve erro de atualiza��o
        if (tRenda == null)
            return new RendaDTO(false, "Renda n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new RendaDTO(true, "Renda atualizado com sucesso", tRenda);
    }

    // M�todo para deletar um cliente
    public static RendaDTO remover(int pidRenda)
    {
        //  Verificando se houve erro de remo��o
        RendaDAO tDao = DaoFactory.getRendaDAO();
        if (! tDao.delete(pidRenda))
            return new RendaDTO(false, "Renda n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new RendaDTO(true, "Renda removido com sucesso");
    }

    // M�todo para pesquisar todos os clientes
    public static RendaDTO pesquisar()
    {
        // Obtendo a lista de clientes
        RendaDAO tDao = DaoFactory.getRendaDAO();
        List<Renda> tLista = tDao.search();

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new RendaDTO(false, "Lista de clientes vazia");

        // Retornando a lista obtida
        return new RendaDTO(true, "Lista de clientes recuperada", tLista);
    }

    // M�todo para pesquisar por nome todas os clientes
    public static RendaDTO pesquisarPorNome(String pDescRenda)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescRenda == null)
            return pesquisar();

        // Obtendo a lista de clientes
        RendaDAO tDao = DaoFactory.getRendaDAO();
        List<Renda> tLista = tDao.searchByDescRenda(pDescRenda);

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new RendaDTO(false, "Nenhum registro encontrado com nome '" + pDescRenda + "'");

        // Retornando a lista obtida
        return new RendaDTO(true, "Lista de clientes recuperados", tLista);
    }

}
