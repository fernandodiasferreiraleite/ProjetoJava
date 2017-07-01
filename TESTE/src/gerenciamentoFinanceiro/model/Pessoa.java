/**
 *
 */
package gerenciamentoFinanceiro.model;

import java.io.Serializable;


/**
 * @author Lab. Desenvolvimento
 *
 */

public class Pessoa implements Serializable
{
     private static final long serialVersionUID = 1L;

    private int             idPessoa;
    private String          nome;
    private String          sexo;

    public Pessoa() {

    }


    public Pessoa(int pIdPessoa, String pNome, String pSexo)
    {
        // TODO Auto-generated constructor stub
        super();
        setIdPessoa(pIdPessoa);
        setNome(pNome);
        setSexo(pSexo);
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

    /**
     * @return the nome
     */

    public String getNome()
    {
        return nome;
    }

    /**
     * @param pNome the nome to set
     */
    public void setNome(String pNome)
    {
        nome = pNome;
    }

    /**
     * @return the dataNascimento
     */

    /**
     * @return the sexo
     */
    public String getSexo()
    {
        return sexo;
    }

    /**
     * @param pSexo the sexo to set
     */
    public void setSexo(String pSexo)
    {
        sexo = pSexo;
    }

    /**
     * @return the despesa
     */
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        // TODO Auto-generated method stub
        return super.clone();
    }

	@Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(idPessoa);
        tBuilder.append(", ");
        tBuilder.append(nome);
        tBuilder.append(", ");
        tBuilder.append(sexo);
        tBuilder.append("]");
        return tBuilder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + getIdPessoa();
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
        Pessoa other = (Pessoa) obj;
        if (getIdPessoa() != other.getIdPessoa())
            return false;
        return true;
    }

	public void setDataNascimento(java.util.Date date) {
		// TODO Auto-generated method stub

	}
}
