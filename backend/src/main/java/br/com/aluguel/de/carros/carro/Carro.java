package br.com.aluguel.de.carros.carro;

import br.com.aluguel.de.carros.usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioRegistrador;
    private float kmsRodados;
    private String documentoCarro;
    private String tipoCombustivel;
    private String marca;
    private String modelo;
    private String placa;
    private double valorCarro;
    private boolean estaAlugado;

    public double getValorCarro() {
        return valorCarro;
    }

    public void setValorCarro(double valorCarro) {
        this.valorCarro = valorCarro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioRegistrador() {
        return usuarioRegistrador;
    }

    public void setUsuarioRegistrador(Usuario usuarioRegistrador) {
        this.usuarioRegistrador = usuarioRegistrador;
    }

    public float getKmsRodados() {
        return kmsRodados;
    }

    public void setKmsRodados(float kmsRodados) {
        this.kmsRodados = kmsRodados;
    }

    public String getDocumentoCarro() {
        return documentoCarro;
    }

    public void setDocumentoCarro(String documentoCarro) {
        this.documentoCarro = documentoCarro;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isEstaAlugado() {
        return estaAlugado;
    }

    public void setEstaAlugado(boolean estaAlugado) {
        this.estaAlugado = estaAlugado;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", usuarioRegistrador=" + usuarioRegistrador +
                ", kmsRodados=" + kmsRodados +
                ", documentoCarro='" + documentoCarro + '\'' +
                ", tipoCombustivel='" + tipoCombustivel + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", valorCarro=" + valorCarro +
                ", estaAlugado=" + estaAlugado +
                '}';
    }
}
