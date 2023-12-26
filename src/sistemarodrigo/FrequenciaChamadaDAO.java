package sistemarodrigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FrequenciaChamadaDAO {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sistemarodrigo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void inserirFrequenciaChamada(String matricula, String disciplina, String turma, String numeroAula, boolean presenca) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO Frequencia_Chamada (Matricula, CodigoDisc, NumeroTurma, NumeroAula, Presenca) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, matricula);
                    pstmt.setString(2, disciplina);
                    pstmt.setString(3, turma);
                    pstmt.setString(4, numeroAula);
                    pstmt.setBoolean(5, presenca);
                    pstmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao inserir frequência chamada: " + e.getMessage());
        }
    }
}

