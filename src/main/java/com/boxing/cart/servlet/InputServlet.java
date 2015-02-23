package com.boxing.cart.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<form action=\"result\">");
        writer.write("<p>Input discount information: <input name=\"discount_information\" /></p>");
        writer.write("<p>Input item information: <input name=\"item_information\" /></p>");
        writer.write("<p>Input settlement day: <input name=\"settlement_information\" /></p>");
        writer.write("<p>Input coupon information: <input name=\"coupon_information\" /></p>");
        writer.write("<p><input type=\"submit\"/></p>");
        writer.write("</form>");
        writer.flush();
    }
}
