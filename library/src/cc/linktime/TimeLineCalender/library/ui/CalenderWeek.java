package cc.linktime.TimeLineCalender.library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.TimeLineCalender.library.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeek extends ViewGroup {
    private int totalWidth;
    private int totalHeight;
    private int bodyWidth;
    private int sideWidth;

    private View weekbody;
    private View weekside;

    public CalenderWeek(Context context) {
        super(context);
    }

    public CalenderWeek(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
//        weekbody = layoutInflater.inflate(R.layout.week_body,null);
//        weekside = layoutInflater.inflate(R.layout.week_side,null);
//        this.addView(weekbody);
//        this.addView(weekside);
    }

    public CalenderWeek(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("Week", "Week --- onMeasure " + totalHeight + " " + totalWidth);

        sideWidth = totalWidth / 5;
        bodyWidth = sideWidth * 4;

        //findViewById(R.id.weekside).measure(sideWidth,totalHeight);
        //findViewById(R.id.weekbody).measure(bodyWidth,totalHeight);

        CalenderWeekSide weekside = (CalenderWeekSide)findViewById(R.id.weekside);
        CalenderWeekBody weekBody = (CalenderWeekBody)findViewById(R.id.weekbody);

        Log.i("Week", "Week --- onMeasure sideWidth " + sideWidth);
        weekside.measure(sideWidth, totalHeight);
        weekBody.measure(bodyWidth, totalHeight);

        weekside.setCursorListener(weekBody);
        weekside.setEventListListener((CalenderTimeLineEventList)weekBody.findViewById(R.id.timeline_eventlist));

        setMeasuredDimension(totalWidth, totalHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        findViewById(R.id.weekbody).layout(0,0,bodyWidth,totalHeight);
        findViewById(R.id.weekside).layout(bodyWidth,0,totalWidth,totalHeight);
    }

    public ArrayList<ArrayList<CalenderTimeLineEvent>> getEventList() {
        return ((CalenderTimeLineEventList)findViewById(R.id.weekbody).findViewById(R.id.timeline_eventlist)).getEvenList();
    }

    public void setEventList(ArrayList<ArrayList<CalenderTimeLineEvent>> eventList){
        /**
         * 事件列表向量数组必须等于7，对应一周七天
         */
        ((CalenderTimeLineEventList)findViewById(R.id.weekbody).findViewById(R.id.timeline_eventlist)).setEvenList(eventList);
    }
}