package com.demo.weatherdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.weatherdemo.R;
import com.demo.weatherdemo.bean.AirBean;
import com.demo.weatherdemo.bean.AlarmBean;
import com.demo.weatherdemo.bean.AllInfoBean;
import com.demo.weatherdemo.bean.DayBean;
import com.demo.weatherdemo.bean.HourBean;
import com.demo.weatherdemo.bean.IPBean;
import com.demo.weatherdemo.bean.IndexBean;
import com.demo.weatherdemo.bean.RealTimeBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AllinfoAdapter extends RecyclerView.Adapter {
    private static final String TAG = "AllinfoAdapter";
    private AllInfoBean allInfoBean;
    private Context context;
    private LayoutInflater inflater;
    public static final int REALTIME = 0;
    public static final int HOUR = 1;
    public static final int DAY = 2;
    public static final int INDEX = 3;
    public static final int INFO = 26;
    public static final int SUN = 27;
    public static final int TIPS = 28;
    public static final int ABOUT = 29;
    public AllinfoAdapter(AllInfoBean allInfoBean,Context context) {
        this.allInfoBean = allInfoBean;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case REALTIME:
                return new RealTimeViewHolder(inflater.inflate(R.layout.realtime,null) , context);
            case HOUR:
                return new AllHourViewHolder(inflater.inflate(R.layout.allhour,null) , context);
            case DAY:
                return new AllDayViewHolder(inflater.inflate(R.layout.allday,null) , context);
            case INDEX:
                return new IndexViewHolder(inflater.inflate(R.layout.index, parent,false),context);
            case INFO:
            case SUN:
            case TIPS:
            case ABOUT:
            default:
                return new IndexViewHolder(inflater.inflate(R.layout.index,parent,false),context);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RealTimeViewHolder) {
            RealTimeViewHolder realTimeViewHolder = (RealTimeViewHolder)holder;
            realTimeViewHolder.setData(allInfoBean.getRealTimeBean(),allInfoBean.getDayBeans(),allInfoBean.getAirBean(),allInfoBean.getIpBean(),allInfoBean.getAlarmBean());
        }
        if(holder instanceof AllHourViewHolder) {
            AllHourViewHolder allHourViewHolder = (AllHourViewHolder)holder;
            allHourViewHolder.setData(allInfoBean.getHourBeans());
        }
        if(holder instanceof AllDayViewHolder) {
            AllDayViewHolder allDayViewHolder = (AllDayViewHolder)holder;
            allDayViewHolder.setData(allInfoBean.getDayBeans());
        }
        if(holder instanceof IndexViewHolder) {
            IndexViewHolder indexViewHolder = (IndexViewHolder)holder;
            indexViewHolder.setData(allInfoBean.getIndexBeans().get(position - 3));
        }
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case REALTIME:
                return REALTIME;
            case HOUR:
                return HOUR;
            case DAY:
                return DAY;
            case INDEX:
                return INDEX;
            case INFO:
                return INFO;
            case SUN:
                return SUN;
            case TIPS:
                return TIPS;
            case ABOUT:
                return ABOUT;
            default:
                return position;
        }
    }

    private class RealTimeViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private TextView city;
        private TextView degree;
        private TextView day_degree;
        private TextView weather;
        private TextView alarm;
        private ImageView icon;
        public RealTimeViewHolder (@NonNull View itemView , Context context) {
            super(itemView);
            this.context = context;
            city = itemView.findViewById(R.id.city);
            degree = itemView.findViewById(R.id.degree);
            day_degree = itemView.findViewById(R.id.day_degree);
            weather = itemView.findViewById(R.id.weather);
            alarm = itemView.findViewById(R.id.alarm);
            icon = itemView.findViewById(R.id.icon);
        }
        @SuppressLint("SetTextI18n")
        void setData(RealTimeBean realTimeBean , List<DayBean> dayBeans , AirBean airBean , IPBean ipBean , AlarmBean alarmBean){
            Date date = new Date();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
            for (DayBean dayBean : dayBeans) {
                if (dayBean.getTime().equals(dateFormat.format(date))){
                    day_degree.setText(dayBean.getMax_degree() + "℃ / " + dayBean.getMin_degree() + "℃");
                    break;
                }
            }
            city.setText(ipBean.getCity());
            degree.setText(realTimeBean.getDegree());
            weather.setText(realTimeBean.getWeather() + " 空气" + airBean.getAqi_name());
            if (alarmBean.getType_name() != null && !alarmBean.getType_name().isEmpty()){
                alarm.setText(alarmBean.getType_name() + alarmBean.getLevel_name() + "预警");
                alarm.setVisibility(View.VISIBLE);
            }
            try {
                URL url = new URL("https://mat1.gtimg.com/pingjs/ext2020/weather/pc/icon/currentweather/day/" + realTimeBean.getWeather_code() + ".png");
                Glide.with(context)
                        .load(url)
                        .into(icon);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private class AllHourViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        public AllHourViewHolder (@NonNull View itemView , Context context) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.allhour);
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        void setData(List<HourBean> hourBeans){
            AllHourAdapter adapter = new AllHourAdapter(hourBeans,context);
            recyclerView.setAdapter(adapter);
        }
    }

    private class AllDayViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        public AllDayViewHolder (@NonNull View itemView , Context context) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.allday);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        void setData(List<DayBean> dayBeans){
            AllDayAdapter adapter = new AllDayAdapter(dayBeans,context);
            recyclerView.setAdapter(adapter);
        }
    }

    private class IndexViewHolder extends RecyclerView.ViewHolder{
        private int width;
        private int height;
        private ConstraintLayout constraintLayout;
        private TextView name;
        private TextView info;
        private TextView detail;
        private ImageView icon;
        public IndexViewHolder (@NonNull View itemView , Context context) {
            super(itemView);
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            width = wm.getDefaultDisplay().getWidth();
            height = wm.getDefaultDisplay().getHeight();
            constraintLayout = itemView.findViewById(R.id.index_layout);
            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            detail = itemView.findViewById(R.id.detail);
            icon = itemView.findViewById(R.id.icon);
        }
        void setData(final IndexBean indexBean){
            name.setText(indexBean.getName());
            info.setText(indexBean.getInfo());
//            detail.setText(indexBean.getDetail());
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Toast.makeText(context,indexBean.getName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class AllInfoViewHolder extends RecyclerView.ViewHolder{
        public AllInfoViewHolder (@NonNull View itemView) {
            super(itemView);
        }
        void setData(){

        }
    }
}
