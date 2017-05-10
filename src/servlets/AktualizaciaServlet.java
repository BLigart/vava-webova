package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import model.Plan;
import model.User;

/**
 * Servlet implementation class AktualizaciaServlet
 */
@WebServlet("/aktualizacia")
public class AktualizaciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AktualizaciaServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		try {
			user.get_volitelne_predmety();
		} catch (FailingHttpStatusCodeException e) {
			logger.warn(e.getMessage(), e);
;
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
;
		}
		request.getSession().setAttribute("user", user);
		
		response.sendRedirect("plany_screen.jsp");
		return;
	}

}
