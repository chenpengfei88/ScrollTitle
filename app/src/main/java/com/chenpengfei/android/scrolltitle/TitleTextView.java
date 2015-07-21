package com.chenpengfei.android.scrolltitle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chenpengfei on 2015/7/21.
 */
public class TitleTextView extends TextView {

    //偏移距离
    private int offset = 0;
    private boolean isInit = true;
    //默认偏移的高度
    private int initHeight;

    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, offset);
        canvas.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        super.onDraw(canvas);
        canvas.restore();
    }

    public void init(int offset){
        if(isInit) {
            this.offset = offset;
            initHeight = offset;
            invalidate();
            isInit = false;
        }
    }

    public void setOffset(int offset){
        setVisibility(View.VISIBLE);
        this.offset = this.offset - offset;
        if(this.offset <= 0){
            this.offset =0;
        }
        if(this.offset >= initHeight){
            this.offset = initHeight;
        }
        invalidate();
    }

}
