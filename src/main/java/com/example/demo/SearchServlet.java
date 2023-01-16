package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/index.html")
public class SearchServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO=new FruitDAOImp();
        ArrayList<Fruit> fruitList=fruitDAO.getFruitList();
        HttpSession session=req.getSession();
        session.setAttribute("fruitList",fruitList);
        super.processTemplate("index",req,resp);
    }
}
