package com.example.wlab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userText = request.getParameter("userText");
        String userBackgroundURL = request.getParameter("userBackgroundURL");

        String newBufferContent = "<div>" + userText + "</div><br>" + "<img src=\"" + userBackgroundURL + "\">";

        System.out.println("\n" + "NEW Content: " + newBufferContent + "\n");//test log

        String pathToBuffer = getServletContext().getRealPath("./buffer.txt");
        System.out.println("\nPath: " + pathToBuffer); //test log

        try(FileOutputStream fos = new FileOutputStream(pathToBuffer);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(newBufferContent, 0, newBufferContent.length());
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
