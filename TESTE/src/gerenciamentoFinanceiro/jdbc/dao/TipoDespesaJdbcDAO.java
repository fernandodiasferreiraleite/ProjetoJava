package gerenciamentoFinanceiro.jdbc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gerenciamentoFinanceiro.dao.TipoDespesaDAO;
import gerenciamentoFinanceiro.jdbc.Conexao;
import gerenciamentoFinanceiro.model.TipoDespesa;
import gerenciamentoFinanceiro.util.ExceptionUtil;


public class TipoDespesaJdbcDAO implements TipoDespesaDAO{
		
		  // Conexão com o Oracle
		
			    private  Connection sConexao = Conexao.getConexao();

		
			    // Método para criar um renda na base de dados (INSERT)
			    @Override
			    public  TipoDespesa create(TipoDespesa pTipoDespesa)
			    {
			        // Definindo o objeto renda de retorno
			        TipoDespesa tTipoDespesa = null;

			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "INSERT INTO TIPODESPESA" +
			                                    " (ID_TIPO_DESPESA, NOME_TIPO_DESPESA," + 
			            		               " VALUES (TIPODESPESA_SEQ.NEXTVAL, ?)";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_TIPO_DESPESA"});

			            // Colocando os parâmetros recebidos no comando JDBC
			            tComandoJDBC.setString(1, pTipoDespesa.getNmTipoDespesa());
			            
			            // Executando o comando de gravação e salvando o número de registros
			            // incluídos
			            int tQtdeReg = tComandoJDBC.executeUpdate();

			            // Verificando se um registro foi incluído
			            if (tQtdeReg == 1)
			            {
			                // Copiando o renda para o retorno
			                tTipoDespesa = pTipoDespesa;

			                // Recuperando o código gerado pelo Oracle
			                ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
			                tRsChave.next();
			                tTipoDespesa.setIdTipoDespesa(tRsChave.getInt(1));
			            }

			            // Liberando os recursos JDBC
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criação do renda");
			        }

