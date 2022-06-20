package academy.learnprogramming;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DataSource {
    public static final String DB_NAME = "quaseTresLanches.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\nicko\\OneDrive\\Área de Trabalho\\Repositorio\\QuaseTresLanches2.0\\" + DB_NAME;
    private Connection conn;

    public static final String TABELA_CLIENTE = "cliente";
    public static final String COLUNA_CLIENTE_ID = "_id";
    public static final int INDEX_CLIENTE_ID = 1;
    public static final String COLUNA_CLIENTE_NOME = "nome";
    public static final int INDEX_CLIENTE_NOME = 2;
    public static final String COLUNA_CLIENTE_ENDERECO = "endereco";
    public static final int INDEX_CLIENTE_ENDERECO = 3;
    public static final String COLUNA_CLIENTE_TELEFONE = "telefone";
    public static final int INDEX_CLIENTE_TELEFONE = 4;
    public static final String COLUNA_CLIENTE_CPF = "cpf";
    public static final int INDEX_CLIENTE_CPF = 5;

    public static final String TABELA_CARDAPIO = "cardapio";
    public static final String COLUNA_CARDAPIO_ID = "_id";
    public static final String COLUNA_CARDAPIO_PRATO = "prato";
    public static final String COLUNA_CARDAPIO_VALOR = "valor";

    public static final String TABELA_PEDIDO = "pedido";
    public static final String COLUNA_PEDIDO_ID = "_id";
    public static final String COLUNA_PEDIDO_ID_CARDAPIO = "id_cardapio";
    public static final String COLUNA_PEDIDO_NUMERO_PEDIDO = "numero_do_pedido";


    public static final String TABELA_CLIENTE_PEDIDO = "cliente_pedido";
    public static final String COLUNA_ID_CLIENTE = "id_cliente";
    public static final String COLUNA_ID_PEDIDO = "id_pedido";

    public static final int ORDER_BY_NOME = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String ADICIONAR_CLIENTE = "INSERT INTO " + TABELA_CLIENTE + '(' + COLUNA_CLIENTE_NOME + ", " +
            COLUNA_CLIENTE_ENDERECO + ", " + COLUNA_CLIENTE_TELEFONE + ", " + COLUNA_CLIENTE_CPF + ") VALUES (?, ?, ?, ?)";
    private PreparedStatement adicionarEmCliente;

    public static final String BUSCAR_NOME_CLIENTE = "SELECT " + COLUNA_CLIENTE_NOME + " FROM " + TABELA_CLIENTE + " WHERE " +
            COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement buscarNomeCliente;

    public static final String BUSCAR_ID_CLIENTE_PELO_NOME = "SELECT " + COLUNA_CLIENTE_ID + " FROM " + TABELA_CLIENTE + " WHERE " +
            COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement buscarIdCliente;

    public static final String BUSCAR_DADOS_CLIENTE = "SELECT * FROM " + TABELA_CLIENTE + " WHERE " + COLUNA_CLIENTE_NOME +
            " = ? COLLATE NOCASE";
    private PreparedStatement buscarDadosCliente;

    public static final String ATUALIZAR_CPF = "UPDATE " + TABELA_CLIENTE + " SET " + COLUNA_CLIENTE_CPF + " = ? WHERE " +
            COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement atualizarCpf;

    public static final String ATUALIZAR_ENDERECO = "UPDATE " + TABELA_CLIENTE + " SET " + COLUNA_CLIENTE_ENDERECO +
            " = ? WHERE " + COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement atualizarEndereco;

    public static final String ATUALIZAR_TELEFONE = "UPDATE " + TABELA_CLIENTE + " SET " + COLUNA_CLIENTE_TELEFONE +
            " = ? WHERE " + COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement atualizarTelefone;

    public static final String ADICIONAR_PEDIDO_UNITARIO_AOS_PEDIDOS = "INSERT INTO " + TABELA_PEDIDO + '(' + COLUNA_PEDIDO_ID_CARDAPIO +
            ", " + COLUNA_PEDIDO_NUMERO_PEDIDO + ')' + " VALUES (?, ?)";
    private PreparedStatement adicionarPedidoUnitarioAosPedidos;

    public static final String BUSCAR_ID_DO_PRATO = "SELECT " + COLUNA_CARDAPIO_ID + " FROM " + TABELA_CARDAPIO + " WHERE " +
            COLUNA_CARDAPIO_PRATO + " = ?";
    private PreparedStatement buscarIdDoPrato;

    public static final String BUSCAR_PRATO_VALOR_NUMERO_PEDIDO = "SELECT " + TABELA_CARDAPIO + "." + COLUNA_CARDAPIO_PRATO +
            ", " + TABELA_CARDAPIO + "." + COLUNA_CARDAPIO_VALOR + ", " + TABELA_PEDIDO + "." + COLUNA_PEDIDO_NUMERO_PEDIDO + ", " +
            TABELA_PEDIDO + "." + COLUNA_PEDIDO_ID + " FROM " + TABELA_PEDIDO + " INNER JOIN " + TABELA_CARDAPIO + " ON " +
            TABELA_PEDIDO + "." + COLUNA_PEDIDO_ID_CARDAPIO + " = " + TABELA_CARDAPIO + "." + COLUNA_CARDAPIO_ID + " WHERE " +
            TABELA_PEDIDO + "." + COLUNA_PEDIDO_NUMERO_PEDIDO + " = ? ORDER BY " + TABELA_PEDIDO + "." + COLUNA_PEDIDO_ID;
    private PreparedStatement buscarPratoValorNumeroPedido;

    public static final String ADICIONAR_PEDIDOS_UNITARIO_CLIENTE_PEDIDO = "INSERT INTO " + TABELA_CLIENTE_PEDIDO +
            " VALUES (?, ?)";
    private PreparedStatement adicionarPedidosUnitariosClientePedido;


    private static final String BUSCAR_NUMERO_DO_PEDIDO_PELO_NOME = "SELECT DISTINCT " + TABELA_PEDIDO + "." +
            COLUNA_PEDIDO_NUMERO_PEDIDO + " FROM " + TABELA_CLIENTE + " INNER JOIN " + TABELA_CLIENTE_PEDIDO + " ON " +
            TABELA_CLIENTE + "." + COLUNA_CLIENTE_ID + " = " + TABELA_CLIENTE_PEDIDO + "." + COLUNA_ID_CLIENTE + " INNER JOIN " +
            TABELA_PEDIDO + " ON " + TABELA_CLIENTE_PEDIDO + "." + COLUNA_ID_PEDIDO + " = " + TABELA_PEDIDO + "." + COLUNA_PEDIDO_ID +
            " WHERE " + TABELA_CLIENTE + "." + COLUNA_CLIENTE_NOME + " = ? COLLATE NOCASE";
    private PreparedStatement buscarNumeroDoPedioPeloNome;

    private static final String BUSCAR_ITEM_DO_CARDAPIO_PELO_NUMERO_DO_PEDIDO = "SELECT " + TABELA_CARDAPIO + "." +
            COLUNA_CARDAPIO_PRATO + ", " + TABELA_CARDAPIO + "." + COLUNA_CARDAPIO_VALOR + " FROM " + TABELA_PEDIDO + " INNER JOIN " +
            TABELA_CARDAPIO + " ON " + TABELA_PEDIDO + "." + COLUNA_PEDIDO_ID_CARDAPIO + " = " + TABELA_CARDAPIO + "." +
            COLUNA_CARDAPIO_ID + " WHERE " + TABELA_PEDIDO + "." + COLUNA_PEDIDO_NUMERO_PEDIDO + " = ?";
    private PreparedStatement buscarItemDoCardapioPeloNumeroDoPedido;

    private static final String BUSCAR_ITENS_NO_CARRINHO = "SELECT "+TABELA_CARDAPIO+"."+COLUNA_CARDAPIO_PRATO+" ,"+
            TABELA_CARDAPIO+"."+COLUNA_CARDAPIO_VALOR+" FROM "+TABELA_PEDIDO+" INNER JOIN "+ TABELA_CARDAPIO+" ON "+
            TABELA_PEDIDO+"."+COLUNA_PEDIDO_ID_CARDAPIO+" = "+TABELA_CARDAPIO+"."+COLUNA_CARDAPIO_ID+" WHERE "+
            TABELA_PEDIDO+"."+COLUNA_PEDIDO_NUMERO_PEDIDO+" = ?";
    private PreparedStatement buscarItensNoCarrinho;

    private static final String BUSCAR_PEDIDOS_UNITARIO_PELO_NUMERO_DO_PEDIDO ="SELECT "+COLUNA_PEDIDO_ID+" FROM "+
            TABELA_PEDIDO+" WHERE "+ COLUNA_PEDIDO_NUMERO_PEDIDO+" = ? ";
    private PreparedStatement buscarPedidosUnitarioPeloNumeroDoPedido;

    private static final String DELETAR_PEDIDO_DE_TABELA_PEDIDO="DELETE FROM "+TABELA_PEDIDO+" WHERE "+COLUNA_PEDIDO_ID+
        " = ?";
    private PreparedStatement deletarPedidoDeTabelaPedido;

    private static final String SOMA_DOS_PRODUTOS = "SELECT SUM ("+TABELA_CARDAPIO+"."+COLUNA_CARDAPIO_VALOR+") FROM "+
            TABELA_PEDIDO+" INNER JOIN "+TABELA_CARDAPIO+" ON "+TABELA_PEDIDO+"."+COLUNA_PEDIDO_ID_CARDAPIO+" = "+
            TABELA_CARDAPIO+"."+COLUNA_CARDAPIO_ID+" WHERE "+TABELA_PEDIDO+"."+COLUNA_PEDIDO_NUMERO_PEDIDO+" = ?";
    private PreparedStatement somaDosProdutos;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            buscarNomeCliente = conn.prepareStatement(BUSCAR_NOME_CLIENTE);
            adicionarEmCliente = conn.prepareStatement(ADICIONAR_CLIENTE);
            buscarIdCliente = conn.prepareStatement(BUSCAR_ID_CLIENTE_PELO_NOME);
            buscarDadosCliente = conn.prepareStatement(BUSCAR_DADOS_CLIENTE);
            atualizarCpf = conn.prepareStatement(ATUALIZAR_CPF);
            atualizarEndereco = conn.prepareStatement(ATUALIZAR_ENDERECO);
            atualizarTelefone = conn.prepareStatement(ATUALIZAR_TELEFONE);
            adicionarPedidoUnitarioAosPedidos = conn.prepareStatement(ADICIONAR_PEDIDO_UNITARIO_AOS_PEDIDOS);
            buscarIdDoPrato = conn.prepareStatement(BUSCAR_ID_DO_PRATO);
            buscarPratoValorNumeroPedido = conn.prepareStatement(BUSCAR_PRATO_VALOR_NUMERO_PEDIDO);
            adicionarPedidosUnitariosClientePedido =
                    conn.prepareStatement(ADICIONAR_PEDIDOS_UNITARIO_CLIENTE_PEDIDO);
            buscarNumeroDoPedioPeloNome = conn.prepareStatement(BUSCAR_NUMERO_DO_PEDIDO_PELO_NOME);
            buscarItemDoCardapioPeloNumeroDoPedido =
                    conn.prepareStatement(BUSCAR_ITEM_DO_CARDAPIO_PELO_NUMERO_DO_PEDIDO);
            buscarItensNoCarrinho = conn.prepareStatement(BUSCAR_ITENS_NO_CARRINHO);
            buscarPedidosUnitarioPeloNumeroDoPedido= conn.prepareStatement(BUSCAR_PEDIDOS_UNITARIO_PELO_NUMERO_DO_PEDIDO);
            deletarPedidoDeTabelaPedido = conn.prepareStatement(DELETAR_PEDIDO_DE_TABELA_PEDIDO);
            somaDosProdutos = conn.prepareStatement(SOMA_DOS_PRODUTOS);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (somaDosProdutos != null){
                somaDosProdutos.close();
            }
            if (deletarPedidoDeTabelaPedido != null){
                deletarPedidoDeTabelaPedido.close();
            }
            if (buscarPedidosUnitarioPeloNumeroDoPedido != null){
                buscarPedidosUnitarioPeloNumeroDoPedido.close();
            }
            if (buscarItensNoCarrinho != null){
                buscarItensNoCarrinho.close();
            }
            if (buscarItemDoCardapioPeloNumeroDoPedido != null) {
                buscarItemDoCardapioPeloNumeroDoPedido.close();
            }
            if (buscarNumeroDoPedioPeloNome != null) {
                buscarNumeroDoPedioPeloNome.close();
            }
            if (adicionarPedidosUnitariosClientePedido != null) {
                adicionarPedidosUnitariosClientePedido.close();
            }
            if (buscarPratoValorNumeroPedido != null) {
                buscarPratoValorNumeroPedido.close();
            }
            if (buscarIdDoPrato != null) {
                buscarIdDoPrato.close();
            }
            if (adicionarPedidoUnitarioAosPedidos != null) {
                adicionarPedidoUnitarioAosPedidos.close();
            }
            if (atualizarTelefone != null) {
                atualizarTelefone.close();
            }
            if (atualizarEndereco != null) {
                atualizarEndereco.close();
            }
            if (atualizarCpf != null) {
                atualizarCpf.close();
            }
            if (buscarDadosCliente != null) {
                buscarDadosCliente.close();
            }
            if (buscarNomeCliente != null) {
                buscarNomeCliente.close();
            }
            if (adicionarEmCliente != null) {
                adicionarEmCliente.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean clienteCadastrado(String nome) {
        try {
            buscarNomeCliente.setString(1, nome);
            ResultSet resultSet = buscarNomeCliente.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Excecao em dataSource.clienteCadastrado.");
            e.printStackTrace();
            return false;
        }
    }

    public void adicionarCliente(String nome) {
        Scanner scanner = new Scanner(System.in);
        if (!clienteCadastrado(nome)) {
            try {
                System.out.print("Digite o endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("Digite o telefone:");
                String telefone = scanner.nextLine();
                System.out.print("Digite o CPF:");
                String cpf = scanner.nextLine();

                conn.setAutoCommit(false);
                adicionarEmCliente.setString(1, nome);
                adicionarEmCliente.setString(2, endereco);
                adicionarEmCliente.setString(3, telefone);
                adicionarEmCliente.setString(4, cpf);
                int linhasAfetadas = adicionarEmCliente.executeUpdate();
                if (linhasAfetadas == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("Adicionar novo cliente falhou");
                }

            } catch (SQLException e) {
                System.out.println("Adicionar cliente gerou exceção");
                e.printStackTrace();
                try {
                    System.out.println("Realizando Rollback");
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Problemas ao executar o Rollback");
                    ex.printStackTrace();
                }
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.out.println("Nao foi possivel restaurar o auto-commit");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Cliente já está cadastrado.");
        }
    }

    public Cliente buscarDadosDoCliente(String nome) {
        try {
            buscarDadosCliente.setString(1, nome);
            ResultSet dadoDoCliente = buscarDadosCliente.executeQuery();
            if (dadoDoCliente.next()) {
                String endereco = dadoDoCliente.getString(3);
                String telefone = dadoDoCliente.getString(4);
                String cpf = dadoDoCliente.getString(5);
                Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Excecao ao retornar os dados do cliente em datasource.buscarDadosDoCliente()");
            e.printStackTrace();
            return null;
        }
    }



    public void preencherMapaPedidosDoCliente() {
        String sql = "SELECT DISTINCT " + TABELA_CLIENTE + "." + COLUNA_CLIENTE_NOME + ", " + TABELA_PEDIDO + "." +
                COLUNA_PEDIDO_NUMERO_PEDIDO + " FROM " + TABELA_CLIENTE + " INNER JOIN " + TABELA_CLIENTE_PEDIDO + " ON " +
                TABELA_CLIENTE + "." + COLUNA_CLIENTE_ID + " = " + TABELA_CLIENTE_PEDIDO + "." + COLUNA_ID_CLIENTE + " INNER JOIN " +
                TABELA_PEDIDO + " ON " + TABELA_CLIENTE_PEDIDO + "." + COLUNA_ID_PEDIDO + " = " + TABELA_PEDIDO + "." +
                COLUNA_PEDIDO_ID;

    }


    public void setAtualizarCpfCliente(String nome, String cpfNovo) {
        try {
            conn.setAutoCommit(false);
            atualizarCpf.setString(1, cpfNovo);
            atualizarCpf.setString(2, nome);
            int linhasAfetadas = atualizarCpf.executeUpdate();
            if (linhasAfetadas == 1) {
                conn.commit();
            } else {
                throw new SQLException("Modifica CPF falhou");
            }
        } catch (SQLException e) {
            System.out.println("Modifica CPF falhou");
            e.printStackTrace();
            try {
                System.out.println("Realizando Rollback");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar o Rollback");
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nao foi possivel restaurar o auto-commit");
                e.printStackTrace();
            }
        }
    }

    public void setAtualizarEnderecoCliente(String nome, String enderecoNovo) {

        try {
            conn.setAutoCommit(false);
            atualizarEndereco.setString(1, enderecoNovo);
            atualizarEndereco.setString(2, nome);
            int linhasAfetadas = atualizarEndereco.executeUpdate();
            if (linhasAfetadas == 1) {
                conn.commit();
            } else {
                throw new SQLException("Modificar endereço falhou");
            }
        } catch (SQLException e) {
            System.out.println("Modifica endereço falhou");
            e.printStackTrace();
            try {
                System.out.println("Realizando Rollback");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar o Rollback");
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nao foi possivel restaurar o auto-commit");
                e.printStackTrace();
            }
        }
    }

    public void setAtualizarTelefoneCliente(String nome, String telefoneNovo) {
        try {
            conn.setAutoCommit(false);
            atualizarTelefone.setString(1, telefoneNovo);
            atualizarTelefone.setString(2, nome);
            int linhasAfetadas = atualizarTelefone.executeUpdate();
            if (linhasAfetadas == 1) {
                conn.commit();
            } else {
                throw new SQLException("Modificar telefone falhou");
            }
        } catch (SQLException e) {
            System.out.println("Modifica CPF falhou");
            e.printStackTrace();
            try {
                System.out.println("Realizando Rollback");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar o Rollback");
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nao foi possivel restaurar o auto-commit");
                e.printStackTrace();
            }
        }
    }

    public void adicionarPedidoUnitario(Pedido pedido, long numeroDoPedido) {
        String prato = pedido.getPedidos();
        try {
            buscarIdDoPrato.setString(1, prato);
            ResultSet resultSet = buscarIdDoPrato.executeQuery();
            int idDoPrato = resultSet.getInt(1);

            conn.setAutoCommit(false);
            adicionarPedidoUnitarioAosPedidos.setInt(1, idDoPrato);
            adicionarPedidoUnitarioAosPedidos.setLong(2, numeroDoPedido);
            int linhaAfetada = adicionarPedidoUnitarioAosPedidos.executeUpdate();
            if (linhaAfetada == 1) {
                conn.commit();
            } else {
                throw new SQLException("Falha ao tentar adicionar pedido unitario a tabela pedido");
            }
        } catch (SQLException e) {
            System.out.println("Adicionar pedido gerou exceção");
            e.printStackTrace();
            try {
                System.out.println("Realizando Rollback");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar o Rollback");
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nao foi possivel restaurar o auto-commit");
                e.printStackTrace();
            }
        }
    }

    public Long buscarNumeroDoPedidoAtual(){
        String sql = "SELECT MAX ("+COLUNA_PEDIDO_NUMERO_PEDIDO+") FROM "+TABELA_PEDIDO;
        long numeroDoPedido = 0;

        try (Statement statement = conn.createStatement();
             ResultSet numPedio = statement.executeQuery(sql)){
            numeroDoPedido = numPedio.getInt(1)+1;
            return numeroDoPedido;
        } catch (SQLException e) {
            System.out.println("Excecao ao em dataSource.buscarNumeroDoPedidoAtual.");
            e.printStackTrace();
            return null;
        }
    }

    public int mostrarIntensNoCarrinho(long numeroDoPedido){
        try {
            buscarItensNoCarrinho.setLong(1,numeroDoPedido);
            ResultSet pedidosNoCarrinho = buscarItensNoCarrinho.executeQuery();
            Pedido pedido = new Pedido(pedidosNoCarrinho.getString(1),
                    pedidosNoCarrinho.getDouble(2));

            if (pedido.getPedidos()!=null) {
                int contador = 0;
                while (pedidosNoCarrinho.next()) {
                    contador++;
                    System.out.println("Item " + contador + ":");
                    System.out.println("    ." + pedidosNoCarrinho.getString(1));
                    System.out.printf("    .R$%.2f\n", pedidosNoCarrinho.getDouble(2));
                }
                return contador;
            }else {
                System.out.println("Ainda não existem itens no carrinho.");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Excecao em dataSource.mostrarItensNoCarrinho");
            e.printStackTrace();
            return -1;
        }
    }

    public void excluirItemDoCarrinho(long numeroDoPedido){
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        int numeroDeItensNoCarrinho = mostrarIntensNoCarrinho(numeroDoPedido);

        if (numeroDeItensNoCarrinho == 0){
            System.out.println("Não é possivel excluir itens do carrinho no momento.");
        }else {
            System.out.print("Digite o numero do item a ser excluido: ");
            int itemParaExlcuir = validarValor();
            scanner.nextLine();

            while (!sair) {
                if (itemParaExlcuir >= 1 || itemParaExlcuir <= numeroDeItensNoCarrinho) {

                    try {
                        buscarPedidosUnitarioPeloNumeroDoPedido.setLong(1, numeroDeItensNoCarrinho);
                        ResultSet pedidosUnitario = buscarPedidosUnitarioPeloNumeroDoPedido.executeQuery();
                        Map<Integer, Integer> mapaPedidoUnitario = new LinkedHashMap<>();
                        int contador = 0;

                        while (pedidosUnitario.next()) {
                            contador++;
                            mapaPedidoUnitario.put(contador, pedidosUnitario.getInt(1));
                        }

                        int idPedidoUnitario = mapaPedidoUnitario.get(itemParaExlcuir);
                        conn.setAutoCommit(false);
                        deletarPedidoDeTabelaPedido.setInt(1, idPedidoUnitario);
                        int linhaAfetada = deletarPedidoDeTabelaPedido.executeUpdate();

                        if (linhaAfetada == 1) {
                            conn.commit();
                            System.out.println("Item unitario excluido do carrinho.");
                        } else {
                            throw new SQLException("Falha ao tentar excluir pedido unitario a tabela pedido");
                        }
                    } catch (SQLException e) {
                        System.out.println("Exluir do carrinho pedido gerou exceção");
                        e.printStackTrace();
                        try {
                            System.out.println("Realizando Rollback");
                            conn.rollback();
                        } catch (SQLException ex) {
                            System.out.println("Problemas ao executar o Rollback");
                            ex.printStackTrace();
                        }
                    } finally {
                        try {
                            sair = true;
                            System.out.println("Voltando ao comportamento padrao do commit");
                            conn.setAutoCommit(true);
                        } catch (SQLException e) {
                            System.out.println("Nao foi possivel restaurar o auto-commit");
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Valor inserido inváldido.");
                    System.out.print("Digite o numero do item a ser excluido: ");
                    itemParaExlcuir = validarValor();
                    scanner.nextLine();
                }
            }
        }
    }

    public int validarValor(){
        Scanner scanner = new Scanner(System.in);
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

    public void adicionarPedidoAoCliente(String nome, long numeroDoPedido) {
        int idCliente = 0;
        try {
            buscarIdCliente.setString(1,nome);
            ResultSet resultSet = buscarIdCliente.executeQuery();
            idCliente=resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Excecao so procurar idCliente em adicionarPedidoAoCliente.");
            e.printStackTrace();
        }

        try {
            buscarPedidosUnitarioPeloNumeroDoPedido.setLong(1,numeroDoPedido);
            ResultSet idPedidosUnitario = buscarPedidosUnitarioPeloNumeroDoPedido.executeQuery();

            conn.setAutoCommit(false);
            while (idPedidosUnitario.next()){
                adicionarPedidosUnitariosClientePedido.setLong(1, idCliente);
                adicionarPedidosUnitariosClientePedido.setLong(2, idPedidosUnitario.getLong(1));
                int linhaAfetada = adicionarPedidosUnitariosClientePedido.executeUpdate();
                if (linhaAfetada == 1) {

                } else {
                    throw new SQLException("Falha ao tentar adicionar pedido unitario a tabela cliente_pedido.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Adicionar pedido unitaria a tabela cliente_pedido gerou acessao!");
            e.printStackTrace();
            try {
                System.out.println("Realizando Rollback.");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback nao foi feito!");
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nao foi possivel voltar o commit ao seu comporatamento padrao!");
                e.printStackTrace();
            }
        }

    }

    public void mostrarListaDeClientes() {
        String sql = "SELECT " + COLUNA_CLIENTE_ID + ", " + COLUNA_CLIENTE_NOME + " FROM " + TABELA_CLIENTE;
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet!=null ) {
                while (resultSet.next()) {
                    int _id = resultSet.getInt(1);
                    String nome = resultSet.getString(2);
                    System.out.printf("%d - %s", _id, nome.toUpperCase());
                    System.out.println();
                }
            } else {
                System.out.println("Ainda não existem clientes cadastrados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPedidosDoCliente(String nome) {
        if (clienteCadastrado(nome)) {
            try {
                buscarNumeroDoPedioPeloNome.setString(1, nome);
                ResultSet resultBuscarNumeroDoPedido = buscarNumeroDoPedioPeloNome.executeQuery();
                if (resultBuscarNumeroDoPedido != null) {
                    System.out.format("Cliente: %s.\n", nome.toUpperCase());

                    while (resultBuscarNumeroDoPedido.next()) {
                        int numeroDoPedido = resultBuscarNumeroDoPedido.getInt(1);
                        System.out.format("    Pedido %d:\n", numeroDoPedido);

                        buscarItemDoCardapioPeloNumeroDoPedido.setInt(1, numeroDoPedido);
                        ResultSet resultItemCardapio = buscarItemDoCardapioPeloNumeroDoPedido.executeQuery();
                        while (resultItemCardapio.next()) {
                            System.out.format("        Item   : %s\n", resultItemCardapio.getString(1));
                            System.out.format("        Valor  : %.2f\n", resultItemCardapio.getDouble(2));
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("Cliente ainda não efetuou nenhum pedido.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Cliente não cadastrado no banco de dados.");
        }
    }

    public double somaValorDoPedido(long numeroDoPedido){
        try {
            somaDosProdutos.setLong(1,numeroDoPedido);
            ResultSet resultSet = somaDosProdutos.executeQuery();
            double total = resultSet.getDouble(1);
            return total;

        }catch (SQLException e){
            System.out.println("Escecao em somaValorDoPedido");
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Pedido> itensDentroDoPedido (long numeroDoPedido){
        try {
            buscarItensNoCarrinho.setLong(1,numeroDoPedido);
            ResultSet itensDoPedido = buscarItensNoCarrinho.executeQuery();
            ArrayList<Pedido> pedidos = new ArrayList<>();
            Pedido pedido;
            while (itensDoPedido.next()){
                pedido = new Pedido(itensDoPedido.getString(1),
                        itensDoPedido.getDouble(2));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            System.out.println("Excecao em dataSource.itensDentroDoPeido().");
            e.printStackTrace();
            return null;
        }
    }

}































