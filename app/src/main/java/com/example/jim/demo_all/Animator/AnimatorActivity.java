package com.example.jim.demo_all.Animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jim.demo_all.R;

public class AnimatorActivity extends AppCompatActivity {

    private ImageButton ballziyou;
    private ImageButton ballpao;
    private Button btn_set;
    private Animation animationSet;
    private TextView text;

    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;

    private boolean mIsMenuOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ballziyou= (ImageButton) findViewById(R.id.ball_ziyou);
        ballpao= (ImageButton) findViewById(R.id.ball_pao);
        btn_set= (Button) findViewById(R.id.btn_set);
        text= (TextView) findViewById(R.id.test);

        initView();

    }


    private void initView() {
        mMenuButton = (Button) findViewById(R.id.menu);
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsMenuOpen) {
                    mIsMenuOpen = true;
                    doAnimateOpen(mItemButton1, 0, 5, 300);
                    doAnimateOpen(mItemButton2, 1, 5, 300);
                    doAnimateOpen(mItemButton3, 2, 5, 300);
                    doAnimateOpen(mItemButton4, 3, 5, 300);
                    doAnimateOpen(mItemButton5, 4, 5, 300);
                } else {
                    mIsMenuOpen = false;
                    doAnimateClose(mItemButton1, 0, 5, 300);
                    doAnimateClose(mItemButton2, 1, 5, 300);
                    doAnimateClose(mItemButton3, 2, 5, 300);
                    doAnimateClose(mItemButton4, 3, 5, 300);
                    doAnimateClose(mItemButton5, 4, 5, 300);
//                    ObjectAnimator.ofFloat(mMenuButton, "translationX", 0, 0).setDuration(500)
//                            .start();


                }

            }
        });

        mItemButton1 = (Button) findViewById(R.id.item1);
        mItemButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "你点击了buttom" + 1, Toast.LENGTH_SHORT).show();
            }
        });

        mItemButton2 = (Button) findViewById(R.id.item2);
        mItemButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "你点击了buttom" + 2, Toast.LENGTH_SHORT).show();
            }
        });
        mItemButton3 = (Button) findViewById(R.id.item3);
        mItemButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "你点击了buttom" + 3, Toast.LENGTH_SHORT).show();
            }
        });
        mItemButton4 = (Button) findViewById(R.id.item4);
        mItemButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "你点击了buttom" +4, Toast.LENGTH_SHORT).show();
            }
        });
        mItemButton5 = (Button) findViewById(R.id.item5);
        mItemButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "你点击了buttom" + 5, Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        view.setClickable(true);
        double degree = Math.toRadians(90)/(total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为 500ms
        set.setDuration(1 * 500).start();
    }

    private void doAnimateClose(final View view, int index, int total,
                                int radius) {

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));

        set.setDuration(1 * 500).start();
        view.setClickable(false);

    }

    //测试属性动画效果
    public void rotateyAnimRun(final View view){
        ObjectAnimator anim=ObjectAnimator
                .ofFloat(view,"RotationX",1.0f,0.0f)
                .setDuration(1500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cval= (float) animation.getAnimatedValue();
                Log.d("cval的值为", ": "+cval);
                view.setAlpha(cval);
                view.setRotationX(cval);
                view.setRotationY(cval);
                view.setScaleX(cval);
                view.setScaleY(cval);

            }
        });
    }
    //测试xml文件的动画集运用
    public void btn_set(View view){
        animationSet= AnimationUtils.loadAnimation(this,R.anim.animationset);
        text.startAnimation(animationSet);
    }

    //用propertyValuesHolder来展示多个动画
    public void propertyValuesHolder(View view){
        PropertyValuesHolder pvhx=PropertyValuesHolder.ofFloat("rotationX",0.0f,360.0f);
        PropertyValuesHolder pvhy=PropertyValuesHolder.ofFloat("rotationY",0.0f,360.0f);
        PropertyValuesHolder pvha=PropertyValuesHolder.ofFloat("scaleX",1.0f,0.0f);
        PropertyValuesHolder pvhtx=PropertyValuesHolder.ofFloat("TranslationX",80.0f,100.0f);
        PropertyValuesHolder pvhty=PropertyValuesHolder.ofFloat("TranslationY",80.0f,100.0f);
        ObjectAnimator.ofPropertyValuesHolder(view,pvha,pvhtx,pvhty,pvhx,pvhy).setDuration(500).start();
    }
    //抛物线动画效果
    public void ballPao(View view){
        ValueAnimator animator=new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0,0));
        animator.setInterpolator(new LinearInterpolator());//设置变化速率为均匀
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue){
                Log.e(" ", fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                ballpao.setX(point.x);
                ballpao.setY(point.y);
            }
        });

    }
    //自由落体动画效果
    public void ballZiyou(View view){
        ValueAnimator animator=ValueAnimator.ofFloat(0,2000);
        animator.setTarget(ballziyou);
        animator.setDuration(1500).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ballziyou.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }
    //测试布局动画
    public void layout_animation(View view){
        Intent intent=new Intent(AnimatorActivity.this,LayoutAnimationActivity.class);
        startActivity(intent);
    }


    public void listview_anim(View view){
        Intent intent_listview_anim=new Intent(AnimatorActivity.this,Anim_Listview_Activity.class);
        startActivity(intent_listview_anim);
    }
}
