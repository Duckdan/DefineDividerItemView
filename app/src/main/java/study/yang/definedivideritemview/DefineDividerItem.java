package study.yang.definedivideritemview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class DefineDividerItem extends RecyclerView.ItemDecoration {
    //水平
    public static final int HORIZONTAL = 0;
    //垂直
    public static final int VERTICAL = 1;
    private static final String TAG = "DefineDividerItem";

    //分割线Drawable
    private Drawable mDivider;
    //分割线位置
    private int mOrientation = 1;
    private final Rect mBounds = new Rect();

    /**
     * 设置分割线的方向
     *
     * @param orientation
     */
    public void setOrientation(int orientation) {
        if (orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        } else {
            this.mOrientation = orientation;
        }
    }

    /**
     * 设置分割线的样式
     *
     * @param drawable
     */
    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        } else {
            this.mDivider = drawable;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        Log.e(TAG, "onDraw");
    }

    /**
     * 绘制垂直线
     *
     * @param
     * @param parent
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            Log.e("====", "childLayout布局：：" + parent.getChildLayoutPosition(child) + "childAdapter布局：：" + parent.getChildAdapterPosition(child));
            //子View的测量高度
            int childMeasuredHeight = child.getMeasuredHeight();
            int bottom = this.mBounds.bottom + - childMeasuredHeight;
            int top = bottom - this.mDivider.getIntrinsicHeight() - childMeasuredHeight;//-child.getMeasuredHeight()
            //将mBounds的位置信息再传递给mDivider的Bounds
            this.mDivider.setBounds(left, top, right, bottom);
            //绘制mDivider
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }

    /**
     * 绘制水平线
     *
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getLeft(), top, parent.getRight() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            Log.e("====", "childLayout布局：：" + parent.getChildLayoutPosition(child) + "childAdapter布局：：" + parent.getChildAdapterPosition(child));
            //子View的测量宽度
            int measuredWidth = child.getMeasuredWidth();
            int right = this.mBounds.right - measuredWidth;
            int left = right - this.mDivider.getIntrinsicWidth() - measuredWidth;//-child.getMeasuredHeight()
            //将mBounds的位置信息再传递给mDivider的Bounds
            this.mDivider.setBounds(left, top, right, bottom);
            //绘制mDivider
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.e(TAG, "onDrawOver");

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int position = layoutManager.getPosition(view);
        int childCount = layoutManager.getChildCount();
        RecyclerView.Adapter parentAdapter = parent.getAdapter();
        Log.e("====", childCount + "::getItemOffsets::" + position + "::getItemOffsets::" + parentAdapter.getItemCount());
        int totalChildCount = parentAdapter.getItemCount() - 1;
        //获取mDivider的内在高度
        if (mOrientation == VERTICAL) {
            int intrinsicHeight = mDivider.getIntrinsicHeight();
            Log.e("====", "内在高度" + intrinsicHeight);
            getItemVerticalOffsets(outRect, intrinsicHeight, position, totalChildCount);
        } else {
            int intrinsicWidth = mDivider.getIntrinsicWidth();
            getItemHorizontalOffsets(outRect, intrinsicWidth, position, totalChildCount);
        }

    }

    /**
     * 获取水平的条目偏移
     *
     * @param outRect
     * @param offsets
     * @param position
     * @param totalChildCount
     */
    private void getItemHorizontalOffsets(Rect outRect, int offsets, int position, int totalChildCount) {
        if (position == totalChildCount) {
            //保证最后一个条目距离屏幕底部也有间隔
            outRect.set(offsets, 0, offsets, 0);
        } else {
            outRect.set(offsets, 0, 0, 0);
        }
    }

    /**
     * 获取垂直的条目偏移
     *
     * @param outRect
     * @param offsets 偏移度
     */
    private void getItemVerticalOffsets(Rect outRect, int offsets, int position, int totalChildCount) {
        if (position == totalChildCount) {
            //保证最后一个条目距离屏幕底部也有间隔
            outRect.set(0, offsets, 0, offsets);
        } else {
            outRect.set(0, offsets, 0, 0);
        }
    }
}
