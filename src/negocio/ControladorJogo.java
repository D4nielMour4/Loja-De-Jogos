package negocio;

import dados.IRepositorioJogo;
import dados.RepositorioJogo;
import entidades.FaixaEtaria;
import entidades.Jogo;
import exceptions.ElementoDuplicadoException;
import exceptions.ElementoInvalidoException;
import exceptions.ElementoNaoEncontradoException;
import exceptions.ElementoNuloException;

import java.util.List;

public class ControladorJogo {

    private IRepositorioJogo repositorioJogo;
    private static ControladorJogo instance;

    private ControladorJogo() {
        repositorioJogo = RepositorioJogo.getInstance();
    }

    public static ControladorJogo getInstance(){
        if(instance == null){
            instance = new ControladorJogo();
        }
        return instance;
    }
    public void inserirJogo(Jogo jogo) throws ElementoNuloException, ElementoDuplicadoException, ElementoInvalidoException {
        if(jogo == null){
            throw new ElementoNuloException("Jogo não pode ser nulo");
        }
        if(jogo.getNome() == null || jogo.getNome().isEmpty()){
            throw new ElementoNuloException("Nome do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getDesenvolvedora() == null || jogo.getDesenvolvedora().isEmpty()){
            throw new ElementoNuloException("Desenvolvedora do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getGenero() == null || jogo.getGenero().isEmpty()){
            throw new ElementoNuloException("Genero do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getResumo() == null || jogo.getResumo().isEmpty()){
            throw new ElementoNuloException("Resumo do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getFaixaEtaria() == null){
            throw new ElementoNuloException("Faixa etaria do jogo não pode ser nulo");
        }
        if (jogo.getValor() == 0 || jogo.getValor() < 0){
            throw new ElementoInvalidoException("Valor do jogo não pode ser zero ou negativo");
        }
        if (jogo.getId() == 0 || jogo.getId() < 0){
            throw new ElementoInvalidoException("Id do jogo não pode ser zero ou negativo");
        }
        if(jogo.getFaixaEtaria() != FaixaEtaria.LIVRE && jogo.getFaixaEtaria() != FaixaEtaria.DEZ && jogo.getFaixaEtaria() != FaixaEtaria.DOZE && jogo.getFaixaEtaria() != FaixaEtaria.QUATORZE && jogo.getFaixaEtaria() != FaixaEtaria.DEZESSEIS && jogo.getFaixaEtaria() != FaixaEtaria.DEZOITO){
            throw new ElementoInvalidoException("Faixa etaria do jogo não pode ser diferente das opções disponiveis");
        }
        if(repositorioJogo.obterJogoPorNome(jogo.getNome()) != null){
            throw new ElementoDuplicadoException("Jogo já cadastrado");
        }
        repositorioJogo.inserirJogo(jogo);
    }

    public Jogo obterJogoPorNome(String nome) throws ElementoNaoEncontradoException {
        if (repositorioJogo.obterJogoPorNome(nome) == null){
            throw new ElementoNaoEncontradoException("Jogo não encontrado");
        }
        return repositorioJogo.obterJogoPorNome(nome);
    }

    public void removerJogo(Jogo jogoParaExcluir) throws ElementoNuloException, ElementoNaoEncontradoException {
        if (jogoParaExcluir == null){
            throw new ElementoNuloException("Jogo não pode ser nulo");
        }

        List<Jogo> jogos = repositorioJogo.listarJogos();
        boolean jogoEncontrado = false;

        for (Jogo jogo : jogos) {
            if (jogo.equals(jogoParaExcluir)) {
                jogoEncontrado = true;
                break;
            }
        }

        if (jogoEncontrado == false) {
            throw new ElementoNaoEncontradoException("Jogo não encontrado.");
        }
        repositorioJogo.removerJogo(jogoParaExcluir);
    }

    public void atualizarJogo(Jogo jogoAntigo, Jogo jogoNovo) throws ElementoNuloException, ElementoNaoEncontradoException, ElementoDuplicadoException, ElementoInvalidoException {
        if (jogoAntigo == null || jogoNovo == null){
            throw new ElementoNuloException("Jogo não pode ser nulo");
        }

        List<Jogo> jogos = repositorioJogo.listarJogos();
        boolean jogoEncontrado = false;

        for (Jogo jogo : jogos) {
            if (jogo.equals(jogoAntigo)) {
                jogoEncontrado = true;
                break;
            }
        }

        if (jogoEncontrado == false) {
            throw new ElementoNaoEncontradoException("Jogo não encontrado.");
        }

        if (jogoNovo.getNome() == null || jogoNovo.getNome().isEmpty()){
            throw new ElementoNuloException("Nome do jogo não pode ser nulo ou vazio");
        }
        if (jogoNovo.getDesenvolvedora() == null || jogoNovo.getDesenvolvedora().isEmpty()){
            throw new ElementoNuloException("Desenvolvedora do jogo não pode ser nulo ou vazio");
        }
        if (jogoNovo.getGenero() == null || jogoNovo.getGenero().isEmpty()){
            throw new ElementoNuloException("Genero do jogo não pode ser nulo ou vazio");
        }
        if (jogoNovo.getResumo() == null || jogoNovo.getResumo().isEmpty()){
            throw new ElementoNuloException("Resumo do jogo não pode ser nulo ou vazio");
        }
        if (jogoNovo.getFaixaEtaria() == null){
            throw new ElementoNuloException("Faixa etaria do jogo não pode ser nulo");
        }
        if (jogoNovo.getValor() == 0 || jogoNovo.getValor() < 0){
            throw new ElementoInvalidoException("Valor do jogo não pode ser zero ou negativo");
        }
        if (jogoNovo.getId() == 0 || jogoNovo.getId() < 0){
            throw new ElementoInvalidoException("Id do jogo não pode ser zero ou negativo");
        }
        if (jogoNovo.getFaixaEtaria() != FaixaEtaria.LIVRE && jogoNovo.getFaixaEtaria() != FaixaEtaria.DEZ && jogoNovo.getFaixaEtaria() != FaixaEtaria.DOZE && jogoNovo.getFaixaEtaria() != FaixaEtaria.QUATORZE && jogoNovo.getFaixaEtaria() != FaixaEtaria.DEZESSEIS && jogoNovo.getFaixaEtaria() != FaixaEtaria.DEZOITO){
            throw new ElementoInvalidoException("Faixa etaria do jogo não pode ser diferente das opções disponiveis");
        }
        repositorioJogo.atualizarJogo(jogoAntigo, jogoNovo);
    }

    public List<Jogo> listarJogos(){
        return repositorioJogo.listarJogos();
    }
}

