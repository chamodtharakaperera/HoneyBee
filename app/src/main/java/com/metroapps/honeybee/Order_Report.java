package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Order_Report extends AppCompatActivity {

    public Connection con;
    private TextView heading, sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__report);

        String connectionURL = null;
        Connection connection = null;

        Intent rep2 = getIntent();
        String year = rep2.getStringExtra("salesyr2");


        heading = findViewById(R.id.topic1);
        sales = findViewById(R.id.totOrders);

        heading.setText("Annual Order Report " + year);


        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            int[] odr = new int[12];
            String[] month = new String[12];
            int no = 0;

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://nibm.database.windows.net:1433;DatabaseName=pepperdb;user=adminuser@nibm;password=pass@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            con = DriverManager.getConnection(connectionURL);


            String query = "select mn.MName, coalesce(a.OrderCount,0) as OrderCounut from (select month(Orderdate) as MonthNo, count(*) as OrderCount from Orders where year(OrderDate)='"+ year +"' group by month(Orderdate)) a full join MonthVal mn on a.MonthNo = mn.MNo order by mn.MNo;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                odr[no] = rs.getInt("OrderCounut");
                month[no] = rs.getString("MName");
                no++;
            }

            AnyChartView anyChartView = findViewById(R.id.barChart1);
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            Cartesian cartesian = AnyChart.column();

            ArrayList<DataEntry> data = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                data.add(new ValueDataEntry(i + 1, odr[i]));
            }

            Column column = cartesian.column(data);

            column.tooltip()
                    .titleFormat("{%X}")
                    .position(Position.CENTER_BOTTOM)
                    .anchor(Anchor.CENTER_BOTTOM)
                    .offsetX(0d)
                    .offsetY(5d)
                    .format("${%Value}{groupsSeparator: }");

            cartesian.animation(true);
            cartesian.title("Monthly Orders");

            cartesian.yScale().minimum(0d);

            cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
            cartesian.interactivity().hoverMode(HoverMode.BY_X);

            cartesian.xAxis(0).title("Month");
            cartesian.yAxis(0).title("Orders");

            anyChartView.setChart(cartesian);

            con.close();

            int totsales = 0;
            for (int i = 0; i < 12; i++) {
                totsales = totsales + odr[1];
            }
            sales.setText("Total Orders : " +totsales);


        } catch (SQLException e1) {
            //txt.setText( "SQL Database Error");
        } catch (Exception e) {
            //txt.setText( "Unknown Error(s)");
        }
    }
}