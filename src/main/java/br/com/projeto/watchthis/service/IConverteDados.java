package br.com.projeto.watchthis.service;

public interface IConverteDados {

    // converte dado para um classe generica
    <T> T obterDados (String json, Class<T> classe);
}
