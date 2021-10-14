import java.util.*;

public class Combinations {
    public static <E> Iterable<List<E>> combinations(List<E> all, int comSize) {
        return () -> new CombinationIter<>(all, comSize);
    }

    private static class CombinationIter<E> implements Iterator<List<E>> {
        final List<E> all;
        final int comSize;
        final int[] indices;
        List<E> next;

        CombinationIter(List<E> all, int comSize) {
            this.all = all;
            this.comSize = comSize;
            this.indices = new int[comSize];
            if (all.size() >= comSize) {
                for (int i = 0; i < comSize; ++i) {
                    indices[i] = i;
                }
                this.next = selected();
            }
        }

        @Override
        public boolean hasNext() {
            return (this.next != null);
        }

        @Override
        public List<E> next() {
            if (this.next==null) {
                throw new NoSuchElementException();
            }
            List<E> cur = this.next;
            this.next = findNext();
            return cur;
        }

        List<E> selected() {
            List<E> list = new ArrayList<E>(this.comSize);
            for (int i : indices) {
                list.add(all.get(i));
            }
            return list;
        }

        List<E> findNext() {
            final int n = all.size();
            int index;
            for (index = comSize-1; index >= 0; --index) {
                if (indices[index] != index + n - comSize) {
                    break;
                }
            }
            if (index < 0) {
                return null;
            }
            indices[index] += 1;
            for (int i = index+1; i < comSize; ++i) {
                indices[i] = indices[i-1] + 1;
            }
            return selected();
        }
    
    }

}
