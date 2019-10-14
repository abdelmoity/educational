package com.abdo.adf.storeshop.view.beans.reports;


import oracle.adf.view.rich.component.rich.data.RichTable;


public class AllProductsCountPriceReport {
    private RichTable tableReport;

//    @PostConstruct
//    public void init(){
//        OperationBinding oBinding = AdfUtils.getBindingOperation("getAlltList");
//        oBinding.execute();
//    }
    
    public AllProductsCountPriceReport() {
        super();
    }

    public void setTableReport(RichTable tableReport) {
        this.tableReport = tableReport;
    }

    public RichTable getTableReport() {
        return tableReport;
    }
}
