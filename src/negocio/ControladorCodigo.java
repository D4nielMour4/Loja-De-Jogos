package negocio;

import dados.IRepositorioCodigo;
import dados.RepositorioCodigo;
import exceptions.ElementoDuplicadoException;
import exceptions.ElementoInvalidoException;
import exceptions.ElementoNuloException;

public class ControladorCodigo {

    private IRepositorioCodigo repositorioCodigo;
    private static ControladorCodigo instance;

    private ControladorCodigo() {
        repositorioCodigo = RepositorioCodigo.getInstance();
    }

    public static ControladorCodigo getInstance() {
        if (instance == null) {
            instance = new ControladorCodigo();
        }
        return instance;
    }
    public void inserir(String codigo) throws ElementoNuloException, ElementoInvalidoException, ElementoDuplicadoException {
        if(codigo == null || codigo.isEmpty()){
            throw new ElementoNuloException("O código não pode ser nulo ou vazio.");
        }
        if(codigo.length() != 10){
            throw new ElementoInvalidoException("O código deve ter 10 caracteres.");
        }
        if(!codigo.matches("[a-zA-Z0-9]+")){
            throw new ElementoInvalidoException("O código deve conter apenas letras e números.");
        }
        if(repositorioCodigo.listar().contains(codigo)){
            throw new ElementoDuplicadoException("O código já foi cadastrado.");
        }
        repositorioCodigo.inserir(codigo);
    }

    public void listar() {
        repositorioCodigo.listar();
    }
}
