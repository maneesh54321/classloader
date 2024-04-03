package com.ms.cl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseClassLoader extends ClassLoader {

	private final String connString;

	public DatabaseClassLoader(ClassLoader parent, String connString) {
		super("DatabaseClassLoader", parent);
		this.connString = connString;
	}

	public DatabaseClassLoader(String connString) {
		super("DatabaseClassLoader", ClassLoader.getPlatformClassLoader());
		this.connString = connString;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			return super.findClass(name);
		} catch (ClassNotFoundException e){
			try {
				byte[] data = getClassData(name);
				assert data != null;
				return defineClass(name, data, 0, data.length);
			} catch (Exception ex){
				throw new ClassNotFoundException(ex.getMessage());
			}
		}
	}

	private byte[] getClassData(String name) throws SQLException {
		byte[] result = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connString);
			PreparedStatement statement = conn.prepareStatement("select * from class where name = ?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				System.out.println("id: " + rs.getInt(1));
				System.out.println("name: " + rs.getString(2));
				result = rs.getBytes(3);
			}
		} finally {
			assert conn != null;
			conn.close();
		}
		return result;
	}
}
