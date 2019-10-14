package com.abdo.adf.storeshop.model.util;

import java.util.Map;

import oracle.adf.share.ADFContext;

import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.SequenceImpl;


public class ModelHelper
{
  public static oracle.jbo.domain.Number getNextSeqNo(String seqName, DBTransaction dbt)
  {
    SequenceImpl seq = new SequenceImpl(seqName, dbt);
    return seq.getSequenceNumber();
  }

  public static Timestamp getCurrentDate()
  {
    Timestamp datetime = new Timestamp(System.currentTimeMillis());
    return datetime;
  }
  
  public static Number getCurrentUserCode()
  {
    Map sessionScope = ADFContext.getCurrent().getSessionScope();
    Number userid = new Number(Long.valueOf((String)sessionScope.get("userId")));
    return new Number(userid);
  }
  
}
