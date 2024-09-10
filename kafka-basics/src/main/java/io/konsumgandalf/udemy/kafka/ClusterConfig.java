package io.konsumgandalf.udemy.kafka;

enum ClusterConfig {
    SVC_IP("192.168.4.16"),
    SVC_PORT("9094"),
    SVC_ADDR(SVC_IP.value+":"+SVC_PORT.value),
    GROUP_ID("udemy-course"),
    TOPIC("my-topic-2");

    private final String value;

    ClusterConfig(String value) {
        this.value = value;
    }
    public String getValue() {return this.value;}
}