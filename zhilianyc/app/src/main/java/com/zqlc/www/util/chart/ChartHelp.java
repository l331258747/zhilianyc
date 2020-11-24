package com.zqlc.www.util.chart;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.zqlc.www.R;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

public class ChartHelp {

    Context mContext;
    LineChart lineChart;

    public ChartHelp(Context context, LineChart lineChart) {
        mContext = context;
        this.lineChart = lineChart;
    }

    /**
     * 展示曲线
     *
     * @param datas 数据集合
     * @param name     曲线名称
     */
    public void showLineChart(String name,List<ChartLineBean> datas) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            ChartLineBean data = datas.get(i);
            Entry entry = new Entry(i, data.getNum());
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    public void initLineChart(List<ChartLineBean> datas) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //取消缩放
        lineChart.setScaleEnabled(false);
        //设置XY轴动画效果
        lineChart.animateY(1500);
        lineChart.animateX(1500);

        //描述
        Description description = lineChart.getDescription();
        description.setEnabled(false);

        /***XY轴的设置***/
        XAxis xAxis = lineChart.getXAxis();
        YAxis leftYAxis = lineChart.getAxisLeft();
        YAxis rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(ContextCompat.getColor(mContext, R.color.color_text));
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setTextColor(ContextCompat.getColor(mContext,R.color.color_text));
        //但还是显示了网格线，而且不是我们想要的 虚线 。其实那是 X Y轴自己的网格线，禁掉即可

        leftYAxis.setDrawGridLines(true);
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);


        rightYaxis.setEnabled(false);

        /***折线图例 标签 设置***/
        Legend legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //自动换行
        legend.setWordWrapEnabled(false);
        //不显示legend
        legend.setEnabled(true);


        //传入X轴的值，所以自定义X轴的值可以 写在该方法内
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return datas.get((int) value).getName();
            }
        });
    }


    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     */
    private void initLineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setColor(ContextCompat.getColor(mContext,R.color.color_368feb));
        lineDataSet.setCircleColor(ContextCompat.getColor(mContext,R.color.color_368feb));
        lineDataSet.setValueTextColor(ContextCompat.getColor(mContext,R.color.color_text));
        lineDataSet.setDrawValues(false);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        //设置曲线展示为圆滑曲线（如果不设置则默认折线）
        //lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    }
}
