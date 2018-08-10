package com.ksyun.media.streamer.demo;

import com.ksyun.live.demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivity extends Activity {
    private static final String TAG = "DemoActivity";

    @BindView(R.id.connectBT)
    protected Button mConnectButton;
    @BindView(R.id.rtmpUrl)
    protected EditText mUrlEditText;
    @BindView(R.id.rtmpToken)
    protected EditText mTokenEditText;
    protected DemoFragment mDemoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        ButterKnife.bind(this);

        getRtmpType();

        Spinner demoTypeSpinner = (Spinner) findViewById(R.id.demo_type);
        String[] items = new String[]{"基礎Demo", "標準Demo"};//, "懸浮窗Demo", "純音頻推流"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        demoTypeSpinner.setAdapter(adapter);
        demoTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        mDemoFragment = new BaseDemoFragment();
                        break;
                    case 1:
                        mDemoFragment = new StdDemoFragment();
                        break;
                    case 2:
                        mDemoFragment = new FloatDemoFragment();
                        break;
                    case 3:
                        mDemoFragment = new AudioDemoFragment();
                        break;
                    default:
                        break;
                }
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        mDemoFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });

        mDemoFragment = new BaseDemoFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                mDemoFragment).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.connectBT)
    public void onClick() {
        doStart();
    }

    protected void doStart() {
        if (!TextUtils.isEmpty(mUrlEditText.getText()) && !TextUtils.isEmpty(mTokenEditText.getText())) {
            String url = mUrlEditText.getText().toString() + mTokenEditText.getText().toString();
            if (url.startsWith("rtmp")) {
                mDemoFragment.start(url);
            }
        }
    }

    private void getRtmpType(){
        Bundle bundle = this.getIntent().getExtras();

        switch (bundle.getInt("rtmpType")){
            case 0:
                mUrlEditText.setText("rtmp://rtmp-api.facebook.com:80/rtmp/");
                break;
            case 1:
                mUrlEditText.setText("rtmp://live-ams.twitch.tv/app/");
                break;
            case 2:
                mUrlEditText.setText("rtmp://a.rtmp.youtube.com/live2/");
                break;
            case 3:
                mUrlEditText.setText("rtmp://live-ea.livehouse.in/app/");
                break;
            case 4:
                mUrlEditText.setText("rtmp://192.168.100.64:1935/rtmplive/");
                break;
            case 5:
                break;

                default:
                    break;
        }
    }
}
