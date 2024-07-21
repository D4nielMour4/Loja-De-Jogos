package entidades;

import java.util.ArrayList;

public class ItemVenda {
    private static int contadorIds = 0; // Variável estática para controle do ID autoincrementável
    private Jogo jogo;
    private int idItemVenda;
    private int quantidade;
    private double valorTotal;
    private ArrayList<String> codigos;

    public ItemVenda(Jogo jogo, int quantidade) {
        this.jogo = jogo;
        this.idItemVenda = ++contadorIds; // Incrementa o contador de IDs e atribui ao ID do item
        this.quantidade = quantidade;
        valorTotal = jogo.getValor() * quantidade;
        this.codigos = gerarCodigos(quantidade);
    }
    
    private ArrayList<String> gerarCodigos(int quantidade) {
        ArrayList<String> codigos = new ArrayList<>();

        for (int j = 0; j < quantidade; j++) {
            String codigo = gerarCodigoUnico();

            while (codigos.contains(codigo)) {
                codigo = gerarCodigoUnico();
            }

            codigos.add(codigo);
        }
        return codigos;
    }

    private String gerarCodigoUnico() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codigo = new StringBuilder();

        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 10; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }

        return codigo.toString();
    }

    public void aplicarDesconto(Promocao promocao) {
        if (promocao.getJogoEmPromocao().equals(jogo)) {
            valorTotal = promocao.calcularValorComDesconto() * quantidade;
        } else {
            throw new IllegalArgumentException("O jogo não está em promoção.");
        }
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.codigos = gerarCodigos(quantidade);
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "jogo=" + jogo +
                ", idItemVenda=" + idItemVenda +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", codigos=" + codigos +
                '}';
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }
}
