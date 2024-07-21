package dados;

import entidades.Avaliacao;

import java.util.ArrayList;
import java.util.List;


public class RepositorioAvaliacao implements IRepositorioAvaliacao {

	private ArrayList<Avaliacao> avaliacoes;
	private static IRepositorioAvaliacao instance;

	private RepositorioAvaliacao() {
		avaliacoes = new ArrayList<>();
	}
	public static IRepositorioAvaliacao getInstance(){
		if(instance == null){
			instance = new RepositorioAvaliacao();
		}
		return instance;
	}

	@Override
	public void inserir(Avaliacao avaliacao){
			avaliacoes.add(avaliacao);
		}
	@Override
	public List<Avaliacao> listar(){
		System.out.println("Listando Avaliações:");
		for (Avaliacao avaliacao : avaliacoes) {
			System.out.println(avaliacao);
		}
		return avaliacoes;
		}
	@Override
	public void atualizar(Avaliacao avaliacaoAntiga, Avaliacao novaAvaliacao) {
		for (Avaliacao avaliacao : avaliacoes) {
			if (avaliacao.equals(avaliacaoAntiga)) {
				int index = avaliacoes.indexOf(avaliacao);
				avaliacoes.set(index, novaAvaliacao);
				break;
			}
		}
	}
	@Override
	public void excluir(Avaliacao avaliacaoParaExcluir) {
		for (Avaliacao avaliacao : avaliacoes) {
			if (avaliacao.equals(avaliacaoParaExcluir)) {
				avaliacoes.remove(avaliacao);
				break;
			}
		}
	}
	}