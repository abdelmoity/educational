package com.abdo.adf.storeshop.view.beans.lookups;


import com.abdo.adf.storeshop.view.beans.BaseBean;

import oracle.adf.view.rich.event.DialogEvent;

public class PaymentsTypes extends BaseBean {
    public PaymentsTypes() {
        super();
    }

    public void delPaymentTypeListener(DialogEvent dialogEvent) {
       delete();
    }
}
