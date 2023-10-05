package com.feijaopreto.cadastroclientes.database.dao;

import com.feijaopreto.cadastroclientes.database.conexao.Conexao;
import com.feijaopreto.cadastroclientes.models.Estado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements DAO<Estado, Integer>{
    @Override
    public Estado inserir(Estado estado) {
        return null;
    }

    @Override
    public void atualizar(Estado estado) {

    }

    @Override
    public Estado recuperar(Integer estadoID) {
        return null;
    }

    @Override
    public List<Estado> recuperarTodos() {
        Connection conexao = null;
        List<Estado> estados = new ArrayList<>();

        try {
            conexao = Conexao.abreConexao();
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM estados");

            while(resultado.next()) {
                Estado estado = new Estado(resultado.getInt("id"), resultado.getString("sigla"));

                estados.add(estado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return estados;
    }

    @Override
    public void remover(Integer estadoID) {

    }
}
