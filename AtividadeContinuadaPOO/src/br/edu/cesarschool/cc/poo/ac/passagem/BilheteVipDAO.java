package br.edu.cesarschool.cc.poo.ac.passagem;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteVipDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);

    public BilheteVipDAO() {

    }

    private String obterIdUnico(BilheteVip bilheteVip) {
        return bilheteVip.gerarNumero();
    }

    public BilheteVip buscar(String numeroBilhete) {
        return (BilheteVip) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip biV = buscar(idUnico);
        if (biV == null) {
            cadastro.incluir(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip biV = buscar(idUnico);
        if (biV != null) {
            cadastro.alterar(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        BilheteVip biV = buscar(numeroBilhete);
        if (biV != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}