package com.ezen.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.member.dto.MemberDto;

public class MemberDao {
	
	//싱글턴 코드
	private MemberDao() {}
	private static MemberDao ist = new MemberDao();
	public static MemberDao getInstance() { return ist; }
	
	//데이터베이스 Access에 필요한 객체 및 변수
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.OracleDriver";
	
	//getConnection과 close
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","tiger");
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace(); }
		return con;
	}
	private void close() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		
		String sql = "select * from member where userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { close(); }
		
		return mdto;
	}
	public int insertMember(MemberDto mdto) {
		int result = 0;
		String sql = "insert into member(userid,name,pwd,phone,email,admin)"
				+ " values(?,?,?,?,?,?)";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getEmail());
			pstmt.setInt(6, mdto.getAdmin());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		
		return result;
	}
	public int updateMember(MemberDto mdto) {
		int result = 0;
		String sql = "update member set name=?,pwd=?,phone=?,email=?,admin=?"
				+ " where userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getPhone());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setInt(5, mdto.getAdmin());
			pstmt.setString(6, mdto.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		
		return result;
	}
	public ArrayList<MemberDto> selectMember() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "select * from member order by admin, userid";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto mdto = new MemberDto();
				mdto.setName(rs.getString("name"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
				list.add(mdto); 
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {close();}
		return list;
	}
	public void editAdmin(String userid, int admin) {
		String sql = "update member set admin=? where userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
	}
	public void deleteMember(String userid) {
		String sql = "delete from member where userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
	}

}
