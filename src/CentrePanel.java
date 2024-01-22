import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.standard.DateTimeAtProcessing;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CentrePanel extends JPanel {
	private Statement stmt;
	// frame hien ra khi nhan nut tim kiem
	private JFrame frame = null;
	//
	private RoundButton deleteButton, changebutton, searchButton;
	// id
	private String idToDeletString = "";

	// top panel
	private JButton topSearchButton;
	private JTextField topTextField;
	// info panel
	private JTextField idTextField, nameTextField, birthTextField, genderTextField, cmndTextField;
	private JTextField chucvuTextField, addressTextField;
	private JLabel idLabel, nameLabel, birthLabel, genderLabel, cmndLabel, chucvuLabel, addressLabel;

	public CentrePanel(Statement _stmt) {
		this.stmt = _stmt;
		setUpPanel();
		addPressAction();
		addDeleteAction();
		addChangeAction();
	}

	private void setUpPanel() {
		setPreferredSize(new Dimension(800, 50));
		setBackground(Color.gray);
		int topInset = 5;
		int leftInset = 50;
		int bottomInset = 0;
		int rightInset = 50;

		this.setBorder(BorderFactory.createEmptyBorder(topInset, leftInset, bottomInset, rightInset));

		// Adjust the button size by setting a preferred size
		int buttonWidth = 30;
		int buttonHeight = 5;

		// nằm trong frame khi nhấn tìm kiếm
		changebutton = new RoundButton("Chỉnh sửa");
		changebutton.setVisible(false);
		//
		deleteButton = new RoundButton("Xóa nhân sự");
		deleteButton.setVisible(false);
		//
		searchButton = new RoundButton("Tìm thông tin");
		searchButton.setPreferredSize(new Dimension(200, 30));
		this.add(searchButton);

	}

	private void addPressAction() {
		searchButton.addActionListener(new ActionListener() { // thêm sự kiện khi nhấn nút

			@Override
			public void actionPerformed(ActionEvent e) {
				createSearchFrame();// khi nhấn nút thì tạo khung tìm kiếm
			}
		});
	}

	private void createSearchFrame() {
		if (frame == null)
			frame = new JFrame();
		else {

			return;
		}

		frame.setSize(new Dimension(350, 450));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);// set ở giữa màn hình
		// setlayout
		frame.setLayout(new BorderLayout());
		addSearchTopPanel(frame);
		addSearchInfoPanel(frame);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame = null;
				System.out.println(1);
			}
		});
	}

	private void addSearchTopPanel(JFrame frame) {
		JPanel topPanel = new JPanel();

		topPanel.setBackground(Color.pink);
		topPanel.setPreferredSize(new Dimension(100, 100));

		topSearchButton = new JButton("Tìm kiếm");
		topTextField = new JTextField();
		// add gui
		topTextField.setPreferredSize(new Dimension(200, 30));
		topSearchButton.setPreferredSize(new Dimension(100, 30));

		changebutton.setPreferredSize(new Dimension(100, 30));
		changebutton.setBounds(5, 10, 30, 20);
		changebutton.setBackground(Color.lightGray);

		deleteButton.setVisible(false);
		deleteButton.setPreferredSize(new Dimension(100, 30));
		deleteButton.setBackground(Color.lightGray);

		// can le

		topPanel.add(topTextField);
		topPanel.add(topSearchButton);
		topPanel.add(deleteButton);
		//

		//
		topPanel.add(changebutton);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.setVisible(true);
	}

	private void addSearchInfoPanel(JFrame frame) {
		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(350, 350));
		infoPanel.setBackground(Color.white);
		// setlayout
		infoPanel.setLayout(new GridLayout(7, 2, 10, 20));
		idLabel = new JLabel("ID  :");
		infoPanel.add(idLabel);
		idTextField = new JTextField();
		idTextField.setEditable(false);
		infoPanel.add(idTextField);
		nameLabel = new JLabel("Tên   :");
		infoPanel.add(nameLabel);
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		infoPanel.add(nameTextField);
		// cmnd
		cmndLabel = new JLabel("CMND  :");
		infoPanel.add(cmndLabel);
		cmndTextField = new JTextField();
		cmndTextField.setEditable(false);
		infoPanel.add(cmndTextField);
		//
		birthLabel = new JLabel("Ngày sinh   :");
		infoPanel.add(birthLabel);
		birthTextField = new JTextField();
		birthTextField.setEditable(false);
		infoPanel.add(birthTextField);
		//
		genderLabel = new JLabel("Giới tính   :");
		infoPanel.add(genderLabel);
		genderTextField = new JTextField();
		genderTextField.setEditable(false);
		infoPanel.add(genderTextField);
		// dia chi
		addressLabel = new JLabel("Địa chỉ   :");
		infoPanel.add(addressLabel);
		addressTextField = new JTextField();
		addressTextField.setEditable(false);
		infoPanel.add(addressTextField);
		// chức vụ
		chucvuLabel = new JLabel("Chức vụ   :");
		infoPanel.add(chucvuLabel);
		chucvuTextField = new JTextField();
		chucvuTextField.setEditable(false);
		infoPanel.add(chucvuTextField);
		int topPadding = 10;
		int leftPadding = 10;
		int bottomPadding = 10;
		int rightPadding = 10;
		infoPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
		frame.add(infoPanel, BorderLayout.CENTER);
		// them su kien cho nut search khi click
		dislayInfoWhenPressSearchButton();
	}

	private void dislayInfoWhenPressSearchButton() {
		// thêm sự kiện cho nút
		topSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				executeDataFromDatabase();
				// System.out.println(1);

			}
		});
	}

	private void executeDataFromDatabase() {// truy xuất dữ liệu khi tìm kiếm
		// giờ mình lấy id từ ô text
		String inputId = topTextField.getText();
		System.out.println(inputId);
		if (!inputId.isEmpty()) {
			String sql = "select * from danhsachnhansu.thongtinnhansu where id='" + inputId + "'";
			try {
				ResultSet resultSet = stmt.executeQuery(sql);
				if (!resultSet.next()) {
					throw new SQLException("id khong ton tai");
				} else {
					deleteButton.setVisible(true);
					changebutton.setVisible(true);
				}
				idTextField.setText(resultSet.getString("id"));
				idToDeletString = (resultSet.getString("id"));

				nameTextField.setText(resultSet.getString("ten"));
				// cmnd
				cmndTextField.setText(resultSet.getString("cmnd"));
				//
				birthTextField.setText(dateProcess(resultSet.getString("ngaysinh")));
				//
				genderTextField.setText(resultSet.getString("gioitinh"));
				// dia chi
				addressTextField.setText(resultSet.getString("diachi"));
				// chức vụ
				chucvuTextField.setText(resultSet.getString("chucvu"));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// s
	}

	private String dateProcess(String s) {
		// cat xau 
		s = s.substring(0, s.indexOf(" "));

		// Tách xâu thành các phần 
		String[] parts = s.split("-");

		// Đảo ngược vị trí các phần 
		return s = parts[2] + "-" + parts[1] + "-" + parts[0];
	}

	private void addDeleteAction() {
		System.out.println(deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(1);
				String sql = "delete from danhsachnhansu.thongtinnhansu where id='" + idToDeletString + "'";
				try {
					stmt.executeUpdate(sql);
					deleteButton.setVisible(false);
					idToDeletString = "";
					idTextField.setText("");
					idToDeletString = "";

					nameTextField.setText("");
					// cmnd
					cmndTextField.setText("");
					//
					birthTextField.setText("");
					//
					genderTextField.setText("");
					// dia chi
					addressTextField.setText("");
					// chức vụ
					chucvuTextField.setText("");
					JOptionPane.showMessageDialog(null, "Đã xóa thành công");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

//them nut chinh sua
	private void addChangeAction() {
		changebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idTextField.getText().isEmpty())
					return;
				if (changebutton.getText() == "Chỉnh sửa") {
					nameTextField.setEditable(true);
					cmndTextField.setEditable(true);
					birthTextField.setEditable(true);
					genderTextField.setEditable(true);
					addressTextField.setEditable(true);
					changebutton.setText("Lưu");
				} else {
					String sql = "UPDATE danhsachnhansu.thongtinnhansu set ten='" + nameTextField.getText() + "'";
					String sql2 = "UPDATE danhsachnhansu.thongtinnhansu set cmnd='" + cmndTextField.getText() + "'";
					String sql3 = "UPDATE danhsachnhansu.thongtinnhansu set ngaysinh='"
							+ dateProcessReverse(birthTextField.getText()) + "'";
					String sql4 = "UPDATE danhsachnhansu.thongtinnhansu set gioitinh='" + genderTextField.getText()
							+ "'";
					String sql5 = "UPDATE danhsachnhansu.thongtinnhansu set diachi='" + addressTextField.getText()
							+ "'";
					String where = "where id='" + idTextField.getText() + "'";

					try {
						stmt.executeUpdate(sql + where);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Tên không hợp lệ");
					}
					try {
						stmt.executeUpdate(sql2 + where);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "CMND không hợp lệ");
					}
					try {
						stmt.executeUpdate(sql3 + where);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ");
					}
					try {
						stmt.executeUpdate(sql4 + where);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					try {
						stmt.executeUpdate(sql5 + where);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					changebutton.setText("Chỉnh sửa");
					// khi ma luu xong
					nameTextField.setEditable(false);
					cmndTextField.setEditable(false);
					birthTextField.setEditable(false);
					genderTextField.setEditable(false);
					addressTextField.setEditable(false);
				}
				// update data

				String sql = "UPDATE danhsachnhansu.thongtinnhansu";

			}
		});
	}

	private String dateProcessReverse(String s) {

		String[] parts = s.split("-");

		// Đảo ngược vị trí các phần
		return s = parts[2] + "-" + parts[1] + "-" + parts[0];
	}
}
