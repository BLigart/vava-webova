package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.VolitelnyPredmetManager;
import model.User;
import model.VolitelnyPredmet;

/**
 * Servlet implementation class VytvoreniePlanuServlet
 */
@WebServlet("/vytvorenieplanu")
public class VytvoreniePlanuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		VolitelnyPredmetManager volitelnyPredmetManager = new VolitelnyPredmetManager();
		List<VolitelnyPredmet> volitelne_predmety_list = null;
		try {
			volitelne_predmety_list = volitelnyPredmetManager.getAllVolitelnyPredmet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<VolitelnyPredmet> volitelne_predmety = new ArrayList<VolitelnyPredmet>();
		for(VolitelnyPredmet volitelnyPredmet: volitelne_predmety_list) {
			volitelne_predmety.add(volitelnyPredmet);
		}
		//user.setVolitelne_predmety(volitelne_predmety);
		
		request.getSession().setAttribute("volitelne_predmety", volitelne_predmety);
		
		request.getSession().setAttribute("predmety_v_plane", null);
		request.getSession().setAttribute("nazovPlanu", "");
		request.getSession().setAttribute("saved_semester", "0");
		
		response.sendRedirect("novy_plan_screen.jsp");
		return;
	}

}
