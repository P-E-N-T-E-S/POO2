package br.edu.cesarschool.cc.poo.ac.cliente;
import br.edu.cesarschool.cc.poo.ac.utils.*;

public class ClienteMediator {
	ClienteDAO clienteDAO;
	
	public ClienteMediator() {
		
	}
	
    public Cliente buscar(String cpf) {
        return clienteDAO.buscar(cpf);
    }
    
    public String validar(Cliente cliente) {
    	if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
    		return "CPF errado";
    	}
    	else if ()
    }
    
    
}
