package br.com.aluguel.de.carros.carro;

import br.com.aluguel.de.carros.alugador.Alugador;
import br.com.aluguel.de.carros.usuario.Usuario;

public class CarroDto {
    private Long id;
    private Usuario usuario;
    private float kmsRodados;
    private String documentoCarro;
    private String tipoCombustivel;
    private String marca;
    private String modelo;
    private String placa;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
