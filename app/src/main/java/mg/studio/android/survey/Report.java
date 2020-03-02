package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Report extends AppCompatActivity {

    TextView t;
    String result;
    String filePath;
    Context context;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,	Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        t=findViewById(R.id.text);
        Bundle bundle=this.getIntent().getExtras();
        result=bundle.getString("");
        t.setText(result);
        context = getApplicationContext();
    }


    public void onClickBtn(View view){
        try{
            filePath = context.getFilesDir().getPath()+"/report.json";
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            System.out.println(filePath);
            JsonWriter writer=new JsonWriter(new OutputStreamWriter(fileOutputStream));
            writer.beginObject();
            writer.name("report").value(result);
            writer.endObject();
            writer.close();
            /*FileInputStream fileInputStream=openFileInput("report.json");
            JsonReader reader=new JsonReader(new InputStreamReader(fileInputStream));
            reader.beginObject();
            while(reader.hasNext()) {
                if(reader.nextName().equals("report"))
                System.out.println(reader.nextString());
            }
            reader.endObject();
            reader.close();*/
            int permission = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
            filePath = context.getExternalFilesDir(null).getPath()+"/report.json";
            System.out.println(filePath);
            FileOutputStream fileOutputStream1=new FileOutputStream(filePath);
            JsonWriter writer1=new JsonWriter(new OutputStreamWriter(fileOutputStream1));
            writer1.beginObject();
            writer1.name("report").value(result);
            writer1.endObject();
            writer1.close();
            /*FileInputStream fileInputStream1=new FileInputStream(filePath);
            JsonReader reader1=new JsonReader(new InputStreamReader(fileInputStream1));
            reader1.beginObject();
            while(reader1.hasNext()) {
                if(reader1.nextName().equals("report"))
                    System.out.println(reader1.nextString());
            }
            reader1.endObject();
            reader1.close();*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
