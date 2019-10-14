package com.abdo.adf.storeshop.view.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class AdfUtils {
    public AdfUtils() {
        super();
    }

    public static void setMessagesErr(String msg) {
        AdfFacesContext adfFacesContext = null;
        adfFacesContext = AdfFacesContext.getCurrentInstance();
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        ctx.addMessage(null, fm);
    }
    
    public static void setMessageSuccess(String msg) {
        AdfFacesContext adfFacesContext = null;
        adfFacesContext = AdfFacesContext.getCurrentInstance();
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        ctx.addMessage(null, fm);
    }
    
    public static void setMessageSuccessDelete() {
        AdfFacesContext adfFacesContext = null;
        adfFacesContext = AdfFacesContext.getCurrentInstance();
        FacesContext ctx = FacesContext.getCurrentInstance();
        String msg=getStringFromBundle("SUCCESS_DELETE");
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        ctx.addMessage(null, fm);
    }
    
    public static void setMessageErrorDelete() {
        AdfFacesContext adfFacesContext = null;
        adfFacesContext = AdfFacesContext.getCurrentInstance();
        FacesContext ctx = FacesContext.getCurrentInstance();
        String msg=getStringFromBundle("ERROR_DELETE_RECORD");
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        ctx.addMessage(null, fm);
    }
    
    public static void setMessageErrorDeleteRecordWithChilds() {
        String msg=getStringFromBundle("CANOT_DELETE_RECORD_FOR_CHILD");
        showMsgWarning(msg);
    }
    
    public static void showMsgWarning(String msg) {
        AdfFacesContext adfFacesContext = null;
        adfFacesContext = AdfFacesContext.getCurrentInstance();
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");
        ctx.addMessage(null, fm);
    }
    
    public static String getStringFromBundle(String key){
        ResourceBundle bundle= ResourceBundle.getBundle("com.abdo.adf.storeshop.view.bundle.viewController");
        return bundle.getString(key);
    }
    
    public static void saveTranscation() {
        try{
            commit();
            setMessageSuccess(getStringFromBundle("SUCCESS_SAVE"));
        }catch(Exception e){
            e.printStackTrace();
            rollback();
            setMessagesErr(getStringFromBundle("ERROR_SAVE"));
        }
    }
    
    public static void commit(){
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding = bindings.getOperationBinding("Commit");
            operationBinding.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
        operationBinding.execute();
    }
        
    public static void delete(){
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding = bindings.getOperationBinding("Delete");
            operationBinding.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteOperationByName(String deleteOperationName){
        try {
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding = bindings.getOperationBinding(deleteOperationName);
            operationBinding.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static BindingContainer getBindContainter() {
        BindingContainer container = BindingContext.getCurrent().getCurrentBindingsEntry();
        return container;
    }

    
    public static OperationBinding getBindingOperation(String operationName) {
        OperationBinding executedOperation = null;
        if (operationName != null) {
            if (!operationName.equals(null)) {
                executedOperation = getBindContainter().getOperationBinding(operationName);
            }
        }
        return executedOperation;
    }
}
