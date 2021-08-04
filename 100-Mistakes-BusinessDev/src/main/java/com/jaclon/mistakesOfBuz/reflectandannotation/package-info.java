/**
 * 泛型因为类型擦除会导致泛型方法 T 占位符被替换为 Object，子类如果使用具体类 型覆盖父类实现，编译器会生成桥接方法。
 * 这样既满足子类方法重写父类方法的定义，又满 足子类实现的方法有具体的类型。使用反射来获取方法清单时，需要特别注意这一点。
 *
 * 自定义注解可以通过标记元注解 @Inherited 实现注解的继承，不过这只适用于类。
 * 如果要继承定义在接口或方法上的注解，可以使用 Spring 的工具类 AnnotatedElementUtils，并注意各种 getXXX 方法和 findXXX 方法的区别
 *
 * @author jaclon
 * @since 2021/8/4 18:35
 */
package com.jaclon.mistakesOfBuz.reflectandannotation;

/**
 * Java 的泛型类型在编译后擦除为 Object。虽然子类指定了父类泛型 T 类型是 String，
 * 但编译后 T 会被擦除成为 Object，所以父类 setValue 方法的入参是 Object， value 也是 Object。
 * 如果子类 Child2 的 setValue 方法要覆盖父类的 setValue 方法，那 入参也必须是 Object。
 * 所以，编译器会为我们生成一个所谓的 bridge 桥接方法
 */