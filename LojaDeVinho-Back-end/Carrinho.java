import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemVenda> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
            System.out.println("Item removido do carrinho.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void exibirCarrinho() {
        System.out.println("Carrinho de Compras:");
        for (int i = 0; i < itens.size(); i++) {
            ItemVenda item = itens.get(i);
            System.out.println((i + 1) + ". " + item.getProduto().getNome() + " - Quantidade: " + item.getQuantidade());
        }
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
}
