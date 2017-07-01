package gerenciamentoFinanceiro.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gerenciamentoFinanceiro.dao.RendaDAO;
import gerenciamentoFinanceiro.jdbc.Conexao;
import gerenciamentoFinanceiro.model.Renda;
import gerenciamentoFinanceiro.util.ExceptionUtil;

public class RendaJdbcDAO implements RendaDAO{

	/**
	 * 
	 */
	
	  // Conex�o com o Oracle
	
		    private  Connection sConexao = Conexao.getConexao();

	
		    // M�todo para criar um renda na base de dados (INSERT)
		    @Override
		    public  Renda create(Renda pRenda)
		    {
		        // Definindo o objeto renda de retorno
		        Renda tRenda = null;

		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "INSERT INTO RENDA" +
		                                    " (ID_RENDA, DESC_RENDA,"
		                                    + " ID_PESSOA, OBS, VALOR)" +
		                                    " VALUES (RENDA_SEQ.NEXTVAL, ?, ?, ?, ?)";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_RENDA"});

		            // Colocando os par�metros recebidos no comando JDBC
		            tComandoJDBC.setString(1, pRenda.getDescRenda());
		            tComandoJDBC.setInt(2, pRenda.getIdPessoa());
		            tComandoJDBC.setInt(3, pRenda.getIdPessoa());
		            tComandoJDBC.setString(4, pRenda.getObs());
		            tComandoJDBC.setFloat(5, pRenda.getValor());
		            
		            // Executando o comando de grava��o e salvando o n�mero de registros
		            // inclu�dos
		            int tQtdeReg = tComandoJDBC.executeUpdate();

		            // Verificando se um registro foi inclu�do
		            if (tQtdeReg == 1)
		            {
		                // Copiando o renda para o retorno
		                tRenda = pRenda;

		                // Recuperando o c�digo gerado pelo Oracle
		                ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
		                tRsChave.next();
		                tRenda.setIdRenda(tRsChave.getInt(1));
		            }

		            // Liberando os recursos JDBC
		            tComandoJDBC.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de cria��o do renda");
		        }

