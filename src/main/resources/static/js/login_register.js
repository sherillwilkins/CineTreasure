//用户的用户名和密码
var user = {'root': 'root', 'username': 'password'};

function initLoginPage() {
    //初始化验证码
    const captcha = document.getElementById('captcha-img');
    sessionStorage.removeItem("Captcha");
    captcha.src = generateCaptcha();
    captcha.onclick = function () {
        captcha.src = generateCaptcha();
    };
}

/**
 * 产生验证码并且设置session
 */
function generateCaptcha() {
    var code = getRandomString();
    sessionStorage.removeItem("Captcha");
    setSession('Captcha', code);
    var canvas = getStringCanvas(code);
    return canvasToBase64Image(canvas);
}

/**
 * 得到随机字符串
 * @param  {number} len [字符串长度]
 * @return {[String]}     [随机字符串]
 */
function getRandomString(len) {
    var ostr = "abcdefghijkmnpqrstuvwxyzABCEFGHJKLMNPQRSTWXYZ1234567890";
    var len = len || 4;
    var ostr_len = ostr.length;
    var randomString = "";
    for (var i = 0; i < len; i++) {
        randomString += ostr.charAt(Math.floor(Math.random() * ostr_len));
    }
    return randomString;
}


/**
 * 得到随机颜色的16进制值
 * @return {[String]} [颜色的十六进制值]
 */
function getRandomColor() {
    return '#' + ('00000' + (Math.random() * 0x1000000 << 0).toString(16)).substr(-6);
}

/**
 * HTML5 canvas对象转换成png格式图片的base64编码字符串
 * @param canvas {HTMLCanvasElement}
 * @returns {string}png格式图片的base64编码字符串
 */
function canvasToBase64Image(canvas) {
    return canvas.toDataURL("image/png");
}

/**
 * 获得
 * @param string
 * @returns {HTMLCanvasElement}
 */
function getStringCanvas(string) {
    var canvas = document.createElement("canvas"); //创建canvas的对象
    var context = canvas.getContext("2d"); //canvas画图的环境

    canvas.width = 80;
    canvas.height = 30;
    var string = string || "capa";

    // 填充字符串
    for (var i = 0; i < string.length; i++) {
        var txt = string.charAt(i);
        var text_x = 10 + i * 18;
        var text_y = 20 + Math.random() * 6;
        // 产生一个随机角度
        var angle = Math.random() - 0.8;
        context.font = "bold 22px 微软雅黑";
        context.fillStyle = getRandomColor();
        context.translate(text_x, text_y); // 移动到想0+x，0+y
        context.rotate(angle);
        context.fillText(txt, 0, 0);
        // 还原
        context.rotate(-angle);
        context.translate(-text_x, -text_y);
    }

    // 验证码上显示线条
    for (var i = 0; i < 5; i++) {
        context.strokeStyle = getRandomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas.width, Math.random() * canvas.height);
        context.lineTo(Math.random() * canvas.width, Math.random() * canvas.height);
        context.stroke();
    }

    // 验证码上显示斑点
    for (var i = 0; i < 30; i++) {
        context.strokeStyle = getRandomColor();
        context.beginPath();
        var x = Math.random() * canvas.width;
        var y = Math.random() * canvas.height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }

    return canvas;
}

//End Login Code
