package MenuAndItens;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private String titulo;
    private String subTitulo;
    private ArrayList<ItemSemValor> itemSemValors;
    private ArrayList<ItemComValor> itemComValors;

    public Menu(String titulo){
        this(titulo,null);//poqunho de duvida
    }

    public Menu(String titulo, String subTitulo) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.itemSemValors = new ArrayList<ItemSemValor>();
        this.itemComValors = new ArrayList<ItemComValor>();
    }

    public void imprimir(){
        System.out.println("========================================");
        System.out.println("           "+titulo);
        if (subTitulo != null){
            System.out.println("     "+subTitulo);
        }
        System.out.println(" ");
        if (itemSemValors != null){
            int contatdorMenu = 1;
            for (ItemSemValor itemSemValor : itemSemValors){
                System.out.println("     "+contatdorMenu+"- "+itemSemValor.getTexto());
                contatdorMenu++;
            }
        }
        if (itemComValors != null){
            int contatdorMenu = 1;
            for (ItemComValor itemComValor : itemComValors){
                System.out.println("     "+contatdorMenu+"- "+fortamarPreco(itemComValor.getTexto(),itemComValor.getPreco()));
                contatdorMenu++;
            }
        }
        System.out.println("");
        System.out.println("     0- Finalizar "+titulo.toLowerCase());
        System.out.println("========================================");
    }

    public int lerOpcao(){
        int opcao = -1;
        while (true) {
            System.out.print("Opção: ");
            boolean eUmInteiro = scanner.hasNextInt();
            if (eUmInteiro){
                opcao = scanner.nextInt();
                if (opcao>=0 && opcao <=itemSemValors.size() && itemSemValors != null){
                    break;
                }else if(opcao>=0 && opcao <=itemComValors.size() && itemComValors != null){
                    break;
                }else {
                    System.out.println("Opcão inválida, digite novamente");
                }
            }else {
                System.out.println("Apenas números sao validos nesse menu");
            }
            scanner.nextLine();
        }
        return opcao;
    }

    private String fortamarPreco (String item, Double valor){
        return String.format("%-20s", item) + " R$" + String.format(Locale.GERMAN, "%,.2f", valor);
    }

    public void adicionarItemSemValor(String nome, String texto){
        itemSemValors.add(new ItemSemValor(nome,texto));
    }

    public void adicionarItemComValor(String nome, String texto,Double preco){
        itemComValors.add(new ItemComValor(nome,texto,preco));
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public ArrayList<ItemSemValor> getItemSemValors() {
        return itemSemValors;
    }

    public ArrayList<ItemComValor> getItemComValors() {
        return itemComValors;
    }
}
