package com.example.jim.demo_all.Animator;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;

import com.example.jim.demo_all.R;

public class LayoutAnimationActivity extends AppCompatActivity implements
        OnCheckedChangeListener{

    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;



    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        viewGroup = (ViewGroup) findViewById(R.id.id_container);

        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        mChangeAppear.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        mDisAppear.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        mChangeDisAppear.setOnCheckedChangeListener((OnCheckedChangeListener) this);

        // 创建一个GridLayout
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(4);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);


    }


    /**
     * 添加按钮
     *
     * @param view
     */
    public void addBtn(View view)
    {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mGridLayout.removeView(button);
            }
        });
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                (mAppear.isChecked() ? ObjectAnimator.ofFloat(this,"scaleX",0,1): null));
                        //这里可以自定义view的动画、
                        // 也可以用默认的 mTransition.getAnimator(LayoutTransition.APPEARING)
        //也可以设置多个动画属性
//        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",0,100,0);
//        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top",1,1);
//        Animator changeAppearAnimator
//                = ObjectAnimator.ofPropertyValuesHolder(layoutTransitionGroup, pvhLeft,pvhBottom,pvhTop,pvhRight);
//        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING,changeAppearAnimator);

        mTransition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (mChangeAppear.isChecked() ? mTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));
        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));

        mGridLayout.setLayoutTransition(mTransition);
    }
}