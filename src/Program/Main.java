package Program;

import dados.*;
import entidades.*;
import exceptions.*;
import negocio.Fachada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ElementoInvalidoException, ElementoNuloException, SenhaFracaException, ElementoDuplicadoException, AcessoInvalidoException {
        // Instanciando Repositorios:
        RepositorioAdmin repositorioAdmin = (RepositorioAdmin) RepositorioAdmin.getInstance();
        RepositorioCliente repositorioCliente = (RepositorioCliente) RepositorioCliente.getInstance();
        RepositorioCodigo repositorioCodigo = (RepositorioCodigo) RepositorioCodigo.getInstance();
        RepositorioJogo repositorioJogo = (RepositorioJogo) RepositorioJogo.getInstance();
        RepositorioVenda repositorioVenda = (RepositorioVenda) RepositorioVenda.getInstance();

        // Gerando Pessoas
        Pessoa admin1 = new Pessoa("João", "joaoemail@gmail.com", "senha123", LocalDate.of(1999, 12, 12), true);
        Pessoa admin2 = new Pessoa("Maria", "mariaemail@yahoo.com", "senha123", LocalDate.of(1999, 12, 12), true);
        Pessoa cliente1 = new Pessoa("Vagner", "vagneremail@gmail.com", "coxinha123", LocalDate.of(2002, 5, 15), false);
        Pessoa cliente2 = new Pessoa("Bruno", "brunoemail@gmail.com", "123456", LocalDate.of(2001, 7, 15), false);

        // Gerando Jogo
        Jogo jogo1 = new Jogo("@imagens/minecraft.jpg", "Minecraft", 29.90, "Mojang Studios", "Casual", "Um jogo casual de construção", FaixaEtaria.LIVRE);
        Jogo jogo2 = new Jogo("@imagens/gtaV.jpg", "GTA V", 69.90, "Rockstar Games", "Ação", "Um jogo de ação", FaixaEtaria.DEZESSEIS);
        // Gerando itemVenda

        ItemVenda itemVenda1 = new ItemVenda(jogo1, 2);
        for (String codigo : itemVenda1.getCodigos()) {
            repositorioCodigo.inserir(codigo);
        }
        ItemVenda itemVenda2 = new ItemVenda(jogo2, 1);
        for (String codigo : itemVenda2.getCodigos()) {
            repositorioCodigo.inserir(codigo);
        }


        // Gerando Venda
        Venda venda1 = new Venda(cliente1, LocalDateTime.now());
        venda1.adicionarItemVenda(itemVenda1);
        venda1.adicionarItemVenda(itemVenda2);


        // Inserindo no repositorio
        repositorioAdmin.inserir(admin1);
        repositorioAdmin.inserir(admin2);
        repositorioCliente.inserirCliente(cliente1);
        repositorioCliente.inserirCliente(cliente2);
        repositorioJogo.inserirJogo(jogo1);
        repositorioJogo.inserirJogo(jogo2);
        repositorioVenda.inserir(venda1);

        // Listando coisas
        //Listando Admin
        List<Pessoa> admins = repositorioAdmin.listar();
        System.out.println("Admins:");
        for (Pessoa admin : admins) {
            System.out.println("Nome: " + admin.getNome() + ", Email: " + admin.getEmail());
        }
        // Removendo admin
        repositorioAdmin.excluir(admin1);
        System.out.println("\nAdmins:");
        for (Pessoa admin : admins) {
            System.out.println("Nome: " + admin.getNome() + ", Email: " + admin.getEmail());
        }

        //Listando Cliente
        List<Pessoa> clientes = repositorioCliente.listarClientes();
        System.out.println("\nClientes:");
        for (Pessoa cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome() + ", Email: " + cliente.getEmail());
        }

        // Removendo cliente
        repositorioCliente.removerCliente("brunoemail@gmail.com");
        System.out.println("\nClientes:");
        for (Pessoa cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome() + ", Email: " + cliente.getEmail());
        }
        //Listando Codigos
        List<String> codigos = repositorioCodigo.listar();
        System.out.println("\nCódigos:");
        for (String codigo : codigos) {
            System.out.println("Código: " + codigo);
        }

        //Listando Jogo
        List<Jogo> jogos = repositorioJogo.listarJogos();
        System.out.println("\nJogos:");
        for (Jogo jogo : jogos) {
            System.out.println("Nome: " + jogo.getNome() + ", Desenvolvedora: " + jogo.getDesenvolvedora());
        }

        //Removendo Jogo
        repositorioJogo.removerJogo(jogo1);
        System.out.println("\nJogos:");
        for (Jogo jogo : jogos) {
            System.out.println("Nome: " + jogo.getNome() + ", Desenvolvedora: " + jogo.getDesenvolvedora());
        }

        //listando venda
        List<Venda> vendas = repositorioVenda.listar();
        System.out.println("\nVendas:");
        System.out.println(repositorioVenda.listar());
        for (Venda venda : vendas) {
            System.out.println("Cliente: " + venda1.getCliente().getNome() + ", Data: " + venda1.getData() + ", Valor: " + venda1.calcularTotal());
            List<ItemVenda> itensVenda = venda.getItensVenda();
            System.out.println("Itens da Venda:");
            for (ItemVenda itemVenda : itensVenda) {
                System.out.println("  Jogo: " + itemVenda.getJogo().getNome() + ", Quantidade: " + itemVenda.getQuantidade());
                System.out.println("  Códigos:");
                for (String codigo : codigos) {
                    System.out.println("      Código: " + codigo);
                }
            }
        }
    }
}