package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		GuestbookDao guestbookDao = new GuestbookDao();

		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		Date regdate = java.sql.Date.valueOf(request.getParameter("regdate"));


		Guestbook guestbook = new Guestbook(id, name, content, regdate);
		guestbookDao.addGuestbook(guestbook);

		response.sendRedirect("/guestbook/guestbooks.jsp");

	}

}
