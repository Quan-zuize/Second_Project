/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rank;

import Home.home;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Rank extends javax.swing.JFrame {

    /**
     * Creates new form rank
     *
     */
    int player_id;
    String name;

    public final void addData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //db_demo là tên của database, root là username và password là rỗng
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/epj2_game", "root", "")) {
                //db_demo là tên của database, root là username và password là rỗng
                ArrayList<String> rank = new ArrayList<>();

                Statement stmt = con.createStatement();
                stmt.executeUpdate("DELETE from rank");

                ResultSet rs = stmt.executeQuery("select ROW_NUMBER() OVER (ORDER BY max DESC) as rank,p.nickname,MAX(a.score) as max ,MIN(a.time) as time \n"
                        + "FROM achievement a \n"
                        + "INNER JOIN player p \n"
                        + "ON a.player_id = p.player_id \n"
                        + "GROUP BY a.player_id,p.nickname \n"
                        + "ORDER BY time ASC , max DESC");

                while (rs.next()) {
                    Statement stmt2 = con.createStatement();
                    stmt2.executeUpdate("INSERT INTO `rank`( `rank_id`, `player_name`, `highest_score`, `time`) VALUES ('" + rs.getInt(1) + "','" + rs.getString(2) + "','" + rs.getInt(3) + "','" + rs.getFloat(4) + "')");

                    rank.add(rs.getString(2));
                    rank.add("" + rs.getInt(3) + "");
                    rank.add("" + rs.getFloat(4) + "");
                }

                jTextField1.setText(rank.get(0));
                jTextField2.setText(rank.get(1));
                jTextField3.setText(rank.get(2));
                jTextField4.setText(rank.get(3));
                jTextField5.setText(rank.get(4));
                jTextField6.setText(rank.get(5));
                jTextField7.setText(rank.get(6));
                jTextField8.setText(rank.get(7));
                jTextField9.setText(rank.get(8));

                ResultSet rst = stmt.executeQuery("SELECT p.nickname, r.highest_score, r.time \n"
                        + "FROM rank r \n"
                        + "INNER JOIN player p \n"
                        + "ON r.player_name = p.nickname \n"
                        + "WHERE p.player_id = " + player_id + "\n"
                        + "GROUP BY p.nickname, r.highest_score, r.time");

                while (rst.next()) {
                    name = rst.getString(1);
                    jTextField10.setText("You");
                    jTextField11.setText("" + rst.getInt(2) + "");
                    jTextField12.setText("" + rst.getFloat(3) + "");
                }

                ResultSet rset = stmt.executeQuery("Select r.rank_id from rank r\n"
                        + "INNER JOIN player p \n"
                        + "ON r.player_name ='" + name + "'");

                while (rset.next()) {
                    jLabel6.setText("No." + rset.getInt(1));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public Rank(int id) {
        player_id = id;
        this.setTitle("Rank");
        initComponents();
        setBounds(0, 0, 1200, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        addData();
    }

    public Rank() {
        this.setTitle("Rank");
        initComponents();
        setBounds(0, 0, 1200, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        addData();
    }

    class ThaoTac {

        public Connection ketNoi() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/epj2_game", "root", "");
            return con;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 214, 0));
        jLabel10.setText("       Player rank");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(420, 20, 276, 63);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Time");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(760, 90, 80, 40);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Score");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(610, 90, 90, 40);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Name");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(430, 90, 70, 40);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rank");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(250, 100, 80, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("No.1");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(250, 150, 50, 40);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("No.2");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 210, 50, 40);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("No.3");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(250, 270, 50, 40);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(590, 150, 110, 40);

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField12);
        jTextField12.setBounds(710, 400, 150, 40);

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField9);
        jTextField9.setBounds(710, 270, 150, 40);

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField10);
        jTextField10.setBounds(350, 400, 230, 40);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(350, 150, 230, 40);

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField3);
        jTextField3.setBounds(710, 150, 150, 40);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel5.setText("                    ....");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(420, 330, 510, 40);

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField6);
        jTextField6.setBounds(710, 210, 150, 40);

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField7);
        jTextField7.setBounds(350, 270, 230, 40);

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField8);
        jTextField8.setBounds(590, 270, 110, 40);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField4);
        jTextField4.setBounds(350, 210, 230, 40);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("No.");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(250, 400, 60, 40);

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(590, 210, 110, 40);

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField11);
        jTextField11.setBounds(590, 400, 110, 40);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Project\\src\\Rank\\137322576_234040788097888_1351706914099467358_n.jpg")); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12);
        jLabel12.setBounds(890, 470, 200, 40);

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Project\\src\\Rank\\pngtree-award-ceremony-blue-literary-banner-image_192709.jpg")); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 0, 1200, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        new home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Rank().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}