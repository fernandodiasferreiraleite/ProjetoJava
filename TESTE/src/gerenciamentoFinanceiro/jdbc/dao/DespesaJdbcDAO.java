/**
 *
 */
package gerenciamentoFinanceiro.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gerenciamentoFinanceiro.dao.DespesaDAO;
import gerenciamentoFinanceiro.jdbc.Conexao;
import gerenciamentoFinanceiro.model.Despesa;
import gerenciamentoFinanceiro.util.ExceptionUtil;

/**
 * @author elain
 *
 */
public class DespesaJdbcDAO implements DespesaDAO{


  // Conex�o com o Oracle
	    private  Connection sConexao = Conexao.getConexao();

	    // M�todo para criar um despesa na base de dados (INSERT)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#create(br.edu.opet.model.Despesa)
	     */
	    @Override
	    public  Despesa create(Despesa pDespesa)
	    {
	        // Definindo o objeto despesa de retorno
	        Despesa tDespesa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "INSERT INTO DESPESA" +
	                                    " (ID_DESPESA, NOME_DESPESA, ID_TIPO_DESPESA,"
	                                    + " ID_PESSOA, DATA_PAGAMENTO, VALOR, DESCONTO)" +
	                                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_DESPESA"});

	            // Colocando os par�metros recebidos no comando JDBC
	            tComandoJDBC.setInt(1, pDespesa.getIdDespesa());
	            tComandoJDBC.setString(2, pDespesa.getNomeDespesa());
	            tComandoJDBC.setInt(3, pDespesa.getIdTipoDespesa());
	            tComandoJDBC.setInt(4, pDespesa.getIdPessoa());
	            tComandoJDBC.setString(5,pDespesa.getDataPagamento());
	            tComandoJDBC.setFloat(6, pDespesa.getValor());
	            tComandoJDBC.setFloat(7, pDespesa.getDesconto());

	            // Executando o comando de grava��o e salvando o n�mero de registros
	            // inclu�dos
	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            // Verificando se um registro foi inclu�do
	            if (tQtdeReg == 1)
	            {
	                // Copiando o despesa para o retorno
	                tDespesa = pDespesa;

	                // Recuperando o c�digo gerado pelo Oracle
	                ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
	                tRsChave.next();
	                tDespesa.setIdDespesa(tRsChave.getInt(1));
	            }

	            // Liberando os recursos JDBC
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de cria��o do despesa");
	        }

