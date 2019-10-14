package com.abdo.adf.storeshop.view.beans.lookups;


import com.abdo.adf.storeshop.view.beans.BaseBean;

import oracle.adf.view.rich.event.DialogEvent;

public class ProductsType extends BaseBean {
    public ProductsType() {
        super();
    }

    public void delProductTypeListener(DialogEvent dialogEvent) {
        deleteOperationByName("Delete1");
    }
}
