package ir.keifiat.BookReader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hossein on 8/9/15.
 */
public class SurveyActivity extends Activity {
    private Intent currentIntent;
    private int qos = 100;

    private View.OnClickListener finishButtonListener;
    private Button finishButton;
    private RatingBar rating;
    private TextView ratingText;

    private ScrollView scroll;

    private RadioGroup genderRadioGroup;
    private RadioGroup badThing;
    private RadioButton genderRadio;
    private RadioButton badThingRadio;
    private ListView list;
    private View.OnClickListener droidTapListener;
    private AdapterView.OnItemClickListener listTapListener;

    ArrayList<String> listItems=new ArrayList<String>();

    ArrayAdapter<String> adapter;
    private EditText loc;
    private TextView locText;
    private String currentDateAndTime;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        new LanguageHelper(getBaseContext());

        setContentView(R.layout.survey);

        initializeApp();

        if(savedInstanceState != null) {
            qos = savedInstanceState.getInt("qos");
            updateView();
        }

    }

    private void updateView() {
    }

    // @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("qos", qos);
    }

    private void initializeApp() {

        rating=(RatingBar)findViewById(R.id.ratingBar);
        ratingText=(TextView)findViewById(R.id.ratingText);

        scroll=(ScrollView)findViewById(R.id.scroll);
        loc=(EditText)findViewById(R.id.newLocation);
        locText=(TextView)findViewById(R.id.locationText);

        finishButton =(Button)findViewById(R.id.finishSurveyButton);

        finishButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                finishSurvey();
            }
        };

        finishButton.setOnClickListener(finishButtonListener);

        list=(ListView)findViewById(R.id.previousLocation);

        listTapListener = new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = ((TextView)view).getText().toString();

                selectLocation(item);

            }
        };


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listItems);

        adapter.add(getResources().getString(R.string.homeLocation));
        adapter.add(getResources().getString(R.string.workLocation));
        adapter.add(getResources().getString(R.string.carLocation));
        adapter.add(getResources().getString(R.string.metroLocation));
        adapter.add(getResources().getString(R.string.busLocation));

        list.setAdapter(adapter);

        list.setOnItemClickListener(listTapListener);

    }

    private void selectLocation(String item) {
        loc.setText(item);
    }

    private void finishSurvey() {

        if(!validate())
            return;

        currentIntent = getIntent();

        // returning values
        currentIntent.putExtra("qoe", 5);
        setResult(Activity.RESULT_OK, currentIntent);

        genderRadioGroup =(RadioGroup)findViewById(R.id.radioGender);
        genderRadio =(RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        currentDateAndTime = sdf.format(new Date());

/*      // Using Browser

        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://keifiat.ir/send.php?data=qoeSurvey,"
                        +","+rating.getRating()
                        +","+genderRadio.getText()
                        +","+badThingRadio.getText()
                        +","+currentDateAndTime));
        startActivity(i);
        */

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    BufferedReader in = null;
                    String data = null;

                    try{
                        HttpClient httpclient = new DefaultHttpClient();

                        HttpGet request = new HttpGet();
                        URI url = new URI("http://keifiat.ir/send.php?data=qoeSurvey,"+
                                URLEncoder.encode("," + rating.getRating()
                                        + "," + genderRadio.getText()
                                        + "," + currentDateAndTime, "UTF-8"));
                        request.setURI(url);
                        HttpResponse response = httpclient.execute(request);
                        in = new BufferedReader(new InputStreamReader(
                                response.getEntity().getContent()));

                        String line = in.readLine();
                        Toast.makeText(getApplicationContext(), "Your survey has been successfully submitted.",
                                Toast.LENGTH_LONG).show();

                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Error in submitting your survey!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        thread.start();

    }

    private boolean validate() {
        boolean valid = true;
        if(rating.getRating() == 0) {
            scroll.scrollTo(0,0);
            ratingText.setError("Please rate!");
            ratingText.requestFocusFromTouch();
            ratingText.setTextColor(Color.RED);
            valid=false;
        }

        if(loc.getText().length()==0) {
            scroll.scrollTo(0,0);
            locText.setError("Please enter your location");
            locText.setTextColor(Color.RED);
            valid=false;
        }

        return valid;
    }
}
