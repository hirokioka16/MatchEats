package com.example.demo.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.util.FileUtils;

@Component
@Order(2)
@WebFilter(filterName="LoginCheckFilter", urlPatterns="/*")
public class LoginCheckFilter implements Filter{
	Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
	//チェック除外画面
	private String excludeDispList[] =
		{
			"/login","/auth","/logout","/menu","/inputUserEntry/input","/inputUserEntry/confirm",
			"/inputUserEntry/insert","/inputUserEntry/complete","/detailfoodlist","/seachfoodlist",
			"/adminlogin","/adminauth","/adminlogout","/adminmenu/menu","/adminmenu/transfer",
			"/adminmenu/transfer/confirmapproval","/adminmenu/transfer/insertapproval",
			"/adminmenu/alltransaction","/inputContact/contactlist",
			"/delivery/requestlist","/delivery/requestDetail","/delivery/deliveryinsert","/delivery/deliverycomplete",
			"/delivery/approvallist","/delivery/collectionconfirm","/delivery/collectioninsert","/delivery/collectioncomplete",
			"/delivery/mydeliverylist","/delivery/confirm","/delivery/insert","/delivery/complete","/eachgenre"
		};
	private String excludeExtList[] =
		{
			"js","css","png","gif","jpg","ico"
		};

	@Override
	public void destroy() {
		// do nothing

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//リクエストのサーブレットパスを取得
		String servletPath = ((HttpServletRequest)request).getServletPath();

		//除外画面に含まれている場合はチェックしない
		if( Arrays.asList(excludeDispList).contains(servletPath)){
			chain.doFilter(request, response);
			return;
		}
		//js,cs,png,gif,ico,jpgは除外
		if( Arrays.asList(excludeExtList).contains(FileUtils.getExt(servletPath))){
			chain.doFilter(request, response);
			return;
		}

		//ログインセッションを取得し、存在しない場合は、ログイン画面に飛ばす
		HttpSession session = ((HttpServletRequest)request).getSession(false);

		if( session == null ){
			//セッションがない場合はログイン画面へ
			((HttpServletResponse)response).sendRedirect("/MatchEats/login");
			return;
		}
		LoginInfoDto loginInfo =
				(LoginInfoDto)session.getAttribute("loginInfo");



		if( loginInfo == null ){
			//ログイン画面へ転送
			logger.debug("Filter!!! servletPath="+servletPath);
			((HttpServletResponse)response).sendRedirect("/MatchEats/login");
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// do nothing

	}

}
