/**
 *
 */
package gerenciamentoFinanceiro.model;

import java.io.Serializable;


/**
 * @author Lab. Desenvolvimento
 *
 */

public class Renda implements Serializable
{

   /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int         idRenda;
    private String      descRenda;
    private float       valor;
    private String      obs;
    private int      idPessoa;

    public Renda() {

    }

    /**
     * @return the idRenda
     */
    public int getIdRenda()
    {
        return idRenda;
    }

    /**
     * @param pIdRenda the idRenda to set
     */
    public void setIdRenda(int pIdRenda)
    {
        idRenda = pIdRenda;
    }

    /**
     * @return the descRenda
     */
    public String getDescRenda()
    {
        return descRenda;
    }

    /**
     * @param pDescRenda the descRenda to set
     */
    public void setDescRenda(String pDescRenda)
    {
        descRenda = pDescRenda;
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
     * @return the obs
     */
    public String getObs()
    {
        return obs;
    }

    /**
     * @param pObs the obs to set
     */
    public void setObs(String pObs)
    {
        obs = pObs;
    }

    /**
     * @return the idPessoa
     */

    public int getIdPessoa()
    {
        return idPessoa;
    }

    /**
     * @param pIdPessoa the idPessoa to set
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
        result = prime * result + getIdRenda();
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
        Renda other = (Renda) obj;
        if (getIdRenda() != other.getIdRenda())
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
        tBuilder.append(getIdRenda());
        tBuilder.append(", ");
        tBuilder.append(getDescRenda());
        tBuilder.append(", ");
        tBuilder.append(getValor());
        tBuilder.append(", ");
        tBuilder.append(getObs());
        tBuilder.append("]");
        return tBuilder.toString();
    }


}
