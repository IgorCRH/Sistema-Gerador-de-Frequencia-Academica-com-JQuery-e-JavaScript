package sistemarodrigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemarodrigo.AlunoModel;
import sistemarodrigo.TurmaModel;

public class TurmaDAO {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sistemarodrigo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public List<TurmaModel> getAllTurmas() {
        List<TurmaModel> turmas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT NumeroTurma, Matricula, CodigoDisc FROM Turma";
                try (PreparedStatement pstmt = connection.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String numeroTurma = rs.getString("NumeroTurma");
                        String matriculasTurma = rs.getString("Matricula");
                        String disciplinaCodigoDisc = rs.getString("CodigoDisc");

                        
                        DisciplinaModel disciplina = getDisciplinaByCodigo(disciplinaCodigoDisc, connection);

                       
                        List<AlunoModel> alunos = AlunoModel.getAlunosDaTurma(numeroTurma, connection);

                        TurmaModel turma = new TurmaModel(numeroTurma, disciplina, alunos);
                        turmas.add(turma);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return turmas;
    }

    public static List<TurmaModel> getTurmasPorDisciplina(String disciplinaCodigoDisc) {
        List<TurmaModel> turmas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT NumeroTurma FROM Turma WHERE CodigoDisc = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, disciplinaCodigoDisc);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            String numeroTurma = rs.getString("NumeroTurma");

                          
                            DisciplinaModel disciplina = getDisciplinaByCodigo(disciplinaCodigoDisc, connection);

                            // Obtém a lista de alunos associada à turma
                            List<AlunoModel> alunos = AlunoModel.getAlunosDaTurma(numeroTurma, connection);

                            TurmaModel turma = new TurmaModel(numeroTurma, disciplina, alunos);
                            turmas.add(turma);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return turmas;
    }

    private static DisciplinaModel getDisciplinaByCodigo(String codigoDisciplina, Connection connection) throws SQLException {
        String sql = "SELECT CodigoDisc, Nome, Departamento FROM Disciplina WHERE CodigoDisc = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, codigoDisciplina);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nomeDisciplina = rs.getString("Nome");
                    String nomeDepartamento = rs.getString("Departamento");
                    return new DisciplinaModel(codigoDisciplina, nomeDisciplina, nomeDepartamento);
                }
            }
        }
        return null;
    }


    
}

