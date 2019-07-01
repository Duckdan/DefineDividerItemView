package study.yang.definedivideritemview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public  class RvVerticalAdapter extends RecyclerView.Adapter<RvVerticalAdapter.RvHolder> {

    private Context context;

    public RvVerticalAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        TextView textView = new TextView(context);
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

