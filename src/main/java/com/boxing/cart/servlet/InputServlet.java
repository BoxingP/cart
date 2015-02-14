package com.boxing.cart.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("text/html;charset=UTF-8");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("<form action=\"result\">");
        outputStreamWriter.write("<p>Input discount information: <input name=\"discount_information\" /></p>");
        outputStreamWriter.write("<p>Input item information: <input name=\"item_information\" /></p>");
        outputStreamWriter.write("<p>Input coupon information: <input name=\"coupon_information\" /></p>");
        outputStreamWriter.write("<p><input type=\"submit\"/></p>");
        outputStreamWriter.write("</form>");
        outputStreamWriter.flush();
    }
}
