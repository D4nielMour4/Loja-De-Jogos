package dados;

import entidades.Pessoa;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAdmin implements IRepositorioAdmin {
    private ArrayList<Pessoa> admins;
    private static IRepositorioAdmin instance;
    private RepositorioAdmin() {
        admins = new ArrayList<>();
    }
    public static IRepositorioAdmin getInstance() {
        if (instance == null) {
            instance = new RepositorioAdmin();
        }
        return instance;
    }
    @Override
    public void inserir(Pessoa admin) {
        admins.add(admin);
    }
    @Override
    public Pessoa obterAdminPorEmail(String email) {
        for (Pessoa admin : admins) {
            if (admin.getEmail().equals(email)) return admin;
        }
        return null;
    }
    @Override
    public List<Pessoa> listar() {
        System.out.println("Listando Admins:");
        for (Pessoa admin : admins) {
            System.out.println(admin);
        }
        return admins;
    }
    @Override
    public void atualizar(Pessoa adminAntigo, Pessoa adminNovo) {
        admins.set(admins.indexOf(adminAntigo), adminNovo);
    }
    @Override
    public void excluir(Pessoa adminParaExcluir) {
        admins.remove(adminParaExcluir);
    }
}
