/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ht.test.nifty.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-5-6")
public class ThriftResponseHeader implements org.apache.thrift.TBase<ThriftResponseHeader, ThriftResponseHeader._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftResponseHeader> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftResponseHeader");

  private static final org.apache.thrift.protocol.TField RET_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("retCode", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField RET_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("retMsg", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXTENSION_FIELD_DESC = new org.apache.thrift.protocol.TField("extension", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftResponseHeaderStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftResponseHeaderTupleSchemeFactory());
  }

  public int retCode; // required
  public String retMsg; // required
  public String extension; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RET_CODE((short)1, "retCode"),
    RET_MSG((short)2, "retMsg"),
    EXTENSION((short)3, "extension");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // RET_CODE
          return RET_CODE;
        case 2: // RET_MSG
          return RET_MSG;
        case 3: // EXTENSION
          return EXTENSION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RETCODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RET_CODE, new org.apache.thrift.meta_data.FieldMetaData("retCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.RET_MSG, new org.apache.thrift.meta_data.FieldMetaData("retMsg", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXTENSION, new org.apache.thrift.meta_data.FieldMetaData("extension", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftResponseHeader.class, metaDataMap);
  }

  public ThriftResponseHeader() {
  }

  public ThriftResponseHeader(
    int retCode,
    String retMsg,
    String extension)
  {
    this();
    this.retCode = retCode;
    setRetCodeIsSet(true);
    this.retMsg = retMsg;
    this.extension = extension;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftResponseHeader(ThriftResponseHeader other) {
    __isset_bitfield = other.__isset_bitfield;
    this.retCode = other.retCode;
    if (other.isSetRetMsg()) {
      this.retMsg = other.retMsg;
    }
    if (other.isSetExtension()) {
      this.extension = other.extension;
    }
  }

  public ThriftResponseHeader deepCopy() {
    return new ThriftResponseHeader(this);
  }

  @Override
  public void clear() {
    setRetCodeIsSet(false);
    this.retCode = 0;
    this.retMsg = null;
    this.extension = null;
  }

  public int getRetCode() {
    return this.retCode;
  }

  public ThriftResponseHeader setRetCode(int retCode) {
    this.retCode = retCode;
    setRetCodeIsSet(true);
    return this;
  }

  public void unsetRetCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RETCODE_ISSET_ID);
  }

  /** Returns true if field retCode is set (has been assigned a value) and false otherwise */
  public boolean isSetRetCode() {
    return EncodingUtils.testBit(__isset_bitfield, __RETCODE_ISSET_ID);
  }

  public void setRetCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RETCODE_ISSET_ID, value);
  }

  public String getRetMsg() {
    return this.retMsg;
  }

  public ThriftResponseHeader setRetMsg(String retMsg) {
    this.retMsg = retMsg;
    return this;
  }

  public void unsetRetMsg() {
    this.retMsg = null;
  }

  /** Returns true if field retMsg is set (has been assigned a value) and false otherwise */
  public boolean isSetRetMsg() {
    return this.retMsg != null;
  }

  public void setRetMsgIsSet(boolean value) {
    if (!value) {
      this.retMsg = null;
    }
  }

  public String getExtension() {
    return this.extension;
  }

  public ThriftResponseHeader setExtension(String extension) {
    this.extension = extension;
    return this;
  }

  public void unsetExtension() {
    this.extension = null;
  }

  /** Returns true if field extension is set (has been assigned a value) and false otherwise */
  public boolean isSetExtension() {
    return this.extension != null;
  }

  public void setExtensionIsSet(boolean value) {
    if (!value) {
      this.extension = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RET_CODE:
      if (value == null) {
        unsetRetCode();
      } else {
        setRetCode((Integer)value);
      }
      break;

    case RET_MSG:
      if (value == null) {
        unsetRetMsg();
      } else {
        setRetMsg((String)value);
      }
      break;

    case EXTENSION:
      if (value == null) {
        unsetExtension();
      } else {
        setExtension((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RET_CODE:
      return Integer.valueOf(getRetCode());

    case RET_MSG:
      return getRetMsg();

    case EXTENSION:
      return getExtension();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RET_CODE:
      return isSetRetCode();
    case RET_MSG:
      return isSetRetMsg();
    case EXTENSION:
      return isSetExtension();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftResponseHeader)
      return this.equals((ThriftResponseHeader)that);
    return false;
  }

  public boolean equals(ThriftResponseHeader that) {
    if (that == null)
      return false;

    boolean this_present_retCode = true;
    boolean that_present_retCode = true;
    if (this_present_retCode || that_present_retCode) {
      if (!(this_present_retCode && that_present_retCode))
        return false;
      if (this.retCode != that.retCode)
        return false;
    }

    boolean this_present_retMsg = true && this.isSetRetMsg();
    boolean that_present_retMsg = true && that.isSetRetMsg();
    if (this_present_retMsg || that_present_retMsg) {
      if (!(this_present_retMsg && that_present_retMsg))
        return false;
      if (!this.retMsg.equals(that.retMsg))
        return false;
    }

    boolean this_present_extension = true && this.isSetExtension();
    boolean that_present_extension = true && that.isSetExtension();
    if (this_present_extension || that_present_extension) {
      if (!(this_present_extension && that_present_extension))
        return false;
      if (!this.extension.equals(that.extension))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_retCode = true;
    list.add(present_retCode);
    if (present_retCode)
      list.add(retCode);

    boolean present_retMsg = true && (isSetRetMsg());
    list.add(present_retMsg);
    if (present_retMsg)
      list.add(retMsg);

    boolean present_extension = true && (isSetExtension());
    list.add(present_extension);
    if (present_extension)
      list.add(extension);

    return list.hashCode();
  }

  @Override
  public int compareTo(ThriftResponseHeader other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRetCode()).compareTo(other.isSetRetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.retCode, other.retCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRetMsg()).compareTo(other.isSetRetMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRetMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.retMsg, other.retMsg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExtension()).compareTo(other.isSetExtension());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExtension()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.extension, other.extension);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftResponseHeader(");
    boolean first = true;

    sb.append("retCode:");
    sb.append(this.retCode);
    first = false;
    if (!first) sb.append(", ");
    sb.append("retMsg:");
    if (this.retMsg == null) {
      sb.append("null");
    } else {
      sb.append(this.retMsg);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("extension:");
    if (this.extension == null) {
      sb.append("null");
    } else {
      sb.append(this.extension);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ThriftResponseHeaderStandardSchemeFactory implements SchemeFactory {
    public ThriftResponseHeaderStandardScheme getScheme() {
      return new ThriftResponseHeaderStandardScheme();
    }
  }

  private static class ThriftResponseHeaderStandardScheme extends StandardScheme<ThriftResponseHeader> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftResponseHeader struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RET_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.retCode = iprot.readI32();
              struct.setRetCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RET_MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.retMsg = iprot.readString();
              struct.setRetMsgIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXTENSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.extension = iprot.readString();
              struct.setExtensionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftResponseHeader struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RET_CODE_FIELD_DESC);
      oprot.writeI32(struct.retCode);
      oprot.writeFieldEnd();
      if (struct.retMsg != null) {
        oprot.writeFieldBegin(RET_MSG_FIELD_DESC);
        oprot.writeString(struct.retMsg);
        oprot.writeFieldEnd();
      }
      if (struct.extension != null) {
        oprot.writeFieldBegin(EXTENSION_FIELD_DESC);
        oprot.writeString(struct.extension);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftResponseHeaderTupleSchemeFactory implements SchemeFactory {
    public ThriftResponseHeaderTupleScheme getScheme() {
      return new ThriftResponseHeaderTupleScheme();
    }
  }

  private static class ThriftResponseHeaderTupleScheme extends TupleScheme<ThriftResponseHeader> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftResponseHeader struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRetCode()) {
        optionals.set(0);
      }
      if (struct.isSetRetMsg()) {
        optionals.set(1);
      }
      if (struct.isSetExtension()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRetCode()) {
        oprot.writeI32(struct.retCode);
      }
      if (struct.isSetRetMsg()) {
        oprot.writeString(struct.retMsg);
      }
      if (struct.isSetExtension()) {
        oprot.writeString(struct.extension);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftResponseHeader struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.retCode = iprot.readI32();
        struct.setRetCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.retMsg = iprot.readString();
        struct.setRetMsgIsSet(true);
      }
      if (incoming.get(2)) {
        struct.extension = iprot.readString();
        struct.setExtensionIsSet(true);
      }
    }
  }

}
