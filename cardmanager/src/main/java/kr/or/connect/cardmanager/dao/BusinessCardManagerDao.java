package kr.or.connect.cardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.connect.cardmanager.dto.BusinessCard;

public class BusinessCardManagerDao {

	private static String dburl = "jdbc:mysql://localhost:3306/nodedb";
	private static String dbUser = "root";
	private static String dbpasswd = "root";


	public void addBusinessCard(BusinessCard businessCard) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String insertSql = "INSERT INTO card (name,phone,companyName,createDate) VALUES (?,?,?,?)";
		try(Connection conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps = conn.prepareStatement(insertSql)){

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());
			ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));

			ps.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BusinessCard> searchBusinessCard(String searchKeyword) {
		List<BusinessCard> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String selectSql = "SELECT * FROM card where name like '%"+searchKeyword +"%'";
		try(Connection conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps = conn.prepareStatement(selectSql)) {

			//ps.setString(1,"%"+searchKeyword+"%");

			try(ResultSet rs = ps.executeQuery()) {

				while(rs.next()){
					String name = rs.getString(1);
					String phone = rs.getString(2);
					String companyName = rs.getString(3);
					BusinessCard businessCard = new BusinessCard(name, phone, companyName);
					list.add(businessCard);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return list;
	}

}
