var result;

function sleep(delay) {
    var start = (new Date()).getTime();
    while ((new Date()).getTime() - start < delay) {
        continue;
    }
}

function check() {
    result = true;
    var email = document.getElementById("email").value;
    var isemail =  isEmail(email);
    if(isemail){
        result = result && true;
    }else {
        $("#email").focus();
        result = result && false;
    }

    var pwd = document.getElementById("passwd").value;
    if(pwd.length<6){
        alert("密码必须大于6位");
        result = result && false;
        $("#passwd").focus();
    }else if(pwd.length>16){
        alert("密码必须小于16位");
        result = result && false;
        $("#passwd").focus();
    }

    var repasswd = document.getElementById("repasswd").value;
    if(repasswd == pwd){
        result = result && true
    }else{
        $("#repasswd").focus();
        result = result && false
    }



    if(result){
        $("#tos_modal").modal();

    }else {
        return false
    }

}

function  sunbmit_form() {
    $("#form").submit();
}

function isEmail(strEmail){
    //定义正则表达式的变量:邮箱正则
    var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    //文本框不为空，并且验证邮箱正则成功，给出正确提示
    if(strEmail != null && strEmail.search(reg) != -1)
    {
        return true
    }else{
        return false
    }
}
//检测邮箱
document.getElementById("email").addEventListener("input",function () {
    var email  = document.getElementById("email").value;
    var b = isEmail(email);
    const  email_strong = document.getElementById("emailCheck");
    if(b){
        email_strong.innerHTML= '<span style="color: green; font-weight: bold">邮箱可用️</span>';
    }else {
        email_strong.innerHTML = '<span style="color: red; font-weight: bold\">邮箱不可用！️</span>';
    }

})

//检测密码
document.getElementById("repasswd").addEventListener("input",function () {
    var repasswd  = document.getElementById("repasswd").value;
    const  repassCheck = document.getElementById("passwdCheck");
    var passwd = document.getElementById("passwd").value;
    if(passwd != repasswd){
        repassCheck.innerHTML= '<span style="color: red; font-weight: bold">密码不一致!请重新输入</span>';
    }else {
        repassCheck.innerHTML= '<span style="color: green; font-weight: bold">密码一致</span>';
    }

})

document.getElementById("passwd").addEventListener("input",function () {
    var repasswd  = document.getElementById("repasswd").value;
    const  repassCheck = document.getElementById("passwdCheck");
    var passwd = document.getElementById("passwd").value;
    if(passwd != repasswd){
        repassCheck.innerHTML= '<span style="color: red; font-weight: bold">密码不一致!请重新输入</span>';
    }else {
        repassCheck.innerHTML= '<span style="color: green; font-weight: bold">密码一致</span>';
    }

})