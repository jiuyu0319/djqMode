package com.mode.djq.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.mode.djq.net.utils.SPUtil;

public class LibAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.initContext(this);
        OkGo.getInstance().init(this);
    }
}


/********  权限
 *    private final String[] PERMISSIONS = new String[]{
 *             Manifest.permission.READ_EXTERNAL_STORAGE,
 *             Manifest.permission.WRITE_EXTERNAL_STORAGE,
 *             Manifest.permission.CAMERA,
 *             Manifest.permission.RECORD_AUDIO,
 *             Manifest.permission.MODIFY_AUDIO_SETTINGS,
 *             Manifest.permission.ACCESS_COARSE_LOCATION,
 *             Manifest.permission.ACCESS_FINE_LOCATION,
 *             Manifest.permission.ACCESS_WIFI_STATE,
 *             Manifest.permission.CHANGE_WIFI_STATE,
 *     };
 *
 *       @SuppressLint("WrongConstant")
 *     private void checkPermission() {
 *         AndPermission.with(this)
 *                 .runtime()
 *                 .permission(PERMISSIONS)
 *                 .onGranted(new Action<List<String>>() {
 *                     @Override
 *                     public void onAction(List<String> data) {
 *
 *                         new Handler().postDelayed(new Runnable() {
 *                             @Override
 *                             public void run() {
 *                                 goToActivity();
 *                             }
 *                         },1000);
 *
 *                     }
 *                 })
 *                 .onDenied(new Action<List<String>>() {
 *                     @Override
 *                     public void onAction(List<String> data) {
 *
 *                          // 当用户没有允许该权限时，回调该方法
 *                          Toast.makeText(SplashActivity.this,"没有获取文件存储权限，该功能无法使用",Toast.LENGTH_SHORT).show();
 *
 *                          判断用户是否点击了禁止后不再询问，AndPermission.hasAlwaysDeniedPermission(MainActivity.this, data)
 *
 *                      goToActivity();
 *                      if(AndPermission.hasAlwaysDeniedPermission(SplashActivity.this,data)){
 *                             //true，弹窗再次向用户索取权限
 *                      showSettingDialog(SplashActivity.this,data);
 *                  }
 *                  }
 *                   }).start();
 *
 *              }
 *
 *          private void showSettingDialog(SplashActivity splashActivity,List<String> data){
 *             }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */



/**
 *      65535
 *     implementation 'com.android.support:multidex:1.0.3'

 *     Application
 *
 *     onCreate(){
 *       MultiDex.install(this);
 *     }
 */

