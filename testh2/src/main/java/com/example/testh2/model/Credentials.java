package com.example.testh2.model;

public class Credentials {
	
	private String username;
	private String password;
	private String nahodnyTextARozhodneNieHeslo;
	private String access;
	
	
	public Credentials() {
		
	}
	
	public Credentials(String username, String password, String nahodnyTextARozhodneNieHeslo, String access) {
		this.username = username;
		this.password = password;
		this.nahodnyTextARozhodneNieHeslo = nahodnyTextARozhodneNieHeslo;
		this.access = access;
	}
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
		this.nahodnyTextARozhodneNieHeslo = null;
		this.access = null;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getNahodnyTextARozhodneNieHeslo() {
		return nahodnyTextARozhodneNieHeslo;
	}

	public void setNahodnyTextARozhodneNieHeslo(String nahodnyTextARozhodneNieHeslo) {
		this.nahodnyTextARozhodneNieHeslo = nahodnyTextARozhodneNieHeslo;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + password + ", nahodnyTextARozhodneNieHeslo="
				+ nahodnyTextARozhodneNieHeslo + ", access=" + access + "]";
	}
}
