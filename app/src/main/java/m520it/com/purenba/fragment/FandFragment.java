package m520it.com.purenba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import m520it.com.purenba.R;
import m520it.com.purenba.base.BaseFragment;
import m520it.com.purenba.config.Contan;

/**
 * Created by 亮 on 2017/4/24.
 */

public class FandFragment extends BaseFragment {

    private WebView mWebView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fand, container, false);
        mWebView = (WebView) view.findViewById(R.id.web_view);
                mWebView.loadUrl(Contan.URL_FIND);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        return view;
    }
}
