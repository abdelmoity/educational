package com.abdo.adf.storeshop.view.beans.reports;


import com.abdo.adf.storeshop.view.beans.BaseBean;
import com.abdo.adf.storeshop.view.itext.FontStyle;
import com.abdo.adf.storeshop.view.itext.ReportBuilder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.jbo.Row;


public class ReportBase extends BaseBean {
    private ReportBuilder className;
    private Map otherData;
    private Map salePrintMap;
    private boolean showCommercial=true;
    
    
    public ReportBase() {
        super();
    }
    
    
    public ReportBuilder buildReport(ArrayList<String> columns,Row[]rows,String repName) {        
        className = new ReportBuilder();
        repName = className.loadKey(repName);
        className.setAttributes(columns);
       
        className.setArialFontPath(getArialFontPath());
        Document document = null;
        /*
         * set comm And Tax Card
         */
        if(showCommercial){
            String commAndTax=
                loadKey("COMMERCIAL_ID")+getFromSession("commercialId").toString()+"  "+loadKey("TAX_CARD")+getFromSession("taxCard").toString();
            className.setCommAndTax(commAndTax);
        }
        try {
            /**
             * intiate document and add component
             **/
            document = className.openDocument(columns);
            className.addTitle(document, repName, Element.ALIGN_CENTER,
                               new FontStyle(15, Font.BOLD,BaseColor.BLACK).createFont(),
                               getLogo("logoico.jpg"), Element.ALIGN_RIGHT);
            /*
             * add sale Print Data
             */
            if(salePrintMap!=null && salePrintMap.size()>0){
                className.addSalePrintData(document, salePrintMap);
            }
            
            /*
             * draw Table Content
             */
            document.add(className.drawTable(columns, rows, new FontStyle(12, Font.BOLD,BaseColor.WHITE).createFont(),
                                             new FontStyle().createFont()));
            /*
             * add others
             */
            if(otherData!=null && otherData.size()>0){
                className.addOtherData(document, otherData);
            }
            className.closeDocument(document);
            className.setDocument(document);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return className;

    }
    
    public String getLogo(String imageName) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = "";
        try {
            path = ec.getResource("/assets/img/" + imageName).getPath(); //"/Image/momra.jpeg").getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void setClassName(ReportBuilder className) {
        this.className = className;
    }

    public ReportBuilder getClassName() {
        return className;
    }

    public void setOtherData(Map otherData) {
        this.otherData = otherData;
    }

    public Map getOtherData() {
        return otherData;
    }


    public void setShowCommercial(boolean showCommercial) {
        this.showCommercial = showCommercial;
    }

    public boolean isShowCommercial() {
        return showCommercial;
    }
    
    public String loadKey(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.abdo.adf.storeshop.view.bundle.reportbundle");
        return bundle.getString(key);
    }

    public void setSalePrintMap(Map salePrintMap) {
        this.salePrintMap = salePrintMap;
    }

    public Map getSalePrintMap() {
        return salePrintMap;
    }
}
