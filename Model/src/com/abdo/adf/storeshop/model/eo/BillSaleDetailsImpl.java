package com.abdo.adf.storeshop.model.eo;


import com.abdo.adf.storeshop.model.util.ModelHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jbo.DMLConstraintException;
import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 06 03:14:27 EEST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BillSaleDetailsImpl extends EntityImpl {
    

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getId();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        Price {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getPrice();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setPrice((Number)value);
            }
        }
        ,
        Discount {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getDiscount();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setDiscount((Number)value);
            }
        }
        ,
        CreatedBy {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreatedDate {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getCreatedDate();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setCreatedDate((Timestamp)value);
            }
        }
        ,
        LastUpdatedDate {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getLastUpdatedDate();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setLastUpdatedDate((Timestamp)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        BillSaleId {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getBillSaleId();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setBillSaleId((Number)value);
            }
        }
        ,
        Product {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getProduct();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setProduct((Number)value);
            }
        }
        ,
        Amount {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getAmount();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setAmount((Number)value);
            }
        }
        ,
        ProductCategory {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getProductCategory();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setProductCategory((Number)value);
            }
        }
        ,
        BillSale {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getBillSale();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setBillSale((EntityImpl)value);
            }
        }
        ,
        PurchaseBillDetails {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getPurchaseBillDetails();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setPurchaseBillDetails((PurchaseBillDetailsImpl)value);
            }
        }
        ,
        SysUser {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getSysUser();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setSysUser((EntityImpl)value);
            }
        }
        ,
        SysUser1 {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getSysUser1();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setSysUser1((EntityImpl)value);
            }
        }
        ,
        ProductsCategory {
            public Object get(BillSaleDetailsImpl obj) {
                return obj.getProductsCategory();
            }

            public void put(BillSaleDetailsImpl obj, Object value) {
                obj.setProductsCategory((ProductsCategoryImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(BillSaleDetailsImpl object);

        public abstract void put(BillSaleDetailsImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }

    public static final int ID = AttributesEnum.Id.index();
    public static final int PRICE = AttributesEnum.Price.index();
    public static final int DISCOUNT = AttributesEnum.Discount.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int LASTUPDATEDDATE = AttributesEnum.LastUpdatedDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int BILLSALEID = AttributesEnum.BillSaleId.index();
    public static final int PRODUCT = AttributesEnum.Product.index();
    public static final int AMOUNT = AttributesEnum.Amount.index();
    public static final int PRODUCTCATEGORY = AttributesEnum.ProductCategory.index();
    public static final int BILLSALE = AttributesEnum.BillSale.index();
    public static final int PURCHASEBILLDETAILS = AttributesEnum.PurchaseBillDetails.index();
    public static final int SYSUSER = AttributesEnum.SysUser.index();
    public static final int SYSUSER1 = AttributesEnum.SysUser1.index();
    public static final int PRODUCTSCATEGORY = AttributesEnum.ProductsCategory.index();

    /**
     * This is the default constructor (do not remove).
     */
    public BillSaleDetailsImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.abdo.adf.storeshop.model.eo.BillSaleDetails");
    }

    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the value of Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Id.
     * @param value value to set the Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for Price, using the alias name Price.
     * @return the value of Price
     */
    public Number getPrice() {
        return (Number)getAttributeInternal(PRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Price.
     * @param value value to set the Price
     */
    public void setPrice(Number value) {
        setAttributeInternal(PRICE, value);
    }

    /**
     * Gets the attribute value for Discount, using the alias name Discount.
     * @return the value of Discount
     */
    public Number getDiscount() {
        return (Number)getAttributeInternal(DISCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Discount.
     * @param value value to set the Discount
     */
    public void setDiscount(Number value) {
        setAttributeInternal(DISCOUNT, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Timestamp getCreatedDate() {
        return (Timestamp)getAttributeInternal(CREATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedDate.
     * @param value value to set the CreatedDate
     */
    public void setCreatedDate(Timestamp value) {
        setAttributeInternal(CREATEDDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedDate, using the alias name LastUpdatedDate.
     * @return the value of LastUpdatedDate
     */
    public Timestamp getLastUpdatedDate() {
        return (Timestamp)getAttributeInternal(LASTUPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedDate.
     * @param value value to set the LastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp value) {
        setAttributeInternal(LASTUPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the value of LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for BillSaleId, using the alias name BillSaleId.
     * @return the value of BillSaleId
     */
    public Number getBillSaleId() {
        return (Number)getAttributeInternal(BILLSALEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BillSaleId.
     * @param value value to set the BillSaleId
     */
    public void setBillSaleId(Number value) {
        setAttributeInternal(BILLSALEID, value);
    }

    /**
     * Gets the attribute value for Product, using the alias name Product.
     * @return the value of Product
     */
    public Number getProduct() {
        return (Number)getAttributeInternal(PRODUCT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Product.
     * @param value value to set the Product
     */
    public void setProduct(Number value) {
        setAttributeInternal(PRODUCT, value);
    }

    /**
     * Gets the attribute value for Amount, using the alias name Amount.
     * @return the value of Amount
     */
    public Number getAmount() {
        return (Number)getAttributeInternal(AMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Amount.
     * @param value value to set the Amount
     */
    public void setAmount(Number value) {
        setAttributeInternal(AMOUNT, value);
    }

    /**
     * Gets the attribute value for ProductCategory, using the alias name ProductCategory.
     * @return the value of ProductCategory
     */
    public Number getProductCategory() {
        return (Number)getAttributeInternal(PRODUCTCATEGORY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProductCategory.
     * @param value value to set the ProductCategory
     */
    public void setProductCategory(Number value) {
        setAttributeInternal(PRODUCTCATEGORY, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getBillSale() {
        return (EntityImpl)getAttributeInternal(BILLSALE);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setBillSale(EntityImpl value) {
        setAttributeInternal(BILLSALE, value);
    }

    /**
     * @return the associated entity PurchaseBillDetailsImpl.
     */
    public PurchaseBillDetailsImpl getPurchaseBillDetails() {
        return (PurchaseBillDetailsImpl)getAttributeInternal(PURCHASEBILLDETAILS);
    }

    /**
     * Sets <code>value</code> as the associated entity PurchaseBillDetailsImpl.
     */
    public void setPurchaseBillDetails(PurchaseBillDetailsImpl value) {
        setAttributeInternal(PURCHASEBILLDETAILS, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getSysUser() {
        return (EntityImpl)getAttributeInternal(SYSUSER);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setSysUser(EntityImpl value) {
        setAttributeInternal(SYSUSER, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getSysUser1() {
        return (EntityImpl)getAttributeInternal(SYSUSER1);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setSysUser1(EntityImpl value) {
        setAttributeInternal(SYSUSER1, value);
    }

    /**
     * @return the associated entity ProductsCategoryImpl.
     */
    public ProductsCategoryImpl getProductsCategory() {
        return (ProductsCategoryImpl)getAttributeInternal(PRODUCTSCATEGORY);
    }

    /**
     * Sets <code>value</code> as the associated entity ProductsCategoryImpl.
     */
    public void setProductsCategory(ProductsCategoryImpl value) {
        setAttributeInternal(PRODUCTSCATEGORY, value);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }

    protected synchronized void doDML(int operation, TransactionEvent e) throws DMLConstraintException {
        try {
            if (operation == DML_INSERT) {
                setId(getNextId());
                setCreatedBy(ModelHelper.getCurrentUserCode());
                setCreatedDate(ModelHelper.getCurrentDate());
            } else if (operation == DML_UPDATE) {
                setLastUpdatedBy(ModelHelper.getCurrentUserCode());
                setLastUpdatedDate(ModelHelper.getCurrentDate());
            }
            super.doDML(operation, e);
            // update BaleSal totalPrice,totalAccount
            updateBaleSale(getBillSaleId());
            getBillSale().refresh(0);
        } catch (DMLConstraintException f) {
            getDBTransaction().rollback();
            throw f;
        } catch (Exception ex) {
            ex.printStackTrace();
            getDBTransaction().rollback();
        }
    }

    private Number getNextId() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Number result = null;
        try {
            String sql = "SELECT nvl(max(B.ID),0)+1 from BILL_SALE_DETAILS B ";
            ps = getDBTransaction().createPreparedStatement(sql, 0);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Number(rs.getString(1));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
        }
        return result;
    }
    
    private void updateBaleSale(Number billSaleId) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder sql=new StringBuilder("");
            sql.append("UPDATE BILL_SALE B set B.TOTAL_PRICE=(SELECT NVL(SUM(D.PRICE),0) FROM BILL_SALE_DETAILS D WHERE D.BILL_SALE_ID=?)");
            sql.append(",B.DISCOUNT=(SELECT NVL(SUM(D.DISCOUNT),0) FROM BILL_SALE_DETAILS D WHERE D.BILL_SALE_ID=?) WHERE B.ID=?");
            ps = getDBTransaction().createPreparedStatement(sql.toString(), 0);
            ps.setLong(1, new Long(billSaleId.toString()));
            ps.setLong(2, new Long(billSaleId.toString()));
            ps.setLong(3, new Long(billSaleId.toString()));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        }
    }
