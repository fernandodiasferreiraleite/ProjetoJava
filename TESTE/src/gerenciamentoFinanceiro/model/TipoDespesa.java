/**
 *
 */
package gerenciamentoFinanceiro.model;

import java.io.Serializable;

/**
 * @author Lab. Desenvolvimento
 *
 */
public class TipoDespesa implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;
    private int             idTipoDespesa;
    private String          nmTipoDespesa;
    
    public TipoDespesa()
    {

    }

    public int getIdTipoDespesa()
    {
        return idTipoDespesa;
    }


    public void setIdTipoDespesa(int pIdTipoDespesa)
    {
        idTipoDespesa = pIdTipoDespesa;
    }

    public String getNmTipoDespesa()
    {
        return nmTipoDespesa;
    }

    public void setNmTipoDespesa(String pNmTipoDespesa)
    {
        nmTipoDespesa = pNmTipoDespesa;
    }

    

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + getIdTipoDespesa();
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
        TipoDespesa other = (TipoDespesa) obj;
        if (getIdTipoDespesa() != other.getIdTipoDespesa())
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(getIdTipoDespesa());
        tBuilder.append(", ");
        tBuilder.append(getNmTipoDespesa());
        tBuilder.append("]");
        return tBuilder.toString();
    }


}
