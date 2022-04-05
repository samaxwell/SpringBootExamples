package com.sean.kafkaretries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.GenericMessage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* Documentation
 *
 *  https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/spring-cloud-stream.html#_testing
 */
@SpringBootTest
@ImportAutoConfiguration(TestChannelBinderConfiguration.class)
public class KafkaTests {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // IntelliJ
    private InputDestination input;


    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private OutputDestination output;

    @Test
    public void testEmptyConfiguration() {
        this.input.send(new GenericMessage<byte[]>("hello".getBytes()));
        assertArrayEquals("HELLO".getBytes(), output.receive().getPayload());
    }


//    @Test
    public void sampleTest() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration.getCompleteConfiguration())
                .run("--spring.cloud.function.definition=uppercase")) {
            InputDestination source = context.getBean(InputDestination.class);
            OutputDestination target = context.getBean(OutputDestination.class);
            source.send(new GenericMessage<byte[]>("hello".getBytes()));

            assertEquals(target.receive().getPayload(), "HELLO".getBytes());
        }
    }
}
