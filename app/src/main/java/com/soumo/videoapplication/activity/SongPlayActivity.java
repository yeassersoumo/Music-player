package com.soumo.videoapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.soumo.videoapplication.R;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SongPlayActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000 ;


    // Storage Permissions
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);

        // received data

        String video  = getIntent().getExtras().getString("song_video");


        VideoView videoView = findViewById(R.id.videoViewId);

        //Show video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);


        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(video));
        videoView.requestFocus();
        videoView.start();

        //For Download video
        ImageView imageView = findViewById(R.id.downloadId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If OS is Marshmallow or Above, handel run time permission

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){

                        //permission denied request it
                        String [] permissions = (PERMISSIONS_STORAGE );

                        //Show popup
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);




                    }
                    else {
                        //permission already granted perform download

                        startDownloading();

                    }
                }

                else {

                    // system os is less than marshmallow perform download
                    startDownloading();

                }
            }
        });
    }

    private void startDownloading() {

        String video  = getIntent().getExtras().getString("song_video");

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(video));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalFilesDir(Environment.DIRECTORY_DOWNLOADS, "");

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    //handel permission result


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


            switch (requestCode){
                case  PERMISSION_STORAGE_CODE:{
                    if(grantResults.length > 0 && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED){
                        startDownloading();
                    }
                    else {

                    }
                }
            }
        }

}
