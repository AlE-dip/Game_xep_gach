package com.dipale.xepgach;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.nio.file.Watchable;
import java.util.ArrayList;

public class CellAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    Gach gach;
    Tuong tuong;
    int count;

    public CellAdapter(Context context, int layout, Gach gach, Tuong tuong, int count) {
        this.context = context;
        this.layout = layout;
        this.gach = gach;
        this.tuong = tuong;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView cell;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layout, null);

            holder.cell = (ImageView) convertView.findViewById(R.id.imgCell);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        for (int i = 0; i < 3; i++) {
            if (gach != null && (position == gach.getTam() || position == gach.getCanh()[i])) {
                holder.cell.setBackgroundResource(R.drawable.cell_w);
            }
        }
        if(gach != null && getCount() == 8){
            Log.d("ca","c0:"+gach.getCanh()[0]+" c1:"+gach.getCanh()[1]+" c2:"+gach.getCanh()[2]);
        }
        if(getCount() == 200){
            for (int cell : tuong.getWall()) {
                if (position == cell) {
                    holder.cell.setBackgroundResource(R.drawable.cell_w2);
                }
            }
        }

        return convertView;
    }
}
