package com.feijaopreto.cadastroclientes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroClienteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(CadastroClienteApplication.class.getResource("views/cadastro-cliente-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}