package instructionsprocessing;

import static instructionsprocessing.InstructionMessage.Priority.HIGH;
import static instructionsprocessing.InstructionMessage.Priority.LOW;
import static instructionsprocessing.InstructionMessage.Priority.MEDIUM;

/**
 * @author arkangelofkaos
 */
public class InstructionMessage implements Comparable<InstructionMessage> {

    public static final int LOW_PRIORITY_INSTRUCTION_TYPE_BOUND = 10;
    public static final int MEDIUM_PRIORITY_INSTRUCTION_TYPE_BOUND = 90;
    public static final int HIGH_PRIORITY_INSTRUCTION_TYPE_BOUND = 99;

    public static enum Priority {
        LOW, MEDIUM, HIGH
    }

    private final int instructionType;
    private final int productCode;
    private final int quantity;
    private final int uom;
    private final int timestamp;

    public InstructionMessage(int instructionType, int productCode, int quantity, int uom, int timestamp) {
        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
        this.timestamp = timestamp;
    }

    public boolean isValid() {
        return 0 < instructionType && instructionType < 100 &&
                0 < productCode &&
                0 < quantity &&
                0 <= uom && uom < 256 &&
                0 < timestamp;
    }

    public Priority calculatePriority() {
        if (0 < instructionType &&
                instructionType <= LOW_PRIORITY_INSTRUCTION_TYPE_BOUND)
            return HIGH;

        if (LOW_PRIORITY_INSTRUCTION_TYPE_BOUND < instructionType &&
                instructionType <= MEDIUM_PRIORITY_INSTRUCTION_TYPE_BOUND)
            return MEDIUM;

        if (MEDIUM_PRIORITY_INSTRUCTION_TYPE_BOUND < instructionType &&
                instructionType <= HIGH_PRIORITY_INSTRUCTION_TYPE_BOUND)
            return LOW;

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstructionMessage that = (InstructionMessage) o;

        if (instructionType != that.instructionType) return false;
        if (productCode != that.productCode) return false;
        if (quantity != that.quantity) return false;
        if (timestamp != that.timestamp) return false;
        if (uom != that.uom) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instructionType;
        result = 31 * result + productCode;
        result = 31 * result + quantity;
        result = 31 * result + uom;
        result = 31 * result + timestamp;
        return result;
    }

    @Override
    public int compareTo(InstructionMessage o) {
        int comparePriority = this.calculatePriority().compareTo(o.calculatePriority());
        if (comparePriority != 0)
            return comparePriority;

        return o.timestamp - this.timestamp;
    }

    @Override
    public String toString() {
        return "InstructionMessage{" +
                "instructionType=" + instructionType +
                ", productCode=" + productCode +
                ", quantity=" + quantity +
                ", uom=" + uom +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getInstructionType() {
        return instructionType;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUom() {
        return uom;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
