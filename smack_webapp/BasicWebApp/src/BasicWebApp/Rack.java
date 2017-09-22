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

public class Rack extends HttpServlet implements java.io.Serializable{
	
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
	
	public void updateTable(JDBCTutorialUtilities myUtil, Connection conn, String id, String slot, String status){
		try {
		      conn = myUtil.getConnection();

		      Statement stmt = null;
		      String update = "UPDATE racks " +
		    		  		  "SET slot" + slot + " = '" + status + "' " +
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
		req.getRequestDispatcher("/racks.jsp").forward(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ArrayList<Smack> racksData = new ArrayList<Smack>();
		Connection myConnection = null;
		JDBCTutorialUtilities myJDBCTutorialUtilities = dbConnect();
		
	    // Prepare messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Get and validate id.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("id", "Please enter an ID");
        } else if (!id.matches("\\d+")) {
            messages.put("id", "Please enter digits only");
        }

        // Get and validate slot.
        String slot = req.getParameter("slot");
        if (slot == null || slot.trim().isEmpty()) {
            messages.put("slot", "Please enter slot number");
        } else if (!slot.matches("\\d+")) {
            messages.put("slot", "Please enter digits only");
        }
        
        // Get and validate status.
        String status = req.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            messages.put("status", "Please enter status");
        } else if (!slot.matches("\\p{Alnum}+")) {
            messages.put("status", "Please enter alphanumeric chars only");
        }

        // Check for validation errors.
        if (messages.isEmpty()) {
            //messages.put("success", String.format("Hello, your name is %s and your age is %s!", name, age));
        	this.updateTable(myJDBCTutorialUtilities, myConnection, id, slot, status);
        }
        
	    this.getTable(racksData, myJDBCTutorialUtilities, myConnection);
	    //System.out.println(racksData);
	    this.closeConn(myConnection);
		req.setAttribute("racksData",racksData);
		req.getRequestDispatcher("/racks.jsp").forward(req,resp);
	}
}
