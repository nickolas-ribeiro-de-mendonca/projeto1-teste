package academy.learnprogramming;

import MenuAndItens.Menu;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static DataSource dataSource = new DataSource();

    static int opcao;

    public static void main(String[] args) {
        dataSource.open();
        menuInicial();
        dataSource.close();
    }

    public static void menuInicial() {
        Menu menu = new Menu("QUASE TRES LANCHES");
        menu.adicionarItemSemValor("GERENCIAR", "Gerenciar Clientes");
        menu.adicionarItemSemValor("ADICIONAR", "Adicionar Pedidos");
        menu.imprimir();
        opcao = menu.lerOpcao();
        switch (opcao) {
            case 0:
                System.out.println("Finalizando Prgrama...");
                System.exit(0);
                break;
            case 1:
                menuGerenciarclientes();
                break;
            case 2:
                menuAdicionarPedido();
                break;
        }
    }

    public static void menuGerenciarclientes() {
        Menu menu = new Menu("GERRENCIAR CLIENTES");
        menu.adicionarItemSemValor("LISTA DE CLIENTES", "Imprimir lista de Clientens");
        menu.adicionarItemSemValor("LISTA DE PEDIDOS", "Imprimir Pedidos do Cliente");
        menu.adicionarItemSemValor("ADICIONAR CLIENTE", "Adicionar Cliente");
        menu.adicionarItemSemValor("EDITAR CLIENTE", "Editar Dados do Cliente");
        boolean sair = false;
        while (!sair) {
            menu.imprimir();
            opcao = menu.lerOpcao();
            switch (opcao) {
                case 0:
                    System.out.println("Voltando ao muenu inicial...");
                    System.out.println("");
                    menuInicial();
                    break;
                case 1:
                    dataSource.mostrarListaDeClientes();
                    break;
                case 2:
                    System.out.print("Digite o nome completo do cliente: ");
                    String nome = scanner.nextLine();
                    dataSource.mostrarPedidosDoCliente(nome);
                    break;
                case 3:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    dataSource.adicionarCliente(nome);
                    break;
                case 4:
                    editarDadosDoCliente();
                    break;
            }
        }
        menuInicial();
    }

    public static void editarDadosDoCliente() {
        System.out.print("Digite o nome competo do cliente: ");
        String nome = scanner.nextLine();
        if (dataSource.clienteCadastrado(nome)) {
            Cliente antigoCliente  = dataSource.buscarDadosDoCliente(nome);
            System.out.println("Dados do cliente:");
            System.out.println("1- Endereco: " + antigoCliente.getEnderecoDoCliente());
            System.out.println("2- Telefone: " + antigoCliente.getNumeroDeTelefoneDoCliente());
            System.out.println("3- CPF: " + antigoCliente.getCpf());
            System.out.print("Indice do dado a ser editado: ");
            boolean sair = false;
            while (!sair) {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o novo endereço: ");
                        String novoEndereco = scanner.nextLine();
                        antigoCliente.setEnderecoDoCliente(novoEndereco);
                        dataSource.setAtualizarEnderecoCliente(nome,novoEndereco);
                        sair = true;
                        break;
                    case 2:
                        System.out.print("Digite o novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        antigoCliente.setNumeroDeTelefoneDoCliente(novoTelefone);
                        dataSource.setAtualizarTelefoneCliente(nome,novoTelefone);
                        sair = true;
                        break;
                    case 3:
                        System.out.println("Digite o novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        antigoCliente.setCpf(novoCpf);
                        dataSource.setAtualizarCpfCliente(nome,novoCpf);
                        sair = true;
                        break;
                    default:
                        System.out.println("Digite uma opcao valida.");
                        break;
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuAdicionarPedido() {
        Menu menu = new Menu("ADICIONAR PEDIDO");
        menu.adicionarItemSemValor("PIZZA", "Opções de Pizzas");
        menu.adicionarItemSemValor("LANCHES", "Opções de Lanches");
        menu.adicionarItemSemValor("SALGADOS", "Opções de Salgados");
        menu.adicionarItemSemValor("ITENS NO CARRINHO", "Imprimir Itens no Carrinho");
        menu.adicionarItemSemValor("EXLCLUIR ITEM DO CARRINHO", "Excluir Item no Carrinho");
        long numeroDoPedido = dataSource.buscarNumeroDoPedidoAtual();
        boolean sair = false;
        while (!sair) {
            menu.imprimir();
            opcao = menu.lerOpcao();
            switch (opcao) {
                case 0:
                    finalizarPedidio(numeroDoPedido);
                    System.out.println();
                    sair = true;
                    break;
                case 1:
                    Pedido pedidoPizza = adicionarPizza();
                    if (pedidoPizza.getPedidos()!=null){
                        dataSource.adicionarPedidoUnitario(pedidoPizza,numeroDoPedido);
                    }
                    break;
                case 2:
                    Pedido pedidoLanche = adicionarLanche();
                    if (pedidoLanche.getPedidos()!=null){
                        dataSource.adicionarPedidoUnitario(pedidoLanche,numeroDoPedido);
                    }
                    break;
                case 3:
                    Pedido pedidoSalgadinho = adicionarSalgadinho();
                    if (pedidoSalgadinho.getPedidos()!=null){
                        dataSource.adicionarPedidoUnitario(pedidoSalgadinho,numeroDoPedido);
                    }
                    break;
                case 4:
                    dataSource.mostrarIntensNoCarrinho(numeroDoPedido);
                    break;
                case 5:
                    dataSource.excluirItemDoCarrinho(numeroDoPedido);
                    break;
                default:
                    System.out.print("Digite um valor valido: ");
                    break;
            }
        }
        menuInicial();
    }

    public static Pedido adicionarPizza() {
        Pizza pizza = new Pizza();
        pizza.escolhaDePizza();
        return pizza.adicionarPedidos();
    }

    public static Pedido adicionarLanche() {
        Lanche lanche = new Lanche();
        lanche.adicaoDeIngredientes();
        return lanche.adicionarPedidos();
    }

    public static Pedido adicionarSalgadinho() {
        Salgadinhos salgadinhos = new Salgadinhos();
        salgadinhos.escolherSalgado();
        return salgadinhos.adicionarPedidos();
    }

    public static void finalizarPedidio(long numeroDoPedido) {
        System.out.println("======================================");
        System.out.println("         CONCLUSAO DO PEDIDO");
        String nome = null;
        System.out.println("Cliente ja possui registro na loja? ");
        System.out.print("1 - SIM, 2 - NAO :");
        boolean entrega = false;
        boolean sair = false;
        int opcao = 0;
        String tipoDePagamento = null;
        double valorDisponibilizado = 0;
        double troco = 0;

        while (!sair) {
            opcao = checarSeEnumero();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    if (dataSource.clienteCadastrado(nome)) {
                        System.out.println("Cliente encontrado!");
                    } else {
                        System.out.println("Cliente nao foi encontrado\n" +
                                "Necessario realizar o cadastro");
                        dataSource.adicionarCliente(nome);
                    }
                    sair = true;
                    break;
                case 2:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    dataSource.adicionarCliente(nome);
                    sair = true;
                    break;
                default:
                    System.out.print("Digite uma opcao valida: ");
                    break;
            }
        }

        dataSource.adicionarPedidoAoCliente(nome,numeroDoPedido);

        System.out.println("");
        System.out.println("Pedido para entrega ou retirada no local: ");
        System.out.print("1 - ENTRGA, 2 - RETIRADA: ");
        sair = false;
        double taxa = 0;
        while (!sair) {
            opcao = checarSeEnumero();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Uma taxa de entrega de R$5,00 sera cobrada");
                    taxa = 5;
                    entrega = true;
                    sair = true;
                    break;
                case 2:
                    System.out.println("Retirada no local selecionado");
                    entrega = false;
                    sair = true;
                    break;
                default:
                    System.out.print("Digite um valor valido: ");
                    break;
            }
        }
        double totalDoPedido = taxa + dataSource.somaValorDoPedido(numeroDoPedido);
        System.out.println("Valor total R$" + totalDoPedido);
        System.out.println("");
        System.out.println("Pagamento sera realizado em:");
        System.out.print("1- CARTAO DE CREDITO 2- DINHEIRO: ");
        sair = false;
        while (!sair) {
            opcao = checarSeEnumero();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Pagamento com cartao selecionado");
                    tipoDePagamento = "CARTAO";
                    sair = true;
                    break;
                case 2:
                    System.out.println("Pagamento em dinheiro selecionado");
                    tipoDePagamento = "DINHEIRO";
                    System.out.println("Sera necessario pagameno troco? ");
                    System.out.print("1- SIM 2- NAO: ");
                    while (!sair) {
                        opcao = checarSeEnumero();
                        scanner.nextLine();
                        switch (opcao) {
                            case 1:
                                System.out.print("Digite o valor disponibilizado: ");
                                while (true) {
                                    boolean eUmInteiro = scanner.hasNextDouble();
                                    if (eUmInteiro) {
                                        valorDisponibilizado = scanner.nextDouble();
                                        if (valorDisponibilizado >= totalDoPedido) {
                                            troco = valorDisponibilizado - totalDoPedido;
                                            System.out.println("Troco: R$" + troco);
                                            break;
                                        } else {
                                            System.out.print("Valor invalido, digite um novo valor: ");
                                            scanner.nextLine();
                                        }
                                    } else {
                                        System.out.println("Apenas números são permitidos nesse menu.");
                                        System.out.print("Digite um novo valor: ");
                                        scanner.nextLine();
                                    }
                                }
                                scanner.nextLine();
                                sair = true;
                                break;
                            case 2:
                                System.out.println("Troco nao sera disponibilizado");
                                troco = 0;
                                sair = true;
                                break;
                            default:
                                System.out.print("Digite uma opcao valida: ");
                                break;

                        }
                    }
                default:
                    System.out.print("Digite uma opcao valida: ");
                    break;
            }
        }
        printConclusao(entrega, taxa, troco, tipoDePagamento, totalDoPedido, valorDisponibilizado, nome,numeroDoPedido);
    }

    public static void printConclusao(boolean entrega, double taxa, double troco, String tipoDePagamento, double total,
                                      double valorDispoibilizado,String nome,long numeroDoPedido) {
        System.out.println(" ");
        System.out.println("***********************************");
        System.out.println("         PEDIDO FINALIZADO         ");
        Cliente cliente = dataSource.buscarDadosDoCliente(nome);
        System.out.println("NOME: " + cliente.getNomeDoCliente());
        if (entrega) {
            System.out.println("ENDERECO: " + cliente.getEnderecoDoCliente());
        }
        ArrayList<Pedido> pedidos = dataSource.itensDentroDoPedido(numeroDoPedido);
        System.out.println("         ITENS DO CARRINHO");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-25sR$%.2f", pedido.getPedidos(), pedido.getValor());
            System.out.println(" ");
        }
        if (taxa != 0) {
            System.out.printf("%-25sR$%.2f", "Taxa de entrega", taxa);
            System.out.println("");
        }
        System.out.printf("%-25sR$%.2f", "Total", total);
        System.out.println("");
        System.out.println("TIPO DE PAGAMENTO: " + tipoDePagamento);
        if (troco != 0) {
            System.out.printf("%-25sR$%.2f", "Valor disponibilizado:", valorDispoibilizado);
            System.out.println("");
            System.out.printf("%-25sR$%.2f", "Troco", troco);
            System.out.println("");
        }
        System.out.println("***********************************");
    }

    public static int checarSeEnumero() {
        while (true) {
            boolean eUmInteiro = scanner.hasNextInt();
            if (eUmInteiro) {
                int opcao = scanner.nextInt();
                return opcao;
            } else {
                System.out.println("Apenas números são permitidos nesse menu.");
                return 1000;
            }
        }
    }

}
