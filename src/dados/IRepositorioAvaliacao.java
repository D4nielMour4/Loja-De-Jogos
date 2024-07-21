package dados;

import entidades.Avaliacao;

import java.util.List;

public interface IRepositorioAvaliacao {

	public void inserir (Avaliacao avaliacao);
	public List<Avaliacao> listar();
	public void atualizar (Avaliacao avaliacao, Avaliacao novaAvaliacao);
	public void excluir(Avaliacao avaliacaoParaExcluir);
}