		        // Retornando o objeto Renda
		        return tRenda;
		    }

		    // M�todo para recuperar um renda da base de dados (SELECT)
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#recovery(int)
		     */
		    @Override
		    public  Renda recovery(int pidRenda)
		    {
		        // Definindo o objeto renda de retorno
		        Renda tRenda = null;

		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "SELECT ID_RENDA, NOME_RENDA, ID_TIPO_RENDA, ID_PESSOA, DATA_PAGAMENTO, VALOR" +
		                                    " FROM RENDA" +
		                                    " WHERE ID_RENDA = ?";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

		            // Colocando o par�metro recebido no comando JDBC
		            tComandoJDBC.setInt(1, pidRenda);

		            // Executando o comando e salvando o ResultSet para processar
		            ResultSet tResultSet = tComandoJDBC.executeQuery();

		            // Verificando se um registro foi lido
		            if (tResultSet.next())
		            {
		                // Salvando o Renda para retornar depois
		                tRenda = carregarRenda(tResultSet);
		            }

		            // Liberando os recursos JDBC
		            tResultSet.close();
		            tComandoJDBC.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o do renda");
		        }

		        // Retornando o objeto Renda
		        return tRenda;
		    }

		    // M�todo para atualizar um renda na base de dados (UPDATE)
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#update(br.edu.opet.model.Renda)
		     */
		    @Override
		    public  Renda update(Renda pRenda)
		    {
		        // Definindo o objeto renda de retorno
		        Renda tRenda = null;

		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "UPDATE RENDA SET" +
		                                    " DESC_RENDA = ?,  ID_PESSOA = ?,"
		                                    + " OBS = ?, VALOR = ? " +
		                                    " WHERE ID_RENDA = ?";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

		            // Colocando os par�metros recebidos no comando JDBC
		            tComandoJDBC.setString(1, pRenda.getDescRenda());
		            tComandoJDBC.setInt(2, pRenda.getIdPessoa());
		            tComandoJDBC.setString(3, pRenda.getObs());
		            tComandoJDBC.setFloat(4, pRenda.getValor());
		            tComandoJDBC.setInt(5, pRenda.getIdRenda());

		            // Executando o comando de regrava��o e salvando o n�mero de registros alterados
		            int tQtdeReg = tComandoJDBC.executeUpdate();

		            // Verificando se um registro foi alterado
		            if (tQtdeReg == 1)
		            {
		                // Copiando o renda para o retorno
		                tRenda = pRenda;
		            }

		            // Liberando os recursos JDBC
		            tComandoJDBC.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de atualiza��o do renda");
		        }

		        // Retornando o objeto Renda
		        return tRenda;
		    }

		    // M�todo para deletar um renda na base de dados (DELETE)
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#delete(int)
		     */
		    @Override
		    public  boolean delete(int pidRenda)
		    {
		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "DELETE RENDA " +
		                                    " WHERE ID_RENDA = ?";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

		            // Colocando o par�metro recebido no comando JDBC
		            tComandoJDBC.setInt(1, pidRenda);

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
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de dele��o do renda");
		        }

		        // Se chegou nesse ponto a remo��o n�o foi efetuada
		        return false;
		    }

		    // M�todo para pesquisar todos os rendas da base de dados
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#search()
		     */
		    @Override
		    public  List<Renda> search()
		    {
		        // Criando a tLista de rendas vazia
		        List<Renda> tLista = new ArrayList<>();

		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "SELECT ID_RENDA, DESC_RENDA, ID_PESSOA, OBS, VALOR" +
		                                    " FROM RENDA " +
		                                    " ORDER BY UPPER(DESC_RENDA)";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

		            // Executando o comando e salvando o ResultSet para processar
		            ResultSet tResultSet = tComandoJDBC.executeQuery();

		            // Enquanto houver registros, processa
		            while (tResultSet.next())
		            {
		                // Salvando o Renda retornado para adicionar na lista
		                Renda tRenda = carregarRenda(tResultSet);

		                // Adicionando o renda na tLista
		                tLista.add(tRenda);
		            }

		            // Liberando os recursos JDBC
		            tResultSet.close();
		            tComandoJDBC.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o da lista de rendas");
		        }

		        // Retornando a lista de rendas
		        return tLista;
		    }

		    // M�todo para pesquisar por nome todos os rendas da base de dados
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#searchByDescRenda(java.lang.String)
		     */
		    @Override
		    public  List<Renda> searchByDescRenda(String pDescRenda)
		    {
		        // Acertando o crit�rio de pesquisa
		        String tDescRendaPesquisa = "%" + pDescRenda + "%";

		        // Criando a tLista de rendas vazia
		        List<Renda> tLista = new ArrayList<>();

		        try
		        {
		            // Criando o comando SQL e o comando JDBC
		            String tComandoSQL = "SELECT ID_RENDA, DESC_RENDA, ID_PESSOA, OBS, VALOR" +
		                                    " FROM RENDA" +
		                                    " WHERE UPPER(DESC_RENDA) LIKE UPPER(?)" +
		                                    " ORDER BY UPPER(DESC_RENDA)";
		            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

		            // Colocando os par�metros recebidos no par�metros do comando JDBC
		            tComandoJDBC.setString(1, tDescRendaPesquisa);

		            // Executando o comando e salvando o ResultSet para processar
		            ResultSet tResultSet = tComandoJDBC.executeQuery();

		            // Enquanto houver registros, processa
		            while (tResultSet.next())
		            {
		                // Salvando o Renda retornado para adicionar na lista
		                Renda tRenda = carregarRenda(tResultSet);

		                // Adicionando o renda na tLista
		                tLista.add(tRenda);
		            }

		            // Liberando os recursos JDBC
		            tResultSet.close();
		            tComandoJDBC.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo da pesquisa por nome dos rendas");
		        }

		        // Retornando a lista de rendas
		        return tLista;
		    }

		    // M�todo para pesquisar por email todos os rendas da base de dados
		    /* (non-Javadoc)
		     * @see br.edu.opet.dao.jdbc.RendaDAO#searchByIdPessoal(java.lang.String)
		     */


		    // M�todo para processar o ResultSet e retornar um Renda
		    private  Renda carregarRenda(ResultSet tResultSet) throws SQLException
		    {
		        // Criando um novo renda para armazenar as informa��es lidas
		        Renda tRenda = new Renda();

		        // Recuperando as informa��es do ResultSet e colocando objeto criado
		        tRenda.setIdRenda(tResultSet.getInt("ID_RENDA"));
		        tRenda.setDescRenda(tResultSet.getString("DESC_RENDA"));
		        tRenda.setIdPessoa(tResultSet.getInt("ID_PESSOA"));
		        tRenda.setObs(tResultSet.getString("OBS"));
		        tRenda.setValor(tResultSet.getFloat("VALOR"));
		        
		        // Retornando o renda criado
		        return tRenda;
		    }
		
}
