import java.util.*;

class CacheNode {
    int key;
    String value;
    int freq;
    long lastAccessTime;

    CacheNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
        this.lastAccessTime = System.nanoTime();
    }
}

class CacheLevel {
    int capacity;
    int cost;
    PriorityQueue<CacheNode> heap; 
    Map<Integer, CacheNode> map;

    CacheLevel(int capacity, int cost) {
        this.capacity = capacity;
        this.cost = cost;
        this.map = new HashMap<>();
        this.heap = new PriorityQueue<>((a, b) -> {
            if (a.freq != b.freq) return a.freq - b.freq;
            return Long.compare(a.lastAccessTime, b.lastAccessTime);
        });
    }
}

public class MultiLevelCacheSystem {
    private CacheLevel[] levels;

    public MultiLevelCacheSystem() {
        levels = new CacheLevel[]{
            new CacheLevel(2, 1),   // L1
            new CacheLevel(5, 3),   // L2
            new CacheLevel(10, 10)  // L3
        };
    }

    // 從 L1 → L2 → L3 順序找，如果找到，更新該節點的頻率 + 時間戳記，根據頻率決定是否要往上移動
    public String get(int key) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].map.containsKey(key)) {
                CacheNode node = levels[i].map.get(key);
                node.freq++;
                node.lastAccessTime = System.nanoTime();
                promoteIfNeeded(node, i);
                return node.value;
            }
        }
        return null;
    }
    
    // 檢查 L1 → L2 → L3 是否已存在 → 更新，否則放到 L1。如果滿了，根據 heap 淘汰最不常用的，移到下一層
    public void put(int key, String value) {
        if (updateIfExists(key, value)) return;
        insertIntoLevel(0, new CacheNode(key, value));
    }

    private boolean updateIfExists(int key, String value) {
        for (CacheLevel level : levels) {
            if (level.map.containsKey(key)) {
                CacheNode node = level.map.get(key);
                node.value = value;
                node.freq++;
                node.lastAccessTime = System.nanoTime();
                return true;
            }
        }
        return false;
    }
    
    /*
    如果 L1 滿 → 最不常用的移到 L2
    L2 滿 → 最不常用的移到 L3
    L3 滿 → 直接刪除
    */
    private void insertIntoLevel(int levelIndex, CacheNode node) {
        CacheLevel level = levels[levelIndex];
        if (level.heap.size() >= level.capacity) {
            CacheNode evicted = level.heap.poll();
            level.map.remove(evicted.key);
            if (levelIndex + 1 < levels.length) {
                insertIntoLevel(levelIndex + 1, evicted);
            }
        }
        level.heap.offer(node);
        level.map.put(node.key, node);
    }

    private void promoteIfNeeded(CacheNode node, int currentLevelIndex) {
        if (currentLevelIndex == 0) return; // 已在 L1
        double avgFreq = levels[currentLevelIndex].heap.stream()
                .mapToInt(n -> n.freq).average().orElse(0);
        if (node.freq > avgFreq) {
            moveNode(node, currentLevelIndex, currentLevelIndex - 1);
        }
    }

    private void moveNode(CacheNode node, int fromLevel, int toLevel) {
        levels[fromLevel].heap.remove(node);
        levels[fromLevel].map.remove(node.key);
        insertIntoLevel(toLevel, node);
    }

    public void printLevels() {
        for (int i = 0; i < levels.length; i++) {
            List<Integer> keys = new ArrayList<>();
            for (CacheNode n : levels[i].heap) keys.add(n.key);
            Collections.sort(keys);
            System.out.print("L" + (i + 1) + ": " + keys + (i < levels.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        System.out.println("put(1, \"A\"); put(2, \"B\"); put(3, \"C\");");
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printLevels();
        System.out.println();

        System.out.println("get(1); get(1); get(2);");
        cache.get(1);
        cache.get(1);
        cache.get(2);
        System.out.println("// 1被頻繁存取，應該移到L1");
        cache.printLevels();
        System.out.println();

        System.out.println("put(4, \"D\"); put(5, \"E\"); put(6, \"F\");");
        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println("// 根據存取頻率決定資料在各層級的分布");
        cache.printLevels();
    }
}
