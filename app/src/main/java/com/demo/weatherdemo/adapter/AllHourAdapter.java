package com.demo.weatherdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.weatherdemo.R;
import com.demo.weatherdemo.bean.HourBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AllHourAdapter extends RecyclerView.Adapter {
	private List<HourBean> hourBeans;
	private Context context;
	private LayoutInflater inflater;
	public AllHourAdapter(List<HourBean> hourBeans,Context context){
		this.hourBeans = hourBeans;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
		return new HourViewHolder(inflater.inflate(R.layout.hour,null),context);
	}

	@Override
	public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder, int position) {
		HourViewHolder hourViewHolder = (HourViewHolder)holder;
		hourViewHolder.setData(hourBeans.get(position));
	}

	@Override
	public int getItemCount () {
		return hourBeans.size() / 2;
	}

	private class HourViewHolder extends RecyclerView.ViewHolder{
		private TextView hour;
		private ImageView icon;
		private TextView degree;
		public HourViewHolder (@NonNull View itemView,Context context) {
			super(itemView);
			hour = itemView.findViewById(R.id.hour);
			icon = itemView.findViewById(R.id.icon);
			degree = itemView.findViewById(R.id.degree);
		}
		void setData(HourBean hourBean){
			hour.setText(getTime(hourBean.getUpdate_time()));
			try {
				URL url = new URL("https://mat1.gtimg.com/pingjs/ext2020/weather/pc/icon/currentweather/day/" + hourBean.getWeather_code() + ".png");
				Glide.with(context)
						.load(url)
						.into(icon);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			degree.setText(hourBean.getDegree() + "℃");
		}
	}

	private String getTime(String time){
		return time.substring(8,10) + ":00";
	}
}
