package com.example.fivechess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private Chess_Panel panel;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        panel = (Chess_Panel) findViewById(R.id.main_panel);
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("游戏结束");
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                MainActivity.this.finish();
            }
        });
        builder.setPositiveButton("再来一局", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface interface1, int which) {

                panel.restartGame();
            }
        });
        panel.setOnGameListener(new Chess_Panel.onGameListener() {

            @Override
            public void onGameOver(int i) {
                String str = "";
                if (i == Chess_Panel.WHITE_WIN) {
                    str = "白方胜利！";
                } else if (i == Chess_Panel.BLACK_WIN) {
                    str = "黑方胜利！";
                }
                builder.setMessage(str);
                builder.setCancelable(false);   //不可用返回键取消
                AlertDialog dialog = builder.create();
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.x = 0;
                params.y = panel.getUnder();
                dialogWindow.setAttributes(params);  //设置Dialog显示的位置
                dialog.setCanceledOnTouchOutside(false);   //不可点击取消
                dialog.show();
            }
        });

    }
}