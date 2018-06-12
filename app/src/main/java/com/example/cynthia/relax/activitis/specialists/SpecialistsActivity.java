package com.example.cynthia.relax.activitis.specialists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.specialist_detail.SpecialistDetailActivity;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.presenters.SpecialistsPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecialistsActivity extends BaseActivity implements SpecialistView{
    List<SpecialistBean> specialistBeans;

    private RecyclerView specialistRecyclerView;
    private Spinner searchTypeSpinner;
    private Spinner sortKeySpinner;
    private ImageButton refresh;

    private GridLayoutManager gridLayoutManager;
    private SpecialistAdapter specialistAdapter;
    private ArrayAdapter<String> typeSpinnerAdapter;
    private ArrayAdapter<String> sortSpinnerAdapter;
    private SpecialistsPresenter specialistsPresenter;

    private int type = 0;
    private int sort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialists);
        initializeTop(this, true, "专家列表");
        specialistBeans = new ArrayList<>();
        specialistsPresenter = new SpecialistsPresenter(this);

        specialistRecyclerView = (RecyclerView)findViewById(R.id.specialistRecyclerView);
        sortKeySpinner = (Spinner)findViewById(R.id.sortKey);
        searchTypeSpinner = (Spinner)findViewById(R.id.searchByType);
        refresh = (ImageButton)findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent();
                intent.setClass(SpecialistsActivity.this, SpecialistsActivity.class);
                startActivity(intent);
            }
        });
        //new Thread(new SpecialistThread()).start();

        gridLayoutManager = new GridLayoutManager(this,2);
        specialistAdapter = new SpecialistAdapter(specialistBeans,this);

        specialistRecyclerView.setLayoutManager(gridLayoutManager);
        specialistRecyclerView.setAdapter(specialistAdapter);

        typeSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getTypes());
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(typeSpinnerAdapter);
        searchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                getSpecialistData(type,sort);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = 0;
                sort = 0;
            }
        });

        sortSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getSorts());
        sortSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortKeySpinner.setAdapter(sortSpinnerAdapter);
        sortKeySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort = position;
                getSpecialistData(type,sort);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = 0;
                sort = 0;
            }
        });


    }

    public class SpecialistThread implements Runnable {
        @Override
        public void run() {
            getSpecialistData(type,sort);
        }
    }

    @Override
    public void getSpecialistData(int type,int sort){
        specialistsPresenter.getSortedSpecialistByType(type,sort);
    }

    @Override
    public void setSpecialistData(List<SpecialistBean> specialistBeans){
        this.specialistBeans = specialistBeans;
        specialistAdapter.setSpecialistBeanList(this.specialistBeans);
        specialistAdapter.notifyDataSetChanged();

    }

    @Override
    public void showFailedMsg(String msg){
        Toast.makeText(SpecialistsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<String> getTypes(){
        List<String> types = new ArrayList<>();
        types.add("全部分类");
        types.add("婚恋");
        types.add("亲子");
        types.add("职场");
        types.add("健康");
        return types;
    }

    @Override
    public List<String> getSorts(){
        List<String> sorts = new ArrayList<>();
        sorts.add("按评分顺序排序");
        sorts.add("按完成咨询单数排序");
        sorts.add("按从业时长排序");
        return sorts;
    }

    @Override
    public Context context() {
        return this;
    }


}
