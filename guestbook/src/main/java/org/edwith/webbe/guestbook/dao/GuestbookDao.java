package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<Guestbook> getGuestbooks(){
		List<Guestbook> list = new ArrayList<>();

		try {

			conn = DBUtil.getConnection();
			String selectSql = "SELECT * FROM guestbook";
			ps = conn.prepareStatement(selectSql);
			rs = ps.executeQuery();

			while(rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date rgdate = rs.getDate(4);
				Guestbook guestbook = new Guestbook(id,name, content,rgdate);
				list.add(guestbook);
			}

		} catch (Exception e) {
			System.err.println("get guestBook error:" + e);

		}finally {
			if(ps!=null){try{ps.close();}catch (SQLException e) {}}
			if(conn!=null){try{conn.close();}catch (SQLException e) {}}

		}return list;
	}

	public void addGuestbook(Guestbook guestbook){

		conn = DBUtil.getConnection();
		String insertSql = "INSERT INTO guestbook (id,name,content,regdate) VALUES (?,?,?,?)";
		try
		{
			ps = conn.prepareStatement(insertSql);
			ps.setLong(1, guestbook.getId());
			ps.setString(2, guestbook.getName());
			ps.setString(3, guestbook.getContent());
			ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));

			ps.executeUpdate();

		} catch (Exception e) {
			System.err.println("get guestBook error:" + e);

		}finally {
			if(ps!=null){try{ps.close();}catch (SQLException e) {}}
			if(conn!=null){try{conn.close();}catch (SQLException e) {}}
		}
	 }
}
