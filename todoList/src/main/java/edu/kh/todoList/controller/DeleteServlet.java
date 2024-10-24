package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.dto.Todo;
import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			TodoListService service = new TodoListServiceImpl();
			int result = service.todoDelete(todoNo);
			
			if(result != 0) {
				// 메인페이지로 redirect
				resp.sendRedirect("/");
				return;
					
			}
			String path = "/WEB-INF/views/detail.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
