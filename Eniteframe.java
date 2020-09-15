
import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Eniteframe extends javax.swing.JFrame {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dataismi = "question";
    private String driver = "com.mysql.jdbc.Driver";
    private String userismi = "root";
    private String sifre = "";
    private ResultSet resultset;
    String kullacisim, adres = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

    Socket sc;
    BufferedReader reader;
    PrintWriter writer;

    public Eniteframe() {

        initComponents();

        Statement statement;

        try {
            statement = baglantiAc();
            
        } catch (Exception exception) {

            JOptionPane.showConfirmDialog(null, "Bağlantı Başarısız", "MySQL Bağlantısı", JOptionPane.PLAIN_MESSAGE);
            System.out.println(exception);
        }
                dispose();

    }

    public Statement baglantiAc() throws Exception {
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection(url + dataismi, userismi, sifre);
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    public void Kapa() throws Exception {
        connection.close();
    }




    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream, okay = "Okay", bagla = "Bagla", baglanmadi = "Baglanmadi";
        }
    }

    public void ListenThread() {
        Thread IncomingReader = new Thread(new Eniteframe.IncomingReader());
        IncomingReader.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("START");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Datacom question = new Datacom();
        question.setVisible(true);
        if (isConnected == false) {
            try {
                sc = new Socket(adres, port);
                InputStreamReader streamreader = new InputStreamReader(sc.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sc.getOutputStream());
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {

            }

        }
        dispose();
    
    }//GEN-LAST:event_jButton1ActionPerformed

/**
 * @param args the command line arguments
 */
public static void main(String args[])  throws ClassNotFoundException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eniteframe().setVisible(true);
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

