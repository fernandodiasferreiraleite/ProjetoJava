package gerenciamentoFinanceiro.controller;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.DespesaDAO;
import gerenciamentoFinanceiro.dto.DespesaDTO;
import gerenciamentoFinanceiro.model.Despesa;


public class DespesaController {

	 // Método para criar um cliente
    public static DespesaDTO cadastrar(Despesa pDespesa)
    {
        if (pDespesa == null)
            return new DespesaDTO(false, "Erro ao tentar cadastrar um cliente");

        // Chamando a camada de persistência
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        Despesa tDespesa = tDao.create(pDespesa);

        // Verificando se houve erro de criação
        if (tDespesa == null)
            return new DespesaDTO(false, "Problemas na gravação do cliente");

        // Retornando o indicativo de sucesso com o objeto criado
        return new DespesaDTO(true, "Despesa gravado com sucesso", tDespesa);
    }

    // Método para recuperar uma disciplina
    public static DespesaDTO recuperar(int pidDespesa)
    {
        // Lendo o objeto
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        Despesa tDespesa = tDao.recovery(pidDespesa);

        // Verificando se houve erro de recuperação
        if (tDespesa == null)
            return new DespesaDTO(false, "Despesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new DespesaDTO(true, "Despesa lido com sucesso", tDespesa);
    }

    // Método para atualizar uma disciplina
    public static DespesaDTO atualizar(Despesa pDespesa)
    {
        if (pDespesa == null)
            return new DespesaDTO(false, "Tentativa de atualizar cliente falhou");

        // Chamando a camada de persistência
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        Despesa tDespesa = tDao.update(pDespesa);

        // Verificando se houve erro de atualização
        if (tDespesa == null)
            return new DespesaDTO(false, "Despesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new DespesaDTO(true, "Despesa atualizado com sucesso", tDespesa);
    }

    // Método para deletar um cliente
    public static DespesaDTO remover(int pidDespesa)
    {
        //  Verificando se houve erro de remoção
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        if (! tDao.delete(pidDespesa))
            return new DespesaDTO(false, "Despesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new DespesaDTO(true, "Despesa removido com sucesso");
    }

    // Método para pesquisar todos os despesas
    public static DespesaDTO pesquisar()
    {
        // Obtendo a lista de despesas
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        List<Despesa> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new DespesaDTO(false, "Lista de despesas vazia");

        // Retornando a lista obtida
        return new DespesaDTO(true, "Lista de despesas recuperada", tLista);
    }

    // Método para pesquisar por nome todas os despesas
    public static DespesaDTO pesquisarPorNome(String pNomeDespesa)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pNomeDespesa == null)
            return pesquisar();

        // Obtendo a lista de despesas
        DespesaDAO tDao = DaoFactory.getDespesaDAO();
        List<Despesa> tLista = tDao.searchByNomeDespesa(pNomeDespesa);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new DespesaDTO(false, "Nenhum registro encontrado com nome '" + pNomeDespesa + "'");

        // Retornando a lista obtida
        return new DespesaDTO(true, "Lista de despesas recuperados", tLista);
    }

}
