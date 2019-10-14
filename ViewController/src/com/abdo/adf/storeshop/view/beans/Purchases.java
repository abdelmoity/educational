package com.abdo.adf.storeshop.view.beans;


import com.abdo.adf.storeshop.view.utils.AdfUtils;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class Purchases extends BaseBean {
    private RichSelectOneChoice productType;

    public Purchases() {
        super();
    }

 

    public void deleteReceiptDetail(DialogEvent dialogEvent) {
        delete();
    }

    public void categoryChange(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void setProductType(RichSelectOneChoice productType) {
        this.productType = productType;
    }

    public RichSelectOneChoice getProductType() {
        return productType;
    }
    
    public void amountChangeListener(ValueChangeEvent valueChangeEvent) {
        Row row = getCurrentRow();
        if (valueChangeEvent.getNewValue() == null) {
            row.setAttribute("Price", new Number(0));
        } else {
            calculatePrice((Number)valueChangeEvent.getNewValue());
        }

    }

    private void calculatePrice(Number amount) {
        Row row = getCurrentRow();
        if (row != null && row.getAttribute("Product") != null &&
            row.getAttribute("Amount") != null) {
            Number productId = (Number)row.getAttribute("Product");
            OperationBinding oBinding = AdfUtils.getBindingOperation("getAllAmountProduct");
            oBinding.getParamsMap().put("productId", productId);
            Object result = oBinding.execute();
            if (result != null) {
                Row resultRow = (Row)result;
                Number purchasePrice = (Number)resultRow.getAttribute("PurchasePrice");
                row.setAttribute("Price", amount.mul(purchasePrice));
            }
        }
    }

    private Row getCurrentRow() {
        Row billSaleDetailsRow = getCurrentRowFromIteratorName("PurchaseBillDetailsView2Iterator");
        return billSaleDetailsRow;
    }
}
