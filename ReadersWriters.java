
public class ReadersWriters {

    private int readers = 0;
    private int waitingWriters = 0;
    private boolean writing = false;

    public synchronized void StartWrite() throws InterruptedException {
        while (readers > 0 || writing) {
            waitingWriters++;
            wait();
            waitingWriters--;
        }
        writing = true;
    }

    public synchronized void StopWrite() {
        writing = false;
        notifyAll();
    }

    public synchronized void StartRead() throws InterruptedException {
        while (writing || waitingWriters > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void StopRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }



}
