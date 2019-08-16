package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.alugador.Alugador;
import br.com.aluguel.de.carros.cliente.Cliente;
import br.com.aluguel.de.carros.usuario.Usuario;

import java.time.LocalDate;

public class AluguelDto {
    private Long id;
    private Usuario usuario;
    private LocalDate retirada;
    private LocalDate entrega;
    private float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
