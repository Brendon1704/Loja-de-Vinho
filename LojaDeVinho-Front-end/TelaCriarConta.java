import javax.swing.*;
import java.awt.*;

public class TelaCriarConta extends JFrame {
    private JTextField campoNovoUsuario;
    private JPasswordField campoNovaSenha;
    private JTextField campoIdade;
    private JButton botaoCriarConta;
    private ProductDisplay telaPrincipal;

    public TelaCriarConta(ProductDisplay telaPrincipal) {
        this.telaPrincipal = telaPrincipal;

        setTitle("Criar Conta");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel rotuloNovoUsuario = new JLabel("Novo Usuário:");
        campoNovoUsuario = new JTextField();
        JLabel rotuloNovaSenha = new JLabel("Nova Senha:");
        campoNovaSenha = new JPasswordField();
        JLabel rotuloIdade = new JLabel("Idade:");
        campoIdade = new JTextField();
        botaoCriarConta = new JButton("Criar Conta");

        botaoCriarConta.addActionListener(e -> {

            String usuario = campoNovoUsuario.getText();
            String senha = new String(campoNovaSenha.getPassword());
            int idade;
            try {
                idade = Integer.parseInt(campoIdade.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira uma idade válida.");
                return;
            }

            if (idade < 18) {
                JOptionPane.showMessageDialog(this, "Você precisa ter 18 anos ou mais para criar uma conta.");
                return;
            }


            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
            telaPrincipal.setUsuarioAtual(usuario);
            dispose();
        });

        add(rotuloNovoUsuario);
        add(campoNovoUsuario);
        add(rotuloNovaSenha);
        add(campoNovaSenha);
        add(rotuloIdade);
        add(campoIdade);
        add(new JLabel());
        add(botaoCriarConta);

        setVisible(true);
    }
}

