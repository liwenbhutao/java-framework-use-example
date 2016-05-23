package com.ht.test.jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by hutao on 16/5/9.
 * 下午1:49
 */
public class MyJmeterSampleClient extends AbstractJavaSamplerClient {
    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        System.out.println("enter....");
        System.out.println("[runTest]");
        result.setSuccessful(true);
        result.sampleEnd();
        return result;
    }
}
