package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String act = request.getParameter("action");
		
		if("addList".equals(act)) {
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> gList = guestbookDao.getList();
			
			request.setAttribute("gList", gList);
			
			//포워드 만들기
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		}  else if ("add".equals(act)){
			//파라미터 꺼내옴
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//vo 만든다
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			System.out.println(guestbookVo);
			
			//dao 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			
			guestbookDao.guestbookInsert(guestbookVo);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
			

		} else if ("deleteForm".equals(act)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		} else if ("delete".equals(act)) {
			
			//파라미터 꺼내옴
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//dao 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			
			guestbookDao.guestbookDelete(no, password);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
		} else {
			System.out.println("파라미터 값 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}