package com.cellnex.api.sca;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.Statement;

@Controller
@RequestMapping("/jvconsult")
public class JVConsultController {
	
	Connection connMain;
	Connection connSupp;
	Statement stmt;
	
	private static String[] connMainStr = {"jdbc:vertica://10.12.70.169:5433/main", "dbadmin", ""};
	private static String[] connSuppStr = {"jdbc:vertica://10.12.70.169:5433/main", "dbadmin", ""};
	//private static String[] connMainStr = {"jdbc:mysql://localhost/consmain", "root", "root"}; // 3306
	//private static String[] connSuppStr = {"jdbc:mysql://localhost/conssupp", "root", "root"};
	
	public JVConsultController() {
		try {
			//Class.forName("com.vertica.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			/*connMain = DriverManager.getConnection(connMainStr[0], connMainStr[1], connMainStr[2]);
			stmt = connMain.createStatement();
			connSupp = DriverManager.getConnection(connSuppStr[0], connSuppStr[1], connSuppStr[2]);
			stmt = connSupp.createStatement();*/
		} catch (Exception e) {
			// ..
		}
	}
	
	@RequestMapping(value = "/simple", method = RequestMethod.GET)
	public String simple(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!");
		
		/*
		String sql = "SELECT * FROM main WHERE ...";
		ResultSet rs = stmt.executeQuery(sql);
		*/
		//try { conn.close();	} catch (Exception e) {	}
		return "mainConsult";
	}
	
	@RequestMapping(value = "/complex/{par}", method = RequestMethod.GET)
	public String complex(@PathVariable String par, ModelMap model) {
		JSONObject obj=new JSONObject();
		obj.put("name",par);
		obj.put("num",new Integer(100));
		par = obj.toString();
		model.addAttribute("msg", par);
		
		//try { conn.close();	} catch (Exception e) {	}
		return "mainConsult2";
	}
	
	
	/*  POST
	 *  @RequestMapping(method = RequestMethod.POST)
		@ResponseBody 
		public void addComputer(@RequestBody Computer computer) {
		  computerStorage.add(computer);
		}
		
		..
		
		Test POST http://localhost:8080/SpringREST/rest/computer
		Before sending the request, add an http header Content-Type with the button
		
		Add Request Header of Rest client : Content-Type:application/json; charset=utf-8
		add a JSON content in the request body 
		  {"reference":"MAC","description":"Test","model":"MB Pro","brand":"Apple"}
	 * 
	 */
	/******* SUBSCRIPTIONS *********/
	@RequestMapping(value = "/subscription/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createSubscription(@RequestBody Subscription subscription) {
		// check if subscription exist..

		if (true /*Subscription already exist.. userService.isUserExist(user)*/) {
			closeConn();
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        // Save subscription
 
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		closeConn();
        return new ResponseEntity<Void>(/*headers, */HttpStatus.CREATED);
	}
	
	//@RequestMapping(value = "/subscription/read/{user}", method = RequestMethod.GET)
	/*public ResponseEntity<List> readSubscriptions(@PathVariable String user) { //List<Subscription>
		List<Subscription> subscriptions = new ArrayList<Subscription>(); //Get the subscriptions here!!!!
		String tmp = "";
		try {
			Class.forName("com.vertica.jdbc.Driver");
			Connection connSupp = DriverManager.getConnection(connSuppStr[0], connSuppStr[1], connSuppStr[2]);
			stmt = connSupp.createStatement();
			Statement stmtSupp = connSupp.createStatement();
			ResultSet rsIns = null;
			rsIns = stmtSupp.executeQuery("SELECT USER, USR, TABLE_NAME, CONDITION_NOTIFICATION FROM CONDITIONS WHERE USR = 'MARIO'");
			while (rsIns.next()) {
				Subscription s = new Subscription();
				s.setUser(rsIns.getString(1));
	        	s.setTable(rsIns.getString(2));
	        	s.setCondition(rsIns.getString(3));
				subscriptions.add(s);
	        }
		} catch (Exception e) { tmp = e.toString(); }
		
		//if(subscriptions.isEmpty()){
            //return new ResponseEntity<List<Subscription>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        //}
        //return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
		
		String str = Integer.toString(subscriptions.size());
		str = tmp;
		closeConn();
        return new ResponseEntity<List>(str, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/subscription/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") long id, @RequestBody Subscription subscription) {
		// The id exist?
		//User currentUser = userService.findById(id);
        
        if (true) {
            //try { conn.close();	} catch (Exception e) {	}
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }
 
        // Set the subscription
        //currentUser.setName(user.getName());
        //currentUser.setAge(user.getAge());
        //currentUser.setSalary(user.getSalary());
        //userService.updateUser(currentUser);
        
        closeConn();
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/subscription/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubscription(@PathVariable("id") long id) {
		// Find subscription
		//User user = userService.findById(id);
        
		if (true/*user == null*/) {
			closeConn();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
 
        // Delete
		//userService.deleteUserById(id);
		closeConn();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	private void closeConn() {
		try {
			connMain.close();
			connSupp.close();
		} catch (Exception e) {	}
	}
}
