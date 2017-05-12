package m520it.com.purenba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import m520it.com.purenba.R;
import m520it.com.purenba.util.ActivityUtils;

public class NBADetailActivity extends Activity {

    private Button mBack;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba_item_dateil);
        mWebView = (WebView) findViewById(R.id.datil_web);
        Intent intent = getIntent();
        String newAppId = intent.getStringExtra("newsAppId");

        mWebView.loadUrl("http://view.inews.qq.com/a/"+newAppId);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        mBack = (Button) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(NBADetailActivity.this);
            }
        });
    }
}
