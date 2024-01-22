
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Danhsachnhansu extends JPanel {
	private JPanel contentPanel;
	private JScrollPane jScrollPane;
	private JPanel titlePanel;
	// nhận quyền
	private Statement stmt;

	public Danhsachnhansu(Statement _stmt) {
		this.stmt = _stmt;// đã nhận quyền được cấp thông qua hàm tạo constructor
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.orange);
		jScrollPane = new JScrollPane(contentPanel);
		addGUI();
		setUp();

	}

	private void setUp() {
		this.setPreferredSize(new Dimension(600, 200));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		jScrollPane.setOpaque(true); // Bật tính năng vẽ nền
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jScrollPane);
	}

	private void addGUI() {

		addLabelTitle();
		updateData();
	}

	private void addLabelTitle() {
		titlePanel = new JPanel(new GridLayout(1, 7));
		titlePanel.setBackground(Color.orange);
		titlePanel.add(new JLabel("ID"));
		titlePanel.add(new JLabel("Họ và tên"));
		titlePanel.add(new JLabel("CMND"));
		titlePanel.add(new JLabel("Ngày sinh"));
		titlePanel.add(new JLabel("Giới tính"));
		titlePanel.add(new JLabel("Địa chỉ"));
		titlePanel.add(new JLabel("Chức vụ"));
		this.add(titlePanel);
	}

	private void updateData() {
		contentPanel.setLayout(new GridLayout(0, 7));

		ArrayList<String> studentInfoList = new ArrayList<>();
		// hiển thị danh sách lên bảng
		String sql = "select *from danhsachnhansu.thongtinnhansu";
		// truy xuất thông qua quyền đã được cấp và lưu nó vào resulset
		// bat buoc de trong try catch
		try {
			ResultSet resultSet = this.stmt.executeQuery(sql);
			// chừng nào có dữ liệu thì lấy ra
			while (resultSet.next()) {
				JTextField id = new JTextField(resultSet.getString("id"));
				id.setEditable(false);
				id.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(id);
				JTextField name = new JTextField(resultSet.getString("ten"));
				name.setEditable(false);
				name.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(name);
				JTextField cmnd = new JTextField(resultSet.getString("cmnd"));
				cmnd.setEditable(false);
				cmnd.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(cmnd);
				JTextField ngaysinh = new JTextField(dateProcess(resultSet.getString("ngaysinh")));
				ngaysinh.setEditable(false);
				ngaysinh.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(ngaysinh);
				JTextField gioitinh = new JTextField(resultSet.getString("gioitinh"));
				gioitinh.setEditable(false);
				gioitinh.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(gioitinh);
				JTextField diachi = new JTextField(resultSet.getString("diachi"));
				diachi.setEditable(false);
				diachi.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(diachi);
				JTextField chucvu = new JTextField(resultSet.getString("chucvu"));
				chucvu.setEditable(false);
				chucvu.setFont(new Font("Arial", Font.BOLD, 12));
				contentPanel.add(chucvu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String dateProcess(String s) {
		s = s.substring(0, s.indexOf(" "));

		// Tách xâu thành các phần
		String[] parts = s.split("-");

		// Đảo ngược vị trí các phần
		return s = parts[2] + "-" + parts[1] + "-" + parts[0];
	}
}