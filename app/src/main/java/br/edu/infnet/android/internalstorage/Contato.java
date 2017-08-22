package br.edu.infnet.android.internalstorage;

import java.io.Serializable;

/**
 * Created by paulo.marinho on 01/08/17.
 */

public class Contato implements Serializable{

    private static final String FILE_NAME = "contatos.data";
    private String nome;
    private String telefone;

    /* getter e setter
    automaticamente ao colocar public
    com autocompletion, testem*/

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
