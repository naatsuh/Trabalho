package Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "agenda")
public class Agenda {
    public Agenda(int id, String nome, String telefone, String endereco, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public Agenda(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }
    public Agenda(){

    }

    @Id
    @Column(name = "_id")
    @GeneratedValue(generator = "agenda_cont")
    @GenericGenerator(name = "agenda_cont", strategy = "increment")
    private int id;

    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String endereco;
    @Column
    private String email;

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "[Nome: "+nome+"] [Telefone: "+telefone+"] [Endereço: "+endereco+"] [Email: "+email+"]";
    }
}

