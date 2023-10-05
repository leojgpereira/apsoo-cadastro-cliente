package com.feijaopreto.cadastroclientes.models;

public class Estado {
    private Integer id;
    private String sigla;

    public Estado(Integer id, String sigla) {
        setId(id);
        setSigla(sigla);
    }

    public Estado(String sigla) {
        setSigla(sigla);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    private void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return getSigla();
    }
}
