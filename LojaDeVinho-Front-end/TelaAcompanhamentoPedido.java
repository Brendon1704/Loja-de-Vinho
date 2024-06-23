import javax.swing.*;
import java.awt.*;

public class TelaAcompanhamentoPedido extends JFrame {
    private JLabel statusPedidoLabel;
    private String codigoRastreamento;

    public TelaAcompanhamentoPedido(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;

        setTitle("Acompanhamento de Pedido");
        setSize(1300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusPedidoLabel = new JLabel("Seu pedido está sendo processado. Código de rastreamento: " + codigoRastreamento + " Entrega será feita em até 3 dias úteis via Correios.", JLabel.CENTER);
        statusPedidoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(statusPedidoLabel, BorderLayout.CENTER);

        setVisible(true);
    }
}
