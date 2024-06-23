import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JButton botaoCriarConta;
    private ProductDisplay telaPrincipal;

    public TelaLogin(ProductDisplay telaPrincipal) {
        this.telaPrincipal = telaPrincipal;

        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel rotuloUsuario = new JLabel("Usuário:");
        campoUsuario = new JTextField();
        JLabel rotuloSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();
        botaoLogin = new JButton("Login");
        botaoCriarConta = new JButton("Criar Conta");

        botaoLogin.addActionListener(e -> {

            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());
            if (usuario.equals("admin") && senha.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                telaPrincipal.setUsuarioAtual(usuario);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
            }
        });

        botaoCriarConta.addActionListener(e -> new TelaCriarConta(telaPrincipal));

        add(rotuloUsuario);
        add(campoUsuario);
        add(rotuloSenha);
        add(campoSenha);
        add(botaoCriarConta);
        add(botaoLogin);

        setVisible(true);
    }
}
