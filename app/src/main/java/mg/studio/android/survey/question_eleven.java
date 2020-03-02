package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class question_eleven extends AppCompatActivity {

    TextView t;
    String str;
    CheckBox box1,box2,box3,box4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_eleven);
        box1=findViewById(R.id.cbox1);
        box2=findViewById(R.id.cbox2);
        t=findViewById(R.id.text2);
        Bundle bundle = this.getIntent().getExtras();
        str=bundle.getString("")+"Q11."+t.getText().toString()+"\n";
    }

    public void onClickBtn(View view){
        if((box1.isChecked())||(box2.isChecked())) {
            Intent intent = new Intent(this, question_twelve.class);
            Bundle bundle=new Bundle();

            if(box1.isChecked()){
                bundle.putString("",str+"A:"+box1.getText().toString()+"\n");
            }
            if(box2.isChecked()){
                bundle.putString("",str+"A:"+box2.getText().toString()+"\n");
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Need choice",Toast.LENGTH_LONG).show();
    }
}
