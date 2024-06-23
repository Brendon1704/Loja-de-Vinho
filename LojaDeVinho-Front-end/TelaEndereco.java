import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEndereco extends JFrame {
    private JTextField campoEndereco;
    private JTextField campoCidade;
    private JTextField campoCEP;
    private JButton botaoConfirmarEndereco;
    private List<Vinho> itensCarrinho;
    private ProductDisplay telaPrincipal;

    public TelaEndereco(List<Vinho> itensCarrinho, ProductDisplay telaPrincipal) {
        this.itensCarrinho = itensCarrinho;
        this.telaPrincipal = telaPrincipal;

        setTitle("Endereço de Entrega");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel rotuloEndereco = new JLabel("Endereço:");
        campoEndereco = new JTextField();
        JLabel rotuloCidade = new JLabel("Cidade:");
        campoCidade = new JTextField();
        JLabel rotuloCEP = new JLabel("CEP:");
        campoCEP = new JTextField();
        botaoConfirmarEndereco = new JButton("Confirmar Endereço");

        botaoConfirmarEndereco.addActionListener(e -> new TelaPagamento(itensCarrinho, telaPrincipal));

        add(rotuloEndereco);
        add(campoEndereco);
        add(rotuloCidade);
        add(campoCidade);
        add(rotuloCEP);
        add(campoCEP);
        add(new JLabel());
        add(botaoConfirmarEndereco);

        setVisible(true);
    }
}
