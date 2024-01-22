import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicBorders;

public class RoundButton extends JButton {
    public RoundButton(String text) {
        setText(text);
        setPreferredSize(new Dimension(120, 60));
       // setContentAreaFilled(false);
        setBackground(Color.pink);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 9));
       // setForeground(Color.white);
        setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(3, 3, 3, 3, Color.blue),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Thêm hiệu ứng Hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.black);
            }
        });

        // Thêm hiệu ứng Press
        getModel().addChangeListener(evt -> {
            if (getModel().isPressed()) {
                setForeground(Color.red);
            } else {
                setForeground(Color.red);
            }
        });
    }
}