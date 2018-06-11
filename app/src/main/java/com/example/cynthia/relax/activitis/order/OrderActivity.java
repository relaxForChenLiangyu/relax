package com.example.cynthia.relax.activitis.order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderActivity;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.Type;
import com.example.cynthia.relax.presenters.OrderPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderView {
    String[] timeDots = {"10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00", "19:00", "20:00", "21:00"};
    DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    Date dateStartTime = new Date();
    Date dateEndTime = new Date();
    Intent intent;
    Bundle bundle;
    SpecialistBean specialistBean;
    OrderPresenter orderPresenter;
    Integer startTimeInt = 0;
    String selectedTime = "10:00";
    String Money = "100";
    SharedPreferences sharedPreferences;
    Integer userId;
    Integer selectedType = 0;

    @Bind(R.id.OspecialistName)
    TextView OspecialistName;
    @Bind(R.id.Otype)
    Spinner Otype;
    @Bind(R.id.OorderDate)
    TextView OorderDate;
    @Bind(R.id.OstartTime)
    Spinner OstartTime;
    @Bind(R.id.OendTime)
    Spinner OendTime;
    @Bind(R.id.Osum)
    TextView Osum;
    @Bind(R.id.Odescription)
    EditText Odescription;
    @Bind(R.id.OpayBtn)
    Button OpayBtn;
    @Bind(R.id.OcancelBtn)
    Button OcancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("预约");
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        orderPresenter = new OrderPresenter(this);
        Osum.setText("￥ 100");
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 1);
        intent = getIntent();
        bundle = intent.getExtras();
        specialistBean = (SpecialistBean) bundle.getSerializable("specialistBean");
        OspecialistName.setText(specialistBean.getRealName());
        selectedTime = intent.getStringExtra("selectedTimeSlot");
        orderPresenter.setType(specialistBean.getSpecialistId());
        Calendar now = Calendar.getInstance();
        OorderDate.setText(now.get(Calendar.YEAR) + "." + intent.getStringExtra("selectedDate"));
        try {
            formatStartTime(OorderDate.getText().toString() + " " + selectedTime + ":00");
            formatEndTime(OorderDate.getText().toString() + " " + String.valueOf(Integer.parseInt(selectedTime.split(":")[0]) + 1) + ":00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setStartTimes();
        OcancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        OpayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Odescription.getText() == null)
                    showMsg("请填写简单的问题描述！");
                else {
                    orderPresenter.placeOrder(userId, specialistBean.getSpecialistId(), selectedType, Money, Odescription.getText().toString(), dateStartTime, dateEndTime);
                    startActivity(new Intent(OrderActivity.this, HistoryOrderActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void displayTypes(final String[] types) {
        ArrayAdapter<String> typeAdatper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types);
        Otype.setAdapter(typeAdatper);
        Otype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setType(types[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setType(String setType) {
        selectedType = Type.getTypeByTitle(setType);
    }

    @Override
    public void setStartTimes() {
        final char[] everySpot = intent.getStringExtra("selectedDayStatus").toCharArray();
        List<String> starts = new ArrayList<>();
        for (int i = 0; i < everySpot.length; i++) {
            if (everySpot[i] == '0' && i == 0) {
                starts.add(timeDots[0]);
            } else if (everySpot[i] == '0' && i < 5 && i > 0) {
                starts.add(timeDots[i + 1]);
            } else if (everySpot[i] == '0' && i > 4) {
                starts.add(timeDots[i + 2]);
            }
        }
        final String[] startTimes = arrayToString(starts);
        ArrayAdapter<String> startTimeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, startTimes);
        OstartTime.setAdapter(startTimeAdapter);
        for (int i = 0; i < startTimes.length; i++)
            if (selectedTime.equals(startTimes[i]))
                OstartTime.setSelection(i);
        OstartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    formatStartTime(OorderDate.getText().toString() + " " + startTimes[position] + ":00");
                    formatEndTime(OorderDate.getText().toString() + " " + String.valueOf(Integer.parseInt(startTimes[position].split(":")[0]) + 1) + ":00:00");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (startTimes[position]) {
                    case "10:00":
                        setEndTimes(new String[]{"11:00"});
                        break;
                    case "16:00":
                        setEndTimes(new String[]{"17:00"});
                        break;
                    case "19:00":
                        if (everySpot[6] == 0)
                            setEndTimes(new String[]{"20:00", "21:00"});
                        else
                            setEndTimes(new String[]{"20:00"});
                        break;
                    default:
                        final List<String> endTime = new ArrayList<>();
                        for (int i = 2; i < 5; i++) {
                            if (startTimes[position].equals(timeDots[i])) {
                                for (int j = i - 1; j < 5; j++) {
                                    if (everySpot[j] == '0') {
                                        endTime.add(timeDots[j + 2]);
                                    } else
                                        break;
                                }
                            }
                        }
                        startTimeInt = Integer.parseInt(startTimes[position].split(":")[0]);
                        final String[] endTimes = arrayToString(endTime);
                        setEndTimes(endTimes);
                        OendTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    formatEndTime(OorderDate.getText().toString() + " " + endTimes[position] + ":00");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Money = Integer.toString(100 * (Integer.parseInt(endTimes[position].split(":")[0]) - startTimeInt));
                                Osum.setText("￥ " + Money);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setEndTimes(String[] endTimes) {
        ArrayAdapter<String> endTimeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, endTimes);
        OendTime.setAdapter(endTimeAdapter);
    }

    public String[] arrayToString(List<String> para) {
        String[] result = new String[para.size()];
        for (int i = 0; i < para.size(); i++)
            result[i] = para.get(i);
        return result;
    }

    @Override
    public void formatStartTime(String strStart) throws ParseException {
        dateStartTime = df.parse(strStart);
    }

    @Override
    public void formatEndTime(String strEnd) throws ParseException {
        dateEndTime = df.parse(strEnd);
    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(OrderActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
