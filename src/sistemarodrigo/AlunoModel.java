package sistemarodrigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel {
    private int matricula;
    private String nome;
    private String login;
    private String cpf;
    private String senha;
    private String email;

    public AlunoModel(int matricula, String nome, String login, String cpf, String senha, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.login = login;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    protected static List<AlunoModel> getAlunosDaTurma(String numeroTurma, Connection connection) throws SQLException {
        List<AlunoModel> alunos = new ArrayList<>();

        String sql = "SELECT Matricula FROM Turma WHERE NumeroTurma = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroTurma);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String matriculaAluno = rs.getString("Matricula");

                    // Obtém o aluno associado à matrícula
                    AlunoModel aluno = getAlunoByMatricula(matriculaAluno, connection);
                    alunos.add(aluno);
                }
            }
        }

        return alunos;
    }
    
    private static AlunoModel getAlunoByMatricula(String matriculaAluno, Connection connection) throws SQLException {
        String sql = "SELECT Matricula, Nome, Login, CPF, Senha, Email FROM Alunos WHERE Matricula = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, matriculaAluno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int matricula = rs.getInt("Matricula");
                    String nome = rs.getString("Nome");
                    String login = rs.getString("Login");
                    String cpf = rs.getString("CPF");
                    String senha = rs.getString("Senha");
                    String email = rs.getString("Email");

                    return new AlunoModel(matricula, nome, login, cpf, senha, email);
                }
            }
        }

        return null;
    }

}
