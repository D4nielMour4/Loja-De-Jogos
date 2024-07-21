package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venda {
    private Pessoa cliente;
    private ArrayList<ItemVenda> itensVenda = new ArrayList();
    private LocalDateTime data;

    public Venda(Pessoa cliente, LocalDateTime data) {
        this.cliente = cliente;
        this.data = data;
    }

    public void adicionarItemVenda(ItemVenda novoItem){
        if(!itensVenda.contains(novoItem)){
            itensVenda.add(novoItem);
        }
    }

    public void removerItemVenda(ItemVenda item){
        if(itensVenda.contains(item)){
            itensVenda.remove(item);
        }
    }

    public double calcularTotal() {
        double valorFinal = 0;
        if(!itensVenda.isEmpty()){
            for (ItemVenda venda: itensVenda) {
                valorFinal += venda.getValorTotal();
            }
        }
        return valorFinal;
    }

    public String getData() {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public ArrayList<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public String listarCodigosVenda() {
        StringBuilder codigos = new StringBuilder();
        codigos.append("Itens da Venda:\n");
        for (ItemVenda itemVenda : itensVenda) {
            codigos.append("Jogo: ").append(itemVenda.getJogo().getNome()).append(", Quantidade: ").append(itemVenda.getQuantidade()).append("\n");
            codigos.append("Códigos:\n");
            for (String codigo : itemVenda.getCodigos()) {
                codigos.append("    Código: ").append(codigo).append("\n");
            }
        }
        return codigos.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = formatter.format(data);

        result.append("Venda{\n")
                .append("   Data da venda: ").append(dataFormatada).append("\n")
                .append("   Cliente: ").append(cliente.getNome()).append(" | Idade: ").append(cliente.calcularIdade()).append("\n")
                .append("   Total: ").append(calcularTotal()).append("\n")
                .append("   Itens:\n");

        result.append(String.format("%-20s | %-10s | %-10s | %-10s\n", "Nome do produto", "Preço", "Quantidade", "Total"));
        for (int i = 0; i < 61; i++) result.append("-");
        result.append("\n");

        for (ItemVenda item : itensVenda) {
            result.append(String.format("%-20s | $%-9.2f | %-9d | $%-9.2f\n",
                    item.getJogo().getNome(),
                    item.getJogo().getValor(),
                    item.getQuantidade(),
                    item.getValorTotal()));
        }

        for (int i = 0; i < 61; i++) result.append("-");
        result.append("\n");

        result.append(String.format("%-48s $%-9.2f\n", "Total da venda:", calcularTotal()));
        result.append("}");

        return result.toString();
    }
}
