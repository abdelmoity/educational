package com.abdo.adf.storeshop.view.beans.lookups;


import com.abdo.adf.storeshop.view.beans.BaseBean;

import oracle.adf.view.rich.event.DialogEvent;

public class Suppliers extends BaseBean {
    public Suppliers() {
        super();
    }

    public void delSupplierListener(DialogEvent dialogEvent) {
        // Add event code here...
        delete();
    }
}
