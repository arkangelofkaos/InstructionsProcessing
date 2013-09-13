package instructionsprocessing;

import static instructionsprocessing.InstructionMessage.HIGH_PRIORITY_INSTRUCTION_TYPE_BOUND;
import static instructionsprocessing.InstructionMessage.LOW_PRIORITY_INSTRUCTION_TYPE_BOUND;
import static instructionsprocessing.InstructionMessage.MEDIUM_PRIORITY_INSTRUCTION_TYPE_BOUND;

/**
 * Utility class designed to provide test data
 *
 * @author arkangelofkaos
 */
public class TestInstructionMessageProvider {

    public static InstructionMessageBuilder validInstructionMessageBuilder() {
        return new InstructionMessageBuilder()
                .withInstructionType(1)
                .withProductCode(1)
                .withQuantity(1)
                .withUom(0)
                .withTimestamp(1);
    }

    public static InstructionMessageBuilder lowPriorityMessageBuilder() {
        return validInstructionMessageBuilder()
                .withInstructionType(LOW_PRIORITY_INSTRUCTION_TYPE_BOUND);
    }

    public static InstructionMessageBuilder mediumPriorityMessageBuilder() {
        return validInstructionMessageBuilder()
                .withInstructionType(MEDIUM_PRIORITY_INSTRUCTION_TYPE_BOUND);
    }

    public static InstructionMessageBuilder highPriorityMessageBuilder() {
        return validInstructionMessageBuilder()
                .withInstructionType(HIGH_PRIORITY_INSTRUCTION_TYPE_BOUND);
    }
}
