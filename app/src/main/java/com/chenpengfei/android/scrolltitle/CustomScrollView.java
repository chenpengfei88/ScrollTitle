package com.chenpengfei.android.scrolltitle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Chenpengfei on 2015/7/20.
 */
public class CustomScrollView extends ScrollView{

    private ScrollViewInterface scrollViewInterface;

    public void setScrollViewInterface(ScrollViewInterface scrollViewInterface){
        this.scrollViewInterface = scrollViewInterface;
    }


    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollViewInterface!=null)
        scrollViewInterface.scrollStatus(t, oldt);
    }
}
