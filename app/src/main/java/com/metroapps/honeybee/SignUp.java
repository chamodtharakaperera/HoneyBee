package com.metroapps.honeybee;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp extends AppCompatActivity {

    private TextView em, no, pw, cpw, reg;
    private Button signup;

    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String connectionURL = null;
    Connection connection = null;
    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        em = findViewById(R.id.txtUName);
        no = findViewById(R.id.txtUName3);
        pw = findViewById(R.id.txtUName4);
        cpw = findViewById(R.id.txtUName5);
        reg = findViewById(R.id.txtsignuplink2);
        signup = findViewById(R.id.button3);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bl2 = new Intent(getApplicationContext(), SignIn.class);
                startActivity(bl2);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (em.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Email-address", Toast.LENGTH_SHORT).show();
                } else if (no.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Mobile no", Toast.LENGTH_SHORT).show();
                } else if (pw.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (cpw.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Re-enter Password", Toast.LENGTH_SHORT).show();
                } else if (!((pw.getText().toString()).equals(cpw.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Passwords mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        int k = 0;
                        String username = em.getText().toString();

                        Class.forName("net.sourceforge.jtds.jdbc.Driver");
                        connectionURL = "jdbc:jtds:sqlserver://nibm.database.windows.net:1433;DatabaseName=pepperdb;user=adminuser@nibm;password=pass@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                        con = DriverManager.getConnection(connectionURL);

                        String query2 = "select  * from SingUp where uemail = '" + username + "' ";

                        Statement st2 = con.createStatement();
                        ResultSet rs2 = st2.executeQuery(query2);
                        while (rs2.next()) {
                            k = 1;

                        }
                        //con.close();

                        int tp = Integer.parseInt(no.getText().toString());

                        if (k == 1) {
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        } else if (k == 0) {
                            String query3 = "insert into Singup(uemail, upassword, mobileno) values('" + username + "','" + pw.getText().toString() + "','" + tp + "')";
                            Statement st3 = con.createStatement();
                            st3.executeUpdate(query3);
                            //con.close();

                            /*Intent bl = new Intent(getApplicationContext(), SignIn.class);
                            startActivity(bl);*/
                        }

                        con.close();
                    } catch (SQLException e1) {
                        Log.e("MYAPP", "exception", e1);
                    } catch (Exception e) {
                        Log.e("MYAPP", "exception", e);
                    }
                }
            }
        });


    }

    public void loadSignin(View view) {
        Intent a = new Intent(getBaseContext(), SignIn.class);
        startActivity(a);
    }
}