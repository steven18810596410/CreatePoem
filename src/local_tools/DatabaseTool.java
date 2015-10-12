package local_tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTool {

	public static void insert(String word, String tone, String property) {
		Statement stmt = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/words_db?user=root&password=a111111");
			stmt = conn.createStatement();
			stmt.execute("insert into words(word,tone,property) values('"
					+ word + "','" + tone + "','" + property + "')");
		} catch (Exception e) {

		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	public static String getWordByToneAndProperty(int toneIndex, String property) {
		String[] tones = { "ˉ", "ˊ", "ˇ", "ˋ" };
		String tone = tones[toneIndex-1];

		int count = 0;

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/words_db?user=root&password=a111111");
			stmt = conn.createStatement();
			stmt.execute("select count(*) from words where tone like '%" + tone
					+ "%' and property like '%" + property + "%'");
			rs = stmt.getResultSet();
			rs.next();
			count = rs.getInt(1);

			int pickIndex = (int) (Math.random() * count);
			if (pickIndex == 0)
				pickIndex = 1;
			stmt.execute("select * from words where tone like '%" + tone
					+ "%' and property like '%" + property + "%'");
			rs = stmt.getResultSet();
			while (pickIndex > 0) {
				rs.next();
				pickIndex--;
			}
			return rs.getString("word");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
