package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

@WebServlet(name = "/index", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).setFirstResult(15 * (page - 1))
                .setMaxResults(15).getResultList();

        Long message_count = (long) em.createNamedQuery("getMessagesCount", Long.class).getSingleResult();

        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", message_count);
        request.setAttribute("page", page);

        //もしフラッシュメッセージがセッションスコープに保存されていたらリクエストスコープに入れ直す
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
    }
}
