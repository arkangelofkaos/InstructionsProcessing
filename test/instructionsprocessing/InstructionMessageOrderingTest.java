package instructionsprocessing;

import org.testng.annotations.Test;

import static instructionsprocessing.TestInstructionMessageProvider.highPriorityMessageBuilder;
import static instructionsprocessing.TestInstructionMessageProvider.lowPriorityMessageBuilder;
import static instructionsprocessing.TestInstructionMessageProvider.validInstructionMessageBuilder;
import static org.testng.Assert.assertTrue;

/**
 * @author arkangelofkaos
 */
public class InstructionMessageOrderingTest {

    @Test
    public void shouldOrderHigherPriorityMessageFirst() throws Exception {
        InstructionMessage highPriorityMessage = lowPriorityMessageBuilder().build();
        InstructionMessage lowPriorityMessage = highPriorityMessageBuilder().build();

        assertTrue(highPriorityMessage.compareTo(lowPriorityMessage) > 0);
    }

    @Test
    public void shouldOrderEarlierMessageFirstWhenBothMessagesHaveSamePriority() throws Exception {
        InstructionMessage earlierMessage = validInstructionMessageBuilder().withTimestamp(100).build();
        InstructionMessage laterMessage = validInstructionMessageBuilder().withTimestamp(200).build();

        assertTrue(earlierMessage.compareTo(laterMessage) > 0);
    }
}
