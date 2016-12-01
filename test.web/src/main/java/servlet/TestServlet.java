package servlet;

import org.aspectj.asm.HierarchyWalker;
import sun.util.resources.hr.CalendarData_hr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * Created by noodles on 16/3/12.
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        BufferedReader reader = req.getReader();
//        StringBuffer stringBuffer = new StringBuffer();
//
//        char[] chars = new char[128];
//        while (reader.read(chars) != -1){
//            stringBuffer.append(chars);
//        }
//        System.out.println(stringBuffer.toString());
//
//        if(Math.random() > 0.5){
//            resp.getOutputStream().write("success".getBytes());
//        }else {
//            resp.getOutputStream().write("fail".getBytes());
//        }
//
//        resp.getOutputStream().close();

        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headName = (String) headerNames.nextElement();
            System.out.println(headName + ":" + req.getHeader(headName));
        }


        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = (String) parameterNames.nextElement();
            System.out.println(String.format("%s=%s",name,req.getParameter(name)));
        }


        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String tempStr = null;
        while ((tempStr = reader.readLine()) != null){
            sb.append(tempStr);
        }
        System.out.println(sb.toString());

        String value = req.getParameter("formKey1");
        System.out.println(value);
        System.out.println("------------");

        if(Math.random() * 10 > 5){
            resp.getOutputStream().write("success".getBytes());
        }else {
            resp.getOutputStream().write("succe21ss".getBytes());
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
