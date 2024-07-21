package dados;

import entidades.Jogo;

import java.util.List;

public interface IRepositorioJogo {

        void inserirJogo(Jogo jogo);
        Jogo obterJogoPorNome(String nome);
        void removerJogo(Jogo jogo);
        void atualizarJogo(Jogo jogoAntigo, Jogo jogoNovo);
        List<Jogo> listarJogos();
}
