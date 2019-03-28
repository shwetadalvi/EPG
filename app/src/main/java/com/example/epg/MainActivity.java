package com.example.epg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interceptors.GzipRequestInterceptor;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    String str_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);


        //   webview.getSettings().setJavaScriptEnabled(true);

      /*  webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //showProgressDialog();
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
              //  hideProgressDialog();
              *//*  for (String ord : ordinals) {
                    if (url.contains(ord)) {

                        webview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                        webview.setVisibility(View.GONE);
                        wait.setVisibility(View.VISIBLE);
                        break;
                    }else{*//*
                        webview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                   // }
               // }

                super.onPageFinished(view, url);
            }
        });
*/
       /* OkHttpClient client=new OkHttpClient();
        try {
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(new TLSSocketFactory())
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
          postApi();
        //postApi1();
/*String str_url = "https://demo-ipg.comtrust.ae:2443";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(str_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", str_url);
                startActivity(intent);
            }
        });
        // webview.loadUrl("https://demoipg.comtrust.ae/PaymentEx/Paymentpartner/Payment");

    }

    private void postApi1() {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(new TLSSocketFactory())
                   // .addInterceptor(new GzipRequestInterceptor())
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);

        // AndroidNetworking.setParserFactory(new JacksonParserFactory());

        //   param.addProperty("ReturnPath","https://partner.ctdev.comtrust.ae/banktestnbad/Authorization.aspx?capture=true");


        EPGRequest user = new EPGRequest();
        Registration registration = new Registration();
        registration.setReturnPath("https://partner.ctdev.comtrust.ae/banktestnbad/Authorization.aspx?capture=true");
        registration.setCurrency("AED");
        registration.setTransactionHint("CPT:Y;VCC:Y;");
        registration.setOrderID("7210055701315195");
        registration.setStore("Mobile");
        registration.setTerminal("Recharge");
        registration.setChannel("Web");
        registration.setAmount("2.00");
        registration.setCustomer("Demo Merchant");
        registration.setOrderName("Paybill");
        registration.setUserName("Demo_fY9c");
        registration.setPassword("Comtrust@20182018");

        user.setRegistration(registration);

        AndroidNetworking.post("https://demo-ipg.comtrust.ae:2443")
                .addBodyParameter(user) // posting java object
                .setTag("test")
                .addHeaders("Content-Type", "application/xml")
                .addHeaders("Accept", "text/xml-standard-api")

                // .setOkHttpClient(okHttpClient)
                .setPriority(Priority.MEDIUM)
                .build()

              .getAsObject(EPGResponse.class, new ParsedRequestListener<EPGResponse>() {


                    @Override
                    public void onResponse(EPGResponse response) {
                        Log.d("login resonse :", response.toString());
                        str_url = response.getTransaction().getPaymentPortal();
                        Log.d("login resonse1 :", response.getTransaction().getPaymentPortal());
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.d("login error :", error.toString());
                    }
                });


    }


    private void postApi() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        JsonObject param = new JsonObject();
        param.addProperty("Currency", "AED");
        //   param.addProperty("ReturnPath","https://partner.ctdev.comtrust.ae/banktestnbad/Authorization.aspx?capture=true");
        param.addProperty("TransactionHint", "CPT:Y;VCC:Y;");
        param.addProperty("OrderID", "7210055701315195");
        param.addProperty("Store", "Mobile");
        param.addProperty("Terminal", "Recharge");
        param.addProperty("Channel", "Web");
        param.addProperty("Amount", "2.00");

        param.addProperty("Customer", "Demo Merchant");
        param.addProperty("OrderName", "Paybill");
        param.addProperty("UserName", "Demo_fY9c");
        param.addProperty("Password", "Comtrust@20182018");


        EPGRequest user = new EPGRequest();
        Registration registration = new Registration();
        registration.setReturnPath("https://partner.ctdev.comtrust.ae/banktestnbad/Authorization.aspx?capture=true");
        registration.setCurrency("AED");
        registration.setTransactionHint("CPT:Y;VCC:Y;");
        registration.setOrderID("7210055701315195");
        registration.setStore("Mobile");
        registration.setTerminal("Recharge");
        registration.setChannel("Web");
        registration.setAmount("2.00");
        registration.setCustomer("Demo Merchant");
        registration.setOrderName("Paybill");
        registration.setUserName("Demo_fY9c");
        registration.setPassword("Comtrust@20182018");

        user.setRegistration(registration);
        Log.d("login params :", String.valueOf(user.toString()));
        Call<EPGResponse> call = apiInterface.registerUser(user);

        call.enqueue(new Callback<EPGResponse>() {
            @Override
            public void onResponse(Call<EPGResponse> call, retrofit2.Response<EPGResponse> response) {
                EPGResponse response1 = response.body();
               // Log.d("login resonse :", new Gson().toJson(response));
                Log.d("login resonse :", String.valueOf(response.isSuccessful()));
                str_url = response1.getTransaction().getPaymentPortal();
              /*  if(response.body().getIserror().equalsIgnoreCase("No")) {
                    Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity

                            .class);
                    startActivity(intent);
                    finish();
                } else
                    Snackbar.make(buttonLogin, response.body().getMessage(), Snackbar.LENGTH_LONG).show();*/
            }

            @Override
            public void onFailure(Call<EPGResponse> call, Throwable t) {
                Log.d("login exception :", t.toString());
            }
        });


    }
}


