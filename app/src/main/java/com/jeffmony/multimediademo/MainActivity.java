package com.jeffmony.multimediademo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeffmony.multimediademo.audiorecord.AudioRecordActivity;
import com.jeffmony.multimediademo.audiotrack.AudioTrackActivity;
import com.jeffmony.multimediademo.camera.CameraPreviewActivity;
import com.jeffmony.multimediademo.mediacodec.CodecActivity;
import com.jeffmony.multimediademo.muxerextract.MediaMuxerExtractActivity;
import com.jeffmony.multimediademo.opengl.OpenGLActivity;
import com.jeffmony.multimediademo.surface.SurfaceActivity;

/**
 * 功能选择界面
 *
 * @author Richie on 2018.10.17
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_surface_view).setOnClickListener(this);
        findViewById(R.id.btn_audio_record).setOnClickListener(this);
        findViewById(R.id.btn_audio_track).setOnClickListener(this);
        findViewById(R.id.btn_camera_preview).setOnClickListener(this);
        findViewById(R.id.btn_media_muxur_extract).setOnClickListener(this);
        findViewById(R.id.btn_media_codec).setOnClickListener(this);
        findViewById(R.id.btn_opengles).setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 0);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_surface_view:
                intent = new Intent(this, SurfaceActivity.class);
                break;
            case R.id.btn_audio_record:
                intent = new Intent(this, AudioRecordActivity.class);
                break;
            case R.id.btn_audio_track:
                intent = new Intent(this, AudioTrackActivity.class);
                break;
            case R.id.btn_camera_preview: {
                String[] colors = {"SurfaceView Camera", "TextureView Camera"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick preview type");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        int previewType = 0;
                        if (which == 0) {
                            previewType = CameraPreviewActivity.TYPE_SURFACE_VIEW_CAMERA;
                        } else if (which == 1) {
                            previewType = CameraPreviewActivity.TYPE_TEXTURE_VIEW_CAMERA;
                        } else if (which == 2) {
                            previewType = CameraPreviewActivity.TYPE_SURFACE_VIEW_CAMERA2;
                        }
                        Intent intent = new Intent(MainActivity.this, CameraPreviewActivity.class);
                        intent.putExtra(CameraPreviewActivity.PREVIEW_TYPE, previewType);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
            break;
            case R.id.btn_media_muxur_extract:
                intent = new Intent(this, MediaMuxerExtractActivity.class);
                break;
            case R.id.btn_media_codec:
                intent = new Intent(this, CodecActivity.class);
                break;
            case R.id.btn_opengles: {
                String[] types = {"Triangle", "Image"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick draw type");
                builder.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on types[which]
                        int drawType;
                        if (which == 0) {
                            drawType = OpenGLActivity.TYPE_TRIANGLE;
                        } else if (which == 1) {
                            drawType = OpenGLActivity.TYPE_IMAGE;
                        } else {
                            drawType = OpenGLActivity.TYPE_IMAGE;
                        }
                        Intent intent = new Intent(MainActivity.this, OpenGLActivity.class);
                        intent.putExtra(OpenGLActivity.TYPE, drawType);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
            break;
            default:
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
