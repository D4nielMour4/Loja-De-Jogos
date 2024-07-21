package dados;

import java.util.List;

public interface IRepositorioCodigo {
    public void inserir (String codigo);
    public List<String> listar();
}
