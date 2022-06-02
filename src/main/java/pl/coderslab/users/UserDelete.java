package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserDelete", value = "/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user;
        if (id == null) {
            response.sendRedirect("/user/list");
        } else {
            UserDao userDao = new UserDao();
            user = userDao.read(Integer.parseInt(id));
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/users/delete.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ifDelete = request.getParameter("ifDelete");
        if ("true".equals(ifDelete)){
            int id = -1;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
//                errorMessages.add("Niepoprawny numer id");
                response.getWriter().append("Niepoprawny numer id");
                return;
            }
            UserDao userDao = new UserDao();
            userDao.delete(id);
        }
        response.sendRedirect("/user/list");
    }
}
