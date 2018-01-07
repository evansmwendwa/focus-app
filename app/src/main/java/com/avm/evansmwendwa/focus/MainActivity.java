package com.avm.evansmwendwa.focus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Boolean backgroundActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch switchButton = (Switch) findViewById(R.id.switchWallpaper);
        final TextView wallpaperText = (TextView) findViewById(R.id.wallpaperText);

        final LinearLayout wallpaperWidget = (LinearLayout) findViewById(R.id.wallpaperWidget);
        wallpaperWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backgroundActive) {
                    wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_disabled));
                    backgroundActive = false;
                    switchButton.setChecked(false);
                    wallpaperText.setTextColor(getResources().getColor(R.color.inactiveText));
                } else {
                    wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_active));
                    backgroundActive = true;
                    switchButton.setChecked(true);
                    wallpaperText.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
    }
}
