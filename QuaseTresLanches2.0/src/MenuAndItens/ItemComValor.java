package MenuAndItens;

public class ItemComValor extends ItemSemValor {

    private double preco;

    public ItemComValor(String nome, String texto, double preco) {
        super(nome, texto);
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
