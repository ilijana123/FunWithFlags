package ilijana.example.funwithflags;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    int numberQuestions=-1;
    int currentQuestion=-1;
    int trueAnswer=-1;
    int correctAnswers=0;
    String [] countries=null;
    TypedArray imgs=null;
    RadioButton radio1,radio2,radio3,radio4;
    int p=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    protected void onStart() {
        super.onStart();
        countries=getResources().getStringArray(R.array.countries);
        imgs=getResources().obtainTypedArray(R.array.images);
        Intent intent = getIntent();
        if(intent.getStringExtra("name").equals("easy")) {
            numberQuestions=5;
        } else if(intent.getStringExtra("name").equals("medium")) {
            numberQuestions=10;
        } else if(intent.getStringExtra("name").equals("hard")) {
            numberQuestions=15;
        }
        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
        radio3=findViewById(R.id.radio3);
        radio4=findViewById(R.id.radio4);
        drawQuestion(p);
    }
    public void drawQuestion(int p) {
        Random rnd = new Random();
        int broj = rnd.nextInt(100);
        int i = imgs.getResourceId(broj, -1);
        ImageView imageView = findViewById(R.id.flagImage);
        imageView.setImageResource(i);
        TextView subtitle = findViewById(R.id.subtitle);
        String formattedText = getString(R.string.question, p + 1, numberQuestions);
        radio2.setText(countries[broj]);
        radio1.setText(countries[rnd.nextInt(100)]);
        radio3.setText(countries[rnd.nextInt(100)]);
        radio4.setText(countries[rnd.nextInt(100)]);
        radio1.setId(Integer.parseInt("2"));
        radio2.setId(Integer.parseInt("1"));
        radio3.setId(Integer.parseInt("3"));
        radio4.setId(Integer.parseInt("4"));

        RadioGroup group = (RadioGroup) findViewById(R.id.radiogroup);
        List<RadioButton> buttons = new ArrayList<>();
        for (int j = 0; j < group.getChildCount(); j++) {
            View child = group.getChildAt(j);
            if (child instanceof RadioButton) {
                buttons.add((RadioButton) child);
            }
        }
        Collections.shuffle(buttons);
        group.removeAllViews();
        for (RadioButton button : buttons) {
            group.addView(button);
        }
    }
        public void radioClick(View view) {
            RadioGroup group=(RadioGroup) findViewById(R.id.radiogroup);
            int id=group.getCheckedRadioButtonId();
            if(id==1) {
                Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
                correctAnswers++;
            } else {
                Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
            }
            group.clearCheck();
            p++;
            if(p==numberQuestions) {
                Intent intent=new Intent(QuizActivity.this,ResultsActivity.class);
                intent.putExtra("correctAnswers",String.valueOf(correctAnswers));
                intent.putExtra("numberOfQuestions",String.valueOf(numberQuestions));
                startActivity(intent);
            } else {
                drawQuestion(p);
            }
    }
}