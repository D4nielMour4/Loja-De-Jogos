package entidades;

public enum FaixaEtaria {
    LIVRE(0), DEZ(10), DOZE(12), QUATORZE(14), DEZESSEIS(16), DEZOITO(18);

    private final int idade;

    public int getIdade() {
        return idade;
    }

    FaixaEtaria(int idade) {
        this.idade = idade;
    }
}
