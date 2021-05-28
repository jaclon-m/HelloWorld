package com.jaclon.mistakesOfBuz.nullValue.pojonotnull;

/**
 * 解决方案小总结
 * 1. dto和Entity分离，区分数据传输和最终的持久化对象。数据库不允许null值，对时间字段用CURRENT_TIMESTAMP。
 * 2. dto中相关属性用optional包装，判断不传还是传null（转换为空字符串）。区分是想清空还是不改变
 * 3. 生成动态sql，实现只更新修改过的字段。对于没有赋值的字段不进行修改。可能需要先查询一次实体（hibernate @DynamicUpdate，mybatis未知）
 *
 * @author jaclon
 * @since 2021/5/28 17:43
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequestMapping("pojonull")
@RestController
public class POJONullController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        UserDto result = objectMapper.readValue("{\"id\":\"1\", \"age\":30, \"name\":null}", UserDto.class);
        log.info("field name with null value dto:{} name:{}", result, result.getName().orElse("N/A"));
        // field name with null value dto:UserDto(id=1, name=Optional.empty, age=Optional[30]) name:N/A
        log.info("missing field name dto:{}", objectMapper.readValue("{\"id\":\"1\", \"age\":30}", UserDto.class));
        // missing field name dto:UserDto(id=1, name=null, age=Optional[30])
    }

    /**
     *  * 对于 JSON 到 DTO 的反序列化过程，null 的表达是有歧义 的，客户端不传某个属性，或者传 null，这个属性在 DTO 中都是 null
     *  * POJO 中的字段有默认值。如果客户端不传值，就会赋值为默认值，导致创建时间也被 更新到了数据库中
     *  * 注意字符串格式化时可能会把 null 值格式化为 null 字符串
     *  * DTO 和 Entity 共用了一个 POJO
     *  * 数据库字段允许保存 null，会进一步增加出错的可能性和复杂度
     *
     * @param user
     * @return
     */
    @PostMapping("wrong")
    public User wrong(@RequestBody User user) {
        user.setNickname(String.format("guest%s", user.getName()));
        return userRepository.save(user);
    }

    /**
     * 在重构了 DTO 和 Entity 后，我们重新定义一个 right 接口，以便对更新操作进行更精细化 的处理。首先是参数校验:
     * 对传入的 UserDto 和 ID 属性先判空，如果为空直接抛出 IllegalArgumentException。
     * 根据 id 从数据库中查询出实体后进行判空，如果为空直接抛出 IllegalArgumentException。
     * 然后，由于 DTO 中已经巧妙使用了 Optional 来区分客户端不传值和传 null 值，那么业务 逻辑实现上就可以按照客户端的意图来分别实现逻辑。
     * 如果不传值，那么 Optional 本身为 null，直接跳过 Entity 字段的更新即可，这样动态生成的 SQL 就不会包含这个列;如果传 了值，那么进一步判断传的是不是 null。
     *
     * 下面，我们根据业务需要分别对姓名、年龄和昵称进行更新:
     * 对于姓名，我们认为客户端传 null 是希望把姓名重置为空，允许这样的操作，使用 Optional 的 orElse 方法一键把空转换为空字符串即可。
     * 对于年龄，我们认为如果客户端希望更新年龄就必须传一个有效的年龄，年龄不存在重 置操作，可以使用 Optional 的 orElseThrow 方法在值为空的时候抛出 IllegalArgumentException。
     * 对于昵称，因为数据库中姓名不可能为 null，所以可以放心地把昵称设置为 guest 加上 数据库取出来的姓名。
     * @param user
     * @return
     */
    @PostMapping("right")
    public UserEntity right(@RequestBody UserDto user) {
        if (user == null || user.getId() == null)
            throw new IllegalArgumentException("用户Id不能为空");

        UserEntity userEntity = userEntityRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

        if (user.getName() != null) {
            userEntity.setName(user.getName().orElse(""));
        }
        userEntity.setNickname("guest" + userEntity.getName());
        if (user.getAge() != null) {
            userEntity.setAge(user.getAge().orElseThrow(() -> new IllegalArgumentException("年龄不能为空")));
        }
        return userEntityRepository.save(userEntity);
    }
}
