package experiment6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Multiplication_servlet")
public class Multiplication_servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Display form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Enter Number for Multiplication Table</h2>");
        out.println("<form method='post' action='Multiplication_servlet'>");
        out.println("Number: <input type='text' name='number'><br><br>");
        out.println("<input type='submit' value='Generate'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // Process user input
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int number = Integer.parseInt(request.getParameter("number"));

            out.println("<html><body>");

            // Multiplication Table
            out.println("<h2>Multiplication Table</h2>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>Expression</th><th>Result</th></tr>");

            for (int i = 1; i <= 10; i++) {
                out.println("<tr><td>" + number + " x " + i + "</td><td>" + (number * i) + "</td></tr>");
            }

            out.println("</table>");

            // Square of number
            out.println("<h2>Square of Number</h2>");
            out.println("<p>" + number + " × " + number + " = <b>" + (number * number) + "</b></p>");

            out.println("</body></html>");

        } catch (NumberFormatException e) {
            out.println("<h3>Please enter a valid number.</h3>");
        }
    }
}