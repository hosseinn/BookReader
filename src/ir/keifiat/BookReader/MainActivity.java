package ir.keifiat.BookReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView message;
    private LinearLayout surveyTouch;

    private ListView list;

    private View.OnClickListener droidTapListener;
    private ListView.OnItemClickListener listTapListner;

    private int pageNumber = 0;

    private int InternalRequestCode;

    public MainActivity() {
        InternalRequestCode = 1;
    }


    ArrayList<String> listItems=new ArrayList<String>();

    ArrayAdapter<String> adapter;

    /**
     * Called when the activity is first created.
     */
    // @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        new LanguageHelper(getBaseContext());

        setContentView(R.layout.main_menu);

        initializeApp();

        // not introduced before

        if(savedInstanceState != null) {
            pageNumber = savedInstanceState.getInt("pageNumber");
            updateView();
        }
        else {
            doIntroduction();
        }
    }

    // @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("pageNumber", pageNumber);
    }

    private void initializeApp() {

        new LanguageHelper(getBaseContext());

        message=(TextView)findViewById(R.id.message);

        surveyTouch=(LinearLayout)findViewById(R.id.opinionLayout);

        droidTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                doSurvey();
            }
        };

        surveyTouch.setOnClickListener(droidTapListener);

        list=(ListView)findViewById(R.id.listView);

        listTapListner = new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = ((TextView)view).getText().toString();

                readBook(item);

            }
        };


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        adapter.add("مطلب اول");
        adapter.add("مطلب دوم");
        adapter.add("مطلب سوم");
        adapter.add("مطلب چهارم");

        list.setAdapter(adapter);

        list.setOnItemClickListener(listTapListner);
    }

    private void doIntroduction() {

        Intent i = new Intent(this, IntroductionActivity.class);

        i.putExtra("intro", 1);

        startActivityForResult(i, InternalRequestCode);

    }

    private void doSurvey() {

        Intent i = new Intent(this, SurveyActivity.class);

        i.putExtra("qos", 5);

        startActivityForResult(i, InternalRequestCode);

    }

    private void updateView() {
        if(pageNumber == 0) return;
        String temp = Integer.toString(pageNumber);
    }

    private void readBook(String bookName) {
        // jump to another activity
        Intent i = new Intent(this, ReadBookActivity.class);

        i.putExtra("bookName", bookName);

        // startActivity(i);
        startActivityForResult(i, InternalRequestCode);
    }
}
