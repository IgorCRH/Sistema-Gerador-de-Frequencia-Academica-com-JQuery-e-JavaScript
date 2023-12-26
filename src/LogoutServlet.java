import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém a sessão
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalida a sessão
            session.invalidate();
        }

        // Redireciona para a página de login (ajuste o caminho conforme necessário)
        response.sendRedirect("login.jsp");
    }
}