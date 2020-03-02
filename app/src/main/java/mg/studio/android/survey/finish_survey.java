package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class finish_survey extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_survey);
        bundle = this.getIntent().getExtras();
    }

    public void onClickBtn(View view){
            Intent intent = new Intent(this, Report.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
