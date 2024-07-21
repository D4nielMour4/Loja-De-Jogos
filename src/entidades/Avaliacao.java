package entidades;

import java.time.LocalDateTime;

public class Avaliacao {
    private Pessoa cliente;
    private Jogo jogo;
    private int nota;
    private String comentario;
    private LocalDateTime data;

    public Avaliacao(Pessoa cliente, Jogo jogo, int nota, String comentario, LocalDateTime data) {
        this.cliente = cliente;
        this.jogo = jogo;
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDateTime.now();
    }

    public Avaliacao() {
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData(){
        return data;
    }
}