/**
 * 一般在Aplication，或者基类中配置，只需要调用一次即可
 * 可以配置log开关
 * 全局的超时时间
 * 全局cookie管理策略
 * Https配置
 * 超时重连次数
 * 公共的请求头和请求参数等信息
 * 不要忘记了在清单文件中注册 Application
 * 不要忘记了在清单文件中注册 Application
 * 不要忘记了在清单文件中注册 Application
 * 1. 最简单的配置
 * 如果你什么都不想自己写，那么下面一行代码就够了。
 * OkGo.getInstance().init(this);
 * 这样写是使用OkGO内部默认初始化的OkHttpClient来进行网络请求，包含了基本的log打印，超时时间和https相关的配置，但是建议还是自己配置好OkHttpClient传给OkGo比较好，详细自定义OkHttpClient的方法就是配置原生的okhttp的方法，建议的一些配置如下，都是可选的，如果需要你就加，不需要就别加了。
 * 2. 构建OkHttpClient.Builder
 * 这个不用多说，一行代码搞定
 * OkHttpClient.Builder builder = new OkHttpClient.Builder();
 * 3. 配置log
 * 可以使用OkGo内置的log拦截器打印log，如果你觉得不好用，也可以自己写个，这个没有限制。
 * HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
 * //log打印级别，决定了log显示的详细程度
 * loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
 * //log颜色级别，决定了log在控制台显示的颜色
 * loggingInterceptor.setColorLevel(Level.INFO);
 * builder.addInterceptor(loggingInterceptor);
 *
 * //非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
 * builder.addInterceptor(new ChuckInterceptor(this));
 * 4. 配置超时时间
 * 默认使用的超时时间就是60秒，如果你想改，可以自己设置
 * //全局的读取超时时间
 * builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
 * //全局的写入超时时间
 * builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
 * //全局的连接超时时间
 * builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
 * 很多人问这三个超时时间是什么意思？这里稍微解释一下：
 * connectTimeout：指http建立通道的时间，我们知道http底层是基于TCP/IP协议的，而TCP协议有个三次握手协议，所谓三次握手简单的理解为
 * 客户端问服务端：我要准备给你发数据了，你准备好了么
 * 服务端向客户端回答：我准备好了，你可以发数据了
 * 客户端回答服务端：我收到你的消息了，我要发数据了
 * 然后巴拉巴拉一堆数据过去了。 这里就能看出来，只有这三次握手建立后，才能开始发送数据，否则数据是无法发送的，那么建立这个通道的时间就叫做connectTimeout，想一想，如果我们网络不好，平均建立这个通道就要10秒，结果我们代码中设定的这个时间是5秒，那么这个连接永远建立不起来，建立到一半，就中断了。
 * writeTimeout：基于前面的通道建立完成后，客户端终于可以向服务端发送数据了，客户端发送数据是不是要把数据写出去啊，所以叫写入时间，突然，服务器挂了，客户端能知道服务器挂了么，不知道的，所以客户端还在继续傻傻的向服务端写数据，可是服务端能收到这个数据么，肯定收不到，服务端都挂了，怎么收，同样的，客户端这个数据其实是写不出去的，客户端又写不出去，他又不知道服务端不能接受数据了，难道要一直这么等着服务端缓过来？肯定是不可能的哈，这样会造成资源的极端浪费，所以这个时候就有个writeTimeout时间控制这个傻傻的客户端要等服务端多长时间。
 * readTimeout：继续前面的，现在通道连接建立完成了，客户端也终于把数据发给服务端了，服务端巴拉巴拉一顿计算，把客户端需要的数据准备好了，准备返回给客户端。but，要搞事情了，网络不通或者客户端出了毛病，客户端无法接受到服务端的数据了，类比之前的分析，客户端要这么傻傻的等着服务端发数据么，就算你等着他也发不过来了是不，这时候就有了个readTimeout时间来控制这个过程，告诉客户端收不到服务端的数据时，要傻傻等多久。
 * 现在这三个时间我们都有了印象，他是控制了http进行数据交互的三个阶段的超时时间，试想一下，假如我们把这三个时间都设置为一分钟，那么最坏最巧合的时候，刚好connectTimeout要超时候，啪，连上了，然后刚好writeTimeout要超时的时候，啪，数据发出去了，然后又刚好readTimeout要超时的时候，啪，数据收到了，所以你等了三分钟，依然没有超时，数据还能正常收到。懂了么？只是这种情况实在太难遇到！
 * 5. 配置Cookie，以下几种任选其一就行
 * 如果你用到了Cookie的持久化或者叫Session的保持，那么建议配置一个Cookie，这个也是可以自定义的，不一定非要用OkGo自己的，以下三个是OkGo默认提供的三种方式，可以选择添加，也可以自己实现CookieJar的接口，自己管理cookie。
 * //使用sp保持cookie，如果cookie不过期，则一直有效
 * builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
 * //使用数据库保持cookie，如果cookie不过期，则一直有效
 * builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
 * //使用内存保持cookie，app退出后，cookie消失
 * builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
 * 6. Https配置，以下几种方案根据需要自己设置
 * 这个也是可以自定义的，HttpsUtils只是框架内部提供的方便管理Https的一个工具类，你也可以自己实现，最好只要给OkHttpClient.Builder传递一个sslSocketFactory就行。
 * //方法一：信任所有证书,不安全有风险
 * HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
 * //方法二：自定义信任规则，校验服务端证书
 * HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
 * //方法三：使用预埋证书，校验服务端证书（自签名证书）
 * HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
 * //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
 * HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
 * builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
 * //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
 * builder.hostnameVerifier(new SafeHostnameVerifier());
 * 7. 配置OkGo
 * 以上代码主要是OkHttpClient的配置，其实和OkGo也没啥关系，你要是使用其他okhttp的框架也得配置，都是一样的，包括你配置其他拦截器什么的，只要okhttp支持的，你都可以加，都是有效的。那么下面的代码才是OkGo特有的配置，在初始化完成后，可以传入我们配置好的OkHttpClient，也可以配置其他参数，详细如下：
 * //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
 * HttpHeaders headers = new HttpHeaders();
 * headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
 * headers.put("commonHeaderKey2", "commonHeaderValue2");
 * HttpParams params = new HttpParams();
 * params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
 * params.put("commonParamsKey2", "这里支持中文参数");
 * //-------------------------------------------------------------------------------------//
 *
 * OkGo.getInstance().init(this)                       //必须调用初始化
 *     .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
 *     .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
 *     .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
 *     .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
 *     .addCommonHeaders(headers)                      //全局公共头
 *     .addCommonParams(params);                       //全局公共参数
 */
