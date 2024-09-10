package io.konsumgandalf.udemy.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class PropertiesFactory {
    private static Properties cachedProducer;
    private static Properties cachedConsumer;

    public static Properties getProducerProps() {
        if (cachedProducer == null) {
            cachedProducer = createProducerProps();
        }
        return cachedProducer;
    }

    public static Properties getConsumerProps() {
        if (cachedConsumer == null) {
            cachedConsumer = createConsumerProps();
        }
        return cachedConsumer;
    }

    private static void connect(Properties props) {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ClusterConfig.SVC_ADDR.getValue());
    }

    private static Properties createProducerProps() {
        Properties props = new Properties();

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // Sets the maximum number of bytes that can be sent in a single batch by a producer
        // props.put(ProducerConfig.BATCH_SIZE_CONFIG, 400);
        // RoundRobinPartitioner distributes message evenly across all partitions
        // props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName());

        PropertiesFactory.connect(props);

        return props;
    }

    private static Properties createConsumerProps() {
        Properties props = new Properties();

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ClusterConfig.GROUP_ID.getValue());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        PropertiesFactory.connect(props);

        return props;
    }
}
