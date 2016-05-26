package sampleproject.robosoft.com.popupfragmentexample;

import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CategoryFragment mCategoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.see_all_text)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopUpFragment();
            }
        });

        initializeTermsAndConditionsText();
    }

    private void initializeTermsAndConditionsText() {

        TextView txtTermsConditions = (TextView) findViewById(R.id.hyperlink_txt);
        String text = "By clicking Sign Up, you agree to our Terms & Conditions" +
                " and that you have read our Privacy Policy";
        txtTermsConditions.setText(text);

        //terms and conditions click handling
        String terms = "Terms & Conditions";
        int start = text.indexOf(terms);
        txtTermsConditions.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spans = (Spannable) txtTermsConditions.getText();
        URLSpan spanNoUnderline = new ExpandedURLSpanNoUnderline(terms);
        spans.setSpan(spanNoUnderline, start, terms.length() + start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spans.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.paytm_blue)), start, terms.length() + start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //privacy policy click handling
        terms = "Privacy Policy";
        start = text.indexOf(terms);
        txtTermsConditions.setMovementMethod(LinkMovementMethod.getInstance());
        spans = (Spannable) txtTermsConditions.getText();
        spanNoUnderline = new ExpandedURLSpanNoUnderline(terms);
        spans.setSpan(spanNoUnderline, start, terms.length() + start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spans.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.paytm_blue)), start, terms.length() + start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //set custom fonts
    }

    protected class ExpandedURLSpanNoUnderline extends URLSpan {

        public ExpandedURLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            String clickedText = this.getURL();
            if (!TextUtils.isEmpty(clickedText)) {
                String url = null;
                if (clickedText.equalsIgnoreCase("Terms & Conditions")) {
                    url = "google.com";
                } else {
                    url = "yahoo.com";
                }
                //
                /*WebView theWebPage = new WebView(MainActivity.this);
                theWebPage.getSettings().setJavaScriptEnabled(true);
                theWebPage.getSettings().setPluginState(WebSettings.PluginState.ON);
                setContentView(theWebPage);
                theWebPage.loadUrl(url);*/
            }

        }
    }

    private void initPopUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mCategoryFragment = new CategoryFragment(this);
        fragmentTransaction.replace(R.id.category_fragment, mCategoryFragment).addToBackStack(null);
        findViewById(R.id.category_fragment).setVisibility(View.VISIBLE);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        findViewById(R.id.category_fragment).setVisibility(View.GONE);
    }
}
