package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private Button _ok_button1,_ok_button2;
    private TextView output_TextView1,output_TextView2;
    private EditText input_TextBox1,input_TextBox2;
    //private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _ok_button1=(Button) findViewById(R.id.button1);
        input_TextBox1=(EditText) findViewById(R.id.editText1);
        output_TextView1=findViewById(R.id.output1);

        _ok_button2=(Button) findViewById(R.id.button2);
        input_TextBox2=(EditText) findViewById(R.id.editText2);
        output_TextView2=findViewById(R.id.output2);
        //_ok_button1.setOnClickListener(this);
        MobileAds.initialize(this, "ca-app-pub-9946024219063334~1363592914");
        AdView mAdview=(AdView) findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdview.loadAd(adRequest);

    }
    //encode message to ASCII code plus '#'
    public String encode(String text){
        String new_str="";
        for (char ch: text.toCharArray()) {
            new_str+=Integer.toString(((int)ch)*2-1);
            new_str+='#';
        }
        return new_str;
    }
    //decode ASCII code to string
    public String decode(String text){
        String new_str="",num="";
        int n=text.length();
        for(int i=0;i<n;i++){
            if(text.charAt(i)>='0'&& text.charAt(i)<='9'){
                num+=text.charAt(i);
            }
            else{
                new_str+=(char)((Integer.valueOf(num)+1)/2);
                num="";
            }
        }
        return new_str;
    }

    //encode button
    public void onClick1(View v){
        String text;
        if(v.getId()==_ok_button1.getId()){
            text=input_TextBox1.getText().toString();
            text=encode(text);
            output_TextView1.setText(text);
        }
    }

    //decode button
    public void onClick2(View v){
        String text;
        if(v.getId()==_ok_button2.getId()){
            text=input_TextBox2.getText().toString();
            text=decode(text);
            output_TextView2.setText(text);
        }
    }
}
