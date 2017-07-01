package gerenciamentoFinanceiro.dto;

import java.util.List;

import gerenciamentoFinanceiro.model.Despesa;


public class DespesaDTO extends AbstractDTO<Despesa> {
	
	/* Construtores da classe */
    public DespesaDTO(boolean pOk, String pMensagem)
    {
        super(pOk, pMensagem);
    }

    public DespesaDTO(boolean pOk, String pMensagem, Despesa pDespesa)
    {
        super(pOk, pMensagem, pDespesa);
    }

    public DespesaDTO(boolean pOk, String pMensagem, List<Despesa> pLista)
    {
        super(pOk, pMensagem, pLista);
    }

    /* Métodos de acesso */
    public Despesa getDespesa()
    {
        return getObjeto();
    }

    public void setDespesa(Despesa pDespesa)
    {
        setObjeto(pDespesa);
    }



}
