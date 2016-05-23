namespace java com.ht.test.nifty.thrift

struct ThriftRequestHeader{
    1:  i32 businessId,
    2:  i32 version,
    3:  string extension
}

struct ThriftRequest
{
  1:  ThriftRequestHeader header,
  2:  string body
}

struct ThriftResponseHeader{
    1:  i32 retCode,
    2:  string retMsg,
    3:  string extension
}

struct ThriftResponse
{
  1:  ThriftResponseHeader header,
  2:  string body
}

service IThriftServer
{
  ThriftResponse request(1: ThriftRequest request);
}