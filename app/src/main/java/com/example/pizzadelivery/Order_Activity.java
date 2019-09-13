package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Order_Activity extends AppCompatActivity {
    TextView basepriceView,toppings_total,toppingList,deliveryCost,totalCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_);
        basepriceView=(TextView)findViewById(R.id.basepriceView);
        toppings_total=(TextView)findViewById(R.id.toppingtotalView);
        toppingList=(TextView)findViewById(R.id.toppingslist);
        deliveryCost=(TextView)findViewById(R.id.deliverycostView);
        totalCost=(TextView)findViewById(R.id.totalView);



        if(getIntent()!=null && getIntent().getExtras()!=null){
            Order order= (Order) getIntent().getExtras().getSerializable(MainActivity.REQ_CODE);

            String alltopping_list= Arrays.toString(order.toppings.toArray()).replace("[","").replace("]","");

            basepriceView.setText(order.baseprice.toString());
            toppings_total.setText(order.toppingsprice.toString());
            toppingList.setText(alltopping_list);
            deliveryCost.setText(order.deliverycost.toString());
            totalCost.setText(order.totalCost.toString());


        }

    }
}
