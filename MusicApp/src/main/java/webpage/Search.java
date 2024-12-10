package webpage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.Audio;
import jdbc.AudioDAO;
import jdbc.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchBy = request.getParameter("mediaType");
		String searchbox = request.getParameter("searchbox");
		AudioDAO dao = new AudioDAO();
		if(searchBy.equals("Song"))
		{
			try {
				Audio audio = dao.getAudioThatIsSong(searchbox);
				request.setAttribute("search", audio);
				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e) {
				System.out.println("Something went wrong");
			}
		}
		else {
			try {
				Audio audio = dao.getAudioThatIsPod(searchbox);
				request.setAttribute("search", audio);
				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e) {
				System.out.println("Something went wrong");
			}
		}
	}

}
