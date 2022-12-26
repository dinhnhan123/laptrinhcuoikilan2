package controller;

import Dao.Dao;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "category", value = "/category")
public class CategoryControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // việc của con servlet này có nhiệm vụ là lấy id về
        String cateID = request.getParameter("cid"); // lấy được categoryid về rồi
        Dao dao = new Dao();
        List<Product> list = dao.getProductByCID(cateID);
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();


        request.setAttribute("listC",listC);
        request.setAttribute("p",last);
        request.setAttribute("listP", list); // Đẩy list lên listP trên trang home mà list lúc
        // nảy là list của tất cả sản phẩm , list này là của một danh mục chẳng hạn như của adidas thôi
        request.setAttribute("tag",cateID);
        request.getRequestDispatcher("Home.jsp").forward(request, response);// vẫn tiếp tục đẩy lên trang home
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
