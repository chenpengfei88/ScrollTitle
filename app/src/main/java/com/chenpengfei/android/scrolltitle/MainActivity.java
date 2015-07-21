package com.chenpengfei.android.scrolltitle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author  Chenpengfei
 * @Email  450032215@qq.com
 *  Descirption 首页
 */
public class MainActivity extends Activity {

    //外层scrollview
    private CustomScrollView customScrollView;
    //图片显示view
    private ProportionImageView proportionImageView;
    private LinearLayout titleLinearLayout, dishSaleLinearLayout;
    private boolean isShow;
    private TextView priceTextView ;
    private TitleTextView titleDishesTextView;
    private int scrollHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //title linearlayout
        titleLinearLayout = (LinearLayout)findViewById(R.id.title_linearlayout_id);
        //菜品名称和售卖数量linearlayout
        dishSaleLinearLayout = (LinearLayout) findViewById(R.id.dishes_sale_count_LinearLayout);
        //设置title linearlayout透明
        titleLinearLayout.getBackground().setAlpha(0);
        //价格
        priceTextView = (TextView)findViewById(R.id.price);
        //title菜品名称
        titleDishesTextView = (TitleTextView)findViewById(R.id.title_dishes_id);
        //图片
        proportionImageView = (ProportionImageView)findViewById(R.id.imageview_id);
        proportionImageView.setRatio(R.drawable.dan);
        customScrollView = (CustomScrollView)findViewById(R.id.scrollview);
        customScrollView.setScrollViewInterface(new ScrollViewInterface() {
            @Override
            public void scrollStatus(int t, int oldt) {
                //y轴移动了多高
                int  yheight = customScrollView.getScrollY();
                //图片的高度减去titleLinearLayout的高度，滑动到这个高度的时候显示title
                if(scrollHeight == 0)
                    scrollHeight = proportionImageView.getMeasuredHeight() - measureHeight(titleLinearLayout);
                //设置title名字的默认偏移量
                titleDishesTextView.init(measureHeight(titleLinearLayout));
                //滚动的高度大于scrollHeight
                if(yheight >= scrollHeight){
                    //没显示
                    if(!isShow){
                        isShow = true;
                        titleLinearLayout.getBackground().setAlpha(250);
                        //渐变显示
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bian);
                        titleLinearLayout.startAnimation(animation);
                    }
                    //如果是向上滚动设置偏移量
                    if(t > oldt){
                        titleDishesTextView.setOffset(t - oldt);
                    }
                } else {
                    titleLinearLayout.getBackground().setAlpha(0);
                    isShow = false;
                }
                //滚动的高度大于scrollHeight+菜品和数量textview 高度的时候显示隐藏的价格textview
                if(yheight >= scrollHeight + measureHeight(dishSaleLinearLayout)){
                    priceTextView.setVisibility(View.VISIBLE);
                } else {
                    priceTextView.setVisibility(View.GONE);
                    if(titleDishesTextView.getVisibility() == View.VISIBLE && t < oldt) {
                        titleDishesTextView.setOffset(t - oldt);
                    }
                }
            }
        });
    }

    /**
     * 计算view的高度
     * @param textView
     * @return
     */
    public int measureHeight(View textView){
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(ViewGroup.LayoutParams.MATCH_PARENT, height);
        return textView.getMeasuredHeight();
    }

}
