package com.abdo.adf.storeshop.view.beans.reports;


import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;


public class ReceiptsSupplierPeriodReport extends ReportBase {
    private Number totalPrice;
    private Number totalDiscount;
    public ReceiptsSupplierPeriodReport() {
        super();
    }

    public String sumTotal() {
        Long totalPriceSum=0L;
        Long totalDiscountSum=0L;
        ViewObject vo = getViewObjectFromIteratorName("ReceiptsWithSuppliersPeriod1Iterator");
        vo.setRangeSize(-1);
        Row[] rows = vo.getAllRowsInRange();
        for (Row row : rows) {
           Number totalPrice = (Number)row.getAttribute("ReceiptTotalPrice");
            totalPriceSum+=totalPrice.longValue();
           Number totalDiscount = (Number)row.getAttribute("ReceiptDiscount");
            totalDiscountSum+=totalDiscount.longValue();
        }
        setTotalPrice(new Number(totalPriceSum));
        setTotalDiscount(new Number(totalDiscountSum));
        
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
        columns.add("Id");
        columns.add("CompanyName");
        columns.add("ReceiptDate");
        columns.add("ReceiptTotalPrice");
        columns.add("ReceiptDiscount");
        Row[]rows=getAllRowsFromIterator("ReceiptsWithSuppliersPeriod1Iterator");
        // show total price ,total discount
        Map m=new HashMap();
        if(getTotalPrice()!=null && getTotalDiscount()!=null){
            m.put("TOTAL_DISCOUNT", getTotalDiscount().toString()); 
            m.put("TOTAL_PRICE", getTotalPrice().toString());  
            setOtherData(m);
        }
        
        return buildReport(columns,rows,"RECEIPTS_SUPPLIERS_PERIOD");
    }
    
    /*
     * End report
     */

    public void setTotalPrice(Number totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Number getTotalPrice() {
        return totalPrice;
    }

    public void setTotalDiscount(Number totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Number getTotalDiscount() {
        return totalDiscount;
    }
}
