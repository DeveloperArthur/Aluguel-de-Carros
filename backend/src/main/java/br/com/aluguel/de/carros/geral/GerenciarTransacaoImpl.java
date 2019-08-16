package br.com.aluguel.de.carros.geral;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GerenciarTransacaoImpl<T> implements GerenciarTransacao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }
}