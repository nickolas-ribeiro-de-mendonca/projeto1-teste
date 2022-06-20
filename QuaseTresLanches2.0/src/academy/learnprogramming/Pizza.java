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
                "    delicioso molho faieto na casa");
        menu.adicionarItemComValor("CALABRESA",Cardapio.PIZZA_CALABRESA,Cardapio.CALABRESA_VALOR);
        menu.adicionarItemComValor("MARGUERITA",Cardapio.PIZZA_MARGUERITA,Cardapio.MARGUERITA_VALOR);
        menu.adicionarItemComValor("MUSSARELA",Cardapio.PIZZA_MUSSARELA,Cardapio.MUSSARELA_VALOR);
        menu.adicionarItemComValor("FRANGO",Cardapio.PIZZA_FRANGO,Cardapio.FRANGO_VALOR);
        menu.adicionarItemComValor("BACON",Cardapio.PIZZA_BACON,Cardapio.BACON_VALOR);
        menu.adicionarItemComValor("PROTUGUESA",Cardapio.PIZZA_PORTUGUESA,Cardapio.PORTUGUESA_VALOR);
        menu.adicionarItemComValor("PERSONALIZADA",Cardapio.PIZZA_PERSONALIZADA,0.0);
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
                    setPreco(Cardapio.CALABRESA_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_CALABRESA);
                    sair = true;
                    break;
                case 2:
                    setPreco(Cardapio.MARGUERITA_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_MARGUERITA);
                    sair = true;
                    break;
                case 3:
                    setPreco(Cardapio.MUSSARELA_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_MUSSARELA);
                    sair = true;
                    break;
                case 4:
                    setPreco(Cardapio.FRANGO_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_FRANGO);
                    sair = true;
                    break;
                case 5:
                    setPreco(Cardapio.BACON_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_BACON);
                    sair = true;
                    break;
                case 6:
                    setPreco(Cardapio.PORTUGUESA_VALOR);
                    setSaborDaPizza(Cardapio.PIZZA_PORTUGUESA);
                    sair = true;
                    break;
                case 7:
                    MontarPizza montarPizza = new MontarPizza();
                    setPreco(montarPizza.adicaoDeIngredientes());
                    setSaborDaPizza(Cardapio.PIZZA_PERSONALIZADA);
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
