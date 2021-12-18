package tech.bittercoffee.wechat.api.trade.models;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识一个类的属性是一个API接口传递/嵌套类型的名称
 * 
 * @author BitterCoffee
 *
 */
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiField {

	/**
	 * @return 在API调用/返回中的字段名
	 */
	String name();

	/**
	 * @return 对应的嵌套子类类型
	 */
	Class<?> subType() default Void.class;

	/**
	 * @return 子对象名称
	 */
	String subName() default "";

	/**
	 * @return 对应的标识这个集合中元素数量的字段名
	 */
	String countName() default "";
}
