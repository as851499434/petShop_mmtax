function selectChange() {
    $("#taskTitle1").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent1").val(result.msg)
        })
    })
    $("#taskTitle2").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent2").val(result.msg)
        })
    })
    $("#taskTitle3").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent3").val(result.msg)
        })
    })
    $("#taskTitle4").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent4").val(result.msg)
        })
    })
    $("#taskTitle5").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent5").val(result.msg)
        })
    })
    $("#taskTitle6").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent6").val(result.msg)
        })
    })
    $("#taskTitle7").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent7").val(result.msg)
        })
    })
    $("#taskTitle8").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent8").val(result.msg)
        })
    })
    $("#taskTitle9").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent9").val(result.msg)
        })
    })
    $("#taskTitle10").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent10").val(result.msg)
        })
    })
    $("#taskTitle11").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent11").val(result.msg)
        })
    })
    $("#taskTitle12").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent12").val(result.msg)
        })
    })
    $("#taskTitle13").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent13").val(result.msg)
        })
    })
    $("#taskTitle14").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent14").val(result.msg)
        })
    })
    $("#taskTitle15").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent15").val(result.msg)
        })
    })
    $("#taskTitle16").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent16").val(result.msg)
        })
    })
    $("#taskTitle17").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent17").val(result.msg)
        })
    })
    $("#taskTitle18").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent18").val(result.msg)
        })
    })
    $("#taskTitle19").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent19").val(result.msg)
        })
    })
    $("#taskTitle20").change(function(){
        $.get("/manager/taskInfo/getTaskInfoById?id="+$(this).children('option:selected').val(),function (result) {
            $("#taskContent20").val(result.msg)
        })
    })
}