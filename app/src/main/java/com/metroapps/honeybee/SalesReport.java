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
import com.anychart.charts.Pie;
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
import java.util.List;

public class SalesReport extends AppCompatActivity {

    public Connection con;
    private TextView heading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sales_report );

        String connectionURL = null;
        Connection connection = null;

        Intent rep2 = getIntent();
        String year = rep2.getStringExtra( "salesyr" );


        heading = findViewById( R.id.topic );
        heading.setText( "Annual Sales Report " + year );

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            int[] amt = new int[12];
            String[] month = new String[12];
            int no=0;

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://nibm.database.windows.net:1433;DatabaseName=pepperdb;user=adminuser@nibm;password=pass@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            con = DriverManager.getConnection( connectionURL );


            String query = "select mn.MName, coalesce(a.Amount,0) as Amount from (select month(Orderdate) as MonthNo, sum(Amount) as Amount from Orders where year(OrderDate)='" + year +"' group by month(Orderdate)) a full join MonthVal mn on a.MonthNo = mn.MNo order by mn.MNo";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( query );
            while(rs.next())
            {
                amt[no] = rs.getInt("Amount");
                month[no] = rs.getString( "MName");
                no++;
            }

            final AnyChartView view1 = findViewById( R.id.pieChart );
            APIlib.getInstance().setActiveAnyChartView(view1);
            final Pie pie = AnyChart.pie();

            ArrayList<DataEntry> dataEntries = new ArrayList<>();

            int q1_tot=0, q2_tot=0, q3_tot=0, q4_tot  =0;

            q1_tot = amt[0] + amt[1] + amt[2];
            q2_tot = amt[3] + amt[4] + amt[5];
            q3_tot = amt[6] + amt[7] + amt[8];
            q4_tot = amt[9] + amt[10] + amt[11];

            dataEntries.add(new ValueDataEntry( 1, q1_tot ) );
            dataEntries.add(new ValueDataEntry( 2, q2_tot ) );
            dataEntries.add(new ValueDataEntry( 3, q3_tot ) );
            dataEntries.add(new ValueDataEntry( 4, q4_tot ) );

            pie.title("Quarterly Sales");

            pie.data(dataEntries);
            view1.setChart(pie);

            AnyChartView anyChartView = findViewById(R.id.barChart);
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            Cartesian cartesian = AnyChart.column();

            ArrayList<DataEntry> data = new ArrayList<>();
            for (int i=0; i<12; i++)
            {
                data.add(new ValueDataEntry( i+1, amt[i] ) );
            }

            Column column = cartesian.column(data);

            column.tooltip()
                    .titleFormat("{%X}")
                    .position( Position.CENTER_BOTTOM)
                    .anchor( Anchor.CENTER_BOTTOM)
                    .offsetX(0d)
                    .offsetY(5d)
                    .format("${%Value}{groupsSeparator: }");

            cartesian.animation(true);
            cartesian.title("Monthly Sales");

            cartesian.yScale().minimum(0d);

            cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

            cartesian.tooltip().positionMode( TooltipPositionMode.POINT);
            cartesian.interactivity().hoverMode( HoverMode.BY_X);

            cartesian.xAxis(0).title("Month");
            cartesian.yAxis(0).title("Sales");

            anyChartView.setChart(cartesian);

            con.close();

        }

        catch(SQLException e1)
        {
            //txt.setText( "SQL Database Error");
        }
        catch(Exception e)
        {
            //txt.setText( "Unknown Error(s)");
        }
    }
}