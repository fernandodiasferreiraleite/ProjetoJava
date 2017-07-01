package gerenciamentoFinanceiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import gerenciamentoFinanceiro.controllerdao.DespesaControllerDAO;
import gerenciamentoFinanceiro.model.Despesa;

@Named("despesaBean")
@SessionScoped
public class DespesaBean implements Serializable{

		/**
	 *
	 */
	private static final long serialVersionUID = 1L;

		/*
		 * Vari�veis de inst�ncia
		 */
		private Despesa despesa;

		private List<Despesa> despesas;

		private DespesaControllerDAO manutencaoDespesa;

		private String caption;

		/*
		 * Fun��o construtora da classe
		 */

		public DespesaBean() {

			despesa = new Despesa();

			manutencaoDespesa = new DespesaControllerDAO();
		}

		/*
		 * M�todos de acesso
		 */

		/**
		 * @return the manutencaoDespesa
		 */
		public DespesaControllerDAO getDespesaControllerDAO() {
			return manutencaoDespesa;
		}

		/**
		 * @param manutencaoDespesa the manutencaoDespesa to set
		 */
		public void setDespesaControllerDAO(DespesaControllerDAO manutencaoDespesa) {
			this.manutencaoDespesa = manutencaoDespesa;
		}

		/**
		 * @return the despesa
		 */
		public Despesa getDespesa() {
			return despesa;
		}

		/**
		 * @param despesa the despesa to set
		 */
		public void setDespesa(Despesa despesa) {
			this.despesa = despesa;
		}

		/**
		 * @return the despesas
		 */
		public List<Despesa> getDespesas() {
			return despesas;
		}

		/**
		 * @param despesas the despesas to set
		 */
		public void setDespesas(List<Despesa> despesas) {
			this.despesas = despesas;
		}

		/**
		 * @return the caption
		 */
		public String getCaption() {
			return caption;
		}

		/**
		 * @param caption the caption to set
		 */
		public void setCaption(String caption) {
			this.caption = caption;
		}



		/*
		 * Opera��es da classe
		 */



		public String salvar() {

			// Declara��o de vari�veis

			FacesContext context = FacesContext.getCurrentInstance();

			// Processamento dos dados

			if (this.despesa.getIdDespesa() !=0) {

				this.despesa = manutencaoDespesa.cadastrar(this.despesa);

				this.caption = "Dados Cadastros com Sucesso";

			} else {

				this.despesa = manutencaoDespesa.atualizar(this.despesa);

				this.caption = "Dados Atualizados com Sucesso";
			}

			if (this.despesa == null) {

				this.despesas = null;

				return "mostrarCadastro";

			} else {

				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "N�o foi poss�vel salvar os dados.", ""));

				return null;
			}
		}


		public String consultar() {

			// Processamento dos dados

			this.despesas = manutencaoDespesa.consultar();

			// Sa�da da informa��o

			return "/paginas/consulta/consultarDespesa";
		}

		@SuppressWarnings("null")
		public String excluir() {

			FacesContext context = FacesContext.getCurrentInstance();

			Boolean flag = null;

			// Processamento dos dados

// 			flag = manutencaoDespesa.excluir(this.despesa);

			if (flag.equals(true)) {

				this.despesas = manutencaoDespesa.consultar();

				return null;

			} else {

				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"N�o foi poss�vel excluir os dados da pessoa C�digo # " + despesa.getIdDespesa(), ""));

				return null;
			}
		}

		public String pesquisar(String nome) {

			this.despesas = manutencaoDespesa.pesquisar(nome);

			this.setCaption("Resultado da Pesquisa");

			return "/paginas/consulta/consultaDespesa";
		}

		public String menuCadastrar() {

			this.inicializacao();

			return "/paginas/cadastro/cadastrarDespesa";
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see cadastro.universitario.controller.
		 * pessoa.PessoaBean#menuConsultar()
		 */
		public String menuConsultar() {

			this.inicializacao();

			return "/paginas/consulta/consultarDespesa";
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see cadastro.universitario.controller.
		 * pessoa.PessoaBean#menuPesquisar()
		 */
		public String menuPesquisar() {

			this.inicializacao();

			return "/paginas/pesquisa/pesquisaDespesa/pesquisarDespesa";
		}

		public String homepage() {

			this.inicializacao();

			return "/paginas/homepage";
		}

		/**
		 *
		 */
		private void inicializacao() {

			this.despesa = new Despesa();

			this.caption = null;

			this.despesas = null;

		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((despesa == null) ? 0 : despesa.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DespesaBean)) {
				return false;
			}
			DespesaBean other = (DespesaBean) obj;
			if (despesa == null) {
				if (other.despesa != null) {
					return false;
				}
			} else if (!despesa.equals(other.despesa)) {
				return false;
			}
			return true;

	}
}