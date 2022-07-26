package jp.suntech.s21009.bmicalculators009;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HelloListener listener = new HelloListener();//リスナの生成

        Button btClick= findViewById(R.id.btcalculate);//確定ボタン
        btClick.setOnClickListener(listener);//リスナ指定

        Button btClear= findViewById(R.id.btclear);//クリアボタン
        btClear.setOnClickListener(listener);//リスナ指定
    }
    //listenner Class <= イベントの監視
    private class HelloListener implements View.OnClickListener{
        //eventHandler <=イベント時の処理
        @Override
        public void onClick(View view) {
            EditText input_age = findViewById(R.id.etAge);
            EditText input_height = findViewById(R.id.etHeight);
            EditText input_weight = findViewById(R.id.etWeight);
            OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();

            String age_str = input_age.getText().toString();
            String height_str = input_height.getText().toString();
            String weight_str = input_weight.getText().toString();

            int age = 0;
            float height = 0;
            float weight = 0;



//
            //画面上部の出力制御用
            TextView DegreeOfObesity = findViewById(R.id.tvDoO);
            TextView output = findViewById(R.id.tvout_str);//体重評価
            TextView AppropriateWeight = findViewById(R.id.tvAW);
            TextView output_weight = findViewById(R.id.tvoutfloat);//適正体重
            TextView Unit = findViewById(R.id.tvunit4);//


            int id = view.getId();
            switch(id){
                case R.id.btcalculate:
                    if(TextUtils.isEmpty(age_str)||TextUtils.isEmpty(height_str)||TextUtils.isEmpty(weight_str)){
                        Toast.makeText(MainActivity.this,R.string.keikoku,Toast.LENGTH_LONG).show();
                        break;
                    }
                    else{
                        age = Integer.parseInt(age_str);
                        height = Float.parseFloat(height_str);
                        weight = Float.parseFloat(weight_str);
                    }
                    DegreeOfObesity.setText(getString(R.string.tv_DoO));
                    AppropriateWeight.setText(getString(R.string.tv_AW));
                    Unit.setText(getString(R.string.unit_of_weight));

                    if(age>=16){
                        float BMI = weight/(height/100)/(height/100);//BMI
                        if(BMI>=40){
                            output.setText(getString(R.string.Bmi1));//体重評価を返却
                            output.setTextColor(Color.RED);
                        }
                        else if(BMI>=35){
                            output.setText(getString(R.string.Bmi2));//体重評価を返却
                            output.setTextColor(Color.parseColor("#CC6666"));
                        }
                        else if(BMI>=30){
                            output.setText(getString(R.string.Bmi3));//体重評価を返却
                            output.setTextColor(Color.parseColor("#993399"));
                        }
                        else if(BMI>=25){
                            output.setText(getString(R.string.Bmi4));//体重評価を返却
                            output.setTextColor(Color.parseColor("#6633CC"));
                        }
                        else if(BMI>=18.5){
                            output.setText(getString(R.string.Bmi5));//体重評価を返却
                            output.setTextColor(Color.BLUE);
                        }
                        else{
                            output.setText(getString(R.string.Bmi6));//体重評価を返却
                            output.setTextColor(Color.YELLOW);
                        }
                        float Rweight=22*(height/100)*(height/100);//返却する適正体重
                        output_weight.setText(String.format("%.1f",Rweight));//適正体重を返却
                    }else if(age>=6){
                        dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                        float Rohrer = weight/(height/100)/(height/100)/(height/100)*10;//ローレル指数
                        if(Rohrer>=160){
                            output.setText(R.string.Rohrer1);//体重評価を返却
                            output.setTextColor(Color.RED);
                        }
                        else if(Rohrer>=145){
                            output.setText(R.string.Rohrer2);//体重評価を返却
                            output.setTextColor(Color.parseColor("#993399"));
                        }
                        else if(Rohrer>=115){
                            output.setText(R.string.Rohrer3);//体重評価を返却
                            output.setTextColor(Color.BLUE);
                        }
                        else if(Rohrer>=100){
                            output.setText(R.string.Rohrer4);//体重評価を返却
                            output.setTextColor(Color.parseColor("#AAAA33"));
                        }
                        else{
                            output.setText(R.string.Rohrer5);//体重評価を返却
                            output.setTextColor(Color.YELLOW);
                        }
                        float Rweight=130*(height/100)*(height/100)*(height/100)/10;//返却する適正体重
                        output_weight.setText(String.format("%.1f",Rweight));//適正体重を返却
                    }else{
                        dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                        float Kaup = weight/(height/100)/(height/100);//カウプ指数
                        if(Kaup>=20){
                            output.setText(R.string.Kaup1);//体重評価を返却
                            output.setTextColor(Color.RED);
                        }
                        else if(Kaup>=18){
                            output.setText(R.string.Kaup2);//体重評価を返却
                            output.setTextColor(Color.parseColor("#993399"));
                        }
                        else if(Kaup>=15){
                            output.setText(R.string.Kaup3);//体重評価を返却
                            output.setTextColor(Color.BLUE);
                        }
                        else if(Kaup>=13){
                            output.setText(R.string.Kaup4);//体重評価を返却
                            output.setTextColor(Color.parseColor("#AAAA33"));
                        }
                        else{
                            output.setText(R.string.Kaup5);//体重評価を返却
                            output.setTextColor(Color.YELLOW);
                        }
                        float Rweight=16*(height/100)*(height/100);//返却する適正体重
                        output_weight.setText(String.format("%.1f",Rweight));//適正体重を返却
                    }
                    break;
                case R.id.btclear:
                    DegreeOfObesity.setText("");
                    output.setText("");
                    AppropriateWeight.setText("");
                    output_weight.setText("");
                    Unit.setText("");


                    input_age.setText("");
                    input_height.setText("");
                    input_weight.setText("");
                    break;
            }
        }
    }
}