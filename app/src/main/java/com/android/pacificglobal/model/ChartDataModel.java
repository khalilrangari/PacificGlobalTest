package com.android.pacificglobal.model;

public class ChartDataModel {
  String month;
  int salesFigure;
  int purchaseFigure;

  public ChartDataModel(String month, int salesFigure, int purchaseFigure) {
    this.month = month;
    this.salesFigure = salesFigure;
    this.purchaseFigure = purchaseFigure;
  }

  public String getMonth() {
    return month;
  }

  public int getSalesFigure() {
    return salesFigure;
  }

  public int getPurchaseFigure() {
    return purchaseFigure;
  }
}
