import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class userBorrowFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();

    String userName, borrowUid;
    String Sid, bookName, bookAuthor, bookCategories, bookPrice;
    String borrowSid;
    int borrowTime;
    String[] tempSid = new String[100];
    int i = 0;


    /**
     * Create the frame.
     */
    public userBorrowFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 900, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBounds(0, 0, 150, 511);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel welLabel = new JLabel("欢迎你");
        welLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        welLabel.setBounds(10, 10, 51, 23);
        panel.add(welLabel);

        JLabel userNameLabel = new JLabel();
        userNameLabel.setBackground(new Color(245, 245, 245));
        userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        userNameLabel.setBounds(37, 43, 103, 36);
        panel.add(userNameLabel);
        String sql = "select * from user where Uid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login.acc);
            //执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString("UserName");
                borrowUid = rs.getString("Uid");
            }
            userNameLabel.setText(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton infoButton = new JButton("个人信息");
        infoButton.setBackground(UIManager.getColor("Button.highlight"));
        infoButton.setFont(new Font("宋体", Font.PLAIN, 18));
        infoButton.setBounds(0, 105, 150, 50);
        panel.add(infoButton);
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userInfoFrame userInfoFrame = new userInfoFrame();
                userInfoFrame.setVisible(true);
                dispose();
            }
        });

        JButton borrowButton = new JButton("借阅书籍");
        borrowButton.setFont(new Font("宋体", Font.PLAIN, 18));
        borrowButton.setBackground(UIManager.getColor("Button.highlight"));
        borrowButton.setBounds(0, 155, 150, 50);
        panel.add(borrowButton);
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userBorrowFrame userBorrowFrame = new userBorrowFrame();
                userBorrowFrame.setVisible(true);
                dispose();
            }
        });



        JButton orderButton = new JButton("历史订单");
        orderButton.setFont(new Font("宋体", Font.PLAIN, 18));
        orderButton.setBackground(UIManager.getColor("Button.highlight"));
        orderButton.setBounds(0, 205, 150, 50);
        panel.add(orderButton);
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userOrderFrame userOrderFrame = new userOrderFrame();
                userOrderFrame.setVisible(true);
                dispose();
            }
        });

        JButton returnButton = new JButton("归还图书");
        returnButton.setFont(new Font("宋体", Font.PLAIN, 18));
        returnButton.setBackground(UIManager.getColor("Button.highlight"));
        returnButton.setBounds(0, 255, 150, 50);
        panel.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userReturnFrame userReturnFrame = new userReturnFrame();
                userReturnFrame.setVisible(true);
                dispose();
            }
        });

        JButton reLoginButton = new JButton("返回登陆界面");
        reLoginButton.setFont(new Font("宋体", Font.PLAIN, 18));
        reLoginButton.setBackground(Color.WHITE);
        reLoginButton.setBounds(0, 305, 150, 50);
        panel.add(reLoginButton);
        reLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login login = new login();
                login.setVisible(true);
                dispose();
            }
        });

        JLabel lblNewLabel = new JLabel("当前可借阅书籍");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        lblNewLabel.setBounds(160, 0, 190, 25);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 30, 736, 411);
        contentPane.add(scrollPane);

        String[] nums = {"书籍编号", "书籍名称", "作者", "出版社", "参考价格"};
        DefaultTableModel dm = new DefaultTableModel(nums, 0);

        JTable table = new JTable(dm);
        scrollPane.setColumnHeaderView(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);

        scrollPane.setViewportView(table);


        tableStyle.setTableStyle(table);//调用表格设置

        String sql1 = "SELECT * FROM book WHERE State = '0'";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql1);
            //执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()) {
                Sid = rs.getString("Sid");
                //将查询出来的书籍编号存入数组
                i++;
                tempSid[i] = Sid;
                bookName = rs.getString("bookName");
                bookAuthor = rs.getString("Author");
                bookCategories = rs.getString("Categories");
                bookPrice = rs.getString("price");
                String[] nums1 = {Sid, bookName, bookAuthor, bookCategories, bookPrice};
                dm.addRow(nums1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel SidLabel = new JLabel("编号");
        SidLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        SidLabel.setBounds(210, 465, 54, 23);
        contentPane.add(SidLabel);

        //借阅编号输入框
        JTextField borrowSidField = new JTextField();
        borrowSidField.setBounds(260, 462, 180, 30);
        contentPane.add(borrowSidField);
        borrowSidField.setColumns(15);
        borrowSidField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowSidField.setText("请输入要借阅的书籍编号");
        borrowSidField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrowSidField.setText("");
            }
        });

        //借阅按钮
        JButton borrowButton1 = new JButton("确认借阅");
        borrowButton1.setBounds(645, 461, 93, 30);
        contentPane.add(borrowButton1);


        JLabel timeLabel = new JLabel("时间");
        timeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        timeLabel.setBounds(460, 465, 42, 23);
        contentPane.add(timeLabel);

        //借阅时间选择
        JComboBox<String> timeComboBox = new JComboBox<>();
        timeComboBox.setBounds(510, 465, 60, 23);
        timeComboBox.addItem("1");
        timeComboBox.addItem("2");
        timeComboBox.addItem("3");
        timeComboBox.addItem("4");
        timeComboBox.addItem("5");
        timeComboBox.addItem("6");
        timeComboBox.addItem("7");
        contentPane.add(timeComboBox);

        //借阅按钮监听事件
        borrowButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowSid = borrowSidField.getText();


                //再次查询书籍是否能被借阅
                int x;
                for (x=0;x <= i;x++){
                    if (borrowSid.equals(tempSid[x])) break;
                }
                if (x <= i){
                    //更新书籍状态
                    String sql1 = "UPDATE book SET State=1 WHERE Sid=?";
                    PreparedStatement ps1 = null;
                    try {
                        ps1 = conn.prepareStatement(sql1);
                        ps1.setString(1, borrowSid);
                        ps1.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    //日期
                    SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //获取当前日期
                    Date date = new Date(System.currentTimeMillis());
                    String borrowStartTime = startDate.format(date);

                    //计算结束日期
                    borrowTime = timeComboBox.getSelectedIndex() + 1;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, borrowTime);
                    Date edate = new Date();
                    edate = calendar.getTime();
                    String borrowEndTime = startDate.format(edate);

                    //在borrowing表中插入信息
                    String sql2 = "INSERT INTO borrowing (Sid,Uid,StartTime,planEndTime) VALUES (?,?,?,?)";
                    PreparedStatement ps2 = null;
                    try {
                        ps2 = conn.prepareStatement(sql2);
                        ps2.setString(1, borrowSid);
                        ps2.setString(2, borrowUid);
                        ps2.setString(3, borrowStartTime);
                        ps2.setString(4, borrowEndTime);
                        ps2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "借阅成功！", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }else {JOptionPane.showMessageDialog(null, "请输入的书籍编号不正确或无法借阅！", "", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });


    }
}