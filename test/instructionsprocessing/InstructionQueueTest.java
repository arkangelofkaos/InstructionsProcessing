package instructionsprocessing;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static instructionsprocessing.TestInstructionMessageProvider.highPriorityMessageBuilder;
import static instructionsprocessing.TestInstructionMessageProvider.lowPriorityMessageBuilder;
import static instructionsprocessing.TestInstructionMessageProvider.mediumPriorityMessageBuilder;
import static instructionsprocessing.TestInstructionMessageProvider.validInstructionMessageBuilder;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author arkangelofkaos
 */
public class InstructionQueueTest {

    private InstructionQueue instructionQueue;

    @BeforeMethod
    public void initialiseBeforeMethod() {
        instructionQueue = new InstructionQueue();
    }

    @Test
    public void shouldReturnSizeOfZeroForNewInstructionsQueue() throws Exception {
        assertEquals(instructionQueue.numberOfQueuedInstructionMessage(), 0);
    }

    @Test(expectedExceptions = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenPlacingInvalidMessageOnQueue() throws Exception {
        InstructionMessage invalidInstructionMessage =
                new InstructionMessageBuilder()
                        .withQuantity(-1)
                        .build();

        instructionQueue.place(
                invalidInstructionMessage
        );
    }

    @Test
    public void shouldPlaceInstructionMessageOnQueueAndIncrementSize() throws Exception {
        instructionQueue.place(validInstructionMessageBuilder().build());
        assertEquals(instructionQueue.numberOfQueuedInstructionMessage(), 1);
    }

    @Test
    public void shouldRemoveInstructionMessageJustPlacedOntoTheQueue() throws Exception {
        InstructionMessage instructionMessage = validInstructionMessageBuilder().build();
        instructionQueue.place(instructionMessage);
        assertTrue(instructionQueue.remove(instructionMessage));
    }

    @Test(dataProvider = "instructionMessagesToQueue")
    public void shouldPlaceHighPriorityMessageAtFrontOfQueue(Iterable<InstructionMessage> instructionMessagesToQueue,
                                                             InstructionMessage highestPriorityMessage) throws Exception {
        for (InstructionMessage instructionMessage : instructionMessagesToQueue) {
            instructionQueue.place(instructionMessage);
        }

        assertEquals(instructionQueue.retrieveInstructionMessageAtFrontOfQueue(), highestPriorityMessage);
    }

    @DataProvider(name = "instructionMessagesToQueue")
    public Object[][] instructionMessagesToQueue() {
        InstructionMessage lowPriorityMessage = lowPriorityMessageBuilder().build();
        InstructionMessage mediumPriorityMessage = mediumPriorityMessageBuilder().build();
        InstructionMessage highPriorityMessage = highPriorityMessageBuilder().build();

        return new Object[][]{
                {
                        Arrays.asList(
                                lowPriorityMessage,
                                mediumPriorityMessage,
                                highPriorityMessage
                        ),
                        highPriorityMessage
                },
                {
                        Arrays.asList(
                                mediumPriorityMessage,
                                highPriorityMessage,
                                lowPriorityMessage
                        ),
                        highPriorityMessage
                },
                {
                        Arrays.asList(
                                highPriorityMessage,
                                mediumPriorityMessage,
                                lowPriorityMessage
                        ),
                        highPriorityMessage
                },
        };
    }

}
