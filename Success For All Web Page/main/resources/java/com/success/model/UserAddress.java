package com.success.model;

import javax.persistence.Embeddable;

@Embeddable
public class UserAddress {
	
	private String userState;
	private String userTown;
    private String userZip;
	private String userStreet;
	private String userCountry;
	
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserTown() {
		return userTown;
	}
	public void setUserTown(String userTown) {
		this.userTown = userTown;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public String getUserStreet() {
		return userStreet;
	}
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	@Override
	public String toString() {
		return "UserAddress [userState=" + userState + ", userTown=" + userTown + ", userZip=" + userZip
				+ ", userStreet=" + userStreet + ", userCountry=" + userCountry + "]";
	}
}
