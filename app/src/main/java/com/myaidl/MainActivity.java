package com.myaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout activityMain;
    private TextView tvContent;
    private TextView tvAdd;
    private IMyAidlInterface iMyAidlInterface;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent1 = new Intent(this, IMyAidlInterface.class);
        boolean result =  this.getApplicationContext().bindService(intent1, connection,BIND_AUTO_CREATE);

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num+=1;
                Person person=new Person("姓名"+num);
                try {
                    iMyAidlInterface.addPerson(person);
                    List<Person> list=iMyAidlInterface.getPersonList();
                    tvContent.setText(tvContent+"\n"+list.size());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface=IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface=null;
        }
    };



    private void initView() {
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvAdd = (TextView) findViewById(R.id.tv_add);
    }
}
