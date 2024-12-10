package webpage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Settings
 */
@WebServlet("/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Settings() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		else if(request.getParameter("creatorpage") != null) {
			response.sendRedirect("creatorpage.jsp");
		}
		else{
			UserDAO dao = new UserDAO();
			String username = (String) session.getAttribute("username");
			int id = 0;
			try {
				id = dao.getUserIdViaUsername(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("dao get went wrong");
			}
			String genre = request.getParameter("genres");
			try {
				dao.updateGenre(genre, id);
				response.sendRedirect("homepage.jsp");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("dao update went wrong");
			}
		}
		
	}

}
