package entidades;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Jogo {
    private static final AtomicInteger contadorIds = new AtomicInteger(0);

    private int id;
    private String path;
    private String nome;
    private double valor;
    private String desenvolvedora;
    private String genero;
    private String resumo;
    private FaixaEtaria faixaEtaria;

    public Jogo(String path, String nome, double valor, String desenvolvedora, String genero, String resumo, FaixaEtaria faixaEtaria) {
        this.id = contadorIds.incrementAndGet();
        this.path = path;
        this.nome = nome;
        this.valor = valor;
        this.desenvolvedora = desenvolvedora;
        this.genero = genero;
        this.resumo = resumo;
        this.faixaEtaria = faixaEtaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(FaixaEtaria faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", desenvolvedora='" + desenvolvedora + '\'' +
                ", genero='" + genero + '\'' +
                ", resumo='" + resumo + '\'' +
                ", faixaEtaria=" + faixaEtaria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return id == jogo.id && Double.compare(jogo.valor, valor) == 0 && Objects.equals(path, jogo.path) && Objects.equals(nome, jogo.nome) && Objects.equals(desenvolvedora, jogo.desenvolvedora) && Objects.equals(genero, jogo.genero) && Objects.equals(resumo, jogo.resumo) && faixaEtaria == jogo.faixaEtaria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, nome, valor, desenvolvedora, genero, resumo, faixaEtaria);
    }
}
