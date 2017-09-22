package BasicWebApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BootstrapTest extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		int numSlots = 4;
		String numSlots1 = "this is a test";
		req.setAttribute("numSlots", numSlots);
		req.getRequestDispatcher("/bootstrap.jsp").forward(req,resp);
	}
}
