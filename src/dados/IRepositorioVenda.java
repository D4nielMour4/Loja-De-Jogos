package dados;

import entidades.Venda;

import java.util.List;

public interface IRepositorioVenda {
    public void inserir (Venda venda);
    public List<Venda> listar();
    public void atualizar (Venda vendaAntiga, Venda vendaNova);
    public void excluir(Venda vendaParaExcluir);
}
