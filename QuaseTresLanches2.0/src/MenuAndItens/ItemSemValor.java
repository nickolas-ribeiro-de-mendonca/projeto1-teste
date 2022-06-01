package MenuAndItens;

public class ItemSemValor {
    private String nome;
    private String texto;

    public ItemSemValor(String nome, String texto) {
        this.nome = nome;
        this.texto = texto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public String getTexto() {
        return texto;
    }
}
