package instructionsprocessing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static instructionsprocessing.TestInstructionMessageProvider.validInstructionMessageBuilder;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author arkangelofkaos
 */
public class InstructionMessageValidationTest {

    @Test(dataProvider = "invalidInstructionMessages")
    public void shouldBeInvalid(String failureMessage, InstructionMessage invalidInstructionMessage) throws Exception {
        assertFalse(invalidInstructionMessage.isValid(), failureMessage);
    }

    @Test(dataProvider = "validInstructionMessages")
    public void shouldBeValid(String failureMessage, InstructionMessage validInstructionMessage) {
        assertTrue(validInstructionMessage.isValid(), failureMessage);
    }

    @DataProvider(name = "validInstructionMessages")
    public Object[][] validInstructionMessages() {
        return new Object[][]{
                {
                        "Valid instruction message with lower bound values found to be invalid.",
                        validInstructionMessageBuilder().build()
                },
                {
                        "Valid instruction message with upper bound values found to be invalid.",
                        validInstructionMessageBuilder()
                                .withInstructionType(99)
                                .withUom(255)
                                .build()
                },
        };
    }

    @DataProvider(name = "invalidInstructionMessages")
    public Object[][] invalidInstructionMessages() {
        return new Object[][]{
                {
                        "Instruction type of value 0 or less is invalid",
                        validInstructionMessageBuilder().withInstructionType(0).build()
                },
                {
                        "Instruction type of value 100 or more is invalid",
                        validInstructionMessageBuilder().withInstructionType(100).build()
                },
                {
                        "Product code of value 0 or less is invalid",
                        validInstructionMessageBuilder().withProductCode(0).build()
                },
                {
                        "Quantity of value 0 or less is invalid",
                        validInstructionMessageBuilder().withQuantity(0).build()
                },
                {
                        "UOM of value less than 0 is invalid",
                        validInstructionMessageBuilder().withUom(-1).build()
                },
                {
                        "UOM of value 256 or more is invalid",
                        validInstructionMessageBuilder().withUom(256).build()
                },
                {
                        "Timestamp of value 0 or less is invalid",
                        validInstructionMessageBuilder().withTimestamp(0).build()
                },
        };
    }
}
