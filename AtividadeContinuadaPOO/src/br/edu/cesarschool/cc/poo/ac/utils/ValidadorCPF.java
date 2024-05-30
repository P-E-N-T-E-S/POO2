package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {
    private ValidadorCPF() {
    }

    public static void isCpfValido(String cpf) throws CpfInvalidoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new CpfInvalidoException("CPF vazio ou nulo.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new CpfInvalidoException("CPF deve conter exatamente 11 dígitos numéricos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new CpfInvalidoException("CPF com todos os dígitos iguais é inválido.");
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        if (Character.getNumericValue(cpf.charAt(9)) != digito1 || Character.getNumericValue(cpf.charAt(10)) != digito2) {
            throw new CpfInvalidoException("Dígitos verificadores do CPF são inválidos.");
        }
    }
}
