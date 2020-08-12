package com.example.slide8nc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView my_img;
    PreviewView viewFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_img = findViewById(R.id.my_img);
        viewFinder = findViewById(R.id.viewFinder);
    }

    // camera mặc định của máy thông qua Intent
    //https://meet.google.com/linkredirect?authuser=1&dest=https%3A%2F%2Fdeveloper.android.com%2Ftraining%2Fcamera%2Fphotobasics
    //https://meet.google.com/linkredirect?authuser=1&dest=https%3A%2F%2Fcodelabs.developers.google.com%2Fcodelabs%2Fcamerax-getting-started%2F%230
    public void openCameraIntent(View view) {
        // xin quyền trong manifest
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // kiểm tra tinsh khả dụng camera trong máy
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 304);
        }
    }

    // bước tiếp theo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 304 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            my_img.setImageBitmap(imgBitmap);
        }
    }

    //
    // mở kết nối xem hình ảnh truyền từ camera để chụp ảnh hoặc quay video
    public void openCameraPeview(View view) {
        // code mẫu https://github.com/huuhuybn/CameraX2020
        //https://codelabs.developers.google.com/codelabs/camerax-getting-started/#1
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 96);
        } else {
            startCamerePerview();
        }
    }

    public void startCamerePerview() {

    }

    //
    // làm 2 viêc phát nhạc từ link mp3 hoặc trong project
    public void playMedia(View view) {

        // link mp3
        //String link = "https://data3.chiasenhac.com/downloads/1808/5/1807216-a82cf9d3/128/Six%20Feet%20Under%20-%20Billie%20Eilish.mp3";
        //MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(link));
        //mediaPlayer.start();

        // chạy trong project
        // tạo thư mục raw
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.yeuem);
        mediaPlayer.start();
        // tổng thời gian
        int duration = mediaPlayer.getDuration();
        // thời gian nghe
        int currentTime = mediaPlayer.getCurrentPosition();
    }
}