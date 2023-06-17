package com.example.myapp.profile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;



import androidx.annotation.Nullable;

import com.example.myapp.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyView extends View implements View.OnTouchListener {
    private final Paint paint;
    private final Path path;
    private Point point;
    public  List<Path> pathList;

    private Button button;

    public MyView(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
        pathList = new ArrayList<>();

        setOnTouchListener(this);
//        MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.ringtone);
//        mediaPlayer.start();

        String pathName = Environment.getExternalStorageDirectory()+"//Download/abc.mp3";
        Uri location = Uri.fromFile(new File(pathName));
        Log.e("error", Environment.getExternalStorageDirectory().getPath());
        MediaPlayer mediaPlayer1 = new MediaPlayer();
        mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            //mediaPlayer1.setDataSource("https://www.setasringtones.com/storage/ringtones/9/9b364085855582e669a1f5466f7c48be.mp3");
            mediaPlayer1.setDataSource(pathName);
            mediaPlayer1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer1.start();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//           paint.setColor(Color.RED);
//           paint.setStyle(Paint.Style.STROKE);
//           paint.setStrokeWidth(10);
//           canvas.drawCircle(300,300,100,paint);
//
//           paint.setColor(getResources().getColor(R.color.purple_200));
//           paint.setStyle((Paint.Style.FILL));
//           canvas.drawRect(300,600,500,700,paint);
//
//            paint.setColor(getResources().getColor(R.color.purple_700));
//            paint.setStyle((Paint.Style.FILL));
//            canvas.drawLine(700,600,700,1000,paint);
//
//
//            paint.setColor(getResources().getColor(R.color.purple_700));
//            paint.setStyle((Paint.Style.FILL));
//            path.moveTo(100,100);
//            path.lineTo(200,200);
//            path.arcTo(300,200,500,500,50,120,true);
//            invalidate();
//            canvas.drawPath(path,paint);

        paint.setColor(getResources().getColor(R.color.purple_700));
        paint.setStyle((Paint.Style.STROKE));
        paint.setStrokeWidth(20);
        for (Path path : pathList){
            canvas.drawPath(path,paint);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            point = new Point();
            point.x = (int) event.getX();
            point.y = (int) event.getY();
            path.moveTo(point.x,point.y);
            // pathList.add(path);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            point = new Point();
            point.x = (int) event.getX();
            point.y = (int) event.getY();
            // path.arcTo(300,200,500,500,50,120,true);
            path.lineTo(point.x,point.y);
            pathList.add(path);

            invalidate();
        }

        return true;
    }

    public void invalid(){
        pathList.remove(pathList.size()-1);
        invalidate();
    }

}
