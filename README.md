# djqMode

                  allprojects {
                      repositories {
                        ...
                        maven { url 'https://jitpack.io' }
                      }
                    }

                  dependencies {
                            implementation 'com.github.jiuyu0319:djqMode:1.0.+'
                    }


1.内置升级功能，一句话就可以升级app 兼容安卓10. 通知栏进度条










                 DownloadManager manager = DownloadManager.getInstance(MainActivity.this);
                                                manager.setApkName("appupdate.apk")
                                                        .setApkUrl(apkurl)
                                                        .setSmallIcon(R.mipmap.logo)
                                                        .download();
2. 继承了okgo  简单用法get 








                new OKGO().Get("url", MainActivity.this, new OkGoNetCallBack() {
                          @Override
                          public void onSuccess(String data) {
                              LogUtil.e("onsuccess",data);
                          }

                          @Override
                          public void onError(Throwable e) {

                          }
                      });
                psot 
                
                HttpParams httpParams = new HttpParams();
                httpParams.put("platform","windows");
                httpParams.put("channel","gotocn");
                httpParams.put("start","1");
        
            new OKGO().Post("",MainActivity.this,httpParams,new OkGoNetCallBack(){

            @Override
            public void onSuccess(String data) {
                
            }

            @Override
            public void onError(Throwable e) {

            }
        });
