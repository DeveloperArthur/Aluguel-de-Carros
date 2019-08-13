package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.alugador.Alugador;
import br.com.aluguel.de.carros.cliente.Cliente;

import java.time.LocalDate;

public class AluguelDto {
    private Long id;
    private Cliente cliente;
    private Alugador alugador;
    private LocalDate retirada;
    private LocalDate entrega;
    private float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Alugador getAlugador() {
        return alugador;
    }

    public void setAlugador(Alugador alugador) {
        this.alugador = alugador;
    }

    public LocalDate getRetirada() {
        return retirada;
    }

    public void setRetirada(LocalDate retirada) {
        this.retirada = retirada;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
