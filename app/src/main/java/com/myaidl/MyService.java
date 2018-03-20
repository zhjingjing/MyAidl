package com.myaidl;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class MyService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    private ArrayList<Person> mPersons;


    private IBinder iBinder=new IMyAidlInterface.Stub(){

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons=new ArrayList<>();
        return iBinder;
    }
}
