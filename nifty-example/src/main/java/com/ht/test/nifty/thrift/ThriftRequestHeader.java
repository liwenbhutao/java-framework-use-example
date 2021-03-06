/**
 * Autogenerated by Thrift Compiler (0.9.2)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package com.ht.test.nifty.thrift;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-5-6")
public class ThriftRequestHeader implements org.apache.thrift.TBase<ThriftRequestHeader, ThriftRequestHeader._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftRequestHeader> {
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftRequestHeader");
    private static final org.apache.thrift.protocol.TField BUSINESS_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("businessId", org.apache.thrift.protocol.TType.I32, (short) 1);
    private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.I32, (short) 2);
    private static final org.apache.thrift.protocol.TField EXTENSION_FIELD_DESC = new org.apache.thrift.protocol.TField("extension", org.apache.thrift.protocol.TType.STRING, (short) 3);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    // isset id assignments
    private static final int __BUSINESSID_ISSET_ID = 0;
    private static final int __VERSION_ISSET_ID = 1;

    static {
        schemes.put(StandardScheme.class, new ThriftRequestHeaderStandardSchemeFactory());
        schemes.put(TupleScheme.class, new ThriftRequestHeaderTupleSchemeFactory());
    }

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.BUSINESS_ID, new org.apache.thrift.meta_data.FieldMetaData("businessId", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
        tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
        tmpMap.put(_Fields.EXTENSION, new org.apache.thrift.meta_data.FieldMetaData("extension", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftRequestHeader.class, metaDataMap);
    }

    public int businessId; // required
    public int version; // required
    public String extension; // required
    private byte __isset_bitfield = 0;

    public ThriftRequestHeader() {
    }

    public ThriftRequestHeader(
            int businessId,
            int version,
            String extension) {
        this();
        this.businessId = businessId;
        setBusinessIdIsSet(true);
        this.version = version;
        setVersionIsSet(true);
        this.extension = extension;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public ThriftRequestHeader(ThriftRequestHeader other) {
        __isset_bitfield = other.__isset_bitfield;
        this.businessId = other.businessId;
        this.version = other.version;
        if (other.isSetExtension()) {
            this.extension = other.extension;
        }
    }

    public ThriftRequestHeader deepCopy() {
        return new ThriftRequestHeader(this);
    }

    @Override
    public void clear() {
        setBusinessIdIsSet(false);
        this.businessId = 0;
        setVersionIsSet(false);
        this.version = 0;
        this.extension = null;
    }

    public int getBusinessId() {
        return this.businessId;
    }

    public ThriftRequestHeader setBusinessId(int businessId) {
        this.businessId = businessId;
        setBusinessIdIsSet(true);
        return this;
    }

    public void unsetBusinessId() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BUSINESSID_ISSET_ID);
    }

    /**
     * Returns true if field businessId is set (has been assigned a value) and false otherwise
     */
    public boolean isSetBusinessId() {
        return EncodingUtils.testBit(__isset_bitfield, __BUSINESSID_ISSET_ID);
    }

    public void setBusinessIdIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BUSINESSID_ISSET_ID, value);
    }

    public int getVersion() {
        return this.version;
    }

    public ThriftRequestHeader setVersion(int version) {
        this.version = version;
        setVersionIsSet(true);
        return this;
    }

    public void unsetVersion() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSION_ISSET_ID);
    }

    /**
     * Returns true if field version is set (has been assigned a value) and false otherwise
     */
    public boolean isSetVersion() {
        return EncodingUtils.testBit(__isset_bitfield, __VERSION_ISSET_ID);
    }

    public void setVersionIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSION_ISSET_ID, value);
    }

    public String getExtension() {
        return this.extension;
    }

    public ThriftRequestHeader setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public void unsetExtension() {
        this.extension = null;
    }

    /**
     * Returns true if field extension is set (has been assigned a value) and false otherwise
     */
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
            case BUSINESS_ID:
                if (value == null) {
                    unsetBusinessId();
                } else {
                    setBusinessId((Integer) value);
                }
                break;

            case VERSION:
                if (value == null) {
                    unsetVersion();
                } else {
                    setVersion((Integer) value);
                }
                break;

            case EXTENSION:
                if (value == null) {
                    unsetExtension();
                } else {
                    setExtension((String) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case BUSINESS_ID:
                return Integer.valueOf(getBusinessId());

            case VERSION:
                return Integer.valueOf(getVersion());

            case EXTENSION:
                return getExtension();

        }
        throw new IllegalStateException();
    }

    /**
     * Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
     */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case BUSINESS_ID:
                return isSetBusinessId();
            case VERSION:
                return isSetVersion();
            case EXTENSION:
                return isSetExtension();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof ThriftRequestHeader)
            return this.equals((ThriftRequestHeader) that);
        return false;
    }

    public boolean equals(ThriftRequestHeader that) {
        if (that == null)
            return false;

        boolean this_present_businessId = true;
        boolean that_present_businessId = true;
        if (this_present_businessId || that_present_businessId) {
            if (!(this_present_businessId && that_present_businessId))
                return false;
            if (this.businessId != that.businessId)
                return false;
        }

        boolean this_present_version = true;
        boolean that_present_version = true;
        if (this_present_version || that_present_version) {
            if (!(this_present_version && that_present_version))
                return false;
            if (this.version != that.version)
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

        boolean present_businessId = true;
        list.add(present_businessId);
        if (present_businessId)
            list.add(businessId);

        boolean present_version = true;
        list.add(present_version);
        if (present_version)
            list.add(version);

        boolean present_extension = true && (isSetExtension());
        list.add(present_extension);
        if (present_extension)
            list.add(extension);

        return list.hashCode();
    }

    @Override
    public int compareTo(ThriftRequestHeader other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.valueOf(isSetBusinessId()).compareTo(other.isSetBusinessId());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetBusinessId()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.businessId, other.businessId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetVersion()).compareTo(other.isSetVersion());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetVersion()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, other.version);
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
        StringBuilder sb = new StringBuilder("ThriftRequestHeader(");
        boolean first = true;

        sb.append("businessId:");
        sb.append(this.businessId);
        first = false;
        if (!first) sb.append(", ");
        sb.append("version:");
        sb.append(this.version);
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

    /**
     * The set of fields this struct contains, along with convenience methods for finding and manipulating them.
     */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        BUSINESS_ID((short) 1, "businessId"),
        VERSION((short) 2, "version"),
        EXTENSION((short) 3, "extension");

        private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

        static {
            for (_Fields field : EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        private final short _thriftId;
        private final String _fieldName;

        _Fields(short thriftId, String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // BUSINESS_ID
                    return BUSINESS_ID;
                case 2: // VERSION
                    return VERSION;
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

        public short getThriftFieldId() {
            return _thriftId;
        }

        public String getFieldName() {
            return _fieldName;
        }
    }

    private static class ThriftRequestHeaderStandardSchemeFactory implements SchemeFactory {
        public ThriftRequestHeaderStandardScheme getScheme() {
            return new ThriftRequestHeaderStandardScheme();
        }
    }

    private static class ThriftRequestHeaderStandardScheme extends StandardScheme<ThriftRequestHeader> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftRequestHeader struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // BUSINESS_ID
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.businessId = iprot.readI32();
                            struct.setBusinessIdIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // VERSION
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.version = iprot.readI32();
                            struct.setVersionIsSet(true);
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

        public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftRequestHeader struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            oprot.writeFieldBegin(BUSINESS_ID_FIELD_DESC);
            oprot.writeI32(struct.businessId);
            oprot.writeFieldEnd();
            oprot.writeFieldBegin(VERSION_FIELD_DESC);
            oprot.writeI32(struct.version);
            oprot.writeFieldEnd();
            if (struct.extension != null) {
                oprot.writeFieldBegin(EXTENSION_FIELD_DESC);
                oprot.writeString(struct.extension);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class ThriftRequestHeaderTupleSchemeFactory implements SchemeFactory {
        public ThriftRequestHeaderTupleScheme getScheme() {
            return new ThriftRequestHeaderTupleScheme();
        }
    }

    private static class ThriftRequestHeaderTupleScheme extends TupleScheme<ThriftRequestHeader> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, ThriftRequestHeader struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetBusinessId()) {
                optionals.set(0);
            }
            if (struct.isSetVersion()) {
                optionals.set(1);
            }
            if (struct.isSetExtension()) {
                optionals.set(2);
            }
            oprot.writeBitSet(optionals, 3);
            if (struct.isSetBusinessId()) {
                oprot.writeI32(struct.businessId);
            }
            if (struct.isSetVersion()) {
                oprot.writeI32(struct.version);
            }
            if (struct.isSetExtension()) {
                oprot.writeString(struct.extension);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, ThriftRequestHeader struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            BitSet incoming = iprot.readBitSet(3);
            if (incoming.get(0)) {
                struct.businessId = iprot.readI32();
                struct.setBusinessIdIsSet(true);
            }
            if (incoming.get(1)) {
                struct.version = iprot.readI32();
                struct.setVersionIsSet(true);
            }
            if (incoming.get(2)) {
                struct.extension = iprot.readString();
                struct.setExtensionIsSet(true);
            }
        }
    }

}

