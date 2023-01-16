package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidstr=req.getParameter("fruit_id");
        int fruit_id=Integer.parseInt(fidstr);
        FruitDAO fruitDAO=new FruitDAOImp();
        Fruit fruit=fruitDAO.getFruitById(fruit_id);
        req.setAttribute("fruit",fruit);
        super.processTemplate("edit",req,resp);
    }
}
