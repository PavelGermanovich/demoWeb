package homework.lesson27;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginUser")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("loginName");
        String password = req.getParameter("password");
        DbUserService dbAdmin = new DbUserService();
        boolean validateLogin = dbAdmin.isLoginExist(userName);
        if (validateLogin) {
            if (dbAdmin.validateCredentials(userName, password)) {
                System.out.println("connection went successfull");
                String path = dbAdmin.getUserData(userName).isRoot() ? "/userAdminPage.html" : "/userPage.html";
                getServletContext().getRequestDispatcher(path).forward(req, resp);
            } else {
                System.out.println("Credentials were not validates");
            }
        } else {
            System.out.println("Login was not validated");
        }
    }
}
