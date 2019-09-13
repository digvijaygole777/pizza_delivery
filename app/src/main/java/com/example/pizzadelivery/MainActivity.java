package com.example.pizzadelivery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addTopping,clearPizza,checkout;
    ProgressBar progressBar;
    LinearLayout linearlayout,linearLayout2;

    Integer images[]={R.drawable.bacon,R.drawable.cheese,R.drawable.garlic,R.drawable.green_pepper,R.drawable.mashroom,R.drawable.olive,R.drawable.onion,R.drawable.red_pepper};
    String items[]={"bacon","cheese","garlic","green_pepper","mashroom","olive","onion","red_pepper"};
    String[] selected_items=new String[10];
    ArrayList<String> toppings_list=new ArrayList<>();

    int progress=0;
    int i=0;
    double baseprice=6.5;
    double topping_price;
    double delivery_cost=0;
    double total_cost=0;
    static String REQ_CODE="order";
    String[] topping_selected=null;
    CheckBox delivery;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearlayout=(LinearLayout)findViewById(R.id.linear_layout1);
        linearLayout2=(LinearLayout)findViewById(R.id.linear_layout2);
        addTopping=(Button)findViewById(R.id.addTopping);
        clearPizza=(Button)findViewById(R.id.clearpizza);
        checkout=(Button)findViewById(R.id.checkout);
        progressBar=(ProgressBar)findViewById(R.id.progressBar3);
        delivery=(CheckBox)findViewById(R.id.deliverycheckbox);
        progressBar.setMin(0);
        progressBar.setMax(100);



        delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    delivery_cost=4.0;
                }else{
                    delivery_cost=0;
                }
                Log.i("delivery", String.valueOf(delivery_cost));
            }
        });

        clearPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_cost=delivery_cost;
                progress=0;
                progressBar.setProgress(0);
                topping_price=0;
               linearlayout.removeAllViews();
               linearLayout2.removeAllViews();
            }
        });


        addTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topping_price +=1.5;
                if(progress<100){
                    final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Choose a Topping")
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progress += 10;

                                    ImageView imageView = new ImageView(linearlayout.getContext());
                                    if (linearLayout2.getChildCount() < 5) {
                                        imageView.setImageResource(images[which]);
                                        linearLayout2.addView(imageView);
                                        progressBar.setProgress(progress);
                                    } else if (linearlayout.getChildCount() < 5) {
                                        imageView.setImageResource(images[which]);
                                        linearlayout.addView(imageView);
                                        progressBar.setProgress(progress);
                                    }
                                  toppings_list.add(items[which]);
                                }
                            });
                    AlertDialog alert=builder.create();
                    alert.show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Maximum Topping capacity reached!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_cost=baseprice+delivery_cost+topping_price;

                Order order=new Order(baseprice,topping_price,toppings_list,delivery_cost,total_cost);

                Intent intent=new Intent(MainActivity.this,Order_Activity.class);
                intent.putExtra(REQ_CODE,order);
                startActivity(intent);


//                Log.i("totalcost", String.valueOf(total_cost));
//                Log.i("items selected",toppings_list.toString());
            }
        });
    }
}
