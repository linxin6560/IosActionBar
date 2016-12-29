package me.levylin.iosactionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import me.levylin.lib.iosactionbar.Action;
import me.levylin.lib.iosactionbar.IosActionBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IosActionBar actionBar = (IosActionBar) findViewById(R.id.ios_actionbar);
        actionBar.setTitle(getTitle());
        actionBar.addLeftAction(new Action() {
            @Override
            public void performAction(View view) {

            }

            @Override
            public int getDrawable() {
                return R.mipmap.ic_launcher;
            }
        });
        actionBar.addRightAction(new Action() {
            @Override
            public void performAction(View view) {

            }

            @Override
            public String getTextStr() {
                return "测试";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
