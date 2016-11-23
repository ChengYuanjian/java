package com.truepaas.log.spout;

import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;

public  class MessageScheme implements Scheme {

    /* (non-Javadoc)
     * @see backtype.storm.spout.Scheme#deserialize(byte[])
     */
    public List<Object> deserialize(ByteBuffer ser) {
        try {
            String msg = new String(getString(ser));
            return new Values(msg);
        } catch (Exception e) {

        }
        return null;
    }


    /* (non-Javadoc)
     * @see backtype.storm.spout.Scheme#getOutputFields()
     */
    public Fields getOutputFields() {
        // TODO Auto-generated method stub
        return new Fields("msg");
    }

    /**
     * ByteBuffer 转换 String
     * @param buffer
     * @return
     */
    public  String getString(ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try
        {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }
}