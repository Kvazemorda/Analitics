package server.servlet;

import server.bean.ReportBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("getReport/")
public class ReportServlet extends HttpServlet {
    @EJB ReportBean reportBean;

    public ReportServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reportBean = new ReportBean();
        PrintWriter out = resp.getWriter();
        out.println("doGet " + reportBean.getReport());
    }
}
