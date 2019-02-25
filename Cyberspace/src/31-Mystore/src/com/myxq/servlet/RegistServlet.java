package com.myxq.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.myxq.domain.User;
import com.myxq.util.JdbcUtil;


/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		//����������� ����Ӧ�ı���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ����  ��֤��
		String code = request.getParameter("code");
		System.out.println("code="+code);
		//��ȡ���������ɵ���֤��  
		String word = (String) this.getServletContext().getAttribute("checkCode");
		//�ж��������֤
		if(code.equals(word)) {
			//�����ȷ 
			//1.�������в���
			Map<String, String[]> parameterMap = request.getParameterMap();
			User u = new User();
			//2.�ѽ��յĲ�����װ��User����
			try {
				BeanUtils.populate(u, parameterMap);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			//3.����uid
			u.setUid(UUID.randomUUID().toString());
			//4.д�뵽���ݿ�
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			String sql ="insert into user value(?,?,?,?)";
			try {
				qr.update(sql,u.getUid(),u.getUsername(),u.getPassword(),u.getPhone());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().write("ע��ɹ�");
			//��ת����¼
			response.setHeader("refresh", "3;url=/31-Mystore/login.jsp");
		}else {
			//����ȷ �������û���֤�������ת��ע��ҳ
			response.getWriter().write("��֤�����");
			response.setHeader("refresh", "3;url=/31-Mystore/regist.jsp");
		}
	}
}
