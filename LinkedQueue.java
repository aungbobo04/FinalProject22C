public final class LinkedQueue<T> implements QueueInterface<T> {
    private Node head;
    private Node tail;

    class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    } // end Node

    public void enqueue(T newEntry) {
        Node entry = new Node(newEntry);

        if(tail != null) {
            tail.next = entry;
        }
        tail = entry;

        if(head == null) {
            head = entry;
        }
    }

    public T dequeue() {
        T data = head.data;
        head = head.next;
        if(head == null) {
            tail = null;
        }

        return data;
    }

    public T getFront() {
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }
} // end Linkedqueue