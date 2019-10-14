package com.abdo.adf.storeshop.view.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;


public class Main implements Serializable {
    private String taskFlowId = "/taskflows/main_TF.xml#main_TF";
    private RichRegion taskFlowRegionBinding;

    public Main() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void navigateDynamicTaskFlow(ActionEvent actionEvent) {
        actionEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String taskUrl = (String)actionEvent.getComponent().getAttributes().get("taskFlowUrl");
        if (taskUrl != null) {
            taskFlowId = taskUrl;
            taskFlowRegionBinding.refresh(FacesContext.getCurrentInstance());
            AdfFacesContext.getCurrentInstance().addPartialTarget(taskFlowRegionBinding);
        }
    }

    public void setTaskFlowRegionBinding(RichRegion taskFlowRegionBinding) {
        this.taskFlowRegionBinding = taskFlowRegionBinding;
    }

    public RichRegion getTaskFlowRegionBinding() {
        return taskFlowRegionBinding;
    }

    public void setTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String getTaskFlowId() {
        return taskFlowId;
    }
}
