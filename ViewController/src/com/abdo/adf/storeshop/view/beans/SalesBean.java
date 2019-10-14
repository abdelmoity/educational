package com.abdo.adf.storeshop.view.beans;


import com.abdo.adf.storeshop.view.utils.AdfUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class SalesBean extends BaseBean {


    public SalesBean() {
        super();
    }


    public void delBillSaleDetailListener(DialogEvent dialogEvent) {
        delete();
    }

    public void amountChangeListener(ValueChangeEvent valueChangeEvent) {
        Row billSaleDetailsRow = getCurrentRow();
        if (valueChangeEvent.getNewValue() == null) {
            billSaleDetailsRow.setAttribute("Price", new Number(0));
        } else {
            billSaleDetailsRow.setAttribute("Amount", valueChangeEvent.getNewValue());
            String allAmount=calculatePrice();
            if(allAmount!=null){
                AdfUtils.showMsgWarning(AdfUtils.getStringFromBundle("EXCEED_ALL_AMOUNT")+allAmount);
            }
            
        }

    }

    private String calculatePrice() {
        Row billSaleDetailsRow = getCurrentRow();
        if (billSaleDetailsRow != null && billSaleDetailsRow.getAttribute("Product") != null &&
            billSaleDetailsRow.getAttribute("Amount") != null) {
            Number productId = (Number)billSaleDetailsRow.getAttribute("Product");
            OperationBinding oBinding = AdfUtils.getBindingOperation("getAllAmountProduct");
            oBinding.getParamsMap().put("productId", productId);
            Object result = oBinding.execute();
            if (result != null) {
                Row row = (Row)result;
                Number salePrice = (Number)row.getAttribute("SalePrice");
                Number allAmount = (Number)row.getAttribute("AllAmount");
                Number amount = (Number)billSaleDetailsRow.getAttribute("Amount");
                if (allAmount.compareTo(amount) == -1) {
                    billSaleDetailsRow.setAttribute("Price", amount.mul(salePrice));
                    return allAmount.toString();
                }
                billSaleDetailsRow.setAttribute("Price", amount.mul(salePrice));
            }

        }
        return null;
    }

    private Row getCurrentRow() {
        Row billSaleDetailsRow = getCurrentRowFromIteratorName("BillSaleDetailsView2Iterator");
        return billSaleDetailsRow;
    }
}
