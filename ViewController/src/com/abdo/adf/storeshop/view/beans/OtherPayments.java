package com.abdo.adf.storeshop.view.beans;

import oracle.adf.view.rich.event.DialogEvent;

public class OtherPayments extends BaseBean {
    public OtherPayments() {
        super();
    }

    public void delOtherPaymentListener(DialogEvent dialogEvent) {
        delete();
    }
}
