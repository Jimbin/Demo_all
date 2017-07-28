package com.example.jim.demo_all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jim.demo_all.R;

import java.util.List;

/**
 * Created by Jim斌 on 2017/7/25.
 */

public class Recycle_Adapter extends RecyclerView.Adapter<Recycle_Adapter.ViewHolderA>{
    private Context mContext;
    private List<String> mList;

    public Recycle_Adapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_layout, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    public void onBindViewHolder(ViewHolderA holder, int position) {
        //此处设置Item中view的数据
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }

    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{

        TextView mTextView;
        public ViewHolderA(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycle_textview);
        }
    }
}