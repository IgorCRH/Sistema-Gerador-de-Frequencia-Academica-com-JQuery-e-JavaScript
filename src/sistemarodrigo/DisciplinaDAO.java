package sistemarodrigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sistemarodrigo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void cadastrarDisciplina(String nomeDisciplina, String departamento) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO Disciplina (Nome, Departamento) VALUES (?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, nomeDisciplina);
                    pstmt.setString(2, departamento);
                    pstmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }
    
    public static List<DisciplinaModel> getAllDisciplinas() {
        List<DisciplinaModel> disciplinas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT CodigoDisc, Nome, Departamento FROM Disciplina";
                try (PreparedStatement pstmt = connection.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String codigoDisciplina = rs.getString("CodigoDisc");
                        String nomeDisciplina = rs.getString("Nome");
                        String departamento = rs.getString("Departamento");

                        DisciplinaModel disciplina = new DisciplinaModel(codigoDisciplina, nomeDisciplina, departamento);
                        disciplinas.add(disciplina);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }
}
