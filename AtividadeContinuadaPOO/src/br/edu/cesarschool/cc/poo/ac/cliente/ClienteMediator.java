package br.edu.cesarschool.cc.poo.ac.cliente;
import br.edu.cesarschool.cc.poo.ac.utils.*;

public class ClienteMediator {
	ClienteDAO clienteDAO;

    public Cliente buscar(String cpf) {
		return clienteDAO.buscar(cpf);
    }
    
    public String validar(Cliente cliente) {
    	if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
    		return "CPF errado";
    	}

		if (StringUtils.isVaziaOuNula(cliente.getNome()) || cliente.getNome().length() < 2) {
			return "nome errado";
		}

		if (cliente.getSaldoPontos() < 0) {
			return "saldo errado";
		}

		return null;
    }

	public String incluir(Cliente cliente) {
		if (validar(cliente) != null) {
			return validar(cliente);
		}
		else {
			if (!clienteDAO.incluir(cliente)) {
				return "Cliente ja existente";
			}
			else {
				return null;
			}
		}
	}

	public String excluir(String cpf) {
		if (!ValidadorCPF.isCpfValido(cpf)) {
			return "CPF errado";
		}
		else {
			if (clienteDAO.excluir(cpf)) {
				return null;
			}
			else {
				return "Cliente inexistente";
			}
		}
	}
    
    
}
