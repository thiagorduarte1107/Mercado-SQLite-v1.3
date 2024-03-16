package Model;

import Helper.Utils;
import Model.Produto;
import Model.ProdutoDAO;

import java.util.Scanner;

public class Mercado {
    private static Scanner teclado = new Scanner(System.in);
    private static ProdutoDAO produtoDao = new ProdutoDAO(); // Adicionado

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("=================================");
        System.out.println("========= BEM-VINDO(A) ==========");
        System.out.println("======== MERCADO CENTRAL ========");
        System.out.println("=================================");
        System.out.println(" Selecione uma opção abaixo: ");
        System.out.println("01 - CADASTRAR PRODUTO: ");
        System.out.println("02 - LISTAR PRODUTO: ");
        System.out.println("03 - COMPRAR PRODUTO: ");
        System.out.println("04 - VISUALIZAR CARRINHO: ");
        System.out.println("05 - SAIR: ");

        try {
            int opcao = lerOpcaoDoUsuario();
            processarOpcaoMenu(opcao);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            pausarEMostrarMenu();
        }
    }

    private static int lerOpcaoDoUsuario() {
        String entrada = teclado.nextLine();
        return Integer.parseInt(entrada); // Pode lançar NumberFormatException se a entrada não for um número válido
    }

    private static void processarOpcaoMenu(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                visualizarCarrinho();
                break;
            case 5:
                System.out.println("===== OBRIGADO E VOLTE SEMPRE ======");
                Utils.pausar(2);
                System.exit(0);
            default:
                imprimirMensagemEPausar("OPÇÃO INVÁLIDA.");
                break;
        }
    }

    private static void pausarEMostrarMenu() {
        Utils.pausar(2);
        menu();
    }

    private static void imprimirMensagemEPausar(String mensagem) {
        System.out.println(mensagem);
        Utils.pausar(2);
    }

    private static void cadastrarProduto() {
        System.out.println("CADASTRO DE PRODUTO");
        System.out.println("===================");
        System.out.println("INFORME O NOME DO PRODUTO: ");
        String nome = teclado.nextLine();
        System.out.println("INFORME A DESCRIÇÃO DO PRODUTO: ");
        String descricao = teclado.nextLine();
        System.out.println("INFORME O PREÇO DO PRODUTO: ");
        double preco = Double.parseDouble(teclado.nextLine());
        System.out.println("INFORME O CÓDIGO DO PRODUTO: ");
        int codigo = Integer.parseInt(teclado.nextLine());
        System.out.println("INFORME A QUANTIDADE EM ESTOQUE DO PRODUTO: ");
        int estoque = Integer.parseInt(teclado.nextLine());

        Produto produto = new Produto(nome, descricao, preco, codigo, estoque);
        produtoDao.insertProduto(produto); // Adiciona o produto ao banco de dados
        System.out.println("O PRODUTO " + produto.getNome() + " FOI CADASTRADO COM SUCESSO.");
        pausarEMostrarMenu();
    }

    private static void listarProdutos() {
        System.out.println("LISTAGEM DE PRODUTOS ");
        System.out.println();

        produtoDao.listarProdutos(); // Lista os produtos do banco de dados

        pausarEMostrarMenu();
    }

    private static void comprarProdutos() {
        // Implemente conforme necessário
    }

    private static void visualizarCarrinho() {
        // Implemente conforme necessário
    }
}
