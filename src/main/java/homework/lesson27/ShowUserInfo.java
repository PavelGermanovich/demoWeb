package homework.lesson27;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getUsersInfo", urlPatterns = {"/getUsersInfo"})
public class ShowUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        List<User> userList = new DbUserService().getUserData();
        ObjectMapper objectMapper = new ObjectMapper();
        writer.print(objectMapper.writeValueAsString(userList));
    }
}
