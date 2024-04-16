package br.edu.cesarschool.cc.poo.ac.passagem;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);

    public BilheteDAO() {

    }

    private String obterIdUnico(Bilhete bilhete) {
        return bilhete.gerarNumero();
    }

    public Bilhete buscar(String numeroBilhete) {
        return (Bilhete) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bi = buscar(idUnico);
        if (bi == null) {
            cadastro.incluir(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bi = buscar(idUnico);
        if (bi != null) {
            cadastro.alterar(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        Bilhete bi = buscar(numeroBilhete);
        if (bi != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}