			        // Retornando o objeto TipoDespesa
			        return tTipoDespesa;
			    }

			    // Método para recuperar um renda da base de dados (SELECT)
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#recovery(int)
			     */
			    @Override
			    public  TipoDespesa recovery(int pidTipoDespesa)
			    {
			        // Definindo o objeto renda de retorno
			        TipoDespesa tTipoDespesa = null;

			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "SELECT ID_TIPO_DESPESA, NOME_TIPO_DESPESA" +
			                                    " FROM TIPODESPESA" +
			                                    " WHERE ID_TIPODESPESA = ?";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

			            // Colocando o parâmetro recebido no comando JDBC
			            tComandoJDBC.setInt(1, pidTipoDespesa);

			            // Executando o comando e salvando o ResultSet para processar
			            ResultSet tResultSet = tComandoJDBC.executeQuery();

			            // Verificando se um registro foi lido
			            if (tResultSet.next())
			            {
			                // Salvando o TipoDespesa para retornar depois
			                tTipoDespesa = carregarTipoDespesa(tResultSet);
			            }

			            // Liberando os recursos JDBC
			            tResultSet.close();
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação do renda");
			        }

			        // Retornando o objeto TipoDespesa
			        return tTipoDespesa;
			    }

			    // Método para atualizar um renda na base de dados (UPDATE)
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#update(br.edu.opet.model.TipoDespesa)
			     */
			    @Override
			    public  TipoDespesa update(TipoDespesa pTipoDespesa)
			    {
			        // Definindo o objeto renda de retorno
			        TipoDespesa tTipoDespesa = null;

			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "UPDATE TIPODESPESA SET" +
			                                    " NOME_TIPO_DESPESA = ?"+
			                                    " WHERE ID_TIPO_DESPESA = ?";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

			            // Colocando os parâmetros recebidos no comando JDBC
			            tComandoJDBC.setString(1, pTipoDespesa.getNmTipoDespesa());
			            tComandoJDBC.setInt(2, pTipoDespesa.getIdTipoDespesa());

			            // Executando o comando de regravação e salvando o número de registros alterados
			            int tQtdeReg = tComandoJDBC.executeUpdate();

			            // Verificando se um registro foi alterado
			            if (tQtdeReg == 1)
			            {
			                // Copiando o renda para o retorno
			                tTipoDespesa = pTipoDespesa;
			            }

			            // Liberando os recursos JDBC
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método de atualização do renda");
			        }

			        // Retornando o objeto TipoDespesa
			        return tTipoDespesa;
			    }

			    // Método para deletar um renda na base de dados (DELETE)
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#delete(int)
			     */
			    @Override
			    public  boolean delete(int pidTipoDespesa)
			    {
			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "DELETE TIPODESPESA " +
			                                    " WHERE ID_TIPO_DESPESA = ?";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

			            // Colocando o parâmetro recebido no comando JDBC
			            tComandoJDBC.setInt(1, pidTipoDespesa);

			            // Executando o comando de remoção e salvando o número de registros removidos
			            int tQtdeReg = tComandoJDBC.executeUpdate();

			            // Verificando se um registro foi removido
			            if (tQtdeReg == 1)
			            {
			                // Indicado que a remoção foi efetuado com sucesso
			                return true;
			            }

			            // Liberando os recursos JDBC
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método de deleção do renda");
			        }

			        // Se chegou nesse ponto a remoção não foi efetuada
			        return false;
			    }

			    // Método para pesquisar todos os rendas da base de dados
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#search()
			     */
			    @Override
			    public  List<TipoDespesa> search()
			    {
			        // Criando a tLista de rendas vazia
			        List<TipoDespesa> tLista = new ArrayList<>();

			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "SELECT ID_TIPO_DESPESA, NOME_TIPO_DESPESA" +
			                                    " FROM TIPODESPESA " +
			                                    " ORDER BY UPPER(DESC_TIPO_DESPESA)";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

			            // Executando o comando e salvando o ResultSet para processar
			            ResultSet tResultSet = tComandoJDBC.executeQuery();

			            // Enquanto houver registros, processa
			            while (tResultSet.next())
			            {
			                // Salvando o TipoDespesa retornado para adicionar na lista
			                TipoDespesa tTipoDespesa = carregarTipoDespesa(tResultSet);

			                // Adicionando o renda na tLista
			                tLista.add(tTipoDespesa);
			            }

			            // Liberando os recursos JDBC
			            tResultSet.close();
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação da lista de rendas");
			        }

			        // Retornando a lista de rendas
			        return tLista;
			    }

			    // Método para pesquisar por nome todos os rendas da base de dados
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#searchByDescTipoDespesa(java.lang.String)
			     */
			    @Override
			    public  List<TipoDespesa> searchByNomeTipoDespesa(String pDescTipoDespesa)
			    {
			        // Acertando o critério de pesquisa
			        String tDescTipoDespesaPesquisa = "%" + pDescTipoDespesa + "%";

			        // Criando a tLista de rendas vazia
			        List<TipoDespesa> tLista = new ArrayList<>();

			        try
			        {
			            // Criando o comando SQL e o comando JDBC
			            String tComandoSQL = "SELECT ID_TIPO_DESPESA, NOME_TIPODESPESA" +
			                                    " FROM TIPODESPESA" +
			                                    " WHERE UPPER(NOME_TIPO_DESPESA) LIKE UPPER(?)" +
			                                    " ORDER BY UPPER(NOME_TIPO_DESPESA)";
			            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

			            // Colocando os parâmetros recebidos no parâmetros do comando JDBC
			            tComandoJDBC.setString(1, tDescTipoDespesaPesquisa);

			            // Executando o comando e salvando o ResultSet para processar
			            ResultSet tResultSet = tComandoJDBC.executeQuery();

			            // Enquanto houver registros, processa
			            while (tResultSet.next())
			            {
			                // Salvando o TipoDespesa retornado para adicionar na lista
			                TipoDespesa tTipoDespesa = carregarTipoDespesa(tResultSet);

			                // Adicionando o renda na tLista
			                tLista.add(tTipoDespesa);
			            }

			            // Liberando os recursos JDBC
			            tResultSet.close();
			            tComandoJDBC.close();
			        }
			        catch (SQLException tExcept)
			        {
			            ExceptionUtil.mostrarErro(tExcept, "Erro no método da pesquisa por nome dos rendas");
			        }

			        // Retornando a lista de rendas
			        return tLista;
			    }

			    // Método para pesquisar por email todos os rendas da base de dados
			    /* (non-Javadoc)
			     * @see br.edu.opet.dao.jdbc.TipoDespesaDAO#searchByIdPessoal(java.lang.String)
			     */


			    // Método para processar o ResultSet e retornar um TipoDespesa
			    private  TipoDespesa carregarTipoDespesa(ResultSet tResultSet) throws SQLException
			    {
			        // Criando um novo renda para armazenar as informações lidas
			        TipoDespesa tTipoDespesa = new TipoDespesa();

			        // Recuperando as informações do ResultSet e colocando objeto criado
			        tTipoDespesa.setIdTipoDespesa(tResultSet.getInt("ID_TIPO_DESPESA"));
			        tTipoDespesa.setNmTipoDespesa(tResultSet.getString("NOME_TIPO_DESPESA"));
			        			        
			        // Retornando o renda criado
			        return tTipoDespesa;
			    }

}
