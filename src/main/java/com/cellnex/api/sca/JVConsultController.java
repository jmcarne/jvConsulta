package com.cellnex.api.sca;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
@RequestMapping("/jvconsult")
public class JVConsultController {
	
	private Connection connMain;
	private Connection connSupp;
	private Statement stmt;
	
	private static String[] connMainStr = {"jdbc:vertica://172.16.2.85:5433/main", "dbadmin", "password"};
	private static String[] connSuppStr = {"jdbc:vertica://172.16.2.85:5433/main", "dbadmin", "password"};
	//private static String[] connMainStr = {"jdbc:mysql://localhost/consmain", "root", "root"}; // 3306
	//private static String[] connSuppStr = {"jdbc:mysql://localhost/conssupp", "root", "root"};

	public JVConsultController() {
		try {
			Class.forName("com.vertica.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			connMain = DriverManager.getConnection(connMainStr[0], connMainStr[1], connMainStr[2]);
			stmt = connMain.createStatement();
			connSupp = DriverManager.getConnection(connSuppStr[0], connSuppStr[1], connSuppStr[2]);
			stmt = connSupp.createStatement();
		} catch (Exception e) {
			// ..
		}
	}

	/*-------------------Retrieve  -----------------------------------------------------------------------------------*/

	//@RequestMapping(value = "/simple", method = RequestMethod.GET)
    @RequestMapping(value = "/simple", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String simple(ModelMap model) {

		model.addAttribute("msg", "JCG Hello World!");

		try {

			//String sql = "SELECT * FROM main";
			String sql = "SELECT node_name, node_state, node_address from nodes";
			System.out.println("sql: "+ sql);
			Connection conn = DriverManager.getConnection(connSuppStr[0], connSuppStr[1], connSuppStr[2]);
			Statement stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery(sql);

		} catch (Exception e){

            System.out.println(e.getMessage());
		}

		return "mainConsult";
	}

	/*-------------------Retrieve Single User with parameter ---------------------------------------------------------*/

	@RequestMapping(value = "/complex/{par}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/complex/{par}", method = RequestMethod.GET)
	public String complex(@PathVariable String par, ModelMap model) {
        System.out.println("Fetching User with id " + par);
        JSONObject obj=new JSONObject();
		obj.put("name",par);
		obj.put("num",100);
		par = obj.toString();
		model.addAttribute("msg", par);
		
		//try { conn.close();	} catch (Exception e) {	}
		return "mainConsult2";
	}
    /*
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
     */


    /* -------------------Create a User-------------------------------------------------------- */

    //  @RequestMapping(method = RequestMethod.POST)
    //@ResponseBody
    //public void addComputer(@RequestBody Computer computer) {
    //computerStorage.add(computer);
    //}
    //
    ////TODO
    //
    //Test POST http://localhost:8080/SpringREST/rest/computer
    //Before sending the request, add an http header Content-Type with the button

    //Add Request Header of Rest client : Content-Type:application/json; charset=utf-8
    //&add a JSON content in the request body
    //  {"reference":"MAC","description":"Test","model":"MB Pro","brand":"Apple"}

	/*--------------------SUBSCRIPTIONS--------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/

	/* -------------------Create a Subscription---------------------------------------------- */

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

	/* -------------------Retrieve a Subscription---------------------------------------------- */

	//@RequestMapping(value = "/subscription/read/{user}", method = RequestMethod.GET)
	/*ublic ResponseEntity<List> readSubscriptions(@PathVariable String user) { //List<Subscription>
		List<Subscription> subscriptions = new ArrayList<Subscription>(); //Get the subscriptions here!!!!
		String tmp = "";
		try {
			Class.forName("com.vertica.jdbc.Driver");
			Connection connSupp = DriverManager.getConnection(connSuppStr[0], connSuppStr[1], connSuppStr[2]);
			stmt = connSupp.createStatement();
			Statement stmtSupp = connSupp.createStatement();
			ResultSet rsIns = null;
			rsIns = stmtSupp.executeQuery("SELECT USER, USR, TABLE_NAME, CONDITION_NOTIFICATION FROM CONDITIONS WHERE USR = 'user'");
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

    /*------------------- Update a User --------------------------------------------------------*/

	@RequestMapping(value = "/subscription/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") long id, @RequestBody Subscription subscription) {
		// The id exist?
		User currentUser = userService.findById(id);
        
        if (true) {
            //try { conn.close();	} catch (Exception e) {	}
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        } else {

		}
 
        // Set the subscription
        //currentUser.setName(user.getName());
        //currentUser.setAge(user.getAge());
        //currentUser.setSalary(user.getSalary());
        //userService.updateUser(currentUser);
        
        closeConn();
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
		
	}

	/*------------------- Delete a User --------------------------------------------------------*/
	@RequestMapping(value = "/subscription/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubscription(@PathVariable("id") long id) {
		// Find subscription
		//User user = userService.findById(id);
        
		if (true/*user == null*/) {
			closeConn();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

		}
 
        // Delete
		//userService.deleteUserById(id);
		closeConn();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private void closeConn() {
		try {
			connMain.close();
			connSupp.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
