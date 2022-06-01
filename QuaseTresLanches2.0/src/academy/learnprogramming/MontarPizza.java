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
        menuBorda.adicionarItemComValor("SIMPLES", "Simples", 0.0);
        menuBorda.adicionarItemComValor("CATUPIRY", "Catupiri", 5.0);
        menuBorda.imprimir();
        while (!sair) {
            opcao = menuBorda.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Uma tipo de borda deve ser selecionado");
                    break;
                case 1:
                    System.out.println("Borda simples adicionada por R$0.00");
                    setPrecoDoAdicional(0);
                    sair = true;
                    break;
                case 2:
                    System.out.println("Borda de caputiry adicionada por R$5.00");
                    setPrecoDoAdicional(5 + getPrecoDoAdicional());
                    sair = true;
                    break;

            }
        }

        Menu menuRecheio = new Menu("OPÇÕES DE RECHEIO");
        menuRecheio.adicionarItemComValor("MUSSARELA", "Mussarela", 3.0);
        menuRecheio.adicionarItemComValor("FRANGO", "Frago", 3.0);
        menuRecheio.adicionarItemComValor("BACON", "Bacon", 3.0);
        menuRecheio.adicionarItemComValor("PRESUNTO", "Presunto", 3.0);
        menuRecheio.adicionarItemComValor("TOMATE", "Tomate", 3.0);
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
                    System.out.println("Mussarela adicionadapor R$3.00");
                    setPrecoDoAdicional(3 + getPrecoDoAdicional());
                    break;
                case 2:
                    System.out.println("Frango adicionadapor R$5.00");
                    setPrecoDoAdicional(5 + getPrecoDoAdicional());
                    break;
                case 3:
                    System.out.println("Baacon adicionadapor R$6.00");
                    setPrecoDoAdicional(6 + getPrecoDoAdicional());
                    break;
                case 4:
                    System.out.println("Presunto adicionadapor R$4.00");
                    setPrecoDoAdicional(4 + getPrecoDoAdicional());
                    break;
                case 5:
                    System.out.println("Tomate adicionadapor R$2.00");
                    setPrecoDoAdicional(2 + getPrecoDoAdicional());
                    break;
            }
        }

        Menu menuMolho = new Menu("OPÇÕES DE MOLHO");
        menuMolho.adicionarItemComValor("MOLHO TOMAMATE", "Molho de Tomaete", 3.0);
        menuMolho.adicionarItemComValor("MOLHO PARISIENCE", "Molho Parisience", 5.0);
        menuMolho.imprimir();
        sair = false;
        while (!sair) {
            opcao = menuMolho.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Um tipo de molho de ser selecionado");
                    break;
                case 1:
                    System.out.println("Molho de tomate adicionadapor R$3.00");
                    setPrecoDoAdicional(3 + getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Molho parisience adicionadapor R$5.00");
                    setPrecoDoAdicional(5 + getPrecoDoAdicional());
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
