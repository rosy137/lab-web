package com.itwill.post.controller.post;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.service.PostService;


/**
 * Servlet implementation class PostCreateController
 */
@WebServlet(name = "postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	
	private final PostService postservice = PostService.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/post/create.jsp")
		       .forward(request, response);
		log.info("doGet()");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    log.info("doPost()");

	    // 요청에 포함된 요청 파라미터 정보들을 추출
	    String title = request.getParameter("title"); // create.jsp 에 입력한 내용과 동일하게
	    String content = request.getParameter("content");
	    String author = request.getParameter("author");
	    
	    Post post = new Post(0, title, content, author, null, null);
	    // 서비스 계층의 메서드를 호출해서 DB에 포스트 저장
	    int result = postservice.create(post);
	    log.info("create result= {}", result);
	    // 포스트 목록 페이지로 이동(redirect)
	    response.sendRedirect("/jsp2/post"); // 요ㅗ청주소: /contextRoot/path
	    
	    // PRG(Post-Redirect-Get) 패턴
	}

}
