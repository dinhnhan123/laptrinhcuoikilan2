package controller;

import Dao.Dao;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Detail", value = "/detail")
public class DetailControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pid");
        Dao dao = new Dao();
        Product p =  dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        request.setAttribute("listC",listC);
        request.setAttribute("p",last);
        request.setAttribute("detail", p);
        request.getRequestDispatcher("Detail.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
