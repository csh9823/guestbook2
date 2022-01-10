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
		System.out.println("GuestbookController");//작동확인용
		
		String act = request.getParameter("action");//파라미터에서 액션을 뽑아냄
		
		if("addList".equals(act)) {
			System.out.println("action=addList");
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> gList = guestbookDao.getList();
			
			request.setAttribute("gList", gList);//이름(키값), 넣을것
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");//옮겨갈 경로
			rd.forward(request, response);//이 2개를 넘긴다
		}  else if ("add".equals(act)){
			//파라미터 3개 꺼내온다
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			//vo로 만든다
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			System.out.println(guestbookVo);
			
			//dao 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			//dao.insert();
			guestbookDao.guestbookInsert(guestbookVo);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
			//=>리다이렉트는 리스폰의 메소드를 사용, 파일경로가 아닌 주소값을 넣어줌.

		} else if ("deleteForm".equals(act)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);//이름(키값), 넣을것
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");//옮겨갈 경로
			rd.forward(request, response);//이 2개를 넘긴다
			
		} else if ("delete".equals(act)) {
			//파라미터 꺼내온다
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//dao 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			//delete
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