package util;

public class CpfUtil {
    public static boolean verificarCpfValido(String cpf) {

        if (cpf.length() != 11) return false;
        int soma = 0;
        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;
        for (int i = 0; i < 9; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * (10 - i);
        }
        int resto = (soma * 10) % 11;
        if (resto == 10) resto = 0;
        if (resto != cpf.charAt(9) - '0') return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        resto = (soma * 10) % 11;
        if (resto == 10) resto = 0;

        return resto == cpf.charAt(10) - '0';
    }
    public static String limparCpf(String cpf){
        String cpfLimpo = cpf.replaceAll("\\D", "");
        return cpfLimpo;
    }
}
