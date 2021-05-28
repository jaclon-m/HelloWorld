package com.jaclon.mistakesOfBuz.nullValue;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 5个常见判空和解决方案
 * - 参数值是 Integer 等包装类型，使用时因为自动拆箱出现了空指针异常;
 * - 字符串比较出现空指针异常;
 * - 诸如 ConcurrentHashMap 这样的容器不支持 Key 和 Value 为 null，强行 put null 的 Key 或 Value 会出现空指针异常;
 * - A 对象包含了 B，在通过 A 对象的字段获得 B 之后，没有对字段判空就级联调用 B 的方 法出现空指针异常;
 * - 方法或远程服务返回的 List 不是空而是 null，没有进行判空就直接调用 List 的方法出现 空指针异常。
 *
 * 对于 Integer 的判空，可以使用 Optional.ofNullable 来构造一个 Optional，然后使用 orElse(0) 把 null 替换为默认值再进行 +1 操作。
 * 对于 String 和字面量的比较，可以把字面量放在前面，比如"OK".equals(s)，这样即使 s 是 null 也不会出现空指针异常;而对于两个可能为 null 的字符串变量的 equals 比 较，可以使用 Objects.equals，它会做判空处理。
 * 对于 ConcurrentHashMap，既然其 Key 和 Value 都不支持 null，修复方式就是不要 把 null 存进去。HashMap 的 Key 和 Value 可以存入 null，而 ConcurrentHashMap 看似是 HashMap 的线程安全版本，却不支持 null 值的 Key 和 Value，这是容易产生误 区的一个地方。
 * 对于类似 fooService.getBarService().bar().equals(“OK”) 的级联调用，需要判空的 地方有很多，包括 fooService、getBarService() 方法的返回值，以及 bar 方法返回的 字符串。如果使用 if-else 来判空的话可能需要好几行代码，但使用 Optional 的话一行 代码就够了。
 * 对于 rightMethod 返回的 List，由于不能确认其是否为 null，所以在调用 size 方法获 得列表大小之前，同样可以使用 Optional.ofNullable 包装一下返回值，然后通 过.orElse(Collections.emptyList()) 实现在 List 为 null 的时候获得一个空的 List，最后 再调用 size 方法。
 *
 * @author jaclon
 * @since 2021/5/28 16:06
 */
@Slf4j
public class NPEExceptionTest {

    public static void main(String[] args) {
        NPEExceptionTest test = new NPEExceptionTest();
//        test.wrong("1111");
        test.right("1111");
    }

    /**
     * @RequestParam(value = "test", defaultValue = "1111")
     * @param test
     * @return
     */
    public int wrong( String test) {
        return wrongMethod(test.charAt(0) == '1' ? null : new FooService(),
                test.charAt(1) == '1' ? null : 1,
                test.charAt(2) == '1' ? null : "OK",
                test.charAt(3) == '1' ? null : "OK").size();
    }

    /**
     * @RequestParam(value = "test", defaultValue = "1111")
     * @param test
     * @return
     */
    public int right( String test) {
        return Optional.ofNullable(rightMethod(test.charAt(0) == '1' ? null : new FooService(),
                test.charAt(1) == '1' ? null : 1,
                test.charAt(2) == '1' ? null : "OK",
                test.charAt(3) == '1' ? null : "OK"))
                .orElse(Collections.emptyList()).size();
    }

    private List<String> wrongMethod(FooService fooService, Integer i, String s, String t) {
        log.info("result {} {} {} {}", i + 1, s.equals("OK"), s.equals(t),
                new ConcurrentHashMap<String, String>().put(null, null));
        if (fooService.getBarService().bar().equals("OK"))
            log.info("OK");
        return null;
    }

    private List<String> rightMethod(FooService fooService, Integer i, String s, String t) {
        log.info("result {} {} {} {}", Optional.ofNullable(i).orElse(0) + 1, "OK".equals(s), Objects.equals(s, t), new HashMap<String, String>().put(null, null));
        Optional.ofNullable(fooService)
                .map(FooService::getBarService)
                .filter(barService -> "OK".equals(barService.bar()))
                .ifPresent(result -> log.info("OK"));
        return new ArrayList<>();
    }

    class FooService {
        @Getter
        private BarService barService;

    }

    class BarService {
        String bar() {
            return "OK";
        }
    }
}
