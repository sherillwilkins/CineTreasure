/**********/
import('/static/plugin/jquery/jquery-3.7.1.js')
// import('/static/plugin/layui/layui.js')

/**
 * 判断指定的元素是否存在给定类名
 * @param ele 元素对象
 * @param cls 类名
 * @returns {boolean}
 */
function hasClass(ele, cls) {
    cls = cls || '';
    //当cls没有参数时，返回false
    if (cls.replace(/\s/g, '').length == 0) return false;
    return new RegExp(' ' + cls + ' ').test(' ' + ele.className + ' ');
}

/**
 * 给指定的元素添加类名
 * @param ele 元素对象
 * @param cls 类名
 */
function addClass(ele, cls) {
    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
}

/**
 * 移除指定元素的类名
 * @param ele 元素对象
 * @param cls 类名
 */
function removeClass(ele, cls) {
    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
        newClass = newClass.replace(' ' + cls + ' ', ' ');
    }
    ele.className = newClass.replace(/^\s+|\s+$/g, '');
}

/**
 * 获得session
 * @param key session键
 * @returns {string}
 */
function getSession(key) {
    return sessionStorage.getItem(key);
}

/**
 * 设置session
 * @param key session键
 * @param value session值
 */
function setSession(key, value) {
    var key = key || 'key';
    sessionStorage.setItem(key, value);
}

/**
 * 移除session
 * @param key
 */
function removeSession(key) {
    sessionStorage.removeItem(key);
}

/**
 * 阻止事件向上传播
 * @param evt 事件
 */
function stopPropagation(evt) {
    evt.stopPropagation ? evt.stopPropagation() : evt.cancelBubble = true;
}

/**
 * 初始化公共
 */
function initPublic() {
    //初始化搜索框
    initSearch();
}

function openVip() {
    $.ajax({
        url: "/api/user/vip",
        method: "PUT",
        success: function (res) {
            layer.msg(res.message);
        }
    });
}

function logout() {
    $.ajax({
        url: "/api/user/logout",
        method: "POST",
        success: function (res) {
            layer.msg(res.message);
            window.location.href = "/page/index";
        }
    });
}

/**
 * 初始化搜索框动画
 */
function initSearch() {
    // begin: 点击搜索框以外，如果输入框是活动的，变为不活动
    document.addEventListener('click', function () {
        var si = document.getElementById('search-input');
        if (hasClass(si, 'active')) {
            si.value = '';
            removeClass(si, 'active')
        }
    }, false);
    // 阻止事件冒泡
    document.querySelector('.header .search').onclick = function (event) {
        stopPropagation(event);
    };
    // :end
}

/** 导航栏搜索按钮 */
function searchBtnClick(obj, evt) {
    var si = document.getElementById('search-input');
    if (hasClass(si, 'active')) {
        searchBtnSubmit(si.value);
        si.focus();
    } else {
        addClass(si, 'active');
        si.focus();
    }
}


function searchBtnSubmit(value) {
    window.location.href = "/page/search?keyword=" + value;
}

function initScrollToTop() {
    let tt = document.getElementById("toTop");
    let checkToTop = setInterval(function () {
        let st = document.body.scrollTop || document.documentElement.scrollTop;
        if (st > window.screen.height * 0.6) {
            tt.style.display = "block";
        } else {
            tt.style.display = "none";
        }
    }, 700);

    tt.onclick = function () {
        ScrollTop(0, 300);
    };
    const ScrollTop = (number = 0, time) => {
        if (!time) {
            document.body.scrollTop = document.documentElement.scrollTop = number;
            return number;
        }
        // 设置循环的间隔时间，值越小消耗性能越高
        const spacingTime = 10;
        // 计算循环的次数
        let spacingInex = time / spacingTime;
        // 获取当前滚动条位置
        let nowTop = document.body.scrollTop + document.documentElement.scrollTop;
        // 计算每次滑动的距离
        let everTop = (number - nowTop) / spacingInex;
        let scrollTimer = setInterval(() => {
            if (spacingInex > 0) {
                spacingInex--;
                ScrollTop(nowTop += everTop);
            } else {
                clearInterval(scrollTimer);
            }
        }, spacingTime);
    };
}
