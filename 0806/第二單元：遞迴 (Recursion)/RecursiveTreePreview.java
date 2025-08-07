import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveTreePreview {

    // 1. 檔案系統模擬
    interface FileSystemNode {
        String getName();
    }
    static class File implements FileSystemNode {
        private String name;
        public File(String name) { this.name = name; }
        public String getName() { return this.name; }
    }
    static class Folder implements FileSystemNode {
        private String name;
        private List<FileSystemNode> children = new ArrayList<>();
        public Folder(String name) { this.name = name; }
        public String getName() { return this.name; }
        public void addChild(FileSystemNode node) { this.children.add(node); }
        public List<FileSystemNode> getChildren() { return this.children; }
    }

    // 2. 選單結構
    static class MenuItem {
        private String title;
        private List<MenuItem> subItems = new ArrayList<>();
        public MenuItem(String title) { this.title = title; }
        public void addSubItem(MenuItem item) { this.subItems.add(item); }
        public String getTitle() { return this.title; }
        public List<MenuItem> getSubItems() { return this.subItems; }
    }

    // 4. 巢狀清單
    // 這個類別設計用來代表一個可以是數字，也可以是列表的結構
    static class NestedInteger {
        private Integer value;
        private List<NestedInteger> list = new ArrayList<>();
        private boolean isInteger;

        public NestedInteger(Integer value) {
            this.value = value;
            this.isInteger = true;
        }
        public NestedInteger() { this.isInteger = false; }
        public void add(NestedInteger ni) { this.list.add(ni); }
        public boolean isInteger() { return isInteger; }
        public Integer getInteger() { return value; }
        public List<NestedInteger> getList() { return list; }
    }


    public static void main(String[] args) {

        // 1. 測試計算資料夾總檔案數
        Folder root = new Folder("root");
        Folder docs = new Folder("Documents");
        Folder photos = new Folder("Photos");
        root.addChild(new File("readme.txt"));
        root.addChild(docs);
        root.addChild(photos);
        docs.addChild(new File("cv.docx"));
        docs.addChild(new File("report.pdf"));
        photos.addChild(new File("cat.jpg"));
        Folder vacation = new Folder("Vacation");
        photos.addChild(vacation);
        vacation.addChild(new File("beach.jpg"));
        System.out.println("\n1. 資料夾總檔案數: " + countFiles(root)); 

        // 2. 測試列印多層選單
        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem fileMenu = new MenuItem("檔案");
        MenuItem editMenu = new MenuItem("編輯");
        mainMenu.addSubItem(fileMenu);
        mainMenu.addSubItem(editMenu);
        fileMenu.addSubItem(new MenuItem("開新檔案"));
        fileMenu.addSubItem(new MenuItem("儲存檔案"));
        MenuItem recentMenu = new MenuItem("最近開啟");
        fileMenu.addSubItem(recentMenu);
        recentMenu.addSubItem(new MenuItem("file1.txt"));
        editMenu.addSubItem(new MenuItem("剪下"));
        editMenu.addSubItem(new MenuItem("複製"));
        System.out.println("\n2. 列印多層選單結構:");
        printMenu(mainMenu);

        // 3. 測試展平巢狀陣列
        Object[] nestedArray = {1, new Object[]{2, 3}, 4, new Object[]{5, new Object[]{6, 7}}};
        List<Integer> flatList = flattenArray(nestedArray);
        System.out.println("\n3. 展平巢狀陣列:");
        System.out.println("原始: {1, {2, 3}, 4, {5, {6, 7}}}");
        System.out.println("展平後: " + flatList); 
        
        // 4. 測試計算巢狀清單最大深度
        NestedInteger listRoot = new NestedInteger(); // 這是最外層的列表
        listRoot.add(new NestedInteger(1));
        NestedInteger nestedList1 = new NestedInteger();
        nestedList1.add(new NestedInteger(2));
        nestedList1.add(new NestedInteger(3));
        listRoot.add(nestedList1); // 深度 2
        NestedInteger nestedList2 = new NestedInteger();
        NestedInteger nestedList3 = new NestedInteger();
        nestedList3.add(new NestedInteger(4)); // 深度 3
        nestedList2.add(nestedList3);
        listRoot.add(nestedList2);
        System.out.println("\n4. 巢狀清單最大深度: " + maxDepth(listRoot)); 
    }
    

    // 1. 遞迴計算資料夾中的總檔案數
    public static int countFiles(FileSystemNode node) {
        if (node instanceof File) {
            return 1;
        }
        if (node instanceof Folder) {
            int count = 0;
            for (FileSystemNode child : ((Folder) node).getChildren()) {
                count += countFiles(child);
            }
            return count;
        }
        return 0;
    }

    // 2. 遞迴列印多層選單結構
    public static void printMenu(MenuItem item) {
        printMenuRecursive(item, "");
    }
    private static void printMenuRecursive(MenuItem item, String indent) {
        System.out.println(indent + "- " + item.getTitle());
        for (MenuItem subItem : item.getSubItems()) {
            printMenuRecursive(subItem, indent + "  ");
        }
    }

    // 3. 遞迴處理巢狀陣列的展平
    public static List<Integer> flattenArray(Object[] array) {
        List<Integer> flatList = new ArrayList<>();
        flattenRecursive(array, flatList);
        return flatList;
    }
    private static void flattenRecursive(Object[] array, List<Integer> flatList) {
        for (Object element : array) {
            if (element instanceof Integer) {
                flatList.add((Integer) element);
            } else if (element instanceof Object[]) {
                flattenRecursive((Object[]) element, flatList);
            }
        }
    }

    // 4. 遞迴計算巢狀清單的最大深度
    public static int maxDepth(NestedInteger nestedItem) {
        // 基礎情況：如果是一個數字，深度為 1
        if (nestedItem.isInteger()) {
            return 1;
        }
        // 遞迴步驟：如果是一個列表，深度為 1 + max(所有子項的深度)
        int maxChildDepth = 0;
        // 如果列表為空，深度為 1
        if (nestedItem.getList().isEmpty()) {
            return 1;
        }
        for (NestedInteger child : nestedItem.getList()) {
            maxChildDepth = Math.max(maxChildDepth, maxDepth(child));
        }
        return 1 + maxChildDepth;
    }
}