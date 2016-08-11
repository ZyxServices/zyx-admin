/**
 * Created by Rainbow on 16-8-8.
 */
var assembled = function (desc) {
    var jqueryObj = $(desc);
    //console.log(jqueryObj);
    var arrayConstants = [], objConstants = {};
    $.each(jqueryObj, function (i, item) {
        objConstants = {};
        objConstants.type = 1;
        $(item)[0].tagName.toLowerCase() == "br" ? objConstants.symbol = 1 : objConstants.symbol = 3;
        arrayConstants.push(objConstants);
        if ($(item).children().length > 0) {
            checkChildren($(item).children())
        } else {
            assem(item);
        }
    })
};

/**
 * 解析处理过程
 */
var tempArray = [], tempObj = {};
var checkChildren = function (context) {
    console.log(context)
    /*$.each(context, function (i, item) {
        tempObj = {};
        if ($(item).children().length > 0) {
            checkChildren($(item).children());
        } else {
            assem(item);
        }
    })*/
};

/**
 * 具体解析步骤
 */
var assem = function (data) {

    console.log($(data)[0])
    //console.log($(data)[0].tagName.toLowerCase());
    if ($(data)[0].tagName == "img") {

    } else if ($(data)[0].tagName == "a") {

    } else if ($(data)[0].tagName == "br" || $(data)[0].tagName == "p") {
        tempObj.type = 1;
        $(data)[0].tagName == "br" ? tempObj.symbol = 1 : tempObj.symbol = 3;
        tempArray.push(tempObj);
    } else {
        tempObj.type = 1;
        tempObj.text = $(data).html();
        $(tempObj.text)
        console.log($(data).attr("style"));
    }
}