	        // Retornando o objeto Despesa
	        return tDespesa;
	    }

	    // M�todo para recuperar um despesa da base de dados (SELECT)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#recovery(int)
	     */
	    @Override
	    public  Despesa recovery(int pidDespesa)
	    {
	        // Definindo o objeto despesa de retorno
	        Despesa tDespesa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_DESPESA, NOME_DESPESA, ID_TIPO_DESPESA, ID_PESSOA, DATA_PAGAMENTO, VALOR" +
	                                    " FROM DESPESA" +
	                                    " WHERE ID_DESPESA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando o par�metro recebido no comando JDBC
	            tComandoJDBC.setInt(1, pidDespesa);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Verificando se um registro foi lido
	            if (tResultSet.next())
	            {
	                // Salvando o Despesa para retornar depois
	                tDespesa = carregarDespesa(tResultSet);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o do despesa");
	        }

	        // Retornando o objeto Despesa
	        return tDespesa;
	    }

	    // M�todo para atualizar um despesa na base de dados (UPDATE)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#update(br.edu.opet.model.Despesa)
	     */
	    @Override
	    public  Despesa update(Despesa pDespesa)
	    {
	        // Definindo o objeto despesa de retorno
	        Despesa tDespesa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "UPDATE DESPESA SET" +
	                                    " NOME_DESPESA = ?, ID_TIPO_DESPESA = ?, ID_PESSOA = ?,"
	                                    + " DATA_PAGAMENTO = ?, VALOR = ? DESCONTO = ?" +
	                                    " WHERE ID_DESPESA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando os par�metros recebidos no comando JDBC
	            tComandoJDBC.setString(1, pDespesa.getNomeDespesa());
	            tComandoJDBC.setInt(2, pDespesa.getIdTipoDespesa());
	            tComandoJDBC.setInt(3, pDespesa.getIdPessoa());
	            tComandoJDBC.setString(4, pDespesa.getDataPagamento());
	            tComandoJDBC.setFloat(5, pDespesa.getValor());
	            tComandoJDBC.setFloat(6, pDespesa.getDesconto());
	            tComandoJDBC.setInt(7, pDespesa.getIdDespesa());

	            // Executando o comando de regrava��o e salvando o n�mero de registros alterados
	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            // Verificando se um registro foi alterado
	            if (tQtdeReg == 1)
	            {
	                // Copiando o despesa para o retorno
	                tDespesa = pDespesa;
	            }

	            // Liberando os recursos JDBC
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de atualiza��o do despesa");
	        }

	        // Retornando o objeto Despesa
	        return tDespesa;
	    }

	    // M�todo para deletar um despesa na base de dados (DELETE)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#delete(int)
	     */
	    @Override
	    public  boolean delete(int pidDespesa)
	    {
	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "DELETE DESPESA " +
	                                    " WHERE ID_DESPESA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando o par�metro recebido no comando JDBC
	            tComandoJDBC.setInt(1, pidDespesa);

	            // Executando o comando de remo��o e salvando o n�mero de registros removidos
	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            // Verificando se um registro foi removido
	            if (tQtdeReg == 1)
	            {
	                // Indicado que a remo��o foi efetuado com sucesso
	                return true;
	            }

	            // Liberando os recursos JDBC
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de dele��o do despesa");
	        }

	        // Se chegou nesse ponto a remo��o n�o foi efetuada
	        return false;
	    }

	    // M�todo para pesquisar todos os despesas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#search()
	     */
	    @Override
	    public  List<Despesa> search()
	    {
	        // Criando a tLista de despesas vazia
	        List<Despesa> tLista = new ArrayList<>();

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_DESPESA, NOME_DESPESA, ID_TIPO_DESPESA, ID_PESSOA, DATA_PAGAMENTO, VALOR, DESCONTO" +
	                                    " FROM DESPESA " +
	                                    " ORDER BY UPPER(NOME_DESPESA)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Enquanto houver registros, processa
	            while (tResultSet.next())
	            {
	                // Salvando o Despesa retornado para adicionar na lista
	                Despesa tDespesa = carregarDespesa(tResultSet);

	                // Adicionando o despesa na tLista
	                tLista.add(tDespesa);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o da lista de despesas");
	        }

	        // Retornando a lista de despesas
	        return tLista;
	    }

	    // M�todo para pesquisar por nome todos os despesas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#searchByNomeDespesa(java.lang.String)
	     */
	    @Override
	    public  List<Despesa> searchByNomeDespesa(String pNomeDespesa)
	    {
	        // Acertando o crit�rio de pesquisa
	        String tNomeDespesaPesquisa = "%" + pNomeDespesa + "%";

	        // Criando a tLista de despesas vazia
	        List<Despesa> tLista = new ArrayList<>();

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_DESPESA, NOME_DESPESA, ID_TIPO_DESPESA, ID_PESSOA, DATA_PAGAMENTO, VALOR, DESCONTO" +
	                                    " FROM DESPESA" +
	                                    " WHERE UPPER(NOME_DESPESA) LIKE UPPER(?)" +
	                                    " ORDER BY UPPER(NOME_DESPESA)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando os par�metros recebidos no par�metros do comando JDBC
	            tComandoJDBC.setString(1, tNomeDespesaPesquisa);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Enquanto houver registros, processa
	            while (tResultSet.next())
	            {
	                // Salvando o Despesa retornado para adicionar na lista
	                Despesa tDespesa = carregarDespesa(tResultSet);

	                // Adicionando o despesa na tLista
	                tLista.add(tDespesa);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo da pesquisa por nome dos despesas");
	        }

	        // Retornando a lista de despesas
	        return tLista;
	    }

	    // M�todo para pesquisar por email todos os despesas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.DespesaDAO#searchByIdTipoDespesal(java.lang.String)
	     */


	    // M�todo para processar o ResultSet e retornar um Despesa
	    private  Despesa carregarDespesa(ResultSet tResultSet) throws SQLException
	    {
	        // Criando um novo despesa para armazenar as informa��es lidas
	        Despesa tDespesa = new Despesa();

	        // Recuperando as informa��es do ResultSet e colocando objeto criado
	        tDespesa.setIdDespesa(tResultSet.getInt("ID_DESPESA"));
	        tDespesa.setNomeDespesa(tResultSet.getString("NOME_DESPESA"));
	        tDespesa.setIdTipoDespesa(tResultSet.getInt("ID_TIPO_DESPESA"));
	        tDespesa.setIdPessoa(tResultSet.getInt("ID_PESSOA"));
	        tDespesa.setDataPagamento(tResultSet.getString("DATA_PAGAMENTO"));
	        tDespesa.setValor(tResultSet.getFloat("VALOR"));
	        tDespesa.setValor(tResultSet.getFloat("DESCONTO"));

	        // Retornando o despesa criado
	        return tDespesa;
	    }


}
