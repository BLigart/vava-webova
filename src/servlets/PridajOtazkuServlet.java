package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.OtazkaManager;
import model.Otazka;
import model.Test;

/**
 * Servlet implementation class PridajOtazkuServlet
 */
@WebServlet("/pridajotazku")
public class PridajOtazkuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String otazka_text = request.getParameter("otazka_text");
		String odpoved_text = request.getParameter("odpoved_text");
		Test test = (Test) request.getSession().getAttribute("selectedTest");
				
		Otazka otazka = new Otazka(otazka_text, odpoved_text, test.getId());
		OtazkaManager otazkaManager = new OtazkaManager();
		try {
			otazkaManager.insertOtazka(otazka);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		List<Otazka> otazky = null;
		try {
			otazky = otazkaManager.getAllOtazkaFromTest(test.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("otazky", otazky);
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
