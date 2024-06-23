import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class TelaPagamento extends JFrame {
    private JTextField campoNumeroCartao;
    private JTextField campoTitularCartao;
    private JTextField campoDataExpiracao;
    private JTextField campoCVV;
    private JButton botaoPagar;
    private JComboBox<String> metodoPagamentoComboBox;
    private List<Vinho> itensCarrinho;
    private ProductDisplay telaPrincipal;

    public TelaPagamento(List<Vinho> itensCarrinho, ProductDisplay telaPrincipal) {
        this.itensCarrinho = itensCarrinho;
        this.telaPrincipal = telaPrincipal;

        setTitle("Pagamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel rotuloNumeroCartao = new JLabel("Número do Cartão:");
        campoNumeroCartao = new JTextField();
        JLabel rotuloTitularCartao = new JLabel("Nome no Cartão:");
        campoTitularCartao = new JTextField();
        JLabel rotuloDataExpiracao = new JLabel("Data de Expiração:");
        campoDataExpiracao = new JTextField();
        JLabel rotuloCVV = new JLabel("CVV:");
        campoCVV = new JTextField();


        JLabel rotuloMetodoPagamento = new JLabel("Método de Pagamento:");
        String[] opcoesMetodoPagamento = {"Cartão de Crédito", "Cartão de Débito"};
        metodoPagamentoComboBox = new JComboBox<>(opcoesMetodoPagamento);

        botaoPagar = new JButton("Pagar");

        botaoPagar.addActionListener(e -> {
            String metodoSelecionado = (String) metodoPagamentoComboBox.getSelectedItem();

            switch (metodoSelecionado) {
                case "Cartão de Crédito":
                    processarPagamentoCartao("Crédito");
                    break;
                case "Cartão de Débito":
                    processarPagamentoCartao("Débito");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Método de pagamento não suportado.");
            }
        });

        add(rotuloNumeroCartao);
        add(campoNumeroCartao);
        add(rotuloTitularCartao);
        add(campoTitularCartao);
        add(rotuloDataExpiracao);
        add(campoDataExpiracao);
        add(rotuloCVV);
        add(campoCVV);
        add(rotuloMetodoPagamento);
        add(metodoPagamentoComboBox);
        add(new JLabel());
        add(botaoPagar);

        setVisible(true);
    }

    private void processarPagamentoCartao(String tipo) {

        String codigoRastreamento = UUID.randomUUID().toString();
        telaPrincipal.setCodigoRastreamento(codigoRastreamento);
        JOptionPane.showMessageDialog(this, "Pagamento com " + tipo + " realizado com sucesso!\nCódigo de rastreamento: " + codigoRastreamento);
        new TelaAcompanhamentoPedido(codigoRastreamento);
        dispose();
    }
}
