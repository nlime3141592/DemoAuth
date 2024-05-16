package com.ex.demoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DemoAuthApplication
{
	public static void main(String[] args)
	{
		// TestConnectToDB();
		SpringApplication.run(DemoAuthApplication.class, args);
	}

	// TEST: 데이터베이스 연결을 테스트하기 위한 테스트 코드.
	private static void TestConnectToDB()
	{
		String dbUrl = "jdbc:mariadb://localhost:3306/board";

		try
		{
			Connection conn = DriverManager.getConnection(dbUrl, "root", "1234");

			// NOTE: Execute query.
			PreparedStatement state = conn.prepareStatement("select * from board");

			ResultSet results = state.executeQuery();

			while(results.next())
			{
				for(int i = 0; i < 8; ++i)
					System.out.println(results.getString(i + 1));
			}

			conn.close();
		}
		catch(SQLException _sqlEx)
		{

		}
	}
}
