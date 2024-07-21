package dados;

import entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCliente implements IRepositorioCliente {

    private ArrayList<Pessoa> clientes;
    private Pessoa ultimoCliente;
    private static IRepositorioCliente instance;

    private RepositorioCliente() {
        clientes = new ArrayList<>();
    }

    public static IRepositorioCliente getInstance() {
        if (instance == null) {
            instance = new RepositorioCliente();
        }
        return instance;
    }
    @Override
    public void inserirCliente(Pessoa cliente) {
        clientes.add(cliente);
    }
    @Override
    public void inserirUltimoCliente(Pessoa cliente){
        ultimoCliente = cliente;
    }
    @Override
    public Pessoa obterClientePorEmail(String email) {
        for (Pessoa cliente : clientes) {
            if (cliente.getEmail().equals(email)) return cliente;
        }
        return null;
    }
    @Override
    public Pessoa obterUltimoCliente (){
        return ultimoCliente;
    }
    @Override
    public void removerCliente(String email) {
        if (email != null) {
            for (Pessoa cliente : clientes) {
                if (cliente.getEmail().equals(email)) {
                    clientes.remove(cliente);
                    break;
                }
            }
        }

    }

    @Override
    public void atualizarCliente(Pessoa clienteAntigo, Pessoa clienteNovo) {
        for (Pessoa cliente : clientes) {
            if (cliente.equals(clienteAntigo)) {
                int index = clientes.indexOf(cliente);
                clientes.set(index, clienteNovo);
                break;
            }
        }
    }
    @Override
    public List<Pessoa> listarClientes() {
        System.out.println("Listando Clientes:");
        for (Pessoa cliente : clientes) {
            System.out.println(cliente);
        }
        return clientes;
    }
}
