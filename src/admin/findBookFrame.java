package admin;
import main.MySQLLink;
import main.tableStyle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class findBookFrame extends JFrame {
    ResultSet rs = null;
    Connection conn = MySQLLink.getConnection();
    String Sid,bookName,bookAuthor,bookCategories,bookPrice,state;

    public  findBookFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 900, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 30, 900, 550);
        contentPane.add(scrollPane);

        //左上角返回主界面按钮
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(5, 5, 93, 23);
        contentPane.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminFrame adminFrame = new adminFrame();
                adminFrame.setVisible(true);
                dispose();
            }
        });

        String[] nums = {"书籍编号", "书籍名称", "作者", "出版社", "参考价格","状态"};
        DefaultTableModel dm = new DefaultTableModel(nums, 0);

        JTable table = new JTable(dm);
        scrollPane.setColumnHeaderView(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);

        scrollPane.setViewportView(table);


        tableStyle.setTableStyle(table);//调用表格设置

        String sql = "SELECT * FROM book ";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Sid = rs.getString("Sid");
                bookName = rs.getString("bookName");
                bookAuthor = rs.getString("Author");
                bookCategories = rs.getString("Categories");
                bookPrice = rs.getString("price");
                String tempstate = rs.getString("State");
                switch (tempstate){
                    case "0" :state = "未借出";break;
                    case "1" :state = "已借出";break;
                }
                String[] nums1 = {Sid, bookName, bookAuthor, bookCategories, bookPrice,state};
                dm.addRow(nums1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
