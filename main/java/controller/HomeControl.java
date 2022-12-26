package controller;

import Dao.Dao;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeControl", value = "/homeControl")
public class HomeControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // b1 lấy dữ liệu từ lớp dao
        Dao dao = new Dao();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        // b2: đẩy dữ liệu từ lớp dao sang trang jsp
        request.setAttribute("listP",list);
        request.setAttribute("listC",listC);
        request.setAttribute("p",last);
        request.getRequestDispatcher("Home.jsp").forward(request,response);
        // lỗi 404 -> thuộc về đường dẫn url
        // lỗi 500 -> thuộc về jsp kiểm tra lại propeties
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
