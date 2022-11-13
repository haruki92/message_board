package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		
//		CSRF対策のチェック　
//		_tokenに値がセットされていなかったりセッションIDと値が異なったりしたらデータの登録ができないようにしている
		if (_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();
			em.getTransaction().begin();
			
			Message m = new Message();
			
//			フォームに入力されたタイトルの取得・セット
			String title = request.getParameter("title");
			m.setTitle(title);
//			フォームに入力されたコンテンツの取得・セット
			String content = request.getParameter("content");
			m.setContent(content);
			
//			現在の日時を取得
			Timestamp currenTime = new Timestamp(System.currentTimeMillis());
			m.setCreated_at(currenTime);
			m.setUpdated_at(currenTime);
			
//			必要な情報をセットしたmをデータベースにセーブ
			em.persist(m);
//			コミット
			em.getTransaction().commit();
			em.close();
			
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}

}
