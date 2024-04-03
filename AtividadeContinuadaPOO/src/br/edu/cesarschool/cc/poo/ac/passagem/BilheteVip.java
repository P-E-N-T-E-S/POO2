package br.edu.cesarschool.cc.poo.ac.passagem;
import java.time.LocalDateTime;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

public class BilheteVip extends Bilhete {
	private double bonusPontuacao;

	public BilheteVip(LocalDateTime dhInclusao, LocalDateTime dhUltimaAtualizacao, Cliente cliente, Voo voo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
		super(dhInclusao, dhUltimaAtualizacao, cliente, voo, preco, pagamentoEmPontos, dataHora);
		this.bonusPontuacao = bonusPontuacao;
	}

	public double getBonusPontuacao() {
		return bonusPontuacao;
	}

	public void setBonusPontuacao(double bonusPontuacao) {
		this.bonusPontuacao = bonusPontuacao;
	}
	
	public double obterValorPontuacaoVip() {
		return obterValorPontuacao() * (1 + bonusPontuacao / 100);
	}
	
}
