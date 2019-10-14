package com.abdo.adf.storeshop.view.beans.reports;


import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;


public class BillSaleReport extends ReportBase {
    private Number totalPrice;
    private Number totalDiscount;
    public BillSaleReport() {
        super();
    }

    public String sumTotal() {
        Long totalPriceSum=0L;
        Long totalDiscountSum=0L;
        ViewObject vo = getViewObjectFromIteratorName("BillSaleReport1Iterator");
        vo.setRangeSize(-1);
        Row[] rows = vo.getAllRowsInRange();
        for (Row row : rows) {
           Number totalPrice = (Number)row.getAttribute("TotalPrice");
            totalPriceSum+=totalPrice.longValue();
           Number totalDiscount = (Number)row.getAttribute("Discount");
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
        columns.add("SaleDate");
        columns.add("CompanyName");
        columns.add("TotalPrice");
        columns.add("Discount");
        Row[]rows=getAllRowsFromIterator("BillSaleReport1Iterator");
        
        Map m=new LinkedHashMap();
        if(getTotalPrice()!=null && getTotalDiscount()!=null){
            Object fromDate = resolveExpression("#{bindings.FROM_DATE.inputValue}");
            Object toDate = resolveExpression("#{bindings.TO_DATE.inputValue}");
            if(fromDate!=null){
                String fDate=parseDateFormat((Date)fromDate);
                m.put("FROM_DATE_LABEL", fDate);
            }
            if(toDate!=null){
                String tDate=parseDateFormat((Date)toDate);
                m.put("TO_DATE_LABEL", tDate);
            }
            m.put("TOTAL_PRICE", getTotalPrice().toString());  
            m.put("TOTAL_DISCOUNT", getTotalDiscount().toString()); 
            setOtherData(m);
        }
        return buildReport(columns,rows,"BILL_SALES_REPORT");
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
