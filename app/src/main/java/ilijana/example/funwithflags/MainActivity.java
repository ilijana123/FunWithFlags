package ilijana.example.funwithflags;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button easyButton, mediumButton, hardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        easyButton=findViewById(R.id.easyButton);
        mediumButton=findViewById(R.id.mediumButton);
        hardButton=findViewById(R.id.hardButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyClicked(view);
            }
        });
        mediumButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MediumClicked(view);
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                HardClicked(view);
            }
        });
    }
    public void EasyClicked(View view) {
        Intent intent=new Intent(MainActivity.this,
                QuizActivity.class);
        intent.putExtra("name","easy");
        startActivity(intent);
    }
    public void MediumClicked(View view) {
        Intent intent=new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("name","medium");
        startActivity(intent);
    }
    public void HardClicked(View view) {
        Intent intent=new Intent(MainActivity.this,QuizActivity.class);
        intent.putExtra("name","hard");
        startActivity(intent);
    }
}