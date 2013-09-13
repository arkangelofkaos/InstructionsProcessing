package instructionsprocessing;

/**
 * @author arkangelofkaos
 */
public class InstructionMessageBuilder {

    private int instructionType;
    private int productCode;
    private int quantity;
    private int uom;
    private int timestamp;

    public InstructionMessage build() {
        return new InstructionMessage(instructionType, productCode, quantity, uom, timestamp);
    }

    public InstructionMessageBuilder withInstructionType(int instructionType) {
        this.instructionType = instructionType;
        return this;
    }

    public InstructionMessageBuilder withProductCode(int productCode) {
        this.productCode = productCode;
        return this;
    }

    public InstructionMessageBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public InstructionMessageBuilder withUom(int uom) {
        this.uom = uom;
        return this;
    }

    public InstructionMessageBuilder withTimestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
