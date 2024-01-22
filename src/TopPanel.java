import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopPanel extends JPanel {
	private JLabel title;
	private JPanel content, leftContent, rightContent;
	// khai bao label va textfield cho left content
	private JLabel iDLabel, nameLabel, cmndLabel, birthdayLabel, sexLabel, addressLabel;
	private JTextField idTextField, nameTextField, cmndTextField, birthdayTextField, genderTextField, addressTextField;
	private JLabel chucvuLabeL;
	private RoundButton addButton;
	private Statement stmt;
	// chuc vu
	JComboBox positionComboBox;

	public TopPanel(Statement _stmt) {
		this.stmt = _stmt;
		// tien hanh thiet lap cho jpanel
		setUpPanel();
		addTile();
		addContent();
		addEmployeeAction();
	}

	private void setUpPanel() {
		// setsize
		setPreferredSize(new Dimension(800, 200));
		// setBackground( Color.GREEN);
		// them layout
		setLayout(new BorderLayout());

	}

	// Tieu de
	private void addTile() {
		title = new JLabel("QUẢN LÝ NHÂN SỰ");
		title.setPreferredSize(new Dimension(600, 50));
		// them title vao panel
		this.add(title, BorderLayout.NORTH);
		// căn chữ vào giữa
		title.setHorizontalAlignment(JLabel.CENTER);
		// phóng to chữ
		Font font = new Font("Arial", Font.BOLD, 35);
		title.setFont(font);
	}

	private void addContent() {
		// khoi tao content panel
		content = new JPanel();
		content.setPreferredSize(new Dimension(600, 100));
		// khởi tạo layout để sắp xếp left và right
		content.setLayout(new GridLayout(1, 2));// 2 cột 1 hàng
		// thêm left và right
		addLeftContent();
		addRightContent();
		content.add(leftContent);
		content.add(rightContent);
		// thêm content vào panel
		this.add(content, BorderLayout.CENTER);

	}

	private void addRightContent() {
		rightContent = new JPanel();
		rightContent.setBackground(Color.lightGray);
		rightContent.setLayout(new GridLayout(5, 2, 0, 5));
		int topInset = 10;
		int leftInset = 10;
		int bottomInset = 10;
		int rightInset = 10;

		rightContent.setBorder(BorderFactory.createEmptyBorder(topInset, leftInset, bottomInset, rightInset));

		// khai bao cac lable
		sexLabel = new JLabel("Giới tính");
		sexLabel.setHorizontalAlignment(JLabel.CENTER);
		sexLabel.setForeground(Color.BLUE);
		addressLabel = new JLabel("Địa chỉ");
		addressLabel.setHorizontalAlignment(JLabel.CENTER);
		addressLabel.setForeground(Color.BLUE);
		// khai bao textfield
		genderTextField = new JTextField();
		addressTextField = new JTextField();
		// them lable va textfield
		rightContent.add(sexLabel);
		rightContent.add(genderTextField);
		rightContent.add(addressLabel);
		rightContent.add(addressTextField);
		// them button

		// option textfield
		JTextField positionTextField = new JTextField();
		positionComboBox = new JComboBox<>(new String[] { "Lập trình viên", "tester", "Quản lý" });
		positionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Set the text field value based on the selected item in the combo box
				positionTextField.setText(positionComboBox.getSelectedItem().toString());
			}
		});

		// Add the position text field and combo box to the panel
		chucvuLabeL = new JLabel("Chức vụ:");
		chucvuLabeL.setHorizontalAlignment(JLabel.CENTER);
		chucvuLabeL.setForeground(Color.BLUE);
		rightContent.add(chucvuLabeL);
		rightContent.add(positionComboBox);
		// add button
		addButton = new RoundButton("Thêm nhân sự");
		JButton virtualButton = new JButton();
		virtualButton.setVisible(false);
		rightContent.add(virtualButton);
		rightContent.add(addButton);
	}

	private void addLeftContent() {
		leftContent = new JPanel();
		// set màu chữ
		leftContent.setBackground(Color.LIGHT_GRAY);
		// dung grid de sap xep 2 cot 4 hang
		leftContent.setLayout(new GridLayout(6, 2, 0, 5));
		// khai bao cac label
		iDLabel = new JLabel("ID");
		iDLabel.setHorizontalAlignment(JLabel.CENTER);
		iDLabel.setForeground(Color.BLUE);
		nameLabel = new JLabel("Tên");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setForeground(Color.BLUE);
		cmndLabel = new JLabel("CMND");
		cmndLabel.setHorizontalAlignment(JLabel.CENTER);
		cmndLabel.setForeground(Color.BLUE);
		birthdayLabel = new JLabel("Ngày sinh");
		birthdayLabel.setHorizontalAlignment(JLabel.CENTER);// căn giữa
		birthdayLabel.setForeground(Color.BLUE);
		// khai báo text field
		idTextField = new JTextField();
		nameTextField = new JTextField();
		cmndTextField = new JTextField();
		birthdayTextField = new JTextField();
		// them lable va text file lan luot trai qua phai , tren xuong
		leftContent.add(iDLabel);
		leftContent.add(idTextField);
		leftContent.add(nameLabel);
		leftContent.add(nameTextField);
		leftContent.add(cmndLabel);
		leftContent.add(cmndTextField);
		leftContent.add(birthdayLabel);
		leftContent.add(birthdayTextField);
	}

	private void addEmployeeAction() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO danhsachnhansu.thongtinnhansu(id, ten, cmnd, ngaysinh,gioitinh,diachi, chucvu) "
						+ "values('" + idTextField.getText() + "'" + "," + "'" + nameTextField.getText() + "'" + ","
						+ "'" + cmndTextField.getText() + "'" + "," + "'"
						+ dateProcessReverse(birthdayTextField.getText()) + "'" + "," //
						+ "'" + genderTextField.getText() + "'" + "," + "'" + addressTextField.getText() + "'" + ","
						+ "'" + positionComboBox.getSelectedItem().toString() + "')";
				try {
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
				}

			}
		});

	}

	private String dateProcessReverse(String s) {

		String[] parts = s.split("-");

		// Đảo ngược vị trí các phần
		return s = parts[2] + "-" + parts[1] + "-" + parts[0];
	}
}
