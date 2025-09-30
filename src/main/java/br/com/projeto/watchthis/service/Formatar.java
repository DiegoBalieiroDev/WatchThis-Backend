package br.com.projeto.watchthis.service;

public class Formatar {
    public static String formatar(String anoOriginal) {
        if (anoOriginal == null || anoOriginal.isBlank()) {
            return "Desconhecido";
        }

        // Padroniza o traço
        anoOriginal = anoOriginal.replace("–", "-").trim();

        if (anoOriginal.endsWith("-")) {
            return anoOriginal.replace("-", "") + " - atualmente";
        }

        return anoOriginal;
    }
}
