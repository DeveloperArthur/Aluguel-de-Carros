package br.com.aluguel.de.carros.geral;

public interface GerenciarTransacao<T> {
    void refresh(T t);
}