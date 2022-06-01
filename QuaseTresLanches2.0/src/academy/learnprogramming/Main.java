package academy.learnprogramming;

import MenuAndItens.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Long, ArrayList<Pedido>> mapaDePedidos = new LinkedHashMap<>(); //k = numero do pedio, value = lista dos pedidos
    private static Map<String, ArrayList<Long>> mapaPedidosDoCliente = new LinkedHashMap<>();//k = nome, value =lista com n dos pedidos
    private static Map<String, Long> mapaDeClientes2 = new LinkedHashMap<>();//key nome valor numer do cliente
    private static Map<Long, Cliente> mapaDeClientes = new LinkedHashMap<>();//key numero do cliente valor cliente
    static long numeroDoPedido;
    static long contadorDeCliente;
    static int opcao;

    public static void main(String[] args) {
        menuInicial();
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
        menu.adicionarItemSemValor("LISTA DE PEDIDOS", "Imprimir Pedidos do Clientens");
        menu.adicionarItemSemValor("ADICIONAR CLIENTE", "Adicionar Cliente");
        menu.adicionarItemSemValor("RMEOVER CLIENTE", "Remover Cliente");
        menu.adicionarItemSemValor("EDITAr CLIENTE", "Editar Dados do Cliente");
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
                    mostrarListaDeClientes();
                    break;
                case 2:
                    mostrarPeidosDoCliente();
                    break;
                case 3:
                    System.out.print("Digite o nome completo do cliente: ");
                    String nome = scanner.nextLine();
                    if (mapaDeClientes2.containsKey(nome)) {
                        System.out.println("Cliente ja esta cadastrado");
                    } else {
                        adicionarCliente(nome);
                    }
                    break;
                case 4:
                    removerCliente();
                    break;
                case 5:
                    editarDadosDoCliente();
                    break;
            }
        }
        menuInicial();
    }

    public static void mostrarListaDeClientes() {
        if (mapaDeClientes.size() != 0) {
            for (long i = 1; i <= contadorDeCliente; i++) {
                if (mapaDeClientes.get(i) != null) {
                    Cliente cliente = mapaDeClientes.get(i);
                    System.out.println("Cliente " + i + ":");
                    System.out.println("         Nome: " + cliente.getNomeDoCliente());
                    System.out.println("         Endereco: " + cliente.getEnderecoDoCliente());
                    System.out.println("         Telefone: " + cliente.getNumeroDeTelefoneDoCliente());
                    System.out.println("         CPF: " + cliente.getCpf());
                    System.out.println(" ");
                }
            }
        } else {
            System.out.println("Ainda nao existem cliente cadastrados");
        }
    }

    public static void mostrarPeidosDoCliente() {
        System.out.print("Digite o nome completo do cliente: ");
        String nome = scanner.nextLine();
        System.out.println("");
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
            } else {
                System.out.println("Cliente ainda nao possui pedidos efetuados");
            }
        } else {
            System.out.println("Cliente nao encontrado");
        }
    }

    public static void adicionarCliente(String nome) {
        for (long i = 1; i<=contadorDeCliente+1; i++){
            if (mapaDeClientes.containsKey(i) && mapaDeClientes.get(i)==null){
                System.out.print("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();
                System.out.print("Digite o endereo do cliente: ");
                String endereco = scanner.nextLine();
                System.out.print("Digite o telefone do cliente: ");
                String telefone = scanner.nextLine();
                Cliente novoCliente = new Cliente(nome, endereco, telefone, cpf);//cria um novo cliente
                mapaPedidosDoCliente.put(nome, new ArrayList<Long>());
                mapaDeClientes.put(i, novoCliente);//add no mapa key contCliente, valor Cliente
                mapaDeClientes2.put(nome, i);//add no mapa key nome, valor contCliente
                System.out.println("Cliente adicionado com sucesso");
                arquivarMapaDeClientes();
                arquivarMapaDeClientes2();
                break;

            }else {
                contadorDeCliente++;
                System.out.print("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();
                System.out.print("Digite o endereo do cliente: ");
                String endereco = scanner.nextLine();
                System.out.print("Digite o telefone do cliente: ");
                String telefone = scanner.nextLine();
                Cliente novoCliente = new Cliente(nome, endereco, telefone, cpf);//cria um novo cliente
                mapaPedidosDoCliente.put(nome, new ArrayList<Long>());
                mapaDeClientes.put(contadorDeCliente, novoCliente);//add no mapa key contCliente, valor Cliente
                mapaDeClientes2.put(nome, contadorDeCliente);//add no mapa key nome, valor contCliente
                System.out.println("Cliente adicionado com sucesso");
                arquivarMapaDeClientes();
                arquivarMapaDeClientes2();
                break;
            }
        }
    }

    public static void removerCliente() {
        System.out.print("Digite o Completo do cliente: ");
        String nome = scanner.nextLine();
        if (mapaDeClientes2.containsKey(nome)) {
            mapaPedidosDoCliente.remove(nome);
            long contador = mapaDeClientes2.get(nome);
            mapaDeClientes.remove(contador);
            mapaDeClientes2.remove(nome);
            System.out.println("Cliente excluido com sucesso!");
            arquivarMapaDeClientes();
            arquivarMapaDeClientes2();
        }else {
            System.out.println("Ciente não encontrado");
        }
    }

    public static void editarDadosDoCliente() {
        System.out.print("Digite o nome competo do cliente: ");
        String nome = scanner.nextLine();
        if (mapaDeClientes2.containsKey(nome)) {
            long i = mapaDeClientes2.get(nome);
            Cliente antigoCliente = mapaDeClientes.get(i);
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
                        mapaDeClientes.put(i, antigoCliente);
                        sair = true;
                        break;
                    case 2:
                        System.out.print("Digite o novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        antigoCliente.setNumeroDeTelefoneDoCliente(novoTelefone);
                        mapaDeClientes.put(i, antigoCliente);
                        sair = true;
                        break;
                    case 3:
                        System.out.println("Digite o novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        antigoCliente.setCpf(novoCpf);
                        mapaDeClientes.put(i, antigoCliente);
                        sair = true;
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }
            }
        } else {
            System.out.println("Cliente nao encontrado");
        }
        arquivarMapaDeClientes();
    }

    public static void menuAdicionarPedido() {
        Menu menu = new Menu("ADICIONAR PEDIDO");
        menu.adicionarItemSemValor("PIZZA", "Opções de Pizzas");
        menu.adicionarItemSemValor("LANCHES", "Opções de Lanches");
        menu.adicionarItemSemValor("SALGADOS", "Opções de Salgados");
        menu.adicionarItemSemValor("LISTA PEDIDOS", "Imprimir Itens no Carrinho");
        menu.adicionarItemSemValor("LISTA PEDIDOS", "Excluir Item no Carrinho");
        numeroDoPedido++;
        ArrayList<Pedido> pedidoTemporarios = new ArrayList<>();
        boolean sair = false;
        while (!sair) {
            menu.imprimir();
            opcao = menu.lerOpcao();
            switch (opcao) {
                case 0:
                    mapaDePedidos.put(numeroDoPedido, pedidoTemporarios);
                    finalizarPedidio();
                    arquivarMapaDePedidos();
                    arquivarMapaPedidosDoCliente();
                    System.out.println();
                    sair=true;
                    break;
                case 1:
                    pedidoTemporarios.add(adicionarPizza());
                    checarPedidoNulo(pedidoTemporarios);
                    break;
                case 2:
                    pedidoTemporarios.add(adicionarLanche());
                    checarPedidoNulo(pedidoTemporarios);
                    break;
                case 3:
                    pedidoTemporarios.add(adicionarSalgadinho());
                    checarPedidoNulo(pedidoTemporarios);
                    break;
                case 4:
                    mapaDePedidos.put(numeroDoPedido, pedidoTemporarios);
                    mostrarListaDePedidos();
                    break;
                case 5:
                    mapaDePedidos.put(numeroDoPedido, pedidoTemporarios);
                    excluirItemDoCarrinho();
                    break;
                default:
                    System.out.print("Digite um valor valido: ");
                    break;
            }
        }
        menuInicial();
    }

    public static void checarPedidoNulo(ArrayList<Pedido> pedidoTemporarios){
        if (pedidoTemporarios.get((pedidoTemporarios.size())-1).getPedidos() == null){
            pedidoTemporarios.remove((pedidoTemporarios.size()-1));
        }
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

    public static boolean mostrarListaDePedidos() {
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
        } else {
            System.out.println("Carrinho ainda nao possui itens!");
            retorno = false;
        }
        return retorno;
    }

    public static void excluirItemDoCarrinho() {
        if (mostrarListaDePedidos()) {
            System.out.print("Digite o indice do pedido a ser excluido: ");
            int indice = scanner.nextInt();
            scanner.nextLine();
            ArrayList<Pedido> arrayTemporario = new ArrayList<>();
            arrayTemporario = mapaDePedidos.get(numeroDoPedido);
            if (indice > 0 && indice <= arrayTemporario.size()) {
                arrayTemporario.remove(indice - 1);
                System.out.println("Item removido com sucesso!");
            } else {
                System.out.println("Item nao encontrado no carrinho");
            }

        } else {
            System.out.println("Nao foi possivel excluir itens do carrinho!");
        }

    }

    public static void finalizarPedidio() {
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
                    if (mapaDeClientes2.containsKey(nome)) {
                        System.out.println("Cliente encontrado!");
                    } else {
                        System.out.println("Cliente nao foi encontrado\n" +
                                "Necessario realizar o cadastro");
                        adicionarCliente(nome);
                    }
                    sair = true;
                    break;
                case 2:
                    System.out.print("Digite o nome completo do cliente: ");
                    nome = scanner.nextLine();
                    if (mapaDeClientes2.containsKey(nome)) {
                        System.out.println("Cliente ja esta castrado");
                    } else {
                        adicionarCliente(nome);
                    }
                    sair = true;
                    break;
                default:
                    System.out.print("Digite uma opcao valida: ");
                    break;
            }
        }
        ArrayList<Long> novaLista = mapaPedidosDoCliente.get(nome);
        novaLista.add(numeroDoPedido);

        mapaPedidosDoCliente.put(nome, novaLista);
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
        ArrayList<Pedido> pedidos = mapaDePedidos.get(numeroDoPedido);
        double totalDoPedido = 0;
        for (Pedido pedido : pedidos) {
            totalDoPedido += pedido.getValor();
        }
        totalDoPedido += taxa;
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
                                while (true){
                                    boolean eUmInteiro = scanner.hasNextDouble();
                                    if (eUmInteiro){
                                        valorDisponibilizado = scanner.nextDouble();
                                        if (valorDisponibilizado>=totalDoPedido){
                                            troco = valorDisponibilizado - totalDoPedido;
                                            System.out.println("Troco: R$" + troco);
                                            break;
                                        }else {
                                            System.out.print("Valor invalido, digite um novo valor: ");
                                            scanner.nextLine();
                                        }
                                    }else {
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
        printConclusao(entrega, taxa, troco, tipoDePagamento, totalDoPedido, valorDisponibilizado);
    }

    public static void printConclusao(boolean entrega, double taxa, double troco, String tipoDePagamento, double total,
                                      double valorDispoibilizado) {
        System.out.println(" ");
        System.out.println("***********************************");
        System.out.println("         PEDIDO FINALIZADO         ");
        Cliente cliente = mapaDeClientes.get(contadorDeCliente);
        System.out.println("NOME: " + cliente.getNomeDoCliente());
        if (entrega) {
            System.out.println("ENDERECO: " + cliente.getEnderecoDoCliente());
        }
        ArrayList<Pedido> pedidos = mapaDePedidos.get(numeroDoPedido);
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

    public static void arquivarMapaDeClientes(){
        try (BufferedWriter locFie =new BufferedWriter(new FileWriter("mapaDeClientes.txt"))){
            for (Cliente cliente: mapaDeClientes.values()){
                String nomeCliente = cliente.getNomeDoCliente();
                String numero = mapaDeClientes2.get(nomeCliente).toString();
                locFie.write(numero+","+cliente.getNomeDoCliente()+","+cliente.getCpf()+
                        ","+cliente.getEnderecoDoCliente()+","+cliente.getNumeroDeTelefoneDoCliente()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void arquivarMapaDeClientes2() {
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("mapaDeClientes2.txt"))){
            for(Long numero : mapaDeClientes2.values()){
                String nome = mapaDeClientes.get(numero).getNomeDoCliente();
                locFile.write(nome+","+numero+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void arquivarMapaDePedidos() {

        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("mapaDePedidos.txt"))){
            for (Map.Entry<Long,ArrayList<Pedido>> kav :  mapaDePedidos.entrySet()){
                String stringPedido ="";
                long key = kav.getKey();
                ArrayList arrayList = new ArrayList<>();
                arrayList=kav.getValue();
                String[] data = new String[mapaDePedidos.get(key).size()];
                for (int i =0;i<arrayList.size();i++){
                    data[i] = String.valueOf(arrayList.get(i));
                }
                for (int i = 0 ; i< data.length;i++){
                    stringPedido +=","+data[i];
                }
                locFile.write(key+stringPedido+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void arquivarMapaPedidosDoCliente(){

        try (BufferedWriter locFile = new BufferedWriter(new FileWriter ("mapaPedidosDoCliente.txt"))){
            for (Map.Entry<String,ArrayList<Long>> kav : mapaPedidosDoCliente.entrySet()){
                String numeros = "";
                for (long numero : kav.getValue()){
                    numeros += ","+numero;
                }
                locFile.write(kav.getKey()+numeros+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {

        try(BufferedReader dirFile = new BufferedReader(new FileReader("mapaDeClientes.txt"))) {
            String input;
            while ((input = dirFile.readLine()) != null){
                String [] data = input.split(",");
                long key = Long.parseLong(data[0]);
                String nome = data[1];
                String cpf = data[2];
                String endereco = data[3];
                String telefone = data[4];
                Cliente cliente = new Cliente(nome,cpf,endereco,telefone);
                mapaDeClientes.put(key,cliente);
                contadorDeCliente = key;

            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

        try (BufferedReader dirFile = new BufferedReader(new FileReader("mapaDeClientes2.txt"))){
            String input;
            while ((input = dirFile.readLine()) != null){
                String[] data = input.split(",");
                String key = data[0];
                long numero = Long.parseLong(data[1]);
                mapaDeClientes2.put(key, numero);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader dirFile = new BufferedReader(new FileReader("mapaDepedidos.txt"))){
            String input;

            while ((input = dirFile.readLine())!=null){
                ArrayList<Pedido> listaDePedidos = new ArrayList<>();
                String[] data = input.split(",");
                long key = Long.parseLong(data[0]);
                for (int i =1;i< data.length;i++){
                    String data2 = data[i];
                    String[] data3=data2.split(":");
                    String pedido = data3[0];
                    double valor = Double.parseDouble(data3[1]);
                    Pedido pedido1 = new Pedido(pedido,valor);
                    listaDePedidos.add(pedido1);
                }
                mapaDePedidos.put(key,listaDePedidos);
                numeroDoPedido = key;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader dirFile = new BufferedReader(new FileReader("mapaPedidosDoCliente.txt"))){
            String input;
            while ((input = dirFile.readLine())!=null){
                ArrayList<Long> arrayList = new ArrayList<>();
                String[] data = input.split(",");
                String nome = data[0];
                for (int i =1;i<data.length;i++){
                    arrayList.add(Long.valueOf(data[i]));
                }
                mapaPedidosDoCliente.put(nome,arrayList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int checarSeEnumero(){
        while (true){
            boolean eUmInteiro = scanner.hasNextInt();
            if (eUmInteiro){
                int opcao = scanner.nextInt();
                return opcao;
            }else {
                System.out.println("Apenas números são permitidos nesse menu.");
                return 1000;
            }
        }
    }
}
