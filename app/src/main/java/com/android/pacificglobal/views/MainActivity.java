package com.android.pacificglobal.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.pacificglobal.model.ChartDataModel;
import com.android.pacificglobal.viewmodels.MainActivityViewModel;
import com.android.pacificglobal.R;
import com.android.pacificglobal.databinding.ActivityMainBinding;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = MainActivity.class.getSimpleName().toString();
  private ActivityMainBinding mBinding;
  private MainActivityViewModel mMainViewModel;
  private List<ChartDataModel> dataModelList = new ArrayList<>();
  private HIOptions options;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupView();
    setupViewModel();
    subscribeChartData();
  }

  /**
   * this method is use to get updated data from the view model
   */
  private void subscribeChartData() {
    mMainViewModel.getChartDataListLiveData().observe(this, new Observer<List<ChartDataModel>>() {
      @Override
      public void onChanged(List<ChartDataModel> chartDataModels) {
        dataModelList = chartDataModels;
        createChart(0);
      }
    });
  }

  /**
   * this method is use to create chart and display and handle
   *
   * @param position
   */
  private void createChart(int position) {

    options = new HIOptions();

    HIChart chart = new HIChart();
    chart.setType("column");
    options.setChart(chart);

    HITitle title = new HITitle();
    title.setText("Demo chart");

    options.setTitle(title);

    final HIXAxis hixAxis = new HIXAxis();
    ArrayList<String> categories = new ArrayList<>();
    for (int i = 0; i < dataModelList.size(); i++) {
      categories.add(dataModelList.get(i).getMonth());
    }

    hixAxis.setCategories(categories);
    options.setXAxis(new ArrayList() {{
      add(hixAxis);
    }});

    ArrayList<Integer> purchaseValue = new ArrayList<>();
    for (int i = 0; i < dataModelList.size(); i++) {
      purchaseValue.add(dataModelList.get(i).getPurchaseFigure());
    }

    HIColumn purchaseSeries = new HIColumn();
    purchaseSeries.setName("purchase");
    purchaseSeries.setData(new ArrayList<>(purchaseValue));

    ArrayList<Integer> salesValue = new ArrayList<>();
    for (int i = 0; i < dataModelList.size(); i++) {
      salesValue.add(dataModelList.get(i).getSalesFigure());
    }

    HIColumn salesSeries = new HIColumn();
    salesSeries.setName("sales");
    salesSeries.setData(new ArrayList<>(salesValue));

    ArrayList<HISeries> seriesList = new ArrayList<>();
    seriesList.add(purchaseSeries);
    seriesList.add(salesSeries);

    if (position == 1) {
      seriesList.remove(1);
      options.setSeries(seriesList);
      mBinding.hcView.setOptions(options);
      mBinding.hcView.reload();
    } else if (position == 2) {
      seriesList.remove(0);
      options.setSeries(seriesList);
      mBinding.hcView.setOptions(options);
      mBinding.hcView.reload();
    } else {
      options.setSeries(seriesList);
      mBinding.hcView.setOptions(options);
      mBinding.hcView.reload();
    }

  }

  /**
   * this method is ude to set up data into the view model
   */
  private void setupViewModel() {
    mBinding.setViewModel(mMainViewModel);
    mMainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    mMainViewModel.setDataForChart();
  }

  /**
   * this method is use for setup xml layout view to the java class
   */
  private void setupView() {
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mBinding.spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        createChart(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }
}