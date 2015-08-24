package ir.keifiat.BookReader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by hossein on 8/16/15.
 */
public class IntroductionActivity extends Activity {

    private Intent currentIntent;
    private int intro = 0;

    private View.OnClickListener finishButtonListener;
    private Button finishButton;

    private View.OnClickListener changeLanguageButtonListener;
    private Button changeLanguageButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        new LanguageHelper(getBaseContext());

        setContentView(R.layout.introduction);

        initializeApp();

        if(savedInstanceState != null) {
            intro = savedInstanceState.getInt("intro");
            updateView();
        }

    }

    private void updateView() {
    }

    // @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("intro", 1);
    }

    private void initializeApp() {

        // finish intro

        finishButton =(Button)findViewById(R.id.finishIntroButton);

        finishButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                finishIntroduction();
            }
        };

        finishButton.setOnClickListener(finishButtonListener);

        // change language

        changeLanguageButton =(Button)findViewById(R.id.changeLanguageButton);

        changeLanguageButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                changeLanguage();
            }
        };

        changeLanguageButton.setOnClickListener(changeLanguageButtonListener);

    }

    private void changeLanguage() {
        String languageToLoad="en";
//        if(getResources().getConfiguration().locale.getDisplayName().contains("en_US"))
//          languageToLoad = "fa_IR";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
//        findViewById (R.id.mainLayout).invalidate();
//        setContentView(R.layout.introduction);
    }

    private void finishIntroduction() {

        currentIntent = getIntent();

        // returning values
        currentIntent.putExtra("intro", 1);
        setResult(Activity.RESULT_OK, currentIntent);

        this.finish();
    }
}
