/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import ejb.NewsEntity;
import ejb.NewsEntityFacade;
import ejb.SessionManagerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bikas
 * 
 * Server to display all the News
 */
@WebServlet(name="News", urlPatterns={"/News"})
public class News extends HttpServlet {
    
    //SesseionBean 
    @EJB
    private SessionManagerBean sessionManagerBean;
    
    //MessageBean
    @EJB
    private NewsEntityFacade newsEntityFacade;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>News</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><center><font color='#0000ff'><b>News Portal</font></b></center></h1>");
            
            out.println("<a href='News'><center><font color='#0000ff'><b>Please Refresh</font></b></center></a><br/>");
            
            boolean found = false;
            out.println("<table border='1' align='center'>");
            out.println("<tr> <td><b>Title</b></td><td><b>Body</b></td></tr>");
            
            
            List news = newsEntityFacade.findAll();
            for (Iterator it = news.iterator(); it.hasNext();) {
                found = true;
                NewsEntity elem = (NewsEntity) it.next();
                out.println("<tr> <td>"+elem.getTitle()+"</td><td>"+elem.getBody()+"</td></tr>");
            }
            if(!found)
                 out.println("<tr colspan='2'> <td>No News Found</td></tr>");
                   
            out.println("</table>");
            out.println("<br>");
            
            out.println("<a href='PostNews'><center><font color='#0000ff'><b>Add New News</font></b></center></a>");
            out.println("<br><br>");
            
            out.println("<center> Current Active User: " + sessionManagerBean.getActiveSessionsCount() + "</center>");


            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
