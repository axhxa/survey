package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Text extends AppCompatActivity {
    LinearLayout layout;
    Context context;
    String report;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        context = getApplicationContext();
        layout = findViewById(R.id.mainLayout);
        /*AnalysisJson(context.getFilesDir().getPath()+"/survey.json");*/
        /*AnalysisJson("file:///android_asset/survey.json");*/
        AnalysisJson("survey.json");
        bundle=new Bundle();
    }

    public String getjson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = Text.this.getAssets();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line=bufferedReader.readLine() )!= null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void AnalysisJson(String fileName) {
        String text = getjson(fileName);
        System.out.println(text);

        try {
            JSONObject object = new JSONObject(text);
            JSONObject jsonObject = object.getJSONObject("survey");
            report="survey id="+jsonObject.getString("id")+"\n";
            /*for(int i=0;i<jsonArray.length();i++){//survey
                JSONObject jsonObject=jsonArray.getJSONObject(i);*/
            int len = jsonObject.getInt("len");
            JSONArray question = jsonObject.getJSONArray("questions");
            for (int j = 0; j < question.length(); j++) {//questions
                RadioGroup radioGroup = new RadioGroup(this);
                JSONObject jsonObject1 = question.getJSONObject(j);
                String question_text = String.valueOf(j + 1)+"." + jsonObject1.getString("question");
                report=report+"Question"+question_text+"\n";
                String type = jsonObject1.getString("type");
                TextView textView = new TextView(this);
                textView.setText(question_text);
                System.out.println(question_text);
                layout.addView(textView);
                if (type.equals("single")) {
                    layout.addView(radioGroup);
                }
                JSONArray option = jsonObject1.getJSONArray("options");
                for (int k = 0; k < option.length(); k++) {//options
                    JSONObject jsonObject11 = option.getJSONObject(k);
                    String str = jsonObject11.getString(String.valueOf(k + 1));
                    if (type.equals("single")) {
                        final RadioButton button = new RadioButton(this);
                        button.setText(str);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                report=report+"Answer."+button.getText().toString()+"\n";

                            }
                        });
                        radioGroup.addView(button);
                    } else {
                        CheckBox box = new CheckBox(this);
                        box.setText(str);
                        report=report+box.getText().toString()+"\n";
                        layout.addView(box);
                    }
                }
            }
            final Button next_button=new Button(this);
            next_button.setText("Show report");
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Report.class);
                    System.out.println("report="+report);
                    bundle.putString("",report);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            layout.addView(next_button);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

