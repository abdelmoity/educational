package com.abdo.adf.storeshop.view.beans;


import com.abdo.adf.storeshop.view.utils.AdfUtils;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.ADFContext;

import oracle.jbo.Row;


public class Login extends BaseBean {
    private String userId="1";
    private String userPassword="1234";
    private String taskFlowId = "/taskflows/main_TF.xml#main_TF";

    public Login() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String checkLogin() {
        if (userId != null && userPassword != null) {
           Row row= getUserRowByCriteria();
           if (row==null){
                AdfUtils.setMessagesErr(AdfUtils.getStringFromBundle("ERROR_USER_PASSWORD"));
               return null;
           }
            ADFContext.getCurrent().getSessionScope().put("userId",row.getAttribute("Id").toString());
            ADFContext.getCurrent().getSessionScope().put("userName",row.getAttribute("Name").toString());
            ADFContext.getCurrent().getSessionScope().put("roleId", row.getAttribute("Role").toString());
            
            /*
             * Company Data
             */
            Row companyDataRow= getProjectCompanyData();
            if (row!=null){
                ADFContext.getCurrent().getSessionScope().put("commercialId",companyDataRow.getAttribute("CommericialId").toString());
                ADFContext.getCurrent().getSessionScope().put("taxCard",companyDataRow.getAttribute("TaxCard").toString());
            }
            navigateToMainTaskFlow();

            return "index";
        }
        return null;
    }
    
    private void navigateToMainTaskFlow(){
        FacesContext ctxt = FacesContext.getCurrentInstance();
        Application app = ctxt.getApplication();
        Main dyanmicRegion = (Main)app.createValueBinding("#{mainBean}").getValue(ctxt);
        dyanmicRegion.setTaskFlowId("/taskflows/main_TF.xml#main_TF");
    }
    
    private Row getUserRowByCriteria() {
        Map paramMap=new HashMap();
        try {
            paramMap.put("userId", new oracle.jbo.domain.Number(userId));
            paramMap.put("password", getUserPassword());
        } catch (Exception e) {
            return null;
        }
        Row[] allRows= applyViewCriteriaOnViewObjectByIteratorName("getUserByIdCriteria",paramMap, "SysUserView1Iterator");
       if(allRows!=null && allRows.length>0){
           return allRows[0];
       }
        return null;
    }
    
    private Row getProjectCompanyData(){
       Row[]rows= getAllRowsFromIterator("ProjectCompanyDataView1Iterator");
       if(rows.length>0){
           return rows[0];
       }
       return null;
    }

   
   


    public String logOut() {
        FacesContext contx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)contx.getExternalContext().getSession(true);
        session.invalidate();
       // clearSession();
        return "login";
    }

    private void clearSession() {
        ADFContext.getCurrent().getSessionScope().remove("userId");
        ADFContext.getCurrent().getSessionScope().remove("roleId");
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}
