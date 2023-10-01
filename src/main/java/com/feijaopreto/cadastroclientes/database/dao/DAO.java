package com.feijaopreto.cadastroclientes.database.dao;

import java.util.List;

public interface DAO<T> {
    T inserir(T objeto);
    void atualizar(T objeto);
    T recuperar(Integer objetoID);
    List<T> recuperarTodos();
    void remover(Integer objetoID);
}
