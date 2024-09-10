package io.konsumgandalf.udemy.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    private static final Logger _log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());

    public static void main(String[] args) {
        _log.info("Hello world!");

        Properties props = PropertiesFactory.getProducerProps();

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int partition = 1; // Specify the partition
        String key = "key";
        String value = "hello world";


        for (int i=0; i <= 10; i++) {
            for (int j=0; j <= 30; j++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(ClusterConfig.TOPIC.getValue(), value + i);
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        // Executes every time a record is successfully sent or an exception is thrown
                        if (e == null) {
                            // The record was successfully sent
                            _log.info("Received new metadata. \n" +
                                    "Topic: " + recordMetadata.topic() + "\n" +
                                    "Partition: " + recordMetadata.partition() + "\n" +
                                    "Offset: " + recordMetadata.offset() + "\n" +
                                    "Timestamp: " + recordMetadata.timestamp());
                        } else {
                            _log.error("Error while producing", e);
                        }
                    }
                });
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



        // Flush would be called automatically when the producer is closed
        producer.flush();

        producer.close();
    }
}
