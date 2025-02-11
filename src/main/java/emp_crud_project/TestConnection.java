package emp_crud_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test_conn")
public class TestConnection extends HttpServlet {

	@Resource(name = "sprk_emp")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		Connection conn = null;
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Testing DB Connection Servlet</title>");
		out.print("</head>");
		out.print("<body>");

		try {
			conn = dataSource.getConnection();
			if(conn != null)
			{
				out.print("<h1>Connection Establish Successfully</h1>");
				out.print("<p>Connection:" + conn + "</p>");
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			out.print("<h1 style='color:red'>Connection Not Found</h1>");
		}

		out.print("</body>");
		out.print("</html>");
	}

}
