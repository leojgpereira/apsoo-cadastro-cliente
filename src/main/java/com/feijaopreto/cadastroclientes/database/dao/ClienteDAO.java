package com.feijaopreto.cadastroclientes.database.dao;

import com.feijaopreto.cadastroclientes.database.conexao.Conexao;
import com.feijaopreto.cadastroclientes.models.Cliente;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAO implements DAO<Cliente, String> {
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
            alert.setTitle("Clientes");
            alert.setContentText("Cliente já cadastrado!");
            alert.showAndWait();

            return null;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Clientes");
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

            String codeSQL = "UPDATE clientes SET nome = ?, rua = ?, numero_residencia = ?, cidade = ?, bairro = ?, email = ?, telefone = ?, data_nascimento = ? WHERE cpf = ?";

            PreparedStatement pstmt = conexao.prepareStatement(codeSQL);

            pstmt.setString(1, nome);
            pstmt.setString(2, rua);
            pstmt.setInt(3, numeroResidencial);
            pstmt.setString(4, cidade);
            pstmt.setString(5, bairro);
            pstmt.setString(6, email);
            pstmt.setString(7, telefone);
            pstmt.setString(8, dataNascimento.toString());
            pstmt.setString(9, cpf);
            pstmt.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Clientes");
            alert.setContentText("Não foi possível atualizar o cliente!");
            alert.showAndWait();
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public Cliente recuperar(String clienteCPF) {
        Connection conexao = null;

        try {
            conexao = Conexao.abreConexao();
            String codeSQL = "SELECT * FROM clientes WHERE cpf = ?";
            PreparedStatement pstmt = conexao.prepareStatement(codeSQL);
            pstmt.setString(1, clienteCPF);
            pstmt.execute();

            ResultSet resultado = pstmt.getResultSet();
            Cliente cliente = new Cliente(
                    resultado.getString("cpf"),
                    resultado.getString("data_nascimento"),
                    resultado.getString("nome"),
                    resultado.getString("rua"),
                    resultado.getString("numero"),
                    resultado.getString("bairro"),
                    resultado.getString("cidade"),
                    resultado.getString("estado"),
                    resultado.getString("telefone"),
                    resultado.getString("email")
            );

            return cliente;
        } catch (SQLException e) {
            return null;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public List<Cliente> recuperarTodos() {
        Connection conexao = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conexao = Conexao.abreConexao();
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM clientes");

            while(resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getString("cpf"),
                        resultado.getString("data_nascimento"),
                        resultado.getString("nome"),
                        resultado.getString("rua"),
                        resultado.getString("numero"),
                        resultado.getString("bairro"),
                        resultado.getString("cidade"),
                        resultado.getString("estado"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                );

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return clientes;
    }

    @Override
    public void remover(String clienteCPF) {
        Connection conexao = null;

        try {
            conexao = Conexao.abreConexao();

            String codeSQL = "DELETE FROM clientes WHERE cpf = ?";

            PreparedStatement pstmt = conexao.prepareStatement(codeSQL);
            pstmt.setString(1, clienteCPF);
            pstmt.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Clientes");
            alert.setContentText("Não foi possível remover o cliente!");
            alert.showAndWait();
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
}
