package controller;

import Dao.Dao;
import model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
                String username = request.getParameter("user");
                String password = request.getParameter("pass");

                Dao dao = new Dao();
                Account account = dao.login(username,password);
                if(account == null){
                    request.setAttribute("mess","Bạn sai user hoặc password , vui lòng đăng nhập lại");
                    request.getRequestDispatcher("Login.jsp").forward(request,response);
                    // khi nào mà cần mang theo dữ liệu thì mình dùng getRequestDispatcher
                    //trong trường này thì login.jsp nó cần phải mang theo dữ liệu là mess

                }else {
//                    request.getRequestDispatcher("homeControl").forward(request,response);
                    HttpSession session = request.getSession();
                    session.setAttribute("acc",account);
                    session.setMaxInactiveInterval(10);
                    response.sendRedirect("homeControl");
                    // còn nếu mà không cần mang theo dữ liệu chỉ chuyển trang thì dùng reponse.sendRedirect
                }
    }
}
