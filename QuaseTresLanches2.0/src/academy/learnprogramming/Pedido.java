package academy.learnprogramming;


public class Pedido {
    private String pedidos;
    private double valor;


    public Pedido(String pedidos, double valor) {
        this.pedidos = pedidos;
        this.valor = valor;
    }

    public Pedido criarNovoPedido(String nome, double valor){
        return new Pedido(nome,valor);
    }

    public String getPedidos() {
        return pedidos;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("%s",pedidos)+":"+String.format("%.2f",valor);
    }
}
