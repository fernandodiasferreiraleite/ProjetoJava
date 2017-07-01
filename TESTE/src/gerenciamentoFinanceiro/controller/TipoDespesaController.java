package gerenciamentoFinanceiro.controller;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.TipoDespesaDAO;
import gerenciamentoFinanceiro.dto.TipoDespesaDTO;
import gerenciamentoFinanceiro.model.TipoDespesa;

public class TipoDespesaController {

	 // Método para criar um cliente
    public static TipoDespesaDTO cadastrar(TipoDespesa pTipoDespesa)
    {
        if (pTipoDespesa == null)
            return new TipoDespesaDTO(false, "Erro ao tentar cadastrar um cliente");

        // Chamando a camada de persistência
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        TipoDespesa tTipoDespesa = tDao.create(pTipoDespesa);

        // Verificando se houve erro de criação
        if (tTipoDespesa == null)
            return new TipoDespesaDTO(false, "Problemas na gravação do cliente");

        // Retornando o indicativo de sucesso com o objeto criado
        return new TipoDespesaDTO(true, "TipoDespesa gravado com sucesso", tTipoDespesa);
    }

    // Método para recuperar uma disciplina
    public static TipoDespesaDTO recuperar(int pidTipoDespesa)
    {
        // Lendo o objeto
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        TipoDespesa tTipoDespesa = tDao.recovery(pidTipoDespesa);

        // Verificando se houve erro de recuperação
        if (tTipoDespesa == null)
            return new TipoDespesaDTO(false, "TipoDespesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new TipoDespesaDTO(true, "TipoDespesa lido com sucesso", tTipoDespesa);
    }

    // Método para atualizar uma disciplina
    public static TipoDespesaDTO atualizar(TipoDespesa pTipoDespesa)
    {
        if (pTipoDespesa == null)
            return new TipoDespesaDTO(false, "Tentativa de atualizar cliente falhou");

        // Chamando a camada de persistência
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        TipoDespesa tTipoDespesa = tDao.update(pTipoDespesa);

        // Verificando se houve erro de atualização
        if (tTipoDespesa == null)
            return new TipoDespesaDTO(false, "TipoDespesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new TipoDespesaDTO(true, "TipoDespesa atualizado com sucesso", tTipoDespesa);
    }

    // Método para deletar um cliente
    public static TipoDespesaDTO remover(int pidTipoDespesa)
    {
        //  Verificando se houve erro de remoção
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        if (! tDao.delete(pidTipoDespesa))
            return new TipoDespesaDTO(false, "TipoDespesa não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new TipoDespesaDTO(true, "TipoDespesa removido com sucesso");
    }

    // Método para pesquisar todos os clientes
    public static TipoDespesaDTO pesquisar()
    {
        // Obtendo a lista de clientes
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        List<TipoDespesa> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new TipoDespesaDTO(false, "Lista de clientes vazia");

        // Retornando a lista obtida
        return new TipoDespesaDTO(true, "Lista de clientes recuperada", tLista);
    }

    // Método para pesquisar por nome todas os clientes
    public static TipoDespesaDTO pesquisarPorNome(String pNomeTipoDespesa)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pNomeTipoDespesa == null)
            return pesquisar();

        // Obtendo a lista de clientes
        TipoDespesaDAO tDao = DaoFactory.getTipoDespesaDAO();
        List<TipoDespesa> tLista = tDao.searchByNomeTipoDespesa(pNomeTipoDespesa);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new TipoDespesaDTO(false, "Nenhum registro encontrado com nome '" + pNomeTipoDespesa + "'");

        // Retornando a lista obtida
        return new TipoDespesaDTO(true, "Lista de clientes recuperados", tLista);
    }

}
