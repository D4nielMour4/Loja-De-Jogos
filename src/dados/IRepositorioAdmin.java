package dados;

import entidades.Pessoa;
import exceptions.*;

import java.util.List;

public interface IRepositorioAdmin {
    public void inserir (Pessoa admin);
    Pessoa obterAdminPorEmail(String email);
    public List<Pessoa> listar();
    public void atualizar (Pessoa adminAntigo, Pessoa adminNovo);
    public void excluir(Pessoa adminParaExcluir);

}
