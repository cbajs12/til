package Algorithm;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

public class Cannibalism {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int member;
        int boat;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            member = Integer.parseInt(data[0]);
            boat = Integer.parseInt(data[1]);

            if (member < 1 || member > 10)
                return;

            if (boat < 1 || boat > 5)
                return;

            result[i] = findSolution(member, boat);
        }

        for (int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int member, int boat){
        Queue<NodeCannibal> queue = new Queue<NodeCannibal>() {
            int front, rear;
            int SIZE = 256;

            @Override
            public boolean add(NodeCannibal nodeCannibal) {
                if(fullQueue() == 1)
                    return false;

//                 = nodeCannibal;
                return true;
            }

            @Override
            public boolean offer(NodeCannibal nodeCannibal) {
                return false;
            }

            @Override
            public NodeCannibal remove() {
                return null;
            }

            @Override
            public NodeCannibal poll() {
                return null;
            }

            @Override
            public NodeCannibal element() {
                return null;
            }

            @Override
            public NodeCannibal peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<NodeCannibal> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends NodeCannibal> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            public void init(){
                front = 0;
                rear = 0;
            }

            public int fullQueue(){
                if(front == (rear+1) % SIZE)
                    return 1;

                return 0;
            }

            public int emptyQueue(){
                if(front == rear)
                    return 1;

                return 0;
            }
        };
        return 0;
    }
}

class NodeCannibal{
    int numResearchers;
    int numCannibals;
    int boat;
    int count;

    NodeCannibal(int numCannibals, int numResearchers, int boat, int count){
        this.boat = boat;
        this.count = count;
        this.numCannibals = numCannibals;
        this.numResearchers = numResearchers;
    }

    public int getCount() {
        return count;
    }

    public int getNumResearchers() {
        return numResearchers;
    }

    public int getNumCannibals() {
        return numCannibals;
    }

    public int getBoat() {
        return boat;
    }

    public void setNumResearchers(int numResearchers) {
        this.numResearchers = numResearchers;
    }

    public void setNumCannibals(int numCannibals) {
        this.numCannibals = numCannibals;
    }

    public void setBoat(int boat) {
        this.boat = boat;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
