package com.abdo.adf.storeshop.view.beans;

import oracle.adf.view.rich.event.DialogEvent;

public class Users extends BaseBean {
    public Users() {
        super();
    }
    
    public void delUserListener(DialogEvent dialogEvent) {
        try {
            delete();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
}
