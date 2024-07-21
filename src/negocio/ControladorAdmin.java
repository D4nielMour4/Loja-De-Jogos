package negocio;

import dados.IRepositorioAdmin;
import dados.RepositorioAdmin;
import entidades.Pessoa;
import exceptions.*;

import java.util.List;

public class ControladorAdmin {

    private IRepositorioAdmin repositorioAdmin;
    private static ControladorAdmin instance;

    private ControladorAdmin() {
        repositorioAdmin = RepositorioAdmin.getInstance();
    }

    public static ControladorAdmin getInstance(){
        if(instance == null){
            instance = new ControladorAdmin();
        }
        return instance;
    }

    private boolean verificarSenhaForte(String senha) {
        // Exigir pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais
        return senha.length() >= 8 && senha.matches(".*[A-Z].*") && senha.matches(".*[a-z].*")
                && senha.matches(".*\\d.*") && senha.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/~`].*");
    }

    public void inserir(Pessoa admin) throws AcessoInvalidoException, ElementoDuplicadoException, ElementoNuloException, ElementoInvalidoException, SenhaFracaException {
        if (admin == null) {
            throw new ElementoNuloException("Administrador não pode ser nulo.");
        }
        if (admin.getNome() == null) {
            throw new ElementoNuloException("Nome não pode ser nulo.");
        }
        if(admin.getSenha() == null || admin.getSenha().isEmpty()){
            throw new ElementoNuloException("Senha não pode ser nula ou vazia.");
        }
        if (!admin.isEhAdm()) {
            throw new AcessoInvalidoException("Apenas administradores podem ser cadastrados.");
        }
        if (admin.getDataNascimento() == null) {
            throw new ElementoNuloException("Data de nascimento não pode ser nula.");
        }
        if (admin.getEmail() == null) {
            throw new ElementoNuloException("Email não pode ser nulo.");
        }
        if (!admin.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ElementoInvalidoException("Email inválido, o email deve ser no formato \"email@dominio\".");
        }
        if (!verificarSenhaForte(admin.getSenha())) {
            throw new SenhaFracaException("Sua senha deve ter pelo menos 8 " +
                    "caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
        }
        if (repositorioAdmin.listar().contains(admin)) {
            throw new ElementoDuplicadoException("Esse administrador ja foi cadastrado.");
        }

        repositorioAdmin.inserir(admin);
    }

    public Pessoa obterAdminPorEmail(String email) throws ElementoNaoEncontradoException, ElementoNuloException {
        if (email == null) {
            throw new ElementoNuloException("Email não pode ser nulo.");
        }
        if (repositorioAdmin.obterAdminPorEmail(email) == null) {
            throw new ElementoNaoEncontradoException("Administrador não encontrado.");
        }
        return repositorioAdmin.obterAdminPorEmail(email);
    }

    public void listar(){
        repositorioAdmin.listar();
    }

    public void atualizar(Pessoa adminAntigo, Pessoa adminNovo) throws ElementoNaoEncontradoException, ElementoNuloException, AcessoInvalidoException, SenhaFracaException, ElementoDuplicadoException, ElementoInvalidoException {
        if (adminAntigo == null || adminNovo == null) {
            throw new ElementoNuloException("Administrador não pode ser nulo.");
        }
        List<Pessoa> admins = repositorioAdmin.listar();
        boolean adminEncontrado = false;

        for (Pessoa admin : admins) {
            if (admin.equals(adminAntigo)) {
                adminEncontrado = true;
                break;
            }
        }

        if (adminEncontrado == false) {
            throw new ElementoNaoEncontradoException("Administrador não encontrado para atualização.");
        }
        if (adminNovo.isEhAdm() == false) {
            throw new AcessoInvalidoException("Apenas administradores podem ser cadastrados.");
        }

        if (adminNovo.getNome() == null) {
            throw new ElementoNuloException("Nome não pode ser nulo.");
        }
        if(adminNovo.getSenha() == null || adminNovo.getSenha().isEmpty()){
            throw new ElementoNuloException("Senha não pode ser nula ou vazia.");
        }
        if (adminNovo.getDataNascimento() == null) {
            throw new ElementoNuloException("Data de nascimento não pode ser nula.");
        }
        if (adminNovo.getEmail() == null) {
            throw new ElementoNuloException("Email não pode ser nulo.");
        }
        if (!adminNovo.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ElementoInvalidoException("Email inválido, o email deve ser no formato \"email@dominio\".");
        }
        if (!verificarSenhaForte(adminNovo.getSenha())) {
            throw new SenhaFracaException("Sua senha deve ter pelo menos 8 " +
                    "caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
        }
        if (repositorioAdmin.listar().contains(adminNovo)) {
            throw new ElementoDuplicadoException("Esse administrador ja foi cadastrado.");
        }

        repositorioAdmin.atualizar(adminAntigo, adminNovo);
    }

    public void excluir(Pessoa adminParaExcluir) throws ElementoNaoEncontradoException, ElementoNuloException {
        if (adminParaExcluir == null) {
            throw new ElementoNuloException("Administrador não pode ser nulo.");
        }

        List<Pessoa> admins = repositorioAdmin.listar();
        boolean adminEncontrado = false;

        for (Pessoa admin : admins) {
            if (admin.equals(adminParaExcluir)) {
                adminEncontrado = true;
                break;
            }
        }

        if (adminEncontrado == false) {
            throw new ElementoNaoEncontradoException("Administrador não encontrado para exclusão.");
        }

        repositorioAdmin.excluir(adminParaExcluir);
    }
}
