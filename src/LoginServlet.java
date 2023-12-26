import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sistemarodrigo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        try {

            String userType = authenticateUser(login, senha);

            if (userType != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userType", userType);


                if ("aluno".equals(userType)) {
                    response.sendRedirect("paginadoaluno.jsp");
                } else if ("docente".equals(userType)) {
                    response.sendRedirect("paginadocente.jsp");
                } else if ("coordenador".equals(userType)) {
                    response.sendRedirect("paginacoordenador.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private String authenticateUser(String login, String senha) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            String alunoQuery = "SELECT 'aluno' AS TipoUsuario FROM Alunos WHERE Login = ? AND Senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(alunoQuery)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, senha);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("TipoUsuario");
                    }
                }
            }


            String docenteQuery = "SELECT 'docente' AS TipoUsuario FROM Docente WHERE Login = ? AND Senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(docenteQuery)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, senha);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("TipoUsuario");
                    }
                }
            }


            String coordenadorQuery = "SELECT 'coordenador' AS TipoUsuario FROM Coordenador WHERE Login = ? AND Senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(coordenadorQuery)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, senha);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("TipoUsuario");
                    }
                }
            }
        }
        return null;
    }

}
