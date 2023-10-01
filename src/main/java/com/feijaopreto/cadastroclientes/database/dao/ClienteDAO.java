package com.feijaopreto.cadastroclientes.database.dao;

import com.feijaopreto.cadastroclientes.models.Cliente;

import java.util.List;

public class ClienteDAO implements DAO<Cliente> {
    @Override
    public Cliente inserir(Cliente cliente) {
        return null;
    }

    @Override
    public void atualizar(Cliente cliente) {

    }

    @Override
    public Cliente recuperar(Integer clienteID) {
        return null;
    }

    @Override
    public List<Cliente> recuperarTodos() {
        return null;
    }

    @Override
    public void remover(Integer clienteID) {

    }
}
