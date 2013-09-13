package instructionsprocessing;

import java.util.PriorityQueue;

/**
 * @author arkangelofkaos
 */
public class InstructionQueue {

    private PriorityQueue<InstructionMessage> queue;

    public InstructionQueue() {
        this.queue = new PriorityQueue<>();
    }

    public int numberOfQueuedInstructionMessage() {
        return queue.size();
    }

    public void place(InstructionMessage instructionMessage) throws InvalidMessageException {
        if (!instructionMessage.isValid())
            throw new InvalidMessageException();

        queue.offer(instructionMessage);
    }

    public boolean remove(InstructionMessage instructionMessage) {
        return queue.remove(instructionMessage);
    }

    public InstructionMessage retrieveInstructionMessageAtFrontOfQueue() {
        return queue.poll();
    }
}
