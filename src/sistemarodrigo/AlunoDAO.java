package sistemarodrigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sistemarodrigo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void cadastrarAlunonaTurma(String numeroTurma, String matricula, String codigoDisciplina) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            	
            	String sql = "INSERT INTO Turma (NumeroTurma, Matricula, CodigoDisc) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, numeroTurma);
                    if (matricula != null && !matricula.isEmpty()) {
                    int matriculaInt = Integer.parseInt(matricula);
                	pstmt.setInt(2, matriculaInt);
                    } else {
                    	System.out.println("Não foi possível.");
                    }
                    pstmt.setString(3, codigoDisciplina);
                    pstmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}