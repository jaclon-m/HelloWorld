package com.jaclon.mistakesOfBuz.reflectandannotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 *
 *
 * @author jaclon
 * @since 2021/8/4 18:29
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws NoSuchMethodException {
        right();
    }

    public static void right() throws NoSuchMethodException {
        Parent parent = new Parent();
        log.info("ParentClass:{}", getAnnotationValue(parent.getClass().getAnnotation(MyAnnotation.class)));
        log.info("ParentMethod:{}", getAnnotationValue(parent.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));

        Child child = new Child();
        log.info("ChildClass:{}", getAnnotationValue(AnnotatedElementUtils.findMergedAnnotation(child.getClass(), MyAnnotation.class)));
        log.info("ChildMethod:{}", getAnnotationValue(AnnotatedElementUtils.findMergedAnnotation(child.getClass().getMethod("foo"), MyAnnotation.class)));

    }

    private static String getAnnotationValue(MyAnnotation annotation) {
        if (annotation == null) return "";
        return annotation.value();
    }

    @MyAnnotation(value = "ClassHaha")
    @Slf4j
    static class Parent {

        @MyAnnotation(value = "MethodHaha")
        public void foo() {
        }
    }

    @Slf4j
    static class Child extends Parent {
        @Override
        public void foo() {
        }
    }
}
