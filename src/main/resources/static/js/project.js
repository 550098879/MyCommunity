window.onload=function(){

}

//获取父级评论的ajax请求
function findParentComment(){
    var parentId = $("#questionId").val();
    $.get('/findComment/'+parentId+'/1', function(data) {
        console.log("父级评论信息:",data);
        //怎么讲这些信息显示到界面上呢?
        var html = "";
        for(var i in data){
            var item = data[i];
            html+="<div class='media'><div class='media-left'><a href='#'>"+
                "<img class='media-object' style='width:40px;height:40px;border-radius:5px;' src='"+item.user.avatarUrl+"'></a></div>"+
                "<div class='media-body'><h4 class='media-heading'>"+item.user.name+"</h4><p>"+item.comment.content+"</p>"+   //这是回复的内容
                "<div><a class='btn btn-default' role='button' onclick='like("+item.comment.id+")'>"+      //点赞按钮
                "<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span>  "+
                  "<lable id='like-"+item.comment.id+"'>"+item.comment.likeCount+"</lable>"+
                "</a><button class='btn btn-primary' data-target='#"+item.comment.id+"' type='button' data-toggle='collapse'"+
                "aria-expanded='false' aria-controls='collapseExample' onclick='findPChildComment("+item.comment.id+")'>"+       //评论按钮
                "<span class='glyphicon glyphicon-comment'  aria-hidden='true'></span>  "+item.comment.count+
                "</button><span  style='float: right;'>"+new Date(item.comment.gmtModified).toLocaleString()+
                "</span></div>"+
                "<div class='collapse' id='"+item.comment.id+"'>"+
                "<div class='well well-lg' id='comment-"+item.comment.id+"'>"+
                //折叠窗口
                "</div></div>"+
                "</div><hr></div>";
        }
        $("#result").html(html);
        if(data.length > 0){
            var commentCount =item.commentCount+"个回复";
            $("#commentCount").html(commentCount);
        }

    });
}

//获取子级评论的ajax请求
function findPChildComment(parentId){

    $.get('/findComment/'+parentId+'/2', function(data) {
        console.log("子评论:",data);
        var id = "#comment-"+parentId;
        // for(var i in data) {
        //     var item = data[i];
        //
        //     var media = $("</div>",{
        //         "class":'media',
        //     }).append($("<img/>",{
        //         "class":"media-object",
        //         "style":'width:40px;height:40px;border-radius:5px;',
        //         "src":item.user.avatarUrl,
        //     })).append($("</div>",{
        //         "class":'media-body',
        //     })).append($("<h4>",{
        //         "class":'media-heading',
        //         "text":item.user.name,
        //     })).append($("</span>",{
        //         "style":'float: right;',
        //         "text":new Date(item.comment.gmtModified).toLocaleString(),
        //     }));
        //
        //     $(id).prepend(media);
        // }
        var childComment = "";
        for(var i in data){
            var item = data[i];
            childComment+="<div class='media'><div class='media-left'><a href='#'>"+
                "<img class='media-object' style='width:40px;height:40px;border-radius:5px;' src='"+item.user.avatarUrl+"'></a></div>"+
                "<div class='media-body'><h4 class='media-heading'>"+item.user.name+"</h4>"+item.comment.content+   //这是回复的内容
                "</a><span  style='float: right;'>"+new Date(item.comment.gmtModified).toLocaleString()+
                "</span></div></div><hr></div>" ;

        }
        //评论区
        childComment+="<div>"+
                "<input class='form-control' placeholder='评论一下' id='childComment-"+parentId+"'>"+
                "<input type='reset' value='取消' class='btn btn-default' style='float: right;'>"+
                "<input type='button' value='评论' class='btn btn-success' style='float: right;'"+
                " onclick='insertComment("+parentId+")'>"+
                "</div>"

        $(id).html(childComment);

    });


}

function like(id){
//添加点赞数
    $.get("/likeComment/"+id,function(data){
        $("#like-"+id).html(data);
    });
}

//回复问题的ajax请求
function insertComment(commentId) {
    var parentId = $("#questionId").val();
    var type = $("#type").val();
    var content = $("#content").val();

    if(commentId != null){
        parentId = commentId;
        type = 2;
        content = $("#childComment-"+commentId).val();
    }


    if (!content) {
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

                if(type == 2){

                }


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


//添加标签
function selectTag(tag){

    var tags = $("#tags").val();
    if(tags.length == 0){
        $("#tags").val(tag);
    }else{
        if(tags.indexOf(tag) == -1){
            tags = tags+","+ tag ;
            $("#tags").val(tags);
        }

    }


}
