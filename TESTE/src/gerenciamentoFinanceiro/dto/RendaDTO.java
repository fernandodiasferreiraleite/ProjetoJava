package gerenciamentoFinanceiro.dto;

import java.util.List;

import gerenciamentoFinanceiro.model.Renda;


public class RendaDTO extends AbstractDTO<Renda> {
	
	/* Construtores da classe */
    public RendaDTO(boolean pOk, String pMensagem)
    {
        super(pOk, pMensagem);
    }

    public RendaDTO(boolean pOk, String pMensagem, Renda pRenda)
    {
        super(pOk, pMensagem, pRenda);
    }

    public RendaDTO(boolean pOk, String pMensagem, List<Renda> pLista)
    {
        super(pOk, pMensagem, pLista);
    }

    /* Métodos de acesso */
    public Renda getRenda()
    {
        return getObjeto();
    }

    public void setRenda(Renda pRenda)
    {
        setObjeto(pRenda);
    }



}
