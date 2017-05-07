package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.FirmaManager;
import model.Firma;
import model.Rok;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("firma", null);
		FirmaManager firmaManager = new FirmaManager();
		List<Firma> firma = null;
		try {
			firma = firmaManager.getFirmaByLoginPassword(username, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(firma.size() == 1) {
			request.getSession().setAttribute("firma", firma.get(0));
			response.sendRedirect("plany_screen.jsp");
			return;
		}
		
		
		
		User user = new User();
		user.loadpage();
		boolean result = false;
		user.setUsername(username);
		
		try {
			result = user.aisLogin(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("student_main_menu.jsp");
			return;
		}
		else {
			response.sendRedirect("login_screen.jsp");
			return;
		}
	}

}
