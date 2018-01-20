package com.avm.evansmwendwa.focus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";

    private Boolean backgroundActive = false;

    private Switch switchButton;
    private TextView wallpaperText;
    private LinearLayout wallpaperWidget;

    private MaterialDialog.Builder dialogProgress;

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
                if(backgroundActive) {
                    showWallpaperDialog(getString(R.string.disable_wallpaper_dialog_desc), false);
                } else {
                    showWallpaperDialog(getString(R.string.wallpaper_dialog_desc), true);
                }
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checked = switchButton.isChecked();
                switchButton.setChecked(!checked);
                wallpaperWidget.callOnClick();
            }
        });

        // dialog progressbar
        dialogProgress = new MaterialDialog.Builder(this)
                .content("Loading...")
                .progress(true, 0)
                .progressIndeterminateStyle(true);
    }

    public void toggleWallpaper() {
        if(backgroundActive) {
            deactivateWallpaperWidget();
            backgroundActive = false;
        } else {
            activateWallpaperWidget();
            backgroundActive = true;
        }
    }

    public void showWallpaperDialog(String description, Boolean activate) {
        String positiveText = getString(R.string.action_continue);

        if(!activate) {
            positiveText = getString(R.string.action_deactivate);
        }

        new MaterialDialog.Builder(this)
                .title(R.string.daily_wallpaper)
                .content(description)
                .positiveText(positiveText)
                .negativeText(R.string.action_cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        toggleWallpaper();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .show();
    }

    public void activateWallpaperWidget() {
        dialogProgress.content("Updating daily wallpaper").show();

        wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_active));
        backgroundActive = true;
        switchButton.setChecked(true);
        wallpaperText.setTextColor(getResources().getColor(R.color.colorWhite));
        wallpaperText.setText("Turn off daily wallpaper");
    }

    public void deactivateWallpaperWidget() {
        dialogProgress.content("Deactivating daily wallpaper").show();

        wallpaperWidget.setBackground(getResources().getDrawable(R.drawable.wallpaper_disabled));
        backgroundActive = false;
        switchButton.setChecked(false);
        wallpaperText.setTextColor(getResources().getColor(R.color.inactiveText));
        wallpaperText.setText("Turn on daily wallpaper");
    }
}
