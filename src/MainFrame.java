import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class MainFrame {
	private  static Statement stmt;// stat
	public static void main(String[] args) {
		MainFrame myFrame = new MainFrame();		
		// ket noi cho database
		String url="jdbc:mysql://localhost:3306/danhsachnhansu";// / ten database
		String user="root";// ten nguoi dung
		String password="1234";// mat khau cua database
		String driverName="com.mysql.cj.jdbc.Driver";// cái này là  nguồn tải thư viện driver
		// dùng để keet noi db
		// bat dau thiet lap ket noi
		// dau tien phai tải drivername về , bắt buộc để trong trycacth
		try {
			Class.forName(driverName);
			
			try {
				// ket noi
				Connection myConnection= DriverManager.getConnection(url, user, password);
				// giờ bắt đầu lấy dữ liệu , truy xuất dữ liệu cần dùng statement
				stmt= myConnection.createStatement();
				String sql="select *from danhsachnhansu.thongtinnhansu";
				// luu ket qua truy xuat duoc vào resuslt set
				ResultSet resultSet=stmt.executeQuery(sql);
				while (resultSet.next()) {// chừng nào còn dữ liệu thì lấy ra
					// lấy theo từng hàng , mỗi hàng  sẽ lấy các cột tương ứng
//					System.out.println(resultSet.getString("id"));
//					System.out.println(resultSet.getString("ten"));
//					System.out.println(resultSet.getString("cmnd"));
//					System.out.println(resultSet.getString("ngaysinh"));
//					System.out.println(resultSet.getString("gioitinh"));
//					System.out.println(resultSet.getString("diachi"));
//					System.out.println(resultSet.getString("chucvu"));
					// lấy xong nó sẽ trở về check xem thử còn hàng tiếp theo ko
					// nếu còn thì lấy không thì thôi
				}
				// sau khi ket noi xog thi tao app
				myFrame.createApplication();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void createApplication() {
		JFrame frame= new JFrame();
		frame.setResizable(false);// ngan phóng ta
		// khai bao cac panel
		TopPanel topPanel= new TopPanel(stmt);
		CentrePanel centrePanel= new CentrePanel(stmt);
		BottomPanel bottomPanel= new BottomPanel();
		// cấp quyền nè
		Danhsachnhansu danhsachnhansu= new Danhsachnhansu(stmt);
		
		// khai báo layout để có thể sắp xếp
		frame.setLayout(new BorderLayout());
		// sau khi khai báo thì thêm vào frame thông qua add
		frame.add(topPanel,BorderLayout.NORTH);
		frame.add(centrePanel,BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);
		frame.add(danhsachnhansu,BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
