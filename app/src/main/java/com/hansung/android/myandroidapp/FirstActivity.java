package com.hansung.android.myandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycle";
    private static final int request_code = 0;
    private  ListView mListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout의 activity_first.xml 뷰에 세팅
        setContentView(R.layout.activity_first);


                /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        //로그 보기
        Log.i(TAG, getLocalClassName() + ".onCreate");

        //activity_first의 buttonSecondActivity 버튼 눌리면
        Button btn = (Button)findViewById(R.id.buttonSecondActivity);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐드를 사용해서 SecondActivity.java를 호출하고 액티비티 실행
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });*/

        btn = (Button)findViewById(R.id.buttonDialActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //암시적 인텐드 실행 114로 전화 걸기
                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"));
                startActivity(implicit_intent);
            }
        });

        btn = (Button)findViewById(R.id.buttonThirdActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ThirdActivity의 "UseDefinedExtra"에 "Hello"를 싣고, 액티비티 실행
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("UserDefinedExtra", "Hello");
                startActivityForResult(intent, request_code);
            }
        });
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pic1.jpg), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //호출된 activity서 넘어온 request_code가 이곳에서 넘겨준 request_code와 같은지 확인
        if (requestCode != request_code || data == null)
            return;
        String msg = data.getStringExtra("ResultString");
        Log.i(TAG, "ActivityResult:" + resultCode + " " + msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getLocalClassName() + ".onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getLocalClassName() + ".onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getLocalClassName() + ".onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getLocalClassName() + ".onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getLocalClassName() + ".onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getLocalClassName() + ".onDestroy");
    }
}
