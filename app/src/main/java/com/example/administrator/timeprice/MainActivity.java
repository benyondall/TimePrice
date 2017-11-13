package com.example.administrator.timeprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    /*隐藏布局中日期得设定*/
    @BindView(R.id.dialog_tv_date_award)
    TextView dialogTvDateAward;
    /*隐藏布局中得时间设定*/
    @BindView(R.id.dialog_tv_time_award)
    TextView dialogTvTimeAward;
    /*设置日期得布局*/
    @BindView(R.id.ll_data_award)
    LinearLayout llDataAward;
    /*重复中每天得点击事件*/
    @BindView(R.id.tv_evey_day_award)
    TextView tvEveyDayAward;
    /*重复中时间得展示*/
    @BindView(R.id.tv_show_times_award)
    TextView tvShowTimesAward;
    @BindView(R.id.ll_repetition_award)
    LinearLayout llRepetitionAward;
    @BindView(R.id.activity_award_setting)
    LinearLayout activityAwardSetting;


    private CustomDatePicker customDatePicker1, customDatePicker2,customDatePicker3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initDatePicker();
    }

    /*初始化数据*/
    private void initView() {
        boolean isOne = getIntent().getBooleanExtra("Award",true);
    }

    @OnClick({ R.id.dialog_tv_date_award, R.id.dialog_tv_time_award, R.id.ll_data_award, R.id.tv_evey_day_award, R.id.tv_show_times_award, R.id.ll_repetition_award, R.id.activity_award_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_tv_date_award:
                 /*
                * 时间控件日期得设置
                * */
                String data=dialogTvDateAward.getText().toString();
                if(data.equals("请设置日期")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                    String now = sdf.format(new Date());
                    customDatePicker1.show(now);
                }else{
                    // 日期格式为yyyy-MM-dd
                    customDatePicker1.show(dialogTvDateAward.getText().toString());
                }
                break;
            case R.id.dialog_tv_time_award:
                // 日期格式为yyyy-MM-dd HH:mm
                String time=dialogTvTimeAward.getText().toString();
                if(time.equals("请设置时间")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                    String now = sdf.format(new Date());
                    customDatePicker2.show(now);
                }else{
                    // 日期格式为yyyy-MM-dd
                    customDatePicker2.show("2000-09-08 "+dialogTvTimeAward.getText().toString());
                }
                break;
            case R.id.ll_data_award:
                break;
            case R.id.tv_show_times_award:
                String timeShow=tvShowTimesAward.getText().toString();
                if(timeShow.equals("请设置时间")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                    String now = sdf.format(new Date());
                    customDatePicker3.show(now);
                }else{
                    // 日期格式为yyyy-MM-dd
                    customDatePicker3.show("2000-09-08 "+tvShowTimesAward.getText().toString());
                }
                // 日期格式为yyyy-MM-dd HH:mm

                break;
            case R.id.ll_repetition_award:
                break;
            case R.id.activity_award_setting:
                break;

        }
    }





    /*
       *
       * 设置重复当中得时间
       * */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        dialogTvDateAward.setText("请设置日期");
        dialogTvTimeAward.setText("请设置时间");
        tvShowTimesAward.setText("请设置时间");
        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                dialogTvDateAward.setText(time.split(" ")[0]);
            }
        }, now,"2050-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(true); // 不允许循环滚动
        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                dialogTvTimeAward.setText(time.split(" ")[1]);
            }
        }, now,"2050-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动

        customDatePicker3 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvShowTimesAward.setText(time.split(" ")[1]);
            }
        }, "1970-01-01 00:00","2050-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker3.showSpecificTime(true); // 显示时和分
        customDatePicker3.setIsLoop(true); // 允许循环滚动
    }
}
