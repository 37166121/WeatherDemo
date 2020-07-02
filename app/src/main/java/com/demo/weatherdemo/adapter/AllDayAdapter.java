package com.demo.weatherdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.weatherdemo.R;
import com.demo.weatherdemo.bean.DayBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AllDayAdapter extends RecyclerView.Adapter {
	private List<DayBean> dayBeans;
	private Context context;
	private LayoutInflater inflater;
	public AllDayAdapter(List<DayBean> dayBeans,Context context){
		this.dayBeans = dayBeans;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
		return new DayViewHolder(inflater.inflate(R.layout.day,null),context);
	}

	@Override
	public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder, int position) {
		DayViewHolder dayViewHolder = (DayViewHolder)holder;
		dayViewHolder.setData(dayBeans.get(position));
	}

	@Override
	public int getItemCount () {
		return dayBeans.size();
	}

	class DayViewHolder extends RecyclerView.ViewHolder{
		private TextView day;
		private ImageView icon;
		private TextView degree;
		public DayViewHolder (@NonNull View itemView , Context context) {
			super(itemView);
			day = itemView.findViewById(R.id.day);
			icon = itemView.findViewById(R.id.icon);
			degree = itemView.findViewById(R.id.degree);
		}

		@SuppressLint("SetTextI18n")
		void setData(DayBean dayBean){
			day.setText(getDay(dayBean.getTime()));
			try {
				URL url = new URL("https://mat1.gtimg.com/pingjs/ext2020/weather/pc/icon/currentweather/day/" + dayBean.getDay_weather_code() + ".png");
				Glide.with(context)
						.load(url)
						.into(icon);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			degree.setText(dayBean.getMax_degree() + "℃ / " + dayBean.getMin_degree() + "℃");
		}
	}

	private String getDay(String day){
		return day.substring(5,10);
	}
}
