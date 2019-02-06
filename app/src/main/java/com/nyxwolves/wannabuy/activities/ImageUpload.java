package com.nyxwolves.wannabuy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.os.Bundle;
import android.util.Log;
=======
import android.util.Base64;
>>>>>>> 31d533f3031bb5d7816ce11d4a8a0801e4032324
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class ImageUpload extends AppCompatActivity {
    final int REQUEST_IMAGE = 1;
    boolean check = true;
    Button submitButton;
<<<<<<< HEAD

    String individualOrDealer;
=======
    String ownerOrDealer;
>>>>>>> 31d533f3031bb5d7816ce11d4a8a0801e4032324
    int adsNum;
    int PAYMENT_CODE = 120;
    int adId;
    ProgressDialog progressDialog;
    String ServerUploadPath = "http://wannabuy.in/api/images/upload_image.php";
    Bitmap bitmap;
    ImageView image;
    Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

<<<<<<< HEAD
        individualOrDealer = getIntent().getStringExtra(getString(R.string.owner_dealer_flag));
        try{
            Log.d("AD_ID",getIntent().getStringExtra(getString(R.string.ad_id)));
            adId = Integer.parseInt(getIntent().getStringExtra(getString(R.string.ad_id)));
            adsNum = Integer.parseInt(getIntent().getStringExtra(getString(R.string.ad_num)));
=======
        setTitle("Upload Images");

        Intent intent = getIntent();
        ownerOrDealer = intent.getStringExtra(getString(R.string.owner_dealer_flag));
        try{
            adId = Integer.parseInt(intent.getStringExtra(getString(R.string.AD_ID)));
            adsNum = Integer.parseInt(intent.getStringExtra(getString(R.string.ad_num)));
>>>>>>> 31d533f3031bb5d7816ce11d4a8a0801e4032324
        }catch (NumberFormatException e){
            Log.d("EXCEC",e.toString());
        }

        image = findViewById(R.id.image);

        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE);
            }
        });

        Button upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUploadToServerFunction();
            }
        });

        submitButton = findViewById(R.id.ad_submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPayment();
            }
        });
    }

    private void checkPayment(){
        if (individualOrDealer.equals(getString(R.string.individual))) {//if user is owner

            if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                intiatePayment(2999, getString(R.string.pay_sell_ad));
            } else {
                intiatePayment(999, getString(R.string.pay_rent_ad));
            }

        } else if (individualOrDealer.equals(getString(R.string.dealer))) {//if user is dealer

            if (adsNum == 0) {
                //needs to pay
                if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                    intiatePayment(10000, getString(R.string.pay_sell_ad));
                } else {
                    intiatePayment(10000, getString(R.string.pay_rent_ad));
                }
            } else if (adsNum > 0) {
                //still has credits left
                startIntentToHome();

            }
        }
    }

    private void startIntentToHome() {
        Intent i = new Intent(ImageUpload.this, HomeActivity.class);
        i.setAction(getString(R.string.POST_AD));
<<<<<<< HEAD
        i.putExtra(getString(R.string.ad_id),adId);
        i.putExtra(getString(R.string.owner_dealer_flag),individualOrDealer);
=======
        i.putExtra(getString(R.string.AD_ID), adId);
>>>>>>> 31d533f3031bb5d7816ce11d4a8a0801e4032324
        startActivity(i);
    }

    private void intiatePayment(int amount, String sellOrRent) {
        Intent paymentIntent = new Intent(ImageUpload.this, PaymentActivity.class);
        paymentIntent.putExtra(PaymentActivity.AMOUNT, amount);
        paymentIntent.putExtra(PaymentActivity.DESCRIPTION, sellOrRent);
        startActivityForResult(paymentIntent, PAYMENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYMENT_CODE){
            if(resultCode == RESULT_OK){
                startIntentToHome();
            }
        }

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                image.setImageBitmap(bitmap);
                select.setText("Select Another Image");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ImageUploadToServerFunction() {
        AsyncTaskUploadClass upload = new AsyncTaskUploadClass();
        upload.execute();
    }

    class AsyncTaskUploadClass extends AsyncTask<Void, Void, String> {
        HashMap<String, String> HashMapParams = new HashMap<>();
        ImageProcessClass imageProcessClass = new ImageProcessClass();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ImageUpload.this, "Image is Uploading",
                    "Please Wait", false, false);
        }

        @Override
        protected String doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStreamObject;
            byteArrayOutputStreamObject = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStreamObject);
            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
            HashMapParams.put("image_name", UUID.randomUUID().toString());
            HashMapParams.put("image", ConvertImage);
            HashMapParams.put(getString(R.string.AD_ID), adId + "");
            String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);
            return FinalData;
        }

        @Override
        protected void onPostExecute(String string1) {
            super.onPostExecute(string1);
            progressDialog.dismiss();
            Toast.makeText(ImageUpload.this, string1, Toast.LENGTH_LONG).show();
            //finish();
        }
    }

    public class ImageProcessClass {

        public String ImageHttpRequest(String requestURL, HashMap<String, String> PData) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url;
                HttpURLConnection httpURLConnectionObject;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject;
                BufferedReader bufferedReaderObject;
                int RC;
                url = new URL(requestURL);
                httpURLConnectionObject = (HttpURLConnection) url.openConnection();
                httpURLConnectionObject.setReadTimeout(19000);
                httpURLConnectionObject.setConnectTimeout(19000);
                httpURLConnectionObject.setRequestMethod("POST");
                httpURLConnectionObject.setDoInput(true);
                httpURLConnectionObject.setDoOutput(true);
                OutPutStream = httpURLConnectionObject.getOutputStream();
                bufferedWriterObject = new BufferedWriter(
                        new OutputStreamWriter(OutPutStream, StandardCharsets.UTF_8));
                bufferedWriterObject.write(bufferedWriterDataFN(PData));
                bufferedWriterObject.flush();
                bufferedWriterObject.close();
                OutPutStream.close();
                RC = httpURLConnectionObject.getResponseCode();
                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReaderObject =
                            new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String RC2;
                    while ((RC2 = bufferedReaderObject.readLine()) != null) {
                        stringBuilder.append(RC2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {
            StringBuilder stringBuilderObject;
            stringBuilderObject = new StringBuilder();
            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilderObject.append("&");
                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
                stringBuilderObject.append("=");
                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }
            return stringBuilderObject.toString();
        }
    }
}
