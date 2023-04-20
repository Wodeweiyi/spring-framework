/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.xml;

import org.w3c.dom.Node;

import org.springframework.beans.factory.config.BeanDefinitionHolder;

/**
 * DefaultBeanDefinitionDocumentReader用于处理自定义嵌套（直接位于<bean>下）标记的接口。
 * 装饰也可能基于应用于<bean>标记的自定义属性进行。实现可以自由地将自定义标记中的元数据转换为所需的任意数量的BeanDefinition，并转换封闭
 * <bean>标记的org.springframework.beans.factory.config.BeanDefinition，
 * 甚至可能返回一个完全不同的org.springframework.bean s.factory.configuration.bean Definition来替换原始标记。
 * BeanDefinitionDecorator应该意识到他们可能是一个链的一部分。特别是，BeanDefinitionDecorator应该意识到，
 * 以前的BeanDefinitionDecurator可能已经用org.springframework.aop.framework.ProxyFactoryBean定义
 * 替换了原始的org.springframework.beans.factory.config.BeanDefinition，从而允许添加自定义拦截器。
 * 想要将拦截器添加到封闭bean的BeanDefinitionDecorator应该扩展org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecurator，
 * 它处理链接，确保只创建一个代理，并包含链中的所有拦截器。
 * 解析器从NamespaceHandler中为自定义标记所在的命名空间定位BeanDefinitionDecorator。
 *
 * @author Rob Harrop
 * @since 2.0
 * @see NamespaceHandler
 * @see BeanDefinitionParser
 */
public interface BeanDefinitionDecorator {

	/**
	 * 分析指定的Node（元素或属性），并修饰提供的org.springframework.beans.factory.config.BeanDefinition，返回修饰的定义。
	 * 实现可以选择返回一个全新的定义，该定义将替换生成的org.springframework.beans.factory.BeanFactory中的原始定义。
	 * 提供的ParserContext可用于注册支持主定义所需的任何其他bean。
	 */
	BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder definition, ParserContext parserContext);

}
