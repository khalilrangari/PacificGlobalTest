package com.android.pacificglobal.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.pacificglobal.model.ChartDataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

  MutableLiveData<List<ChartDataModel>> chartDataListLiveData = new MutableLiveData<>();
  MutableLiveData<List<String>> filterEntries = new MutableLiveData<>();

  /**
   * this method is use for setup data into the list of model
   */
  public void setDataForChart() {
    ArrayList<ChartDataModel> list = new ArrayList<>();
    list.add(new ChartDataModel("Jan", 21, 5));
    list.add(new ChartDataModel("Feb", 32, 15));
    list.add(new ChartDataModel("Mar", 19, 11));
    list.add(new ChartDataModel("Apr", 8, 17));
    list.add(new ChartDataModel("May", 26, 5));
    list.add(new ChartDataModel("Jun", 31, 2));
    list.add(new ChartDataModel("Jul", 37, 28));
    list.add(new ChartDataModel("Aug", 16, 14));
    list.add(new ChartDataModel("Sep", 25, 19));
    list.add(new ChartDataModel("Oct", 30, 8));
    list.add(new ChartDataModel("Nov", 15, 7));
    list.add(new ChartDataModel("Dec", 24, 12));
    chartDataListLiveData.postValue(list);

  }

  /**
   * this method is use for retrive data from the list
   * @return list
   */
  public MutableLiveData<List<ChartDataModel>> getChartDataListLiveData() {
    return chartDataListLiveData;
  }

}
