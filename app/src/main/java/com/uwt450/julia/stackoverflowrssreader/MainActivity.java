package com.uwt450.julia.stackoverflowrssreader;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.uwt450.julia.stackoverflowrss.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.SHARED_PREFS), Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RSSService.setServiceAlarm(view.getContext(), true);
                editor.putBoolean(getString(R.string.ON), true);
                editor.commit();
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RSSService.setServiceAlarm(view.getContext(), false);
                editor.putBoolean(getString(R.string.ON), false);
                editor.commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String result = getIntent().getStringExtra(RSSService.FEED);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadData(result, "text/html", null);
    }
}
