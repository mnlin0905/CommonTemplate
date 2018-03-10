## Question

在已经标注@Autowired注解且**require为true**的情况下,在未传递对应参数跳转时,仍可以成功,这样会导致在目标类中使用时,默认已经检测完成,导致**空指针**异常.

## 问题重现

#### 第一步:设置目标类需要的参数,同时设置该参数为必传

目标Activity文件:

---
     @Autowired(name = "name", required = true)
     String name;
---

#### 第二步:尝试进行跳转,不添加必传参数

当前Activity文件:

---
    ARouter.getInstance()
           .build(***Activity)
           // 不添加任何参数
           .navigation();
---

然后会发现可以成功跳转,且目标Activity的name字段为**null**

## 查看原因

先查看直接对字段赋值的代码,代码所在文件为:\*Activity$$Arouter$$Autowired.java

---
    public class *Activity$$ARouter$$Autowired implements ISyringe {
      private SerializationService serializationService;
    
      @Override
      public void inject(Object target) {
        serializationService = ARouter.getInstance().navigation(SerializationService.class);
        *Activity substitute = (*Activity)target;
        substitute.name = substitute.getIntent().getStringExtra("name");
        if (null == substitute.name) {
          Log.e("ARouter::", "The field 'name' is null, in class '" + *Activity.class.getName() + "!");
        }
      }
    }
---

可以看到这里即便未获取到参数,也只是打印了一行日志而已;


然后再查看生成这个类的**processor**的源码

---
    if (fieldConfig.required() && !element.asType().getKind().isPrimitive()) {  // Primitive wont be check.
        injectMethodBuilder.beginControlFlow("if (null == substitute."+fieldName+")");
        injectMethodBuilder.addStatement(
        "$T.e(\""+Consts.TAG+"\", \"The field '"+fieldName+"' is null, in class '\" + $T.class.getName() + \"!\")",AndroidLog,ClassName.get(parent));
        injectMethodBuilder.endControlFlow();
    }
---

这里框架里面判断:**在该字段必须,且该字段不是java基本类型类型的情况下**
会添加接下来的代码,也就是上面所写的一行log

## 需求

在之前查看makedown以及ARouter框架注释时,发现里面有关于自动注入的说明:

---
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface Autowired {
    
        // Mark param's name or service name.
        String name() default "";
    
        // If required, app will be crash when value is null.
        // Primitive type wont be check!
        boolean required() default false;
    
        // Description of the field
        String desc() default "No desc.";
    }
---

这里面有写:**如果必须字段未传,则将crash掉**
但事实上并没有引发异常出现

现在实际的需求时:需要在目标参数未获取到时,让框架调用自定义的降级策略,也就是把这次跳转当做路径异常来处理,不知道有什么好的实现方法?
或者说是不是我使用框架的方法出现了问题?

````
 说明:当前框架使用的版本为最新版,依次为:1.0.4;  1.3.1;  1.1.4;
````