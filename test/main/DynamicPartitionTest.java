package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicPartitionTest {
    DynamicPartition dynamicPartition;
    List<DynamicPartition.Area> list;
    int[] jobSize;

    @BeforeEach
    void setUp() {
        dynamicPartition = new DynamicPartition();
        list = new LinkedList<>();
        jobSize = new int[]{12, 30, 28};
        DynamicPartition.Area area1 = new DynamicPartition.Area(100, 35, true);
        DynamicPartition.Area area2 = new DynamicPartition.Area(156, 12, true);
        DynamicPartition.Area area3 = new DynamicPartition.Area(200, 28, true);
        list.add(area1);
        list.add(area2);
        list.add(area3);
    }

    @Test
    void firstFit() {
        dynamicPartition.firstFit(list, jobSize);
        dynamicPartition.printResult(list);
    }

    @Test
    void bestFit() {
        dynamicPartition.bestFit(list,jobSize);
        dynamicPartition.printResult(list);
    }

    @Test
    void worstFit() {
        dynamicPartition.worstFit(list,jobSize);
        dynamicPartition.printResult(list);
    }

}