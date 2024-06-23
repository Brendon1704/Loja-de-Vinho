import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDisplay extends JFrame {
    private JPanel productPanel;
    private JButton verCarrinhoButton;
    private JButton loginButton;
    private JButton criarContaButton;
    private JButton sairButton;
    private JButton acompanharPedidoButton;
    private JLabel userLabel;
    private List<Vinho> itensCarrinho = new ArrayList<>();
    private String usuarioAtual = null;
    private String codigoRastreamento = null;

    public ProductDisplay(List<Vinho> vinhos) {
        setTitle("Vitrine de Vinhos");
        setSize(1200, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 10, 10));
        for (Vinho vinho : vinhos) {
            JPanel cartaoProduto = criarCartaoProduto(vinho);
            productPanel.add(cartaoProduto);
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        verCarrinhoButton = new JButton("Ver Carrinho");
        loginButton = new JButton("Login");
        criarContaButton = new JButton("Criar Conta");
        sairButton = new JButton("Sair");
        acompanharPedidoButton = new JButton("Acompanhar Pedido");
        userLabel = new JLabel("Usuário: Não logado");

        verCarrinhoButton.addActionListener(e -> new TelaCarrinho(itensCarrinho, this));
        loginButton.addActionListener(e -> new TelaLogin(this));
        criarContaButton.addActionListener(e -> new TelaCriarConta(this));
        sairButton.addActionListener(e -> System.exit(0));
        acompanharPedidoButton.addActionListener(e -> new TelaAcompanhamentoPedido(codigoRastreamento));

        painelInferior.add(userLabel);
        painelInferior.add(verCarrinhoButton);
        painelInferior.add(loginButton);
        painelInferior.add(criarContaButton);
        painelInferior.add(acompanharPedidoButton);
        painelInferior.add(sairButton);

        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void setUsuarioAtual(String usuario) {
        usuarioAtual = usuario;
        userLabel.setText("Usuário: " + usuario);
    }

    public String getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setCodigoRastreamento(String codigo) {
        codigoRastreamento = codigo;
    }

    private JPanel criarCartaoProduto(Vinho vinho) {
        JPanel cartaoProduto = new JPanel();
        cartaoProduto.setLayout(new BorderLayout(10, 10));
        cartaoProduto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nomeProduto = new JLabel(vinho.getNome(), JLabel.CENTER);
        nomeProduto.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel precoProduto = new JLabel("R$ " + String.format("%.2f", vinho.getPreco()), JLabel.CENTER);
        precoProduto.setFont(new Font("Arial", Font.PLAIN, 12));

        JTextArea descricaoProduto = new JTextArea(vinho.getDescricao());
        descricaoProduto.setEditable(false);
        descricaoProduto.setLineWrap(true);
        descricaoProduto.setWrapStyleWord(true);

        JButton verDescricaoButton = new JButton("Ver Descrição");
        verDescricaoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, descricaoProduto, "Descrição de " + vinho.getNome(), JOptionPane.PLAIN_MESSAGE);
        });

        JButton adicionarAoCarrinhoButton = new JButton("Adicionar ao Carrinho");
        adicionarAoCarrinhoButton.addActionListener(e -> {
            itensCarrinho.add(vinho);
            JOptionPane.showMessageDialog(this, "Vinho adicionado ao carrinho!");
        });

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 5, 5));
        painelBotoes.add(verDescricaoButton);
        painelBotoes.add(adicionarAoCarrinhoButton);

        cartaoProduto.add(nomeProduto, BorderLayout.NORTH);
        cartaoProduto.add(precoProduto, BorderLayout.CENTER);
        cartaoProduto.add(painelBotoes, BorderLayout.SOUTH);

        return cartaoProduto;
    }

    public static void main(String[] args) {
        List<Vinho> vinhos = new ArrayList<>();
        vinhos.add(new Vinho("Cabernet Sauvignon", 120.00, "Um vinho tinto encorpado"));
        vinhos.add(new Vinho("Chardonnay", 85.00, "Um vinho branco fresco"));
        vinhos.add(new Vinho("Merlot", 100.00, "Um vinho tinto suave"));
        vinhos.add(new Vinho("Pinot Noir", 150.00, "Um vinho tinto elegante"));
        vinhos.add(new Vinho("Malbec", 110.00, "Um vinho tinto encorpado e frutado"));
        vinhos.add(new Vinho("Syrah", 95.00, "Um vinho tinto picante e aromático"));
        vinhos.add(new Vinho("Sauvignon Blanc", 90.00, "Um vinho branco refrescante com notas cítricas"));
        vinhos.add(new Vinho("Carmenere", 105.00, "Um vinho tinto com sabor de frutas vermelhas e especiarias"));
        vinhos.add(new Vinho("Riesling", 80.00, "Um vinho branco delicado e floral"));
        vinhos.add(new Vinho("Tempranillo", 115.00, "Um vinho tinto com notas de couro e frutas maduras"));
        vinhos.add(new Vinho("Zinfandel", 125.00, "Um vinho tinto rico e encorpado com sabor de amora e especiarias"));
        vinhos.add(new Vinho("Gewürztraminer", 95.00, "Um vinho branco aromático com notas de lichia e especiarias"));
        vinhos.add(new Vinho("Cabernet Franc", 130.00, "Um vinho tinto elegante com taninos suaves e notas de pimenta"));
        vinhos.add(new Vinho("Viognier", 85.00, "Um vinho branco perfumado com aromas de flores e frutas tropicais"));
        vinhos.add(new Vinho("Grenache", 110.00, "Um vinho tinto com sabor de cereja e especiarias"));

        new ProductDisplay(vinhos);
    }
}
