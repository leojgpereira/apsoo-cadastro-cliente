package com.feijaopreto.cadastroclientes.controllers;

import com.feijaopreto.cadastroclientes.database.dao.EstadoDAO;
import com.feijaopreto.cadastroclientes.models.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroClienteController implements Initializable {

    @FXML
    private Button botaoCancelar;

    @FXML
    private Button botaoConfirmar;

    @FXML
    private TextField campoBairro;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoCidade;

    @FXML
    private TextField campoDataNascimento;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoEndereco;

    @FXML
    private TextField campoFone;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoNumero;

    @FXML
    private ComboBox<Estado> listaEstados;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EstadoDAO estadoDAO = new EstadoDAO();

        List<Estado> estados = estadoDAO.recuperarTodos();
        ObservableList estadosObservableList = FXCollections.observableList(estados);
        listaEstados.getItems().clear();
        listaEstados.setPromptText("Selecione um estado...");
        listaEstados.setItems(estadosObservableList);
    }

    @FXML
    void botaoConfirmarClicado(ActionEvent event) {
        String cpf = campoCPF.getText();
        String dataNascimento = campoDataNascimento.getText();
        String nome = campoNome.getText();
        String endereco = campoEndereco.getText();
        String numero = campoNumero.getText();
        String bairro = campoBairro.getText();
        String cidade = campoCidade.getText();
        Estado estado = listaEstados.getValue();
        String fone = campoFone.getText();
        String email = campoEmail.getText();

        System.out.println(estado.getSigla());

        cadastrarCliente(cpf, dataNascimento, nome, endereco, numero, bairro, cidade, estado, fone, email);
    }

    @FXML
    void botaoCancelarClicado(ActionEvent event) {
        Stage janelaAtual = (Stage) botaoCancelar.getScene().getWindow();
        janelaAtual.close();
    }

    private void cadastrarCliente(String cpf, String dataNascimento, String nome, String endereco, String numero, String bairro, String cidade, Estado estado, String fone, String email) {
        // Desenvolver lógica de cadastro aqui
    }
}