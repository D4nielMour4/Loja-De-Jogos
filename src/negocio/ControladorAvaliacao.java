package negocio;

import dados.IRepositorioAvaliacao;
import dados.RepositorioAvaliacao;
import entidades.Avaliacao;
import exceptions.ElementoDuplicadoException;
import exceptions.ElementoInvalidoException;
import exceptions.ElementoNaoEncontradoException;
import exceptions.ElementoNuloException;

import java.util.List;

public class ControladorAvaliacao {

    private IRepositorioAvaliacao repositorioAvaliacao;
    private static ControladorAvaliacao instance;

    private ControladorAvaliacao() {
        repositorioAvaliacao = RepositorioAvaliacao.getInstance();
    }

    public static ControladorAvaliacao getInstance() {
        if (instance == null) {
            instance = new ControladorAvaliacao();
        }
        return instance;
    }

    public void inserirAvaliacao(Avaliacao avaliacao) throws ElementoNuloException, ElementoInvalidoException {
        if (avaliacao == null) {
            throw new ElementoNuloException("Avaliação não pode ser nula.");
        }
        if (avaliacao.getCliente() == null) {
            throw new ElementoNuloException("Cliente não pode ser nulo.");
        }
        if (avaliacao.getJogo() == null) {
            throw new ElementoNuloException("Jogo não pode ser nulo.");
        }
        if (avaliacao.getData() == null) {
            throw new ElementoNuloException("Data não pode ser nula.");
        }
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new ElementoInvalidoException("A nota da avaliação deve estar entre 1 e 5.");
        }
        if (repositorioAvaliacao.listar().contains(avaliacao)) {
            throw new ElementoInvalidoException("Avaliação já existe.");
        } else {
            repositorioAvaliacao.inserir(avaliacao);
        }
    }

    public void listarAvaliacoes() {
        repositorioAvaliacao.listar();
    }

    public void atualizarAvaliacao(Avaliacao avaliacaoAntiga, Avaliacao novaAvaliacao) throws ElementoNuloException, ElementoInvalidoException, ElementoNaoEncontradoException, ElementoDuplicadoException {
        if (avaliacaoAntiga == null || novaAvaliacao == null) {
            throw new ElementoNuloException("Avaliação não pode ser nula.");
        }

        List<Avaliacao> avaliacoes = repositorioAvaliacao.listar();
        boolean avaliacaoEncontrada = false;

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.equals(avaliacaoAntiga)) {
                avaliacaoEncontrada = true;
                break;
            }
        }

        if (avaliacaoEncontrada == false) {
            throw new ElementoNaoEncontradoException("Avaliação não encontrada.");
        }

        if (novaAvaliacao.getCliente() == null) {
            throw new ElementoNuloException("Cliente não pode ser nulo.");
        }
        if (novaAvaliacao.getJogo() == null) {
            throw new ElementoNuloException("Jogo não pode ser nulo.");
        }
        if (novaAvaliacao.getData() == null) {
            throw new ElementoNuloException("Data não pode ser nula.");
        }
        if (repositorioAvaliacao.listar().contains(novaAvaliacao)) {
            throw new ElementoDuplicadoException("Avaliação já existe.");
        }
        if (novaAvaliacao.getNota() < 1 || novaAvaliacao.getNota() > 5) {
            throw new ElementoInvalidoException("A nota da avaliação deve estar entre 1 e 5.");
        }

        repositorioAvaliacao.atualizar(avaliacaoAntiga, novaAvaliacao);
    }


    public void excluirAvaliacao(Avaliacao avaliacaoParaExcluir) throws ElementoNuloException, ElementoNaoEncontradoException {
        if (avaliacaoParaExcluir == null) {
            throw new ElementoNuloException("Avaliação não pode ser nula.");
        }

        List<Avaliacao> avaliacoes = repositorioAvaliacao.listar();
        boolean avaliacaoEncontrada = false;

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.equals(avaliacaoParaExcluir)) {
                avaliacaoEncontrada = true;
                break;
            }
        }

        if (avaliacaoEncontrada == false) {
            throw new ElementoNaoEncontradoException("Avaliação não encontrada.");
        }

        repositorioAvaliacao.excluir(avaliacaoParaExcluir);
    }
}