/**
 *
 */
package gerenciamentoFinanceiro.model;

import java.io.Serializable;



/**
 * @author Lab. Desenvolvimento
 *
 */
public class Despesa implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer         idDespesa;
    private int			idTipoDespesa;
    private String      nomeDespesa;
    private String        dataPagamento;
    private float       desconto;
    private float       valor;
    private int     	idPessoa;


    public Despesa() {
    		super();
    }




    public Despesa(Integer pIdDespesa, int pIdTipoDespesa, String pNomeDespesa, String pDataPagamento, float pDesconto, float pValor, int pIdPessoa)
    {
        super();
        idDespesa = pIdDespesa;
        idTipoDespesa = pIdTipoDespesa;
        nomeDespesa = pNomeDespesa;
        dataPagamento = pDataPagamento;
        desconto = pDesconto;
        valor = pValor;
        idPessoa = pIdPessoa;
    }




    /**
     * @return the idDespesa
     */

    public Integer getIdDespesa()
    {
        return idDespesa;
    }

    /**
     * @param pIdDespesa the idDespesa to set
     */
    public void setIdDespesa(Integer pIdDespesa)
    {
        idDespesa = pIdDespesa;
    }

    /**
     * @return the idTipoDespesa
     */

    public int getIdTipoDespesa()
    {
        return idTipoDespesa;
    }

    /**
     * @param pIdTipoDespesa the idTipoDespesa to set
     */
    public void setIdTipoDespesa(int pIdTipoDespesa)
    {
        idTipoDespesa = pIdTipoDespesa;
    }

    /**
     * @return the nomeDespesa
     */
    public String getNomeDespesa()
    {
        return nomeDespesa;
    }

    /**
     * @param pNomeDespesa the nomeDespesa to set
     */
    public void setNomeDespesa(String pNomeDespesa)
    {
        nomeDespesa = pNomeDespesa;
    }

    /**
     * @return the dataPagamento
     */

    public String getDataPagamento()
    {
        return dataPagamento;
    }

    /**
     * @param date the dataPagamento to set
     */


    /**
     * @return the desconto
     */
    public float getDesconto()
    {
        return desconto;
    }

    public void setDataPagamento(String pDate)
    {
        dataPagamento = pDate;
    }




    /**
     * @param pDesconto the desconto to set
     */
    public void setDesconto(float pDesconto)
    {
        desconto = pDesconto;
    }

    /**
     * @return the valor
     */

    public float getValor()
    {
        return valor;
    }

    /**
     * @param pValor the valor to set
     */
    public void setValor(float pValor)
    {
        valor = pValor;
    }

    /**
     * @return the idUsuario
     */
    public int getIdPessoa()
    {
        return idPessoa;
    }

    /**
     * @param pIdUsuario the idUsuario to set
     */
    public void setIdPessoa(int pIdPessoa)
    {
        idPessoa = pIdPessoa;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + getIdDespesa();
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Despesa other = (Despesa) obj;
        if (getIdDespesa() != other.getIdDespesa())
            return false;
        return true;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(getIdDespesa());
        tBuilder.append(", ");
        tBuilder.append(getIdTipoDespesa());
        tBuilder.append(", ");
        tBuilder.append(getNomeDespesa());
        tBuilder.append(", ");
        tBuilder.append(getDataPagamento());
        tBuilder.append(", ");
        tBuilder.append(getDesconto());
        tBuilder.append(", ");
        tBuilder.append(getValor());
        tBuilder.append("]");
        return tBuilder.toString();
    }




}
