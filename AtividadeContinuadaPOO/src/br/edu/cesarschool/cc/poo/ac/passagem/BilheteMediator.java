package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;
import java.time.LocalDateTime;

public class BilheteMediator {
    private static BilheteMediator instancia;
    private BilheteDAO bilheteDao = new BilheteDAO();
    private BilheteVipDAO bilheteVipDao = new BilheteVipDAO();
    private VooMediator vooMediator = VooMediator.obterInstancia();
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();

    private BilheteMediator() {
    }

    public static BilheteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new BilheteMediator();
        }
        return instancia;
    }
    
    public Bilhete buscar(String numeroBilhete) {
        return this.bilheteDao.buscar(numeroBilhete);
     }

     public BilheteVip buscarVip(String numeroVip) {
        return this.bilheteVipDao.buscar(numeroVip);
     }
     
     public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
    	 if(!ValidadorCPF.isCpfValido(cpf)) {
    		 return "CPF errado";
    	 }
    	 String validarCiaNumero = vooMediator.validarCiaNumero(ciaAerea, numeroVoo);
    	 if(validarCiaNumero != null) {
    		 return validarCiaNumero;
    	 }
    	 if(preco <= 0) {
    		 return "Preco errado";
    	 }
    	 
    	 if(pagamentoEmPontos < 0) {
    		 return "Pagamento pontos errado";
    	 }
    	 if (preco < pagamentoEmPontos) {
    	     return "Preco menor que pagamento em pontos";
    	 }
    	 LocalDateTime dataHoraAtual = LocalDateTime.now().plusHours(1);
    	 if (dataHora.isBefore(dataHoraAtual)) {
    	     return "data hora invalida";
    	 }
    	 return null;
     }
     
     public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
    	 String mensagemErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
    	 if (mensagemErro != null) {
    		 return new ResultadoGeracaoBilhete(null, null, mensagemErro);
    	 }
    	    
    	 Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
         String idVoo = voo.obterIdVoo();
         Voo vooBuscado = this.vooMediator.buscar(idVoo);
         if (vooBuscado == null) {
             return new ResultadoGeracaoBilhete((Bilhete)null, (BilheteVip)null, "Voo nao encontrado");
         }
         
         Cliente cliente = this.clienteMediator.buscar(cpf);
         if (cliente == null) {
            return new ResultadoGeracaoBilhete((Bilhete)null, (BilheteVip)null, "Cliente nao encontrado");
         }
         double valorPontosNecessarios = pagamentoEmPontos * 20.0;
         if (cliente.getSaldoPontos() < valorPontosNecessarios) {
            return new ResultadoGeracaoBilhete((Bilhete)null, (BilheteVip)null, "Pontos insuficientes");
         }

         Bilhete bilhete = new Bilhete(cliente, vooBuscado, preco, pagamentoEmPontos, dataHora);
         cliente.debitarPontos(valorPontosNecessarios);
         cliente.creditarPontos(bilhete.obterValorPontuacao());

         if (!bilheteDao.incluir(bilhete)) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
         }
         return this.clienteMediator.alterar(cliente) != null ? new ResultadoGeracaoBilhete((Bilhete)null, (BilheteVip)null, "Erro ao atualizar cliente") : new ResultadoGeracaoBilhete(bilhete, null, null);
         
     }
     
     public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
    	 	
    	 String mensagemErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
    	 if (mensagemErro != null) {
    		 return new ResultadoGeracaoBilhete(null, null, mensagemErro);
    	 }

    	 if (bonusPontuacao <= 0 || bonusPontuacao > 100) {
    	     return new ResultadoGeracaoBilhete(null, null, "Bonus errado");
    	 }

    	 Voo voo = new Voo(null, null, ciaAerea, numeroVoo);

    	 String idVoo = voo.obterIdVoo();

    	 Voo vooEncontrado = vooMediator.buscar(idVoo);
    	 if (vooEncontrado == null) {
    	    return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
    	 }
    	 Cliente cliente = clienteMediator.buscar(cpf);
    	 if (cliente == null) {
    	    return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
    	 }

    	 double pontosNecessarios = pagamentoEmPontos * 20;
    	 if (cliente.getSaldoPontos() < pontosNecessarios) {
    	    return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
    	 }

    	 BilheteVip bilheteVip = new BilheteVip(cliente, vooEncontrado, preco, pagamentoEmPontos, dataHora, bonusPontuacao);

    	 cliente.debitarPontos(pontosNecessarios);
    	 cliente.creditarPontos(bilheteVip.obterValorPontuacaoVip());

    	 if (!bilheteVipDao.incluir(bilheteVip)) {
    		return new ResultadoGeracaoBilhete(null, null, "Bilhete vip ja existente");
    	 }

    	 String mensagemAlterarCliente = clienteMediator.alterar(cliente);
    	 if (mensagemAlterarCliente != null) {
    	    return new ResultadoGeracaoBilhete(null, null, mensagemAlterarCliente);
    	 }

    	    return new ResultadoGeracaoBilhete(null, bilheteVip, null);
    	}   
}