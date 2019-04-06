package com.nyxwolves.wannabuy.activities;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.imageAdapter.ImageAdapter;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class ImageUpload extends AppCompatActivity {

    Button submitButton;
    Bitmap bitmap;
    Button select;
    ProgressDialog progressDialog;
    ArrayList<Uri> mArrayUri;
    int adsNum;
    int PAYMENT_CODE = 120;
    String adId;
    String individualOrDealer;
    String ownerOrDealer;
    final int REQUEST_IMAGE = 1;
    boolean check = true;
    boolean isImageSelected = false;
    String ServerUploadPath = "http://wannabuy.in/api/images/upload_image.php";
    int imageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        individualOrDealer = getIntent().getStringExtra(getString(R.string.owner_dealer_flag));

        adId = getIntent().getStringExtra(getString(R.string.AD_ID));

        setTitle("Upload Images");

        Intent intent = getIntent();
        ownerOrDealer = intent.getStringExtra(getString(R.string.owner_dealer_flag));
        try {
            adsNum = Integer.parseInt(intent.getStringExtra(getString(R.string.ad_num)));
        } catch (NumberFormatException e) {
            Log.e("ImageUpload", e.toString());
        }

        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE);
            }
        });

        submitButton = findViewById(R.id.ad_submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUploadToServerFunction();
            }
        });
    }

    private void checkPayment() {
        if (individualOrDealer.equals(getString(R.string.individual))) {//if user is owner

            if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                //intiatePayment(2999, getString(R.string.pay_sell_ad));
                startIntentToHome();
            } else {
                //intiatePayment(999, getString(R.string.pay_rent_ad));
                startIntentToHome();
            }

        } else if (individualOrDealer.equals(getString(R.string.dealer))) {//if user is dealer

            if (adsNum == 0) {
                //needs to pay
                if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                    //intiatePayment(10000, getString(R.string.pay_sell_ad));
                    startIntentToHome();
                } else {
                    //intiatePayment(10000, getString(R.string.pay_rent_ad));
                    startIntentToHome();
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

        i.putExtra(getString(R.string.AD_ID), adId);
        i.putExtra(getString(R.string.owner_dealer_flag), individualOrDealer);

        i.putExtra(getString(R.string.AD_ID), adId);
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
        if (requestCode == PAYMENT_CODE) {
            if (resultCode == RESULT_OK) {
                startIntentToHome();
            }
        }

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            mArrayUri = new ArrayList<>();

            if (data.getData() != null) {
                Uri mImageUri = data.getData();
                mArrayUri.add(mImageUri);
            }

            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri2 = item.getUri();
                    mArrayUri.add(uri2);
                }
            }
            setTitle(mArrayUri.size() + " image selected");
            setupRecyclerView(mArrayUri);
        }
    }

    void setupRecyclerView(ArrayList<Uri> images) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ImageAdapter imageAdapter = new ImageAdapter(this, images);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void ImageUploadToServerFunction() {
        progressDialog = ProgressDialog.show(ImageUpload.this, "Please Wait",
                "Uploading Images " + imageCount + "/" + mArrayUri.size());
        for (int i = 0; i < mArrayUri.size(); i++) {
            AsyncTaskUploadClass upload = new AsyncTaskUploadClass();
            upload.execute(mArrayUri.get(i));
        }
    }

    class AsyncTaskUploadClass extends AsyncTask<Uri, Void, String> {
        HashMap<String, String> HashMapParams = new HashMap<>();
        ImageProcessClass imageProcessClass = new ImageProcessClass();

        @Override
        protected String doInBackground(Uri... uris) {
            ByteArrayOutputStream byteArrayOutputStreamObject;
            byteArrayOutputStreamObject = new ByteArrayOutputStream();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uris[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                isImageSelected = true;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
                final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
                HashMapParams.put("image_name", UUID.randomUUID().toString());
                HashMapParams.put(getString(R.string.AD_ID), adId + "");
                HashMapParams.put("image", ConvertImage);
                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);
                return FinalData;
            } else {
                isImageSelected = false;
                return "Please Choose an Image.";
            }
        }

        @Override
        protected void onPostExecute(String string1) {
            super.onPostExecute(string1);
            if (imageCount == mArrayUri.size()) {
                progressDialog.dismiss();
                Toast.makeText(ImageUpload.this, "Images Uploaded Successfully",
                        Toast.LENGTH_SHORT).show();
                if (isImageSelected)
                    checkPayment();
            } else {
                imageCount++;
                progressDialog.setMessage("Uploading Images " + imageCount + "/" + mArrayUri.size());
            }
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

