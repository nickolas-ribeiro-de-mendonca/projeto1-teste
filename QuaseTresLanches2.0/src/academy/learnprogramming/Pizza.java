package academy.learnprogramming;

import MenuAndItens.Menu;

public class Pizza  implements AdicionarPedidos{
    private String saborDaPizza;
    private double preco;


    public Pizza() {
        this.saborDaPizza = saborDaPizza;
        this.preco = preco;
    }

    public void escolhaDePizza(){
        Menu menu = new Menu("MENU DE PIZZAS","Todas as pizzas contam com um\n" +
                "    delicioso moho faieto na casa");
        menu.adicionarItemComValor("CALABRESA","Calabresa",30.0);
        menu.adicionarItemComValor("MARGUERITA","Marguerita",25.0);
        menu.adicionarItemComValor("MUSSARELA","Mussarela",20.0);
        menu.adicionarItemComValor("FRANGO","Frango",25.0);
        menu.adicionarItemComValor("BACON","Bacon",28.0);
        menu.adicionarItemComValor("PROTUGUESA","Porturguesa",35.0);
        menu.adicionarItemComValor("PERSONALIZADA","A Moda do Cliente",0.0);
        menu.imprimir();
        int opcao;
        boolean sair = false;
        while (!sair) {
            opcao = menu.lerOpcao();
            switch (opcao) {
                case 0:
                    sair = true;
                    System.out.println("Retornando ao menu  de pedidos...");
                    break;
                case 1:
                    setPreco(30);
                    setSaborDaPizza("Calabresa");
                    sair = true;
                    break;
                case 2:
                    setPreco(25);
                    setSaborDaPizza("Marguerita");
                    sair = true;
                    break;
                case 3:
                    setPreco(20);
                    setSaborDaPizza("Mussarela");
                    sair = true;
                    break;
                case 4:
                    setPreco(25);
                    setSaborDaPizza("Frango");
                    sair = true;
                    break;
                case 5:
                    setPreco(28.00);
                    setSaborDaPizza("Bacon");
                    sair = true;
                    break;
                case 6:
                    setPreco(35);
                    setSaborDaPizza("Portuguesa");
                    sair = true;
                    break;
                case 7:
                    MontarPizza montarPizza = new MontarPizza();
                    setPreco(montarPizza.adicaoDeIngredientes());
                    setSaborDaPizza("A Moda do Cliente");
                    sair = true;
                    break;
            }
            if(opcao != 0) {
                System.out.println("Pizza sabor " + getSaborDaPizza() + ", adicionada - R$" + getPreco());
            }
            System.out.println(" ");
        }
    }

    @Override
    public Pedido adicionarPedidos() {
        Pedido novoPedido = new Pedido(getSaborDaPizza(),getPreco());
        return novoPedido;
    }

    public String getSaborDaPizza() {
        return saborDaPizza;
    }

    public void setSaborDaPizza(String saborDaPizza) {
        this.saborDaPizza = saborDaPizza;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
