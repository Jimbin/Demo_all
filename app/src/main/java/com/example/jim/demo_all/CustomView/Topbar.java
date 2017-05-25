package com.example.jim.demo_all.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jim.demo_all.R;

/**
 * Created by Jim斌 on 2017/4/28.
 */

public class Topbar extends RelativeLayout {
    private Button leftButtom,rightButtom;
    private TextView title;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    private float titleTextSize;
    private int titleTextColor;
    private String titleText;

    private LayoutParams leftparam,rightparam,titleparam;

    private topbarclickListener listener;
    public interface topbarclickListener{
        public void leftClick();
        public void rightClick();
    }
    public void setOnTopbarClickListener(topbarclickListener listener){
        this.listener=listener;
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //用TypeArray这个数据结构通过obtainStyledAttributes方法存取自定义控件的属性，第一个参数将构造方法的attrs传入
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        //将属性关联起来，像R.styleable.Topbar_leftTextColor这种方式
        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        rightTextColor=ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText=ta.getString(R.styleable.Topbar_rightText);

        titleTextSize=ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor=ta.getColor(R.styleable.Topbar_titleTextColor,0);
        titleText=ta.getString(R.styleable.Topbar_title);

        ta.recycle();//关联完属性后回收这个数据结构所占用的内存

        leftButtom=new Button(context);
        rightButtom=new Button(context);
        title=new TextView(context);

        //获取完自定义的属性后把它们赋给控件
        leftButtom.setBackground(leftBackground);
        leftButtom.setTextColor(leftTextColor);
        leftButtom.setText(leftText);
        rightButtom.setBackground(rightBackground);
        rightButtom.setTextColor(rightTextColor);
        rightButtom.setText(rightText);
        title.setText(titleText);
        title.setTextSize(titleTextSize);
        title.setTextColor(titleTextColor);
        title.setGravity(Gravity.CENTER);
//        setBackgroundColor(13434);

        //接下来把控件放在布局上面
        // 首先定义布局的宽高属性
        leftparam=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //增添一条左对齐的规则，左对齐是Relativelayout的属性，所以引用它
        leftparam.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //把控件加入到布局中
        addView(leftButtom,leftparam);

        rightparam=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButtom,rightparam);

        titleparam=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleparam.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(title,titleparam);

        leftButtom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightButtom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }
}
