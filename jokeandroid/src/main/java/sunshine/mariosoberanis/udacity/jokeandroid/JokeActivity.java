package sunshine.mariosoberanis.udacity.jokeandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "joke";

    public static Intent launchIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);

        String joke = getIntent().getStringExtra(JOKE);
        if(joke == null)
            joke = "no joki for yuki sorry :(";

        TextView textView = (TextView) findViewById(R.id.joke);
        textView.setText(joke);
    }
}
