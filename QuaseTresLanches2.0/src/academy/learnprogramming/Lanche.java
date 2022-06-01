package academy.learnprogramming;

import MenuAndItens.Menu;

public class Lanche implements MenuDemontagem, AdicionarPedidos{
    private double precoDoAdicional;
    private String nomeDoLanche;

    public Lanche() {
        this.nomeDoLanche = "Lanche Personalizado";

    }

    @Override
    public void opcoesDeMontagem() {
        System.out.println("=======================================");
        System.out.println("      Menu de opicoes de montagem");
        System.out.println("                 Pao:");
        System.out.println("          1 - Simpes: R$2,00");
        System.out.println("          2 - Brioche: R$5,00");
        System.out.println("                Recheios:");
        System.out.println("         1 - Mussarela: R$2.00");
        System.out.println("           2 - Frango: R$2.50");
        System.out.println("            3 - Bacon: R$3.00");
        System.out.println("           4 - Presunto: R$2.00");
        System.out.println("            5 - Tomate: R$2.00");
        System.out.println("          6 - Hamburguer: R$6.00");
        System.out.println("             7 - Ovo: R$2.00");
        System.out.println("                 Molhos:");
        System.out.println("         1 - Catchup: R$1.00");
        System.out.println("      2 - Molho esespecia: R$3.00");
        System.out.println("=======================================");

    }

    @Override
    public double adicaoDeIngredientes() {
        Menu menuPao = new Menu("OPÇÕES DE PÃES");
        menuPao.adicionarItemComValor("SIMPLES","Pão Simples",2.0);
        menuPao.adicionarItemComValor("BRIOCHE","Pão Brioche",5.0);
        menuPao.imprimir();
        boolean sair = false;
        int opcao;
        while (!sair) {
            opcao = menuPao.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Uma opção de pão deve ser selecionada");
                    break;
                case 1:
                    System.out.println("Pao simples adicionada por R$2.00");
                    setPrecoDoAdicional(2+getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Pao brioche adicionada por R$5.00");
                    setPrecoDoAdicional(5 + getPrecoDoAdicional());
                    sair= true;
                    break;
            }
        }
        Menu menuRecheio = new Menu("OPÇÕES DE INGREDIENTES");
        menuRecheio.adicionarItemComValor("MUSSARELA","Mussarela",2.0);
        menuRecheio.adicionarItemComValor("FRANGO","Frango",2.5);
        menuRecheio.adicionarItemComValor("BACON","Bacon",3.0);
        menuRecheio.adicionarItemComValor("PRESUNTO","Presunto",2.0);
        menuRecheio.adicionarItemComValor("TOMATE","Tomate",2.0);
        menuRecheio.adicionarItemComValor("HAMBURGUER","Hamburguer",6.0);
        menuRecheio.adicionarItemComValor("OVO","Ovo",2.0);
        menuRecheio.imprimir();
        sair = false;
        while (!sair) {
            opcao = menuRecheio.lerOpcao();
            switch (opcao) {
                case 0:
                    sair = true;
                    break;
                case 1:
                    System.out.println("Mussarela adicionadapor R$2.00");
                    setPrecoDoAdicional(2 + getPrecoDoAdicional());
                    break;
                case 2:
                    System.out.println("Frango adicionadapor R$2.50");
                    setPrecoDoAdicional(2.5 + getPrecoDoAdicional());
                    break;
                case 3:
                    System.out.println("Baacon adicionadapor R$3.00");
                    setPrecoDoAdicional(3 + getPrecoDoAdicional());
                    break;
                case 4:
                    System.out.println("Presunto adicionadapor R$2.00");
                    setPrecoDoAdicional(2 + getPrecoDoAdicional());
                    break;
                case 5:
                    System.out.println("Tomate adicionadapor R$2.00");
                    setPrecoDoAdicional(2+getPrecoDoAdicional());
                    break;
                case 6:
                    System.out.println("Hamburguer adicionadapor R$6.00");
                    setPrecoDoAdicional(6+getPrecoDoAdicional());
                    break;
                case 7:
                    System.out.println("Ovo adicionadapor R$2.00");
                    setPrecoDoAdicional(2+getPrecoDoAdicional());
                    break;
            }
        }
        Menu menuMolho = new Menu("OPÇÕES DE MELHOS");
        menuMolho.adicionarItemComValor("CATCHUP","Catchuo",1.0);
        menuMolho.adicionarItemComValor("ESPECIAL","Molho Especial",3.0);
        menuMolho.imprimir();
        sair=false;
        while (!sair) {
            opcao = menuMolho.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Uma opção de molho deve ser seleciona");
                    break;
                case 1:System.out.println("Catchup adicionadapor R$1.00");
                    setPrecoDoAdicional(1+getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Molho especial adicionadapor R$3.00");
                    setPrecoDoAdicional(3+getPrecoDoAdicional());
                    sair= true;
                    break;
            }
        }
        System.out.println(getNomeDoLanche()+", adicionado por R$"+getPrecoDoAdicional());
        System.out.println(" ");
        return getPrecoDoAdicional();
    }

    @Override
    public Pedido adicionarPedidos() {
        Pedido novoPedido = new Pedido(getNomeDoLanche(),getPrecoDoAdicional());
        return novoPedido;
    }

    public double getPrecoDoAdicional() {
        return precoDoAdicional;
    }

    public void setPrecoDoAdicional(double precoDoAdicional) {
        this.precoDoAdicional = precoDoAdicional;
    }

    public String getNomeDoLanche() {
        return nomeDoLanche;
    }

}
