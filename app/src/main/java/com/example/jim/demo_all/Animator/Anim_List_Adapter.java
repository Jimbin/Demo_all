package com.example.jim.demo_all.Animator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jim.demo_all.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim斌 on 2017/6/4.
 */

public class Anim_List_Adapter extends BaseAdapter {
    private List<Drawable> mDrawableList=new ArrayList<>();
    private int mlength=0;
    private LayoutInflater minflater;
    private Context mcontext;
    private ListView mlistview;

    private Animation animation;
    private int mFirstPosition=0;
    private int mFirstTop=0;


    private boolean isScrollDown=false;
    public Anim_List_Adapter(Context context,ListView listView,List<Drawable> drawables,int length){
        mcontext=context;
        mlistview=listView;
        mlength=length;
        mDrawableList=drawables;
        minflater=LayoutInflater.from(context);

        animation= AnimationUtils.loadAnimation(mcontext,R.anim.bottom_in_anim);
        mlistview.setOnScrollListener(scrolllistener);

    }

    AbsListView.OnScrollListener scrolllistener=new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstChild = view.getChildAt(0);
            if (firstChild == null) return;
            int top = firstChild.getTop();
            /**
             * firstVisibleItem > mFirstPosition表示向下滑动一整个Item
             * mFirstTop > top表示在当前这个item中滑动
             */
            isScrollDown = firstVisibleItem > mFirstPosition || mFirstTop > top;
            mFirstTop = top;
            mFirstPosition = firstVisibleItem;
        }
    };
    @Override
    public int getCount() {
        return mlength;
    }

    @Override
    public Object getItem(int position) {
        return mDrawableList.get(position%mDrawableList.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;

        if (convertView==null){
            holder=new ViewHolder();
            convertView=minflater.inflate(R.layout.anim_item,null);
            holder.mImageview= (ImageView) convertView.findViewById(R.id.anim_image);
            holder.mtextview= (TextView) convertView.findViewById(R.id.anim_text);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
//清除当前显示区域中所有item的动画
        for (int i=0;i<mlistview.getChildCount();i++){
            View view = mlistview.getChildAt(i);
            view.clearAnimation();
        }
        if (isScrollDown) {
            convertView.startAnimation(animation);
        }
        convertView.setTag(holder);
        holder.mImageview.setImageDrawable(mDrawableList.get(position%mDrawableList.size()));
        holder.mtextview.setText(position+" ");
        return convertView;
    }
    public class ViewHolder{
        public ImageView mImageview;
        public TextView mtextview;
    }
}
