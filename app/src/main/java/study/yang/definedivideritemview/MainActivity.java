package study.yang.definedivideritemview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DefineDividerItem dividerItem = new DefineDividerItem();
        dividerItem.setDrawable(getResources().getDrawable(R.drawable.item_vertical_divider));
        dividerItem.setOrientation(DefineDividerItem.VERTICAL);
        rv.addItemDecoration(dividerItem);
        RvVerticalAdapter verticalAdapter = new RvVerticalAdapter(this);
        RvHorizontalAdapter horizontalAdapter = new RvHorizontalAdapter(this);
        rv.setAdapter(verticalAdapter);
    }



}
