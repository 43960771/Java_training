package admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class adminFrame extends JFrame {
    public  adminFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 900, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 884, 511);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton findUserButton = new JButton("查找用户");
        findUserButton.setFont(new Font("宋体", Font.PLAIN, 18));
        findUserButton.setBackground(Color.WHITE);
        findUserButton.setBounds(225, 135, 150, 50);
        panel.add(findUserButton);
        findUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findUserFrame findUserFrame = new findUserFrame();
                findUserFrame.setVisible(true);
                dispose();
            }
        });

        JButton findBookButton = new JButton("查找书籍");
        findBookButton.setFont(new Font("宋体", Font.PLAIN, 18));
        findBookButton.setBackground(Color.WHITE);
        findBookButton.setBounds(479, 135, 150, 50);
        panel.add(findBookButton);
        findBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findBookFrame findBookFrame = new findBookFrame();
                findBookFrame.setVisible(true);
                dispose();
            }
        });

        JButton addUserButton = new JButton("添加用户");
        addUserButton.setFont(new Font("宋体", Font.PLAIN, 18));
        addUserButton.setBackground(Color.WHITE);
        addUserButton.setBounds(225, 235, 150, 50);
        panel.add(addUserButton);
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUserFrame addUserFrame = new addUserFrame();
                addUserFrame.setVisible(true);
                dispose();
            }
        });

        JButton addBookButton = new JButton("添加书籍");
        addBookButton.setFont(new Font("宋体", Font.PLAIN, 18));
        addBookButton.setBackground(Color.WHITE);
        addBookButton.setBounds(479, 235, 150, 50);
        panel.add(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookFrame addBookFrame = new addBookFrame();
                addBookFrame.setVisible(true);
                dispose();
            }
        });

        JButton deleteUserButton = new JButton("删除用户");
        deleteUserButton.setFont(new Font("宋体", Font.PLAIN, 18));
        deleteUserButton.setBackground(Color.WHITE);
        deleteUserButton.setBounds(225, 335, 150, 50);
        panel.add(deleteUserButton);
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUserFrame deleteUserFrame = new deleteUserFrame();
                deleteUserFrame.setVisible(true);
                dispose();
            }
        });

        JButton deleteBookButton = new JButton("删除书籍");
        deleteBookButton.setFont(new Font("宋体", Font.PLAIN, 18));
        deleteBookButton.setBackground(Color.WHITE);
        deleteBookButton.setBounds(479, 335, 150, 50);
        panel.add(deleteBookButton);
        deleteBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBookFrame deleteBookFrame = new deleteBookFrame();
                deleteBookFrame.setVisible(true);
                dispose();
            }
        });
    }
}
