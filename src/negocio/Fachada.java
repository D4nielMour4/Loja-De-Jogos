package negocio;

import entidades.*;
import exceptions.*;

import java.util.List;

public class Fachada {

    private static Fachada instance;

    private ControladorAdmin controladorAdministrador;
    private ControladorAvaliacao controladorAvaliacao;
    private ControladorCliente controladorCliente;
    private ControladorCodigo controladorCodigo;
    private ControladorJogo controladorJogo;

    private ControladorVenda controladorVenda;

    private Fachada(){
        controladorAdministrador = ControladorAdmin.getInstance();
        controladorAvaliacao = ControladorAvaliacao.getInstance();
        controladorCliente = ControladorCliente.getInstance();
        controladorCodigo = ControladorCodigo.getInstance();
        controladorJogo = ControladorJogo.getInstance();
        controladorVenda = ControladorVenda.getInstance();
    }

    public static Fachada getInstance(){
        if(instance == null){
            instance = new Fachada();
        }
        return instance;
    }

    //Administradores
    public void inserirAdmin(Pessoa admin)
            throws ElementoInvalidoException, ElementoNuloException, SenhaFracaException, ElementoDuplicadoException, AcessoInvalidoException {
        controladorAdministrador.inserir(admin);
    }

    public Pessoa obterAdminPorEmail(String email) throws ElementoNuloException, ElementoNaoEncontradoException {
        Pessoa admin = controladorAdministrador.obterAdminPorEmail(email);
        return admin;
    }

    public void listarAdmins(){
        controladorAdministrador.listar();
    }

    public void removerAdmin(Pessoa admin) throws ElementoNaoEncontradoException, ElementoNuloException {
        controladorAdministrador.excluir(admin);
    }

    public void atualizarAdmin(Pessoa adminAntigo, Pessoa adminNovo) throws ElementoNaoEncontradoException, ElementoNuloException, ElementoInvalidoException, SenhaFracaException, ElementoDuplicadoException, AcessoInvalidoException {
        controladorAdministrador.atualizar(adminAntigo, adminNovo);
    }

    //Avaliacoes
    public void inserirAvaliacao(Avaliacao avaliacao) throws ElementoNuloException, ElementoInvalidoException {
        controladorAvaliacao.inserirAvaliacao(avaliacao);
    }

    public void listarAvaliacoes(){
        controladorAvaliacao.listarAvaliacoes();
    }

    public void atualizarAvaliacao(Avaliacao avaliacaoAntiga, Avaliacao novaAvaliacao) throws ElementoNuloException, ElementoInvalidoException, ElementoNaoEncontradoException, ElementoDuplicadoException {
        controladorAvaliacao.atualizarAvaliacao(avaliacaoAntiga, novaAvaliacao);
    }

    public void excluirAvaliacao(Avaliacao avaliacao) throws ElementoNuloException, ElementoNaoEncontradoException {
        controladorAvaliacao.excluirAvaliacao(avaliacao);
    }

    //Clientes
    public void inserirCliente(Pessoa cliente) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException, SenhaFracaException, AcessoInvalidoException {
        controladorCliente.inserirCliente(cliente);
    }
    public void listarClientes(){
        controladorCliente.listarClientes();
    }

    public Pessoa obterClientePorEmail(String email) throws ElementoNuloException, ElementoInvalidoException, ElementoNaoEncontradoException {
        Pessoa pessoa = controladorCliente.obterClientePorEmail(email);
        return pessoa;
    }

    public Pessoa obterUltimoCliente(){
        Pessoa pessoa = controladorCliente.obterUltimoCliente();
        return pessoa;
    }

    public void atualizarCliente(Pessoa clienteAntigo, Pessoa clienteNovo) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException, ElementoNaoEncontradoException, SenhaFracaException, AcessoInvalidoException {
        controladorCliente.atualizarCliente(clienteAntigo, clienteNovo);
    }

    public void removerCliente(String email) throws ElementoNuloException, ElementoNaoEncontradoException {
        controladorCliente.removerCliente(email);
    }

    //Codigo
    public void inserirCodigo(String codigo) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException {
        controladorCodigo.inserir(codigo);
    }

    public void inserirUltimoCliente(Pessoa pessoa){
        controladorCliente.inserirUltimoCliente(pessoa);
    }

    public void listarCodigos(){
        controladorCodigo.listar();
    }

    //Jogos
    public void inserirJogo(Jogo jogo) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException {
        controladorJogo.inserirJogo(jogo);
    }

    public List<Jogo> listarJogos(){
        return controladorJogo.listarJogos();
    }

    public Jogo obterJogoPorNome(String nome) throws ElementoInvalidoException, ElementoNaoEncontradoException {
        Jogo jogo = controladorJogo.obterJogoPorNome(nome);
        return jogo;
    }

    public void removerJogo(Jogo jogoParaExcluir) throws ElementoNuloException, ElementoNaoEncontradoException {
        controladorJogo.removerJogo(jogoParaExcluir);
    }

    public void atualizarJogo(Jogo jogoAntigo, Jogo jogoNovo) throws ElementoNuloException, ElementoNaoEncontradoException, ElementoDuplicadoException, ElementoInvalidoException {
        controladorJogo.atualizarJogo(jogoAntigo, jogoNovo);
    }

    //Vendas
    public String obterRelatorioVendas() {
        StringBuilder relatorio = new StringBuilder();
        List<Venda> vendas = controladorVenda.listarVendas();

        for (Venda venda : vendas) {
            relatorio.append("Venda ").append(vendas.indexOf(venda)).append(":\n");
            relatorio.append(venda.toString());
        }

        return relatorio.toString();
    }
    public void inserirVenda(Venda venda) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException {
        controladorVenda.inserirVenda(venda);
    }

    public List<Venda> listarVendas(){
        return controladorVenda.listarVendas();
    }

    public void atualizarVenda(Venda vendaAntiga, Venda vendaNova) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException, ElementoNaoEncontradoException {
        controladorVenda.atualizarVenda(vendaAntiga, vendaNova);
    }

    public void removerVenda(Venda venda) throws ElementoNuloException, ElementoNaoEncontradoException {
        controladorVenda.excluirVenda(venda);
    }
}