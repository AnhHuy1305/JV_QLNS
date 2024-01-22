import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NhanVien {
	String id;
	String ten;
	String gioiTinh;
	String cccd;
	String diaChi;
	String chucVu;

	public NhanVien(String id, String ten, String gioiTinh, String cccd, String diaChi, String chucVu) {
		this.id = id;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.cccd = cccd;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Tên: " + ten + ", Giới Tính: " + gioiTinh + ", CCCD: " + cccd + ", Địa Chỉ: " + diaChi
				+ ", Chức Vụ: " + chucVu;
	}
}

public class QuanLyNhanSuGUI extends JPanel {

	private DefaultListModel<NhanVien> listModel;
	private JList<NhanVien> nhanVienList;
	private JTextField txtID, txtTen, txtGioiTinh, txtCCCD, txtDiaChi, txtChucVu;

	public QuanLyNhanSuGUI() {

		listModel = new DefaultListModel<>();
		nhanVienList = new JList<>(listModel);

		txtID = new JTextField(20);
		txtTen = new JTextField(20);
		txtGioiTinh = new JTextField(20);
		txtCCCD = new JTextField(20);
		txtDiaChi = new JTextField(20);
		txtChucVu = new JTextField(20);

		JButton btnThem = new JButton("Thêm Nhân Viên");
		JButton btnXoa = new JButton("Xóa Nhân Viên");
		JButton btnChinhSua = new JButton("Chỉnh Sửa Thông Tin");
		JButton btnTimKiem = new JButton("Tìm Kiếm Nhân Viên");

		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(txtID);
		inputPanel.add(new JLabel("Tên:"));
		inputPanel.add(txtTen);
		inputPanel.add(new JLabel("Giới Tính:"));
		inputPanel.add(txtGioiTinh);
		inputPanel.add(new JLabel("CCCD:"));
		inputPanel.add(txtCCCD);
		inputPanel.add(new JLabel("Địa Chỉ:"));
		inputPanel.add(txtDiaChi);
		inputPanel.add(new JLabel("Chức Vụ:"));
		inputPanel.add(txtChucVu);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(btnThem);
		buttonPanel.add(btnXoa);
		buttonPanel.add(btnChinhSua);
		buttonPanel.add(btnTimKiem);

		add(new JScrollPane(nhanVienList), BorderLayout.CENTER);
		add(inputPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themNhanVien();
			}
		});

		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xoaNhanVien();
			}
		});

		btnChinhSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chinhSuaNhanVien();
			}
		});

		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timKiemNhanVien();
			}
		});

		// nhanVienList.addListSelectionListener(e -> hienThongTinNhanVien());

		listModel.addElement(new NhanVien("NV001", "Nguyen Van A", "Nam", "123456789", "Ha Noi", "Nhan vien"));
		listModel.addElement(new NhanVien("NV002", "Tran Thi B", "Nu", "987654321", "Ho Chi Minh", "Truong phong"));

		setSize(600, 400);
		setVisible(true);
	}

	private void themNhanVien() {
		String id = txtID.getText();
		String ten = txtTen.getText();
		String gioiTinh = txtGioiTinh.getText();
		String cccd = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		String chucVu = txtChucVu.getText();

		NhanVien nhanVien = new NhanVien(id, ten, gioiTinh, cccd, diaChi, chucVu);
		listModel.addElement(nhanVien);

	}

	private void xoaNhanVien() {
		int selectedIndex = nhanVienList.getSelectedIndex();
		if (selectedIndex != -1) {
			listModel.remove(selectedIndex);

		}
	}

	private void chinhSuaNhanVien() {
		int selectedIndex = nhanVienList.getSelectedIndex();
		if (selectedIndex != -1) {
			NhanVien selectedNhanVien = listModel.getElementAt(selectedIndex);
			selectedNhanVien.id = txtID.getText();
			selectedNhanVien.ten = txtTen.getText();
			selectedNhanVien.gioiTinh = txtGioiTinh.getText();
			selectedNhanVien.cccd = txtCCCD.getText();
			selectedNhanVien.diaChi = txtDiaChi.getText();
			selectedNhanVien.chucVu = txtChucVu.getText();

			nhanVienList.repaint();

		}
	}

	private void timKiemNhanVien() {
		String keyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:");
		if (keyword != null) {
			keyword = keyword.toLowerCase();

			java.util.List<NhanVien> resultList = new java.util.ArrayList<>();
			for (int i = 0; i < listModel.getSize(); i++) {
				NhanVien nhanVien = listModel.getElementAt(i);
				if (nhanVien.id.toLowerCase().contains(keyword) || nhanVien.ten.toLowerCase().contains(keyword)
						|| nhanVien.gioiTinh.toLowerCase().contains(keyword)
						|| nhanVien.cccd.toLowerCase().contains(keyword)
						|| nhanVien.diaChi.toLowerCase().contains(keyword)
						|| nhanVien.chucVu.toLowerCase().contains(keyword)) {
					resultList.add(nhanVien);
				}
			}

			DefaultListModel<NhanVien> searchResultModel = new DefaultListModel<>();
			for (NhanVien nhanVien : resultList) {
				searchResultModel.addElement(nhanVien);
			}
		}
	}

}