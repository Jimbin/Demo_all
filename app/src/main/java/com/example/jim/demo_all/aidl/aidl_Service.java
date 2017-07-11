package com.example.jim.demo_all.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Jim斌 on 2017/7/11.
 */

public class aidl_Service extends Service{
    @Nullable
    @Override
    //当客服端绑定到这个服务时就会调用它
    public IBinder onBind(Intent intent) {
//        return iBinder;
        return mBinder;
    }

    private final My_AidlInterface.Stub mBinder = new My_AidlInterface.Stub() {
        @Override
        public int add(int n1, int n2) throws RemoteException {
            return n1+n2;
        }

    };

    private IBinder iBinder= new My_AidlInterface.Stub() {
        @Override
        public int add(int n1, int n2) throws RemoteException {
            return n1+n2;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("aidl服务开始了", ": ");
    }
}
