package com.coding.consumer;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.consumers.MessageAuditConsumer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageAuditConsumerTests {

	@InjectMocks
	MessageAuditConsumer messageAuditConsumer;

	@Rule
	public final OutputCapture outputCapture = new OutputCapture();
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void test_message_audit() {
		messageAuditConsumer.processMessageAudit(new Object());
		outputCapture.expect(containsString("Message received for message audit"));
	}

}
