<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script>
var parseData = '';
var strData = '';

window.onload = function () {
  $.ajax({
    type: "GET",
    url: "/getIntentsList",
    success: function(data){
      parseData= JSON.parse(data.data).items;
      intentsNameList();
      // google.charts.load('current', {'packages':['bar']});
      // google.charts.setOnLoadCallback(drawChart);
    }
  });
};


function intentsNameList(){

  for(var i = 0; i < parseData.length; i++){
    $("#intentList").append("<a href='#' onclick='intentDetail(\"" + parseData[i].intent_id + "\")'> - " + parseData[i].intent_name + "</a> <br>");
  }
};


function intentDetail(id){

  $.ajax({
    type: "POST",
    url: "/getIntentDetailList",
    contentType: 'application/json',
    dataType : 'json',
    data: JSON.stringify({ id: id }),
    success: function(data){
      parseData= JSON.parse(data.data).items;
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
    }
  });
}




function drawChart() {
  var dateList = new Array();
  var countList = new Array();

  for(var i = 0; i < parseData.length; i++){
    var timestamp = parseData[i].date * 1000;
    var sysdate = new Date(timestamp);
    sysdate = dateToStr(sysdate);

    dateList[i] = sysdate
    countList[i] = parseData[i].count;
  }

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Date');
  data.addColumn('number', 'Count');

  for(i = 0; i < dateList.length; i++){
    data.addRow([dateList[i], countList[i]]);
  }

  var options = {
    chart: {
      title: 'intents Performance'
      // ,subtitle: 'Sales, Expenses, and Profit: 2014-2017',
    },
    hAxis : {
      textStyle : {
        fontSize: 10
      }

    }
  };

  var chart = new google.charts.Bar(document.getElementById('content'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
};

function dateToStr(format){
  var year = format.getFullYear();

  var month = format.getMonth() + 1;
  if(month<10) month = '0' + month;

  var date = format.getDate();
  if(date<10) date = '0' + date;

  var hour = format.getHours();
  if(hour<10) hour = '0' + hour;

  var min = format.getMinutes();
  if(min<10) min = '0' + min;

  var sec = format.getSeconds();
  if(sec<10) sec = '0' + sec;

  // return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
  return year + "-" + month + "-" + date;
};

</script>

<div style="width: 10%; height: 500px; float: left; padding-left: 20px;">
Intents
    <div id = "intentList" >

    </div>

</div>
<div id = "content" style="width: 80%; height: 500px;float: left;">


</div>