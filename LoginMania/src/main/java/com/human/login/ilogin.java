package com.human.login;

import java.util.ArrayList;

public interface ilogin {
	void resistor(String name,String gender,String userid, String passcode, String interest1);
	ArrayList<resi> id_check();
	ArrayList<rlogin> login_check();
	void updateLogin(String userid);
	void updateLogout(String userid);
	
}
