window.onload=function(){
    findParentComment();//加载评论信息
}

//获取父级评论的ajax请求
function findParentComment(){
    var parentId = $("#questionId").val();
    $.get('/findComment/'+parentId+'/1', function(data) {
        console.log("父级评论信息:",data);
        //怎么讲这些信息显示到界面上呢?

    });
}

//获取子级评论的ajax请求
function findPChildComment(parentId){

    $.get('/findComment/'+parentId+'/2', function(data) {


    });
}





//添加评论的ajax请求
function insertComment() {
    var parentId = $("#questionId").val();
    var type = $("#type").val();
    var content = $("#content").val();
    if (content == "") {
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "post",
        url: "/comment",
        dataType: "json",
//发送json对象
        contentType: "application/json", //设置内容类型
        data: JSON.stringify({  //设置json对象参数
            "parentId": parentId,
            "type": type,
            "content": content,
        }),
          // data:{
          //      //普通字段,不是json对象,服务器接收不需要@RequestBody注解,
          //         也不需要写contentType
          //     "parentId":parentId,
          //     "type":type,
          //     "content":content,
          // },
        success: function (data) {
            console.log("返回的数据", data);
            if (data.code == 200) {
                $("#comment").hide();//隐藏回复区
                $("#content").val("");//清空文本域
                findParentComment();//刷新评论信息
            } else {
                if (data.code == 2003) {
                    if (confirm(data.message)) {
                        //选择是否前往登陆
                        location.href = "https://github.com/login/oauth/authorize?client_id=c675236053a463ef56cb&redirect_uri=http://localhost:7777/callback&scope=user&state=1";
                        window.localStorage.setItem("parentId", parentId);
                    }
                } else {
                    alert(data.message);
                }
            }
        },
    });
}