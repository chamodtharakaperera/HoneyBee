package com.metroapps.honeybee;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.metroapps.honeybee.ui.AdminHome;
import com.metroapps.honeybee.ui.FoodTypeInserter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignIn extends AppCompatActivity {

    private TextView singin;
    private EditText uname, pass;
    private Button login;
    private ImageButton userloc;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String connectionURL = null;
    Connection connection = null;
    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //getSupportActionBar().hide();


        singin = findViewById(R.id.txt_singup);
        uname = findViewById(R.id.txtUName);
        pass = findViewById(R.id.txtPwd);
        login = findViewById(R.id.btn_signIn);
        userloc = findViewById(R.id.btnloc);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getBaseContext(), SignUp.class);
                startActivity(a);
            }
        });

        userloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(b);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pw = null;
                String mail = uname.getText().toString();

                if (uname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Email-address", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (uname.getText().toString().trim().matches(emailPattern)) {
                        try {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            int type = 0;
                            String email = null, password = null;
                            int k = 0;
                            String username = uname.getText().toString();

                            Class.forName("net.sourceforge.jtds.jdbc.Driver");
                            connectionURL = "jdbc:jtds:sqlserver://nibm.database.windows.net:1433;DatabaseName=pepperdb;user=adminuser@nibm;password=pass@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                            con = DriverManager.getConnection(connectionURL);

                            String query = "select * from SingUp where uemail = '" + username + "' ";

                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery(query);
                            while (rs.next()) {
                                email = rs.getString("uemail");
                                password = rs.getString("upassword");
                                type = rs.getInt("utype");
                                k = 1;
                            }

                            if (k == 1) {
                                if (password.equals(pass.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "Access Granted", Toast.LENGTH_LONG).show();

                                    if (type == 1) {
                                        //Toast.makeText(getApplicationContext(), "Welcome User", Toast.LENGTH_LONG).show();
                                        Intent d = new Intent(getApplicationContext(), MainActivity.class);
                                        d.putExtra("nuser", username);
                                        startActivity(d);
                                    } else if (type == 2) {
                                        //Toast.makeText(getApplicationContext(), "Welcome Admin", Toast.LENGTH_LONG).show();
                                        Intent e = new Intent(getApplicationContext(), AdminHome.class);
                                        e.putExtra("nuser2", username);
                                        startActivity(e);
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_LONG).show();
                            }

                        } catch (SQLException e1) {
                            Log.e("MYAPP", "exception", e1);
                        } catch (Exception e) {
                            Log.e("MYAPP", "exception", e);
                        }

                    }
                }
            }
        });

    }
}