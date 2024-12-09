package webpage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jdbc.Audio;
import jdbc.AudioDAO;
import jdbc.UserDAO;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,
		maxFileSize = 1024 * 1024 * 100,
		maxRequestSize = 1024 * 1024 * 100
		)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		 String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		 if(fileExtension.equals("wav")){
			 for(Part part: request.getParts()) {
				 part.write("C:\\upload\\" + fileName);
				 String genre = request.getParameter("genre");
				 String category = request.getParameter("category");
				 try {
					int id = (int) request.getSession().getAttribute("userId");
					AudioDAO dao = new AudioDAO();
					dao.addAudio(new Audio(fileName, id, genre, category, "C:\\upload\\" + fileName ));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
				 
				 String message = "File Uploaded.";
				 request.setAttribute("message", message);
				 RequestDispatcher rd = request.getRequestDispatcher("creatorpage.jsp");
				 rd.forward(request, response);
			 }
		 }
		 else {
			 String message = "Invalid file.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("creatorpage.jsp");
				rd.forward(request, response);
		 }
		 
		
	}

}
