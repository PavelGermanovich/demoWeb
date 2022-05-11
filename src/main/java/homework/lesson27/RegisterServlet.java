package homework.lesson27;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registerUser")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        DbUserService dbAdmin = new DbUserService();
        if (!dbAdmin.isLoginExist(login)) {
            User user = new User(login, password, name, age);
            dbAdmin.createNewUser(user);
            getServletContext().getRequestDispatcher("/index.html")
                    .forward(req, resp);
            System.out.println("user was registered");
        } else {
            System.out.println("login exist");
        }
    }
}
