package com.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Crud {
	static Connection cn = null;
	public static void main(String[] args) throws InterruptedException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/hotel_db?user=root&password=Tamil14tamil@";
						
		    cn = DriverManager.getConnection(url);
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		Scanner input = new Scanner(System.in);
		
		//postVal(cn, input);
		//getVals(cn);	
		//getSpecificVal(cn, input);
		//updateVal(cn, input);
		//del(cn, input);
		

		while(true) {
			System.out.println("1 for Reserve the Room : ");
			System.out.println("2 for Reservation details : ");
			System.out.println("3 for Updating Room info : ");
			System.out.println("4 for Getting the Room_No : ");
			System.out.println("5 for Closing the Reservation : ");
			System.out.println("6 for Exit Room : ");
			int choice = input.nextInt();
			input.nextLine();
			if(choice == 6) {
				exit();
				return;
			}
			switch(choice) {
				
				case (1) -> postVal(cn, input);
				case (2) -> getVals(cn);
				case (3) -> updateVal(cn, input);
				case (4) -> getSpecificVal(cn, input);
				case (5) -> del(cn, input);				
				
			}
		}
	}
	
	static void postVal(Connection cn, Scanner input) {
		try {
			System.out.print("Enter the name : ");
			String name = input.nextLine();
			
			System.out.print("Enter the Room no : ");
			String room_no = input.nextLine();
			
			System.out.print("Enter the contact no : ");
			String mobile_no = input.nextLine();
			
			String insert = "insert into reservation (guestName, roomNo, contact) values (?, ?, ?)";
			PreparedStatement st = cn.prepareStatement(insert);
			st.setString(1, name);
			st.setString(2, room_no);
			st.setString(3, mobile_no);
			
			int count = st.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	static void getVals(Connection cn) {
		try {
						
			String query = "select * from reservation";
			Statement st = cn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int ids = rs.getInt("id");
				String name = rs.getString("guestName");
				String room_no = rs.getString("roomNo");
				String mobile_no = rs.getString("contact");
				System.out.println(ids + " " + name + " " + room_no + " " + mobile_no);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	static void getSpecificVal(Connection cn, Scanner input) {
		
		try {
			System.out.print("Enter the id : ");
			int id = input.nextInt();
			
			String query = "select * from reservation where id = ?"; 
			PreparedStatement st = cn.prepareStatement(query);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int ids = rs.getInt("id");
				String name = rs.getString("guestName");
				String room_no = rs.getString("roomNo");
				String mobile_no = rs.getString("contact");
				
				System.out.println(ids + " " + name + " " + room_no + " " + mobile_no);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	static void updateVal(Connection cn, Scanner input) {
		
		try {
			System.out.print("Enter the id : ");
			int id = input.nextInt();
			input.nextLine();
			System.out.print("Enter the name : ");
			String name = input.nextLine();
			
			System.out.print("Enter the room no : ");
			String room_no = input.nextLine();
			
			System.out.print("Enter the mobile no : ");
			String mobile_no = input.nextLine();
			
			String update = "update reservation set guestName = ?, roomNo = ?, contact = ? where id = ?";
			PreparedStatement st = cn.prepareStatement(update);
			st.setString(1, name);
			st.setString(2, room_no);
			st.setString(3, mobile_no);
			st.setInt(4, id);
			
			int count = st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	} 
	
	static void del(Connection cn, Scanner input) {
		try {
			System.out.print("Enter the id : ");
			int id = input.nextInt();
			
			String delete = "delete from reservation where id = ?";
			PreparedStatement st = cn.prepareStatement(delete);
			st.setInt(1, id);
			
			int count = st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	static void exit() throws InterruptedException {
		
		System.out.println("...Exit the Hotel...");
		
		int i = 0;
		while(i >= 0) {			
			Thread.sleep(500);
			System.out.print(".");
			if(i > 5) {
				break;
			}
			i++;
		}
		System.out.println("Reservation Closed :)");
	}
}
