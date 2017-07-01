package gerenciamentoFinanceiro.testes;

import java.util.List;

import gerenciamentoFinanceiro.dao.DaoFactory;
import gerenciamentoFinanceiro.dao.PessoaDAO;
import gerenciamentoFinanceiro.model.Pessoa;

public class TestePessoaDAO
{

   public static void main(String[] args)
        {
            Pessoa tObjeto1 = new Pessoa(1, "Jurubelbo Estocrácio","M");
            Pessoa tObjeto2 = new Pessoa(2, "Anafilda Grafunveda", "F");
            Pessoa tObjeto3 = new Pessoa(3, "Veronska Transpka Vodiska", "F");

            // Criando o objeto de persistência
            PessoaDAO tPessoaDao = DaoFactory.getPessoaDAO();

            // Incluindo os objetos
            System.out.println();
            System.out.println("Incluindo os clientes");
            tObjeto1= tPessoaDao.create(tObjeto1);
            if (tObjeto1 != null)
                System.out.println("Incluído......... : " + tObjeto1);
            else
                System.out.println("Erro na inclusão : " + tObjeto1);
            tObjeto2 = tPessoaDao.create(tObjeto2);
            if (tObjeto2 != null)
                System.out.println("Incluído......... : " + tObjeto2);
            else
                System.out.println("Erro na inclusão : " + tObjeto2);
            tObjeto3 = tPessoaDao.create(tObjeto3);
            if (tObjeto3 != null)
                System.out.println("Incluído......... : " + tObjeto3);
            else
                System.out.println("Erro na inclusão : " + tObjeto3);

            // Recuperando os objetos
            System.out.println();
            System.out.println("Recuperando os objetos");
            Pessoa tObjeto4 = tPessoaDao.recovery(tObjeto1.getIdPessoa());
            if (tObjeto4 != null)
                System.out.println("Recuperado....... : " + tObjeto4);
            else
                System.out.println("Erro na recuperação : " + tObjeto4);
            Pessoa tObjeto5 = tPessoaDao.recovery(tObjeto2.getIdPessoa());
            if (tObjeto5 != null)
                System.out.println("Recuperado....... : " + tObjeto5);
            else
                System.out.println("Erro na recuperação : " + tObjeto5);
            Pessoa tObjeto6 = tPessoaDao.recovery(tObjeto3.getIdPessoa());
            if (tObjeto6 != null)
                System.out.println("Recuperado....... : " + tObjeto6);
            else
                System.out.println("Erro na recuperação : " + tObjeto6);

            // Atualizando os objetos
            System.out.println();
            System.out.println("Atualizando os objetos");
            tObjeto1.setNome(tObjeto1.getNome() + " Silva");
            tObjeto1.setSexo("M");


            tObjeto2.setNome(tObjeto2.getNome() + " Pereira");
            tObjeto2.setSexo("F");


            tObjeto3.setNome(tObjeto3.getNome() + " Souza");
            tObjeto3.setSexo("F");
            Pessoa tObjeto7 = tPessoaDao.update(tObjeto1);
            if (tObjeto7 != null)
                System.out.println("Atualizado....... : " + tObjeto7);
            else
                System.out.println("Erro na atualização : " + tObjeto7);
            Pessoa tObjeto8 = tPessoaDao.update(tObjeto2);
            if (tObjeto8 != null)
                System.out.println("Atualizado....... : " + tObjeto8);
            else
                System.out.println("Erro na atualização : " + tObjeto8);
            Pessoa tObjeto9 = tPessoaDao.update(tObjeto3);
            if (tObjeto9 != null)
                System.out.println("Atualizado....... : " + tObjeto9);
            else
                System.out.println("Erro na atualização : " + tObjeto9);

            // Recuperando os objetos atualizados
            System.out.println();
            System.out.println("Recuperando os objetos atualizados");
            Pessoa tObjeto10 = tPessoaDao.recovery(tObjeto1.getIdPessoa());
            if (tObjeto10 != null)
                System.out.println("Recuperado....... : " + tObjeto10);
            else
                System.out.println("Erro na recuperação : " + tObjeto10);
            Pessoa tObjeto11 = tPessoaDao.recovery(tObjeto2.getIdPessoa());
            if (tObjeto11 != null)
                System.out.println("Recuperado....... : " + tObjeto11);
            else
                System.out.println("Erro na recuperação : " + tObjeto11);
            Pessoa tObjeto12 = tPessoaDao.recovery(tObjeto3.getIdPessoa());
            if (tObjeto12 != null)
                System.out.println("Recuperado....... : " + tObjeto12);
            else
                System.out.println("Erro na recuperação : " + tObjeto12);

            // Listando os objetos do cadastro
            System.out.println();
            System.out.println("Listando os objetos do cadastro");
            List<Pessoa> tLista1 = tPessoaDao.search();
            for (Pessoa tObjeto : tLista1)
            {
                System.out.println("Relação.......... : " + tObjeto);
            }

            // Listando os objetos do cadastro por nome
            System.out.println();
            System.out.println("Listando os objetos do cadastro por nome");
            List<Pessoa> tLista2 = tPessoaDao.searchByNome("ve");
            for (Pessoa tObjeto : tLista2)
            {
                System.out.println("Relação por nome. : " + tObjeto);
            }

            // Removendo os objetos
            System.out.println();
            System.out.println("Removendo os objetos");
            if (tPessoaDao.delete(tObjeto1.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto1);
            else
                System.out.println("Erro na remoção.. : " + tObjeto1);
            if (tPessoaDao.delete(tObjeto2.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto2);
            else
                System.out.println("Erro na remoção.. : " + tObjeto2);
            if (tPessoaDao.delete(tObjeto3.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto3);
            else
                System.out.println("Erro na remoção.. : " + tObjeto3);

            // Verificando a remoção dos objetos
            System.out.println();
            System.out.println("Verificando a remoção dos objetos");
            if (tPessoaDao.delete(tObjeto1.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto1);
            else
                System.out.println("Erro na remoção.. : " + tObjeto1);
            if (tPessoaDao.delete(tObjeto2.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto2);
            else
                System.out.println("Erro na remoção.. : " + tObjeto2);
            if (tPessoaDao.delete(tObjeto3.getIdPessoa()))
                System.out.println("Removido......... : " + tObjeto3);
            else
                System.out.println("Erro na remoção.. : " + tObjeto3);
        }


}
