package com.chenpengfei.android.scrolltitle;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.text.DecimalFormat;

/**
 * Created by Chenpengfei on 2015/7/14.
 * Description 宽高比例图片
 */
public class ProportionImageView extends ImageView {

    /** 图片宽和高的比例 */
    private float ratio;

    public ProportionImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ProportionImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProportionImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 父容器传过来的宽度方向上的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 父容器传过来的高度方向上的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 父容器传过来的宽度的值
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        // 父容器传过来的高度的值
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingLeft() - getPaddingRight();
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
            // 判断条件为，宽度模式为Exactly，也就是填充父窗体或者是指定宽度；
            // 且高度模式不是Exaclty，代表设置的既不是fill_parent也不是具体的值，于是需要具体测量
            // 且图片的宽高比已经赋值完毕，不再是0.0f
            // 表示宽度确定，要测量高度
            height = (int) (width / ratio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
            // 判断条件跟上面的相反，宽度方向和高度方向的条件互换
            // 表示高度确定，要测量宽度
            width = (int) (height * ratio + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //计算图片宽高比例
    public void setRatio(int id) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        if(opts!=null) {
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), id, opts);
            this.ratio = (float)opts.outWidth / opts.outHeight;
            DecimalFormat df = new DecimalFormat("0.00");
            this.ratio = Float.parseFloat(df.format(ratio));
        }
    }
}
