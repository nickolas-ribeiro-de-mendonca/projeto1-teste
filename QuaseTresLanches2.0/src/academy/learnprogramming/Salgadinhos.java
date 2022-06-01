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
        System.out.println("      1 - Pastel de frango R$4.00");
        System.out.println("       2 - Empada de frago R$4.50");
        System.out.println("        3 - Joelho de moca R$4.00");
        System.out.println("            Salgados fritos:");
        System.out.println("           4 - Coxinha R$3.50");
        System.out.println("       5 - Pastesl de carne R$4.50");
        System.out.println("           6 - Quibe R$4.00");
        System.out.println("=======================================");
    }

    public void escolherSalgado(){
        int opcao;
        Menu menu = new Menu("OPÇÕES DE SALGADOS","Salgados assados");
        menu.adicionarItemComValor("PASTELFRANGO","Pastel de frango",4.0);
        menu.adicionarItemComValor("EMPADAFRANGO","Empada de Frango",4.5);
        menu.adicionarItemComValor("JOELHO","Joelho de Moça",4.0);
        menu.adicionarItemComValor("COXINHA","Coxinha",3.5);
        menu.adicionarItemComValor("PASTELCARNE","Pastel de Carne",4.5);
        menu.adicionarItemComValor("QUIBE","Quibe",4.0);
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
                    setPrecoSalgado(4);
                    setTipoDeSalgado("Pastel assado");
                    sair = true;
                    break;
                case 2:
                    setPrecoSalgado(4.5);
                    setTipoDeSalgado("Empada de frago");
                    sair = true;
                    break;
                case 3:
                    setPrecoSalgado(4);
                    setTipoDeSalgado("Joelho de moça");
                    sair = true;
                    break;
                case 4:
                    setPrecoSalgado(3.5);
                    setTipoDeSalgado("Coxinha");
                    sair = true;
                    break;
                case 5:
                    setPrecoSalgado(4.5);
                    setTipoDeSalgado("Pastel de carne");
                    sair = true;
                    break;
                case 6:
                    setPrecoSalgado(4);
                    setTipoDeSalgado("Quibe");
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
