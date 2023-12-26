import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemarodrigo.DisciplinaDAO;

@WebServlet("/CadastrarDisciplinaServlet")
public class CadastrarDisciplinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeDisciplina = request.getParameter("nomedisc");
        String departamentoDisciplina = request.getParameter("departamentodisc");

        try {
            DisciplinaDAO.cadastrarDisciplina(nomeDisciplina, departamentoDisciplina);
            response.sendRedirect("inscricaoDisciplina.jsp?success=true");
        } catch (SQLException e) {
            // Trate a exceção de banco de dados adequadamente
            e.printStackTrace();
            response.sendRedirect("inscricaoDisciplina.jsp?success=false");
        }
    }
}
