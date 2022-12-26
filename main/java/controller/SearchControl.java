package controller;

import Dao.Dao;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchControl", value = "/search")
public class SearchControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt"); // vd người dùng nhập giày chạy bộ thì mình lấy được giày bộ
        Dao dao = new Dao();
        List<Product> list = dao.SearchByName(txtSearch);
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();

        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.setAttribute("txts", txtSearch);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
