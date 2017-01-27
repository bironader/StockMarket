//package com.example.biro.stockmarket.Network;
//
///**
// * Created by biro on 12/1/2016.
// */
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;
//import com.example.biro.stockmarket.MyApp;
//
//
//public class AsyncVolley {
//
//    private static  AsyncVolley vInstance = null;
//
//
//    private RequestQueue requestQueue;
//
//    private AsyncVolley() {
//        requestQueue =Volley.newRequestQueue(MyApp.getAppContext());
//    }
//
//    public static AsyncVolley getvInstance()
//    {
//        if(vInstance == null)
//        {
//            vInstance = new AsyncVolley();
//        }
//
//            return vInstance;
//
//    }
//
//
//    public RequestQueue getRequestQueue() {
//        return requestQueue;
//    }
//}
