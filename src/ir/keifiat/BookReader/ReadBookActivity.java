package ir.keifiat.BookReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hossein on 8/4/15.
 */
public class ReadBookActivity extends Activity {

    private int firstVisibleLineNumber = 0;
    private int lastVisibleLineNumber = 0;
    private int pageNumber = 0;

    private TextView message;
    private TextView contents;

    private Intent currentIntent;
    private String bookName = "";

    private Layout layout;
    private int height;
    private int scrollY;
    private ViewTreeObserver obs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.book_reader);

        initializeApp();

        if(savedInstanceState != null) {
            pageNumber = savedInstanceState.getInt("pageNumber");
            updateView();
        }
    }

    private void updateView() {
    }

    // @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("pageNumber", pageNumber);
    }

    private void initializeApp() {

        message=(TextView)findViewById(R.id.bookTitle);
        contents=(TextView)findViewById(R.id.pageContents);

        contents.setMovementMethod(new ScrollingMovementMethod());

        makeDelay((int) Math.round(Math.random() * 3000 + 2000));

        currentIntent = getIntent();

        // accessing parameter received from the intent
        Bundle extras = currentIntent.getExtras();
        if(extras != null) {
            bookName = extras.getString("bookName");
        }

        message.setText("بهترین روش ها برای تقویت حافظه");
        if(bookName.equals("مطلب اول")) {
            contents.setText(Html.fromHtml("<p><i> این روزها از دست دادن حافظه تبدیل به یک معضل همه گیر شده است و فقط پیرترها دچار آن نمی شوند؛ بنابراین راه های تقویت هوش و حافظه اهمیت زیادی دارد. </i></p><p>این روزها از دست دادن حافظه تبدیل به یک معضل همه گیر شده است و فقط پیرترها دچار آن نمی شوند؛ بنابراین راه های تقویت هوش و حافظه اهمیت زیادی دارد.</p><p><b>چند روش برای حفظ قدرت حافظه</b><p><b>۱ برخی بازی ها را امتحان کنید</b><p>بازی های خوب بسیاری وجود دارند که شما می توانید از آنها برای تقویت حافظه تان استفاده کنید؛ مثل جدول، شطرنج و... این بازی ها کمک می کنند تا بیشتر فکرتان را متمرکز کنید و ذهنتان را فقط به سمت یک چیز سوق دهید.</p><p><b>۲ اسامی را به خاطر بسپارید</b></p><p>نه تنها به خاطر سپردن اسامی کمک زیادی به تقویت حافظه می کند، بلکه برای زندگی نیز سودمند است؛ چرا که بیشتر افراد در به خاطر سپردن اسامی دچار مشکل می شوند. روش های زیادی برای به یاد سپردن اسامی وجوددارد؛ مثلا اسامی افراد را با اسامی حیوانات و یا برخی اعداد همراه کنید. هروقت که از آن عدد یا نام حیوان و یا هرچیز دیگری استفاده کنید، به یاد آن نام می افتید.</p>"));
        }
        else if(bookName.equals("مطلب دوم")) {
            contents.setText(Html.fromHtml("<p><b>۴ یک زبان جدید یاد بگیرید</b></p><p>یک راه خوب دیگر برای تقویت حافظه تان این است که یک زبان جدید یاد بگیرید. این کار هم باعث تقویت حافظه و هم یک مهارت زبانی می شود و چندان هم دشوار نیست. بسیاری از دی وی دی ها، نوارها و خدمات دیگر در این زمینه کمک می کند.</p><p><b>۵ ورزش کنید</b></p><p>خوب و سالم غذاخوردن باعث سلامت جسم و ذهن می شود؛ اما ورزش نیز در این میان اهمیت ویژه ای دارد. هر روز نیم ساعت ورزش کنید. برای افراد پرمشغله، کمی پیاده روی از محل کار تا خانه هم غنیمت است.</p><p><b>۶ ماشین خود را پیدا کنید</b></p><p>به یک پارکینگ بزرگ ماشین فکر کنید که پر از ماشین است. وقتی در چنین شرایطی قرار می گیرید، آیا به راحتی ماشین خود را پیدا می کنید یا اینکه همیشه گیج و سردرگم می شوید؟ این کار را چند بار انجام دهید و هر بار سریع تر ماشین خود را پیدا کنید.</p>"));
        }
        else if(bookName.equals("مطلب سوم")) {
            contents.setText(Html.fromHtml("<p><b>۷ به طبیعت بروید</b></p><p>برخی درمان های طبیعی برای تقویت حافظه جزء بهترین درمان ها محسوب می شوند. داروهای سنتی عملکرد مغز و سیستم عصبی را تقویت می کنند. رزماری یکی از گیاهانی است که به تقویت حافظه کمک می کند.</p><p>۸ خواب کافی داشته باشید</p><p>همه ما می دانیم که خواب چه تاثیری در سلامتی دارد. اگر خواب کافی نداشته باشید، ذهن تان مغشوش می شود و سلول های مغزی فرصت کافی برای تجدید قوا نخواهند داشت.</p><p><b>۹ گل ها را ببویید:</b></p><p>شاید کمی عجیب به نظر برسد، اما بوییدن برخی گل ها، مثل گل رز، باعث تقویت حافظه می شود.</p>"));
        }
        else if(bookName.equals("مطلب چهارم")) {
            contents.setText(Html.fromHtml("<p><b>از دست دادن حافظه</b></p><p>از وقتی که عینک خود را در جایی می گذارید و بعدا قادر به یافتن آن نیستید حتی ممکن است عینک تان روی موهای سرتان باشد و شما برای پیدا کردنش تمام دور و برتان را زیرورو کنید یا وقتی که فراموش می کنید ناهار کودک تان را به او بدهید تا با خود به مدرسه ببرد. کاهش حافظه به مقدار بسیار کم کاملا طبیعی است؛ اما حتی همین مقدار کم نیز در آینده برای شما دردسرساز می شود و باید جدی گرفته شود.</p><p><b>ارتباط میان عملکرد مغز و تناسب اندام</b></p><p>افرادی که تصمیم گرفته اند خود را به اندامی متناسب برسانند، شاید تنها چیزی که در این زمینه به آن فکر نکرده اند، تاثیر این کار بر روی عملکرد مغز است. سال هاست که محققان روی ارتباط جسم، ذهن و روح تحقیق می کنند. اینکه یوگا تمرین می کنید یا هر ورزش دیگری انجام می دهید، همه در جهت رسیدن به احساس خوب و حرکت داشتن است.</p>"));
        }

        // get count
        int count = 0;

        // returning values
        currentIntent.putExtra("ReadCount", count);
        setResult(Activity.RESULT_OK, currentIntent);

        // If used, it will autoamtically return to main menu!
        //finish();
    }

    private void makeDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}