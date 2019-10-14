package com.abdo.adf.storeshop.model.eo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jbo.DMLConstraintException;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 06 20:37:26 EEST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BillSaleImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(BillSaleImpl obj) {
                return obj.getId();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        SaleDate {
            public Object get(BillSaleImpl obj) {
                return obj.getSaleDate();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setSaleDate((Date)value);
            }
        }
        ,
        TotalPrice {
            public Object get(BillSaleImpl obj) {
                return obj.getTotalPrice();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setTotalPrice((Number)value);
            }
        }
        ,
        Discount {
            public Object get(BillSaleImpl obj) {
                return obj.getDiscount();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setDiscount((Number)value);
            }
        }
        ,
        Customer {
            public Object get(BillSaleImpl obj) {
                return obj.getCustomer();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setCustomer((Number)value);
            }
        }
        ,
        CreatedBy {
            public Object get(BillSaleImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreatedDate {
            public Object get(BillSaleImpl obj) {
                return obj.getCreatedDate();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setCreatedDate((Timestamp)value);
            }
        }
        ,
        LastUpdatedDate {
            public Object get(BillSaleImpl obj) {
                return obj.getLastUpdatedDate();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setLastUpdatedDate((Timestamp)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(BillSaleImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        BillSaleDetails {
            public Object get(BillSaleImpl obj) {
                return obj.getBillSaleDetails();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Customers {
            public Object get(BillSaleImpl obj) {
                return obj.getCustomers();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setCustomers((CustomersImpl)value);
            }
        }
        ,
        SysUser {
            public Object get(BillSaleImpl obj) {
                return obj.getSysUser();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setSysUser((EntityImpl)value);
            }
        }
        ,
        SysUser1 {
            public Object get(BillSaleImpl obj) {
                return obj.getSysUser1();
            }

            public void put(BillSaleImpl obj, Object value) {
                obj.setSysUser1((EntityImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(BillSaleImpl object);

        public abstract void put(BillSaleImpl object, Object value);

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
    public static final int SALEDATE = AttributesEnum.SaleDate.index();
    public static final int TOTALPRICE = AttributesEnum.TotalPrice.index();
    public static final int DISCOUNT = AttributesEnum.Discount.index();
    public static final int CUSTOMER = AttributesEnum.Customer.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int LASTUPDATEDDATE = AttributesEnum.LastUpdatedDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int BILLSALEDETAILS = AttributesEnum.BillSaleDetails.index();
    public static final int CUSTOMERS = AttributesEnum.Customers.index();
    public static final int SYSUSER = AttributesEnum.SysUser.index();
    public static final int SYSUSER1 = AttributesEnum.SysUser1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public BillSaleImpl() {
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
     * Gets the attribute value for SaleDate, using the alias name SaleDate.
     * @return the value of SaleDate
     */
    public Date getSaleDate() {
        return (Date)getAttributeInternal(SALEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SaleDate.
     * @param value value to set the SaleDate
     */
    public void setSaleDate(Date value) {
        setAttributeInternal(SALEDATE, value);
    }

    /**
     * Gets the attribute value for TotalPrice, using the alias name TotalPrice.
     * @return the value of TotalPrice
     */
    public Number getTotalPrice() {
        return (Number)getAttributeInternal(TOTALPRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalPrice.
     * @param value value to set the TotalPrice
     */
    public void setTotalPrice(Number value) {
        setAttributeInternal(TOTALPRICE, value);
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
     * Gets the attribute value for Customer, using the alias name Customer.
     * @return the value of Customer
     */
    public Number getCustomer() {
        return (Number)getAttributeInternal(CUSTOMER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Customer.
     * @param value value to set the Customer
     */
    public void setCustomer(Number value) {
        setAttributeInternal(CUSTOMER, value);
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
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getBillSaleDetails() {
        return (RowIterator)getAttributeInternal(BILLSALEDETAILS);
    }

    /**
     * @return the associated entity CustomersImpl.
     */
    public CustomersImpl getCustomers() {
        return (CustomersImpl)getAttributeInternal(CUSTOMERS);
    }

    /**
     * Sets <code>value</code> as the associated entity CustomersImpl.
     */
    public void setCustomers(CustomersImpl value) {
        setAttributeInternal(CUSTOMERS, value);
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
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.abdo.adf.storeshop.model.eo.BillSale");
    }
    
    protected synchronized void doDML(int operation, TransactionEvent e) throws DMLConstraintException {
        try {
            if (operation == DML_INSERT) {
                setId(getNextId());
//                setCreatedBy(ModelHelper.getCurrentUserCode());
//                setCreatedDate(ModelHelper.getCurrentDate());

            } else if (operation == DML_UPDATE) {
//                setLastUpdatedBy(ModelHelper.getCurrentUserCode());
//                setLastUpdatedDate(ModelHelper.getCurrentDate());
            }
            super.doDML(operation, e);
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
            String sql = "SELECT nvl(max(B.ID),0)+1 from BILL_SALE B ";
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
}
