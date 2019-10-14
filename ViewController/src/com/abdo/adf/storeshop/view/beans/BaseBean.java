package com.abdo.adf.storeshop.view.beans;


import com.abdo.adf.storeshop.view.utils.AdfUtils;

import java.net.MalformedURLException;

import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.DMLConstraintException;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaManager;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.format.DefaultDateFormatter;
import oracle.jbo.format.FormatErrorException;


public class BaseBean {
    private Number tempNumber;

    public BaseBean() {
        super();
    }
    /*
     * Return View Object By Iterator Name
     * from page bindings
     */

    public ViewObject getViewObjectFromIteratorName(String MyViewObjectIteratorName) {
        ViewObject vo = null;
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterator = bindings.findIteratorBinding(MyViewObjectIteratorName);
            vo = iterator.getViewObject();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public Row[] getAllRowsFromIterator(String MyViewObjectIteratorName) {
        ViewObject vo = null;
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterator = bindings.findIteratorBinding(MyViewObjectIteratorName);
            vo = iterator.getViewObject();
            vo.setRangeSize(-1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return vo.getAllRowsInRange();
    }


    public Row getCurrentRowFromIteratorName(String MyViewObjectIteratorName) {
        ViewObject vo = null;
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterator = bindings.findIteratorBinding(MyViewObjectIteratorName);
            vo = iterator.getViewObject();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return vo.getCurrentRow();
    }

    /*
     * Apply View Criteria on View Object
     */

    public Row[] applyViewCriteriaOnViewObjectByIteratorName(String MyViewCriteriaName, Map vcParams,
                                                             String MyViewObjectIteratorName) {
        try {
            ViewObject vo = this.getViewObjectFromIteratorName(MyViewObjectIteratorName);
            ViewCriteriaManager vcm = vo.getViewCriteriaManager();
            ViewCriteria vc = vcm.getViewCriteria(MyViewCriteriaName);
            vo.applyViewCriteria(vc);
            // get params
            Iterator it = vcParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                vo.setNamedWhereClauseParam(pair.getKey().toString(), pair.getValue());
            }
            vo.executeQuery();
            vo.getEstimatedRowCount();
            return vo.getAllRowsInRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save() {
        AdfUtils.saveTranscation();
        return null;
    }

    public void delPopUpListener(DialogEvent dialogEvent) {
        delete();
    }
    public String delete() {
        try {
            AdfUtils.delete();
            AdfUtils.commit();
            AdfUtils.setMessageSuccessDelete();
        } catch (DMLConstraintException c) {
            AdfUtils.setMessageErrorDeleteRecordWithChilds();
        } catch (Exception e) {
            e.printStackTrace();
            AdfUtils.setMessageErrorDelete();
        }
        return null;
    }

    public String deleteOperationByName(String deleteOperationName) {
        try {
            AdfUtils.deleteOperationByName(deleteOperationName);
            AdfUtils.commit();
            AdfUtils.setMessageSuccessDelete();
        } catch (DMLConstraintException c) {
            AdfUtils.setMessageErrorDeleteRecordWithChilds();
        } catch (Exception e) {
            e.printStackTrace();
            AdfUtils.setMessageErrorDelete();
        }
        return null;
    }

    public String getArialFontPath() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = "";
        try {
            path = ec.getResource("/font/arial.ttf").getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static FacesContext getFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext;
    }

    /**
     * Method for taking a reference to a JSF binding expression and returning
     * the matching object (or creating it).
     * @param expression EL expression
     * @return Managed object
     * @signature MF
     */
    public static Object resolveExpression(String expression) {
        FacesContext facesContext = getFacesContext();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    public void setTempNumber(Number tempNumber) {
        this.tempNumber = tempNumber;
    }

    public Number getTempNumber() {
        return tempNumber;
    }

    public String parseDateFormat(oracle.jbo.domain.Date date) {
        String pattern = "dd/MM/yyyy";
        DefaultDateFormatter ddf = new DefaultDateFormatter();
        String result = "";
        try {
            result = ddf.format("yyyy/MM/dd", date);
        } catch (FormatErrorException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object getFromSession(String key) {
        FacesContext ctx = getFacesContext();
        Map sessionState = ctx.getExternalContext().getSessionMap();
        return sessionState.get(key);
    }
}
