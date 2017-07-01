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

import gerenciamentoFinanceiro.dao.PessoaDAO;
import gerenciamentoFinanceiro.jdbc.Conexao;
import gerenciamentoFinanceiro.model.Pessoa;
import gerenciamentoFinanceiro.util.ExceptionUtil;

/**
 * @author elain
 *
 */
public class PessoaJdbcDAO implements PessoaDAO{


  // Conexão com o Oracle
	    private  Connection sConexao = Conexao.getConexao();

	    // Método para criar um pessoa na base de dados (INSERT)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#create(br.edu.opet.model.Pessoa)
	     */
	    @Override
	    public  Pessoa create(Pessoa pPessoa)
	    {
	        // Definindo o objeto pessoa de retorno
	        Pessoa tPessoa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "INSERT INTO PESSOA" +
	                                    " (ID_PESSOA, NOME, SEXO)" +
	                                    " VALUES (PESSOA_SEQ.NEXTVAL, ?, ?)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_PESSOA"});

	            // Colocando os parâmetros recebidos no comando JDBC
	            tComandoJDBC.setString(1, pPessoa.getNome());
	            tComandoJDBC.setString(2, pPessoa.getSexo());


	            // Executando o comando de gravação e salvando o número de registros
	            // incluídos
	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            // Verificando se um registro foi incluído
	            if (tQtdeReg == 1)
	            {
	                // Copiando o pessoa para o retorno
	                tPessoa = pPessoa;

	                // Recuperando o código gerado pelo Oracle
	                ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
	                tRsChave.next();
	                tPessoa.setIdPessoa(tRsChave.getInt(1));
	            }

	            // Liberando os recursos JDBC
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criação do pessoa");
	        }

	        // Retornando o objeto Pessoa
	        return tPessoa;
	    }

	    // Método para recuperar um pessoa da base de dados (SELECT)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#recovery(int)
	     */
	    @Override
	    public  Pessoa recovery(int pidPessoa)
	    {
	        // Definindo o objeto pessoa de retorno
	        Pessoa tPessoa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_PESSOA, NOME, SEXO" +
	                                    " FROM PESSOA" +
	                                    " WHERE ID_PESSOA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando o parâmetro recebido no comando JDBC
	            tComandoJDBC.setInt(1, pidPessoa);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Verificando se um registro foi lido
	            if (tResultSet.next())
	            {
	                // Salvando o Pessoa para retornar depois
	                tPessoa = carregarPessoa(tResultSet);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação do pessoa");
	        }

	        // Retornando o objeto Pessoa
	        return tPessoa;
	    }

	    // Método para atualizar um pessoa na base de dados (UPDATE)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#update(br.edu.opet.model.Pessoa)
	     */
	    @Override
	    public  Pessoa update(Pessoa pPessoa)
	    {
	        // Definindo o objeto pessoa de retorno
	        Pessoa tPessoa = null;

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "UPDATE PESSOA SET" +
	                                    " NOME = ?, SEXO = ?"
	                                    + " WHERE ID_PESSOA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando os parâmetros recebidos no comando JDBC
	            tComandoJDBC.setString(1, pPessoa.getNome());
	            tComandoJDBC.setString(2, pPessoa.getSexo());;
	            tComandoJDBC.setInt(3, pPessoa.getIdPessoa());

	            // Executando o comando de regravação e salvando o número de registros alterados
	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            // Verificando se um registro foi alterado
	            if (tQtdeReg == 1)
	            {
	                // Copiando o pessoa para o retorno
	                tPessoa = pPessoa;
	            }

	            // Liberando os recursos JDBC
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de atualização do pessoa");
	        }

	        // Retornando o objeto Pessoa
	        return tPessoa;
	    }

	    // Método para deletar um pessoa na base de dados (DELETE)
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#delete(int)
	     */
	    @Override
	    public  boolean delete(int pidPessoa)
	    {
	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "DELETE PESSOA " +
	                                    " WHERE ID_PESSOA = ?";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando o parâmetro recebido no comando JDBC
	            tComandoJDBC.setInt(1, pidPessoa);

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
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de deleção do pessoa");
	        }

	        // Se chegou nesse ponto a remoção não foi efetuada
	        return false;
	    }

	    // Método para pesquisar todos os pessoas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#search()
	     */
	    @Override
	    public  List<Pessoa> search()
	    {
	        // Criando a tLista de pessoas vazia
	        List<Pessoa> tLista = new ArrayList<>();

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_PESSOA, NOME, SEXO" +
	                                    " FROM PESSOA " +
	                                    " ORDER BY UPPER(NOME)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Enquanto houver registros, processa
	            while (tResultSet.next())
	            {
	                // Salvando o Pessoa retornado para adicionar na lista
	                Pessoa tPessoa = carregarPessoa(tResultSet);

	                // Adicionando o pessoa na tLista
	                tLista.add(tPessoa);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação da lista de pessoas");
	        }

	        // Retornando a lista de pessoas
	        return tLista;
	    }

	    // Método para pesquisar por nome todos os pessoas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#searchByNomePessoa(java.lang.String)
	     */
	    @Override
	    public  List<Pessoa> searchByNome(String pNome)
	    {
	        // Acertando o critério de pesquisa
	        String tNomePessoaPesquisa = "%" + pNome + "%";

	        // Criando a tLista de pessoas vazia
	        List<Pessoa> tLista = new ArrayList<>();

	        try
	        {
	            // Criando o comando SQL e o comando JDBC
	            String tComandoSQL = "SELECT ID_PESSOA, NOME, SEXO" +
	                                    " FROM PESSOA" +
	                                    " WHERE UPPER(NOME) LIKE UPPER(?)" +
	                                    " ORDER BY UPPER(NOME)";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            // Colocando os parâmetros recebidos no parâmetros do comando JDBC
	            tComandoJDBC.setString(1, tNomePessoaPesquisa);

	            // Executando o comando e salvando o ResultSet para processar
	            ResultSet tResultSet = tComandoJDBC.executeQuery();

	            // Enquanto houver registros, processa
	            while (tResultSet.next())
	            {
	                // Salvando o Pessoa retornado para adicionar na lista
	                Pessoa tPessoa = carregarPessoa(tResultSet);

	                // Adicionando o pessoa na tLista
	                tLista.add(tPessoa);
	            }

	            // Liberando os recursos JDBC
	            tResultSet.close();
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método da pesquisa por nome dos pessoas");
	        }

	        // Retornando a lista de pessoas
	        return tLista;
	    }

	    // Método para pesquisar por email todos os pessoas da base de dados
	    /* (non-Javadoc)
	     * @see br.edu.opet.dao.jdbc.PessoaDAO#searchByl(java.lang.String)
	     */


	    // Método para processar o ResultSet e retornar um Pessoa
	    private  Pessoa carregarPessoa(ResultSet tResultSet) throws SQLException
	    {
	        // Criando um novo pessoa para armazenar as informações lidas
	        Pessoa tPessoa = new Pessoa();

	        // Recuperando as informações do ResultSet e colocando objeto criado
	        tPessoa.setIdPessoa(tResultSet.getInt("ID_PESSOA"));
	        tPessoa.setNome(tResultSet.getString("NOME"));
	        tPessoa.setSexo(tResultSet.getString("SEXO"));

	        // Retornando o pessoa criado
	        return tPessoa;
	    }


}
