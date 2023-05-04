import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortDemo {
    public static void sortByField(List<Item> itemList, String fieldName) {
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // 获取排序字段的值
                Object fieldValue1 = getFieldValue(item1, fieldName);
                Object fieldValue2 = getFieldValue(item2, fieldName);
                // 比较字段的值并返回排序结果
                if (fieldValue1 instanceof Comparable && fieldValue2 instanceof Comparable) {
                    return ((Comparable) fieldValue1).compareTo((Comparable) fieldValue2);
                } else {
                    return String.valueOf(fieldValue1).compareTo(String.valueOf(fieldValue2));
                }
            }
        });
    }
    // 根据字段名获取字段的值
    private static Object getFieldValue(Item item, String fieldName) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get field value", e);
        }
    }
    public static class Item {
        private int id;

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }

        private String name;
        private double price;
        public Item(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }
    }
    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "item1", 12.0));
        itemList.add(new Item(2, "item2", 8.0));
        itemList.add(new Item(3, "item3", 12.0));
        itemList = itemList.stream()
                .sorted(Comparator.comparing(Item::getId).reversed())
                .sorted(Comparator.comparing(Item::getPrice))
                .collect(Collectors.toList());
//        sortByField(itemList, "price"); // 根据价格升序排序
        System.out.println(itemList);
    }
}