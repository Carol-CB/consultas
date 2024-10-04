import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class Sistema {
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1-Novo paciente");
            System.out.println("2- Editar paciente");
            System.out.println("3- Novo atendimento");
            System.out.println("4- Listar pacientes");
            System.out.println("5- mostrar 1 paciente");
            System.out.println("6- Apagar registro de um paciente");
            System.out.println("7-Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    novoPaciente(scanner);
                    break;
                case 2:
                    editarPaciente(scanner);
                    break;
                case 3:
                    novoAtend(scanner);
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    mostrar(scanner);
                    break;
                case 6:
                    deletar(scanner);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("escolha uma oção possível...");
            }
        } while (opcao != 7);

        scanner.close();
    }
    private static void novoPaciente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        LocalDate dataNascimento;
        try {
            dataNascimento = LocalDate.parse(dataStr, formatoData);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de datainválido!");
            dataNascimento = LocalDate.now();
        }
        Paciente paciente = new Paciente(nome, dataNascimento);
        pacientes.add(paciente);
    }

    private static void editarPaciente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                System.out.print("Novo nome: ");
                paciente.setNome(scanner.nextLine());
                System.out.print("Nova data de nascimento (dd/mm/yyyy): ");
                String dataStr = scanner.nextLine();
                try {
                    paciente.setDataNascimento(LocalDate.parse(dataStr, formatoData));
                } catch (DateTimeParseException e) {
                    System.out.println("Inválido");
                }
                return;
            }
        }
        System.out.println("Esse paciente não existe...");
    }
    private static void novoAtend(Scanner scanner) {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                System.out.print("Data atendimento (dd/mm/yyyy): ");
                String dataStr = scanner.nextLine();
                LocalDate data;
                try {
                    data = LocalDate.parse(dataStr, formatoData);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido.");
                    data = LocalDate.now();
                }
                System.out.print("Descrição atendimento: ");
                String descricao = scanner.nextLine();
                Atendimento atendimento = new Atendimento(data, descricao);
                paciente.adicionarAtendimento(atendimento);
                return;
            }
        }
        System.out.println("Paciente não encontrado.");
    }

    private static void listar() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }
    private static void mostrar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                System.out.println(paciente);
                List<Atendimento> atendimentos = paciente.getAtendimentos();
                for (int i = 0; i < atendimentos.size(); i++) {
                    System.out.println(atendimentos.get(i));
                    if ((i + 1) % 5 == 0) {
                        System.out.println("Para continuar: enter.");
                        scanner.nextLine();
                    }
                }
                return;
            }
        }
        System.out.println("não encontrado.");
    }

    private static void deletar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        Paciente remover = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                remover = paciente;
                break;
            }
        }
        if (remover != null) {
            pacientes.remove(remover);
            System.out.println("Paciente removidop.");
        } else {
            System.out.println("Não encontrado.");
        }
    }
}