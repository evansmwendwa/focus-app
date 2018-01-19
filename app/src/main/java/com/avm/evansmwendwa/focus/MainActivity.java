package com.avm.evansmwendwa.focus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Boolean backgroundActive = false;

    private Switch switchButton;
    private TextView wallpaperText;
    private LinearLayout wallpaperWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind controls
        switchButton = (Switch) findViewById(R.id.switchWallpaper);
        wallpaperText = (TextView) findViewById(R.id.wallpaperText);
        wallpaperWidget = (LinearLayout) findViewById(R.id.wallpaperWidget);

        // bind events
        wallpaperWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleWallpaper();
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleWallpaper();
            }
        });
    }

    public void toggleWallpaper() {
        if(this.backgroundActive) {
            deactivateWallpaperWidget();
            backgroundActive = false;
        } else {
            activateWallpaperWidget();
            backgroundActive = true;
        }
    }

    public void activateWallpaperWidget() {
        wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_active));
        backgroundActive = true;
        switchButton.setChecked(true);
        wallpaperText.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void deactivateWallpaperWidget() {
        wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_disabled));
        backgroundActive = false;
        switchButton.setChecked(false);
        wallpaperText.setTextColor(getResources().getColor(R.color.inactiveText));
    }
}
