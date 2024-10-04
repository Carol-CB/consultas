import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nome;
    private LocalDate dataNascimento;
    private List<Atendimento> atendimentos;

    public Paciente(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.atendimentos = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }
    public void adicionarAtendimento(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }
    @Override
    public String toString() {
        return "Nome: " + nome + ", Data de Nascimento: " + dataNascimento;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return nome.equals(paciente.nome);
    }
}