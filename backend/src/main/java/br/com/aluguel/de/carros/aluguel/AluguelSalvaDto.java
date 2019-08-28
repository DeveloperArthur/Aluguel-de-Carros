package br.com.aluguel.de.carros.aluguel;

import java.time.LocalDate;

public class AluguelSalvaDto {
    private Long id;
    private Long usuarioClienteId;
    private Long carroId;
    private LocalDate retirada;
    private LocalDate entrega;
    private float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioClienteId() {
        return usuarioClienteId;
    }

    public void setUsuarioClienteId(Long usuarioClienteId) {
        this.usuarioClienteId = usuarioClienteId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
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
