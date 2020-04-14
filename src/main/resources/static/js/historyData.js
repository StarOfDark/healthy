// <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
//页面加载完成执行函数
// 发送ajax请求获取数据绘制图像
$(function chart() {
    $.ajax({
        url: '/UserData/findAll',
        data: {},
        dataType: "json",
        success: function (response) {
            console.log(response);


            //绘制曲线
            var data = response;
            // var data = [[1,2],[2,2],[3,6],[4,8],[5,7],[6,2]]
            // detailChart, //
            container = document.getElementById('container'),
                detailContainer = null,
                masterContainer = null;

//创建详细数据图
            function createDetail(masterChart) {
                var detailData = [],
                    detailStart = 0;
                Highcharts.each(masterChart.series[0].data, function (d) {
                    if (d.x >= detailStart) {
                        detailData.push(d.y);
                    }
                });
                detailChart = Highcharts.chart(detailContainer, {
                    chart: {
                        marginBottom: 120,
                        reflow: false,
                        marginLeft: 50,
                        marginRight: 20,
                        backgroundColor: 'transparent',
                        style: {
                            position: 'absolute'
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    title: {
                        text: ''
                    },
                    subtitle: {
                        // text: '通过拖动下方图表选择区域'
                    },
                    xAxis: {
                        //type: 'datetime'
                        maxZoom: 1,
                        labels: {
                            style: {
                                color: '#9faab3'
                            }
                        }
                    },
                    yAxis: {
                        title: {
                            text: null
                        },
                        maxZoom: 0.1
                    },
                    tooltip: {
                        formatter: function () {
                            var point = this.points[0];
                            return '<b>' + Highcharts.numberFormat(point.y, 2);
                        },
                        shared: true
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        series: {
                            marker: {
                                enabled: false,
                                states: {
                                    hover: {
                                        enabled: true,
                                        radius: 3
                                    }
                                }
                            }
                        }
                    },
                    series: [{
                        name: 'ECG',
                        pointStart: detailStart,
                        pointInterval: 0.002,
                        data: detailData
                    }],
                });
            }

//创建导航图
            function createMaster() {
                return Highcharts.chart(masterContainer, {
                    chart: {
                        reflow: false,
                        borderWidth: 0,
                        backgroundColor: null,
                        marginLeft: 50,
                        marginRight: 20,
                        zoomType: 'x',
                        events: {
                            // listen to the selection event on the master chart to update the
                            // extremes of the detail chart
                            selection: function (event) {
                                var extremesObject = event.xAxis[0],
                                    min = extremesObject.min,
                                    max = extremesObject.max,
                                    detailData = [],
                                    xAxis = this.xAxis[0];
                                Highcharts.each(this.series[0].data, function (d) {
                                    if (d.x > min && d.x < max) {
                                        detailData.push([d.x, d.y]);
                                    }
                                });
                                // move the plot bands to reflect the new detail span
                                xAxis.removePlotBand('mask-before');
                                xAxis.addPlotBand({
                                    id: 'mask-before',
                                    from: 0,
                                    to: min,
                                    color: 'rgba(255, 255, 255, 0.2)'
                                });
                                xAxis.removePlotBand('mask-after');
                                xAxis.addPlotBand({
                                    id: 'mask-after',
                                    from: max,
                                    to: 300,
                                    color: 'rgba(255, 255, 255, 0.2)'
                                });
                                detailChart.series[0].setData(detailData);
                                return false;
                            }
                        }
                    },
                    title: {
                        text: null
                    },
                    xAxis: {
                        //type: 'datetime',
                        //showLastTickLabel: true,
                        // fourteen days
                        plotBands: [{
                            id: 'mask-before',
                            // from: Date.UTC(2006, 0, 1),
                            // to: Date.UTC(2008, 7, 1),
                            color: 'rgba(0, 0, 0, 0.2)'
                        }],
                        title: {
                            text: null
                        },
                        labels: {
                            style: {
                                color: '#9faab3'
                            }
                        }
                    },
                    yAxis: {
                        gridLineWidth: 0,
                        labels: {
                            enabled: false
                        },
                        title: {
                            text: null
                        },
                        min: 0.06,
                        showFirstLabel: false
                    },
                    tooltip: {
                        formatter: function () {
                            return false;
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        series: {
                            fillColor: {
                                linearGradient: [0, 0, 0, 70],
                                stops: [
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, 'rgba(255,255,255,0)']
                                ]
                            },
                            lineWidth: 1,
                            marker: {
                                enabled: false
                            },
                            shadow: false,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            enableMouseTracking: false
                        }
                    },
                    series: [{
                        type: 'area',
                        //name: 'USD to EUR',
                        // pointInterval: 24 * 3600 * 1000,
                        // pointStart: Date.UTC(2006, 0, 1),
                        data: data
                    }],
                    exporting: {
                        enabled: false
                    }
                }, function (masterChart) {
                    createDetail(masterChart);
                });
            }

            /*
            * 创建 detailContainer 并 append 到 container 中
            */
            detailContainer = document.createElement('div');
            container.appendChild(detailContainer);
            /*
            * 创建 masterContainer 并 append 到 container 中
            */
            masterContainer = document.createElement('div');
            masterContainer.style.position = 'absolute';
            masterContainer.style.top = '300px';
            masterContainer.style.height = '100px';
            masterContainer.style.width = '100%';
            container.appendChild(masterContainer);
            /*
            * 开始创建导航图，详细的图是在导航图的回调函数中创建的
            * 代码入口
            */
            createMaster();

        },
        cache: false
    });

})
