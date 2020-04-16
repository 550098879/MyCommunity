#项目笔记

###一.功能实现  
##登陆  

1.通过GitHub实现第三方授权登陆社区  
[GitHub API]https://developer.github.com  
  
Github -> setting -> Developer settings -> 创建一个OAuth Apps  
登陆链接 :
https://github.com/login/oauth/authorize?client_id=客户编号&redirect_uri=回调网址&scope=user&state=1  
2.通过okhttp发送post请求和get请求,并使用fastjson将参数对象转换为json格式字符串(创建一个实体类用于传递所需参数)
```
   AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
   accessTokenDTO.setClient_id("***");//OAuth 上的clientid和clientsecret
   accessTokenDTO.setClient_secret("***");
   accessTokenDTO.setCode(code);
   accessTokenDTO.setRedirect_uri("http://localhost:7777/callback");
   accessTokenDTO.setState(state);
   String accessToken = githubProvider.getAccessToken(accessTokenDTO);

    //设置相应参数
```
3.编写getAccessToken()方法,通过okhttp方式发送post请求,获取访问令牌  
  
4.通过访问令牌,"https://api.github.com/user?access_token="+access_token  

  发出get请求,返回github用户的json数据,通过 JSON.parseObject(str, GithubUser.class)
  返回一个用户的数据,至此完成第三方登陆授权.  
  
#####注意事项  

1.OkHttp中的response.body().string();操作实际上是流操作,只执行一次.  
2.出现Connection reset的原因可能是当前网络请求次数过多,可以重启网络尝试解决
3.AccessTokenDTO()实体类的参数都对应OAuth Apps 中的帮助文档中的参数,不可写错.
4.涉及的包和类:   
 provider:授权管理相关类  
 AuthorizeController:此处应注意,类不要添加RequestMapping,否则回调地址找不到  
 相应的请求地址.只需要在callback方法上添加  @GetMapping("/callback")即可.  
 entity:负责数据模型传输  
5.OkHttp中的Request和servlet中的request是不同的,注意引入的包是否错误
6.涉及依赖:
```
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>3.14.1</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.60</version>
</dependency>

```
6.流程总结:  
  
1)通过bootstrap完成简单的登陆界面     
 
2)前往GitHub创建一个OAuth App,阅读授权OAuth应用文档,完成创建
3)绑定登陆访问连接:https://github.com/login/oauth/authorize?client_id=c675236053a463ef56cb&redirect_uri=http://localhost:7777/callback&scope=user&state=1
4)获取AccessToken,先发送post请求获取AccessToken,再通过get请求获取用户信息(provider.GithubProvider) 
5)通过访问令牌(AccessToken)获取用户信息  
6)此处还未完全完成登录功能,只是可以获取到自己的GitHub用户信息.

 

        





   
   