package gerenciamentoFinanceiro.dao;

import java.util.List;

import gerenciamentoFinanceiro.model.TipoDespesa;


public interface TipoDespesaDAO
{
 // M�todo para criar um cliente na base de dados (INSERT)
    TipoDespesa create(TipoDespesa pTipoDespesa);

    // M�todo para recuperar um cliente da base de dados (SELECT)
    TipoDespesa recovery(int pIdTipoDespesa);

    // M�todo para atualizar um cliente na base de dados (UPDATE)
    TipoDespesa update(TipoDespesa pTipoDespesa);

    // M�todo para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdTipoDespesa);

    // M�todo para pesquisar todos os clientes da base de dados
    List<TipoDespesa> search();

    // M�todo para pesquisar por nome todos os clientes da base de dados
    List<TipoDespesa> searchByNomeTipoDespesa(String pDescTipoDespesa);

}
