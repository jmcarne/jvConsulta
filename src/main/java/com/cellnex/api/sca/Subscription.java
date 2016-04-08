package com.cellnex.api.sca;

public class Subscription {
	private String user;
	private String table;
	private String condition;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String usr) {
		user = usr;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String tab) {
		table = tab;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String cond) {
		condition = cond;
	}
	
	public String toString() {
        return "Subscription [user=" + user + ", table=" + table + ", condition=" + condition + "]";
    }
}
