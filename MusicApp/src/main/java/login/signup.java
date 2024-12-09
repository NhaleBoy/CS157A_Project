package login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.UserDAO;
import jdbc.User;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			UserDAO dao = new UserDAO();
			String username = sanitize.sanitizeTrim(request.getParameter("username"));
			String password = sanitize.sanitizeTrim(request.getParameter("password"));
			String email = sanitize.sanitizeTrim(request.getParameter("email"));
			// Begin validating username, password, and email
			if(sanitize.isValidEmail(email)) {
				//Check if username already exists
				if(dao.doesEmailExist(email) || dao.doesUsernameExist(username))
				{
					String message = "Please use other email or username.";
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
				else {
					//add information to database, UserID is auto increment and pref Genre is null
					User user = new User(username, password, email);
					dao.addUser(user);
				}
			}
			else {
				String message = "Invalid Email.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
			String message = "Sign up created please log in.";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		catch(Exception e){
			out.println("Error: " + e.getMessage());
		}
		
	}

}
