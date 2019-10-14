package com.abdo.adf.storeshop.view.beans;

import oracle.adf.view.rich.event.DialogEvent;

public class SysRoleTaskFlows extends BaseBean {
    public SysRoleTaskFlows() {
        super();
    }
    
    public void delRoleTaskFlow(DialogEvent e){
        delete();
    }
}
