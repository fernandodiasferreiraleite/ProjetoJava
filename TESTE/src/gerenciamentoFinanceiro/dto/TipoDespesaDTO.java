package gerenciamentoFinanceiro.dto;

import java.util.List;

import gerenciamentoFinanceiro.model.TipoDespesa;


public class TipoDespesaDTO extends AbstractDTO<TipoDespesa> {
	
	/* Construtores da classe */
    public TipoDespesaDTO(boolean pOk, String pMensagem)
    {
        super(pOk, pMensagem);
    }

    public TipoDespesaDTO(boolean pOk, String pMensagem, TipoDespesa pTipoDespesa)
    {
        super(pOk, pMensagem, pTipoDespesa);
    }

    public TipoDespesaDTO(boolean pOk, String pMensagem, List<TipoDespesa> pLista)
    {
        super(pOk, pMensagem, pLista);
    }

    /* Métodos de acesso */
    public TipoDespesa getTipoDespesa()
    {
        return getObjeto();
    }

    public void setTipoDespesa(TipoDespesa pTipoDespesa)
    {
        setObjeto(pTipoDespesa);
    }



}
