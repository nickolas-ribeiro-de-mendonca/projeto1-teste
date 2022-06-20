package academy.learnprogramming;

import MenuAndItens.Menu;

public class Lanche implements MenuDemontagem, AdicionarPedidos{
    private double precoDoAdicional;
    private String nomeDoLanche;

    public Lanche() {
        this.nomeDoLanche = "Hamburguer";
    }

    @Override
    public void opcoesDeMontagem() {
        System.out.println("=======================================");
        System.out.println("      Menu de opicoes de montagem");
        System.out.println("                 Pao:");
        System.out.println("          1 - "+Cardapio.LANCHE_PAO_SIMPLES+": R$"+Cardapio.VALOR_PAO_SIMPLES);
        System.out.println("          2 - "+Cardapio.LANCHE_PAO_BRIOCHE+": R$"+Cardapio.VALOR_PAO_BRIOCHE);
        System.out.println("                Recheios:");
        System.out.println("         1 - "+Cardapio.LANCHE_RECHEIO_MUSSARELA+": R$"+Cardapio.VALOR_LANCHE_MUSSARELA);
        System.out.println("           2 - "+Cardapio.LANCHE_RECHEIO_FRANGO+": R$"+Cardapio.VALOR_LANCHE_FRANGO);
        System.out.println("            3 - "+Cardapio.LANCHE_RECHEIO_BACON+": R$"+Cardapio.VALOR_LANCHE_BACON);
        System.out.println("           4 - "+Cardapio.LANCHE_RECHEIO_PRESUNTO+": R$"+Cardapio.VALOR_LANCHE_PRESUNTO);
        System.out.println("            5 - "+Cardapio.LANCHE_RECHEIO_TOMATE+": R$"+Cardapio.VALOR_LANCHE_TOMATE);
        System.out.println("          6 - "+Cardapio.LANCHE_RECHEIO_HAMBURGUER+": R$"+Cardapio.VALOR_LANCHE_HAMBURGUER);
        System.out.println("             7 - "+Cardapio.LANCHE_RECHEIO_OVO+": R$"+Cardapio.VALOR_LANCHE_OVO);
        System.out.println("                 Molhos:");
        System.out.println("         1 - "+Cardapio.LANCHE_MOLHO_CATCHUP+": R$"+Cardapio.VALOR_LANCHE_CATCHUP);
        System.out.println("      2 - "+Cardapio.LANCHE_MOLHO_ESPECIAL+": R$"+Cardapio.VALOR_LANCHE_ESPECIAL);
        System.out.println("=======================================");

    }

    @Override
    public double adicaoDeIngredientes() {
        Menu menuPao = new Menu("OPÇÕES DE PÃES");
        menuPao.adicionarItemComValor("SIMPLES",Cardapio.LANCHE_PAO_SIMPLES,Cardapio.VALOR_PAO_SIMPLES);
        menuPao.adicionarItemComValor("BRIOCHE",Cardapio.LANCHE_PAO_BRIOCHE,Cardapio.VALOR_PAO_BRIOCHE);
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
                    System.out.println("Pao simples adicionada por R$"+Cardapio.VALOR_PAO_SIMPLES);
                    setPrecoDoAdicional(Cardapio.VALOR_PAO_SIMPLES+getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Pao brioche adicionada por R$"+Cardapio.VALOR_PAO_BRIOCHE);
                    setPrecoDoAdicional(Cardapio.VALOR_PAO_BRIOCHE + getPrecoDoAdicional());
                    sair= true;
                    break;
            }
        }
        Menu menuRecheio = new Menu("OPÇÕES DE INGREDIENTES");
        menuRecheio.adicionarItemComValor("MUSSARELA",Cardapio.LANCHE_RECHEIO_MUSSARELA,Cardapio.VALOR_LANCHE_MUSSARELA);
        menuRecheio.adicionarItemComValor("FRANGO",Cardapio.LANCHE_RECHEIO_FRANGO,Cardapio.VALOR_LANCHE_FRANGO);
        menuRecheio.adicionarItemComValor("BACON",Cardapio.LANCHE_RECHEIO_BACON,Cardapio.VALOR_LANCHE_BACON);
        menuRecheio.adicionarItemComValor("PRESUNTO",Cardapio.LANCHE_RECHEIO_PRESUNTO,Cardapio.VALOR_LANCHE_PRESUNTO);
        menuRecheio.adicionarItemComValor("TOMATE",Cardapio.LANCHE_RECHEIO_TOMATE,Cardapio.VALOR_LANCHE_TOMATE);
        menuRecheio.adicionarItemComValor("HAMBURGUER",Cardapio.LANCHE_RECHEIO_HAMBURGUER,Cardapio.VALOR_LANCHE_HAMBURGUER);
        menuRecheio.adicionarItemComValor("OVO",Cardapio.LANCHE_RECHEIO_OVO,Cardapio.VALOR_LANCHE_OVO);
        menuRecheio.imprimir();
        sair = false;
        while (!sair) {
            opcao = menuRecheio.lerOpcao();
            switch (opcao) {
                case 0:
                    sair = true;
                    break;
                case 1:
                    System.out.println("Mussarela adicionadapor R$"+Cardapio.VALOR_LANCHE_MUSSARELA);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_MUSSARELA + getPrecoDoAdicional());
                    break;
                case 2:
                    System.out.println("Frango adicionadapor R$"+Cardapio.VALOR_LANCHE_FRANGO);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_FRANGO + getPrecoDoAdicional());
                    break;
                case 3:
                    System.out.println("Baacon adicionadapor R$"+Cardapio.VALOR_LANCHE_BACON);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_BACON + getPrecoDoAdicional());
                    break;
                case 4:
                    System.out.println("Presunto adicionadapor R$"+Cardapio.VALOR_LANCHE_PRESUNTO);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_PRESUNTO + getPrecoDoAdicional());
                    break;
                case 5:
                    System.out.println("Tomate adicionadapor R$"+Cardapio.VALOR_LANCHE_TOMATE);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_TOMATE+getPrecoDoAdicional());
                    break;
                case 6:
                    System.out.println("Hamburguer adicionadapor R$"+Cardapio.VALOR_LANCHE_HAMBURGUER);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_HAMBURGUER+getPrecoDoAdicional());
                    break;
                case 7:
                    System.out.println("Ovo adicionadapor R$"+Cardapio.VALOR_LANCHE_OVO);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_OVO+getPrecoDoAdicional());
                    break;
            }
        }
        Menu menuMolho = new Menu("OPÇÕES DE MELHOS");
        menuMolho.adicionarItemComValor("CATCHUP",Cardapio.LANCHE_MOLHO_CATCHUP,Cardapio.VALOR_LANCHE_CATCHUP);
        menuMolho.adicionarItemComValor("ESPECIAL",Cardapio.LANCHE_MOLHO_ESPECIAL,Cardapio.VALOR_LANCHE_ESPECIAL);
        menuMolho.imprimir();
        sair=false;
        while (!sair) {
            opcao = menuMolho.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Uma opção de molho deve ser seleciona");
                    break;
                case 1:System.out.println("Catchup adicionadapor R$"+Cardapio.VALOR_LANCHE_CATCHUP);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_CATCHUP+getPrecoDoAdicional());
                    sair = true;
                    break;
                case 2:
                    System.out.println("Molho especial adicionadapor R$"+Cardapio.VALOR_LANCHE_ESPECIAL);
                    setPrecoDoAdicional(Cardapio.VALOR_LANCHE_ESPECIAL+getPrecoDoAdicional());
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
