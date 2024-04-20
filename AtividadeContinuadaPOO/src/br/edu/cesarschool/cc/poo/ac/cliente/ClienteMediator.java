package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
   private static ClienteMediator instance;
   private ClienteDAO clienteDao = new ClienteDAO();

   private ClienteMediator() {
   }

   public static synchronized ClienteMediator obterInstancia() {
      if (instance == null) {
         instance = new ClienteMediator();
      }

      return instance;
   }

   public Cliente buscar(String cpf) {
      return this.clienteDao.buscar(cpf);
   }

   public String validar(Cliente cliente) {
      boolean cpfvalido = ValidadorCPF.isCpfValido(cliente.getCpf());
      boolean nomevalido = StringUtils.isVaziaOuNula(cliente.getNome()) && cliente.getNome().length() >= 2;
      if (!nomevalido) {
         return "nome errado";
      } else if (!cpfvalido) {
         return "CPF errado";
      } else {
         boolean saldovalido = cliente.getSaldoPontos() >= 0.0;
         return !saldovalido ? "saldo errado" : null;
      }
   }

   public String incluir(Cliente cliente) {
      String erro = this.validar(cliente);
      if (erro == null) {
         boolean inclusao = this.clienteDao.incluir(cliente);
         return inclusao ? null : "Cliente ja existente";
      } else {
         return erro;
      }
   }

   public String alterar(Cliente cliente) {
      String erro = this.validar(cliente);
      if (erro == null) {
         boolean alteracao = this.clienteDao.alterar(cliente);
         return alteracao ? null : "Cliente inexistente";
      } else {
         return erro;
      }
   }

   public String excluir(String cpf) {
      Cliente cliente = this.buscar(cpf);
      if (cliente == null) {
         return "Cliente inexistente";
      } else {
         String erro = this.validar(cliente);
         if (erro == null) {
            boolean exclusao = this.clienteDao.excluir(cliente.getCpf());
            return exclusao ? null : "Cliente inexistente";
         } else {
            return erro;
         }
      }
   }
}