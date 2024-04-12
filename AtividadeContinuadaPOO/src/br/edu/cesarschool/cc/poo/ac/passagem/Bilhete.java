package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.*;
import java.time.LocalDateTime;

public class Bilhete extends Registro {
	private Cliente cliente;
	private Voo voo;
	private double preco;
	private double pagamentoEmPontos;
	private LocalDateTime dataHora;
	
	public Bilhete(LocalDateTime dhInclusao,LocalDateTime dhUltimaAtualizacao, Cliente cliente, Voo voo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
		super(dhInclusao, dhUltimaAtualizacao);
		this.cliente = cliente;
		this.voo = voo;
		this.preco = preco;
		this.pagamentoEmPontos = pagamentoEmPontos;
		this.dataHora = dataHora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Voo getVoo() {
		return voo;
	}

	public double getPreco() {
		return preco;
	}

	public double getPagamentoEmPontos() {
		return pagamentoEmPontos;
	}

	public void setPagamentoEmPontos(double pagamentoEmPontos) {
		this.pagamentoEmPontos = pagamentoEmPontos;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public double obterValorPago() {
		return this.preco - this.pagamentoEmPontos;
	}
	
	public double obterValorPontuacao() {
		return (this.obterValorPago() / 20);
	}
	
	public String gerarNumero() {
		return cliente.getCpf() + voo.obterIdVoo() + dataHora.getYear() + dataHora.getMonthValue() + dataHora.getDayOfMonth();
	}
}


