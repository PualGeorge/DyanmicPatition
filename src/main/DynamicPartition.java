package main;

import java.rmi.MarshalException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Dynamic partition.
 *
 * @author pc
 * @since 2020 /9/20 The type Dynamic partition.
 */
public class DynamicPartition {
    /**
     * The type Area.
     */
    static class Area {
        /**
         * The Start.
         */
        int start;
        /**
         * The Size.
         */
        int size;
        /**
         * The Is empty.
         */
        boolean isEmpty;

        /**
         * Instantiates a new Area.
         *
         * @param start   the start
         * @param size    the size
         * @param isEmpty the is empty
         */
        public Area(int start, int size, boolean isEmpty) {
            this.start = start;
            this.size = size;
            this.isEmpty = isEmpty;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }
    }

    /**
     * First fit list.
     *
     * @param list the list
     * @return the list
     */
    public List<Area> firstFit(List<Area> list, int[] jobSize) {
        for (int value : jobSize) {
            for (int i = 0, size = list.size(); i < size; i++) {
                Area area = list.get(i);
                if (allocate(list, area, value) == 1) {
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Best fit list.
     *
     * @param list the list
     * @return the list
     */
    public List<Area> bestFit(List<Area> list, int[] jobSize) {
        List<Area> sortedList = new LinkedList<>(list);
        sortList(sortedList);
        for (int value : jobSize) {
            for (Area area : sortedList) {
                if (allocate(list, area, value) == 1) {
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Worst fit list.
     *
     * @param list the list
     * @return the list
     */
    public List<Area> worstFit(List<Area> list, int[] jobSize) {
        List<Area> sortedList = new LinkedList<>(list);
        sortList(sortedList);
        for (int value : jobSize) {
            for (int size=sortedList.size()-1,i=size;i>0;i--) {
                Area area=sortedList.get(i);
                if (allocate(list, area, value) == 1) {
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Print result.
     *
     * @param list the list
     */
    public void printResult(List<Area> list) {
        for (Area area : list) {
            System.out.println(area.start + "\t" + area.size + "\t" + area.isEmpty + "\t");
        }
    }

    private void sortList(List<Area> list) {
        list.sort(new Comparator<Area>() {
            @Override
            public int compare(Area o1, Area o2) {
                return o1.size - o2.size;
            }
        });
    }

    private int allocate(List<Area> list, Area area, int value) {
        if (area.isEmpty && area.size > value) {
            int index = list.indexOf(area);
            list.add(index + 1, new Area(area.start + value, area.size - value, true));
            area.size = value;
            area.isEmpty = false;
            list.set(index, area);
            return 1;
        } else if (area.isEmpty && area.size == value) {
            int index = list.indexOf(area);
            area.isEmpty = false;
            list.set(index, area);
            return 1;
        }
        return 0;
    }
}
