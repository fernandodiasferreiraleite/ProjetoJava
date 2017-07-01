package gerenciamentoFinanceiro.controller;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.RendaDAO;
import gerenciamentoFinanceiro.dto.RendaDTO;
import gerenciamentoFinanceiro.model.Renda;

public class RendaController {

	 // Método para criar um cliente
    public static RendaDTO cadastrar(Renda pRenda)
    {
        if (pRenda == null)
            return new RendaDTO(false, "Erro ao tentar cadastrar um cliente");

        // Chamando a camada de persistência
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.create(pRenda);

        // Verificando se houve erro de criação
        if (tRenda == null)
            return new RendaDTO(false, "Problemas na gravação do cliente");

        // Retornando o indicativo de sucesso com o objeto criado
        return new RendaDTO(true, "Renda gravado com sucesso", tRenda);
    }

    // Método para recuperar uma disciplina
    public static RendaDTO recuperar(int pidRenda)
    {
        // Lendo o objeto
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.recovery(pidRenda);

        // Verificando se houve erro de recuperação
        if (tRenda == null)
            return new RendaDTO(false, "Renda não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new RendaDTO(true, "Renda lido com sucesso", tRenda);
    }

    // Método para atualizar uma disciplina
    public static RendaDTO atualizar(Renda pRenda)
    {
        if (pRenda == null)
            return new RendaDTO(false, "Tentativa de atualizar cliente falhou");

        // Chamando a camada de persistência
        RendaDAO tDao = DaoFactory.getRendaDAO();
        Renda tRenda = tDao.update(pRenda);

        // Verificando se houve erro de atualização
        if (tRenda == null)
            return new RendaDTO(false, "Renda não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new RendaDTO(true, "Renda atualizado com sucesso", tRenda);
    }

    // Método para deletar um cliente
    public static RendaDTO remover(int pidRenda)
    {
        //  Verificando se houve erro de remoção
        RendaDAO tDao = DaoFactory.getRendaDAO();
        if (! tDao.delete(pidRenda))
            return new RendaDTO(false, "Renda não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new RendaDTO(true, "Renda removido com sucesso");
    }

    // Método para pesquisar todos os clientes
    public static RendaDTO pesquisar()
    {
        // Obtendo a lista de clientes
        RendaDAO tDao = DaoFactory.getRendaDAO();
        List<Renda> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new RendaDTO(false, "Lista de clientes vazia");

        // Retornando a lista obtida
        return new RendaDTO(true, "Lista de clientes recuperada", tLista);
    }

    // Método para pesquisar por nome todas os clientes
    public static RendaDTO pesquisarPorNome(String pDescRenda)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescRenda == null)
            return pesquisar();

        // Obtendo a lista de clientes
        RendaDAO tDao = DaoFactory.getRendaDAO();
        List<Renda> tLista = tDao.searchByDescRenda(pDescRenda);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new RendaDTO(false, "Nenhum registro encontrado com nome '" + pDescRenda + "'");

        // Retornando a lista obtida
        return new RendaDTO(true, "Lista de clientes recuperados", tLista);
    }

}
