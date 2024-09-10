package io.konsumgandalf.udemy.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger _log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        _log.info("Hello world!");

        Properties props = PropertiesFactory.getProducerProps();

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("my-topic-1", "hello world");

        producer.send(producerRecord);

        // Flush would be called automatically when the producer is closed
        producer.flush();

        producer.close();
    }
}
