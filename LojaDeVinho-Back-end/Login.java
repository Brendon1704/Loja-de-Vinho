import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private List<Cliente> clientes;

    public Login() {
        this.clientes = new ArrayList<>();
    }

    public Cliente criarConta(Scanner scanner) {
        System.out.println("\n=== Criar Conta ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento (dia mês ano, com espaços sem barras): ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int ano = scanner.nextInt();
        scanner.nextLine();
        DataNascimento dataNascimento = new DataNascimento(dia, mes, ano);

        if (verificarIdade(dataNascimento)) {
            Cliente cliente = new Cliente(nome, cpf, dataNascimento);
            clientes.add(cliente);
            System.out.println("Conta criada com sucesso para " + cliente.getNome() + ".");
            return cliente;
        } else {
            System.out.println("É necessário ter mais de 18 anos para criar uma conta.");
            return null;
        }
    }

    public Cliente realizarLogin(Scanner scanner) {
        System.out.println("\n=== Login ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("Login realizado com sucesso para " + cliente.getNome() + ".");
                return cliente;
            }
        }

        System.out.println("Cliente não encontrado.");
        return null;
    }

    private boolean verificarIdade(DataNascimento dataNascimento) {

        DataNascimento hoje = new DataNascimento(21, 6, 2024);
        if (hoje.getAno() - dataNascimento.getAno() > 18) {
            return true;
        } else if (hoje.getAno() - dataNascimento.getAno() == 18) {
            if (hoje.getMes() > dataNascimento.getMes()) {
                return true;
            } else if (hoje.getMes() == dataNascimento.getMes()) {
                if (hoje.getDia() >= dataNascimento.getDia()) {
                    return true;
                }
            }
        }
        return false;
    }
}
