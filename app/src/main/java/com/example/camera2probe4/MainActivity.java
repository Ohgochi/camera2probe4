// Original Code: Tobias Weis / android-camera2probe
// https://github.com/TobiasWeis/android-camera2probe/wiki
//
// Ported to Modern Java and new API
// .... Android Studio 2020.3.1, API 29 and Java 8
// The original code emailed the result to the embedded email address,
// but I changed this to file save
// Toyoaki, OHGOCHI  https://twitter.com/Ohgochi/

package com.example.camera2probe4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private CameraSpecToHtml convHtml = new CameraSpecToHtml(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener( V -> {
            saveInfoFile();
        });

        CameraSpec specs = new CameraSpec(this);
        specs.getCameraSpec();
        convHtml.setUnencodedHtml(specs.getSpecs());
        String resultHtml = convHtml.getUnencodedHtml();
        WebView wv = (WebView) findViewById(R.id.probe);
        wv.loadDataWithBaseURL(null, String.valueOf(resultHtml), "text/html", "utf-8", null);
    }

    private void saveInfoFile() {
        Intent htmlIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        htmlIntent.addCategory(Intent.CATEGORY_OPENABLE);
        htmlIntent.setType("text/html");
        htmlIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.file_name));

        saveInfoFile.launch(htmlIntent);
    }

    ActivityResultLauncher<Intent> saveInfoFile =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() != RESULT_OK ) {
                                // error logout
                                return;
                            }

                            Uri uri = result.getData().getData();
                            try (OutputStream outStream = getContentResolver().openOutputStream(uri)) {
                                String resultHtml = convHtml.getUnencodedHtml();
                                outStream.write(resultHtml.getBytes());
                                outStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
}
