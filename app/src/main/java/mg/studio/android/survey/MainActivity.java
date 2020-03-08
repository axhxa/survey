package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        c=findViewById(R.id.box);
    }

    public void onClickBtn(View view){
        if(c.isChecked()) {
            Intent intent = new Intent(this,Text.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Need to agree the requests",Toast.LENGTH_LONG).show();
    }
}
