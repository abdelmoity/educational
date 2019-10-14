package com.abdo.adf.storeshop.view.servlets;


import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShowPdfServlet extends HttpServlet {

    @SuppressWarnings("compatibility:-4363086105387473623")
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                         IOException {

        HttpSession sess = request.getSession();
        response.setContentType("application/pdf");
        Object className = sess.getAttribute("className");
        ByteArrayOutputStream out = ((ReportBuilder)className).exportDocumentPDF();


        response.setContentType("Application/pdf");
        response.setContentType("application/pdf; charset=UTF-8");
        response.setHeader("Content-Disposition", "inline;filename=./" + "report" + ".pdf");
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        out.close();
    }

}

