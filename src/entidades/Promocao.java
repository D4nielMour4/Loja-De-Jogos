package entidades;

import java.time.LocalDate;

public class Promocao {
    private Jogo jogoEmPromocao;
    private String nome;
    private int idPromocao;
    private double desconto;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Promocao(Jogo jogoEmPromocao, String nome, double desconto, LocalDate dataInicio, LocalDate dataFim, int idPromocao) {
        this.jogoEmPromocao = jogoEmPromocao;
        this.nome = nome;
        this.idPromocao = idPromocao;
        this.setDesconto(desconto);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public double calcularValorComDesconto() {
        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isAfter(dataInicio) && dataAtual.isBefore(dataFim)) {
            return jogoEmPromocao.getValor() * (1 - desconto / 100);
        } else {
            throw new IllegalArgumentException("A promoção não está ativa.");
        }
    }

    public Jogo getJogoEmPromocao() {
        return jogoEmPromocao;
    }

    public void setJogoEmPromocao(Jogo jogoEmPromocao) {
        this.jogoEmPromocao = jogoEmPromocao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("O valor do desconto deve estar entre 0 e 100.");
        } else {
            this.desconto = desconto;
        }
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
