package com.human.login;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.ibatis.session.SqlSession;

import com.human.login.ilogin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		return "home";
	}
	@RequestMapping("/login")
	public String login(Locale locale, Model model) {
		return "login"; 
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session = hsr.getSession(true);
		String userid=(String)session.getAttribute("userid");
		ilogin id=sqlSession.getMapper(ilogin.class);
		id.updateLogout(userid);
		
		session.invalidate();
		return "redirect:/"; 
	}
	@RequestMapping(value="/finish_login")
	public String finish_login(HttpServletRequest hsr,Locale locale, Model model) {
		ilogin id=sqlSession.getMapper(ilogin.class);
		String userid=hsr.getParameter("userid");
		id.updateLogin(userid);
		HttpSession session = hsr.getSession(true);
		session.setAttribute("userid", userid);
		return "home"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/login_check",method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	public String login_check(HttpServletRequest hsr,Locale locale, Model model) {
		ilogin id=sqlSession.getMapper(ilogin.class);
		ArrayList<rlogin> ml = id.login_check();
		JSONArray ja=new JSONArray();
		for(int i=0; i<ml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("userid",ml.get(i).getUserid());
			jo.put("passcode",ml.get(i).getPasscode());
			ja.add(jo);
			}
		return ja.toString();
	}
//	@ResponseBody
	@RequestMapping("/signon")
	public String signon(Locale locale, Model model) {	
		return "signon"; 
	}
	@ResponseBody
	@RequestMapping(value="/id_check",method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	public String id_cehck() {
		ilogin id=sqlSession.getMapper(ilogin.class);
		ArrayList<resi> ml = id.id_check();
		JSONArray ja=new JSONArray();
		for(int i=0; i<ml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("userid",ml.get(i).getUserid());
			ja.add(jo);
			}
		return ja.toString();
	}
	@RequestMapping(value="/signon_check",method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	public String signon_check(HttpServletRequest hsr,Model model) {
		String retval="";
		
		try {
		String name=hsr.getParameter("name");
		String gender=hsr.getParameter("gender");
		String userid=hsr.getParameter("userid");
		String passcode=hsr.getParameter("passcode");
		String[] interest=hsr.getParameterValues("interest");

		String interest1 = "";
		
		
		
		for(int i=0;i<interest.length;i++) {
			interest1+=interest[i];
			if(i==interest.length-1)break;
			interest1+=",";
		}
		
		ilogin login=sqlSession.getMapper(ilogin.class);
		login.resistor(name, gender, userid, passcode, interest1);

		retval="ok";
	}catch(Exception e) {
		retval="fail";
	}
		return "home";

	}
}
