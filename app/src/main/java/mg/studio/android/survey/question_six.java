package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class question_six extends AppCompatActivity {

    TextView t;
    String str;
    EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_six);
        edittext=findViewById(R.id.edittext1);
        t=findViewById(R.id.text2);
        Bundle bundle = this.getIntent().getExtras();
        str=bundle.getString("")+"Q6."+t.getText().toString()+"\n";
    }

    public void onClickBtn(View view){
        if(!edittext.getText().toString().isEmpty()) {
            Intent intent = new Intent(this, question_seven.class);
            Bundle bundle=new Bundle();
            bundle.putString("",str+"A:"+edittext.getText().toString()+"\n");
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Need choice",Toast.LENGTH_LONG).show();
    }
}