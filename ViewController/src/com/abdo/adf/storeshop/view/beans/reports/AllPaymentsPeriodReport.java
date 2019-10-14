package com.abdo.adf.storeshop.view.beans.reports;


import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;


public class AllPaymentsPeriodReport extends ReportBase {
    private Number totalPrice;

    public AllPaymentsPeriodReport() {
        super();
    }

    public String sumTotal() {
        Long totalPriceSum = 0L;
        ViewObject vo = getViewObjectFromIteratorName("AllPaymentsPeriodReport1Iterator");
        vo.setRangeSize(-1);
        Row[] rows = vo.getAllRowsInRange();
        for (Row row : rows) {
            Number totalPrice = (Number)row.getAttribute("AllTotalPrice");
            totalPriceSum += totalPrice.longValue();
        }
        setTotalPrice(new Number(totalPriceSum));

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
        columns.add("PaymentTypeName");
        columns.add("AllTotalPrice");
        Row[]rows=getAllRowsFromIterator("AllPaymentsPeriodReport1Iterator");
        
        Map m=new LinkedHashMap();
        if(getTotalPrice()!=null){
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
           
           
            
            setOtherData(m);
        }
        return buildReport(columns,rows,"ALL_PAYMENTS_PERIOD_REPORT");
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
}
