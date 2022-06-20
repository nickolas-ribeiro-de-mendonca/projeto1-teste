package academy.learnprogramming;

import MenuAndItens.Menu;

public class Salgadinhos implements AdicionarPedidos {
    private String tipoDeSalgado;
    private double precoSalgado;

    public Salgadinhos(){

    }

    public void listaDeSalgados(){
        System.out.println("=======================================");
        System.out.println("           Opcoes de salgados");
        System.out.println("            Salgados Assados:");
        System.out.println("      1 - "+Cardapio.SALGADO_PASTEL_DE_FRANGO+" R$"+Cardapio.VALOR_PASTEL_DE_FRANGO);
        System.out.println("       2 - "+Cardapio.SALGADO_EMPADA_DE_FRANG+" R$"+Cardapio.VALOR_EMPADA_DE_FRANGO);
        System.out.println("        3 - "+Cardapio.SALGADO_EMPADA_DE_FRANG+" R$"+Cardapio.VALOR_EMPADA_DE_FRANGO);
        System.out.println("            Salgados fritos:");
        System.out.println("           4 - "+Cardapio.SALGADO_COXINHA+" R$"+Cardapio.VALOR_COXINHA);
        System.out.println("       5 - "+Cardapio.SALGADO_PASTEL_CARNE+" R$"+Cardapio.VALOR_PASTEL_DE_CARNE);
        System.out.println("           6 - Quibe "+Cardapio.SALGADO_QUIBE+" R$"+Cardapio.VALOR_QUIBE);
        System.out.println("=======================================");
    }

    public void escolherSalgado(){
        int opcao;
        Menu menu = new Menu("OPÇÕES DE SALGADOS","Salgados assados");
        menu.adicionarItemComValor("PASTELFRANGO",Cardapio.SALGADO_PASTEL_DE_FRANGO,Cardapio.VALOR_PASTEL_DE_FRANGO);
        menu.adicionarItemComValor("EMPADAFRANGO",Cardapio.SALGADO_EMPADA_DE_FRANG,Cardapio.VALOR_EMPADA_DE_FRANGO);
        menu.adicionarItemComValor("JOELHO",Cardapio.SALGADO_JOELHO_DE_MOCA,Cardapio.VALOR_JOELHO_DE_MOCA);
        menu.adicionarItemComValor("COXINHA",Cardapio.SALGADO_COXINHA,Cardapio.VALOR_COXINHA);
        menu.adicionarItemComValor("PASTELCARNE",Cardapio.SALGADO_PASTEL_CARNE,Cardapio.VALOR_PASTEL_DE_CARNE);
        menu.adicionarItemComValor("QUIBE",Cardapio.SALGADO_QUIBE,Cardapio.VALOR_QUIBE);
        menu.imprimir();
        boolean sair = false;
        while (!sair){
            System.out.println("Digitie opcao de salgado desejado: ");
            opcao = menu.lerOpcao();
            switch (opcao){
                case 0:
                    System.out.println("Retornando ao menu de pedidos...");
                    sair = true;
                    break;
                case 1:
                    setPrecoSalgado(Cardapio.VALOR_PASTEL_DE_FRANGO);
                    setTipoDeSalgado(Cardapio.SALGADO_PASTEL_DE_FRANGO);
                    sair = true;
                    break;
                case 2:
                    setPrecoSalgado(Cardapio.VALOR_EMPADA_DE_FRANGO);
                    setTipoDeSalgado(Cardapio.SALGADO_EMPADA_DE_FRANG);
                    sair = true;
                    break;
                case 3:
                    setPrecoSalgado(Cardapio.VALOR_JOELHO_DE_MOCA);
                    setTipoDeSalgado(Cardapio.SALGADO_JOELHO_DE_MOCA);
                    sair = true;
                    break;
                case 4:
                    setPrecoSalgado(Cardapio.VALOR_COXINHA);
                    setTipoDeSalgado(Cardapio.SALGADO_COXINHA);
                    sair = true;
                    break;
                case 5:
                    setPrecoSalgado(Cardapio.VALOR_PASTEL_DE_CARNE);
                    setTipoDeSalgado(Cardapio.SALGADO_PASTEL_CARNE);
                    sair = true;
                    break;
                case 6:
                    setPrecoSalgado(Cardapio.VALOR_PASTEL_DE_CARNE);
                    setTipoDeSalgado(Cardapio.SALGADO_QUIBE);
                    sair = true;
                    break;
            }
        }
        System.out.println(getTipoDeSalgado()+", adicionado por R$"+getPrecoSalgado());
        System.out.println("");
    }

    @Override
    public Pedido adicionarPedidos() {
        Pedido novoPedido = new Pedido(getTipoDeSalgado(),getPrecoSalgado());
        return novoPedido;
    }

    public String getTipoDeSalgado() {
        return tipoDeSalgado;
    }

    public void setTipoDeSalgado(String tipoDeSalgado) {
        this.tipoDeSalgado = tipoDeSalgado;
    }

    public double getPrecoSalgado() {
        return precoSalgado;
    }

    public void setPrecoSalgado(double precoSalgado) {
        this.precoSalgado = precoSalgado;
    }
}
