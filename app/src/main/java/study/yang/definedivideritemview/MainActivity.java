package study.yang.definedivideritemview;

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
        dividerItem.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        rv.addItemDecoration(dividerItem);
        rv.setAdapter(new RvAdapter());
    }


    class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {

        @NonNull
        @Override
        public RvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            TextView textView = new TextView(getBaseContext());
            textView.setGravity(Gravity.CENTER);
            RecyclerView.LayoutParams rlp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 100);
            textView.setLayoutParams(rlp);
            textView.setBackgroundColor(Color.WHITE);
            return new RvHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull RvHolder rvHolder, int position) {
            TextView itemView = (TextView) rvHolder.itemView;
            String text = "文本：：" + position;
            itemView.setText(text);
        }

        @Override
        public int getItemCount() {
            return 45;
        }

        class RvHolder extends RecyclerView.ViewHolder {

            public RvHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
