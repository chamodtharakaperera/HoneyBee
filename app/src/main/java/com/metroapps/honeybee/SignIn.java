package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignIn extends AppCompatActivity {

    private TextView singin;
    private EditText uname, pass;
    private Button login;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String connectionURL = null;
    Connection connection = null;
    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();



        singin = findViewById( R.id.txt_singup );
        uname = findViewById( R.id.txtUName );
        pass = findViewById( R.id.txtPwd );
        login = findViewById( R.id.btn_signIn );

        singin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( getBaseContext(), SignUp.class );
                startActivity( a );
            }
        } );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pw= null;
                String mail = uname.getText().toString();

                if(uname.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Email-address",Toast.LENGTH_SHORT).show();
                }
                else if(pass.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (uname.getText().toString().trim().matches(emailPattern))
                    {
                        try
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            int type = 0;
                            String email = null, password = null;
                            int k=0;
                            String username = uname.getText().toString();

                            Class.forName("net.sourceforge.jtds.jdbc.Driver");
                            connectionURL = "jdbc:jtds:sqlserver://nibm.database.windows.net:1433;DatabaseName=pepperdb;user=adminuser@nibm;password=pass@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                            con = DriverManager.getConnection( connectionURL );

                            String query = "select * from SingUp where uemail = '" + username + "' ";

                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery( query );
                            while(rs.next())
                            {
                                email = rs.getString("uemail");
                                password = rs.getString( "upassword");
                                type = rs.getInt( "utype" );
                                k = 1;
                            }

                            if(k==1)
                            {
                                if(password.equals(pass.getText().toString()))
                                {
                                    Toast.makeText(getApplicationContext(), "Access Granted", Toast.LENGTH_LONG).show();

                                    /*Intent d = new Intent(getApplicationContext(), MainActivity.class);
                                    d.putExtra("email", mail);
                                    startActivity(d);*/
                                    if(type == 1)
                                    {
                                        Toast.makeText(getApplicationContext(), "Welcome User", Toast.LENGTH_LONG).show();
                                    }
                                    else if(type == 2)
                                    {
                                        Toast.makeText(getApplicationContext(), "Welcome Admin", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_LONG).show();
                            }

                        }
                        catch(SQLException e1)
                        {
                            singin.setText( "SQL Database Error");
                        }
                        catch(Exception e)
                        {
                            singin.setText( "Unknown Error(s)" );
                            Log.e("MYAPP", "exception", e);
                        }

                    }
                }
            }
        });

    }
}