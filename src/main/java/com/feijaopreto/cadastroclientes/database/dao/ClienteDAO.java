package com.feijaopreto.cadastroclientes.database.dao;

import com.feijaopreto.cadastroclientes.database.conexao.Conexao;
import com.feijaopreto.cadastroclientes.models.Cliente;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

public class ClienteDAO implements DAO<Cliente> {
    @Override
    public Cliente inserir(Cliente cliente) {
        String cpf = cliente.getCpf();
        String nome = cliente.getNome();
        String rua = cliente.getRua();
        Integer numeroResidencial = cliente.getNumero();
        String cidade = cliente.getCidade();
        String bairro = cliente.getBairro();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        Date dataNascimento = cliente.getDataNascimento();

        Connection conexao = null;

        try {
            conexao = Conexao.abreConexao();

            String codeSQL = "INSERT INTO clientes (cpf, nome, rua, numero_residencia, cidade, bairro, email," +
                    "telefone, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conexao.prepareStatement(codeSQL);

            pstmt.setString(1, cpf);
            pstmt.setString(2, nome);
            pstmt.setString(3, rua);
            pstmt.setInt(4, numeroResidencial);
            pstmt.setString(5, cidade);
            pstmt.setString(6, bairro);
            pstmt.setString(7, email);
            pstmt.setString(8, telefone);
            pstmt.setString(9, dataNascimento.toString());
            pstmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cadastro");
            alert.setContentText("Cliente já cadastrado!");
            alert.showAndWait();

            return null;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro");
            alert.setContentText("Não foi possível cadastrar o cliente!");
            alert.showAndWait();

            return null;
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return cliente;
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
