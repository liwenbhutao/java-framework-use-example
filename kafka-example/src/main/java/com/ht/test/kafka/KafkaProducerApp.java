package com.ht.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by hutao on 16/6/1.
 * 下午2:34
 */
@Slf4j
public class KafkaProducerApp {
    public static void run() {
        try {
            final Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("client.id", "DemoProducer");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            final KafkaProducer<String, String> producer = new KafkaProducer<>(props);
            final String topic = "topic2";
            for (int i = 0; i < 100; i++) {
                final String messageNo = i + "_" + RandomStringUtils.randomAlphabetic(10);
                String messageStr = "Message_" + i;
                final Future<RecordMetadata> future = producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        messageStr));
                final RecordMetadata recordMetadata = future.get();
                log.info("send record result:{},partition:{}", recordMetadata, recordMetadata.partition());

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        run();

    }
}
