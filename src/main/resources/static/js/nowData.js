// 绘制曲线
var chart = Highcharts.chart('container', {

    chart: {

        type: 'spline',
        // events: {
            load: refreshData(),// 图表加载完毕后执行的回调函数
        //   }
        backgroundColor: 'transparent',
    },
    title: {
        text: ''
    },
    subtitle: {
        text: ''
    },
    yAxis: {
        // minorGridLineDashStyle: 'longdash',
        // minorTickInterval: 'auto',
        // minorTickWidth: 0,
        title: {
            text: ''
        }
        // labels: {
        //     style: {
        //         color: '#9faab3'
        //     }
        // }

    },
    xAxis: {
        // minorGridLineDashStyle: 'longdash',
        labels: {
            style: {
                color: '#9faab3'
            }
        }
    },
    plotOptions: {
        series: {
            label: {
                connectorAllowed: false
            },
            pointStart: 0
        }
    },
    series: [{
        name: '',
        data: [],
        // data: [[1,2],[2,1.5]],
        //data: ${sessionScope.sessionData},
        point: {
            events: {
                remove: function () {
                    return true;
                }
            }
        }
    }],
    responsive: {
        rules: [{
            condition: {
                maxWidth: 500
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }
});
//图标点击事件函数:编辑用户
function editUSer(id) {
    $.get('/UserData/changeUser',{"id":id},function () {
        location.href = "userForm.html";
    })
}
//图标点击事件函数：跳转历史数据
function historyData(id) {
    $.get('/UserData/changeUser',{"id":id},function () {
        location.href = "historyData.html";
    })
}
//用户表单点击事件
function setAtrribute(id) {
    $.get('/UserData/setAttribute',{"id":id,"count": 1},function () {
        console.log(id);
        var series = chart.series[0];
        while (series.data.length) {
            chart.series[0].data[0].remove();
        }
        console.log(chart.series[0].data);
        requestData();
    })
}
// 点击后刷新事件
function requestData() {//加了id GWY
    $.ajax({
        url: '/UserData/findDataById',
        data: {},
        dataType: "json",
        success: function (response) {
            var series = chart.series[0],
                shift = series.data.length > 500; // 当数据点数量超过 20 个，则指定删除第一个点
//
            // 新增点操作
//           //具体的参数详见：https://api.hcharts.cn/highcharts#Series.addPoint
            chart.series[0].addPoint([Number(response.time), Number(response.ecgData)], true, shift, false);
            //显示数据
//           document.getElementById("userTemp").innerHTML = response.userTemp;
//           document.getElementById("nibp").innerHTML = "130/71";
//
//           document.getElementById("spO2").innerHTML = response.userSpo2;
//           document.getElementById("glucose").innerHTML = 30;
            // 一豪秒后继续调用本函数
            setTimeout(requestData, 10);
        },
        cache: false
    });
}
//图表加载完毕刷新

function refreshData() {
    $.ajax({
        url: '/UserData/refreshData',
        data: {},
        dataType: "json",
        success: function (response) {
            var series = chart.series[0],
                shift = series.data.length > 500; // 当数据点数量超过 500 个，则指定删除第一个点
            // 新增点操作
            chart.series[0].addPoint([Number(response.time), Number(response.ecgData)], true, shift, false);
            setTimeout(requestData, 1000);
        },
        cache: false
    });
}

