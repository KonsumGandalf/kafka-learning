package io.konsumgandalf.udemy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

import static java.lang.StringTemplate.STR;

public class ConsumerDemo {
    private static final Logger _log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        Properties props = PropertiesFactory.getConsumerProps();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList(ClusterConfig.TOPIC.getValue(), "my-topic-1"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);

            for (ConsumerRecord<String, String> record: records) {
                _log.info(STR."Key \{record.key()}, Value \{record.value()}");
                _log.info(STR."Partition \{record.partition()}, Offset \{record.offset()}");
            }

            _log.info("...pooling");
        }
    }
}
