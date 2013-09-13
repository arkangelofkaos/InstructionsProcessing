package instructionsprocessing;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static instructionsprocessing.InstructionMessage.Priority;
import static instructionsprocessing.InstructionMessage.Priority.HIGH;
import static instructionsprocessing.InstructionMessage.Priority.LOW;
import static instructionsprocessing.InstructionMessage.Priority.MEDIUM;
import static org.testng.Assert.assertEquals;

/**
 * @author arkangelofkaos
 */
public class InstructionMessagePriorityCalculationTest {

    @Test(dataProvider = "priortyInstructionMessages")
    public void shouldCalculatePriorityBasedOnInstructionType(String failureMessage,
                                                              InstructionMessage instructionMessage,
                                                              Priority priority) throws Exception {
        assertEquals(instructionMessage.calculatePriority(), priority, failureMessage);
    }

    @DataProvider(name = "priortyInstructionMessages")
    public Object[][] priortyInstructionMessages() {
        return new Object[][]{
                {
                        "Instruction message with instructionType 1 should be High priority",
                        buildInstructionMessageWithInstructionType(1),
                        HIGH
                },
                {
                        "Instruction message with instructionType 10 should be High priority",
                        buildInstructionMessageWithInstructionType(10),
                        HIGH
                },
                {
                        "Instruction message with instructionType 11 should be High priority",
                        buildInstructionMessageWithInstructionType(11),
                        MEDIUM
                },
                {
                        "Instruction message with instructionType 90 should be High priority",
                        buildInstructionMessageWithInstructionType(90),
                        MEDIUM
                },
                {
                        "Instruction message with instructionType 91 should be High priority",
                        buildInstructionMessageWithInstructionType(91),
                        LOW
                },
                {
                        "Instruction message with instructionType 99 should be High priority",
                        buildInstructionMessageWithInstructionType(99),
                        LOW
                },
        };
    }

    private InstructionMessage buildInstructionMessageWithInstructionType(int instructionType) {
        return new InstructionMessageBuilder().withInstructionType(instructionType).build();
    }
}
