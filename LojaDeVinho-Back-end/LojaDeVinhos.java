import java.util.List;
import java.util.Scanner;

public class LojaDeVinhos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Produto albariño = new Produto("Albariño", 50.0, 10);
        Produto arbane = new Produto("Arbane", 45.0, 5);
        Produto bobal = new Produto("Bobal", 40.0, 8);
        Produto cabernetFranc = new Produto("Cabernet franc", 55.0, 7);
        Produto cabernetSauvignon = new Produto("Cabernet sauvignon", 60.0, 12);
        Produto carignan = new Produto("Carignan", 35.0, 6);
        Produto carmenere = new Produto("Carménère", 50.0, 10);
        Produto chardonnay = new Produto("Chardonnay", 45.0, 15);
        Produto cheninBlanc = new Produto("Chenin blanc", 40.0, 9);
        Produto garnacha = new Produto("Garnacha", 50.0, 8);
        Produto gewurztraminer = new Produto("Gewürztraminer", 55.0, 7);
        Produto malbec = new Produto("Malbec", 60.0, 10);
        Produto merlot = new Produto("Merlot", 50.0, 12);
        Produto montepulciano = new Produto("Montepulciano", 45.0, 5);
        Produto moscato = new Produto("Moscato", 40.0, 8);
        Produto mourvedre = new Produto("Mourvèdre", 55.0, 6);
        Produto petitVerdot = new Produto("Petit Verdot", 50.0, 10);
        Produto pinotGrigio = new Produto("Pinot grigio", 45.0, 15);
        Produto pinotNoir = new Produto("Pinot noir", 60.0, 9);
        Produto riesling = new Produto("Riesling", 50.0, 8);


        Estoque estoque = new Estoque();
        estoque.adicionarProduto(albariño);
        estoque.adicionarProduto(arbane);
        estoque.adicionarProduto(bobal);
        estoque.adicionarProduto(cabernetFranc);
        estoque.adicionarProduto(cabernetSauvignon);
        estoque.adicionarProduto(carignan);
        estoque.adicionarProduto(carmenere);
        estoque.adicionarProduto(chardonnay);
        estoque.adicionarProduto(cheninBlanc);
        estoque.adicionarProduto(garnacha);
        estoque.adicionarProduto(gewurztraminer);
        estoque.adicionarProduto(malbec);
        estoque.adicionarProduto(merlot);
        estoque.adicionarProduto(montepulciano);
        estoque.adicionarProduto(moscato);
        estoque.adicionarProduto(mourvedre);
        estoque.adicionarProduto(petitVerdot);
        estoque.adicionarProduto(pinotGrigio);
        estoque.adicionarProduto(pinotNoir);
        estoque.adicionarProduto(riesling);


        Carrinho carrinho = new Carrinho();


        Login login = new Login();

        boolean continuar = true;
        while (continuar) {
            exibirMenuPrincipal();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Cliente cliente = fazerLoginOuCriarConta(scanner, login);
                    if (cliente != null) {
                        System.out.println("Bem-vindo, " + cliente.getNome() + "!");
                    }
                    break;
                case 2:
                    exibirOpcoesDeCompra(scanner, estoque, carrinho);
                    break;
                case 3:
                    carrinho.exibirCarrinho();
                    break;
                case 4:
                    finalizarCompra(scanner, carrinho);
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saindo da aplicação. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Login / Criar Conta");
        System.out.println("2. Adicionar Produtos ao Carrinho");
        System.out.println("3. Exibir Carrinho");
        System.out.println("4. Finalizar Compra");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static Cliente fazerLoginOuCriarConta(Scanner scanner, Login login) {
        System.out.println("\n=== Login / Criar Conta ===");
        System.out.println("1. Login");
        System.out.println("2. Criar Conta");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                return login.realizarLogin(scanner);
            case 2:
                return login.criarConta(scanner);
            default:
                System.out.println("Opção inválida.");
                return null;
        }
    }

    private static void exibirOpcoesDeCompra(Scanner scanner, Estoque estoque, Carrinho carrinho) {
        boolean continuarComprando = true;
        while (continuarComprando) {
            List<Produto> produtos = estoque.getProdutos();
            exibirEstoque(produtos);

            System.out.println("Escolha um produto para adicionar ao carrinho (ou 0 para voltar):");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 0) {
                continuarComprando = false;
            } else if (escolha >= 1 && escolha <= produtos.size()) {
                System.out.print("Digite a quantidade desejada: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                Produto produtoEscolhido = produtos.get(escolha - 1);

                if (produtoEscolhido.getQuantidade() >= quantidade) {
                    ItemVenda item = new ItemVenda(produtoEscolhido, quantidade);
                    carrinho.adicionarItem(item);
                    System.out.println(produtoEscolhido.getNome() + " adicionado ao carrinho.");
                } else {
                    System.out.println("Quantidade indisponível em estoque.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private static void finalizarCompra(Scanner scanner, Carrinho carrinho) {
        if (carrinho.getItens().isEmpty()) {
            System.out.println("Não há itens no carrinho para finalizar a compra.");
            return;
        }

        System.out.println("\n=== Finalizar Compra ===");
        carrinho.exibirCarrinho();

        double total = calcularTotalCompra(carrinho);
        System.out.println("Total da Compra: R$ " + total);

        System.out.println("Meios de Pagamento Disponíveis:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. Pix");

        System.out.print("Selecione o meio de pagamento desejado: ");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoPagamento) {
            case 1:
                System.out.println("Pagamento realizado com Cartão de Crédito.");
                break;
            case 2:
                System.out.println("Pagamento realizado com Cartão de Débito.");
                break;
            case 3:
                System.out.println("Pagamento realizado com Pix.");
                break;
            default:
                System.out.println("Opção de pagamento inválida.");
                break;
        }

        carrinho.getItens().clear();
    }

    private static double calcularTotalCompra(Carrinho carrinho) {
        double total = 0.0;
        for (ItemVenda item : carrinho.getItens()) {
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total;
    }

    private static void exibirEstoque(List<Produto> produtos) {
        System.out.println("\n=== Estoque de Vinhos ===");
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + " - R$ " + produto.getPreco()
                    + " - Disponível: " + produto.getQuantidade() + " unidades");
        }
        System.out.println("0. Voltar");
    }
}
