package login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import jdbc.UserDAO;
import login.sanitize;

/**
 * Servlet implementation class nameCheck
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			UserDAO dao = new UserDAO();
			String username = sanitize.sanitizeTrim(request.getParameter("username"));
			String password = sanitize.sanitizeTrim(request.getParameter("password"));
			
			
			if(dao.checkLogin(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				int id = dao.getUserIdViaUsername(username);
				session.setAttribute("userId", id);
				response.sendRedirect("homepage.jsp");
			}
			else
			{
				String message = "Invalid login.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e){
			out.println("Error: " + e.getMessage());
		}
	}

}
