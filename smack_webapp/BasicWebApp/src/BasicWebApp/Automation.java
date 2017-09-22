package BasicWebApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Automation {
	
	public static JDBCTutorialUtilities dbConnect(){
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

	public static void main(String[] args) {
		
		Connection conn = null;
		JDBCTutorialUtilities myUtil = dbConnect();
		
		try {
		      conn = myUtil.getConnection();

		      Statement stmt = null;
		      String query = "SELECT * FROM reserve";
		      long curtime = System.currentTimeMillis();
		      long durtime = 0;
		      long oldtime = 0;
		      int racknum = 0;
		      int slotnum = 0;
		      
		      try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while(rs.next()){
		        	// DO STUFF HERE
		        	//Automation checks database for inconsistencies
		        	//i.e. a reserved slot was taken and now the reservation
		        	//needs to be updated.
		        	racknum = rs.getInt("id");
		        	slotnum = rs.getInt("slot");
		        	System.out.println("Checking rack " + racknum + " slot " + slotnum + "...");
		        	if(checkRacks(conn, racknum, slotnum)){
		        		//slot used, remove entry from reserve table
		        		
		        		removeEntry(conn, racknum, slotnum);
		        		continue;
		        	}
		        	//slot still rsvd/open
		        	//check if reserved for too long
		        	
		        	durtime = rs.getInt("duration") * 60000;
		        	oldtime = rs.getLong("time");
		        	if(curtime - oldtime > durtime){
		        		//slot reserved for too long
		        		//remove entry, set racks entry to open, set pins entry to null
		        		removeEntry(conn, racknum, slotnum);
		        		setRack(conn, racknum, slotnum);
		        		setPins(conn, racknum, slotnum);
		        		continue;
		        	}
		        	System.out.println("Entry is valid.");
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
		JDBCTutorialUtilities.closeConnection(conn);
	    System.out.println("Connection closed.");

	}

	private static void setPins(Connection conn, int id, int slot) {
		// TODO Auto-generated method stub
		System.out.println("Setting PIN of slot to null...");
		Statement stmt = null;
	      String update = "UPDATE pins " +
		  		  		  "SET slot" + slot + " = NULL " +
		  		  		  "WHERE id = " + id;
	      System.out.println(update);
	      try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(update);
	
	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JDBCTutorialUtilities.printSQLException(e);
				//e.printStackTrace();
			} 
	       }
	      }
	}

	private static void setRack(Connection conn, int id, int slot) {
		// set slot to open
		System.out.println("Setting slot to open...");
		Statement stmt = null;
	      String update = "UPDATE racks " +
  		  		  		  "SET slot" + slot + " = 'open' " +
  		  		  		  "WHERE id = " + id;
	      System.out.println(update);
	      try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(update);
	
	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JDBCTutorialUtilities.printSQLException(e);
				//e.printStackTrace();
			} 
	       }
	      }
	}

	private static void removeEntry(Connection conn, int id, int slot) {
		// remove entry from reserve table
		System.out.println("Removing entry from reserve table...");
		Statement stmt = null;
	      String update = "DELETE FROM reserve " +
		  		  		  "WHERE id = '" + id + "' AND slot = '" + slot + "' ";
	      System.out.println(update);
	      try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(update);
	
	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JDBCTutorialUtilities.printSQLException(e);
				//e.printStackTrace();
			} 
	       }
	      }
	      
	}

	private static boolean checkRacks(Connection conn, int racknum, int slotnum) {
		// TODO Auto-generated method stub
		System.out.println("Checking if slot is now used...");
		Statement stmt = null;
	    String query = "SELECT * FROM racks " +
	    			   "WHERE id = " + racknum;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while(rs.next()){
	        	// DO STUFF HERE
	        	String slotstatus = rs.getString("slot" + slotnum);
	        	if(slotstatus.equals("used")){
	        		return true;
	        	}
	        	else{
	        		return false;
	        	}
	        	
	        }

	      } catch (SQLException e) {
	        JDBCTutorialUtilities.printSQLException(e);
	      } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JDBCTutorialUtilities.printSQLException(e);
				//e.printStackTrace();
			} }
	      }
	    
		return false;
	}

}
