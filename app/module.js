var app={};
module.exports=app;

//项目名称
app.name='mall';

//项目版本号
app.version='1.0.0';

//项目打包时候的应用id
app.applicationId='com.common.template';

//最低支持的SDK版本号
app.minSdkVersion=16;

//项目创建时间
app.createTime=new Date('2017.12.1 8:30:00');

//最近一次更新时间
app.updateTime=new Date('2017.12.1 8:30:00');

//name为框架或者库的名称;introduce为功能说明;github为项目在git上的地址;若github为null,表示第三方库不在git上,会使用url字段给出项目的网址
app.frames=[
    {
        name:'robotium',
        introduce:'Android UI Testing',
        github:'https://github.com/RobotiumTech/robotium'
    },
    {
        name:'XRecyclerView',
        introduce:'a RecyclerView that implements pullrefresh and loadingmore featrues.you can use it like a standard RecyclerView',
        github:'https://github.com/jianghejie/XRecyclerView'
    },
    {
        name:'CircleImageView',
        introduce:'A circular ImageView for Android',
        github:'https://github.com/hdodenhof/CircleImageView'
    },
    {
        name:'SHSegmentControl',
        introduce:'segmentcontrol widget for android',
        github:'https://github.com/7heaven/SHSegmentControl'
    },
    {
        name:'GridPasswordView',
        introduce:'An android password view that looks like the pay password view in wechat app and alipay app',
        github:'https://github.com/Jungerr/GridPasswordView'
    },
    {
        name:'FlowLayout',
        introduce:'Android流式布局，支持单选、多选等，适合用于产品标签等',
        github:'https://github.com/hongyangAndroid/FlowLayout'
    },
    {
        name:'RxJava',
        introduce:'RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM',
        github:'https://github.com/ReactiveX/RxJava'
    },
    {
        name:'RxAndroid',
        introduce:'RxJava bindings for Android',
        github:'https://github.com/ReactiveX/RxAndroid'
    },
    {
        name:'gson',
        introduce:'A Java serialization/deserialization library to convert Java Objects into JSON and back',
        github:'https://github.com/google/https://github.com/google/gson'
    },
    {
        name:'retrofit',
        introduce:'Type-safe HTTP client for Android and Java by Square, Inc.',
        github:'https://github.com/square/retrofit'
    },
    {
        name:'okhttp',
        introduce:'An HTTP+HTTP/2 client for Android and Java applications.',
        github:'https://github.com/square/okhttp'
    },
    {
        name:'glide',
        introduce:'An image loading and caching library for Android focused on smooth scrolling',
        github:'https://github.com/bumptech/glide'
    },
    {
        name:'dagger',
        introduce:'A fast dependency injector for Android and Java.',
        github:'https://github.com/square/dagger'
    },
    {
        name:'butterknife',
        introduce:'Bind Android views and callbacks to fields and methods.',
        github:'https://github.com/JakeWharton/butterknife'
    },
    {
        name:'logger',
        introduce:'Simple, pretty and powerful logger for android',
        github:'https://github.com/orhanobut/logger'
    },
    {
        name:'AndroidUtilCode',
        introduce:'Android developers should collect the following utils(updating)',
        github:'https://github.com/Blankj/AndroidUtilCode'
    },
    {
        name:'android-zxingLibrary',
        introduce:'几行代码快速集成二维码扫描功能',
        github:'https://github.com/yipianfengye/android-zxingLibrary'
    },
    {
        name:'StatusBarUtil',
        introduce:'A util for setting status bar style on Android App',
        github:'https://github.com/laobie/StatusBarUtil'
    },
    {
        name:'RxDownload',
        introduce:'A multi-threaded download tool written with RxJava and Kotlin',
        github:'https://github.com/ssseasonnn/RxDownload'
    },
    {
        name:'Android-TextView-LinkBuilder',
        introduce:'Insanely easy way to define clickable links within a TextView.',
        github:'https://github.com/klinker24/Android-TextView-LinkBuilder'
    },
    {
        name:'leakcanary',
        introduce:'A memory leak detection library for Android and Java',
        github:'https://github.com/square/leakcanary'
    },
    {
        name:'stetho',
        introduce:'Stetho is a debug bridge for Android applications, enabling the powerful Chrome Developer Tools and much more.',
        github:'https://github.com/facebook/stetho'
    },
    {
        name:'recyclerview-animators',
        introduce:'An Android Animation library which easily add itemanimator to RecyclerView items',
        github:'https://github.com/wasabeef/recyclerview-animators'
    },
    {
        name:'PermissionsDispatcher',
        introduce:'Simple annotation-based API to handle runtime permissions.',
        github:'https://github.com/permissions-dispatcher/PermissionsDispatcher'
    },
    {
        name:'glide-transformations',
        introduce:'An Android transformation library providing a variety of image transformations for Glide',
        github:'https://github.com/wasabeef/glide-transformations'
    },
    {
        name:'TakePhoto',
        introduce:'一款用于在Android设备上获取照片（拍照或从相册、文件中选择）、裁剪图片、压缩图片的开源工具库',
        github:'https://github.com/crazycodeboy/TakePhoto'
    },
    {
        name:'Android-PickerView',
        introduce:'This is a picker view for android , support linkage effect, timepicker and optionspicker.（时间选择器、省市区三级联动）',
        github:'https://github.com/Bigkoo/Android-PickerView'
    },
    {
        name:'Android-ConvenientBanner',
        introduce:'Simple and convenient banner, loop viewpager with 3D effects',
        github:'https://github.com/saiwu-bigkoo/Android-ConvenientBanner'
    },
    {
        name:'analytics',
        introduce:'友盟统计',
        github:null,
        url:'http://dev.umeng.com/analytics/android-doc/integration'
    },{
        name:'TBS',
        introduce:'腾讯浏览服务',
        url:'https://x5.tencent.com/tbs/guide/sdkInit.html'
    }
];

//从main目录开始计算,设定为根目录,目录层级之下为
app.catalogs={
    path:'/main',
    introduce:'项目源码,包括代码,资源文件,配置文件等',
    sub:[
        {
            path:'/res',
            introduce:'项目中所使用的资源文件,包括xml,图片等'
        },
        {
            path:'/com/common/template',
            introduce:'路径为包名,之下包含所有的java源码',
            sub:[
                {
                    path:'/adapter',
                    introduce:'ListView或者RecycleView适配器'
                },
                {
                    path:'/presenter',
                    introduce:'mvp结构处理',
                    sub:[
                        {
                            path:'/contract',
                            introduce:'定义mvp中presenter和view需要实现的接口'
                        }
                    ]
                },
                {
                    path:'/util',
                    introduce:'全局使用的缓存类,字符串处理类,加密类等等'
                },
                {
                    path:'/window',
                    introduce:'自定义的view,dialog,toast,snackBar等widget部件'
                }
            ]
        },
        {
            path:'/assets',
            introduce:'额外的资源文件,如txt文本,h5等',
            sub:[
                {
                    path:'/litepal.xml',
                    introduce:'litepal数据库配置文件'
                }
            ]
        }
    ]
};

app.shares={
    weixin:{

    }
};

//keystore.jks
app.keystore={
}

高德地图开放平台：（绑定手机号：18948780828）
账号：255778184@qq.com
密码：g2535886

融云账号：（绑定手机号：18948780828）
账号：255778184@qq.com
密码：g2535886
