package br.edu.cesarschool.cc.poo.ac.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TelaCliente {
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
    private static final Scanner ENTRADA = new Scanner(System.in);

    public void exibirMenuPrincipal() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    alterar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    System.out.println("Saindo do cadastro de clientes");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!!");
                    break;
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void incluir() {
        System.out.println("Insira o cpf do cliente: ");
        String cpf = ENTRADA.nextLine();
        System.out.println("Insira o nome do cliente: ");
        String nome = ENTRADA.nextLine();
        System.out.println("Insira o saldo do cliente");
        double saldo = ENTRADA.nextDouble();
        Cliente cliente = new Cliente(cpf, nome, saldo);
        String retorno = clienteMediator.incluir(cliente);
        if (retorno == null) {
            System.out.println("Cliente incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void alterar() {
        String cpf = buscar().getCpf();
        System.out.println("Insira o nome do cliente: ");
        String nome = ENTRADA.nextLine();
        System.out.println("Insira o saldo do cliente");
        double saldo = ENTRADA.nextDouble();
        Cliente cliente = new Cliente(cpf, nome, saldo);
        String retorno = clienteMediator.alterar(cliente);
        if (retorno == null) {
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private Cliente buscar() {
        System.out.print("Digite o cpf do cliente: ");
        String cpf = ENTRADA.nextLine();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado");
            return null;
        } else {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Saldo: " + cliente.getSaldoPontos());
            return cliente;
        }
    }

    private void excluir() {
        System.out.print("Digite o cpf do cliente: ");
        String cpf = ENTRADA.nextLine();
        String retorno = clienteMediator.excluir(cpf);
        if (retorno == null) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }
}