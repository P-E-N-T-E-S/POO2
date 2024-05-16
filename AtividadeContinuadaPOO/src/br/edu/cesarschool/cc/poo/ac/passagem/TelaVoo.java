package br.edu.cesarschool.cc.poo.ac.passagem;

import java.util.Scanner;

public class TelaVoo {
    private VooMediator vooMediator = VooMediator.obterInstancia();
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
                    System.out.println("Saindo do cadastro de voos");
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
        System.out.println("Insira a companhia aérea: ");
        String companhiaAerea = ENTRADA.next();
        System.out.println("Insira o número do voo: ");
        int numeroVoo = ENTRADA.nextInt();
        System.out.println("Insira o aeroporto de origem: ");
        String aeroportoOrigem = ENTRADA.next();
        System.out.println("Insira o aeroporto de destino: ");
        String aeroportoDestino = ENTRADA.next();
        System.out.println("Insira os dias da semana (1-7 separados por vírgula): ");
        String diasDaSemanaString = ENTRADA.next();
        String[] diasDaSemanaArray = diasDaSemanaString.split(",");
        int[] diasDaSemana = new int[diasDaSemanaArray.length];
        for (int i = 0; i < diasDaSemanaArray.length; i++) {
            diasDaSemana[i] = Integer.parseInt(diasDaSemanaArray[i]);
        }
        Voo voo = new Voo(companhiaAerea, numeroVoo, aeroportoOrigem, aeroportoDestino, diasDaSemana);

        String retorno = vooMediator.incluir(voo);
        if (retorno == null) {
            System.out.println("Voo incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void alterar() {
        System.out.println("Digite o número do voo que deseja alterar: ");
        String numeroVoo = ENTRADA.next();
        Voo voo = buscar(numeroVoo);
        if (voo != null) {
            System.out.println("Insira a nova companhia aérea: ");
            String companhiaAerea = ENTRADA.next();
            System.out.println("Insira o novo aeroporto de origem: ");
            String aeroportoOrigem = ENTRADA.next();
            System.out.println("Insira o novo aeroporto de destino: ");
            String aeroportoDestino = ENTRADA.next();
            System.out.println("Insira os novos dias da semana (1-7 separados por vírgula): ");
            String diasDaSemanaString = ENTRADA.next();
            String[] diasDaSemanaArray = diasDaSemanaString.split(",");
            int[] diasDaSemana = new int[diasDaSemanaArray.length];
            for (int i = 0; i < diasDaSemanaArray.length; i++) {
                diasDaSemana[i] = Integer.parseInt(diasDaSemanaArray[i]);
            }
            voo.setCompanhiaAerea(companhiaAerea);
            voo.setAeroportoOrigem(aeroportoOrigem);
            voo.setAeroportoDestino(aeroportoDestino);
            voo.setDiasDaSemana(diasDaSemana);
        String retorno = vooMediator.alterar(voo);
        if (retorno == null) {
            System.out.println("Voo alterado com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private Cliente buscar() {
        System.out.print("Digite o voo: ");
        String idVoo = ENTRADA.nextLine();
        Voo voo = vooMediator.buscar(idVoo);
        if (voo == null) {
            System.out.println("Voo nao encontrado");
            return null;
        } else {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Saldo: " + cliente.getSaldoPontos());
            return cliente;
        }
    }

    private void excluir() {
        System.out.print("Digite o id do voo: ");
        String idVoo = ENTRADA.nextLine();
        String retorno = vooMediator.excluir(idVoo);
        if (retorno == null) {
            System.out.println("Voo excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }
}