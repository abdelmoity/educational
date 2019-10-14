package com.abdo.adf.storeshop.view.beans.reports;


import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;


public class BillSalePrintReport extends ReportBase {
    private Number totalPriceMaster;
    private Number totalDiscountMaster;
    private String customerName;
    private String saleDateOperation;
    private Number billNo;

    public BillSalePrintReport() {
        super();
    }

    public String sumTotal() {

        ViewObject vo = getViewObjectFromIteratorName("BillSalePrintReport1Iterator");
        vo.setRangeSize(-1);
        Row[] rows = vo.getAllRowsInRange();
        if (rows.length > 0) {
            Number totalPrice = (Number)rows[0].getAttribute("TotalPriceMaster");
            Number totalDiscount = (Number)rows[0].getAttribute("TotalDiscountMaster");
            setTotalPriceMaster(totalPrice);
            setTotalDiscountMaster(totalDiscount);
            Date saledate = (Date)rows[0].getAttribute("SaleDate");
            setSaleDateOperation(parseDateFormat(saledate));
            setCustomerName((String)rows[0].getAttribute("CompanyName"));
            setBillNo((Number)rows[0].getAttribute("BillSaleId"));
        }
        return null;
    }

    /*
     * start Report
     */

    @Override
    public ReportBuilder getClassName() {
        return initReportBuilder();
    }


    public ReportBuilder initReportBuilder() {

        ArrayList<String> columns = new ArrayList<String>();
//        columns.add("BillSaleId");
//        columns.add("SaleDate");
        columns.add("CupeName");
        columns.add("Quantity");
        columns.add("TotalPrice");
        columns.add("Discount");
        Row[] rows = getAllRowsFromIterator("BillSalePrintReport1Iterator");

        Map m = new LinkedHashMap();
        if (customerName != null) {
            m.put("CompanyName", customerName);
        }
        if (saleDateOperation != null) {
            m.put("SaleDatePrint", saleDateOperation);
        }
        if (getTotalPriceMaster() != null) {
            m.put("TotalPriceMaster", getTotalPriceMaster().toString());
        }
        if (getTotalDiscountMaster() != null) {
            m.put("TotalDiscountMaster", getTotalDiscountMaster().toString());
        }
        if (getBillNo() != null) {
            m.put("BillSaleId", getBillNo().toString());
        }

        setSalePrintMap(m);

        return buildReport(columns, rows, "BillSalePrintReport");
    }
    
    

    /*
     * End report
     */

    public void setTotalPriceMaster(Number totalPrice) {
        this.totalPriceMaster = totalPrice;
    }

    public Number getTotalPriceMaster() {
        return totalPriceMaster;
    }

    public void setTotalDiscountMaster(Number totalDiscount) {
        this.totalDiscountMaster = totalDiscount;
    }

    public Number getTotalDiscountMaster() {
        return totalDiscountMaster;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setSaleDateOperation(String saleDateOperation) {
        this.saleDateOperation = saleDateOperation;
    }

    public String getSaleDateOperation() {
        return saleDateOperation;
    }

    public void setBillNo(Number billNo) {
        this.billNo = billNo;
    }

    public Number getBillNo() {
        return billNo;
    }
}
