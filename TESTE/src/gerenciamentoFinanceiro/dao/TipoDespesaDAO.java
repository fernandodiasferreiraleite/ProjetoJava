package gerenciamentoFinanceiro.dao;

import java.util.List;

import gerenciamentoFinanceiro.model.TipoDespesa;


public interface TipoDespesaDAO
{
 // Método para criar um cliente na base de dados (INSERT)
    TipoDespesa create(TipoDespesa pTipoDespesa);

    // Método para recuperar um cliente da base de dados (SELECT)
    TipoDespesa recovery(int pIdTipoDespesa);

    // Método para atualizar um cliente na base de dados (UPDATE)
    TipoDespesa update(TipoDespesa pTipoDespesa);

    // Método para deletar um cliente na base de dados (DELETE)
    boolean delete(int pIdTipoDespesa);

    // Método para pesquisar todos os clientes da base de dados
    List<TipoDespesa> search();

    // Método para pesquisar por nome todos os clientes da base de dados
    List<TipoDespesa> searchByNomeTipoDespesa(String pDescTipoDespesa);

}
