package ilijana.example.funwithflags;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultsActivity extends AppCompatActivity {
    TextView playerTitle, playerResults;
    Button playAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        int numberOfQuestions=Integer.parseInt(getIntent().getStringExtra("numberOfQuestions"));
        int correctAnswers=Integer.parseInt(getIntent().getStringExtra("correctAnswers"));
        float percentage=(float) correctAnswers/ (float) numberOfQuestions;
        playerTitle=findViewById(R.id.playerTitle);
        if (percentage >= 0.8) {
            playerTitle.setText(getString(R.string.flag_guru));
        } else if (percentage >= 0.6 && percentage < 0.8) {
            playerTitle.setText(getString(R.string.very_good));
        } else if (percentage >= 0.4 && percentage < 0.6) {
            playerTitle.setText(getString(R.string.good));
        } else if (percentage >= 0.2 && percentage < 0.4) {
            playerTitle.setText(getString(R.string.bad));
        } else if (percentage < 0.2) {
            playerTitle.setText(getString(R.string.flag_flaw));
        }
        playerResults=findViewById(R.id.playerResults);
        playerResults.setText(getString(R.string.correct_answers,correctAnswers,numberOfQuestions,percentage));

    }
    public void playAgain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}