package br.edu.cesarschool.cc.poo.ac.cliente;
import java.time.LocalDateTime;

import br.edu.cesarschool.cc.poo.ac.utils.*;

public class Cliente extends Registro {
	private String cpf;
	private String nome;
	private double saldoPontos;
	
	public Cliente(LocalDateTime dhInclusao,LocalDateTime dhUltimaAtualizacao, String cpf, String nome, double saldoPontos) {
		super(dhInclusao, dhUltimaAtualizacao);
		this.cpf = cpf;
		this.nome = nome;
		this.saldoPontos = saldoPontos;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSaldoPontos() {
		return saldoPontos;
	}
	
	public void creditarPontos(double valor) {
		saldoPontos += valor;
	}
	
	public void debitarPontos(double valor) {
		saldoPontos -= valor;
	}
}