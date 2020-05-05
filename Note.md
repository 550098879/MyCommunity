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

2.出现Connection reset的原因可能是当前网络请求GitHub太慢,可以重启网络尝试解决  
或者更换浏览器试试  

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

###H2数据库(嵌入式数据库)  
1.引入H2数据库依赖  
```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>1.4.199</version>
</dependency>
```
2.调用idea的Database工具栏,连接H2数据库,只需要设置数据库存放位置即可.  
1)url:jdbc:h2:d:/H2/community windows填写具体文件路径  

2)下载driver,测试连接,成功后H2文件夹下面会多出一个数据库文件,连接成功.  

3)直接操作创建的数据库,new 新增需要的表或其他信息  

4)其他数据库的连接也累死,但是直接连接数据库,而H2数据库则是先创建数据库再使用.  

###SpringBoot整合MyBatis
1.引入依赖
```
<!--MyBatis的依赖,来自mybatis-->
	<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
	</dependency>
  <!--jdbc的依赖-->
  <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>

```
2.编写持久化接口(数据库操作接口),有两种方式执行sql  
1)在编写的repository接口上添加@Mapper注解,然后在方法上添加@S/U/D/I(sql语句)注解,执行sql语句  
2)编写单独的Mapping文件,放在指定文件中,并在application.properties中添加路径配置,配置完成即可调用接口中的方法


###通过cookie实现服务器重启而不影响登陆状态
 1.第一次登陆成功后,将数据保存到数据库,同时使用UUID获取一个唯一的token存储到数据库中(作为查询使用)  
 2.下次登陆时,进入主页后遍历cookie,查找是否存在token,存在则查询数据库,并将数据存储到session中即可.  
 3.此处受益于thymeleaf模板,可以根据登陆状态控制组件的显示和消除

        
###Flyway数据库更新控制
1.基于H2的数据库变更脚本记录
2.引入依赖,添加到<build><plugins>中,
```xml
    <!--FlyWay H2数据库同步(记录下操作,并依次执行脚本)-->
    <!--mvn flyway:migrate 执行更新方法-->
    <!--当修改了数据库表的时候,可以添加V2_Add_bio_to_user_table.sql(形容操作),然后执行该sql语句-->
    <plugin>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>6.3.3</version>
        <configuration>
            <url>jdbc:h2:d:/H2/community</url>
            <user>sa</user>
            <password>123</password>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <version>1.4.199</version>
            </dependency>
        </dependencies>
    </plugin>
    

```
3.在修改了数据库够,只需要在db/migration文件下添加一个相应的修改sql,并执行  
mvn flyway:migrate 命令即可


##BootStrap
1.栅格布局方式(分为12格,响应式布局)
```html
<div class="container-fluid">
  <div class="row">
      <div class="col-md-9">  
      <div class="col-md-3">
  </div>
</div>

```
  
2.iframe的后续可利用性,减少页面的跳转

###背景图片的问题(加载本地静态资源问题)  
1.将静态资源放置在static目录下,项目启动会自动加载这个文件下的静态资源  
2.加载静态资源时,不许要添加static文件的路径,直接添加 images/xxx.jpg即可
3.基于根目录加载静态文件: /images/... ; /js/xxx.js    
  static为默认加载的根目录,不需要添加前缀

###Thymeleaf  
1.代码复制功能
```html
<!-- 新建一个nav.html网页 只留下基础的网页网页元素-->
<div th:fragment="nav">
    <!--要被复制的代码-->
</div>

<div th:include="nav.html :: nav">
    <!--引入代码,nav是需要对应的-->
</div>

```

###枚举类  
```java
package org.zyx.exception;

/** 错误码顶级接口,兼容不同类型的错误码枚举类
 * Created by SunShine on 2020/5/3.
 */
public interface ICustomizeErrorCode {

    String getMessage();

}

/** 枚举类
 *  1.定义不同类型的ErrorCode
 *  2.由于存在不同类型的错误信息,如用户,问题,系统等错误信息,会定义多种错误枚举类
 *  3.枚举类定义的不是常量,而是对象,
 *  4.私有构造实现无法在其他类创建对象,只能在本类创建对象
 * Created by SunShine on 2020/5/3.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    /** 1.定义的对象,调用枚举类的私有方法,给message赋值
     *  2.QUESTION_NOT_FOUND 不是常量,而是对象
     *  3.message是需要的错误信息
     *  4.执行顺序: 调用私有构造 -- 为message赋值 -- 在异常类中调用getMessage()方法获取错误信息
     */
    QUESTION_NOT_FOUND("问题已删除或不存在,请重试");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}


/** 自定义异常类
 * Created by SunShine on 2020/5/3.
 */
public class CustomizeException extends RuntimeException{

    private String massage;
    public CustomizeException(String msg){
        this.massage=msg;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){

        /** 1.顶级接口参数
         *  2.实质上是不同的枚举类的对象(实现了ICustomizeErrorCode错误码接口)
         *  3.调用枚举类对象的getMessage()方法,获取错误信息
         */
        this.massage=errorCode.getMessage();
    }

    public String getMessage() {
        return massage;
    }
}


//在需要手动抛出异常时: 
//throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);调用枚举类的固有对象


```

- 外键约束导致的引用完整性异常:是否只针对H2数据库?暂未可知,先删除外键约束完善功能
   
