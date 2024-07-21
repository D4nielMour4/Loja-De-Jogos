package dados;

import entidades.Venda;

import java.util.ArrayList;
import java.util.List;

public class RepositorioVenda implements IRepositorioVenda{

    private ArrayList<Venda> vendas;
    private static IRepositorioVenda instance;

    private RepositorioVenda() {
        vendas = new ArrayList<>();
    }

    public static IRepositorioVenda getInstance(){
        if(instance == null){
            instance = new RepositorioVenda();
        }
        return instance;
    }
    @Override
    public void inserir(Venda venda) {
        if(venda != null){
            vendas.add(venda);
        }
    }
    @Override
    public List<Venda> listar() {
        System.out.println("Listando Vendas:");
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
        return vendas;
    }

    @Override
    public void atualizar(Venda vendaAntiga, Venda vendaNova) {
        for (Venda venda : vendas) {
            if (venda.equals(vendaAntiga)) {
                int index = vendas.indexOf(venda);
                vendas.set(index, vendaNova);
                break;
            }
        }
    }

    @Override
    public void excluir(Venda vendaParaExcluir) {
        for (Venda venda : vendas) {
            if (venda.equals(vendaParaExcluir)) {
                vendas.remove(venda);
                break;
            }
        }

    }
}
