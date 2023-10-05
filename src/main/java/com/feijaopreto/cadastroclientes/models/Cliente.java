package com.feijaopreto.cadastroclientes.models;

import com.feijaopreto.cadastroclientes.exceptions.CampoInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class Cliente {
    private String cpf;
    private String nome;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private Estado estado;
    private String telefone;
    private String email;
    private Date dataNascimento;


    public Cliente(String cpf, String dataNascimento, String nome, String endereco, String numero, String bairro, String cidade, Estado estado, String fone, String email) throws CampoInvalidoException {
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setNome(nome);
        setRua(endereco);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setTelefone(fone);
        setEmail(email);
    }

    public Cliente(String cpf, String dataNascimento, String nome, String endereco, String numero, String bairro, String cidade, String estado, String fone, String email) {
        this.cpf = cpf;

        try {
            this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
        } catch (ParseException e) {
            this.dataNascimento = null;
        }

        this.nome = nome;
        this.rua = endereco;
        this.numero = Integer.parseInt(numero);
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = fone;
        this.email = email;
        this.estado = new Estado(estado);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws CampoInvalidoException {
        if(cpf != "") {
            if(Pattern.matches("\\d{11}", cpf)) {
                this.cpf = cpf;
            } else {
                throw new CampoInvalidoException("CPF inválido!");
            }
        } else {
            throw new CampoInvalidoException("CPF não preenchido!");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoInvalidoException {
        if(nome != "") {
            this.nome = nome;
        } else {
            throw new CampoInvalidoException("Nome não preenchido!");
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws CampoInvalidoException {
        try {
            this.numero = Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            throw new CampoInvalidoException("Número inválido!");
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) throws CampoInvalidoException {
        if(estado != null) {
            this.estado = estado;
        } else {
            throw new CampoInvalidoException("Estado não informado!");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws CampoInvalidoException {
        if(telefone != "") {
            this.telefone = telefone;
        } else {
            throw new CampoInvalidoException("Telefone não preenchido!");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws CampoInvalidoException {
        if(email != "") {
            this.email = email;
        } else {
            throw new CampoInvalidoException("Email não preenchido!");
        }
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) throws CampoInvalidoException {
        if(dataNascimento != null && dataNascimento != "") {
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataNascimento, formato);
                this.dataNascimento = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                throw new CampoInvalidoException("Data de nascimento inválida!");
            }
        } else {
            throw new CampoInvalidoException("Data de nascimento não preenchida!");
        }
    }
}
