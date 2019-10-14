package com.abdo.adf.storeshop.view.beans.lookups;


import com.abdo.adf.storeshop.view.beans.BaseBean;

import oracle.adf.view.rich.event.DialogEvent;

public class Employees extends BaseBean {
    public Employees() {
        super();
    }

    public void delEmployeeListener(DialogEvent dialogEvent) {
        delete();
    }
}
