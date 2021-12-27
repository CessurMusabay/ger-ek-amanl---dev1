
public class BoundedBuffer {

    private int buffer[];
    private int first;
    private int last;
    private int numberInBuffer = 0;
    private int size;

    public BoundedBuffer(int length) {
        size = length;
        buffer = new int[size];
        last = 0;
        first = 0;
    }

    public synchronized void put(int item) throws InterruptedException {
        while (numberInBuffer == size) {
            wait();
        }
        ;
        last = (last + 1) % size;
        numberInBuffer++;
        buffer[last] = item;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (numberInBuffer == 0) {
            wait();
        }
        ;
        first = (first + 1) % size;
        numberInBuffer--;
        notifyAll();
        return buffer[first];
    }

}
