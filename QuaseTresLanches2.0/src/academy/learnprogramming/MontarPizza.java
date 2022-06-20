package academy.learnprogramming;

import MenuAndItens.Menu;

public class MontarPizza extends Pizza implements MenuDemontagem {
    private double precoDoAdicional;

    public MontarPizza() {
        super();
    }

    @Override
    public void opcoesDeMontagem() {

    }

    @Override
    public double adicaoDeIngredientes() {
        int opcao;
        boolean sair = false;
        Menu menuBorda = new Menu("OPÇÕES DE BORDA");
        menuBorda.adicionarItemComValor("SIMPLES", Cardapio.PIZZA_ADICIONAL_BODRDA_SIMPLES,Cardapio.VALOR_ADICIONAL_PIZZA_SIMPLES);
        menuBorda.adicionarItemComValor("CATUPIRY", Cardapio.PIZZA_ADICIONAL_BODRDA_CATUPIRY,Cardapio.VALOR_ADICIONAL_PIZZA_CATUPIRY);
        menuBorda.imprimir();
        while (!sair) {
            opcao = menuBorda.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Uma tipo de borda deve ser selecionado");
                    break;
                case 1:
                    System.out.println("Borda simples adicionada por R$"+Cardapio.VALOR_ADICIONAL_PIZZA_SIMPLES);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_SIMPLES);
                    sair = true;
                    break;
                case 2:
                    System.out.println("Borda de caputiry adicionada por R$"+Cardapio.VALOR_ADICIONAL_PIZZA_SIMPLES);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_SIMPLES + getPrecoDoAdicional());
                    sair = true;
                    break;

            }
        }

        Menu menuRecheio = new Menu("OPÇÕES DE RECHEIO");
        menuRecheio.adicionarItemComValor("MUSSARELA", Cardapio.PIZZA_ADICIONAL_MUSSARELA,Cardapio.VALOR_ADICIONAL_PIZZA_MUSSARELA);
        menuRecheio.adicionarItemComValor("FRANGO", Cardapio.PIZZA_ADICIONAL_FRANGO,Cardapio.VALOR_ADICIONAL_PIZZA_FRANGO);
        menuRecheio.adicionarItemComValor("BACON", Cardapio.PIZZA_ADICIONAL_BACON,Cardapio.VALOR_ADICIONAL_PIZZA_BACON);
        menuRecheio.adicionarItemComValor("PRESUNTO", Cardapio.PIZZA_ADICIONAL_PRESUNTO,Cardapio.VALOR_ADICIONAL_PIZZA_PRESUNTO);
        menuRecheio.adicionarItemComValor("TOMATE", Cardapio.PIZZA_ADICIONAL_TOMATE,Cardapio.VALOR_ADICIONAL_PIZZA_TOMATE);
        menuRecheio.imprimir();
        sair = false;
        opcao = -1;
        while (!sair) {
            opcao = menuRecheio.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Adição de recheio finalizada");
                    sair = true;
                case 1:
                    System.out.println("Mussarela adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_MUSSARELA);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_MUSSARELA + getPrecoDoAdicional());
                    break;
                case 2:
                    System.out.println("Frango adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_FRANGO);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_FRANGO + getPrecoDoAdicional());
                    break;
                case 3:
                    System.out.println("Baacon adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_BACON);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_BACON + getPrecoDoAdicional());
                    break;
                case 4:
                    System.out.println("Presunto adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_PRESUNTO);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_PRESUNTO + getPrecoDoAdicional());
                    break;
                case 5:
                    System.out.println("Tomate adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_TOMATE);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_TOMATE + getPrecoDoAdicional());
                    break;
            }
        }

        Menu menuMolho = new Menu("OPÇÕES DE MOLHO");
        menuMolho.adicionarItemComValor("MOLHO TOMAMATE", Cardapio.PIZZA_ADICIONAL_MOLHO_TOMATE,Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_TOMATE);
        menuMolho.adicionarItemComValor("MOLHO PARISIENCE", Cardapio.PIZZA_ADICIONAL_MOLHO_PARISIENCE,Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_PARISIENCE);
        menuMolho.imprimir();
        sair = false;
        while (!sair) {
            opcao = menuMolho.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Um tipo de molho de ser selecionado");
                    break;
                case 1:
                    System.out.println("Molho de tomate adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_TOMATE);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_TOMATE + getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Molho parisience adicionadapor R$"+Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_PARISIENCE);
                    setPrecoDoAdicional(Cardapio.VALOR_ADICIONAL_PIZZA_MOLHO_PARISIENCE + getPrecoDoAdicional());
                    sair = true;
                    break;
            }
        }
        System.out.println("Valor da pizza personalizada: R$" + getPreco());
        return getPreco();
    }

    @Override
    public double getPreco() {
        return super.getPreco() + getPrecoDoAdicional();
    }

    @Override
    public String getSaborDaPizza() {
        return super.getSaborDaPizza();
    }


    public double getPrecoDoAdicional() {
        return precoDoAdicional;
    }

    public void setPrecoDoAdicional(double precoDoAdicional) {
        this.precoDoAdicional = precoDoAdicional;
    }
}
