package BasicWebApp;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.sun.org.apache.xerces.internal.util.Status;

public class Reserve extends HttpServlet implements java.io.Serializable{
	
	public JDBCTutorialUtilities dbConnect(){
		JDBCTutorialUtilities myUtil;
		System.out.println("Initializing connection...");
		System.out.println("Using working dir " + System.getProperty("user.dir"));
		try {
	        System.out.println("Reading properties file " + "mysql-sample-properties.xml");
	        myUtil = new JDBCTutorialUtilities("mysql-sample-properties.xml");
	      } catch (Exception e) {
	        System.err.println("Problem reading properties file " + "mysql-sample-properties.xml");
	        e.printStackTrace();
	        return null;
	      }
		return myUtil;
	}
	
	public boolean checkReserve(JDBCTutorialUtilities myUtil, Connection conn, String id, String slot){
		//true = entry exists
		//false = entry does not exist
		//do not need this function
		try {
		      conn = myUtil.getConnection();

		      Statement stmt = null;
		      String query = "SELECT * FROM reserve " +
		    		  		  "WHERE id = " + id + " AND slot = " + slot;
		      
		      try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while(rs.next()){
		        	return true;
		        }
		        return false;

		      } catch (SQLException e) {
		        JDBCTutorialUtilities.printSQLException(e);
		      } finally {
		        if (stmt != null) { stmt.close(); }
		      }
		      
		    } catch (SQLException e) {
		      JDBCTutorialUtilities.printSQLException(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		return false;
		
	}
	
	public void updateReserve(HttpServletRequest req, JDBCTutorialUtilities myUtil, Connection conn, String id, String slot, int dur){
		try {
		      conn = myUtil.getConnection();
		      String uniqueID = req.getSession().getId();
		      Statement stmt = null;
		      String update = "INSERT INTO reserve (id, slot, ip, time, duration) " +
		    		  		  "VALUES (" + id + "," + slot + ",'" + uniqueID + "'," + System.currentTimeMillis() + "," + dur +")";
		      System.out.println(update);
		      try {
		        stmt = conn.createStatement();
		        stmt.executeUpdate(update);

		      } catch (SQLException e) {
		        JDBCTutorialUtilities.printSQLException(e);
		      } finally {
		        if (stmt != null) { stmt.close(); }
		      }
		      
		    } catch (SQLException e) {
		      JDBCTutorialUtilities.printSQLException(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		
	}
	
	public void updatePins(HttpServletRequest req, JDBCTutorialUtilities myUtil, Connection conn, String id, String slot, String pin){
		try{
		  conn = myUtil.getConnection();
	      //String uniqueID = req.getSession().getId();
	      Statement stmt = null;
	      String update = "UPDATE pins " +
		  		  		  "SET slot" + slot + " = '" + pin + "' " +
		  		  		  "WHERE id = " + id;
	      System.out.println(update);
	      try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(update);

	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { stmt.close(); }
	      }
	      
	    } catch (SQLException e) {
	      JDBCTutorialUtilities.printSQLException(e);
	    } catch (Exception e) {
	      e.printStackTrace(System.err);
	    }
	}
	
	public boolean checkTable(JDBCTutorialUtilities myUtil, Connection conn, String id, String slot){
		//true = slot open, can be reserved
		//false = slot not open, cannot be reserved
		try {
		      conn = myUtil.getConnection();

		      Statement stmt = null;
		      String query = "SELECT * FROM racks " +
		    		  		  "WHERE id = " + id;
		      
		      try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        String status = null;
		        while(rs.next()){
		        	status = rs.getString("slot" + slot);
		        }
		        System.out.println(status);
		        if(status.equals("open")){
		        	System.out.println("true");
		        	return true;
		        }
		        else{
		        	return false;
		        }

		      } catch (SQLException e) {
		        JDBCTutorialUtilities.printSQLException(e);
		      } finally {
		        if (stmt != null) { stmt.close(); }
		      }
		      
		    } catch (SQLException e) {
		      JDBCTutorialUtilities.printSQLException(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		return false;
		
	}
	
	public boolean checkIP (HttpServletRequest req, JDBCTutorialUtilities myUtil, Connection conn){
		//one device (user) is not allowed to reserve multiple slots
		//this prevents someone from just reserving all slots on a rack
		//true - no IP currently in database matches current user IP
		//false - user IP found in database
		
		try {
		      conn = myUtil.getConnection();
		      String userIP = req.getSession().getId();
		      Statement stmt = null;
		      String query = "SELECT * FROM reserve " +
		    		  		  "WHERE ip = '" + userIP + "'";
		      
		      try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while(rs.next()){
		        	String ip = rs.getString("ip");
		        	if(ip.equals(userIP)){
		        		return false;
		        	}
		        }
		        return true;
		        

		      } catch (SQLException e) {
		        JDBCTutorialUtilities.printSQLException(e);
		      } finally {
		        if (stmt != null) { stmt.close(); }
		      }
		      
		    } catch (SQLException e) {
		      JDBCTutorialUtilities.printSQLException(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		return false;
	}
	public void getTable(ArrayList<Smack> rData, JDBCTutorialUtilities myUtil, Connection conn){
		 
	    try {
	      conn = myUtil.getConnection();

	      Statement stmt = null;
	      String query = "SELECT * FROM racks";
	      
	      try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        System.out.println("Retrieving data...");
	        while (rs.next()) {
	        	//Smack rackData = new Smack(rs.getInt("id"), rs.getString("slot0"), rs.getString("slot1"), rs.getString("slot2"), rs.getString("slot3"));
	          Smack rack = new Smack();
	          rack.setRackID(rs.getInt("id"));
	          rack.setSlot0(rs.getString("slot0"));
	          rack.setSlot1(rs.getString("slot1"));
	          rack.setSlot2(rs.getString("slot2"));
	          rack.setSlot3(rs.getString("slot3"));
	        	/*int rackID = rs.getInt("id");
	          String slot0 = rs.getString("slot0");
	          String slot1 = rs.getString("slot1");
	          String slot2 = rs.getString("slot2");
	          String slot3 = rs.getString("slot3");
	          String rackData = ("Rack: " + rackID + ", Slot 0: " + slot0 + ", Slot 1: " + slot1 + ", Slot 2: " + slot2 + ", Slot 3: " + slot3);
	          *///System.out.println(rackData);
	          rData.add(rack);
	        }

	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { stmt.close(); }
	      }
	      
	    } catch (SQLException e) {
	      JDBCTutorialUtilities.printSQLException(e);
	    } catch (Exception e) {
	      e.printStackTrace(System.err);
	    }
	    
	}
	
	public void getTable(Smack rack, JDBCTutorialUtilities myUtil, Connection conn, String id){
		 
	    try {
	      conn = myUtil.getConnection();

	      Statement stmt = null;
	      String query = "SELECT * FROM racks " +
	    		  "WHERE id = " + id;
	      
	      try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        System.out.println("Retrieving data...");
	        while (rs.next()) {
	        	//Smack rackData = new Smack(rs.getInt("id"), rs.getString("slot0"), rs.getString("slot1"), rs.getString("slot2"), rs.getString("slot3"));
	          //Smack rack = new Smack();
	          rack.setRackID(rs.getInt("id"));
	          rack.setSlot0(rs.getString("slot0"));
	          rack.setSlot1(rs.getString("slot1"));
	          rack.setSlot2(rs.getString("slot2"));
	          rack.setSlot3(rs.getString("slot3"));
	        	/*int rackID = rs.getInt("id");
	          String slot0 = rs.getString("slot0");
	          String slot1 = rs.getString("slot1");
	          String slot2 = rs.getString("slot2");
	          String slot3 = rs.getString("slot3");
	          String rackData = ("Rack: " + rackID + ", Slot 0: " + slot0 + ", Slot 1: " + slot1 + ", Slot 2: " + slot2 + ", Slot 3: " + slot3);
	          *///System.out.println(rackData);
	          //rData.add(rack);
	        }

	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { stmt.close(); }
	      }
	      
	    } catch (SQLException e) {
	      JDBCTutorialUtilities.printSQLException(e);
	    } catch (Exception e) {
	      e.printStackTrace(System.err);
	    }
	    
	}
	
	public void updateRacks(JDBCTutorialUtilities myUtil, Connection conn, String id, String slot){
		try {
		      conn = myUtil.getConnection();

		      Statement stmt = null;
		      String update = "UPDATE racks " +
		    		  		  "SET slot" + slot + " = 'rsvd' " +
		    		  		  "WHERE id = " + id;
		      
		      try {
		        stmt = conn.createStatement();
		        stmt.executeUpdate(update);

		      } catch (SQLException e) {
		        JDBCTutorialUtilities.printSQLException(e);
		      } finally {
		        if (stmt != null) { stmt.close(); }
		      }
		      
		    } catch (SQLException e) {
		      JDBCTutorialUtilities.printSQLException(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
	}
	
	public void closeConn(Connection conn){
		JDBCTutorialUtilities.closeConnection(conn);
		System.out.println("Connection closed.");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ArrayList<Smack> racksData = new ArrayList<Smack>();
		JDBCTutorialUtilities myJDBCTutorialUtilities = dbConnect();
		Connection myConnection = null;
	    
	    this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
	    //System.out.println(racksData);
	    
	    this.closeConn(myConnection);
	    //System.out.println(racksData.get(0).getId());
		req.setAttribute("racksData",racksData);
		//req.getRequestDispatcher("/racks.jsp").forward(req,resp);
		req.getRequestDispatcher("/reserve.jsp").forward(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ArrayList<Smack> racksData = new ArrayList<Smack>();
		Connection myConnection = null;
		JDBCTutorialUtilities myJDBCTutorialUtilities = dbConnect();
		
	    // Prepare messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String id = req.getParameter("id");
        String slot = req.getParameter("slot");
        String pin = req.getParameter("pin");
        String duration = req.getParameter("duration");
        int dur = 0;
        try{
        // Get and validate id.
        
        if (id == null || id.trim().isEmpty()) {
            messages.put("id", "Please enter an ID.");
        } else if (!id.matches("\\d+")) {
            messages.put("id", "Please enter digits only.");
        } else if (Integer.parseInt(id) < 0 || Integer.parseInt(id) > 2){
        	messages.put("id", "Please enter a valid rack ID.");
        }

        // Get and validate slot.
        
        if (slot == null || slot.trim().isEmpty()) {
            messages.put("slot", "Please enter slot number.");
        } else if (!slot.matches("\\d+")) {
            messages.put("slot", "Please enter digits only.");
        } else if (Integer.parseInt(slot) < 0 || Integer.parseInt(slot) > 3){
        	messages.put("slot", "Please enter a correct slot number.");
        }
        
        // Get and validate pin.
        
        if (pin == null || pin.trim().isEmpty()) {
            messages.put("pin", "Please enter a PIN.");
        } else if (!pin.matches("\\d+")) {
            messages.put("pin", "Please enter digits only.");
        } else if (pin.toCharArray().length != 4){
        	messages.put("pin", "Please enter 4 digits.");
        }
        
     // Get and validate duration.
        
        
        if (duration == null || duration.trim().isEmpty()) {
            messages.put("duration", "Please enter a time duration in minutes.");
        } else if (!duration.matches("\\d+")) {
            messages.put("duration", "Please enter digits only.");
        } else if (Integer.parseInt(duration) < 0 || Integer.parseInt(duration) > 60){
        	messages.put("duration", "Please enter a time between 0 and 60 minutes.");
        } else {
        	dur = Integer.parseInt(duration);
        }
        } catch (NumberFormatException e){
        	messages.put("id", "Please don't try to break the server with big numbers.");
        }

        // Check for validation errors.
        this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
        if (messages.isEmpty()) {
        	
            // Check for existing reservation entry
        	if(!(this.checkTable(myJDBCTutorialUtilities, myConnection, id, slot))){
        		//slot in use
        		messages.put("id", "This slot is currently in use.");
        		//this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
        	}
        	// Check if user already has a reserved slot
        	else if (!(this.checkIP(req, myJDBCTutorialUtilities, myConnection))){
        		messages.put("id", "You cannot reserve multiple slots.");
        		//this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
        	}
        	else{
        		racksData = new ArrayList<Smack>();
        		this.updateRacks(myJDBCTutorialUtilities, myConnection, id, slot);
        		this.updateReserve(req, myJDBCTutorialUtilities, myConnection, id, slot, dur);
        		this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
        		this.updatePins(req, myJDBCTutorialUtilities, myConnection, id, slot, pin);
        	}
        }
        
	    //this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
	    //System.out.println(racksData);
	    this.closeConn(myConnection);
		req.setAttribute("racksData",racksData);
		req.getRequestDispatcher("/reserve.jsp").forward(req,resp);
	}
}

