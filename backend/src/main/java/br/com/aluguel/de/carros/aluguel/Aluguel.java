package br.com.aluguel.de.carros.aluguel;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long idCliente;
    private Long idAlugador;
    private LocalDate retirada;
    private LocalDate entrega;
    private float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdAlugador() {
        return idAlugador;
    }

    public void setIdAlugador(Long idAlugador) {
        this.idAlugador = idAlugador;
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
