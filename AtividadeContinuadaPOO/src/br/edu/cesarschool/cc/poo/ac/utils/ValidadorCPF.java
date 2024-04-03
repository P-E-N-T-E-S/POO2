package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {

    private ValidadorCPF() {
     
    }

    public static boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (StringUtils.isVaziaOuNula(cpf) && cpf.length() == 11) {
            boolean digitosIguais = true;
            for (int i = 1; i < cpf.length(); i++) {
                if (cpf.charAt(i) != cpf.charAt(i - 1)) {
                    digitosIguais = false;
                    break;
                }
            }
            if (!digitosIguais) {
                int soma = 0;
                for (int i = 0; i < 9; i++) {
                    soma += (cpf.charAt(i) - '0') * (10 - i);
                }
                int resto = soma % 11;
                int digitoVerificador1 = (resto < 2) ? 0 : (11 - resto);

                if (digitoVerificador1 == cpf.charAt(9) - '0') {
                    soma = 0;
                    for (int i = 0; i < 10; i++) {
                        soma += (cpf.charAt(i) - '0') * (11 - i);
                    }
                    resto = soma % 11;
                    int digitoVerificador2 = (resto < 2) ? 0 : (11 - resto);

                    return (digitoVerificador2 == cpf.charAt(10) - '0');
                }
            }
        }
        return false;
    }
}