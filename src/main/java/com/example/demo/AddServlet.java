package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fidstr=req.getParameter("fid");
        int fid=Integer.parseInt(fidstr);
        String fname=req.getParameter("fname");
        String fpricestr=req.getParameter("fprice");
        int fprice=Integer.parseInt(fpricestr);
        String fnumberstr=req.getParameter("fnumber");
        int fnumber=Integer.parseInt(fnumberstr);
        int ftotal=fprice*fnumber;
        String fremark=req.getParameter("fremark");
        FruitDAO fruitDAO=new FruitDAOImp();
        boolean flag=fruitDAO.addFruit(new Fruit(fid,fname,fnumber,fprice,ftotal,fremark));
        System.out.println(flag?"sucessful":"failed");
    }
}
