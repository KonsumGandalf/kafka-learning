package io.konsumgandalf.udemy.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {
    private static final Logger _log = LoggerFactory.getLogger(ProducerDemoKeys.class.getSimpleName());

    public static void main(String[] args) {
        _log.info("Hello world!");

        Properties props = PropertiesFactory.getProducerProps();

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i <= 10; i++) {

            int partition = 1; // Specify the partition
            String key = "id_";
            String value = "hello world";

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(ClusterConfig.TOPIC.getValue(), partition, key+i, value+i);
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // Executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        // The record was successfully sent
                        _log.info("Key : " + key + " | Partition : " + partition + " | Offset : " + recordMetadata.offset());
                    } else {
                        _log.error("Error while producing", e);
                    }
                }
            });
        }


        // Flush would be called automatically when the producer is closed
        producer.flush();

        producer.close();
    }
}
