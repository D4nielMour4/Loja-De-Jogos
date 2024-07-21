package negocio;

import dados.IRepositorioVenda;
import dados.RepositorioVenda;
import entidades.ItemVenda;
import entidades.Venda;
import exceptions.ElementoDuplicadoException;
import exceptions.ElementoInvalidoException;
import exceptions.ElementoNaoEncontradoException;
import exceptions.ElementoNuloException;

import java.util.List;

public class ControladorVenda {

    private IRepositorioVenda repositorioVenda;
    private static ControladorVenda instance;

    private ControladorVenda() {
        repositorioVenda = RepositorioVenda.getInstance();
    }

    public static ControladorVenda getInstance(){
        if(instance == null){
            instance = new ControladorVenda();
        }
        return instance;
    }

    public void inserirVenda(Venda venda) throws ElementoNuloException, ElementoDuplicadoException, ElementoInvalidoException {
        //if(venda == null){
           // throw new ElementoNuloException("Venda não pode ser nula.");
        //}
        if(repositorioVenda.listar().contains(venda)){
            throw new ElementoDuplicadoException("Venda já cadastrada.");
        }
        if(venda.getItensVenda().isEmpty()){
            throw new ElementoInvalidoException("Venda não pode ser vazia.");
        }
        if(venda.getCliente() == null){
            throw new ElementoNuloException("Venda não pode ser feita sem um cliente.");
        }
        if(venda.getData() == null){
            throw new ElementoNuloException("Venda não pode ser feita sem uma data.");
        }
        for (ItemVenda itens : venda.getItensVenda()) {
            if(itens.getJogo() == null){
                throw new ElementoNuloException("Venda não pode ser feita sem um jogo.");
            }
            if(itens.getQuantidade() <= 0){
                throw new ElementoInvalidoException("Venda não pode ser feita com quantidade negativa.");
            }
        }
        repositorioVenda.inserir(venda);
    }

    public List<Venda> listarVendas(){
        return repositorioVenda.listar();
    }

    public void atualizarVenda(Venda vendaAntiga, Venda vendaNova) throws ElementoNuloException,
            ElementoInvalidoException, ElementoDuplicadoException, ElementoNaoEncontradoException {
        if(vendaAntiga == null || vendaNova == null){
            throw new ElementoNuloException("Venda não pode ser nula.");
        }

        List<Venda> vendas = repositorioVenda.listar();
        boolean vendaAntigaExiste = false;

        for (Venda venda : vendas) {
            if(venda.equals(vendaAntiga)){
                vendaAntigaExiste = true;
                break;
            }
        }

        if(vendaAntigaExiste == false){
            throw new ElementoNaoEncontradoException("Venda não encontrada.");
        }

        if(vendaNova.getItensVenda().isEmpty()){
            throw new ElementoInvalidoException("Venda não pode ser vazia.");
        }
        if(vendaNova.getCliente() == null){
            throw new ElementoNuloException("Venda não pode ser feita sem um cliente.");
        }
        if(vendaNova.getData() == null){
            throw new ElementoNuloException("Venda não pode ser feita sem uma data.");
        }
        for (ItemVenda itens : vendaNova.getItensVenda()) {
            if(itens.getJogo() == null){
                throw new ElementoNuloException("Venda não pode ser feita sem um jogo.");
            }
            if(itens.getQuantidade() <= 0){
                throw new ElementoInvalidoException("Venda não pode ser feita com quantidade negativa.");
            }
        }
        if (repositorioVenda.listar().contains(vendaNova)) {
            throw new ElementoDuplicadoException("Venda já cadastrada.");
        }
        repositorioVenda.atualizar(vendaAntiga, vendaNova);
    }

    public void excluirVenda(Venda vendaParaExcluir) throws ElementoNuloException, ElementoNaoEncontradoException {
        if(vendaParaExcluir == null){
            throw new ElementoNuloException("Venda não pode ser nula.");
        }
        List<Venda> vendas = repositorioVenda.listar();
        boolean vendaExiste = false;

        for (Venda venda : vendas) {
            if(venda.equals(vendaParaExcluir)){
                vendaExiste = true;
                break;
            }
        }

        if(vendaExiste == false){
            throw new ElementoNaoEncontradoException("Venda não encontrada.");
        }

        repositorioVenda.excluir(vendaParaExcluir);
    }
}
