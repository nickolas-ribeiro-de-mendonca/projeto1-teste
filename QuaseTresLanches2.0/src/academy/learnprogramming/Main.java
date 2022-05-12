package academy.learnprogramming;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Long, ArrayList> mapaDePedidos = new HashMap<>(); //k = numero do pedio, value = lista dos pedidos
    private static Map<String, ArrayList> mapaPedidosDoCliente = new HashMap<>();//k = nome, value =lista com n dos pedidos
    private static Map<String,Long> mapaDeClientes2 = new HashMap<>();
    private static Map<Long, Cliente> mapaDeClientes = new HashMap<Long,Cliente>();
    static long numeroDoPedido = 0;
    static long contadorDeCliente = 0;
    public static void main(String[] args) {
        menuInicial();
        menuInicialOpcoes();

    }

    public static void menuInicial(){
        System.out.println();
        System.out.println("======================================");
        System.out.println("           QUASE TRES LANCHESS");
        System.out.println("          1 - Gerenciar clientes");
        System.out.println("           2 - Adicionar pedido");
        System.out.println("           0 - Finaliza programa");
        System.out.println("======================================");
        System.out.print("Digite opcao: ");
    }

    public static void menuInicialOpcoes(){
        boolean sair = false;
        while (!sair){
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 0:
                    System.out.println("Finalizando programa...");
                    sair = true;
                    break;
                case 1:
                    menuGerenciarclientes();
                    break;
                case 2:
                    menuAdicionarPedido();
                    break;
                default:
                    System.out.println("Valor invalido por favor digite um valor valido.");
                    break;
            }
        }

    }

    public static void  miniGerenciarClientes(){
        System.out.println();
        System.out.println("======================================");
        System.out.println("     1 - Mostrar lista de clientes");
        System.out.println("     2 - Mostrar pedidos do cliente");
        System.out.println("         3 - Adicionar cliente");
        System.out.println("          4 - Remover cliente");
        System.out.println("      5 - Editar dados do cliente");
        System.out.println("       0 - voltar ao menu incial");
        System.out.println("======================================");
        System.out.print("Digite sua opcao: ");
    }
    public static void menuGerenciarclientes(){
        System.out.println();
        System.out.println("======================================");
        System.out.println("     1 - Mostrar lista de clientes");
        System.out.println("     2 - Mostrar pedidos do cliente");
        System.out.println("         3 - Adicionar cliente");
        System.out.println("          4 - Remover cliente");
        System.out.println("      5 - Editar dados do cliente");
        System.out.println("       0 - voltar ao menu incial");
        System.out.println("======================================");
        System.out.print("Digite sua opcao: ");
        boolean sair = false;
        while (!sair){
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 0:
                    System.out.println("Voltando ao muenu inicial...");
                    sair = true;
                    menuInicial();
                    break;
                case 1:
                    mostrarListaDeClientes();
                    miniGerenciarClientes();
                    break;
                case 2:
                    mostrarPeidosDoCliente();
                    miniGerenciarClientes();
                    break;
                case 3:
                    System.out.print("Digite o nome completo do cliente: ");
                    String nome = scanner.nextLine();
                    if (mapaDeClientes2.containsKey(nome)){
                        System.out.println("Digite ja esta cadastrado");
                    }else {
                        adicionarCliente(nome);
                    }
                    miniGerenciarClientes();
                    break;
                case 4:
                    removerCliente();
                    miniGerenciarClientes();
                    break;
                case 5:
                    editarDadosDoCliente();
                    miniGerenciarClientes();
                    break;
                default:
                    System.out.println("Valor invalido por favor digite um valor valido.");
                    break;
            }
        }
    }

    public static void mostrarListaDeClientes() {
        if (mapaDeClientes.size() != 0) {
            for (long i = 1; i <=contadorDeCliente; i++) {
                if (mapaDeClientes.get(i)!= null) {
                    Cliente cliente = mapaDeClientes.get(i);
                    System.out.println("Cliente " + i + ":");
                    System.out.println("         Nome: " + cliente.getNomeDoCliente());
                    System.out.println("         Endereco: " + cliente.getEnderecoDoCliente());
                    System.out.println("         Telefone: " + cliente.getNumeroDeTelefoneDoCliente());
                    System.out.println("         CPF: " + cliente.getCpf());
                    System.out.println(" ");
                }
            }
        }else {
            System.out.println("Ainda nao existem cliente cadastrados");
        }
    }

    public static void mostrarPeidosDoCliente(){
        System.out.print("Digite o nome completo do cliente: ");
        String nome = scanner.nextLine();
        if (mapaPedidosDoCliente.containsKey(nome)) {
            ArrayList<Long> listaDeNumeros = mapaPedidosDoCliente.get(nome);
            if (!listaDeNumeros.isEmpty()) {
                for (long i : listaDeNumeros) {
                    ArrayList<Pedido> pedidos = mapaDePedidos.get(i);
                    System.out.println("Numero do pedido " + i);
                    for (Pedido pedido : pedidos) {
                        System.out.println(pedido.getPedidos() + " - R$" + pedido.getValor());
                    }
                    System.out.println("");
                }
            }else {
                System.out.println("Cliente ainda nao possui pedidos efetuados");
            }
        }else {
            System.out.println("Cliente nao encontrado");
        }
    }

    public static void adicionarCliente(String nome){
        contadorDeCliente++;
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o endereo do cliente: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        Cliente novoCliente = new Cliente(nome,endereco,telefone,cpf);//cria um novo cliente
        mapaPedidosDoCliente.put(nome,new ArrayList<Long>());
        mapaDeClientes.put(contadorDeCliente,novoCliente);//add no mapa key contCliente, valor Cliente
        mapaDeClientes2.put(nome,contadorDeCliente);//add no mapa key nome, valor contCliente
        System.out.println("Cliente adicionado com sucesso");
    }

    public static void removerCliente(){
        System.out.print("Digite o Completo do cliente: ");
        String nome = scanner.nextLine();
        mapaPedidosDoCliente.remove(nome);
        long contador = mapaDeClientes2.get(nome);
        mapaDeClientes.remove(contador);
        mapaDeClientes2.remove(nome);
        System.out.println("Cliente excluido com sucesso!");
    }

    public static void editarDadosDoCliente(){
        System.out.print("Digite o nome competo do cliente: ");
        String nome = scanner.nextLine();
        long i = mapaDeClientes2.get(nome);
        Cliente antigoCliente = mapaDeClientes.get(i);
        if (mapaDeClientes2.containsKey(nome)) {
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
                        System.out.print("Digite o novo endereco: ");
                        String novoEndereco = scanner.nextLine();
                        antigoCliente.setEnderecoDoCliente(novoEndereco);
                        mapaDeClientes.put(i,antigoCliente);
                        sair= true;
                        break;
                    case 2:
                        System.out.print("Digite o novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        antigoCliente.setNumeroDeTelefoneDoCliente(novoTelefone);
                        mapaDeClientes.put(i,antigoCliente);
                        sair = true;
                        break;
                    case 3:
                        System.out.println("Digite o novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        antigoCliente.setCpf(novoCpf);
                        mapaDeClientes.put(i,antigoCliente);
                        sair = true;
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }
            }
        }else {
            System.out.println("Cliente nao encontrado");
        }
    }

    public static void miniAdicionarPedido(){
        System.out.println();
        System.out.println("======================================");
        System.out.println("               1 - Pizzas");
        System.out.println("               2 - Lanches");
        System.out.println("              3 - Salgados");
        System.out.println("      4 - Mostrar lista de pedidos");
        System.out.println("      5 - Excluir item do carrinho");
        System.out.println("         0 - Finalizar pedido");
        System.out.println("======================================");
        System.out.print("Digite sua opcao: ");
    }
    public static void menuAdicionarPedido(){
        System.out.println();
        System.out.println("======================================");
        System.out.println("               1 - Pizzas");
        System.out.println("               2 - Lanches");
        System.out.println("              3 - Salgados");
        System.out.println("      4 - Mostrar lista de pedidos");
        System.out.println("      5 - Excluir item do carrinho");
        System.out.println("         0 - Finalizar pedido");
        System.out.println("======================================");
        System.out.print("Digite sua opcao: ");
        ArrayList<Pedido> pedidoTemporarios = new ArrayList<>();
        numeroDoPedido++;
        boolean sair = false;
        while (!sair){
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 0:
                    mapaDePedidos.put(numeroDoPedido,pedidoTemporarios);
                    System.out.println(pedidoTemporarios.size());
                    finalizarPedidio();
                    sair = true;
                    break;
                case 1:
                    pedidoTemporarios.add(adicionarPizza());
                    miniAdicionarPedido();
                    break;
                case 2:
                    pedidoTemporarios.add(adicionarLanche());
                    miniAdicionarPedido();
                    break;
                case 3:
                    pedidoTemporarios.add(adicionarSalgadinho());
                    miniAdicionarPedido();
                    break;
                case  4:
                    mapaDePedidos.put(numeroDoPedido,pedidoTemporarios);
                    mostrarListaDePedidos();
                    miniAdicionarPedido();
                    break;
                case 5:
                    mapaDePedidos.put(numeroDoPedido,pedidoTemporarios);
                    excluirItemDoCarrinho();
                    miniAdicionarPedido();
                    break;
                default:
                    System.out.println("Opcao invalida, digite uma opcao valida ");
                    break;
            }
        }
        menuInicial();
    }

    public static Pedido adicionarPizza(){
        Pizza pizza = new Pizza("Pizza",1);
        pizza.escolhaDePizza();
        return pizza.adicionarPedidos();
    }

    public static Pedido adicionarLanche(){
        Lanche lanche = new Lanche();
        lanche.adicaoDeIngredientes();
        return lanche.adicionarPedidos();
    }

    public static Pedido adicionarSalgadinho(){
        Salgadinhos salgadinhos = new Salgadinhos();
        salgadinhos.escolherSalgado();
        return salgadinhos.adicionarPedidos();
    }

    public static boolean mostrarListaDePedidos(){
        ArrayList<Pedido> arrayTemporario = mapaDePedidos.get(numeroDoPedido);
        boolean retorno = false;
        if (!arrayTemporario.isEmpty()) {
            int i = 1;
            retorno = true;
            System.out.println("======================================");
            for (Pedido pedido : arrayTemporario) {
                System.out.println(i + " Pedido: " + pedido.getPedidos() + ", R$" + pedido.getValor());
                i++;
            }
            System.out.println("======================================");
        }else {
            System.out.println("Carrinho ainda nao possui itens!");
            retorno = false;
        }
        return retorno;
    }

    public static void excluirItemDoCarrinho(){
        if (mostrarListaDePedidos()) {
            System.out.print("Digite o indice do pedido a ser excluido: ");
            int indice = scanner.nextInt();
            scanner.nextLine();
            ArrayList<Pedido> arrayTemporario = new ArrayList<>();
            arrayTemporario = mapaDePedidos.get(numeroDoPedido);
            if (indice>0 && indice<=arrayTemporario.size()){
                arrayTemporario.remove(indice-1);
                System.out.println("Item removido com sucesso!");
            }else {
                System.out.println("Item nao encontrado no carrinho");
            }

        }else {
            System.out.println("Nao foi possivel excluir itens do carrinho!");
        }

    }

    public static void finalizarPedidio(){
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
        double troco =0;

        while (!sair) {
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    if (mapaDeClientes2.containsKey(nome)){
                        System.out.println("Cliente encontrado!");
                    }else {
                        System.out.println("Cliente nao foi encontrado\n" +
                                "Necessario realizar o cadastro");
                        adicionarCliente(nome);
                    }
                    sair = true;
                    break;
                case 2:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    if (mapaDeClientes2.containsKey(nome)){
                        System.out.println("Cliente ja esta castrado");
                    }else {
                        adicionarCliente(nome);
                    }
                    sair = true;
                    break;
                default:
                    System.out.println("Digite uma opcao valida");
                    break;
            }
        }
        ArrayList<Long> novaLista = mapaPedidosDoCliente.get(nome);
        novaLista.add(numeroDoPedido);
        mapaPedidosDoCliente.put(nome,novaLista);
        System.out.println("");
        System.out.println("Pedido para entrega ou retirada no local: ");
        System.out.print("1 - ENTRGA, 2 - RETIRADA: ");
        sair = false;
        double taxa = 0;
        while (!sair){
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Uma taxa de entrega de R$5,00 sera cobrada");
                    taxa = 5;
                    entrega = true;
                    sair= true;
                    break;
                case 2:
                    System.out.println("Retirada no loca selecionado");
                    entrega = false;
                    sair = true;
                    break;
                default:
                    System.out.println("Digite um valor valido ");
                    break;
            }
        }
        ArrayList<Pedido> pedidos = mapaDePedidos.get(numeroDoPedido);
        double totalDoPedido = 0;
        for (Pedido pedido : pedidos){
            totalDoPedido += pedido.getValor();
        }
        totalDoPedido += taxa;
        System.out.println("Valor total R$"+totalDoPedido);
        System.out.println("");
        System.out.println("Pagamento sera realizado em:");
        System.out.print("1- CARTAO DE CREDITO 2- DINHEIRO: ");
        sair = false;
        while (!sair){
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Pagamento com cartao selecionado");
                    tipoDePagamento = "CARTAO";
                    sair=true;
                    break;
                case 2:
                    System.out.println("Pagamento em dinheiro selecionado");
                    tipoDePagamento = "DINHEIRO";
                    System.out.println("Sera necessario pagameno troco? ");
                    System.out.print("1- SIM 2- NAO: ");
                    while (!sair){
                        opcao = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcao){
                            case 1:
                                System.out.print("Digite o valor disponibilizado: ");
                                valorDisponibilizado = scanner.nextDouble();
                                scanner.nextLine();
                                troco = valorDisponibilizado - totalDoPedido;
                                System.out.println("Troco: R$"+troco);
                                sair = true;
                                break;
                            case 2:
                                System.out.println("Troco nao sera disponibilizado");
                                troco = 0;
                                sair = true;
                                break;
                            default:
                                System.out.println("Digite uma opcao valida");
                                break;

                        }
                    }
            }
        }
        printConclusao(entrega,taxa,troco,tipoDePagamento,totalDoPedido,valorDisponibilizado);
    }

    public static void printConclusao(boolean entrega, double taxa, double troco, String tipoDePagamento,double total,
                                      double valorDispoibilizado){
        System.out.println(" ");
        System.out.println("***********************************");
        System.out.println("         PEDIDO FINALIZADO         ");
        Cliente cliente = mapaDeClientes.get(contadorDeCliente);
        System.out.println("NOME: "+cliente.getNomeDoCliente());
        if (entrega){
            System.out.println("ENDERECO: "+cliente.getEnderecoDoCliente());
        }
        ArrayList<Pedido> pedidos = mapaDePedidos.get(numeroDoPedido);
        System.out.println("         ITENS DO CARRINHO");
        for (Pedido pedido : pedidos){
            System.out.printf("%-25sR$%.2f",pedido.getPedidos(),pedido.getValor());
            System.out.println(" ");
        }
        if (taxa !=0){
            System.out.printf("%-25sR$%.2f","Taxa de entrega",taxa);
            System.out.println("");
        }
        System.out.printf("%-25sR$%.2f","Total",total);
        System.out.println("");
        System.out.println("TIPO DE PAGAMENTO: "+tipoDePagamento);
        if (troco != 0 ){
            System.out.printf("%-25sR$%.2f","Valor disponibilizado:",valorDispoibilizado);
            System.out.println("");
            System.out.printf("%-25sR$%.2f","Troco",troco);
            System.out.println("");
        }
        System.out.println("***********************************");
    }
}
