package com.abdo.adf.storeshop.view.itext;


import com.abdo.adf.storeshop.view.beans.BaseBean;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import oracle.adf.controller.v2.context.LifecycleContext;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class ReportBuilder extends BaseBean{
    Document document = null;
    PdfWriter pdfWriter = null;
    private Row[] rows;
    private List<String> attributes = new ArrayList<String>();
    private String title;
    PdfPTable table;
    private String momraLogoPath;
    private String arialFontPath;
    private String description;
    ByteArrayOutputStream outStream; 
    private LifecycleContext c;
    private String commAndTax;
   

    public ReportBuilder() {

    }

    public ByteArrayOutputStream exportDocumentPDF() {

        return getOutStream();
    }


    /***
     * addTitle(Document ,titleName ,titlePosition, fontStyle)
     *
     ***/
    public Document openDocument(ArrayList<String> attNames) {
        Document doc1 = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            doc1 = new Document(PageSize.A4, 50, 50, 50, 50);
            if (attNames.size() > 10)
                doc1.setPageSize(PageSize.A4_LANDSCAPE.rotate());
            pdfWriter = PdfWriter.getInstance(doc1, out);
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            /*
             * set commercial And Tax
             */
            event.setCommAndTaxCard(commAndTax);
            pdfWriter.setPageEvent(event);
            doc1.open();
            setOutStream(out);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc1;
    }

    public void closeDocument(Document document1) {
        document1.close();
    }


    /***
     * drawTable(List<String> attNames, Row[] rows)
     *
     ***/
    public PdfPTable drawTable(List<String> attNames, Row[] rows, Font tableHeadrFont, Font tableBodyFont) {
        float[] columnWidth = new float[attNames.size()];
        float cellwidth = getCellWidth(rows, attNames);
        PdfPTable table = null;
        if (attNames.size() > 0)
            table = new PdfPTable(attNames.size());
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        table.setWidthPercentage(100);
        // fill column width
        for (int n = 0; n < attNames.size(); n++) {
            String datatype = getDataType(rows, attNames.get(n));
            System.out.println();
            if (datatype.equalsIgnoreCase("Number")) {
                columnWidth[columnWidth.length - 1 - n] = cellwidth;
            }
            if (datatype.equalsIgnoreCase("String")) {
                columnWidth[columnWidth.length - 1 - n] = cellwidth * 2;
            }
            if (datatype.equalsIgnoreCase("Date")) {
                columnWidth[columnWidth.length - 1 - n] = cellwidth * 1.5f;
            }
        }
        PdfPCell cell;
        for (String headerName : attNames) {
            String label = loadKey(headerName);
            cell = new PdfPCell(new Phrase(label, tableHeadrFont));
            cell.setNoWrap(false);
            cell.setBackgroundColor(new BaseColor(95, 95, 95));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(25);
            table.addCell(cell);
        }
        table.setHeaderRows(1);
        boolean b = true;
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            for (String attName : attNames) {
                String attribValue = "-";
                if (row.getAttribute(attName) != null){
                 String dataType= getDataType(row, attName);
                 if(dataType.equalsIgnoreCase("Date")){
                     attribValue = parseDateFormat((oracle.jbo.domain.Date)row.getAttribute(attName));
                 }else{
                    attribValue = row.getAttribute(attName).toString();
                }}
                cell = new PdfPCell(new Phrase(attribValue, tableBodyFont));
                cell.setNoWrap(false);
                cell.setBackgroundColor(b ? BaseColor.LIGHT_GRAY : BaseColor.WHITE);
                cell.setArabicOptions(ColumnText.DIGITS_EN2AN); // for arabic number
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setFixedHeight(20);
                table.addCell(cell);
            }
            b = !b;
        }
        try {
            table.setWidths(columnWidth);
        } catch (DocumentException e) {
        }
        return table;
    }
    
    
    

    


    /***
     * addTitle(Document ,titleName ,titlePosition, fontStyle)
     *
     ***/
    public void addTitle(Document document, String reportTitle, int titleAllignment, Font fontStyle) {
        try {
            PdfPTable header = new PdfPTable(1);
            header.setWidthPercentage(100f);

            header.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            PdfPCell title = new PdfPCell(new Phrase(reportTitle, fontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setHorizontalAlignment(titleAllignment);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            header.addCell(title);
            document.add(header);
            document.add(new Phrase("\n\n"));

        } catch (BadElementException e) {
        } catch (DocumentException e) {
        }

    }
    /***
     * addTitle(Document ,titleName ,titlePosition ,image1 ,image1Position ,fontStyle )
     *
     ***/
    public void addTitle(Document document, String reportTitle, int titleAllignment, Font titleFontStyle, String imagePath,
                          int imgeAlligment) {
        Image image1 = null;
        try {
            PdfPTable header = new PdfPTable(3);
            header.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            header.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            header.setLockedWidth(true);
            header.getDefaultCell().setPadding(15);
            header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            image1 = Image.getInstance(imagePath);
            image1.scaleToFit(60f, 40f);
            image1.setAlignment(imgeAlligment);
            PdfPCell logocell = new PdfPCell(image1, false);
            logocell.setBorder(Rectangle.NO_BORDER);
            logocell.setVerticalAlignment(Element.ALIGN_TOP);
            header.addCell(logocell);

            PdfPCell title = new PdfPCell(new Phrase(reportTitle, titleFontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setHorizontalAlignment(titleAllignment);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            title.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            header.addCell(title);
            header.addCell("");
            header.completeRow();
            document.add(header);
            document.add(new Phrase("\n"));

        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        }
    }

    /***
     * addHeader(Document ,titleName ,titlePosition ,fontStyle)
     *
     ***/
    public void addHeader(Document document, String reportTitle, int titleAllignment, Font fontStyle) {
        try {
            PdfPTable header = new PdfPTable(1);
            header.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            PdfPCell title = new PdfPCell(new Phrase(reportTitle, fontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setHorizontalAlignment(titleAllignment);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            header.addCell(title);
            header.addCell("");
            header.completeRow();
            document.add(header);
            document.add(new Phrase("\n\n"));

        } catch (BadElementException e) {
        } catch (DocumentException e) {
        }
    }

    /***
     * addHeader(Document ,titleName ,titlePosition ,image1 ,image1Position ,fontStyle )
     *
     ***/
    public void addHeader(Document document, String reportTitle, int titleAllignment, String imagePath,
                          int imgeAlligment, Font fontStyle) {
        Image image1 = null;
        try {
            PdfPTable header = new PdfPTable(3);
            header.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            header.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            header.setLockedWidth(true);
            //            header.setWidthPercentage(100);
            header.getDefaultCell().setPadding(15);
            header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            image1 = Image.getInstance(imagePath);
            image1.scaleToFit(80f, 80f);
            image1.setAlignment(imgeAlligment);
            PdfPCell logocell = new PdfPCell(image1, false);
            logocell.setBorder(Rectangle.NO_BORDER);
            logocell.setVerticalAlignment(Element.ALIGN_TOP);
            //            logocell.setHorizontalAlignment(imgeAlligment);
            header.addCell(logocell);

            PdfPCell title = new PdfPCell(new Phrase(reportTitle, fontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setHorizontalAlignment(titleAllignment);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            title.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            header.addCell(title);
            header.addCell("");
            header.completeRow();
            document.add(header);
            document.add(new Phrase("\n\n"));

        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        }
    }

    /***
     * addHeader(Document ,titleName ,titlePosition ,image1 ,image1Position ,image2 ,image2Postion ,fontStyle)
     *
     ***/
    public void addHeader(Document document, String reportTitle, int titleAllignment, String imageName,
                          int imgeAlligment, String image2Name, int imge2Alligment, Font fontStyle) {

        Image image1 = null;
        Image image2 = null;
        try {
            PdfPTable header = new PdfPTable(3);
            header.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            header.setWidths(new int[] { 1, 3, 1 });


            header.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            header.setLockedWidth(true);
            header.setWidthPercentage(100);
            header.getDefaultCell().setPadding(15);
            header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            image1 = Image.getInstance(imageName);
            image1.scaleToFit(80f, 80f);
            image1.setAlignment(imgeAlligment);
            PdfPCell logocell = new PdfPCell(image1, false);
            logocell.setBorder(Rectangle.NO_BORDER);
            logocell.setVerticalAlignment(Element.ALIGN_TOP);
            header.addCell(logocell);

            PdfPCell title = new PdfPCell(new Phrase(reportTitle, fontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setHorizontalAlignment(titleAllignment);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            header.addCell(title);

            image2 = Image.getInstance(image2Name);
            image2.scaleToFit(80f, 80f);
            image2.setAlignment(imge2Alligment);

            PdfPCell image2Cell = new PdfPCell(image2, false);
            image2Cell.setBorder(Rectangle.NO_BORDER);
            image2Cell.setVerticalAlignment(Element.ALIGN_TOP);
            image2Cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            header.addCell(image2Cell);

            document.add(header);
            document.add(new Phrase("\n\n"));
        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        }
    }

    /***
     * addDescription(Document document, String description, int descriptionAllignment, Font fontStyle)
     *
     ***/
    public void addDescription(Document document, String description, int descriptionAllignment, Font fontStyle) {
        try {
            PdfPTable table = new PdfPTable(1);
            PdfPCell title = new PdfPCell(new Phrase(description, fontStyle));
            title.setBorder(Rectangle.NO_BORDER);
            title.setVerticalAlignment(Element.ALIGN_BOTTOM);
            table.setHorizontalAlignment(descriptionAllignment);
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            table.addCell(title);
            document.add(table);
            document.add(new Phrase("\n\n"));

        } catch (BadElementException e) {
        } catch (DocumentException e) {
        }
    }

    /***
     * addNewLine(Document document)
     *
     ***/

    public void addNewLine(Document document) {
        try {
            document.add(new Phrase("\n\n"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * getDataType(Row[] rows, String attName)
     * **/
    private String getDataType(Row[] rows, String attName) {
        String lastWord = "";
        String datatype = "";
        try {
            datatype = rows[0].getStructureDef().findAttributeDef(attName).getJavaType().getName();
            lastWord = datatype.substring(datatype.lastIndexOf(".") + 1);
            System.out.print("attributename = " + attName + "  data Type -- " + lastWord);
        } catch (Exception e) {
            System.err.println("generate java imple for vo ");
        }
        return lastWord;
    }
    
    private String getDataType(Row row, String attName) {
        String lastWord = "";
        String datatype = "";
        try {
            datatype = row.getStructureDef().findAttributeDef(attName).getJavaType().getName();
            lastWord = datatype.substring(datatype.lastIndexOf(".") + 1);
            System.out.print("attributename = " + attName + "  data Type -- " + lastWord);
        } catch (Exception e) {
            System.err.println("generate java imple for vo ");
        }
        return lastWord;
    }

    /**
     * getCellWidth(Row[] rows, List<String> attNames)
     *
     * **/
    private float getCellWidth(Row[] rows, List<String> attNames) {
        int numString = 0;
        int numDate = 0;
        int numNumber = 0;
        float smallWidth = 0;
        for (int i = 0; i < attNames.size(); i++) {
            if ("java.lang.String".equalsIgnoreCase(rows[0].getStructureDef().getAttributeDef(i).getJavaType().getName())) {
                numString += 1;
            }
            if ("oracle.jbo.domain.Date".equalsIgnoreCase(rows[0].getStructureDef().getAttributeDef(i).getJavaType().getName())) {
                numDate += 1;
            }
            if ("oracle.jbo.domain.Number".equalsIgnoreCase(rows[0].getStructureDef().getAttributeDef(i).getJavaType().getName())) {
                numNumber += 1;
            }
        }
        int smallCell = numNumber + (numString * 3) + (numDate * 2);
        if (smallCell > 0)
            smallWidth = 100 / smallCell;
        System.out.println("cell width " + smallWidth);
        return smallWidth;
    }

    public static String getStringFromResourceBundle(String key, String resourceBundlePath) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Locale locale = facesContext.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundlePath, locale);
        return bundle.getString(key);
    }

    public void setHeader(String title, String position) {

    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setOutStream(ByteArrayOutputStream outStream) {
        this.outStream = outStream;
    }

    public ByteArrayOutputStream getOutStream() {
        return outStream;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }

    public Row[] getRows() {
        return rows;
    }

    public void setMomraLogoPath(String momraLogoPath) {
        this.momraLogoPath = momraLogoPath;
    }

    public String getMomraLogoPath() {
        return momraLogoPath;
    }

    public void setArialFontPath(String arialFontPath) {
        this.arialFontPath = arialFontPath;
    }

    public String getArialFontPath() {
        return arialFontPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void openWindow(String url) {
        try {
             ExtendedRenderKitService erks =
             Service.getRenderKitService(FacesContext.getCurrentInstance(),
             ExtendedRenderKitService.class);
             StringBuilder strb = new StringBuilder("window.open('" +url+ "');");
             erks.addScript(FacesContext.getCurrentInstance(), strb.toString());

            
        } catch (Exception e) {
                System.err.println("Exception in openWindow() function: " +
                                      e.toString());
        }

     }
    
    public  void goToExternalUrl(String url) {
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (Exception e) {
            System.err.println("Exception in goToExternalUrl() function: " +
                                  e.toString());
        }

    }
    
    /*
     * Add Other Data
     */
    
    public void addOtherData(Document document, Map m) {
       
        try {
            PdfPTable otherData = new PdfPTable(1);
            otherData.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            otherData.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            otherData.setLockedWidth(true);
            Iterator it = m.entrySet().iterator();
            String resultAdded=" ";
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    String key=loadKey(pair.getKey().toString());
                    String value=pair.getValue().toString();
                    it.remove(); 
                    resultAdded+="   "+key+" : "+value;
                }
            Font font=new FontStyle(12, Font.ITALIC,BaseColor.DARK_GRAY).createFont();
            PdfPCell keyValueCell = new PdfPCell(new Phrase(resultAdded,font ));
            keyValueCell.setFixedHeight(27);
            keyValueCell.setBackgroundColor(new BaseColor(242, 238, 215));
            keyValueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            otherData.addCell(keyValueCell);
            
            otherData.completeRow();
            document.add(otherData);
        } catch (BadElementException e) {
        }catch (DocumentException e) {
        }
    }
    
    public void addSalePrintData(Document document, Map m) {
       
        try {
            PdfPTable salePrintData = new PdfPTable(1);
            salePrintData.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            salePrintData.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            salePrintData.setLockedWidth(true);
            Iterator it = m.entrySet().iterator();
            String resultAdded="";
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    String key=loadKey(pair.getKey().toString());
                    String value=pair.getValue().toString();
                    it.remove(); 
                    resultAdded+="   "+key+" : "+value+"\n";
                }
            Font font=new FontStyle(13, Font.ITALIC,BaseColor.DARK_GRAY).createFont();
            PdfPCell keyValueCell = new PdfPCell(new Phrase(resultAdded,font ));
            //keyValueCell.setBackgroundColor(new BaseColor(242, 238, 215));
            keyValueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            keyValueCell.setBorder(Rectangle.NO_BORDER);
            salePrintData.addCell(keyValueCell);
            
            salePrintData.completeRow();
            salePrintData.setSpacingAfter(2f);
            document.add(salePrintData);
            
        } catch (BadElementException e) {
        }catch (DocumentException e) {
        }
    }


    public String loadKey(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.abdo.adf.storeshop.view.bundle.reportbundle");
        return bundle.getString(key);
    }


    public void setCommAndTax(String commAndTax) {
        this.commAndTax = commAndTax;
    }

    public String getCommAndTax() {
        return commAndTax;
    }
}
