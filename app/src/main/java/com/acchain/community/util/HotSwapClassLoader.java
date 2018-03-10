package com.acchain.community.util;

/**
 * Created on 2018/2/27
 * function : 自定义类加载器
 * <p>
 * 为了多次载入执行类而加入的加载器
 * <p>
 * 吧defineClass方法放开，只有外部显示调用的时候才会用到loadByte方法
 * <p>
 * 由虚拟机调用时，仍然按照双亲委派规则使用loadClass方法进行类加载
 *
 * @author ACChain
 */

public class HotSwapClassLoader extends ClassLoader {
    /**
     * Creates a new class loader using the <tt>ClassLoader</tt> returned by
     * the method {@link #getSystemClassLoader()
     * <tt>getSystemClassLoader()</tt>} as the parent class loader.
     * <p>
     * <p> If there is a security manager, its {@link
     * SecurityManager#checkCreateClassLoader()
     * <tt>checkCreateClassLoader</tt>} method is invoked.  This may result in
     * a security exception.  </p>
     *
     * @throws SecurityException If a security manager exists and its
     *                           <tt>checkCreateClassLoader</tt> method doesn't allow creation
     *                           of a new class loader.
     */
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 加载自定义的类（字节流形式）
     *
     * @param classByte 字节流
     * @return 返回加载完成的class类
     */
    public Class loadByte(byte[] classByte) {
        return defineClass(null,classByte,0,classByte.length);
    }
}
