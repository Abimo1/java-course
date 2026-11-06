package LevelOne;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- ТЕСТ 1: Базовые операции ---");
        MyHashMap<String, Integer> m1 = new MyHashMap<>();
        m1.put("a", 1);
        m1.put("b", 2);
        System.out.println(m1.get("a") == 1); // true
        System.out.println(m1.get("b") == 2); // true
        m1.put("a", 100);
        System.out.println(m1.get("a") == 100); // true (перезапись)
        m1.remove("a");
        System.out.println(m1.get("a") == null); // true

        System.out.println("\n--- ТЕСТ 2: Расширение (resize) ---");
        MyHashMap<Integer, String> m2 = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            m2.put(i, "val" + i);
        }
        boolean resizeOk = true;
        for (int i = 0; i < 1000; i++) {
            if (!("val" + i).equals(m2.get(i))) {
                resizeOk = false;
                break;
            }
        }
        System.out.println("1000 элементов сохранены и найдены: " + resizeOk); // true

        System.out.println("\n--- ТЕСТ 3: Массовое удаление ---");
        for (int i = 0; i < 1000; i += 2) { // удаляем каждый второй
            m2.remove(i);
        }
        boolean removeOk = true;
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                if (m2.get(i) != null) removeOk = false; // четные удалены
            } else {
                if (!("val" + i).equals(m2.get(i))) removeOk = false; // нечетные на месте
            }
        }
        System.out.println("Массовое удаление корректно: " + removeOk); // true

        System.out.println("\n--- ТЕСТ 4: null-ключ ---");
        MyHashMap<String, Integer> m3 = new MyHashMap<>();
        m3.put(null, 42);
        System.out.println(m3.get(null) == 42); // true
        m3.remove(null);
        System.out.println(m3.get(null) == null); // true

        System.out.println("\n--- ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ ---");
    }
}
